package estimate.web;

import java.util.ArrayList;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.apache.struts.action.Action;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;



public class EnterSealed extends ActionSupport implements SessionAware, ParameterAware{

	private String path;
	private String loggedInUserId;
	private String costCenterNo;
	private String csc;
	
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private static final Log log = LogFactory.getLog(EnterSealed.class);
	private static final String newPath="Estimation>New Job>Delete";
	
	public static String getNewpath() {
		return newPath;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getLoggedInUserId() {
		return loggedInUserId;
	}
	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}
	public String getCostCenterNo() {
		return costCenterNo;
	}
	public void setCostCenterNo(String costCenterNo) {
		this.costCenterNo = costCenterNo;
	}
	public String getCsc() {
		return csc;
	}
	public void setCsc(String csc) {
		this.csc = csc;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}

	public Map<String, String[]> getParameters() {
		return parameters;
	}
	
	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters=parameters;
		
	}
	
	public String getSessionKey(String key) {
        return getSession().get(key).toString();
    }
	
	public String execute()	 
	{
		try {
			setLoggedData();
			setPath(newPath);
			
		}catch(Exception ex){			
			return "error";
		} 
		
		return "success";
	}
	
	public void setLoggedData() {
	       
        log.info(getSession());
        setLoggedInUserId(getSessionKey("userName"));
        setCostCenterNo(getSessionKey("costCenterNo"));
        setCsc(getSessionKey("costCenterName"));
       
    }
	public Map<String, Object> getSession() {
		return session;
	}
}
