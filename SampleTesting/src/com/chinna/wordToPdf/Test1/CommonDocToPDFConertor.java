package com.chinna.wordToPdf.Test1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author 		 : Chinna
 * @Created Date : 12/02/2018
 * @Usage  		 : Converts Doc File To DocX and converts DocX File to PDF  
 */
public class CommonDocToPDFConertor {
	public static String convertDocToDocx(String srcFile, String destFile)
			throws Exception {

		Document doc = new Document(srcFile);
		doc.save(destFile);
		return destFile;
	}

	public static String readDocxFile(String srcFile) throws IOException {
		String output = "";
		String checkOutput = "";

		File file = new File(srcFile);
		FileInputStream fis = new FileInputStream(file.getAbsolutePath());
		XWPFDocument document = new XWPFDocument(fis);
		List<XWPFParagraph> paragraphs = document.getParagraphs();
		for (XWPFParagraph para : paragraphs) {
			checkOutput = para.getText();
			if (checkOutput != null
					&& !checkOutput
							.equals("Evaluation Only. Created with Aspose.Words. Copyright 2003-2011 Aspose Pty Ltd.")) {
				if (checkOutput.isEmpty()) {
					checkOutput = "NEW_LINE";
				}
				output = output + "\n" + checkOutput + "\n";
			}
		}
		fis.close();

		return output;
	}

	public static String writePdfFile(String output, String descFile)
			throws FileNotFoundException, DocumentException {
		HeaderFooterPageEvent event = new HeaderFooterPageEvent();
		com.itextpdf.text.Document document = new com.itextpdf.text.Document(
				PageSize.A4, 33, 33, 75, 80);
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream(descFile));

		// add header and footer
		writer.setPageEvent(event);

		// write to document
		document.open();
		Paragraph paragraph = new Paragraph();
		paragraph.setSpacingBefore(1);
		paragraph.setSpacingAfter(2);
		paragraph.setLeading(1, 1);
		String[] splitter = output.split("\\n");
		for (int i = 0; i < splitter.length; i++) {
			Chunk chunk = new Chunk(splitter[i]);
			if (splitter[i] != null && splitter[i].equalsIgnoreCase("NEW_LINE")) {
				Paragraph newLineParagraph = new Paragraph();
				newLineParagraph.setSpacingBefore(2);
				newLineParagraph.setSpacingAfter(3);
				document.add(newLineParagraph);
				document.add(newLineParagraph);
			} else {

				document.add(chunk);
			}
			document.add(paragraph);
		}
		document.close();
		return "SUCCESS";
	}

	public static String  generateDocToPDF(String sourceDocFileName,
			String destinationDocxFileName, String destinationPDFFileName)
			throws DocumentException, IOException,
			com.itextpdf.text.DocumentException {
		String result = "FAILED";
		try {
			convertDocToDocx(sourceDocFileName, destinationDocxFileName); // Convert Doc file to Docx File
			
			String output = readDocxFile(destinationDocxFileName); // Read Docx file to String
			
			result = writePdfFile(output, destinationPDFFileName); // Create PDF File
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return result;
	}
	public String  generateDocxToPDF( String destinationDocxFileName, String destinationPDFFileName)
			throws DocumentException, IOException,
			com.itextpdf.text.DocumentException {
		String result = "FAILED";
		try {
			String output = readDocxFile(destinationDocxFileName); // Read Docx file to String
			
			//result = writePdfFile(output, destinationPDFFileName); // Create PDF File
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return result;
	}
	public static void main(String[] args) {

		try {
			Document doc = new Document("C:\\Users\\DEP00010\\Desktop\\678-2018_Convention with priority - Request_07-06-2018-12-33-01-190.doc");
			doc.save("C:\\Users\\DEP00010\\Desktop\\678-2018" + "Out.docx", SaveFormat.DOCX);
		System.out.println("success");
			//convertDocToDocx("C:\\Users\\DEP00010\\Desktop\\678-2018_Convention with priority - Request_07-06-2018-12-33-01-190.doc","C:\\Users\\DEP00010\\Desktop\\678-2018_Convention.docx");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
