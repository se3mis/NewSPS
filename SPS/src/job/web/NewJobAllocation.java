package job.web;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import job.model.JobInfo;
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
import org.jboss.metadata.process.processor.ejb.jboss.SetExplicitLocalJndiNameProcessor;

import util.common.EstimateStatus;
import util.common.Format;
import util.common.JobProcessStatus;
import com.opensymphony.xwork2.ActionSupport;

import estimate.model.Spsetpol;
import estimate.service.EstimateEjb;



public class NewJobAllocation extends ActionSupport implements SessionAware, ParameterAware
{
	private static final String MSG_ERROR="ERROR";
	private static final String MSG_INFO="INFO";
	private static final String MSG_DONE="DONE";
	private static final String MSG_NONE="NONE";
	
	
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;	
	private List<String> listContractor;
	private List<String> listContractorID;
	private static final Log log = LogFactory.getLog(NewJobAllocation.class);
	private static final String newPath="Manage Jobs>Job Allocation";
	private String path;
	private String loggedInUserId;
	private String loggedInUserLevel;
	private String costCenterNo;
	private String csc;	
	private String errorMessage;
	private String messageType=MSG_NONE;
	private String applicationNo;
	private String contractor;
	private String jobAllocationDate;
	private String jobInHand;
	private String totalAmount;
	private String refList;
	private String jobNo;
	private String consumerName;
	private String jobAmount;
	private String jobAllocate;
	private String contractDetails;
	private String contractorID;
	private List<AllocateDetails> list;
	private AllocateJobList allocateJobList;	
	
		
		public String execute(){
			try{
				setLoggedData();
			 	setPath(newPath);
				allocateJobList = new AllocateJobList();
				list = new ArrayList<AllocateDetails>(); 
				SpestcntEjb ejb=new SpestcntEjb(getSessionKey("region"));	
				listContractor= new  ArrayList<String>();
				listContractorID = new  ArrayList<String>();
				List<Spestcnt> spestcntList=new ArrayList<Spestcnt>();	
				List<JobInfo> jobInfoList=new ArrayList<JobInfo>();	
				
				String costCenter = getSessionKey("costCenterNo");
				spestcntList=ejb.getContractorByStatus(costCenter, "1");
				Iterator<Spestcnt> it1 = spestcntList.iterator();
				
				 while (it1.hasNext()) {  
					 Spestcnt spestcnt1=it1.next();
					 listContractor.add(spestcnt1.getContractorName());	
					 listContractorID.add(spestcnt1.getId().getContractorId());	
					 				        	        	
			       }   				
				if(contractor!=null && !contractor.equals("-1") &&  contractorID!=null && !contractorID.equals("-1") ){					
					
					try{	
						JobEjb jejb=new JobEjb(getSessionKey("region"));
						jobInfoList =  jejb.getJobInformations(costCenterNo, new Long(EstimateStatus.JOB_ONGOING));						
						Iterator<JobInfo> it2 = jobInfoList.iterator();						
						Format format = new Format(); 					
						jobAllocationDate = format.FormatDate("dd/MM/yyyy");													
						 while (it2.hasNext())
						 {  
							 JobInfo jobInfo=it2.next();							 					
							 Double jobAmt = 0.0;
							 if(jobInfo.getAmount()!=null){
								  jobAmt = jobInfo.getAmount().doubleValue();
								
							 }													 				 
								 
							 AllocateDetails record=new AllocateDetails(jobInfo.getProjectNo(), jobInfo.getFullName(),jobAmt);
							 allocateJobList.addList(list,record);   
												        	        	
					     }   
						 SpestcntPK spestcntpk = new SpestcntPK();
						 spestcntpk.setContractorId(contractorID);
						 spestcntpk.setDeptId(costCenter);
						 
						 Spestcnt spestcnt=ejb.findById(spestcntpk);
							if(spestcnt!=null){
								jobInHand =String.valueOf(spestcnt.getJobInHand());
								totalAmount= String.valueOf(spestcnt.getTotalAmount());
							}
													
					}catch(Exception ex){
						
						return "error";
					}						 				 
				}else{
					
					 jobInHand ="";
					 totalAmount= "";
					 jobAllocationDate="";
					 list =null;
				}
				
			}
			catch(Exception ex)
			{
				return "error";
			}
			
			return "success";
		}
		
		public String close() {
			setLoggedData();
			return "close";
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
		
		public String Close(){	
			setLoggedData();
			return "close";
		}
		
		public String forword(){
			try{
				//System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEWWWWWWWWWWWWWWWWWWWWW  "+refList);
				setLoggedData();
				String newJobNo=null;
				if(refList!=null){				
					String[] jobList=refList.split(":");
									
						String[] valueList=jobList[0].split(";");						
						if(valueList.length>2){	
							//System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEWWWWWWWWWWWWWWWWWWWWW  "+valueList[0].trim());
							EstimateEjb estimateEjb=new EstimateEjb(getSessionKey("region"));
							String costCenter = getSessionKey("costCenterNo");
							String postfix="00";
							if (costCenter.substring(4, 6).equals("10")){
								postfix="20";
								
							}else{
								postfix="10";
							}
							System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEWWWWWWWWWWWWWWWWWWWWW  "+costCenter.substring(4, 6)+" "+costCenter.substring(0, 4)+postfix);
							newJobNo=estimateEjb.changeCostCenter(valueList[0].trim(), costCenter, costCenter.substring(0, 4)+postfix);
							setLoggedData();
							execute();
							setMessageType(MSG_DONE);
							setErrorMessage("The job forworded to "+costCenter.substring(0, 4)+postfix+" and the new job No is "+newJobNo);
							return SUCCESS;
						}else{
							setLoggedData();
							execute();
							setMessageType(MSG_ERROR);
							setErrorMessage("Error Occur");
							return ERROR;
						}
						
					
					
				}else{
					setLoggedData();
					execute();
					setMessageType(MSG_ERROR);
					setErrorMessage("Error Occur");
					return ERROR;
					
				}
			}catch (Exception e) {
				e.printStackTrace();
				setLoggedData();
				execute();
				setMessageType(MSG_ERROR);
				setErrorMessage("Error Occur");
				return ERROR;
			}
				
		}
		
		public String Save(){
			try{
				if(refList!=null){				
					String[] jobList=refList.split(":");
					JobEjb jobEjb =new JobEjb(getSessionKey("region"));
					SpestcntEjb ejbCnt=new SpestcntEjb(getSessionKey("region"));	
					String costCenter = getSessionKey("costCenterNo");					
							
					List<Spestcnd> spsetCndList =new ArrayList<Spestcnd>();
					BigDecimal jobInHand = null;
					BigDecimal totalAmount = null;
					
					SpestcntPK spestcntpk = new SpestcntPK();
					spestcntpk.setContractorId(contractorID);
					spestcntpk.setDeptId(costCenter);
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
						String[] valueList=jobList[i].split(";");						
						
						if(valueList.length>2){								
							Spestcnd spestcnd = new Spestcnd();
							SpestcndPK spestcndPK = new SpestcndPK();
														
							spestcndPK.setContractorId(contractorID);
							spestcndPK.setDeptId(costCenter);
							spestcndPK.setProjectNo(valueList[0].trim());
							spestcnd.setId(spestcndPK);
							spestcnd.setAmount(new BigDecimal(valueList[2].trim()));
							spestcnd.setConsumerName(valueList[1].trim());	
							Date dt= new Date();
							Format format=new Format();
							spestcnd.setAllocatedDate(dt);
							spestcnd.setAllocatedTime(format.FormatDate());
							spestcnd.setAllocatedUser(getSessionKey("userName"));
							spestcnd.setStatus(JobProcessStatus.ALLOCATED);						
							spsetCndList.add(spestcnd);
														
							if(spestcnt!=null){
								jobCount = jobCount+1;
								totAmount = totAmount +Double.valueOf(valueList[2].trim());
															
							}				
						}	
					}
					
					if(spestcnt!=null){	
						spestcnt.setJobInHand(new BigDecimal(jobCount));
						spestcnt.setTotalAmount(new BigDecimal(totAmount));
												
					}
					
					jobEjb.alocateJob(spsetCndList,spestcnt);
				}
			}catch(Exception ex){
				System.out.println("The error is..... :"+ex);
				return "error";
			}
			setLoggedData();
			execute();
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
	        setLoggedInUserLevel(getSessionKey("userRole"));
	       
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
		public List<String> getListContractorID() {
			return listContractorID;
		}
		public void setListContractorID(List<String> listContractorID) {
			this.listContractorID = listContractorID;
		}
		

		public String getRefList() {
			return refList;
		}
		public void setRefList(String refList) {
			this.refList = refList;
		}
		
		public String getErrorMessage() {
			return errorMessage;
		}
		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}
		
		public String getContractorID() {
			return contractorID;
		}
		public void setContractorID(String contractorID) {
			this.contractorID = contractorID;
		}
		public String getContractDetails() {
			return contractDetails;
		}
		public void setContractDetails(String contractDetails) {
			this.contractDetails = contractDetails;
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
			public List<AllocateDetails> getList() {
				return list;
			}
			public void setList(List<AllocateDetails> list) {
				this.list = list;
			}
			public void setConsumerName(String consumerName) {
				this.consumerName = consumerName;
			}
			
				public String getApplicationNo() {
				return applicationNo;
			}
			public void setApplicationNo(String applicationNo) {
				this.applicationNo = applicationNo;
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
			
			public Map<String, Object> getSession() {
					return session;	
			}
			
			public String getMessageType() {
				return messageType;
			}


			public void setMessageType(String messageType) {
				this.messageType = messageType;
			}
			public String getLoggedInUserLevel() {
				return loggedInUserLevel;
			}


			public void setLoggedInUserLevel(String loggedInUserLevel) {
				this.loggedInUserLevel = loggedInUserLevel;
			}

}
