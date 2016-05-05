package offDoc.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.TextAttribute;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.AttributedString;
import java.util.Calendar;
import java.util.Date;


import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import util.common.Format;
import util.common.Path;



public class PDFEstimateAmount implements Printable {

	String costCenterName;
	String address;
	String applicationNumber;String customerName;
	String streeet;String phase;String appDate; 
	String  suberb; String city; String postalCode;
	String totalCost;
	String costCenterArea;
	String securityDeposit;
	Graphics2D g2d ;
	public  String EXPORT_REPORT_DIRECTORY ;

	public PDFEstimateAmount(String costCenterName,String costCenterArea,
			String address,String applicationNumber,String customerName,
			String street,String  suberb,String city,String postalCode,String phase,String appDate,String totalCost ,String securityDeposit)
	{
		this.costCenterName = costCenterName;
		this.costCenterArea = costCenterArea;
		this.address = address;
		this.applicationNumber = applicationNumber;
		this.customerName = customerName;
		this.streeet = street;
		this.suberb = suberb;
		this.city = city;
		this.postalCode = postalCode;
		this.phase = phase;
		this.appDate = appDate;
		this.totalCost = totalCost;
		this.securityDeposit = securityDeposit;

	}

	private String printUnderLine(Font ft, String line,int x, int y)
	{
		g2d.setFont(ft);
		String tmp,rest = "";
		if (line.length() < 80)
		{		 
			AttributedString as1 = new AttributedString( line);
			as1.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);			
			g2d.drawString(as1.getIterator(), x, y);			 
			return "";
		}
		else
		{
			int idn = line.indexOf(" ",70);
			tmp = line.substring(0, idn);
			rest = line.substring(idn + 1,line.length());


			AttributedString as1 = new AttributedString( tmp);
			as1.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);			
			g2d.drawString(as1.getIterator(), x, y);				

		}
		return rest;
	}
	private String printLine(Font ft, String line,int x, int y,int lineLength,int indexStartAt)
	{

		g2d.setFont(ft);
		String tmp,rest = "";
		if (line.length() < lineLength)
		{
			g2d.drawString(line,x, y);

			return "";
		}
		else
		{
			int idn = line.indexOf(" ",indexStartAt);
			tmp = line.substring(0, idn);
			rest = line.substring(idn + 1,line.length());

			g2d.drawString(tmp,x, y);
		}
		return rest;
	}


	@Override
	public int print(Graphics g, PageFormat format, int pageIndex)
	throws PrinterException {
		/* We'll assume that Jav2D is available.
		 */
		g2d = (Graphics2D) g;
		/* Move the origin from the corner of the Paper to the corner
		 * of the imageable area.
		 */
		g2d.translate(format.getImageableX(), format.getImageableY());
		/* Set the text color.
		 */
		g2d.setPaint(Color.black);


		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


		Font ft =   new Font ("Times New Roman", Font.PLAIN, 12);
		Font ft_bold =   new Font ("Times New Roman", Font.BOLD, 12);
		Font ft_heading =   new Font ("Times New Roman", Font.BOLD, 15);
		Font ft_1 =   new Font ("Times New Roman", Font.PLAIN, 14);
		g2d.setFont(ft_1);

		int x;
		int y;
		int d = 12;
		int width = 100;

		double xx = format.getWidth();
		double yy = format.getHeight();

		x=0;
		y=10;
		// print reference no
		g2d.drawString(padLeft("My No :- " + this.applicationNumber,width), x, y);
		y = y + 15;
		String line = "Ceylon Electricity Board, " + this.costCenterName + " - Area Office";
		String rest = printUnderLine( ft_heading, line, x,y);
		while (rest != "") {
			y = y + 15;
			rest = printUnderLine( ft_heading, line, x,y);
		}
		y = y + 15;
		line = this.address;
		rest = printUnderLine( ft_heading, line, x,y);
		while (rest != "") {
			y = y + 15;
			rest = printUnderLine( ft_heading, line, x,y);
		}




		return Printable.PAGE_EXISTS;
	}

	public static String padLeft(String s, int n) {
		return String.format("%1$#" + n + "s", s);
	}
	public static String padRight(String s, int n) {
		return String.format("%1$-" + n + "s", s);
	}



	public void Printx()
	{
		PrinterJob printerJob = PrinterJob.getPrinterJob();

		Book book = new Book();	
		book.append(new PDFEstimateAmount(this.costCenterName,
				this.costCenterArea,
				this.address,
				this.applicationNumber ,
				this.customerName,
				this.streeet,
				this.suberb,
				this.city, 
				this.postalCode,
				this.phase,
				this.appDate,this.totalCost,this.securityDeposit), new PageFormat());
		printerJob.setPageable(book);


		boolean doPrint =  printerJob.printDialog();
		if (doPrint) {

			try {
				printerJob.print();
			} catch (PrinterException exception) {
				System.err.println("Printing error: " + exception);
			}
		}

	}

	public String PrintPDF()
	{
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);


		String pdfPath = "";
		Path path = new Path();
		System.out.println(System.getProperty("os.name"));
		if (System.getProperty("os.name").equals("Windows XP") || System.getProperty("os.name").equals("Windows 7")){

			EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY");

		}else{

			EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY_LINUX");
		}

		Calendar calendar = Calendar.getInstance();
		pdfPath = EXPORT_REPORT_DIRECTORY +
		calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH ) + 1)+ "-" 
		+ calendar.get(Calendar.DATE)+ "-" + calendar.get(Calendar.HOUR) + "-" + calendar.get(Calendar.MINUTE)+ "-" 
		+ calendar.get(Calendar.SECOND)+ "-" + calendar.get(Calendar.MILLISECOND)   + "L.pdf";



		try {




			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfPath,false));
			document.open();


			com.itextpdf.text.Font TIMES_BOLD = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);
			com.itextpdf.text.Font TIMES_ROMAN = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12);
			com.itextpdf.text.Font TIMES_BOLD_14 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 13);





			Format fmt = new Format();
			Paragraph par = new Paragraph();

 
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));
			 
			
		 	
		 	
			par = new   Paragraph("My No :- " + this.applicationNumber ,TIMES_ROMAN);
			par.setAlignment(Element.ALIGN_RIGHT);
			document.add(par);
			
			par = new Paragraph();
			par.setSpacingAfter(0);		 
		 	document.add(par);

		 	Chunk chunk = new Chunk();
//			Chunk chunk = new Chunk("CEYLON ELECTRICITY BOARD, " + this.costCenterName.trim().toUpperCase() + " - Area Office",TIMES_BOLD_14);
//			chunk.setUnderline(0.2f, -2f);
//			par = new   Paragraph(chunk);
//			par.setAlignment(Element.ALIGN_CENTER);
//			document.add(par);
//
//		 
//
//			chunk = new Chunk(this.address.toUpperCase(),TIMES_BOLD_14);
//			chunk.setUnderline(0.2f, -2f);
//			par = new   Paragraph(chunk);			 
//			par.setAlignment(Element.ALIGN_CENTER);
//			document.add(par);		 
//			
//			par = new Paragraph();
//			par.setSpacingAfter(0);	
//			par.setSpacingBefore(10);		
//		 	document.add(par);


			par = new   Paragraph(this.customerName,TIMES_BOLD);
			par.setAlignment(Element.ALIGN_LEFT);
			document.add(par);

			par = new   Paragraph(this.streeet + ", " + this.suberb + ",",TIMES_ROMAN);
			par.setAlignment(Element.ALIGN_LEFT);
			document.add(par);

			par = new   Paragraph(this.city + ", ",TIMES_ROMAN);
			par.setAlignment(Element.ALIGN_LEFT);
			document.add(par);

			par = new Paragraph();
			par.setSpacingAfter(0);	
			par.setSpacingBefore(10);		
		 	document.add(par);

			par = new   Paragraph("Dear Sir/Madam",TIMES_BOLD);
			par.setAlignment(Element.ALIGN_LEFT);
			document.add(par);
			par = new Paragraph();
			par.setSpacingAfter(0);	
			par.setSpacingBefore(10);	
		 	document.add(par);


			chunk = new Chunk("Quotation for Electricity Supply to Premises : " + this.streeet + ", " + this.suberb + ", " + this.city + ".",TIMES_BOLD); //+ this.applicationNumber + " dated " + this.appDate + " for " + this.phase + ", Service Connection  to premises, " ,TIMES_BOLD);
			chunk.setUnderline(0.2f, -2f);
			par = new   Paragraph(chunk);
			par.setAlignment(Element.ALIGN_LEFT);
			document.add(par);
			par = new Paragraph();
			par.setSpacingAfter(0);		
			par.setSpacingBefore(20);	
		 	document.add(par);



			par = new   Paragraph("With reference to your application No :- ",TIMES_ROMAN);
			chunk = new Chunk(this.applicationNumber,TIMES_BOLD);
			par.add(chunk);

			chunk = new Chunk(" dated ",TIMES_ROMAN);
			par.add(chunk);
			chunk = new Chunk(this.appDate,TIMES_BOLD);
			par.add(chunk);
			chunk = new Chunk(", I wish to inform you that the cost of a ",TIMES_ROMAN);
			par.add(chunk);
			chunk = new Chunk(this.phase,TIMES_BOLD);
			par.add(chunk);
			chunk = new Chunk(" service connection to the above premises is ",TIMES_ROMAN);
			par.add(chunk);
			
			BigDecimal tot1 = new BigDecimal(0);
			BigDecimal tot2 = new BigDecimal(0);
			if (!(this.totalCost.isEmpty() || this.totalCost.equals("")))
			{
				tot1 = new BigDecimal(this.totalCost.replaceAll("Rs. ", "").replaceAll(",","" ));
				 
				 
			}
			
			if (!(this.securityDeposit.isEmpty() || this.securityDeposit.equals("")))
			  tot2 =  new BigDecimal(this.securityDeposit.replaceAll("Rs. ", "").replaceAll(",","" ));
			
			chunk = new Chunk(tot1.subtract(tot2).toString(),TIMES_BOLD);
			par.add(chunk);
			chunk = new Chunk(" and the security deposit required is ",TIMES_ROMAN);
			par.add(chunk);
			chunk = new Chunk(this.securityDeposit,TIMES_BOLD);
			par.add(chunk);
			chunk = new Chunk(". This quotation is based on standard conditions & if any extra work is required, the additional cost will be intimated to you accrodingly. This will be valid only till ",TIMES_ROMAN);
			par.add(chunk);
			chunk = new Chunk("31st December 2011.",TIMES_BOLD);
			par.add(chunk);
			par.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(par);
			par = new Paragraph();
			par.setSpacingAfter(0);		
			par.setSpacingBefore(5);	
		 	document.add(par);




			par = new   Paragraph("Before the Paying-In-Voucher for the above payments are sent to you, please comply with the following requirements. ",TIMES_ROMAN);
			par.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(par);
			par = new Paragraph();
			par.setSpacingAfter(0);	
			par.setSpacingBefore(5);	
		 	document.add(par);


			String temp = "(a) i. Produce a document issued by the Colombo Muncipal Council in proof of a separate assesment number for the above premises. " + 
			"(On production of this letter to the Assessor's Dept. of the Colombo Muncipal Council, the assessor will arrange to send to the C.E.B a certificate " +
			"of the assessment number of your premises).";
			par = new   Paragraph( temp ,TIMES_ROMAN);
			par.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(par);
			par = new Paragraph();
			par.setSpacingAfter(0);	
			par.setSpacingBefore(5);	
		 	document.add(par);
			
			temp = "    ii. Produce a codument issued from NHDA if the premises is handed over by NHDA. "  ;
			par = new   Paragraph( temp ,TIMES_ROMAN);
			par.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(par);
			
			par = new Paragraph();
			par.setSpacingAfter(0);	
			par.setSpacingBefore(5);	
		 	document.add(par);
		 

			temp = "(b) Forward letters of consent from the owners/occupants of the premises. Necessary forms are enclosed herewith. (Part II of the form should be prefected by you.)"  ;
			par = new   Paragraph( temp,TIMES_ROMAN);
			par.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(par);
			
			par = new Paragraph();
			par.setSpacingAfter(0);	
			par.setSpacingBefore(5);	
		 	document.add(par);
	 

			temp = "(c) Produce a copy of the latest electricity bill after payment of all oustanding electricity dues."  ;
			par = new   Paragraph( temp,TIMES_ROMAN);
			par.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(par);
	 
			par = new Paragraph();
			par.setSpacingAfter(0);	
			par.setSpacingBefore(5);	
		 	document.add(par);
			
			temp = "(d) The internal wiring installation be tested and passed."  ;
			par = new   Paragraph( temp ,TIMES_ROMAN);
			par.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(par);
		 
			par = new Paragraph();
			par.setSpacingAfter(0);	
			par.setSpacingBefore(5);	
		 	document.add(par);
			 
			
			temp = "(e).........................................."  ;
			par = new   Paragraph( temp ,TIMES_ROMAN);
			par.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(par);
			par = new Paragraph();
			par.setSpacingAfter(0);		 
		 	document.add(par);

			
		 	par = new Paragraph();
			par.setSpacingAfter(0);	
			par.setSpacingBefore(10);	
		 	document.add(par);
			
			temp = "ONLY FOR THE UNDERGROUND SERVICES"  ;
			chunk = new Chunk(temp,TIMES_BOLD_14); 
			chunk.setUnderline(0.2f, -2f);
			par = new   Paragraph(chunk);
			par.setAlignment(Element.ALIGN_LEFT);
			document.add(par);
			 

			par = new Paragraph();
			par.setSpacingAfter(0);	
			par.setSpacingBefore(5);	
		 	document.add(par);

			temp = "The above quotation does not include the cost of re-instatement of C.M.C roads. Documents for obtaining the C.M.C permit will be issued to you with the Paying-In-Vouchers. The re-instatement of Private Roads will be your responsibility."  ;
			par = new   Paragraph( temp ,TIMES_ROMAN);
			par.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(par);
			par = new Paragraph();
			par.setSpacingAfter(0);	
			par.setSpacingBefore(5);	
		 	document.add(par);





		 

			par = new   Paragraph("Yours faithfully,",TIMES_ROMAN);
			par.setAlignment(Element.ALIGN_LEFT);
			document.add(par);
			par = new Paragraph();
			par.setSpacingAfter(0);	
			par.setSpacingBefore(15);	
		 	document.add(par);

		 	 


			par = new   Paragraph(".................................................",TIMES_BOLD);
			par.setAlignment(Element.ALIGN_LEFT);
			document.add(par);

			par = new   Paragraph("AREA ELECTRICAL ENGINEER",TIMES_BOLD);
			par.setAlignment(Element.ALIGN_LEFT);
			document.add(par);

			par = new   Paragraph(this.costCenterArea.toUpperCase(),TIMES_BOLD);
			par.setAlignment(Element.ALIGN_LEFT);
			document.add(par);

			par = new   Paragraph("Date -" + fmt.formatDate(new Date()).toUpperCase() ,TIMES_BOLD);
			par.setAlignment(Element.ALIGN_LEFT);
			document.add(par);




			document.close();
			//			try{
			//
			//				Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + PDFpath);
			//				p.waitFor();
			//
			//				System.out.println("Done");
			//
			//			}catch(Exception ex){
			//				ex.printStackTrace();
			//			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pdfPath;
	}
}
