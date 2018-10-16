package com.chinna.wordToPdf.Test1;

import java.io.File;
import java.io.FileOutputStream;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class DocCreator {

	public static void createDocFile(String fileName) {

		try {
			File file = new File(fileName);
			FileOutputStream fos = new FileOutputStream(file.getAbsolutePath());

			XWPFDocument doc = new XWPFDocument();
			XWPFParagraph tempParagraph = doc.createParagraph();
			XWPFRun tempRun = tempParagraph.createRun();

			tempRun.setText("This is a Paragraph");
			tempRun.setFontSize(12);
			doc.write(fos);
			fos.close();
			
			System.out.println(file.getAbsolutePath()+ " created successfully!");

		} catch (Exception e) {

		}

	}

	public static void main(String[] args) {
		
		//create docx file
		createDocFile("D:\\DocxFile.docx");
		
		//create doc file
		createDocFile("D:\\DocFile.doc");

	}

}
