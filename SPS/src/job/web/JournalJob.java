package job.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import util.common.ApplicationSubType;
import util.common.ApplicationType;
import util.common.EstimateStatus;
import util.common.Format;
import util.common.Limit;
import util.common.SystemValue;
import util.common.TemporaryConnValue;

import application.model.Application;
import application.model.WiringLandDetail;
import application.model.WiringLandDetailPK;
import application.service.ApplicantEjb;
import application.service.ApplicationEjb;
import application.service.WiringLandDetailEjb;
import application.model.Applicant;

import estimate.service.PcestdttEjb;
import estimate.service.PcesthttEjb;
import estimate.service.PcjbtypmEjb;
import estimate.service.SpcndctmEjb;
import estimate.service.SpcrconvEjb;
import estimate.service.SpcstngmEjb;
import estimate.service.SpestlabEjb;
import estimate.service.SpserestEjb;
import estimate.ejb.Spugcblm;
import estimate.ejb.SpugcblmPK;
import estimate.model.Approval;
import estimate.model.FundSource;
import estimate.model.LabourGrid;
import estimate.model.Pcestdtt;
import estimate.model.Pcesthtt;
import estimate.model.Spcndctm;
import estimate.model.SpcndctmPK;
import estimate.model.Spcrconv;
import estimate.model.SpcrconvPK;
import estimate.model.SpcstngmPK;
import estimate.model.Spcstngm;
import estimate.model.Spestlab;
import estimate.model.SpestlabPK;
import estimate.model.Speststd;
import estimate.model.SpeststdPK;
import estimate.model.Splpsvcm;
import estimate.model.SplpsvcmPK;
import estimate.model.Spratesm;
import estimate.model.SpratesmPK;
import estimate.model.Spsecdep;
import estimate.model.SpsecdepPK;
import estimate.model.Spserest;
import estimate.service.SpeststdEjb;
import estimate.service.EstimateEjb;
import estimate.model.MaterialGrid;

import job.model.Pcestdmt;
import job.model.PcestdmtPK;
import job.model.Pctrxdmt;
import job.model.PctrxdmtPK;
import job.model.Pctrxhmt;
import job.model.PctrxhmtPK;
import job.service.PcestdmtEjb;
import job.service.PcesthmtEjb;
import job.service.PctrxdmtEjb;
import job.service.PctrxhmtEjb;
import job.model.Pcesthmt;
import job.service.JobEjb;

import com.opensymphony.xwork2.ActionSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import piv.model.PivDetail;
import piv.service.PivDetailEjb;


@SuppressWarnings("serial")
public class JournalJob extends ActionSupport implements SessionAware, ParameterAware,ServletResponseAware,ServletRequestAware{
	private static final Log log = LogFactory.getLog(JournalJob.class);
	private static final String viewPath="Manage Jobs>";
	
	NumberFormat nf = NumberFormat.getInstance();
    
    
	private HttpServletRequest request;
	private HttpServletResponse response;
	  
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private String errorMessage;
	private String successMessage;
	private String infoMessage;
	
	//session variables
	int count = 0;
	private Map<String, Object> sessionMap;
	String loggedUser;
	private String region;
	private String city;
	private String suburb;
	private String streetAddress;
	private String postalCode;
	private String estimateStatus;
	private String estimateStatusDesc;

	//JSP Fields
	private String costCenterNo;
	private String areaCostCenter;
	private String costCenterName;
	private String applicationNo;//to be removed....//DON"T REMOVE //DW
	
	private String jobNo;
	private String applicantName;
	private String address;
	private String neighboursAccNo;
	private String assessmentNo;
	private String description;
	private String applicantDate;
	private String phase;
	private String phaseDB;
	private String connectionType;
	private String connectionTypeDb;
	private String tariffCode;
	private String tariffCategory;
	private String conductorType;
	private String appSubType;
	private String appSubTypeDB;
	private String duration;
	private double durationDB;
	
	private String isLoopService;
	private String categoryCode;
	private String lineLength;
	private String loopLength;
	private String serviceLength;
	private String conductorLength;
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
	private String revReason;
	private long revNo;
	private List<FundSource> fundSourceList;
	//private List<String> fundSourceList;
	private List<String> categoryCodeList;
	private List<String> jobNoList;
	private String fundSourceID;
	private String wiringType;
	private String conversionLength;
	private String cableType;
	private String isStandardVC;
	private String conversionLength2P;
	private String circuitLength;
	
	private String fixedCost;
	private String variableCost;
	private String subTotal;
	private String otherCost;
	private String otherLabourCost;
	private String convCost;
	private String capitalCost;
	private String taxAmount;
	private String secDeposit;
	private String totalCost;
	private String addlDeposit;
	
	// Hidden Fields
	private String isViewApplicant;
	private String wireMeterPrice;
	private String isMatListPopUp;
	private String isMatListClose;
	private String isUndoReject;
	private String isApprove;
	private String isLineLenEntered;
	
	
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
	private String[] savedCusQty;
	//private String totalMatCost;
	private String totalDetailCost;
	//private String[] txtCusQty;//commented by gayani
	private String[] txtComQty;//added by gayani
	//Labour List variables
	private Collection selectLabList;
	private String[] txtSelLabQty;
	private String[] txtSelLabCost;
	private String totalLabCost;
	private String[] chkSelLabCode;
	private String isRemoveLabClicked;
	
	//other Fields
	private String path;
	private String state;
	private String jobType;
	
	
	//DW
	private String labourRate;
	private String transportRate;
	private String overheadRate;
	private String[] txtSelLabHrs;
	
    //Journal For Job
    private String jvNo;
    private String doc_pf = "JV_MIS";
	private java.util.Date jvDate = new Date(); 
	public String execute()	{
		
		setErrorMessage(null);
		setSuccessMessage(null);
		setInfoMessage(null);
		setLoggedData();
		System.out.println("JV Number : 0 "+ getJvNo());
		setFormData();
		//Danusha
		HttpServletRequest request = ServletActionContext.getRequest();
		
		System.out.println("state : 0 "+ state);
		//DW
		if (state!=null){
			if(state.equals("print")){
				setLoggedData();
				
				viewJob();
				return SUCCESS;
			}
		}
		
		//DW
		setPath(viewPath);
		setState("newEstimate");
			
		
	
		System.out.println("Hiii execute 7 " +  jobNo);
		if(jobNo!=null && jobNo.trim().length()>0 ){
			if(isRemoveLabClicked!=null && isRemoveLabClicked.equals("true")){
				removeLabour();
			}else{
				clearSessionVariables();
			}
			System.out.println("Hiii execute 8");
			viewJob();
			System.out.println("Hiii execute 15");
		}else{
			isUndoReject = null;
			isApprove = null;
		}
		System.out.println("Hiii execute 16");
		return SUCCESS;
	}
	
	
	public String undoReject()
	{
		setErrorMessage(null);
		setSuccessMessage(null);
		setInfoMessage(null);
		setLoggedData();
		isUndoReject = "true";
		setFormData();
		setPath(viewPath);
		return SUCCESS;
	}
	//DW
	public String printDirect(){
		setErrorMessage(null);
		setSuccessMessage(null);
		setInfoMessage(null);
		setLoggedData();
		setFormData();
		setState("print");
		setPath(viewPath);
		Format format=new Format();
		if (getSessionKey("smcType").equals("NC")){
			jobType="SMC";
		}else if (getSessionKey("smcType").equals("CR")){
			jobType="CRJ";
		}else {
			jobType="XXX";
		}
		jobNo =getSessionKey("costCenterNo")+"/"+jobType+"/"+format.getYear().substring(2, format.getYear().length())+"/";
		return SUCCESS;
	}
	
	
	public String sendToApproval()
	{
		try
		{
			setErrorMessage(null);
			setSuccessMessage(null);
			setInfoMessage(null);
			setLoggedData();
			setPath(viewPath);
			
			nf.setGroupingUsed(true);
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			
			PcesthmtEjb pcesthmtEjb = new PcesthmtEjb(getSessionKey("region"));
			//pcesthmtEjb.changeStatusPcesthmt(jobNo, costCenterNo, new BigDecimal(EstimateStatus.JOB_APPROVAL_ES));
			
			Calendar calendar = Calendar.getInstance();
			Format format=new Format();
			Approval approval=new Approval();
			approval.setApprovalId(null);
			approval.setReferenceNo(jobNo);
			approval.setDeptId(costCenterNo);
			approval.setApprovalType("JOB_VLDT");
			approval.setApprovedLevel(getSessionKey("userRole"));
			approval.setApprovedBy(getSessionKey("userName"));
			approval.setApprovedDate(calendar.getTime());
			approval.setApprovedTime(format.FormatTime());
			approval.setFromStatus(new BigDecimal(EstimateStatus.JOB_RIVISION).toString());
			approval.setToStatus(new BigDecimal(EstimateStatus.JOB_APPROVAL_ES).toString());
			approval.setStandardCost(new BigDecimal(nf.parse(totalCost).doubleValue()));
			approval.setDetailedCost(new BigDecimal(nf.parse(totalDetailCost).doubleValue()));
			
			//Pcesthmt pcesthmt = pcesthmtEjb.findByJobNo(jobNo, costCenterNo);
			Pcesthmt pcesthmt =null;
			if(costCenterNo.equals("547.00")||costCenterNo.equals("547.10")||costCenterNo.equals("547.20")|| costCenterNo.equals("542.00") || costCenterNo.equals("542.10")|| costCenterNo.equals("542.20") || costCenterNo.equals("541.00")|| costCenterNo.equals("541.10")|| costCenterNo.equals("541.20") || costCenterNo.equals("548.00")|| costCenterNo.equals("548.10")|| costCenterNo.equals("548.20") || costCenterNo.equals("541.40")){
				pcesthmt = pcesthmtEjb.findByJobNo(jobNo);
			}else{
				pcesthmt = pcesthmtEjb.findByJobNo(jobNo, costCenterNo);
			}
			
			pcesthmt.setStatus(new Long(EstimateStatus.JOB_APPROVAL_ES));
			pcesthmtEjb.validateJob(pcesthmt, approval);
			
			clearFields();
			setFormData();
			setSuccessMessage("Job sent for approval successfully!");
			return SUCCESS;
		}
		catch(Exception e)
		{
			return ERROR;
		}
	}
	
	public String undo()
	{
		setErrorMessage(null);
		setSuccessMessage(null);
		setInfoMessage(null);
		setLoggedData();
		setPath(viewPath);
		PcesthmtEjb pcesthmtEjb = new PcesthmtEjb(getSessionKey("region"));
		//Pcesthmt pcesthmt = pcesthmtEjb.findByJobNo(jobNo, costCenterNo);
		Pcesthmt pcesthmt = null;
		if(costCenterNo.equals("547.00")||costCenterNo.equals("547.10")||costCenterNo.equals("547.20")|| costCenterNo.equals("542.00") || costCenterNo.equals("542.10")|| costCenterNo.equals("542.20") || costCenterNo.equals("541.00")|| costCenterNo.equals("541.10")|| costCenterNo.equals("541.20") || costCenterNo.equals("548.00")|| costCenterNo.equals("548.10")|| costCenterNo.equals("548.20") || costCenterNo.equals("541.40")){
			pcesthmt = pcesthmtEjb.findByJobNo(jobNo);
		}else{
			pcesthmt = pcesthmtEjb.findByJobNo(jobNo, costCenterNo);
		}
		
		pcesthmt.setStatus(new Long(EstimateStatus.JOB_RIVISION));
		pcesthmtEjb.updatePcesthmt(pcesthmt);
		setFormData();
		clearFields();
		this.jobNo = null;
		setSuccessMessage("Rejected job undone successfully!");
		
		return SUCCESS;
	}
	
	public String approve()
	{
		setErrorMessage(null);
		setSuccessMessage(null);
		setInfoMessage(null);
		setLoggedData();
		isUndoReject = null;
		isApprove = "true";
		setFormData();
		setFromMainMenu();
		setPath(viewPath);
		return SUCCESS;
	}
	
	private void setFromMainMenu(){
		HttpServletRequest request = ServletActionContext.getRequest();
			
		String revisedProjectNo=request.getParameter("projectNo");	
		String revisedCostCen=request.getParameter("costCen");
		
				
		if(revisedProjectNo!=null && revisedProjectNo.length()>0 && revisedCostCen!=null && revisedCostCen.length()>0){
			setJobNo(revisedProjectNo.trim());
			setCostCenterNo(revisedCostCen.trim());
			
			if(jobNo!=null && jobNo.trim().length()>0 )
			{
				viewJob();
			}
		}
	}
	
	public String rejectJob()
	{
		setErrorMessage(null);
		setSuccessMessage(null);
		setInfoMessage(null);
		//setLoggedData();
		setPath(viewPath);
		PcesthmtEjb pcesthmtEjb = new PcesthmtEjb(getSessionKey("region"));
		//Pcesthmt pcesthmt = pcesthmtEjb.findByJobNo(jobNo, costCenterNo);
		Pcesthmt pcesthmt =null;
		//if(costCenterNo.equals("541.00") || costCenterNo.equals("541.10") || costCenterNo.equals("541.20") || costCenterNo.equals("542.00") || costCenterNo.equals("542.10") || costCenterNo.equals("542.20") || costCenterNo.equals("547.00")|| costCenterNo.equals("547.10")|| costCenterNo.equals("547.20") || costCenterNo.equals("548.00") || costCenterNo.equals("548.10") || costCenterNo.equals("548.20") || costCenterNo.equals("541.40")){
		if(costCenterNo.equals("547.00") || costCenterNo.equals("547.10") || costCenterNo.equals("547.20") || costCenterNo.equals("542.00") || costCenterNo.equals("542.10") || costCenterNo.equals("542.20") || costCenterNo.equals("541.00")|| costCenterNo.equals("541.10")|| costCenterNo.equals("541.20") || costCenterNo.equals("548.00") || costCenterNo.equals("548.10") || costCenterNo.equals("548.20") || costCenterNo.equals("541.40")){
			pcesthmt = pcesthmtEjb.findByJobNo(jobNo);
		}else{
			pcesthmt = pcesthmtEjb.findByJobNo(jobNo, costCenterNo);
		}
		
		
		
		Long preStatus = pcesthmt.getStatus();
		pcesthmt.setStatus(new Long(EstimateStatus.JOB_REJECTED));
		pcesthmt.setRejctUid(getSessionKey("userName"));
		pcesthmt.setRejctDt(new Date());
		pcesthmt.setRejectReason(rejectReason);
		try
		{
			//pcesthmtEjb.updatePcesthmt(pcesthmt);	
			
			nf.setGroupingUsed(true);
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			
			Calendar calendar = Calendar.getInstance();
			Format format=new Format();
			Approval approval=new Approval();
			approval.setApprovalId(null);
			approval.setReferenceNo(applicationNo);
			approval.setDeptId(costCenterNo);
			approval.setApprovalType("JOB_RJCT");
			approval.setApprovedLevel(getSessionKey("userRole"));
			approval.setApprovedBy(getSessionKey("userName"));
			approval.setApprovedDate(calendar.getTime());
			approval.setApprovedTime(format.FormatTime());
			approval.setReason(rejectReason);
			approval.setFromStatus(preStatus.toString());
			approval.setToStatus(pcesthmt.getStatus().toString());
			approval.setStandardCost(new BigDecimal(nf.parse(totalCost).doubleValue()));
			approval.setDetailedCost(new BigDecimal(nf.parse(totalDetailCost).doubleValue()));
			
			pcesthmtEjb.rejectJob(pcesthmt, approval);
		}
		catch(Exception e3){}

		
		setFormData();
		clearFields();
		setSuccessMessage("Job rejected successfully.");
		return SUCCESS;
	}
	
	
	/**public String approveJob()
	{
		try
		{
			setErrorMessage(null);
			setSuccessMessage(null);
			setInfoMessage(null);
			//setLoggedData();
			setPath(viewPath);
			PcesthmtEjb pcesthmtEjb = new PcesthmtEjb(getSessionKey("region"));
			Pcesthmt pcesthmt=pcesthmtEjb.findByEstimationNo(applicationNo.trim());
			nf.setGroupingUsed(true);
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			
			//Pcesthmt pcesthmt = pcesthmtEjb.findByJobNo(jobNo, costCenterNo);
			//	applicationNo = pcesthmt.getId().getEstimateNo();
			
			PivDetailEjb pivDetailEjb = new PivDetailEjb(getSessionKey("region"));
			
			PivDetail pivDetail = null;
			if(costCenterNo.equals("547.00")||costCenterNo.equals("547.10")||costCenterNo.equals("547.20")|| costCenterNo.equals("542.00") || costCenterNo.equals("542.10")|| costCenterNo.equals("542.20") || costCenterNo.equals("541.00")|| costCenterNo.equals("541.10")|| costCenterNo.equals("541.20") || costCenterNo.equals("548.00")|| costCenterNo.equals("548.10")|| costCenterNo.equals("548.20") || costCenterNo.equals("541.40")){
				pivDetail = pivDetailEjb.findByReferenceNo(applicationNo,"EST");
			}else{
				pivDetail = pivDetailEjb.findByReferenceNo(costCenterNo, applicationNo,"EST");
			}
			//if (wld!=null){
						
			//PivDetail pivDetail = pivDetailEjb.findByReferenceNo(costCenterNo, applicationNo,"EST");
			PivDetail loanPivDetail = null;
			if(costCenterNo.equals("547.00")||costCenterNo.equals("547.10")||costCenterNo.equals("547.20")|| costCenterNo.equals("542.00") || costCenterNo.equals("542.10")|| costCenterNo.equals("542.20") || costCenterNo.equals("541.00")|| costCenterNo.equals("541.10")|| costCenterNo.equals("541.20") || costCenterNo.equals("548.00")|| costCenterNo.equals("548.10")|| costCenterNo.equals("548.20") || costCenterNo.equals("541.40")){
				loanPivDetail = pivDetailEjb.findByReferenceNo(applicationNo,"ELN");
			}else{
				loanPivDetail = pivDetailEjb.findByReferenceNo(costCenterNo, applicationNo,"ELN");
			}
			
			
			//PivDetail loanPivDetail = pivDetailEjb.findByReferenceNo(costCenterNo, applicationNo,"ELN");
			
			
			
			double stdPivAmt = 0;
			double loanPivAmt = 0;
			if(pivDetail!=null)
				stdPivAmt = pivDetail.getGrandTotal().doubleValue();
			if(loanPivDetail!=null)
				loanPivAmt = loanPivDetail.getGrandTotal().doubleValue();
			double piv2Total = stdPivAmt+loanPivAmt;
			double totCost = nf.parse(totalCost).doubleValue();
			String msg = "";
			if (pcesthmt.getCatCd().trim().equals("SMC") || pcesthmt.getCatCd().trim().equals("CRJ") || pcesthmt.getCatCd().trim().equals("TMP") ){
				if(totCost>piv2Total){
					//System.out.println("has");
					msg = pcesthmtEjb.approveJob(jobNo, costCenterNo, getSession().get("userRole").toString(),getSession().get("userName").toString(),true);
				}else{
					msg = pcesthmtEjb.approveJob(jobNo, costCenterNo, getSession().get("userRole").toString(),getSession().get("userName").toString(),false);
				   // System.out.println("no");
				}
			}else{
					msg = pcesthmtEjb.approveJobMA_SA(jobNo.trim(), costCenterNo, getSession().get("userRole").toString(), getSession().get("userName").toString());
					//System.out.println("MA_SAo");
			}
			//setFormData();
			//clearFields();
			int errorIndex = msg.indexOf('@');
			int successIndex = msg.indexOf('#');
			msg =msg.substring(1);
			clearFields();
			
			setFormData();
			if(errorIndex!=-1)
				setErrorMessage(msg);
			else if(successIndex!=-1)
				setSuccessMessage(msg);
			
			//setErrorMessage(msg);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}*/
	
	
	//action invoke when Save button is clicked
	public String save()
	{
		try
		{
			loggedUser = getSession().get("userName").toString();
			setLoggedData();
			setPath(viewPath);
			
			PcesthmtEjb pcesthmtEjb = new PcesthmtEjb(getSessionKey("region"));
			Pcesthmt pcesthmt = pcesthmtEjb.findByJobNo(jobNo, costCenterNo);
			
		    
			revNo = pcesthmt.getId().getRevNo();
			String msg = modify(pcesthmt);
			if(msg==null)
				setSuccessMessage("Job modified successfully!");
			else
				setErrorMessage(msg);
			
			setFormData();
			
			
		}
		catch(Exception e)
		{
			setErrorMessage("Unexpected Error! Please retry.");
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	
	
	public String printJob(){
		return SUCCESS;
	}
	
	public String findJobNo(){
		setLoggedData();
		viewJob();
		return SUCCESS;
	}
	
	//action invoke when Find button is clicked
	public String viewJob()	{
		
		try	
		{
			
			setPath(viewPath);
			clearFields();
			setFormData();
	
			PcesthmtEjb pcesthmtEjb = new PcesthmtEjb(getSessionKey("region"));
			
			Pcesthmt pcesthmt =null;
			
			pcesthmt = pcesthmtEjb.findByJobNo(jobNo, costCenterNo);
			
			EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
			applicationNo = pcesthmt.getId().getEstimateNo().trim();
			
			
			
				setEstimateData(pcesthmt);
				System.out.println("Hiii execute 9");
				this.isViewApplicant = "true";	
				System.out.println("Hiii execute 10");
				return SUCCESS;
			
		}
		catch(Exception e){
			setErrorMessage("Unexpected Error! Please retry.");
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
		setFormData();
		return SUCCESS;
	}
	
	//action invoke when Close button is clicked
	public String close() {
		setLoggedData();
		return "close";
	}
	
	private void setFormData()
	{
		EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
    	fundSourceList = estimateEjb.getFundSourceJV(costCenterNo);
    	System.out.println("fundSourceList List : " + fundSourceList);
    	PcesthmtEjb pcesthmtEjb = new PcesthmtEjb(getSessionKey("region"));
    	PcjbtypmEjb pcjbtypmEjb = new PcjbtypmEjb(getSessionKey("region"));
    	categoryCodeList = pcjbtypmEjb.getCatCode(costCenterNo);
    	//jobNoList = pcesthmtEjb.getJobNoListWithoutOTnCO(costCenterNo, new BigDecimal(EstimateStatus.JOB_ONGOING ));
    
    	jobNoList = pcesthmtEjb.getJobNoList(costCenterNo, new Long(EstimateStatus.JOB_ONGOING ));
    	//Calendar myCal = myCal.getInstance()
    	Calendar calendar = Calendar.getInstance();
    	setJvDate(calendar.getTime());
    	//setJvDate(new Date());	
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
		this.tariffCode = "";
		this.tariffCategory = "";
		
		this.isLoopService = "";
		this.categoryCode = "";
		this.lineLength = "";
		this.loopLength = "";
		this.serviceLength = "";
		this.conductorLength = "";
		this.spans = "";
		this.poleNo = "";
		this.stayNo = "";
		this.strutsNo = "";
		this.sinNo = "";
		this.transColor = "";
		this.distanceToServicePlace = "";
		this.estimationType = "";
		this.jobDesc = "";
		this.fundSourceID = null;
		this.jvDate = new Date();
		this.jvNo = "";
		this.fixedCost = "";
		this.variableCost = "";
		this.subTotal = "";
		this.otherCost = "";
		this.otherLabourCost = "";
		this.convCost = "";
		this.capitalCost = "";
		this.taxAmount = "";
		this.secDeposit = "";
		this.totalCost = "";
		this.addlDeposit = "";
		
		clearSessionVariables();
	}
	
	private void clearSessionVariables()
	{
		sessionMap.remove("IsNewEstimate");
		sessionMap.remove("SelectMaterialList");
		sessionMap.remove("SavedMaterialList");
		sessionMap.remove("IsMaterialsAdd");
	}

	//modify estimate
	private String modify(Pcesthmt pcesthmt)throws Exception
	{
		System.out.println("Begin...");
		String msg = null;
		Format format=new Format();
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		Pctrxhmt pctrxhmt = null;
		double totalLabourCost = 0;
		double totalLabourHrs = 0;
		double otherC = 0;
		double otherLabourC = 0;
		double trx_cost = 0;
		Pctrxdmt pctrxdmt = null;		
		pcesthmt.setCatCd(categoryCode);
		pcesthmt.setDescr(jobDesc);
		pcesthmt.setStdCost(new BigDecimal(Double.toString(nf.parse(totalDetailCost).doubleValue())));
		
		
		if(fundSourceID!=null && fundSourceID.length()>0)
		{
			StringTokenizer st = new StringTokenizer(fundSourceID,"###");
			pcesthmt.setFundSource(st.nextToken());
			pcesthmt.setFundId(st.nextToken());
		}
		
		PcesthttEjb pcesthttEjb=new PcesthttEjb(getSessionKey("region"));
    	long newRevNo = pcesthmt.getId().getRevNo();
    	long httRevNo = pcesthttEjb.getHttRevNo(applicationNo,costCenterNo);
    	
		if(httRevNo!=-1)
		{
			newRevNo = httRevNo+1;
			
		}
		System.out.println("Begin...1");	
		//update pctrxhmt 
		PctrxhmtEjb pctrxhmtEjb = new PctrxhmtEjb(getSessionKey("region"));
		
		pctrxhmt = pctrxhmtEjb.findBy3PK(costCenterNo,getJvNo(),doc_pf);
		
		if(pctrxhmt != null){
			System.out.println("Begin...3");
			System.out.println("Begin...4" + getJvDate());
			pctrxhmt.setBatchId("");
			pctrxhmt.setStatus(new BigDecimal(2));
			pctrxhmt.setDocDt(new Date());
			pctrxhmt.setAcctDt(new Date());
			pctrxhmt.setTrxType(new BigDecimal(9));
			
			pctrxhmt.setEntBy("MIS");
			pctrxhmt.setEntDt(new Date());
			pctrxhmt.setModiBy("MIS");
			
			pctrxhmt.setModiDt(new Date());
			pctrxhmt.setRemarks("Modified By MIS");
			
		}else{
			System.out.println("Begin...4");
			pctrxhmt = new Pctrxhmt();
			PctrxhmtPK pctrxhmtPK = new PctrxhmtPK();
			pctrxhmtPK.setDeptId(costCenterNo);
			pctrxhmtPK.setDocNo(getJvNo());
			pctrxhmtPK.setDocPf(doc_pf);
			pctrxhmt.setId(pctrxhmtPK);
			pctrxhmt.setBatchId("");
			pctrxhmt.setStatus(new BigDecimal(2));
			pctrxhmt.setDocDt(new Date());
			pctrxhmt.setAcctDt(new Date());
			pctrxhmt.setTrxType(new BigDecimal(9));
			
			pctrxhmt.setEntBy("MIS");
			pctrxhmt.setEntDt(new Date());
			pctrxhmt.setModiBy("MIS");
			pctrxhmt.setModiDt(new Date());
			pctrxhmt.setRemarks("Modified By MIS");
			
		}
		
		LinkedHashMap selectMatCodeMap = (LinkedHashMap)sessionMap.get("SelectMaterialList");
		ArrayList<String> savedMat = (ArrayList<String>)sessionMap.get("SavedMaterialList");
		if(savedMat==null)
			savedMat = new ArrayList<String>();
		ArrayList insertList = null;
		ArrayList updateList = null;
		
		ArrayList insertListTrx = null;
		ArrayList updateListTrx = null;
		Pctrxdmt  pctrxdmtNew = null;
		
		if(selectMatCodeMap!=null && selectMatCodeMap.size()>0)
		{
			Iterator it = selectMatCodeMap.keySet().iterator();
			int i=0;
			
			PcestdmtEjb pcestdmtEjb = new PcestdmtEjb(getSessionKey("region"));
			PctrxdmtEjb pctrxdmtEjb = new PctrxdmtEjb(getSessionKey("region"));
			
			
			while(it.hasNext())
			{
				String matCode = (String)it.next();
				MaterialGrid tmpLst = (MaterialGrid)selectMatCodeMap.get(matCode);
				
				String uom  = tmpLst.getUom();
				String up  = tmpLst.getUnitPrice().toString();
				String matName  = tmpLst.getMatNm();
				String qty = txtSelQty[i];
				String cost = txtSelMatCost[i];
				String resType = tmpLst.getResType();
				BigDecimal resCat = tmpLst.getResCat();
				String comQty = txtComQty[i];
				BigDecimal comQtyTest = tmpLst.getCommittedQty();
				
				double comCost = 0;
				double trxAmt = 0;
				double tmpQty = Double.parseDouble(qty);
				if(comQty!=null && comQty.length()>0)
				{
					comCost = tmpLst.getUnitPrice().doubleValue()*new BigDecimal(comQty).doubleValue();
				}
				
				if(savedMat.contains(matCode))
				{
					Pcestdmt pcestdmt = pcestdmtEjb.findBy3PK(applicationNo, costCenterNo, matCode);
					pcestdmt.setEstimateCost(new BigDecimal(Double.toString(nf.parse(cost).doubleValue())));
					pcestdmt.setEstimateQty(new BigDecimal(Double.toString(nf.parse(qty).doubleValue())));
					
					if(comQty!=null && comQty.length()>0)
					{
						
						pcestdmt.setCommitedQty(new BigDecimal(Double.toString(nf.parse(comQty).doubleValue())));
						pcestdmt.setCommitedCost(new BigDecimal(comCost).setScale(2, BigDecimal.ROUND_HALF_UP));
						tmpLst.setCommittedQty(new BigDecimal(comQty));
						tmpLst.setCommittedCost(new BigDecimal(comCost));
												
					}
					
					BigDecimal comQtyNew = tmpLst.getCommittedQty();
					BigDecimal comQtyOld = tmpLst.getSavedQty();
					if(comQtyOld == null){
						comQtyOld = new BigDecimal(0);
					}
					BigDecimal ajq = new BigDecimal(0);                  
					
					if(comQtyNew ==null){
						comQtyNew = new BigDecimal(0);
					}
					if(comQtyOld ==null){
						comQtyOld = new BigDecimal(0);
					}
					
					if(comQtyNew != null || comQtyOld != null){
						ajq = comQtyNew.subtract(comQtyOld);
						
					}
					String add_ded = "";
					if(ajq.doubleValue() > 0){
						System.out.println("AJ QU + : "+ ajq.doubleValue());
						add_ded = "ADD";
					}else{
						System.out.println("AJ QU  - : "+ ajq.doubleValue());
						add_ded = "DED";
					}
					
					
					
					//try{
						
						//pctrxdmt = pctrxdmtEjb.findBy3PK( costCenterNo ,  getJvNo() , doc_pf, getCount());
						
					//}catch(Exception e){
						//System.out.println("Error : "+ e);
					//}
					
					
					if(ajq.doubleValue() != 0){
						count ++;
						setCount(getCount());
						
						//if(pctrxdmt == null){
						System.out.println("Count : "+ getCount() + getJvNo());
							System.out.println("Begin...5");
							PctrxdmtPK pctrxdmtPK = new PctrxdmtPK();
							pctrxdmtPK.setDeptId(costCenterNo);
							pctrxdmtPK.setDocNo(getJvNo());
							pctrxdmtPK.setDocPf(doc_pf);
							pctrxdmtPK.setSeqNo(getCount());
							pctrxdmtNew = new Pctrxdmt();
							pctrxdmtNew.setId(pctrxdmtPK);
							pctrxdmtNew.setProjectNo(jobNo);
							pctrxdmtNew.setRevNo(new BigDecimal(2));
							pctrxdmtNew.setResCd(matCode);
							pctrxdmtNew.setAddDed(add_ded);
							pctrxdmtNew.setUomCd(uom);
							pctrxdmtNew.setDueQty(new BigDecimal(0));
							pctrxdmtNew.setTrxQty(ajq);
							
							trxAmt = tmpLst.getUnitPrice().doubleValue()* ajq.doubleValue();
							pctrxdmtNew.setTrxAmt(new BigDecimal(trxAmt));
							
							trx_cost = trx_cost + trxAmt;
							System.out.println("Transaction Quantity : " +trx_cost );
							if(insertListTrx == null)
								insertListTrx = new ArrayList();
							
							insertListTrx.add(pctrxdmtNew);
								
							System.out.println("Begin...6");
						//}
						/**else
						{   
							
							pctrxdmt.setProjectNo(jobNo);
							pctrxdmt.setRevNo(new BigDecimal(2));
							pctrxdmt.setResCd(matCode);
							pctrxdmt.setAddDed(add_ded);
							pctrxdmt.setUomCd(uom);
							pctrxdmt.setDueQty(new BigDecimal(0));
							pctrxdmt.setTrxQty(ajq);
							
							trxAmt = tmpLst.getUnitPrice().doubleValue()* ajq.doubleValue();
							pctrxdmt.setTrxAmt(new BigDecimal(trxAmt));
							
							
							trx_cost = trx_cost + trxAmt;
							System.out.println("Transaction Quantity : " +trx_cost );
							if(updateListTrx == null)
								updateListTrx = new ArrayList();
							
							if(pctrxdmt != null)
								updateListTrx.add(pctrxdmt);
							System.out.println("Begin...7");
							
						}*/
						
					
					}
					
						        
					
					if(updateList==null)
						updateList = new ArrayList();
					updateList.add(pcestdmt);
					
					
					
					pcestdmtEjb.updatePcestdmt(pcestdmt);
					
					
					
				}
				else
				{
					Pcestdmt pcestdmt = new Pcestdmt();
					
					PcestdmtPK pcestdmtPK = new PcestdmtPK();
					pcestdmtPK.setDeptId(costCenterNo);
					pcestdmtPK.setEstimateNo(applicationNo);
					pcestdmtPK.setResCd(matCode);
					pcestdmtPK.setRevNo(1);
										
					pcestdmt.setId(pcestdmtPK);
					pcestdmt.setEstimateCost(new BigDecimal(Double.toString(nf.parse(cost).doubleValue())));
					pcestdmt.setEstimateQty(new BigDecimal(Double.toString(nf.parse(qty).doubleValue())));
					pcestdmt.setResType(resType);
					pcestdmt.setResCat(resCat);
					pcestdmt.setUnitPrice(new BigDecimal(Double.toString(nf.parse(up).doubleValue())));
					pcestdmt.setUom(uom);
					pcestdmt.getId().setRevNo(newRevNo);
					
					if(comQty!=null && comQty.trim().length()>0)
					{
						pcestdmt.setCustomerQty(new BigDecimal(comQty));
						tmpLst.setCustomerQty(new BigDecimal(comQty));
					}
					
					if(insertList==null)
						insertList = new ArrayList();
					insertList.add(pcestdmt);
					savedMat.add(matCode);
			   
				}
				
				sessionMap.put("SavedMaterialList",savedMat);
				tmpLst.setEstimateQty(new BigDecimal(qty));
				tmpLst.setEstimateCost(new BigDecimal(nf.parse(cost).doubleValue()));
				i++;
				
				
				
			}
			if(pctrxhmt != null){
				pctrxhmt.setTrxVal(new BigDecimal(trx_cost));
			}
			
		}
		System.out.println("Begin...7 : " +pctrxhmt );
		JobEjb jobEjb = new JobEjb(getSessionKey("region"));
		jobEjb.update(pctrxhmt, pcesthmt, insertListTrx, updateListTrx, updateList, newRevNo);
		
		return msg;
		
	}
	
	
	
	
	private void setPctrxHmtData(){
		//PctrxhmtEjb pctrxhmtEjb=new PctrxhmtEjb(getSessionKey("region"));
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
	
	
	//set wiring details to the form fields
	private void setWiringData(Application application)
	{
		WiringLandDetailEjb wlEJB = new WiringLandDetailEjb(getSessionKey("region"));
		WiringLandDetailPK wlPK = new WiringLandDetailPK();
		wlPK.setApplicationId(application.getId().getApplicationId());
		wlPK.setDeptId(costCenterNo);
		WiringLandDetail wld = null;
		if(costCenterNo.equals("547.00")||costCenterNo.equals("547.10")||costCenterNo.equals("547.20")|| costCenterNo.equals("542.00") || costCenterNo.equals("542.10")|| costCenterNo.equals("542.20") || costCenterNo.equals("541.00")|| costCenterNo.equals("541.10")|| costCenterNo.equals("541.20") || costCenterNo.equals("548.00")|| costCenterNo.equals("548.10")|| costCenterNo.equals("548.20") || costCenterNo.equals("541.40")){
			wld = wlEJB.findByApplicationNo(application.getApplicationNo());
		}else{
			wld = wlEJB.findByAppId(wlPK);
		}
		if (wld!=null){
		
			assessmentNo = wld.getAssessmentNo();
			neighboursAccNo = wld.getNeighboursAccNo();
			phaseInt = wld.getPhase().intValue();
			phaseDB = wld.getPhase().toString();
			if(phaseInt==1)
				phase = "SINGLE";
			else
				phase = "THREE";
			connectionTypeInt = wld.getConnectionType().intValue();
			connectionType = wld.getConnectionType().toString()+" A";
			connectionTypeDb = wld.getConnectionType().toString();
			tariffCode = wld.getTariffCode();
			tariffCategoryCode = wld.getTariffCatCode();
			tariffCategory = wld.getTariffCatCode();
			//isLoopService = wld.getIsLoopService();
			streetAddress = wld.getServiceStreetAddress();
			suburb = wld.getServiceSuburb();
			city = wld.getServiceCity();
			if(wld.getServicePostalCode()!=null)
				postalCode = wld.getServicePostalCode().toString();
			address = wld.getServiceStreetAddress()+", "+wld.getServiceSuburb()+", "+wld.getServiceCity();
		}
	}
	
	//set estimate details to the form fields
	private void setEstimateData(Pcesthmt pcesthmt){
		System.out.println("Hi setEstimateData 1" );
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		
		sessionMap.put("IsNewEstimate", "N");
		estimateStatus = pcesthmt.getStatus().toString();
		EstimateStatus es = new EstimateStatus();
		estimateStatusDesc = es.searchStatus(pcesthmt.getStatus().intValue());
		jobDesc = pcesthmt.getDescr();
		revReason = pcesthmt.getRevReason();
		categoryCode = pcesthmt.getCatCd();
		if(categoryCode!=null)
			categoryCode = categoryCode.trim();
		rejectReason = pcesthmt.getRejectReason();
		revNo = pcesthmt.getId().getRevNo();
		String fundSource = pcesthmt.getFundSource();
		String fundId = pcesthmt.getFundId();
		if(fundSource!=null)
			fundSource = fundSource.trim();
		if(fundId!=null)
			fundId = fundId.trim();
		this.fundSourceID = fundSource+"###"+fundId;
		
		System.out.println("Hi setEstimateData 1" );
			//get materials from dmt table
			JobEjb jobEjb = new JobEjb(getSessionKey("region"));
			System.out.println("JV : "+applicationNo);
			selectMatList = jobEjb.getMaterialGridNew(applicationNo, costCenterNo);
			LinkedHashMap matMap = new LinkedHashMap();
			System.out.println(" ####################" + selectMatList );
			Iterator it = selectMatList.iterator();
			ArrayList<String> savedMat = new ArrayList<String>();
			while(it.hasNext())
			{
				MaterialGrid grid = (MaterialGrid)it.next();
				if(grid.getResCd() != null){
					matMap.put(grid.getResCd().trim(), grid);
					savedMat.add(grid.getResCd().trim());
				}
				
			}
			sessionMap.put("SelectMaterialList",matMap);
			sessionMap.put("SavedMaterialList",savedMat);
			System.out.println(" End Of setEstimateData : ####################" );
		
	}
	
	private void setEstimateCostData()
	{
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		
		/*PcesthmtEjb pcesthmtEjb = new PcesthmtEjb(getSessionKey("region"));
		Pcesthmt pcesthmt = pcesthmtEjb.findByEstimationNo(applicationNo,costCenterNo);
		//findByEstimationNo(applicationNo,costCenterNo);
		if(pcesthmt==null)
		{
			fixedCost = "0.00";
			variableCost = "0.00";
			subTotal = fixedCost;
			otherCost = "0.00";
			otherLabourCost = "0.00";
			convCost = "0.00";
			taxAmount = "0.00";
			secDeposit = "0.00";
			totalCost = "0.00";
		}
		else
		{*/
			SpeststdPK speststdPK = new SpeststdPK();
			speststdPK.setDeptId(costCenterNo);
			speststdPK.setEstimateNo(applicationNo);
			SpeststdEjb speststdEJB = new SpeststdEjb(getSessionKey("region"));
			Speststd speststd=null;
			if(costCenterNo.equals("547.00")||costCenterNo.equals("547.10")||costCenterNo.equals("547.20")|| costCenterNo.equals("542.00") || costCenterNo.equals("542.10")|| costCenterNo.equals("542.20") || costCenterNo.equals("541.00")|| costCenterNo.equals("541.10")|| costCenterNo.equals("541.20") || costCenterNo.equals("548.00")|| costCenterNo.equals("548.10")|| costCenterNo.equals("548.20") || costCenterNo.equals("541.40")){
				speststd = speststdEJB.findByEstimateNo(applicationNo);
			}else{
				speststd = speststdEJB.findById(speststdPK);
			}
			if (speststd!=null){
				//DW
				convCost="0.00";
				if(speststd.getConversionCost()!=null)
					convCost = nf.format(speststd.getConversionCost());
				capitalCost="0.00";
				if(speststd.getCapitalCost()!=null)
					capitalCost = nf.format(speststd.getCapitalCost());
				fixedCost = nf.format(speststd.getFixedCost());
				secDeposit = nf.format(speststd.getSecurityDeposit());
				variableCost = nf.format(speststd.getVariableCost());
				otherCost="0.00";
				if(speststd.getOtherCost()!=null)
					otherCost = nf.format(speststd.getOtherCost());
				otherLabourCost="0.00";
				if(speststd.getOtherLabourCost()!=null)
					otherLabourCost = nf.format(speststd.getOtherLabourCost());
				
				if(speststd.getAddlDeposit()!=null)
					addlDeposit = nf.format(speststd.getAddlDeposit());
				else
					addlDeposit = "0.00";
				//if(speststd.getTaxAmount()!=null)
					//taxAmount = nf.format(speststd.getTaxAmount());
				//else
					//taxAmount = "0.00";
				subTotal = nf.format(new Float(speststd.getFixedCost().floatValue()+speststd.getVariableCost().floatValue()));
				totalCost = nf.format(speststd.getTotalCost());
			}
		//}
	}
	
	private void displayCostComponents()
	{
		try
		{
			nf.setGroupingUsed(true);
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			double vc = 0;
			double fc = 0;
			double sd = 0;
			double coc = 0;
			EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
			if(lineLength!=null && lineLength.trim().length()>0)
			{
				Spserest spserest=new Spserest();
				SpserestEjb spserestEjb=new SpserestEjb(getSessionKey("region"));
				spserest=spserestEjb.findByApplicationNo(applicationNo);
				String isServiceConversion=spserest.getIsServiceConversion();
				
				
				
				//LOOP SERVICE
				if(isLoopService!=null && isLoopService.equals("Y"))
				{
					Splpsvcm splpsvcm;
					SplpsvcmPK splpsvcmPK = new SplpsvcmPK();
					splpsvcmPK.setDeptId(costCenterNo);
					splpsvcmPK.setWiringType(wiringType);
					double lineLen = Double.parseDouble(lineLength);
					SpcstngmEjb spcstngmEjb = new SpcstngmEjb(getSessionKey("region"));
					Spcstngm spcstngm = null;
										
					//DW
					//SERVICE CONVERSION
					if (isServiceConversion!=null)
					{
						System.out.println("isServiceConversion "+isServiceConversion);
						if (isServiceConversion.equals("3P-30A"))
						{
							if(wiringType.equals("OH"))
							{
								spcstngm = spcstngmEjb.getSpcstngm(new Long(3), new Long(30), tariffCategory, new Long(lineLength), costCenterNo);
								vc = lineLen*spcstngm.getWireMeterPrice().doubleValue();
								fc = spcstngm.getFixedCost().doubleValue();
							}
							else
							{//UG
								SpugcblmPK spugcblmPK = new SpugcblmPK();
								spugcblmPK.setCableType("16XPLE");
								spugcblmPK.setDeptId(costCenterNo);
								spugcblmPK.setPhase(Long.parseLong(phaseDB));
								spugcblmPK.setConnectionType(Long.parseLong(connectionTypeDb));
								spugcblmPK.setPhase(Long.parseLong("3"));
								spugcblmPK.setConnectionType(Long.parseLong("30"));
								Spugcblm spugcblm = estimateEjb.getSpugcblm(spugcblmPK);
								if(spugcblm!=null)
								{
									fc = spugcblm.getFixedCost().doubleValue();
									vc = lineLen*spugcblm.getWireMeterPrice().doubleValue();
								}
							}//END UG
						}
						else if (isServiceConversion.equals("3P-60A"))
						{
							if(wiringType.equals("OH"))
							{
								spcstngm = spcstngmEjb.getSpcstngm(new Long(3), new Long(60), tariffCategory, new Long(lineLength), costCenterNo);
								vc = lineLen*spcstngm.getWireMeterPrice().doubleValue();
								fc = spcstngm.getFixedCost().doubleValue();
							}
							else
							{//UG
								SpugcblmPK spugcblmPK = new SpugcblmPK();
								spugcblmPK.setCableType("35XPLE");
								spugcblmPK.setDeptId(costCenterNo);
								spugcblmPK.setPhase(Long.parseLong(phaseDB));
								spugcblmPK.setConnectionType(Long.parseLong(connectionTypeDb));
								spugcblmPK.setPhase(Long.parseLong("3"));
								spugcblmPK.setConnectionType(Long.parseLong("60"));
								Spugcblm spugcblm = estimateEjb.getSpugcblm(spugcblmPK);
								if(spugcblm!=null)
								{
									fc = spugcblm.getFixedCost().doubleValue();
									vc = lineLen*spugcblm.getWireMeterPrice().doubleValue();
								}
							}//END UG	
						}
						else if (isServiceConversion.equals("3P-30C"))
						{
							if(wiringType.equals("OH"))
							{
								SpcrconvEjb spcrconvEjb = new SpcrconvEjb(getSessionKey("region"));
								SpcrconvPK spcrconvPK = new SpcrconvPK();
								spcrconvPK.setFromConnectionType(30);
								spcrconvPK.setFromPhase(1);
								spcrconvPK.setToConnectionType(30);
								spcrconvPK.setToPhase(3);
								Spcrconv spcrconv = spcrconvEjb.findById(spcrconvPK);
								vc = Double.parseDouble(lineLength)*spcrconv.getWireMeterPrice().doubleValue();
								fc = spcrconv.getFixedCost().doubleValue();
							}
							else
							{//UG
								SpugcblmPK spugcblmPK = new SpugcblmPK();
								spugcblmPK.setCableType("16XPLE");
								spugcblmPK.setDeptId(costCenterNo);
								spugcblmPK.setPhase(Long.parseLong(phaseDB));
								spugcblmPK.setConnectionType(Long.parseLong(connectionTypeDb));
								spugcblmPK.setPhase(Long.parseLong("3"));
								spugcblmPK.setConnectionType(Long.parseLong("30"));
								Spugcblm spugcblm = estimateEjb.getSpugcblm(spugcblmPK);
								if(spugcblm!=null){
									fc = spugcblm.getFixedCost().doubleValue();
									vc = lineLen*spugcblm.getWireMeterPrice().doubleValue();
								}
							}//END UG
						}
						else if (isServiceConversion.equals("3P-60C"))
						{
							if(wiringType.equals("OH"))
							{
								SpcrconvEjb spcrconvEjb = new SpcrconvEjb(getSessionKey("region"));
								SpcrconvPK spcrconvPK = new SpcrconvPK();
								spcrconvPK.setFromConnectionType(30);
								spcrconvPK.setFromPhase(1);
								spcrconvPK.setToConnectionType(60);
								spcrconvPK.setToPhase(3);
								Spcrconv spcrconv = spcrconvEjb.findById(spcrconvPK);
								vc = Double.parseDouble(lineLength)*spcrconv.getWireMeterPrice().doubleValue();
								fc = spcrconv.getFixedCost().doubleValue();
							}
							else
							{//UG
								SpugcblmPK spugcblmPK = new SpugcblmPK();
								spugcblmPK.setCableType("35XPLE");
								spugcblmPK.setDeptId(costCenterNo);
								spugcblmPK.setPhase(Long.parseLong(phaseDB));
								spugcblmPK.setConnectionType(Long.parseLong(connectionTypeDb));
								spugcblmPK.setPhase(Long.parseLong("3"));
								spugcblmPK.setConnectionType(Long.parseLong("60"));
								Spugcblm spugcblm = estimateEjb.getSpugcblm(spugcblmPK);
								if(spugcblm!=null)
								{
									fc = spugcblm.getFixedCost().doubleValue();
									vc = lineLen*spugcblm.getWireMeterPrice().doubleValue();
								}
							}//END UG
						}
						else if (isServiceConversion.equals("0P_00A"))
						{
							splpsvcmPK.setPhase(Long.parseLong(phaseDB));
							splpsvcmPK.setConnectionType(Long.parseLong(connectionTypeDb));
							splpsvcm = estimateEjb.getSplpsvcm(splpsvcmPK);
							if(splpsvcm!=null)
							{
								fc = splpsvcm.getFixedCost().doubleValue();
								vc = lineLen*splpsvcm.getWireMeterPrice().doubleValue();
							}
						}
					}//END SERVICE CONVERSION
					else
					{
						splpsvcmPK.setPhase(Long.parseLong(phaseDB));
						splpsvcmPK.setConnectionType(Long.parseLong(connectionTypeDb));
						splpsvcm = estimateEjb.getSplpsvcm(splpsvcmPK);
						if(splpsvcm!=null)
						{
							fc = splpsvcm.getFixedCost().doubleValue();
							vc = lineLen*splpsvcm.getWireMeterPrice().doubleValue();
						}
						
					}
				}//END LOOP SERVICE
				else 
				{// NOT LOOP SERVICE
					//OVER HEAD
					if(wiringType.equals("OH"))
					{
						double lineLen = Double.parseDouble(lineLength);
						double serLen = Double.parseDouble(serviceLength);
						SpcstngmEjb spcstngmEjb = new SpcstngmEjb(getSessionKey("region"));
						Spcstngm spcstngm = null;
						System.out.println("@@@ OH con");
						ArrayList costList = new ArrayList();
						costList = calculateCosts();
						fc = (Double)costList.get(0);
						vc = (Double)costList.get(1);
						sd = (Double)costList.get(2);
					}//END OH AND NOT LOOPSERVICE 
					else {//UNDER GROUND
				
						SpugcblmPK spugcblmPK = new SpugcblmPK();
						spugcblmPK.setCableType(cableType);
						spugcblmPK.setDeptId(costCenterNo);
						spugcblmPK.setPhase(Long.parseLong(phaseDB));
						spugcblmPK.setConnectionType(Long.parseLong(connectionTypeDb));
						//DW
						if (isServiceConversion!=null)
						{
							if (isServiceConversion.equals("3P-30A"))
							{
								spugcblmPK.setPhase(Long.parseLong("3"));
								spugcblmPK.setConnectionType(Long.parseLong("30"));
							}
							else if (isServiceConversion.equals("3P-60A"))
							{
								spugcblmPK.setPhase(Long.parseLong("3"));
								spugcblmPK.setConnectionType(Long.parseLong("60"));
							}
							else
							{
								spugcblmPK.setPhase(Long.parseLong(phaseDB));
								spugcblmPK.setConnectionType(Long.parseLong(connectionTypeDb));
								
							}
						}
						else
						{
							spugcblmPK.setPhase(Long.parseLong(phaseDB));
							spugcblmPK.setConnectionType(Long.parseLong(connectionTypeDb));
						}
						
						//DW
						Spugcblm spugcblm = estimateEjb.getSpugcblm(spugcblmPK);
						if(spugcblm!=null)
						{
							double lineLen = Double.parseDouble(lineLength);
							fc = spugcblm.getFixedCost().doubleValue();
							vc = lineLen*spugcblm.getWireMeterPrice().doubleValue();
						}
					}//END UNDER GROUND
				}//END - NOT LOOP SERVICE
				if(appSubTypeDB!=null && appSubTypeDB.equals("C3"))	
				{
					if(durationDB==1)
					{
						vc = vc*TemporaryConnValue.CON3_FIRST_YEAR;
					}
					else
					{
						vc = vc*(TemporaryConnValue.CON3_FIRST_YEAR+(TemporaryConnValue.CON3_NEXT_YEARS*(durationDB-1)));
						
					}
				}
				//calculating conversion cost
				if(conversionLength!=null && conversionLength.length()>0)
				{
					int convLen = Integer.parseInt(conversionLength);
					//int convLen2P = Integer.parseInt(conversionLength2P);
					SpratesmPK spratesmpK = new SpratesmPK();
					spratesmpK.setApplicationType("NC");
					spratesmpK.setDeptId(costCenterNo);
					Spratesm spratesm = estimateEjb.getSpratesm(spratesmpK);
					if(spratesm!=null)
					{
						coc = spratesm.getConversionRate().doubleValue()*convLen;
					}
				}
				if(conversionLength2P!=null && conversionLength2P.length()>0)
				{
					int convLen2P = Integer.parseInt(conversionLength2P);
					SpratesmPK spratesmpK = new SpratesmPK();
					spratesmpK.setApplicationType("NC");
					spratesmpK.setDeptId(costCenterNo);
					Spratesm spratesm = estimateEjb.getSpratesm(spratesmpK);
					if(spratesm!=null)
					{
						coc = coc + spratesm.getConversionRate2p().doubleValue()*convLen2P;
					}
				}
				if(circuitLength!=null && circuitLength.length()>0)
				{
					int circuitLen = Integer.parseInt(circuitLength);
					SpratesmPK spratesmpK = new SpratesmPK();
					spratesmpK.setApplicationType("NC");
					spratesmpK.setDeptId(costCenterNo);
					Spratesm spratesm = estimateEjb.getSpratesm(spratesmpK);
					if(spratesm!=null)
					{
						//coc = coc + spratesm.getSecondCircuit().doubleValue()*circuitLen;
					}
					
				}
			
			}//end line len >0
			
			//Retrieving security deposit
			/*ApplicantEjb applicantEjb = new ApplicantEjb(getSessionKey("region"));
			Applicant applicant = applicantEjb.findById(idNo);
			if(applicant.getCebEmployee()!=null && applicant.getCebEmployee().equals("Y"))
			{
				sd = 0;
			}else{
				SpsecdepPK spsecdepPK = new SpsecdepPK();
				spsecdepPK.setTariffCatCode(tariffCategory);
				spsecdepPK.setDeptId(costCenterNo);
				spsecdepPK.setPhase(Long.parseLong(phaseDB));
				spsecdepPK.setConnectionType(Long.parseLong(connectionTypeDb));
				
				
				Spsecdep spsecdep = estimateEjb.getSpsecdep(spsecdepPK);
				if(spsecdep!=null){
					sd = spsecdep.getSecurityDeposit().doubleValue();
				}
			}*/
			
			//Retrieving capital cost
			WiringLandDetailEjb wlEJB = new WiringLandDetailEjb(getSessionKey("region"));
			WiringLandDetail wld = wlEJB.findByApplicationNo(applicationNo);
			int metal = wld.getMetalCrusher().intValue();
			int motor = wld.getMotorTotal().intValue();
			int sawMill  = wld.getSawMills().intValue();
			int weld = wld.getWeldingPlant().intValue();
			double cc = 0;
			if(metal>0 || sawMill>0 || weld>0)
			{
				if(phaseDB.equals("3")&&connectionTypeDb.equals("60"))
					cc = 100000;
				else if(phaseDB.equals("3")&&connectionTypeDb.equals("30"))
					cc = 100000;
				else if(phaseDB.equals("1")&&connectionTypeDb.equals("30"))
					cc = 75000;
				
			}
			
			double oc = 0;
			if(otherCost!=null && otherCost.trim().length()>0)
				oc = nf.parse(otherCost).doubleValue();
			
			double olc = 0;
			if(otherLabourCost!=null && otherLabourCost.trim().length()>0)
				olc = nf.parse(otherLabourCost).doubleValue();
			
			/*double cc = 0;
			if(capitalCost!=null && capitalCost.trim().length()>0)
				cc = nf.parse(capitalCost).doubleValue();*/
			double tax = 0;
			if(taxAmount!=null && taxAmount.trim().length()>0)
				tax = nf.parse(taxAmount).doubleValue();
			
			double st = fc+vc;
			double tc = st+oc+cc+tax+sd+coc;
	
			subTotal = nf.format(st);
			totalCost = nf.format(tc);
			variableCost = nf.format(vc);
			fixedCost = nf.format(fc);
			secDeposit = nf.format(sd);
			capitalCost = nf.format(cc);
			convCost = nf.format(coc);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//return SUCCESS;
	}
	
	private ArrayList calculateCosts()
	{
		ArrayList costList = new ArrayList();
		try
		{
			
			double vc = 0;
			double fc = 0;
			double sd = 0;
			double lineLen = Double.parseDouble(lineLength);
			double serLen = Double.parseDouble(serviceLength);
			SpcstngmEjb spcstngmEjb = new SpcstngmEjb(getSessionKey("region"));
			Spcstngm spcstngm = null;
			
			Spserest spserest=new Spserest();
			SpserestEjb spserestEjb=new SpserestEjb(getSessionKey("region"));
			spserest=spserestEjb.findByApplicationNo(applicationNo);
			String isServiceConversion=spserest.getIsServiceConversion();
			if(lineLen>SystemValue.LOWER_MAX_LINE_LENGTH)
			{
				if (isServiceConversion!=null && isServiceConversion.equals("3P-30A"))
				{
					spcstngm = spcstngmEjb.getSpcstngm(new Long(3), new Long(30), tariffCategory, new Long(SystemValue.LOWER_MAX_LINE_LENGTH), costCenterNo);
					vc = SystemValue.LOWER_MAX_LINE_LENGTH*spcstngm.getWireMeterPrice().doubleValue();
				}
				else if (isServiceConversion!=null && isServiceConversion.equals("3P-60A"))
				{
					spcstngm = spcstngmEjb.getSpcstngm(new Long(3), new Long(60), tariffCategory, new Long(SystemValue.LOWER_MAX_LINE_LENGTH), costCenterNo);
					vc = SystemValue.LOWER_MAX_LINE_LENGTH*spcstngm.getWireMeterPrice().doubleValue();
				}
				else
				{
					if(isStandardVC.equals("Y"))
					{
						spcstngm = spcstngmEjb.getSpcstngm(new Long(phaseDB), new Long(connectionTypeDb), tariffCategory, new Long(SystemValue.LOWER_MAX_LINE_LENGTH), costCenterNo);
						if (phase.trim().equals("SINGLE"))
						{
							vc = (SystemValue.LOWER_MAX_LINE_LENGTH-50)*spcstngm.getWireMeterPrice().doubleValue();
						}
						else
						{
							vc = SystemValue.LOWER_MAX_LINE_LENGTH*spcstngm.getWireMeterPrice().doubleValue();
						}
						SpcndctmEjb spcndctmEjb = new SpcndctmEjb(getSessionKey("region"));
						Spcndctm spcndctm = null;
						SpcndctmPK spcndctmPK = new SpcndctmPK();
						spcndctmPK.setPhase(new Long(phaseDB));
						spcndctmPK.setConductorType("FLY");
						spcndctm = spcndctmEjb.findById(spcndctmPK);
						double diff = lineLen-SystemValue.LOWER_MAX_LINE_LENGTH;
						vc = vc+ (diff*spcndctm.getWireMeterPrice().doubleValue());
					}
					else
					{
						spcstngm = spcstngmEjb.getSpcstngm(new Long(phaseDB), new Long(connectionTypeDb), tariffCategory, new Long(SystemValue.LOWER_MAX_LINE_LENGTH), costCenterNo);
						if (phase.trim().equals("SINGLE"))
						{
							if(serLen>52)
							{
								vc = (serLen-50)*spcstngm.getWireMeterPrice().doubleValue();
							}
							else
							{
								vc = 0;
							}
						}
						else
						{
							vc = serLen*spcstngm.getWireMeterPrice().doubleValue();
						}
						SpcndctmEjb spcndctmEjb = new SpcndctmEjb(getSessionKey("region"));
						Spcndctm spcndctm = null;
						SpcndctmPK spcndctmPK = new SpcndctmPK();
						spcndctmPK.setPhase(new Long(phaseDB));
					
						if(conductorType==null || conductorType.length()==0)
							conductorType = "FLY";
						double conLen = 0;
						if(conductorLength!=null && conductorLength.length()>0)
							conLen = Double.parseDouble(conductorLength);
						spcndctmPK.setConductorType(conductorType);
						spcndctm = spcndctmEjb.findById(spcndctmPK);
						vc = vc+ (conLen*spcndctm.getWireMeterPrice().doubleValue());
						
					}
				}
				
			}
			else
			{
				System.out.println("");
				if (isServiceConversion!=null && isServiceConversion.equals("3P-30A"))
				{
					spcstngm = spcstngmEjb.getSpcstngm(new Long(3), new Long(30), tariffCategory, new Long(lineLength), costCenterNo);
					vc = lineLen*spcstngm.getWireMeterPrice().doubleValue();
				}
				else if (isServiceConversion!=null && isServiceConversion.equals("3P-60A"))
				{
					spcstngm = spcstngmEjb.getSpcstngm(new Long(3), new Long(60), tariffCategory, new Long(lineLength), costCenterNo);
					vc = lineLen*spcstngm.getWireMeterPrice().doubleValue();
				}
				else
				{
					if(isStandardVC.equals("Y"))
					{
						spcstngm = spcstngmEjb.getSpcstngm(new Long(phaseDB), new Long(connectionTypeDb), tariffCategory, new Long(lineLength), costCenterNo);
						if (phase.trim().equals("SINGLE"))
						{
							if (lineLen>52)
							{
								vc = (lineLen-50)*spcstngm.getWireMeterPrice().doubleValue();
							}
							else
							{
								vc =0;
							}
						}
						else
						{
							vc = lineLen*spcstngm.getWireMeterPrice().doubleValue();
						}
					}
					else
					{
						spcstngm = spcstngmEjb.getSpcstngm(new Long(phaseDB), new Long(connectionTypeDb), tariffCategory, new Long(serviceLength), costCenterNo);
						if(serLen>52)
						{
							//spcstngm = spcstngmEjb.getSpcstngm(new Long(phaseDB), new Long(connectionTypeDb), tariffCategory, new Long(serviceLength), costCenterNo);
							vc = (serLen-50)*spcstngm.getWireMeterPrice().doubleValue();
						}
						else
						{
							vc = 0;
						}
						System.out.println("@@conductorLength "+conductorLength);
						if(conductorType==null || conductorType.length()==0)
							conductorType = "FLY";
						double conLen = 0;
						if(conductorLength!=null && conductorLength.length()>0)
							conLen = Double.parseDouble(conductorLength);
						SpcndctmEjb spcndctmEjb = new SpcndctmEjb(getSessionKey("region"));
						Spcndctm spcndctm = null;
						SpcndctmPK spcndctmPK = new SpcndctmPK();
						spcndctmPK.setPhase(new Long(phaseDB));
						spcndctmPK.setConductorType(conductorType);
						spcndctm = spcndctmEjb.findById(spcndctmPK);
						vc = vc+ (conLen*spcndctm.getWireMeterPrice().doubleValue());
					}
				}
			}
			if(spcstngm!=null)
			{
				fc = spcstngm.getFixedCost().doubleValue();
				sd = spcstngm.getSecurityDeposit().doubleValue();
			}
			costList.add(fc);
			costList.add(vc);
			costList.add(sd);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return costList;
	}
	
	public String calEstCostToLineLen()
	{
		if(isLineLenEntered!=null && isLineLenEntered.equals("true"))
		{
			displayCostComponents();
		}
		else
		{
			setEstimateCostData();
		}
		return SUCCESS;
	}
	
	/*public String calEstCostToLineLen()
	{
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		try
		{
			if(isLineLenEntered!=null && isLineLenEntered.equals("true"))
			{
				
				if(lineLength!=null && lineLength.trim().length()>0)
				{
					double lineLen = Double.parseDouble(lineLength);
					SpcstngmEjb spcstngmEjb = new SpcstngmEjb(getSessionKey("region"));
					Spcstngm spcstngm = null;
					double vc = 0;
					double fc = 0;
					double sd = 0;
					if(lineLen>Limit.LOWER_MAX_LINE_LENGTH){
						//spcstngm = spcstngmEjb.getSpcstngm(new Long(phaseDB), new Long(connectionTypeDb), tariffCategory, new Long(Limit.LOWER_MAX_LINE_LENGTH), costCenterNo);
						//vc = Limit.LOWER_MAX_LINE_LENGTH*spcstngm.getWireMeterPrice().doubleValue();
						spcstngm = spcstngmEjb.getSpcstngm(new Long(phaseDB), new Long(connectionTypeDb), tariffCategory, new Long(SystemValue.LOWER_MAX_LINE_LENGTH), costCenterNo);
						if (phase.trim().equals("SINGLE")){
							
							vc = (SystemValue.LOWER_MAX_LINE_LENGTH-50)*spcstngm.getWireMeterPrice().doubleValue();
						}else{
							
							vc = SystemValue.LOWER_MAX_LINE_LENGTH*spcstngm.getWireMeterPrice().doubleValue();
						}
						
						fc = spcstngm.getFixedCost().doubleValue();
						sd = spcstngm.getSecurityDeposit().doubleValue();
						
						//if(conductorType==null || conductorType.length()==0)
							conductorType = "FLY";
						
						SpcndctmEjb spcndctmEjb = new SpcndctmEjb(getSessionKey("region"));
						
						SpcndctmPK spcndctmPK = new SpcndctmPK();
						spcndctmPK.setConductorType(conductorType);
						spcndctmPK.setPhase(new Long(phaseDB));
						
						Spcndctm spcndctm = spcndctmEjb.findById(spcndctmPK);
						double diff = lineLen-Limit.LOWER_MAX_LINE_LENGTH;
						vc = vc+ (diff*spcndctm.getWireMeterPrice().doubleValue());
						
						
						
						
					}
					else
					{
						//spcstngm = spcstngmEjb.getSpcstngm(new Long(phaseDB), new Long(connectionTypeDb), tariffCategory, new Long(lineLength), costCenterNo);
						//vc = lineLen*spcstngm.getWireMeterPrice().doubleValue();
						//fc = spcstngm.getFixedCost().doubleValue();
						
						
						spcstngm = spcstngmEjb.getSpcstngm(new Long(phaseDB), new Long(connectionTypeDb), tariffCategory, new Long(lineLength), costCenterNo);
						if (phase.trim().equals("SINGLE")){
							
							if (lineLen>52){
								vc = (lineLen-50)*spcstngm.getWireMeterPrice().doubleValue();
							}else{
								vc =0;
							}
						}else{
							
							vc = lineLen*spcstngm.getWireMeterPrice().doubleValue();
						}
						sd = spcstngm.getSecurityDeposit().doubleValue();
						fc = spcstngm.getFixedCost().doubleValue();
						
					}
					
					if(appSubTypeDB!=null && appSubTypeDB.equals("C3"))
					{
						if(durationDB==1)
						{
							vc = vc*TemporaryConnValue.CON3_FIRST_YEAR;
						}
						else
						{
							vc = vc*(TemporaryConnValue.CON3_FIRST_YEAR+(TemporaryConnValue.CON3_NEXT_YEARS*(durationDB-1)));
							
						}
					}
					double oc = nf.parse(otherCost).doubleValue();
					double olc = nf.parse(otherLabourCost).doubleValue();
					double cc = nf.parse(convCost).doubleValue();
					double cac = nf.parse(capitalCost).doubleValue();
					double tax = nf.parse(taxAmount).doubleValue();
					double st = fc+vc;
					double tc = st+oc+olc+cc+tax+sd+cac;
			
					subTotal = nf.format(st);
				
					totalCost = nf.format(tc);
					variableCost = nf.format(vc);
					fixedCost = nf.format(fc);
					secDeposit = nf.format(sd);
				}
			}
			else
			{
				setEstimateCostData();
				
			}
			
		}
		catch(Exception e){
			
			e.printStackTrace();
		}
		return SUCCESS;
	}*/
	
	
	public void setLoggedData() 
	{
		log.info( getSession());
		setLoggedInUserId(getSessionKey("userName"));
		setCostCenterNo(getSessionKey("costCenterNo"));
		setCostCenterName(getSessionKey("costCenterName"));
		region = getSessionKey("region");
	}
	
	
	public String removeLabour()
	{
		try
		{
			setFormData();
			setPath(viewPath);
			double newTotLabCost = 0;
			LinkedHashMap selectLabCodeMap = (LinkedHashMap)sessionMap.get("SelectLabourList");
			if(selectLabCodeMap!=null && selectLabCodeMap.size()>0)
			{
				Iterator it = selectLabCodeMap.keySet().iterator();
				int i=0;
				double totalCost = 0;
				while(it.hasNext())
				{
					String matCode = (String)it.next();
					LabourGrid tmpLst = (LabourGrid)selectLabCodeMap.get(matCode);
					
					//String uom  = tmpLst.getUom();
					String up  = tmpLst.getUnitPrice().toString();
					String labName  = tmpLst.getDescription();
					String qty = "";
					if(tmpLst.getItemQty()!=null) 
						qty = tmpLst.getItemQty().toString();
					if(txtSelLabQty!=null && txtSelLabQty.length>0)
						qty = txtSelLabQty[i];
					if(qty!=null && qty.length()>0)
						tmpLst.setItemQty(new BigDecimal(qty));
					
					String cost = "";
					if(tmpLst.getLabourCost()!=null) 
						cost = tmpLst.getLabourCost().toString();
					if(txtSelLabCost!=null && txtSelLabCost.length>0)
						cost = txtSelLabCost[i];
					if(cost!=null && cost.length()>0)
					{
						tmpLst.setLabourCost(new BigDecimal(nf.parse(cost).doubleValue()));
						totalCost = totalCost+nf.parse(cost).doubleValue();
					}
					totalLabCost = nf.format(totalCost);
					
					selectLabCodeMap.put(matCode,tmpLst);
					
					i++;
				}
				//selectMatList = selMatTmp;
			}
			double delCost = 0;
			if(chkSelLabCode!=null)//adding materials from new list
			{
				ArrayList<SpestlabPK> removeList = null;
				String isNewEst = (String)sessionMap.get("IsNewEstimate");
				ArrayList savedMat = (ArrayList)sessionMap.get("SavedLabourList");
				for(int i=0;i<chkSelLabCode.length;i++)
				{
					StringTokenizer st = new StringTokenizer(chkSelLabCode[i],"###");
					
					String indexStr = st.nextToken();
					String matCode = st.nextToken();
					selectLabCodeMap.remove(matCode);
					
					String tmpCost = txtSelLabCost[Integer.parseInt(indexStr)];
					double delLabCost = 0;
					if(tmpCost!=null && tmpCost.trim().length()>0)
						delLabCost = nf.parse(tmpCost).doubleValue();
					double labCost = nf.parse(totalLabCost).doubleValue();
					newTotLabCost = labCost-delLabCost;
					totalLabCost = nf.format(newTotLabCost);
					if(isNewEst!=null && isNewEst.equals("N"))
					{
						if(savedMat.contains(matCode))
						{
							//deleting materials
							SpestlabPK spestlabPK = new SpestlabPK();
							spestlabPK.setDeptId(costCenterNo);
							spestlabPK.setEstimateNo(applicationNo);
							spestlabPK.setLabourCode(matCode);
							
							if(removeList==null)
								removeList = new ArrayList<SpestlabPK>();
							removeList.add(spestlabPK);
							delCost = delCost+delLabCost;
						}
					}
					
				}
				if(removeList!=null)
				{
					PcestdmtEjb pcestdmtEjb = new PcestdmtEjb(getSessionKey("region"));
					Pcestdmt pcestdmt = pcestdmtEjb.findBy3PK(applicationNo, costCenterNo, "LABOUR");
					pcestdmt.setEstimateCost(new BigDecimal(newTotLabCost));
					pcestdmt.setEstimateQty(new BigDecimal(newTotLabCost));
					
					PcesthmtEjb pcesthmtEjb = new PcesthmtEjb(getSessionKey("region"));
					Pcesthmt pcesthmt = null;
					try
					{
						//pcesthmt = pcesthmtEjb.findByEstimationNo(applicationNo,costCenterNo);
						if(costCenterNo.equals("547.00")||costCenterNo.equals("547.10")||costCenterNo.equals("547.20")|| costCenterNo.equals("542.00") || costCenterNo.equals("542.10")|| costCenterNo.equals("542.20") || costCenterNo.equals("541.00")|| costCenterNo.equals("541.10")|| costCenterNo.equals("541.20") || costCenterNo.equals("548.00")|| costCenterNo.equals("548.10")|| costCenterNo.equals("548.20") || costCenterNo.equals("541.40")){
							pcesthmt = pcesthmtEjb.findByJobNo(jobNo);
						}else{
							pcesthmt = pcesthmtEjb.findByEstimationNo(applicationNo,costCenterNo);
						}
						double preStdCost = pcesthmt.getStdCost().doubleValue();
						double newStdCost = preStdCost - delCost;
						pcesthmt.setStdCost(new BigDecimal(newStdCost));
						
					}
					catch(Exception e1){
						e1.printStackTrace();
					}
					JobEjb jobEjb = new JobEjb(getSessionKey("region"));
					
					//estimateEjb.removeLabour(removeList,pcesthtt,pcestdtt);
					jobEjb.removeLabour(removeList,pcesthmt,pcestdmt);
				}
			}
			LinkedHashMap selectMatCodeMap = (LinkedHashMap)sessionMap.get("SelectMaterialList");
			MaterialGrid tmpLst = (MaterialGrid)selectMatCodeMap.get("LABOUR");
			tmpLst.setEstimateQty(new BigDecimal(newTotLabCost));
			tmpLst.setEstimateCost(new BigDecimal(newTotLabCost));
			
			sessionMap.put("SelectLabourList",selectLabCodeMap);
			sessionMap.put("SelectMaterialList",selectMatCodeMap);
			selectLabList = selectLabCodeMap.values();
			isRemoveLabClicked = null; 
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
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
		if(isUndoReject!=null && isUndoReject.equals("true"))
			this.path = path+"Undo Rejected Jobs";
		else if(isApprove!=null && isApprove.equals("true"))
			this.path = path+"Approve Jobs";
		else
			this.path = path+"Revise Jobs";
				
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
	
	public String getConductorLength() {
		return conductorLength;
	}


	public void setConductorLength(String conductorLength) {
		this.conductorLength = conductorLength;
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

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo.trim();
	}


	public String getRevReason() {
		return revReason;
	}


	public void setRevReason(String revReason) {
		this.revReason = revReason;
	}


	public long getRevNo() {
		return revNo;
	}


	public void setRevNo(long revNo) {
		this.revNo = revNo;
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

	public String getWiringType() {
		return wiringType;
	}

	public void setWiringType(String wiringType) {
		this.wiringType = wiringType;
	}

	/*public String getRda() {
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
	public List<String> getJobNoList() {
		return jobNoList;
	}

	public void setJobNoList(List<String> jobNoList) {
		this.jobNoList = jobNoList;
	}

	public String getIsUndoReject() {
		return isUndoReject;
	}

	public void setIsUndoReject(String isUndoReject) {
		this.isUndoReject = isUndoReject;
	}


	public String getIsApprove() {
		return isApprove;
	}


	public void setIsApprove(String isApprove) {
		this.isApprove = isApprove;
	}


	public String getRejectReason() {
		return rejectReason;
	}


	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}


	public List<String> getCategoryCodeList() {
		return categoryCodeList;
	}


	public void setCategoryCodeList(List<String> categoryCodeList) {
		this.categoryCodeList = categoryCodeList;
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


	public String getPhaseDB() {
		return phaseDB;
	}


	public void setPhaseDB(String phaseDB) {
		this.phaseDB = phaseDB;
	}


	public String getConnectionTypeDb() {
		return connectionTypeDb;
	}


	public void setConnectionTypeDb(String connectionTypeDb) {
		this.connectionTypeDb = connectionTypeDb;
	}


	public String getAppSubType() {
		return appSubType;
	}


	public void setAppSubType(String appSubType) {
		this.appSubType = appSubType;
	}


	public String getAppSubTypeDB() {
		return appSubTypeDB;
	}


	public void setAppSubTypeDB(String appSubTypeDB) {
		this.appSubTypeDB = appSubTypeDB;
	}


	public String getDuration() {
		return duration;
	}


	public void setDuration(String duration) {
		this.duration = duration;
	}


	public double getDurationDB() {
		return durationDB;
	}


	public void setDurationDB(double durationDB) {
		this.durationDB = durationDB;
	}


	public Collection getSelectLabList() {
		return selectLabList;
	}


	public void setSelectLabList(Collection selectLabList) {
		this.selectLabList = selectLabList;
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


	public String getServiceLength() {
		return serviceLength;
	}


	public void setServiceLength(String serviceLength) {
		this.serviceLength = serviceLength;
	}


	public String getOtherLabourCost() {
		return otherLabourCost;
	}


	public void setOtherLabourCost(String otherLabourCost) {
		this.otherLabourCost = otherLabourCost;
	}


	public String getSuccessMessage() {
		return successMessage;
	}


	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}


	public String getInfoMessage() {
		return infoMessage;
	}


	public void setInfoMessage(String infoMessage) {
		this.infoMessage = infoMessage;
	}


	public String getCapitalCost() {
		return capitalCost;
	}


	public void setCapitalCost(String capitalCost) {
		this.capitalCost = capitalCost;
	}


	public String getConversionLength() {
		return conversionLength;
	}


	public void setConversionLength(String conversionLength) {
		this.conversionLength = conversionLength;
	}

	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
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

	public String getJobType() {
		return jobType;
	}


	public void setJobType(String jobType) {
		this.jobType = jobType;
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


	public String[] getTxtComQty() {
		return txtComQty;
	}


	public void setTxtComQty(String[] txtCusQty) {
		this.txtComQty = txtCusQty;
	}


	public String getAddlDeposit() {
		return addlDeposit;
	}


	public void setAddlDeposit(String addlDeposit) {
		this.addlDeposit = addlDeposit;
	}


	public String getCableType() {
		return cableType;
	}


	public void setCableType(String cableType) {
		this.cableType = cableType;
	}


	public String getIsStandardVC() {
		return isStandardVC;
	}


	public void setIsStandardVC(String isStandardVC) {
		this.isStandardVC = isStandardVC;
	}


	public String getConversionLength2P() {
		return conversionLength2P;
	}


	public void setConversionLength2P(String conversionLength2P) {
		this.conversionLength2P = conversionLength2P;
	}


	public String getCircuitLength() {
		return circuitLength;
	}


	public void setCircuitLength(String circuitLength) {
		this.circuitLength = circuitLength;
	}


	public String getAreaCostCenter() {
		return areaCostCenter;
	}


	public void setAreaCostCenter(String areaCostCenter) {
		this.areaCostCenter = areaCostCenter;
	}


	public String getTotalDetailCost() {
		return totalDetailCost;
	}


	public void setTotalDetailCost(String totalDetailCost) {
		this.totalDetailCost = totalDetailCost;
	}


	public void setJvNo(String jvNo) {
		this.jvNo = jvNo;
	}


	public String getJvNo() {
		return jvNo;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public int getCount() {
		return count;
	}

	public void setJvDate(Date jv_date) {
		this.jvDate = jv_date;
	}


	public Date getJvDate() {
		return jvDate;
	}

	

	

}//End of class
