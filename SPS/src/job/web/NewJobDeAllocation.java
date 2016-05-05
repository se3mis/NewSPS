package job.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import job.model.Spestcnd;
import job.model.SpestcndPK;
import job.model.Spestcnt;
import job.model.SpestcntPK;
import job.service.JobEjb;
import job.service.SpestcndEjb;
import job.service.SpestcntEjb;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import util.common.EstimateStatus;
import util.common.Format;
import util.common.JobProcessStatus;

import com.opensymphony.xwork2.ActionSupport;
public class NewJobDeAllocation extends ActionSupport implements SessionAware, ParameterAware
{
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
private Map<String, Object> session;
private Map <String, String[]> parameters;

private List<String> listContractor;
private List<String> listContractorID;

private List<AllocateDetails> list;
private AllocateJobList allocateJobList;

private static final Log log = LogFactory.getLog(NewJobDeAllocation.class);
private static final String newPath="Manage Jobs>Job DeAllocation";
private String path;
private String loggedInUserId;
private String costCenterNo;
private String csc;
private String statusMsgErr;
private String errorMessage;
private String jobNo;
private String consumerName;
private String jobAmount;
private String jobAllocate;
private String contractDetails;
private String contractorID;
private String contractor;
private String jobAllocationDate;
private String jobInHand;
private String totalAmount;










public String execute(){
	try{
		setLoggedData();
	 	setPath(newPath);
		allocateJobList = new AllocateJobList();
		list = new ArrayList<AllocateDetails>(); 
		SpestcndEjb ejb=new SpestcndEjb(getSessionKey("region"));	
		SpestcntEjb ejbst=new SpestcntEjb(getSessionKey("region"));
		listContractor= new  ArrayList<String>();
		listContractorID = new  ArrayList<String>();
		List<Spestcnd> spestcndAllocatedList=new ArrayList<Spestcnd>();	
		List<Spestcnd> spestcndList=new ArrayList<Spestcnd>();	
		String costCenter = getSessionKey("costCenterNo");
		spestcndAllocatedList=ejb.getAll(costCenter);
		
		Iterator<Spestcnd> it1 = spestcndAllocatedList.iterator();
		Map<String, Spestcnd> alloConMap = new HashMap<String, Spestcnd>();
		 while (it1.hasNext()) {  
			 Spestcnd spestcnd1=it1.next();
			 alloConMap.put(spestcnd1.getId().getContractorId(), spestcnd1);
			       	        	
	       }
		 List<String> list1 = new ArrayList<String>(alloConMap.keySet());
		 for(String conId : list1){
			 if(ejbst.findByContractorId(conId,costCenter) != null){
				 listContractor.add(ejbst.findByContractorId(conId,costCenter).getContractorName());
				 listContractorID.add(conId);	
			 }	 
		 }
		 
		 if(contractor!=null && !contractor.equals("-1") &&  contractorID!=null && !contractorID.equals("-1") )
		 {
			 	SpestcndEjb spejb=new SpestcndEjb(getSessionKey("region"));
				spestcndList=spejb.getJobList(contractorID,costCenter,JobProcessStatus.ALLOCATED);
				Iterator<Spestcnd> it2 = spestcndList.iterator();
				Format format = new Format(); 					
				jobAllocationDate = format.FormatDate("dd/MM/yyyy");
								
				 while (it2.hasNext()) {  
					 Spestcnd spestcnd=it2.next();					 
					 Double amount = 0.0;									 
					 if(spestcnd.getAmount()!=null)
						 amount = spestcnd.getAmount().doubleValue();				 
									 
					 AllocateDetails record=new AllocateDetails(spestcnd.getId().getProjectNo(), spestcnd.getConsumerName(),amount);
					 allocateJobList.addList(list,record);    
					 			        	        	
			          }   		
				 	
				 	SpestcntPK spestcntpk = new SpestcntPK();
				 	spestcntpk.setContractorId(contractorID);
				 	spestcntpk.setDeptId(costCenter);
				 
				 	Spestcnt spestcnt=ejbst.findById(spestcntpk);
					if(spestcnt!=null){
						jobInHand =String.valueOf(spestcnt.getJobInHand());
						totalAmount= String.valueOf(spestcnt.getTotalAmount());
					}
				}
		
		return "success";
		}
	catch(Exception ex){
		System.out.println("exception is:"+ ex);
			return "error";
		}	
	}

public String close(){
	setLoggedData();
	return "close";
}

public String Save(){
		
	try{
		if(refList!=null){
			String[] jobList=refList.split(":");
			JobEjb jobEjb =new JobEjb(getSessionKey("region"));
			SpestcntEjb ejbCnt=new SpestcntEjb(getSessionKey("region"));
			String costCenter = getSessionKey("costCenterNo");
			
			SpestcntPK spestcntpk = new SpestcntPK();
			spestcntpk.setContractorId(contractorID);
			spestcntpk.setDeptId(costCenter);
			
			BigDecimal jobInHand = null;
			BigDecimal totalAmount = null;
			
			int jobCount = 0;
			Double totAmount = (double) 0;			 
			Spestcnt spestcnt=ejbCnt.findById(spestcntpk);
			JobEjb jobejb=new JobEjb(getSessionKey("region"));
			List<SpestcndPK> spsetCndRemovePKList =new ArrayList<SpestcndPK>();
			if(spestcnt!=null){
				jobInHand = spestcnt.getJobInHand();
				totalAmount = spestcnt.getTotalAmount();
				jobCount = jobInHand.intValue();
				totAmount = totalAmount.doubleValue();
			}
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<jobList.length;i++){
				String[] valueList=jobList[i].split(",");
				
				if(valueList.length>1)
				{		
					String jobID=valueList[0].trim();
					
					boolean isMatIssued= jobejb.isMaterialIssued(jobID, costCenter);
					if(!isMatIssued){					
							jobCount = jobCount-1;
							totAmount = totAmount -Double.valueOf(valueList[1].trim());
							SpestcndPK spestcndPK = new SpestcndPK();
							spestcndPK.setContractorId(contractorID.trim());
							spestcndPK.setDeptId(costCenter.trim());
							spestcndPK.setProjectNo(jobID);
							spsetCndRemovePKList.add(spestcndPK);	
							
					}else
					{						
							sb.append(jobID+",");
					}										
				}
			}		
			
			if(spestcnt!=null){	
				spestcnt.setJobInHand(new BigDecimal(jobCount));
				spestcnt.setTotalAmount(new BigDecimal(totAmount));
				
			}
			jobEjb.deAloocateJob(spsetCndRemovePKList, spestcnt);
			String jobnolist = sb.toString();
			if(jobnolist!=null && jobnolist!="" && jobnolist.length()>0)
				statusMsgErr = "Job numbers :" +jobnolist.substring(0, jobnolist.length()-1)+" Could not be allocated as materials are issued";
		}
	
	}catch(Exception ex){
		ex.printStackTrace();
		return "error";
	}
	
	execute();
	setLoggedData();	
	return "success";
}

public String Finish(){
	try{
		if(refList!=null){
			String[] jobList=refList.split(":");
			SpestcndEjb ejbCnd =new SpestcndEjb(getSessionKey("region"));
			SpestcntEjb ejbCnt=new SpestcntEjb(getSessionKey("region"));
			String costCenter = getSessionKey("costCenterNo");
			
			SpestcntPK spestcntpk = new SpestcntPK();
			spestcntpk.setContractorId(contractorID);
			spestcntpk.setDeptId(costCenter);
			
			BigDecimal jobInHand = null;
			BigDecimal totalAmount = null;
			
			int jobCount = 0;
			Double totAmount = (double) 0;			 
			Spestcnt spestcnt=ejbCnt.findById(spestcntpk);
			
			if(spestcnt!=null){
				jobInHand = spestcnt.getJobInHand();
				totalAmount = spestcnt.getTotalAmount();
				jobCount = jobInHand.intValue();
				totAmount = totalAmount.doubleValue();
			}
			 
			for(int i=0;i<jobList.length;i++){
				String[] valueList=jobList[i].split(",");
				
				if(valueList.length>1)
				{		
					String jobID=valueList[0].trim();
					List<Spestcnd> spestcndList= ejbCnd.findByJobNo(jobID,costCenter);
					if(spestcndList!=null && spestcndList.size()>0)					{
						
						Spestcnd spestcnd = spestcndList.get(0);
							if(spestcnd!=null)
							{	
									jobCount = jobCount-1;
									totAmount = totAmount -Double.valueOf(valueList[1].trim());
									
									Date dt= new Date();
									spestcnd.setAllocatedDate(dt);
									spestcnd.setAllocatedTime(dt.toString());
									spestcnd.setStatus(JobProcessStatus.FINISHED);
									spestcnd.setSealNo("");
									ejbCnd.updateSpestcnd(spestcnd);
									
									
							}
					}					
				}
			}
			
			if(spestcnt!=null){	
				spestcnt.setJobInHand(new BigDecimal(jobCount));
				spestcnt.setTotalAmount(new BigDecimal(totAmount));
				ejbCnt.updateSpestcnt(spestcnt);
			}
		}
	}catch(Exception ex){
		return "error";
	}
	
	execute();
	setLoggedData();	
	return "success";
}

public String getSessionKey(String key) {
    return getSession().get(key).toString();
}

	public void setLoggedData() {
   
    log.info(getSession());
    setLoggedInUserId(getSessionKey("userName"));
    setCostCenterNo(getSessionKey("costCenterNo"));
    setCsc(getSessionKey("costCenterName"));
   
}
	
	public String getStatusMsgErr() {
		return statusMsgErr;
	}


	public void setStatusMsgErr(String statusMsgErr) {
		this.statusMsgErr = statusMsgErr;
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

	private String refList;

	public String getRefList() {
		return refList;
	}


	public void setRefList(String refList) {
		this.refList = refList;
	}


	public List<AllocateDetails> getList() {
		return list;
	}


	public void setList(List<AllocateDetails> list) {
		this.list = list;
	}


	public AllocateJobList getAllocateJobList() {
		return allocateJobList;
	}


	public void setAllocateJobList(AllocateJobList allocateJobList) {
		this.allocateJobList = allocateJobList;
	}
	
	public String getJobNo() {
		return jobNo;
	}


	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}


	public String getConsumerName() {
		return consumerName;
	}


	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}


	public String getJobAmount() {
		return jobAmount;
	}


	public void setJobAmount(String jobAmount) {
		this.jobAmount = jobAmount;
	}


	public String getJobAllocate() {
		return jobAllocate;
	}


	public void setJobAllocate(String jobAllocate) {
		this.jobAllocate = jobAllocate;
	}


	public String getContractDetails() {
		return contractDetails;
	}


	public void setContractDetails(String contractDetails) {
		this.contractDetails = contractDetails;
	}
	
	public String getContractorID() {
		return contractorID;
	}


	public void setContractorID(String contractorID) {
		this.contractorID = contractorID;
	}


	public String getContractor() {
		return contractor;
	}


	public void setContractor(String contractor) {
		this.contractor = contractor;
	}


	public String getJobAllocationDate() {
		return jobAllocationDate;
	}


	public void setJobAllocationDate(String jobAllocationDate) {
		this.jobAllocationDate = jobAllocationDate;
	}


	public String getJobInHand() {
		return jobInHand;
	}


	public void setJobInHand(String jobInHand) {
		this.jobInHand = jobInHand;
	}


	public String getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}


	public List<String> getListContractor() {
		return listContractor;
	}


	public void setListContractor(List<String> listContractor) {
		this.listContractor = listContractor;
	}


	public List<String> getListContractorID() {
		return listContractorID;
	}


	public void setListContractorID(List<String> listContractorID) {
		this.listContractorID = listContractorID;
	}


	public Map<String, Object> getSession() {
		return session;	
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
	


	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


}
