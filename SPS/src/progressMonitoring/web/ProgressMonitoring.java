package progressMonitoring.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;
import java.util.Map.Entry;

import masters.model.Bank;
import masters.service.BankEjb;

import org.apache.commons.httpclient.NoHttpResponseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import org.jboss.aspects.security.Unchecked;

import piv.model.PivDetail;
import piv.model.PivDetailPK;
import piv.model.TempTb;
import piv.service.PivDetailEjb;

//import progressMonitoring.service.PcinitialdataEjb;
import reports.web.report;

import security.service.SecurityEjb;

import util.common.ApplicationStatus;

import util.common.EstimateStatus;
import util.common.Format;
import util.common.Path;
import util.common.PivPrefixType;
import util.common.StandardEstimateStatus;

import progressMonitoring.model.Pcinitialdata;
import progressMonitoring.model.PcinitialdataPK;
import progressMonitoring.model.Pcmiledates;
import progressMonitoring.model.Pcmilem;
import progressMonitoring.model.Pcmilesumary;
import progressMonitoring.service.PcinitialdataEjb;
import progressMonitoring.service.PcmiledatesEjb;
import progressMonitoring.service.PcmilemEjb;
import progressMonitoring.service.PcmilesumaryEjb;


import application.model.Applicant;
import application.model.Application;
import application.model.WiringLandDetail;
import application.model.WiringLandDetailCon;
import application.model.WiringLandDetailPK;
import application.service.ApplicationEjb;
import application.service.ApplicationReferenceEjb;
import application.service.ApplicationTransactionEjb;
import application.service.WiringLandDetailConEjb;
import application.service.WiringLandDetailEjb;
import application.web.Message;
import applicationBS.web.NewApplication;
import bsh.Console;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.bcel.internal.generic.RET;
import com.sun.tools.ws.processor.model.Request;

import costcenter.service.CostCenterEjb;
import estimate.model.EstimateReference;
import estimate.model.Pcesthtt;
import estimate.model.Spestedy;
import estimate.model.SpestedyCons;
import estimate.model.Spstdesthmt;
import estimate.model.SpstdesthmtPK;
import estimate.service.EstimateReferenceEjb;
import estimate.service.PcesthttEjb;
import estimate.service.SpestedyConEjb;
import estimate.service.SpestedyEjb;
import estimate.service.SpstdesthmtEjb;

import javassist.expr.NewArray;
import javassist.runtime.Desc;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;

import job.model.JobInfo;
import job.model.Pcesthmt;
import job.model.Spestcnd;
import job.model.Spestcnt;
import job.model.SpestcntPK;
import job.model.Spexpjob;
import job.model.Spodrcrd;
import job.model.SpodrcrdPK;
import job.service.ExportEjb;
import job.service.JobEjb;
import job.service.PcesthmtEjb;
import job.service.SpestcndEjb;
import job.service.SpestcntEjb;
import job.service.SpodrcrdEjb;

import util.common.Constants;

@SuppressWarnings({ "serial", "unused" })
public class ProgressMonitoring extends ActionSupport implements SessionAware,ParameterAware {
	private String region;
	private String costCenterNo;
	private String errorMessage;

	private String path;
	private String loggedInUserLevel;
	private String loggedInCostCenterNo;

	private String jobAssDate;//JobAssignDate--have to create getters and setters
	private String targetDateDis;
	private String targetDate;//targetDate
	private String jobnoReceDate;//job Number reveive date--have to create getters and setters
	
	private String jobAlloDate;
	private String codeNumber;
	private String projectLetterReceivedOnDate;
	private String proLtrToEsOnDate;
	private String estReceivedOnDate;
	private String estimateSntToDGMOnDate;
	private String estimatedDate;
	private String esAllocatedDate;
	private List<Pcinitialdata> pcinitiladataList;
	private ArrayList<String> listuserName = new ArrayList<String>();
	private List<String> listConref = new ArrayList<String>();
	private List<String> listJoblist = new ArrayList<String>();
	private ArrayList<String> listContractorsList  = new ArrayList<String>();
	private List<String> allJoblist = new ArrayList<String>();
	private Map <String, String[]> parameters;
	private static final String MSG_ERROR="ERROR";
	private static final String MSG_INFO="INFO";
	private static final String MSG_DONE="DONE";

	private static final String MSG_NONE="NONE";
	private Map<String, Object> session;
	private String messageType=MSG_NONE;

	
	private static final String newPath = "Progress Monitoring>Add Progress";
	private static final String modifyPath = "Customer>Modify Progress";

	List<String> errorMessages = new ArrayList<String>();
	NumberFormat nf = NumberFormat.getInstance();
	public String loggedInUserId;
	public Object revisedJobsList;
	public String Cscname;
	public ArrayList<Pcinitialdata> progressList  ;
	private static final Log log = LogFactory.getLog(NewApplication.class);

	// fields
	public String FileNotxfd;//not used in code???
	public String success;
	public String fundId;//FundId
	public String esttBy;//estBy
	public String areaCode;//areaCode
	public String conBy;//conBy
	public String supBy;//supBy
	public String costCenter;
	public String refNo;//RefNumber
	public String projectNo;//ProjectNumber
	public String fileNo;//fileNumber
	public String decrp;//description
	public String devSec;//devSec
	public String district;//devSec
	public String csc;//devSec
	public String electorate;//devSec
	public String conName;
	public String ht33;//ht33
	public String abc4w;//abc4w
	public String lt3Phase;//lt3phase
	public String abc5w;//abc5w
	public String HT11;
	public String lt11;//lt11
	public String ltsPhase;//stspace
	public String sub33;//sub33
	public String abcsecct;//abcsecct
	public String sub11;//sub11
	public String subCapacity;//subCapacity
	public String detailCost;
	public String varianceNew;
	public String hiddentableRadioButtonValue;
	public String augSub;
	

	public String stdCost;
	public String perCapacity;
	public String newCapacity;
	
	public String combineRun;
	public String lt13;
	public String lt23;
	public String noPoleShifted;
	public String no_ht_pole;
	public String no_lt_pole;
    public String profundaut;
    public String noPoleTobeShifted;
	// public String FileNotxfd;
	private String lblSuccess = null;
	private String lblError = null;
	private String ganDaysRequired;
	private String feedername;
	
	

	//variables for table and data fields

	public List<String> jobNumberList = new ArrayList<String>(); ;
	public List<String> milestoneList = new ArrayList<String>();
	

	private String state;
	private String state1;
	public String getState1() {
		return state1;
	}
	public void setCodeNumber(String codeNumber) {
		this.codeNumber = codeNumber;
	}

	public String getCodeNumber() {
		return codeNumber;
	}
	
	public void setJobAlloDate(String jobAlloDate) {
		this.jobAlloDate = jobAlloDate;
	}

	public String getJobAlloDate() {
		return jobAlloDate;
	}
	
	public void setFeedername(String feederna) {
		this.feedername = feederna;
	}

	public String getFeedername() {
		return feedername;
	}

	public void setState1(String state1) {
		this.state1 = state1;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	private String txtSearchValue;
	String idSearchType="";
	private String estimateNo;
	private String applicationId;
	
	private String categoryId;
	private String fundsou;
	Format format=new Format();
	public String getAugSub() {
		return augSub;
	}

	public void setAugSub(String augSub) {
		this.augSub = augSub;
	}
	public String getTxtSearchValue() {
		return txtSearchValue;
	}

	public void setTxtSearchValue(String txtSearchValue) {
		this.txtSearchValue = txtSearchValue;
	}

	public String getHiddentableRadioButtonValue() {
		return hiddentableRadioButtonValue;
	}

	public void setHiddentableRadioButtonValue(String hiddentableRadioButtonValue) {
		this.hiddentableRadioButtonValue = hiddentableRadioButtonValue;
	}

	public String getIdSearchType() {
		return idSearchType;
	}

	public void setIdSearchType(String idSearchType) {
		this.idSearchType = idSearchType;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}
	private void loadUserList(String region,String costCenterNo){
		List<String> usersList = new ArrayList<String>();
		usersList.add("ES");
		usersList.add("EE");
		
		SecurityEjb secejb=new SecurityEjb(region);
		List<String> userList = secejb.getUserList(costCenterNo,usersList);
		//DW
		
		
		Iterator<String> usit = userList.iterator();
		
		listuserName = new ArrayList<String>();
		while (usit.hasNext()) {        	 
	        	String esUser=usit.next();
	        	listuserName.add(esUser);		       	        	        	
		}
		
		SpestcntEjb spestcntEjb=new SpestcntEjb(region);
		List<Spestcnt> cnts = spestcntEjb.getAll(costCenterNo);
		//DW
		
		
		Iterator<Spestcnt> cntsIt = cnts.iterator();
		
		listContractorsList = new ArrayList<String>();
		
		while (cntsIt.hasNext()) {        	 
				Spestcnt esUser=cntsIt.next();
				System.out.println("esUser : " + esUser.getContractorName());
				
				listContractorsList.add(esUser.getContractorName());		       	        	        	
		}
		
		
	}
	
	private void loadConRef(String region,String costCenterNo){
		
		
		PcesthttEjb pcEjb = new PcesthttEjb(region);
		PcesthmtEjb peEjb = new PcesthmtEjb(region);
		
		
		listConref = pcEjb.getEstimateNoList(costCenterNo);
		
		
		List<Long> statuses = new ArrayList<Long>();
		statuses.add(new Long(EstimateStatus.JOB_ONGOING));
		statuses.add(new Long(EstimateStatus.JOB_SOFT_CLOSED));
		statuses.add(new Long(EstimateStatus.JOB_SOFT_CLOSED));
		statuses.add(new Long(EstimateStatus.JOB_RIVISION));
		statuses.add(new Long(EstimateStatus.JOB_APPROVAL_CE));
		listJoblist = peEjb.findEstNosList(costCenterNo, statuses);
		
	}
	public String searchJob(){
		setLoggedData();
		setState1("save");
		PcinitialdataEjb pcinEjb=new PcinitialdataEjb(getSessionKey("region"));
		pcinitiladataList = new ArrayList<Pcinitialdata>();
		if(txtSearchValue != null && txtSearchValue.length() != 0){
			/*if(idSearchType.equalsIgnoreCase("comRef"))
			{
				pcinitiladataList=pcinEjb.getDataForEstNumber(txtSearchValue,"Commercial");
				System.out.println(idSearchType);
			}
			else if(idSearchType.equalsIgnoreCase("constructRef"))
			{
				
				pcinitiladataList=pcinEjb.getDataForEstNumber(txtSearchValue,"constructRef");
			}
			else if(idSearchType.equalsIgnoreCase("projJobNo"))
			{
				
				pcinitiladataList=pcinEjb.getDataForEstNumber(txtSearchValue,"projJobNo");
			}*/
			
			pcinitiladataList=pcinEjb.getDataForEstNumber(txtSearchValue.trim(),"constructRef");
			
			
		}
		
		System.out.println(" pcinitiladataList :  " + pcinitiladataList );
		
		PcesthttEjb pc = new PcesthttEjb(getSessionKey("region"));
		PcesthmtEjb pchmte = new PcesthmtEjb(getSessionKey("region"));
		Pcesthtt pchtt =  pc.findByEstimationNo(txtSearchValue.trim(), costCenterNo,Constants.REV_NO);
		Pcesthmt pchmt = null;
		String estimateNo = null;
		BigDecimal detailCost = new BigDecimal("0");
		String fundSource = null;
		String projectNo = null;
		String applicationNo = null;
		if(pchtt != null){
			System.out.println(" setDecrp 1:  " );
			estimateNo = pchtt.getId().getEstimateNo();
			fundSource = pchtt.getFundSource();
			detailCost = pchtt.getStdCost();
			setDecrp(pchtt.getDescr());
		}else{
			pchmt =  pchmte.findByEstimationNo(txtSearchValue.trim(), costCenterNo);
			if(pchmt != null){
				System.out.println(" setDecrp 2:  " );
				estimateNo=pchmt.getId().getEstimateNo();
				fundSource = pchmt.getFundSource();
				detailCost = pchmt.getStdCost();
				projectNo = pchmt.getProjectNo();
				setDecrp(pchmt.getDescr());
			}
		}
		System.out.println(" estimateNo :  " + estimateNo );

		if(estimateNo != null){
			setFileNo(estimateNo);
			
			EstimateReferenceEjb estejb = new EstimateReferenceEjb();
			EstimateReference refe = estejb.findByWorkEstimateNo(txtSearchValue.trim(),costCenterNo, getSessionKey("region"));
			ApplicationEjb appejb = new ApplicationEjb(getSessionKey("region"));
			Application appli = appejb.findByApplicationNo(txtSearchValue.trim(),costCenterNo);
			
			System.out.println(" refe :  " + refe );
			
			if(detailCost != null){
				setDetailCost(detailCost.toString());
			}
			if(pchmt != null){
				setprojectNo(pchmt.getProjectNo());
				if(pchmt.getPrjAssDt() != null){
				  setJobAssDate(pchmt.getPrjAssDt().toString());
				}
				
			}
			String stdNo = "";
			if(refe != null){
				stdNo = refe.getId().getStandardEstimateNo();
				applicationNo = refe.getId().getStandardEstimateNo();
				setRefNo(applicationNo);
			}else if(appli != null){
				applicationNo = appli.getApplicationNo();
			}else{
				applicationNo = txtSearchValue.trim();
			}
			setDecrp(getDecrp());
			WiringLandDetailCon schemaDetails = null;
			WiringLandDetailConEjb wiringLandDetailConEjb = new WiringLandDetailConEjb(getSessionKey("region"));
			schemaDetails = wiringLandDetailConEjb.findByApplicationNo(applicationNo);
			if(schemaDetails != null){
				setAreaCode(schemaDetails.getAreaCode());
				setDevSec(schemaDetails.getDevSec());
				setFundId(fundSource);
				setFundsou(fundSource);
				setDistrict(schemaDetails.getDistrict());
				setElectorate(schemaDetails.getElectorate());
				setCsc(schemaDetails.getServiceDepoName());
			}
			// site visiting details
			SpestedyConEjb ejb_spestedy = new SpestedyConEjb(getSessionKey("region"));
			SpestedyCons spestedy = null;
			if(stdNo!= null && estimateNo != null){
			    spestedy = ejb_spestedy.getAppointmentByEstimateNo( stdNo.trim(),estimateNo.trim(),costCenterNo);
			}
			
			
			if (spestedy != null)
			{				
				if(spestedy.getAllocatedDate() != null){
					
					setEsAllocatedDate(spestedy.getAllocatedDate().toString());
				}
				
				setEsttBy(spestedy.getAllocatedTo());

			}
			

			SpestcndEjb ejb_spestcnd = new SpestcndEjb(getSessionKey("region"));
			if(projectNo != null){
				List<Spestcnd> spestcnd = ejb_spestcnd.findByJobNo(projectNo.trim(), costCenterNo);
				
				if (spestcnd != null && spestcnd.size() > 0){
					Spestcnd spestcndx = spestcnd.get(0);
					

					SpestcntEjb ejb_spestcnt = new  SpestcntEjb(getSessionKey("region"));
					SpestcntPK spestcntPK = new SpestcntPK();
					spestcntPK.setDeptId(costCenterNo);
					spestcntPK.setContractorId(spestcndx.getId().getContractorId());
					Spestcnt spestcnt = ejb_spestcnt.findById(spestcntPK); 
					
					setConBy(spestcnt.getCode());
					setConName(spestcnt.getContractorName());
					setSupBy(spestcndx.getAllocatedUser());
					//gayani
					System.out.println(" spestcndx.getAllocatedDate() :  " + spestcndx.getAllocatedDate() );
					if(spestcndx.getAllocatedDate()!=null){
						setJobAlloDate(spestcndx.getAllocatedDate().toString());
					}
					

				}
			}
			if(refe != null){
				
				String commCost = costCenterNo.substring(0, 3);
				SpstdesthmtEjb spejb = new SpstdesthmtEjb(getSessionKey("region"));
				SpstdesthmtPK spstdesthmtPK = new SpstdesthmtPK();
				spstdesthmtPK.setApplicationNo(refe.getId().getStandardEstimateNo());
				spstdesthmtPK.setDeptId(commCost+".00");
				spstdesthmtPK.setStdNo(refe.getId().getStandardEstimateNo());
				spstdesthmtPK.setApplicationNo(refe.getId().getStandardEstimateNo());
				
				Spstdesthmt hmt = spejb.findById(spstdesthmtPK, getSessionKey("region"));
				
				if(hmt != null){
					if(hmt.getTotalCost() != null){
						setStdCost(hmt.getTotalCost().toString());
					}
					NumberFormat nf = NumberFormat.getInstance();
					nf.setGroupingUsed(true);
					nf.setMaximumFractionDigits(2);
					nf.setMinimumFractionDigits(2);
					double deuction = 0.0;
					if(hmt.getRebateCost() != null){
						deuction = hmt.getRebateCost().doubleValue()+hmt.getTotalCost().doubleValue();
					}else{
						deuction = hmt.getTotalCost().doubleValue();
					}
					//double deuction = hmt.getRebateCost().doubleValue()+hmt.getTotalCost().doubleValue();
					double workECost = 0.0;
					if(detailCost != null){
						workECost = detailCost.doubleValue();
					}
					double variance = 0;
					if(hmt.getTotalCost() != null && hmt.getTotalCost().doubleValue()!= 0.0){
						variance = ((workECost - deuction)/hmt.getTotalCost().doubleValue())*100;
						setVarianceNew(String.valueOf(variance));
					}
					
					setRefNo(hmt.getId().getStdNo());
					
				//PcesthmtEjb pchmte = new PcesthmtEjb(getSessionKey("region"));
				//JobEjb ejb = new JobEjb(sessionKey);
				//Pcesthmt pchmt =  pchmte.findByEstimationNo(txtSearchValue.trim(), costCenterNo);
				
			
					
				}
				
				

			}
			
				
			}
		/*PcesthttEjb pc = new PcesthttEjb(getSessionKey("region"));
		Pcesthtt pchtt =  pc.findByEstimationNo(txtSearchValue.trim(), costCenterNo,Constants.REV_NO);
		if(pchtt != null){
			setFileNo(pchtt.getId().getEstimateNo());
			
			EstimateReferenceEjb estejb = new EstimateReferenceEjb();
			EstimateReference refe = estejb.findByWorkEstimateNo(txtSearchValue.trim(),costCenterNo, getSessionKey("region"));
			if(refe != null){
				//setRefNo(refe.getId().getStandardEstimateNo());
				if(pchtt.getStdCost() != null){
					setDetailCost(pchtt.getStdCost().toString());
				}
				
				
			
				String commCost = costCenterNo.substring(0, 3);
				SpstdesthmtEjb spejb = new SpstdesthmtEjb(getSessionKey("region"));
				SpstdesthmtPK spstdesthmtPK = new SpstdesthmtPK();
				spstdesthmtPK.setApplicationNo(refe.getId().getStandardEstimateNo());
				spstdesthmtPK.setDeptId(commCost+".00");
				spstdesthmtPK.setStdNo(refe.getId().getStandardEstimateNo());
				spstdesthmtPK.setApplicationNo(refe.getId().getStandardEstimateNo());
				Spstdesthmt hmt = spejb.findById(spstdesthmtPK, getSessionKey("region"));
				if(hmt != null){
					if(hmt.getTotalCost() != null){
						setStdCost(hmt.getTotalCost().toString());
					}
					NumberFormat nf = NumberFormat.getInstance();
					nf.setGroupingUsed(true);
					nf.setMaximumFractionDigits(2);
					nf.setMinimumFractionDigits(2);
					double deuction = 0.0;
					if(hmt.getRebateCost() != null){
						deuction = hmt.getRebateCost().doubleValue()+hmt.getTotalCost().doubleValue();
					}else{
						deuction = hmt.getTotalCost().doubleValue();
					}
					//double deuction = hmt.getRebateCost().doubleValue()+hmt.getTotalCost().doubleValue();
					double workECost = 0.0;
					if(pchtt.getStdCost() != null){
						workECost = pchtt.getStdCost().doubleValue();
					}
					double variance = 0;
					if(hmt.getTotalCost() != null && hmt.getTotalCost().doubleValue()!= 0.0){
						variance = ((workECost - deuction)/hmt.getTotalCost().doubleValue())*100;
						setVarianceNew(String.valueOf(variance));
					}
					setRefNo(hmt.getId().getStdNo());
				PcesthmtEjb pchmte = new PcesthmtEjb(getSessionKey("region"));
				//JobEjb ejb = new JobEjb(sessionKey);
				Pcesthmt pchmt =  pchmte.findByEstimationNo(txtSearchValue.trim(), costCenterNo);
				if(pchmt != null){
					setprojectNo(pchmt.getProjectNo());
					if(pchmt.getPrjAssDt() != null){
					  setJobAssDate(pchmt.getPrjAssDt().toString());
					}
					
				}
				setDecrp(getDecrp());
				WiringLandDetailCon schemaDetails = null;
				WiringLandDetailConEjb wiringLandDetailConEjb = new WiringLandDetailConEjb(getSessionKey("region"));
				schemaDetails = wiringLandDetailConEjb.findByApplicationNo(refe.getId().getStandardEstimateNo());
				if(schemaDetails != null){
					setAreaCode(schemaDetails.getAreaCode());
					setDevSec(schemaDetails.getDevSec());
					setFundId(pchtt.getFundSource());
					setDistrict(schemaDetails.getDistrict());
					setElectorate(schemaDetails.getElectorate());
					setCsc(schemaDetails.getServiceDepoName());
				}
				// site visiting details
				SpestedyConEjb ejb_spestedy = new SpestedyConEjb(getSessionKey("region"));
				SpestedyCons spestedy = ejb_spestedy.getAppointment(costCenterNo, refe.getId().getStandardEstimateNo());
				if (spestedy != null)
				{				
					if(spestedy.getAllocatedDate() != null){
						setEsAllocatedDate(spestedy.getAllocatedDate().toString());
					}
					setEstBy(spestedy.getAllocatedTo());

				}
				

				SpestcndEjb ejb_spestcnd = new SpestcndEjb(getSessionKey("region"));
					if(pchmt != null){
						List<Spestcnd> spestcnd = ejb_spestcnd.findByJobNo(pchmt.getProjectNo().trim(), costCenterNo);
						if (spestcnd != null && spestcnd.size() > 0)
						{
							Spestcnd spestcndx = spestcnd.get(0);
							
		
							SpestcntEjb ejb_spestcnt = new  SpestcntEjb(getSessionKey("region"));
							SpestcntPK spestcntPK = new SpestcntPK();
							spestcntPK.setDeptId(costCenterNo);
							spestcntPK.setContractorId(spestcndx.getId().getContractorId());
							Spestcnt spestcnt = ejb_spestcnt.findById(spestcntPK); 
							
							setConBy(spestcnt.getCode());
							setConName(spestcnt.getContractorName());
							setSupBy(spestcndx.getAllocatedUser());
		
						}
					}
				}

			}
			
				
			}*/
		
		
		if(pcinitiladataList != null){
			//setJobAssDate(pcinitiladataList.);
			if(session != null && !session.containsKey(pcinitiladataList))
			{
				session.put(getSessionKey("userName"),pcinitiladataList);
			}
		}
		
		System.out.println("Search Job : " + jobAssDate);
		return "success";
		
		
	}
	
public String populateEstimateDetails(){
	setState1("save");
	System.out.println("SSSSLLL");
	PcesthttEjb pc = new PcesthttEjb(getSessionKey("region"));
	PcesthmtEjb pchmte = new PcesthmtEjb(getSessionKey("region"));
	Pcesthtt pchtt =  pc.findByEstimationNo(txtSearchValue.trim(), costCenterNo,Constants.REV_NO);
	Pcesthmt pchmt = null;
	String estimateNo = null;
	BigDecimal detailCost = new BigDecimal("0");
	String fundSource = null;
	String projectNo = null;
	if(pchtt != null){
		estimateNo = pchtt.getId().getEstimateNo();
		fundSource = pchtt.getFundSource();
		detailCost = pchtt.getStdCost();
		
	}else{
		pchmt =  pchmte.findByEstimationNo(txtSearchValue.trim(), costCenterNo);
		estimateNo=pchmt.getId().getEstimateNo();
		fundSource = pchmt.getFundSource();
		detailCost = pchmt.getStdCost();
		projectNo = pchmt.getProjectNo();
	}
	if(estimateNo != null){
		setFileNo(estimateNo);
		
		EstimateReferenceEjb estejb = new EstimateReferenceEjb();
		EstimateReference refe = estejb.findByWorkEstimateNo(txtSearchValue.trim(),costCenterNo, getSessionKey("region"));
		if(refe != null){
			//setRefNo(refe.getId().getStandardEstimateNo());
			if(detailCost != null){
				setDetailCost(pchtt.getStdCost().toString());
			}
			
			
		
			String commCost = costCenterNo.substring(0, 3);
			SpstdesthmtEjb spejb = new SpstdesthmtEjb(getSessionKey("region"));
			SpstdesthmtPK spstdesthmtPK = new SpstdesthmtPK();
			spstdesthmtPK.setApplicationNo(refe.getId().getStandardEstimateNo());
			spstdesthmtPK.setDeptId(commCost+".00");
			spstdesthmtPK.setStdNo(refe.getId().getStandardEstimateNo());
			spstdesthmtPK.setApplicationNo(refe.getId().getStandardEstimateNo());
			Spstdesthmt hmt = spejb.findById(spstdesthmtPK, getSessionKey("region"));
			if(hmt != null){
				if(hmt.getTotalCost() != null){
					setStdCost(hmt.getTotalCost().toString());
				}
				NumberFormat nf = NumberFormat.getInstance();
				nf.setGroupingUsed(true);
				nf.setMaximumFractionDigits(2);
				nf.setMinimumFractionDigits(2);
				double deuction = 0.0;
				if(hmt.getRebateCost() != null){
					deuction = hmt.getRebateCost().doubleValue()+hmt.getTotalCost().doubleValue();
				}else{
					deuction = hmt.getTotalCost().doubleValue();
				}
				//double deuction = hmt.getRebateCost().doubleValue()+hmt.getTotalCost().doubleValue();
				double workECost = 0.0;
				if(detailCost != null){
					workECost = detailCost.doubleValue();
				}
				double variance = 0;
				if(hmt.getTotalCost() != null && hmt.getTotalCost().doubleValue()!= 0.0){
					variance = ((workECost - deuction)/hmt.getTotalCost().doubleValue())*100;
					setVarianceNew(String.valueOf(variance));
				}
				setRefNo(hmt.getId().getStdNo());
				
			//PcesthmtEjb pchmte = new PcesthmtEjb(getSessionKey("region"));
			//JobEjb ejb = new JobEjb(sessionKey);
			//Pcesthmt pchmt =  pchmte.findByEstimationNo(txtSearchValue.trim(), costCenterNo);
			if(pchmt != null){
				setprojectNo(pchmt.getProjectNo());
				if(pchmt.getPrjAssDt() != null){
				  setJobAssDate(pchmt.getPrjAssDt().toString());
				}
				
			}
			setDecrp(getDecrp());
			WiringLandDetailCon schemaDetails = null;
			WiringLandDetailConEjb wiringLandDetailConEjb = new WiringLandDetailConEjb(getSessionKey("region"));
			schemaDetails = wiringLandDetailConEjb.findByApplicationNo(refe.getId().getStandardEstimateNo());
			if(schemaDetails != null){
				setAreaCode(schemaDetails.getAreaCode());
				setDevSec(schemaDetails.getDevSec());
				setFundId(fundSource);
				setFundsou(fundSource);
				setDistrict(schemaDetails.getDistrict());
				setElectorate(schemaDetails.getElectorate());
				setCsc(schemaDetails.getServiceDepoName());
			}
			// site visiting details
			SpestedyConEjb ejb_spestedy = new SpestedyConEjb(getSessionKey("region"));
			SpestedyCons spestedy = ejb_spestedy.getAppointment(costCenterNo, refe.getId().getStandardEstimateNo());
			if (spestedy != null)
			{				
				if(spestedy.getAllocatedDate() != null){
					setEsAllocatedDate(spestedy.getAllocatedDate().toString());
				}
				setEsttBy(spestedy.getAllocatedTo());

			}
			

			SpestcndEjb ejb_spestcnd = new SpestcndEjb(getSessionKey("region"));
			if(projectNo != null){
				
				List<Spestcnd> spestcnd = ejb_spestcnd.findByJobNo(projectNo.trim(), costCenterNo);
				System.out.println("SSSS : "+spestcnd);
				if (spestcnd != null && spestcnd.size() > 0){
					Spestcnd spestcndx = spestcnd.get(0);
					

					SpestcntEjb ejb_spestcnt = new  SpestcntEjb(getSessionKey("region"));
					SpestcntPK spestcntPK = new SpestcntPK();
					spestcntPK.setDeptId(costCenterNo);
					spestcntPK.setContractorId(spestcndx.getId().getContractorId());
					Spestcnt spestcnt = ejb_spestcnt.findById(spestcntPK); 
					System.out.println("SSS : "+spestcnt.getCode());
					setConBy(spestcnt.getCode());
					setConName(spestcnt.getContractorName());
					setSupBy(spestcndx.getAllocatedUser());
					System.out.println("spestcndx.getAllocatedDate() : "+spestcndx.getAllocatedDate());
					if(spestcndx.getAllocatedDate()!=null){
						setJobAlloDate(spestcndx.getAllocatedDate().toString());
					}
					
					

				}
			}
				
			}

		}
		
			
		}
	return SUCCESS;
}
	

    public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	public String close() {
		System.out.println("fdgfdhgs close");
		return "close";
	}

	public String getRegion() {
		return region;
	}

	public String getCostCenterNo() {
		return costCenterNo;
	}

	/*
	 * public String getSessionKey(String key) { return
	 * getSession().get(key).toString();
	 * 
	 * }
	 */

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	
	@SuppressWarnings("unchecked")
	public String loadValuesForPage() throws Exception
	{
		System.out.println("Hi loadValuesForPage : " );
		System.out.println(session);
		searchJob();
		List<Pcinitialdata> pcinitiladataListjj;
		pcinitiladataListjj=(List<Pcinitialdata>)session.get(getSessionKey("userName"));
		String[] estimateRef = null;
		try{
		System.out.println("Hi loadValuesForPage 1 : " );
	if(hiddentableRadioButtonValue.contains(","))
	{
        estimateRef=hiddentableRadioButtonValue.split(",");
		hiddentableRadioButtonValue = estimateRef[0];
	}
		for(int i = 0; i < pcinitiladataListjj.size(); i++) {   
			System.out.println(pcinitiladataListjj.get(i).getId().getRefNo());
			if((hiddentableRadioButtonValue.equalsIgnoreCase(pcinitiladataListjj.get(i).getId().getFileNo())))
			{
				System.out.println("Hi1 " );
				Pcinitialdata estNoObject=new Pcinitialdata();
				estNoObject=pcinitiladataListjj.get(i);
				
				fileNo = estNoObject.getId().getFileNo();
				fundId = estNoObject.getFundId();
				esttBy = estNoObject.getEstBy();
				areaCode = estNoObject.getAreaCode();
				conBy = estNoObject.getConBy();
				supBy = estNoObject.getSupBy();
				System.out.println("Hi2 " );
				projectNo = estNoObject.getProjectNo();
				refNo=estNoObject.getId().getRefNo();
				decrp = estNoObject.getDecrp();
				devSec = estNoObject.getDevSec();
				feedername = estNoObject.getFeedername();
				codeNumber = estNoObject.getCodeNumber();
				profundaut = estNoObject.getPro_Fund_Aut();
				if(estNoObject.getHt33() != null){
					ht33 = estNoObject.getHt33().toString();
				}
				if(estNoObject.getAbc4w() != null){
					abc4w = estNoObject.getAbc4w().toString();
				}
				if(estNoObject.getLt3Phase() != null){
					lt3Phase = estNoObject.getLt3Phase().toString();
				}
				if(estNoObject.getAbc5w() != null){
					abc5w = estNoObject.getAbc5w().toString();
				}
				
				if(estNoObject.getAbcsecct() != null){
					abcsecct = estNoObject.getAbcsecct().toString();
				}
				if(estNoObject.getLt11() != null){
					lt11 = estNoObject.getLt11().toString();
				}
				System.out.println("Hi3 " );
				if(estNoObject.getLtsPhase() != null){
					ltsPhase = estNoObject.getLtsPhase().toString();
				}
				if(estNoObject.getSub33() != null){
					sub33 = estNoObject.getSub33().toString();
				}
				if(estNoObject.getSub11() != null){
					sub11 = estNoObject.getSub11().toString();
				}
				if(estNoObject.getSubCapacity() != null){
					subCapacity = estNoObject.getSubCapacity().toString();
				}
				
				if(estNoObject.getPerCapacity() != null){
					perCapacity = estNoObject.getPerCapacity().toString();
				}
				
				if(estNoObject.getNewCapacity() != null){
					newCapacity = estNoObject.getNewCapacity().toString();
				}
				System.out.println("Hi4 " );
				if(estNoObject.getDetailCost() != null){
					detailCost = estNoObject.getDetailCost().toString();
				}
				if( estNoObject.getVarianceNew() != null){
					varianceNew = estNoObject.getVarianceNew().toString();
				}
				
				///////
				if(estNoObject.getCombineRun() != null){
					combineRun = estNoObject.getCombineRun().toString();
				}
				
				if(estNoObject.getLt23() != null){
					lt23 = estNoObject.getLt23().toString();
				}
				
				if(estNoObject.getLt13() != null){
					lt13 = estNoObject.getLt13().toString();
				}
				
				if(estNoObject.getGanDaysRequired() != null){
					ganDaysRequired = estNoObject.getGanDaysRequired().toString();
				}
				
				if(estNoObject.getNoPoleShifted() != null){
					noPoleShifted = estNoObject.getNoPoleShifted().toString();
				}
				
				if(estNoObject.getNoPoleToBeShifted() != null){
					noPoleTobeShifted = estNoObject.getNoPoleToBeShifted().toString();
				}
				
				//if(estNoObject.getPro_Fund_Aut()!= null){
					//profundaut = estNoObject.getPro_Fund_Aut().toString();
				//}
				
				if(estNoObject.getNo_ht_pole() != null){
					no_ht_pole = estNoObject.getNo_ht_pole().toString();
				}
				
				if(estNoObject.getNo_lt_pole() != null){
					no_lt_pole = estNoObject.getNo_lt_pole().toString();
				}
				
				System.out.println("XXXHT : " + estNoObject.getJobAssDate());
				
				if(estNoObject.getJobAssDate() != null){
					jobAssDate=estNoObject.getJobAssDate().toString();
					System.out.println("XXX jobAssDate : " + jobAssDate);
					setJobAssDate(jobAssDate);
					
				}
								
				System.out.println("XXXHT : " + estNoObject.getTargetDate());
				if(estNoObject.getTargetDate() != null){
					targetDate=estNoObject.getTargetDate().toString();
					System.out.println("XXX targetDate: " + targetDate);
					setTargetDate(targetDate);
					setTargetDateDis(targetDate);
				}
				if(estNoObject.getJobnoRecedate() != null){
					jobnoReceDate=estNoObject.getJobnoRecedate().toString();
				}
				if(estNoObject.getJobAlloDate() != null){
					jobAlloDate=estNoObject.getJobAlloDate().toString();
				}
				if(estNoObject.getProjectLetterReceivedOnDate() != null){
					projectLetterReceivedOnDate=estNoObject.getProjectLetterReceivedOnDate().toString();
				}
				if(estNoObject.getProLtrToEsOn() != null){
					proLtrToEsOnDate=estNoObject.getProLtrToEsOn().toString();
				}
				if(estNoObject.getEstRcdOn() != null){
					estReceivedOnDate=estNoObject.getEstRcdOn().toString();
				}
				if(estNoObject.getEstDgmOn() != null){
					estimateSntToDGMOnDate=estNoObject.getEstDgmOn().toString();
				}
				if(estNoObject.getEsAllocatedDate() != null){
					esAllocatedDate = estNoObject.getEsAllocatedDate().toString();
				}
				if(estNoObject.getEsOnDate() != null){
					estimatedDate = estNoObject.getEsOnDate().toString();
				}
				if(estNoObject.getElectorate() != null){
					electorate = estNoObject.getElectorate().toString();
				}
				if(estNoObject.getDistrict() != null){
					district = estNoObject.getDistrict().toString();
				}
				if(estNoObject.getDevSec() != null){
					devSec = estNoObject.getDevSec().toString();
				}
				if(estNoObject.getFeedername() != null){
					devSec = estNoObject.getFeedername().toString();
				}
				if(estNoObject.getStdCost() != null){
					stdCost = estNoObject.getStdCost().toString();
				}
				if(estNoObject.getConBy()!= null){
					conBy = estNoObject.getConBy().toString();
				}
				if(estNoObject.getSupBy()!= null){
					supBy = estNoObject.getSupBy().toString();
				}
				if(estNoObject.getEstBy()!= null){
					esttBy = estNoObject.getEstBy().toString();
				}
				if(estNoObject.getAugSub()!= null){
					augSub = estNoObject.getAugSub().toString();
				}
				
				System.out.println("Hi5 " );
			}
		}
		System.out.println("Hi5 : " + targetDate );
	    
		setState1(null);
		setState("found");
		return SUCCESS;
		}catch (Exception e){
			return ERROR;
		}
	}
	
	public String addProDirect() {
		
		
		try {
			setState1("save");
			setPath(newPath);
			setState(null);
			System.out.println("enter the newdirect");
			System.out.println("new path:" + path);
			System.out.println(costCenterNo);
			
			// clearForm();
			setErrorMessage(null);
			errorMessages.clear();
			 setLoggedData();
			System.out.println("In the New direct method");
			HttpServletRequest request = ServletActionContext.getRequest();
			String estimateNo = request.getParameter("estimateNo");
			System.out.println("In the New direct method : "+ estimateNo);
			if(estimateNo !=null){
				setTxtSearchValue(estimateNo);
			}else{
				setTxtSearchValue("");
			}
			//String aa=searchJob();

			/* PcinitialdataEjb pcin=new PcinitialdataEjb(region);
			pcInitialDataList = new ArrayList<Pcinitialdata>();
			pcInitialDataList=pcin.getAll();*/
			
			// loadEstimationRefNumbers();//to load Application status to be changed
			// loadWorkEstimationRefNumbers();
			// loadWareHouse();
			// loadFundSources();
			// setNewEntryFromMainMenu();
			//if(aa==SUCCESS)
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			return ERROR;
		}
		/*else
			return "error";*/
	}

	
	public String modifyPro() {
		setPath(modifyPath);
		setState("found");
		setState1(null);
		return SUCCESS;
	}

	public String modifyProDirect()
	{		setLoggedData();
			setPath(modifyPath);
			setState("found");
			setState1(null);
			setErrorMessage(null);
			
			PcesthmtEjb pchmtEjb=new PcesthmtEjb(region);
			jobNumberList = new ArrayList<String>();
			List<Long> statuses = new ArrayList<Long>();
			statuses.add(new Long(EstimateStatus.JOB_ONGOING));
			statuses.add(new Long(EstimateStatus.JOB_SOFT_CLOSED));
			statuses.add(new Long(EstimateStatus.JOB_SOFT_CLOSED));
			statuses.add(new Long(EstimateStatus.JOB_RIVISION));
			statuses.add(new Long(EstimateStatus.JOB_APPROVAL_CE));
			jobNumberList=pchmtEjb.findJobNosList(getCostCenterNo(), statuses);
			
			
			PcmilemEjb pcmilemEjb=new PcmilemEjb(region);
			List<Pcmilem> milestones= pcmilemEjb.getMilestoneList(getCostCenterNo(), getRegion());
			for(Pcmilem pcmilem : milestones){
				milestoneList.add(pcmilem.getMile_nm() + " - " + pcmilem.getPercentage()+"%" );
			}
			
		return SUCCESS; 

}
	
	public void setLoggedData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
	
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		String userLevel= (String) request.getSession().getAttribute("userRole");
		setCostCenterNo(costCenterNo);
		setLoggedInUserLevel(userLevel);
		setSession(ActionContext.getContext().getSession());
		// log.info(getSession());
		getCostCenterNo();
		 setLoggedInUserId(getSessionKey("userName"));
		 setCostCenterNo(getSessionKey("costCenterNo"));
		System.out.println(getSessionKey("region"));
		
		setCostCenterNo(costCenterNo);
		setRegion(getSessionKey("region"));
		loadUserList(getRegion(),getCostCenterNo());
		loadConRef(getRegion(),getCostCenterNo());
		// log.info(getSession());
	}

	public String update() throws Exception
	{	setState("found");
	    setState1(null);
		try {
			setLoggedData();
			PcinitialdataPK pcinPK = new PcinitialdataPK();
			
			
			if(getRefNo() == null || getRefNo().equalsIgnoreCase(Constants.DEFAULT_STRING)){
				setRefNo(getfileno());
			}
			
			
			pcinPK.setRefNo(getRefNo().trim());
			pcinPK.setFileNo(getfileno().trim());
			pcinPK.setDeptId(getCostCenterNo());// **************************************************************************
	
			System.out.println("pcinPK" + pcinPK);
			
			//Pcinitialdata pcin = new Pcinitialdata();
			Pcinitialdata pcin = null;
			
			PcinitialdataEjb pcEjb = new PcinitialdataEjb(region);
			
			pcin = pcEjb.findById(pcinPK);
			System.out.println("pcinssssss  " + pcin);
			if(pcin == null){
				pcin = new Pcinitialdata();
				pcin.setId(pcinPK);
			}
			
			
			pcin.setProjectNo(projectNo);
		
			pcin.setDecrp(decrp);
			pcin.setDevSec(devSec);
			pcin.setFeedername(feedername);
			pcin.setFundId(fundId);
			pcin.setEstBy(esttBy);
			pcin.setAreaCode(areaCode);
			pcin.setConBy(conBy);
			pcin.setSupBy(supBy);
			pcin.setCodeNumber(codeNumber);
			pcin.setPro_Fund_Aut(profundaut);
			
			System.out.println("XXXHTcombineRun : " + combineRun);
			
			if(combineRun != null && combineRun.length() > 0 ){
				pcin.setCombineRun(new Float(combineRun));
			}
			
			System.out.println("XXXHTlt23 : " + lt23);
			if(lt23 != null && lt23.length() > 0){
				pcin.setLt23(new Float(lt23));
			}
			System.out.println("XXXHTlt13 : " + lt13);
			if(lt13 != null && lt13.length() > 0){
				pcin.setLt13(new Float(lt13));
			}
			System.out.println("XXXHTnoPoleShifted : " + noPoleShifted);
			if(noPoleShifted != null && noPoleShifted.length() > 0){
				pcin.setNoPoleShifted(new Integer(noPoleShifted));
			}
			
			if(noPoleTobeShifted != null && noPoleTobeShifted.length() > 0){
				pcin.setNoPoleToBeShifted(new Integer(noPoleTobeShifted));
			}
			
			
			if(ganDaysRequired != null && ganDaysRequired.length() > 0){
				pcin.setGanDaysRequired(new Float(ganDaysRequired));
			}
			if(no_ht_pole != null && no_ht_pole.length() > 0){
				pcin.setNo_ht_pole(new Integer(no_ht_pole));
			}
			
			if(no_lt_pole != null && no_lt_pole.length() > 0){
				pcin.setNo_lt_pole(new Integer(no_lt_pole));
			}
			
			if(ht33 != null && ht33.length() > 0){
				pcin.setHt33(new Float(ht33));
			}
			if(abc5w != null && abc5w.length() > 0){
				pcin.setAbc5w(new Float(abc5w));
			}
			if(lt3Phase != null && lt3Phase.length() > 0){
				pcin.setLt3Phase(new Float(lt3Phase));
			}
			if(lt11 != null  && lt11.length() > 0){
				pcin.setLt11(new Float(lt11));
			}
			if(ltsPhase != null && ltsPhase.length() > 0){
				pcin.setLtsPhase(new Float(ltsPhase));
			}
			if(abc4w != null && abc4w.length() > 0){
				pcin.setAbc4w(new Float(abc4w));
			}
			
			if(abcsecct != null && abcsecct.length() > 0){
				pcin.setAbcsecct(new Float(abcsecct));
			}
			if(sub11 != null && sub11.length() > 0){
				pcin.setSub11(new Float(sub11));
			}
			if(sub33 != null && sub33.length() > 0){
				pcin.setSub33(new Float(sub33));
			}
			if(subCapacity != null && subCapacity.length() > 0){
				pcin.setSubCapacity(new Float(subCapacity));
			}
			if(stdCost != null && stdCost.length() > 0){
				pcin.setStdCost(new Float(stdCost));
			}
			if(detailCost != null && detailCost.length() > 0){
				pcin.setDetailCost(new Float(detailCost));
			}
			if(varianceNew != null && varianceNew.length() > 0){
				pcin.setVarianceNew(new Float(varianceNew));
			}
			System.out.println("XXX" + targetDate);
			if(targetDate != null && targetDate.length() > 0){
				System.out.println("XXX : "+ targetDate);
				pcin.setTargetDate(format.StrToDate(targetDate.substring(0, 10)));
			}
		
			if(jobnoReceDate != null && jobnoReceDate.length() > 0){
				pcin.setJobnoRecedate(format.StrToDate(jobnoReceDate.substring(0, 10)));
			}
			if(jobAlloDate != null && jobAlloDate.length() > 0){
				pcin.setJobAlloDate(format.StrToDate(jobAlloDate.substring(0, 10)));
			}
			if(proLtrToEsOnDate != null && proLtrToEsOnDate.length() > 0){
				pcin.setProLtrToEsOn(format.StrToDate(proLtrToEsOnDate.substring(0, 10)));
			}
			if(estimateSntToDGMOnDate != null && estimateSntToDGMOnDate.length() > 0 ){
				pcin.setEstDgmOn(format.StrToDate(estimateSntToDGMOnDate.substring(0, 10)));
			}
			if(projectLetterReceivedOnDate != null && projectLetterReceivedOnDate.length() > 0){
				pcin.setProjectLetterReceivedOnDate(format.StrToDate(projectLetterReceivedOnDate.substring(0, 10)));
			}
		
			if(estReceivedOnDate != null && estReceivedOnDate.length() > 0 ){
				pcin.setEstRcdOn(format.StrToDate(estReceivedOnDate.substring(0, 10)));
			}
			if(esAllocatedDate != null && esAllocatedDate.length() > 0 ){
				pcin.setEsAllocatedDate(format.StrToDate(esAllocatedDate.substring(0, 10)));
			}
			if(estimatedDate != null && estimatedDate.length() > 0){
				pcin.setEsOnDate(format.StrToDate(estimatedDate.substring(0, 10)));
			}
			if(jobAssDate != null && jobAssDate.length() > 0){
				pcin.setJobAssDate(format.StrToDate(jobAssDate.substring(0, 10)));
			}
			if(perCapacity != null && perCapacity.length() > 0){
				pcin.setPerCapacity(new Float(perCapacity));
			}
		
			if(newCapacity != null && newCapacity.length() > 0 ){
				pcin.setNewCapacity(new Float(newCapacity));
			}
			pcin.setDistrict(getDistrict());
			pcin.setDevSec(getDistrict());
			System.out.println("getElectorate() : " + getElectorate());
			pcin.setElectorate(getElectorate());
			pcin.setConBy(getConBy());
			pcin.setEstBy(getEsttBy());
			pcin.setSupBy(getSupBy());
			pcin.setServiceDepoName(getCsc());
			pcin.setId(pcinPK);
			PcinitialdataEjb ejb = new PcinitialdataEjb(getSessionKey("region"));
			System.out.println("Department ID->" + pcinPK.getDeptId()
					+ "+Ref Number" + pcinPK.getRefNo());
			
			ejb.updatePcinitialData(pcin);
			setErrorMessage("Succefully saved...");
			setState(null);
			setMessageType(MSG_DONE);
		}catch (Exception e) {
			// TODO Auto-generated catch block

			System.out.println(e.getCause() + "***************");
			System.out.println(e.getMessage() + "++++++++++++++++++++");

			e.printStackTrace();

			return ERROR;
		}
		return SUCCESS;
	}
	
	public String delete(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String region= (String) request.getSession().getAttribute("region");
	
		String costCenterNo=(String) request.getSession().getAttribute("costCenterNo");
		String userId= (String) request.getSession().getAttribute("userName");
		setCostCenterNo(costCenterNo);
		Pcmiledates objPcMileDate = new Pcmiledates();
		List<Pcmiledates> pcmiledatesList = new ArrayList<Pcmiledates>();
		List<Pcmilesumary> pcmilSumaryList = new ArrayList<Pcmilesumary>();
		PcmiledatesEjb obj = new PcmiledatesEjb(getSessionKey("region"));
		PcmilesumaryEjb objSu = new PcmilesumaryEjb(getSessionKey("region"));
		if(getCostCenterNo() != null && estimateNo != null){
			estimateNo = estimateNo.trim();
			System.out.println("start****1");
			pcmiledatesList = obj.getMilestoneList(getCostCenterNo(), estimateNo, getSessionKey("region"));
			if(pcmiledatesList != null){
			Iterator<Pcmiledates> it = pcmiledatesList.iterator();
	        while (it.hasNext()) {
	        	Pcmiledates pcmiledates = it.next();
	        	obj.removePcmiledates(pcmiledates, getSessionKey("region"));
	        	
	        }
			}
	        System.out.println("start****2");
	        pcmilSumaryList =  objSu.getSummaryList(getCostCenterNo(), estimateNo, getSessionKey("region"));
	        if(pcmilSumaryList != null){
	        Iterator<Pcmilesumary> itS = pcmilSumaryList.iterator();
	        while (itS.hasNext()) {
	        	Pcmilesumary pcmilesumary = itS.next();
	        	objSu.removePcmilesumary(pcmilesumary, getSessionKey("region"));
	        	
	        }
	        }
	        
		}
		
		System.out.println("start**********************"+getCostCenterNo() + "  "+ estimateNo);
		
		
		setLoggedData();
		setErrorMessage("Succefully removed...");
		setState(null);
		setMessageType(MSG_DONE);
		System.out.println("start****hiiii");
		return SUCCESS;
		
	}
	
	
	public String save() throws Exception {
		setState("save");
		setState(null);
		try {
			setLoggedData();
			System.out.println("start**********************");
			// setLoggedData();
			String fileNo = getfileno().trim();
			if(getRefNo() == null || getRefNo().equalsIgnoreCase(Constants.DEFAULT_STRING)){
				setRefNo(fileNo.trim());
			}
			
			String projectNo = getProjectNo();
			String decrp = getDecrp();
			String devSec = getDevSec();
			String feedername = getFeedername();
			String codeNumber = getCodeNumber();
			String fundId = getFundId();
			String estBy = getEsttBy();
			String areaCode = getAreaCode();
			String conBy = getConBy();
			String supBy = getSupBy();
			String ht33 = getHt33();//Have to change all the float values to String.. Check where are the places tht errors can occur.. ask tht i have to change the database class.ejb
			String lt3Phase = getLt3Phase();
			String abc5w = getAbc5w();
			String lt11 = getLt11();
			String ltsPhase = getLtsPhase();
			String abc4w = getAbc4w();
			String sub33 = getSub33();
			String abcsecct = getAbcsecct();
			String sub11 = getSub11();
			String subCapacity = getSubCapacity();
			String csc = getCsc();
			String proFund = getProfundaut();
			// setCostCenter("costCenterNo");
			// setCostCenter(getSessionKey(costCenter));
			// System.out.println("cost center"+costCenter);
			// PcinitialdataEjb ejb = new PcinitialdataEjb(region);
			// PcinitialdataEjb ejb = new PcinitialdataEjb();

			// PcinitialdataEjb ej = new
			// PcinitialdataEjb(getSessionKey(region));

		

			PcinitialdataPK pcinPK = new PcinitialdataPK();
			// pcinPK.setDeptId(getCostCenterNo());
			System.out.println("test");
			pcinPK.setRefNo(getRefNo().trim());
			pcinPK.setDeptId(getCostCenterNo());// **************************************************************************
			pcinPK.setFileNo(getFileNo().trim());
			System.out.println("pcinPK" + pcinPK);

			Pcinitialdata pcin = new Pcinitialdata();
			pcin.setId(pcinPK);
			/*
			 * pcin.setFileNo(getfileno()); pcin.setAbc4w(getAbc4w());
			 * pcin.setAreaCode(getAreaCode());
			 */
			//pcin.getId().setFileNo(getFileNo());
			System.out.println("test2");
			// pcin.setRefNo(refNo);
			pcin.setProjectNo(projectNo);
			System.out.println(projectNo);
			pcin.setDecrp(decrp);
			pcin.setDevSec(devSec);
			pcin.setFeedername(feedername);
			pcin.setCodeNumber(codeNumber);
			pcin.setFundId(fundId);
			pcin.setEstBy(estBy);
			pcin.setAreaCode(areaCode);
			pcin.setConBy(conBy);
			pcin.setSupBy(supBy);
			
			pcin.setPro_Fund_Aut(proFund);
			if(combineRun != null && combineRun.length() > 0){
				pcin.setCombineRun(new Float(combineRun));
			}
			
			if(lt23 != null && lt23.length() > 0){
				pcin.setLt23(new Float(lt23));
			}
			
			if(lt13 != null && lt13.length() > 0){
				pcin.setLt13(new Float(lt13));
			}
			
			if(ganDaysRequired != null && ganDaysRequired.length() > 0){
				pcin.setGanDaysRequired(new Float(ganDaysRequired));
			}
			
			
			if(noPoleShifted != null && noPoleShifted.length() > 0){
				pcin.setNoPoleShifted(new Integer(noPoleShifted));
			}
			
			if(noPoleTobeShifted != null && noPoleTobeShifted.length() > 0){
				pcin.setNoPoleToBeShifted(new Integer(noPoleTobeShifted));
			}
			
			if(no_ht_pole != null && no_ht_pole.length() > 0){
				pcin.setNo_ht_pole(new Integer(no_ht_pole));
			}
			
			if(no_lt_pole != null && no_lt_pole.length() > 0){
				pcin.setNo_lt_pole(new Integer(no_lt_pole));
			}

			if(ht33 != null && ht33.length() > 0){
				pcin.setHt33(new Float(ht33));
			}
			if(abc5w != null && abc5w.length() > 0){
				pcin.setAbc5w(new Float(abc5w));
			}
			if(lt3Phase != null && lt3Phase.length() > 0){
				pcin.setLt3Phase(new Float(lt3Phase));
			}
			if(lt11 != null && lt11.length() > 0){
				pcin.setLt11(new Float(lt11));
			}
			if(ltsPhase != null && ltsPhase.length() > 0){
				pcin.setLtsPhase(new Float(ltsPhase));
			}
			if(abc4w != null && abc4w.length() > 0 ){
				pcin.setAbc4w(new Float(abc4w));
			}
			
			if(abcsecct != null  && abcsecct.length() > 0){
				pcin.setAbcsecct(new Float(abcsecct));
			}
			if(sub11 != null && sub11.length() > 0){
				pcin.setSub11(new Float(sub11));
			}
			if(sub33 != null && sub33.length() > 0){
				pcin.setSub33(new Float(sub33));
			}
			if(subCapacity != null && subCapacity.length() > 0){
				pcin.setSubCapacity(new Float(subCapacity));
			}
			if(stdCost != null && stdCost.length() > 0){
				pcin.setStdCost(new Float(stdCost));
			}
			if(detailCost != null && detailCost.length() > 0){
				pcin.setDetailCost(new Float(detailCost));
			}
			if(varianceNew != null && varianceNew.length() > 0){
				pcin.setVarianceNew(new Float(varianceNew));
			}
			System.out.println("A " + targetDate );
			if(targetDate != null && targetDate.length() > 0){
				System.out.println("B " + targetDate );
				pcin.setTargetDate(format.StrToDate(targetDate.substring(0, 10)));
			}
		
			if(jobnoReceDate != null && jobnoReceDate.length() > 0){
				pcin.setJobnoRecedate(format.StrToDate(jobnoReceDate.substring(0, 10)));
			}
			if(jobAlloDate != null && jobAlloDate.length() > 0){
				pcin.setJobAlloDate(format.StrToDate(jobAlloDate.substring(0, 10)));
			}
			if(proLtrToEsOnDate != null && proLtrToEsOnDate.length() > 0){
				pcin.setProLtrToEsOn(format.StrToDate(proLtrToEsOnDate.substring(0, 10)));
			}
			if(estimateSntToDGMOnDate != null && estimateSntToDGMOnDate.length() > 0){
				pcin.setEstDgmOn(format.StrToDate(estimateSntToDGMOnDate.substring(0, 10)));
			}
			if(projectLetterReceivedOnDate != null && projectLetterReceivedOnDate.length() > 0){
				pcin.setProjectLetterReceivedOnDate(format.StrToDate(projectLetterReceivedOnDate.substring(0, 10)));
			}
		
			if(estReceivedOnDate != null && estReceivedOnDate.length() > 0){
				pcin.setEstRcdOn(format.StrToDate(estReceivedOnDate.substring(0, 10)));
			}
			if(esAllocatedDate != null && esAllocatedDate.length() > 0){
				pcin.setEsAllocatedDate(format.StrToDate(esAllocatedDate.substring(0, 10)));
			}
			if(estimatedDate != null && estimatedDate.length() > 0){
				pcin.setEsOnDate(format.StrToDate(estimatedDate.substring(0, 10)));
			}
			if(jobAssDate != null && jobAssDate.length() > 0){
				pcin.setJobAssDate(format.StrToDate(jobAssDate.substring(0, 10)));
			}
			if(perCapacity != null && perCapacity.length() > 0){
				pcin.setPerCapacity(new Float(perCapacity));
			}
		
			if(newCapacity != null && newCapacity.length() > 0){
				pcin.setNewCapacity(new Float(newCapacity));
			}
			
			pcin.setDistrict(getDistrict());
			pcin.setDevSec(getDistrict());
			System.out.println("getElectorate() : " + getElectorate());
			pcin.setElectorate(getElectorate());
			pcin.setConBy(getConBy());
			pcin.setEstBy(getEsttBy());
			pcin.setSupBy(getSupBy());
			pcin.setServiceDepoName(getCsc());
			pcin.setId(pcinPK);
			PcinitialdataEjb ejb = new PcinitialdataEjb(getSessionKey("region"));
			System.out.println("Department ID->" + pcinPK.getDeptId()
					+ "+Ref Number" + pcinPK.getRefNo());
			ejb.createPcinitialdata(pcin);

			/*
			 * Pcinitialdata pcin = new Pcinitialdata();
			 * 
			 * //pcin.setLt3Phase(lt3Phase.toString()); //Pcinitialdata pp =
			 * ejb.findById(pcin);
			 * 
			 * 
			 * /*if (pp == null) { ejb.createPcinitialdata(pcin);
			 * setLblSuccess("App added successfully"); }else { execute();
			 * setLblError("App already exist."); return SUCCESS; }
			 */
		/*	if(!session.containsKey(pcinitiladataList))
			{
				session.put(getSessionKey("userName"),pcinitiladataList);
			}*/
			setErrorMessage("Succefully saved...");
			setMessageType(MSG_DONE);
			setState1(null);
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block

			System.out.println(e.getCause() + "***************");
			System.out.println(e.getMessage() + "++++++++++++++++++++");

			e.printStackTrace();

			return ERROR;
		}
	}
	
	
	

	

	public String getLoggedInUserLevel() {
		return loggedInUserLevel;
	}

	public void setLoggedInUserLevel(String loggedInUserLevel) {
		this.loggedInUserLevel = loggedInUserLevel;
	}

	public NumberFormat getNf() {
		return nf;
	}

	public void setNf(NumberFormat nf) {
		this.nf = nf;
	}

	public Object getRevisedJobsList() {
		return revisedJobsList;
	}

	public void setRevisedJobsList(Object revisedJobsList) {
		this.revisedJobsList = revisedJobsList;
	}

	public ArrayList<Pcinitialdata> getProgressList() {
		return progressList;
	}

	public void setProgressList(ArrayList<Pcinitialdata> progressList) {
		this.progressList = progressList;
	}

	public String getFileNotxfd() {
		return FileNotxfd;
	}

	public void setFileNotxfd(String fileNotxfd) {
		FileNotxfd = fileNotxfd;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	
	public String getDetailCost() {
		return detailCost;
	}

	public void setDetailCost(String detailCost) {
		this.detailCost = detailCost;
	}

	public String getVarianceNew() {
		return varianceNew;
	}

	public void setVarianceNew(String varianceNew) {
		this.varianceNew = varianceNew;
	}

	public String getStdCost() {
		return stdCost;
	}

	public void setStdCost(String stdCost) {
		this.stdCost = stdCost;
	}



	public String getErrorMessage() {
		return errorMessage;
	}

	public String getPath() {
		return path;
	}

	public Map<String, String[]> getParameters() {
		return parameters;
	}

	public static String getNewpath() {
		return newPath;
	}

	public static String getModifypath() {
		return modifyPath;
	}

	public static Log getLog() {
		return log;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public String getFileNo() {
		return fileNo;
	}

	public String getLblSuccess() {
		return lblSuccess;
	}

	public String getLblError() {
		return lblError;
	}

	public void setCscname(String cscname) {
		Cscname = cscname;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public void setEsttBy(String estBy) {
		this.esttBy = estBy;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public void setSupBy(String supBy) {
		this.supBy = supBy;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	
	public void setRegion(String region) {
		this.region = region;
	}

	

	public List<Pcinitialdata> getPcinitiladataList() {
		return pcinitiladataList;
	}

	public void setPcinitiladataList(List<Pcinitialdata> pcinitiladataList) {
		this.pcinitiladataList = pcinitiladataList;
	}

	

	private void clear() {
		setFileNo(null);
		setRefNo(null);
		setConBy(null);
		setprojectNo(null);
		setDecrp(null);
		setDevSec(null);

	}

	private void setDevSec(String devSec) {
		this.devSec = devSec;

	}

	private void setDecrp(String decrp) {
		this.decrp = decrp;

	}
	

	private void setprojectNo(String projectNo) {
		this.projectNo = projectNo;

	}

	private void setRefNo(String refNo) {
		this.refNo = refNo;

	}

	private void setLblError(String lblerror) {
		this.lblError = lblerror;

	}

	public void setLblSuccess(String lblSuccess) {
		this.lblSuccess = lblSuccess;
	}

	public void setCostCenter(String sessionKey) {
		this.costCenterNo = costCenter;
	}

	public String getSupBy() {

		return supBy;
	}

	public String getConBy() {

		return conBy;
	}

	public String getAreaCode() {

		return areaCode;
	}

	public String getEsttBy() {

		return esttBy;
	}

	public String getFundId() {

		return fundId;
	}

	public String getDevSec() {

		return devSec;
	}

	public String getDecrp() {

		return decrp;
	}

	public String getProjectNo() {

		return projectNo;
	}

	public String getRefNo() {

		return refNo;
	}

	public String getFileno(String fileNo2) {

		return fileNo;
	}

	public String getfileno() {

		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;

	}

	public void setConBy(String conBy) {
		this.conBy = conBy;
		// this.contractorAddress = contractorAddress;
	}

	public void setDate(String formatDate) {

	}

	public void setCscname(Object cscname) {

	}

	public Object getCscname() {

		return Cscname;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath(String path) {
		return path;
	}

	public void setCostCenterNo(String costCenterNo) {
		this.costCenterNo = costCenterNo;
	}

	public ArrayList<Pcinitialdata> getprogressList() {
		return progressList;
		// return addProList;
	}

	public void setprogressList(ArrayList<Pcinitialdata> progressList) {
		this.progressList = progressList;
		System.out.println("add pro");
	}

	public String getLoggedInUserId() {
		return loggedInUserId;
	}

	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}

	
	
	
	@Override
	public void setParameters(Map<String, String[]> arg0) {

		this.parameters=arg0;

	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	public String getSessionKey(String key) {
		return getSession().get(key).toString();
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public String getLoggedInCostCenterNo() {
		return loggedInCostCenterNo;
	}

	public void setLoggedInCostCenterNo(String loggedInCostCenterNo) {
		this.loggedInCostCenterNo = loggedInCostCenterNo;
	}

	public String getNoPoleTobeShifted() {
		return noPoleTobeShifted;
	}

	public void setNoPoleTobeShifted(String noPoleTobeShifted) {
		this.noPoleTobeShifted = noPoleTobeShifted;
	}
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCsc() {
		return csc;
	}

	public void setCsc(String csc) {
		this.csc = csc;
	}

	public String getElectorate() {
		return electorate;
	}

	public void setElectorate(String electorate) {
		this.electorate = electorate;
	}

	

	public String getJobAssDate() {
		return jobAssDate;
	}

	public void setJobAssDate(String jobAssDate) {
		this.jobAssDate = jobAssDate;
	}

	public String getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
	}

	public String getJobnoReceDate() {
		return jobnoReceDate;
	}

	public void setJobnoReceDate(String jobnoReceDate) {
		this.jobnoReceDate = jobnoReceDate;
	}

	public String getProjectLetterReceivedOnDate() {
		return projectLetterReceivedOnDate;
	}

	public void setProjectLetterReceivedOnDate(String projectLetterReceivedOnDate) {
		this.projectLetterReceivedOnDate = projectLetterReceivedOnDate;
	}

	public String getProLtrToEsOnDate() {
		return proLtrToEsOnDate;
	}

	public void setProLtrToEsOnDate(String proLtrToEsOnDate) {
		this.proLtrToEsOnDate = proLtrToEsOnDate;
	}

	public String getEstReceivedOnDate() {
		return estReceivedOnDate;
	}

	public void setEstReceivedOnDate(String estReceivedOnDate) {
		this.estReceivedOnDate = estReceivedOnDate;
	}

	public String getEstimateSntToDGMOnDate() {
		return estimateSntToDGMOnDate;
	}

	public void setEstimateSntToDGMOnDate(String estimateSntToDGMOnDate) {
		this.estimateSntToDGMOnDate = estimateSntToDGMOnDate;
	}

	public String getEstimatedDate() {
		return estimatedDate;
	}

	public void setEstimatedDate(String estimatedDate) {
		this.estimatedDate = estimatedDate;
	}

	public String getEsAllocatedDate() {
		return esAllocatedDate;
	}

	public void setEsAllocatedDate(String esAllocatedDate) {
		this.esAllocatedDate = esAllocatedDate;
	}
	
	
	public String getProfundaut() {
		return profundaut;
	}

	public void setProfundaut(String profundaut) {
		this.profundaut = profundaut;
	}
	public String getHt33() {
		return ht33;
	}

	public void setHt33(String ht33) {
		this.ht33 = ht33;
	}

	public String getAbc4w() {
		return abc4w;
	}

	public void setAbc4w(String abc4w) {
		this.abc4w = abc4w;
	}

	public String getLt3Phase() {
		return lt3Phase;
	}

	public void setLt3Phase(String lt3Phase) {
		this.lt3Phase = lt3Phase;
	}

	public String getAbc5w() {
		return abc5w;
	}

	public void setAbc5w(String abc5w) {
		this.abc5w = abc5w;
	}

	public String getHT11() {
		return HT11;
	}

	public void setHT11(String hT11) {
		HT11 = hT11;
	}

	public String getLt11() {
		return lt11;
	}

	public void setLt11(String lt11) {
		this.lt11 = lt11;
	}

	public String getLtsPhase() {
		return ltsPhase;
	}

	public void setLtsPhase(String ltsPhase) {
		this.ltsPhase = ltsPhase;
	}

	public String getSub33() {
		return sub33;
	}

	public void setSub33(String sub33) {
		this.sub33 = sub33;
	}

	public String getAbcsecct() {
		return abcsecct;
	}

	public void setAbcsecct(String abcsecct) {
		this.abcsecct = abcsecct;
	}

	public String getSub11() {
		return sub11;
	}

	public void setSub11(String sub11) {
		this.sub11 = sub11;
	}

	public String getSubCapacity() {
		return subCapacity;
	}

	public void setSubCapacity(String subCapacity) {
		this.subCapacity = subCapacity;
	}

	public String getPerCapacity() {
		return perCapacity;
	}

	public void setPerCapacity(String perCapacity) {
		this.perCapacity = perCapacity;
	}

	public String getNewCapacity() {
		return newCapacity;
	}

	public void setNewCapacity(String newCapacity) {
		this.newCapacity = newCapacity;
	}
	
	public String getCombineRun() {
		return combineRun;
	}

	public void setCombineRun(String combineRun) {
		this.combineRun = combineRun;
	}
	
	public String getLt23() {
		return lt23;
	}

	public void setLt23(String lt23) {
		this.lt23 = lt23;
	}
	
	public String getNoPoleShifted() {
		return noPoleShifted;
	}

	public void setNoPoleShifted(String noPoleShifted) {
		this.noPoleShifted = noPoleShifted;
	}
	
	public String getNo_ht_pole() {
		return no_ht_pole;
	}

	public void setNo_ht_pole(String no_ht_pole) {
		this.no_ht_pole = no_ht_pole;
	}
	
	public String getGanDaysRequired() {
		return ganDaysRequired;
	}

	public void setGanDaysRequired(String ganDaysRequired) {
		this.ganDaysRequired = ganDaysRequired;
	}
	
	public String getNo_lt_pole() {
		return no_lt_pole;
	}

	public void setNo_lt_pole(String no_lt_pole) {
		this.no_lt_pole = no_lt_pole;
	}


	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}

	public String getConName() {
		return conName;
	}

	public void setConName(String conName) {
		this.conName = conName;
	}

	public ArrayList<String> getListuserName() {
		return listuserName;
	}

	public void setListuserName(ArrayList<String> listuserName) {
		this.listuserName = listuserName;
	}

	public ArrayList<String> getListContractorsList() {
		return listContractorsList;
	}

	public void setListContractorsList(ArrayList<String> listContractorsList) {
		this.listContractorsList = listContractorsList;
	}

	public List<String> getJobNumberList() {
		return jobNumberList;
	}

	public void setJobNumberList(List<String> jobNumberList) {
		this.jobNumberList = jobNumberList;
	}

	public List<String> getMilestoneList() {
		return milestoneList;
	}

	public void setMilestoneList(List<String> milestoneList) {
		this.milestoneList = milestoneList;
	}

	public String getEstimateNo() {
		return estimateNo;
	}

	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getFundsou() {
		return fundsou;
	}

	public void setFundsou(String fundsou) {
		this.fundsou = fundsou;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public void setTargetDateDis(String targetDateDis) {
		this.targetDateDis = targetDateDis;
	}

	public String getTargetDateDis() {
		return targetDateDis;
	}

	public void setListConref(List<String> listConref) {
		this.listConref = listConref;
	}

	public List<String> getListConref() {
		return listConref;
	}

	public void setAllJoblist(List<String> allJoblist) {
		this.allJoblist = allJoblist;
	}

	public List<String> getAllJoblist() {
		return allJoblist;
	}

	public void setListJoblist(List<String> listJoblist) {
		this.listJoblist = listJoblist;
	}

	public List<String> getListJoblist() {
		return listJoblist;
	}
	
}// End of class
