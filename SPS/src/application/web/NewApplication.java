package application.web;

//import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.SessionAware;

import util.common.Format;

import com.opensymphony.xwork2.ActionSupport;

import estimate.service.TariffCategoryEjb;
import estimate.service.TariffEjb;

@SuppressWarnings("serial")
public class NewApplication extends ActionSupport implements SessionAware{
	//Common Fields
	private static final Log log = LogFactory.getLog(NewApplication.class);
	private static final String newPath="Application>New Application";
	private Map<String, Object> session;
    private String loggedInUserId;
    private String state;
    private Format format;
    private String path;
    //JSP Fields
    private String date;
    private String costCenterNo;
    private String csc;
    private List<String> listTariffCode;
	private List<String> listTariffCatCode;


	private String preparedBy;
    
	public NewApplication() {
		super();
		setFormat(new  Format());
	}
	
 	public String execute(){
 		setState("newApplication");
 		setLoggedData();
 		setPath(newPath);
 		log.info(getState());
 		setDate(getFormat().FormatDate());
 		setPreparedBy(getLoggedInUserId());
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


	public String getCostCenterNo() {
		return costCenterNo;
	}

	public void setCostCenterNo(String costCenterNo) {
		this.costCenterNo = costCenterNo;
	}

	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
    
    public String getPreparedBy() {
		return preparedBy;
	}

	public void setPreparedBy(String preparedBy) {
		this.preparedBy = preparedBy;
	}
	
	public String getPath() {
		return path;
	}



	public void setPath(String path) {
		this.path = path;
	}
	
	public String getCsc() {
		return csc;
	}

	public void setCsc(String csc) {
		this.csc = csc;
	}



	public Map<String, Object> getSession() {
		return session;
	}

	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
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
	
	
	
   

}
