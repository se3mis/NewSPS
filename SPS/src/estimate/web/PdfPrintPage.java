package estimate.web;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PDFRenderer;


public class PdfPrintPage implements Printable{
	private PDFFile file;

	PdfPrintPage(PDFFile file) {
		this.file = file;
	}
	@Override
	public int print(Graphics g, PageFormat format, int index)
			throws PrinterException {
		int pagenum = index + 1;
		if ((pagenum >= 1) && (pagenum <= file.getNumPages())) {
			Graphics2D g2 = (Graphics2D) g;
			
			g2.setFont(new Font("Calibri", Font.PLAIN, 12));
		    g2.setPaint(Color.black);
			PDFPage page = file.getPage(pagenum);

			// fit the PDFPage into the printing area
			Rectangle imageArea = new Rectangle((int) format.getImageableX(), (int) format.getImageableY(),
					(int) format.getImageableWidth(), (int) format.getImageableHeight());
			g2.translate(0, 0);
			PDFRenderer pgs = new PDFRenderer(page, g2, imageArea, null, null);
			try {
				page.waitForFinish();
				pgs.run();
			} catch (InterruptedException ie) {
				// nothing to do
			}
			return PAGE_EXISTS;
		} else {
			return NO_SUCH_PAGE;
		}
	}

}
