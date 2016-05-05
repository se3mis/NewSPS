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

public class PrintSMC implements Printable {

	private ServiceMainCard smc;

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

		x1 = 0;
		y1 = 107;
		float d = (float) 9.8;

		// print smc type
		if (  smc.getSMCType() != null )
			g2d.drawString(smc.getSMCType() , x1, y1);

		x1 = x1 + d*8;
		// print  sin
		if (  smc.getSIN()  != null )
			g2d.drawString(smc.getSIN() , x1, y1); 

		y1 = y1 + 3;
		y1 = y1 + d*4;
		// print  cost code
		if (  smc.getCostCode()  != null )
			g2d.drawString(smc.getCostCode()  , x1, y1); 

		x1 = x1 + 10*d;
		// print  ecsc
		if (  smc.getECSC()  != null )
			g2d.drawString(smc.getECSC()  , x1, y1); 

		x1 = x1 + 8*d;
		// print  year
		if (  smc.getYear()  != null )
			g2d.drawString(smc.getYear()  , x1, y1); 

		x1 = x1 + 7*d;
		// print  serial job no
		if (  smc.getSerialJobNo()  != null )
			g2d.drawString(smc.getSerialJobNo()  , x1, y1); 

		x1 = x1 + 9*d;
		// print  no of meters
		if (  smc.getNoOfMeters()  != null )
			g2d.drawString(smc.getNoOfMeters()  , x1, y1); 

		
		////////////////////////////////////////// print meter one ///////////////////////////////////////////
		y1 = y1 + 4*d;
		x1=0;
		// print meter number 1
		if (  smc.getMeterNumber1()  != null )
			g2d.drawString(smc.getMeterNumber1()  , x1, y1); 

		x1 = x1 + 8*d;
		// print  meter reading 1
		if (  smc.getMeterReading1()  != null )
			g2d.drawString(smc.getMeterReading1()  , x1, y1); 

		x1 = x1 + 10*d;
		// print  mete taype 1
		if (  smc.getMeterType1()  != null )
			g2d.drawString(smc.getMeterType1()  , x1, y1); 
		
		x1 = x1 + 8*d;
		// print  digits
		if (  smc.getNoOfDigits1()  != null )
			g2d.drawString(smc.getNoOfDigits1()  , x1, y1); 

		x1 = x1 + 7*d;
		// print  connected date
		if (  smc.getConnectedDate()  != null )
			g2d.drawString(smc. getConnectedDate() , x1, y1); 
		///////////////////////////////////////////////////////////////////////////////////////////////	
		
		
		
		//////////////////////////////////////////// meter two ///////////////////////////////////////////////////
		y1 = y1 + 2*d;
		x1=0;
		// print meter number 2
		if (  smc.getMeterNumber2()  != null )
			g2d.drawString(smc.getMeterNumber1()  , x1, y1); 

		x1 = x1 + 8*d;
		// print  meter reading 2
		if (  smc.getMeterReading2()  != null )
			g2d.drawString(smc.getMeterReading1()  , x1, y1); 

		x1 = x1 + 10*d;
		// print  mete taype 2
		if (  smc.getMeterType2()  != null )
			g2d.drawString(smc.getMeterType1()  , x1, y1); 
		
		x1 = x1 + 8*d;
		// print  digits
		if (  smc.getNoOfDigits2()  != null )
			g2d.drawString(smc.getNoOfDigits1()  , x1, y1); 

		
		///////////////////////////////////////////////////////////////////////////////////////////////	
		
		//////////////////////////////////////////// meter three ///////////////////////////////////////////////////
		y1 = y1 + 2*d;
		x1=0;
		// print meter number 3
		if (  smc.getMeterNumber3()  != null )
			g2d.drawString(smc.getMeterNumber1()  , x1, y1); 

		x1 = x1 + 8*d;
		// print  meter reading 3
		if (  smc.getMeterReading3()  != null )
			g2d.drawString(smc.getMeterReading1()  , x1, y1); 

		x1 = x1 + 10*d;
		// print  mete type 3
		if (  smc.getMeterType3()  != null )
			g2d.drawString(smc.getMeterType1()  , x1, y1); 
		
		x1 = x1 + 8*d;
		// print  digits
		if (  smc.getNoOfDigits3()  != null )
			g2d.drawString(smc.getNoOfDigits1()  , x1, y1); 

		x1 = x1 + 7*d;
		// print   average consumption
		if (  smc.getAverageConsumption()  != null )
			g2d.drawString(smc.getAverageConsumption()  , x1, y1); 
		
		///////////////////////////////////////////////////////////////////////////////////////////////	
	 
   
///////////////////////////////////// Row 04 //////////////////////////////////////////////////////////	

	
		y1 = y1 + 5*d;
		x1=0;
		// print  
		if (  smc.getPaymentMode()  != null )
			g2d.drawString(smc.getPaymentMode()  , x1, y1); 

		x1 = x1 + 8*d;
		// print  
		if (  smc.getPaymentDate()  != null )
			g2d.drawString(smc.getPaymentDate()  , x1, y1); 

		x1 = x1 + 18*d;
		// print  
		if (  smc.getSecurityDeposits()  != null )
			g2d.drawString(smc.getSecurityDeposits()  , x1, y1); 

		x1 = x1 + 13*d;
		// print  
		if (  smc.getServiceMainCharge()  != null )
			g2d.drawString(smc.getServiceMainCharge()  , x1, y1); 

		
		
///////////////////////////////////// Row 05 //////////////////////////////////////////////////////////	
		y1 = y1 + 4*d;
		x1=0;
		// print  
		if (  smc.getTax()  != null )
			g2d.drawString(smc.getTax()  , x1, y1); 

		x1 = x1 + 8*d;
		// print  
		if (  smc.getTotal()  != null )
			g2d.drawString(smc.getTotal()  , x1, y1); 

		x1 = x1 + 10*d;
		// print  
		if (  smc.getTariff()  != null )
			g2d.drawString(smc.getTariff()  , x1, y1); 
		
		x1 = x1 + 8*d;
		// print  
		if (  smc.getKVA()  != null )
			g2d.drawString(smc.getKVA()  , x1, y1); 

		x1 = x1 + 7*d;
		// print  
		if (  smc.getConnType()  != null )
			g2d.drawString(smc.getConnType()  , x1, y1); 

		x1 = x1 + 12*d;
		// print  
		if (  smc.getNoOfPhase()  != null )
			g2d.drawString(smc.getNoOfPhase()  , x1, y1); 

///////////////////////////////////// Row 06 //////////////////////////////////////////////////////////	
		
		y1 = (float) (y1 + 4.5*d);
		x1=0;// print  
		if (  smc.getBankReference_PIV2()  != null )
			g2d.drawString(smc.getBankReference_PIV2()  , x1, y1); 

		x1 = x1 +  18*d;
		// print  
		if (  smc.getNeighboursACNo()  != null )
			g2d.drawString(smc.getNeighboursACNo()  , x1, y1); 

		x1 = x1 +  19*d;
		// print  
		if (  smc.getBankBranchCode()  != null )
			g2d.drawString(smc.getBankBranchCode()  , x1, y1); 
			 
///////////////////////////////////// Row 07 //////////////////////////////////////////////////////////	
		y1 = (float) (y1 + 4.5*d);
		x1=0;// print  
		// print  
		if (  smc.getLastName()  != null )
			g2d.drawString(smc.getLastName()  , x1, y1); 

		x1 = x1 +  26*d;
		// print  
		if (  smc.getFirstName()  != null )
			g2d.drawString(smc.getFirstName()  , x1, y1); 
		
		 
		x1=0;
		// print address 
		String tempStreetAddress,tempSuburb,tempCity,tempPostalCode = "";
		tempStreetAddress = smc.getStreetAddress();
		tempSuburb = smc.getSuburb()   ;
		tempCity = smc.getCity();
		y1 = y1 + 5*d;
		float tempY = y1;
		float d1 =10;
		
		 
		if (smc.getPostalCode() != null)
			tempPostalCode = smc.getPostalCode().toString() ;

		if (tempStreetAddress != null && (! (tempStreetAddress.trim().isEmpty())) )
		{
			g2d.drawString(tempStreetAddress ,x1 , tempY);
			tempY =  tempY + d1;
		}

		if (tempSuburb   != null && ((!tempSuburb.trim().isEmpty())) )
		{
			g2d.drawString( tempSuburb  ,x1 , tempY);
			tempY =  tempY + d1;
		}

		if (tempCity   != null && ( ( ! tempCity.trim().isEmpty())) )
		{
			g2d.drawString(tempCity  ,x1 , tempY);
			tempY =  tempY + d1;
		}

		if (tempPostalCode  != null && (! (tempPostalCode.toString().trim().isEmpty())) )
		{
			g2d.drawString(tempPostalCode.toString() ,x1 , tempY);
		}
		 
		
///////////////////////////////////// Row 09 //////////////////////////////////////////////////////////	
		y1 = (float) (y1 + 7.5*d);
		x1=0;
		// print  
		if (  smc.getPIVNumber()  != null )
			g2d.drawString(smc.getPIVNumber()  , x1, y1); 

		x1 = x1 +  18*d;
		// print  
		if (  smc.getPIVDate()  != null )
			g2d.drawString(smc.getPIVDate()  , x1, y1); 
		
		x1 = x1 +  19*d;
		// print  
		if (  smc.getCustomerType()  != null )
			g2d.drawString(smc.getCustomerType()  , x1, y1); 

///////////////////////////////////// Row 10 //////////////////////////////////////////////////////////	
		y1 = y1 + 5*d;
		x1=0;
		// print  
		if (  smc.getReaderCode()  != null )
			g2d.drawString(smc.getReaderCode()  , x1, y1); 

		x1 = x1 +  8*d;
		// print  
		if (  smc.getPackNo()  != null )
			g2d.drawString(smc.getPackNo()  , x1, y1); 
		 
		x1 = x1 +  10*d;
		// print  
		if (  smc.getWalkSequence()  != null )
			g2d.drawString(smc.getWalkSequence()  , x1, y1); 

		x1 = x1 +  15*d;
		// print  
		if (  smc.getOldAcctNumber()  != null )
			g2d.drawString(smc.getOldAcctNumber()  , x1, y1); 
	 

///////////////////////////////////// Loan detials //////////////////////////////////////////////////////////	
		y1 = y1 + 2*d;
		x1=0;
		// print  
		if (  smc.getLoanCode()  != null )
			g2d.drawString("Loan Code:" + smc.getLoanCode()  , x1, y1); 

		x1 = x1 +  13*d;
		// print  
		if (  smc.getLoanDate()  != null )
			g2d.drawString( "Loan Date:"+smc.getLoanDate()  , x1, y1); 
		 

		x1 = x1 +  15*d;
		// print  
		if (  smc.getLoanAmount()  != null )
			g2d.drawString("Loan Amount:" + smc.getLoanAmount()  , x1, y1); 
		
		y1 = y1 + 2*d;
		x1=0;
		
		if (  smc.getNoOfInstalment()  != null )
			g2d.drawString("No. of Inst.:" + smc.getNoOfInstalment()  , x1, y1); 

		x1 = x1 +  12*d;
		if (  smc.getInstalmantAmount()  != null )
			g2d.drawString("Instalmant Amount:" + smc.getInstalmantAmount()  , x1, y1); 
		 


	 
		


		return Printable.PAGE_EXISTS;
	}

	public PrintSMC(ServiceMainCard smc)
	{		 
		this.smc = smc;
	}
	public void Print(boolean showDialog)
	{
		PrinterJob printerJob = PrinterJob.getPrinterJob();

		Book book = new Book();	
		//book.append(new PrintPiv(this.pivDetail,this.applicant), new PageFormat());
		book.append(new PrintSMC(this.smc), new PageFormat());
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
