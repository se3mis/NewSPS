package offDoc.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import offDoc.model.LetterReason;
import offDoc.model.LetterReasonPK;
import offDoc.service.OffDocsEjb;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import piv.service.PivDetailEjb;

 

import util.common.EstimateStatus;
import util.common.Format;
import util.common.Path;
import util.common.Phase;

import application.model.Applicant;
import application.model.Application;
import application.model.WiringLandDetail;
import application.model.WiringLandDetailPK;
import application.service.ApplicantEjb;
import application.service.ApplicationEjb;
import application.service.WiringLandDetailEjb;

import com.opensymphony.xwork2.ActionSupport;

import costcenter.model.Gldeptin;
import costcenter.model.Gldeptm;
import costcenter.service.GldeptinEjb;
import costcenter.service.GldeptmEjb;

import estimate.model.Pcesthtt;
import estimate.model.Speststd;
import estimate.service.PcesthttEjb;
import estimate.service.SpeststdEjb;

@SuppressWarnings("serial")
public class loadDownloadUserManual extends ActionSupport implements SessionAware, ParameterAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private static final String viewPath="Downloads>User Manual";
	private String path;
	private String lblError = null;
	private String lblSuccess = null;

 
	 
	 
 


	 
	private InputStream fileInputStream;
	
	public String execute(){
		setLblError(null);

		setLoggedInUserId(getSession().get("userName").toString());
		setPath(viewPath);
		 	
	 
		
		return SUCCESS;
	}

	 
	public String getSessionKey(String key) {
		return getSession().get(key).toString();
	}
 
	
	
	public String DownlaodManual() throws FileNotFoundException
	{
		Path path = new Path();
		  String REPORT_DIRECTORY = "" ;
		 
		if (System.getProperty("os.name").equals("Windows XP") || System.getProperty("os.name").equals("Windows 7") ){
			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY");		
		}else{
			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY_LINUX");		 
		}
	 
		fileInputStream = new FileInputStream(REPORT_DIRECTORY + "/" + "SMCTrainingManual.pdf");
		return "successprint";
		 
	}
	
	public String DownlaodStdReference() throws FileNotFoundException
	{
		Path path = new Path();
		  String REPORT_DIRECTORY = "" ;
		 
		if (System.getProperty("os.name").equals("Windows XP") || System.getProperty("os.name").equals("Windows 7") ){
			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY");		
		}else{
			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY_LINUX");		 
		}
	 
		fileInputStream = new FileInputStream(REPORT_DIRECTORY + "/" + "StandardEstimateDetails.pdf");
		return "successprint";
		 
	}
	
	
	
	public String Exit(){
		return "closed";
	}
	

 







	@Override
	public void setParameters(Map<String, String[]> parameters){
		// TODO Auto-generated method stub
		this.parameters=parameters;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session=session;
	}

	public String getLoggedInUserId() {
		return loggedInUserId;
	}

	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getLblError() {
		return lblError;
	}

	public void setLblError(String lblError) {
		if (lblError == null )
		{

			this.lblError = lblError;
		}
		else
		{
			if (lblError == "Select Estimate")
				this.lblError = null;
			else
				this.lblError = lblError;			  


		}
	}

	public String getLblSuccess() {
		return lblSuccess;
	}

	public void setLblSuccess(String lblSuccess) {
		this.lblSuccess = lblSuccess;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public Map<String, String[]> getParameters() {
		return parameters;
	}

	public static String getViewpath() {
		return viewPath;
	}

 
	 

	 

	 
 
 

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	 


}
