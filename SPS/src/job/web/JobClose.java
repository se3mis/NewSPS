package job.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import job.model.ClosingJobInfo;
import job.model.Pcesthmt;
import job.service.JobEjb;
import job.service.PcesthmtEjb;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import util.common.EstimateStatus;
import util.common.StatusToDescription;



import com.opensymphony.xwork2.ActionSupport;

public class JobClose extends ActionSupport implements SessionAware, ParameterAware{

	/**
	 * 
	 */
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

	public String getCatCode() {
		return catCode;
	}

	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}

	public String getVarianceInPercent() {
		return varianceInPercent;
	}

	public void setVarianceInPercent(String varianceInPercent) {
		this.varianceInPercent = varianceInPercent;
	}

	private String  catCode;
	private String  varianceInPercent;;
	
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

	private static final Log log = LogFactory.getLog(JobClose.class);
	private static final String newPath="Manage Jobs>Soft Job Close";

	
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
			costCenter = getSessionKey("costCenterNo");			
			if(costCenter!=null && !costCenter.equals("-1")){				
				JobEjb ejb=new JobEjb(getSessionKey("region"));
				listjobNo= ejb.getSoftClosingJobNoList(costCenter);		    				
		        appointmentDate = new Date();	
			}else{
				closingDate="";
			}	
			
			if(jobNo!=null && !jobNo.equals("-1")){
				JobEjb jobejb=new JobEjb(getSessionKey("region"));
				ClosingJobInfo closingJobInfo = jobejb.getClosingJobInfo(jobNo, costCenter);
				if(closingJobInfo!=null){
					  estimatedCost="10000.00";
					  fund=closingJobInfo.getFundSource();
					  if(closingJobInfo.getCommitedCost()!=null)
						  committedCost=closingJobInfo.getCommitedCost().toString();
					  if(closingJobInfo.getStatus()!=null)
						  jobStatus=StatusToDescription.getStatusdescription(closingJobInfo.getStatus().intValue());
					  catCode = closingJobInfo.getCatCd();
					  
				}
			}else
				clearForm();
			
			return "success";
		}catch(Exception ex){
			return "error";
		}
	}
	
	public void setLoggedData() {	       
        log.info(getSession());
        setLoggedInUserId(getSessionKey("userName"));
        setCostCenterNo(getSessionKey("costCenterNo"));
        setCsc(getSessionKey("costCenterName"));
       
    }
	
	public String jobClose(){
		
		try{
			PcesthmtEjb ejb=new PcesthmtEjb(getSessionKey("region"));
			String costCentre = getSessionKey("costCenterNo");
			Pcesthmt pchtmt= ejb.findByJobNo(jobNo.trim(),costCentre);
			
			if(pchtmt!=null){				
				pchtmt.setStatus(new Long(EstimateStatus.JOB_SOFT_CLOSED));
				pchtmt.setAprDt1(appointmentDate);
				pchtmt.setAprUid1(getSessionKey("userName"));
				ejb.updatePcesthmt(pchtmt);
				statusMsg = "The Job :" +jobNo.trim()+" closed successfully";
			}
			setLoggedData();
			execute();
		}catch(Exception ex){
			statusMsgErr = "Error in closing the job :"+jobNo.trim();
			return "error";
		}
	
		return "success";
	}
	
	public String close(){
		setLoggedData() ;
		return "close";
	}
		
	public void clearForm(){
		  estimatedCost="";
		  fund="";
		  committedCost="";
		  jobStatus="";
		  varianceIn="";
		  catCode ="";
		  varianceInPercent="";
	}
}
