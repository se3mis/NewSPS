package estimate.web;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import job.model.Spestcnd;
import job.model.Spestcnt;
import job.model.SpestcntPK;
import job.service.SpestcndEjb;









import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import util.common.Phase;

import com.opensymphony.xwork2.ActionSupport;

import costcenter.model.Gldeptm;
import costcenter.service.GldeptmEjb;
import estimate.model.EstimateReference;
import estimate.model.EstimateReferencePK;
import estimate.model.Spestmtm;
import estimate.model.SpestmtmPK;
import estimate.service.EstimateReferenceEjb;
import estimate.service.SpestmtmEjb;

public class ChangeCodeNo extends ActionSupport implements SessionAware, ParameterAware {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private static final String viewPath="Estimate>Update Code No";
	private String path;
	private String lblError = null;
	private String lblSuccess = null;
	private String[] EstimateReferenceID;
	private String costCenterNo; 
	private String selectedDepartment;
	private List<EstimateReference> estimationRefList;
	
	
	// once user check rows to update.. flolowing array gets fill

	private String[] standardEstimateNo;
	private String[] workEstimateNo;
	private String[] deptid;
	private String[] status;
	private String[] fileReference;
	 
	 
	
	 
	 
	public String[] getStatus() {
		return status;
	}
	public void setStatus(String[] status) {
		this.status = status;
	}

	public String getCostCenterNo() {
		return costCenterNo;
	}
	public void setCostCenterNo(String costCenterNo) {
		this.costCenterNo = costCenterNo;
	}
		
	public String getSelectedDepartment() {
		return selectedDepartment;
	}
	public void setSelectedDepartment(String selectedDepartment) {
		this.selectedDepartment = selectedDepartment;
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
	
	public List<EstimateReference> getEstimationRefList() {
		return estimationRefList;
	}
	public void setEstimationRefList(List<EstimateReference> estimationRefList) {
		this.estimationRefList = estimationRefList;
	}
	public String execute(){
		setLblError(null);		
		setLoggedInUserId(getSession().get("userName").toString());
		setPath(viewPath);
		setCostCenterNo(getSessionKey("costCenterNo"));
		
		try {
			LoadEstimationRef();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	public String getSessionKey(String key) {
		return getSession().get(key).toString();
	}
	
	 
	
	public String LoadEstimationRef() throws Throwable
	{
			
		EstimateReferenceEjb ejb = new EstimateReferenceEjb();
		this.estimationRefList =  ejb.getActiveConstructionEstimates(this.getCostCenterNo(), getSessionKey("region"));
	
		return SUCCESS;
	}
	
	public String Exit(){
		return "close";
	}
	
	@SuppressWarnings("null")
	public String Update()
	{
		if ( getEstimateReferenceID() == null)
		{
			execute();
			setLblError("Please select at least one record to update");
			return SUCCESS;
		}
		int i = 0;
		for (String key : getEstimateReferenceID()) {
			
			StringTokenizer st = new StringTokenizer(key, "#*");
			String standardENo,workENo,depID,newCode,fileRef;
			workENo = st.nextToken();
			System.out.println(workENo+"workENo");
			depID = st.nextToken();
			System.out.println(depID+"dept ID");
			standardENo= st.nextToken();
			System.out.println(standardENo+"standardENo ID");
			EstimateReferencePK id = new EstimateReferencePK();
			id.setDeptId(depID);			
			id.setWorkEstimateNo(workENo);
			id.setStandardEstimateNo(standardENo);
			
			EstimateReferenceEjb ejb = new EstimateReferenceEjb();
			EstimateReference tempObj = null;
			try
			{	
				
				tempObj = ejb.findByAppId(id, getSessionKey("region"));
				
				newCode = this.getStatus()[i];
				System.out.println(newCode+"newCode");
				fileRef = this.getFileReference()[i];
				System.out.println(fileRef+"fileRef");
			
				i++; 
				tempObj.setId(id);
				tempObj.setStatus(newCode);
				tempObj.setFileReference(fileRef);
				ejb.updateEstimateReference(tempObj, getSessionKey("region"));
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
 		setLblSuccess("Code Number details updated successfully");
		return SUCCESS;
		
	}
	/**
	 * @return the fileReference
	 */
	public String[] getFileReference() {
		return fileReference;
	}
	/**
	 * @param fileReference the fileReference to set
	 */
	public void setFileReference(String[] fileReference) {
		this.fileReference = fileReference;
	}
	public String[] getDeptid() {
		return deptid;
	}
	public void setDeptid(String[] deptid) {
		this.deptid = deptid;
	}
	public String[] getWorkEstimateNo() {
		return workEstimateNo;
	}
	public void setWorkEstimateNo(String[] workEstimateNo) {
		this.workEstimateNo = workEstimateNo;
	}
	public String[] getStandardEstimateNo() {
		return standardEstimateNo;
	}
	public void setStandardEstimateNo(String[] standardEstimateNo) {
		this.standardEstimateNo = standardEstimateNo;
	}
	/**
	 * @return the estimateReferenceID
	 */
	public String[] getEstimateReferenceID() {
		return EstimateReferenceID;
	}
	/**
	 * @param estimateReferenceID the estimateReferenceID to set
	 */
	public void setEstimateReferenceID(String[] estimateReferenceID) {
		EstimateReferenceID = estimateReferenceID;
	}
	


}
