package applicationBS.web;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.SessionAware;


import com.opensymphony.xwork2.ActionSupport;

import estimate.model.Spugcstm;
import estimate.service.TariffCategoryEjb;
import estimate.service.TariffEjb;

@SuppressWarnings("serial")
public class AllocateApplication extends ActionSupport implements SessionAware {
	// Common Fields
	private static final Log log = LogFactory.getLog(ApplicationForm.class);
	private static final String allocatePath="Application>Allocate Application";
	private Map<String, Object> session;
	//JSP
	private String loggedInUserId;
	private String path;
	private String costCenterNo;
	private String csc;
	

	private List<String> listTariffCode;
	private List<String> listTariffCatCode;
	// Hidden Fields
	private String state;
	private String hiddenSecondState;
	private String hiddenPath;
	
	


	public String execute(){
		setState("allocateApplication");
		setLoggedData();
		setPath(allocatePath);
		
		setHiddenPath(getPath());//to get back
		setListTariffCode(getTafiffCode());
		setListTariffCatCode(getTafiffCaTCode());
		return SUCCESS;
	}
	public void setLoggedData() {
        //setSession(ActionContext.getContext().getSession());
        log.info(getSession());
        setLoggedInUserId(getSessionKey("userName"));
        setCostCenterNo(getSessionKey("costCenterNo"));
        setCsc(getSessionKey("costCenterName"));
        //log.info(getSession());
    }
 	
 	public String getSessionKey(String key) {
        return getSession().get(key).toString();
    }
	
 	private List<String> getTafiffCaTCode(){
		TariffCategoryEjb ejb=new TariffCategoryEjb(getSessionKey("region"));
		List<String> list=ejb.getTariffCategoryList();
		return list;
		
	}
	
	private List<String> getTafiffCode(){
		TariffEjb ejb=new TariffEjb(getSessionKey("region"));
		List<String> list=ejb.getTariffCodeList();
		return list;
		
	}

	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}
	
	public Map<String, Object> getSession() {
		return session;
	}
	public String getCostCenterNo() {
		return costCenterNo;
	}
	public void setCostCenterNo(String costCenterNo) {
		this.costCenterNo = costCenterNo;
	}
	
	public String getLoggedInUserId() {
		return loggedInUserId;
	}

	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getHiddenSecondState() {
		return hiddenSecondState;
	}
	public void setHiddenSecondState(String hiddenSecondState) {
		this.hiddenSecondState = hiddenSecondState;
	}
	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}
	public String getHiddenPath() {
		return hiddenPath;
	}


	public void setHiddenPath(String hiddenPath) {
		this.hiddenPath = hiddenPath;
	}
	
	public List<String> getListTariffCode() {
		return listTariffCode;
	}

	public void setListTariffCode(List<String> listTariffCode) {
		this.listTariffCode = listTariffCode;
	}

	public List<String> getListTariffCatCode() {
		return listTariffCatCode;
	}

	public void setListTariffCatCode(List<String> listTariffCatCode) {
		this.listTariffCatCode = listTariffCatCode;
	}
	public String getCsc() {
		return csc;
	}
	public void setCsc(String csc) {
		this.csc = csc;
	}

	

}
