package offDoc.web;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
 
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class mainAppEstimateRef {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  String[] selectedReasons =   new String[3] ;
		  selectedReasons[0] = "A responsible person was not available to collect required information ";
		  selectedReasons[1] = "Already a power supply is available at the requested premises Already a power supply is available at the requested. ";
		  selectedReasons[2] = "It is required to get a new premises number to get the new connection from the bulk supply available at the requested premises";
		PDFEstimateReference print = new PDFEstimateReference("ee homagama","Homagama Area","No 240,Kuppiyawathha, High Level Road Athurugiriya Malabe, Colombo 06" ,
				"510.20/SN./2010/0001","M.M. Lakmal Fernando","No 280 2/1 Meemanagoda Road", "Abathale Handiya, Highlevel Road", "Colombo 06","10200",
				"Single Phase","2010 Nov 23",new Date(),"Morning",selectedReasons);
			print.PrintPDF( );
//			
//		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
//		try {
//			String path = "C:\\aaITextTest.pdf";
//			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
//			document.open();
//			document.add(new Paragraph("First page of the document."));
//
//			document.add(new Paragraph("Some more text on the first page with different color and font type.",FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD)));
//		
//			document.close();
//			try{
//				 
//				  Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + path);
//				  p.waitFor();
//			 
//				  System.out.println("Done");
//			 
//			     }catch(Exception ex){
//				  ex.printStackTrace();
//			     }
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (DocumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		
	}

}
