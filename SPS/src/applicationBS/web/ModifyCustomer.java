package applicationBS.web;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import estimate.service.TariffCategoryEjb;
import estimate.service.TariffEjb;

@SuppressWarnings("serial")
public class ModifyCustomer extends ActionSupport implements SessionAware, ParameterAware {
	// common
	private static final Log log = LogFactory.getLog(CustomerRegistration.class);
	private static final String modifyPath="Customer>Modify Customer";
	private Map<String, Object> session;
	private String loggedInUserId;
	private String state;
	private String path;
	
	
	private Map <String, String[]> parameters;
	
	public String execute() {
		setState("modifyApplicant");
		System.out.println(getState());
		setLoggedData();
		setPath(modifyPath);
		return SUCCESS;
	}
	
	public void setLoggedData() {
		log.info(getSession());
		setLoggedInUserId(getSessionKey("userName"));
	}
	public String getSessionKey(String key) {
        return getSession().get(key).toString();
    }
	
	

	
	
	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters=parameters;
		
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}
	
	public String getLoggedInUserId() {
		return loggedInUserId;
	}
	public void setLoggedInUserId(final String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}
	public String getState() {
		return state;
	}
	public void setState(final String state) {
		this.state = state;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public Map<String, String[]> getParameters() {
		return parameters;
	}
	
	
}
