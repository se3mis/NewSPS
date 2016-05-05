package print.standardEstimate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;

//import org.apache.jasper.tagplugins.jstl.core.ForEach;

//import util.common.ManopApp;

import application.model.Applicant;

import estimate.model.Pcestdtt;
import estimate.model.Pcesthtt;
import estimate.model.Speststd;

public class PrintEstimate implements Printable {

	private Pcesthtt pcesthtt;
	private Speststd speststd;
	private ArrayList<Pcestdtt> pcestdttList;
	private Applicant applicant;
	public PrintEstimate(Pcesthtt pcesthtt,Speststd speststd,ArrayList<Pcestdtt> pcestdttList,Applicant applicant)
	{
		this.pcesthtt = pcesthtt;
		this.speststd = speststd;
		this.pcestdttList = pcestdttList;
		this.applicant = applicant;
	}
	
	public void Print()
	{
		PrinterJob printerJob = PrinterJob.getPrinterJob();

		Book book = new Book();	
		book.append(new PrintEstimate(pcesthtt,speststd,pcestdttList,applicant), new PageFormat());
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
		/*01                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
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
		
		
//		for (int i = 0; i < 1000; i= i + 30) {
//			 g2d.drawString(String.valueOf(i) , i, 80);
//			
//		}
		
		// print line length
		 g2d.drawString("Line Length", 100, 20);
		 
//		 // print application no
//		 g2d.drawString("Applicatin no", 30, 20);
//		 
//		 // print fixed cost
//		 g2d.drawString("fixed cost", 300, 10);
//		 
//		// print Consumer Name
//		 g2d.drawString("consumer name", 300, 10);
//		 
//		 //print variable cost
//		 g2d.drawString("variable cost", 600, 10);
//		 
//		 //print consumer address
//		 g2d.drawString("consumer address", 600, 10);
//		 
//		 //print other cost
//		 g2d.drawString("other cost", 600, 10);
//		 
//		 //print sub total
//		 g2d.drawString("sub total", 600, 10);
//		 
//		 //print security deposite
//		 g2d.drawString("security deposite", 600, 10);
//		 
//		 //print VAT
//		 g2d.drawString("VAT", 600, 10);
//		 
//		 //print TOTAL
//		 g2d.drawString("total", 600, 10);
//		 
//		 //print estimated date
//		 g2d.drawString("estimated date", 600, 10);
//		 
//		  
//		  for (Pcestdtt element : pcestdttList) {
//			  g2d.drawString("total", 600, 10);
//		}
		  

		return Printable.PAGE_EXISTS;
	}
}
