package applicationBS.web;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import util.common.Format;

import com.opensymphony.xwork2.ActionSupport;

import estimate.service.TariffCategoryEjb;
import estimate.service.TariffEjb;

@SuppressWarnings("serial")
public class ConfirmApplication extends ActionSupport implements SessionAware, ParameterAware {
	// Common Fields
	private static final Log log = LogFactory.getLog(ApplicationForm.class);
	private static final String confirmPath="Application>Generate App No";
	private Map<String, Object> session;
	private String loggedInUserId;
	private String costCenterNo;
	private String csc;
	private String path;
	// Persistence
	//private EntityManagerFactory emf;
	//private EntityManager em;

	private Format format;
	//JSP Fields
	private String state;
	private String approvedBy;
	private List<String> listTariffCode;
	private List<String> listTariffCatCode;
	private String smcType;	
	
	
	public ConfirmApplication() {
		super();
		//setEmf(Persistence.createEntityManagerFactory("SMC"));
		//setFormat(new Format());
	}
	
	public String execute(){
		setLoggedData();
		setState("confirmApp");
		setPath(confirmPath); 
		setListTariffCode(getTafiffCode());
		setListTariffCatCode(getTafiffCaTCode());
		return SUCCESS;
	}
	public void setLoggedData() {
        //setSession(ActionContext.getContext().getSession());
        //log.info(getSession());
        setLoggedInUserId(getSessionKey("userName"));
        setCostCenterNo(getSessionKey("costCenterNo"));
        setCsc(getSessionKey("costCenterName"));
        setSmcType(getSessionKey("smcType"));
        log.info(getSession());
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

 	//Getters and Setters
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
	
	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	
	/*public EntityManagerFactory getEmf() {
		return emf;
	}
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
	public EntityManager getEm() {
		return em;
	}
	public void setEm(EntityManager em) {
		this.em = em;
	}*/
	public Format getFormat() {
		return format;
	}
	public void setFormat(Format format) {
		this.format = format;
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

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setParameters(Map<String, String[]> arg0) {
		// TODO Auto-generated method stub
		
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
	
	public String getSmcType() {
		return smcType;
	}
	public void setSmcType(String smcType) {
		this.smcType = smcType;
	}

}
