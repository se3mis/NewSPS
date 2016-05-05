package job.web;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import estimate.model.Pcesthtt;

import java.util.Iterator;
import job.model.PaySlipInfo;
import job.service.JobEjb;


public class SoftCloseGroup extends ActionSupport implements SessionAware, ParameterAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private static final Log log = LogFactory.getLog(SoftCloseGroup.class);
	private static final String newPath="Manage Jobs>Soft Close Job - Group";

	private String path;
	private String loggedInUserId;
	private String costCenterNo;
	private String csc;
	private String costCenter;
	private String statusMsg;
	private String statusMsgNotClosed;
	private String statusMsgErr;
	private List<String> listpaySlipNo;
	private String[] jobNumberList;
	



	public String[] getJobNumberList() {
		return jobNumberList;
	}

	public void setJobNumberList(String[] jobNumberList) {
		this.jobNumberList = jobNumberList;
	}

	public List<String> getListpaySlipNo() {
		return listpaySlipNo;
	}

	public void setListpaySlipNo(List<String> listpaySlipNo) {
		this.listpaySlipNo = listpaySlipNo;
	}

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
	
	private List<PaySlipDetails> list;
	private PaySlipCloseList closeJobList;
	private List<String> listreferenceNo;
	private Date closingDate;
	
	

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	private String selectedNo;

	public String getSelectedNo() {
		return selectedNo;
	}

	public void setSelectedNo(String selectedNo) {
		this.selectedNo = selectedNo;
	}
		
	public List<String> getListreferenceNo() {
		return listreferenceNo;
	}

	public void setListreferenceNo(List<String> listreferenceNo) {
		this.listreferenceNo = listreferenceNo;
	}

	public PaySlipCloseList getCloseJobList() {
		return closeJobList;
	}

	public void setCloseJobList(PaySlipCloseList closeJobList) {
		this.closeJobList = closeJobList;
	}

	public List<PaySlipDetails> getList() {
		return list;
	}

	public void setList(List<PaySlipDetails> list) {
		this.list = list;
	}

	public String execute()	 
	{
		try{
			setLoggedData();
		 	setPath(newPath);
			
			closeJobList= new PaySlipCloseList();
			listreferenceNo = new ArrayList<String>();		
	    	list = new ArrayList<PaySlipDetails>(); 	    	
	    	JobEjb ejb=new JobEjb(getSessionKey("region"));
	    	costCenter = getSessionKey("costCenterNo");   
	    	listpaySlipNo = new ArrayList<String>();
			
			if(costCenter!=null && !costCenter.equals("-1")){
				
				List<PaySlipInfo> paySlipInfoList =	ejb.getPaySlipNoList(costCenter);
				Iterator<PaySlipInfo> it = paySlipInfoList.iterator();
				
				while (it.hasNext()) {  
					PaySlipInfo paySlipInfo = it.next();
					if(paySlipInfo.getJobNos()!=null){
							List<String>jobNumbers= paySlipInfo.getJobNos();							
							StringBuilder sbJobNumbers=new StringBuilder();
							for(int i=0;i<jobNumbers.size();i++){
								if(i==0)
									sbJobNumbers.append(jobNumbers.get(i));
								else
									sbJobNumbers.append(","+jobNumbers.get(i));
							}	
								String slipAmt = null;
								if(paySlipInfo.getPaySlipAmount()!=null)
									slipAmt = paySlipInfo.getPaySlipAmount().toEngineeringString();
								
							PaySlipDetails paySlipDetails= new PaySlipDetails(sbJobNumbers.toString(),paySlipInfo.getDocNo(),slipAmt,paySlipInfo.getChequeDate());
							closeJobList.addList(list,paySlipDetails);						
					}
					
		       } 			

				closingDate = new Date();
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
			List<String>closingList = new ArrayList<String>();
			for(int i=0;i<jobNumberList.length;i++){
				closingList.add(jobNumberList[i]);
			}
			JobEjb jobejb=new JobEjb(getSessionKey("region"));
			String CostCen = getSessionKey("costCenterNo");
			jobejb.softJobCloser(closingList, CostCen);
			statusMsg = "Selected pay slips closed successfully";			
			setLoggedData();
			execute();
		}catch(Exception ex){
			statusMsgErr= "Error in closing selected pay slips";
			return "error";
		}
		
		return "success";
		
		
	}
		
	public String close()	 
	{
		setLoggedData();
		return "closed";
	}
	
}
