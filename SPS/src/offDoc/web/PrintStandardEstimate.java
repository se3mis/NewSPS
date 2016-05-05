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



import util.common.Format;


import application.model.Applicant;

import estimate.model.MaterialGrid;
import estimate.model.Pcestdtt;
import estimate.model.Pcesthtt;
import estimate.model.Speststd;

public class PrintStandardEstimate implements Printable {



	private StandardEstimate estimate;
	NumberFormat nf = NumberFormat.getInstance();
	//private PivDetail pivDetail;
	Format fmt = new Format();


	public PrintStandardEstimate( StandardEstimate estimate)
	{
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		this.estimate = estimate;
	}	


	public void Print(boolean showDialog)
	{
		PrinterJob printerJob = PrinterJob.getPrinterJob();

		Book book = new Book();	
		//book.append(new PrintPiv(this.pivDetail,this.applicant), new PageFormat());
		book.append(new PrintStandardEstimate(this.estimate), new PageFormat());
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
		x1 =  120;
		x2 = 330;
		y = 40;
		d = 20;
		d1 = 15;
		int l = 12;

		int currentY = 20;



		// print line length
		if (estimate.getLineLength() != null)
			g2d.drawString( padLeft(estimate.getLineLength() + " m", l)   , x1, currentY);





		// print application no
		if (estimate.getApplicationNumuber() != null)
			g2d.drawString(estimate.getApplicationNumuber(), x2, currentY);
		//		 
		currentY = currentY + d;
		// print fixed cost
		if (estimate.getFixedCost() != null)
			g2d.drawString(padLeft(estimate.getFixedCost(),l), x1,currentY);

		// print Consumer Name
		if (estimate.getConsumerName() != null)
			g2d.drawString(estimate.getConsumerName(), x2, currentY);

		currentY = currentY + d;
		//print variable cost
		if (estimate.getVariableCost() != null)
			g2d.drawString(padLeft(estimate.getVariableCost(),l), x1, currentY);

		//print consumer address

		int tempY = currentY;
		String tempStreetAddress,tempSuburb,tempCity,tempPostalCode = "";
		tempStreetAddress = estimate.getStreetAddress() ;
		tempSuburb = estimate.getSuburb()   ;
		tempCity = estimate.getCity();
		if (estimate.getPostalCode() != null)
			tempPostalCode = estimate.getPostalCode().toString() ;

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
		
		int tempX2,tempY2;
		tempX2 = x2 - 80;
		tempY2 = tempY;
		
		// print labour cost
		if (estimate.getLabourCost() != null)
		{
			tempY2 = tempY2 + d;
			g2d.drawString("Labour Cost :" + estimate.getLabourCost(), tempX2, tempY2);
		}
		
		
		// print labour cost
		if (estimate.getTransportCost() != null)
		{
			tempY2 = tempY2 + d;
			g2d.drawString("Transport Cost :" + estimate.getTransportCost(), tempX2, tempY2);
		}
		
		
		// print labour cost
		if (estimate.getOverheadCost() != null)
		{
			tempY2 = tempY2 + d;
			g2d.drawString("Overhead Cost :" + estimate.getOverheadCost(), tempX2, tempY2);			
		}
		
		
		// print labour cost
		if (estimate.getLabourCost() != null)
		{
			tempY2 = tempY2 + d;
			g2d.drawString("Additional Deposite :" + estimate.getAdditionalDeposite(), tempX2, tempY2);
		}
		




		currentY = currentY + d;
		//print other cost
		if (estimate.getOtherCost() != null)
			g2d.drawString(padLeft(estimate.getOtherCost(),l), x1, currentY);

		currentY = currentY + d + 10;
		//print sub total
		if (estimate.getSubTotal() != null)
			g2d.drawString(padLeft(estimate.getSubTotal(),l), x1, currentY);

		currentY = currentY + d;
		//print security deposit
		if (estimate.getSecurityDeposit() != null)
			g2d.drawString(padLeft(estimate.getSecurityDeposit(),l), x1, currentY);

		currentY = currentY + d;
		//print VAT
		if (estimate.getVAT() != null)
			g2d.drawString(padLeft(estimate.getVAT(),l), x1, currentY);

		currentY = currentY + d-5;
		//print TOTAL

		if (estimate.getTotal() != null)
			g2d.drawString(padLeft(estimate.getTotal(),l), x1, currentY);


		
		if (estimate.getEstimatedDate() != null)
			g2d.drawString(estimate.getEstimatedDate(), x2, currentY);
		
		
		currentY = currentY + 3 *  d;
		// draw Line

		g2d.drawLine(0, currentY, 450, currentY);


		//////           PRINT DETAILED ESTIMATE                      ///////////////
		// print Heading
		x1 = 0;
		x2 = 60;
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


		int tempDescriptionLength = 30;
		if (estimate.getMaterialGrid() != null)
		{
			for (MaterialGrid material : estimate.getMaterialGrid()) {
				if (material.getResCd() != null)
					g2d.drawString(padRight(material.getResCd(),10) , x1, currentY);
				String materialName = material.getMatNm();
				System.out.println("xx " + materialName);
				System.out.println("ll " + materialName.length());

				if (materialName.length() >= tempDescriptionLength)
				{			 
					g2d.drawString(padRight(materialName.substring(0,tempDescriptionLength),50), x2, currentY);
					if (materialName.length() > tempDescriptionLength)
					{
						currentY = currentY + 12;
						int tempLength = materialName.length();
						if (tempLength >= tempDescriptionLength *2)
							tempLength = tempDescriptionLength *2;					 

						g2d.drawString(padRight(materialName.substring(tempDescriptionLength,tempLength).trim(),50), x2, currentY);
					}
				}
				else
					g2d.drawString(padRight(materialName,50), x2, currentY);
				g2d.drawString(padRight(material.getUom(),5) , x3, currentY);
				g2d.drawString(padLeft(nf.format(material.getUnitPrice()),10) , x4, currentY);
				g2d.drawString(padLeft(nf.format(material.getEstimateQty()),10), x5, currentY);
				g2d.drawString(padLeft(nf.format(material.getEstimateCost()),12), x6, currentY);
				currentY = currentY + 12;
			}
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
