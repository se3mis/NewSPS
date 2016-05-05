package rebate.web;

import inventory.service.InwrhmapEjb;
import inventory.service.InwrhmtmEjb;

import java.math.BigDecimal;
import java.util.*;

import job.model.Spestcnt;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import rebate.model.Sprebate;
import rebate.model.SprebatePK;
import rebate.service.RebateEjb;

import com.opensymphony.xwork2.ActionSupport;

import estimate.service.PcesthttEjb;

public class Rebate extends ActionSupport implements SessionAware, ParameterAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String loggedInUserId;
	private String costCenterNo;
	private Map<String, Object> session;
	@SuppressWarnings("unused")
	private Map<String, String[]> parameters;
	
	
	private String LblSuccess;
	private String LblError;
	private String errestimateNo;
	private String errresCd;
	
	private String estimateNo;
	private String deptId;
	private String resCd;
	private BigDecimal offchargeQty;
	private BigDecimal rebateCost;
	private BigDecimal rebateQty;
	private BigDecimal revNo;
	private BigDecimal unitPrice;
	private BigDecimal reusableQty;
	private String sprebateID;
	private List<String> estimationNoList;
	private List<String> resourceCodeList;
	private List<String> rebateList;

	
	
	
	public String execute(){
		
		setLoggedData();	
		this.setRevNo(new BigDecimal(2));
		setResocrceCodeList();
		setEstimationNoList();
		setunitPrice();
		setRebateList();			
		
		System.out.println("+++++++++++++++++++++++++++++++++++++");
		
		
		return SUCCESS;
	}
	
	
	public void setEstimationNoList()
	{
		PcesthttEjb Ejb=new PcesthttEjb(getSessionKey("region"));
	
		estimationNoList=Ejb.getEstimateNoList(this.costCenterNo, (long) 75);	
		
		
		
	}
	
	
	
	
	public String addRebate() throws Exception{		
		
		
		String estimateNo=this.getEstimateNo();
		String deptId=this.getDeptId();
		String resCd=this.getResCd();
		BigDecimal offchargeQty=this.getOffchargeQty();
		BigDecimal rebateCost=this.getRebateCost();
		BigDecimal rebateQty=this.getRebateQty();
		BigDecimal revNo=new BigDecimal(2);
		BigDecimal unitPrice=this.getUnitPrice();
		BigDecimal reusableQty=this.getReusableQty();
		String message=null;
		
		
		
		RebateEjb ReEJB=new RebateEjb(getSessionKey("region"));
		
		
		SprebatePK SprebatePK=new SprebatePK();
		SprebatePK.setDeptId(deptId);
		SprebatePK.setEstimateNo(estimateNo);
		SprebatePK.setResCd(resCd);		
        
        Sprebate Sprebate=new Sprebate();
        Sprebate.setId(SprebatePK);
        Sprebate.setOffchargeQty(offchargeQty);
        Sprebate.setRebateCost(rebateCost);
        Sprebate.setRebateQty(rebateQty);
        Sprebate.setRevNo(revNo);
        Sprebate.setUnitPrice(unitPrice);
        Sprebate.setReusableQty(reusableQty);
		
        System.out.println(Sprebate+"11111111111111111111111111111111111111");
       
        Sprebate sprebate=ReEJB.findById(SprebatePK, getSessionKey("region"));
        
        if (sprebate == null)
		{try{
        	ReEJB.createRebate(Sprebate, getSessionKey("region"));
			this.setLblSuccess("Rebate added successfully");
		}catch(Exception e)
		{
			execute();
			this.setLblError("Rebate already exist.");
		}
		}
        else
		{			
			setLblError("Rebate already exist.");
			
		}
       
        execute();
		return SUCCESS;
	}

public void setRebateList() {
		
		System.out.println(estimationNoList.get(0)+"88888888888"+deptId+"888888888888"+getSessionKey("region")+"888888888");
		
		RebateEjb EJB=new RebateEjb(getSessionKey("region"));
		if(this.getEstimateNo()==null)
		{
			rebateList= EJB.findByEstimationNoStr(estimationNoList.get(0),this.getCostCenterNo(),getSessionKey("region"));
		}
		else{
			rebateList= EJB.findByEstimationNoStr(this.getEstimateNo(),this.getCostCenterNo(),getSessionKey("region"));
		}
		
		
		
	}
	
	public String close(){
		return "close";
	}
	
	public void setResocrceCodeList(){
		InwrhmtmEjb Ejb=new InwrhmtmEjb(getSessionKey("region"));
		InwrhmapEjb MapEjb=new InwrhmapEjb();
		//get warehouse number according to deID
		String warehouse=MapEjb.mapWarehouse(this.getDeptId(), getSessionKey("region"));
		this.resourceCodeList=Ejb.findMatCds(warehouse, getSessionKey("region"));
		
		
	}
		
	
	public String setunitPrice()
	{
		System.out.println("///?????????????????????????????????????????????????");
		
		InwrhmtmEjb Ejb=new InwrhmtmEjb(getSessionKey("region"));		
		this.setUnitPrice(Ejb.findUnitPriceByMatCd(this.getResCd(), getSessionKey("region")));
		setEstimationNoList();
		setResocrceCodeList();
				
		
		return SUCCESS;
					
	}
	
	public void setLoggedData(){		
        setLoggedInUserId(getSessionKey("userName"));
        setCostCenterNo(getSessionKey("costCenterNo"));
        setDeptId(this.getCostCenterNo());
        
        
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

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public String getSessionKey(String key) {
        return getSession().get(key).toString();
    }

	public String getEstimateNo() {
		return estimateNo;
	}

	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getResCd() {
		return resCd;
	}

	public void setResCd(String resCd) {
		this.resCd = resCd;
	}

	public BigDecimal getOffchargeQty() {
		return offchargeQty;
	}

	public void setOffchargeQty(BigDecimal offchargeQty) {
		this.offchargeQty = offchargeQty;
	}

	public BigDecimal getRebateCost() {
		return rebateCost;
	}

	public void setRebateCost(BigDecimal rebateCost) {
		this.rebateCost = rebateCost;
	}

	public BigDecimal getRebateQty() {
		return rebateQty;
	}

	public void setRebateQty(BigDecimal rebateQty) {
		this.rebateQty = rebateQty;
	}

	public BigDecimal getRevNo() {
		return revNo;
	}

	public void setRevNo(BigDecimal revNo) {
		this.revNo = revNo;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getReusableQty() {
		return reusableQty;
	}

	public void setReusableQty(BigDecimal reusableQty) {
		this.reusableQty = reusableQty;
	}

	public String getLblError() {
		return LblError;
	}

	public void setLblError(String LblError) {
		this.LblError = LblError;
	}

	public String getLblSuccess() {
		return LblSuccess;
	}

	public void setLblSuccess(String LblSuccess) {
		this.LblSuccess = LblSuccess;
	}

	@Override
	public void setParameters(Map<String, String[]> parameter) {
		this.parameters=parameter;
		
	}

	public Map<String, Object> getParameters() {
		return session;
	}

	public String getErrestimateNo() {
		return errestimateNo;
	}

	public void setErrestimateNo(String errestimateNo) {
		this.errestimateNo = errestimateNo;
	}

	public String getErrresCd() {
		return errresCd;
	}

	public void setErrresCd(String errresCd) {
		this.errresCd = errresCd;
	}

	


	public List<String> getRebateList() {
		return rebateList;
	}


	public void setRebateList(List<String> rebateList) {
		this.rebateList = rebateList;
	}


	public String getSprebateID() {
		return sprebateID;
	}


	public void setSprebateID(String sprebateID) {
		this.sprebateID = sprebateID;
	}


	public List<String> getEstimationNoList() {
		return estimationNoList;
	}


	public void setEstimationNoList(List<String> estimationNoList) {
		this.estimationNoList = estimationNoList;
	}


	public List<String> getResocrceCodeList() {
		return resourceCodeList;
	}


	public void setResocrceCodeList(List<String> resocrceCodeList) {
		this.resourceCodeList = resocrceCodeList;
	}

}


	
