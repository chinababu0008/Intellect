package com.chinna.wordToPdf.Test1;

import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author 		 : Chinna
 * @Created Date : 12/02/2018
 * @Usage  		 : Used to Add DND Header and Footer to the PDF  
 */
public class HeaderFooterPageEvent extends PdfPageEventHelper {

	private PdfTemplate t;
	private Image total;
	protected float tableHeight;

	public void onOpenDocument(PdfWriter writer, Document document) {
		t = writer.getDirectContent().createTemplate(20, 20);
		try {
			total = Image.getInstance(t);
		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		}
	}

	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		try{
		addHeader(writer);
		addFooter(writer);
		}catch(Exception exception){
			exception.printStackTrace();
		}
	}

	public float getTableHeight() {
		return tableHeight;
	}

	private void addHeader(PdfWriter writer) throws DocumentException, MalformedURLException, IOException {
		PdfPTable header = new PdfPTable(1);
	
			// set defaults
			header.setWidths(new int[] { 64 });
			header.setTotalWidth(527);
			header.setLockedWidth(true);
			header.getDefaultCell().setFixedHeight(75);
			header.getDefaultCell().setBorder(Rectangle.BOTTOM);
			header.getDefaultCell().setBorderColor(BaseColor.WHITE);
			header.getDefaultCell()
					.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableHeight = header.getTotalHeight();
			// add image
			Image logo = Image
					.getInstance("D:\\Project\\Do_Not_Delete_Images\\DNDHeaderLogo.jpg");

			header.addCell(logo);
			// write content
			header.writeSelectedRows(0, -1, 34, 840, writer.getDirectContent());
		
	}

	private void addFooter(PdfWriter writer) throws DocumentException, MalformedURLException, IOException {
		PdfPTable footer = new PdfPTable(1);
			// set defaults
			footer.setWidths(new int[] { 64 });
			footer.setTotalWidth(527);
			footer.setLockedWidth(true);
			footer.getDefaultCell().setBorder(Rectangle.TOP);
			footer.getDefaultCell().setBorderColor(BaseColor.BLACK);
			footer.getDefaultCell().setFixedHeight(150);
			footer.getDefaultCell()
					.setHorizontalAlignment(Element.ALIGN_CENTER);

			// add copyright
			Image logo = Image
					.getInstance("D:\\Project\\Do_Not_Delete_Images\\DNDFootererLogo.jpg");
			footer.addCell(logo);
			// write page
			PdfContentByte canvas = writer.getDirectContent();
			canvas.beginMarkedContentSequence(PdfName.ACTIVATION);
			footer.writeSelectedRows(0, -1, 34, 80, canvas);
	}

	public void onCloseDocument(PdfWriter writer, Document document) {
		int totalLength = String.valueOf(writer.getPageNumber()).length();
		int totalWidth = totalLength * 5;
		ColumnText.showTextAligned(t, Element.ALIGN_RIGHT, new Phrase(String.valueOf(writer.getPageNumber()), new Font( Font.FontFamily.TIMES_ROMAN, 8)), totalWidth, 15, 0);
	}
}
