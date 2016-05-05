package masters.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import util.common.Format;
import util.common.Phase;
import util.common.Tariff;

import com.opensymphony.xwork2.ActionSupport;

import estimate.model.Spcndctm;
import estimate.model.SpcndctmPK;
import estimate.model.Spcstngm;
import estimate.model.SpcstngmPK;
import estimate.service.SpcndctmEjb;
import estimate.service.SpcstngmEjb;

public class SPCNDCTMMaster extends ActionSupport implements SessionAware, ParameterAware {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private static final String viewPath="Masters>Conductor Type Material Master	Details";
	private String path;
	private String lblError = null;
	private String lblSuccess = null;

	private List<Phase> phaseList;
	private String selectedPhase;

	private List<String> conductorTypesList;
	private String selectedConductorType;

	private List<Tariff> tarrifCategoryList;
	private String selectedTarrifCategory;


	private BigDecimal wireMeterPrice;
	private String updateBy;
	private String updateDate;

	private String costCenter;


	public String execute(){
		setLblError(null);

		setLoggedInUserId(getSession().get("userName").toString());
		setPath(viewPath);
		setCostCenter(getSessionKey("costCenterNo"));

		setPhaseList();
		setConductorTypesList();

		String phase,conductorType;
		phase = getSelectedPhase();
		conductorType = getSelectedConductorType();

		if (phase == null || conductorType == null )
		{
			getSPCNDCTMDetials("1","FLY");
		}else
		{
			getSPCNDCTMDetials(phase,conductorType);
		}

		return SUCCESS;
	}

	public String Close(){
		return "close";
	}

	private void setPhaseList()
	{
		this.phaseList = new   ArrayList<Phase>();
		Phase single = new Phase("Single Phase", "1");		 
		Phase three = new Phase("3 Phase","3");
		this.phaseList.add(single);		 
		this.phaseList.add(three);

	}

	private void setConductorTypesList()
	{
		this.conductorTypesList = new ArrayList<String>();
		this.conductorTypesList.add("FLY");
		this.conductorTypesList.add("ABC");

	}

	private void getSPCNDCTMDetials(String phase,String conductorType)
	{		 
		SpcndctmPK id = new SpcndctmPK();

		id.setConductorType(conductorType);
		id.setPhase(Long.parseLong(phase));


		SpcndctmEjb ejb = new SpcndctmEjb(getSessionKey("region"));

		Spcndctm spcndctm = null;
		try
		{			  
			spcndctm = ejb.findById(id);

		}catch(Exception e1)
		{	
			setLblError("Could not find wiring meter details");
			return;
		}
		if (spcndctm == null)
		{
			BigDecimal bigD = new BigDecimal("0");			 
			setWireMeterPrice(bigD);
		}else
		{
			setWireMeterPrice(spcndctm.getWireMeterPrice());	 
			setUpdateBy(spcndctm.getUpdatedBy());
			Format ft = new Format();
			setUpdateDate(ft.formatDate( spcndctm.getUpdatedDate()));

		}
	}
	
	public String UpdateMaster(){
		 
		setCostCenter(getSessionKey("costCenterNo"));
		BigDecimal wireMetrPrice;		 
		wireMetrPrice = getWireMeterPrice(); 
		
		
		if (wireMetrPrice == null)
			wireMetrPrice = new BigDecimal(0);
		
		SpcndctmPK id = new SpcndctmPK();
		String phase,conductorType;
		phase = getSelectedPhase();
		conductorType = getSelectedConductorType();
		id.setConductorType(conductorType);
		id.setPhase(Long.parseLong(phase));
		
		SpcndctmEjb ejb = new SpcndctmEjb(getSessionKey("region"));
	 
		Spcndctm spcndctm = null;
		try
		{			  
			spcndctm = ejb.findById(id);
			  
		}catch(Exception e1)
		{
			return SUCCESS;
		}
		
		Date dt = new Date();
		setLoggedInUserId(getSession().get("userName").toString());
		if (spcndctm == null)
		{
			spcndctm = new Spcndctm() ;
			spcndctm.setId(id);
			spcndctm.setWireMeterPrice(wireMetrPrice);			 
			Format ft =  new Format();			 
			spcndctm.setUpdatedDate(dt);
			spcndctm.setUpdatedBy(getLoggedInUserId());			 
			ejb.createSpcndctm(spcndctm);
		}else
		{
			spcndctm = new Spcndctm() ;
			spcndctm.setId(id);
			spcndctm.setWireMeterPrice(wireMetrPrice);			 
			Format ft =  new Format();			 
			spcndctm.setUpdatedDate(dt);
			spcndctm.setUpdatedBy(getLoggedInUserId());	
			ejb.updateSpcndctm(spcndctm);
		}
		setLblSuccess("Updated successfully");
		execute();
		return SUCCESS;
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

	public List<Phase> getPhaseList() {
		return phaseList;
	}

	public void setPhaseList(List<Phase> phaseList) {
		this.phaseList = phaseList;
	}

	public String getSelectedPhase() {
		return selectedPhase;
	}

	public void setSelectedPhase(String selectedPhase) {
		this.selectedPhase = selectedPhase;
	}

	public List<String> getConductorTypesList() {
		return conductorTypesList;
	}

	public void setConductorTypesList(List<String> conductorTypesList) {
		this.conductorTypesList = conductorTypesList;
	}

	public String getSelectedConductorType() {
		return selectedConductorType;
	}

	public void setSelectedConductorType(String selectedConductorType) {
		this.selectedConductorType = selectedConductorType;
	}

	public List<Tariff> getTarrifCategoryList() {
		return tarrifCategoryList;
	}

	public void setTarrifCategoryList(List<Tariff> tarrifCategoryList) {
		this.tarrifCategoryList = tarrifCategoryList;
	}

	public String getSelectedTarrifCategory() {
		return selectedTarrifCategory;
	}

	public void setSelectedTarrifCategory(String selectedTarrifCategory) {
		this.selectedTarrifCategory = selectedTarrifCategory;
	}

	public BigDecimal getWireMeterPrice() {
		return wireMeterPrice;
	}

	public void setWireMeterPrice(BigDecimal wireMeterPrice) {
		this.wireMeterPrice = wireMeterPrice;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public Map<String, String[]> getParameters() {
		return parameters;
	}


}
