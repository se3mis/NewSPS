package job.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import job.model.Spestbip;
import job.model.SpestbipPK;
import job.model.Spestcnd;
import job.model.SpestcndPK;
import job.model.Spestcnt;
import estimate.model.Spestlab;
import estimate.model.SpestlabPK;
import job.service.JobEjb;
import job.service.SpestbipEjb;
import job.service.SpestcndEjb;
import job.service.SpestcntEjb;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import util.common.Format;
import util.common.JobProcessStatus;
import com.opensymphony.xwork2.ActionSupport;


public class BillContractor extends ActionSupport implements SessionAware, ParameterAware{

	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;	
	private static final Log log = LogFactory.getLog(BillContractor.class);
	private static final String newPath="Manage Jobs>Bill Contractor";	
	private String path;
	private String loggedInUserId;
	private String costCenterNo;
	private String csc;
	private String contractor;	
	private String contractorID;
	private String estimatedBy;
	private Date billDate;
	private String billReferenceNo;
	private List<String> listContractor;
	private String jobNumber;
	private List<String> listjobNumber;
	private List<String> listContractorID;
	private List<Spestlab>  billDetails;
	private String consumerName;
	private String grandTotal;
	private String statusMsg;
	private String statusMsgErr;
	
	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public String getStatusMsgErr() {
		return statusMsgErr;
	}

	public void setStatusMsgErr(String statusMsgErr) {
		this.statusMsgErr = statusMsgErr;
	}


	public String getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(String grandTotal) {
		this.grandTotal = grandTotal;
	}

	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public List<Spestlab> getBillDetails() {
		return billDetails;
	}

	public void setBillDetails(List<Spestlab> billDetails) {
		this.billDetails = billDetails;
	}

	public String getBillReferenceNo() {
		return billReferenceNo;
	}

	public void setBillReferenceNo(String billReferenceNo) {
		this.billReferenceNo = billReferenceNo;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public String getEstimatedBy() {
		return estimatedBy;
	}

	public void setEstimatedBy(String estimatedBy) {
		this.estimatedBy = estimatedBy;
	}

	public String getContractorID() {
		return contractorID;
	}

	public void setContractorID(String contractorID) {
		this.contractorID = contractorID;
	}

	public List<String> getListContractorID() {
		return listContractorID;
	}

	public void setListContractorID(List<String> listContractorID) {
		this.listContractorID = listContractorID;
	}

	public List<String> getListContractor() {
		return listContractor;
	}

	public void setListContractor(List<String> listContractor) {
		this.listContractor = listContractor;
	}


	public List<String> getListjobNumber() {
		return listjobNumber;
	}

	public void setListjobNumber(List<String> listjobNumber) {
		this.listjobNumber = listjobNumber;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public List<ContractorBillDetails> getList() {
		return list;
	}

	public void setList(List<ContractorBillDetails> list) {
		this.list = list;
	}

	public ContractorBillList getContractBillList() {
		return contractBillList;
	}

	public void setContractBillList(ContractorBillList contractBillList) {
		this.contractBillList = contractBillList;
	}

	private List<ContractorBillDetails> list;
	private ContractorBillList contractBillList;
	

	public String getContractor() {
		return contractor;
	}

	public void setContractor(String contractor) {
		this.contractor = contractor;
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

	public Map<String, Object> getSession() {
		return session;	
	}
 	
 	public String getSessionKey(String key) {
        return getSession().get(key).toString();
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
	
	public String execute()	 
	{
		try
		{			
			setLoggedData();
			setPath(newPath);
			SpestcntEjb ejb=new SpestcntEjb(getSessionKey("region"));			
			listjobNumber = new ArrayList<String>();
			listContractor  = new ArrayList<String>();
			listContractorID = new ArrayList<String>(); 
			list = new ArrayList<ContractorBillDetails>();
			List<Spestcnd> spestcndList=new ArrayList<Spestcnd>();
			String costCentre = getSessionKey("costCenterNo");			
			List<Spestcnt> spestcntList=new ArrayList<Spestcnt>();
			SpestcndEjb spejb=new SpestcndEjb(getSessionKey("region"));		
			spestcntList=ejb.getAll(costCentre);
			Iterator<Spestcnt> it1 = spestcntList.iterator();									
				
			while (it1.hasNext()) {  
				Spestcnt spestcnt1=it1.next();				
				String scontractorID = spestcnt1.getId().getContractorId();
				listContractor.add(spestcnt1.getContractorName());	
				listContractorID.add(scontractorID);	
				
			  } 	
			
			 if(contractor!=null && !contractor.equals("-1") &&  contractorID!=null && !contractorID.equals("-1") )
			 {			
				spestcndList=spejb.getJobList(contractorID,costCentre,JobProcessStatus.FINISHED);
				Iterator<Spestcnd> it2 = spestcndList.iterator();
				while (it2.hasNext()) {  
					 Spestcnd spestcnd=it2.next();
					 String projectNo = spestcnd.getId().getProjectNo();
					 listjobNumber.add(projectNo);
					 if(jobNumber!=null && !jobNumber.equals("-1") && jobNumber.trim().equals(projectNo.trim()))
					 {
						 consumerName = spestcnd.getConsumerName();
					 }
					 
				} 
			 }else {
				 resetPage();
			 }			 
			 
			 if(jobNumber!=null && !jobNumber.equals("-1")){
				 	estimatedBy = getSessionKey("userName");	
					billDate =new Date();
					setBillDetails(costCentre.trim(),jobNumber.trim());
			 }
			 else {
				
				 resetPage();
			 }
			 	
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("The error is........."+ex);
			
		}
		return "success";
	}
	
	private void resetPage(){
		 grandTotal="";
		 estimatedBy = "";	
		 consumerName = "";	
		 billReferenceNo="";
		 statusMsg="";
		 statusMsgErr="";
		 billDate=null;
	}
	
 	public void setLoggedData() {
        
        log.info(getSession());
        setLoggedInUserId(getSessionKey("userName"));
        setCostCenterNo(getSessionKey("costCenterNo"));
        setCsc(getSessionKey("costCenterName"));
    }
 	
	public String close()
	{
		setLoggedData();
		return "closed";
	}
	
	private void setBillDetails(String deptId , String jobNumber)
	{
			JobEjb jobEjb=new JobEjb(getSessionKey("region"));
			billDetails = new  ArrayList<Spestlab>();
			billDetails = jobEjb.getLaborDetails(jobNumber,deptId);
			double total = 0;
			
			Iterator<Spestlab> it2 = billDetails.iterator();
			while (it2.hasNext()) {  
				 Spestlab laborItem=it2.next();
				 double laborCharge;
				if(laborItem.getLabourCost()!=null)
					  laborCharge = laborItem.getLabourCost().doubleValue();
				else
					  laborCharge =0;
				
				total= total + laborCharge;
				 
			} 
			
			grandTotal = String.valueOf(total);
			
			 if (this.billDetails.size() == 0)
					this.billDetails = null;
	}
	
	public String bill()
	{
		setLoggedData();
		
		try{
			if(!jobNumber.trim().equals("")){
				SpestcndEjb ejb =new SpestcndEjb(getSessionKey("region"));
				String costCentre = getSessionKey("costCenterNo");
				SpestcndPK spestcndPK=new SpestcndPK();
				spestcndPK.setContractorId(contractorID);
				spestcndPK.setDeptId(costCentre.trim());
				spestcndPK.setProjectNo(jobNumber.trim());
				Spestcnd spestcnd = ejb.findById(spestcndPK);
				
				if(spestcnd!=null ){	
						spestcnd.setStatus(JobProcessStatus.BILLED);					
														}
				JobEjb jobEjb=new JobEjb(getSessionKey("region"));
				SpestbipPK spestbipPK = new SpestbipPK();
				
				spestbipPK.setBillSettlementNo(billReferenceNo.trim());
				spestbipPK.setDeptId(costCentre);
				spestbipPK.setProjectNo(jobNumber.trim());
				
				Spestbip spestbip=new Spestbip();
				spestbip.setId(spestbipPK);
				spestbip.setBillDate(billDate);
				if(grandTotal!=null && grandTotal!="")
					spestbip.setTotalBillAmt(new BigDecimal(grandTotal));
							
				jobEjb.billContractor(spestcnd, spestbip);
				resetPage();
				statusMsg = "The Job number :"+jobNumber+" was billed successfully";
				jobNumber = "";
				contractorID = "";
				contractor = "";
				
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			statusMsgErr = "The Job number :"+jobNumber+" was not billed successfully";
			
		}
		
		execute();
		return "success";
	}

}
