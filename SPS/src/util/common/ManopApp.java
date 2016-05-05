package util.common;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextLayout;
import java.awt.geom.Point2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.AttributedCharacterIterator;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import application.service.ApplicantEjb;

public class ManopApp implements Printable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//		// TODO Auto-generated method stub
		//		for (int i = 0; i < 3; i++) {
		//			double payment = i * 3453443;
		//		    System.out.println("Your payment is ");
		//		    NumberFormat nf = NumberFormat.getInstance();
		//		    nf.setMaximumFractionDigits(2);
		//		    nf.setMinimumFractionDigits(2);
		//		    System.out.println(nf.format(payment));
		//		}
		//		String dt = "2009-08-07";
		//		Date dd = new Date( );
		//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd");
		//		System.out.println("dd :" + sdf.format(dd));
		//		
		//		System.out.println("aaaaaaaaaa");
		//		
		//		ApplicantEjb applicantEjb=new ApplicantEjb();
		//		 
		//		System.out.println(applicantEjb.findById("34567890"));
		//		System.out.println(applicantEjb.findById("123"));

		System.out.println("@######################################################################################");

		String input = "1345345 233";
		Boolean xx = input.matches("^[0-9 ]*$");
		System.out.println("xx" + xx);
		System.out.println("@######################################################################################");

		System.out.println("Printing Module");
		PrinterJob printerJob = PrinterJob.getPrinterJob();

		Book book = new Book();	
		book.append(new ManopApp(), new PageFormat());
		printerJob.setPageable(book);
		boolean doPrint = printerJob.printDialog();
		if (doPrint) {

			try {
				printerJob.print();
			} catch (PrinterException exception) {
				System.err.println("Printing error: " + exception);
			}
		}
		
		
	}

	@Override
	public int print(Graphics g, PageFormat format, int pageIndex)
	throws PrinterException {
		/* We'll assume that Jav2D is available.
		 */
		Graphics2D g2d = (Graphics2D) g;
		/* Move the origin from the corner of the Paper to the corner
		 * of the imageable area.
		 */
		g2d.translate(format.getImageableX(), format.getImageableY());
		/* Set the text color.
		 */
		g2d.setPaint(Color.black);
		/* Use a LineBreakMeasurer instance to break our text into
		 * lines that fit the imageable area of the page.
		 */
		Point2D.Float pen = new Point2D.Float();
		 g2d.drawString(".10, 10", 10, 10);
		 g2d.drawString(".300, 10", 300, 10);
		 g2d.drawString(".600, 10", 600, 10);
		 g2d.drawString(".0, 400", 10, 400);
		 g2d.drawString(".300, 400", 300, 400);
		 g2d.drawString(".600, 400", 600, 400);
		 g2d.drawString(".10, 700", 10, 600);
		 g2d.drawString(".300, 700", 300, 600);
		 g2d.drawString(".600, 700", 600, 600);
		  
		// g2d.drawString(":", 60, 30);
		// g2d.drawString("Meemanage Manoj Lakmal Kumara Perera", 80, 30);
		 
//		 g2d.drawString("Address", 20, 45);
//		 g2d.drawString(":", 60, 45);
//		 g2d.drawString("95 c Kalalgoda, Pannipitiya", 60, 45);
		return Printable.PAGE_EXISTS;
	}
	
	

}
