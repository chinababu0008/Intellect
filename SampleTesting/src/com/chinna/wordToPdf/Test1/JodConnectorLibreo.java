package com.chinna.wordToPdf.Test1;

import java.io.File;
import java.util.Collections;

import bboss.org.artofsolving.jodconverter.OfficeDocumentConverter;
import bboss.org.artofsolving.jodconverter.document.DocumentFamily;
import bboss.org.artofsolving.jodconverter.document.DocumentFormat;
import bboss.org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import bboss.org.artofsolving.jodconverter.office.OfficeManager;

public class JodConnectorLibreo {
	
	public static void main(String[] args) {
			File inputFile = new File("C:/Users/DEP00010/Desktop/678-2018.doc");
	    	File outputDOCXFile = new File("C:/Users/DEP00010/Desktop/678-2018_docx.docx");
	    	File outputPDFFile = new File("C:/Users/DEP00010/Desktop/678-2018_pdf.pdf"); 
			OfficeManager officeManager = new DefaultOfficeManagerConfiguration() .setOfficeHome(new File("C:/Program Files (x86)/LibreOffice 4")) .buildOfficeManager();
			
			// Start the Open Office using below code
			//C:\Program Files (x86)\LibreOffice 4\program>soffice -headless -accept="socket,host=127.0.0.1,port=8100;urp;" -nofirststartwizard
			officeManager.start();
			
			OfficeDocumentConverter converter = new OfficeDocumentConverter( officeManager);
			
			DocumentFormat docx = converter.getFormatRegistry().getFormatByExtension("docx");
			docx.setStoreProperties(DocumentFamily.TEXT, Collections.singletonMap("FilterName", "MS Word 2007 XML"));
			
			converter.convert(inputFile, outputDOCXFile, docx);
		    System.err.println(outputDOCXFile+" : Genarated successfully");
		    
		    converter.convert(inputFile, outputPDFFile);
			 System.err.println(outputPDFFile+" : Genarated successfully");
	}
	 

}

