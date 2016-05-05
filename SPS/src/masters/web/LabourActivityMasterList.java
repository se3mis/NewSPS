package masters.web;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import estimate.model.Splaborm;
import estimate.service.SplabormEjb;
 

public class LabourActivityMasterList extends ActionSupport implements SessionAware, ParameterAware  {
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private static final String viewPath="Masters>Labour Activity Masters";
	private String path;
	private String lblError = null;
	private String lblSuccess = null;

	private List<Splaborm> activityList;
	
	public String execute(){
		setLblError(null);
		
		setLoggedInUserId(getSession().get("userName").toString());
		setPath(viewPath);
		 
		
		fillActivityList();
		return SUCCESS;
	}
	
	private void fillActivityList()
	{
		SplabormEjb ejb = new SplabormEjb(getSessionKey("region"));
		 
		this.activityList = ejb.getAll();// .getAll();
	}
	
	
	public String Close(){
		return "close";
	}
	@Override
	public void setParameters(Map<String, String[]> parameters) {
		// TODO Auto-generated method stub
		this.parameters=parameters;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session=session;
	}
	public String getSessionKey(String key) {
		return getSession().get(key).toString();
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

	 

	public Map<String, Object> getSession() {
		return session;
	}

	public Map<String, String[]> getParameters() {
		return parameters;
	}

	public List<Splaborm> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<Splaborm> activityList) {
		this.activityList = activityList;
	}

	 
	
	
	
}
