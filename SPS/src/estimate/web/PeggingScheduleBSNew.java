package estimate.web;

import inventory.service.InwrhmtmEjb;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Map.Entry;
import java.util.Set;

import masters.service.ProvinceDetailsMasterEjb;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import piv.model.PivDetail;
import piv.model.PivDetailPK;
import piv.model.TempTb;
import piv.service.PivDetailEjb;
import print.standardEstimate.DetailEstimatePrintDetails;
import reports.web.report;
import security.service.SecurityEjb;

import util.common.ApplicationStatus;
import util.common.AppointmentStatus;
import util.common.EstimateStatus;
import util.common.Format;
import util.common.JobProcessStatus;
import util.common.Path;
import util.common.StandardEstimateStatus;
import application.model.Application;
import application.model.ApplicationReference;
import application.model.WiringLandDetailCon;
import application.model.WiringLandDetailConPK;
import application.service.ApplicationEjb;
import application.service.ApplicationReferenceEjb;
import application.service.ApplicationTransactionEjb;
import application.service.WiringLandDetailConEjb;
import application.service.WiringLandDetailEjb;
import estimate.dto.ApproveDetails;
import estimate.dto.DetailEstimateDTO;
import estimate.dto.EstimateDetails;
import estimate.dto.Norm;
import estimate.dto.PegItemDTO;
import estimate.model.Approval;
import estimate.model.EstimateReference;
import estimate.model.Pcestdtt;
import estimate.model.PcestdttPK;
import estimate.model.Pcesthtt;
import estimate.model.PcesthttPK;
import estimate.model.Pcfunddm;
import estimate.model.Pegschdmt;
import estimate.model.PegschdmtPK;
import estimate.model.SpPointdmt;
import estimate.model.SpestedyCons;
import estimate.model.SpestedyConsPK;
import estimate.model.Spstdestdmt;
import estimate.model.SpstdestdmtPK;
import estimate.model.Spstdesthmt;
import estimate.model.SpstdesthmtPK;
import estimate.service.EstimateEjb;
import estimate.service.EstimateReferenceEjb;
import estimate.service.PcestdttEjb;
import estimate.service.PcesthttEjb;
import estimate.service.PegschdmtEjb;
import estimate.service.SpestedyConEjb;
import estimate.service.SpestedyEjb;
import estimate.service.SpstdesthmtEjb;


import com.opensymphony.xwork2.ActionSupport;

import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import job.model.JobInfo;
import job.model.Pcesthmt;
import job.model.Spestcnd;
import job.model.SpestcndPK;
import job.model.Spestcnt;
import job.model.SpestcntPK;
import job.service.JobEjb;
import job.service.PcesthmtEjb;
import job.service.SpestcntEjb;
import util.common.Constants;;

@SuppressWarnings("serial")
public class PeggingScheduleBSNew extends ActionSupport implements SessionAware, ParameterAware {
	private String userId;
	private String password;
	private String region;
	private String costCenterNo;
	private String costCenterName;
	private String estimateNo;
	private String fileRef;
	private String pivNo;
	private String wareHouseId;
	private String amount;
	private String pivDate;
	private String estimateDate;
	private String rejectReason;
	private String fundsou;
	private String rebate;
	private String smcType;
	private String branchType;
	private String categoryId;
	private String fundid;
	private String description;
	private String totalCost;
	private String lblError=null;
	private String lblSuccess=null;
	private static final String MSG_ERROR="ERROR";
	private static final String MSG_INFO="INFO";
	private static final String MSG_DONE="DONE";
	private String errorMessage;
	private static final String MSG_NONE="NONE";
	
	private String messageType=MSG_NONE;
	private List<String> listuserName = new ArrayList<String>();
	private List<String> listuserNameNew = new ArrayList<String>();
	
	private String appointedUserName;
	private List<String> selectedPegItemList=null;
	
	Map<String,PegItemDTO> selectedPegschuleItems=null;
	Map<String,DetailEstimateDTO> selectedAdditionalMaterials=null;
	Map<String,DetailEstimateDTO> addedItemsFromPegSchedule=null;
	Map<String,DetailEstimateDTO> allSelectedAdditionalResoMap=null;
	
	Map<String,DetailEstimateDTO> resourceMap =null;
	
	
	
	Map<String,DetailEstimateDTO> updatedDetailsMap =null;
	Map<String,DetailEstimateDTO> deletedDetailsMap =null;
	Map<String,DetailEstimateDTO> alreadyAddedDetails = null;
	EstimateDetails dto = null;
	List<String> errorMessages = new ArrayList<String>();
	NumberFormat nf = NumberFormat.getInstance();
	
	private String loggedInUserId;
	private String loggedInUserLevel;
	private String postUserName;
	private String postUserNameNew;
	
	private String applicationType;
	
	private String divSec;
	private String area;
	private String eCSC;
	private String district;
	private String esname;
	
	String listRescd ;
   	String listResName ;
   	String listQuantity ;
   	String listUom ;
   	String listUnitPrice ;
   	String listEstimateCost;
   	String totalMaterialCost ;
   	String totalWayleaves ;
   	String totalLabour ;
   	String totalSubsistance ;
   	String totalContingencies ;
   	String totalOverhead;
	String totalEstimatedCapitalCost;
   	
	private String applicationId;
	private Map<String, Object> session;
	//print variables
	private  String selectCategoryString = "----Select Category-----";
	private String selectedReport;
	private static final String viewPath="Reports>Select Report";
	private String path;
	private InputStream fileInputStream;
	private InputStream fileInputStream1;

	private Map <String, String[]> parameters;
	private List<String> listContractor;
	
	private List<String> listContractorID;
	private String contractorID;
	private String projectNo;
	private String contractor ;
	
	private String[] DetailsList;

	
	private String approvalComment;
	public String init()
	{
		errorMessages.clear();
		setInfoFromMainMenu();
		loadInitEstimationRefNumbers();
		loadAllEstimationRefNumbers();
		loadWareHouse();
		loadFundSources();
		return SUCCESS;
	}
	
	public String initUpdate()
	{
		errorMessages.clear();
		setInfoFromMainMenu();
		loadEstimationRefNumbers();//to load Application status to be changed
		loadWorkEstimationRefNumbers();
		loadAllEstimationRefNumbers();
		loadWareHouse();
		loadFundSources();
		loaduserIds();
		return SUCCESS;
	}
	public String initPriceUpdate()
	{
		errorMessages.clear();
		setInfoFromMainMenu();
		loadEstimationRefNumbers();//to load Application status to be changed
		loadWorkEstimationRefNumbers();
		loadAllEstimationRefNumbers();
		loadWareHouse();
		loadFundSources();
		return SUCCESS;
	}
	public String initItembyitem()
	{
		errorMessages.clear();
		setInfoFromMainMenu();
		loadInitEstimationRefNumbers();
		loadAllEstimationRefNumbers();
		loadWareHouse();
		loadFundSources();
		return SUCCESS;
	}
	
	public String initUpdateItembyitem()
	{
		errorMessages.clear();
		setInfoFromMainMenu();
		loadEstimationRefNumbers();//to load Application status to be changed
		loadWorkEstimationRefNumbers();
		loadAllEstimationRefNumbers();
		loadWareHouse();
		loadFundSources();
		return SUCCESS;
	}
	public String initdeleteDetailEstimation()
	{
		errorMessages.clear();
		setInfoFromMainMenu();
		loadEstimationRefNumbers();//to load Application status to be changed
		loadWorkEstimationRefNumbers();
		loadAllEstimationRefNumbers();
		loadWareHouse();
		loadFundSources();
		return SUCCESS;
	}
	public String initViewItembyitem()
	{
		errorMessages.clear();
		setInfoFromMainMenu();
		//loadEstimationRefNumbers();//to load Application status to be changed
		loadWorkEstimationRefNumbersForView();
		//loadAllEstimationRefNumbers();
		loadWareHouse();
		loadFundSources();
		return SUCCESS;
	}
	public String initPrintItembyitem()
	{
		errorMessages.clear();
		setInfoFromMainMenu();
		//loadEstimationRefNumbers();//to load Application status to be changed
		loadWorkEstimationRefNumbersForPrint();
		//loadAllEstimationRefNumbers();
		loadWareHouse();
		loadFundSources();
		return SUCCESS;
	}
	public String initContractorAllocation()
	{
		errorMessages.clear();
		loadContractorList();
		loadUsers();
		
		loadWareHouse();
		loadFundSources();
		return SUCCESS;
	}
	public void setInfoFromMainMenu(){

		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
	
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		String branchType= (String) request.getSession().getAttribute("branchType");
		setCostCenterNo(costCenterNo);
		setBranchType(branchType);
		setUserId(userId);
		setRegion(region);
		List<String> usersList = new ArrayList<String>();
		usersList.add("ES");
		usersList.add("EE");
		
		SecurityEjb secejb=new SecurityEjb(region);
		List<String> userList = secejb.getUserList(costCenterNo,usersList);
		//DW
		
		//if(costCenterNo.equals("452.40")|| costCenterNo.equals("452.50")|| costCenterNo.equals("452.80")||costCenterNo.equals("452.90")||costCenterNo.equals("453.00")||costCenterNo.equals("453.10")||costCenterNo.equals("453.20")||costCenterNo.equals("453.30")||costCenterNo.equals("453.40")|| costCenterNo.equals("454.00")||costCenterNo.equals("454.10")||costCenterNo.equals("454.20")||costCenterNo.equals("454.50")||costCenterNo.equals("455.00")||costCenterNo.equals("455.10")||costCenterNo.equals("455.20")||costCenterNo.equals("455.30")||costCenterNo.equals("455.40")|| costCenterNo.equals("455.50")||costCenterNo.equals("456.00")||costCenterNo.equals("456.10")|| costCenterNo.equals("456.20")||costCenterNo.equals("456.30")||costCenterNo.equals("457.00")||costCenterNo.equals("457.10")||costCenterNo.equals("457.20")|| costCenterNo.equals("457.30")||costCenterNo.equals("450.00")|| costCenterNo.equals("450.80")||costCenterNo.equals("451.00")||costCenterNo.equals("451.10")||costCenterNo.equals("451.20")||costCenterNo.equals("451.30")||costCenterNo.equals("451.40")||costCenterNo.equals("451.50")||costCenterNo.equals("451.90") || costCenterNo.equals("452.00") || costCenterNo.equals("452.10")||costCenterNo.equals("452.20")||costCenterNo.equals("452.30")){
			//costCenterNo = costCenterNo.substring(0, 3).concat(".00");
			
			//userList = secejb.getUserList(costCenterNo,usersList);
			
		//}
		Iterator<String> usit = userList.iterator();
		
		listuserName = new ArrayList<String>();
		while (usit.hasNext()) {        	 
	        	String esUser=usit.next();
	        	listuserName.add(esUser);		       	        	        	
		} 
		
	}
	
	private void loadUsers(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		List<String> usersList = new ArrayList<String>();
		usersList.add("ES");
		usersList.add("EE");
		
		SecurityEjb secejb=new SecurityEjb(region);
		List<String> userList = secejb.getUserList(costCenterNo,usersList);
		//DW
		//if(costCenterNo.equals("452.40")|| costCenterNo.equals("452.50")|| costCenterNo.equals("452.80")||costCenterNo.equals("452.90")||costCenterNo.equals("453.00")||costCenterNo.equals("453.10")||costCenterNo.equals("453.20")||costCenterNo.equals("453.30")||costCenterNo.equals("453.40")|| costCenterNo.equals("454.00")||costCenterNo.equals("454.10")||costCenterNo.equals("454.20")||costCenterNo.equals("454.50")||costCenterNo.equals("455.00")||costCenterNo.equals("455.10")||costCenterNo.equals("455.20")||costCenterNo.equals("455.30")||costCenterNo.equals("455.40")|| costCenterNo.equals("455.50")||costCenterNo.equals("456.00")||costCenterNo.equals("456.10")|| costCenterNo.equals("456.20")||costCenterNo.equals("456.30")||costCenterNo.equals("457.00")||costCenterNo.equals("457.10")||costCenterNo.equals("457.20")|| costCenterNo.equals("457.30")||costCenterNo.equals("450.00")|| costCenterNo.equals("450.80")||costCenterNo.equals("451.00")||costCenterNo.equals("451.10")||costCenterNo.equals("451.20")||costCenterNo.equals("451.30")||costCenterNo.equals("451.40")||costCenterNo.equals("451.50")||costCenterNo.equals("451.90") || costCenterNo.equals("452.00") || costCenterNo.equals("452.10")||costCenterNo.equals("452.20")||costCenterNo.equals("452.30")){
			//costCenterNo = costCenterNo.substring(0, 3).concat(".00");
			
			//userList = secejb.getUserList(costCenterNo,usersList);
			
		//}
		
		Iterator<String> usit = userList.iterator();
		
		listuserName = new ArrayList<String>();
		while (usit.hasNext()) {        	 
	        	String esUser=usit.next();
	        	listuserName.add(esUser);		       	        	        	
		} 
	}
	private void loadContractorList(){
		
		
		SpestcntEjb ejb=new SpestcntEjb(getSessionKey("region"));	
		List<Spestcnt> spestcntList=new ArrayList<Spestcnt>();	
		listContractor = new ArrayList<String>();
		listContractorID = new ArrayList<String>();
		String costCenter = getSessionKey("costCenterNo");
		spestcntList=ejb.getContractorByStatus(costCenter, "1");
		Iterator<Spestcnt> it1 = spestcntList.iterator();
		
		 while (it1.hasNext()) {  
			 Spestcnt spestcnt1=it1.next();
			 listContractor.add(spestcnt1.getCode());	
			 listContractorID.add(spestcnt1.getCode());	
					        	        	
	       }   				
	}
	
	public void loadInitEstimationRefNumbers(){
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			List<String> estimationRefNos = null;
			SpestedyConEjb estimateEjb = new SpestedyConEjb(getRegion());
			String userRole= (String) request.getSession().getAttribute("userRole");
			if(userRole.equalsIgnoreCase("DEO")){
				estimationRefNos = estimateEjb.loadApplicationRefnos(null,getCostCenterNo(),null); 
			}else{
				estimationRefNos = estimateEjb.loadApplicationRefnos(getUserId(),getCostCenterNo(),null); 
			}
			//HttpServletRequest request = ServletActionContext.getRequest();
			request.getSession().setAttribute("estimationRefNos",estimationRefNos);
		}catch(Exception e){
			
		}
	
	}
	
	//Use to load construction estimates for DEO
	public void loadAllEstimationRefNumbers(){
		try{
			List<String> estimationRefNos = null;
			SpestedyConEjb estimateEjb = new SpestedyConEjb(getRegion());
			estimationRefNos = estimateEjb.loadApplicationRefnos(null,getCostCenterNo(),null); 
			HttpServletRequest request = ServletActionContext.getRequest();
			request.getSession().setAttribute("allEstimationRefNos",estimationRefNos);
		}catch(Exception e){
			
	}
		
		
		
	}
	public void loadEstimationRefNumbers(){
		try{
			List<String> estimationRefNos = null;
			SpestedyConEjb estimateEjb = new SpestedyConEjb(getRegion());
			estimationRefNos = estimateEjb.loadApplicationRefnos(getUserId(),getCostCenterNo(),AppointmentStatus.DETAIL_EST_CREATED); // to do laod Detail estimate created AppointmentStatus.DETAIL_EST_CREATED
			HttpServletRequest request = ServletActionContext.getRequest();
			request.getSession().setAttribute("estimationRefNos",estimationRefNos);
		}catch(Exception e){
			
		}
		
		
		
	}
	
	public void loadWorkEstimationRefNumbers(){
		try{
			List<String> estimateNos = new ArrayList<String>();;
			List<String> estimateNosModified =null;
			List<String> estimateNosRejected = null;
			List<String> estimateNosRejected1 = null;
			List<String> estimateNosRejected2 = null;
			List<String> estimateNosRejected3 = null;
			List<String> estimateNosRejected4 = null;
			List<String> filteredList = null;
			//EstimateEjb estimateEjb = new EstimateEjb(getRegion());
			PcesthttEjb ejb = new PcesthttEjb(getRegion());
			List<Long> status = new ArrayList<Long>();
			status.add(new Long(EstimateStatus.MODIFIED));
			status.add(new Long(EstimateStatus.EST_REJECTED));
			/*if(getCostCenterNo().equalsIgnoreCase("430.25")){
				estimateNosModified = ejb.getEstimateNoList(getCostCenterNo(), Constants.REV_NO,status,getUserId());
			}else{*/
				estimateNosModified = ejb.getEstimateNoList(getCostCenterNo(), Constants.REV_NO,status,null);
			/*}*/
			
			//estimateNosRejected = ejb.getEstimateNoList(getCostCenterNo(), Constants.REV_NO,new Long(EstimateStatus.EST_REJECTED));
			/*estimateNosRejected1 = ejb.getEstimateNoList(getCostCenterNo(), Constants.REV_NO,new Long(EstimateStatus.EST_APPROVAL_EE));
			estimateNosRejected2 = ejb.getEstimateNoList(getCostCenterNo(), Constants.REV_NO,new Long(EstimateStatus.EST_APPROVAL_CE));
			estimateNosRejected3 = ejb.getEstimateNoList(getCostCenterNo(), Constants.REV_NO,new Long(EstimateStatus.EST_APPROVAL_DGM));
			estimateNosRejected4 = ejb.getEstimateNoList(getCostCenterNo(), Constants.REV_NO,new Long(EstimateStatus.EST_POSTED));*/
			estimateNos.addAll(estimateNosModified);
			//estimateNos.addAll(estimateNosRejected);
			
			/*estimateNos.addAll(estimateNosRejected1);
			estimateNos.addAll(estimateNosRejected2);
			estimateNos.addAll(estimateNosRejected3);
			estimateNos.addAll(estimateNosRejected4);*/
			//estimationRefNos = estimateEjb.loadStandEstmatenos(getCostCenterNo(),ApplicationStatus.NEW_APPLICATION.getKey(),getRegion()); //SEND_FOR_CONSTRUCTION_MAINTENANCE
			HttpServletRequest request = ServletActionContext.getRequest();
			if(getCostCenterNo().equalsIgnoreCase("543.10") || getCostCenterNo().equalsIgnoreCase("543.40") || getCostCenterNo().equalsIgnoreCase("543.20")){
				String smcType= (String) request.getSession().getAttribute("smcType");
				filteredList = filterEstimatesList(estimateNos,smcType);
			}else{
				filteredList=estimateNos;
			}
			
			request.getSession().setAttribute("estimateNos",filteredList);
		}catch(Exception e){
			
		}
		
		
		
	}
	
	public void loadWorkEstimationRefNumbersForPrint(){
		try{
			List<String> estimateNos = new ArrayList<String>();;
			List<String> estimateNosApproveEE =null;
			List<String> estimateNosApproveCE = null;
			List<String> estimateNosApproveDGM = null;
			List<String> estimateNosApprovePosting = null;
			List<String> estimateNosApprovePosted = null;
			List<String> estimateNosModified =null;
			List<String> estimateNosRejected = null;
			List<String> filteredList = null;
			//EstimateEjb estimateEjb = new EstimateEjb(getRegion());
			PcesthttEjb ejb = new PcesthttEjb(getRegion());
			List<Long> status = new ArrayList<Long>();
			//status.add(new Long(EstimateStatus.EST_APPROVAL_EE));
			//status.add(new Long(EstimateStatus.EST_APPROVAL_CE));
			//estimateNosModified = ejb.getEstimateNoList(getCostCenterNo(), Constants.REV_NO,new Long(EstimateStatus.EST_APPROVAL_EE));
			estimateNosModified = ejb.getEstimateNoList(getCostCenterNo(), Constants.REV_NO,new Long(EstimateStatus.MODIFIED));
			estimateNosRejected = ejb.getEstimateNoList(getCostCenterNo(), Constants.REV_NO,new Long(EstimateStatus.EST_REJECTED));
			estimateNosApproveEE = ejb.getEstimateNoList(getCostCenterNo(), Constants.REV_NO,new Long(EstimateStatus.EST_APPROVAL_EE));
			estimateNosApproveCE = ejb.getEstimateNoList(getCostCenterNo(), Constants.REV_NO,new Long(EstimateStatus.EST_APPROVAL_CE));
			estimateNosApproveDGM = ejb.getEstimateNoList(getCostCenterNo(), Constants.REV_NO,new Long(EstimateStatus.EST_APPROVAL_DGM));
			estimateNosApprovePosting = ejb.getEstimateNoList(getCostCenterNo(), Constants.REV_NO,new Long(EstimateStatus.EST_APPROVED));
			estimateNosApprovePosted = ejb.getEstimateNoList(getCostCenterNo(), Constants.REV_NO,new Long(EstimateStatus.EST_POSTED));
			estimateNos.addAll(estimateNosModified);
			estimateNos.addAll(estimateNosRejected);
			estimateNos.addAll(estimateNosApproveEE);
			estimateNos.addAll(estimateNosApproveCE);
			estimateNos.addAll(estimateNosApproveDGM);
			estimateNos.addAll(estimateNosApprovePosting);
			
			estimateNos.addAll(estimateNosApprovePosted);
			//estimationRefNos = estimateEjb.loadStandEstmatenos(getCostCenterNo(),ApplicationStatus.NEW_APPLICATION.getKey(),getRegion()); //SEND_FOR_CONSTRUCTION_MAINTENANCE
			HttpServletRequest request = ServletActionContext.getRequest();
			if(getCostCenterNo().equalsIgnoreCase("543.10") || getCostCenterNo().equalsIgnoreCase("543.40") || getCostCenterNo().equalsIgnoreCase("543.20")){
				String smcType= (String) request.getSession().getAttribute("smcType");
				filteredList = filterEstimatesList(estimateNos,smcType);
			}else{
				filteredList=estimateNos;
			}
			
			request.getSession().setAttribute("estimateNos",filteredList);
		}catch(Exception e){
			
		}
		
		
		
	}
	public void loadWorkEstimationRefNumbersForView(){
		try{
			List<String> estimateNos = new ArrayList<String>();;
			List<String> estimateNosApproveEE =null;
			List<String> estimateNosApproveCE = null;
			List<String> estimateNosApproveDGM = null;
			List<String> estimateNosApprovePosting = null;
			List<String> estimateNosApprovePosted = null;
			
			List<String> filteredList = null;
			//EstimateEjb estimateEjb = new EstimateEjb(getRegion());
			PcesthttEjb ejb = new PcesthttEjb(getRegion());
			List<Long> status = new ArrayList<Long>();
			//status.add(new Long(EstimateStatus.EST_APPROVAL_EE));
			//status.add(new Long(EstimateStatus.EST_APPROVAL_CE));
			//estimateNosModified = ejb.getEstimateNoList(getCostCenterNo(), Constants.REV_NO,new Long(EstimateStatus.EST_APPROVAL_EE));
			estimateNosApproveEE = ejb.getEstimateNoList(getCostCenterNo(), Constants.REV_NO,new Long(EstimateStatus.EST_APPROVAL_EE));
			estimateNosApproveCE = ejb.getEstimateNoList(getCostCenterNo(), Constants.REV_NO,new Long(EstimateStatus.EST_APPROVAL_CE));
			estimateNosApproveDGM = ejb.getEstimateNoList(getCostCenterNo(), Constants.REV_NO,new Long(EstimateStatus.EST_APPROVAL_DGM));
			estimateNosApprovePosting = ejb.getEstimateNoList(getCostCenterNo(), Constants.REV_NO,new Long(EstimateStatus.EST_APPROVED));
			estimateNosApprovePosted = ejb.getEstimateNoList(getCostCenterNo(), Constants.REV_NO,new Long(EstimateStatus.EST_POSTED));
			estimateNos.addAll(estimateNosApproveEE);
			estimateNos.addAll(estimateNosApproveCE);
			estimateNos.addAll(estimateNosApproveDGM);
			estimateNos.addAll(estimateNosApprovePosting);
			
			estimateNos.addAll(estimateNosApprovePosted);
			//estimationRefNos = estimateEjb.loadStandEstmatenos(getCostCenterNo(),ApplicationStatus.NEW_APPLICATION.getKey(),getRegion()); //SEND_FOR_CONSTRUCTION_MAINTENANCE
			HttpServletRequest request = ServletActionContext.getRequest();
			if(getCostCenterNo().equalsIgnoreCase("543.10") || getCostCenterNo().equalsIgnoreCase("543.40") || getCostCenterNo().equalsIgnoreCase("543.20")){
				String smcType= (String) request.getSession().getAttribute("smcType");
				filteredList = filterEstimatesList(estimateNos,smcType);
			}else{
				filteredList=estimateNos;
			}
			
			request.getSession().setAttribute("estimateNos",filteredList);
		}catch(Exception e){
			
		}
		
		
		
	}
	public void loadWareHouse(){
		try{
			List<String> warehouses = null;
			EstimateEjb estimateEjb = new EstimateEjb(getRegion());
			warehouses = estimateEjb.loadWarehouses(getCostCenterNo(),getRegion()); //SEND_FOR_CONSTRUCTION_MAINTENANCE
			HttpServletRequest request = ServletActionContext.getRequest();
			request.getSession().setAttribute("warehouses",warehouses);
		}catch(Exception e){
			
		}
		
		
		
	}
	private List<String> filterEstimatesList(List<String> estimateList,String smcType){
		List<String> filteredList = new ArrayList<String>();
		for(String estimateNo: estimateList){
			if(estimateNo.contains(smcType)){
				filteredList.add(estimateNo);
			}
		}
		if(filteredList != null && filteredList.size() ==0 ){
			
		}
		return filteredList;
		
	}
	
	public void loadFundSources(){
		try{
			List<String> fundSources = null;
			EstimateEjb estimateEjb = new EstimateEjb(getRegion());
			fundSources = estimateEjb.getFundSources(getCostCenterNo(),getRegion()); //SEND_FOR_CONSTRUCTION_MAINTENANCE
			HttpServletRequest request = ServletActionContext.getRequest();
			request.getSession().setAttribute("fundsources",fundSources);
		}catch(Exception e){
			
		}
		
		
		
	}
	public String newDirectForNewEntry()
	{
		clearForm();
		setErrorMessage(null);
		/*setSuccessMessage(null);
		setInfoMessage(null);
		setLoggedData();
		this.isViewOnly = null;
		this.isModify = null;
		this.isUndoReject = "true";
		this.isApprove = null;
		this.isPrint = null;
		this.isRoughEstimate = null;
		setPath(viewPath);*/
		clearForm();
		setErrorMessage(null);
		errorMessages.clear();
		setInfoFromMainMenu();
		//loadEstimationRefNumbers();//to load Application status to be changed
		//loadWorkEstimationRefNumbers();
		loadWareHouse();
		loadFundSources();
		setNewEntry();
		//setInfoFromMainMenu();
		//loadUndoEstimationRefNumbers();
		
		//==setFormData();
		return SUCCESS;
	}
	public String newDirectEstimateEntry()
	{
		
		try{
		System.out.println("hiiiiiiiiiiiiiii");	
		clearForm();
		setErrorMessage(null);
		
		clearForm();
		setErrorMessage(null);
		errorMessages.clear();
		setInfoFromMainMenu();
		//loadAllEstimationRefNumbers();
		loadWareHouse();
		loadFundSources();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String sessionKey= (String) request.getSession().getAttribute("region");
		String estimateNo=request.getParameter("estNo");
		String costCen=request.getParameter("costCen");
		if(estimateNo!=null && estimateNo.length()>0 && costCen!=null && costCen.length()>0){
			setEstimateNo(estimateNo);
			setCostCenterNo(costCen);
			//viewApplicantDetails();
			EstimateReferenceEjb estejb = new EstimateReferenceEjb();
			EstimateReference refe = estejb.findByWorkEstimateNo(estimateNo,costCen, sessionKey);
			if(refe != null){
				List<String> estimateRefnos = new ArrayList<String>();
				estimateRefnos.add(refe.getId().getStandardEstimateNo());
				request.getSession().setAttribute("estimationRefNos",estimateRefnos);
				request.getSession().setAttribute("fundSource",getFundSource(refe.getId().getStandardEstimateNo()) != null ? getFundSource(refe.getId().getStandardEstimateNo()) : "-1");
				SpstdesthmtEjb ejb = new SpstdesthmtEjb(sessionKey);
				SpstdesthmtPK hmt = new SpstdesthmtPK();
				hmt.setApplicationNo(refe.getId().getStandardEstimateNo());
				hmt.setStdNo(refe.getId().getStandardEstimateNo());
				String commercialDeptid=null;
				ProvinceDetailsMasterEjb promasEjb = new ProvinceDetailsMasterEjb();
				String commercialId = null;
				try {
					commercialId = promasEjb.getCommercialId(getCostCenterNo(),  getSessionKey("region"));
				} catch (Exception e) {
					return ERROR;
				}
				
				if(commercialId == null){
					commercialId= costCenterNo.substring(0, 3);
					commercialDeptid = commercialId.concat(".00");

				}
				hmt.setDeptId(commercialDeptid);
				SpestedyConEjb ejbspe = new SpestedyConEjb(sessionKey);
				
				List<SpestedyCons> allocatedDetails = ejbspe.getAppointmentByUser(refe.getId().getStandardEstimateNo(),getEstimateNo(),costCenterNo,null);
				if(allocatedDetails != null && allocatedDetails.size() > 0 && allocatedDetails.get(0) != null && allocatedDetails.get(0).getDescription() != null){
					request.getSession().setAttribute("description",allocatedDetails.get(0).getDescription());
				}else {
				
					Spstdesthmt hmtstd = ejb.findById(hmt, sessionKey);
					if(hmtstd != null && hmtstd.getDescription() != null){
						request.getSession().setAttribute("description",hmtstd.getDescription());
					}else{
						request.getSession().setAttribute("description","");
					}
				}
			
			}
		}
		request.getSession().setAttribute("estNo",estimateNo != null ? estimateNo : "");
		
		String userId= (String) request.getSession().getAttribute("userName");
		
		setUserId(userId);
		setRegion(sessionKey);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("hiiiiiiiiiiiiiii2");	
		return SUCCESS;
	}
	public String getFundSource(String applicationNo){
		HttpServletRequest request = ServletActionContext.getRequest();
		String sessionKey= (String) request.getSession().getAttribute("region");
		ApplicationReferenceEjb ap = new ApplicationReferenceEjb(sessionKey);
		ApplicationReference refre = ap.findByApplicationNo(applicationNo);
		if(refre != null){
			
			WiringLandDetailConEjb con = new WiringLandDetailConEjb(sessionKey);
			WiringLandDetailConPK pk = new WiringLandDetailConPK();
			pk.setDeptId(costCenterNo.substring(0, 3).concat(".00"));
			pk.setApplicationId(refre.getId().getApplicationId());
			WiringLandDetailCon appp =con.findByAppId(pk);
			if(appp != null ){
				return appp.getFundSource();
			}
			
		}
		return null;
	}
	
	public void setNewEntry(){
		setInfoFromMainMenu();
		HttpServletRequest request = ServletActionContext.getRequest();
		String sessionKey= (String) request.getSession().getAttribute("region");
		String estimateNo=request.getParameter("estNo");
		String costCen=request.getParameter("costCen");
		if(estimateNo!=null && estimateNo.length()>0 && costCen!=null && costCen.length()>0){
			setEstimateNo(estimateNo);
			setCostCenterNo(costCen);
			//viewApplicantDetails();
			
		}
		List<String> estimationRefNos = new ArrayList<String>();
		
		if(estimateNo != null){
			estimationRefNos.add(estimateNo);
			
			request.getSession().setAttribute("estimationRefNos",estimationRefNos);
		}
	
		String userId= (String) request.getSession().getAttribute("userName");
		
		setUserId(userId);
		setRegion(sessionKey);
		
		
	}
	public String newDirectFroValidate()
	{
		setLoggedData();
		loadWareHouse();
		loadFundSources();
		setNewEntryFromdashBoard();
		return SUCCESS;
	}
	public String validateFromMainMenu()
	{
		setLblError(null);
		
		setLoggedData();
		loadEstimateNos();
		return SUCCESS;
		
	}

	public String close() {
		
		return "close";
	}
	public String saveDetailEstimation(){
		try{
			
			List<Pcestdtt> delist = null;
			List<DetailEstimateDTO> detailEstimateDTOlist = null;
			List<DetailEstimateDTO> pointDmtList = null;
			List<DetailEstimateDTO> allList = new ArrayList<DetailEstimateDTO>();
			HttpServletRequest request = ServletActionContext.getRequest();
			String region= (String) request.getSession().getAttribute("region");
			String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
			String userId= (String) request.getSession().getAttribute("userName");
			String smcType= (String) request.getSession().getAttribute("smcType");
			String estimateType= (String) request.getSession().getAttribute("estimateType");
			String branchType= (String) request.getSession().getAttribute("branchType");
			List<PegItemDTO> list = null;
			List<Pegschdmt> pegItemlist = null;
			EstimateEjb estimateEjb = new EstimateEjb(region); 
			WiringLandDetailEjb wiringLandDetailEjb= new WiringLandDetailEjb(region);
			ApplicationEjb applicationEjb = new ApplicationEjb(region);
			PivDetailEjb pivejb =new PivDetailEjb(region);
			//if(validateForm()){
				selectedPegItemList = (List<String>) request.getSession().getAttribute("selectedpegIdList");
				System.out.println("test : 1" );

				selectedPegschuleItems = (Map<String, PegItemDTO>) request.getSession().getAttribute("pegItemDetails");
				System.out.println("test : 2" );
				
				if(selectedPegschuleItems != null && selectedPegschuleItems.size() > 0){
					PegschdmtEjb pegschdmtEjbejb = new PegschdmtEjb(region);
					
					list = new ArrayList<PegItemDTO>(selectedPegschuleItems.values());

					if(list != null){
						pegItemlist = populatePegschdmts(costCenterNo,getEstimateNo().trim(),list);
						System.out.println("test : 3" );
					}
					/*for(Pegschdmt peg : list ){
					 * 
						pegschdmtEjbejb.insert(peg, region);
					}*/
					//pegschdmtEjbejb.insert(peg, region);
				}
				
				allSelectedAdditionalResoMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("allSelectedAdditionalResources");
				//addedItemsFromPegSchedule = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("detailEstimationDetails");
				
	
				System.out.println("test : 4" );
				//if((selectedAdditionalMaterials != null && selectedAdditionalMaterials.size() > 0))
				//	detailEstimateDTOlist = new ArrayList<DetailEstimateDTO>(selectedAdditionalMaterials.values());
				//}
				if(allSelectedAdditionalResoMap != null && allSelectedAdditionalResoMap.size() > 0){
					pointDmtList = new ArrayList<DetailEstimateDTO>(allSelectedAdditionalResoMap.values());
				}
					
					//if(detailEstimateDTOlist != null){
					//	allList.addAll(detailEstimateDTOlist);
					//}
				if(pointDmtList != null){
					delist = populatePcestdtts(costCenterNo,pointDmtList);
					System.out.println("test : 5" );
				}
			
					
					
				
				
		        
		           /* pcestdttItemByItemDataProvider.setValue("res_type",selectedRowKey, drpDwnResType.getSelected());
		            pcestdttItemByItemDataProvider.setValue("res_cd",selectedRowKey, drpDwnResCode.getSelected());
		            pcestdttItemByItemDataProvider.setValue("res_cat",selectedRowKey,txtFldResCat.getText());
		            Number num = sps.util.Util.getNumberFromFormattedDoubleStr(""+txtFldEstimatedQty.getText());
		            Object estQty = new Double(num.doubleValue());
		            
		            pcestdttItemByItemDataProvider.setValue("estimate_qty",selectedRowKey, estQty);
		            pcestdttItemByItemDataProvider.setValue("tolerance",selectedRowKey, txtFldTollerance.getText());
		            pcestdttItemByItemDataProvider.setValue("unit_price", selectedRowKey,txtFldPrice.getText());
		            Number num1 = sps.util.Util.getNumberFromFormattedDoubleStr(""+txtFldEstimatedCost.getText());
		            Object estiCost = new Double(num1.doubleValue());
		            
		            pcestdttItemByItemDataProvider.setValue("estimate_cost", selectedRowKey,estiCost);
		            pcestdttItemByItemDataProvider.setValue("estimate_no",selectedRowKey,  txtFldEstNo.getText());
		            pcestdttItemByItemDataProvider.setValue("rev_no",selectedRowKey ,rev_no);
		            pcestdttItemByItemDataProvider.setValue("dept_id",selectedRowKey,  getSessionBean1().getLoggedInCostCenter());
		            pcestdttItemByItemDataProvider.setValue("gen_res",selectedRowKey,  gen_res);
		            pcestdttItemByItemDataProvider.setValue("uom", selectedRowKey,hdnFldUOM.getText());
		            pcestdttItemByItemDataProvider.setValue("norm_default", selectedRowKey, norm_default);
		            */
		       
				
				System.out.println("test : 6" );
				Format format=new Format();
				PcesthttPK pcesthttPK = new PcesthttPK();
				pcesthttPK.setEstimateNo(getEstimateNo().trim());
				pcesthttPK.setDeptId(costCenterNo);
				pcesthttPK.setRevNo(2);//for new methods-
				
				Pcesthtt pcesthtt = new Pcesthtt();
				pcesthtt.setId(pcesthttPK);
				System.out.println("test : 7" );
				pcesthtt.setPartialPmt("F");
				pcesthtt.setNormDefault(new BigDecimal(1));
				pcesthtt.setSubCont("T");
				pcesthtt.setControlled("T");
				pcesthtt.setPriority(new BigDecimal(0));
				pcesthtt.setAllocPaid(new BigDecimal(0));
				pcesthtt.setEstType("2");
				System.out.println("test : 8" );
				pcesthtt.setAllocSettle(new BigDecimal(0)); 
				if(getRebate() == null || getRebate().equalsIgnoreCase(Constants.DEFAULT_STRING)){
					pcesthtt.setPartialAmt(new BigDecimal(0));
					setRebate("0");
				}else{
					pcesthtt.setPartialAmt(new BigDecimal(getRebate()));
				}
				System.out.println("test : 9" );
				pcesthtt.setEtimateDt(new java.util.Date());
				pcesthtt.setStatus(new Long(EstimateStatus.NEW));
				pcesthtt.setEntBy(userId);
				pcesthtt.setEntDt(new java.util.Date());
				pcesthtt.setCatCd(getCategoryId());
				System.out.println("test : 10" );
				//pcesthtt.set
				pcesthtt.setConfDt(format.getDefaultDate());
				pcesthtt.setAprDt1(format.getDefaultDate());
				pcesthtt.setAprDt2(format.getDefaultDate());
				pcesthtt.setAprDt3(format.getDefaultDate());
				pcesthtt.setAprDt4(format.getDefaultDate());
				pcesthtt.setAprDt5(format.getDefaultDate());
				pcesthtt.setRejctDt(format.getDefaultDate());
				pcesthtt.setReviseDt(format.getDefaultDate());
				pcesthtt.setDescr(getDescription());
				if(getEstimateNo() != null && getEstimateNo().contains(Constants.SPS_ESTIMATE_CODE) && costCenterNo.contains("52")){
					pcesthtt.setLabel4(getEstimateNo().replace(Constants.SPS_ESTIMATE_CODE, Constants.SMC_JOB_NUMBER_CODE));
				}
				//pcesthtt.setStdCost(new BigDecimal(Double.toString(nf.parse(getTotalCost()).doubleValue())));
				if(getTotalCost() == null || getTotalCost().equalsIgnoreCase(Constants.DEFAULT_STRING)){
					setTotalCost("0");
				}
				pcesthtt.setStdCost(new BigDecimal(getTotalCost()));
				System.out.println("test : 11" );
				if(getFundid()!=null && getFundsou().length()>0)
				{
					
					pcesthtt.setFundSource(getFundsou().trim());
					pcesthtt.setFundId(getFundid().trim());
				}
				System.out.println("test : 12" );
				TempTb tb = new TempTb();
				tb.setDeptId(costCenterNo);
				tb.setEstimateNo(getEstimateNo().trim());
				System.out.println("test : 13" );
				if(getAmount() == null || getAmount().equals("")){
					System.out.println("test : 11" + getAmount() );
					setAmount("0");
				}
				System.out.println("test : 14" );
				tb.setPivAmount(new BigDecimal(getAmount()));
				if(getPivDate() != null && !getPivDate().equalsIgnoreCase("")){
					System.out.println("test : 15" + getPivDate() );
					tb.setPivDate(format.StrToDate(getPivDate().substring(0, 10)));
				}
				tb.setPivNo(getPivNo().trim());
				System.out.println("test : 16"  +getPivNo() );
				//tb.setRowId(get);
				tb.setStatus("U");
				
				estimateEjb.insertDetailEst(pcesthtt,delist,tb,pegItemlist,appointedUserName,getFileRef(),region);
				System.out.println("test : 17");
				
				if(branchType != null && branchType.equalsIgnoreCase(Constants.BRANCHTYPE_GOVERNMENT_INSTALLATION)){
					estimateType = smcType;
				}
				if(estimateType == null){
					estimateType = Constants.DEFAULT_ESTIMATE_TYPE;
				}
				estimateEjb.updateDetailEstimateCost(getEstimateNo(),estimateType, costCenterNo, region);
		/*		EstimateReferenceEjb estejb = new EstimateReferenceEjb();
				EstimateReference refe = estejb.findByWorkEstimateNo(getEstimateNo(),costCenterNo, region);
				
				
				if(refe != null){
					 SpestedyConEjb ejbspCon = new SpestedyConEjb(region);
					 SpestedyCons spestedyCons = new SpestedyCons();
					 List<SpestedyCons> spestedyCons1 = ejbspCon.getAppointmentByUser(refe.getId().getStandardEstimateNo(),getEstimateNo(),pcesthtt.getId().getDeptId(),null);
					 if(spestedyCons1 == null){
						 List<SpestedyCons> spestedyCons2 = ejbspCon.getAppointmentByUser(refe.getId().getStandardEstimateNo(),null,pcesthtt.getId().getDeptId(),null);
						 
						 if(spestedyCons2 != null && spestedyCons2.size() == 1 ){
							 spestedyCons = spestedyCons2.get(0);
							 if(spestedyCons.getWestimateNo() == null){
								 ejbspCon.updateWorkEstimateNumber(refe.getId().getStandardEstimateNo().trim(), pcesthtt.getId().getDeptId(), AppointmentStatus.DETAIL_EST_CREATED,getEstimateNo());
							 }else if(spestedyCons.getWestimateNo().equalsIgnoreCase(getEstimateNo())){
								 ejbspCon.changeStatusAppointByWorkEstimate(refe.getId().getStandardEstimateNo().trim(), pcesthtt.getId().getDeptId(), AppointmentStatus.DETAIL_EST_CREATED,getEstimateNo());
							 }
							
						 }else{
							 
						 }
						
					 }else if(spestedyCons1 != null && spestedyCons1.size() > 0 && spestedyCons1.get(0) != null){
						 spestedyCons = spestedyCons1.get(0);
						 if(spestedyCons.getStatus() !=null && spestedyCons.getStatus().equalsIgnoreCase(Constants.ACTIVE_APPOINMENT_STATUS)){
							 ejbspCon.changeStatusAppointByWorkEstimate(refe.getId().getStandardEstimateNo().trim(),pcesthtt.getId().getDeptId(), AppointmentStatus.DETAIL_EST_CREATED,getEstimateNo());
						 }
						
					 }else{
						 if(appointedUserName.equalsIgnoreCase("-1")){//to auto save allocated user when no allocation function
							 appointedUserName = userId;
						 }
						 if (appointedUserName !=null)
						 {
							    spestedyCons = new SpestedyCons();
							 	SpestedyConsPK id= new SpestedyConsPK();
							 	id.setDeptId(costCenterNo);
							 	spestedyCons.setId(id);	
								Calendar calandar= Calendar.getInstance();
								spestedyCons.setAppointmentDate(format.StrToDate(pcesthtt.getEtimateDt().toString().substring(0, 10)));
								//spestedyCons.setDescription(getDescription());
								if(getDescription() != null){
									if(getDescription().length() > 100){
										spestedyCons.setDescription(getDescription().substring(0, 100));
									}else{
										spestedyCons.setDescription(getDescription());
									}
								}
								spestedyCons.setTimeSession("Morning");
								spestedyCons.setAllocatedTo(appointedUserName);	
								spestedyCons.setAllocatedBy(userId);
								spestedyCons.setAllocatedDate(calandar.getTime());
								spestedyCons.setAllocatedTime(format.FormatTime());
								spestedyCons.setAppoinmentType(AppointmentStatus.listappointmentType);
								spestedyCons.setStatus(AppointmentStatus.getAptStsVal(AppointmentStatus.DETAIL_EST_CREATED));
								spestedyCons.setReferenceNo(getEstimateNo());
								spestedyCons.setWestimateNo(getEstimateNo());
								ejbspCon.makeAnAppointment(spestedyCons);
								//ejbspCon.changeStatusAppointByWorkEstimate(getEstimateNo(), pcesthtt.getId().getDeptId(),getEstimateNo(), AppointmentStatus.DETAIL_EST_CREATED);
						 }
					 }
					 refe.setStatus("1");
					 refe.setEntryBy(userId);
					 refe.setFileReference(getFileRef().trim());
					 estejb.updateEstimateReference(refe, region);
					
				}*/
				
				if(getEstimateNo() != null && getEstimateNo().contains(Constants.SPS_ESTIMATE_CODE) && branchType != null && branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_UNIT)){
					
					PcesthmtEjb pcesthmtEjb = new PcesthmtEjb(region);
					Pcesthmt pcesthmt = pcesthmtEjb.findByJobNo(getApplicationId(),costCenterNo);
					if(pcesthmt != null && pcesthmt.getId().getEstimateNo() != null){
						
						
						ApplicationTransactionEjb ejb = new ApplicationTransactionEjb(region);
						ejb.updateApplicationSubType(costCenterNo, Constants.SMC_APPLICATION_SUB_TYPE, pcesthmt.getId().getEstimateNo() );
					}
				}
				setMessageType(MSG_DONE);
				setErrorMessage("Successfully saved Detail Estimation Details");
				//estimateEjb.addAdditionalCost(getEstimateNo(),costCenterNo,getTotalCost(),region);
				clearForm();
				loadUsers();
			//}else{
				/*StringBuffer bf = new StringBuffer();
				bf.append("Enter required details");
				for(String errorMessage :errorMessages){
					bf.append(errorMessage +", ");
					
				}
				setLblError(bf.toString());
				return SUCCESS;*/
			//}
			
		}catch (RollbackException e) {
			loadUsers();
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage("Error Occured");
			return ERROR;
		} catch (PersistenceException e) {
			loadUsers();
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			return ERROR;
		} catch (Exception e) {
			loadUsers();
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setErrorMessage(e.getMessage());
			return ERROR;
		} finally{
			//getEm().close();
			//em=null;
			//getEmf().close();
			//emf=null;
			//format=null;
		}
		
		//setLblError("Successfully saved Detail Estimation Details");
		return SUCCESS;
		
	}
	
	private boolean validateForm(){
		if(getApplicationId()== null){
			setLblError("Please enter Application Id");
			errorMessages.add("Application Id");
			return false;
		}
		if(getEstimateNo() == null){
			setLblError("Please enter PIV No");
			errorMessages.add("PIV No");
			return false;
		}
		
		
		
		return true;
		
	}
	private List<Pcestdtt> populatePcestdtts(String costCenter,List<DetailEstimateDTO> detaList){
		
	 	Long revNo = 2l;
        String genRes = "F";
        String normDefault = "F";
	       
		List<Pcestdtt> lst = new ArrayList<Pcestdtt>();
		if(detaList != null){
			for(DetailEstimateDTO dto :detaList){
				Pcestdtt pcestdtt = new Pcestdtt();
				
				PcestdttPK pcestdttPk = new PcestdttPK();
				pcestdttPk.setDeptId(costCenter);
				pcestdttPk.setEstimateNo(getEstimateNo().trim());
				pcestdttPk.setResCd(dto.getResourceCode());
				pcestdttPk.setRevNo(revNo);
				
				pcestdtt.setId(pcestdttPk);
				if(dto.getResCategory() != null){
					pcestdtt.setResCat(new BigDecimal(dto.getResCategory()));
				}else{
					pcestdtt.setResCat(null);
				}
				//pcestdtt.setResCat(new BigDecimal(dto.getResCategory()));
				pcestdtt.setEstimateCost((dto.getEstimateCost() == null || dto.getEstimateCost().equals(Constants.DEFAULT_STRING) ? new BigDecimal("0") : dto.getEstimateCost()));
				pcestdtt.setEstimateQty((dto.getEstimatedQuantity() == null || dto.getEstimatedQuantity().equals(Constants.DEFAULT_STRING) ? new BigDecimal("0") : dto.getEstimatedQuantity()));
				pcestdtt.setResType(dto.getResourceType());
				pcestdtt.setUom(dto.getUom());
				System.out.println("Hi unit price");
				pcestdtt.setUnitPrice(dto.getUnitPrice());
				pcestdtt.setNormDefault(normDefault);
				pcestdtt.setGenRes(genRes);
				lst.add(pcestdtt);
			}
		}
		
		
		
		return lst;
		
	}
private List<Pegschdmt> populatePegschdmts(String costCenter,String estimatNo,List<PegItemDTO> detaList){
		
	 	Long revNo = 2l;
        String genRes = "F";
        String normDefault = "F";
	       
		List<Pegschdmt> lst = new ArrayList<Pegschdmt>();
		if(detaList != null){
			for(PegItemDTO dto :detaList){
				Pegschdmt pegschdmt = new Pegschdmt();
				
				PegschdmtPK pegschdmtPk = new PegschdmtPK();
				pegschdmtPk.setDeptId(costCenter);
				pegschdmtPk.setEstimateNo(estimatNo.trim());
				pegschdmtPk.setNodeId(dto.getNodeId());
				
				
				pegschdmt.setId(pegschdmtPk);
				
				pegschdmt.setNodeDes(dto.getNodeDescription());
				pegschdmt.setNoOfItem(dto.getNoOfItem());
				
				lst.add(pegschdmt);
			}
		}
		
		
		
		return lst;
		
	}
	private Pcestdtt setUpdatedDtts(Pcestdtt dtt,DetailEstimateDTO dto){
		
	 	
		List<Pcestdtt> lst = new ArrayList<Pcestdtt>();
		if(dtt != null){
			if(dto != null){
				
				dtt.setEstimateCost(dto.getEstimateCost());
				dtt.setEstimateQty(dto.getEstimatedQuantity());
				
			}
		}
	
		return dtt;
		
	}
private Pcestdtt setPrice(Pcestdtt dtt){
		String warehouseId = null;
		InwrhmtmEjb ejb=new InwrhmtmEjb("R4");
	 	if(dtt.getId().getDeptId().equalsIgnoreCase("550.20")){
	 		warehouseId = "550.11";
	 	}else if(dtt.getId().getDeptId().equalsIgnoreCase("510.20")){
	 		warehouseId = "510.11";
	 	}
	 	BigDecimal price = new BigDecimal("0");
		List<Pcestdtt> lst = new ArrayList<Pcestdtt>();
		if(dtt != null){
			
				price = ejb.findUPByMatCd(dtt.getId().getResCd().trim(), warehouseId);
				if(price == null){
					price = ejb.findUPByMatCd(dtt.getId().getResCd().trim(), "550.12");
				}
				dtt.setUnitPrice(price);
				dtt.setEstimateCost(new BigDecimal(dtt.getUnitPrice().doubleValue()*dtt.getEstimateQty().doubleValue()));
				
				
		
		}
	
		return dtt;
		
	}
	public String updateDetailEstimation(){
		List<Pcestdtt> delist = null;
		List<DetailEstimateDTO> detailEstimateDTOlist = null;
		List<DetailEstimateDTO> pointDmtList = null;
		List<Pcestdtt> addedList = null;
		List<Pcestdtt> updatedList = new ArrayList<Pcestdtt>();
		List<Pcestdtt> deletedList = new ArrayList<Pcestdtt>();
		List<DetailEstimateDTO> allList = new ArrayList<DetailEstimateDTO>();
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		String smcType= (String) request.getSession().getAttribute("smcType");
		String estimateType= (String) request.getSession().getAttribute("estimateType");
		String branchType= (String) request.getSession().getAttribute("branchType");
		Map<String, PegItemDTO> existingPegItemMapItems = null;
		List<PegItemDTO> newlyAddedpegDTOlist = new ArrayList<PegItemDTO>();
		List<PegItemDTO> updatedpegDTOlist = new ArrayList<PegItemDTO>();
		List<Pegschdmt> newlyAddedpegItemlist = new ArrayList<Pegschdmt>();
		List<Pegschdmt> updatedpegItemlist = new ArrayList<Pegschdmt>();
		try{
			EstimateEjb estimateEjb = new EstimateEjb(region); 
			WiringLandDetailEjb wiringLandDetailEjb= new WiringLandDetailEjb(region);
			ApplicationEjb applicationEjb = new ApplicationEjb(region);
			PivDetailEjb pivejb =new PivDetailEjb(region);
		//if(validateForm()){
			//selectedPegItemList = (List<String>) request.getSession().getAttribute("selectedpegIdList");
			

			selectedPegschuleItems = (Map<String, PegItemDTO>) request.getSession().getAttribute("pegItemDetails");
			existingPegItemMapItems = (Map<String, PegItemDTO>) request.getSession().getAttribute("existingPegItemMap");
			if(selectedPegschuleItems != null && selectedPegschuleItems.size() > 0){
				PegschdmtEjb pegschdmtEjbejb = new PegschdmtEjb(region);
				
				List<PegItemDTO> list = new ArrayList<PegItemDTO>(selectedPegschuleItems.values());
				if(existingPegItemMapItems != null && existingPegItemMapItems.size() > 0){
					for(PegItemDTO pegItemDTO : list){
						if(!existingPegItemMapItems.containsKey(pegItemDTO.getNodeId().trim())){
							newlyAddedpegDTOlist.add(pegItemDTO);
							
						}else if(pegItemDTO.getNoOfItem().doubleValue() != existingPegItemMapItems.get(pegItemDTO.getNodeId().trim()).getNoOfItem().doubleValue()){
							updatedpegDTOlist.add(pegItemDTO);
						}
					}
				}
				


			}

			resourceMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("detailEstimationDetails");
			//addedItemsFromPegSchedule = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("detailEstimationDetails");
			
			updatedDetailsMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("updatedDetails");
			
			deletedDetailsMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("deletedDetails");
			
			alreadyAddedDetails = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("alreadyAddedDetails");
			
			if(resourceMap != null && resourceMap.size() > 0){
				addedList = populatePcestdtts(costCenterNo,new ArrayList<DetailEstimateDTO>(resourceMap.values()));
			}
			
		
			Format format=new Format();
			EstimateDetails dto = (EstimateDetails) request.getSession().getAttribute("estimateDetailobject");
			if(dto != null){
				Pcesthtt pchtt = dto.getHtt();
				if(pchtt != null){
					
					
					pchtt.setPartialAmt(new BigDecimal(getRebate() != null ? getRebate()  : "0"));
					pchtt.setEtimateDt(new java.util.Date());
					pchtt.setStatus(new Long(EstimateStatus.MODIFIED));
					pchtt.setEntBy(userId);
					pchtt.setEntDt(new java.util.Date());
					pchtt.setCatCd(getCategoryId());
					
					//pcesthtt.set
					pchtt.setConfDt(format.getDefaultDate());
					pchtt.setAprDt1(format.getDefaultDate());
					pchtt.setAprDt2(format.getDefaultDate());
					pchtt.setAprDt3(format.getDefaultDate());
					pchtt.setAprDt4(format.getDefaultDate());
					pchtt.setAprDt5(format.getDefaultDate());
					pchtt.setRejctDt(format.getDefaultDate());
					pchtt.setReviseDt(format.getDefaultDate());
					pchtt.setDescr(getDescription());
					pchtt.setRejectReason(getRejectReason());
					//pcesthtt.setStdCost(new BigDecimal(Double.toString(nf.parse(getTotalCost()).doubleValue())));
					if(getTotalCost().equalsIgnoreCase(Constants.DEFAULT_STRING)){
						setTotalCost("0");
					}
					pchtt.setStdCost(new BigDecimal(getTotalCost() != null ? getTotalCost()  : "0"));
					
					if(getFundid()!=null && getFundsou().length()>0)
					{
						
						pchtt.setFundSource(getFundsou().trim());
						pchtt.setFundId(getFundid().trim());
					}
				}
				
				
				if(newlyAddedpegDTOlist != null && newlyAddedpegDTOlist.size() > 0){
					newlyAddedpegItemlist = populatePegschdmts(pchtt.getId().getDeptId(), pchtt.getId().getEstimateNo(), newlyAddedpegDTOlist);
				
				}
				if(updatedpegDTOlist != null && updatedpegDTOlist.size() > 0){
					updatedpegItemlist = populatePegschdmts(pchtt.getId().getDeptId(), pchtt.getId().getEstimateNo(), updatedpegDTOlist);
				
				}
				TempTb tb = dto.getTb();
				
				if(tb != null){
					
					try {
						tb.setPivAmount(new BigDecimal(nf.parse(getAmount()).toString()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(getPivDate() != null &&  !getPivDate().equalsIgnoreCase("")){
						tb.setPivDate(format.StrToDate(getPivDate().substring(0, 10)));
					}
					tb.setPivNo(getPivNo().trim());
					
				}
				List<Pcestdtt> list = dto.getDtts();
				if(list != null && list.size() > 0){
					
					for(Pcestdtt dtt : list){
						if(updatedDetailsMap != null && updatedDetailsMap.containsKey(dtt.getId().getResCd().trim())){
							setUpdatedDtts(dtt,updatedDetailsMap.get(dtt.getId().getResCd().trim()));
							updatedList.add(dtt);
						}
						if(deletedDetailsMap != null && deletedDetailsMap.containsKey(dtt.getId().getResCd().trim())){
							deletedList.add(dtt);
						}
					}
					
					
				}
				/*double totalCost = detailEstimateUpdatedTotal(new BigDecimal(getTotalCost()).doubleValue(),dto.getDtts(),addedList,updatedDetailsMap,deletedDetailsMap);
				if(totalCost != 0.0){
				 pchtt.setStdCost(new BigDecimal(totalCost));
				}*/
				
				
					estimateEjb.updateWorkEstimateDetails(pchtt, null, addedList, updatedList,deletedList,newlyAddedpegItemlist,updatedpegItemlist,tb,region);
				/*	if(smcType == null){
						
						smcType = "DE";
						
					}*/
					
					if(branchType != null && branchType.equalsIgnoreCase(Constants.BRANCHTYPE_GOVERNMENT_INSTALLATION)){
						estimateType = smcType;
					}
					if(estimateType == null){
						estimateType = Constants.DEFAULT_ESTIMATE_TYPE;
					}
					
					estimateEjb.updateDetailEstimateCost(getEstimateNo(),estimateType, costCenterNo, region);
					setMessageType(MSG_DONE);
					setErrorMessage("Successfully saved Detail Estimation Details");
					//estimateEjb.updateDetailEstimateCost(getEstimateNo(),"DES" ,costCenterNo, region);
					clearForm();
				
				}
			} catch (RollbackException e) {
				loadUserList(region,costCenterNo);
				e.printStackTrace();
				System.out.println("ERROR MESSEGE" + e.getMessage());
				setErrorMessage(e.getMessage());
				return ERROR;
			} catch (PersistenceException e) {
				loadUserList(region,costCenterNo);
				e.printStackTrace();
				System.out.println("ERROR MESSEGE" + e.getMessage());
				setErrorMessage(e.getMessage());
				return ERROR;
			} catch (Exception e) {
				loadUserList(region,costCenterNo);
				e.printStackTrace();
				System.out.println("ERROR MESSEGE" + e.getMessage());
				setErrorMessage(e.getMessage());
				return ERROR;
			} finally{
				//getEm().close();
				//em=null;
				//getEmf().close();
				//emf=null;
				//format=null;
			}
			
			
			
			//estimateEjb.addAdditionalCost(getEstimateNo(),costCenterNo,getTotalCost(),region);
			
			
		//}else{
			/*StringBuffer bf = new StringBuffer();
			bf.append("Enter required details");
			for(String errorMessage :errorMessages){
				bf.append(errorMessage +", ");
				
			}
			setLblError(bf.toString());
			return SUCCESS;*/
		//}
		loadUserList(region,costCenterNo);
		
		//setLblError("Successfully saved Detail Estimation Details");
		return SUCCESS;
	}
	public String updateAndValidate(){
		List<Pcestdtt> delist = null;
		List<DetailEstimateDTO> detailEstimateDTOlist = null;
		List<DetailEstimateDTO> pointDmtList = null;
		List<Pcestdtt> addedList = null;
		List<Pcestdtt> updatedList = new ArrayList<Pcestdtt>();
		List<Pcestdtt> deletedList = new ArrayList<Pcestdtt>();
		List<DetailEstimateDTO> allList = new ArrayList<DetailEstimateDTO>();
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		String smcType= (String) request.getSession().getAttribute("smcType");
		String estimateType= (String) request.getSession().getAttribute("estimateType");
		String branchType= (String) request.getSession().getAttribute("branchType");
		Map<String, PegItemDTO> existingPegItemMapItems = null;
		List<PegItemDTO> newlyAddedpegDTOlist = new ArrayList<PegItemDTO>();
		List<PegItemDTO> updatedpegDTOlist = new ArrayList<PegItemDTO>();
		List<Pegschdmt> newlyAddedpegItemlist = new ArrayList<Pegschdmt>();
		List<Pegschdmt> updatedpegItemlist = new ArrayList<Pegschdmt>();
		try{
			EstimateEjb estimateEjb = new EstimateEjb(region); 
			WiringLandDetailEjb wiringLandDetailEjb= new WiringLandDetailEjb(region);
			ApplicationEjb applicationEjb = new ApplicationEjb(region);
			PivDetailEjb pivejb =new PivDetailEjb(region);
		//if(validateForm()){
			//selectedPegItemList = (List<String>) request.getSession().getAttribute("selectedpegIdList");
			

			selectedPegschuleItems = (Map<String, PegItemDTO>) request.getSession().getAttribute("pegItemDetails");
			existingPegItemMapItems = (Map<String, PegItemDTO>) request.getSession().getAttribute("existingPegItemMap");
			if(selectedPegschuleItems != null && selectedPegschuleItems.size() > 0){
				PegschdmtEjb pegschdmtEjbejb = new PegschdmtEjb(region);
				
				List<PegItemDTO> list = new ArrayList<PegItemDTO>(selectedPegschuleItems.values());
				if(existingPegItemMapItems != null && existingPegItemMapItems.size() > 0){
					for(PegItemDTO pegItemDTO : list){
						if(!existingPegItemMapItems.containsKey(pegItemDTO.getNodeId().trim())){
							newlyAddedpegDTOlist.add(pegItemDTO);
							
						}else if(pegItemDTO.getNoOfItem().doubleValue() != existingPegItemMapItems.get(pegItemDTO.getNodeId().trim()).getNoOfItem().doubleValue()){
							updatedpegDTOlist.add(pegItemDTO);
						}
					}
				}
				


			}

			resourceMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("detailEstimationDetails");
			//addedItemsFromPegSchedule = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("detailEstimationDetails");
			
			updatedDetailsMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("updatedDetails");
			
			deletedDetailsMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("deletedDetails");
			
			alreadyAddedDetails = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("alreadyAddedDetails");
			
			if(resourceMap != null && resourceMap.size() > 0){
				addedList = populatePcestdtts(costCenterNo,new ArrayList<DetailEstimateDTO>(resourceMap.values()));
			}
			
		
			Format format=new Format();
			EstimateDetails dto = (EstimateDetails) request.getSession().getAttribute("estimateDetailobject");
			if(dto != null){
				Pcesthtt pchtt = dto.getHtt();
				if(pchtt != null){
					
					
					pchtt.setPartialAmt(new BigDecimal(getRebate() != null ? getRebate()  : "0"));
					pchtt.setEtimateDt(new java.util.Date());
					pchtt.setStatus(new Long(EstimateStatus.MODIFIED));
					pchtt.setEntBy(userId);
					pchtt.setEntDt(new java.util.Date());
					pchtt.setCatCd(getCategoryId());
					
					//pcesthtt.set
					pchtt.setConfDt(format.getDefaultDate());
					pchtt.setAprDt1(format.getDefaultDate());
					pchtt.setAprDt2(format.getDefaultDate());
					pchtt.setAprDt3(format.getDefaultDate());
					pchtt.setAprDt4(format.getDefaultDate());
					pchtt.setAprDt5(format.getDefaultDate());
					pchtt.setRejctDt(format.getDefaultDate());
					pchtt.setReviseDt(format.getDefaultDate());
					pchtt.setDescr(getDescription());
					pchtt.setRejectReason(getRejectReason());
					//pcesthtt.setStdCost(new BigDecimal(Double.toString(nf.parse(getTotalCost()).doubleValue())));
					if(getTotalCost().equalsIgnoreCase(Constants.DEFAULT_STRING)){
						setTotalCost("0");
					}
					pchtt.setStdCost(new BigDecimal(getTotalCost() != null ? getTotalCost()  : "0"));
					
					if(getFundid()!=null && getFundsou().length()>0)
					{
						
						pchtt.setFundSource(getFundsou().trim());
						pchtt.setFundId(getFundid().trim());
					}
				}
				
				
				if(newlyAddedpegDTOlist != null && newlyAddedpegDTOlist.size() > 0){
					newlyAddedpegItemlist = populatePegschdmts(pchtt.getId().getDeptId(), pchtt.getId().getEstimateNo(), newlyAddedpegDTOlist);
				
				}
				if(updatedpegDTOlist != null && updatedpegDTOlist.size() > 0){
					updatedpegItemlist = populatePegschdmts(pchtt.getId().getDeptId(), pchtt.getId().getEstimateNo(), updatedpegDTOlist);
				
				}
				TempTb tb = dto.getTb();
				
				if(tb != null){
					
					try {
						tb.setPivAmount(new BigDecimal(nf.parse(getAmount()).toString()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(getPivDate() != null &&  !getPivDate().equalsIgnoreCase("")){
						tb.setPivDate(format.StrToDate(getPivDate().substring(0, 10)));
					}
					tb.setPivNo(getPivNo().trim());
					
				}
				List<Pcestdtt> list = dto.getDtts();
				if(list != null && list.size() > 0){
					
					for(Pcestdtt dtt : list){
						if(updatedDetailsMap != null && updatedDetailsMap.containsKey(dtt.getId().getResCd().trim())){
							setUpdatedDtts(dtt,updatedDetailsMap.get(dtt.getId().getResCd().trim()));
							updatedList.add(dtt);
						}
						if(deletedDetailsMap != null && deletedDetailsMap.containsKey(dtt.getId().getResCd().trim())){
							deletedList.add(dtt);
						}
					}
					
					
				}
				/*double totalCost = detailEstimateUpdatedTotal(new BigDecimal(getTotalCost()).doubleValue(),dto.getDtts(),addedList,updatedDetailsMap,deletedDetailsMap);
				if(totalCost != 0.0){
				 pchtt.setStdCost(new BigDecimal(totalCost));
				}*/
				if(branchType != null && branchType.equalsIgnoreCase(Constants.BRANCHTYPE_GOVERNMENT_INSTALLATION)){
					estimateType = smcType;
				}
				if(estimateType == null){
					estimateType = Constants.DEFAULT_ESTIMATE_TYPE;
				}
				
				
					String msg = estimateEjb.updateAndValidate(pchtt,addedList, updatedList,deletedList,newlyAddedpegItemlist,updatedpegItemlist,tb, getSession().get("userRole").toString(),getSessionKey("userName"),getApprovalComment(),estimateType);
				
					
					setMessageType(MSG_DONE);
					setErrorMessage(msg);
					//estimateEjb.updateDetailEstimateCost(getEstimateNo(),"DES" ,costCenterNo, region);
					clearForm();
				
				}
			} catch (RollbackException e) {
				loadUserList(region,costCenterNo);
				e.printStackTrace();
				System.out.println("ERROR MESSEGE" + e.getMessage());
				setErrorMessage(e.getMessage());
				return ERROR;
			} catch (PersistenceException e) {
				loadUserList(region,costCenterNo);
				e.printStackTrace();
				System.out.println("ERROR MESSEGE" + e.getMessage());
				setErrorMessage(e.getMessage());
				return ERROR;
			} catch (Exception e) {
				loadUserList(region,costCenterNo);
				e.printStackTrace();
				System.out.println("ERROR MESSEGE" + e.getMessage());
				setErrorMessage(e.getMessage());
				return ERROR;
			} finally{
				//getEm().close();
				//em=null;
				//getEmf().close();
				//emf=null;
				//format=null;
			}
			
			
			
			//estimateEjb.addAdditionalCost(getEstimateNo(),costCenterNo,getTotalCost(),region);
			
			
		//}else{
			/*StringBuffer bf = new StringBuffer();
			bf.append("Enter required details");
			for(String errorMessage :errorMessages){
				bf.append(errorMessage +", ");
				
			}
			setLblError(bf.toString());
			return SUCCESS;*/
		//}
		loadUserList(region,costCenterNo);
		loadEstimateNos();
		//setLblError("Successfully saved Detail Estimation Details");
		return SUCCESS;
	}

	public String updatePriceEstimation(){
		List<Pcestdtt> delist = null;
		List<DetailEstimateDTO> detailEstimateDTOlist = null;
		List<DetailEstimateDTO> pointDmtList = null;
		List<Pcestdtt> addedList = null;
		List<Pcestdtt> updatedList = new ArrayList<Pcestdtt>();
		List<Pcestdtt> deletedList = new ArrayList<Pcestdtt>();
		List<DetailEstimateDTO> allList = new ArrayList<DetailEstimateDTO>();
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		String smcType= (String) request.getSession().getAttribute("smcType");
		String estimateType= (String) request.getSession().getAttribute("estimateType");
		String branchType= (String) request.getSession().getAttribute("branchType");
		Map<String, PegItemDTO> existingPegItemMapItems = null;
		List<PegItemDTO> newlyAddedpegDTOlist = new ArrayList<PegItemDTO>();
		List<PegItemDTO> updatedpegDTOlist = new ArrayList<PegItemDTO>();
		List<Pegschdmt> newlyAddedpegItemlist = new ArrayList<Pegschdmt>();
		List<Pegschdmt> updatedpegItemlist = new ArrayList<Pegschdmt>();
		try{
			EstimateEjb estimateEjb = new EstimateEjb(region); 
			WiringLandDetailEjb wiringLandDetailEjb= new WiringLandDetailEjb(region);
			ApplicationEjb applicationEjb = new ApplicationEjb(region);
			PivDetailEjb pivejb =new PivDetailEjb(region);
		//if(validateForm()){
			//selectedPegItemList = (List<String>) request.getSession().getAttribute("selectedpegIdList");
			


			
			Format format=new Format();
			EstimateDetails dto = (EstimateDetails) request.getSession().getAttribute("estimateDetailobject");
			if(dto != null){
				Pcesthtt pchtt = dto.getHtt();
				if(pchtt != null){
					
					
					pchtt.setPartialAmt(new BigDecimal(getRebate() != null ? getRebate()  : "0"));
					pchtt.setEtimateDt(new java.util.Date());
					pchtt.setStatus(pchtt.getStatus());
					pchtt.setEntBy(userId);
					pchtt.setEntDt(new java.util.Date());
					pchtt.setCatCd(getCategoryId());
					
					//pcesthtt.set
					pchtt.setConfDt(format.getDefaultDate());
					pchtt.setAprDt1(format.getDefaultDate());
					pchtt.setAprDt2(format.getDefaultDate());
					pchtt.setAprDt3(format.getDefaultDate());
					pchtt.setAprDt4(format.getDefaultDate());
					pchtt.setAprDt5(format.getDefaultDate());
					pchtt.setRejctDt(format.getDefaultDate());
					pchtt.setReviseDt(format.getDefaultDate());
					pchtt.setDescr(getDescription());
					pchtt.setRejectReason(getRejectReason());
					//pcesthtt.setStdCost(new BigDecimal(Double.toString(nf.parse(getTotalCost()).doubleValue())));
					if(getTotalCost().equalsIgnoreCase(Constants.DEFAULT_STRING)){
						setTotalCost("0");
					}
					pchtt.setStdCost(new BigDecimal(getTotalCost() != null ? getTotalCost()  : "0"));
					
					if(getFundid()!=null && getFundsou().length()>0)
					{
						
						pchtt.setFundSource(getFundsou().trim());
						pchtt.setFundId(getFundid().trim());
					}
				}
				
				
				
				List<Pcestdtt> list = dto.getDtts();
				if(list != null && list.size() > 0){
					
					for(Pcestdtt dtt : list){
						if(dtt != null){
							if(dtt != null && (dtt.getResType().trim().equalsIgnoreCase("MAT-COST"))){
								setPrice(dtt);
								updatedList.add(dtt);
							}
						}
							
						
						
					}
					
					
				}
				/*double totalCost = detailEstimateUpdatedTotal(new BigDecimal(getTotalCost()).doubleValue(),dto.getDtts(),addedList,updatedDetailsMap,deletedDetailsMap);
				if(totalCost != 0.0){
				 pchtt.setStdCost(new BigDecimal(totalCost));
				}*/
				
				
					estimateEjb.updateWorkEstimateDetails(pchtt, null, null, updatedList,null,null,null,null,region);
					if(branchType != null && branchType.equalsIgnoreCase(Constants.BRANCHTYPE_GOVERNMENT_INSTALLATION)){
						estimateType = smcType;
					}
					if(estimateType == null){
						estimateType = Constants.DEFAULT_ESTIMATE_TYPE;
					}
					estimateEjb.updateDetailEstimateCost(getEstimateNo(),estimateType, costCenterNo, region);
					setMessageType(MSG_DONE);
					setErrorMessage("Successfully saved Detail Estimation Details");
					//estimateEjb.updateDetailEstimateCost(getEstimateNo(),"DES" ,costCenterNo, region);
					clearForm();
				
				}
			} catch (RollbackException e) {
				loadUserList(region,costCenterNo);
				e.printStackTrace();
				System.out.println("ERROR MESSEGE" + e.getMessage());
				setErrorMessage(e.getMessage());
				return ERROR;
			} catch (PersistenceException e) {
				loadUserList(region,costCenterNo);
				e.printStackTrace();
				System.out.println("ERROR MESSEGE" + e.getMessage());
				setErrorMessage(e.getMessage());
				return ERROR;
			} catch (Exception e) {
				loadUserList(region,costCenterNo);
				e.printStackTrace();
				System.out.println("ERROR MESSEGE" + e.getMessage());
				setErrorMessage(e.getMessage());
				return ERROR;
			} finally{
				//getEm().close();
				//em=null;
				//getEmf().close();
				//emf=null;
				//format=null;
			}
			
			
			
			//estimateEjb.addAdditionalCost(getEstimateNo(),costCenterNo,getTotalCost(),region);
			
			
		//}else{
			/*StringBuffer bf = new StringBuffer();
			bf.append("Enter required details");
			for(String errorMessage :errorMessages){
				bf.append(errorMessage +", ");
				
			}
			setLblError(bf.toString());
			return SUCCESS;*/
		//}
		loadUserList(region,costCenterNo);
		
		//setLblError("Successfully saved Detail Estimation Details");
		return SUCCESS;
	}
	
	
	public String deleteDetailEstimation(){
		
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		String smcType= (String) request.getSession().getAttribute("smcType");
		try{
			EstimateReferenceEjb estimateEjb = new EstimateReferenceEjb(); 
			EstimateEjb estimate = new EstimateEjb(region);
			PcesthttEjb phttejb = new PcesthttEjb(region);
			PcestdttEjb pdttejb = new PcestdttEjb(region);
			Format format=new Format();
			EstimateDetails dto = (EstimateDetails) request.getSession().getAttribute("estimateDetailobject");
			if(dto != null){
				Pcesthtt pchtt = dto.getHtt();
				if(pchtt != null){
					List<Pcestdtt> list = dto.getDtts();
					
					if(list != null){
						
						TempTb tb = dto.getTb();
						
						EstimateReference refe = estimateEjb.findByWorkEstimateNo(pchtt.getId().getEstimateNo(),costCenterNo,region);
						
						estimate.deleteEstimate(pchtt, list, tb, refe);
						setMessageType(MSG_DONE);
						setErrorMessage("Successfully deleted Detail Estimation Details");
						loadWorkEstimationRefNumbers();
					}
					
					
					
				}
				
				
				
				clearForm();
				
				}
			} catch (RollbackException e) {
				loadUserList(region,costCenterNo);
				e.printStackTrace();
				System.out.println("ERROR MESSEGE" + e.getMessage());
				setErrorMessage(e.getMessage());
				return ERROR;
			} catch (PersistenceException e) {
				loadUserList(region,costCenterNo);
				e.printStackTrace();
				System.out.println("ERROR MESSEGE" + e.getMessage());
				setErrorMessage(e.getMessage());
				return ERROR;
			} catch (Exception e) {
				loadUserList(region,costCenterNo);
				e.printStackTrace();
				System.out.println("ERROR MESSEGE" + e.getMessage());
				setErrorMessage(e.getMessage());
				return ERROR;
			} finally{
				//getEm().close();
				//em=null;
				//getEmf().close();
				//emf=null;
				//format=null;
			}
			
			
			
			//estimateEjb.addAdditionalCost(getEstimateNo(),costCenterNo,getTotalCost(),region);
			
			
		//}else{
			/*StringBuffer bf = new StringBuffer();
			bf.append("Enter required details");
			for(String errorMessage :errorMessages){
				bf.append(errorMessage +", ");
				
			}
			setLblError(bf.toString());
			return SUCCESS;*/
		//}
		loadUserList(region,costCenterNo);
		loadWorkEstimationRefNumbers();
		//setLblError("Successfully saved Detail Estimation Details");
		return SUCCESS;
	}
	private double detailEstimateUpdatedTotal(Double totalCost, List<Pcestdtt> existingDtts,List<Pcestdtt> addedList,Map<String, DetailEstimateDTO> updatedItems,Map<String, DetailEstimateDTO> deletedItems){
		
		double totalExistingCost = 0;
		if(updatedItems != null && existingDtts != null){
			for(Pcestdtt dtt :existingDtts){
				if(updatedItems.containsKey(dtt.getId().getResCd().trim())){
					//totalExistingCost = totalExistingCost + dtt.getEstimateCost().doubleValue();
					
					totalCost = totalCost - dtt.getEstimateCost().doubleValue() + updatedItems.get(dtt.getId().getResCd().trim()).getEstimateCost().doubleValue();
				}
			}
		}
		if(deletedItems != null && existingDtts != null){
			for(Pcestdtt dtt :existingDtts){
				if(deletedItems.containsKey(dtt.getId().getResCd().trim())){
					//totalExistingCost = totalExistingCost + dtt.getEstimateCost().doubleValue();
					totalCost = totalCost - dtt.getEstimateCost().doubleValue();
				}
			}
		}
		if(addedList != null){
			for(Pcestdtt dtt :addedList){
				//if(deletedItems.containsKey(dtt.getId().getResCd())){
					//totalExistingCost = totalExistingCost + dtt.getEstimateCost().doubleValue();
				if(dtt.getEstimateCost() != null){
					totalCost = totalCost + dtt.getEstimateCost().doubleValue();
				}
				//}
			}
		}
		return totalCost;
		
	}
	public String getEstimateNo() {
		return estimateNo;
	}

	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
	}

	public String getFileRef() {
		return fileRef;
	}

	public void setFileRef(String fileRef) {
		this.fileRef = fileRef;
	}

	public String getPivNo() {
		return pivNo;
	}

	public void setPivNo(String pivNo) {
		this.pivNo = pivNo;
	}

	public String getWareHouseId() {
		return wareHouseId;
	}

	public void setWareHouseId(String wareHouseId) {
		this.wareHouseId = wareHouseId;
	}

	

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	

	
	public String getPivDate() {
		return pivDate;
	}

	public void setPivDate(String pivDate) {
		this.pivDate = pivDate;
	}

	public String getEstimateDate() {
		return estimateDate;
	}

	public void setEstimateDate(String estimateDate) {
		this.estimateDate = estimateDate;
	}

	public String getTotalMaterialCost() {
		return totalMaterialCost;
	}

	public void setTotalMaterialCost(String totalMaterialCost) {
		this.totalMaterialCost = totalMaterialCost;
	}

	public String getTotalWayleaves() {
		return totalWayleaves;
	}

	public void setTotalWayleaves(String totalWayleaves) {
		this.totalWayleaves = totalWayleaves;
	}

	public String getTotalLabour() {
		return totalLabour;
	}

	public void setTotalLabour(String totalLabour) {
		this.totalLabour = totalLabour;
	}

	public String getTotalSubsistance() {
		return totalSubsistance;
	}

	public void setTotalSubsistance(String totalSubsistance) {
		this.totalSubsistance = totalSubsistance;
	}

	public String getTotalContingencies() {
		return totalContingencies;
	}

	public void setTotalContingencies(String totalContingencies) {
		this.totalContingencies = totalContingencies;
	}

	public String getTotalOverhead() {
		return totalOverhead;
	}

	public void setTotalOverhead(String totalOverhead) {
		this.totalOverhead = totalOverhead;
	}

	public String getTotalEstimatedCapitalCost() {
		return totalEstimatedCapitalCost;
	}

	public void setTotalEstimatedCapitalCost(String totalEstimatedCapitalCost) {
		this.totalEstimatedCapitalCost = totalEstimatedCapitalCost;
	}

	public String getFundsou() {
		return fundsou;
	}

	public void setFundsou(String fundsou) {
		this.fundsou = fundsou;
	}

	

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getLoggedInUserId() {
		return loggedInUserId;
	}

	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}

	public String getLoggedInUserLevel() {
		return loggedInUserLevel;
	}

	public void setLoggedInUserLevel(String loggedInUserLevel) {
		this.loggedInUserLevel = loggedInUserLevel;
	}

	public String getPostUserName() {
		return postUserName;
	}

	public void setPostUserName(String postUserName) {
		this.postUserName = postUserName;
	}

	public String getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	public String getRebate() {
		return rebate;
	}

	public void setRebate(String rebate) {
		this.rebate = rebate;
	}

	public String getAppointedUserName() {
		return appointedUserName;
	}

	public void setAppointedUserName(String appointedUserName) {
		this.appointedUserName = appointedUserName;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getFundid() {
		return fundid;
	}

	public void setFundid(String fundid) {
		this.fundid = fundid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getSelectedPegItemList() {
		return selectedPegItemList;
	}

	public void setSelectedPegItemList(List<String> selectedPegItemList) {
		this.selectedPegItemList = selectedPegItemList;
	}

	public Map<String, DetailEstimateDTO> getSelectedAdditionalMaterials() {
		return selectedAdditionalMaterials;
	}

	public void setSelectedAdditionalMaterials(
			Map<String, DetailEstimateDTO> selectedAdditionalMaterials) {
		this.selectedAdditionalMaterials = selectedAdditionalMaterials;
	}

	public Map<String, DetailEstimateDTO> getAddedItemsFromPegSchedule() {
		return addedItemsFromPegSchedule;
	}

	public void setAddedItemsFromPegSchedule(
			Map<String, DetailEstimateDTO> addedItemsFromPegSchedule) {
		this.addedItemsFromPegSchedule = addedItemsFromPegSchedule;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public List<String> getListContractor() {
		return listContractor;
	}

	public void setListContractor(List<String> listContractor) {
		this.listContractor = listContractor;
	}

	public List<String> getListContractorID() {
		return listContractorID;
	}

	public void setListContractorID(List<String> listContractorID) {
		this.listContractorID = listContractorID;
	}

	public String getContractorID() {
		return contractorID;
	}

	public void setContractorID(String contractorID) {
		this.contractorID = contractorID;
	}

	public String getContractor() {
		return contractor;
	}

	public void setContractor(String contractor) {
		this.contractor = contractor;
	}

	/*public String updateEstimation(){
		try{
			//EstimateEjb estimateEjb = new EstimateEjb(getRegion());
			//applicationRefNos = estimateEjb.loadApplicationRefnos(getUserId(),getCostCenterNo()); 
			//HttpServletRequest request = ServletActionContext.getRequest();
			//request.getSession().setAttribute("applicationRefNos",applicationRefNos);
			
			
			//System.out.println(getSecDeposit());
			//System.out.println(getSinNo());
			Spstdesthmt spstdesthmt = new Spstdesthmt();
			List<Spstdestdmt> lineTypeList = new ArrayList<Spstdestdmt>();
			SpstdesthmtPK id = new SpstdesthmtPK();
			id.setApplicationNo(getApplicationNo());
			id.setStdNo(getApplicationNo());
			spstdesthmt.setRejReason(getRejectedReason());
			spstdesthmt.setSecDeposit(getSecDeposit());
			spstdesthmt.setId(id);
			spstdesthmt.setDescription(getJobDescription());
			spstdesthmt.setTotalCost(getTotalCost());
			//spstdesthmt.setSinNo(getSinNo());
			HttpServletRequest request = ServletActionContext.getRequest();
			String region= (String) request.getSession().getAttribute("region");
			String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
			
			EstimateEjb estimateEjb = new EstimateEjb(region);
			WiringLandDetailEjb wiringLandDetailEjb= new WiringLandDetailEjb(region);
			ApplicationEjb applicationEjb = new ApplicationEjb(region);
			//PcestdttEjb pcestdttEjb = new PcestdttEjb();

			estimateEjb.updateStdEstimateDetails(getApplicationNo(),getSecDeposit(),getTotalCost(),region);
			
			Application application = applicationEjb.findByApplicationNo(applicationNo);
			
			wiringLandDetailEjb.updateDemand(application.getId().getApplicationId(),Long.parseLong(getDemand()),region);
			//loadEstimationRefNumbers();
			setLblSuccess("Application Details successfully updated");
			
		}catch(Exception e){
			//execute();
			setLblError("Error Occured in Updating Estimation Details");
			return SUCCESS;
		}
		
		return SUCCESS;
		
	}
		
	*/
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCostCenterNo() {
		return costCenterNo;
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
	public String getLblError() {
		return lblError;
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

	

	public String getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}


 
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}

	private void clearForm(){
		HttpServletRequest request = ServletActionContext.getRequest();
		resourceMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("detailEstimationDetails");
		//addedItemsFromPegSchedule = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("detailEstimationDetails");
		if(resourceMap != null){
			resourceMap.clear();
		}
		request.getSession().setAttribute("detailEstimationDetails",resourceMap);
		
		updatedDetailsMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("updatedDetails");
		
		if(updatedDetailsMap != null){
			updatedDetailsMap.clear();
		}
		request.getSession().setAttribute("updatedDetails",updatedDetailsMap);
		
		deletedDetailsMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("deletedDetails");
		
		if(deletedDetailsMap != null){
			deletedDetailsMap.clear();
		}
		request.getSession().setAttribute("deletedDetailsMap",deletedDetailsMap);
		
		alreadyAddedDetails = (Map<String,DetailEstimateDTO>) request.getSession().getAttribute("alreadyAddedDetails");
		

		if(alreadyAddedDetails != null){
			alreadyAddedDetails.clear();
		}
		request.getSession().setAttribute("alreadyAddedDetails",alreadyAddedDetails);
		
	
		dto = (EstimateDetails) request.getSession().getAttribute("estimateDetailobject");
		if(dto != null){
			dto = null;
		}
		request.getSession().setAttribute("estimateDetailobject",dto);
		
		selectedPegItemList = (List<String>) request.getSession().getAttribute("selectedpegIdList");
		if(dto != null){
			dto = null;
		}
		request.getSession().setAttribute("estimateDetailobject",dto);
		

		selectedPegschuleItems = (Map<String, PegItemDTO>) request.getSession().getAttribute("pegItemDetails");
		if(selectedPegschuleItems != null){
			selectedPegschuleItems.clear();
		}
		request.getSession().setAttribute("pegItemDetails",selectedPegschuleItems);
	
		allSelectedAdditionalResoMap = (Map<String, DetailEstimateDTO>) request.getSession().getAttribute("allSelectedAdditionalResources");

		if(allSelectedAdditionalResoMap != null){
			allSelectedAdditionalResoMap.clear();
		}
		request.getSession().setAttribute("allSelectedAdditionalResources",allSelectedAdditionalResoMap);
		
		request.getSession().setAttribute("selectedpegIdList",null);
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}
	public String getSelectCategoryString() {
		return selectCategoryString;
	}

	public void setSelectCategoryString(String selectCategoryString) {
		this.selectCategoryString = selectCategoryString;
	}

	public String getSelectedReport() {
		return selectedReport;
	}

	public void setSelectedReport(String selectedReport) {
		this.selectedReport = selectedReport;
	}

	

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public static String getViewpath() {
		return viewPath;
	}


	public InputStream getFileInputStream1() {
		return fileInputStream1;
	}

	public void setFileInputStream1(InputStream fileInputStream1) {
		this.fileInputStream1 = fileInputStream1;
	}

	public Map <String, String[]> getParameters() {
		return parameters;
	}
		

	public String getListRescd() {
		return listRescd;
	}

	public void setListRescd(String listRescd) {
		this.listRescd = listRescd;
	}

	public String getListResName() {
		return listResName;
	}

	public void setListResName(String listResName) {
		this.listResName = listResName;
	}

	public String getListQuantity() {
		return listQuantity;
	}

	public void setListQuantity(String listQuantity) {
		this.listQuantity = listQuantity;
	}

	public String getListUom() {
		return listUom;
	}

	public void setListUom(String listUom) {
		this.listUom = listUom;
	}

	public String getListUnitPrice() {
		return listUnitPrice;
	}

	public void setListUnitPrice(String listUnitPrice) {
		this.listUnitPrice = listUnitPrice;
	}

	public String getListEstimateCost() {
		return listEstimateCost;
	}

	public void setListEstimateCost(String listEstimateCost) {
		this.listEstimateCost = listEstimateCost;
	}

	public List<String> getListuserName() {
		return listuserName;
	}

	public void setListuserName(List<String> listuserName) {
		this.listuserName = listuserName;
	}

	public String getSmcType() {
		return smcType;
	}

	public void setSmcType(String smcType) {
		this.smcType = smcType;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	

	public Map<String, Object> getSession() {
		return session;
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
	public String newDirect()
	{
		clearForm();
		setErrorMessage(null);
		errorMessages.clear();
		setInfoFromMainMenu();
		//loadEstimationRefNumbers();//to load Application status to be changed
		//loadWorkEstimationRefNumbers();
		loadWareHouse();
		loadFundSources();
		setNewEntryFromMainMenu();
		return SUCCESS;
	}
public void setNewEntryFromMainMenu(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String sessionKey= (String) request.getSession().getAttribute("region");
		String estimateNo=request.getParameter("estNo");
		String costCen=request.getParameter("costCen");
		if(estimateNo!=null && estimateNo.length()>0 && costCen!=null && costCen.length()>0){
			setEstimateNo(estimateNo);
			setCostCenterNo(costCen);
			//viewApplicantDetails();
			
		}
		List<String> wEstimationRefNos = new ArrayList<String>();
		List<String> sEstimationRefNos = new ArrayList<String>();
		if(estimateNo != null){
			wEstimationRefNos.add(estimateNo);
			
			request.getSession().setAttribute("estimateNos",wEstimationRefNos);
		}
		EstimateReferenceEjb estejb = new EstimateReferenceEjb();
		EstimateReference refe = estejb.findByWorkEstimateNo(estimateNo,costCenterNo, sessionKey);
		if(refe != null){
			
			sEstimationRefNos.add(refe.getId().getStandardEstimateNo());
			request.getSession().setAttribute("estimationRefNos",sEstimationRefNos);
			//loadEstmationNumbers(request);
		}
		
		String userId= (String) request.getSession().getAttribute("userName");
		
		setUserId(userId);
		setRegion(sessionKey);
		
		
	}
	public void setNewEntryFromdashBoard(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String sessionKey= (String) request.getSession().getAttribute("region");
		String estimateNo=request.getParameter("estNo");
		String costCen=request.getParameter("costCen");
		if(estimateNo!=null && estimateNo.length()>0 && costCen!=null && costCen.length()>0){
			setEstimateNo(estimateNo);
			setCostCenterNo(costCen);
			//viewApplicantDetails();
			
		}
		List<String> wEstimationRefNos = new ArrayList<String>();
		List<String> sEstimationRefNos = new ArrayList<String>();
		if(estimateNo != null){
			wEstimationRefNos.add(estimateNo);
			
			request.getSession().setAttribute("estimateNos",wEstimationRefNos);
		}
		EstimateReferenceEjb estejb = new EstimateReferenceEjb();
		EstimateReference refe = estejb.findByWorkEstimateNo(estimateNo,costCenterNo, sessionKey);
		if(refe != null){
			
			sEstimationRefNos.add(refe.getId().getStandardEstimateNo());
			request.getSession().setAttribute("estimationRefNos",sEstimationRefNos);
			//loadEstmationNumbers(request);
		}
		
		String userId= (String) request.getSession().getAttribute("userName");
		
		setUserId(userId);
		setRegion(sessionKey);
		
		
	}
	public String undoDirect()
	{
		clearForm();
		setErrorMessage(null);
		errorMessages.clear();
		setInfoFromMainMenu();
		//loadEstimationRefNumbers();//to load Application status to be changed
		//loadWorkEstimationRefNumbers();
		loadWareHouse();
		loadFundSources();
		setRejectedWEFromMainMenu();
		return SUCCESS;
	}
	public void setRejectedWEFromMainMenu(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String sessionKey= (String) request.getSession().getAttribute("region");
		String estimateNo=request.getParameter("estNo");
		String costCen=request.getParameter("costCen");
		if(estimateNo!=null && estimateNo.length()>0 && costCen!=null && costCen.length()>0){
			setEstimateNo(estimateNo);
			setCostCenterNo(costCen);
			//viewApplicantDetails();
			
		}
		List<String> wEstimationRefNos = new ArrayList<String>();
		List<String> sEstimationRefNos = new ArrayList<String>();
		if(estimateNo != null){
			wEstimationRefNos.add(estimateNo);
			
			request.getSession().setAttribute("estimateNos",wEstimationRefNos);
		}
		EstimateReferenceEjb estejb = new EstimateReferenceEjb();
		EstimateReference refe = estejb.findByWorkEstimateNo(estimateNo,costCenterNo, sessionKey);
		if(refe != null){
			
			sEstimationRefNos.add(refe.getId().getStandardEstimateNo());
			request.getSession().setAttribute("estimationRefNos",sEstimationRefNos);
			//loadEstmationNumbers(request);
		}
		
		String userId= (String) request.getSession().getAttribute("userName");
		
		setUserId(userId);
		setRegion(sessionKey);
		
		
	}
	private void loadEstimateNos(){
		setLoggedData();
		List<String> estimationRefNos = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		
		PcesthttEjb pcesthttEjb = new PcesthttEjb(region);
		List<Long> status = new ArrayList<Long>();
		
		
		if(getSessionKey("userRole").equalsIgnoreCase("ES")){
			status.add(new Long(EstimateStatus.EST_APPROVAL_ES));
			//status.add(new Long(EstimateStatus.EST_APPROVAL_ES2));
			estimationRefNos = pcesthttEjb.getEstimateNoList(costCenterNo,Constants.REV_NO,new Long(EstimateStatus.EST_APPROVAL_ES)); 
			
		}
		else if(getSessionKey("userRole").equalsIgnoreCase("EE")){
			status.add(new Long(EstimateStatus.EST_APPROVAL_EE));
			estimationRefNos = pcesthttEjb.getEstimateNoList(costCenterNo,Constants.REV_NO,new Long(EstimateStatus.EST_APPROVAL_EE)); 
			
		}else if(getSessionKey("userRole").equalsIgnoreCase("CE")){
			estimationRefNos = pcesthttEjb.getEstimateNoList(costCenterNo,Constants.REV_NO,new Long(EstimateStatus.EST_APPROVAL_CE)); 
			
		}/*else if(getSessionKey("userRole").equalsIgnoreCase("PE")){
			estimationRefNos = pcesthttEjb.getEstimateNoList(costCenterNo,new BigDecimal(EstimateStatus.EST_APPROVAL_DGM)); 
		}*/else if(getSessionKey("userRole").equalsIgnoreCase("DGM")){
			estimationRefNos = pcesthttEjb.getEstimateNoList(costCenterNo,Constants.REV_NO,new Long(EstimateStatus.EST_APPROVAL_DGM)); 
		}
		
		//request.getSession().setAttribute("estimationRefNos",estimationRefNos);
		request.getSession().setAttribute("estimateNos",estimationRefNos);	
	}
	public void setLoggedData() 
	{
		
		
		/*setLoggedInUserLevel(getSession().get("userRole").toString());
		
		setLoggedInUserId(getSession().get("userName").toString());*/
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
	
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		setLoggedInUserLevel(getSession().get("userRole").toString());
		String smcType= (String) request.getSession().getAttribute("smcType");
		setCostCenterNo(costCenterNo);
		setUserId(userId);
		setRegion(region);
		setApplicationType(smcType);
	}
	public String approve()
	{
		
		System.out.println("hi  SMS");
		try{
			setLoggedData();
			HttpServletRequest request = ServletActionContext.getRequest();
			
			PcesthttEjb pcesthttEjb = new PcesthttEjb(region);
			
			String estimateNo= getEstimateNo();
			setErrorMessage(null);
			String usernamePostedTo = getPostUserName();
			String msg = pcesthttEjb.approveEstimate(estimateNo.trim(), getCostCenterNo(), getSession().get("userRole").toString(),getSessionKey("userName"),usernamePostedTo,getSession().get("estimateType").toString(),getApprovalComment());
			int errorIndex = msg.indexOf('@');
			int successIndex = msg.indexOf('#');
			msg =msg.substring(1);
			//earFields();
			//tFormData();
			if(errorIndex!=-1){
				setMessageType(MSG_ERROR);
				setErrorMessage(msg);
				
			}else if(successIndex!=-1){
				setMessageType(MSG_DONE);
			
				setErrorMessage(msg);
			}
		}catch(Exception e){
			return ERROR;
		}
		loadEstimateNos();
		return SUCCESS;
	}
	public String recommendEstimate()
	{
		
		System.out.println("hi  SMS RECCOMEND");
		try{
			setLoggedData();
			HttpServletRequest request = ServletActionContext.getRequest();
			
			PcesthttEjb pcesthttEjb = new PcesthttEjb(region);
			
			String estimateNo= getEstimateNo();
			setErrorMessage(null);
			String usernamePostedTo = getPostUserName();
			String msg = pcesthttEjb.recommendEstimate(estimateNo.trim(), getCostCenterNo(), getSession().get("userRole").toString(),getSessionKey("userName"),usernamePostedTo,getSession().get("estimateType").toString(),getApprovalComment());
			int errorIndex = msg.indexOf('@');
			int successIndex = msg.indexOf('#');
			msg =msg.substring(1);
			//earFields();
			//tFormData();
			if(errorIndex!=-1){
				setMessageType(MSG_ERROR);
				setErrorMessage(msg);
				
			}else if(successIndex!=-1){
				setMessageType(MSG_DONE);
			
				setErrorMessage(msg);
			}
		}catch(Exception e){
			return ERROR;
		}
		loadEstimateNos();
		return SUCCESS;
	}
	public String reject()
	{
		try{
		setLoggedData();
		HttpServletRequest request = ServletActionContext.getRequest();
	
		PcesthttEjb pcesthttEjb = new PcesthttEjb(region);
		ApproveDetails detail = new ApproveDetails();
		String estimateNo= getEstimateNo();
		
		//PcesthttEjb pcesthttEjb = new PcesthttEjb(getSessionKey("region"));
		
		Pcesthtt pcesthtt = pcesthttEjb.findByEstimationNo(estimateNo, costCenterNo,Constants.REV_NO);
		
		Long preStatus = pcesthtt.getStatus();
		pcesthtt.setRejectReason(rejectReason);
		pcesthtt.setRejctUid(getSessionKey("userName"));
		pcesthtt.setRejctDt(new Date());
		pcesthtt.setStatus(new Long(EstimateStatus.EST_REJECTED));
		
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		
		//pcesthttEjb.updatePcesthtt(pcesthtt);
		Calendar calendar = Calendar.getInstance();
		Format format=new Format();
		Approval approval=new Approval();
		approval.setApprovalId(null);
		approval.setReferenceNo(estimateNo.trim());
		approval.setDeptId(costCenterNo);
		approval.setApprovalType("DES_RJCT");
		approval.setApprovedLevel(getSessionKey("userRole"));
		approval.setApprovedBy(getSessionKey("userName"));
		approval.setApprovedDate(calendar.getTime());
		approval.setApprovedTime(format.FormatTime());
		approval.setReason(getRejectReason());
		approval.setFromStatus(preStatus.toString());
		approval.setToStatus(pcesthtt.getStatus().toString());
		//************** comment for SAN_TEST ******************
		//approval.setStandardCost(new BigDecimal(Double.toString(nf.parse(getStandardCost(estimateNo,costCenterNo).toString()).doubleValue())));
		approval.setStandardCost(new BigDecimal("0.0"));
		
		BigDecimal cost = pcesthttEjb.findById(estimateNo, costCenterNo, Constants.REV_NO).getStdCost();
		String detailCost = null;
		if(cost != null){
			detailCost = cost.toString();
			approval.setDetailedCost(new BigDecimal(Double.toString(nf.parse(detailCost).doubleValue())));
			
		}
		
		pcesthttEjb.rejectEstimate(pcesthtt, approval);
		setMessageType(MSG_DONE);
		setErrorMessage("Estimate rejected successfully.");
		
		}
		catch(Exception e){}
		
		/*boolean status = false;
		if(estimateNo != null && getSessionKey("userRole").equalsIgnoreCase("EE")){
		
			
			//pcesthttEjb.changeStatusPcesthtt(estimateNo,costCenterNo,new BigDecimal(EstimateStatus.EST_REJECTED));
			pcesthttEjb.updateEstimateDetails(estimateNo,costCenterNo,new Long(EstimateStatus.EST_REJECTED),userId,new Date(),null,null,null,null,null,null,getRejectReason());
			setErrorMessage("DONE:- Rejected By EE");
		}else if(estimateNo != null && getSessionKey("userRole").equalsIgnoreCase("ES")){
		
			//pcesthttEjb.changeStatusPcesthtt(estimateNo,costCenterNo,new BigDecimal(EstimateStatus.EST_REJECTED));
			pcesthttEjb.updateEstimateDetails(estimateNo,costCenterNo,new Long(EstimateStatus.EST_REJECTED),userId,new Date(),null,null,null,null,null,null,getRejectReason());
			setErrorMessage("DONE:- Rejected By ES");
		}else if(estimateNo != null && getSessionKey("userRole").equalsIgnoreCase("CE")){
		
			//pcesthttEjb.changeStatusPcesthtt(estimateNo,costCenterNo,new BigDecimal(EstimateStatus.EST_REJECTED));
			pcesthttEjb.updateEstimateDetails(estimateNo,costCenterNo,new Long(EstimateStatus.EST_REJECTED),userId,new Date(),null,null,null,null,null,null,getRejectReason());
			setErrorMessage("DONE:- Rejected By CE");
		}else if(estimateNo != null && getSessionKey("userRole").equalsIgnoreCase("PE")){
		
			pcesthttEjb.changeStatusPcesthtt(estimateNo,costCenterNo,new BigDecimal(EstimateStatus.EST_REJECTED));
			
			setErrorMessage("DONE:- Rejected By PE");
		}else if(estimateNo != null && getSessionKey("userRole").equalsIgnoreCase("DGM")){
			
			//pcesthttEjb.changeStatusPcesthtt(estimateNo,costCenterNo,new BigDecimal(EstimateStatus.EST_REJECTED));
			pcesthttEjb.updateEstimateDetails(estimateNo,costCenterNo,new Long(EstimateStatus.EST_REJECTED),userId,new Date(),null,null,null,null,null,null,getRejectReason());
			setErrorMessage("DONE:- Rejected By DGM");
		}
		
		
		//if(status){
			setMessageType(MSG_DONE);
			
		//}
*/		loadEstimateNos();
		return SUCCESS;
	}
	public String PrintEstimateA4(){
		HttpServletRequest request = ServletActionContext.getRequest();		
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");		
		String region= (String) request.getSession().getAttribute("region");
		String smcType= (String) request.getSession().getAttribute("smcType");
		Path path = new Path();
		
		report rept = new report();
		String REPORT_DIRECTORY = "" ;
		String EXPORT_REPORT_DIRECTORY = "";
		if (System.getProperty("os.name").equals("Windows XP") || System.getProperty("os.name").equals("Windows 7") ){
			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY");
			EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY");

		}else{
			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY_LINUX");
			EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY_LINUX");
		}	
		SecurityEjb ejb=new SecurityEjb(region);
		String authCostCenters = "";
		List<String> authCost = ejb.getAuthorizedCostCenters(userId);
		
		String authCostList[] = authCost.toArray(new String[0]);		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("@project_no", "'"+getEstimateNo()+"'");
		param.put("@costctr","'"+costCenterNo+"'");

		String fileName = null;
		if(costCenterNo.equalsIgnoreCase("521.10") || costCenterNo.equalsIgnoreCase("521.20") ||
				costCenterNo.equalsIgnoreCase("521.30") || costCenterNo.equalsIgnoreCase("521.40") || 
				costCenterNo.equalsIgnoreCase("521.50") || costCenterNo.equalsIgnoreCase("522.10") || 
				costCenterNo.equalsIgnoreCase("522.20") || costCenterNo.equalsIgnoreCase("522.40") || 
				costCenterNo.equalsIgnoreCase("522.50") || costCenterNo.equalsIgnoreCase("522.60") ||
				costCenterNo.equalsIgnoreCase("523.10") || costCenterNo.equalsIgnoreCase("523.40") ||
				costCenterNo.equalsIgnoreCase("523.60") || costCenterNo.equalsIgnoreCase("523.70") || 
				costCenterNo.equalsIgnoreCase("514.20") || costCenterNo.equalsIgnoreCase("524.10") || 
				costCenterNo.equalsIgnoreCase("524.20") || costCenterNo.equalsIgnoreCase("524.30") || 
				costCenterNo.equalsIgnoreCase("524.40") || costCenterNo.equalsIgnoreCase("524.50") ||
				costCenterNo.equalsIgnoreCase("525.10") || costCenterNo.equalsIgnoreCase("525.20") || 
				costCenterNo.equalsIgnoreCase("525.30") || costCenterNo.equalsIgnoreCase("526.10") || 
				costCenterNo.equalsIgnoreCase("526.20") || costCenterNo.equalsIgnoreCase("526.30") || 
				costCenterNo.equalsIgnoreCase("526.40")){
				param.put("@devsec", "'"+getDivSec()+"'");
				param.put("@csc","'"+geteCSC()+"'");
				param.put("@area", "'"+getArea()+"'");
				param.put("@district","'"+getDistrict()+"'");
				param.put("@esname","'"+getEsname()+"'");
				fileName = rept.generateReport("EstimateGalle",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}else{
				param.put("@devsec", "'"+getDivSec()+"'");
				param.put("@csc","'"+geteCSC()+"'");
				param.put("@area", "'"+getArea()+"'");
				param.put("@district","'"+getDistrict()+"'");
				param.put("@esname","'"+getEsname()+"'");
				fileName = rept.generateReport("EstimateGalle",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}
		try {
			if (fileName == "")
			{
				setLblError("Error occured while generating the report");
				return SUCCESS;
			}
			fileInputStream = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}

		return "successprint";

	}
	public String PrintEstimate(){
		System.out.println("Hiii PrintEstimate");
		HttpServletRequest request = ServletActionContext.getRequest();
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		String region= (String) request.getSession().getAttribute("region");
		String smcType= (String) request.getSession().getAttribute("smcType");
		
		Path path = new Path();
		
		report rept = new report();
		String REPORT_DIRECTORY = "" ;
		String EXPORT_REPORT_DIRECTORY = "";
		if (System.getProperty("os.name").equals("Windows XP") || System.getProperty("os.name").equals("Windows 7") ){
			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY");
			EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY");

		}else{
			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY_LINUX");
			EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY_LINUX");
		}
		SecurityEjb ejb=new SecurityEjb(region);
		String authCostCenters = "";
		List<String> authCost = ejb.getAuthorizedCostCenters(userId);
		String authCostList[] = authCost.toArray(new String[0]);

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("@project_no", "'"+getEstimateNo()+"'");
		param.put("@costctr","'"+costCenterNo+"'");

		String fileName = null;
		if(costCenterNo.equals("543.10") || costCenterNo.equals("543.20") || costCenterNo.equals("543.40")){
			if(smcType.equalsIgnoreCase("MNT")){
				fileName = rept.generateReport("Estimate_MN",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			}else if(smcType.equalsIgnoreCase("TCI")){
				fileName = rept.generateReport("Estimate_GI",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			}else{
				fileName = rept.generateReport("Estimate_GI_TPV_TPJ_TPC_TPL",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			}
		}else{
			fileName = rept.generateReport("Estimate",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}
	
		try {
			if (fileName == "")
			{
				setLblError("Error occured while generating the report");
				return SUCCESS;
			}
			fileInputStream = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		}
		
		loadUserList(region,costCenterNo);
		return "successprint";


	}
	
	private void loadUserList(String region,String costCenterNo){
		List<String> usersList = new ArrayList<String>();
		
		
		SecurityEjb secejb=new SecurityEjb(region);
		List<String> userList  = null;
		if(branchType != null && branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_UNIT) ){
				usersList.add("ES");
				usersList.add("EE");
				
				
				userList = secejb.getAllUserList(costCenterNo,usersList);
		}else{
			usersList.add("ES");
			usersList.add("EE");
			userList = secejb.getUserList(costCenterNo,usersList);
		}
		
		
		Iterator<String> usit = userList.iterator();
		
		listuserName = new ArrayList<String>();
		while (usit.hasNext()) {        	 
	        	String esUser=usit.next();
	        	listuserName.add(esUser);		       	        	        	
		} 
	}
/*	public String PrintEstimateA3(){
		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("inside ViewGayani........");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		
		String region= (String) request.getSession().getAttribute("region");
		String smcType= (String) request.getSession().getAttribute("smcType");
		
		//if (this.selectedReport == null)
		//{
			//execute();
			//setLblError(this.selectCategoryString);
			//return SUCCESS;
		//}
		//String rpt = getSelectedReport();
		
		//executePrint();
		System.out.println("inside 3........");
		Path path = new Path();
		System.out.println("inside 4........");
		report rept = new report();
		String REPORT_DIRECTORY = "" ;
		String EXPORT_REPORT_DIRECTORY = "";
		if (System.getProperty("os.name").equals("Windows XP") || System.getProperty("os.name").equals("Windows 7") ){
			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY");
			EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY");

		}else{
			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY_LINUX");
			EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY_LINUX");
		}
		System.out.println("inside 5........");

		SecurityEjb ejb=new SecurityEjb(region);
		System.out.println("inside 6........");
		String authCostCenters = "";
		List<String> authCost = ejb.getAuthorizedCostCenters(userId);
		System.out.println("inside 7........");
		String authCostList[] = authCost.toArray(new String[0]);
		System.out.println("inside 8........");
		*//**if (authCostList != null)
		{
			for (String key : authCostList) {

				key = "'" +  key.trim() + "'";			
				if (key != "''")
				{
					authCostCenters = authCostCenters + "," + key;
				}
			}
			authCostCenters = authCostCenters.substring(1,authCostCenters.length());	
		}*//*





		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("@project_no", "'"+getEstimateNo()+"'");
		param.put("@costctr","'"+costCenterNo+"'");

		System.out.println("Application Number :  " + getApplicationNo());
		System.out.println("CC Number :  " + costCenterNo);
		param.put("@appNo", "'"+getApplicationNo()+"'");
		param.put("@costctr","'"+costCenterNo+"'");
		String fileName = null;
		if(costCenterNo.equals("510.20") || costCenterNo.equals("543.10") || costCenterNo.equals("543.20") || costCenterNo.equals("543.40")){
			if(smcType.equalsIgnoreCase("MNT")){
				fileName = rept.generateReport("Estimate_MN",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			}else if(smcType.equalsIgnoreCase("TCI")){
				fileName = rept.generateReport("Estimate_GI",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			}else{
				fileName = rept.generateReport("Estimate_GI_TPV_TPJ_TPC_TPL",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			}
		}else{
			fileName = rept.generateReport("Estimate",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}
		
		fileName = rept.generateReportA3("EstimateA3",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		
		
		
		System.out.println("inside ViewGayani");
		System.out.println("xxxxxxxxx"+ fileName);
		
		try {
			if (fileName == "")
			{
				setLblError("Error occured while generating the report");
				return SUCCESS;
			}
			fileInputStream = new FileInputStream(fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "successprintA3";


	}*/
	 public String PrintEstimateA3() {
		    HttpServletRequest request = ServletActionContext.getRequest();
			
			String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
			String userId= (String) request.getSession().getAttribute("userName");
			
			String region= (String) request.getSession().getAttribute("region");
			String smcType= (String) request.getSession().getAttribute("smcType");
		 
		 
	        DetailEstimatePrintDetails gPS= new DetailEstimatePrintDetails();
	       	List<String> listRescd = new ArrayList<String>();
			List<String> listResName = new ArrayList<String>();
			List<String> listQuantity = new ArrayList<String>();
			List<String> listUom = new ArrayList<String>();
			List<String> listUnitPrice = new ArrayList<String>();
			List<String> listEstimateCost = new ArrayList<String>();
	        
	       
	        
	        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	        Date date =new Date();
	        String currentDate = dateFormat.format(date);
	        
	        try {

	            PcesthttEjb pc = new PcesthttEjb(region);
	    		Pcesthtt pchtt =  pc.findByEstimationNo(estimateNo, costCenterNo,Constants.REV_NO);

	    		PcestdttEjb pd = new PcestdttEjb(region);
	    		List<Pcestdtt> pcdtts =  pd.findByEstimationNo(estimateNo, costCenterNo,Constants.REV_NO);
	    		
	    		int rowCount=pcdtts.size();
	    		
	            if (rowCount > 0) {
	               
	               for( Pcestdtt dt : pcdtts ){
						listRescd.add(dt.getId().getResCd());
						listResName.add(dt.getId().getResCd());
						
						if(dt.getEstimateQty() != null){
							listQuantity.add(String.valueOf(dt.getEstimateQty()));
						}else{
							listQuantity.add(String.valueOf("0"));
						}
						if (dt.getUom() != null) {
							listUom.add(String.valueOf(dt.getUom())); 
						}else {
							listQuantity.add(String.valueOf("NO"));
						}
						
						listUnitPrice.add(dt.getUnitPrice().toString()); 
						listEstimateCost.add(dt.getEstimateCost().toString());
					}
		
	                gPS.setTotResCount(listRescd.size());
	                int totPages = (listRescd.size() / 22);
	                gPS.setEstCat(pchtt.getCatCd()) ;
	                gPS.setFundSource(pchtt.getFundSource()) ;
	                gPS.setDescription(pchtt.getDescr()) ;
	                gPS.setEstimateDate(pchtt.getEtimateDt().toString());
	                gPS.setPrintDate(currentDate);
					
	                gPS.setArrEst_cost(listEstimateCost);
	                gPS.setArrEst_qty(listQuantity);
	                gPS.setArrPrice(listUnitPrice);
	                gPS.setArrUom(listUom);
	                gPS.setArrResCode(listRescd);
	                gPS.setArrResName(listRescd);
	               
	                gPS.setTotal(pchtt.getStdCost().doubleValue());
	            }
	        } catch (Exception ex) {
	            
	        }
	        StringBuilder builder = new StringBuilder();
	        JAXBContext contextObj;
			try {
				contextObj = JAXBContext.newInstance(DetailEstimatePrintDetails.class);
			
	        
	        Marshaller marshallerObj = contextObj.createMarshaller();
	        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	     
	        
	      try {
			marshallerObj .marshal(gPS, new FileOutputStream("D:\\PrintResult.xml"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			 
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//FileInputStream  fin=null;
			try {
				//fin = new FileInputStream ("D:\\PrintResult.xml");
				BufferedReader reader = new BufferedReader(new FileReader("D:\\PrintResult.xml"));
				String line = null;
				
				try {
					while ( (line = reader.readLine()) != null)
					    builder.append(line);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		    //I don't know what to put below this, to read FileInpuStream object fin

		   // String dexml = (String)xstream.fromXML(fin);

			//System.out.println(dexml);

		    // Close our input stream
		   	


			//System.out.println(dexml);

		   		
	        request.getSession().setAttribute("fillDataParams", builder.toString());
	        request.getSession().getAttribute("fillDataParams");
	       // request.getSession().setAttribute("reportName", "PeggingSchedule");
	       // request.getSession().setAttribute("NewJobPrinting", "NewJobPrinting");
	       
	      
	        return "PrintApplet";
	    }
	public String PrintSummaryEstimateA3(){
		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("inside ViewGayani........");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		
		String region= (String) request.getSession().getAttribute("region");
		String smcType= (String) request.getSession().getAttribute("smcType");
		
		//if (this.selectedReport == null)
		//{
			//execute();
			//setLblError(this.selectCategoryString);
			//return SUCCESS;
		//}
		//String rpt = getSelectedReport();
		
		//executePrint();
		System.out.println("inside 3........");
		Path path = new Path();
		System.out.println("inside 4........");
		report rept = new report();
		String REPORT_DIRECTORY = "" ;
		String EXPORT_REPORT_DIRECTORY = "";
		if (System.getProperty("os.name").equals("Windows XP") || System.getProperty("os.name").equals("Windows 7") ){
			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY");
			EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY");

		}else{
			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY_LINUX");
			EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY_LINUX");
		}
		System.out.println("inside 5........");

		SecurityEjb ejb=new SecurityEjb(region);
		System.out.println("inside 6........");
		String authCostCenters = "";
		List<String> authCost = ejb.getAuthorizedCostCenters(userId);
		System.out.println("inside 7........");
		String authCostList[] = authCost.toArray(new String[0]);
		System.out.println("inside 8........");
		/**if (authCostList != null)
		{
			for (String key : authCostList) {

				key = "'" +  key.trim() + "'";			
				if (key != "''")
				{
					authCostCenters = authCostCenters + "," + key;
				}
			}
			authCostCenters = authCostCenters.substring(1,authCostCenters.length());	
		}*/





		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("@project_no", "'"+getEstimateNo()+"'");
		param.put("@costctr","'"+costCenterNo+"'");

		/*System.out.println("Application Number :  " + getApplicationNo());
		System.out.println("CC Number :  " + costCenterNo);
		param.put("@appNo", "'"+getApplicationNo()+"'");
		param.put("@costctr","'"+costCenterNo+"'");*/
		String fileName = null;
		/*if(costCenterNo.equals("510.20") || costCenterNo.equals("543.10") || costCenterNo.equals("543.20") || costCenterNo.equals("543.40")){
			if(smcType.equalsIgnoreCase("MNT")){
				fileName = rept.generateReport("Estimate_MN",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			}else if(smcType.equalsIgnoreCase("TCI")){
				fileName = rept.generateReport("Estimate_GI",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			}else{
				fileName = rept.generateReport("Estimate_GI_TPV_TPJ_TPC_TPL",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
			}
		}else{
			fileName = rept.generateReport("Estimate",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		}*/
		
		fileName = rept.generateReportA3("EstimateSummaryA3",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		
		
		
		System.out.println("inside ViewGayani");
		System.out.println("xxxxxxxxx"+ fileName);
		
		try {
			if (fileName == "")
			{
				setLblError("Error occured while generating the report");
				return SUCCESS;
			}
			fileInputStream = new FileInputStream(fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "successprintA3";


	}
	public String PrintSummaryReport(){
		HttpServletRequest request = ServletActionContext.getRequest();
	
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		
		String region= (String) request.getSession().getAttribute("region");
		String smcType= (String) request.getSession().getAttribute("smcType");
		
		
		Path path = new Path();
		
		report rept = new report();
		String REPORT_DIRECTORY = "" ;
		String EXPORT_REPORT_DIRECTORY = "";
		if (System.getProperty("os.name").equals("Windows XP") || System.getProperty("os.name").equals("Windows 7") ){
			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY");
			EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY");

		}else{
			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY_LINUX");
			EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY_LINUX");
		}
	
		SecurityEjb ejb=new SecurityEjb(region);
		
		String authCostCenters = "";
		List<String> authCost = ejb.getAuthorizedCostCenters(userId);
		
		String authCostList[] = authCost.toArray(new String[0]);
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("@appNo", "'"+getEstimateNo()+"'");
		param.put("@costctr","'"+costCenterNo+"'");

		
		String fileSummary = rept.generateReport("Summary_Estimate",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		
		
		System.out.println("inside ViewGayani");
		System.out.println("xxxxxxxxx"+ fileSummary);
		
		try {
			if (fileSummary == "")
			{
				setLblError("Error occured while generating the report");
				return SUCCESS;
			}
			fileInputStream = new FileInputStream(fileSummary);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "successprint";


	}
	
	public String PrintEstimateVarianceReport(){
		HttpServletRequest request = ServletActionContext.getRequest();
	
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		
		String region= (String) request.getSession().getAttribute("region");
		String smcType= (String) request.getSession().getAttribute("smcType");
		
		
		Path path = new Path();
		
		report rept = new report();
		String REPORT_DIRECTORY = "" ;
		String EXPORT_REPORT_DIRECTORY = "";
		if (System.getProperty("os.name").equals("Windows XP") || System.getProperty("os.name").equals("Windows 7") ){
			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY");
			EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY");

		}else{
			REPORT_DIRECTORY = path.getReportPath("REPORT_DIRECTORY_LINUX");
			EXPORT_REPORT_DIRECTORY = path.getReportPath("EXPORT_REPORT_DIRECTORY_LINUX");
		}
	
		SecurityEjb ejb=new SecurityEjb(region);
		
		String authCostCenters = "";
		List<String> authCost = ejb.getAuthorizedCostCenters(userId);
		
		String authCostList[] = authCost.toArray(new String[0]);
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("@project_no", "'"+getEstimateNo()+"'");
		param.put("@costctr","'"+costCenterNo+"'");

		
		String fileSummary = rept.generateReport("EstimatewithVariance",param,getSession(),REPORT_DIRECTORY,EXPORT_REPORT_DIRECTORY);
		
		
		System.out.println("inside ViewGayani");
		System.out.println("xxxxxxxxx"+ fileSummary);
		
		try {
			if (fileSummary == "")
			{
				setLblError("Error occured while generating the report");
				return SUCCESS;
			}
			fileInputStream = new FileInputStream(fileSummary);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "successprint";


	}
	
	
	public String saveContractorAllocation(){
		try{
				
				
				JobEjb jobEjb =new JobEjb(getSessionKey("region"));
				SpestcntEjb ejbCnt=new SpestcntEjb(getSessionKey("region"));	
				String costCenter = getSessionKey("costCenterNo");					
						
				List<Spestcnd> spsetCndList =new ArrayList<Spestcnd>();
				BigDecimal jobInHand = null;
				BigDecimal totalAmount = null;
				Pcesthmt pchmt = null;
				PcesthmtEjb pc = new PcesthmtEjb(getSessionKey("region"));
				SpestcntPK spestcntpk = new SpestcntPK();
				spestcntpk.setContractorId(getContractor());
				spestcntpk.setDeptId(costCenter);
				int jobCount = 0;
				Double totAmount = (double) 0;
				 
				Spestcnt spestcnt=ejbCnt.findById(spestcntpk);
				if(spestcnt!=null){								 
					 jobInHand = spestcnt.getJobInHand();
					 totalAmount = spestcnt.getTotalAmount();
					 jobCount = jobInHand.intValue();
					 totAmount = totalAmount.doubleValue();
					 }
				
				if(estimateNo != null && !estimateNo.trim().equalsIgnoreCase(Constants.DEFAULT_STRING)){
					pchmt =  pc.findByEstimationNo(estimateNo, costCenterNo);
				}
		
				if(pchmt != null){								
					Spestcnd spestcnd = new Spestcnd();
					SpestcndPK spestcndPK = new SpestcndPK();
												
					spestcndPK.setContractorId(getContractor());
					spestcndPK.setDeptId(costCenter);
					spestcndPK.setProjectNo(pchmt.getProjectNo());
					spestcnd.setId(spestcndPK);
					spestcnd.setAmount(pchmt.getStdCost());
					//spestcnd.setConsumerName(valueList[1].trim());	
					Date dt= new Date();
					Format format=new Format();
					spestcnd.setAllocatedDate(dt);
					spestcnd.setAllocatedTime(format.FormatDate());
					spestcnd.setAllocatedUser(getAppointedUserName());
					spestcnd.setStatus(JobProcessStatus.ALLOCATED);						
					spsetCndList.add(spestcnd);
												
								
				}else{
					setMessageType(MSG_INFO);
					setErrorMessage("Job number was not still assigned");
				}
			
				
				if(spestcnt!=null){	
					spestcnt.setJobInHand(new BigDecimal(jobCount));
					spestcnt.setTotalAmount(new BigDecimal(totAmount));
											
				}
				
				jobEjb.alocateJob(spsetCndList,spestcnt);
				}catch(Exception ex){
				setMessageType(MSG_ERROR);
			    setErrorMessage("Error Occured.");
			    
			    return "error";
		}
		setLoggedData();
		initContractorAllocation();
		setMessageType(MSG_DONE);
		setErrorMessage("Successfully allocated the Contractor");
		return "success";
	}
	
	public String getDivSec() {
		return divSec;
	}

	public void setDivSec(String divSec) {
		this.divSec = divSec;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String geteCSC() {
		return eCSC;
	}

	public void seteCSC(String eCSC) {
		this.eCSC = eCSC;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getEsname() {
		return esname;
	}

	public void setEsname(String esname) {
		this.esname = esname;
	}

	public String getApprovalComment() {
		return approvalComment;
	}

	public void setApprovalComment(String approvalComment) {
		this.approvalComment = approvalComment;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}
	
	
	public String post() throws ParseException  
	{
		try{
			
			System.out.println("tttttttttttt");
			System.out.println("EstimateList");
			HttpServletRequest request = ServletActionContext.getRequest();
			setLoggedData();
			nf.setGroupingUsed(true);
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			
			
			System.out.println("tttttttttttt 1");
			System.out.println("EstimateList 2");
			PcesthttEjb pcesthttEjb = new PcesthttEjb(region);
			SecurityEjb secejb=new SecurityEjb(getSessionKey("region"));
			String usernamePostedTo = getPostUserNameNew();
			String userLevelPostedTo = secejb.getAuthorizedLevel(usernamePostedTo);
			String postinguserLevel = secejb.getAuthorizedLevel(userId);
			//String totalCost = getTotalCost();
			BigDecimal detailCost = new BigDecimal("0");
			//String[] estimateNo= getHiddenEstimateNo().split(",");
			//boolean status = false;
			
			if(  usernamePostedTo != null && getEstimateNo() != null){
			String applicationNo = getEstimateNo().trim();
			System.out.println("tttttttttttt 3" + getEstimateNo().trim());
			System.out.println("EstimateList 3");
				
					
			estimateNo=applicationNo;
				
			Calendar calendar = Calendar.getInstance();
			Format format=new Format();
			Approval approval=new Approval();
			approval.setApprovalId(null);
			approval.setReferenceNo(applicationNo);
			approval.setDeptId(costCenterNo);
			approval.setApprovalType("EE_VLDT");
			approval.setApprovedLevel(getSessionKey("userRole"));
			approval.setApprovedBy(getSessionKey("userName"));
			System.out.println("EstimateList 4");
			Long userStatus =null;
			if(userLevelPostedTo != null){
				if(userLevelPostedTo.trim().equalsIgnoreCase("ES")){
					//if(postinguserLevel.equalsIgnoreCase("DEO")){
						userStatus = Long.parseLong(EstimateStatus.EST_APPROVAL_ES);
						approval.setApprovedLevel(getSessionKey("userRole"));
						approval.setApprovedBy(getSessionKey("userRole"));
						approval.setApprovalType("EST_ENTY");
						
					//}else if(postinguserLevel.equalsIgnoreCase("ES")){
						//userStatus = StandardEstimateStatus.TO_BE_CHECKED_BY_ELECTRICAL_SUPIRINTENDENT2.getKey();
						//approval.setApprovedLevel(getSessionKey("userRole"));
						//approval.setApprovedBy(getSessionKey("userRole"));
						//approval.setApprovalType("EST_ENTY");
					//}
					
						System.out.println("EstimateList 5");
				}else if(userLevelPostedTo.trim().equalsIgnoreCase("EE")){
					userStatus = Long.parseLong(EstimateStatus.EST_APPROVAL_EE);
					approval.setApprovedLevel(getSessionKey("userRole"));
					approval.setApprovedBy(getSessionKey("userRole"));
					approval.setApprovalType("EST_ENTY");
					
				}else if(userLevelPostedTo.trim().equalsIgnoreCase("CE")){
					userStatus = Long.parseLong(EstimateStatus.EST_APPROVAL_CE);
					approval.setApprovedLevel(getSessionKey("userRole"));
					approval.setApprovedBy(getSessionKey("userRole"));
					approval.setApprovalType("EST_ENTY");
					System.out.println("EstimateList 6");
				}else if(userLevelPostedTo.trim().equalsIgnoreCase("DGM")){
					userStatus = Long.parseLong(EstimateStatus.EST_APPROVAL_DGM);
					approval.setApprovedLevel(getSessionKey("userRole"));
					approval.setApprovedBy(getSessionKey("userRole"));
					approval.setApprovalType("EST_ENTY");
					
				}
			}
			System.out.println("EstimateList 7");
			approval.setApprovedDate(calendar.getTime());
			approval.setApprovedTime(format.FormatTime());
			System.out.println("EstimateList 8");
			approval.setFromStatus(new BigDecimal(EstimateStatus.NEW
					).toString());
			approval.setToStatus(new BigDecimal(userStatus).toString());
			//************* comment for SAN_TEST ****************
			//approval.setStandardCost(new BigDecimal(Double.toString(nf.parse(getStandardCost(applicationNo,costCenterNo).toString()).doubleValue())));
			approval.setStandardCost(new BigDecimal("0.0"));
			PcesthttPK phtpk = new PcesthttPK();
			phtpk.setDeptId(costCenterNo);
			phtpk.setEstimateNo(applicationNo.trim());
			phtpk.setRevNo(Constants.REV_NO);
			System.out.println("EstimateList 9");
			Pcesthtt pcesthtt = pcesthttEjb.findById(phtpk);
			
			if(pcesthtt != null){
				detailCost = pcesthtt.getStdCost();
			}
			 
			if(detailCost != null ){
				approval.setDetailedCost(detailCost);
			}
			System.out.println("EstimateList 10");
			pcesthtt.setStatus(userStatus);
			pcesthtt.setAprUid5(usernamePostedTo);
			pcesthttEjb.validateEstimate(pcesthtt, approval);
			//clearFields();
			setMessageType(MSG_DONE);
			setErrorMessage("DONE:- Estimate sent for approval successfully!");
			//setSuccessMessage("Estimate sent for approval successfully!");
			/*String[] estimateNo= getHiddenEstimateNo().split(",");
			boolean status = false;
			if(estimateNo[0].trim() != null){
			
				pcesthttEjb.changeStatusPcesthtt(estimateNo[0].trim(),costCenterNo,new Long(EstimateStatus.EST_APPROVAL_EE));
				
				
			}
			
			
			//if(status){
			}	setMessageType(MSG_DONE);
				setErrorMessage("DONE:- Successfully Posted for EE");*/
			}	
			
		}catch(Exception e){
			return ERROR;
		}
		
		setInfoFromMainMenu();
		loadWorkEstimationRefNumbers();
		//fillEstimateList();
		//loaduserIds();
		return SUCCESS;
	}
	
	private void loaduserIds(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
		String smcType= (String) request.getSession().getAttribute("smcType");
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String branchType= (String) request.getSession().getAttribute("branchType");
		SecurityEjb secejb=new SecurityEjb(getSessionKey("region"));
		SpestedyEjb spestedyEjb=new SpestedyEjb(getSessionKey("region"));
		//DW
		List<String> userlevelList = new ArrayList<String>();
		List<String> userList =null;
		
		
		
		if(getSessionKey("userRole").equalsIgnoreCase("EE")){
			userlevelList.add("PE");
			userlevelList.add("CE");
			userlevelList.add("DGM");
		}else if (getSessionKey("userRole").equalsIgnoreCase("DEO")){
			userlevelList.add("ES");
		}else if (getSessionKey("userRole").equalsIgnoreCase("ES")){
			userlevelList.add("EE");
				
		}else{
			userlevelList.add("CE");
			userlevelList.add("EE");
			userlevelList.add("ES");
			userlevelList.add("DGM");
		}
		
		userList = secejb.getUserList(costCenterNo,userlevelList);
		
		
		
		if(branchType != null && (branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_UNIT) 
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_GOVERNMENT_INSTALLATION))){
				userlevelList.add("ES");
				userlevelList.add("EE");
				userlevelList.add("CE");
				userlevelList.add("DGM");
				
				userList = secejb.getAllUserList(costCenterNo,userlevelList);
		}
		if((getSessionKey("userRole").equalsIgnoreCase("EE") || getSessionKey("userRole").equalsIgnoreCase("CE") ) && costCenterNo.equalsIgnoreCase("420.90")){
			
			userlevelList.add("DGM");
			userList = secejb.getAllUserList(costCenterNo,userlevelList);
		}
		
        if(costCenterNo.equals("450.50")|| costCenterNo.equals("452.40")|| costCenterNo.equals("452.50")|| costCenterNo.equals("452.80")||costCenterNo.equals("452.90")||costCenterNo.equals("453.00")||costCenterNo.equals("453.10")||costCenterNo.equals("453.20")||costCenterNo.equals("453.30")||costCenterNo.equals("453.40")|| costCenterNo.equals("454.00")||costCenterNo.equals("454.10")||costCenterNo.equals("454.20")||costCenterNo.equals("454.50")||costCenterNo.equals("455.00")||costCenterNo.equals("455.10")||costCenterNo.equals("455.20")||costCenterNo.equals("455.30")||costCenterNo.equals("455.40")|| costCenterNo.equals("455.50")||costCenterNo.equals("456.00")||costCenterNo.equals("456.10")|| costCenterNo.equals("456.20")||costCenterNo.equals("456.30")||costCenterNo.equals("457.00")||costCenterNo.equals("457.10")||costCenterNo.equals("457.20")|| costCenterNo.equals("457.30")||costCenterNo.equals("450.00")|| costCenterNo.equals("450.80")||costCenterNo.equals("451.00")||costCenterNo.equals("451.10")||costCenterNo.equals("451.20")||costCenterNo.equals("451.30")||costCenterNo.equals("451.40")||costCenterNo.equals("451.50")||costCenterNo.equals("451.90") || costCenterNo.equals("452.00") || costCenterNo.equals("452.10")||costCenterNo.equals("452.20")||costCenterNo.equals("452.30")){
			
        	if (getSessionKey("userRole").equalsIgnoreCase("DEO")){
    			userlevelList.add("ES");
    			//costCenterNo = costCenterNo.substring(0, 3).concat(".00");
    			userList = secejb.getUserList(costCenterNo,userlevelList);
        	}else if(getSessionKey("userRole").equalsIgnoreCase("ES")){
        		userlevelList.add("EE");
        		userlevelList.add("CE");
        		costCenterNo = costCenterNo.substring(0, 3).concat(".00");
    			userList = secejb.getUserList(costCenterNo,userlevelList);
        	}else if(getSessionKey("userRole").equalsIgnoreCase("EE")){
        		userlevelList.add("CE");
				userlevelList.add("DGM");
        		costCenterNo = costCenterNo.substring(0, 3).concat(".00");
    			userList = secejb.getUserList(costCenterNo,userlevelList);
        	}
			
			
		}
		
		Iterator<String> usit = userList.iterator();
		
		 while (usit.hasNext()) {        	 
	        	String esUser=usit.next();
	        	
	        	listuserNameNew.add(esUser);		       	        	        	
	    } 
	}
	
	public String[] getDetailsList() {
		return DetailsList;
	}
	public void setDetailsList(String[] detailsList) {
		DetailsList = detailsList;
	}

	public void setPostUserNameNew(String postUserNameNew) {
		this.postUserNameNew = postUserNameNew;
	}

	public String getPostUserNameNew() {
		return postUserNameNew;
	}

	public void setListuserNameNew(List<String> listuserNameNew) {
		this.listuserNameNew = listuserNameNew;
	}

	public List<String> getListuserNameNew() {
		return listuserNameNew;
	}
	

	


	
	
	

}//End of class
