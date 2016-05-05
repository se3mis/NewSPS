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

public class PrintTemporaryJobEnagize implements Printable {

	private TemporaryJobEnagize temporaryJobEnagize;

	public PrintTemporaryJobEnagize( TemporaryJobEnagize temporaryJobEnagize)
	{		 
		this.temporaryJobEnagize = temporaryJobEnagize;
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
		Font ftBold =   new Font ("Courier New", Font.BOLD, 10);
		g2d.setFont(ft);
		/* Use a LineBreakMeasurer instance to break our text into
		 * lines that fit the imageable area of the page.
		 */
		Point2D.Float pen = new Point2D.Float();




		int x;
		int y;
		int d;

		x =  20;		
		y = 20;
		d = 20;


		// print job number
		int n = 35;
		if (temporaryJobEnagize.getJobNumber() != null)
		{
			y = y + d;
			g2d.drawString(padRight("Job Number",n) + ": " + temporaryJobEnagize.getJobNumber(), x, y);
		}

		// print job date
		if (temporaryJobEnagize.getJobDate() != null)
		{
			y = y + d;
			g2d.drawString(padRight("Date",n) + ": " + temporaryJobEnagize.getJobDate(), x, y);
		}




		String tempStreetAddress,tempSuburb,tempCity,tempPostalCode = "";
		tempStreetAddress = temporaryJobEnagize.getStreetAddress() ;
		tempSuburb = temporaryJobEnagize.getSuburb()   ;
		tempCity = temporaryJobEnagize.getCity();
		if (temporaryJobEnagize.getPostalCode() != null)

			tempPostalCode = temporaryJobEnagize.getPostalCode().toString() ;

		if (tempStreetAddress != null && (! (tempStreetAddress.trim().isEmpty())) )
		{
			y =  y + d;
			g2d.drawString(padRight("Address",n) + ": " + tempStreetAddress ,x , y);

		}

		if (tempSuburb   != null && ((!tempSuburb.trim().isEmpty())) )
		{
			y =  y + d;
			g2d.drawString(padRight("",n) + "  " + tempSuburb ,x , y);


		}

		if (tempCity   != null && ( ( ! tempCity.trim().isEmpty())) )
		{
			y =  y + d;
			g2d.drawString(padRight("",n) + "  " + tempCity ,x , y);


		}

		if (tempPostalCode  != null && (! (tempPostalCode.toString().trim().isEmpty())) )
		{
			y =  y + d;
			g2d.drawString(padRight("",n) + "  " + tempPostalCode ,x , y);

		}

		// print  
		if (temporaryJobEnagize.getApplicationSendDate() != null)
		{
			y = y + d;
			g2d.drawString(padRight("Applicatin Send Date",n) + ": " + temporaryJobEnagize.getApplicationSendDate(), x, y);
		}

		// print  
		if (temporaryJobEnagize.getApplicationRecivedDateFromREEOffice() != null)
		{
			y = y + d;
			g2d.drawString(padRight("Applicatin Recived Date from R.E.E Office",n) + ": " + temporaryJobEnagize.getApplicationRecivedDateFromREEOffice(), x, y);
		}

		// print  
		if (temporaryJobEnagize.getRequiredServiceDurationFromDate() != null)
		{
			y = y + d;
			g2d.drawString(padRight("Required Service Duration From",n) + ": " + temporaryJobEnagize.getRequiredServiceDurationFromDate(), x, y);
		}

		// print  
		if (temporaryJobEnagize.getRequiredServiceDurationToDate() != null)
		{
			y = y + d;
			g2d.drawString(padRight("Required Service Duration To",n) + ": " + temporaryJobEnagize.getRequiredServiceDurationToDate(), x, y);
		}

		// print  
		if (temporaryJobEnagize.getEstimateAmount() != null)
		{
			y = y + d;
			g2d.drawString(padRight("Estimate Amount",n) + ": " + temporaryJobEnagize.getEstimateAmount(), x, y);
		}

		// print  
		if (temporaryJobEnagize.getSecurityDeposite() != null)
		{
			y = y + d;
			g2d.drawString(padRight("Security Deposite",n) + ": " + temporaryJobEnagize.getSecurityDeposite(), x, y);
		}

		// print  
		if (temporaryJobEnagize.getAdditionalDeposite() != null)
		{
			y = y + d;
			g2d.drawString(padRight("Additional Deposite",n) + ": " + temporaryJobEnagize.getAdditionalDeposite(), x, y);
		}

		// print  
		if (temporaryJobEnagize.getPivNumber() != null)
		{
			y = y + d;
			g2d.drawString(padRight("PIV Number",n) + ": " + temporaryJobEnagize.getPivNumber(), x, y);
		}

		// print  
		if (temporaryJobEnagize.getPivDate() != null)
		{
			y = y + d;
			g2d.drawString(padRight("PIV Date",n) + ": " + temporaryJobEnagize.getPivDate(), x, y);
		}

		// print  
		if (temporaryJobEnagize.getServiceConnectedDate() != null)
		{
			y = y + d;
			g2d.drawString(padRight("Service Connected Date",n) + ": " + temporaryJobEnagize.getServiceConnectedDate(), x, y);
		}

		// print  
		if (temporaryJobEnagize.getServiceConnectedlinesManName() != null)
		{
			y = y + d;
			g2d.drawString(padRight("Service Connected Linesman Name",n) + ": " + temporaryJobEnagize.getServiceConnectedlinesManName(), x, y);
		}

		// print  
		if (temporaryJobEnagize.getMaterialNoticeNumber() != null)
		{
			y = y + d;
			g2d.drawString(padRight("Material Notice Number",n) + ": " + temporaryJobEnagize.getMaterialNoticeNumber(), x, y);
		}

		// print  
		if (temporaryJobEnagize.getMaterialNoticeDate() != null)
		{
			y = y + d;
			g2d.drawString(padRight("Material Notice Date",n) + ": " + temporaryJobEnagize.getMaterialNoticeDate(), x, y);
		}

		// print  
		if (temporaryJobEnagize.getPhase() != null)
		{
			y = y + d;
			g2d.drawString(padRight("Phase",n) + ": " + temporaryJobEnagize.getPhase(), x, y);
		}




		y = y + d;		
		int x2,x3;		
		x2 = x + 5*d;
		x3 = x + 12*d;
		g2d.setFont(ftBold);
		g2d.drawString("Meter Number", x, y);
		g2d.drawString("Initial Reading", x2, y);
		g2d.drawString("Final Reading", x3, y);
		g2d.setFont(ft);

/////////////// METER ONE
			
		// print  
		if (temporaryJobEnagize.getMeterNumber1() != null)	
		{
			y = y + d;	
			g2d.drawString(temporaryJobEnagize.getMeterNumber1(), x, y);	
		}

		// print  
		if (temporaryJobEnagize.getMeterNumber1InitialReading() != null)		
			g2d.drawString( temporaryJobEnagize.getMeterNumber1InitialReading(), x2, y);

		// print  
		if (temporaryJobEnagize.getMeterNumber1FinalReading() != null)		
			g2d.drawString(temporaryJobEnagize.getMeterNumber1FinalReading(), x3, y);
		
		///////////////// METER TWO
			
		// print  
		if (temporaryJobEnagize.getMeterNumber2() != null)		
		{
			y = y + d;	
			g2d.drawString(temporaryJobEnagize.getMeterNumber2(), x, y);		
		}
			

		// print  
		if (temporaryJobEnagize.getMeterNumber2InitialReading() != null)		
			g2d.drawString( temporaryJobEnagize.getMeterNumber2InitialReading(), x2, y);

		// print  
		if (temporaryJobEnagize.getMeterNumber2FinalReading() != null)		
			g2d.drawString(temporaryJobEnagize.getMeterNumber2FinalReading(), x3, y);
		
		//METER THREE
		
		// print  
		if (temporaryJobEnagize.getMeterNumber3() != null)	
		{
			y = y + d;		
			g2d.drawString(temporaryJobEnagize.getMeterNumber3(), x, y);	
		}

		// print  
		if (temporaryJobEnagize.getMeterNumber3InitialReading() != null)		
			g2d.drawString( temporaryJobEnagize.getMeterNumber3InitialReading(), x2, y);

		// print  
		if (temporaryJobEnagize.getMeterNumber3FinalReading() != null)		
			g2d.drawString(temporaryJobEnagize.getMeterNumber3FinalReading(), x3, y);


		// print  
		if (temporaryJobEnagize.getServiceDisconnectedDate() != null)
		{
			y = y + d;
			g2d.drawString(padRight("Service Disconnected Date",n) + ": " + temporaryJobEnagize.getServiceDisconnectedDate(), x, y);
		}

		// print  
		if (temporaryJobEnagize.getServiceDisconnectedlinesManName() != null)
		{
			y = y + d;
			g2d.drawString(padRight("Service Disconnected Linesman",n) + ": " + temporaryJobEnagize.getServiceDisconnectedlinesManName(), x, y);
		}

		// print  
		if (temporaryJobEnagize.getUnitsUsed() != null)
		{
			y = y + d;
			g2d.drawString(padRight("Units Used",n) + ": " + temporaryJobEnagize.getUnitsUsed(), x, y);
		}

		// print  
		if (temporaryJobEnagize.getChargedAmount() != null)
		{
			y = y + d;
			g2d.drawString(padRight("Charged Amount",n) + ": " + temporaryJobEnagize.getChargedAmount(), x, y);
		}

		// print  
		if (temporaryJobEnagize.getDateSendToREEOffice() != null)
		{
			y = y + d;
			g2d.drawString(padRight("Date Send To R.E.E. Office",n) + ": " + temporaryJobEnagize.getDateSendToREEOffice(), x, y);
		}


		// print  

		y = y + 3*d;
		g2d.drawString("Signature :______________________              Date :______________________", x, y);








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
		//book.append(new PrintPiv(this.pivDetail,this.applicant), new PageFormat());
		book.append(new PrintTemporaryJobEnagize(this.temporaryJobEnagize), new PageFormat());
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
