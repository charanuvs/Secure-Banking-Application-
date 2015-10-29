package edu.asu.securebanking.view;

import edu.asu.securebanking.beans.Account;
import edu.asu.securebanking.beans.AppUser;
import edu.asu.securebanking.beans.Transaction;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Vikranth on 10/28/2015.
 */
public class AccountPdfView extends AbstractView {

	private static NumberFormat NUMBER_FORMAT = NumberFormat.getNumberInstance(Locale.US);

	private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yy");

	private static Font TIME_ROMAN = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private static Font TIME_ROMAN_SMALL = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

	@Override
	/**
	 * 
	 */
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		AppUser appUser = (AppUser) model.get("appUser");
		final ServletContext servletContext = request.getSession().getServletContext();
	    final File directory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
	    final String filePath = directory.getAbsolutePath();
	    String userName = appUser.getUserId();
	    String fileName = userName+"_account_summary.pdf";
	    response.setContentType("application/pdf");
	    response.setHeader("Content-disposition", "attachment; filename="+ fileName);
	    
	    try {
	        createPDF(filePath+"\\"+fileName, model, request, response);
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        baos = convertPDFToByteArrayOutputStream(filePath+"\\"+fileName);
	        OutputStream os = response.getOutputStream();
	        baos.writeTo(os);
	        os.flush();
	    } catch (Exception e1) {
	        e1.printStackTrace();
	    }
	}
	
	/**
	 * 
	 * @param file
	 * @param model
	 * @param workbook
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public static Document createPDF(String file, Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		@SuppressWarnings("unchecked")
		List<Transaction> transactions = (List<Transaction>) model.get("transactions");
		Account account = (Account) model.get("account");
		AppUser appUser = (AppUser) model.get("appUser");

		Document document = null;

		try {
			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();

			addAccountSummaryHeaders(document);

			addAccountSummaryInfo(document, account);

			createAccountTransactionTable(document, transactions, appUser);

			document.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;

	}

	/**
	 * 
	 * @param document
	 */
	private static void addAccountSummaryHeaders(Document document) {
		document.addTitle("Account Summary");
		document.addSubject("Account Summary");
		document.addAuthor("SS group 10");
		document.addCreator("SS group 10");
	}

	/**
	 * 
	 * @param document
	 * @param account
	 * @throws DocumentException
	 */
	private static void addAccountSummaryInfo(Document document, Account account) throws DocumentException {

		Paragraph preface = new Paragraph();
		addBlankLines(preface, 1);
		preface.add(new Paragraph("Account Summary :", TIME_ROMAN));

		addBlankLines(preface, 1);
		preface.add(new Paragraph("Transaction Information for Account :" + String.valueOf(account.getAccountNum()), TIME_ROMAN));

		preface.add(new Paragraph("Current balance: $" + String.valueOf(account.getBalance().doubleValue()), TIME_ROMAN));

		addBlankLines(preface, 1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		preface.add(
				new Paragraph("Account summary generated on " + simpleDateFormat.format(new Date()), TIME_ROMAN_SMALL));
		document.add(preface);

	}

	/**
	 * 
	 * @param paragraph
	 * @param number
	 */
	private static void addBlankLines(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	/**
	 * 
	 * @param document
	 * @param transactions
	 * @param appUser
	 * @throws DocumentException
	 */
	private static void createAccountTransactionTable(Document document, List<Transaction> transactions, AppUser appUser)
			throws DocumentException {
		Paragraph paragraph = new Paragraph();
		addBlankLines(paragraph, 2);
		document.add(paragraph);
		PdfPTable pdfPTable = new PdfPTable(6);

		if (transactions == null || transactions.size() == 0) {
			PdfPCell cell = new PdfPCell(new Phrase("No transactions for this account"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfPTable.addCell(cell);
			return;
		}

		PdfPCell cell = new PdfPCell(new Phrase("Date"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pdfPTable.addCell(cell);

		cell = new PdfPCell(new Phrase("From Account"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pdfPTable.addCell(cell);

		cell = new PdfPCell(new Phrase("To Account"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pdfPTable.addCell(cell);

		cell = new PdfPCell(new Phrase("Amount"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pdfPTable.addCell(cell);

		cell = new PdfPCell(new Phrase("Transaction Type"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pdfPTable.addCell(cell);

		cell = new PdfPCell(new Phrase("Transaction Status"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pdfPTable.addCell(cell);
		pdfPTable.setHeaderRows(1);

		for (Transaction transaction : transactions) {
			pdfPTable.setWidthPercentage(100);
			pdfPTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfPTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			pdfPTable.addCell(DATE_FORMAT.format(transaction.getDate()));

			// from account
			if (transaction.getFromAccount().getUser().getUserId().equals(appUser.getUserId()))
				pdfPTable.addCell(transaction.getFromAccount().getAccountNum() + " (Your account)");
			else
				pdfPTable.addCell(transaction.getFromAccount().getUser().getName());

			// to account
			if (transaction.getToAccount().getUser().getUserId().equals(appUser.getUserId()))
				pdfPTable.addCell(transaction.getToAccount().getAccountNum() + " (Your account)");
			else
				pdfPTable.addCell(transaction.getToAccount().getUser().getName());

			// amount
			pdfPTable.addCell(NUMBER_FORMAT.format(transaction.getAmount()));

			// transaction type
			pdfPTable.addCell(transaction.getTransactionType());

			// transaction status
			pdfPTable.addCell(transaction.getStatus());
		}

		document.add(pdfPTable);
	}
	
	private ByteArrayOutputStream convertPDFToByteArrayOutputStream(String fileName) {

		InputStream inputStream = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {

			inputStream = new FileInputStream(fileName);
			byte[] buffer = new byte[1024];
			baos = new ByteArrayOutputStream();

			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				baos.write(buffer, 0, bytesRead);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return baos;
	}
}
