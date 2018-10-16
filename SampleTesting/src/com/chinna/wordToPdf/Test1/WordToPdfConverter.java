package com.chinna.wordToPdf.Test1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

public class WordToPdfConverter {
	public static void main(String[] args) {
		convertWordToPdf(
				"C:\\Users\\DEP00010\\Desktop\\Pre-grant publication_DND_A.docx", "C:\\Users\\DEP00010\\Desktop\\DND_A.pdf");
	}

	public static void convertWordToPdf(String src, String desc) {
		try {
			FileInputStream fs = new FileInputStream(src);
			XWPFDocument doc = new XWPFDocument(fs);
			Document pdfdoc = new Document(PageSize.A4, 72, 72, 72, 72);
			PdfWriter pwriter = PdfWriter.getInstance(pdfdoc, new FileOutputStream(desc));
			pwriter.setInitialLeading(20);
			List<XWPFParagraph> plist = doc.getParagraphs();
			pdfdoc.open();
			for (int i = 0; i < plist.size(); i++) {
				XWPFParagraph pa = plist.get(i);
				List<XWPFRun> runs = pa.getRuns();
				for (int j = 0; j < runs.size(); j++) {
					XWPFRun run = runs.get(j);
					List<XWPFPicture> piclist = run.getEmbeddedPictures();
					Iterator<XWPFPicture> iterator = piclist.iterator();
					while (iterator.hasNext()) {
						XWPFPicture pic = iterator.next();
						XWPFPictureData picdata = pic.getPictureData();
						byte[] bytepic = picdata.getData();
						Image imag = Image.getInstance(bytepic);
						pdfdoc.add(imag);

					}
					int color = getCode(run.getColor());
					Font f = null;
					if (run.isBold() && run.isItalic())
						f = FontFactory.getFont(FontFactory.TIMES_ROMAN, run.getFontSize(), Font.BOLDITALIC, new BaseColor(color));
					else if (run.isBold())
						f = FontFactory .getFont(FontFactory.TIMES_ROMAN, run.getFontSize(), Font.BOLD, new BaseColor(color));
					else if (run.isItalic())
						f = FontFactory.getFont(FontFactory.TIMES_ROMAN, run .getFontSize(), Font.ITALIC, new BaseColor( color));
					else if (run.isStrike())
						f = FontFactory.getFont(FontFactory.TIMES_ROMAN, run.getFontSize(), Font.STRIKETHRU, new BaseColor(color));
					else
						f = FontFactory.getFont(FontFactory.TIMES_ROMAN, run .getFontSize(), Font.NORMAL, new BaseColor( color));
					String text = run.getText(-1);
					byte[] bs;
					if (text != null) {
						bs = text.getBytes();
						String str = new String(bs, "UTF-8");
						Chunk chObj1 = new Chunk(str, f);
						pdfdoc.add(chObj1);
					}

				}
				pdfdoc.add(new Chunk(Chunk.NEWLINE));
			}
			pdfdoc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getCode(String code) {
		int colorCode;
		if (code != null)
			colorCode = Long.decode("0x" + code).intValue();
		else
			colorCode = Long.decode("0x000000").intValue();
		return colorCode;
	}

}