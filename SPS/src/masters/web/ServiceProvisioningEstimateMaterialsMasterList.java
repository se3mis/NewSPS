package masters.web;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import estimate.model.Spestmtm;
import estimate.service.SpestmtmEjb;

 
 

public class ServiceProvisioningEstimateMaterialsMasterList extends ActionSupport implements SessionAware, ParameterAware  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private static final String viewPath="Masters>Service Provisioning Estimate Materials Master Detials List";
	private String path;
	private String lblError = null;
	private String lblSuccess = null;
	private String costCenter;
	private List<Spestmtm> MaterialList;
	private List<Spestmtm> single30OH;
	private List<Spestmtm> three30OH;
	private List<Spestmtm> three60OH;
	private List<Spestmtm> single30UG;


	private List<Spestmtm> three30UG;
	private List<Spestmtm> three60UG;
	private List<Spestmtm> single15UG;
	
	
	public String execute(){
		setLblError(null);
		
		setLoggedInUserId(getSession().get("userName").toString());
		setPath(viewPath);
		setCostCenter(getSessionKey("costCenterNo"));
		
		 fillCostingList();
		return SUCCESS;
	}
	
	public String Close(){
		return "close";
	}
	
	
	private void fillCostingList()
	{
		SpestmtmEjb ejb = new SpestmtmEjb(getSessionKey("region"));
	//	this.MaterialList = ejb.getAll(getSessionKey("costCenterNo"));
		this.single30OH = ejb.estimateMaterials(new Long(1), new Long(30), "OH", getSessionKey("costCenterNo"));
		this.three30OH = ejb.estimateMaterials(new Long(3), new Long(30), "OH", getSessionKey("costCenterNo"));
		this.three60OH = ejb.estimateMaterials(new Long(3), new Long(60), "OH", getSessionKey("costCenterNo"));
		this.single30UG = ejb.estimateMaterials(new Long(1), new Long(30), "UG", getSessionKey("costCenterNo"));
		this.three30UG = ejb.estimateMaterials(new Long(3), new Long(30), "UG", getSessionKey("costCenterNo"));
		this.three60UG = ejb.estimateMaterials(new Long(3), new Long(60), "UG", getSessionKey("costCenterNo"));
		this.single15UG = ejb.estimateMaterials(new Long(1), new Long(15), "UG", getSessionKey("costCenterNo"));
	}
	
	 


	@Override
	public void setParameters(Map<String, String[]> parameters) {
		// TODO Auto-generated method stub
		this.parameters=parameters;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session=session;
	}
	public String getSessionKey(String key) {
		return getSession().get(key).toString();
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


	public String getCostCenter() {
		return costCenter;
	}


	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
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

	public List<Spestmtm> getMaterialList() {
		return MaterialList;
	}

	public void setMaterialList(List<Spestmtm> materialList) {
		MaterialList = materialList;
	}
	
	public List<Spestmtm> getSingle30OH() {
		return single30OH;
	}

	public void setSingle30OH(List<Spestmtm> single30oh) {
		single30OH = single30oh;
	}

	public List<Spestmtm> getThree30OH() {
		return three30OH;
	}

	public void setThree30OH(List<Spestmtm> three30oh) {
		three30OH = three30oh;
	}

	public List<Spestmtm> getThree60OH() {
		return three60OH;
	}

	public void setThree60OH(List<Spestmtm> three60oh) {
		three60OH = three60oh;
	}

	public List<Spestmtm> getSingle30UG() {
		return single30UG;
	}

	public void setSingle30UG(List<Spestmtm> single30ug) {
		single30UG = single30ug;
	}

	public List<Spestmtm> getThree30UG() {
		return three30UG;
	}

	public void setThree30UG(List<Spestmtm> three30ug) {
		three30UG = three30ug;
	}

	public List<Spestmtm> getThree60UG() {
		return three60UG;
	}

	public void setThree60UG(List<Spestmtm> three60ug) {
		three60UG = three60ug;
	}
	public List<Spestmtm> getSingle15UG() {
		return single15UG;
	}

	public void setSingle15UG(List<Spestmtm> single15ug) {
		single15UG = single15ug;
	}
}
