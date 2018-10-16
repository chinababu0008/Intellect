package com.chinna.wordToPdf.Test1;

import com.aspose.words.Document;

public class AsposeDocToPdfTest {

	
	public static void main(String[] args) {
		// Open document.
		try {
		Document doc = new Document("\\\\docgen-prod\\documents$\\pms\\IBM_AUTOFORWARD\\Test\\Pre-grant publication_DND_M.doc");

		// Accept all changes.
			doc.acceptAllRevisions();
		// Save document to PDF.
		doc.save("C:\\Users\\DEP00010\\Desktop\\DND_A.pdf");
		System.out.println("Successfully PDF Document Generated ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
