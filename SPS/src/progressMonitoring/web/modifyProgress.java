package progressMonitoring.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import job.model.Pcesthmt;
import job.model.Spestcnt;
import job.model.SpestcntPK;
import job.service.PcesthmtEjb;
import job.service.SpestcntEjb;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import progressMonitoring.model.Pcinitialdata;
import progressMonitoring.model.PcinitialdataPK;
import progressMonitoring.model.Pcmilem;
import progressMonitoring.service.PcinitialdataEjb;
import progressMonitoring.service.PcmilemEjb;
import util.common.EstimateStatus;

import application.service.ApplicationTransactionEjb;

import com.itextpdf.text.Document;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import estimate.model.Spstdestdmt;

public class modifyProgress extends ActionSupport implements SessionAware, ParameterAware {
	
	private static final String modifyPath="Customer>Modify Progress";
	private String path;
	private String errorMessage;
	private String costCenterNo =null;

	

	//variables for table and data fields

	public List<String> jobNumberList;
	public List<String> milestoneList = new ArrayList<String>();
	public String loggedInUserId;
	







	private Map<String, Object> session;

	
	
	public String newDirect()
	{		setLoggedData();
			setPath(modifyPath);

			setErrorMessage(null);
			
			PcesthmtEjb pchmtEjb=new PcesthmtEjb(region);
			jobNumberList = new ArrayList<String>();
			List<Long> statuses = new ArrayList<Long>();
			statuses.add(new Long(EstimateStatus.JOB_ONGOING));
			statuses.add(new Long(EstimateStatus.JOB_SOFT_CLOSED));
			statuses.add(new Long(EstimateStatus.JOB_SOFT_CLOSED));
			statuses.add(new Long(EstimateStatus.JOB_RIVISION));
			statuses.add(new Long(EstimateStatus.JOB_APPROVAL_CE));
			jobNumberList=pchmtEjb.findJobNosList(getCostCenterNo(), statuses);
			
			PcmilemEjb pcmilemEjb=new PcmilemEjb(region);
			List<Pcmilem> milestones= pcmilemEjb.getMilestoneList(getCostCenterNo(), getRegion());
			for(Pcmilem pcmilem : milestones){
				milestoneList.add(pcmilem.getMile_nm() + " - " + pcmilem.getPercentage()+"%" );
			}
			
		return SUCCESS; 

}
	
	
	

	private String region;
	
	private List<String> jobNoList = new ArrayList<String>(); 

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	
	private void setLoggedData() {
		setSession(ActionContext.getContext().getSession());

		getCostCenterNo();
		setLoggedInUserId(getSessionKey("userName"));
		setCostCenterNo(getSessionKey("costCenterNo"));
		System.out.println(getSessionKey("region"));

		setCostCenterNo(costCenterNo);
		setRegion(getSessionKey("region"));
		
		
		
		// log.info(getSession());

		
	}

	public String getSessionKey(String key) {
		return getSession().get(key).toString();
	}
	
	public Map<String, Object> getSession() {
		return session;
	}
	public String searchJob(){
		System.out.println("In search job method");
		//have to use get element by id method to check the value
		//pcinitiladataList=pcinEjb.getDataForEstNumber("000114","customerRef");
		//pcinitiladataList=pcinEjb.getDataForEstNumber("510.20/ENC/2011/0003","projNoJobNo");


		return "success";
		
		
	}

	
	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}


	private void setPath(String path) {
		this.path=path;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	@Override
	public void setParameters(Map<String, String[]> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
		
		
	}

	public String getLoggedInUserId() {
		return loggedInUserId;
	}


	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}
	public String geCostCenterNo(){
		return costCenterNo;
		
	}
	public String setCostCenterNo(String costCenterNo){
		return this.costCenterNo=costCenterNo;
	}

	


	private void setLblError(String string) {
		// TODO Auto-generated method stub
		
	}



	public void setJobNoList(List<String> jobNoList) {
		this.jobNoList = jobNoList;
	}
	public String getCostCenterNo() {
		return costCenterNo;
	}



	public List<String> getJobNumberList() {
		return jobNumberList;
	}



	public void setJobNumberList(List<String> jobNumberList) {
		this.jobNumberList = jobNumberList;
	}

	public List<String> getJobNoList() {
		/*areaCodeList.add("--Select--");
		ApplicationTransactionEjb transactionEjb=new ApplicationTransactionEjb(getSessionKey("region"));
		List<String> areaCodes = transactionEjb.findAreaCodeNames(getCostCenterNo(), getSessionKey("region"));
		if(areaCodes == null && areaCodes.size() == 0){
			areaCodeList = new ArrayList<String>();
			if(getCostCenterNo() != null){
				areaCodeList.add(getCostCenterNo());
			}
		}
		areaCodeList.addAll(areaCodes);
		return areaCodeList;
		*/
		
		return jobNoList;
	}



	public List<String> getMilestoneList() {
		return milestoneList;
	}



	public void setMilestoneList(List<String> milestoneList) {
		this.milestoneList = milestoneList;
	}




}
