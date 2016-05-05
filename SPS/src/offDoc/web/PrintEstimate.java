package offDoc.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;



 
import application.model.Applicant;

import estimate.model.MaterialGrid;
import estimate.model.Pcestdtt;
import estimate.model.Pcesthtt;
import estimate.model.Speststd;

public class PrintEstimate implements Printable {

	private Pcesthtt pcesthtt;
	private Speststd speststd;
	private List<MaterialGrid> pcestdttList;
	private Applicant applicant;
	NumberFormat nf = NumberFormat.getInstance();
	
	
	public PrintEstimate(Pcesthtt pcesthtt,Speststd speststd,List<MaterialGrid> pcestdttList,Applicant applicant)
	{
		this.pcesthtt = pcesthtt;
		this.speststd = speststd;
		this.pcestdttList = pcestdttList;
		this.applicant = applicant;
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
	}
	
	public String Print()
    {
          String printResult = "";
          PrinterJob printerJob = PrinterJob.getPrinterJob();

          Book book = new Book(); 
          book.append(new PrintEstimate(pcesthtt,speststd,pcestdttList,applicant), new PageFormat());
          printerJob.setPageable(book);
          boolean doPrint = printerJob.printDialog();
          System.out.println("doPrint "+doPrint);
          if (doPrint) {

                try {
                      printerJob.print();
                      printResult = "Printing...";
                } catch (PrinterException exception) {
                      System.err.println("Printing error: " + exception);
                      printResult = "Error in printing.";
                }
          }
          else
          {
                printResult = "Printing cancelled";
          }
          return printResult;
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
		Font ft =   new Font ("Courier New", Font.PLAIN, 10);
		g2d.setFont(ft);
		/* Use a LineBreakMeasurer instance to break our text into
		 * lines that fit the imageable area of the page.
		 */
		Point2D.Float pen = new Point2D.Float();
		
		 
	
		
		 int x1,x2,x3,x4,x5,x6,x7;
		 int y;
		 int d,d1;
		 x1 =  130;
		 x2 = 330;
		 y = 40;
		 d = 20;
		 d1 = 15;
		 int l = 12;
		 
		 int currentY = 40;
		 
		
		 
		// print line length
		 g2d.drawString( padLeft(speststd.getLineLength().toString() + " m", l)   , x1, currentY);
		 
		 
		 
		 
		 
		 // print application no
		 g2d.drawString(pcesthtt.getId().getEstimateNo(), x2, currentY);
//		 
		 currentY = currentY + d;
		 // print fixed cost
		 g2d.drawString(padLeft(nf.format( speststd.getFixedCost()),l), x1,currentY);
		 
		// print Consumer Name
		 g2d.drawString(applicant.getFirstName()+" "+applicant.getLastName(), x2, currentY);
		 
		 currentY = currentY + d;
		 //print variable cost
		 g2d.drawString(padLeft(nf.format(speststd.getVariableCost()),l), x1, currentY);
		 
		 //print consumer address
		  
		 int tempY = currentY;
		 String tempStreetAddress,tempSuburb,tempCity,tempPostalCode = "";
		 tempStreetAddress = applicant.getStreetAddress() ;
		 tempSuburb = applicant.getSuburb()   ;
		 tempCity = applicant.getCity();
		 if (applicant.getPostalCode() != null)
			 tempPostalCode = applicant.getPostalCode().toString() ;
		 
		 if (tempStreetAddress != null && (! (tempStreetAddress.trim().isEmpty())) )
		 {
			 g2d.drawString(tempStreetAddress ,x2 , tempY);
			 tempY =  tempY + d1;
		 }
			
		 if (tempSuburb   != null && ((!tempSuburb.trim().isEmpty())) )
		 {
			 g2d.drawString( tempSuburb  ,x2 , tempY);
			 tempY =  tempY + d1;
		 }
		 
		 if (tempCity   != null && ( ( ! tempCity.trim().isEmpty())) )
		 {
			 g2d.drawString(tempCity  ,x2 , tempY);
			 tempY =  tempY + d1;
		 }
		 
		 if (tempPostalCode  != null && (! (tempPostalCode.toString().trim().isEmpty())) )
		 {
			 g2d.drawString(tempPostalCode.toString() ,x2 , tempY);
		 }
		 	
		
		
		
		 
		 currentY = currentY + d;
		 //print other cost
		 g2d.drawString(padLeft(nf.format(speststd.getOtherCost()),l), x1, currentY);
		 
		 currentY = currentY + d + 10;
		 //print sub total
		 String subTotal = nf.format(new Float(speststd.getFixedCost().floatValue()+speststd.getVariableCost().floatValue()));
		 g2d.drawString(padLeft(subTotal,l), x1, currentY);
 		 
		 currentY = currentY + d;
		 //print security deposit
		 g2d.drawString(padLeft(nf.format(speststd.getSecurityDeposit()),l), x1, currentY);
		 
		 currentY = currentY + d;
		 //print VAT
		 g2d.drawString(padLeft("0.00",l), x1, currentY);
		 
		 currentY = currentY + d-5;
		 //print TOTAL
		 String totalCost = nf.format(new Float(speststd.getFixedCost().floatValue()+speststd.getVariableCost().floatValue()+speststd.getSecurityDeposit().floatValue()+speststd.getConversionCost().floatValue()+speststd.getOtherCost().floatValue()));
		 g2d.drawString(padLeft(totalCost,l), x1, currentY);
 
		  
		 currentY = currentY + 3 *  d;
		 // draw Line
		 g2d.drawLine(0, currentY, 450, currentY);
		 
		 
		 //////           PRINT DETAILED ESTIMATE                      ///////////////
		// print Heading
		 x1 = 0;
		 x2 = 80;
		 x3 = 250;
		 x4 = 280;
		 x5 = 340;
		 x6 = 390;
		 //int columnWidth = 15;
		 currentY = currentY + d;
		 g2d.drawString(padRight("Item Code",10), x1, currentY);
		 g2d.drawString(padRight("Description",50), x2, currentY);
		 g2d.drawString(padRight("UOM",5) , x3, currentY);
		 g2d.drawString(padLeft("Price",10) , x4, currentY);
		 g2d.drawString(padLeft("Qauntity",10), x5, currentY);
		 g2d.drawString(padLeft("Cost",12) , x6, currentY);
//		
		 
		 currentY = currentY + 10;
		 g2d.drawLine(0, currentY, 450, currentY);
		 currentY = currentY + 10;
		 
		 
 
		 for (MaterialGrid material : pcestdttList) {
			 g2d.drawString(padRight(material.getResCd(),10) , x1, currentY);
			 g2d.drawString(padRight(material.getMatNm(),50), x2, currentY);
			 g2d.drawString(padRight(material.getUom(),5) , x3, currentY);
			 g2d.drawString(padLeft(nf.format(material.getUnitPrice()),10) , x4, currentY);
			 g2d.drawString(padLeft(nf.format(material.getEstimateQty()),10), x5, currentY);
			 g2d.drawString(padLeft(nf.format(material.getEstimateCost()),12), x6, currentY);
			 currentY = currentY + 12;
		}
		 
		// g2d.drawString("Sub Total for the Material" , x1, currentY);
		 
		return Printable.PAGE_EXISTS;
	}
	public static String padLeft(String s, int n) {
	    return String.format("%1$#" + n + "s", s);
	  }
	 public static String padRight(String s, int n) {
		    return String.format("%1$-" + n + "s", s);
		  }
}
