package estimate.web;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Rectangle;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Rectangle;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
//import java.awt.print.Printable;
//import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import com.sun.pdfview.PDFFile;
//import com.sun.pdfview.PDFPage;
//import com.sun.pdfview.PDFRenderer;
//import com.sun.pdfview.PDFPage;
//import com.sun.pdfview.PDFPrintPage;

//import com.sun.pdfview.PDFRenderer;
public class PrintPdf {

	private PrinterJob pjob = null;

	/**
	 * @param args
	 */

	public PrintPdf(InputStream inputStream, String jobName)
			throws IOException, PrinterException {
		byte[] pdfContent = new byte[inputStream.available()];
		inputStream.read(pdfContent, 0, inputStream.available());
		initialize(pdfContent, jobName);
	}

	/*public PrintPdf(byte[] content, String jobName) throws IOException,
			PrinterException {
		initialize(content, jobName);
	}*/

	private void initialize(byte[] pdfContent, String jobName)
			throws IOException, PrinterException {
		ByteBuffer bb = ByteBuffer.wrap(pdfContent);
		// Create PDF Print Page
		PDFFile pdfFile = new PDFFile(bb);
		//PDFPrintPage pages = new PDFPrintPage(pdfFile);
		PdfPrintPage pages =new PdfPrintPage(pdfFile);
		// Create Print Job
		pjob = PrinterJob.getPrinterJob();
		
		PageFormat pf = PrinterJob.getPrinterJob().defaultPage();
		pf.setOrientation(PageFormat.PORTRAIT);
		pjob.setJobName(jobName);
		Book book = new Book();
		book.append(pages, pf, pdfFile.getNumPages());
		pjob.setPageable(book);

		// to remove margins
		Paper paper =new Paper();
		//paper.setSize(210, 297);
		paper.setImageableArea(1,1, paper.getWidth(), paper.getHeight());
		pf.setPaper(paper);
	}

	public void print() throws PrinterException {
		pjob.print();
	}
}
