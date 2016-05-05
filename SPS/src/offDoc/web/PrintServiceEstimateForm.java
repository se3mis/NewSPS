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

public class PrintServiceEstimateForm implements Printable {

	private ServiceEstimateForm sef;
	public PrintServiceEstimateForm(ServiceEstimateForm sef)
	{
		this.sef = sef;
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
		g2d.setFont(ft);

		float x1,x2,x3,x4,x5,x6,x7,g1;
		float y1,y2,y3;

		x1 = 75;
		y1 = 55;
		float d = (float) 5;

		// print Name
		if (  sef.getNameWithInitials() != null )
			g2d.drawString(sef.getNameWithInitials() , x1, y1);
		
		x1 = x1 +  62*d;
		// print 
		if (  sef.getArea() != null )
			g2d.drawString(sef.getArea()  , x1, y1);
		
		y1 = y1 + d * 3;
		if (  sef.getCSC()  != null )
			g2d.drawString(sef.getCSC()  , x1, y1);
		
		x1 = 75;
		y1 = y1 + d * 3;
		 
		
		
		// print address 
		String tempStreetAddress,tempSuburb,tempCity,tempPostalCode = "";
		tempStreetAddress = sef.getStreetAddress();
		tempSuburb = sef.getSuburb()   ;
		tempCity = sef.getCity();


		float tempY = y1 ;
		float d1 =10;
		if (sef.getPostalCode() != null)
			tempPostalCode = sef.getPostalCode().toString() ;

		if (tempStreetAddress != null && (! (tempStreetAddress.trim().isEmpty())) )
		{
			if (tempSuburb   != null && ((!tempSuburb.trim().isEmpty())) )
			{
				tempStreetAddress += ", " + tempSuburb;
			}
			g2d.drawString(tempStreetAddress ,x1 , tempY);
			tempY =  tempY + d1;
		}

		

		if (tempCity   != null && ( ( ! tempCity.trim().isEmpty())) )
		{
			if (tempPostalCode  != null && (! (tempPostalCode.toString().trim().isEmpty())) )
			{
				tempCity += ", " + tempPostalCode;
			}
			
			g2d.drawString(tempCity  ,x1 , tempY);
			tempY =  tempY + d1;
		}
		
		x1 = x1 +  62*d;
		// print APP NO
		if (  sef.getAppNumber()  != null )
			g2d.drawString(sef.getAppNumber()  , x1, y1);
		
		y1 = tempY;
		x1 = 75;
		y1 = (float) (y1 + d * 2);
		if (  sef.getTelephoneNumber()  != null )
			g2d.drawString(sef.getTelephoneNumber()  , x1, y1);
		
		y1 = (float) (y1 + d * 5);
		if (  sef.getServiceType()  != null )
			g2d.drawString(sef.getServiceType()  , x1, y1);
		
		y1 = (float) (y1 + d * 5);
		if (  sef.getTariff()  != null )
			g2d.drawString(sef.getTariff()  , x1, y1);
		
	
		
		tempY = y1;
		// print Pole details
		x1 = x1 +  60 * d;
		y1 = (float) (y1 + d * 14);
		tempY = y1;
		if (  sef.getItem_P() != null )
			g2d.drawString(sef.getItem_P()  , x1, y1);
		
		// print item S
		y1 = (float) (y1 + d * 5);
		if (  sef.getItem_S()  != null )
			g2d.drawString(sef.getItem_S()  , x1, y1);
		
		y1 = (float) (y1 + d * 5);
		if (  sef.getItem_St()  != null )
			g2d.drawString(sef.getItem_St()  , x1, y1);
		
		y1 = (float) (y1 + d * 5);
		if (  sef.getItem_Ls()  != null )
			g2d.drawString(sef.getItem_Ls()  , x1, y1);
		
		
		y1 = (float) (y1 + d * 5);
		if (  sef.getItem_Lb()  != null )
			g2d.drawString(sef.getItem_Lb()  , x1, y1);
		
		
		y1 = (float) (y1 + d * 5);
		if (  sef.getItem_C1()  != null )
			g2d.drawString(sef.getItem_C1()  , x1, y1);
		
		
		y1 = (float) (y1 + d * 5);
		if (  sef.getItem_C2()  != null )
			g2d.drawString(sef.getItem_C2()  , x1, y1);
		
		
		
		// print descriptions
		y1 = tempY;
		x1 = x1 +  8 * d;
		
		
		if (  sef.getItem_P_Des()  != null )
			g2d.drawString(sef.getItem_P_Des()  , x1, y1);
		
		y1 = (float) (y1 + d * 5);
		if (  sef.getItem_S_Des()  != null )
			g2d.drawString(sef.getItem_S_Des()  , x1, y1);
		
		y1 = (float) (y1 + d * 5);
		if (  sef.getItem_St_Des()  != null )
			g2d.drawString(sef.getItem_St_Des()  , x1, y1);
		
		
		y1 = (float) (y1 + d * 5);
		if (  sef.getItem_Ls_Des()  != null )
			g2d.drawString(sef.getItem_Ls_Des()  , x1, y1);
		
		
		y1 = (float) (y1 + d * 5);
		if (  sef.getItem_Lb_Des()  != null )
			g2d.drawString(sef.getItem_Lb_Des()  , x1, y1);
		
		
		y1 = (float) (y1 + d * 5);
		if (  sef.getItem_C1_Des()  != null )
			g2d.drawString(sef.getItem_C1_Des()  , x1, y1);
		
		
		
		y1 = (float) (y1 + d * 5);
		if (  sef.getItem_C2_Des()  != null )
			g2d.drawString(sef.getItem_C2_Des()  , x1, y1);
		
		 
		// print technical detials
		x1 = 170;
		 
		y1 = (float) (y1 + d * 35);
		tempY = y1;
		if (  sef.getSubstation() != null )
			g2d.drawString(sef.getSubstation()  , x1, y1);
		
		y1 = (float) (y1 + d * 5);
		if (  sef.getDistanceFromSS() != null )
			g2d.drawString(sef.getDistanceFromSS()  , x1, y1);
		
		y1 = (float) (y1 + d * 5.5);
		if (  sef.getTransformerCapacity() != null )
			g2d.drawString(sef.getTransformerCapacity()  , x1, y1);
		
		
		
		y1 = (float) (y1 + d * 5.6);
		if (  sef.getTransformerPowerLoad_KVA()  != null )
			g2d.drawString(sef.getTransformerPowerLoad_KVA()  , x1, y1);
		
		
		y1 = (float) (y1 + d * 1.2);
		if (  sef.getFeederPeakLoad_A()  != null )
			g2d.drawString(sef.getFeederPeakLoad_A()  , x1, y1);
		
		
		y1 = (float) (y1 + d * 1.1);
		if (  sef.getFeederControlType()  != null )
			g2d.drawString(sef.getFeederControlType()  , x1, y1);
		
		
		x1 = 75;
		x1 = x1 +  65*d;
		y1 = tempY;
		// print 
		if (  sef.getSIN() != null )
			g2d.drawString(sef.getSIN()  , x1, y1);
		
		y1 = y1 + d * 5;
		if (  sef.getPhase()  != null )
			g2d.drawString(sef.getPhase()  , x1, y1);
		
		y1 = y1 + d * 5;
		if (  sef.getPoleNo()  != null )
			g2d.drawString(sef.getPoleNo()  , x1, y1);
		
		
		
		return Printable.PAGE_EXISTS;
	}
	
	public void Print(boolean showDialog)
	{
		PrinterJob printerJob = PrinterJob.getPrinterJob();

		Book book = new Book();	
		//book.append(new PrintPiv(this.pivDetail,this.applicant), new PageFormat());
		book.append(new PrintServiceEstimateForm(this.sef), new PageFormat());
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
