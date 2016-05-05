package offDoc.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import job.model.Pcesthmt;

public class JobTransferredReport implements Printable {

	private List<Pcesthmt> pcesthmtList;
	private Date transferredDate;
	private String costCenter;
	private String transferredBy;
	NumberFormat nf = NumberFormat.getInstance();
	
	public JobTransferredReport(List<Pcesthmt> list,Date transferredDate,String costCenter,String transferredBy)
	{
		this.pcesthmtList = list;
		this.transferredDate = transferredDate;
		this.costCenter = costCenter;
		this.transferredBy = transferredBy;
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
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
		Font ft =   new Font ("Courier New", Font.PLAIN, 10);
		Font ft1 =   new Font ("Courier New", Font.BOLD, 15);
		g2d.setFont(ft);

		  double total =0;
		float x1,x2;
		float y1,y2;

		x1 = 150;
		y1 = 10;
		float d = (float) 5;

		g2d.setFont(ft1);
		g2d.drawString("JOB TRANSFERRED REPORT" , x1, y1);
		y1 = y1 + 15;
		
		g2d.drawString("CEYLON ELECTRICITY BOARD" , x1, y1);
		 
		x1 = 10;
		g2d.setFont(ft);
		y1 = y1 + 30;
		g2d.drawString(padRight("Transferred Date",30) + ": " + this.transferredDate  , x1, y1);
		y1 = y1 + 10;
		g2d.drawString(padRight("Transferred By",30) + ": " + this.transferredBy , x1, y1);
		y1 = y1 + 10;
		g2d.drawString(padRight("Total Number of Jobs",30) + ": " + this.pcesthmtList.size() , x1, y1);
		y1 = y1 + 10;
		g2d.drawString(padRight("Transferred TV Numbers",30) + ": "   , x1, y1);
		y1 = y1 + 10;
		
		// here we save x and y co ordinate to x2 and y2 variables
		// becasue at this time we do not have the total.
		// once the total is availlble we can print using x1 and y1 co ordinates
		x2 = x1;
		y2 = y1;
		//g2d.drawString(padRight("Total Value",30) + ": " + nf.format(total)  , x1, y1);
		y1 = y1 + 10;
		g2d.drawString(padRight("Cost Center",30) + ": " + this.costCenter , x1, y1);
		y1 = y1 + 10;
		g2d.drawString(padRight("Signature",30) + ": "   , x1, y1);
		y1 = y1 + 30;
		g2d.drawString(padRight("Date",30) + ": " + new Date() , x1, y1);
		
		y1 = y1 + 20;
		//g2d.drawLine((int)x1, (int)y1, (int)x1 + 500,(int) y1);
		
		
		g2d.drawString("JOB DETIALS"  , x1, y1);
		y1 = y1 + 10;
		g2d.drawLine((int)x1, (int)y1, (int)x1 + 500,(int) y1);
		
		
		for (Pcesthmt pcesthmt : pcesthmtList) {
			y1 = y1 + 10;
			g2d.drawString(padRight("Category",20) + ": " + pcesthmt.getCatCd() , x1, y1);
			
			y1 = y1 + 10;
			g2d.drawString(padRight("Job Number",20) + ": " + pcesthmt.getProjectNo() , x1, y1);
			
			y1 = y1 + 10;
			total = total +  pcesthmt.getStdCost().doubleValue();
			g2d.drawString(padRight("Estimate Cost",20) + ": " + nf.format(pcesthmt.getStdCost()) , x1, y1);
			
			y1 = y1 + 10;
			g2d.drawString(padRight("PIV Amount",20) + ": "    , x1, y1);
			
			y1 = y1 + 10;
			g2d.drawString(padRight("PIV Number",20) + ": "    , x1, y1);
			
			y1 = y1 + 10;
			g2d.drawString(padRight("Job Description",20) + ": " + pcesthmt.getDescr()    , x1, y1);
			
			y1 = y1 + 20;
		}
		g2d.drawString(padRight("Total Value",30) + ": " + nf.format(total)  , x2, y2);
		
		
		
		return Printable.PAGE_EXISTS;
	}

	public static String padLeft(String s, int n) {
	    return String.format("%1$#" + n + "s", s);
	  }
	 public static String padRight(String s, int n) {
		    return String.format("%1$-" + n + "s", s);
		  }
	public void Print(boolean showDialog)
	{
		PrinterJob printerJob = PrinterJob.getPrinterJob();

		Book book = new Book();	
		List<Pcesthmt> tempList = new ArrayList<Pcesthmt>();
		int count  = 0;
		
		for (Pcesthmt pcesthmt : this.pcesthmtList) {
			tempList.add(pcesthmt);
			count++;
			if (count == 6)
			{
				book.append(new JobTransferredReport(tempList,this.transferredDate,this.costCenter,this.transferredBy), new PageFormat());
				tempList = new ArrayList<Pcesthmt>();
				count = 0;
			}			
		}
		book.append(new JobTransferredReport(tempList,this.transferredDate,this.costCenter,this.transferredBy), new PageFormat());
		
		//book.append(new JobTransferredReport(this.pcesthmtList,this.transferredDate,this.costCenter,this.transferredBy), new PageFormat());
	//	book.append(new JobTransferredReport(this.pcesthmtList,this.transferredDate,this.costCenter,this.transferredBy), new PageFormat());
		printerJob.setPageable(book);
		boolean doPrint = true;
		if (showDialog)
			doPrint = printerJob.printDialog();
		else
			doPrint = true;			


		if (doPrint) {

			try {
				printerJob.print();
				 
			} catch (PrinterException exception) {
				System.err.println("Printing error: " + exception);
			}
		}

	}
	
	
	
}
