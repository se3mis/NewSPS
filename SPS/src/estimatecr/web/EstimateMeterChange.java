package estimatecr.web;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import util.common.AppStatus;
import util.common.ApplicationPrefixType;
import util.common.ApplicationSubType;
import util.common.ApplicationType;
import util.common.EstimateStatus;
import util.common.Format;
import util.common.PivPrefixType;
import util.common.SystemValue;
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
import estimate.service.SpcstngmEjb;
import estimate.model.Approval;
import estimate.model.FundSource;
import estimate.model.LabourGrid;
import estimate.model.Spcndctm;
import estimate.model.SpcndctmPK;
import estimate.model.SpcstngmPK;
import estimate.model.Spcstngm;
import estimate.model.Spestlab;
import estimate.model.SpestlabPK;
import estimate.model.Speststd;
import estimate.model.SpeststdPK;
import estimate.model.Spratesm;
import estimate.model.SpratesmPK;
import estimate.model.Spserest;
import estimate.model.SpserestPK;
import estimate.model.Spsetpol;
import estimate.model.Spsetstu;
import estimate.model.Spsetsty;
import estimate.model.Spsetwir;
import estimate.service.SpeststdEjb;
import estimate.model.Pcestdtt;
import estimate.model.PcestdttPK;
import estimate.service.PcestdttEjb;
import estimate.service.EstimateEjb;
import estimate.service.PcjbtypmEjb;
import estimate.service.SpcndctmEjb;
import estimate.service.SpestedyEjb;
import estimate.service.SpestlabEjb;
import estimate.service.SpserestEjb;
import estimate.service.SpsetpolEjb;
import estimate.service.SpsetstuEjb;
import estimate.service.SpsetstyEjb;
import estimate.service.SpsetwirEjb;
import estimate.model.MaterialGrid;

import com.opensymphony.xwork2.ActionSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

@SuppressWarnings("serial")
public class EstimateMeterChange extends ActionSupport implements SessionAware, ParameterAware,ServletResponseAware,ServletRequestAware{
	private static final Log log = LogFactory.getLog(EstimateMeterChange.class);
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
	private String estimateStatus;
	private String estimateStatusDesc;
	private String city;
	private String suburb;
	private String streetAddress;
	private String postalCode;
	private String estimatedDate;
	private String noOfMeterDmg;
		
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
	private String rejectReason;
	private String wiringType;
	private List<FundSource> fundSourceList;
	private String fundSourceID;
	private String isCEBEmp;
	private List<String> categoryCodeList;
		
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
	//private String wireMeterPrice;
	private String isViewOnly;
	private String isModify;
	private String isApprove;
	private String isUndoReject;
	private String isPrint;
	private String estimateExist = null;
	private String firstYear = Double.toString(TemporaryConnValue.CON3_FIRST_YEAR);
	private String nextYear = Double.toString(TemporaryConnValue.CON3_NEXT_YEARS);
	private String isLineLenEntered;
	private String isRemoveLabClicked;
	private String region ;
	
	/****************************/
	// Local variables
	private int connectionTypeInt;
	private int phaseInt;
	private String tariffCategoryCode;
	
		
	//Material List variables
	private List selectMatList;
	private Collection selectLabList;
	private String[] chkMatCode;
	private String[] chkSelMatCode;
	private String[] txtSelQty;
	private String[] txtSelMatCost;
	//private String[] txtCusQty;
	private String totalDetailCost;
	private String[] txtSelLabHrs;
	private String totalLabHrs;
	private String[] txtSelLabQty;
	private String[] txtSelLabCost;
	private String totalLabCost;
	private String[] chkSelLabCode;
	
	//other Fields
	private String path;
	private String state;
	
	private String labourRate;
	private String transportRate;
	private String overheadRate;
	
	public String execute()
	{
		setErrorMessage(null);
		setSuccessMessage(null);
		setInfoMessage(null);
		setLoggedData();
		setState("newEstimate");
		
		setPath(viewPath);
		if(applicationNo!=null && applicationNo.length()>0)
		{
			if(isRemoveLabClicked!=null && isRemoveLabClicked.equals("true"))
			{
				removeLabour();
				
			}
			else
			{
				clearSessionVariables();
				clearFields();
					
			}
			fillEstimateForm();	
			
		}
		else
		{
			clearSessionVariables();
			clearFields();
			this.isViewOnly = null;
			this.isModify = null;
			this.isApprove = null;
			
		}
		
		//Danusha
		HttpServletRequest request = ServletActionContext.getRequest();
		String estimateNo=request.getParameter("estNo");
		String costCen=request.getParameter("costCen");
		if(estimateNo!=null && estimateNo.length()>0 && costCen!=null && costCen.length()>0){
			setApplicationNo(estimateNo);
			setCostCenterNo(costCen);
			setIsUndoReject("true");
			fillEstimateForm();
			
		}//Danusha
		setFormData();
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
			fillEstimateForm();
			
		}//Dhanusha
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
		setPath(viewPath);
		this.isViewOnly = null;
		this.isModify = null;
		this.isUndoReject = null;
		this.isApprove = "true";
		this.isPrint = null;
		setFormData();
		setInfoFromMainMenu();
		return SUCCESS;
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
		setPath(viewPath);
		setFormData();
		Calendar cal = Calendar.getInstance();
		int curYear = cal.get(Calendar.YEAR);
		applicationNo = costCenterNo+"/"+PivPrefixType.EST_CR_JOBS+"/"+curYear+"/";
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
		setPath(viewPath);
		setInfoFromMainMenu();
		setFormData();
		return SUCCESS;
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
		this.isPrint = "true";
		setPath(viewPath);
		Calendar cal = Calendar.getInstance();
		int curYear = cal.get(Calendar.YEAR);
		applicationNo = costCenterNo+"/"+PivPrefixType.EST_CR_JOBS+"/"+curYear+"/";
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
			approval.setFromStatus(new BigDecimal(EstimateStatus.NEW).toString());
			approval.setToStatus(new BigDecimal(EstimateStatus.EST_APPROVAL_ES).toString());
			approval.setStandardCost(new BigDecimal(Double.toString(nf.parse(totalCost).doubleValue())));
			approval.setDetailedCost(new BigDecimal(Double.toString(nf.parse(totalDetailCost).doubleValue())));
			
			//pcesthttEjb.changeStatusPcesthtt(applicationNo, costCenterNo, new BigDecimal(EstimateStatus.EST_APPROVAL_ES));
			
			Pcesthtt pcesthtt = pcesthttEjb.findByEstimationNo(applicationNo, costCenterNo);
			pcesthtt.setStatus(new Long(EstimateStatus.EST_APPROVAL_ES));
			pcesthttEjb.validateEstimate(pcesthtt, approval);
			clearFields();
			setSuccessMessage("Estimate sent for approval successfully!");
		}
		catch(Exception e){}
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
		String msg = pcesthttEjb.approveEstimate(applicationNo, costCenterNo, getSession().get("userRole").toString(),getSession().get("userName").toString(), null, null, null);
		clearFields();
		setFormData();
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
			//pcesthttEjb.updatePcesthtt(pcesthtt);
		}
		catch(Exception e){}
		clearFields();
		setFormData();
		
		setSuccessMessage("Estimate rejected successfully.");
		return SUCCESS;
	}
	
	public String copyEstimateDetails() throws ParseException
	{
		setErrorMessage(null);
		setSuccessMessage(null);
		setInfoMessage(null);
		setLoggedData();
		setFormData();
		setPath(viewPath);
		PcesthttEjb pcesthttEjb = new PcesthttEjb(getSessionKey("region"));
		Pcesthtt pcesthtt = pcesthttEjb.findByEstimationNo(copyEstimateNo,costCenterNo);
		if(pcesthtt==null)
		{
			setInfoMessage("Estimate does not exist.");
			return ERROR;
		}
		else
		{
			
			//ApplicationEjb applicationEJB = new ApplicationEjb(getSessionKey("region"));
			//Application application = applicationEJB.findByApplicationNo(copyEstimateNo);
			//setApplicantData(application);
			//setWiringData(application);
			setEstimateData(pcesthtt,copyEstimateNo);
			
			return SUCCESS;
		}
		
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
			PcesthttEjb pcesthttEjb = new PcesthttEjb(getSessionKey("region"));
			Pcesthtt pcesthtt = null;
			try
			{
				pcesthtt = pcesthttEjb.findByEstimationNo(applicationNo,costCenterNo);
			}
			catch(Exception e1){}
			//new estimate
			if(pcesthtt==null)
			{
				insert();
				setSuccessMessage("Estimate saved successfully!");
				estimateExist = "true";
				isModify = "true";
				sessionMap.put("IsNewEstimate", "N");
			}
			//modify estimate
			else
			{
				
				if(isUndoReject!=null && isUndoReject.equals("true"))
				{
					pcesthtt.setStatus(new Long(EstimateStatus.MODIFIED));
					pcesthttEjb.updatePcesthtt(pcesthtt);
					clearFields();
					setSuccessMessage("Rejected estimate undone successfully!");
					this.applicationNo = "";
				}
				else
				{
					Long estStatus = pcesthtt.getStatus();
					if(estStatus.toString().equals(EstimateStatus.MODIFIED))
					{
						modify(pcesthtt);
						setSuccessMessage("Estimate modified successfully!");
					}
					else
					{
						setErrorMessage("Sorry! You cannot modify this estimate.");
					}
					ApplicationEjb applicationEJB = new ApplicationEjb(getSessionKey("region"));
					Application application = applicationEJB.findByApplicationNo(applicationNo);
					
					setApplicantData(application);
					setWiringData(application);
				}
				
			}
			isLineLenEntered = null;
			
		}
		catch(Exception e)
		{
			setErrorMessage("Unexpected Error! Please retry.");
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	
	public String viewApplicantDetails()
	{
		setLoggedData();
		setPath(viewPath);
		clearFields();
		setFormData();
		return fillEstimateForm();
		
	}
	
	
	//action invoke when Find button is clicked
	public String fillEstimateForm()
	{
		System.out.println("fillEstimateForm");
		try
		{
			//setLoggedData();
			//setPath(viewPath);
			//clearFields();
			//setFormData();		
			
			//viewing estimate
			if(isViewOnly!=null && isViewOnly.equals("true") || (isPrint!=null && isPrint.equals("true")))
			{
			
				PcesthttEjb pcesthttEjb = new PcesthttEjb(getSessionKey("region"));
				Pcesthtt pcesthtt = pcesthttEjb.findByEstimationNo(applicationNo,costCenterNo);
				if(pcesthtt==null)
				{
					setInfoMessage("Estimate does not exist.");
					return ERROR;
				}
				else
				{
					
					ApplicationEjb applicationEJB = new ApplicationEjb(getSessionKey("region"));
					Application application = applicationEJB.findByApplicationNo(applicationNo);
					setApplicantData(application);
					setWiringData(application);
					setEstimateData(pcesthtt,applicationNo);
					this.isViewApplicant = "true";	
					return SUCCESS;
				}
				
			}//end viewing estimate
			//modify estimate
			else if(isModify!=null && isModify.equals("true"))
			{
				PcesthttEjb pcesthttEjb = new PcesthttEjb(getSessionKey("region"));
				Pcesthtt pcesthtt = pcesthttEjb.findByEstimationNo(applicationNo,costCenterNo);
				if(pcesthtt==null)
				{
					setInfoMessage("Estimate does not exist.");
					return ERROR;
				}
				else
				{
					Long estStatus = pcesthtt.getStatus();
					if(!estStatus.toString().equals(EstimateStatus.MODIFIED))
					{
						setErrorMessage("Sorry! You cannot modify this estimate.");
						this.isViewApplicant = null;		
						return ERROR;
					}
					else
					{
						estimateExist = "true";
						ApplicationEjb applicationEJB = new ApplicationEjb(getSessionKey("region"));
						Application application = applicationEJB.findByApplicationNo(applicationNo);
						setApplicantData(application);
						setWiringData(application);
						setEstimateData(pcesthtt,applicationNo);
						this.isViewApplicant = "true";	
						return SUCCESS;
					}
				}
			}//end modify estimate
			//undo rejected estimate
			else if(isUndoReject!=null && isUndoReject.equals("true"))
			{
				PcesthttEjb pcesthttEjb = new PcesthttEjb(getSessionKey("region"));
				Pcesthtt pcesthtt = pcesthttEjb.findByEstimationNo(applicationNo,costCenterNo);
				if(pcesthtt==null)
				{
					setInfoMessage("Estimate does not exist.");
					return ERROR;
				}
				else
				{
					Long estStatus = pcesthtt.getStatus();
					if(!estStatus.toString().equals(EstimateStatus.EST_REJECTED))
					{
						setErrorMessage("Sorry! This is not an rejected estimate.");
						this.isViewApplicant = null;		
						return ERROR;
					}
					else
					{
						ApplicationEjb applicationEJB = new ApplicationEjb(getSessionKey("region"));
						Application application = applicationEJB.findByApplicationNo(applicationNo);
						setApplicantData(application);
						setWiringData(application);
						setEstimateData(pcesthtt,applicationNo);
						this.isViewApplicant = "true";	
						return SUCCESS;
					}
				}
			}//end undo rejected estimate
			//approve estimate
			else if(isApprove!=null && isApprove.equals("true"))
			{
				PcesthttEjb pcesthttEjb = new PcesthttEjb(getSessionKey("region"));
				Pcesthtt pcesthtt = pcesthttEjb.findByEstimationNo(applicationNo,costCenterNo);
				if(pcesthtt==null)
				{
					setInfoMessage("Estimate does not exist.");
					return ERROR;
				}
				else
				{
					Long estStatus = pcesthtt.getStatus();
					if( !estStatus.toString().equals(EstimateStatus.EST_APPROVAL_ES) &&
						//!estStatus.toString().equals(EstimateStatus.EST_APPROVAL_EE) &&
						//!estStatus.toString().equals(EstimateStatus.EST_APPROVAL_CE) &&
						!estStatus.toString().equals(EstimateStatus.EST_APPROVAL_AGM) &&
						//!estStatus.toString().equals(EstimateStatus.EST_APPROVAL_DGM) &&
						!estStatus.toString().equals(EstimateStatus.EST_APPROVAL_EA) 
					  )
					{
						setErrorMessage("Sorry! You cannot approve this estimate.");
						this.isViewApplicant = null;		
						return ERROR;
					}
					else
					{
						estimateExist = "true";
						ApplicationEjb applicationEJB = new ApplicationEjb(getSessionKey("region"));
						Application application = applicationEJB.findByApplicationNo(applicationNo);
						setApplicantData(application);
						setWiringData(application);
						setEstimateData(pcesthtt,applicationNo);
						this.isViewApplicant = "true";	
						return SUCCESS;
					}
				}
			}//end approve estimate
			//new estimate 
			else
			{
				ApplicationEjb applicationEJB = new ApplicationEjb(getSessionKey("region"));
				Application application = applicationEJB.findByApplicationNo(applicationNo);
				if(application!=null)
				{
					String status = application.getStatus();
					if(status.equals(AppStatus.ALLOCATED))
					{
						PcesthttEjb pcesthttEjb = new PcesthttEjb(getSessionKey("region"));
						Pcesthtt pcesthtt = null;
						try
						{
							pcesthtt = pcesthttEjb.findByEstimationNo(applicationNo,costCenterNo);
							Long estStatus = pcesthtt.getStatus();
							estimateExist = "true";
							if(!estStatus.toString().equals(EstimateStatus.MODIFIED))
							{
								setErrorMessage("Sorry! You cannot modify this estimate.");
								this.isViewApplicant = null;		
								return ERROR;
							}
						}
						catch(Exception e1){}
						setApplicantData(application);
						setWiringData(application);
						setEstimateData(pcesthtt,applicationNo);
						this.isViewApplicant = "true";					
						return SUCCESS;
					}
					else
					{
						if(status.equals(AppStatus.ESTIMATED))
						{
							setInfoMessage("Estimation has already been done for this application.");
							this.isViewApplicant = null;		
							return ERROR;
						}
						else
						{
							setInfoMessage("Please allocate the application first.");
							this.isViewApplicant = null;
							return ERROR;
						}
					}
				}
				else
				{
					setInfoMessage("Application No not exists");
					this.isViewApplicant = null;
					return ERROR;
				}
			}//end new estimate
			 
		}
		catch(Exception e)
		{
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
		clearSessionVariables();
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
		try
		{
			if(isModify!=null && isModify.equals("true"))
			{
				PcesthttEjb pcesthttEjb = new PcesthttEjb(getSessionKey("region"));
				estNoList = pcesthttEjb.getEstimateNoListST(costCenterNo, new Long(EstimateStatus.NEW), ApplicationType.COST_RECOVERY, ApplicationSubType.METER_CHANGE);
			}
			else if(isUndoReject!=null && isUndoReject.equals("true"))
			{
				PcesthttEjb pcesthttEjb = new PcesthttEjb(getSessionKey("region"));
				estNoList = pcesthttEjb.getEstimateNoListST(costCenterNo, new Long(EstimateStatus.EST_REJECTED), ApplicationType.COST_RECOVERY, ApplicationSubType.METER_CHANGE);
			}
			else if(isApprove!=null && isApprove.equals("true"))
			{
				PcesthttEjb pcesthttEjb = new PcesthttEjb(getSessionKey("region"));
				estNoList = pcesthttEjb.getEstNoApprovalListNewST(costCenterNo, getSession().get("userRole").toString(), ApplicationType.COST_RECOVERY, ApplicationSubType.METER_CHANGE);
			}
			
			else//new
			{
				SpestedyEjb  spestedyEjb = new SpestedyEjb(getSessionKey("region"));
				estNoList = spestedyEjb.getVisitedAppoinments(costCenterNo, getLoggedInUserId(), ApplicationType.COST_RECOVERY, ApplicationSubType.METER_CHANGE);
			}
			if(estNoList==null)
				estNoList = new ArrayList();
			EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
	    	fundSourceList = estimateEjb.getFundSource(costCenterNo);
	    	fundSourceID = "CP###CP";
	    	
	    	PcjbtypmEjb pcjbtypmEjb = new PcjbtypmEjb(getSessionKey("region"));
	    	categoryCodeList = pcjbtypmEjb.getCatCode(costCenterNo);
	    	categoryCode = "CRJ";
	    	
	    	SpratesmPK spratesmpK = new SpratesmPK();
			spratesmpK.setApplicationType("CR");
			spratesmpK.setDeptId(costCenterNo);
			Spratesm spratesm = estimateEjb.getSpratesm(spratesmpK);
			if(spratesm!=null)
			{
				labourRate = spratesm.getLabourRate().toString();
				transportRate = spratesm.getTransportRate().toString();
				overheadRate = spratesm.getOverheadRate().toString();
			}
		}
		catch(Exception e)
		{
			fundSourceList = new ArrayList();
			e.printStackTrace();
		}
	}
	
	
	private void clearFields()
	{
		this.isViewApplicant = null;
		this.errorMessage = "";
		this.successMessage = "";
		this.infoMessage = "";
		
		this.applicantName = "";
		this.companyName = "";
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
		this.estimateExist = null;
		this.fundSourceID = null;
		this.rejectReason = "";
		
		
	}
	
	private void clearSessionVariables()
	{
		sessionMap.remove("IsNewEstimate");
		sessionMap.remove("SelectMaterialList");
		sessionMap.remove("SavedMaterialList");
		sessionMap.remove("IsMaterialsAdd");
		sessionMap.remove("SelectLabourList");
		sessionMap.remove("SavedLabourList");
		sessionMap.remove("IsDefMatLoad");
		sessionMap.remove("IsDefLabLoad");
	}

	//add new estimate
	private void insert()throws Exception
	{
		Format format=new Format();
		PcesthttPK pcesthttPK = new PcesthttPK();
		pcesthttPK.setEstimateNo(applicationNo);
		pcesthttPK.setDeptId(costCenterNo);
		pcesthttPK.setRevNo(1);
		
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
		pcesthtt.setStdCost(new BigDecimal(Double.toString(nf.parse(totalDetailCost).doubleValue())));
		
		if(fundSourceID!=null && fundSourceID.length()>0)
		{
			StringTokenizer st = new StringTokenizer(fundSourceID,"###");
			pcesthtt.setFundSource(st.nextToken());
			pcesthtt.setFundId(st.nextToken());
		}
		Speststd speststd = new Speststd();
		SpeststdPK speststdPK = new SpeststdPK();
		speststdPK.setDeptId(costCenterNo);
		speststdPK.setEstimateNo(applicationNo);
		speststd.setId(speststdPK);
		speststd.setConductorType(conductorType);
		//speststd.setConnectorType(connectorType);
		speststd.setConversionCost(new BigDecimal(0));
		speststd.setFixedCost(new BigDecimal(Double.toString(nf.parse(fixedCost).doubleValue())));
		if(lb!=null && lb.trim().length()>0)
			speststd.setLb(new BigDecimal(lb));
		if(lineLength!=null && lineLength.trim().length()>0)
			speststd.setLineLength(new BigDecimal(lineLength));
		if(loopLength==null || loopLength.length()==0)
			speststd.setLoopLength(new BigDecimal(0));
		else
			speststd.setLoopLength(new BigDecimal(loopLength));
		if(poleNo==null || poleNo.length()==0)
			speststd.setPoleNo(new BigDecimal(0));
		else
			speststd.setPoleNo(new BigDecimal(poleNo));
		//speststd.setPoleSize(poleSize);
		//speststd.setPoleType(poleType);
		if(secDeposit==null || secDeposit.length()==0)
			speststd.setSecurityDeposit(new BigDecimal(0));
		else
			speststd.setSecurityDeposit(new BigDecimal(Double.toString(nf.parse(secDeposit).doubleValue())));
		if(distanceToServicePlace==null || distanceToServicePlace.length()==0)
			speststd.setServiceDistance(new BigDecimal(0));
		else
			speststd.setServiceDistance(new BigDecimal(distanceToServicePlace));
		speststd.setSinNo(sinNo);
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
		speststd.setVariableCost(new BigDecimal(Double.toString(nf.parse(variableCost).doubleValue())));
		if(otherCost==null || otherCost.length()==0)
			speststd.setOtherCost(new BigDecimal(0));
		else
			speststd.setOtherCost(new BigDecimal(Double.toString(nf.parse(otherCost).doubleValue())));
		speststd.setCapitalCost(new BigDecimal(0));
		Date curDate = new Date();
		speststd.setUpdDate(curDate);
		speststd.setUpdTime(format.FormatTime());
		speststd.setUpdUser(loggedUser);
		speststd.setTemporaryDeposit(new BigDecimal(0));
		speststd.setTransformColor(transColor);
		speststd.setIsLoopService(isLoopService);
		/*
		speststd.setMc(mc);
		speststd.setRda(rda);
		speststd.setPolice(police);
		speststd.setPsd(psd);
		*/
		speststd.setWiringType(wiringType);
		speststd.setTotalCost(new BigDecimal(Double.toString(nf.parse(totalCost).doubleValue())));
		//pcesthttEJB.createAPcesthtt(pcesthtt);
		//SpeststdEjb speststdEJB = new SpeststdEjb(getSessionKey("region"));
		//speststdEJB.createSpeststd(speststd);
		
		//inserting material costs
		LinkedHashMap selectMatCodeMap = (LinkedHashMap)sessionMap.get("SelectMaterialList");
		ArrayList pcestdttList = null;
		double totDetailCost = 0;
		if(selectMatCodeMap!=null && selectMatCodeMap.size()>0)
		{
			pcestdttList = new ArrayList();
			Iterator it = selectMatCodeMap.keySet().iterator();
			int i=0;
			ArrayList savedMat = new ArrayList();
		
			//PcestdttEjb pcestdttEjb = new PcestdttEjb(getSessionKey("region"));
			
			while(it.hasNext())
			{
				String matCode = (String)it.next();
				savedMat.add(matCode);
				MaterialGrid tmpLst = (MaterialGrid)selectMatCodeMap.get(matCode);
				
				String uom  = tmpLst.getUom();
				String up  = tmpLst.getUnitPrice().toString();
				String matName  = tmpLst.getMatNm();
				String qty = txtSelQty[i];
				String cost = txtSelMatCost[i];
				String resType = tmpLst.getResType();
				BigDecimal resCat = tmpLst.getResCat();
				//String cusQty = txtCusQty[i];
				
				PcestdttPK pcestdttPK = new PcestdttPK();
				pcestdttPK.setDeptId(costCenterNo);
				pcestdttPK.setEstimateNo(applicationNo);
				pcestdttPK.setResCd(matCode);
				pcestdttPK.setRevNo(1);
				
				Pcestdtt pcestdtt = new Pcestdtt();
				pcestdtt.setId(pcestdttPK);
				pcestdtt.setEstimateCost(new BigDecimal(Double.toString(nf.parse(cost).doubleValue())));
				pcestdtt.setEstimateQty(new BigDecimal(qty));
				pcestdtt.setResType(resType);
				pcestdtt.setResCat(resCat);
				pcestdtt.setUnitPrice(new BigDecimal(up));
				pcestdtt.setUom(uom);
				/*if(cusQty!=null && cusQty.length()>0)
				{
					pcestdtt.setCustomerQty(new BigDecimal(cusQty));
					tmpLst.setCustomerQty(new BigDecimal(cusQty));
				}*/
				
				//pcestdttEjb.createPcestdtt(pcestdtt);
				totDetailCost = totDetailCost+nf.parse(cost).doubleValue();
				pcestdttList.add(pcestdtt);
				
				tmpLst.setEstimateQty(new BigDecimal(qty));
				tmpLst.setEstimateCost(new BigDecimal(Double.toString(nf.parse(cost).doubleValue())));
				
				i++;
			}
			sessionMap.put("SavedMaterialList",savedMat);
		}
		
		//inserting labour costs
		LinkedHashMap selectLabCodeMap = (LinkedHashMap)sessionMap.get("SelectLabourList");
		ArrayList spestlabList = null;
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
				String cost = txtSelLabCost[i];
				String hrs = txtSelLabHrs[i];
				
				Spestlab spestlab= new Spestlab();
				
				SpestlabPK spestlabPK = new SpestlabPK();
				spestlabPK.setDeptId(costCenterNo);
				spestlabPK.setEstimateNo(applicationNo);
				spestlabPK.setLabourCode(labCode);
				spestlab.setId(spestlabPK);
				
				spestlab.setItemQty(new BigDecimal(qty));
				spestlab.setLabourCost(new BigDecimal(Double.toString(lg.getUnitPrice().doubleValue()*Double.parseDouble(qty))));
				spestlab.setLabourHours(new BigDecimal(Double.toString(nf.parse(hrs).doubleValue())));
				spestlab.setUnitPrice(lg.getUnitPrice());
				spestlab.setCebUnitPrice(new BigDecimal(labourRate));
				spestlab.setCebLabourCost(new BigDecimal(Double.toString(nf.parse(cost).doubleValue())));
				spestlab.setActivityDescription(lg.getDescription());
				spestlab.setUnitLabourHrs(lg.getUnitLabourHrs());
				spestlabList.add(spestlab);
				
				lg.setItemQty(new BigDecimal(qty));
				lg.setCebLabourCost(new BigDecimal(Double.toString(nf.parse(cost).doubleValue())));
				lg.setLabourHours(new BigDecimal(Double.toString(nf.parse(hrs).doubleValue())));
				lg.setLabourCost(new BigDecimal(Double.toString(lg.getUnitPrice().doubleValue()*Double.parseDouble(qty))));
				
				i++;
			}
			sessionMap.put("SavedLabourList",savedLab);
			sessionMap.put("SelectLabourList",selectLabCodeMap);
		}
		
		pcesthtt.setStdCost(new BigDecimal(totDetailCost));
		
		EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
		//estimateEjb.insert(pcesthtt, speststd, pcestdttList);
		estimateEjb.insert(pcesthtt, speststd, pcestdttList,spestlabList);
	}
	
	//modify estimate
	private void modify(Pcesthtt pcesthtt)throws Exception
	{
		Format format=new Format();
		
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		
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
		pcesthtt.setStdCost(new BigDecimal(Double.toString(nf.parse(totalDetailCost).doubleValue())));
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
		
		//speststd.setConnectorType(connectorType);
		speststd.setConversionCost(new BigDecimal(0));
		speststd.setFixedCost(new BigDecimal(Double.toString(nf.parse(fixedCost).doubleValue())));
		if(lb!=null && lb.trim().length()>0)
			speststd.setLb(new BigDecimal(lb));
		if(lineLength!=null && lineLength.trim().length()>0)
			speststd.setLineLength(new BigDecimal(lineLength));
		if(loopLength!=null && loopLength.trim().length()>0)
			speststd.setLoopLength(new BigDecimal(loopLength));
		if(poleNo!=null && poleNo.trim().length()>0)
			speststd.setPoleNo(new BigDecimal(poleNo));
		//speststd.setPoleSize(poleSize);
		//speststd.setPoleType(poleType);
		speststd.setSecurityDeposit(new BigDecimal(Double.toString(nf.parse(secDeposit).doubleValue())));
		speststd.setServiceDistance(new BigDecimal(distanceToServicePlace));
		speststd.setSinNo(sinNo);
		if(spans!=null && spans.trim().length()>0)
			speststd.setSpan(new BigDecimal(spans));
		if(stayNo!=null && stayNo.trim().length()>0)
			speststd.setStayNo(new BigDecimal(stayNo));
		if(strutsNo!=null && strutsNo.trim().length()>0)
			speststd.setStrutNo(new BigDecimal(strutsNo));
		speststd.setVariableCost(new BigDecimal(Double.toString(nf.parse(variableCost).doubleValue())));
		speststd.setOtherCost(new BigDecimal(Double.toString(nf.parse(otherCost).doubleValue())));
		Date curDate = new Date();
		speststd.setUpdDate(curDate);
		speststd.setUpdTime(format.FormatTime());
		speststd.setUpdUser(loggedUser);
		speststd.setTemporaryDeposit(new BigDecimal(0));
		speststd.setTransformColor(transColor);
		speststd.setConductorType(conductorType);
		speststd.setIsLoopService(isLoopService);
		/*
		speststd.setMc(mc);
		speststd.setRda(rda);
		speststd.setPolice(police);
		speststd.setPsd(psd);
		*/
		speststd.setWiringType(wiringType);
		speststd.setTotalCost(new BigDecimal(Double.toString(nf.parse(totalCost).doubleValue())));
		//pcesthttEJB.updatePcesthtt(pcesthtt);
		
		//speststdEJB.updateSpeststd(speststd);
		
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
				String cost = txtSelMatCost[i];
				String resType = tmpLst.getResType();
				BigDecimal resCat = tmpLst.getResCat();
				//String cusQty = txtCusQty[i];
				/**/
				totDetailCost = totDetailCost+nf.parse(cost).doubleValue();
				if(savedMat.contains(matCode))
				{
					Pcestdtt pcestdtt = pcestdttEjb.findBy3PK(applicationNo, costCenterNo, matCode);
					pcestdtt.setEstimateCost(new BigDecimal(Double.toString(nf.parse(cost).doubleValue())));
					pcestdtt.setEstimateQty(new BigDecimal(qty));
					/*if(cusQty!=null && cusQty.length()>0)
					{
						pcestdtt.setCustomerQty(new BigDecimal(cusQty));
						tmpLst.setCustomerQty(new BigDecimal(cusQty));
					}*/
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
					pcestdtt.setEstimateCost(new BigDecimal(Double.toString(nf.parse(cost).doubleValue())));
					pcestdtt.setEstimateQty(new BigDecimal(qty));
					pcestdtt.setResType(resType);
					pcestdtt.setResCat(resCat);
					pcestdtt.setUnitPrice(new BigDecimal(up));
					pcestdtt.setUom(uom);
					pcestdtt.getId().setRevNo(1);
					/*if(cusQty!=null && cusQty.trim().length()>0)
					{
						pcestdtt.setCustomerQty(new BigDecimal(cusQty));
						tmpLst.setCustomerQty(new BigDecimal(cusQty));
					}*/
					if(insertList==null)
						insertList = new ArrayList<Pcestdtt>();
					insertList.add(pcestdtt);
					savedMat.add(matCode);
				}
				sessionMap.put("SavedMaterialList",savedMat);
				tmpLst.setEstimateQty(new BigDecimal(qty));
				tmpLst.setEstimateCost(new BigDecimal(Double.toString(nf.parse(cost).doubleValue())));
				i++;
			}
		}
		
		//modifying labour costs
		LinkedHashMap selectLabCodeMap = (LinkedHashMap)sessionMap.get("SelectLabourList");
		ArrayList<String> savedLab = (ArrayList)sessionMap.get("SavedLabourList");
		if(savedLab==null)	
			savedLab = new ArrayList<String>();
		ArrayList<Spestlab> insertLabList = null;
		ArrayList<Spestlab> updateLabList = null;
		if(selectLabCodeMap!=null && selectLabCodeMap.size()>0)
		{
			Iterator it = selectLabCodeMap.keySet().iterator();
			int i=0;
		
			SpestlabEjb spestlabEjb = new SpestlabEjb(getSessionKey("region"));
			
			while(it.hasNext())
			{
				String labCode = (String)it.next();
				LabourGrid lg = (LabourGrid)selectLabCodeMap.get(labCode);
				
				String qty = txtSelLabQty[i];
				//String cost = txtSelLabCost[i];
				String hrs = txtSelLabHrs[i];
				/**/
				double cost = 0;
				if(savedLab.contains(labCode))
				{
					SpestlabPK spestlabPK = new SpestlabPK();
					spestlabPK.setDeptId(costCenterNo);
					spestlabPK.setEstimateNo(applicationNo);
					spestlabPK.setLabourCode(labCode);
					
					Spestlab spestlab = spestlabEjb.findById(spestlabPK);
					spestlab.setUnitPrice(lg.getUnitPrice());
					spestlab.setLabourHours(new BigDecimal(Double.toString(nf.parse(hrs).doubleValue())));
					spestlab.setLabourCost(new BigDecimal(Double.toString(lg.getUnitPrice().doubleValue()*Double.parseDouble(qty))));
					spestlab.setItemQty(new BigDecimal(qty));
					cost = Double.parseDouble(labourRate)*lg.getUnitLabourHrs().doubleValue()*Double.parseDouble(qty);
					spestlab.setCebLabourCost(new BigDecimal(cost));
					spestlab.setCebUnitPrice(new BigDecimal(labourRate));
					spestlab.setUnitLabourHrs(lg.getUnitLabourHrs());
					if(updateLabList==null)
						updateLabList = new ArrayList<Spestlab>();
					updateLabList.add(spestlab);
				}
				else
				{
					Spestlab spestlab = new Spestlab();
					
					SpestlabPK spestlabPK = new SpestlabPK();
					spestlabPK.setDeptId(costCenterNo);
					spestlabPK.setEstimateNo(applicationNo);
					spestlabPK.setLabourCode(labCode);
					
					
					
					spestlab.setId(spestlabPK);
					spestlab.setLabourHours(new BigDecimal(Double.toString(nf.parse(hrs).doubleValue())));
					spestlab.setLabourCost(new BigDecimal(Double.toString(lg.getUnitPrice().doubleValue()*Double.parseDouble(qty))));
					spestlab.setItemQty(new BigDecimal(qty));
					spestlab.setUnitPrice(lg.getUnitPrice());
					spestlab.setActivityDescription(lg.getDescription());
					cost = Double.parseDouble(labourRate)*lg.getUnitLabourHrs().doubleValue()*Double.parseDouble(qty);
					spestlab.setCebLabourCost(new BigDecimal(cost));
					spestlab.setCebUnitPrice(new BigDecimal(labourRate));
					spestlab.setUnitLabourHrs(lg.getUnitLabourHrs());
					if(insertLabList==null)
						insertLabList = new ArrayList<Spestlab>();
					insertLabList.add(spestlab);
					savedLab.add(labCode);
				}
				
				lg.setItemQty(new BigDecimal(qty));
				lg.setCebLabourCost(new BigDecimal(cost));
				lg.setLabourHours(new BigDecimal(Double.toString(nf.parse(hrs).doubleValue())));
				lg.setLabourCost(new BigDecimal(Double.toString(lg.getUnitPrice().doubleValue()*Double.parseDouble(qty))));
				lg.setCebUnitPrice(new BigDecimal(labourRate));
				sessionMap.put("SavedLabourList",savedLab);
				
				i++;
			}
		}
		pcesthtt.setStdCost(new BigDecimal(totDetailCost));
		
		EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
		//estimateEjb.update(pcesthtt, speststd, insertList, updateList);
		estimateEjb.update(pcesthtt, speststd, insertList, updateList,insertLabList,updateLabList);
		
		ApplicationEjb applicationEJB = new ApplicationEjb(getSessionKey("region"));
		Application application = applicationEJB.findByApplicationNo(applicationNo);
		setApplicantData(application);
		setWiringData(application);
	}
	
	
	
	//set the application data to the form fields
	private void setApplicantData(Application application)
	{
		ApplicantEjb applicantEJB = new ApplicantEjb(getSessionKey("region"));
		Applicant applicant = applicantEJB.findById(application.getIdNo());
		applicantName = applicant.getFirstName()+" "+applicant.getLastName();
		companyName = applicant.getCompanyName();	
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
		isCEBEmp = applicant.getCebEmployee();
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
		streetAddress = wld.getServiceStreetAddress();
		suburb = wld.getServiceSuburb();
		city = wld.getServiceCity();
		if(wld.getServicePostalCode()!=null)
			postalCode = wld.getServicePostalCode().toString();
		address = wld.getServiceStreetAddress()+", "+wld.getServiceSuburb()+", "+wld.getServiceCity();
		if(wld.getNoOfDmgMeters()!=null)
			noOfMeterDmg = wld.getNoOfDmgMeters().toString();
	}
	
	//set estimate details to the form fields
	private void setEstimateData(Pcesthtt pcesthtt,String appNo) throws ParseException
	{
		
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		if(pcesthtt!=null)
		{
			sessionMap.put("IsNewEstimate", "N");
			jobDesc = pcesthtt.getDescr();
			categoryCode = pcesthtt.getCatCd();
			if(categoryCode!=null)
				categoryCode = categoryCode.trim();
			String fundSource = pcesthtt.getFundSource();
			String fundId = pcesthtt.getFundId();
			if(fundSource!=null)
				fundSource = fundSource.trim();
			if(fundId!=null)
				fundId = fundId.trim();
			this.fundSourceID = fundSource+"###"+fundId;
			
			SpeststdPK speststdPK = new SpeststdPK();
			speststdPK.setDeptId(costCenterNo);
			speststdPK.setEstimateNo(appNo);
			
			SpeststdEjb speststdEJB = new SpeststdEjb(getSessionKey("region"));
			Speststd speststd = speststdEJB.findById(speststdPK);
			
			
			if(speststd.getLb()!=null)
				lb = speststd.getLb().toString();
			if(speststd.getLineLength()!=null)
				lineLength = speststd.getLineLength().toString();
			loopLength = speststd.getLoopLength().toString();
			poleNo = speststd.getPoleNo().toString();
			distanceToServicePlace = speststd.getServiceDistance().toString();
			sinNo = speststd.getSinNo();
			spans = speststd.getSpan().toString();
			stayNo = speststd.getStayNo().toString();
			strutsNo = speststd.getStrutNo().toString();
			transColor = speststd.getTransformColor();
			totalDetailCost = nf.format(pcesthtt.getStdCost());
			taxAmount = "0.00";
			conductorType = speststd.getConductorType();
			isLoopService = speststd.getIsLoopService();
			wiringType = speststd.getWiringType();
				
			EstimateStatus es = new EstimateStatus();
			estimateStatus = pcesthtt.getStatus().toString();
			estimateStatusDesc = es.searchStatus(pcesthtt.getStatus().intValue());
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			estimatedDate = sdf.format(pcesthtt.getEtimateDt());
			
			EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
			selectMatList = estimateEjb.getMaterialGrid(appNo, costCenterNo);
			LinkedHashMap matMap = new LinkedHashMap();
			Iterator it = selectMatList.iterator();
			ArrayList<String> savedMat = new ArrayList<String>();
			while(it.hasNext())
			{
				MaterialGrid grid = (MaterialGrid)it.next();
				matMap.put(grid.getResCd().trim(), grid);
				savedMat.add(grid.getResCd().trim());
			}
			sessionMap.put("SelectMaterialList",matMap);
			sessionMap.put("SavedMaterialList",savedMat);
			
			//set labour costs
			SpestlabEjb spestlabEjb = new SpestlabEjb(getSessionKey("region"));
			selectLabList = spestlabEjb.getSpestlabList(appNo, costCenterNo);
			LinkedHashMap labMap = new LinkedHashMap();
			Iterator itLab = selectLabList.iterator();
			ArrayList<String> savedLab = new ArrayList<String>();
			while(itLab.hasNext())
			{
				LabourGrid lg = (LabourGrid)itLab.next();
				labMap.put(lg.getLabourCode(), lg);
				savedLab.add(lg.getLabourCode());
			}
			sessionMap.put("SelectLabourList",labMap);
			sessionMap.put("SavedLabourList",savedLab);
		}
		else
		{
			sessionMap.put("IsNewEstimate", "Y");
			SpserestPK spserestPK = new SpserestPK();
			spserestPK.setApplicationNo(applicationNo);
			spserestPK.setDeptId(costCenterNo);
			
			SpserestEjb spserestEjb = new SpserestEjb(getSessionKey("region"));
			Spserest spserest = spserestEjb.findById(spserestPK);
			wiringType = spserest.getWiringType();
			lineLength = spserest.getTotalLength();
			isLoopService = spserest.getLoopCable();
			//cableType = spserest.getCableType();
			//cableTypeDis = spserest.getCableType();
			if(spserest.getDistanceToSp()!=null)
				distanceToServicePlace = spserest.getDistanceToSp().toString();
			/*if(spserest.getConversionLength()!=null)
				conversionLength = spserest.getConversionLength().toString();
			*/
			if(spserest.getNoOfSpans()!=null)
				spans = spserest.getNoOfSpans().toString();
			SpsetwirEjb spsetwirEjb = new SpsetwirEjb(getSessionKey("region"));
			List<Spsetwir> wireList = spsetwirEjb.getWireList(applicationNo, costCenterNo);
			/*if(wireList!=null && wireList.size()>0)
			{
				for(int i=0;i<wireList.size();i++)
				{
					Spsetwir spsetwir = wireList.get(i);
					String wireMode = spsetwir.getId().getMatCd();
					if(wireMode.equals("SERVICE"))
					{
						serviceLength = spsetwir.getWireLength().toString();
					}
					else if(wireMode.equals("BARE"))
					{
						conductorLength = spsetwir.getWireLength().toString();
						conductorType = spsetwir.getWireType();
					}
						
				}
			}
			
			SpsetstyEjb spsetstyEjb = new SpsetstyEjb(getSessionKey("region"));
			List<Spsetsty> stayList = spsetstyEjb.getStayList(applicationNo, costCenterNo);
			if(stayList!=null && stayList.size()>0)
			{
				int stay = 0;
				for(int i=0;i<stayList.size();i++)
				{
					Spsetsty spsetsty = stayList.get(i);
					stay = stay+spsetsty.getMatQty().intValue();
				}
				stayNo = Integer.toString(stay);
			}
			
			SpsetstuEjb spsetstuEjb = new SpsetstuEjb(getSessionKey("region"));
			List<Spsetstu> strutList = spsetstuEjb.getStrutList(applicationNo, costCenterNo);
			if(strutList!=null && strutList.size()>0)
			{
				int strut = 0;
				for(int i=0;i<strutList.size();i++)
				{
					Spsetstu spsetstu = strutList.get(i);
					strut = strut+spsetstu.getMatQty().intValue();
				}
				strutsNo = Integer.toString(strut);
				
				
			}
			
			SpsetpolEjb spsetpolEjb = new SpsetpolEjb(getSessionKey("region"));
			List<Spsetpol> poleList = spsetpolEjb.getPoleList(applicationNo, costCenterNo);
			if(poleList!=null && poleList.size()>0)
			{
				int pole = 0;
				for(int i=0;i<poleList.size();i++)
				{
					Spsetpol spsetpol = poleList.get(i);
					pole = pole+spsetpol.getMatQty().intValue();
				}
				poleNo = Integer.toString(pole);
				
				
			}*/
			//isLoopService = "Y";
		}
		
	}
	
	private void setEstimateCostData(Pcesthtt pcesthtt)
	{
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		
		//PcesthttEjb pcesthttEjb = new PcesthttEjb(getSessionKey("region"));
		//Pcesthtt pcesthtt = pcesthttEjb.findByEstimationNo(applicationNo,costCenterNo);
		
		/*if(pcesthtt==null)
		{
			fixedCost =  nf.format(SystemValue.METER_CHANGE_COST);
			variableCost = "0.00";
			subTotal = fixedCost;
			otherCost = "0.00";
			//convCost = "0.00";
			taxAmount = "0.00";
			secDeposit = "0.00";
			totalCost = fixedCost;
		}
		else
		{*/
			SpeststdPK speststdPK = new SpeststdPK();
			speststdPK.setDeptId(costCenterNo);
			speststdPK.setEstimateNo(applicationNo);
			
			SpeststdEjb speststdEJB = new SpeststdEjb(getSessionKey("region"));
			Speststd speststd = speststdEJB.findById(speststdPK);
			if(speststd.getConversionCost()!=null)
				convCost = nf.format(speststd.getConversionCost());
			fixedCost = nf.format(speststd.getFixedCost());
			secDeposit = nf.format(speststd.getSecurityDeposit());
			variableCost = nf.format(speststd.getVariableCost());
			otherCost = nf.format(speststd.getOtherCost());
			taxAmount = "0.00";
			totalCost = nf.format(speststd.getTotalCost());
			subTotal = nf.format(new Float(speststd.getFixedCost().floatValue()+speststd.getVariableCost().floatValue()));
			
	//	}
	}
	
	
	public void setLoggedData() 
	{
		log.info( getSession());
		setLoggedInUserId(getSessionKey("userName"));
		setCostCenterNo(getSessionKey("costCenterNo"));
		setCostCenterName(getSessionKey("costCenterName"));
		region = getSessionKey("region");
	}
	
	public String calEstCostToLineLen()
	{
		try
		{
			PcesthttEjb pcesthttEjb = new PcesthttEjb(getSessionKey("region"));
			Pcesthtt pcesthtt = pcesthttEjb.findByEstimationNo(applicationNo,costCenterNo);
			
			if((isLineLenEntered!=null && isLineLenEntered.equals("true")) || (pcesthtt==null))
			{
				
				displayCostComponents();
			}
			else
			{
				setEstimateCostData(pcesthtt);
				
			}
			
		}
		catch(Exception e){
			
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	private void displayCostComponents()
	{
		ApplicationEjb applicationEJB = new ApplicationEjb(getSessionKey("region"));
		Application application = applicationEJB.findByApplicationNo(applicationNo);
		System.out.print(applicationNo);
		WiringLandDetailEjb wlEJB = new WiringLandDetailEjb(getSessionKey("region"));
		WiringLandDetailPK wlPK = new WiringLandDetailPK();
		wlPK.setApplicationId(application.getId().getApplicationId());
		wlPK.setDeptId(costCenterNo);
		WiringLandDetail wld = wlEJB.findByAppId(wlPK);
		
		int noOfMeters = 1;
		if(wld.getNoOfDmgMeters()!=null)
			noOfMeters = wld.getNoOfDmgMeters().intValue();
		fixedCost =  nf.format(SystemValue.METER_CHANGE_COST*noOfMeters);
		variableCost = "0.00";
		subTotal = fixedCost;
		otherCost = "0.00";
		convCost = "0.00";
		taxAmount = "0.00";
		secDeposit = "0.00";
		totalCost = fixedCost;
	}
	
	public String removeLabour()
	{
		try
		{
			setFormData();
			setPath(viewPath);
			double newTotLabCost = 0;
			double newTotLabHrs = 0;
			LinkedHashMap selectLabCodeMap = (LinkedHashMap)sessionMap.get("SelectLabourList");
			if(selectLabCodeMap!=null && selectLabCodeMap.size()>0)
			{
				Iterator it = selectLabCodeMap.keySet().iterator();
				int i=0;
				double totalCost = 0;
				double totalHrs = 0;
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
					
					String hrs = "";
					if(tmpLst.getLabourHours()!=null) 
						hrs = tmpLst.getLabourHours().toString();
					if(txtSelLabHrs!=null && txtSelLabHrs.length>0)
						hrs = txtSelLabHrs[i];
					if(hrs!=null && hrs.length()>0)
					{
						tmpLst.setLabourHours(new BigDecimal(hrs));
						totalHrs = totalHrs+nf.parse(hrs).doubleValue();
					}
					totalLabHrs = nf.format(totalHrs);
					
					String cost = "";
					if(tmpLst.getCebLabourCost()!=null) 
						cost = tmpLst.getCebLabourCost().toString();
					if(txtSelLabCost!=null && txtSelLabCost.length>0)
						cost = txtSelLabCost[i];
					if(cost!=null && cost.length()>0)
					{
						tmpLst.setCebLabourCost(new BigDecimal(Double.toString(nf.parse(cost).doubleValue())));
						totalCost = totalCost+nf.parse(cost).doubleValue();
					}
					totalLabCost = nf.format(totalCost);
					
					selectLabCodeMap.put(matCode,tmpLst);
					
					i++;
				}
				//selectMatList = selMatTmp;
			}
			LinkedHashMap selectMatCodeMap = (LinkedHashMap)sessionMap.get("SelectMaterialList");
			if(selectMatCodeMap!=null && selectMatCodeMap.size()>0)
			{
				sessionMap.put("IsDefaultMasterAdd", "Y");
				Iterator it = selectMatCodeMap.keySet().iterator();
				int i=0;
				double totalCost = 0;
				while(it.hasNext())
				{
					String matCode = (String)it.next();
					MaterialGrid tmpLst = (MaterialGrid)selectMatCodeMap.get(matCode);
					
					String uom  = tmpLst.getUom();
					String up  = tmpLst.getUnitPrice().toString();
					String matName  = tmpLst.getMatNm();
					String qty = "";
					if(tmpLst.getEstimateQty()!=null) 
						qty = tmpLst.getEstimateQty().toString();
					if(txtSelQty!=null && txtSelQty.length>0)
						qty = txtSelQty[i];
					if(qty!=null && qty.length()>0)
						tmpLst.setEstimateQty(new BigDecimal(qty));
					
					/*String cusQty = "";
					if(tmpLst.getCustomerQty()!=null) 
						cusQty = tmpLst.getCustomerQty().toString();
					if(txtCusQty!=null && txtCusQty.length>0)
						cusQty = txtCusQty[i];
					if(cusQty!=null && cusQty.length()>0)
						tmpLst.setCustomerQty(new BigDecimal(cusQty));
					*/
					String cost = "";
					if(tmpLst.getEstimateCost()!=null) 
						cost = tmpLst.getEstimateCost().toString();
					if(txtSelMatCost!=null && txtSelMatCost.length>0)
						cost = txtSelMatCost[i];
					if(cost!=null && cost.length()>0)
					{
						tmpLst.setEstimateCost(new BigDecimal(Double.toString(nf.parse(cost).doubleValue())));
						totalCost = totalCost+nf.parse(cost).doubleValue();
					}
					totalDetailCost = nf.format(totalCost);
					
					selectMatCodeMap.put(matCode,tmpLst);
					
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
					
					String tmpHrs = txtSelLabHrs[Integer.parseInt(indexStr)];
					double delLabHrs = 0;
					if(tmpHrs!=null && tmpHrs.trim().length()>0)
						delLabHrs = nf.parse(tmpHrs).doubleValue();
					double delOHCost = delLabHrs*Double.parseDouble(overheadRate);
					double labHrs = nf.parse(totalLabHrs).doubleValue();
					newTotLabHrs = labHrs-delLabHrs;
					totalLabHrs = nf.format(newTotLabHrs);
					
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
							delCost = delCost+delLabCost+delOHCost;
						}
					}
					
					
				}
				List dttList = null;
				if(removeList!=null)
				{
					dttList = new ArrayList();
					PcestdttEjb pcestdttEjb = new PcestdttEjb(getSessionKey("region"));
					Pcestdtt pcestdtt = pcestdttEjb.findBy3PK(applicationNo, costCenterNo, "LABOUR");
					pcestdtt.setEstimateCost(new BigDecimal(Double.toString(newTotLabCost)));
					pcestdtt.setEstimateQty(new BigDecimal(Double.toString(newTotLabHrs)));
					dttList.add(pcestdtt);
					
					Pcestdtt pcestdtt1 = pcestdttEjb.findBy3PK(applicationNo, costCenterNo, "OVERHEAD");
					double newOHCost = newTotLabHrs*Double.parseDouble(overheadRate);
					pcestdtt1.setEstimateCost(new BigDecimal(Double.toString(newOHCost)));
					pcestdtt1.setEstimateQty(new BigDecimal(Double.toString(newTotLabHrs)));
					dttList.add(pcestdtt1);
					
					PcesthttEjb pcesthttEjb = new PcesthttEjb(getSessionKey("region"));
					Pcesthtt pcesthtt = null;
					try
					{
						pcesthtt = pcesthttEjb.findByEstimationNo(applicationNo,costCenterNo);
						double preStdCost = pcesthtt.getStdCost().doubleValue();
						double newStdCost = preStdCost - delCost;
						pcesthtt.setStdCost(new BigDecimal(Double.toString(newStdCost)));
						
					}
					catch(Exception e1){
						e1.printStackTrace();
					}
					EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
					
					estimateEjb.removeLabour(removeList,pcesthtt,dttList,null);
					//estimateEjb.removeLabour(removeList,pcesthtt);
				}
			}
			//LinkedHashMap selectMatCodeMap = (LinkedHashMap)sessionMap.get("SelectMaterialList");
			
			MaterialGrid tmpLst = (MaterialGrid)selectMatCodeMap.get("LABOUR");
			tmpLst.setEstimateQty(new BigDecimal(Double.toString(newTotLabHrs)));
			tmpLst.setEstimateCost(new BigDecimal(Double.toString(newTotLabCost)));
			
			MaterialGrid tmpLst1 = (MaterialGrid)selectMatCodeMap.get("OVERHEAD");
			tmpLst1.setEstimateQty(new BigDecimal(Double.toString(newTotLabHrs)));
			tmpLst1.setEstimateCost(new BigDecimal(Double.toString(newTotLabHrs*Double.parseDouble(overheadRate))));
			
			sessionMap.put("SelectLabourList",selectLabCodeMap);
			sessionMap.put("SelectMaterialList",selectMatCodeMap);
			selectLabList = selectLabCodeMap.values();
			isRemoveLabClicked = null; 
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		setSuccessMessage("Removed successfully.");
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
		if(isViewOnly!=null && isViewOnly.equals("true"))
			path = path+"View Estimate";
		else if(isModify!=null && isModify.equals("true"))
			path = path+"Modify Estimate";
		else if(isUndoReject!=null && isUndoReject.equals("true"))
			path = path+"Undo Rejected Estimate";
		else if(isApprove!=null && isApprove.equals("true"))
			path = path+"Approve Estimate";
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

	public String getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}

	public String getConvCost() {
		return convCost;
	}

	public void setConvCost(String convCost) {
		this.convCost = convCost;
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

	public String getIsRemoveLabClicked() {
		return isRemoveLabClicked;
	}

	public void setIsRemoveLabClicked(String isRemoveLabClicked) {
		this.isRemoveLabClicked = isRemoveLabClicked;
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

		public String getStreetAddress() {
			return streetAddress;
		}

		public void setStreetAddress(String streetAddress) {
			this.streetAddress = streetAddress;
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

		public String getPostalCode() {
			return postalCode;
		}

		public void setPostalCode(String postalCode) {
			this.postalCode = postalCode;
		}

		public String getEstimatedDate() {
			return estimatedDate;
		}

		public void setEstimatedDate(String estimatedDate) {
			this.estimatedDate = estimatedDate;
		}

		public String getNoOfMeterDmg() {
			return noOfMeterDmg;
		}

		public void setNoOfMeterDmg(String noOfMeterDmg) {
			this.noOfMeterDmg = noOfMeterDmg;
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

}//End of class
