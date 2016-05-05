package estimate.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import util.common.EstimateStatus;
import util.common.Format;


import com.opensymphony.xwork2.ActionSupport;

import estimate.model.Approval;
import estimate.model.Pcesthtt;
import estimate.model.ReferenceDeleteInfo;
import estimate.model.SpeststdPK;
import estimate.service.EstimateEjb;
import estimate.service.PcesthttEjb;
import estimate.service.SpeststdEjb;

public class DeleteJob extends ActionSupport implements SessionAware, ParameterAware{

	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	
	private static final Log log = LogFactory.getLog(DeleteJob.class);
	private static final String newPath="Estimation>Delete";
	
	private String estimateDate;
	private String referenceNo;
	private String selectedReference;
	
	private String path;
	private String loggedInUserId;
	private String costCenterNo;
	private String csc;
	private String costCentre;
	private String statusMsg;
	
	public String getStatusMsg() {
		return statusMsg;
	}
	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}
	public String getCostCentre() {
		return costCentre;
	}
	public void setCostCentre(String costCentre) {
		this.costCentre = costCentre;
	}
	public String getCsc() {
		return csc;
	}
	public void setCsc(String csc) {
		this.csc = csc;
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
	public String getSelectedReference() {
		return selectedReference;
	}
	public void setSelectedReference(String selectedReference) {
		this.selectedReference = selectedReference;
	}
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	public String getEstimateDate() {
		return estimateDate;
	}
	public void setEstimateDate(String estimateDate) {
		this.estimateDate = estimateDate;
	}
	public String getEstimateCategory() {
		return estimateCategory;
	}
	public void setEstimateCategory(String estimateCategory) {
		this.estimateCategory = estimateCategory;
	}

	private String estimateCategory;
	
	private List<String> listreferenceNo;
	private List<String> listSelectedReference;
	
	public List<String> getListSelectedReference() {
		return listSelectedReference;
	}
	public void setListSelectedReference(List<String> listSelectedReference) {
		this.listSelectedReference = listSelectedReference;
	}
	public List<String> getListreferenceNo() {
		return listreferenceNo;
	}
	public void setListreferenceNo(List<String> listreferenceNo) {
		this.listreferenceNo = listreferenceNo;
	}
	public Map<String, Object> getSession() {
		return session;	
	}
 	
 	public String getSessionKey(String key) {
        return getSession().get(key).toString();
    }
	
	public String execute()	 
	{
		try {
			setLoggedData();
			setPath(newPath);
			PcesthttEjb ejb=new PcesthttEjb(getSessionKey("region"));	
			String loggedCostCentre = getSessionKey("costCenterNo");
			costCentre = loggedCostCentre;
			listSelectedReference = new  ArrayList<String>();
			listreferenceNo = new  ArrayList<String>();
			List<ReferenceDeleteInfo> refList= ejb.getReferenceInfoList(costCentre,new Long(EstimateStatus.MODIFIED));		
			Iterator<ReferenceDeleteInfo> it = refList.iterator();
			
			while (it.hasNext()) {        	 
				ReferenceDeleteInfo info=it.next();
				listreferenceNo.add(info.getEstimateNo());
				listSelectedReference.add(info.getDeptId()+","+info.getRevNo()+","+info.getEtimateDt()+","+info.getEstType());
			}	
		}catch(Exception ex){
			System.out.println("The error is............:"+ex);
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
	
	public String delete()	 
	{
		try{
			setLoggedData();
			EstimateEjb estimateEjb=new EstimateEjb(getSessionKey("region"));
			PcesthttEjb pcesthttEjb=new PcesthttEjb(getSessionKey("region"));
			SpeststdEjb speststdEjb=new SpeststdEjb(getSessionKey("region"));
			String costCentre = getSessionKey("costCenterNo");		
			//Approvals
			Calendar calendar = Calendar.getInstance();
			Format format=new Format();
			Approval approval=new Approval();
			approval.setApprovalId(null);
			approval.setReferenceNo(referenceNo.trim());
			approval.setDeptId(costCentre);
			approval.setApprovalType("EST_DELE");
			approval.setApprovedLevel(getSessionKey("userRole"));
			approval.setApprovedBy(getLoggedInUserId());
			approval.setApprovedDate(calendar.getTime());
			approval.setApprovedTime(format.FormatTime());
			approval.setFromStatus(new BigDecimal(EstimateStatus.MODIFIED).toString());
			approval.setToStatus("00");
			SpeststdPK id=new SpeststdPK();
			id.setDeptId(costCentre);
			id.setEstimateNo(referenceNo.trim());
			approval.setStandardCost(speststdEjb.findById(id).getTotalCost());
			approval.setDetailedCost(pcesthttEjb.findByEstimationNo(referenceNo.trim(), costCenterNo).getStdCost());
			estimateEjb.delete(referenceNo.trim(), costCentre, approval);
			statusMsg = "The Estimates :"+ referenceNo.trim()+" deleted successfully";
			setLoggedData();
			execute();
			return "success";
			
		}catch(Exception ex){
			ex.printStackTrace();
			return "error";
		}
		
	}
	

	public String Close()
	{
		setLoggedData();
		return "closed";
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
	
}
