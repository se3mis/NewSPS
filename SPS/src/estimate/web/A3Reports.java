package estimate.web;
//import java.awt.Container;
import java.util.*;
//import java.awt.print.Book;
//import java.awt.print.PageFormat;
//import java.awt.print.Paper;
//import java.awt.print.Printable;
import java.awt.print.PrinterException;
//import java.awt.print.PrinterJob;
//import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

//import javax.print.DocPrintJob;
//import javax.print.PrintService;
import javax.swing.JFrame;

//import standardEstimate.PrintStandardEstimate;

import net.sf.jasperreports.engine.*;
//import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

//import net.sf.jasperreports.view.*;
//import org.jpedal.PdfDecoder;
//import org.jpedal.examples.viewer.gui.popups.PrintPanel;
//import java.io.InputStream;

public class A3Reports extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Connection con;
	private String derectoryPath;
	private String dbDriver;
	private String dbUrl;
	private String userName;
	private String password;
	// private Calendar calendar = Calendar.getInstance();
	private String pdfPath = "";

	public A3Reports(String drectoryPath, String dbDriver, String dbUrl,
			String userName, String password) throws ClassNotFoundException,
			SQLException {
		this.derectoryPath = drectoryPath;
		this.dbDriver = dbDriver;
		this.dbUrl = dbUrl;
		this.userName = userName;
		this.password = password;

		this.establishConnection();
	}
	public A3Reports() throws ClassNotFoundException,
			SQLException {
		
	}
	private Connection establishConnection() throws SQLException {
		if (con == null) {
			try {
				Class.forName(dbDriver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			con = (Connection) DriverManager.getConnection(dbUrl, userName,
					password);
		}
		return con;
	}

	public void genarateReportWithOutPara(String filePath) throws JRException,
			ClassNotFoundException, SQLException {
		try {
			HashMap<String, String> hMap = new HashMap<String, String>();
			JasperReport jasReport = JasperCompileManager
					.compileReport(filePath);
			JasperPrint jaasPrint = JasperFillManager.fillReport(jasReport,
					hMap, con);
			JRPdfExporter pdf = new JRPdfExporter();
			pdf.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");

			pdfPath = derectoryPath + "R.pdf";

			pdf.setParameter(JRPdfExporterParameter.JASPER_PRINT, jaasPrint);
			pdf.setParameter(JRPdfExporterParameter.OUTPUT_FILE_NAME, pdfPath);
			pdf.exportReport();
			this.printReport(pdfPath);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			con.close();
		}
	}

	public void genarateReportWithPara(String filePath, String ParaName,
			String paraValue) throws SQLException {
		try {
			HashMap<String, String> hMap = new HashMap<String, String>();
			hMap.put(ParaName, paraValue);

			JasperReport jasReport = JasperCompileManager
					.compileReport(filePath);
			JasperPrint jaasPrint = JasperFillManager.fillReport(jasReport,
					hMap, con);

			JRPdfExporter pdf = new JRPdfExporter();
			pdf.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
			pdfPath = derectoryPath + "R.pdf";

			pdf.setParameter(JRPdfExporterParameter.JASPER_PRINT, jaasPrint);
			pdf.setParameter(JRPdfExporterParameter.OUTPUT_FILE_NAME, pdfPath);
			pdf.exportReport();
			this.printReport(pdfPath);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
	}

	public void printReport(String filePath ) throws IOException, PrinterException {
		FileInputStream fis = new FileInputStream(filePath);
		PrintPdf printPDFFile = new PrintPdf(fis, "Test Print PDF");
		printPDFFile.print();
		fis.close();
	}

}
