package estimateMT.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;

import offDoc.web.PrintEstimate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import util.common.Format;

import application.model.Application;
import application.model.WiringLandDetail;
import application.model.WiringLandDetailPK;
import application.service.ApplicantEjb;
import application.service.ApplicationEjb;
import application.service.WiringLandDetailEjb;
import application.model.Applicant;
import estimate.service.PcesthttEjb;
import estimate.model.Pcesthtt;
import estimate.model.PcesthttPK;
import estimate.service.SpcstngmEjb;
import estimate.model.SpcstngmPK;
import estimate.model.Spcstngm;
import estimate.model.Speststd;
import estimate.model.SpeststdPK;
import estimate.service.SpeststdEjb;
import estimate.model.Pcestdtt;
import estimate.model.PcestdttPK;
import estimate.service.PcestdttEjb;
import estimate.service.EstimateEjb;
import estimate.model.MaterialGrid;

import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

 

@SuppressWarnings("serial")
public class PrintEstimateActionMT extends ActionSupport implements SessionAware, ParameterAware,ServletResponseAware,ServletRequestAware{
	private static final Log log = LogFactory.getLog(EstimateMT.class);
	private static final String viewPath="Estimate>Print Estimate";
	
	NumberFormat nf = NumberFormat.getInstance();
    
    
	private HttpServletRequest request;
	private HttpServletResponse response;
	  
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private String errorMessage;
	
	//session variables
	private Map<String, Object> sessionMap;
	String loggedUser;

	//JSP Fields
	private String costCenterNo;
	private String costCenterName;
	private String applicationNo;
	private String applicantName;
	private String address;
	private String neighboursAccNo;
	private String assessmentNo;
	private String description;
	private String applicantDate;
	private String phase;
	private String connectionType;
	private String phaseDb;
	private String connectionTypeDb;
	private String tariffCode;
	private String tariffCategory;
	
	private String isLoopService;
	private String categoryCode;
	private String lineLength;
	private String loopLength;
	private String lb;
	private String spans;
	private String poleNo;
	private String stayNo;
	private String strutsNo;
	private String sinNo;
	private String transColor;
	private String distanceToServicePlace;
	private String estimationType;
	private String jobDesc;
	private String wiringType;
	private String fundSourceID;
	/*private String rda;
	private String psd;
	private String mc;
	private String police;*/
	private String permission;
	private String printMsg;
	
	private String fixedCost;
	private String variableCost;
	private String subTotal;
	private String otherCost;
	private String convCost;
	private String taxAmount;
	private String secDeposit;
	private String totalCost;
	
	// Hidden Fields
	private String isViewApplicant;
	private String wireMeterPrice;
	private String isMatListPopUp;
	private String isMatListClose;
	
	
	/****************************/
	// Local variables
	private int connectionTypeInt;
	private int phaseInt;
	private String tariffCategoryCode;
		
	//Material List variables
	private List selectMatList;
	private String[] chkMatCode;
	private String[] chkSelMatCode;
	private String[] txtSelQty;
	private String[] txtSelMatCost;
	private String totalMatCost;
	
	//other Fields
	private String path;
	private String state;
	
	
	
	public String execute()
	{
		setErrorMessage(null);
		setLoggedData();
		setPath(viewPath);
		setState("newEstimate");
		return SUCCESS;
	}
	
	public String print()
	{
		System.out.println("printing");
		setErrorMessage(null);
		setLoggedData();
		setPath(viewPath);
		setState("newEstimate");
		
		//String applicationNo = "510.20/SNL/2010/0003";
		//String costCenterNo = "510.20";
		PcesthttEjb pcesthttEjb = new PcesthttEjb(getSessionKey("region"));
		Pcesthtt pcesthtt = null;
		pcesthtt = pcesthttEjb.findByEstimationNo(applicationNo,costCenterNo);
		SpeststdPK speststdPK = new SpeststdPK();
		speststdPK.setDeptId(costCenterNo);
		speststdPK.setEstimateNo(applicationNo);
		
		SpeststdEjb speststdEJB = new SpeststdEjb(getSessionKey("region"));
		Speststd speststd = speststdEJB.findById(speststdPK);
		
		
		 
		EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
		List<MaterialGrid> selectMatList = estimateEjb.getMaterialGrid(applicationNo, costCenterNo);
		  
		
		
		ApplicationEjb applicationEJB = new ApplicationEjb(getSessionKey("region"));
		Application application = applicationEJB.findByApplicationNo(applicationNo);
		
		ApplicantEjb applicantEJB = new ApplicantEjb(getSessionKey("region"));
		Applicant applicant = applicantEJB.findById(application.getIdNo());
		String   applicantName = applicant.getFirstName()+" "+applicant.getLastName();
		PrintEstimate pe = new PrintEstimate (pcesthtt,speststd,selectMatList,applicant);
		printMsg = pe.Print();
		
		return "print";
	}
	
		

	//action invoke when Find button is clicked
	public String viewEstimateDetails()
	{
		try
		{
			setLoggedData();
			setPath(viewPath);
			clearFields();
			
			PcesthttEjb pcesthttEjb = new PcesthttEjb(getSessionKey("region"));
			Pcesthtt pcesthtt = pcesthttEjb.findByEstimationNo(applicationNo,costCenterNo);
			if(pcesthtt==null)
			{
				setErrorMessage("Estimate does not exist.");
				this.isViewApplicant = null;
				return ERROR;
			}
			else
			{
				Long estStatus = pcesthtt.getStatus();
				if(estStatus.intValue()==30)
				{
					ApplicationEjb applicationEJB = new ApplicationEjb(getSessionKey("region"));
					Application application = applicationEJB.findByApplicationNo(applicationNo);
					setApplicantData(application);
					setWiringData(application);
					setEstimateData(pcesthtt);
					this.isViewApplicant = "Y";	
					return SUCCESS;
				}
				else
				{
					setErrorMessage("Sorry! You cannot print this estimate.");
					this.isViewApplicant = null;
					return ERROR;
				}
			}
		}
		catch(Exception e)
		{
			setErrorMessage("Unexpected Error! Please retry.");
			this.isViewApplicant = null;
			this.isViewApplicant = null;
			e.printStackTrace();
			return ERROR;
		}
	}
	
	//action invoke when Clear button is clicked
	public String clear()
	{
		this.applicationNo = "";
		setLoggedData();
		setPath(viewPath);
		clearFields();
		return SUCCESS;
	}
	
	//action invoke when Close button is clicked
	public String close() {
		setLoggedData();
		return "close";
	}
	
	private void clearFields()
	{
		this.isViewApplicant = null;
		this.errorMessage = "";
		
		this.applicantName = "";
		this.address = "";
		this.neighboursAccNo = "";
		this.assessmentNo = "";
		this.description = "";
		this.applicantDate = "";
		this.phase = "";
		this.connectionType = "";
		this.phaseDb = "";
		this.connectionTypeDb = "";
		this.tariffCode = "";
		this.tariffCategory = "";
		
		this.isLoopService = "";
		this.categoryCode = "";
		this.lineLength = "";
		this.loopLength = "";
		this.lb = "";
		this.spans = "";
		this.poleNo = "";
		this.stayNo = "";
		this.strutsNo = "";
		this.sinNo = "";
		this.transColor = "";
		this.distanceToServicePlace = "";
		this.estimationType = "";
		this.jobDesc = "";
		
		this.fixedCost = "";
		this.variableCost = "";
		this.subTotal = "";
		this.otherCost = "";
		this.convCost = "";
		this.taxAmount = "";
		this.secDeposit = "";
		this.totalCost = "";
		
		clearSessionVariables();
	}
	
	private void clearSessionVariables()
	{
		sessionMap.remove("IsNewEstimate");
		sessionMap.remove("SelectMaterialList");
		sessionMap.remove("SavedMaterialList");
		sessionMap.remove("IsDefaultMasterAdd");
	}

	
	//set the application data to the form fields
	private void setApplicantData(Application application)
	{
		ApplicantEjb applicantEJB = new ApplicantEjb(getSessionKey("region"));
		Applicant applicant = applicantEJB.findById(application.getIdNo());
		applicantName = applicant.getFirstName()+" "+applicant.getLastName();
				
		java.util.Date appDate = application.getSubmitDate();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		applicantDate = sdf.format(appDate);
		description = application.getDescription();
		
		
	}
	
	
	//set wiring details to the form fields
	private void setWiringData(Application application)
	{
		WiringLandDetailEjb wlEJB = new WiringLandDetailEjb(getSessionKey("region"));
		WiringLandDetailPK wlPK = new WiringLandDetailPK();
		wlPK.setApplicationId(application.getId().getApplicationId());
		wlPK.setDeptId(costCenterNo);
		WiringLandDetail wld = wlEJB.findByAppId(wlPK);
		assessmentNo = wld.getAssessmentNo();
		neighboursAccNo = wld.getNeighboursAccNo();
		phaseInt = wld.getPhase().intValue();
		phaseDb = Integer.toString(phaseInt);
		System.out.println("phaseDb "+phaseDb);
		if(phaseInt==1)
			phase = "SINGLE";
		else
			phase = "THREE";
		connectionTypeInt = wld.getConnectionType().intValue();
		connectionType = wld.getConnectionType().toString()+" A";
		connectionTypeDb = Integer.toString(connectionTypeInt);
		tariffCode = wld.getTariffCode();
		tariffCategoryCode = wld.getTariffCatCode();
		tariffCategory = wld.getTariffCatCode();
		//isLoopService = wld.getIsLoopService();
		address = wld.getServiceStreetAddress()+", "+wld.getServiceSuburb()+", "+wld.getServiceCity();
	}
	
	//set estimate details to the form fields
	private void setEstimateData(Pcesthtt pcesthtt)
	{
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		if(pcesthtt==null)
		{
			sessionMap.put("IsNewEstimate", "Y");
			SpcstngmEjb spcstngmEjb = new SpcstngmEjb(getSessionKey("region"));
			SpcstngmPK spcstngmPK = new SpcstngmPK();
			spcstngmPK.setConnectionType(connectionTypeInt);
			spcstngmPK.setPhase(phaseInt);
			spcstngmPK.setTariffCatCode(tariffCategoryCode);
			spcstngmPK.setDeptId(costCenterNo);
			
			fixedCost = "0.00";
			variableCost = "0.00";
			subTotal = fixedCost;
			otherCost = "0.00";
			convCost = "0.00";
			taxAmount = "0.00";
			wireMeterPrice = "0.00";
			secDeposit = "0.00";
			totalCost = "0.00";
			
			Spcstngm spcstngm = spcstngmEjb.findById(spcstngmPK);
			if(spcstngm!=null)
			{
				fixedCost = nf.format(spcstngm.getFixedCost());
				secDeposit = nf.format(spcstngm.getSecurityDeposit());
				wireMeterPrice = spcstngm.getWireMeterPrice().toString();
				float tot = spcstngm.getFixedCost().floatValue()+spcstngm.getSecurityDeposit().floatValue();
				totalCost = nf.format(tot);
			}
		}
		else
		{
			SpcstngmEjb spcstngmEjb = new SpcstngmEjb(getSessionKey("region"));
			SpcstngmPK spcstngmPK = new SpcstngmPK();
			spcstngmPK.setConnectionType(connectionTypeInt);
			spcstngmPK.setPhase(phaseInt);
			spcstngmPK.setTariffCatCode(tariffCategoryCode);
			spcstngmPK.setDeptId(costCenterNo);
			
			Spcstngm spcstngm = spcstngmEjb.findById(spcstngmPK);
			
			if(spcstngm!=null)
			wireMeterPrice = spcstngm.getWireMeterPrice().toString();
			
			sessionMap.put("IsNewEstimate", "N");
			jobDesc = pcesthtt.getDescr();
			categoryCode = pcesthtt.getCatCd();
			
			SpeststdPK speststdPK = new SpeststdPK();
			speststdPK.setDeptId(costCenterNo);
			speststdPK.setEstimateNo(applicationNo);
			
			SpeststdEjb speststdEJB = new SpeststdEjb(getSessionKey("region"));
			Speststd speststd = speststdEJB.findById(speststdPK);
			
			//connectorType = speststd.getConnectorType();
			convCost = nf.format(speststd.getConversionCost());
			fixedCost = nf.format(speststd.getFixedCost());
			lb = speststd.getLb().toString();
			lineLength = speststd.getLineLength().toString();
			loopLength = speststd.getLoopLength().toString();
			poleNo = speststd.getPoleNo().toString();
			//poleSize = speststd.getPoleSize();
			//poleType = speststd.getPoleType();
			secDeposit = nf.format(speststd.getSecurityDeposit());
			distanceToServicePlace = speststd.getServiceDistance().toString();
			sinNo = speststd.getSinNo();
			spans = speststd.getSpan().toString();
			stayNo = speststd.getStayNo().toString();
			strutsNo = speststd.getStrutNo().toString();
			variableCost = nf.format(speststd.getVariableCost());
			otherCost = nf.format(speststd.getOtherCost());
			transColor = speststd.getTransformColor();
			totalMatCost = nf.format(pcesthtt.getStdCost());
			taxAmount = "0.00";
			/*rda = speststd.getRda();
			mc = speststd.getMc();
			police = speststd.getPolice();
			psd = speststd.getPsd();*/
			permission = "";
			/*if(rda!=null && rda.equals("Y"))
				permission = "RDA";
			if(mc!=null && mc.equals("Y"))
			{
				if(permission.length()>0)
					permission = permission+", ";
				permission = permission+"Municipal Council";
			}
			if(police!=null && police.equals("Y"))
			{
				if(permission.length()>0)
					permission = permission+", ";
				permission = permission+"Police";
			}
			if(psd!=null && psd.equals("Y"))
			{
				if(permission.length()>0)
					permission = permission+", ";
				permission = permission+"PSD";
			}*/
			wiringType = speststd.getWiringType();
			if(wiringType.equals("UG"))
				wiringType = "Under Ground";
			else if(wiringType.equals("OH"))
				wiringType = "Over Head";
			else
				wiringType = "";
			if(pcesthtt.getFundSource()!=null)
				fundSourceID = pcesthtt.getFundSource()+" - "+pcesthtt.getFundId();
			subTotal = nf.format(new Float(speststd.getFixedCost().floatValue()+speststd.getVariableCost().floatValue()));
			totalCost = nf.format(new Float(speststd.getFixedCost().floatValue()+speststd.getVariableCost().floatValue()+speststd.getSecurityDeposit().floatValue()+speststd.getConversionCost().floatValue()+speststd.getOtherCost().floatValue()));
			
			EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
			selectMatList = estimateEjb.getMaterialGrid(applicationNo, costCenterNo);
			LinkedHashMap matMap = new LinkedHashMap();
			Iterator it = selectMatList.iterator();
			ArrayList savedMat = new ArrayList();
			while(it.hasNext())
			{
				MaterialGrid grid = (MaterialGrid)it.next();
				matMap.put(grid.getResCd().trim(), grid);
				savedMat.add(grid.getResCd().trim());
			}
			sessionMap.put("SelectMaterialList",matMap);
			sessionMap.put("SavedMaterialList",savedMat);
		}
	}
	
	
	public void setLoggedData() 
	{
		log.info( getSession());
		setLoggedInUserId(getSessionKey("userName"));
		setCostCenterNo(getSessionKey("costCenterNo"));
		setCostCenterName(getSessionKey("costCenterName"));
	}
	
	
	/************* Getters and Setters ***************/
	
	public String getSessionKey(String key) 
	{
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
	
	public String getCostCenterNo() {
		return costCenterNo;
	}
	public void setCostCenterNo(String costCenterNo) {
		this.costCenterNo = costCenterNo;
	}
	public String getCostCenterName() {
		return costCenterName;
	}
	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAssessmentNo() {
		return assessmentNo;
	}
	public void setAssessmentNo(String assessmentNo) {
		this.assessmentNo = assessmentNo;
	}
	public String getNeighboursAccNo() {
		return neighboursAccNo;
	}
	public void setNeighboursAccNo(String neighboursAccNo) {
		this.neighboursAccNo = neighboursAccNo;
	}
	public String getSinNo() {
		return sinNo;
	}
	public void setSinNo(String sinNo) {
		this.sinNo = sinNo;
	}
	public String getTariffCode() {
		return tariffCode;
	}
	public void setTariffCode(String tariffCode) {
		this.tariffCode = tariffCode;
	}
	
	public String getLineLength() {
		return lineLength;
	}
	public void setLineLength(String lineLength) {
		this.lineLength = lineLength;
	}
	public String getLoopLength() {
		return loopLength;
	}
	public void setLoopLength(String loopLength) {
		this.loopLength = loopLength;
	}
	public String getLb() {
		return lb;
	}
	public void setLb(String lb) {
		this.lb = lb;
	}
	public String getSpans() {
		return spans;
	}
	public void setSpans(String spans) {
		this.spans = spans;
	}
	public String getEstimationType() {
		return estimationType;
	}
	public void setEstimationType(String estimationType) {
		this.estimationType = estimationType;
	}
	public String getDistanceToServicePlace() {
		return distanceToServicePlace;
	}
	public void setDistanceToServicePlace(String distanceToServicePlace) {
		this.distanceToServicePlace = distanceToServicePlace;
	}
		
	
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
	public String getConnectionType() {
		return connectionType;
	}
	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}
	
	
	public String getPhaseDb() {
		return phaseDb;
	}

	public void setPhaseDb(String phaseDb) {
		this.phaseDb = phaseDb;
	}

	public String getConnectionTypeDb() {
		return connectionTypeDb;
	}

	public void setConnectionTypeDb(String connectionTypeDb) {
		this.connectionTypeDb = connectionTypeDb;
	}

	public String getIsLoopService() {
		return isLoopService;
	}
	public void setIsLoopService(String isLoopService) {
		this.isLoopService = isLoopService;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public Map<String, Object> getSession() {
		return sessionMap;
	}
	
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap=sessionMap;
		
	}

	public Map<String, String[]> getParameters() {
		return parameters;
	}
	
	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters=parameters;
		
	}
	
		
	public String[] getChkSelMatCode() {
		return chkSelMatCode;
	}

	public void setChkSelMatCode(String[] chkSelMatCode) {
		this.chkSelMatCode = chkSelMatCode;
	}

	
	public String[] getTxtSelQty() {
		return txtSelQty;
	}

	public void setTxtSelQty(String[] txtSelQty) {
		this.txtSelQty = txtSelQty;
	}

	
	public String getTotalMatCost() {
		return totalMatCost;
	}

	public void setTotalMatCost(String totalMatCost) {
		this.totalMatCost = totalMatCost;
	}

	
	public String[] getTxtSelMatCost() {
		return txtSelMatCost;
	}

	public void setTxtSelMatCost(String[] txtSelMatCost) {
		this.txtSelMatCost = txtSelMatCost;
	}

	
	public void setServletRequest(HttpServletRequest request){
		this.request = request;
	}

	public HttpServletRequest getServletRequest(){
		return request;
	}

	public void setServletResponse(HttpServletResponse response){
		this.response = response;
	}

	public HttpServletResponse getServletResponse(){
		return response;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getApplicantDate() {
		return applicantDate;
	}

	public void setApplicantDate(String applicantDate) {
		this.applicantDate = applicantDate;
	}

	
	public String getFixedCost() {
		return fixedCost;
	}

	public void setFixedCost(String fixedCost) {
		this.fixedCost = fixedCost;
	}

	
	public String getVariableCost() {
		return variableCost;
	}

	public void setVariableCost(String variableCost) {
		this.variableCost = variableCost;
	}

	public String getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}

	public String getOtherCost() {
		return otherCost;
	}

	public void setOtherCost(String otherCost) {
		this.otherCost = otherCost;
	}

	public String getConvCost() {
		return convCost;
	}

	public void setConvCost(String convCost) {
		this.convCost = convCost;
	}

	public String getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}

	public String getSecDeposit() {
		return secDeposit;
	}

	public void setSecDeposit(String secDeposit) {
		this.secDeposit = secDeposit;
	}

	public String getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}

	
	public String getPoleNo() {
		return poleNo;
	}

	public void setPoleNo(String poleNo) {
		this.poleNo = poleNo;
	}

	public String getStayNo() {
		return stayNo;
	}

	public void setStayNo(String stayNo) {
		this.stayNo = stayNo;
	}

	public String getStrutsNo() {
		return strutsNo;
	}

	public void setStrutsNo(String strutsNo) {
		this.strutsNo = strutsNo;
	}

	public String getTransColor() {
		return transColor;
	}

	public void setTransColor(String transColor) {
		this.transColor = transColor;
	}

	public String getTariffCategory() {
		return tariffCategory;
	}

	public void setTariffCategory(String tariffCategory) {
		this.tariffCategory = tariffCategory;
	}

	
	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	
	public String getIsViewApplicant() {
		return isViewApplicant;
	}

	public void setIsViewApplicant(String isViewApplicant) {
		this.isViewApplicant = isViewApplicant;
	}

	
	public String getWireMeterPrice() {
		return wireMeterPrice;
	}

	public void setWireMeterPrice(String wireMeterPrice) {
		this.wireMeterPrice = wireMeterPrice;
	}

	public String getIsMatListPopUp() {
		return isMatListPopUp;
	}

	public void setIsMatListPopUp(String isMatListPopUp) {
		this.isMatListPopUp = isMatListPopUp;
	}
	
	
	public String getIsMatListClose() {
		return isMatListClose;
	}

	public void setIsMatListClose(String isMatListClose) {
		this.isMatListClose = isMatListClose;
	}

	public List getSelectMatList() {
		return selectMatList;
	}

	public void setSelectMatList(List selectMatList) {
		this.selectMatList = selectMatList;
	}

	public String[] getChkMatCode() {
		return chkMatCode;
	}

	public void setChkMatCode(String[] chkMatCode) {
		this.chkMatCode = chkMatCode;
	}
/*
	public String getRda() {
		return rda;
	}

	public void setRda(String rda) {
		this.rda = rda;
	}

	public String getPsd() {
		return psd;
	}

	public void setPsd(String psd) {
		this.psd = psd;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getPolice() {
		return police;
	}

	public void setPolice(String police) {
		this.police = police;
	}
*/
	public String getWiringType() {
		return wiringType;
	}

	public void setWiringType(String wiringType) {
		this.wiringType = wiringType;
	}


	public String getPermission() {
		return permission;
	}


	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getPrintMsg() {
		return printMsg;
	}

	public void setPrintMsg(String printMsg) {
		this.printMsg = printMsg;
	}

	public String getFundSourceID() {
		return fundSourceID;
	}

	public void setFundSourceID(String fundSourceID) {
		this.fundSourceID = fundSourceID;
	}

	

}//End of class
