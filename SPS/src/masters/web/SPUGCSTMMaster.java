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
import estimate.model.Spugcstm;
import estimate.model.SpugcstmPK;
import estimate.service.SpcndctmEjb;
import estimate.service.SpcstngmEjb;
import estimate.service.SpugcstmEjb;

public class SPUGCSTMMaster extends ActionSupport implements SessionAware, ParameterAware {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private static final String viewPath="Masters>Service Type Material Master Detials";
	private String path;
	private String lblError = null;
	private String lblSuccess = null;

	private List<Phase> phaseList;
	private String selectedPhase;

	private List<String> conectionTypesList;
	private String selectedConectionType;

	private List<String> serviceTypesList;
	private String selectedServiceType;

	private List<String> TypesList;
	private String selectedType;


	private BigDecimal wireMeterPrice;
	private BigDecimal fixedCost;
	private String updateBy;
	private String updateDate;

	private String costCenter;


	public String execute(){
		setLblError(null);

		setLoggedInUserId(getSession().get("userName").toString());
		setPath(viewPath);
		setCostCenter(getSessionKey("costCenterNo"));

		setPhaseList();
		setConnectionTypesList();
		setServiceTypeList();


		String phase,conType,loopOrCable,type;
		phase = getSelectedPhase();
		conType = getSelectedConectionType();
		loopOrCable = getSelectedServiceType();
		type = getSelectedType();

		if (phase == null && conType == null && loopOrCable == null && type == null )
		{
			//getSPUGCSTMDetials("1","15","LOOP","1");
			setTypesList("1","15","LOOP");
		}else
		{	
			setTypesList(phase,conType,loopOrCable);		
			//getSPUGCSTMDetials(phase,conType,loopOrCable,type);
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

	private void setConnectionTypesList()
	{
		this.conectionTypesList = new ArrayList<String>();
		this.conectionTypesList.add("15");
		this.conectionTypesList.add("30");
		this.conectionTypesList.add("60");
	}


	private void setServiceTypeList()
	{
		this.serviceTypesList = new ArrayList<String>();
		this.serviceTypesList.add("LOOP");
		this.serviceTypesList.add("CABLE");

	}

	private void setTypesList(String phase,String conType,String loopOrCable)
	{
		String typex = getSelectedType();
		this.TypesList = new ArrayList<String>();
		if (phase.equals("1") && conType.equals("15") && loopOrCable.equals("LOOP") )
		{
			this.TypesList.add("1");
			typex = "1";
		}else if (phase.equals("1") && conType.equals("15") && loopOrCable.equals("CABLE" ))
		{
			this.TypesList.add("162C");
			typex = "162C";
		}else if (phase.equals("1") && conType.equals("30") && loopOrCable.equals("CABLE") )
		{
			this.TypesList.add("162C");
			typex = "162C";
		}else if (phase.equals("3") && conType.equals("15") && loopOrCable.equals("LOOP") )
		{
			this.TypesList.add("2");
			typex = "2";
		}else if (phase.equals("3") && conType.equals("30") && loopOrCable.equals("LOOP" ))
		{
			this.TypesList.add("1");
			typex = "1";
		}else if (phase.equals("3") && conType.equals("30") && loopOrCable.equals("CABLE") )
		{
			this.TypesList.add("16XPLE");
			typex = "16XPLE";
		}else if (phase.equals("3") && conType.equals("60") && loopOrCable.equals("CABLE") )
		{
			this.TypesList.add("35XPLE");
			this.TypesList.add("70XPLE");
			this.TypesList.add("95XPLE");
			if (! (typex == null ))
			{
				if ( !(typex.equals("70XPLE") || typex.equals("95XPLE") ))
				{
					typex = "35XPLE";
				} 
			}else
			{
				typex = "35XPLE";
			}

		}else
		{
			BigDecimal bigD = new BigDecimal("0");			 
			setWireMeterPrice(bigD);
			setFixedCost(bigD);
			setUpdateBy(null);
			setUpdateDate(null);
		}

		getSPUGCSTMDetials(phase,conType,loopOrCable,typex);



	}

	private void getSPUGCSTMDetials(String phase,String conType,String loopOrCable,String type)
	{	
		SpugcstmPK id = new SpugcstmPK();


		id.setCategory(type);
		id.setConnectionType(Long.parseLong(conType));
		id.setLoopCable(loopOrCable);
		id.setPhase(Long.parseLong(phase));




		SpugcstmEjb ejb = new SpugcstmEjb(getSessionKey("region"));

		Spugcstm spugcstm = null;
		try
		{			  
			spugcstm = ejb.findById(id);

		}catch(Exception e1)
		{	
			setLblError("Could not find details");
			return;
		}
		if (spugcstm == null)
		{
			BigDecimal bigD = new BigDecimal("0");			 
			setWireMeterPrice(bigD);
			setFixedCost(bigD);
		}else
		{
			setWireMeterPrice(spugcstm.getWireMeterPrice());	
			setFixedCost(spugcstm.getFixedCost() );
			setUpdateBy(spugcstm.getUpdatedBy());
			Format ft = new Format();
			setUpdateDate(ft.formatDate( spugcstm.getUpdatedDate()));

		}
	}

	public String UpdateMaster(){

		setCostCenter(getSessionKey("costCenterNo"));
		BigDecimal wireMetrPrice,fixedCst;		 
		wireMetrPrice = getWireMeterPrice(); 
		fixedCst = getFixedCost();

		if (wireMetrPrice == null)
			wireMetrPrice = new BigDecimal(0);

		SpugcstmPK id = new SpugcstmPK();
		String phase,conType,loopOrCable,type;
		phase = getSelectedPhase();
		conType = getSelectedConectionType();
		loopOrCable = getSelectedServiceType();
		type = getSelectedType();

		if (type == null || type == "")
		{
			execute();
			setLblError("Invalid Combination");
			BigDecimal bigD = new BigDecimal("0");			 
			setWireMeterPrice(bigD);
			setFixedCost(bigD);
			setUpdateBy(null);
			setUpdateDate(null);
			return SUCCESS;
		}


		id.setCategory(type);
		id.setConnectionType(Long.parseLong(conType));
		id.setLoopCable(loopOrCable);
		id.setPhase(Long.parseLong(phase));



		SpugcstmEjb ejb = new SpugcstmEjb(getSessionKey("region"));

		Spugcstm spugcstm = null;
		try
		{			  
			spugcstm = ejb.findById(id);

		}catch(Exception e1)
		{
			return SUCCESS;
		}

		Date dt = new Date();
		setLoggedInUserId(getSession().get("userName").toString());
		if (spugcstm == null)
		{
			spugcstm = new Spugcstm() ;
			spugcstm.setId(id);
			spugcstm.setWireMeterPrice(wireMetrPrice);	
			spugcstm.setFixedCost(fixedCst);
			Format ft =  new Format();			 
			spugcstm.setUpdatedDate(dt);
			spugcstm.setUpdatedBy(getLoggedInUserId());			 
			ejb.createSpugcstm(spugcstm);
		}else
		{
			spugcstm = new Spugcstm() ;
			spugcstm.setId(id);
			spugcstm.setWireMeterPrice(wireMetrPrice);	
			spugcstm.setFixedCost(fixedCst);
			Format ft =  new Format();			 
			spugcstm.setUpdatedDate(dt);
			spugcstm.setUpdatedBy(getLoggedInUserId());
			ejb.updateSpugcstm(spugcstm);
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

	public List<String> getConectionTypesList() {
		return conectionTypesList;
	}

	public void setConectionTypesList(List<String> conectionTypesList) {
		this.conectionTypesList = conectionTypesList;
	}

	public String getSelectedConectionType() {
		return selectedConectionType;
	}

	public void setSelectedConectionType(String selectedConectionType) {
		this.selectedConectionType = selectedConectionType;
	}

	public List<String> getServiceTypesList() {
		return serviceTypesList;
	}

	public void setServiceTypesList(List<String> serviceTypesList) {
		this.serviceTypesList = serviceTypesList;
	}

	public String getSelectedServiceType() {
		return selectedServiceType;
	}

	public void setSelectedServiceType(String selectedServiceType) {
		this.selectedServiceType = selectedServiceType;
	}

	public List<String> getTypesList() {
		return TypesList;
	}

	public void setTypesList(List<String> typesList) {
		TypesList = typesList;
	}

	public String getSelectedType() {
		return selectedType;
	}

	public void setSelectedType(String selectedType) {
		this.selectedType = selectedType;
	}

	public BigDecimal getWireMeterPrice() {
		return wireMeterPrice;
	}

	public void setWireMeterPrice(BigDecimal wireMeterPrice) {
		this.wireMeterPrice = wireMeterPrice;
	}

	public BigDecimal getFixedCost() {
		return fixedCost;
	}

	public void setFixedCost(BigDecimal fixedCost) {
		this.fixedCost = fixedCost;
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
