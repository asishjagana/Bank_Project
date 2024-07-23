package com.daren.utils;

import com.daren.entity.TransactionDtlEntity;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class PdfGenerator {

	public void generatePdf(HttpServletResponse response, List<TransactionDtlEntity> transactions, File file)
			throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();
		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(20);

		Paragraph paragraph1 = new Paragraph("List Of Transactions", fontTiltle);

		paragraph1.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(paragraph1);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.0f });
		table.setSpacingBefore(10);

		cell.setPhrase(new Phrase("TransId", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Amount Transferred", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Sender", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Transaction Date", font));
		table.addCell(cell);
		Font font1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font1.setSize(18);
		font1.setColor(Color.BLUE);
		for (TransactionDtlEntity trans : transactions) {
			table.addCell(String.valueOf(trans.getTransId()));

			table.addCell(String.valueOf(trans.getAmountToTransfer()));

			table.addCell(trans.getRecevierPhoneno());

			table.addCell(String.valueOf(trans.getCreatedDate()));
		}

		document.add(table);

		document.close();

	}

}