package applicationBS.web;

import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

//import util.common.Format;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class TestPara extends ActionSupport implements SessionAware,ParameterAware {
	
	private Map<String, Object> session;
	//private String loggedInUserId;
	// private Map<?, ?> parameters;
	private String userName;
	private String password;
	//@SuppressWarnings("unchecked")
	private Map <String, String[]> parameters;
	
	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	
	public Map <String, String[]> getParameters() {
		return parameters;
	}

	

	public Map<String, Object> getSession() {
		return session;
	}

	//private Format format;

	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
		
	}

	@Override
	public void setParameters(Map <String, String[]> parameters) {
		this.parameters=parameters;
		
	}
	
	@SuppressWarnings("unchecked")
	public String execute(){
		Map parameters = getParameters();
		//Map parameters=ActionContext.getContext().getParameters();
		//setParameters(parameters);
		//setData();
		System.out.println(parameters);
		//System.out.println(getParameters());
		//getData();
		//setSession(ActionContext.getContext().getSession());
		System.out.println(getSession());
		//System.out.println(getSession().get("userName"));
		
		
		return SUCCESS;
	}
	
	public String enter(){
		Map <String, String[]> parameters = getParameters();
		//Map parameters=ActionContext.getContext().getParameters();
		//setParameters(parameters);
		setData();
		System.out.println(parameters);
		//System.out.println(getParameters());
		getData();
		System.out.println(getSession());
		System.out.println(getSession().get("userName"));
		
		return SUCCESS;
	}
	
	//@SuppressWarnings("unchecked")
	private void setData(){
		getParameters().get("userName");
		String[] par =(String[])getParameters().get("userName");
		setUserName(((par!=null)?par[0]:"NO"));
		String[] par2 =(String[])getParameters().get("password");
		setPassword(((par2!=null)?par2[0]:"NO"));
		
		
	}
	
	private void getData(){
		System.out.println(getParameters());
		System.out.println(getUserName());
		System.out.println(getPassword());
		
		
	}

}
