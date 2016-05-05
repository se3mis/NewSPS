package fa.web;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;

import masters.model.NameValueList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import util.common.ApplicationSubType;
import util.common.EstimateStatus;
import util.common.Format;
import util.common.PivPrefixType;
import util.common.TemporaryConnValue;

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
import estimate.model.Approval;
import estimate.model.FundSource;
import estimate.model.LabourGrid;

import estimate.model.Spestlab;
import estimate.model.SpestlabPK;
import estimate.model.Speststd;
import estimate.model.SpeststdPK;

import estimate.model.Spratesm;
import estimate.model.SpratesmPK;

import estimate.service.SpeststdEjb;
import estimate.model.Pcestdtt;
import estimate.model.PcestdttPK;
import estimate.service.PcestdttEjb;
import estimate.service.EstimateEjb;
import estimate.service.PcjbtypmEjb;

import estimate.model.MaterialGrid;
import fa.model.FAMainCategoryGrid;
import fa.model.FAMatFeatureGrid;
import fa.model.FAMaterialGrid;

import com.opensymphony.xwork2.ActionSupport;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import job.model.Pcesthmt;
import job.service.JobEjb;
import job.service.PcesthmtEjb;
import job.web.ListObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import reports.web.report;

@SuppressWarnings("serial")
public class PlantEquipmentBulk extends ActionSupport implements SessionAware, ParameterAware,ServletResponseAware,ServletRequestAware{
	private static final Log log = LogFactory.getLog(PlantEquipment.class);
	private static final String viewPath="Estimate>";
	
	NumberFormat nf = NumberFormat.getInstance();
    
    
	private HttpServletRequest request;
	private HttpServletResponse response;
	  
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private String errorMessage;
	private String successMessage;
	private String infoMessage;
	
	//session variables
	private Map<String, Object> sessionMap;
	String loggedUser;
	String loggedInUserLevel; //



	//JSP Fields
	private String costCenterNo;
	private String costCenterName;
	private String applicationNo;
	private String estNoPrefix;
	private String estNoSeq;
	private List<String> estNoList;
	private String applicantName;
	private String companyName;
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
	private String copyEstimateNo;
	private String appSubType;
	private String appSubTypeDB;
	private String duration;
	private double durationDB;
	private String conductorType;
	private String conductorLength;
	private String serviceLength;
	private String conversionLength;
	private String conversionLength2P;
	private String circuitLength;
	private String estimatedDate;
	private String city;
	private String suburb;
	private String streetAddress;
	private String postalCode;
	private String applicationId;
	private String idNo;
		
	private String isLoopService;
	private String categoryCode;
	private String lineLength;
	private String loopLength;
	private String isLoopOrCable;
	private String cableType;
	private String cableTypeDis;
	private List<ListObject> cableTypeList;
	private String spans;
	private String poleNo;
	private String stayNo;
	private String strutsNo;
	private String sinNo;
	private String transColor;
	private String distanceToServicePlace;
	private String estimationType;
	private String jobDesc;
	private String rejectReason;
	private String wiringType;
	private List<FundSource> fundSourceList;
	private String fundSourceID;
	private List<String> categoryCodeList;
	private String estimateStatus;
	private String estimateStatusDesc;
	private String isStandardVC;
		
	private String fixedCost;
	private String variableCost;
	private String subTotal;
	private String otherCost;
	private String otherLabourCost;
	private String capitalCost;
	private String convCost;
	private String taxAmount;
	private String secDeposit;
	private String addlDeposit;
	private String totalCost;
	
	
	// Hidden Fields
	private String isViewApplicant;
	private String isViewOnly;
	private String isModify;
	private String isApprove;
	private String isUndoReject;
	private String isPrint;
	private String isRoughEstimate;
	private String estimateExist = null;
	private String firstYear = Double.toString(TemporaryConnValue.CON3_FIRST_YEAR);
	private String nextYear = Double.toString(TemporaryConnValue.CON3_NEXT_YEARS);
	private String isLineLenEntered;
	private String region;
	
	private String labourRate;
	private String transportRate;
	private String overheadRate;
	
	/****************************/
	// Local variables
	private int connectionTypeInt;
	private int phaseInt;
	
	
		
	//Material List variables
	
	private String[] txtSelQty;
	private String[] txtSelMatCost;
	private String[] txtCusQty;
	private String[] txtSelCusCost;
	private String totalDetailCost;
	
	//Labour List variables
	private String[] txtSelLabQty;
	private String[] txtSelLabCost;
	private String totalLabCost;
	private String[] chkSelLabCode;
	private String isRemoveLabClicked;
	private String[] txtSelLabHrs;
	private String totalLabHrs;
	
	//other Fields
	private String path;
	private String state;
	private InputStream fileInputStream;
	
	private Collection<FAMaterialGrid> categorisedMatList;
	private Collection<FAMaterialGrid> unCategorisedMatList;
	private List<NameValueList> faSubCatList;
	private List<NameValueList> faManCatList;
	private Collection<FAMainCategoryGrid> faManCatFeatList;
	private Collection<FAMatFeatureGrid> faMatFeatureList;
	private String[] manCatFeatValue;
	private String[] matFeatValue;
	
	private String moveMatCode;
	private String moveManCatCode;
	private String moveQty;
	private String removeMatCode;
	private String removeManCatCode;
	private String isVerify;
	private String isTransfer;
	
	public String execute()
	{
		setErrorMessage(null);
		setSuccessMessage(null);
		setInfoMessage(null);
		setLoggedData();
		setState("newEstimate");
		
		setPath(viewPath);
		this.isViewOnly = null;
		if(applicationNo!=null && applicationNo.length()>0)
		{
			
			clearSessionVariables();
			clearFields();
			
			viewApplicantDetails();
			
			
			isRemoveLabClicked = null; 
			
		}
		else
		{
			clearSessionVariables();
			clearFields();
			
			this.isModify = null;
			this.isApprove = null;
			this.isPrint = null;
			this.isRoughEstimate = null;
		}
		
		setFormData();
		
		return SUCCESS;
	}
	
	public String verify()
	{
		setErrorMessage(null);
		setSuccessMessage(null);
		setInfoMessage(null);
		setLoggedData();
		setState("newEstimate");
		setFormData();
		setPath(viewPath);
		isVerify = "true";
		isTransfer = null;
		
		return SUCCESS;
	}
	
	public String transfer()
	{
		setErrorMessage(null);
		setSuccessMessage(null);
		setInfoMessage(null);
		setLoggedData();
		setState("newEstimate");
		setFormData();
		setPath(viewPath);
		isVerify = null;
		isTransfer = "true";
		return SUCCESS;
	}
	
	
	public String displayMaterialList() throws Exception 
	{
		try
		{
			
			setMatMasterData();
			if((isTransfer!=null && isTransfer.equals("true"))||(isVerify!=null && isVerify.equals("true")))
			{
				SortedMap matUnCatMap  = (SortedMap)sessionMap.get("UnCategorisedMatList");
				SortedMap matCatMap  = (SortedMap)sessionMap.get("CategorisedMatList");
				unCategorisedMatList = matUnCatMap.values();
				categorisedMatList = matCatMap.values();
			}
			else
			{
				FAMaterialGrid grid1 = 
					new FAMaterialGrid("MAT-COST","F0250",new BigDecimal(1),
							"TRANSFORMER OUTDOOR 3 PHASE 11KV/LT - 160 KVA","NO.",
							null,new BigDecimal(820500),new BigDecimal(1),
						new BigDecimal(820500),null,null,null,new BigDecimal(1),"SUBSTN","Substation","TRANS","Transformer");
				FAMaterialGrid grid2 = 
					new FAMaterialGrid("MAT-COST","A0430",new BigDecimal(1),
							"POLES - CONCRETE PRE-STRESSED 11.0 M 500KG","NO.",
							null,new BigDecimal(34330),new BigDecimal(1),
						new BigDecimal(34330),null,null,null,new BigDecimal(1),"SUBSTN","Substation","POLE","Pole");
				FAMaterialGrid grid3 = 
					new FAMaterialGrid("MAT-COST","D0415",new BigDecimal(1),
							"CONDUCTOR, COPPER (NO.4) - 6MM","Kg.",
							null,new BigDecimal(1495),new BigDecimal(0.5),
						new BigDecimal(747.5),null,null,null,new BigDecimal(0.5),"OHHTLINE","Overhead Transmission Lines","CONDUCT","Conductor");
				FAMaterialGrid grid4  = 
					new FAMaterialGrid("MAT-COST","D0605",new BigDecimal(1),
							"WIRE BINDING, ALUMINIUM (NO.9) 3.6 MM","Kg.",
							null,new BigDecimal(570),new BigDecimal(1),
						new BigDecimal(570),null,null,null,new BigDecimal(0.5),"OHHTLINE","Overhead Transmission Lines","CONDUCT","Conductor");
				//LinkedHashMap selectMatCodeMap = (LinkedHashMap)sessionMap.get("SelectMaterialList");
				//categorisedMatList = selectMatCodeMap.values();
				categorisedMatList = new ArrayList<FAMaterialGrid>();
				categorisedMatList.add(grid3);
				categorisedMatList.add(grid4);
				categorisedMatList.add(grid1);
				categorisedMatList.add(grid2);
				
				
				FAMaterialGrid grid10 = 
					new FAMaterialGrid("MAT-COST","B0210",new BigDecimal(1),
							"BRACKETS D -G.I.- W/O INSULATORS & BOLTS 110 X 92MM","NO.",
							null,new BigDecimal(100),new BigDecimal(15),
						new BigDecimal(1500),null,null,null,new BigDecimal(15),null,null,"ACCESSORY","Accessory");
				FAMaterialGrid grid20 = 
					new FAMaterialGrid("MAT-COST","B0740",new BigDecimal(1),
							"BOLTS  NUTS - G.I. 50 X 16 MM","NO.",
							null,new BigDecimal(50),new BigDecimal(50),
						new BigDecimal(2500),null,null,null,new BigDecimal(50),null,null,"ACCESSORY","Accessory");
				FAMaterialGrid grid30 = 
					new FAMaterialGrid("MAT-COST","C0110",new BigDecimal(1),
							"INSULATOR - LT 90 X 75MM","NO.",
							null,new BigDecimal(42.5),new BigDecimal(80),
						new BigDecimal(3400),null,null,null,new BigDecimal(80),null,null,"ACCESSORY","Accessory");
				FAMaterialGrid grid40  = 
					new FAMaterialGrid("MAT-COST","H0707",new BigDecimal(1),
							"CIRCUIT BREAKER MINIATURE SP & N 30A ( WITHOUT ENCLOSURE)","NO.",
							null,new BigDecimal(535),new BigDecimal(60),
						new BigDecimal(32100),null,null,null,new BigDecimal(60),null,null,"ACCESSORY","Accessory");
				//LinkedHashMap selectMatCodeMap = (LinkedHashMap)sessionMap.get("SelectMaterialList");
				//categorisedMatList = selectMatCodeMap.values();
				unCategorisedMatList = new ArrayList<FAMaterialGrid>();
				unCategorisedMatList.add(grid10);
				unCategorisedMatList.add(grid20);
				unCategorisedMatList.add(grid30);
				unCategorisedMatList.add(grid40);
			
				SortedMap matCatMap = new TreeMap();
				//LinkedHashMap matCatMap = new LinkedHashMap();
				matCatMap.put("SUBSTN###F0250",grid1);
				matCatMap.put("SUBSTN###A0430",grid2);
				matCatMap.put("OHHTLINE###D0415",grid3);
				matCatMap.put("OHHTLINE###D0605",grid4);
				
				SortedMap matUnCatMap = new TreeMap();
				//LinkedHashMap matUnCatMap = new LinkedHashMap();
				matUnCatMap.put("B0210",grid10);
				matUnCatMap.put("B0740",grid20);
				matUnCatMap.put("C0110",grid30);
				matUnCatMap.put("H0707",grid40);
				
				sessionMap.put("CategorisedMatList",matCatMap);
				sessionMap.put("UnCategorisedMatList",matUnCatMap);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			return SUCCESS;
	}
	
	public String moveMaterials()
	{
		try
		{
			//LinkedHashMap matCatMap  = (LinkedHashMap)sessionMap.get("CategorisedMatList");
			//LinkedHashMap matUnCatMap  = (LinkedHashMap)sessionMap.get("UnCategorisedMatList");
			setMatMasterData();
			SortedMap matCatMap  = (SortedMap)sessionMap.get("CategorisedMatList");
			SortedMap matUnCatMap  = (SortedMap)sessionMap.get("UnCategorisedMatList");
			
			FAMaterialGrid tmpCatGrid = new FAMaterialGrid();
			FAMaterialGrid tmpUnCatGrid = (FAMaterialGrid)matUnCatMap.get(moveMatCode);
			
			double commitQty = tmpUnCatGrid.getCommitedQty().doubleValue();
			double movQty = Double.parseDouble(moveQty);
			double balQty = commitQty - movQty;
			double up = tmpUnCatGrid.getUnitPrice().doubleValue();
			
			tmpCatGrid.setFaManCatCode(moveManCatCode);
			tmpCatGrid.setCommitedQty(new BigDecimal(movQty));
			tmpCatGrid.setCommitedCost(new BigDecimal(movQty*up));
			tmpCatGrid.setFaManCatDesc(getManCatDesc(moveManCatCode));
			tmpCatGrid.setFaSubCatCode(tmpUnCatGrid.getFaSubCatCode());
			tmpCatGrid.setFaSubCatDesc(tmpUnCatGrid.getFaSubCatDesc());
			tmpCatGrid.setResCat(tmpUnCatGrid.getResCat());
			tmpCatGrid.setResCd(tmpUnCatGrid.getResCd());
			tmpCatGrid.setResType(tmpUnCatGrid.getResType());
			tmpCatGrid.setMatNm(tmpUnCatGrid.getMatNm());
			tmpCatGrid.setUom(tmpUnCatGrid.getUom());
			tmpCatGrid.setUnitPrice(tmpUnCatGrid.getUnitPrice());
			
			FAMaterialGrid existGrid = (FAMaterialGrid)matCatMap.get(moveManCatCode+"###"+moveMatCode);
			if(existGrid!=null)
			{
				double existQty = existGrid.getCommitedQty().doubleValue();
				tmpCatGrid.setCommitedQty(new BigDecimal(movQty+existQty
						));
			}
			matCatMap.put(moveManCatCode+"###"+moveMatCode, tmpCatGrid);
			
			if(balQty==0)
			{
				matUnCatMap.remove(moveMatCode);
			}
			else
			{
				tmpUnCatGrid.setCommitedQty(new BigDecimal(balQty));
				tmpUnCatGrid.setCommitedCost(new BigDecimal(balQty*up));
				matUnCatMap.put(moveMatCode, tmpUnCatGrid);
			}
				
			unCategorisedMatList = matUnCatMap.values();
			categorisedMatList = matCatMap.values();
			
			sessionMap.put("CategorisedMatList",matCatMap);
			sessionMap.put("UnCategorisedMatList",matUnCatMap);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String removeMaterials()
	{
		try
		{
			System.out.println("removeMaterials");
			setMatMasterData();
			SortedMap matCatMap  = (SortedMap)sessionMap.get("CategorisedMatList");
			SortedMap matUnCatMap  = (SortedMap)sessionMap.get("UnCategorisedMatList");
			
			FAMaterialGrid tmpCatGrid = (FAMaterialGrid)matCatMap.get(removeManCatCode+"###"+removeMatCode);
			FAMaterialGrid tmpUnCatGrid = (FAMaterialGrid)matUnCatMap.get(removeMatCode);
			double newQty = tmpCatGrid.getCommitedQty().doubleValue();
			if(tmpUnCatGrid!=null)
			{
				newQty = newQty+tmpUnCatGrid.getCommitedQty().doubleValue();
			}
			else
			{
				tmpUnCatGrid = new FAMaterialGrid();
				tmpUnCatGrid.setFaSubCatCode(tmpCatGrid.getFaSubCatCode());
				tmpUnCatGrid.setFaSubCatDesc(tmpCatGrid.getFaSubCatDesc());
				tmpUnCatGrid.setResCat(tmpCatGrid.getResCat());
				tmpUnCatGrid.setResCd(tmpCatGrid.getResCd());
				tmpUnCatGrid.setResType(tmpCatGrid.getResType());
				tmpUnCatGrid.setMatNm(tmpCatGrid.getMatNm());
				tmpUnCatGrid.setUom(tmpCatGrid.getUom());
				tmpUnCatGrid.setUnitPrice(tmpCatGrid.getUnitPrice());
			}
			double newCost = newQty*tmpCatGrid.getUnitPrice().doubleValue();
			tmpUnCatGrid.setCommitedQty(new BigDecimal(newQty));
			tmpUnCatGrid.setCommitedCost(new BigDecimal(newCost));
			
			matUnCatMap.put(removeMatCode, tmpUnCatGrid);
			matCatMap.remove(removeManCatCode+"###"+removeMatCode);
			
			unCategorisedMatList = matUnCatMap.values();
			categorisedMatList = matCatMap.values();
			
			sessionMap.put("CategorisedMatList",matCatMap);
			sessionMap.put("UnCategorisedMatList",matUnCatMap);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String approve()
	{
		setErrorMessage(null);
		setSuccessMessage(null);
		setInfoMessage(null);
		setLoggedData();
		setState("newEstimate");
		clearSessionVariables();
		clearFields();
		this.isViewOnly = null;
		this.isModify = null;
		this.isUndoReject = null;
		this.isApprove = "true";
		this.isPrint = null;
		this.isRoughEstimate = null;
		setPath(viewPath);
		setFormData();
		setInfoFromMainMenu();
		return SUCCESS;
	}
	
	
	public String roughEstimate()
	{
		setErrorMessage(null);
		setSuccessMessage(null);
		setInfoMessage(null);
		setLoggedData();
		setState("newEstimate");
		clearSessionVariables();
		clearFields();
		this.isViewOnly = null;
		this.isModify = null;
		this.isUndoReject = null;
		this.isApprove = null;
		this.isPrint = null;
		this.isRoughEstimate = "true";
		setPath(viewPath);
		setFormData();
		setInfoFromMainMenu();
		return SUCCESS;
	}
	
	public void setInfoFromMainMenu(){
		//Dhanusha
		HttpServletRequest request = ServletActionContext.getRequest();
		String estimateNo=request.getParameter("estNo");
		String costCen=request.getParameter("costCen");
		if(estimateNo!=null && estimateNo.length()>0 && costCen!=null && costCen.length()>0){
			setApplicationNo(estimateNo);
			setCostCenterNo(costCen);
			viewApplicantDetails();
			
		}//Dhanusha
	}
	
	public void setUndoRejectFromMainMenu(){
		//Dhanusha
		HttpServletRequest request = ServletActionContext.getRequest();
		String estimateNo=request.getParameter("estNo");
		String costCen=request.getParameter("costCen");
		if(estimateNo!=null && estimateNo.length()>0 && costCen!=null && costCen.length()>0){
			setApplicationNo(estimateNo);
			setCostCenterNo(costCen);
			viewApplicantDetails();
			
		}//Dhanusha
	}
	
	public String viewOnly()
	{
		clearSessionVariables();
		setErrorMessage(null);
		setSuccessMessage(null);
		setInfoMessage(null);
		setLoggedData();
		this.isViewOnly = "true";
		this.isModify = null;
		this.isUndoReject = null;
		this.isApprove = null;
		this.isPrint = null;
		this.isRoughEstimate = null;
		setPath(viewPath);
		setFormData();
		Calendar cal = Calendar.getInstance();
		int curYear = cal.get(Calendar.YEAR);
		applicationNo = costCenterNo+"/"+PivPrefixType.EST_NEW_CONN+"/"+String.valueOf(curYear).substring(2, 4)+"/";
		return SUCCESS;
	}
	
	public String modify()
	{
		clearSessionVariables();
		setErrorMessage(null);
		setSuccessMessage(null);
		setInfoMessage(null);
		setLoggedData();
		this.isViewOnly = null;
		this.isModify = "true";
		this.isUndoReject = null;
		this.isApprove = null;
		this.isPrint = null;
		this.isRoughEstimate = null;
		setPath(viewPath);
		
		setFormData();
		return SUCCESS;
	}
	
	public String undoReject()
	{
		clearSessionVariables();
		setErrorMessage(null);
		setSuccessMessage(null);
		setInfoMessage(null);
		setLoggedData();
		this.isViewOnly = null;
		this.isModify = null;
		this.isUndoReject = "true";
		this.isApprove = null;
		this.isPrint = null;
		this.isRoughEstimate = null;
		setPath(viewPath);
		setUndoRejectFromMainMenu();
		setFormData();
		return SUCCESS;
	}
	
	
	public String sendToapproval()
	{
		try
		{
			setErrorMessage(null);
			setSuccessMessage(null);
			setInfoMessage(null);
			setLoggedData();
			setFormData();
			setPath(viewPath);
			
			nf.setGroupingUsed(true);
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			
			PcesthttEjb pcesthttEjb = new PcesthttEjb(getSessionKey("region"));
			//pcesthttEjb.changeStatusPcesthtt(applicationNo, costCenterNo, new BigDecimal(EstimateStatus.EST_APPROVAL_ES));
			
			Calendar calendar = Calendar.getInstance();
			Format format=new Format();
			Approval approval=new Approval();
			approval.setApprovalId(null);
			approval.setReferenceNo(applicationNo);
			approval.setDeptId(costCenterNo);
			approval.setApprovalType("EST_VLDT");
			approval.setApprovedLevel(getSessionKey("userRole"));
			approval.setApprovedBy(getSessionKey("userName"));
			approval.setApprovedDate(calendar.getTime());
			approval.setApprovedTime(format.FormatTime());
	
			approval.setFromStatus(new BigDecimal(EstimateStatus.NEW
					).toString());
			approval.setToStatus(new BigDecimal(EstimateStatus.EST_APPROVAL_ES).toString());
			approval.setStandardCost(new BigDecimal(Double.toString(nf.parse(totalCost).doubleValue())));
			approval.setDetailedCost(new BigDecimal(Double.toString(nf.parse(totalDetailCost).doubleValue())));
			
			Pcesthtt pcesthtt = pcesthttEjb.findByEstimationNo(applicationNo, costCenterNo);
			pcesthtt.setStatus(new Long(EstimateStatus.EST_APPROVAL_ES));
			pcesthttEjb.validateEstimate(pcesthtt, approval);
			clearFields();
			setSuccessMessage("Estimate sent for approval successfully!");
			return SUCCESS;
		}
		catch(Exception e){
			
			return ERROR;
		}
		
	}
	
	public String print()
	{
		clearSessionVariables();
		setErrorMessage(null);
		setSuccessMessage(null);
		setInfoMessage(null);
		setLoggedData();
		this.isViewOnly = null;
		this.isModify = null;
		this.isUndoReject = null;
		this.isApprove = null;
		this.isRoughEstimate = null;
		this.isPrint = "true";
		setPath(viewPath);
		Calendar cal = Calendar.getInstance();
		int curYear = cal.get(Calendar.YEAR);
		applicationNo = costCenterNo+"/"+PivPrefixType.EST_NEW_CONN+"/"+String.valueOf(curYear).substring(2, 4)+"/";
		setFormData();
		//sessionMap.put("FixedCost", "0.00");
		return SUCCESS;
	}
	
	
	public String approveEstimate()
	{
		setErrorMessage(null);
		setSuccessMessage(null);
		setInfoMessage(null);
		//setLoggedData();
		
		setPath(viewPath);
		
		PcesthttEjb pcesthttEjb = new PcesthttEjb(getSessionKey("region"));
		String msg = "";
		//String msg = pcesthttEjb.approveEstimate(applicationNo, costCenterNo, getSession().get("userRole").toString(),getSessionKey("userName"));
		int errorIndex = msg.indexOf('@');
		int successIndex = msg.indexOf('#');
		msg =msg.substring(1);
		clearFields();
		setFormData();
		if(errorIndex!=-1)
			setErrorMessage(msg);
		else if(successIndex!=-1)
			setSuccessMessage(msg);
		return SUCCESS;
	}
	
	
	
	public String rejectEstimate()
	{
		setErrorMessage(null);
		setSuccessMessage(null);
		setInfoMessage(null);
		//setLoggedData();
		
		setPath(viewPath);
		
		
		PcesthttEjb pcesthttEjb = new PcesthttEjb(getSessionKey("region"));
		
		Pcesthtt pcesthtt = pcesthttEjb.findByEstimationNo(applicationNo, costCenterNo);
		
		Long preStatus = pcesthtt.getStatus();
		pcesthtt.setRejectReason(rejectReason);
		pcesthtt.setRejctUid(getSessionKey("userName"));
		pcesthtt.setRejctDt(new Date());
		pcesthtt.setStatus(new Long(EstimateStatus.EST_REJECTED));
		try
		{
			nf.setGroupingUsed(true);
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			
			//pcesthttEjb.updatePcesthtt(pcesthtt);
			Calendar calendar = Calendar.getInstance();
			Format format=new Format();
			Approval approval=new Approval();
			approval.setApprovalId(null);
			approval.setReferenceNo(applicationNo);
			approval.setDeptId(costCenterNo);
			approval.setApprovalType("EST_RJCT");
			approval.setApprovedLevel(getSessionKey("userRole"));
			approval.setApprovedBy(getSessionKey("userName"));
			approval.setApprovedDate(calendar.getTime());
			approval.setApprovedTime(format.FormatTime());
			approval.setReason(rejectReason);
			approval.setFromStatus(preStatus.toString());
			approval.setToStatus(pcesthtt.getStatus().toString());
			approval.setStandardCost(new BigDecimal(Double.toString(nf.parse(totalCost).doubleValue())));
			approval.setDetailedCost(new BigDecimal(Double.toString(nf.parse(totalDetailCost).doubleValue())));
			
			pcesthttEjb.rejectEstimate(pcesthtt, approval);
			
		}
		catch(Exception e){}
		clearFields();
		setFormData();
		setSuccessMessage("Estimate rejected successfully.");
		return SUCCESS;
	}
	
	
	
	//action invoke when Save button is clicked
	public String save()
	{
		try
		{
			loggedUser = getSession().get("userName").toString();
			setLoggedData();
			setPath(viewPath);
			setFormData();
			if(manCatFeatValue!=null)
			{
				for(int i=0;i<manCatFeatValue.length;i++)
				{
					FAMainCategoryGrid grid = (FAMainCategoryGrid)faManCatFeatList.toArray()[i];
					grid.setFeatureValue(manCatFeatValue[i]);
					
				}
			}
			if(matFeatValue!=null)
			{
				for(int i=0;i<matFeatValue.length;i++)
				{
					FAMatFeatureGrid grid = (FAMatFeatureGrid)faMatFeatureList.toArray()[i];
					grid.setFeatureValue(matFeatValue[i]);
					
				}
			}
		}
		catch(Exception e)
		{
			setErrorMessage("Unexpected Error! Please retry.");
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	private String getManCatDesc(String manCatCode)
	{
		String manCatDesc = "";
		if(faManCatList!=null)
		{
			for(int i=0;i<faManCatList.size();i++)
			{
				NameValueList tmpList = (NameValueList)faManCatList.get(i);
				if(tmpList.getName().equals(manCatCode))
				{
					manCatDesc = tmpList.getValue();
				}
			}
		}
		return manCatDesc;
	}
	
	
	//Method to set whether the estimate is rejected earlier
	
	private void setRejectedStatus(Pcesthtt pcesthtt,Boolean isUndoreject){
		String msg = "";
		if(pcesthtt!=null && pcesthtt.getRejectReason()!=null && pcesthtt.getRejectReason().length()>0){
			if(!isUndoreject)
				msg = "This estimate has rejected previously";
			else
				msg="";
			rejectReason = pcesthtt.getRejectReason();
		}else
			msg="";
		setInfoMessage(msg);
	}
	
	
	
	
	//action invoke when Find button is clicked
	public String viewApplicantDetails()
	{
		try
		{
			setPath(viewPath);
			clearFields();
			setFormData();		
			
			//viewing estimate
			//if(isViewOnly!=null && isViewOnly.equals("true") || (isPrint!=null && isPrint.equals("true")))
			//{
			
				PcesthmtEjb pcesthmtEjb = new PcesthmtEjb(getSessionKey("region"));
				System.out.println("job no @@@ "+applicationNo);
				Pcesthmt pcesthmt = pcesthmtEjb.findByJobNo(applicationNo, "510.20");
				if(pcesthmt==null)
				{
					setInfoMessage("Job does not exist.");
					return ERROR;
				}
				else
				{
					
					//ApplicationEjb applicationEJB = new ApplicationEjb(getSessionKey("region"));
					//Application application = applicationEJB.findByApplicationNo(pcesthmt.getId().getEstimateNo());
					//setApplicantData(application);
					//setWiringData(application);
					setEstimateData(pcesthmt,pcesthmt.getId().getEstimateNo());
					this.isViewApplicant = "true";	
					return SUCCESS;
				}
				
		//	}//end viewing estimate
			
			
			
			
			 
		}
		catch(Exception e)
		{
			setErrorMessage("Unexpected Error! Please retry.");
			this.isViewApplicant = null;
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String peTransfer()
	{
		setFormData();
		return "transfer";
	}
	
	//action invoke when Clear button is clicked
	public String clear()
	{
		this.applicationNo = "";
		setLoggedData();
		setPath(viewPath);
		clearFields();
		clearSessionVariables();
		setFormData();
		return SUCCESS;
	}
	
	//action invoke when Close button is clicked
	public String close() {
		setLoggedData();
		clearSessionVariables();
		return "close";
	}
	
	private void setFormData()
	{
		try
		{
			/*
			if(isModify!=null && isModify.equals("true"))
			{
				PcesthttEjb pcesthttEjb = new PcesthttEjb(getSessionKey("region"));
				estNoList = pcesthttEjb.getEstimateNoListT(costCenterNo, new BigDecimal(EstimateStatus.NEW), ApplicationType.NEW_CONN);
				
			}
			else if(isUndoReject!=null && isUndoReject.equals("true"))
			{
				PcesthttEjb pcesthttEjb = new PcesthttEjb(getSessionKey("region"));
				estNoList = pcesthttEjb.getEstimateNoListT(costCenterNo, new BigDecimal(EstimateStatus.EST_REJECTED), ApplicationType.NEW_CONN);
			}
			else if(isApprove!=null && isApprove.equals("true"))
			{
				PcesthttEjb pcesthttEjb = new PcesthttEjb(getSessionKey("region"));
				estNoList = pcesthttEjb.getEstNoApprovalListNewT(costCenterNo, getSession().get("userRole").toString(), ApplicationType.NEW_CONN);
				
			}
			
			else//new
			{
				SpestedyEjb  spestedyEjb = new SpestedyEjb(getSessionKey("region"));
				estNoList = spestedyEjb.getVisitedAppoinments(costCenterNo, getLoggedInUserId(), ApplicationType.NEW_CONN);
				//estNoList = spestedyEjb.getAppoinmentsByStatus(costCenterNo, getLoggedInUserId(), ApplicationType.NEW_CONN, AppStatus.SERVICE_EST_CREATED);
				categoryCode = "SMC";
				
			}*/
			//if(estNoList==null)
			estNoList = new ArrayList();
			estNoList.add("1690/2009D");
			estNoList.add("1543/2009D");
			EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
	    	fundSourceList = estimateEjb.getFundSource(costCenterNo);
	    	fundSourceID = "CP###CP";
	    	
	    	PcjbtypmEjb pcjbtypmEjb = new PcjbtypmEjb(getSessionKey("region"));
	    	categoryCodeList = pcjbtypmEjb.getCatCode(costCenterNo);
	    	
	    	cableTypeList = new ArrayList<ListObject>();
	    	ListObject l11 = new ListObject("162C","16mm2/2C");
	    	cableTypeList.add(l11);
			
			ListObject l12 = new ListObject("16XPLE","16mm2XPLE");
			cableTypeList.add(l12);
			
			ListObject l13 = new ListObject("35XPLE ","35mm2XPLE");
			cableTypeList.add(l13);
			
			ListObject l14 = new ListObject("70XPLE ","70mm2XPLE");
			cableTypeList.add(l14);
			
			ListObject l15 = new ListObject("95XPLE ","95mm2XPLE");
			cableTypeList.add(l15);
			
	    	//cableTypeList = new ArrayList<String>();
	    	//cableTypeList.add("16mm");
			
			SpratesmPK spratesmpK = new SpratesmPK();
			spratesmpK.setApplicationType(getSessionKey("smcType"));
			spratesmpK.setDeptId(costCenterNo);
			Spratesm spratesm = estimateEjb.getSpratesm(spratesmpK);
			if(spratesm!=null)
			{
				labourRate = spratesm.getLabourRate().toString();
				transportRate = spratesm.getTransportRate().toString();
				overheadRate = spratesm.getOverheadRate().toString();
			}
			
			FAMainCategoryGrid mcGrid1 = new FAMainCategoryGrid("SUBSTN","Substation","SIN","SIN Number",null);
			FAMainCategoryGrid mcGrid2 = new FAMainCategoryGrid("SUBSTN","Substation","LOC_NAME","Location",null);
			FAMainCategoryGrid mcGrid3 = new FAMainCategoryGrid("SUBSTN","Substation","SS_TYPE","S/S Type",null);
			FAMainCategoryGrid mcGrid4 = new FAMainCategoryGrid("SUBSTN","Substation","COM_DATE","Date of Commission",null);
			FAMainCategoryGrid mcGrid9 = new FAMainCategoryGrid("OHHTLINE","Overhead Transmission Lines","LENGTH","Line Length",null);
			FAMainCategoryGrid mcGrid5 = new FAMainCategoryGrid("OHHTLINE","Overhead Transmission Lines","LINE_FROM","Line From",null);
			FAMainCategoryGrid mcGrid6 = new FAMainCategoryGrid("OHHTLINE","Overhead Transmission Lines","LINE_TO","Line To",null);
			FAMainCategoryGrid mcGrid7 = new FAMainCategoryGrid("OHHTLINE","Overhead Transmission Lines","POLE_FROM","Pole From",null);
			FAMainCategoryGrid mcGrid8 = new FAMainCategoryGrid("OHHTLINE","Overhead Transmission Lines","POLE_TO","Pole To",null);
			
			faManCatFeatList = new ArrayList<FAMainCategoryGrid>();
			faManCatFeatList.add(mcGrid1);
			faManCatFeatList.add(mcGrid2);
			faManCatFeatList.add(mcGrid3);
			faManCatFeatList.add(mcGrid4);
			faManCatFeatList.add(mcGrid9);
			faManCatFeatList.add(mcGrid5);
			faManCatFeatList.add(mcGrid6);
			faManCatFeatList.add(mcGrid7);
			faManCatFeatList.add(mcGrid8);
			
			

		}
		catch(Exception e)
		{
			fundSourceList = new ArrayList();
			e.printStackTrace();
		}
	}
	
	private void setMatMasterData()
	{
		NameValueList nvl1 = new NameValueList("METER","Meter");
		NameValueList nvl2 = new NameValueList("CONDUCT","Conductor");
		NameValueList nvl3 = new NameValueList("ACCESSORY","Accessory");
		
		faSubCatList = new ArrayList<NameValueList>();
		faSubCatList.add(nvl1);
		faSubCatList.add(nvl2);
		faSubCatList.add(nvl3);
		
		NameValueList nvl10 = new NameValueList("SUBSTN","Substation");
		NameValueList nvl20 = new NameValueList("OHHTLINE","Overhead Transmission Lines");
		NameValueList nvl30 = new NameValueList("OHLTLINE","Overhead Low Tension Lines");
		NameValueList nvl40 = new NameValueList("UGHTLINE","Underground Transmission Lines");
		NameValueList nvl50 = new NameValueList("UGLTLINE","Underground Low Tension Lines");
		
		faManCatList = new ArrayList<NameValueList>();
		faManCatList.add(nvl10);
		faManCatList.add(nvl20);
		faManCatList.add(nvl30);
		faManCatList.add(nvl40);
		faManCatList.add(nvl50);	
		
		
	}
	
	private void clearFields()
	{
		this.isViewApplicant = null;
		this.errorMessage = "";
		this.successMessage = "";
		
		this.applicantName = "";
		this.companyName = "";
		this.address = "";
		this.neighboursAccNo = "";
		this.assessmentNo = "";
		this.description = "";
		this.applicantDate = "";
		this.phase = "";
		this.connectionType = "";
		this.conductorLength = "";
		this.serviceLength = "";
		this.phaseDb = "";
		this.connectionTypeDb = "";
		this.tariffCode = "";
		this.tariffCategory = "";
		this.idNo = "";
		this.applicationId = "";
		
		this.isLoopService = "";
		this.categoryCode = "";
		this.lineLength = "";
		this.loopLength = "";
		//this.lb = "";
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
		this.otherLabourCost = "";
		this.capitalCost = "";
		this.taxAmount = "";
		this.secDeposit = "";
		this.totalCost = "";
		this.estimateExist = null;
		this.fundSourceID = null;
		this.rejectReason = "";
		this.convCost = "";
		
	}
	
	private void clearSessionVariables()
	{
		sessionMap.remove("IsNewEstimate");
		sessionMap.remove("SelectMaterialList");
		sessionMap.remove("SavedMaterialList");
		sessionMap.remove("IsMaterialsAdd");
		sessionMap.remove("IsDefMatLoad");
		sessionMap.remove("SelectLabourList");
		sessionMap.remove("SavedLabourList");
		sessionMap.remove("IsDefLabLoad");
	}

	//add new estimate
	private void insert()throws Exception
	{
		Format format=new Format();
		
		//inserting labour costs
		LinkedHashMap selectLabCodeMap = (LinkedHashMap)sessionMap.get("SelectLabourList");
		ArrayList spestlabList = null;
		
		double totalLabourCost = 0;
		double totalLabourHrs = 0;
		double otherC = 0;
		double otherLabourC = 0;
		
		if(selectLabCodeMap!=null && selectLabCodeMap.size()>0)
		{
			spestlabList = new ArrayList();
			Iterator it = selectLabCodeMap.keySet().iterator();
			int i=0;
			ArrayList savedLab = new ArrayList();
		
			while(it.hasNext())
			{
				String labCode = (String)it.next();
				savedLab.add(labCode);
				LabourGrid lg = (LabourGrid)selectLabCodeMap.get(labCode);
				
				String qty = txtSelLabQty[i];
				//String hrs = txtSelLabHrs[i];
				
				double tmpHrs = lg.getUnitLabourHrs().doubleValue()*Double.parseDouble(qty);
				totalLabourHrs = totalLabourHrs+tmpHrs;
				
				double tmpLabCost = tmpHrs*lg.getCebUnitPrice().doubleValue();
				totalLabourCost = totalLabourCost+tmpLabCost;
				
				Spestlab spestlab= new Spestlab();
				
				SpestlabPK spestlabPK = new SpestlabPK();
				spestlabPK.setDeptId(costCenterNo);
				spestlabPK.setEstimateNo(applicationNo);
				spestlabPK.setLabourCode(labCode);
				spestlab.setId(spestlabPK);
				
				spestlab.setActivityDescription(lg.getDescription());
				spestlab.setUnitLabourHrs(lg.getUnitLabourHrs());
				spestlab.setItemQty(new BigDecimal(qty));
				spestlab.setLabourHours(new BigDecimal(tmpHrs).setScale(2, BigDecimal.ROUND_HALF_UP));
				spestlab.setUnitPrice(lg.getUnitPrice());
				spestlab.setLabourCost(new BigDecimal(lg.getUnitPrice().doubleValue()*Double.parseDouble(qty)).setScale(2, BigDecimal.ROUND_HALF_UP));
				spestlab.setCebUnitPrice(lg.getCebUnitPrice());
				spestlab.setCebLabourCost(new BigDecimal(tmpLabCost).setScale(2, BigDecimal.ROUND_HALF_UP));
				
				spestlabList.add(spestlab);
				
				lg.setItemQty(new BigDecimal(qty));
				lg.setLabourHours(new BigDecimal(tmpHrs).setScale(2, BigDecimal.ROUND_HALF_UP));
				lg.setCebLabourCost(new BigDecimal(tmpLabCost).setScale(2, BigDecimal.ROUND_HALF_UP));
				lg.setLabourCost(new BigDecimal(lg.getUnitPrice().doubleValue()*Double.parseDouble(qty)).setScale(2, BigDecimal.ROUND_HALF_UP));
				
				i++;
			}
			sessionMap.put("SavedLabourList",savedLab);
			sessionMap.put("SelectLabourList",selectLabCodeMap);
		}
				
		//inserting materials
		LinkedHashMap selectMatCodeMap = (LinkedHashMap)sessionMap.get("SelectMaterialList");
		ArrayList pcestdttList = null;
		double totDetailCost = 0;
		if(selectMatCodeMap!=null && selectMatCodeMap.size()>0)
		{
			pcestdttList = new ArrayList();
			Iterator it = selectMatCodeMap.keySet().iterator();
			int i=0;
			ArrayList savedMat = new ArrayList();
		
			while(it.hasNext())
			{
				String matCode = (String)it.next();
				savedMat.add(matCode);
				MaterialGrid tmpLst = (MaterialGrid)selectMatCodeMap.get(matCode);
				
				String uom  = tmpLst.getUom();
				String up  = tmpLst.getUnitPrice().toString();
				String matName  = tmpLst.getMatNm();
				String qty = txtSelQty[i];
				//String cost = txtSelMatCost[i];
				String resType = tmpLst.getResType();
				BigDecimal resCat = tmpLst.getResCat();
				String cusQty = txtCusQty[i];
				
				double tmpQty = Double.parseDouble(qty);
				double cusCost = 0;
				if(cusQty!=null && cusQty.length()>0)
				{
					cusCost = tmpLst.getUnitPrice().doubleValue()*new BigDecimal(cusQty).doubleValue();
				}
				if(matCode.equals("LABOUR") || matCode.equals("OVERHEAD"))
				{
					tmpQty = totalLabourHrs;
					otherLabourC = otherLabourC+cusCost;
				}
				else
				{
					otherC = otherC+cusCost;
				}
				double matCost = tmpQty*tmpLst.getUnitPrice().doubleValue();
				
				PcestdttPK pcestdttPK = new PcestdttPK();
				pcestdttPK.setDeptId(costCenterNo);
				pcestdttPK.setEstimateNo(applicationNo);
				pcestdttPK.setResCd(matCode);
				pcestdttPK.setRevNo(1);
				
				Pcestdtt pcestdtt = new Pcestdtt();
				pcestdtt.setId(pcestdttPK);
				pcestdtt.setEstimateCost(new BigDecimal(matCost).setScale(2, BigDecimal.ROUND_HALF_UP));
				pcestdtt.setEstimateQty(new BigDecimal(tmpQty).setScale(2, BigDecimal.ROUND_HALF_UP));
				pcestdtt.setResType(resType);
				pcestdtt.setResCat(resCat);
				pcestdtt.setUnitPrice(new BigDecimal(up));
				pcestdtt.setUom(uom);
				
				totDetailCost = totDetailCost+matCost;
				
				if(cusQty!=null && cusQty.length()>0)
				{
					pcestdtt.setCustomerQty(new BigDecimal(cusQty));
					tmpLst.setCustomerQty(new BigDecimal(cusQty));
					
					tmpLst.setCustomerCost(new BigDecimal(cusCost).setScale(2, BigDecimal.ROUND_HALF_UP));
				}
				
				pcestdttList.add(pcestdtt);
				
				tmpLst.setEstimateQty(new BigDecimal(tmpQty));
				tmpLst.setEstimateCost(new BigDecimal(matCost).setScale(2, BigDecimal.ROUND_HALF_UP));
				
				i++;
			}
			sessionMap.put("SavedMaterialList",savedMat);
			sessionMap.put("SelectMaterialList",selectMatCodeMap);
		}
		
		PcesthttPK pcesthttPK = new PcesthttPK();
		pcesthttPK.setEstimateNo(applicationNo);
		pcesthttPK.setDeptId(costCenterNo);
		pcesthttPK.setRevNo(1);//for new methods
		
		Pcesthtt pcesthtt = new Pcesthtt();
		pcesthtt.setId(pcesthttPK);
		
		pcesthtt.setPartialPmt("F");
		pcesthtt.setNormDefault(new BigDecimal(1));
		pcesthtt.setSubCont("T");
		pcesthtt.setControlled("T");
		pcesthtt.setPriority(new BigDecimal(0));
		pcesthtt.setAllocPaid(new BigDecimal(0));
		pcesthtt.setEstType("2");
		pcesthtt.setAllocSettle(new BigDecimal(0));
		
		pcesthtt.setEtimateDt(new java.util.Date());
		pcesthtt.setStatus(new Long(EstimateStatus.MODIFIED));
		pcesthtt.setEntBy(loggedUser);
		pcesthtt.setEntDt(new java.util.Date());
		pcesthtt.setCatCd(categoryCode);
		pcesthtt.setConfDt(format.getDefaultDate());
		pcesthtt.setAprDt1(format.getDefaultDate());
		pcesthtt.setAprDt2(format.getDefaultDate());
		pcesthtt.setAprDt3(format.getDefaultDate());
		pcesthtt.setAprDt4(format.getDefaultDate());
		pcesthtt.setAprDt5(format.getDefaultDate());
		pcesthtt.setRejctDt(format.getDefaultDate());
		pcesthtt.setReviseDt(format.getDefaultDate());
		pcesthtt.setDescr(jobDesc);
		pcesthtt.setStdCost(new BigDecimal(totDetailCost).setScale(2, BigDecimal.ROUND_HALF_UP));
		if(fundSourceID!=null && fundSourceID.length()>0)
		{
			StringTokenizer st = new StringTokenizer(fundSourceID,"###");
			pcesthtt.setFundSource(st.nextToken());
			pcesthtt.setFundId(st.nextToken());
		}
		
		Date curDate = new Date();
		
		Speststd speststd = new Speststd();
		SpeststdPK speststdPK = new SpeststdPK();
		speststdPK.setDeptId(costCenterNo);
		speststdPK.setEstimateNo(applicationNo);
		speststd.setId(speststdPK);
		speststd.setConductorType(conductorType);
		speststd.setWiringType(wiringType);
		speststd.setSinNo(sinNo);
		speststd.setUpdDate(curDate);
		speststd.setUpdTime(format.FormatTime());
		speststd.setUpdUser(loggedUser);
		speststd.setTransformColor(transColor);
		speststd.setIsLoopService(isLoopService);
		//speststd.setIsStandardVc(isStandardVC);
		
		if(conductorLength==null || conductorLength.length()==0)
			speststd.setConductorLength(new BigDecimal(0));
		else
			speststd.setConductorLength(new BigDecimal(conductorLength));
		if(cableType!=null && cableType.length()>0)
			speststd.setCableType(cableType);
		if(serviceLength==null || serviceLength.length()==0)
			speststd.setServiceLength(new BigDecimal(0));
		else
			speststd.setServiceLength(new BigDecimal(serviceLength));
		if(lineLength==null || lineLength.length()==0)
			speststd.setLineLength(new BigDecimal(0));
		else
			speststd.setLineLength(new BigDecimal(lineLength));
		if(loopLength==null || loopLength.length()==0)
			speststd.setLoopLength(new BigDecimal(0));
		else
			speststd.setLoopLength(new BigDecimal(loopLength));
		if(poleNo==null || poleNo.length()==0)
			speststd.setPoleNo(new BigDecimal(0));
		else
			speststd.setPoleNo(new BigDecimal(poleNo));
		if(distanceToServicePlace==null || distanceToServicePlace.length()==0)
			speststd.setServiceDistance(new BigDecimal(0));
		else
			speststd.setServiceDistance(new BigDecimal(distanceToServicePlace));
		if(spans==null || spans.length()==0)
			speststd.setSpan(new BigDecimal(0));
		else
			speststd.setSpan(new BigDecimal(spans));
		if(stayNo==null || stayNo.length()==0)
			speststd.setStayNo(new BigDecimal(0));
		else
			speststd.setStayNo(new BigDecimal(stayNo));
		if(strutsNo==null || strutsNo.length()==0)
			speststd.setStrutNo(new BigDecimal(0));
		else
			speststd.setStrutNo(new BigDecimal(strutsNo));
		if(conversionLength==null || conversionLength.length()==0)
			speststd.setConversionLength(new BigDecimal(0));
		else
			speststd.setConversionLength(new BigDecimal(conversionLength));
		/**gayani if(conversionLength2P==null || conversionLength2P.length()==0)
			speststd.setConversionLength2p(new BigDecimal(0));
		else
			speststd.setConversionLength2p(new BigDecimal(conversionLength2P));
		if(circuitLength==null || circuitLength.length()==0)
			speststd.setSecondCircuitLength(new BigDecimal(0));
		else
			speststd.setSecondCircuitLength(new BigDecimal(circuitLength));*/
		
		double fixedC = 0;
		double variableC = 0;
		double securityD = 0;
		//double otherC = 0;
		//double otherLabourC = 0;
		double conversionC = 0;
		double capitalC = 0;
		double additionalD = 0;
		double labourC = 0;
		double transportC = 0;
		double overheadC = 0;
		double damageC = 0;
		double boardC = 0;
		double contingencyC = 0;
		
		if(fixedCost!=null && fixedCost.length()>0)
			fixedC = nf.parse(fixedCost).doubleValue();
		if(variableCost!=null && variableCost.length()>0)
			variableC = nf.parse(variableCost).doubleValue();
		if(secDeposit!=null && secDeposit.length()>0)
			securityD = nf.parse(secDeposit).doubleValue();
		if(convCost!=null && convCost.length()>0)
			conversionC = nf.parse(convCost).doubleValue();
		if(capitalCost!=null && capitalCost.length()>0)
			capitalC = nf.parse(capitalCost).doubleValue();
		if(addlDeposit!=null && addlDeposit.length()>0)
			additionalD = nf.parse(addlDeposit).doubleValue();
		
		speststd.setFixedCost(new BigDecimal(fixedC).setScale(2, BigDecimal.ROUND_HALF_UP));
		speststd.setVariableCost(new BigDecimal(variableC).setScale(2, BigDecimal.ROUND_HALF_UP));
		speststd.setSecurityDeposit(new BigDecimal(securityD).setScale(2, BigDecimal.ROUND_HALF_UP));
		speststd.setOtherCost(new BigDecimal(otherC).setScale(2, BigDecimal.ROUND_HALF_UP));
		speststd.setOtherLabourCost(new BigDecimal(otherLabourC).setScale(2, BigDecimal.ROUND_HALF_UP));
		speststd.setConversionCost(new BigDecimal(conversionC).setScale(2, BigDecimal.ROUND_HALF_UP));
		speststd.setCapitalCost(new BigDecimal(capitalC).setScale(2, BigDecimal.ROUND_HALF_UP));
		speststd.setAddlDeposit(new BigDecimal(additionalD).setScale(2, BigDecimal.ROUND_HALF_UP));
		
		speststd.setLabourCost(new BigDecimal(labourC));
		speststd.setTransportCost(new BigDecimal(transportC));
		speststd.setOverheadCost(new BigDecimal(overheadC));
		speststd.setDamageCost(new BigDecimal(damageC));
		speststd.setBoardCharge(new BigDecimal(boardC));
		speststd.setContingencyCost(new BigDecimal(contingencyC));
		
		speststd.setTemporaryDeposit(new BigDecimal(0));
		speststd.setBoardChargePresent(new BigDecimal(0));
		speststd.setContingencyPresent(new BigDecimal(0));
		
		
		double totalC = fixedC+variableC+securityD+otherC+otherLabourC+conversionC+capitalC+additionalD
						+labourC+transportC+overheadC+damageC+boardC+contingencyC;
		
		speststd.setTotalCost(new BigDecimal(totalC).setScale(2, BigDecimal.ROUND_HALF_UP));
		
		EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
		estimateEjb.insert(pcesthtt, speststd, pcestdttList,spestlabList);
	}
	
	//modify estimate
	private void modify(Pcesthtt pcesthtt)throws Exception
	{
		Format format=new Format();
		
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		
		double totalLabourCost = 0;
		double totalLabourHrs = 0;
		double otherC = 0;
		double otherLabourC = 0;
		
		
		//modifying material costs
		LinkedHashMap selectMatCodeMap = (LinkedHashMap)sessionMap.get("SelectMaterialList");
		ArrayList<String> savedMat = (ArrayList)sessionMap.get("SavedMaterialList");
		if(savedMat==null)	
			savedMat = new ArrayList<String>();
		ArrayList<Pcestdtt> insertList = null;
		ArrayList<Pcestdtt> updateList = null;
		double totDetailCost = 0;
		if(selectMatCodeMap!=null && selectMatCodeMap.size()>0)
		{
			Iterator it = selectMatCodeMap.keySet().iterator();
			int i=0;
		
			PcestdttEjb pcestdttEjb = new PcestdttEjb(getSessionKey("region"));
			while(it.hasNext())
			{
				String matCode = (String)it.next();
				MaterialGrid tmpLst = (MaterialGrid)selectMatCodeMap.get(matCode);
				
				String uom  = tmpLst.getUom();
				String up  = tmpLst.getUnitPrice().toString();
				String matName  = tmpLst.getMatNm();
				String qty = txtSelQty[i];
				String resType = tmpLst.getResType();
				BigDecimal resCat = tmpLst.getResCat();
				String cusQty = txtCusQty[i];
				
				double tmpQty = Double.parseDouble(qty);
				double cusCost = 0;
				if(cusQty!=null && cusQty.length()>0)
				{
					cusCost = tmpLst.getUnitPrice().doubleValue()*new BigDecimal(cusQty).doubleValue();
				}
				if(matCode.equals("LABOUR") || matCode.equals("OVERHEAD"))
				{
					tmpQty = totalLabourHrs;
					otherLabourC = otherLabourC+cusCost;
				}
				else
				{
					otherC = otherC+cusCost;
				}
				double matCost = tmpQty*tmpLst.getUnitPrice().doubleValue();
				
				
				/**/
				totDetailCost = totDetailCost+matCost;
				if(savedMat.contains(matCode))
				{
					Pcestdtt pcestdtt = pcestdttEjb.findBy3PK(applicationNo, costCenterNo, matCode);
					pcestdtt.setEstimateCost(new BigDecimal(matCost).setScale(2, BigDecimal.ROUND_HALF_UP));
					pcestdtt.setEstimateQty(new BigDecimal(qty));
					if(cusQty!=null && cusQty.length()>0)
					{
						pcestdtt.setCustomerQty(new BigDecimal(cusQty));
						tmpLst.setCustomerQty(new BigDecimal(cusQty));
						
						tmpLst.setCustomerCost(new BigDecimal(cusCost).setScale(2, BigDecimal.ROUND_HALF_UP));
					
					}
					if(updateList==null)
						updateList = new ArrayList<Pcestdtt>();
					updateList.add(pcestdtt);
				}
				else
				{
					Pcestdtt pcestdtt = new Pcestdtt();
					
					PcestdttPK pcestdttPK = new PcestdttPK();
					pcestdttPK.setDeptId(costCenterNo);
					pcestdttPK.setEstimateNo(applicationNo);
					pcestdttPK.setResCd(matCode);
					pcestdttPK.setRevNo(1);
					
					pcestdtt.setId(pcestdttPK);
					pcestdtt.setEstimateCost(new BigDecimal(matCost).setScale(2, BigDecimal.ROUND_HALF_UP));
					pcestdtt.setEstimateQty(new BigDecimal(qty));
					pcestdtt.setResType(resType);
					pcestdtt.setResCat(resCat);
					pcestdtt.setUnitPrice(new BigDecimal(up));
					pcestdtt.setUom(uom);
					pcestdtt.getId().setRevNo(1);
					if(cusQty!=null && cusQty.trim().length()>0)
					{
						pcestdtt.setCustomerQty(new BigDecimal(cusQty));
						tmpLst.setCustomerQty(new BigDecimal(cusQty));
					}
					if(insertList==null)
						insertList = new ArrayList<Pcestdtt>();
					insertList.add(pcestdtt);
					savedMat.add(matCode);
				}
				sessionMap.put("SavedMaterialList",savedMat);
				tmpLst.setEstimateQty(new BigDecimal(qty));
				tmpLst.setEstimateCost(new BigDecimal(Double.toString(matCost)));
				i++;
			}
		}
		
		pcesthtt.setCatCd(categoryCode);
		pcesthtt.setConfDt(format.getDefaultDate());
		pcesthtt.setAprDt1(format.getDefaultDate());
		pcesthtt.setAprDt2(format.getDefaultDate());
		pcesthtt.setAprDt3(format.getDefaultDate());
		pcesthtt.setAprDt4(format.getDefaultDate());
		pcesthtt.setAprDt5(format.getDefaultDate());
		pcesthtt.setRejctDt(format.getDefaultDate());
		pcesthtt.setReviseDt(format.getDefaultDate());
		pcesthtt.setDescr(jobDesc);
		pcesthtt.setStdCost(new BigDecimal(totDetailCost).setScale(2, BigDecimal.ROUND_HALF_UP));
		if(fundSourceID!=null && fundSourceID.length()>0)
		
		{
			StringTokenizer st = new StringTokenizer(fundSourceID,"###");
			pcesthtt.setFundSource(st.nextToken());
			pcesthtt.setFundId(st.nextToken());
		}
		else
		{
			pcesthtt.setFundSource(null);
			pcesthtt.setFundId(null);
		}
		SpeststdPK speststdPK = new SpeststdPK();
		speststdPK.setDeptId(costCenterNo);
		speststdPK.setEstimateNo(applicationNo);
		
		SpeststdEjb speststdEJB = new SpeststdEjb(getSessionKey("region"));
		Speststd speststd = speststdEJB.findById(speststdPK);
		
		Date curDate = new Date();
		speststd.setUpdDate(curDate);
		speststd.setUpdTime(format.FormatTime());
		speststd.setUpdUser(loggedUser);
		speststd.setTemporaryDeposit(new BigDecimal(0));
		speststd.setTransformColor(transColor);
		speststd.setConductorType(conductorType);
		speststd.setIsLoopService(isLoopService);
		speststd.setWiringType(wiringType);
		
		if(cableType!=null && cableType.length()>0)
			speststd.setCableType(cableType);
		if(lineLength!=null && lineLength.trim().length()>0)
			speststd.setLineLength(new BigDecimal(lineLength));
		if(loopLength!=null && loopLength.trim().length()>0)
			speststd.setLoopLength(new BigDecimal(loopLength));
		if(poleNo!=null && poleNo.trim().length()>0)
			speststd.setPoleNo(new BigDecimal(poleNo));
		if(conductorLength==null || conductorLength.length()==0)
			speststd.setConductorLength(new BigDecimal(0));
		else
			speststd.setConductorLength(new BigDecimal(conductorLength));
		if(serviceLength==null || serviceLength.length()==0)
			speststd.setServiceLength(new BigDecimal(0));
		else
			speststd.setServiceLength(new BigDecimal(serviceLength));
		if(distanceToServicePlace!=null && distanceToServicePlace.trim().length()>0)
			speststd.setServiceDistance(new BigDecimal(distanceToServicePlace));
		speststd.setSinNo(sinNo);
		if(spans!=null && spans.trim().length()>0)
			speststd.setSpan(new BigDecimal(spans));
		if(stayNo!=null && stayNo.trim().length()>0)
			speststd.setStayNo(new BigDecimal(stayNo));
		if(strutsNo!=null && strutsNo.trim().length()>0)
			speststd.setStrutNo(new BigDecimal(strutsNo));
		if(conversionLength==null || conversionLength.length()==0)
			speststd.setConversionLength(new BigDecimal(0));
		else
			speststd.setConversionLength(new BigDecimal(conversionLength));
		/** gayani if(conversionLength2P==null || conversionLength2P.length()==0)
			//speststd.setConversionLength2p(new BigDecimal(0));
		//else
			//speststd.setConversionLength2p(new BigDecimal(conversionLength2P));
		if(circuitLength==null || circuitLength.length()==0)
			//speststd.setSecondCircuitLength(new BigDecimal(0));
		//else
			//speststd.setSecondCircuitLength(new BigDecimal(circuitLength));*/
		
		double fixedC = 0;
		double variableC = 0;
		double securityD = 0;
		double conversionC = 0;
		double capitalC = 0;
		double additionalD = 0;
		double labourC = 0;
		double transportC = 0;
		double overheadC = 0;
		double damageC = 0;
		double boardC = 0;
		double contingencyC = 0;
		
		if(fixedCost!=null && fixedCost.length()>0)
			fixedC = nf.parse(fixedCost).doubleValue();
		if(variableCost!=null && variableCost.length()>0)
			variableC = nf.parse(variableCost).doubleValue();
		if(secDeposit!=null && secDeposit.length()>0)
			securityD = nf.parse(secDeposit).doubleValue();
		if(convCost!=null && convCost.length()>0)
			conversionC = nf.parse(convCost).doubleValue();
		if(capitalCost!=null && capitalCost.length()>0)
			capitalC = nf.parse(capitalCost).doubleValue();
		if(addlDeposit!=null && addlDeposit.length()>0)
			additionalD = nf.parse(addlDeposit).doubleValue();

		speststd.setFixedCost(new BigDecimal(fixedC).setScale(2, BigDecimal.ROUND_HALF_UP));
		speststd.setVariableCost(new BigDecimal(variableC).setScale(2, BigDecimal.ROUND_HALF_UP));
		speststd.setSecurityDeposit(new BigDecimal(securityD).setScale(2, BigDecimal.ROUND_HALF_UP));
		speststd.setOtherCost(new BigDecimal(otherC).setScale(2, BigDecimal.ROUND_HALF_UP));
		speststd.setOtherLabourCost(new BigDecimal(otherLabourC).setScale(2, BigDecimal.ROUND_HALF_UP));
		speststd.setConversionCost(new BigDecimal(conversionC).setScale(2, BigDecimal.ROUND_HALF_UP));
		speststd.setCapitalCost(new BigDecimal(capitalC).setScale(2, BigDecimal.ROUND_HALF_UP));
		speststd.setAddlDeposit(new BigDecimal(additionalD).setScale(2, BigDecimal.ROUND_HALF_UP));
		speststd.setLabourCost(new BigDecimal(labourC).setScale(2, BigDecimal.ROUND_HALF_UP));
		speststd.setTransportCost(new BigDecimal(transportC).setScale(2, BigDecimal.ROUND_HALF_UP));
		speststd.setOverheadCost(new BigDecimal(overheadC).setScale(2, BigDecimal.ROUND_HALF_UP));
		speststd.setDamageCost(new BigDecimal(damageC).setScale(2, BigDecimal.ROUND_HALF_UP));
		speststd.setBoardCharge(new BigDecimal(boardC).setScale(2, BigDecimal.ROUND_HALF_UP));
		speststd.setContingencyCost(new BigDecimal(contingencyC).setScale(2, BigDecimal.ROUND_HALF_UP));

		speststd.setTemporaryDeposit(new BigDecimal(0));
		speststd.setBoardChargePresent(new BigDecimal(0));
		speststd.setContingencyPresent(new BigDecimal(0));
		
		double totalC = fixedC+variableC+securityD+otherC+otherLabourC+conversionC+capitalC+additionalD
		+labourC+transportC+overheadC+damageC+boardC+contingencyC;
		speststd.setTotalCost(new BigDecimal(totalC).setScale(2, BigDecimal.ROUND_HALF_UP));
				
		EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
		//estimateEjb.update(pcesthtt, speststd, insertList, updateList,insertLabList,updateLabList);
		
		ApplicationEjb applicationEJB = new ApplicationEjb(getSessionKey("region"));
		Application application = applicationEJB.findByApplicationNo(applicationNo);
		setApplicantData(application);
		setWiringData(application);
	}
	
	
	
	//set the application data to the form fields
	private void setApplicantData(Application application)
	{
		try
		{
			ApplicantEjb applicantEJB = new ApplicantEjb(getSessionKey("region"));
			Applicant applicant = applicantEJB.findById(application.getIdNo());
			applicantName = applicant.getFirstName()+" "+applicant.getLastName();
			companyName = applicant.getCompanyName();
			java.util.Date appDate = application.getSubmitDate();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			applicantDate = sdf.format(appDate);
			description = application.getDescription();
			idNo = applicant.getIdNo();
			applicationId = application.getId().getApplicationId();
			
			appSubTypeDB = application.getApplicationSubType();
			appSubType = ApplicationSubType.getApplicationTypeDesc(appSubTypeDB);
			if(application.getDuration()!=null)
				durationDB = application.getDuration().doubleValue();
			if(appSubTypeDB.equals(ApplicationSubType.CONS_2_YRS) 
				|| appSubTypeDB.equals(ApplicationSubType.CONS_HV)
				|| appSubTypeDB.equals(ApplicationSubType.CONS_LV))
			{
				if(application.getDurationType().equals("D"))
					duration = application.getDuration().toString()+" Days";
				else if(application.getDurationType().equals("M"))
					duration = application.getDuration().toString()+" Months";
				else if(application.getDurationType().equals("Y"))
					duration = application.getDuration().toString()+" Years";
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	//set wiring details to the form fields
	private void setWiringData(Application application)
	{
		try
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
			if(phaseInt==1)
				phase = "SINGLE";
			else
				phase = "THREE";
			connectionTypeInt = wld.getConnectionType().intValue();
			connectionType = wld.getConnectionType().toString()+" A";
			connectionTypeDb = Integer.toString(connectionTypeInt);
			tariffCode = wld.getTariffCode();
			
			tariffCategory = wld.getTariffCatCode();
			streetAddress = wld.getServiceStreetAddress();
			suburb = wld.getServiceSuburb();
			city = wld.getServiceCity();
			if(wld.getServicePostalCode()!=null)
				postalCode = wld.getServicePostalCode().toString();
			address = streetAddress+", "+suburb+", "+city;
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	//set estimate details to the form fields
	private void setEstimateData(Pcesthmt pcesthmt,String appNo) throws ParseException
	{
		try
		{
			nf.setGroupingUsed(true);
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			if(pcesthmt!=null)
			{
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				sessionMap.put("IsNewEstimate", "N");
				jobDesc = pcesthmt.getDescr();
				categoryCode = pcesthmt.getCatCd();
				if(categoryCode!=null)
					categoryCode = categoryCode.trim();
				String fundSource = pcesthmt.getFundSource();
				String fundId = pcesthmt.getFundId();
				if(fundSource!=null)
					fundSource = fundSource.trim();
				if(fundId!=null)
					fundId = fundId.trim();
				this.fundSourceID = fundSource+"###"+fundId;
				
				EstimateStatus es = new EstimateStatus();
				estimateStatus = pcesthmt.getStatus().toString();
				estimateStatusDesc = es.searchStatus(pcesthmt.getStatus().intValue());
				SpeststdPK speststdPK = new SpeststdPK();
				speststdPK.setDeptId(costCenterNo);
				speststdPK.setEstimateNo(appNo);
			
				/*
				SpeststdEjb speststdEJB = new SpeststdEjb(getSessionKey("region"));
				Speststd speststd = speststdEJB.findById(speststdPK);
				System.out.println("hi 12");
				estimatedDate = sdf.format(pcesthmt.getEtimateDt());
				lineLength = speststd.getLineLength().toString();
				loopLength = speststd.getLoopLength().toString();
				poleNo = speststd.getPoleNo().toString();
				distanceToServicePlace = speststd.getServiceDistance().toString();
				sinNo = speststd.getSinNo();
				spans = speststd.getSpan().toString();
				stayNo = speststd.getStayNo().toString();
				strutsNo = speststd.getStrutNo().toString();
				transColor = speststd.getTransformColor();
				totalDetailCost = nf.format(pcesthmt.getStdCost());
				cableType = speststd.getCableType();
				cableTypeDis = speststd.getCableType();
				taxAmount = "0.00";
				conductorType = speststd.getConductorType();
				isLoopService = speststd.getIsLoopService();
				wiringType = speststd.getWiringType();
				if(speststd.getConductorLength()!=null)
					conductorLength = speststd.getConductorLength().toString();
				if(speststd.getServiceLength()!=null)
					serviceLength = speststd.getServiceLength().toString();
				if(speststd.getConversionLength()!=null)
					conversionLength = speststd.getConversionLength().toString();
				if(speststd.getConversionLength2p()!=null)
					conversionLength2P = speststd.getConversionLength2p().toString();
				if(speststd.getSecondCircuitLength()!=null)
					circuitLength = speststd.getSecondCircuitLength().toString();
				if (speststd.getIsStandardVc()!=null){
					isStandardVC=speststd.getIsStandardVc().toString();
				}else{
					isStandardVC="Y";
				}*/
			
					
				//set material costs
				JobEjb jobEjb = new JobEjb(getSessionKey("region"));
				System.out.println();
				/*categorisedMatList = jobEjb.getMaterialGrid(appNo, "510.20");
				LinkedHashMap matMap = new LinkedHashMap();
				Iterator it = categorisedMatList.iterator();
				ArrayList<String> savedMat = new ArrayList<String>();
							
				while(it.hasNext())
				{
					MaterialGrid grid = (MaterialGrid)it.next();
					matMap.put(grid.getResCd().trim(), grid);
					savedMat.add(grid.getResCd().trim());
				}
				sessionMap.put("SelectMaterialList",matMap);
				sessionMap.put("SavedMaterialList",savedMat);*/
				FAMatFeatureGrid matGrid1 = new FAMatFeatureGrid("F0250","TRANSFORMER OUTDOOR 3 PHASE 11KV/LT - 160 KVA","TRANS","Transformer","MNT_TYPE","Mount Type",null);
				FAMatFeatureGrid matGrid2 = new FAMatFeatureGrid("F0250","TRANSFORMER OUTDOOR 3 PHASE 11KV/LT - 160 KVA","TRANS","Transformer","SERIAL","Serial Number",null);
				FAMatFeatureGrid matGrid3 = new FAMatFeatureGrid("F0250","TRANSFORMER OUTDOOR 3 PHASE 11KV/LT - 160 KVA","TRANS","Transformer","TR_MAKER","Make of Transformer",null);
				/*FAMatFeatureGrid matGrid4 = new FAMatFeatureGrid("F0251","TRANSFORMER OUTDOOR 3 PHASE 11KV/LT - 160 KVA","TRANS","Transformer","MNT_TYPE","Mount Type",null);
				FAMatFeatureGrid matGrid5 = new FAMatFeatureGrid("F0251","TRANSFORMER OUTDOOR 3 PHASE 11KV/LT - 160 KVA","TRANS","Transformer","SERIAL","Serial Number",null);
				FAMatFeatureGrid matGrid6 = new FAMatFeatureGrid("F0251","TRANSFORMER OUTDOOR 3 PHASE 11KV/LT - 160 KVA","TRANS","Transformer","TR_MAKER","Make of Transformer",null);
				*/
				faMatFeatureList = new ArrayList<FAMatFeatureGrid>();
				faMatFeatureList.add(matGrid1);
				faMatFeatureList.add(matGrid2);
				faMatFeatureList.add(matGrid3);
				/*faMatFeatureList.add(matGrid4);
				faMatFeatureList.add(matGrid5);
				faMatFeatureList.add(matGrid6);*/
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	private void setEstimateCostData(Pcesthtt pcesthtt)
	{
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		
		SpeststdPK speststdPK = new SpeststdPK();
		speststdPK.setDeptId(costCenterNo);
		speststdPK.setEstimateNo(applicationNo);
		
		SpeststdEjb speststdEJB = new SpeststdEjb(getSessionKey("region"));
		Speststd speststd = speststdEJB.findById(speststdPK);
		if(speststd.getCapitalCost()!=null)
			capitalCost = nf.format(speststd.getCapitalCost());
		fixedCost = nf.format(speststd.getFixedCost());
		secDeposit = nf.format(speststd.getSecurityDeposit());
		variableCost = nf.format(speststd.getVariableCost());
		otherCost = nf.format(speststd.getOtherCost());
		if(speststd.getOtherLabourCost()!=null)
			otherLabourCost = nf.format(speststd.getOtherLabourCost());
		if(speststd.getConversionCost()!=null)
			convCost = nf.format(speststd.getConversionCost());
		if(speststd.getAddlDeposit()!=null)
			addlDeposit = nf.format(speststd.getAddlDeposit());
		taxAmount = "0.00";
		totalCost = nf.format(speststd.getTotalCost());
		subTotal = nf.format(new Float(speststd.getFixedCost().floatValue()+speststd.getVariableCost().floatValue()));
			
		
	}
	
	
	public void setLoggedData() 
	{
		log.info( getSession());
		setLoggedInUserId(getSessionKey("userName"));
		setCostCenterNo(getSessionKey("costCenterNo"));
		setCostCenterName(getSessionKey("costCenterName"));
		setLoggedInUserLevel(getSession().get("userRole").toString());
		region = getSessionKey("region");
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
		if(isViewOnly!=null && isViewOnly.equals("true"))
			path = path+"View Estimate";
		else if(isModify!=null && isModify.equals("true"))
			path = path+"Modify Estimate";
		else if(isUndoReject!=null && isUndoReject.equals("true"))
			path = path+"Undo Rejected Estimate";
		else if(isApprove!=null && isApprove.equals("true"))
			path = path+"Approve/Reject Estimate";
		else if(isPrint!=null && isPrint.equals("true"))
			path = path+"Print Estimate";
		else
			path = path+"New Estimate";
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
		this.applicationNo = applicationNo.trim();
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
	/*public String getLb() {
		return lb;
	}
	public void setLb(String lb) {
		this.lb = lb;
	}*/
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
	
		
	
	
	public String[] getTxtSelQty() {
		return txtSelQty;
	}

	public void setTxtSelQty(String[] txtSelQty) {
		this.txtSelQty = txtSelQty;
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

	

	public String getCapitalCost() {
		return capitalCost;
	}


	public void setCapitalCost(String capitalCost) {
		this.capitalCost = capitalCost;
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

	

	

	public String getWiringType() {
		return wiringType;
	}

	public void setWiringType(String wiringType) {
		this.wiringType = wiringType;
	}

	public String getIsViewOnly() {
		return isViewOnly;
	}

	public void setIsViewOnly(String isViewOnly) {
		this.isViewOnly = isViewOnly;
	}

	public String getIsModify() {
		return isModify;
	}

	public void setIsModify(String isModify) {
		this.isModify = isModify;
	}

	public String getEstimateExist() {
		return estimateExist;
	}

	public void setEstimateExist(String estimateExist) {
		this.estimateExist = estimateExist;
	}

	public String[] getTxtCusQty() {
		return txtCusQty;
	}

	public void setTxtCusQty(String[] txtCusQty) {
		this.txtCusQty = txtCusQty;
	}

	public List<FundSource> getFundSourceList() {
		return fundSourceList;
	}

	public void setFundSourceList(List<FundSource> fundSourceList) {
		this.fundSourceList = fundSourceList;
	}

	public String getFundSourceID() {
		return fundSourceID;
	}

	public void setFundSourceID(String fundSourceID) {
		this.fundSourceID = fundSourceID;
	}

	public String getIsUndoReject() {
		return isUndoReject;
	}

	public void setIsUndoReject(String isUndoReject) {
		this.isUndoReject = isUndoReject;
	}

	public List<String> getEstNoList() {
		return estNoList;
	}

	public void setEstNoList(List<String> estNoList) {
		this.estNoList = estNoList;
	}

	public String getCopyEstimateNo() {
		return copyEstimateNo;
	}

	public void setCopyEstimateNo(String copyEstimateNo) {
		this.copyEstimateNo = copyEstimateNo;
	}

	public String getEstNoPrefix() {
		return estNoPrefix;
	}

	public void setEstNoPrefix(String estNoPrefix) {
		this.estNoPrefix = estNoPrefix;
	}

	public String getEstNoSeq() {
		return estNoSeq;
	}

	public void setEstNoSeq(String estNoSeq) {
		this.estNoSeq = estNoSeq;
	}

	public String getIsApprove() {
		return isApprove;
	}

	public void setIsApprove(String isApprove) {
		this.isApprove = isApprove;
	}

	public String getAppSubType() {
		return appSubType;
	}

	public void setAppSubType(String appSubType) {
		this.appSubType = appSubType;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public String getFirstYear() {
		return firstYear;
	}

	public void setFirstYear(String firstYear) {
		this.firstYear = firstYear;
	}

	public String getNextYear() {
		return nextYear;
	}

	public void setNextYear(String nextYear) {
		this.nextYear = nextYear;
	}

	public String getAppSubTypeDB() {
		return appSubTypeDB;
	}

	public void setAppSubTypeDB(String appSubTypeDB) {
		this.appSubTypeDB = appSubTypeDB;
	}

	public double getDurationDB() {
		return durationDB;
	}

	public void setDurationDB(double durationDB) {
		this.durationDB = durationDB;
	}

	public String getConductorType() {
		return conductorType;
	}

	public void setConductorType(String conductorType) {
		this.conductorType = conductorType;
	}

	public String getIsLineLenEntered() {
		return isLineLenEntered;
	}

	public void setIsLineLenEntered(String isLineLenEntered) {
		this.isLineLenEntered = isLineLenEntered;
	}

	public List<String> getCategoryCodeList() {
		return categoryCodeList;
	}

	public void setCategoryCodeList(List<String> categoryCodeList) {
		this.categoryCodeList = categoryCodeList;
	}

	public String getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}

	public String getEstimatedDate() {
		return estimatedDate;
	}

	public void setEstimatedDate(String estimatedDate) {
		this.estimatedDate = estimatedDate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getConductorLength() {
		return conductorLength;
	}

	public void setConductorLength(String conductorLength) {
		this.conductorLength = conductorLength;
	}

	public String getServiceLength() {
		return serviceLength;
	}

	public void setServiceLength(String serviceLength) {
		this.serviceLength = serviceLength;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}


	public String[] getTxtSelLabQty() {
		return txtSelLabQty;
	}


	public void setTxtSelLabQty(String[] txtSelLabQty) {
		this.txtSelLabQty = txtSelLabQty;
	}


	public String[] getTxtSelLabCost() {
		return txtSelLabCost;
	}


	public void setTxtSelLabCost(String[] txtSelLabCost) {
		this.txtSelLabCost = txtSelLabCost;
	}


	public String getTotalLabCost() {
		return totalLabCost;
	}


	public void setTotalLabCost(String totalLabCost) {
		this.totalLabCost = totalLabCost;
	}


	public String[] getChkSelLabCode() {
		return chkSelLabCode;
	}


	public void setChkSelLabCode(String[] chkSelLabCode) {
		this.chkSelLabCode = chkSelLabCode;
	}


	public String getIsRemoveLabClicked() {
		return isRemoveLabClicked;
	}


	public void setIsRemoveLabClicked(String isRemoveLabClicked) {
		this.isRemoveLabClicked = isRemoveLabClicked;
	}


	

	public String getLoggedInUserLevel() {
		return loggedInUserLevel;
	}


	public void setLoggedInUserLevel(String loggedInUserLevel) {
		this.loggedInUserLevel = loggedInUserLevel;
	}


	public String getIsRoughEstimate() {
		return isRoughEstimate;
	}


	public void setIsRoughEstimate(String isRoughEstimate) {
		this.isRoughEstimate = isRoughEstimate;
	}


	public String getOtherLabourCost() {
		return otherLabourCost;
	}


	public void setOtherLabourCost(String otherLabourCost) {
		this.otherLabourCost = otherLabourCost;
	}


	public String getInfoMessage() {
		return infoMessage;
	}


	public void setInfoMessage(String infoMessage) {
		this.infoMessage = infoMessage;
	}


	public String getIsLoopOrCable() {
		return isLoopOrCable;
	}


	public void setIsLoopOrCable(String isLoopOrCable) {
		this.isLoopOrCable = isLoopOrCable;
	}


	public String getCableType() {
		return cableType;
	}


	public void setCableType(String cableType) {
		this.cableType = cableType;
	}


	public List<ListObject> getCableTypeList() {
		return cableTypeList;
	}


	public void setCableTypeList(List<ListObject> cableTypeList) {
		this.cableTypeList = cableTypeList;
	}


	public String getCableTypeDis() {
		return cableTypeDis;
	}


	public void setCableTypeDis(String cableTypeDis) {
		this.cableTypeDis = cableTypeDis;
	}


	public String getConvCost() {
		return convCost;
	}


	public void setConvCost(String convCost) {
		this.convCost = convCost;
	}


	public String getConversionLength() {
		return conversionLength;
	}


	public void setConversionLength(String conversionLength) {
		this.conversionLength = conversionLength;
	}
	
	
	public String getConversionLength2P() {
		return conversionLength2P;
	}


	public void setConversionLength2P(String conversionLength2P) {
		this.conversionLength2P = conversionLength2P;
	}


	public String getLabourRate() {
		return labourRate;
	}


	public void setLabourRate(String labourRate) {
		this.labourRate = labourRate;
	}


	public String getTransportRate() {
		return transportRate;
	}


	public void setTransportRate(String transportRate) {
		this.transportRate = transportRate;
	}


	public String getOverheadRate() {
		return overheadRate;
	}


	public void setOverheadRate(String overheadRate) {
		this.overheadRate = overheadRate;
	}


	public String[] getTxtSelLabHrs() {
		return txtSelLabHrs;
	}


	public void setTxtSelLabHrs(String[] txtSelLabHrs) {
		this.txtSelLabHrs = txtSelLabHrs;
	}


	public String getTotalLabHrs() {
		return totalLabHrs;
	}


	public void setTotalLabHrs(String totalLabHrs) {
		this.totalLabHrs = totalLabHrs;
	}


	public String getEstimateStatus() {
		return estimateStatus;
	}


	public void setEstimateStatus(String estimateStatus) {
		this.estimateStatus = estimateStatus;
	}


	public String getEstimateStatusDesc() {
		return estimateStatusDesc;
	}


	public void setEstimateStatusDesc(String estimateStatusDesc) {
		this.estimateStatusDesc = estimateStatusDesc;
	}


	public String[] getTxtSelCusCost() {
		return txtSelCusCost;
	}


	public void setTxtSelCusCost(String[] txtSelCusCost) {
		this.txtSelCusCost = txtSelCusCost;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getTotalDetailCost() {
		return totalDetailCost;
	}


	public void setTotalDetailCost(String totalDetailCost) {
		this.totalDetailCost = totalDetailCost;
	}


	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}


	public String getAddlDeposit() {
		return addlDeposit;
	}


	public void setAddlDeposit(String addlDeposit) {
		this.addlDeposit = addlDeposit;
	}


	public String getApplicationId() {
		return applicationId;
	}


	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}


	public String getIdNo() {
		return idNo;
	}


	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	
	public InputStream getFileInputStream() {
		return fileInputStream;
	}


	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}


	public String getCircuitLength() {
		return circuitLength;
	}


	public void setCircuitLength(String circuitLength) {
		this.circuitLength = circuitLength;
	}


	public String getIsStandardVC() {
		return isStandardVC;
	}


	public void setIsStandardVC(String isStandardVC) {
		this.isStandardVC = isStandardVC;
	}


	public List<NameValueList> getFaSubCatList() {
		return faSubCatList;
	}


	public void setFaSubCatList(List<NameValueList> faSubCatList) {
		this.faSubCatList = faSubCatList;
	}


	public Collection<FAMaterialGrid> getUnCategorisedMatList() {
		return unCategorisedMatList;
	}


	public void setUnCategorisedMatList(
			Collection<FAMaterialGrid> unCategorisedMatList) {
		this.unCategorisedMatList = unCategorisedMatList;
	}


	public Collection<FAMaterialGrid> getCategorisedMatList() {
		return categorisedMatList;
	}


	public void setCategorisedMatList(Collection<FAMaterialGrid> categorisedMatList) {
		this.categorisedMatList = categorisedMatList;
	}


	public List<NameValueList> getFaManCatList() {
		return faManCatList;
	}


	public void setFaManCatList(List<NameValueList> faManCatList) {
		this.faManCatList = faManCatList;
	}


	public String getMoveMatCode() {
		return moveMatCode;
	}


	public void setMoveMatCode(String moveMatCode) {
		this.moveMatCode = moveMatCode;
	}


	public String getMoveManCatCode() {
		return moveManCatCode;
	}


	public void setMoveManCatCode(String moveManCatCode) {
		this.moveManCatCode = moveManCatCode;
	}


	public String getMoveQty() {
		return moveQty;
	}


	public void setMoveQty(String moveQty) {
		this.moveQty = moveQty;
	}


	public String getRemoveMatCode() {
		return removeMatCode;
	}


	public void setRemoveMatCode(String removeMatCode) {
		this.removeMatCode = removeMatCode;
	}


	public String getRemoveManCatCode() {
		return removeManCatCode;
	}


	public void setRemoveManCatCode(String removeManCatCode) {
		this.removeManCatCode = removeManCatCode;
	}


	public Collection<FAMainCategoryGrid> getFaManCatFeatList() {
		return faManCatFeatList;
	}


	public void setFaManCatFeatList(Collection<FAMainCategoryGrid> faManCatFeatList) {
		this.faManCatFeatList = faManCatFeatList;
	}


	public String[] getManCatFeatValue() {
		return manCatFeatValue;
	}


	public void setManCatFeatValue(String[] manCatFeatValue) {
		this.manCatFeatValue = manCatFeatValue;
	}


	public Collection<FAMatFeatureGrid> getFaMatFeatureList() {
		return faMatFeatureList;
	}


	public void setFaMatFeatureList(Collection<FAMatFeatureGrid> faMatFeatureList) {
		this.faMatFeatureList = faMatFeatureList;
	}


	public String[] getMatFeatValue() {
		return matFeatValue;
	}


	public void setMatFeatValue(String[] matFeatValue) {
		this.matFeatValue = matFeatValue;
	}

	public String getIsVerify() {
		return isVerify;
	}

	public void setIsVerify(String isVerify) {
		this.isVerify = isVerify;
	}

	public String getIsTransfer() {
		return isTransfer;
	}

	public void setIsTransfer(String isTransfer) {
		this.isTransfer = isTransfer;
	}


	



	


	


	
	
	
	
	

	
	
	

}//End of class
