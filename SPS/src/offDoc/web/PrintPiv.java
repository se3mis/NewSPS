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
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import application.model.Applicant;

import piv.model.PivDetail;
 
import util.common.Format;

public class PrintPiv implements Printable {


	NumberFormat nf = NumberFormat.getInstance();
	//private PivDetail pivDetail;
	Format fmt = new Format();
	//private Applicant applicant;
	private PayingInVoucher piv;

	



	 
public PrintPiv(PayingInVoucher piv)
{
	nf.setGroupingUsed(true);
	nf.setMaximumFractionDigits(2);
	nf.setMinimumFractionDigits(2);
	this.piv = piv;
}

	public PrintPiv(PivDetail pivDetail,Applicant applicant)
	{
	//	this.pivDetail = pivDetail;
	//	this.applicant = applicant;

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
		g2d.setFont(ft);
		 
		int x1,x1_default,x3,x4,x5,x6,x7,g1;
		int y1,y2,y3;

		
		
		
		x1_default = 25;
		x1 = x1_default;
		y1 = 20;

		 
		
		// print bank name
		if (  piv.getBankName() != null )
			g2d.drawString(piv.getBankName() , x1, y1);
		y1 = y1 + 10;
		// print bank branch name
		if (  piv.getBankBranchName() != null )
			g2d.drawString(piv.getBankBranchName() , x1, y1);
		y1 = y1 - 10;

		x1 = x1 + 135;
		// print ceb branch
		if (  piv.getCEBBranch() != null )
			g2d.drawString(piv.getCEBBranch()  , x1, y1);

		x1 = x1 + 170;
		// print cost center
		if ( piv.getCostCenterNo() != null )
			g2d.drawString(piv.getCostCenterNo()  , x1, y1);

		x1 = x1 + 90;
		// print date
		if ( piv.getDate()  != null )
			g2d.drawString( piv.getDate( ) , x1, y1);

		x1 = x1_default + 5;
		y1 = y1 + 30;
		// print reference number
		if (  piv.getReferenceNo()  != null )
			g2d.drawString(piv.getReferenceNo(), x1, y1);
		

		x1 = 230;
		// print description	
		if (piv.getJobDescription() != null)
			g2d.drawString( piv.getJobDescription() , x1, y1);
		y1 = y1 + 10;
		x1 = x1_default + 5;
		// print piv number
		if (  piv.getPIVNumber() != null )
			g2d.drawString("PIV No :" + piv.getPIVNumber() , x1, y1);
		
		
		 
		y1 = y1 + 30;
		// print Depositor name
		if (piv.getDepositorName() != null)
			g2d.drawString(piv.getDepositorName() , x1, y1);
		y1 = y1 + 10;
		// print Depositor NIC
		if (  piv.getDepositorIDNumber() != null )
			g2d.drawString(piv.getDepositorIDNumber() , x1, y1);

		// print address 
		String tempStreetAddress,tempSuburb,tempCity,tempPostalCode = "";
		tempStreetAddress = piv.getStreetAddress();
		tempSuburb = piv.getSuburb()   ;
		tempCity = piv.getCity();


		int tempY = y1 + 40;
		int d1 =10;
		if (piv.getPostalCode() != null)
			tempPostalCode = piv.getPostalCode().toString() ;

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



		y1 = tempY;
		// print bank code
		y1 = y1 + 40;
		if (  piv.getBankCode()  != null )
			g2d.drawString(piv.getBankCode() , x1, y1);

		// print branch code
		y1 = y1 + 20;
		if (  piv.getBranchCode()  != null )
			g2d.drawString(piv.getBranchCode() , x1, y1);



		// print cheque number
		y1 = y1 + 20;
		if (  piv.getChequeNo()   != null )
			g2d.drawString(piv.getChequeNo().toString() , x1, y1);

		// print cheque date
		y1 = y1 + 10;
		if (  piv.getChequeDate()  != null )
			g2d.drawString( piv.getChequeDate() , x1, y1);


		// print amount in words
		y1 = y1 + 20;
		x1 = x1_default + 10;
		if (  piv.getAmountInWords()  != null )
			g2d.drawString( piv.getAmountInWords() , x1, y1);
		
		// print amount in figure
		y1 = y1 + 20;		 
		if (  piv.getAmountInFigures() != null )
			g2d.drawString( piv.getAmountInFigures() , x1, y1);

		// print prepared by
		x1 = x1_default ;
		y1 = y1 + 20;		 
		if (  piv.getPreparedBy()  != null )
			g2d.drawString( piv.getPreparedBy() , x1, y1);
		
		
		
		
		// print Miscellaneous income
		x1 = 390;
		y1 = 100;
		g1 = 20;
		if (  piv.getMiscellaneousIncome()  != null )
			g2d.drawString(padLeft(nf.format(piv.getMiscellaneousIncome()),13), x1, y1);

		// print debtors
		y1 = y1 + g1;
		if (  piv.getEletricityDebtors()  != null )
			g2d.drawString(padLeft(nf.format(piv.getEletricityDebtors()),13), x1, y1);

		// print security deposite
		y1 = y1 + g1 ;
		if (  piv.getSecurityDeposit()  != null )
			g2d.drawString(padLeft(nf.format(piv.getSecurityDeposit()),13), x1, y1);

		// print service connection
		y1 = y1 + g1 ;
		if (  piv.getServiceConnectionOrElectricitySchemes()  != null )
			g2d.drawString(padLeft(nf.format(piv.getServiceConnectionOrElectricitySchemes()),13), x1, y1);

		// print tender deposite
		y1 = y1 + g1 + 5 ;
		if (  piv.getTenderDeposits()  != null )
			g2d.drawString(padLeft(nf.format(piv.getTenderDeposits()),13), x1, y1);

		// print miscellaneous deposites
		y1 = y1 + g1;
		if (  piv.getMiscellaneousDeposits()  != null )
			g2d.drawString(padLeft(nf.format(piv.getMiscellaneousDeposits()),13), x1, y1);

		// print cash in transite
		y1 = y1 + g1;
		if (  piv.getCashInTransit()  != null )
			g2d.drawString(padLeft(nf.format(piv.getCashInTransit()),13), x1, y1);

		// print dishonoured cheques
		y1 = y1 + g1;
		if (  piv.getForDishonouredCheques() != null )
			g2d.drawString(padLeft(nf.format(piv.getForDishonouredCheques()),13), x1, y1);

		// print sub total
		y1 = y1 + g1 * 2 + 10;
		if (  piv.getSubTotal() != null )
			g2d.drawString(padLeft(nf.format(piv.getSubTotal()),13), x1, y1);

		// print VAT
		y1 = y1 + g1 + 5 ;
		if (  piv.getVat()  != null )
			g2d.drawString(padLeft(nf.format(piv.getVat()),13), x1, y1);

		// print  GRAND total
		y1 = y1 + g1 * 2 + 5;
		if (  piv.getGrandTotal()  != null )
				g2d.drawString(padLeft(nf.format(piv.getGrandTotal()),13), x1, y1);
		
		// print loan reference
		x1 = 300 ;
		y1 = y1 + 25;		 
		if (  piv.getLoanReference() != null )
			g2d.drawString( piv.getLoanReference() , x1, y1);
		
		// print loan amount
	
		y1 = y1 + 10;		 
		if (  piv.getLoanAmount()  != null )
			g2d.drawString(padLeft(nf.format(piv.getLoanAmount()),13), x1, y1);
		
		return Printable.PAGE_EXISTS;
	}



	public void Print(boolean showDialog)
	{
		PrinterJob printerJob = PrinterJob.getPrinterJob();

		Book book = new Book();	
		//book.append(new PrintPiv(this.pivDetail,this.applicant), new PageFormat());
		book.append(new PrintPiv(this.piv), new PageFormat());
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

	public static String padLeft(String s, int n) {
		return String.format("%1$#" + n + "s", s);
	}
	public static String padRight(String s, int n) {
		return String.format("%1$-" + n + "s", s);
	}




}
