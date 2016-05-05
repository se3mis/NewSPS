package job.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import job.model.Pcesthmt;
import job.service.JobEjb;
import job.service.PcesthmtEjb;

import offDoc.web.JobTransferredReport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import util.common.EstimateStatus;

import com.opensymphony.xwork2.ActionSupport;



public class HardJobClose extends ActionSupport implements SessionAware, ParameterAware{
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;

	private String path;
	private String loggedInUserId;
	private String costCenterNo;
	private String csc;
	private String costCenter;	
	private List<String> listjobNo;	
	private String closingDate;	
	private String jobNo;
	private Date appointmentDate;
	private String statusMsg;
	private String statusMsgErr;
	private String estimatedCost;
	private String fund;
	private String committedCost;
	private String jobStatus;
	private String varianceIn;
		
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

	public String getStatusMsgErr() {
		return statusMsgErr;
	}

	public void setStatusMsgErr(String statusMsgErr) {
		this.statusMsgErr = statusMsgErr;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(String closingDate) {
		this.closingDate = closingDate;
	}

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public List<String> getListjobNo() {
		return listjobNo;
	}

	public void setListjobNo(List<String> listjobNo) {
		this.listjobNo = listjobNo;
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

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public static String getNewpath() {
		return newPath;
	}

	private static final Log log = LogFactory.getLog(HardJobClose.class);
	private static final String newPath="Manage Jobs>Job Closer";

	
	public String getSessionKey(String key) {
        return getSession().get(key).toString();
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
			costCenter= getSessionKey("costCenterNo");						
			if(costCenter!=null && !costCenter.equals("-1")){				
				PcesthmtEjb ejb=new PcesthmtEjb(getSessionKey("region"));
				listjobNo= ejb.getJobNoList(costCenter, new Long(EstimateStatus.JOB_SOFT_CLOSED));		    				
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
	
	public String hardJobclose(){
		
		String costCentre = getSessionKey("costCenterNo");
		List<Pcesthmt> closedJobList= new ArrayList<Pcesthmt>();	
		try{
			JobEjb jobEjb=new JobEjb(getSessionKey("region"));	
			PcesthmtEjb pcesejb=new PcesthmtEjb(getSessionKey("region"));
			String closeOut=jobEjb.jobCloser(jobNo.trim(),costCentre,  appointmentDate);
			
			if(closeOut.startsWith("#")){
				statusMsg = "The Job :" +closeOut.substring(1)+" closed successfully";
				statusMsgErr ="";
				}
			else if(closeOut.startsWith("$")){
				statusMsgErr = "The Job :" +closeOut.substring(1)+" was not closed properly";
				statusMsg ="";
			}else{
				statusMsgErr = closeOut;
				statusMsg ="";
			}	
			
			
			if(closeOut.length()>18){
				String jobNum = closeOut.substring(13,19);
				Pcesthmt pchtmt= pcesejb.findByJobNo(jobNum,costCentre);
				if(pchtmt!=null)
					closedJobList.add(pchtmt);
			}
			
			if(closedJobList!=null && !closedJobList.isEmpty() && closedJobList.size()>0){
				JobTransferredReport jtr = new JobTransferredReport(closedJobList,new Date(),costCentre,getSessionKey("userName"));
				jtr.Print(true);
			}		
		
			
			setLoggedData();
			execute();
		}catch(Exception ex){
			return "error";
		}
		
		return "success";
	}
	
	public String close(){
		setLoggedData() ;
		return "close";
	}

}
