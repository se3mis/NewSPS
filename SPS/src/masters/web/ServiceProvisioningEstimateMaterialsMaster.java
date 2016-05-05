package masters.web;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import util.common.Format;
import util.common.Phase;

//import com.informix.lang.Decimal;
import com.opensymphony.xwork2.ActionSupport;

import estimate.model.Spestmtm;   
import estimate.model.SpestmtmPK;
import estimate.service.SpestmtmEjb;

@SuppressWarnings("serial")
public class ServiceProvisioningEstimateMaterialsMaster extends ActionSupport implements SessionAware, ParameterAware {
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private static final String viewPath="Masters>Service Provisioning Estimate Materials Master Detials";
	private String path;
	private String lblError = null;
	private String lblSuccess = null;
	private List<Phase> phaseList;


	private String selectedPhase;

	private List<String> conectionTypesList;
	private String selectedConectionType;

//	private List<String> lengthRangeList;
//	private String selectedLengthRange;

	private String updateBy;
	private String updateDate;
	private String updateTime;

	private String mtralCode;
	private String mtralQty;
	private List<Spestmtm> estmateMaterialsList;

	private String[] spestmtmID;
	private String[] matQty;
	private String costCenter;


	private List<String> wiringTypeList;
	private String selectedwiringType; 

	 
	
	 
	public List<String> getWiringTypeList() {
		return wiringTypeList;
	}
	public void setWiringTypeList(List<String> wiringTypeList) {
		this.wiringTypeList = wiringTypeList;
	}
	public String getSelectedwiringType() {
		return selectedwiringType;
	}
	public void setSelectedwiringType(String selectedwiringType) {
		this.selectedwiringType = selectedwiringType;
	}
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	public String[] getMatQty() {
		return matQty;
	}
	public void setMatQty(String[] matQty) {
		this.matQty = matQty;
	}
	public String[] getSpestmtmID() {
		return spestmtmID;
	}
	public void setSpestmtmID(String[] spestmtmID) {
		this.spestmtmID = spestmtmID;
	}
	public List<Spestmtm> getEstmateMaterialsList() {
		return estmateMaterialsList;
	}
	public void setEstmateMaterialsList(List<Spestmtm> estmateMaterialsList) {
		this.estmateMaterialsList = estmateMaterialsList;
	}
	public String getMtralCode() {
		return mtralCode;
	}
	public void setMtralCode(String mtralCode) {
		this.mtralCode = mtralCode;
	}

	 
	public String getMtralQty() {
		return mtralQty;
	}
	public void setMtralQty(String mtralQty) {
		this.mtralQty = mtralQty;
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
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public Map<String, String[]> getParameters() {
		return parameters;
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





	public String execute(){

		setLblError(null);

		setLoggedInUserId(getSession().get("userName").toString());
		setPath(viewPath);
		setCostCenter(getSessionKey("costCenterNo"));
		
		setPhaseList();
		setConnectionTypesList();
	//	setLengthRange();
		setWiringTypeList();
		getEstimateMaterials();

		if (this.estmateMaterialsList == null || this.estmateMaterialsList.size() == 0)
			setLblError("Materials Master Detials not found");
		return SUCCESS;
	}
	public String getSessionKey(String key) {
		return getSession().get(key).toString();
	}
	private void setPhaseList()
	{
		this.phaseList = new   ArrayList<Phase>();
		Phase single = new Phase("Single Phase", "1");
	//	Phase two = new Phase("2 Phase" , "2");
		Phase three = new Phase("3 Phase","3");
		this.phaseList.add(single);
	//	this.phaseList.add(two);
		this.phaseList.add(three);

	}

	private void setConnectionTypesList()
	{
		this.conectionTypesList = new ArrayList<String>();
		this.conectionTypesList.add("30");
		this.conectionTypesList.add("60");
		this.conectionTypesList.add("15");
		
		
	}
	private void setLengthRange()
	{
//		lengthRangeList = new ArrayList<String>();
//
//		lengthRangeList.add("0-30");
//		lengthRangeList.add("31-50");
//		lengthRangeList.add("51-100");
	}

	private void setWiringTypeList()
	{
		wiringTypeList = new ArrayList<String>();
		wiringTypeList.add("Over Head");
		wiringTypeList.add("Underground");//UG if the text changes please edit the getEstimateMaterials() method too
		// OH
	}
	private void getEstimateMaterials()
	{

		String phase,length,type,to,from  ;
		Long lPhase,lLength,lType,lTo,lFrom;
		String wiringType = "OH";
		String slecWirType ;
		
		phase = getSelectedPhase();	 
		//length =   getSelectedLengthRange() ;
		type = getSelectedConectionType();
		slecWirType = getSelectedwiringType();
		if (phase == null ||  type == null || slecWirType == null)
		{
			phase  = "1";
		//	length = "0-30";
			type = "30";
			slecWirType= "Over Head";
		}
	//	String[] tempStr = length.split("-");
	//	from =  tempStr[0] ;
	//	to = tempStr[1] ;
		lPhase = Long.parseLong(phase);
		lType = Long.parseLong(type);
		//lFrom = Long.parseLong(from);
	//	lTo = Long.parseLong(to);
		


		setCostCenter(getSessionKey("costCenterNo"));
		this.estmateMaterialsList = new ArrayList<Spestmtm>();
		SpestmtmEjb ejb = new SpestmtmEjb(getSessionKey("region"));
		
		
		if (slecWirType.equals("Underground"))
			wiringType = "UG";
		else
			wiringType = "OH";
		this.estmateMaterialsList =  ejb.estimateMaterials(lPhase, lType,wiringType,getCostCenter());



	}

	public String Close(){
		return "close";
	}

	public String AddNewMaterial()
	{
		String phase,mCode,length,mtQty  ;
		Double mQty;
		Long  to,from,connectionType;
		phase = getSelectedPhase();
		connectionType = Long.parseLong( getSelectedConectionType());
	
		mCode = getMtralCode().trim();
		
		mtQty = getMtralQty();
		if ( mtQty.isEmpty())
		{
			execute();
			setLblError("Please enter material quantity");
			return SUCCESS;
		}
		mQty = Double.parseDouble( mtQty);

		if (  mCode.isEmpty())
		{
			execute();
			setLblError("Please enter material code");
			return SUCCESS;
		}

		setCostCenter(getSessionKey("costCenterNo"));
		SpestmtmPK id = new SpestmtmPK();
		id.setConnectionType(connectionType);	 
		id.setPhase(Long.parseLong(phase));	 
		id.setMatCd(mCode);
		id.setDeptId(getCostCenter());
		
		if (getSelectedwiringType().equals("Underground"))
			id.setWiringType("UG");  
		else
			id.setWiringType("OH");  
		 

		Spestmtm stemp = new Spestmtm();
		stemp.setId(id);

		BigDecimal bigD = new BigDecimal(mQty); 
		stemp.setMatQty(bigD);
		Date dt = new Date();

		stemp.setUpdDate(dt);
		setLoggedInUserId(getSession().get("userName").toString());
		stemp.setUpdUser(getLoggedInUserId());
		// UPDATE THE DATABASE
		SpestmtmEjb ejb = new SpestmtmEjb(getSessionKey("region"));
		Spestmtm tempObj = null;
	 
		try
		{			 
			tempObj = ejb.findById(id);
			
		}catch(Exception e1)
		{

		}
		
		if (tempObj != null)
		{
			execute();
			setLblError("Material detials already exist");
			return SUCCESS;
		}
			
		 
		try
		{
			ejb.createSpestmtm(stemp);
			
		}catch(Exception e2)
		{
			execute();
			setLblError("Invalid material code");
			return SUCCESS;
		}
		
		execute();
		setLblSuccess("Material detials added successfully");
		return SUCCESS;
	}

	public String Remove()
	{
		if ( getSpestmtmID() == null)
		{
			execute();
			setLblError("Please select at least one record to remove");
			return SUCCESS;
		}
		for (String key : getSpestmtmID()) {
			setCostCenter(getSessionKey("costCenterNo"));
			StringTokenizer st = new StringTokenizer(key, "#*");
			String phase,type,matCode,wiringType,matQty ;
			phase = st.nextToken();
			type = st.nextToken();		 
			matCode = st.nextToken().trim();			
			matQty = st.nextToken();
			wiringType = st.nextToken();
			
			Long lPhase,lType;
			lPhase = Long.parseLong(phase);
			lType = Long.parseLong(type);
		 
			
			setCostCenter(getSessionKey("costCenterNo"));
			SpestmtmPK id = new SpestmtmPK();
			id.setConnectionType(lType); 
			id.setPhase( lPhase ); 
			id.setMatCd(matCode);
			id.setDeptId(getCostCenter());	
			id.setWiringType(wiringType);
			
			SpestmtmEjb ejb = new SpestmtmEjb(getSessionKey("region"));
			Spestmtm tempObj = null;
		 
			try
			{				 
				tempObj = ejb.findById(id)  ;
				ejb.removeSpestmtm(tempObj);
				
			}catch(Exception e1)
			{
				execute();
				setLblError("Remove failed. Material Code :" + matCode);
				System.out.println(e1);	
				return SUCCESS;
			}
		}
		
 		execute();
 		setLblSuccess("Material detials removed successfully");
		return SUCCESS;
	}

	public String Update()
	{
		if ( getSpestmtmID() == null)
		{
			execute();
			setLblError("Please select at least one record to update");
			return SUCCESS;
		}
		int i = 0;
		for (String key : getSpestmtmID()) {
			
			setCostCenter(getSessionKey("costCenterNo"));
			StringTokenizer st = new StringTokenizer(key, "#*");
			String phase,type,matCode,wiringType,  matQty ;
			phase = st.nextToken();
			type = st.nextToken();
	 
			matCode = st.nextToken().trim();
			matQty = st.nextToken();
			wiringType = st.nextToken();
			Long lPhase,lType;
			lPhase = Long.parseLong(phase);
			lType = Long.parseLong(type);
 
			
			
			SpestmtmPK id = new SpestmtmPK();
			id.setConnectionType(lType); 
			id.setPhase( lPhase ); 
			id.setMatCd(matCode);
			id.setDeptId(getCostCenter());	
			id.setWiringType(wiringType);
			
			SpestmtmEjb ejb = new SpestmtmEjb(getSessionKey("region"));
			Spestmtm tempObj = null;
		 
			try
			{				 
				tempObj = ejb.findById(id);
				String matqt = getMatQty()[i];
				i++;
				if (matqt == null || matqt == "" )
					matqt = "0";
				BigDecimal bigD = null;
				try
				{
					bigD = new BigDecimal(matqt);
				}catch (NumberFormatException nf)
				{
					execute();
					setLblError("Update failed for material Code :" + matCode + ". Invalid material Quantity");					 
					return SUCCESS;
				}
				tempObj.setMatQty(bigD);
				ejb.updateSpestmtm(tempObj);
				
			}catch(Exception e1)
			{
				execute();
				setLblError("Update failed. Material Code :" + matCode);
				System.out.println(e1);	
				return SUCCESS;
			}
		}
		
 		execute();
 		setLblSuccess("Material detials updated successfully");
		return SUCCESS;
	}


}
