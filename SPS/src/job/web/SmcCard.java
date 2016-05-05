package job.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;

import offDoc.web.PrintSMC;
import offDoc.web.ServiceMainCard;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import application.model.Application;
import application.model.Spapplon;
import application.model.SpapplonPK;
import application.model.WiringLandDetail;
import application.model.WiringLandDetailPK;
import application.service.ApplicantEjb;
import application.service.ApplicationEjb;
import application.service.SpapplonEjb;
import application.service.WiringLandDetailEjb;
import application.model.Applicant;

import estimate.model.Speststd;
import estimate.model.SpeststdPK;
import estimate.model.Spserest;
import estimate.model.SpserestPK;
import estimate.service.SpeststdEjb;
import estimate.service.SpserestEjb;

import job.model.Spestcnd;
import job.model.SpestcndPK;
import job.model.Spestcnt;
import job.model.SpestcntPK;
import job.model.Spestsea;
import job.model.SpestseaPK;
import job.model.Spodrcrd;
import job.model.SpodrcrdPK;
import job.model.Pcesthmt;

import job.service.JobEjb;
import job.service.PcesthmtEjb;
import job.service.SpestcndEjb;
import job.service.SpestcntEjb;
import job.service.SpestseaEjb;
import job.service.SpodrcrdEjb;

import com.opensymphony.xwork2.ActionSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import piv.model.PivDetail;
import piv.service.PivDetailEjb;
import util.common.Format;
import util.common.JobProcessStatus;

@SuppressWarnings("serial")
public class SmcCard extends ActionSupport implements SessionAware, ParameterAware,ServletResponseAware,ServletRequestAware{
	private static final Log log = LogFactory.getLog(SmcCard.class);
	private static final String viewPath="Manage Jobs>Service Main Card";
	NumberFormat nf = NumberFormat.getInstance();
    
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private String errorMessage;
	private String infoMessage;
	
	//session variables
	private Map<String, Object> sessionMap;
	String loggedUser;

	//JSP Fields
	private String costCenterNo;
	private String jobCostCenterNo;
	private String costCenterName;
	
	private String jobNo;
	private String smcType;
	private String sinNo;
	private String costCode;
	private String ecsc;
	private String year;
	private String serialJobNo;
	private String noOfMeters;
	private String conDate;
	private String printConDate;
	private String avgConsump;
	private String meterNo1;
	private String reading1;
	private String meterType1;
	private String noDigit1;
	private String meterNo2;
	private String reading2;
	private String meterType2;
	private String noDigit2;
	private String meterNo3;
	private String reading3;
	private String meterType3;
	private String noDigit3;
	private String payMode;
	private String payDate;
	private String secDeposit;
	private String smCharge;
	private String taxAmount;
	private String totalCost;
	private String tariffCode;
	private String kva;
	private String connectionType;
	private String phase;
	private String bankRef;
	private String neighboursAccNo;
	private String bankBranch;
	private String lastName;
	private String firstName;
	private String address;
	private String street;
	private String suburb;
	private String city;
	private String postalCode;
	private String pivNo;
	private String pivDate;
	private String cusType;
	private String readerCode;
	private String packNo;
	private String walkSeq;
	private String oldAcct;
	private Date energizeDate;
	private String statusMsg;
	private String SealNumber1;
	private String SealNumber2;
	private String SealNumber3;
	private String SealNumber4;
	private String SealNumber5;
	private String SealNumber6;
	private String SealNumber7;
	private String SealNumber8;
	private String SealNumber9;
	private String isNewOrderCard;
	private String contractorId;
	private String successMessage;	
	private String  hid_CostCenter;
	private String hid_EstimateNo;	
	private String hid_loanCode;
	private Date hid_loanDate;
	private String hid_noOfInstalment;
	private String hid_loanAmount;
	private String hid_instalmantAmount;
	private String jobType;
	private List<String> listjobNo;
	

	

	// Hidden Fields
	private String isViewJob;
	
	//other Fields
	private String path;
	private String state;
	private String region;
	
	

	public String execute()	{
		//return ERROR;getJobNoListBySubType
		//SpestcndEjb spejb=new SpestcndEjb(getSessionKey("region"));
	 	//List<String> jobList=spejb.getJobNoListBySubType(costCenterNo,JobProcessStatus.ALLOCATED,getSessionKey("smcType"));
	 	//setListjobNo(jobList);
		//set
		//System.out.println("4444444444444444444444444 "+getState());
		
		System.out.println("getState() "+getState());
		hid_CostCenter = getSessionKey("costCenterNo");
		if(getState().equals("billingClerk")){
			if(jobNo!=null && jobNo.length()>0){
				setJobNo(jobNo.trim());
				viewOrderCardDetails();
					
			}
			return SUCCESS;
		}else if(getState().equals("nCjobFinishDirect")){
			System.out.println("jobNo%%%%%%%%%%%%%%%%%%%%%%%%%%%% "+jobNo);
			setErrorMessage(null);
			setSuccessMessage(null);
			setInfoMessage(null);
			setLoggedData();
			hid_CostCenter = getSessionKey("costCenterNo");
			setPath(viewPath);
			Format format=new Format();
			if(jobNo!=null && jobNo.length()>0){
				setJobNo(jobNo.trim());
				viewOrderCardDetails();
			}else{
				//jobNo =getSessionKey("costCenterNo")+"/"+"SMC"+"/"+format.getYear().substring(2, format.getYear().length())+"/";
				if (getSessionKey("smcType").equals("NC")){
					jobType="SMC";
				}else if (getSessionKey("smcType").equals("CR")){
					jobType="CRJ";
				}else {
					jobType="XXX";
				}
				jobNo =getSessionKey("costCenterNo")+"/"+jobType+"/"+format.getYear().substring(2, format.getYear().length())+"/";
			}
			return SUCCESS;
		}else if(getState().equals("cRjobFinishDirect")){
			System.out.println("jobNo&&&&&&&&&&&&&&&&&&&&&&&&&&&&& "+jobNo);
			setErrorMessage(null);
			setSuccessMessage(null);
			setInfoMessage(null);
			setLoggedData();
			hid_CostCenter = getSessionKey("costCenterNo");
			setPath(viewPath);
			Format format=new Format();
			if(jobNo!=null && jobNo.length()>0){
				setJobNo(jobNo.trim());
				viewOrderCardDetails();
			}else{
				//jobNo =getSessionKey("costCenterNo")+"/"+"SMC"+"/"+format.getYear().substring(2, format.getYear().length())+"/";
				if (getSessionKey("smcType").equals("NC")){
					jobType="SMC";
				}else if (getSessionKey("smcType").equals("CR")){
					jobType="CRJ";
				}else {
					jobType="XXX";
				}
				jobNo =getSessionKey("costCenterNo")+"/"+jobType+"/"+format.getYear().substring(2, format.getYear().length())+"/";
			}
			return SUCCESS;
		}else{
				hid_CostCenter = getSessionKey("costCenterNo");	
				System.out.println("jobNo^^^^^^^^^^^^^^^^^^^^^^^^ "+jobNo);
				SpestcndEjb spejb=new SpestcndEjb(getSessionKey("region"));
			 	List<String> jobList=spejb.getJobNoListBySubType(costCenterNo,JobProcessStatus.ALLOCATED,getSessionKey("smcType"));
			 	setListjobNo(jobList);
				return ERROR;
		}
	 	//*/	
		/*}else{
			System.out.println("jobNo%%%%%%%%%%%%%%%%%%%%%%%%%%%% "+jobNo);
			setErrorMessage(null);
			setSuccessMessage(null);
			setInfoMessage(null);
			setLoggedData();
			hid_CostCenter = getSessionKey("costCenterNo");
			setPath(viewPath);
			Format format=new Format();
			if(jobNo!=null && jobNo.length()>0){
				setJobNo(jobNo.trim());
				viewOrderCardDetails();
			}else{
				//jobNo =getSessionKey("costCenterNo")+"/"+"SMC"+"/"+format.getYear().substring(2, format.getYear().length())+"/";
				if (getSessionKey("smcType").equals("NC")){
					jobType="SMC";
				}else if (getSessionKey("smcType").equals("CR")){
					jobType="CRJ";
				}else {
					jobType="XXX";
				}
				jobNo =getSessionKey("costCenterNo")+"/"+jobType+"/"+format.getYear().substring(2, format.getYear().length())+"/";
			}
			return SUCCESS;
		}*/
	}
	
	public String savePrint()
	{
		try
		{
			loggedUser = getSession().get("userName").toString();
			setLoggedData();
			setPath(viewPath);
			saveOrderCard();
			setSuccessMessage("Order Card saved and printed successfully.");
		}
		catch(Exception e)
		{
			setErrorMessage("Unexpected Error! Please retry.");
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	public String save() {
		loggedUser = getSession().get("userName").toString();
		setLoggedData();
		setPath(viewPath);
		try	{
			saveOrderCard();
			setSuccessMessage("Order Card saved successfully.");
		}catch(Exception e){
			setErrorMessage("Unexpected Error! Please retry.");
			e.printStackTrace();
			
		}
		SpestcndEjb spejb=new SpestcndEjb(getSessionKey("region"));
	 	List<String> jobList=spejb.getJobNoListBySubType(costCenterNo,JobProcessStatus.ALLOCATED,getSessionKey("smcType"));
	 	setListjobNo(jobList);
		return SUCCESS;
	}
	
	public String back(){
		System.out.println("@@@@@@@@@@");
		return "backToSendToBilling";
	}
	
	
	private void saveOrderCard()throws Exception
	{
		System.out.println("1");
		SpodrcrdPK spodrcrdPK = new SpodrcrdPK();
		spodrcrdPK.setDeptId(costCenterNo);
		spodrcrdPK.setProjectNo(jobNo);
		
		SpodrcrdEjb spodrcrdEjb = new SpodrcrdEjb(getSessionKey("region"));
		
		Spodrcrd spodrcrd =null;
		if(costCenterNo.equals("541.00") || costCenterNo.equals("541.10") || costCenterNo.equals("541.20") || costCenterNo.equals("542.00") || costCenterNo.equals("542.10") || costCenterNo.equals("542.20") || costCenterNo.equals("547.00")|| costCenterNo.equals("547.10")|| costCenterNo.equals("547.20") || costCenterNo.equals("548.00") || costCenterNo.equals("548.10") || costCenterNo.equals("548.20")){
			spodrcrd = spodrcrdEjb.findByJobNo(jobNo);
		}else{
			spodrcrd = spodrcrdEjb.findById(spodrcrdPK);
		}
		
		System.out.println("2");
		if(spodrcrd == null)//insert
		{
			spodrcrd = new Spodrcrd();
			spodrcrd.setId(spodrcrdPK);
			setSpodrcrdValues(spodrcrd);
			spodrcrdEjb.createSpodrcrd(spodrcrd);
		}
		else//update
		{
			setSpodrcrdValues(spodrcrd);
			spodrcrdEjb.updateSpodrcrd(spodrcrd);
		}
		System.out.println("3");
		SpserestEjb spserestEjb=new SpserestEjb(getSessionKey("region"));
		SpserestPK spserestPK=new SpserestPK();
		spserestPK.setApplicationNo(hid_EstimateNo.trim());
		spserestPK.setDeptId(costCenterNo);			
		
		
		Spserest spserest = null;
		if(costCenterNo.equals("541.00") || costCenterNo.equals("541.10") || costCenterNo.equals("541.20") || costCenterNo.equals("542.00") || costCenterNo.equals("542.10") || costCenterNo.equals("542.20") || costCenterNo.equals("547.00")|| costCenterNo.equals("547.10")|| costCenterNo.equals("547.20") || costCenterNo.equals("548.00") || costCenterNo.equals("548.10") || costCenterNo.equals("548.20")){
			spserest = spserestEjb.findByApplicationNo(hid_EstimateNo.trim());
		}else{
			spserest = spserestEjb.findById(spserestPK);
		}
		
		System.out.println("4");
		if(spserest!=null && sinNo!=null && sinNo.length()>0){
			spserest.setSin(sinNo);
			spserestEjb.updateSpserest(spserest);
		}
		
		//DW
		//Spestsea spestsea=new Spestsea();
		SpestseaEjb spestseaEjb=new SpestseaEjb(getSessionKey("region"));
		SpestseaPK spestseaPK=new SpestseaPK();
		spestseaPK.setDeptId(costCenterNo);
		spestseaPK.setProjectNo(jobNo.trim());
		//Spestsea spestsea=spestseaEjb.findById(spestseaPK);
		Spestsea spestsea=null;
		if(costCenterNo.equals("541.00") || costCenterNo.equals("541.10") || costCenterNo.equals("541.20") || costCenterNo.equals("542.00") || costCenterNo.equals("542.10") || costCenterNo.equals("542.20") || costCenterNo.equals("547.00")|| costCenterNo.equals("547.10")|| costCenterNo.equals("547.20") || costCenterNo.equals("548.00") || costCenterNo.equals("548.10") || costCenterNo.equals("548.20")){
			spestsea=spestseaEjb.findByJobNo(jobNo);
		}else{
			spestsea=spestseaEjb.findById(spestseaPK);
		}
		
		System.out.println("5");
		
		if(spestsea!=null ){
			System.out.println("6");
			if(SealNumber1.length()!=0)
				spestsea.setSealNo1(SealNumber1);
			else  spestsea.setSealNo1("0");
			if(SealNumber2.length()!=0)
				spestsea.setSealNo2(SealNumber2);
			else  spestsea.setSealNo2("0");
			if(SealNumber3.length()!=0)
				spestsea.setSealNo3(SealNumber3);
			else  spestsea.setSealNo3("0");
			if(SealNumber4.length()!=0)
				spestsea.setSealNo4(SealNumber4);
			else  spestsea.setSealNo4("0");
			if(SealNumber5.length()!=0)
				spestsea.setSealNo5(SealNumber5);
			else  spestsea.setSealNo5("0");
			if(SealNumber6.length()!=0)
				spestsea.setSealNo6(SealNumber6);
			else  spestsea.setSealNo6("0");
			if(SealNumber7.length()!=0)
				spestsea.setSealNo7(SealNumber7);
			else  spestsea.setSealNo7("0");
			if(SealNumber8.length()!=0)
				spestsea.setSealNo8(SealNumber8);
			else  spestsea.setSealNo8("0");
			if(SealNumber9.length()!=0)
				spestsea.setSealNo9(SealNumber9);
			else  spestsea.setSealNo9("0");
			
			/*
			if(SealNumber1!=null && SealNumber1!="")
				spestsea.setSealNo1(SealNumber1);
			else  spestsea.setSealNo1("0");
			if(SealNumber2!=null && SealNumber2!="")
				spestsea.setSealNo2(SealNumber2);
			else  spestsea.setSealNo2("0");
			if(SealNumber3!=null && SealNumber3!="")
				spestsea.setSealNo3(SealNumber3);
			else  spestsea.setSealNo3("0");
			
			if(SealNumber4!=null && SealNumber4!="")
				spestsea.setSealNo4(SealNumber4);
			if(SealNumber5!=null && SealNumber5!="")
				spestsea.setSealNo5(SealNumber5);
			if(SealNumber6!=null && SealNumber6!="")
				spestsea.setSealNo6(SealNumber6);
			if(SealNumber7!=null && SealNumber7!="")
				spestsea.setSealNo7(SealNumber7);
			if(SealNumber8!=null && SealNumber8!="")
				spestsea.setSealNo8(SealNumber8);
			if(SealNumber9!=null && SealNumber9!="")
				spestsea.setSealNo9(SealNumber9);	
			 */
			//if (SealNumber1.length()!=0 && SealNumber2.length()!=0 && SealNumber3.length()!=0)
			//if (spestsea.getSealNo1().equals("0") && spestsea.getSealNo2().equals("0") && spestsea.getSealNo3().equals("0")){
				
			//}else{
			System.out.println(spestsea);
			spestseaEjb.updateSpestsea(spestsea);	
			//}
			
			
		}else{
			System.out.println("7");
			System.out.println("****"+SealNumber4+"****");
			System.out.println("****"+SealNumber4.length()+"****");
			System.out.println((SealNumber4!=null ));
			System.out.println((SealNumber4!=""));
			
			System.out.println((SealNumber4!=null && SealNumber1!=""));
			System.out.println((SealNumber4.length()!=0));
			spestsea=new Spestsea();
			spestsea.setId(spestseaPK);
			/*if(SealNumber1!=null && SealNumber1!="")
				spestsea.setSealNo1(SealNumber1);
			else  spestsea.setSealNo1("0");
			if(SealNumber2!=null && SealNumber2!="")
				spestsea.setSealNo2(SealNumber2);
			else  spestsea.setSealNo2("0");
			if(SealNumber3!=null && SealNumber3!="")
				spestsea.setSealNo3(SealNumber3);
			else  spestsea.setSealNo3("0");
			if(SealNumber4!=null && SealNumber4!="")
				spestsea.setSealNo4(SealNumber4);
			if(SealNumber5!=null && SealNumber5!="")
				spestsea.setSealNo5(SealNumber5);
			if(SealNumber6!=null && SealNumber6!="")
				spestsea.setSealNo6(SealNumber6);
			if(SealNumber7!=null && SealNumber7!="")
				spestsea.setSealNo7(SealNumber7);
			if(SealNumber8!=null && SealNumber8!="")
				spestsea.setSealNo8(SealNumber8);
			if(SealNumber9!=null && SealNumber9!="")
				spestsea.setSealNo9(SealNumber9);	
			*/
			if(SealNumber1.length()!=0)
				spestsea.setSealNo1(SealNumber1);
			else  spestsea.setSealNo1("0");
			if(SealNumber2.length()!=0)
				spestsea.setSealNo2(SealNumber2);
			else  spestsea.setSealNo2("0");
			if(SealNumber3.length()!=0)
				spestsea.setSealNo3(SealNumber3);
			else  spestsea.setSealNo3("0");
			
			if(SealNumber4.length()!=0)
				spestsea.setSealNo4(SealNumber4);
			else spestsea.setSealNo4("0");
			if(SealNumber5.length()!=0)
				spestsea.setSealNo5(SealNumber5);
			else spestsea.setSealNo5("0");
			if(SealNumber6.length()!=0)
				spestsea.setSealNo6(SealNumber6);
			else spestsea.setSealNo6("0");
			if(SealNumber7.length()!=0)
				spestsea.setSealNo7(SealNumber7);
			else spestsea.setSealNo7("0");
			if(SealNumber8.length()!=0)
				spestsea.setSealNo8(SealNumber8);
			else spestsea.setSealNo8("0");
			if(SealNumber9.length()!=0)
				spestsea.setSealNo9(SealNumber9);	
			else spestsea.setSealNo9("0");
			
			//if (spestsea.getSealNo1().equals("0") && spestsea.getSealNo2().equals("0") && spestsea.getSealNo3().equals("0")){
				
			//}else{
			System.out.println(spestsea);
				spestseaEjb.createSpestsea(spestsea);
			//}
				
			    System.out.println("6");
		}
		
		PcesthmtEjb pcesthmtEjb=new PcesthmtEjb(getSessionKey("region"));
		Pcesthmt pcesthmt=pcesthmtEjb.findByJobNo(jobNo.trim());
		WiringLandDetail wiringLandDetail=null;
		WiringLandDetailEjb wiringLandDetailEjb=null;
		if (pcesthmt!=null){
			wiringLandDetailEjb=new WiringLandDetailEjb(getSessionKey("region"));
			wiringLandDetail=wiringLandDetailEjb.findByApplicationNo(pcesthmt.getId().getEstimateNo());
			if (wiringLandDetail!=null){
				wiringLandDetail.setExistingAccNo(oldAcct);
				wiringLandDetailEjb.updateWiringLandDetail(wiringLandDetail);
			}
		
		}
		
		//DW
	}
	
	public void energize() throws Exception {
		//try{
			setLoggedData();
			SpestseaPK spestseaPK =new SpestseaPK();
			JobEjb jobEjb =new JobEjb(getSessionKey("region"));
			/*spestseaPK.setDeptId(costCenterNo);
			spestseaPK.setProjectNo(jobNo);
			
			Spestsea spestsea=new Spestsea();
			spestsea.setId(spestseaPK);
			
			if(SealNumber1!=null && SealNumber1!="")
				spestsea.setSealNo1(SealNumber1);
			if(SealNumber2!=null && SealNumber2!="")
				spestsea.setSealNo2(SealNumber2);
			if(SealNumber3!=null && SealNumber3!="")
				spestsea.setSealNo3(SealNumber3);
			if(SealNumber4!=null && SealNumber4!="")
				spestsea.setSealNo4(SealNumber4);
			if(SealNumber5!=null && SealNumber5!="")
				spestsea.setSealNo5(SealNumber5);
			if(SealNumber6!=null && SealNumber6!="")
				spestsea.setSealNo6(SealNumber6);
			if(SealNumber7!=null && SealNumber7!="")
				spestsea.setSealNo7(SealNumber7);
			if(SealNumber8!=null && SealNumber8!="")
				spestsea.setSealNo8(SealNumber8);
			if(SealNumber9!=null && SealNumber9!="")
				spestsea.setSealNo9(SealNumber9);		
		
			*/			
			SpestcndEjb spestcndejb =new SpestcndEjb(getSessionKey("region"));
			//SpestcndPK spestcndpk = new SpestcndPK();
			//spestcndpk.setContractorId(contractorId.trim());
			//spestcndpk.setDeptId(costCenterNo.trim());
			//spestcndpk.setProjectNo(jobNo.trim());					 
			Spestcnd spestcnd=null;
			if(costCenterNo.equals("541.00") || costCenterNo.equals("541.10") || costCenterNo.equals("541.20") || costCenterNo.equals("542.00") || costCenterNo.equals("542.10") || costCenterNo.equals("542.20") || costCenterNo.equals("547.00")|| costCenterNo.equals("547.10")|| costCenterNo.equals("547.20") || costCenterNo.equals("548.00") || costCenterNo.equals("548.10") || costCenterNo.equals("548.20")){
				spestcnd=spestcndejb.findByJobNo(jobNo);
			}else{
				List<Spestcnd> spestcndList=spestcndejb.findByJobNo(jobNo.trim(), costCenterNo);
				if(spestcndList.size()>0){
					spestcnd=spestcndList.get(0);
					
				}
			}
			
			contractorId=spestcnd.getId().getContractorId();
			System.out.println("contractorId "+contractorId);
			BigDecimal jobAmount =null;
			if(spestcnd!=null){
				
				spestcnd.setStatus(JobProcessStatus.FINISHED);	
				Calendar calendar = Calendar.getInstance();
				Format format=new Format();
				
				spestcnd.setFinishedDate(calendar.getTime());
				spestcnd.setFinishedUser(getSessionKey("userName"));
				spestcnd.setFinishedTime(format.FormatTime());
				spestcnd.setIsExported("N");
				jobAmount= spestcnd.getAmount();
						
				
			}
			
			SpestcntEjb ejbCnt=new SpestcntEjb(getSessionKey("region"));	
			SpestcntPK spestcntpk = new SpestcntPK();
			spestcntpk.setContractorId(contractorId.trim());
			spestcntpk.setDeptId(costCenterNo.trim());
			int jobCount = 0;
			BigDecimal jobInHand = null;
			BigDecimal totalAmount = null;
			Double totAmount = (double) 0;
			 
			//Spestcnt spestcnt=ejbCnt.findById(spestcntpk);
			Spestcnt spestcnt=null;
			if(costCenterNo.equals("541.00") || costCenterNo.equals("541.10") || costCenterNo.equals("541.20") || costCenterNo.equals("542.00") || costCenterNo.equals("542.10") || costCenterNo.equals("542.20") || costCenterNo.equals("547.00")|| costCenterNo.equals("547.10")|| costCenterNo.equals("547.20") || costCenterNo.equals("548.00") || costCenterNo.equals("548.10") || costCenterNo.equals("548.20")){
				spestcnt=ejbCnt.findByContractorId(contractorId.trim(),costCenterNo);
			}else{
				spestcnt=ejbCnt.findById(spestcntpk);
			}
			if(spestcnt!=null){								 
				 jobInHand = spestcnt.getJobInHand();
				 totalAmount = spestcnt.getTotalAmount();
				 jobCount = jobInHand.intValue()-1;
				 totAmount = totalAmount.doubleValue() - jobAmount.doubleValue();
				
				 spestcnt.setJobInHand(new BigDecimal(jobCount));
				 spestcnt.setTotalAmount(new BigDecimal(totAmount));
				 				
			}
			System.out.println("contractorId "+contractorId+" jobInHand" +jobInHand+"totalAmount "+totalAmount+"jobCount "+jobCount+"totAmount "+totAmount);
			jobEjb.energizeJob(null, spestcnd, spestcnt);
			setSuccessMessage("Job Finished successfully.");
			
		//}catch(Exception ex){
		//	setSuccessMessage("Job not Finished");
		//	ex.printStackTrace();
		//}
		
		//return SUCCESS;
	}
	

	
	
	private void setSpodrcrdValues(Spodrcrd spodrcrd)throws Exception
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(avgConsump!=null && avgConsump.trim().length()>0)
			spodrcrd.setAvgConsump(new BigDecimal(avgConsump));
		if(conDate!=null && conDate.trim().length()>0)
			spodrcrd.setConnectedDate(sdf.parse(conDate.substring(0,10)));
		if(reading1!=null && reading1.trim().length()>0)
			spodrcrd.setInitReading1(new BigDecimal(reading1));
		if(reading2!=null && reading2.trim().length()>0)
			spodrcrd.setInitReading2(new BigDecimal(reading2));
		if(reading3!=null && reading3.trim().length()>0)
			spodrcrd.setInitReading3(new BigDecimal(reading3));
		if(kva!=null && kva.trim().length()>0)
			spodrcrd.setKvaRating(new BigDecimal(kva));
		spodrcrd.setMeterNo1(meterNo1);
		spodrcrd.setMeterNo2(meterNo2);
		spodrcrd.setMeterNo3(meterNo3);
		spodrcrd.setMeterType1(meterType1);
		spodrcrd.setMeterType2(meterType2);
		spodrcrd.setMeterType3(meterType3);
		if(noDigit1!=null && noDigit1.trim().length()>0)
			spodrcrd.setNoOfDigits1(new BigDecimal(noDigit1));
		if(noDigit2!=null && noDigit2.trim().length()>0)
			spodrcrd.setNoOfDigits2(new BigDecimal(noDigit2));
		if(noDigit3!=null && noDigit3.trim().length()>0)
			spodrcrd.setNoOfDigits3(new BigDecimal(noDigit3));
		if(noOfMeters!=null && noOfMeters.trim().length()>0)
			spodrcrd.setNoOfMeters(new BigDecimal(noOfMeters));
		
		//spodrcrd.setOldAccNo(oldAcct);
		spodrcrd.setPackNo(packNo);
		spodrcrd.setReaderCode(readerCode);
		spodrcrd.setUpdBy(loggedUser);
		spodrcrd.setUpdDate(new Date());
		spodrcrd.setWalkSeq(walkSeq);
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		if(conDate!=null && conDate.trim().length()>0)
		{
			printConDate = sdf1.format(sdf.parse(conDate.substring(0,10))); 
		}
		
	}
	
	
	//action invoke when Find button is clicked
	public String viewOrderCardDetails()
	{
		try
		{	//DW
			SpestcndEjb spejb=new SpestcndEjb(getSessionKey("region"));
		 	List<String> jobList=spejb.getJobNoListBySubType(costCenterNo,JobProcessStatus.ALLOCATED,getSessionKey("smcType"));
		 	setListjobNo(jobList);
			//DW
		 	setLoggedData();
			setPath(viewPath);
			clearFields();
			System.out.println("viewOrderCardDetails");
			if (getSessionKey("smcType").equals("NC")){
				jobType="SMC";
			}else if (getSessionKey("smcType").equals("CR")){
				jobType="CRJ";
			}else {
				jobType="XXX";
			}	
			PcesthmtEjb pcesthmtEjb = new PcesthmtEjb(getSessionKey("region"));
			Pcesthmt pcesthmt = null;
			if(costCenterNo.equals("541.00") || costCenterNo.equals("541.10") || costCenterNo.equals("541.20") || costCenterNo.equals("542.00") || costCenterNo.equals("542.10") || costCenterNo.equals("542.20") || costCenterNo.equals("547.00")|| costCenterNo.equals("547.10")|| costCenterNo.equals("547.20") || costCenterNo.equals("548.00") || costCenterNo.equals("548.10") || costCenterNo.equals("548.20")){
				pcesthmt = pcesthmtEjb.findByJobNo(jobNo);
			}else{
				pcesthmt = pcesthmtEjb.findByJobNo(jobNo, costCenterNo);
			}
			if(pcesthmt!=null){
				Spestcnd spestcnd=null;
				List<Spestcnd> listSpestcnd=new ArrayList<Spestcnd>();
				SpestcndEjb spestcndejb =new SpestcndEjb(getSessionKey("region"));		
				//List<Spestcnd> listSpestcnd = spestcndejb.findByJobNo(jobNo.trim(),costCenterNo);
				if(costCenterNo.equals("541.00") || costCenterNo.equals("541.10") || costCenterNo.equals("541.20") || costCenterNo.equals("542.00") || costCenterNo.equals("542.10") || costCenterNo.equals("542.20") || costCenterNo.equals("547.00")|| costCenterNo.equals("547.10")|| costCenterNo.equals("547.20") || costCenterNo.equals("548.00") || costCenterNo.equals("548.10") || costCenterNo.equals("548.20")){
					spestcnd=spestcndejb.findByJobNo(jobNo.trim());
					listSpestcnd.add(spestcnd);
				}else{
					
					listSpestcnd = spestcndejb.findByJobNo(jobNo.trim(),costCenterNo);
				}
				if(listSpestcnd!=null && listSpestcnd.size()>0){
					
					spestcnd = listSpestcnd.get(0);
					
					if(spestcnd!=null ){
						//DW
						Spestsea spestsea=new Spestsea();
						SpestseaEjb spestseaEjb=new SpestseaEjb(getSessionKey("region"));
						SpestseaPK spestseaPK=new SpestseaPK();
						spestseaPK.setDeptId(costCenterNo);
						spestseaPK.setProjectNo(jobNo.trim());
						//spestsea=spestseaEjb.findById(spestseaPK);
						spestsea=spestseaEjb.findById(spestseaPK);
						if(costCenterNo.equals("541.00") || costCenterNo.equals("541.10") || costCenterNo.equals("541.20") || costCenterNo.equals("542.00") || costCenterNo.equals("542.10") || costCenterNo.equals("542.20") || costCenterNo.equals("547.00")|| costCenterNo.equals("547.10")|| costCenterNo.equals("547.20") || costCenterNo.equals("548.00") || costCenterNo.equals("548.10") || costCenterNo.equals("548.20")){
							spestsea=spestseaEjb.findByJobNo(jobNo);
						}else{
							spestsea=spestseaEjb.findById(spestseaPK);
						}
						
						if(spestsea!=null ){
							setSealNumber1(spestsea.getSealNo1());
							setSealNumber2(spestsea.getSealNo2());
							setSealNumber3(spestsea.getSealNo3());
							setSealNumber4(spestsea.getSealNo4());
							setSealNumber5(spestsea.getSealNo5());
							setSealNumber6(spestsea.getSealNo6());
							setSealNumber7(spestsea.getSealNo7());
							setSealNumber8(spestsea.getSealNo8());
							setSealNumber9(spestsea.getSealNo9());
							
						}
						
						//DW
						
						String applicationNo = pcesthmt.getId().getEstimateNo();	
						hid_EstimateNo = applicationNo;
						ApplicationEjb applicationEJB = new ApplicationEjb(getSessionKey("region"));
						Application application = applicationEJB.findByApplicationNo(applicationNo);
						
						
						StringTokenizer st = new StringTokenizer(jobNo,"/");
						String costCenter = null;
						if(st.countTokens()==3){
							costCenter = st.nextToken();
							year = st.nextToken();
							serialJobNo = st.nextToken();
						}else{
							costCenter = this.costCenterNo;
							Date appDate = application.getSubmitDate();
							Calendar cal = Calendar.getInstance();
							cal.setTime(appDate);
							year = Integer.toString(cal.get(Calendar.YEAR));
							serialJobNo = jobNo;
						}
						st = new StringTokenizer(costCenter,".");
						costCode = st.nextToken();
						ecsc = st.nextToken();						
												
						smcType = application.getApplicationType();
						setApplicantData(application);
						setWiringData(application);
						setPIVData(applicationNo,application);
						setEstimateData(pcesthmt);
						setLoanDetails( pcesthmt);
						
						setOrderCardData();
						this.isViewJob = "Y";					
						return SUCCESS;
					}else{
						setErrorMessage("Job can not be found");
						this.isViewJob = null;
						return ERROR;
					}
										
				}else{
					setErrorMessage("Job has not allocated to a contractor yet");
					this.isViewJob = null;
					return ERROR;
				}
				
			}else{
				setErrorMessage("Job does not exists");
				this.isViewJob = null;
				return ERROR;
			}
		}
		catch(Exception e){
			setErrorMessage("Unexpected Error! Please retry.");
			this.isViewJob = null;
			e.printStackTrace();
			return ERROR;
		}
	}
	
	//action invoke when Clear button is clicked
	public String clear()
	{
		this.jobNo = "";
		setErrorMessage("");
		setSuccessMessage(null);
		setInfoMessage(null);
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
		this.isViewJob = null;
		this.smcType = "";
		this.sinNo = "";
		this.costCode = "";
		this.ecsc = "";
		this.year = "";
		this.serialJobNo = "";
		this.noOfMeters = "";
		this.conDate = "";
		this.avgConsump = "";
		this.meterNo1 = "";
		this.reading1 = "";
		this.meterType1 = "";
		this.noDigit1 = "";
		this.meterNo2 = "";
		this.reading2 = "";
		this.meterType2 = "";
		this.noDigit2 = "";
		this.meterNo3 = "";
		this.reading3 = "";
		this.meterType3 = "";
		this.noDigit3 = "";
		this.payMode = "";
		this.payDate = "";
		this.secDeposit = "";
		this.smCharge = "";
		this.taxAmount = "";
		this.totalCost = "";
		this.tariffCode = "";
		this.kva = "";
		this.connectionType = "";
		this.phase = "";
		this.bankRef = "";
		this.neighboursAccNo = "";
		this.bankBranch = "";
		this.lastName = "";
		this.firstName = "";
		this.address = "";
		this.pivNo = "";
		this.pivDate = "";
		this.cusType = "";
		this.readerCode = "";
		this.packNo = "";
		this.walkSeq = "";
		this.oldAcct = "";
		this.street = "";
		this.suburb = "";
		this.city = "";
		this.postalCode = "";
		this.hid_loanCode= "";
		this.hid_loanDate= null;
		this.hid_noOfInstalment= "";
		this.hid_loanAmount= "";
		this.hid_instalmantAmount= "";
		this.SealNumber1= "";
		this.SealNumber2= "";
		this.SealNumber3= "";
		this.SealNumber4= "";
		this.SealNumber5= "";
		this.SealNumber6= "";
		this.SealNumber7= "";
		this.SealNumber8= "";
		this.SealNumber9= "";
		this.conDate=null;
	}
	
	

	private void setOrderCardData()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Spodrcrd spodrcrd =null;
		SpodrcrdPK spodrcrdPK = new SpodrcrdPK();
		
		spodrcrdPK.setDeptId(costCenterNo);
		spodrcrdPK.setProjectNo(jobNo);
		
		SpodrcrdEjb spodrcrdEjb = new SpodrcrdEjb(getSessionKey("region"));
		if(costCenterNo.equals("541.00") || costCenterNo.equals("541.10") || costCenterNo.equals("541.20") || costCenterNo.equals("542.00") || costCenterNo.equals("542.10") || costCenterNo.equals("542.20") || costCenterNo.equals("547.00")|| costCenterNo.equals("547.10")|| costCenterNo.equals("547.20") || costCenterNo.equals("548.00") || costCenterNo.equals("548.10") || costCenterNo.equals("548.20"))
			spodrcrd = spodrcrdEjb.findByJobNo(jobNo);
		else	
			spodrcrd = spodrcrdEjb.findById(spodrcrdPK);
			
		if(spodrcrd!=null){
			isNewOrderCard = null;
			
			if(spodrcrd.getAvgConsump()!=null)
				this.avgConsump = spodrcrd.getAvgConsump().toString();
			if(spodrcrd.getInitReading1()!=null)
				this.reading1 = spodrcrd.getInitReading1().toString();
			if(spodrcrd.getInitReading2()!=null)
				this.reading2 = spodrcrd.getInitReading2().toString();
			if(spodrcrd.getInitReading3()!=null)
				this.reading3 = spodrcrd.getInitReading3().toString();
			if(spodrcrd.getKvaRating()!=null)
				this.kva = spodrcrd.getKvaRating().toString();
			this.meterNo1 = spodrcrd.getMeterNo1();
			this.meterNo2 = spodrcrd.getMeterNo2();
			this.meterNo3 = spodrcrd.getMeterNo3();
			this.meterType1 = spodrcrd.getMeterType1();
			this.meterType2 = spodrcrd.getMeterType2();
			this.meterType3 = spodrcrd.getMeterType3();
			if(spodrcrd.getNoOfDigits1()!=null)
				this.noDigit1 = spodrcrd.getNoOfDigits1().toString();
			if(spodrcrd.getNoOfDigits2()!=null)
				this.noDigit2 = spodrcrd.getNoOfDigits2().toString();
			if(spodrcrd.getNoOfDigits3()!=null)
				this.noDigit3 = spodrcrd.getNoOfDigits3().toString();
			if(spodrcrd.getNoOfMeters()!=null)
				this.noOfMeters = spodrcrd.getNoOfMeters().toString();
			//this.oldAcct = spodrcrd.getOldAccNo();
			this.packNo = spodrcrd.getPackNo();
			this.readerCode = spodrcrd.getReaderCode();
			this.walkSeq = spodrcrd.getWalkSeq();
			if(spodrcrd.getConnectedDate()!=null)
				this.conDate =spodrcrd.getConnectedDate().toString();
			
		}else{
			
			isNewOrderCard = "true";
		}
	}
	
	//set the application data to the form fields
	private void setApplicantData(Application application)
	{
		ApplicantEjb applicantEJB = new ApplicantEjb(getSessionKey("region"));
		Applicant applicant = applicantEJB.findById(application.getIdNo());
		firstName = applicant.getFirstName();
		lastName = applicant.getLastName();

		
	}
	
	
	//set wiring details to the form fields
	private void setWiringData(Application application){
		WiringLandDetailEjb wlEJB = new WiringLandDetailEjb(getSessionKey("region"));
		WiringLandDetailPK wlPK = new WiringLandDetailPK();
		wlPK.setApplicationId(application.getId().getApplicationId());
		wlPK.setDeptId(costCenterNo);
		WiringLandDetail wld = null;
		
		if(costCenterNo.equals("541.00") || costCenterNo.equals("541.10") || costCenterNo.equals("541.20") || costCenterNo.equals("542.00") || costCenterNo.equals("542.10") || costCenterNo.equals("542.20") || costCenterNo.equals("547.00")|| costCenterNo.equals("547.10")|| costCenterNo.equals("547.20") || costCenterNo.equals("548.00") || costCenterNo.equals("548.10") || costCenterNo.equals("548.20"))
			wld = wlEJB.findByApplicationNo(application.getApplicationNo());			 
		else
			wld = wlEJB.findByAppId(wlPK);
		
		 
		neighboursAccNo = wld.getNeighboursAccNo();
		phase = Integer.toString(wld.getPhase().intValue());
		
		connectionType = wld.getConnectionType().toString()+" A";
		tariffCode = wld.getTariffCode();
		cusType = wld.getCustomerCategory();
		oldAcct=wld.getExistingAccNo();
		System.out.println(oldAcct);
			street =  wld.getServiceStreetAddress();
			suburb = wld.getServiceSuburb();
			city = wld.getServiceCity();
			if(wld.getServicePostalCode()!=null)
				postalCode = wld.getServicePostalCode().toString();
			if(street!=null && street.trim().length()>0)
				address = street;
			if(suburb!=null && suburb.trim().length()>0)
				address += "; "+suburb;
			if(city!=null && city.trim().length()>0)
				address += "; "+city;
			//DW
			//SET KIV ratings
			System.out.println(phase+" "+connectionType+" "+connectionType);
			System.out.println(phase.toString().equals("1")+" "+connectionType.toString().equals("30")+" "+connectionType.toString().equals("60"));
			if (phase.toString().equals("1")){
				setKva("7");
			}else if (phase.toString().equals("3")){
				if (connectionType.toString().equals("30 A")){
					setKva("21");
				}else if (connectionType.toString().equals("60 A")){
					setKva("42");
				}
			}
				
				
	}
	
	
	//Set loan details	
	private void setLoanDetails(Pcesthmt pcesthmt){
		SpapplonPK id=new SpapplonPK(); 
		SpapplonEjb spLoanejb=new SpapplonEjb(getSessionKey("region")); 
		id.setApplicationNo(pcesthmt.getId().getEstimateNo()); 			
		System.err.println(costCenterNo);
		id.setDeptId(costCenterNo); 
		//Spapplon spapplon=spLoanejb.findById(id);
		Spapplon spapplon=null;
		//Spapplon spapplon=spLoanejb.findById(id);
		if(costCenterNo.equals("541.00") || costCenterNo.equals("541.10") || costCenterNo.equals("541.20") || costCenterNo.equals("542.00") || costCenterNo.equals("542.10") || costCenterNo.equals("542.20") || costCenterNo.equals("547.00")|| costCenterNo.equals("547.10")|| costCenterNo.equals("547.20") || costCenterNo.equals("548.00") || costCenterNo.equals("548.10") || costCenterNo.equals("548.20"))
		{
			
			spapplon=spLoanejb.findByApplicationNo(pcesthmt.getId().getEstimateNo());
		}
		else
		{
			spapplon=spLoanejb.findById(id);
		}
		
		
		if(spapplon!=null){
			if(spapplon.getLoanReference()!=null)
				hid_loanCode =spapplon.getLoanReference();
			if(spapplon.getAddDate()!=null)
				hid_loanDate = spapplon.getAddDate();
			if(spapplon.getYears()!=null)
				hid_noOfInstalment =spapplon.getYears().toString();
			if(spapplon.getLoanAmount()!=null)
				hid_loanAmount =spapplon.getLoanAmount().toString();
			if(spapplon.getInstallment()!=null)
				hid_instalmantAmount =spapplon.getInstallment().toString();
		}
	}
	
	//set estimate details to the form fields
	private void setEstimateData(Pcesthmt pcesthmt)
	{
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		
		SpeststdPK speststdPK = new SpeststdPK();
		speststdPK.setDeptId(costCenterNo);
		speststdPK.setEstimateNo(pcesthmt.getId().getEstimateNo());
			
		SpeststdEjb speststdEJB = new SpeststdEjb(getSessionKey("region"));
		
		Speststd speststd = null;
		
		SpserestEjb restEjb=new SpserestEjb(getSessionKey("region"));
		SpserestPK spserestPK=new SpserestPK();
		spserestPK.setApplicationNo(pcesthmt.getId().getEstimateNo());
		spserestPK.setDeptId(costCenterNo);			
		//Spserest spserest = restEjb.findById(spserestPK);
		Spserest spserest =null;
		
		if(costCenterNo.equals("541.00") || costCenterNo.equals("541.10") || costCenterNo.equals("541.20") || costCenterNo.equals("542.00") || costCenterNo.equals("542.10") || costCenterNo.equals("542.20") || costCenterNo.equals("547.00")|| costCenterNo.equals("547.10")|| costCenterNo.equals("547.20") || costCenterNo.equals("548.00") || costCenterNo.equals("548.10") || costCenterNo.equals("548.20"))
		{
			
			spserest = restEjb.findByApplicationNo(pcesthmt.getId().getEstimateNo());
		}
		else
		{
			spserest = restEjb.findById(spserestPK);
		}
		
		
		
		
		if(costCenterNo.equals("541.00") || costCenterNo.equals("541.10") || costCenterNo.equals("541.20") || costCenterNo.equals("542.00") || costCenterNo.equals("542.10") || costCenterNo.equals("542.20") || costCenterNo.equals("547.00")|| costCenterNo.equals("547.10")|| costCenterNo.equals("547.20") || costCenterNo.equals("548.00") || costCenterNo.equals("548.10") || costCenterNo.equals("548.20"))
			 speststd = speststdEJB.findByEstimateNo(pcesthmt.getId().getEstimateNo());
		else
			speststd = speststdEJB.findById(speststdPK);
		if(spserest!=null){
			sinNo = spserest.getSin();
		}
		
		if(speststd!=null){
			//double convCost = 0;
			//if(speststd.getConversionCost()!=null)
			//	convCost = speststd.getConversionCost().doubleValue();
			//double fixedCost = speststd.getFixedCost().doubleValue();
			double secDepositDB = speststd.getSecurityDeposit().doubleValue();
			//double variableCost = speststd.getVariableCost().doubleValue();
			//double otherCost = speststd.getOtherCost().doubleValue();
			
			double totalCostDB = speststd.getTotalCost().doubleValue();
			double sub = totalCostDB-secDepositDB;
			
			taxAmount = "0.00";
			secDeposit = nf.format(secDepositDB);
			totalCost = nf.format(totalCostDB);
			smCharge = nf.format(sub);
		}
		
		
	}
	
	private void setPIVData(String estNo,Application application)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		PivDetailEjb PivDetailEjb =  new PivDetailEjb(getSessionKey("region"));
		PivDetail piv =null;		
		if(application!=null && application.getIsLoanApp()!=null && application.getIsLoanApp().equals("Y"))
		{
			//piv = PivDetailEjb.findByReferenceNo(costCenterNo,estNo, "ELN"); 
			piv = PivDetailEjb.findByReferenceNo(estNo, "ELN");
			payDate = sdf.format(piv.getConfirmedDate());
			bankBranch = piv.getPosA()+"/"+piv.getPosCenter();
		}
		else
		{
			 piv = PivDetailEjb.findByReferenceNo(estNo, "EST");
			 payDate = sdf.format(piv.getPaidDate());
			 bankBranch = piv.getBankCode()+"/"+piv.getBranchCode();
		}
		
    	
        bankRef = piv.getId().getPivNo();
        pivNo = piv.getId().getPivNo();
        payMode = piv.getPaymentMode();
        
        pivDate = sdf.format(piv.getPivDate());
        
	}
	
	
	public String nCjobFinishDirect (){
		try {
			setLoggedData();
			setState("nCjobFinishDirect");
			SpestcndEjb spejb=new SpestcndEjb(getSessionKey("region"));
		 	//List<Spestcnd> jobList=spejb.getJobListByType(costCenterNo,JobProcessStatus.ALLOCATED,ApplicationType.NEW_CONN);
			//List<String> jobList2=spejb.getJobNoListByType(costCenterNo,JobProcessStatus.ALLOCATED,getSessionKey("smcType"));
			List<String> jobList=spejb.getJobNoListBySubType(costCenterNo,JobProcessStatus.ALLOCATED,getSessionKey("smcType"));
		 	System.out.println(costCenterNo+"jobList "+jobList.size());
			setListjobNo(jobList);
		 	return SUCCESS;
		}catch (Exception e) {
			setListjobNo(null);
			setErrorMessage(e.getMessage());
			return ERROR;
		}
	}

	public String cRjobFinishDirect (){
		try {
			setLoggedData();
			setState("cRjobFinishDirect");
			SpestcndEjb spejb=new SpestcndEjb(getSessionKey("region"));
		 	//List<Spestcnd> jobList=spejb.getJobListByType(costCenterNo,JobProcessStatus.ALLOCATED,ApplicationType.NEW_CONN);
			//List<String> jobList2=spejb.getJobNoListByType(costCenterNo,JobProcessStatus.ALLOCATED,getSessionKey("smcType"));
		 	List<String> jobList=spejb.getJobNoListBySubType(costCenterNo,JobProcessStatus.ALLOCATED,getSessionKey("smcType"));
		 	//System.out.println("================================= "+costCenterNo+"jobList "+jobList);
		 	System.out.println(costCenterNo+"jobList "+jobList.size());
		 	setListjobNo(jobList);
		 	return SUCCESS;
		}catch (Exception e) {
			setListjobNo(null);
			setErrorMessage(e.getMessage());
			return ERROR;
		}
	}
	
	public String cRjobFinish(){
		try {
			
			setState("cRjobFinishDirect");
			loggedUser = getSession().get("userName").toString();
			setLoggedData();
			setPath(viewPath);
			saveOrderCard();
			//save();
			energize();
			SpestcndEjb spejb=new SpestcndEjb(getSessionKey("region"));
		 	List<String> jobList=spejb.getJobNoListBySubType(costCenterNo,JobProcessStatus.ALLOCATED,getSessionKey("smcType"));
		 	setListjobNo(jobList);
		 	setSuccessMessage("Job has been finished successfully.");
		 	return SUCCESS;
		}catch (Exception e) {
			
			setErrorMessage("Error has occured. "+e.getMessage());
			return ERROR;
		}
	}
	
	public String nCjobFinish(){
		try {
			
			setState("nCjobFinishDirect");
			loggedUser = getSession().get("userName").toString();
			setLoggedData();
			setPath(viewPath);
			saveOrderCard();
			//save();
			energize();
			SpestcndEjb spejb=new SpestcndEjb(getSessionKey("region"));
		 	List<String> jobList=spejb.getJobNoListBySubType(costCenterNo,JobProcessStatus.ALLOCATED,getSessionKey("smcType"));
		 	setListjobNo(jobList);
		 	setSuccessMessage("Job has been finished successfully.");
		 	return SUCCESS;
		}catch (Exception e) {
			
			setErrorMessage("Error has occured. "+e.getMessage());
			return ERROR;
		}
	}
	
	public String smcCardDirect(){
		
		setLoggedData();
		Format format=new Format();
		if (getSessionKey("smcType").equals("NC")){
			jobType="SMC";
		}else if (getSessionKey("smcType").equals("CR")){
			jobType="CRJ";
		}else {
			jobType="XXX";
		}
		jobNo =getSessionKey("costCenterNo")+"/"+jobType+"/"+format.getYear().substring(2, format.getYear().length())+"/";
		setState("smcCardDirect");
		//SpestcndEjb spejb=new SpestcndEjb(getSessionKey("region"));
	 	//List<String> jobList=spejb.getJobNoListBySubType(costCenterNo,JobProcessStatus.ALLOCATED,getSessionKey("smcType"));
	 	//setListjobNo(jobList);
		return SUCCESS;
	}
	
	
	public void setLoggedData() 
	{
		log.info( getSession());
		setLoggedInUserId(getSessionKey("userName"));
		setCostCenterNo(getSessionKey("costCenterNo"));
		setCostCenterName(getSessionKey("costCenterName"));
		hid_CostCenter = getSessionKey("costCenterNo");
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
	
	public String getNeighboursAccNo() {
		return neighboursAccNo;
	}
	
	public void setNeighboursAccNo(String neighboursAccNo) {
		this.neighboursAccNo = neighboursAccNo;
	}
	
	public String getTariffCode() {
		return tariffCode;
	}
	
	public void setTariffCode(String tariffCode) {
		this.tariffCode = tariffCode;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getIsViewJob() {
		return isViewJob;
	}

	public void setIsViewJob(String isViewJob) {
		this.isViewJob = isViewJob;
	}

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSinNo() {
		return sinNo;
	}

	public void setSinNo(String sinNo) {
		this.sinNo = sinNo;
	}

	public String getCostCode() {
		return costCode;
	}

	public void setCostCode(String costCode) {
		this.costCode = costCode;
	}

	public String getEcsc() {
		return ecsc;
	}

	public void setEcsc(String ecsc) {
		this.ecsc = ecsc;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSerialJobNo() {
		return serialJobNo;
	}

	public void setSerialJobNo(String serialJobNo) {
		this.serialJobNo = serialJobNo;
	}

	public String getNoOfMeters() {
		return noOfMeters;
	}

	public void setNoOfMeters(String noOfMeters) {
		this.noOfMeters = noOfMeters;
	}

	public String getAvgConsump() {
		return avgConsump;
	}

	public void setAvgConsump(String avgConsump) {
		this.avgConsump = avgConsump;
	}

	public String getMeterNo1() {
		return meterNo1;
	}

	public void setMeterNo1(String meterNo1) {
		this.meterNo1 = meterNo1;
	}

	public String getReading1() {
		return reading1;
	}

	public void setReading1(String reading1) {
		this.reading1 = reading1;
	}

	public String getMeterType1() {
		return meterType1;
	}

	public void setMeterType1(String meterType1) {
		this.meterType1 = meterType1;
	}

	public String getNoDigit1() {
		return noDigit1;
	}

	public void setNoDigit1(String noDigit1) {
		this.noDigit1 = noDigit1;
	}

	public String getMeterNo2() {
		return meterNo2;
	}

	public void setMeterNo2(String meterNo2) {
		this.meterNo2 = meterNo2;
	}

	public String getReading2() {
		return reading2;
	}

	public void setReading2(String reading2) {
		this.reading2 = reading2;
	}

	public String getMeterType2() {
		return meterType2;
	}

	public void setMeterType2(String meterType2) {
		this.meterType2 = meterType2;
	}

	public String getNoDigit2() {
		return noDigit2;
	}

	public void setNoDigit2(String noDigit2) {
		this.noDigit2 = noDigit2;
	}

	public String getMeterNo3() {
		return meterNo3;
	}

	public void setMeterNo3(String meterNo3) {
		this.meterNo3 = meterNo3;
	}

	public String getReading3() {
		return reading3;
	}

	public void setReading3(String reading3) {
		this.reading3 = reading3;
	}

	public String getMeterType3() {
		return meterType3;
	}

	public void setMeterType3(String meterType3) {
		this.meterType3 = meterType3;
	}

	public String getNoDigit3() {
		return noDigit3;
	}

	public void setNoDigit3(String noDigit3) {
		this.noDigit3 = noDigit3;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public String getSmCharge() {
		return smCharge;
	}

	public void setSmCharge(String smCharge) {
		this.smCharge = smCharge;
	}

	public String getKva() {
		return kva;
	}

	public void setKva(String kva) {
		this.kva = kva;
	}

	public String getBankRef() {
		return bankRef;
	}

	public void setBankRef(String bankRef) {
		this.bankRef = bankRef;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public String getPivNo() {
		return pivNo;
	}

	public void setPivNo(String pivNo) {
		this.pivNo = pivNo;
	}

	public String getPivDate() {
		return pivDate;
	}

	public void setPivDate(String pivDate) {
		this.pivDate = pivDate;
	}

	public String getCusType() {
		return cusType;
	}

	public void setCusType(String cusType) {
		this.cusType = cusType;
	}

	public String getReaderCode() {
		return readerCode;
	}

	public void setReaderCode(String readerCode) {
		this.readerCode = readerCode;
	}

	public String getPackNo() {
		return packNo;
	}

	public void setPackNo(String packNo) {
		this.packNo = packNo;
	}

	public String getWalkSeq() {
		return walkSeq;
	}

	public void setWalkSeq(String walkSeq) {
		this.walkSeq = walkSeq;
	}

	public String getOldAcct() {
		return oldAcct;
	}

	public void setOldAcct(String oldAcct) {
		this.oldAcct = oldAcct;
	}

	public String getSmcType() {
		return smcType;
	}

	public void setSmcType(String smcType) {
		this.smcType = smcType;
	}

	public String getConDate() {
		return conDate;
	}

	public void setConDate(String conDate) {
		this.conDate = conDate;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getIsNewOrderCard() {
		return isNewOrderCard;
	}

	public void setIsNewOrderCard(String isNewOrderCard) {
		this.isNewOrderCard = isNewOrderCard;
	}

	public String getJobCostCenterNo() {
		return jobCostCenterNo;
	}

	public void setJobCostCenterNo(String jobCostCenterNo) {
		this.jobCostCenterNo = jobCostCenterNo;
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
	
	public String getHid_loanCode() {
		return hid_loanCode;
	}

	public void setHid_loanCode(String hid_loanCode) {
		this.hid_loanCode = hid_loanCode;
	}

	public Date getHid_loanDate() {
		return hid_loanDate;
	}

	public void setHid_loanDate(Date hid_loanDate) {
		this.hid_loanDate = hid_loanDate;
	}

	public String getHid_noOfInstalment() {
		return hid_noOfInstalment;
	}

	public void setHid_noOfInstalment(String hid_noOfInstalment) {
		this.hid_noOfInstalment = hid_noOfInstalment;
	}

	public String getHid_loanAmount() {
		return hid_loanAmount;
	}

	public void setHid_loanAmount(String hid_loanAmount) {
		this.hid_loanAmount = hid_loanAmount;
	}

	public String getHid_instalmantAmount() {
		return hid_instalmantAmount;
	}

	public void setHid_instalmantAmount(String hid_instalmantAmount) {
		this.hid_instalmantAmount = hid_instalmantAmount;
	}

	public String getHid_CostCenter() {
		return hid_CostCenter;
	}

	public void setHid_CostCenter(String hid_CostCenter) {
		this.hid_CostCenter = hid_CostCenter;
	}

	
	public String getHid_EstimateNo() {
		return hid_EstimateNo;
	}

	public void setHid_EstimateNo(String hid_EstimateNo) {
		this.hid_EstimateNo = hid_EstimateNo;
	}


	public String getSealNumber8() {
		return SealNumber8;
	}

	public void setSealNumber8(String sealNumber8) {
		SealNumber8 = sealNumber8;
	}

	public String getSealNumber9() {
		return SealNumber9;
	}

	public void setSealNumber9(String sealNumber9) {
		SealNumber9 = sealNumber9;
	}

	public Date getEnergizeDate() {
		return energizeDate;
	}

	public void setEnergizeDate(Date energizeDate) {
		this.energizeDate = energizeDate;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public String getSealNumber1() {
		return SealNumber1;
	}

	public void setSealNumber1(String sealNumber1) {
		SealNumber1 = sealNumber1;
	}

	public String getSealNumber2() {
		return SealNumber2;
	}

	public void setSealNumber2(String sealNumber2) {
		SealNumber2 = sealNumber2;
	}

	public String getSealNumber3() {
		return SealNumber3;
	}

	public void setSealNumber3(String sealNumber3) {
		SealNumber3 = sealNumber3;
	}

	public String getSealNumber4() {
		return SealNumber4;
	}

	public void setSealNumber4(String sealNumber4) {
		SealNumber4 = sealNumber4;
	}

	public String getSealNumber5() {
		return SealNumber5;
	}

	public void setSealNumber5(String sealNumber5) {
		SealNumber5 = sealNumber5;
	}

	public String getSealNumber6() {
		return SealNumber6;
	}

	public void setSealNumber6(String sealNumber6) {
		SealNumber6 = sealNumber6;
	}

	public String getSealNumber7() {
		return SealNumber7;
	}

	public void setSealNumber7(String sealNumber7) {
		SealNumber7 = sealNumber7;
	}

	public String getContractorId() {
		return contractorId;
	}

	public void setContractorId(String contractorId) {
		this.contractorId = contractorId;
	}
	
	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public  List<String> getListjobNo() {
		return listjobNo;
	}

	public void setListjobNo( List<String> listjobNo) {
		this.listjobNo = listjobNo;
	}
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
}//End of class
