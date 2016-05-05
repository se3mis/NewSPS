package job.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import job.service.JobEjb;
import job.service.PcesthmtEjb;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import util.common.EstimateStatus;

import com.opensymphony.xwork2.ActionSupport;

public class ForcedJobClose extends ActionSupport implements SessionAware, ParameterAware{

	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private List<String> listjobNo;	
	private String closingDate;	
	private String jobNo;	
	
	private static final Log log = LogFactory.getLog(ForcedJobClose.class);
	private static final String newPath="Manage Jobs>Forced Job Close";

	private String path;
	private String loggedInUserId;
	private String costCenterNo;
	private String csc;
	private String costCenter;
	private Date appointmentDate;
	private String statusMsg;
	private String statusMsgErr;
	private String  estimatedCost;
	private String  fund;
	private String  committedCost;
	private String  jobStatus;
	private String  varianceIn;
	
	public String getEstimatedCost() {
		return estimatedCost;
	}
	public void setEstimatedCost(String estimatedCost) {
		this.estimatedCost = estimatedCost;
	}
	public String getFund() {
		return fund;
	}
	public void setFund(String fund) {
		this.fund = fund;
	}
	public String getCommittedCost() {
		return committedCost;
	}
	public void setCommittedCost(String committedCost) {
		this.committedCost = committedCost;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	public String getVarianceIn() {
		return varianceIn;
	}
	public void setVarianceIn(String varianceIn) {
		this.varianceIn = varianceIn;
	}
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
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
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
	public String getJobNo() {
		return jobNo;
	}
	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public String getClosingDate() {
		return closingDate;
	}
	public void setClosingDate(String closingDate) {
		this.closingDate = closingDate;
	}
	public List<String> getListjobNo() {
		return listjobNo;
	}
	public void setListjobNo(List<String> listjobNo) {
		this.listjobNo = listjobNo;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public Map<String, String[]> getParameters() {
		return parameters;
	}
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters = parameters;
	}
	
	public String execute()	 
	{
		try{			
			setLoggedData();
		 	setPath(newPath);
				
			listjobNo = new ArrayList<String>();
			costCenter = getSessionKey("costCenterNo");
						
						
			if(costCenter!=null && !costCenter.equals("-1")){				
				PcesthmtEjb ejb=new PcesthmtEjb(getSessionKey("region"));
		    	List<String> resultList= ejb.getJobNoList(costCenter, new Long(EstimateStatus.JOB_ONGOING));		    				
		    	Iterator<String> it = resultList.iterator();
		    	   	
		        while (it.hasNext()) {        	 
		        	String pces=it.next();
		        	listjobNo.add(pces);        	        	
		          } 
		        appointmentDate = new Date();	
			}else{
				closingDate="";
				
			}	
			
			if(jobNo!=null && !jobNo.equals("-1")){
				System.out.println("The job number is.......:"+jobNo);
			}else
				clearForm();
			
			return "success";
		}catch(Exception ex){
			return "error";
		}
	}
	
	public String close(){
		setLoggedData() ;
		return "close";
	}
	
	public String forcedjobclose(){
		String costCentre = getSessionKey("costCenterNo");				
		try{
			JobEjb jobEjb=new JobEjb(getSessionKey("region"));
			jobEjb.forceJobCloser(jobNo.trim(),costCentre, appointmentDate);
			setLoggedData();
			statusMsg = "The Job :" +jobNo.trim()+" closed successfully";
			execute();
		}catch(Exception ex){
			statusMsgErr = "Error in closing the job :"+jobNo.trim();
			return "error";
		}
		
		
		setLoggedData();
		execute();
		return "success";
	}
	
	public void clearForm(){
		  estimatedCost="";
		  fund="";
		  committedCost="";
		  jobStatus="";
		  varianceIn="";
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
}
