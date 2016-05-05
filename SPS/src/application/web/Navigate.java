package application.web;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Navigate extends ActionSupport implements SessionAware, ParameterAware {
	// Common Fields
	private static final Log log = LogFactory.getLog(ApplicationForm.class);
	//private static final String confirmPath="Application>Confirm Application";
	private Map<String, Object> session;
	private String loggedInUserId;
	//private String path;
	
	public Navigate() {
		super();
		//setEmf(Persistence.createEntityManagerFactory("SMC"));
		//setFormat(new Format());
	}
	public String execute(){
		getSessionUserName();
		//setState("confirmApp");
		//setPath(confirmPath); 
		return SUCCESS;
	}
	public void getSessionUserName() {
		//setSession(ActionContext.getContext().getSession());
		// log.info( getSession());
		log.info(getSession());
		setLoggedInUserId(getSession().get("userName").toString());
	}
	
	
	public String getLoggedInUserId() {
		return loggedInUserId;
	}

	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}

	@Override
	public void setParameters(Map<String, String[]> arg0) {
		// TODO Auto-generated method stub
		
	}

}
