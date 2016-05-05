package application.web;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import application.service.ApplicantEjb;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class GenerateIdNo extends ActionSupport implements SessionAware, ParameterAware {
	private static final Log log = LogFactory.getLog(GenerateIdNo.class);
	private static final String TO_NEW_CUSTOMER="Customer>New Customer";
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private String costCenterNo;
	private String  generatedId;
	private String  idNo;
	private String idType;

	

	private String  state;
	private String  path;
	
	public String execute(){
		setLoggedData();
		return SUCCESS;
	}
	
	public String next(){
		setLoggedData();
		setPath(TO_NEW_CUSTOMER);
		setState("fromDummyId");
		setIdNo(generatedId);
		setIdType("NIC");
		return "next";
	}
	
	public String generate(){
		setLoggedData();
		ApplicantEjb ejb=new ApplicantEjb(getSessionKey("region")); 
		setGeneratedId(ejb.genDummyId(costCenterNo));
		return "generate";
	}
	
	public String close(){
		setLoggedData();
		return "close";
	}
	
	public void setLoggedData() {
        log.info(getSession());
		setLoggedInUserId(getSessionKey("userName"));
        setCostCenterNo(getSessionKey("costCenterNo"));
        
        
    }
	
	public String getSessionKey(String key) {
        return getSession().get(key).toString();
    }
	
	public Map<String, Object> getSession() {
		return session;

	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public Map <String, String[]> getParameters() {
		return parameters;
	}
	
	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters=parameters;
		
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
	public String getGeneratedId() {
		return generatedId;
	}

	public void setGeneratedId(String generatedId) {
		this.generatedId = generatedId;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	
	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}
}
