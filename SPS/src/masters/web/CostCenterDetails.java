package masters.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import job.service.SpestcntEjb;

import offDoc.web.Reason;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import costcenter.model.Area;
import costcenter.model.Gldeptin;
import costcenter.model.Province;
import costcenter.service.CostCenterEjb;
import costcenter.service.GldeptinEjb;

public class CostCenterDetails extends ActionSupport implements SessionAware, ParameterAware  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private static final String viewPath="Masters>Cost Center Details";
	private String path;
	private String lblError = null;
	private String lblSuccess = null;
	private String costCenter;
	private String costCenterName;

	private String depFullName;
	private String depAddress;
	private String depTelephone;
	private List<String> regionList;
	private String selectedRegion;
	private List<Province> provinceList;
	private String selectedProvince;
	private List<Area> areaList;
	private String selectedArea;
	private List<String> deptTypeList;
	private String selectedDeptType;
	private String bulkName;
	private String bulkAddress;
	private String bulkTelephone;
	private String branchCode;
	private String bankCode;
	private String posA;
	private String posCentre;

	public String execute(){
		setLblError(null);

		setLoggedInUserId(getSession().get("userName").toString());
		setPath(viewPath);

		setCostCenter(getSessionKey("costCenterNo"));

		setDeptTypeList();
	//	setAreaList();
		showCostCenterDetials();
		setProvinceList();
		setRegionList();
		return SUCCESS;
	}
	public String getSessionKey(String key) {
		return getSession().get(key).toString();
	}

	private void showCostCenterDetials()
	{
		GldeptinEjb ejb = new GldeptinEjb(getSessionKey("region"));
		Gldeptin gledptin =  ejb.findById(getCostCenter());
		if (gledptin != null)
		{
			setBulkAddress(gledptin.getBulkSupplierAdd());//gldeptin.setBulkSupplierAdd(getBulkAddress());
			setBulkName(gledptin.getBulkSupplierName()); //gldeptin.setBulkSupplierName(getBulkName());
			if (gledptin.getBulkSupplierTel()!= null)
				setBulkTelephone(gledptin.getBulkSupplierTel().toString()); //gldeptin.setBulkSupplierTel(tel);
			setDepAddress(gledptin.getDeptAdd()); //gldeptin.setDeptAdd(getDepAddress());
			setSelectedArea(gledptin.getDeptArea()); //gldeptin.setDeptArea(getSelectedArea());
			setDepFullName(gledptin.getDeptFullName()); //gldeptin.setDeptFullName(getDepFullName());
			setCostCenter(gledptin.getDeptId()); //gldeptin.setDeptId(getCostCenter());
			setSelectedDeptType(gledptin.getDeptType()); //gldeptin.setDeptType(getSelectedDeptType());
			setSelectedProvince(gledptin.getDeptProvince()); //gldeptin.setDeptProvince(getSelectedProvince());
			setSelectedRegion(gledptin.getDeptRegion()); //gldeptin.setDeptRegion(getSelectedRegion());
			setBankCode(gledptin.getBankCode()); // gldeptin.setBankCode(getBankCode());
			setBranchCode(gledptin.getBranchCode()); //gldeptin.setBranchCode(getBranchCode());
			setDepTelephone(gledptin.getDeptTel()); //gldeptin.setDeptTel(getDepTelephone());
			setPosA(gledptin.getPosA());
			setPosCentre(gledptin.getPosCenter());

		}
	}

	private void setRegionList()
	{
		this.regionList = new ArrayList<String>();
		this.regionList.add("R1");
		this.regionList.add("R2");
		this.regionList.add("R3");
		this.regionList.add("R4");
	}
	private void setProvinceList()
	{
		this.provinceList = new ArrayList<Province>();
		CostCenterEjb ejb=new CostCenterEjb(getSessionKey("region"));
		this.provinceList = ejb.getAllProvince();
	}

	private void setAreaList()
	{
		this.areaList = new ArrayList<Area>();
		CostCenterEjb ejb=new CostCenterEjb(getSessionKey("region"));
		this.areaList = ejb.getAllAreas();
	}

	private void setDeptTypeList()
	{
		this.deptTypeList = new ArrayList<String>();
		this.deptTypeList.add("Depot");
		this.deptTypeList.add("Area");
		this.deptTypeList.add("Regional");
		this.deptTypeList.add("Provincial");
	}

	public String Exit(){
		return "close";
	}
	public String AddCostCenterDetials()
	{
		setCostCenter(getSessionKey("costCenterNo"));
		GldeptinEjb ejb = new GldeptinEjb(getSessionKey("region"));
		Gldeptin  gldeptin = new Gldeptin();
		gldeptin.setBulkSupplierAdd(getBulkAddress());
		gldeptin.setBulkSupplierName(getBulkName());
		if (!(getBulkTelephone().equals("")))
		{
			BigDecimal tel = new BigDecimal(getBulkTelephone());
			gldeptin.setBulkSupplierTel(tel);
		}
		gldeptin.setDeptAdd(getDepAddress());
		gldeptin.setDeptArea(getSelectedArea());
		gldeptin.setDeptFullName(getDepFullName());
		gldeptin.setDeptId(getCostCenter());
		gldeptin.setDeptType(getSelectedDeptType());
		gldeptin.setDeptProvince(getSelectedProvince());
		gldeptin.setDeptRegion(getSelectedRegion());
		gldeptin.setBankCode(getBankCode());
		gldeptin.setBranchCode(getBranchCode());
		gldeptin.setDeptTel(getDepTelephone());
		gldeptin.setPosA(getPosA());
		gldeptin.setPosCenter(getPosCentre());


		Gldeptin temGldeptin =  ejb.findById(getCostCenter());
		if (temGldeptin != null)
		{
			try {
				ejb.updateGldeptin(gldeptin);
			} catch (Exception e) {
				execute();
				setLblError("Error in bank code or branch code");
				return SUCCESS;
			}
		}else
		{
			try {
				ejb.createGldeptin(gldeptin);
		} catch (Exception e) {
			execute();
			setLblError("Error in bank code or branch code");
			return SUCCESS;
		}
		}

		execute();
		setLblSuccess("Cost center details updated successfully.");
		return SUCCESS;
	}

	public String Close(){
		return "close";
	}

	public List<String> getRegionList() {
		return regionList;
	}
	public void setRegionList(List<String> regionList) {
		this.regionList = regionList;
	}
	public String getSelectedRegion() {
		return selectedRegion;
	}
	public void setSelectedRegion(String selectedRegion) {
		this.selectedRegion = selectedRegion;
	}
	public List<Province> getProvinceList() {
		return provinceList;
	}
	public void setProvinceList(List<Province> provinceList) {
		this.provinceList = provinceList;
	}
	public String getSelectedProvince() {
		return selectedProvince;
	}
	public void setSelectedProvince(String selectedProvince) {
		this.selectedProvince = selectedProvince;
	}
	public String getDepFullName() {
		return depFullName;
	}
	public void setDepFullName(String depFullName) {
		this.depFullName = depFullName;
	}
	public String getDepAddress() {
		return depAddress;
	}
	public void setDepAddress(String depAddress) {
		this.depAddress = depAddress;
	}
	public String getDepTelephone() {
		return depTelephone;
	}
	public void setDepTelephone(String depTelephone) {
		this.depTelephone = depTelephone;
	}

	public List<Area> getAreaList() {
		return areaList;
	}
	public void setAreaList(List<Area> areaList) {
		this.areaList = areaList;
	}
	public String getSelectedArea() {
		return selectedArea;
	}
	public void setSelectedArea(String selectedArea) {
		this.selectedArea = selectedArea;
	}
	public List<String> getDeptTypeList() {
		return deptTypeList;
	}
	public void setDeptTypeList(List<String> deptTypeList) {
		this.deptTypeList = deptTypeList;
	}
	public String getSelectedDeptType() {
		return selectedDeptType;
	}
	public void setSelectedDeptType(String selectedDeptType) {
		this.selectedDeptType = selectedDeptType;
	}
	public String getBulkName() {
		return bulkName;
	}
	public void setBulkName(String bulkName) {
		this.bulkName = bulkName;
	}
	public String getBulkAddress() {
		return bulkAddress;
	}
	public void setBulkAddress(String bulkAddress) {
		this.bulkAddress = bulkAddress;
	}
	public String getBulkTelephone() {
		return bulkTelephone;
	}
	public void setBulkTelephone(String bulkTelephone) {
		this.bulkTelephone = bulkTelephone;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getCostCenter() {
		return costCenter;
	}



	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}



	public String getCostCenterName() {
		return costCenterName;
	}



	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
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
	public String getPosA() {
		return posA;
	}
	public void setPosA(String posA) {
		this.posA = posA;
	}
	public String getPosCentre() {
		return posCentre;
	}
	public void setPosCentre(String posCentre) {
		this.posCentre = posCentre;
	}
	 

}
