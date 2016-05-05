package job.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import offDoc.web.JobTransferredReport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import util.common.EstimateStatus;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Iterator;
import job.model.Pcesthmt;
import job.service.JobEjb;
import job.service.PcesthmtEjb;

public class JobCloseGroup extends ActionSupport implements SessionAware, ParameterAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private static final Log log = LogFactory.getLog(JobCloseGroup.class);
	private static final String newPath="Manage Jobs>Job Closer - Group";

	private String path;
	private String loggedInUserId;
	private String costCenterNo;
	private String csc;
	private Date appointmentDate = new Date();;
	private String costCenter;
	private String statusMsg;
	private String statusMsgNotClosed;
	private String statusMsgErr;
	
	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public String getStatusMsgNotClosed() {
		return statusMsgNotClosed;
	}

	public void setStatusMsgNotClosed(String statusMsgNotClosed) {
		this.statusMsgNotClosed = statusMsgNotClosed;
	}

	public String getStatusMsgErr() {
		return statusMsgErr;
	}

	public void setStatusMsgErr(String statusMsgErr) {
		this.statusMsgErr = statusMsgErr;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
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

	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}
	
	public Map<String, String[]> getParameters() {
		return parameters;
	}
		
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters=parameters;
		
	}	
	
	private List<JobDetails> list;
	private JobCloseList closeJobList;
	private List<String> listreferenceNo;
	private String closingDate;
	
	public String getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(String closingDate) {
		this.closingDate = closingDate;
	}

	private String selectedNo;

	public String getSelectedNo() {
		return selectedNo;
	}

	public void setSelectedNo(String selectedNo) {
		this.selectedNo = selectedNo;
	}

	public String[] getEstimationNumberList() {
		return estimationNumberList;
	}

	public void setEstimationNumberList(String[] estimationNumberList) {
		this.estimationNumberList = estimationNumberList;
	}

	private String[] estimationNumberList;
	
	public List<String> getListreferenceNo() {
		return listreferenceNo;
	}

	public void setListreferenceNo(List<String> listreferenceNo) {
		this.listreferenceNo = listreferenceNo;
	}

	public JobCloseList getCloseJobList() {
		return closeJobList;
	}

	public void setCloseJobList(JobCloseList closeJobList) {
		this.closeJobList = closeJobList;
	}

	public List<JobDetails> getList() {
		return list;
	}

	public void setList(List<JobDetails> list) {
		this.list = list;
	}

	public String execute()	 
	{
		try{
			setLoggedData();
		 	setPath(newPath);
			
			closeJobList= new JobCloseList();
			listreferenceNo = new ArrayList<String>();		
	    	list = new ArrayList<JobDetails>(); 
	    	
	    	PcesthmtEjb ejb=new PcesthmtEjb(getSessionKey("region"));
	    	costCenter = getSessionKey("costCenterNo");
	    	
			
			if(costCenter!=null && !costCenter.equals("-1")){
				//List<String> resultList= ejb.getJobNoList(costCenter, new Long(EstimateStatus.JOB_SOFT_CLOSED));
				List<String> resultList= ejb.getJobNoList(costCenter, new Long(EstimateStatus.JOB_ONGOING));
		    	
				Iterator<String> it = resultList.iterator();		    	
		    	 while (it.hasNext()) {   
		    		JobDetails record=new JobDetails(it.next().toString());
		    		closeJobList.addList(list,record);          	
		         	listreferenceNo.add(record.getJobNo()); 
		           } 
		    	 appointmentDate = new Date();
			}		
	    	
		}catch(Exception ex){
			return "error";
		}
	    	
			return "success";
	}
	
	public void setLoggedData() {
	       
        log.info(getSession());
        setLoggedInUserId(getSessionKey("userName"));
        setCostCenterNo(getSessionKey("costCenterNo"));
        setCsc(getSessionKey("costCenterName"));
       
    }
	
	public String getSessionKey(String key) {
        return getSession().get(key).toString();
    }
	
	
	public String jobclose()	 
	{		
		try{
			String estimateNoArray[]=selectedNo.split(","); 
			String costCentre = getSessionKey("costCenterNo");
			JobEjb jobEjb=new JobEjb(getSessionKey("region"));
			PcesthmtEjb pcesejb=new PcesthmtEjb(getSessionKey("region"));
			List<String> estimateList= new ArrayList<String>();
			for(int i=0;i<estimateNoArray.length;i++){			
				if(estimateNoArray[i].trim()!=null && estimateNoArray[i]!=""){	
					String estNo = estimateNoArray[i].trim();
					estimateList.add(estNo);					
				}	
			}
			
			List<String> outPutList = jobEjb.jobCloser(estimateList,costCentre, appointmentDate);			
			
			String closedList="";
			String notClosedList="";
			String errorList ="";
			
			List<Pcesthmt> closedJobList= new ArrayList<Pcesthmt>();			
			
			if(outPutList.size()>2){
				 closedList = outPutList.get(0);
				 notClosedList = outPutList.get(1);
				 errorList = outPutList.get(2);
			}
			
			statusMsg = "" ;
			statusMsgNotClosed = "";
			statusMsgErr="";
			
			if(closedList!=null && closedList.length()>0){
				String closedListArray[]=closedList.split("#");
				StringBuilder clList = new StringBuilder();
				for(int i=0;i<closedListArray.length;i++){
					
					if(closedListArray[i]!=null && closedListArray[i].length()>0 ){
						
						if(closedListArray[i].length()>18){
							String jobNum = closedListArray[i].substring(13,19);
							Pcesthmt pchtmt= pcesejb.findByJobNo(jobNum,costCentre);
							if(pchtmt!=null)
								closedJobList.add(pchtmt);
						}
					}						
					
					if(i==closedListArray.length-1 && closedListArray[i]!=null && closedListArray[i].length()>0 )
						clList.append(closedListArray[i]);
					else if(closedListArray[i]!=null && closedListArray[i].length()>0)
						clList.append(closedListArray[i]+",");						
				}				
				statusMsg = "The Job numbers:"+clList.toString()+" closed successfully";
				
			}
			
			System.out.println("Gayani  The unsuccess list is...................:"+notClosedList); 
			
			if(notClosedList!=null && notClosedList.length()>0){
				String notClosedListArray[]=notClosedList.split("$");
				StringBuilder ntclList = new StringBuilder();
				for(int i=0;i<notClosedListArray.length;i++){					
						
					if(i==notClosedListArray.length-1 && notClosedListArray[i]!=null && notClosedListArray[i].length()>0)
						ntclList.append(notClosedListArray[i]);
					else if(notClosedListArray[i]!=null && notClosedListArray[i].length()>0)
						ntclList.append(notClosedListArray[i]+",");						
				}				
				statusMsgNotClosed = "The Job numbers:"+ntclList.toString()+" was not closed properly";
								
			}
			
			if(errorList!=null && errorList.length()>0){				

				String errorListArray[]=errorList.split("@");
				StringBuilder errlList = new StringBuilder();
				for(int i=0;i<errorListArray.length;i++){	
							
					if(i==errorListArray.length-1 && errorListArray[i]!=null && errorListArray[i].length()>0)
						errlList.append(errorListArray[i]);
					else if(errorListArray[i]!=null && errorListArray[i].length()>0)
						errlList.append(errorListArray[i]+",");						
				}					
				statusMsgErr = errlList.toString()+ "can not be closed";
			}
			
			if(closedJobList!=null && !closedJobList.isEmpty() && closedJobList.size()>0){
				JobTransferredReport jtr = new JobTransferredReport(closedJobList,new Date(),costCentre,getSessionKey("userName"));
				jtr.Print(true);
			}		
			
			System.out.println("The success list is...................:"+closedList);
			System.out.println("The unsuccess list is...................:"+notClosedList);
			System.out.println("The error list is...................:"+errorList);
		
	
			
			setLoggedData();
			execute();
		}catch(Exception ex){
			return "error";
		}
		
		return "success";
		
		
	}
		
	public String close()	 
	{
		setLoggedData();
		return "close";
	}
	
}
