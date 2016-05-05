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



public class PDFEstimateReference implements Printable {

	String costCenterName;
	String address;
	String applicationNumber;String customerName;
	String streeet;String phase;String appDate;Date visitedDate;String session;
	String  suberb; String city; String postalCode;
	String[] reasons;
	String costCenterArea;
	Graphics2D g2d ;
	public  String EXPORT_REPORT_DIRECTORY ;

	public PDFEstimateReference(String costCenterName,String costCenterArea,
			String address,String applicationNumber,String customerName,
			String street,String  suberb,String city,String postalCode,String phase,String appDate,Date visitedDate,String session,String[] reasons)
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
		this.visitedDate = visitedDate;
		this.session = session;
		this.reasons = reasons;
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



		// print name
		g2d.setFont(ft_bold);
		y = y + d * 5;
		x = 0;
		g2d.drawString(this.customerName, x, y);

		g2d.setFont(ft);
		y = y + d;
		g2d.drawString(this.streeet + ", " + this.suberb + ",", x, y);
		y = y + d;
		g2d.drawString(this.city + ", " , x, y);
		y = y + d;
		g2d.drawString(this.postalCode , x, y);

		y = y + d * 3;
		g2d.setFont(ft_bold);
		g2d.drawString("Dear Sir/Madam" , x, y);


		y = y + d*4;
		line ="Application No " + this.applicationNumber + " dated " + this.appDate + " for " + this.phase + ", Service Connection  to premises, " + this.streeet + ", " + this.suberb + ", " + this.city + ".";
		rest = printUnderLine ( ft, line, x,y);
		while (rest != "") {
			y = y + 15;
			rest = printUnderLine( ft, rest, x,y);
		}
		Format fmt = new Format();
		y = y + d * 2;
		line ="This refers to your application dated " + this.appDate + " on the above matter. An officer has visited the said premises on " + fmt.formatDate(this.visitedDate) + " in the " + this.session + " session to prepare the estimate in order to provide the requested power supply. ";
		rest = printLine( ft, line, x,y,99,90);
		while (rest != "") {
			y = y + 15;
			rest = printLine( ft, rest, x,y,99,90);
		}

		y = y + d * 3 ;
		line = "I regret to inform you that it is not possible to process the application due to following reasons(s):";
		g2d.drawString(line, x, y);



		y = y + d*2;
		int j = 1;

		for (String key : this.reasons) {
			y = y + d;

			line = j + "). " + key;
			rest = printLine( ft_bold, line, x,y,90,80);
			while (rest != "") {
				y = y + 15;
				rest = printLine( ft_bold, rest, x,y,90,80);
			}			

			j++;
		}
		y = y + d*2 ;	
		line = "My officers will require to inspect the said premises again, therefore you are kindly requested to visit this office together with required information and to obtain advices in this regard please.";
		rest = printLine( ft, line, x,y,99,90);
		while (rest != "") {
			y = y + 15;
			rest = printLine( ft, rest, x,y,99,90);
		}			


		y = y + d*3 ;		 
		line = "Thanking you.";
		g2d.drawString(line, x, y);

		y = y + 3*d ;		 
		line = "Yours faithfully,";
		g2d.drawString(line, x, y);

		y = y + d*4 ;		 
		line = "........................................";
		g2d.drawString(line, x, y);

		y = y + d ;		 
		line = "Area Electrical Engineer";
		g2d.drawString(line, x, y);

		y = y + d ;		 
		line = this.costCenterArea;
		g2d.drawString(line, x, y);

		y = y + d ;		 
		line = "Date -" + fmt.formatDate(new Date()) ;
		g2d.drawString(line, x, y);



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
		book.append(new PDFEstimateReference(this.costCenterName,
				this.costCenterArea,
				this.address,
				this.applicationNumber ,
				this.customerName,
				this.streeet,
				this.suberb,
				this.city, 
				this.postalCode,
				this.phase,
				this.appDate,
				this.visitedDate ,
				this.session,this.reasons), new PageFormat());
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
		if (System.getProperty("os.name").equals("Windows XP")){
			 
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
			com.itextpdf.text.Font TIMES_BOLD_14 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 15);





			Format fmt = new Format();
			Paragraph par = new Paragraph();



			par = new   Paragraph("My No :- " + this.applicationNumber,TIMES_ROMAN);
			par.setAlignment(Element.ALIGN_RIGHT);
			document.add(par);
			document.add(new Paragraph("\n"));

			Chunk chunk = new Chunk("Ceylon Electricity Board, " + this.costCenterName.trim() + " - Area Office",TIMES_BOLD_14);
			chunk.setUnderline(0.2f, -2f);
			par = new   Paragraph(chunk);
			par.setAlignment(Element.ALIGN_CENTER);
			document.add(par);

			//			par = new   Paragraph(,TIMES_ROMAN);
			//			par.setAlignment(Element);
			//			document.add(par);

			chunk = new Chunk(this.address,TIMES_BOLD_14);
			chunk.setUnderline(0.2f, -2f);
			par = new   Paragraph(chunk);			 
			par.setAlignment(Element.ALIGN_CENTER);
			document.add(par);		 
			document.add(new Paragraph("\n"));

			par = new   Paragraph(this.customerName,TIMES_BOLD);
			par.setAlignment(Element.ALIGN_LEFT);
			document.add(par);

			par = new   Paragraph(this.streeet + ", " + this.suberb + ",",TIMES_ROMAN);
			par.setAlignment(Element.ALIGN_LEFT);
			document.add(par);

			par = new   Paragraph(this.city + ", ",TIMES_ROMAN);
			par.setAlignment(Element.ALIGN_LEFT);
			document.add(par);

			par = new   Paragraph(this.postalCode,TIMES_ROMAN);
			par.setAlignment(Element.ALIGN_LEFT);
			document.add(par);
			document.add(new Paragraph("\n"));

			par = new   Paragraph("Dear Sir/Madam",TIMES_BOLD);
			par.setAlignment(Element.ALIGN_LEFT);
			document.add(par);
			document.add(new Paragraph("\n"));

			chunk = new Chunk("Application No " + this.applicationNumber + " dated " + this.appDate + " for " + this.phase + ", Service Connection  to premises, " + this.streeet + ", " + this.suberb + ", " + this.city + ".",TIMES_BOLD);
			chunk.setUnderline(0.2f, -2f);
			par = new   Paragraph(chunk);
			par.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(par);
			document.add(new Paragraph("\n"));

			par = new   Paragraph("This refers to your application dated ",TIMES_ROMAN);// + this.appDate + " on the above matter. An officer has visited the said premises on " + fmt.formatDate(this.visitedDate) + " in the " + this.session + " session to prepare the estimate in order to provide the requested power supply. ",TIMES_ROMAN);
			chunk = new Chunk(this.appDate,TIMES_BOLD);
			par.add(chunk);
			chunk = new Chunk(" on the above matter. An officer has visited the said premises on ",TIMES_ROMAN);
			par.add(chunk);
			chunk = new Chunk(fmt.formatDate(this.visitedDate),TIMES_BOLD);
			par.add(chunk);
			chunk = new Chunk( " in the ",TIMES_ROMAN);
			par.add(chunk);
			chunk = new Chunk(this.session,TIMES_BOLD);
			par.add(chunk);
			chunk = new Chunk(" session to prepare the estimate in order to provide the requested power supply. ",TIMES_ROMAN);
			par.add(chunk);
			
			par.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(par);
			document.add(new Paragraph("\n"));

			par = new   Paragraph("I regret to inform you that it is not possible to process the application due to following reasons(s):",TIMES_ROMAN);
			par.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(par);
			document.add(new Paragraph("\n"));




			int j = 1;

			for (String key : this.reasons) {

				par = new   Paragraph(j + "). " + key,TIMES_BOLD);
				par.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(par);
				j++;
			}
			document.add(new Paragraph("\n"));

			par = new   Paragraph("My officers will require to inspect the said premises again, therefore you are kindly requested to visit this office together with required information and to obtain advices in this regard please.",TIMES_ROMAN);
			par.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(par);
			document.add(new Paragraph("\n"));

			par = new   Paragraph("Thanking you.",TIMES_ROMAN);
			par.setAlignment(Element.ALIGN_LEFT);
			document.add(par);
			document.add(new Paragraph("\n"));

			par = new   Paragraph("Yours faithfully,",TIMES_ROMAN);
			par.setAlignment(Element.ALIGN_LEFT);
			document.add(par);
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));

			par = new   Paragraph(".................................................",TIMES_BOLD);
			par.setAlignment(Element.ALIGN_LEFT);
			document.add(par);
			
			par = new   Paragraph("Area Electrical Engineer",TIMES_BOLD);
			par.setAlignment(Element.ALIGN_LEFT);
			document.add(par);

			par = new   Paragraph(this.costCenterArea,TIMES_BOLD);
			par.setAlignment(Element.ALIGN_LEFT);
			document.add(par);

			par = new   Paragraph("Date -" + fmt.formatDate(new Date()) ,TIMES_BOLD);
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
