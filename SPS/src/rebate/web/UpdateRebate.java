package rebate.web;

import inventory.service.InwrhmtmEjb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import job.model.Spestcnt;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import rebate.model.Sprebate;
import rebate.model.SprebatePK;
import rebate.service.RebateEjb;

import com.opensymphony.xwork2.ActionSupport;

import estimate.model.EstimateReference;
import estimate.model.EstimateReferencePK;
import estimate.service.EstimateReferenceEjb;
import estimate.service.PcesthttEjb;

public class UpdateRebate extends ActionSupport implements SessionAware, ParameterAware{
	
	private Map<String, Object> session;
	
	@SuppressWarnings("unused")
	private Map<String, String[]> parameters;
	private List<String> rebateList;
	private String[] SprebateID;
	private String loggedInUserId;
	private String costCenterNo;
	private String lblError;
	private String lblSuccess;
	private BigDecimal[] unitPrice;
	private BigDecimal[] offchargeQty;
	private BigDecimal[] rebateCost;
	private BigDecimal[] rebateQty;
	private BigDecimal[] reusableQty;
	private String estimateNo;
	private String deptId;
	private String region;
	private List<String> estimationNoList;
	
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getEstimateNo() {
		return estimateNo;
	}

	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
	}
	
	
	/*public UpdateRebate(String estimationNo,String deptId,String webAppName)
	{
		this.setEstimationNo(estimationNo);
		this.setDeptId(deptId);
		this.setRegion(webAppName);
		
	}*/
	/**
	 * 
	 */
	private static final long serialVersionUID = 7077641793089670090L;

	public String execute(){
		setLoggedData();
		setEstimationNoList();
		setRebateList();
		return SUCCESS;
	}
	
	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public void setLoggedData(){		
        setLoggedInUserId(getSessionKey("userName"));
        setCostCenterNo(getSessionKey("costCenterNo"));
        setDeptId(this.getCostCenterNo());
        
        
	}
	
	public void setEstimationNoList()
	{
		PcesthttEjb Ejb=new PcesthttEjb(getSessionKey("region"));
	
		estimationNoList=Ejb.getEstimateNoList(this.costCenterNo, (long) 75);	
		
		
		
	}
	
	public void setRebateList() {
		
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
	
	public String getSessionKey(String key) {
        return getSession().get(key).toString();
    }
	
	public void setParameters(Map<String, String[]> parameter) {
		this.parameters=parameter;
		
	}

	public Map<String, Object> getParameters() {
		return session;
	}

	/**
	 * @return the rebateList
	 */
	public List<String> getRebateList() {
		return rebateList;
	}

	/**
	 * @param rebateList the rebateList to set
	 */
	public void setRebateList(List<String> rebateList) {
		this.rebateList = rebateList;
	}
	
	
	public String Updaterebate()
	{
		if ( getSprebateID() == null)
		{
			execute();
			setLblError("Please select at least one record to update");
			return SUCCESS;
		}
		int i = 0;
		for (String key : getSprebateID()) {
			
			StringTokenizer st = new StringTokenizer(key, "#*");
			String txtEstNo,txtDEPTID,txtReCode;
			BigDecimal txtUniPrice,txtOffQty,txtReCost,txtReQty,txtReusableQty;
			txtEstNo = st.nextToken();
			System.out.println("txtEstNo"+txtEstNo);
			txtDEPTID = st.nextToken();
			System.out.println("txtEstNo"+txtDEPTID);
			txtReCode= st.nextToken();
			System.out.println("txtEstNo"+txtReCode);
			SprebatePK id = new SprebatePK();
			id.setEstimateNo(txtEstNo);		
			id.setDeptId(txtDEPTID);
			id.setResCd(txtReCode);
			
			RebateEjb ejb = new RebateEjb(getSessionKey("region"));
			Sprebate tempObj = null;
			try
			{	
				
				tempObj = ejb.findById(id, getSessionKey("region"));
				
				txtUniPrice=this.getUnitPrice()[i];
				System.out.println("txtUniPrice"+txtUniPrice);
				
				if(getOffchargeQty()[i]==null){
					txtOffQty=new BigDecimal(0);
					System.out.println("txtOffQty"+txtOffQty);
				}else{
					txtOffQty=this.getOffchargeQty()[i];
					System.out.println("txtOffQty"+txtOffQty);
				}
				
				if(getRebateCost()[i]==null){
					txtReCost=new BigDecimal(0);
					System.out.println("txtReCost"+txtReCost);
				}else{
					txtReCost=this.getRebateCost()[i];
					System.out.println("txtReCost"+txtReCost);
				}
				
				if(getRebateQty()[i]==null){
					txtReQty=new BigDecimal(0);
					System.out.println("txtReQty"+txtReQty);
				}else{
					txtReQty=this.getRebateQty()[i];
					System.out.println("txtReQty"+txtReQty);
				}
				
				if(getRebateQty()[i]==null){
					txtReusableQty=new BigDecimal(0);
					System.out.println("txtReusableQty"+txtReusableQty);
				}else{
					txtReusableQty=this.getReusableQty()[i];
					System.out.println("txtReusableQty"+txtReusableQty);
				}
			
				i++; 
				tempObj.setId(id);
				tempObj.setUnitPrice(txtUniPrice);
				tempObj.setOffchargeQty(txtOffQty);
				tempObj.setRebateCost(txtReCost);
				tempObj.setRebateQty(txtReQty);
				tempObj.setReusableQty(txtReusableQty);
				
				
				ejb.updateRebate(tempObj, getSessionKey("region"));
				System.out.println(getSessionKey("region"));
				System.out.println(tempObj+"22222222222222222222222222222222222");
				//ejb.updateSpestcnt(tempObj);
				
			}catch(Exception e1)
			{
				execute();
				setLblError("Update failed.");
				 
				return SUCCESS;
			/*}*/
			}
		}
 		execute();
 		setLblSuccess("Rebate details updated successfully");
		return SUCCESS;
		
	}

	/**
	 * @return the lblError
	 */
	public String getLblError() {
		return lblError;
	}

	/**
	 * @param lblError the lblError to set
	 */
	public void setLblError(String lblError) {
		this.lblError = lblError;
	}

	/**
	 * @return the lblSuccess
	 */
	public String getLblSuccess() {
		return lblSuccess;
	}

	/**
	 * @param lblSuccess the lblSuccess to set
	 */
	public void setLblSuccess(String lblSuccess) {
		this.lblSuccess = lblSuccess;
	}

	/**
	 * @return the sprebateID
	 */
	public String[] getSprebateID() {
		return SprebateID;
	}

	/**
	 * @param sprebateID the sprebateID to set
	 */
	public void setSprebateID(String[] sprebateID) {
		SprebateID = sprebateID;
	}

	/**
	 * @return the unitPrice
	 */
	public BigDecimal[] getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(BigDecimal[] unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * @return the offchargeQty
	 */
	public BigDecimal[] getOffchargeQty() {
		return offchargeQty;
	}

	/**
	 * @param offchargeQty the offchargeQty to set
	 */
	public void setOffchargeQty(BigDecimal[] offchargeQty) {
		this.offchargeQty = offchargeQty;
	}

	/**
	 * @return the rebateCost
	 */
	public BigDecimal[] getRebateCost() {
		return rebateCost;
	}

	/**
	 * @param rebateCost the rebateCost to set
	 */
	public void setRebateCost(BigDecimal[] rebateCost) {
		this.rebateCost = rebateCost;
	}

	/**
	 * @return the rebateQty
	 */
	public BigDecimal[] getRebateQty() {
		return rebateQty;
	}

	/**
	 * @param rebateQty the rebateQty to set
	 */
	public void setRebateQty(BigDecimal[] rebateQty) {
		this.rebateQty = rebateQty;
	}

	/**
	 * @return the reusableQty
	 */
	public BigDecimal[] getReusableQty() {
		return reusableQty;
	}

	/**
	 * @param reusableQty the reusableQty to set
	 */
	public void setReusableQty(BigDecimal[] reusableQty) {
		this.reusableQty = reusableQty;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @return the loggedInUserId
	 */
	public String getLoggedInUserId() {
		return loggedInUserId;
	}

	/**
	 * @param loggedInUserId the loggedInUserId to set
	 */
	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}

	/**
	 * @return the costCenterNo
	 */
	public String getCostCenterNo() {
		return costCenterNo;
	}

	/**
	 * @param costCenterNo the costCenterNo to set
	 */
	public void setCostCenterNo(String costCenterNo) {
		this.costCenterNo = costCenterNo;
	}

	/**
	 * @return the estimationNoList
	 */
	public List<String> getEstimationNoList() {
		return estimationNoList;
	}

	/**
	 * @param estimationNoList the estimationNoList to set
	 */
	public void setEstimationNoList(List<String> estimationNoList) {
		this.estimationNoList = estimationNoList;
	}

	/**
	 * @return the sprebateID
	 */
	
}
