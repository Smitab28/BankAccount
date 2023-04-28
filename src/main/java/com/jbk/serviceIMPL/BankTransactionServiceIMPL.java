package com.jbk.serviceIMPL;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jbk.dao.AccountDao;
import com.jbk.dao.BankTransactionDao;
import com.jbk.entity.Account;
import com.jbk.entity.BankTransaction;
import com.jbk.entity.Payment;
import com.jbk.model.TransactionHistory;
import com.jbk.service.BankTransactionService;

@Service
public class BankTransactionServiceIMPL implements BankTransactionService {

	@Autowired
	BankTransactionDao dao;
	@Autowired 
	AccountDao accountDao;
	
	@Override
	public String performTransaction(BankTransaction bankTransaction) {
		String accountNumber =	bankTransaction.getAccount().getAccountNumber();	
		if(bankTransaction.getTransactionType().equalsIgnoreCase("credit")) {
			Account account = accountDao.getAccountByAccountNumber(accountNumber);
			if(account!=null) {
				account.setAccountBalance(account.getAccountBalance()+bankTransaction.getAmount());
				bankTransaction.setStatus("successful");
				account.setUpdatedAt(LocalDateTime.now());
				account.setCreatedAt(account.getCreatedAt());
				bankTransaction.setReasonCode("done");
				accountDao.updateAccountBalance(account);
			}
			else {
				bankTransaction.setStatus("Fail");
				bankTransaction.setReasonCode("No such account exist");
		   }
		}else if(bankTransaction.getTransactionType().equalsIgnoreCase("debit")) {
			Account account = accountDao.getAccountByAccountNumber(accountNumber);
			if(account!=null) {
				if(account.getAccountBalance()>bankTransaction.getAmount()) {
						account.setAccountBalance(account.getAccountBalance()-bankTransaction.getAmount());
						bankTransaction.setStatus("successful");
						bankTransaction.setReasonCode("done");
						account.setUpdatedAt(LocalDateTime.now());
						account.setCreatedAt(account.getCreatedAt());
						accountDao.updateAccountBalance(account);
				}
				else {
						bankTransaction.setStatus("Fail");
						bankTransaction.setReasonCode("Insufficient Balance for this transaction");
						
				}
			}
			else {
				bankTransaction.setStatus("Fail");
				bankTransaction.setReasonCode("No such account exist");
				
		   }
		}
		bankTransaction.setSource("CASH");
		bankTransaction.setUpdatedAt(LocalDateTime.now());
		bankTransaction.setCreatedAt(LocalDateTime.now());
				
		return dao.performTransaction(bankTransaction);
	}

	@Override
	public BankTransaction getTransactionDetails(int transactionId) {
		return dao.getTransactionDetails(transactionId);
	}

	@Override
	public String performAccountToAcountTransactions(BankTransaction bankTransaction) {
		String msg =null;
		if(bankTransaction.getSource()==null)
			msg="Source is Mandatory";
		else {
			Account destinationAccount = accountDao.getAccountByAccountNumber(bankTransaction.getAccount().getAccountNumber());
			if(destinationAccount!=null) {
				Account sourceAccount = accountDao.getAccountByAccountNumber(bankTransaction.getSource());
				if(sourceAccount.getAccountBalance()>bankTransaction.getAmount()) {
					destinationAccount.setAccountBalance(destinationAccount.getAccountBalance()+bankTransaction.getAmount());
					accountDao.updateAccountBalance(destinationAccount);
					sourceAccount.setAccountBalance(sourceAccount.getAccountBalance()-bankTransaction.getAmount());
					accountDao.updateAccountBalance(sourceAccount);
					bankTransaction.setStatus("Done");
					bankTransaction.setReasonCode("Successful");
				}else {
					bankTransaction.setStatus("Fail");
					bankTransaction.setReasonCode("Insufficient funds in Source Account");
				}	
			}
			else{
				bankTransaction.setStatus("Fail");
				bankTransaction.setReasonCode("No Destination Account exist for transfer");
			}
			bankTransaction.setSource("Cash");
			bankTransaction.setCreatedAt(LocalDateTime.now());
			bankTransaction.setUpdatedAt(LocalDateTime.now());
			msg = dao.performTransaction(bankTransaction);
		}
		return msg;
	}

	@Override
	public List<TransactionHistory> getTransactionDetailsOfAccountNumber(String accountNumber) {
		
		return dao.getTransactionDetailsOfAccountNumber(accountNumber);
	}
	
	@Override
	public boolean perfomPayment(Payment payment) {
		return dao.perfomPayment(payment);
	}

	private XSSFWorkbook write(List<TransactionHistory> listOfTransactions) {
		XSSFWorkbook workbook = new XSSFWorkbook(); 
	    XSSFSheet sheet;
        sheet = workbook.createSheet("Transaction History");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "transactionId");
        createCell(row, 1, "accountNumber");
        createCell(row, 2, "Source accountNumber");
        createCell(row, 3, "userId");
        createCell(row, 4, "transactionType");
        createCell(row, 5, "transactionAmount");
        createCell(row, 6, "transactionStatus");
        createCell(row, 7, "transactionReasonCode");
        createCell(row, 8, "transactionCreatedAt");
        
        int rowCount = 1;
        for (TransactionHistory transactionHistory: listOfTransactions) {
             row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, transactionHistory.getTransactionId());
            createCell(row, columnCount++, transactionHistory.getTransactionAccountNumber());
            createCell(row, columnCount++, transactionHistory.getTransactionSource());
            createCell(row, columnCount++, transactionHistory.getUserId());
            createCell(row, columnCount++, transactionHistory.getTransactionType());
            createCell(row, columnCount++, transactionHistory.getTransactionAmount());
            createCell(row, columnCount++, transactionHistory.getTransactionStatus());
            createCell(row, columnCount++, transactionHistory.getTransactionReasonCode());
            createCell(row, columnCount++, transactionHistory.getTransactionCreatedAt());
        }
        
        return workbook;
        
    }
    private void createCell(Row row, int columnCount, Object valueOfCell) {
       // sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else if(valueOfCell instanceof Double) {
        	cell.setCellValue((double) valueOfCell);
        } else if(valueOfCell instanceof LocalDateTime) {
        	cell.setCellValue((LocalDateTime)valueOfCell);
        }
        else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        
    }
    
    public String generateExcelFile(List<TransactionHistory> listOfTransactions) {
    	
    	XSSFWorkbook workbook = write(listOfTransactions);
        String filePath = System.getProperty("user.home");//dynamic path
        filePath = filePath+"/Downloads/TransactionDetails.xlsx";
		File file = new File(filePath);	
        FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			workbook.write(fos);
			workbook.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
        
        return "Export into Excel Successfully";
    }

	@Override
	public List<TransactionHistory> getAllTransactionDetails() {
		return dao.getAllTransactionDetails();
	}
	
	
}
