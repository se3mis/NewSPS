package reports.web;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import estimate.web.A3Reports;


import util.common.Path;

import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
 


public class report extends ActionSupport implements SessionAware, ParameterAware {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public  String REPORT_DIRECTORY ;
	public  String EXPORT_REPORT_DIRECTORY ;
	private String costCenter;
	private Map<String, Object> sessionX;
	private Map <String, String[]> parametersX;
	 
	public String generateReport(String reportName,HashMap<String, Object> param,Map<String, Object> session,String REPORT_DIRECTORY,String EXPORT_REPORT_DIRECTORY){

		String pdfPath = "";
		Path path = new Path();
//		System.out.println(System.getProperty("os.name"));
//		if (System.getProperty("os.name").equals("Windows XP") || System.getProperty("os.name").equals("Windows 7") ){
//			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY");
//			EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY");
//			
//		}else{
//			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY_LINUX");
//			EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY_LINUX");
//		}
//		

		 
		try {
			String db_url = null;
			String db_userName = null;
			String db_password = null;
			String db_driver = null;

			Connection conn = null;
			this.sessionX = session;
			String region = getSessionKey("region");
			

			db_url = path.getDBUrl(region);
			
			db_userName = path.getDBUserName(region);
			db_password = path.getDBPassword(region);
			db_driver = path.getReportPath("db_driver");  	

			try {

				Class.forName(db_driver);// .newInstance();
				System.out.println("Con:" + db_url + "" + db_userName +""+ db_password+"@@@" );
				try{
				conn =   DriverManager.getConnection(db_url.trim(),
						db_userName.trim(), db_password.trim());
				}catch(Exception e){
					System.out.println("JError " + e);
				}
			 //////////////////////////////
				 File file = new File(REPORT_DIRECTORY + "/" + reportName + ".jrxml" );
			        
			        if(!file.exists())
			            throw new JRRuntimeException("File " + file + " not found. The report design must be compiled first.");
///////////////////////////////////////////
				JasperReport jasperReport =  JasperCompileManager.compileReport(REPORT_DIRECTORY + reportName + ".jrxml");
				System.out.println(REPORT_DIRECTORY + reportName + ".jrxml");
				System.out.println(jasperReport);
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);  
 

			//	JasperViewer.viewReport(jasperPrint,false);
				
			//	setCostCenter(getSessionKey("costCenterNo"));
				JRPdfExporter pdf = new JRPdfExporter();

				Calendar calendar = Calendar.getInstance();
				 
				pdf.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");

				pdfPath = EXPORT_REPORT_DIRECTORY +
				calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH ) + 1)+ "-" 
				+ calendar.get(Calendar.DATE)+ "-" + calendar.get(Calendar.HOUR) + "-" + calendar.get(Calendar.MINUTE)+ "-" 
				+ calendar.get(Calendar.SECOND)+ "-" + calendar.get(Calendar.MILLISECOND)   + "R.pdf";
				
				pdf.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
				pdf.setParameter(JRPdfExporterParameter.OUTPUT_FILE_NAME, pdfPath);
				pdf.exportReport();


				System.out.println("Joboxxxxxxxxxxxxxxxxxxxxxxxxxxxx dclose");

				 

			} catch (Exception ex) {
				System.out.println("Joboxxxxxxxxx conn.close();xxxxx dclose"+ ex);
				throw new Exception("Database Connection Error !!");
			} finally {
				System.out.println("Joboxxxxxxxxx conn.close();xxxxx dclose");
				if(conn!=null){
					conn.close();
				}
	            System.out.println("Joboxxxxxxxxx conn.close() tttttttttttttttttt;xxxxx dclose");
	        }
			


		} catch (Exception e) {
			e.printStackTrace();


		}

		return pdfPath ;
	}
	
	public String generateReportA3(String reportName,HashMap<String, Object> param,Map<String, Object> session,String REPORT_DIRECTORY,String EXPORT_REPORT_DIRECTORY){

		String pdfPath = "";
		Path path = new Path();
//		System.out.println(System.getProperty("os.name"));
//		if (System.getProperty("os.name").equals("Windows XP") || System.getProperty("os.name").equals("Windows 7") ){
//			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY");
//			EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY");
//			
//		}else{
//			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY_LINUX");
//			EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY_LINUX");
//		}
//		

		 
		try {
			String db_url = null;
			String db_userName = null;
			String db_password = null;
			String db_driver = null;

			Connection conn = null;
			this.sessionX = session;
			String region = getSessionKey("region");
			

			db_url = path.getDBUrl(region);
			db_userName = path.getDBUserName(region);
			db_password = path.getDBPassword(region);
			db_driver = path.getReportPath("db_driver");  	

			try {

				Class.forName(db_driver);// .newInstance();
				conn =   DriverManager.getConnection(db_url,
						db_userName, db_password);
			 //////////////////////////////
				 File file = new File(REPORT_DIRECTORY + "/" + reportName + ".jrxml" );
			        
			        if(!file.exists())
			            throw new JRRuntimeException("File " + file + " not found. The report design must be compiled first.");
///////////////////////////////////////////
				JasperReport jasperReport =  JasperCompileManager.compileReport(REPORT_DIRECTORY + reportName + ".jrxml");
				System.out.println(REPORT_DIRECTORY + reportName + ".jrxml");
				System.out.println(jasperReport);
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);  
 

			//	JasperViewer.viewReport(jasperPrint,false);
				
			//	setCostCenter(getSessionKey("costCenterNo"));
				JRPdfExporter pdf = new JRPdfExporter();

				Calendar calendar = Calendar.getInstance();
				 
				pdf.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");

				 pdfPath = EXPORT_REPORT_DIRECTORY +
				calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH ) + 1)+ "-" 
				+ calendar.get(Calendar.DATE)+ "-" + calendar.get(Calendar.HOUR) + "-" + calendar.get(Calendar.MINUTE)+ "-" 
				+ calendar.get(Calendar.SECOND)+ "-" + calendar.get(Calendar.MILLISECOND)   + "R.pdf";
				
				pdf.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
				pdf.setParameter(JRPdfExporterParameter.OUTPUT_FILE_NAME, pdfPath);
				pdf.exportReport();
				A3Reports a3re = new A3Reports();
				a3re.printReport(pdfPath );
				System.out.println("Joboxxxxxxxxxxxxxxxxxxxxxxxxxxxx dclose");

				 

			} catch (SQLException ex) {
				System.out.println("Joboxxxxxxxxx conn.close();xxxxx dclose"+ ex);
				throw new Exception("Database Connection Error !!");
			} finally {
				System.out.println("Joboxxxxxxxxx conn.close();xxxxx dclose");
				if(conn!= null){
	            conn.close();
				}
	            System.out.println("Joboxxxxxxxxx conn.close() tttttttttttttttttt;xxxxx dclose");
	        }
			


		} catch (Exception e) {
			e.printStackTrace();


		}

		return pdfPath ;
	}
	
	public String getSessionKey(String key) {
		return getSession().get(key).toString();
	}

	public String getCostCenter() {
		return costCenter;
	}


	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	@Override
	public void setParameters(Map<String, String[]> parameters){
		this.parametersX=parameters;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.sessionX=session;
	}
	public Map<String, Object> getSession() {
		return sessionX;
	}
	public Map<String, String[]> getParameters() {
		return parametersX;
	}


	 

	 

	 
	
}
