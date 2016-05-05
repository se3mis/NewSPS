package pivBS.web;

import java.util.List;
import java.util.Map;

import masters.model.Bank;
import masters.model.BankBranch;
import masters.service.BankEjb;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import util.common.Format;
import util.common.PivPrefixType;

import com.opensymphony.xwork2.ActionSupport;

import costcenter.model.Gldeptin;
import costcenter.service.CostCenterEjb;

@SuppressWarnings("serial")
public class ConfirmPiv extends ActionSupport implements SessionAware, ParameterAware{
	//Common Fields
	private static final Log log = LogFactory.getLog(ConfirmPiv.class);
	private static final String confirmPath="Application>Confirm PIV";
	private Map<String, Object> session;
	private String loggedInUserId;
	private String path;
	//JSP Fields
	private String costCenterNo;
	private String cebBranch;
	private List<Bank> bankList; 
	private List<BankBranch> branchList; 
	private String payingBankCode;
    private String payingBranchCode;
    private String pivNo;
	private Format format;


	//Hidden Fields
    private String state;
    private String hiddenSecondState;
    
	
    public ConfirmPiv() {
		super();
		//setEmf(Persistence.createEntityManagerFactory("SMC"));
		//setFormat(new Format());
		// setDate(getFormat().FormatDate());

	} 
    
    public void setLoggedData() {
        log.info(getSession());
        setLoggedInUserId(getSessionKey("userName"));
        setCostCenterNo(getSessionKey("costCenterNo"));
        setCebBranch(getSessionKey("costCenterName"));
        CostCenterEjb ejb=new CostCenterEjb(getSessionKey("region"));
        Gldeptin gldeptin=ejb.findById(getCostCenterNo());
        setPayingBankCode(gldeptin.getBankCode());
        setPayingBranchCode(gldeptin.getBranchCode());
    }
 	
 	public String getSessionKey(String key) {
        return getSession().get(key).toString();
    }
   
	public String execute(){
		setFormat(new Format());
		setLoggedData();
		setPivNo(PivPrefixType.APP_NEW_CONN+"/"+getCostCenterNo()+"/"+getFormat().getYear()+"/xxxx");
		setBankList(getAllBankList());
		setState("confirm");
    	setPath(confirmPath);
    	return SUCCESS;
    }
	
	private List<Bank> getAllBankList(){
		   BankEjb ejb=new BankEjb(getSessionKey("region"));
		   return ejb.getAllBanks();
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

	public Map<String, Object> getSession() {
		return session;
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
	public String getCebBranch() {
		return cebBranch;
	}

	public void setCebBranch(String cebBranch) {
		this.cebBranch = cebBranch;
	}

	
	
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}

	@Override
	public void setParameters(Map<String, String[]> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public List<Bank> getBankList() {
		return bankList;
	}

	public void setBankList(List<Bank> bankList) {
		this.bankList = bankList;
	}

	public List<BankBranch> getBranchList() {
		return branchList;
	}

	public void setBranchList(List<BankBranch> branchList) {
		this.branchList = branchList;
	}
	

	public String getPayingBankCode() {
		return payingBankCode;
	}

	public void setPayingBankCode(String payingBankCode) {
		this.payingBankCode = payingBankCode;
	}

	public String getPayingBranchCode() {
		return payingBranchCode;
	}

	public void setPayingBranchCode(String payingBranchCode) {
		this.payingBranchCode = payingBranchCode;
	}


	public String getPivNo() {
		return pivNo;
	}

	public void setPivNo(String pivNo) {
		this.pivNo = pivNo;
	}
	

	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}


}
