package masters.web;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Map;

import job.model.Spestcnt;
import job.model.SpestcntPK;
import job.service.SpestcntEjb;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import print.piv.PrintPiv;


 

import com.opensymphony.xwork2.ActionSupport;

public class MasterNorms extends ActionSupport implements SessionAware, ParameterAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private static final String viewPath="Masters>Add Pegging Shedule Materials Details";
	private String path;
	private String lblError = null;
	private String lblSuccess = null;
	
	private String costCenter;
	
	NumberFormat nf = NumberFormat.getInstance();
	
	

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public static String getViewpath() {
		return viewPath;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	
	public Map<String, String[]> getParameters() {
		return parameters;
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
		this.lblError = lblError;
	}
	public String getLblSuccess() {
		return lblSuccess;
	}
	public void setLblSuccess(String lblSuccess) {
		this.lblSuccess = lblSuccess;
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
	
	public String execute(){
		setLblError(null);
		
		setLoggedInUserId(getSession().get("userName").toString());
		setPath(viewPath);
		
		setCostCenter(getSessionKey("costCenterNo"));
		Date dt = new Date();
	
		
		return SUCCESS;
	}
	
	public String close() {
		
		return "close";
	}
	
	public String getSessionKey(String key) {
		return getSession().get(key).toString();
	}
	
}
