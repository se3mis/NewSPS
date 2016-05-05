package masters.web;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import job.model.Spestcnd;
import job.model.Spestcnt;
import job.model.SpestcntPK;
import job.service.SpestcndEjb;
import job.service.SpestcntEjb;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import util.common.Phase;

import com.opensymphony.xwork2.ActionSupport;

import costcenter.model.Gldeptm;
import costcenter.service.GldeptmEjb;

import estimate.model.Spestmtm;
import estimate.model.SpestmtmPK;
import estimate.service.SpestmtmEjb;

public class UpdateContractor extends ActionSupport implements SessionAware, ParameterAware {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private static final String viewPath="Masters>Update Contractor Detials";
	private String path;
	private String lblError = null;
	private String lblSuccess = null;
	private String[] spestcntID;
	private String costCenterNo;

	 
	private String selectedDepartment;
	private List<Spestcnt> constructorList;
	
	// once user check rows to update.. flolowing array gets fill
	private String[] contractorName;
	private String[] contractorAddress;
	private String[] vat;
	private String[] nbt;
	private String[] status;
	private String[] code;
	 
	 
	
	 
	 
	public String[] getStatus() {
		return status;
	}
	public void setStatus(String[] status) {
		this.status = status;
	}
	public String[] getVat() {
		return vat;
	}
	public void setVat(String[] vat) {
		this.vat = vat;
	}
	public String[] getNbt() {
		return nbt;
	}
	public void setNbt(String[] nbt) {
		this.nbt = nbt;
	}
	public String getCostCenterNo() {
		return costCenterNo;
	}
	public void setCostCenterNo(String costCenterNo) {
		this.costCenterNo = costCenterNo;
	}
	public String[] getContractorName() {
		return contractorName;
	}
	public void setContractorName(String[] contractorName) {
		this.contractorName = contractorName;
	}
	public String[] getContractorAddress() {
		return contractorAddress;
	}
	public void setContractorAddress(String[] contractorAddress) {
		this.contractorAddress = contractorAddress;
	}
	public String[] getSpestcntID() {
		return spestcntID;
	}
	public void setSpestcntID(String[] spestcntID) {
		this.spestcntID = spestcntID;
	}
	public String getSelectedDepartment() {
		return selectedDepartment;
	}
	public void setSelectedDepartment(String selectedDepartment) {
		this.selectedDepartment = selectedDepartment;
	}
	
	
	public List<Spestcnt> getConstructorList() {
		return constructorList;
	}
	public void setConstructorList(List<Spestcnt> constructorList) {
		this.constructorList = constructorList;
	}
	public String getLoggedInUserId() {
		return loggedInUserId;
	}
	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getLblError() {
		return lblError;
	}
	public void setLblError(String lblError) {
		this.lblError = lblError;
	}
	public String getLblSuccess() {
		return lblSuccess;
	}
	public void setLblSuccess(String lblSuccess) {
		this.lblSuccess = lblSuccess;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public Map<String, String[]> getParameters() {
		return parameters;
	}
	public static String getViewpath() {
		return viewPath;
	}
	@Override
	public void setParameters(Map<String, String[]> parameters){
		// TODO Auto-generated method stub
		this.parameters=parameters;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session=session;
	}
	
	
	public String execute(){
		setLblError(null);
		
		setLoggedInUserId(getSession().get("userName").toString());
		setPath(viewPath);
		setCostCenterNo(getSessionKey("costCenterNo"));
		LoadContractors();
		
		
		return SUCCESS;
	}
	public String getSessionKey(String key) {
		return getSession().get(key).toString();
	}
	
	 
	
	public String LoadContractors()
	{
		String depId = getCostCenterNo();
		if (depId == null)
		{
			setLblError("Please enter deparment ID");
			return SUCCESS;
		}
		if (depId.trim().isEmpty())
		{
			setLblError("Please enter deparment ID");
			return SUCCESS;
		}
		
		
		
		SpestcntEjb ejb = new SpestcntEjb(getSessionKey("region"));
		this.constructorList =  ejb.getAll(depId);
		return SUCCESS;
	}
	
	public String Exit(){
		return "close";
	}
	
	public String Update()
	{
		if ( getSpestcntID() == null)
		{
			execute();
			setLblError("Please select at least one record to update");
			return SUCCESS;
		}
		int i = 0;
		for (String key : getSpestcntID()) {
			
			StringTokenizer st = new StringTokenizer(key, "#*");
			String contractorID,deptID,newName,newCode,newAddress,nVat,nNbt,status;
			contractorID = st.nextToken();
			deptID = st.nextToken();
		 
			SpestcntPK id = new SpestcntPK();
			id.setContractorId(contractorID);
			id.setDeptId(deptID);
			
			SpestcntEjb ejb = new SpestcntEjb(getSessionKey("region"));
			Spestcnt tempObj = null;
			try
			{				 
				tempObj = ejb.findById(id);
				newName = getContractorName()[i];
				newCode = getCode()[i];
				newAddress = getContractorAddress()[i];
				nVat = getVat()[i];
				status = getStatus()[i];
				if (nVat == null || nVat.isEmpty() )
					nVat = "0";
				
				
				nNbt = getNbt()[i];
				if (nNbt == null || nNbt.isEmpty())
					nNbt = "0";
				i++; 
				 
				tempObj.setContractorName(newName);
				tempObj.setContractorAddress(newAddress);
				 tempObj.setVat(nVat);
				 tempObj.setNbt(nNbt);
				 tempObj.setStatus(status);
				 tempObj.setCode(newCode);
				ejb.updateSpestcnt(tempObj);
				
			}catch(Exception e1)
			{
				execute();
				setLblError("Update failed.");
				 
				return SUCCESS;
			}
		}
		
 		execute();
 		setLblSuccess("Contractor details updated successfully");
		return SUCCESS;
	}
	
	public void setCode(String[] code) {
		this.code = code;
	}
	public String[] getCode() {
		return code;
	}

}
