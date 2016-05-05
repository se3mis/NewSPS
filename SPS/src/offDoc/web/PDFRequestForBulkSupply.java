package offDoc.web;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;

import util.common.Format;
import util.common.Path;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFRequestForBulkSupply {
	String costCenterName;
	String address;
	String applicationNumber;String customerName;
	String streeet;String phase;String appDate;Date visitedDate;String session;
	String  suberb; String city; String postalCode;String costCenterArea;
	String appForwardTo;String appForwardLocation;
	public  String EXPORT_REPORT_DIRECTORY ;
	
	public PDFRequestForBulkSupply(String costCenterName,String costCenterArea,
			String address,String applicationNumber,String customerName,
			String street,String  suberb,String city,String postalCode,
			String phase,String appDate,Date visitedDate,
			String appForwardTo,String appForwardLocation)
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
		this.appForwardTo = appForwardTo;
		this.appForwardLocation = appForwardLocation;
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
			//	document.add(new Paragraph("First page of the document."));

			//	document.add(new Paragraph("Some more text on the first page with different color and font type.",FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD)));

			//	document.add(new Paragraph());

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
			chunk = new Chunk(" on the above matter.",TIMES_ROMAN);
			par.add(chunk);			
			par.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(par);
			document.add(new Paragraph("\n"));

			par = new   Paragraph("I regret to inform you that I am unable to provide an estimate to you on the above application as your power " +
					"requirement exceeds the ordinary supply limit. therefore, your application is forwarded to the " + this.appForwardTo + " to be treated " + 
					"as a request for a bulk elecritcity supply",TIMES_ROMAN);
			par.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(par);
			document.add(new Paragraph("\n"));




			 
		 

			par = new   Paragraph("Yor are kindly requested to contact the " + this.appForwardTo + " in " + this.appForwardLocation + " for further details",TIMES_ROMAN);
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

			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));

			par = new   Paragraph("Copy To :",TIMES_ROMAN);
			par.setAlignment(Element.ALIGN_LEFT);
			document.add(par);
			
			par = new   Paragraph("1.) C.E(Development) - f.i. & n.a pl.",TIMES_ROMAN);
			par.setAlignment(Element.ALIGN_LEFT);
			document.add(par);
			
			par = new   Paragraph("2.) " + this.appForwardTo + " - f.i. & n.a pl.",TIMES_ROMAN);
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
