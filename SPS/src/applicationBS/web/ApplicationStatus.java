package applicationBS.web;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import job.model.Pcestdmt;
import job.model.Pcesthmt;
import job.model.Spestcnd;
import job.model.Spestcnt;
import job.model.SpestcntPK;
import job.model.Spexpjob;
import job.model.Spodrcrd;
import job.model.SpodrcrdPK;
import job.service.ExportEjb;
import job.service.PcestdmtEjb;
import job.service.PcesthmtEjb;
import job.service.SpestcndEjb;
import job.service.SpestcntEjb;
import job.service.SpexpjobEjb;
import job.service.SpodrcrdEjb;
 

import masters.service.ProvinceDetailsMasterEjb;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import piv.model.PivDetail;
import piv.service.PivDetailEjb;
import sun.font.EAttribute;
import util.common.ApplicationSubType;
import util.common.ApplicationType;
import util.common.Constants;
import util.common.EstimateStatus;
import util.common.PIVStatus;
import util.common.ReferenceType;
import util.common.StandardEstimateStatus;
import application.model.Applicant;
import application.model.Application;
import application.model.ApplicationPK;
import application.model.ApplicationReference;
import application.model.ApplicationReferencePK;
import application.model.Spapplon;
import application.model.SpapplonPK;
import application.model.WiringLandDetail;
import application.model.WiringLandDetailCon;
import application.model.WiringLandDetailConPK;
import application.model.WiringLandDetailPK;
import application.service.ApplicantEjb;
import application.service.ApplicationEjb;
import application.service.ApplicationReferenceEjb;
import application.service.SpapplonEjb;
import application.service.WiringLandDetailConEjb;
import application.service.WiringLandDetailEjb;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.bcel.internal.generic.NEW;

import costcenter.model.Area;

import estimate.model.Approval;
import estimate.model.EstimateReference;
import estimate.model.EstimateReferencePK;
import estimate.model.Pcestdtt;
import estimate.model.Pcesthtt;
import estimate.model.PcesthttPK;
import estimate.model.Spestedy;
import estimate.model.Speststd;
import estimate.model.SpeststdPK;
import estimate.model.Spstdesthmt;
import estimate.model.SpstdesthmtPK;
import estimate.service.ApprovalEjb;
import estimate.service.EstimateReferenceEjb;
import estimate.service.PcestdttEjb;
import estimate.service.PcesthttEjb;
import estimate.service.SpestedyEjb;
import estimate.service.SpeststdEjb;
import estimate.service.SpstdesthmtEjb;

public class ApplicationStatus extends ActionSupport implements SessionAware, ParameterAware   {

	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private static final String viewPath="Application>Status";
	private String path;
	private String lblError = null;
	private String lblSuccess = null;
	private String costCenter;
	private String appID;

	private String   defaultString = "  ---  ";

	private String img_piv1;

	private String img_piv1Paid;
	private String img_siteVisited;
	private String img_serviceEstimate;
	private String img_estimateApproval;
	private String img_piv2 ;
	private String img_piv2Paid;
	private String img_jobCreated;
	private String img_enagized;
	private String img_piv3 ;
	private String img_piv3Paid;
	//private String img_jobCreated;
	//private String img_enagized;
	private String img_piv2_loan ;
	private String img_piv2LoanPaid;
	private String img_hasAppliedForLoan;
	private String img_contractorAllocated;
	private String img_billExported;
	private String piv1Date = defaultString;
	private String piv1PaidDate = defaultString;
	private String piv1Amount = defaultString;
	private String siteVisitedDate = defaultString;
	private String siteVisitingFailedReason = defaultString;

	private String estimateDate = defaultString;
	private String estimateAmount = defaultString;
	private String estimateStatus = defaultString;

	private String piv2Date = defaultString;
	private String piv2PaidDate = defaultString;
	private String piv2Amount = defaultString;
	private String piv2Date_loan = defaultString;
	private String piv2PaidDate_loan = defaultString;
	private String piv2Amount_loan = defaultString;
	private String piv3Date = defaultString;
	private String piv3PaidDate = defaultString;

	private String piv3Amount = defaultString;
	private String piv3Number = defaultString;
	private String enagizedDate = defaultString;
	private String piv1Number = defaultString;
	private String piv2Number = defaultString;
	private String piv2Number_loan = defaultString;
	private String applicationNumber = defaultString;
	private String estimateNumber = defaultString;
	private String projectNumber = defaultString;

	private String isMemberOfSamurdhi = defaultString;
	private String sharePrice = defaultString;
	private String noOfShares = defaultString;
	private String applicantName = defaultString;
	private String applicantAddress = defaultString;
	private String applicantNIC = defaultString;

	private List<PivDetail> reinspectionPIVList;
	private String applicatinDetails= defaultString;
	private String appoinmentStatus= defaultString;
	private String contractorName= defaultString;

	// modification Octobor 21
	private String applicationType= defaultString;
	private String serviceAddress= defaultString;
	private String submitDate= defaultString;
	private String assesmentNumber;
	private String neighbourAccNumber;
	private String existingAccNumber;
	private String noOfDamageMeters;

	private String piv1PrintedPIVNumber;
	private String piv1BankCodeBranchCode;
	private String piv1PaymentMode;
	private String piv1ChequeDateNumber;
	private String piv3BankCodeBranchCode;

	private String piv3PaymentMode;
	
	private String piv3ChequeDateNumber;
	
	private String allocatedDate;
	private String allocatedTo;
	private String allocatedBy;

	private List<Pcestdtt> materialDetails;
	private List<EstimateReference> estimateDetails;
	private String detailCost;

	// standard estimate details (qutation details)

	private String rebateCost;
	private String variableCost;
	private String securityDeposit;
	private double estimateCost;
	private String cebCost;
	private String finalEstimatedCost;

	// piv 2 details
	private String piv2PrintedPIVNumber;
	private String piv2BankCodeBranchCode;
	private String piv2PaymentMode;
	private String piv2ChequeDateNumber;
	private String piv3PrintedPIVNumber;

	private String jobStatus;
	private String jobCreatedDate;
	private List<Pcestdmt> jobMaterialDetails;
	private String jobMaterialCost;


	private String contractorAllocatedDate;
	private String finishedDate; // this is the date where user finished the job using the system. actual finished date may be vary.

	private String billExportedDate;
	private String accountNumber;
	private String accountCreatedDate;
	private List<PivDetail> pivHistoryList;
	private List<Approval> approvalHistory;
	
	private String statusWorkES;
	private String statusStandardES;
	
	private String representative;
	private String representativeContact;
	private String areaName;
	private String district;
	private String deviSectrait;
	private String gsDivision;
	
	private String electorate;
	private String serviceDeponame;
	private String contractDemand;
	private String stdEstimateStatus;
	
	NumberFormat nf = NumberFormat.getInstance();


	public String execute(){
		setLblError(null);

		setLoggedInUserId(getSession().get("userName").toString());
		setPath(viewPath);

		setCostCenter(getSessionKey("costCenterNo"));
		setImageURL();
		nf.setGroupingUsed(true);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		setFromApplicationSearch();


		return SUCCESS;
	}

	public void setFromApplicationSearch(){

		HttpServletRequest request = ServletActionContext.getRequest();
		String selectedApplicationId=request.getParameter("applicationId");
        System.out.println("selectedApplicationId"+selectedApplicationId);
		if(selectedApplicationId!=null && selectedApplicationId!=""){
			appID = selectedApplicationId.trim();

			showSearchDetails(); 
		}


	}

	public String Search(){
		// test with this app id
		//ACR/510.20/2011/0005 
		execute();
		return showSearchDetails();
	}

	private String showSearchDetails()
	{
		String yPath = "../../image/Y.png";
		ProvinceDetailsMasterEjb promasEjb = new ProvinceDetailsMasterEjb();
 		
		String commercialId = "";
		String costCentre = getCostCenter();
		try{
		   commercialId = promasEjb.getCommercialId(costCentre,  getSessionKey("region"));
		}catch(Exception e){
			 return ERROR ;
		}
		if(commercialId != null){
			costCentre = commercialId;
		}
		/*String costCentreNo = costCentre.substring(0, 3);
		costCentre = costCentreNo.concat(".00");*/
		ApplicationEjb ejb = new ApplicationEjb(getSessionKey("region"));	
		ApplicationPK pk = new ApplicationPK();
		pk.setApplicationId(appID);
		pk.setDeptId(costCentre);

		Application application = ejb.findByAppId(pk);
		if (application == null)
		{
			return SUCCESS;
		}else
		{

			//application details
			applicationType = ApplicationType.getApplicationTypeDesc(application.getApplicationType()) + "/" + ApplicationSubType.getApplicationTypeDesc(application.getApplicationSubType());
			submitDate = application.getSubmitDate().toString() + "/";
			if (application.getConfirmedDate()!= null)
				submitDate = submitDate +  application.getConfirmedDate().toString();
			else 
				submitDate = submitDate + defaultString;
			// PIV 01 detials
			PivDetailEjb ejb_PIV_Detail = new PivDetailEjb(getSessionKey("region"));				 
			PivDetail pivDetail = ejb_PIV_Detail.findByReferenceNo(costCentre, appID,ReferenceType.APPLICATION); //APP
			
			
			ApplicationReferencePK appref = new ApplicationReferencePK();
			appref.setApplicationId(appID);
			appref.setDeptId(costCentre);
			ApplicationReferenceEjb appReferenceEjb=new ApplicationReferenceEjb(getSessionKey("region"));
			ApplicationReference applicationReference = appReferenceEjb.findByAppId(appref);
			String appNo = null;
			if(applicationReference != null){
				appNo = applicationReference.getApplicationNo();
				projectNumber = applicationReference.getProjectno();
			}
			
			if (pivDetail != null)
			{
				img_piv1 = yPath;
				piv1Date = pivDetail.getPivDate().toString();
				piv1Amount =  nf.format(pivDetail.getPivAmount() ); 
				Date paidDate = pivDetail.getPaidDate();
				piv1Number = pivDetail.getId().getPivNo();
				if (pivDetail.getStatus().equals(PIVStatus.CONFIRMED))
				{					
					img_piv1Paid = yPath;
					if(paidDate != null){
						piv1PaidDate = paidDate.toString();
					}
					

					// appNo = application.getApplicationNo();	
				}

				piv1PrintedPIVNumber = pivDetail.getPivReceiptNo();
				piv1BankCodeBranchCode = pivDetail.getBankCode()  + " / " +  pivDetail.getBranchCode();

				piv1PaymentMode = pivDetail.getPaymentMode();
				if (pivDetail.getChequeDate() != null)
					piv1ChequeDateNumber = pivDetail.getChequeDate().toString();

				if (pivDetail.getChequeNo() != null)
					piv1ChequeDateNumber = piv1ChequeDateNumber + " / " + pivDetail.getChequeNo().toString();


			}

			//String isLoanApp = application.getIsLoanApp();
			

			if (appNo == null)
				return SUCCESS;

			WiringLandDetailEjb ejb_wiring_land_detial = new WiringLandDetailEjb(getSessionKey("region"));
			WiringLandDetailPK wiringPK = new WiringLandDetailPK();
			wiringPK.setApplicationId(appID);
			wiringPK.setDeptId(costCentre);
			WiringLandDetail wiringLandDetail = ejb_wiring_land_detial.findByAppId(wiringPK);
			if (wiringLandDetail != null)
			{
				// print address 
				String tempServiceStreetAddress,tempServiceSuburb,tempServiceCity = "";
				tempServiceStreetAddress = wiringLandDetail.getServiceStreetAddress(); 
				tempServiceSuburb = wiringLandDetail.getServiceSuburb()  ;
				tempServiceCity = wiringLandDetail.getServiceCity();




				if (tempServiceStreetAddress != null && (! (tempServiceStreetAddress.trim().isEmpty())) )
				{
					serviceAddress = tempServiceStreetAddress + ", ";

				}

				if (tempServiceSuburb   != null && ((!tempServiceSuburb.trim().isEmpty())) )
				{
					serviceAddress += tempServiceSuburb + ", ";
				}

				if (tempServiceCity   != null && ( ( ! tempServiceCity.trim().isEmpty())) )
				{
					serviceAddress += tempServiceCity + ".";
				}
				applicatinDetails = wiringLandDetail.getPhase().toString() + " Phase /" + wiringLandDetail.getConnectionType() + "A /" + wiringLandDetail.getTariffCatCode() + " /" + wiringLandDetail.getTariffCode();

				neighbourAccNumber = wiringLandDetail.getNeighboursAccNo();
				assesmentNumber = wiringLandDetail.getAssessmentNo();
				existingAccNumber = wiringLandDetail.getExistingAccNo();
				if (wiringLandDetail.getNoOfDmgMeters() != null  )
					noOfDamageMeters = wiringLandDetail.getNoOfDmgMeters().toString();
			}
			WiringLandDetailConEjb ejb_wiring_land_detialCon = new WiringLandDetailConEjb(getSessionKey("region"));
			WiringLandDetailConPK wiringconPK = new WiringLandDetailConPK();
			wiringconPK.setApplicationId(appID);
			wiringconPK.setDeptId(costCentre);
			WiringLandDetailCon wiringLandDetailcon = ejb_wiring_land_detialCon.findByAppId(wiringconPK);
			
			areaName = wiringLandDetailcon.getAreaCode();
			gsDivision = wiringLandDetailcon.getAgaDivision();
			
		
			
			district = wiringLandDetailcon.getDistrict();
			representative = wiringLandDetailcon.getRepresentative();
			representativeContact = wiringLandDetailcon.getRepContact1();
			district = wiringLandDetailcon.getDistrict();
			deviSectrait = wiringLandDetailcon.getDevSec();
			electorate = wiringLandDetailcon.getElectorate();
			serviceDeponame=wiringLandDetailcon.getServiceDepoName();
			if(wiringLandDetail.getDemand() != null){
				contractDemand=wiringLandDetail.getDemand().toString();
			}
			if (wiringLandDetailcon.getDistrict() != null  ){
				district = wiringLandDetailcon.getDistrict();
			}
			if (wiringLandDetailcon.getAreaCode() != null  ){
				areaName = wiringLandDetailcon.getAreaCode();
			}
			if (wiringLandDetailcon.getAgaDivision() != null  ){
				gsDivision = wiringLandDetailcon.getAgaDivision();
			}
			if (wiringLandDetailcon.getRepresentative() != null  ){
				representative = wiringLandDetailcon.getRepresentative();
			}
			if (wiringLandDetailcon.getRepContact1() != null  ){
				representativeContact = wiringLandDetailcon.getRepContact1();
			}
			if (wiringLandDetailcon.getDevSec() != null  ){
				deviSectrait =wiringLandDetailcon.getDevSec();
			}
			if ( wiringLandDetailcon.getElectorate() != null  ){
				electorate = wiringLandDetailcon.getElectorate();
			}
			if (wiringLandDetailcon.getServiceDepoName()!= null  ){
				serviceDeponame =wiringLandDetailcon.getServiceDepoName();
			}
			if (wiringLandDetail.getDemand()!= null  ){
				contractDemand =wiringLandDetail.getDemand().toString();
			}
			
			applicationNumber = application.getApplicationNo();
			// get applican detials
			ApplicantEjb ejb_applicant = new ApplicantEjb(getSessionKey("region"));
			Applicant applicant = ejb_applicant.findById(application.getIdNo());
			if (applicant != null)
			{
				applicantName = applicant.getFirstName() + " " + applicant.getLastName();


				// print address 
				String tempStreetAddress,tempSuburb,tempCity = "";
				tempStreetAddress = applicant.getStreetAddress();
				tempSuburb = applicant.getSuburb()   ;
				tempCity = applicant.getCity();




				if (tempStreetAddress != null && (! (tempStreetAddress.trim().isEmpty())) )
				{
					applicantAddress = tempStreetAddress + ", ";

				}

				if (tempSuburb   != null && ((!tempSuburb.trim().isEmpty())) )
				{
					applicantAddress += tempSuburb + ", ";
				}

				if (tempCity   != null && ( ( ! tempCity.trim().isEmpty())) )
				{
					applicantAddress += tempCity + ".";
				}

				applicantNIC = applicant.getIdNo();



			}



			// site visiting details
			SpestedyEjb ejb_spestedy = new SpestedyEjb(getSessionKey("region"));
			Spestedy spestedy = ejb_spestedy.getAppointment(costCentre, appNo);
			if (spestedy != null)
			{				
				String status = spestedy.getStatus(); // F - VISITED BUT ESTIMATION FAILED, V - VISITED AND SUCCESSFLY ESTIMATED				
				if (status.equals("S") )
				{
					img_siteVisited =  yPath;

					siteVisitingFailedReason = spestedy.getDescription();

				}
				siteVisitedDate = spestedy.getAppointmentDate().toString();			
				if (status.equals("A"))
					appoinmentStatus = "NOT VISITED";
				if (status.equals("E"))
					appoinmentStatus = "VISITED AND SUCCESSFLY ESTIMATED";
				if (status.equals("F"))
					appoinmentStatus = "VISITED BUT ESTIMATION FAILED";
				if (status.equals("V"))
					appoinmentStatus = "VISITED AND SERVICE ESTIMATE CREATED";

				if (spestedy.getAllocatedDate() != null)
					allocatedDate = spestedy.getAllocatedDate().toString();
				allocatedBy = spestedy.getAllocatedBy();
				allocatedTo = spestedy.getAllocatedTo();

			}

			
			// re inspection details
			reinspectionPIVList = ejb_PIV_Detail.getPivDetailByRefNo(costCentre, appNo, ReferenceType.REINSP);//.findByReferenceNo(costCentre, appID, "RIP");

			
			
			// check for PIV 2
			PivDetailEjb ejb_PIV2_Detail = new PivDetailEjb(getSessionKey("region"));				 
			PivDetail piv2Detail = ejb_PIV2_Detail.findByReferenceNo(costCentre, appNo,ReferenceType.ESTIMATE);// "EST"
			if (piv2Detail != null)
			{				
				img_piv2 = yPath;
				piv2Date = piv2Detail.getPivDate().toString();
				piv2Amount =  nf.format( piv2Detail.getPivAmount( ));  
				Date paidDate = piv2Detail.getPaidDate();
				piv2Number = piv2Detail.getId().getPivNo();
				if  (piv2Detail.getStatus().equals(PIVStatus.PAID) || piv2Detail.getStatus().equals(PIVStatus.CONFIRMED) )
				{
					img_piv2Paid = yPath;
					if(paidDate != null){
						piv2PaidDate = paidDate.toString();
					}
					
				}

				piv2PrintedPIVNumber = piv2Detail.getPivReceiptNo();
				piv2BankCodeBranchCode = piv2Detail.getBankCode()  + " / " +  piv2Detail.getBranchCode();

				piv2PaymentMode = piv2Detail.getPaymentMode();
				if (piv2Detail.getChequeDate() != null)
					piv2ChequeDateNumber = piv2Detail.getChequeDate().toString();

				if (piv2Detail.getChequeNo() != null)
					piv2ChequeDateNumber = piv2ChequeDateNumber + " / " + piv2Detail.getChequeNo().toString();
			}
			// get PIV02 loan detials

			piv2Detail = ejb_PIV2_Detail.findByReferenceNo(costCentre, appNo,ReferenceType.LOAN);// "ELN"
			if (piv2Detail != null)
			{				
				img_piv2_loan = yPath;
				piv2Date_loan = piv2Detail.getPivDate().toString();
				piv2Amount_loan =  nf.format( piv2Detail.getPivAmount( ));  
				Date paidDate = piv2Detail.getPaidDate();
				piv2Number_loan = piv2Detail.getId().getPivNo();
				if  (piv2Detail.getStatus().equals(PIVStatus.PAID) || piv2Detail.getStatus().equals(PIVStatus.CONFIRMED) )
				{					
					img_piv2LoanPaid = yPath;
					if ( paidDate != null)
						piv2PaidDate_loan = paidDate.toString();
				}
			}
			

			BigDecimal estTot = new BigDecimal(0);
			BigDecimal pivIITot = new BigDecimal(0);
			// Standard Estimate (qutation value)
			SpstdesthmtPK spstdesthmtPK = new SpstdesthmtPK();
			spstdesthmtPK.setDeptId(costCentre.trim());
			spstdesthmtPK.setStdNo(appNo.trim());	
			spstdesthmtPK.setApplicationNo(appNo.trim());
			SpstdesthmtEjb spstdesthmtEjb = new SpstdesthmtEjb(getSessionKey("region"));
			Spstdesthmt spstdesthmt = spstdesthmtEjb.findById(spstdesthmtPK, getSessionKey("region"));
			String postDeptId = null;
			String con_MainCostCenter = null;
			if (spstdesthmt != null)
			{	postDeptId = spstdesthmt.getPostDeptId();
				setStatus(spstdesthmt);
				img_serviceEstimate = yPath; // estimate created
				if(spstdesthmt.getTotalCost()!=null){
					estimateAmount = nf.format(spstdesthmt.getTotalCost()); 
					estimateCost = estimateCost + spstdesthmt.getTotalCost().doubleValue();
				//if(spstdesthmt.getFixedCost()!=null)
				//	fixedCost = nf.format(spstdesthmt.getFixedCost()); 
				}
				if(spstdesthmt.getSecDeposit()!=null){
					securityDeposit = nf.format(spstdesthmt.getSecDeposit()); 
					estimateCost = estimateCost + spstdesthmt.getSecDeposit().doubleValue();
				}
				if(spstdesthmt.getRebateCost()!=null){
					rebateCost = nf.format(spstdesthmt.getRebateCost());
					estimateCost = estimateCost - spstdesthmt.getRebateCost().doubleValue();
				}
				if(spstdesthmt.getCebCost()!=null){
					cebCost = nf.format(spstdesthmt.getCebCost()); 
					estimateCost = estimateCost - spstdesthmt.getCebCost().doubleValue();
					
				}
				finalEstimatedCost = nf.format(estimateCost); 
				con_MainCostCenter= spstdesthmt.getPostDeptId();
				//stdEstimateStatus.setStdEstimateStatus();
				
			}
			
			/*EstimateReferencePK estimate = new EstimateReferencePK();
			estimate.setDeptId(spstdesthmt.getPostDeptId());
			estimate.setStandardEstimateNo(appNo.trim());*/
			
			List<PivDetail> piv1 =  ejb_PIV_Detail. getPivDetailByRefNo(costCentre, appID);//  
			List<PivDetail> piv2 = ejb_PIV_Detail.getPivDetailByRefNo(costCentre, appNo);//  
			//List<PivDetail> piv3 = ejb_PIV_Detail.getPivDetailByRefNo(costCentre, projectNumber.trim());
			pivHistoryList =new   ArrayList<PivDetail>();
			pivHistoryList.addAll(piv1);
			pivHistoryList.addAll(piv2);
			
			EstimateReferenceEjb refejb = new EstimateReferenceEjb();
			List<EstimateReference> estimteRefer = null;
			try {
				estimteRefer = refejb.getWorkEstimatesBySEstimateNo(appNo.trim(),con_MainCostCenter, getSessionKey("region"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			estimateDetails = estimteRefer;
			if(estimteRefer == null){
				return SUCCESS;
			
			}
			projectNumber = estimteRefer.get(0).getProjectno();
			// check for estimates
			PcesthttEjb ejb_pcesthtt = new PcesthttEjb(getSessionKey("region"));
			//Pcesthtt pcesthtt = ejb_pcesthtt.findById(pcesthttpk);
			Pcesthtt pcesthtt = null;
			long revNo = 2L;
			//long iteration = 1;
			for(EstimateReference esti : estimteRefer){
					PcesthttPK pcesthttpk = new PcesthttPK();
					pcesthttpk.setDeptId(spstdesthmt.getPostDeptId());
					pcesthttpk.setEstimateNo(esti.getId().getWorkEstimateNo().trim());
					pcesthttpk.setRevNo(revNo);
					
					pcesthtt = ejb_pcesthtt.findById(pcesthttpk);
					if (pcesthtt != null)
					{				
						img_serviceEstimate = yPath; // estimate created
						estimateDate = pcesthtt.getEtimateDt().toString();
						//estimateAmount =  nf.format( pcesthtt.getStdCost() ); 


						estimateNumber = pcesthtt.getId().getEstimateNo();
						String estStatus = pcesthtt.getStatus().toString() ;

						if (estStatus.equals(EstimateStatus.EST_APPROVED) || estStatus.equals(EstimateStatus.EST_POSTING) || estStatus.equals(EstimateStatus.EST_POSTED) )
						{
							ApprovalEjb approvalEjb = new ApprovalEjb(getSessionKey("region"));

							// approval sequence
							img_estimateApproval = yPath; 
						}
						EstimateStatus estSt = new EstimateStatus();
						estimateStatus =estSt.searchStatus((Integer.parseInt(estStatus)));
						if(pcesthtt.getStdCost()!=null)
							detailCost = nf.format(pcesthtt.getStdCost()); 
						// GET MATERIAL DETAILS
						PcestdttEjb pcestdttEjb = new PcestdttEjb(getSessionKey("region"));
						
						//materialDetails = pcestdttEjb.findByEstimationNo(estimateNumber,con_MainCostCenter,revNo-1);
						if(materialDetails != null){
							materialDetails.addAll( pcestdttEjb.findByEstimationNo(estimateNumber,con_MainCostCenter,revNo));

						}else{
							materialDetails =  new ArrayList<Pcestdtt>();

						}
						
					



					}

				
			}
			
			



			// check for job details
			PcesthmtEjb ejb_pcesthmt = new PcesthmtEjb(getSessionKey("region"));
			Pcesthmt pcesthmt = ejb_pcesthmt.findByEstimationNo(estimteRefer.get(0).getId().getWorkEstimateNo().trim());
			if (pcesthmt != null)
			{			
				estimateNumber = pcesthmt.getId().getEstimateNo();
				img_jobCreated = yPath; // estimate created
				String jobNo = pcesthmt.getProjectNo();
				
				if (pcesthmt.getPrjAssDt() != null)
					jobCreatedDate = pcesthmt.getPrjAssDt().toString(); // the date when the project number was created

				jobMaterialCost = nf.format(pcesthmt.getStdCost()); 
				// GET Job MATERIAL DETAILS
				PcestdmtEjb  pcestdmtEjb = new  PcestdmtEjb(getSessionKey("region"));
				jobMaterialDetails = pcestdmtEjb.findByEstimationNo(estimateNumber);

				String jobSts =pcesthmt.getStatus().toString() ;
				EstimateStatus estSt = new EstimateStatus();
				jobStatus =estSt.searchStatus((Integer.parseInt(jobSts)));


				SpestcndEjb ejb_spestcnd = new SpestcndEjb(getSessionKey("region"));
				if(postDeptId != null){
					List<Spestcnd> spestcnd = ejb_spestcnd.findByJobNo(jobNo.trim(), postDeptId);
					if (spestcnd.size() > 0)
					{
						Spestcnd spestcndx = spestcnd.get(0);
						String sts = spestcndx.getStatus();
	
						if (spestcndx.getAllocatedDate() != null)
							contractorAllocatedDate = spestcndx.getAllocatedDate().toString();
						if (spestcndx.getFinishedDate() != null)
							finishedDate = spestcndx.getFinishedDate().toString();
						String isExported = spestcndx.getIsExported();
						if (isExported != null)
						{
							if (isExported.equals("Y"))
							{
								// bill has expoted
								img_billExported = yPath;
								
								ExportEjb exportEjb = new ExportEjb(getSessionKey("region"));
	
								Spexpjob spexpjob =   exportEjb.findByJobNo(jobNo.trim()) ;
								if (spexpjob != null)
								{
									if (spexpjob.getExportedDate() != null)
										billExportedDate = spexpjob.getExportedDate().toString() ;
									if (spexpjob.getAccCreatedDate() != null)
										accountCreatedDate = spexpjob.getAccCreatedDate().toString();
									if (spexpjob.getAccountNo() != null)
										accountNumber = spexpjob.getAccountNo().toString();
								}
	
	
	
	
	
							}
						}
						if (sts != null)
						{		
							if (sts.equals("F") || sts.equals("B")  )
							{
								img_enagized = yPath;
							}
	
	
							SpodrcrdEjb ejb_spodrcrd = new SpodrcrdEjb(getSessionKey("region"));
	
	
							SpodrcrdPK spodrcrdPK = new SpodrcrdPK();
							spodrcrdPK.setDeptId(costCentre);
							spodrcrdPK.setProjectNo(spestcndx.getId().getProjectNo().trim());
							Spodrcrd spodrcrd =  ejb_spodrcrd.findById(spodrcrdPK) ;
							if (spodrcrd != null)
							{
								Date dtConnected = spodrcrd.getConnectedDate();
								if (dtConnected != null)
								{
									enagizedDate =  dtConnected.toString();
								}
							}
	
	
						}
						SpestcntEjb ejb_spestcnt = new  SpestcntEjb(getSessionKey("region"));
						SpestcntPK spestcntPK = new SpestcntPK();
						spestcntPK.setDeptId(postDeptId);
						spestcntPK.setContractorId(spestcndx.getId().getContractorId());
						Spestcnt spestcnt = ejb_spestcnt.findById(spestcntPK); 
						img_contractorAllocated = yPath;
						contractorName = spestcnt.getContractorName();
	
	
					}
				}

			}
			// PIV III Details
			/*piv2Detail = ejb_PIV2_Detail.findByReferenceNo(costCentre, projectNumber.trim(),ReferenceType.JOB);// "ELN"
			if (piv2Detail != null)
			{				
				img_piv3 = yPath;
				piv3Date = piv2Detail.getPivDate().toString();
				piv3Amount =  nf.format( piv2Detail.getPivAmount( ));  



				piv2Amount =  nf.format(pivIITot) ;


				Date paidDate = piv2Detail.getPaidDate();
				piv3Number = piv2Detail.getId().getPivNo();
				if  (piv2Detail.getStatus().equals(PIVStatus.PAID) || piv2Detail.getStatus().equals(PIVStatus.CONFIRMED) )
				{
					img_piv3Paid = yPath;
					piv3PaidDate = paidDate.toString();
				}

				piv3PrintedPIVNumber = piv2Detail.getPivReceiptNo();
				piv3BankCodeBranchCode = piv2Detail.getBankCode()  + " / " +  piv2Detail.getBranchCode();

				piv3PaymentMode = piv2Detail.getPaymentMode();
				if (piv2Detail.getChequeDate() != null)
					piv3ChequeDateNumber = piv2Detail.getChequeDate().toString();

				if (piv2Detail.getChequeNo() != null)
					piv3ChequeDateNumber = piv3ChequeDateNumber + " / " + piv2Detail.getChequeNo().toString();
			}*/

			// piv history
			
			//pivHistoryList.addAll(piv3);
			approvalHistory =new   ArrayList<Approval>();
			ApprovalEjb ejb_approval = new ApprovalEjb(getSessionKey("region"));
			
			List<Approval> approval1 = ejb_approval.findByReferenceNo(appNo);
			approvalHistory.addAll(approval1);
			if(estimateNumber != null){
				List<Approval> approval2 = ejb_approval.findByReferenceNo(estimateNumber.trim());
				approvalHistory.addAll(approval2);
			}
			
			
			


		}
		return SUCCESS;
	}
	private void setImageURL()
	{

		String xPath = "../../image/X.png";
		img_piv1 = xPath;
		img_piv1Paid = xPath;
		img_siteVisited = xPath;
		img_serviceEstimate = xPath;
		img_estimateApproval = xPath;
		img_piv2 = xPath;
		img_piv2Paid = xPath;
		img_jobCreated = xPath;
		img_enagized = xPath;
		img_piv2_loan = xPath;
		img_piv2LoanPaid = xPath;
		img_hasAppliedForLoan = xPath;
		img_contractorAllocated = xPath;
		img_billExported = xPath;
	}

	public String getSessionKey(String key) {
		return getSession().get(key).toString();
	}
	public String Close(){
		return "close";
	}

	
	public String setStatus(Spstdesthmt hmt){
		if(hmt.getStatus() == 1){
			statusStandardES = StandardEstimateStatus.NEW_APPLICATION.getName();
		}else if(hmt.getStatus() == 2){
			statusStandardES = StandardEstimateStatus.APPLICATION_MODIFIED.getName();
		}else if(hmt.getStatus() == 8){
			statusStandardES = StandardEstimateStatus.TO_BE_CHECKED_BY_ELECTRICAL_SUPIRINTENDENT.getName();
		}else if(hmt.getStatus() == 10){
			statusStandardES = StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getName();
		}else if(hmt.getStatus() == 5){
			statusStandardES = StandardEstimateStatus.REJECTED_BY.getName();
		}else if(hmt.getStatus() == 20){
			statusStandardES = StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getName();
		}else if(hmt.getStatus() == 35){
			statusStandardES = StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_CE.getName();
		}else if(hmt.getStatus() == 30){
			statusStandardES = StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getName();
		}else if(hmt.getStatus() == 40){
			statusStandardES = StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getName();
		}else if(hmt.getStatus() == 50){
			statusStandardES = StandardEstimateStatus.APPROVED_ESTIMATES.getName();
		}else if(hmt.getStatus() == 55){
			statusStandardES = StandardEstimateStatus.PIVII_TO_BE_CONFIRMED_BY_ACCOUNT_REV.getName();
		}else if(hmt.getStatus() == 56){
			statusStandardES = StandardEstimateStatus.PIVII_TO_BE_CONFIRMED_BY_DEO.getName();
		}else if(hmt.getStatus() == 60){
			statusStandardES = StandardEstimateStatus.PIVII_CONFIRMATION.getName();
		}else if(hmt.getStatus() == 61){
			statusStandardES = StandardEstimateStatus.PIVII_FOR_SECDEPOSIT.getName();
		}else if(hmt.getStatus() == 70){
			statusStandardES = StandardEstimateStatus.SEND_FOR_CONSTRUCTION_MAINTENANCE.getName();
		}else if(hmt.getStatus() == 80){
			statusStandardES = StandardEstimateStatus.JOB_ALLOCATED.getName();
		}else{
			statusStandardES = "";
		}
		
		return statusStandardES;
		
	}
	

	public String getImg_piv1() {
		return img_piv1;
	}

	public void setImg_piv1(String img_piv1) {
		this.img_piv1 = img_piv1;
	}

	public String getImg_piv1Paid() {
		return img_piv1Paid;
	}

	public void setImg_piv1Paid(String img_piv1Paid) {
		this.img_piv1Paid = img_piv1Paid;
	}

	public String getImg_siteVisited() {
		return img_siteVisited;
	}

	public void setImg_siteVisited(String img_siteVisited) {
		this.img_siteVisited = img_siteVisited;
	}

	public String getImg_serviceEstimate() {
		return img_serviceEstimate;
	}

	public void setImg_serviceEstimate(String img_serviceEstimate) {
		this.img_serviceEstimate = img_serviceEstimate;
	}

	public String getImg_estimateApproval() {
		return img_estimateApproval;
	}

	public void setImg_estimateApproval(String img_estimateApproval) {
		this.img_estimateApproval = img_estimateApproval;
	}

	public String getImg_piv2() {
		return img_piv2;
	}

	public void setImg_piv2(String img_piv2) {
		this.img_piv2 = img_piv2;
	}

	public String getImg_piv2Paid() {
		return img_piv2Paid;
	}

	public void setImg_piv2Paid(String img_piv2Paid) {
		this.img_piv2Paid = img_piv2Paid;
	}

	public String getImg_jobCreated() {
		return img_jobCreated;
	}

	public void setImg_jobCreated(String img_jobCreated) {
		this.img_jobCreated = img_jobCreated;
	}

	public String getImg_enagized() {
		return img_enagized;
	}

	public void setImg_enagized(String img_enagized) {
		this.img_enagized = img_enagized;
	}

	public String getPiv1Date() {
		return piv1Date;
	}

	public void setPiv1Date(String piv1Date) {
		this.piv1Date = piv1Date;
	}

	public String getPiv1PaidDate() {
		return piv1PaidDate;
	}

	public void setPiv1PaidDate(String piv1PaidDate) {
		this.piv1PaidDate = piv1PaidDate;
	}
	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
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

	public String getAppID() {
		return appID.trim();
	}

	public void setAppID(String appID) {
		this.appID = appID.trim();
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public Map<String, String[]> getParameters() {
		return parameters;
	}

	public static String getViewpath() {
		return viewPath;
	}

	public String getSiteVisitingFailedReason() {
		return siteVisitingFailedReason;
	}

	public void setSiteVisitingFailedReason(String siteVisitingFailedReason) {
		this.siteVisitingFailedReason = siteVisitingFailedReason;
	}

	public String getSiteVisitedDate() {
		return siteVisitedDate;
	}

	public void setSiteVisitedDate(String siteVisitedDate) {
		this.siteVisitedDate = siteVisitedDate;
	}

	@Override
	public void setParameters(Map<String, String[]> parameters){
		// TODO Auto-generated method stub
		this.parameters=parameters;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session=session;
	}

	public String getPiv1Amount() {
		return piv1Amount;
	}

	public void setPiv1Amount(String piv1Amount) {
		this.piv1Amount = piv1Amount;
	}

	public String getEstimateDate() {
		return estimateDate;
	}

	public void setEstimateDate(String estimateDate) {
		this.estimateDate = estimateDate;
	}

	public String getEstimateAmount() {
		return estimateAmount;
	}

	public void setEstimateAmount(String estimateAmount) {
		this.estimateAmount = estimateAmount;
	}

	public String getEstimateStatus() {
		return estimateStatus;
	}

	public void setEstimateStatus(String estimateStatus) {
		this.estimateStatus = estimateStatus;
	}

	public String getPiv2Date() {
		return piv2Date;
	}

	public void setPiv2Date(String piv2Date) {
		this.piv2Date = piv2Date;
	}

	public String getPiv2PaidDate() {
		return piv2PaidDate;
	}

	public void setPiv2PaidDate(String piv2PaidDate) {
		this.piv2PaidDate = piv2PaidDate;
	}

	public String getPiv2Amount() {
		return piv2Amount;
	}

	public void setPiv2Amount(String piv2Amount) {
		this.piv2Amount = piv2Amount;
	}

	public String getEnagizedDate() {
		return enagizedDate;
	}

	public void setEnagizedDate(String enagizedDate) {
		this.enagizedDate = enagizedDate;
	}

	public String getPiv1Number() {
		return piv1Number;
	}

	public void setPiv1Number(String piv1Number) {
		this.piv1Number = piv1Number;
	}

	public String getPiv2Number() {
		return piv2Number;
	}

	public void setPiv2Number(String piv2Number) {
		this.piv2Number = piv2Number;
	}

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public String getEstimateNumber() {
		return estimateNumber;
	}

	public void setEstimateNumber(String estimateNumber) {
		this.estimateNumber = estimateNumber;
	}

	public String getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}

	public String getImg_piv2_loan() {
		return img_piv2_loan;
	}

	public void setImg_piv2_loan(String img_piv2_loan) {
		this.img_piv2_loan = img_piv2_loan;
	}

	public String getImg_piv2LoanPaid() {
		return img_piv2LoanPaid;
	}

	public void setImg_piv2LoanPaid(String img_piv2LoanPaid) {
		this.img_piv2LoanPaid = img_piv2LoanPaid;
	}

	public String getPiv2Date_loan() {
		return piv2Date_loan;
	}

	public void setPiv2Date_loan(String piv2Date_loan) {
		this.piv2Date_loan = piv2Date_loan;
	}

	public String getPiv2PaidDate_loan() {
		return piv2PaidDate_loan;
	}

	public void setPiv2PaidDate_loan(String piv2PaidDate_loan) {
		this.piv2PaidDate_loan = piv2PaidDate_loan;
	}

	public String getPiv2Amount_loan() {
		return piv2Amount_loan;
	}

	public void setPiv2Amount_loan(String piv2Amount_loan) {
		this.piv2Amount_loan = piv2Amount_loan;
	}

	public String getPiv2Number_loan() {
		return piv2Number_loan;
	}

	public void setPiv2Number_loan(String piv2Number_loan) {
		this.piv2Number_loan = piv2Number_loan;
	}

	public String getIsMemberOfSamurdhi() {
		return isMemberOfSamurdhi;
	}

	public void setIsMemberOfSamurdhi(String isMemberOfSamurdhi) {
		this.isMemberOfSamurdhi = isMemberOfSamurdhi;
	}

	public String getSharePrice() {
		return sharePrice;
	}

	public void setSharePrice(String sharePrice) {
		this.sharePrice = sharePrice;
	}

	public String getNoOfShares() {
		return noOfShares;
	}

	public void setNoOfShares(String noOfShares) {
		this.noOfShares = noOfShares;
	}

	public List<PivDetail> getReinspectionPIVList() {
		return reinspectionPIVList;
	}

	public void setReinspectionPIVList(List<PivDetail> reinspectionPIVList) {
		this.reinspectionPIVList = reinspectionPIVList;
	}



	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getApplicantAddress() {
		return applicantAddress;
	}

	public void setApplicantAddress(String applicantAddress) {
		this.applicantAddress = applicantAddress;
	}

	public String getApplicantNIC() {
		return applicantNIC;
	}

	public void setApplicantNIC(String applicantNIC) {
		this.applicantNIC = applicantNIC;
	}

	public String getImg_hasAppliedForLoan() {
		return img_hasAppliedForLoan;
	}

	public void setImg_hasAppliedForLoan(String img_hasAppliedForLoan) {
		this.img_hasAppliedForLoan = img_hasAppliedForLoan;
	}

	public String getApplicatinDetails() {
		return applicatinDetails;
	}

	public void setApplicatinDetails(String applicatinDetails) {
		this.applicatinDetails = applicatinDetails;
	}

	public String getAppoinmentStatus() {
		return appoinmentStatus;
	}

	public void setAppoinmentStatus(String appoinmentStatus) {
		this.appoinmentStatus = appoinmentStatus;
	}

	public String getImg_contractorAllocated() {
		return img_contractorAllocated;
	}

	public void setImg_contractorAllocated(String img_contractorAllocated) {
		this.img_contractorAllocated = img_contractorAllocated;
	}

	public String getContractorName() {
		return contractorName;
	}

	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}

	public String getImg_billExported() {
		return img_billExported;
	}

	public void setImg_billExported(String img_billExported) {
		this.img_billExported = img_billExported;
	}

	public String getStatusWorkES() {
		return statusWorkES;
	}

	public void setStatusWorkES(String statusWorkES) {
		this.statusWorkES = statusWorkES;
	}

	public String getStatusStandardES() {
		return statusStandardES;
	}

	public void setStatusStandardES(String statusStandardES) {
		this.statusStandardES = statusStandardES;
	}

	public String getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	public String getServiceAddress() {
		return serviceAddress;
	}

	public void setServiceAddress(String serviceAddress) {
		this.serviceAddress = serviceAddress;
	}

	public String getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}

	public String getAssesmentNumber() {
		return assesmentNumber;
	}

	public void setAssesmentNumber(String assesmentNumber) {
		this.assesmentNumber = assesmentNumber;
	}

	public String getNeighbourAccNumber() {
		return neighbourAccNumber;
	}

	public void setNeighbourAccNumber(String neighbourAccNumber) {
		this.neighbourAccNumber = neighbourAccNumber;
	}

	public String getExistingAccNumber() {
		return existingAccNumber;
	}

	public void setExistingAccNumber(String existingAccNumber) {
		this.existingAccNumber = existingAccNumber;
	}

	public List<EstimateReference> getEstimateDetails() {
		return estimateDetails;
	}

	public void setEstimateDetails(List<EstimateReference> estimateDetails) {
		this.estimateDetails = estimateDetails;
	}

	public String getNoOfDamageMeters() {
		return noOfDamageMeters;
	}

	public void setNoOfDamageMeters(String noOfDamageMeters) {
		this.noOfDamageMeters = noOfDamageMeters;
	}

	public String getPiv1PrintedPIVNumber() {
		return piv1PrintedPIVNumber;
	}

	public void setPiv1PrintedPIVNumber(String piv1PrintedPIVNumber) {
		this.piv1PrintedPIVNumber = piv1PrintedPIVNumber;
	}

	public String getPiv1BankCodeBranchCode() {
		return piv1BankCodeBranchCode;
	}

	public void setPiv1BankCodeBranchCode(String piv1BankCodeBranchCode) {
		this.piv1BankCodeBranchCode = piv1BankCodeBranchCode;
	}

	public String getPiv1PaymentMode() {
		return piv1PaymentMode;
	}

	public void setPiv1PaymentMode(String piv1PaymentMode) {
		this.piv1PaymentMode = piv1PaymentMode;
	}

	public String getPiv1ChequeDateNumber() {
		return piv1ChequeDateNumber;
	}

	public void setPiv1ChequeDateNumber(String piv1ChequeDateNumber) {
		this.piv1ChequeDateNumber = piv1ChequeDateNumber;
	}

	public String getAllocatedDate() {
		return allocatedDate;
	}

	public void setAllocatedDate(String allocatedDate) {
		this.allocatedDate = allocatedDate;
	}

	public String getAllocatedTo() {
		return allocatedTo;
	}

	public void setAllocatedTo(String allocatedTo) {
		this.allocatedTo = allocatedTo;
	}

	public String getAllocatedBy() {
		return allocatedBy;
	}

	public void setAllocatedBy(String allocatedBy) {
		this.allocatedBy = allocatedBy;
	}

	public List<Pcestdtt> getMaterialDetails() {
		return materialDetails;
	}

	public void setMaterialDetails(List<Pcestdtt> materialDetails) {
		this.materialDetails = materialDetails;
	}

	public String getDetailCost() {
		return detailCost;
	}

	public void setDetailCost(String detailCost) {
		this.detailCost = detailCost;
	}

	public String getDefaultString() {
		return defaultString;
	}

	public void setDefaultString(String defaultString) {
		this.defaultString = defaultString;
	}

	

	public String getVariableCost() {
		return variableCost;
	}

	public void setVariableCost(String variableCost) {
		this.variableCost = variableCost;
	}

	public String getSecurityDeposit() {
		return securityDeposit;
	}

	public void setSecurityDeposit(String securityDeposit) {
		this.securityDeposit = securityDeposit;
	}

	

	public String getRebateCost() {
		return rebateCost;
	}

	public void setRebateCost(String rebateCost) {
		this.rebateCost = rebateCost;
	}

	

	public double getEstimateCost() {
		return estimateCost;
	}

	public void setEstimateCost(double estimateCost) {
		this.estimateCost = estimateCost;
	}

	public String getCebCost() {
		return cebCost;
	}

	public void setCebCost(String cebCost) {
		this.cebCost = cebCost;
	}

	public String getPiv2PrintedPIVNumber() {
		return piv2PrintedPIVNumber;
	}

	public void setPiv2PrintedPIVNumber(String piv2PrintedPIVNumber) {
		this.piv2PrintedPIVNumber = piv2PrintedPIVNumber;
	}

	public String getPiv2BankCodeBranchCode() {
		return piv2BankCodeBranchCode;
	}

	public void setPiv2BankCodeBranchCode(String piv2BankCodeBranchCode) {
		this.piv2BankCodeBranchCode = piv2BankCodeBranchCode;
	}

	public String getPiv2PaymentMode() {
		return piv2PaymentMode;
	}

	public void setPiv2PaymentMode(String piv2PaymentMode) {
		this.piv2PaymentMode = piv2PaymentMode;
	}

	public String getPiv2ChequeDateNumber() {
		return piv2ChequeDateNumber;
	}

	public void setPiv2ChequeDateNumber(String piv2ChequeDateNumber) {
		this.piv2ChequeDateNumber = piv2ChequeDateNumber;
	}

	public String getJobCreatedDate() {
		return jobCreatedDate;
	}

	public void setJobCreatedDate(String jobCreatedDate) {
		this.jobCreatedDate = jobCreatedDate;
	}

	public List<Pcestdmt> getJobMaterialDetails() {
		return jobMaterialDetails;
	}

	public void setJobMaterialDetails(List<Pcestdmt> jobMaterialDetails) {
		this.jobMaterialDetails = jobMaterialDetails;
	}

	public String getJobMaterialCost() {
		return jobMaterialCost;
	}

	public void setJobMaterialCost(String jobMaterialCost) {
		this.jobMaterialCost = jobMaterialCost;
	}

	public String getContractorAllocatedDate() {
		return contractorAllocatedDate;
	}

	public void setContractorAllocatedDate(String contractorAllocatedDate) {
		this.contractorAllocatedDate = contractorAllocatedDate;
	}

	public String getFinishedDate() {
		return finishedDate;
	}

	public void setFinishedDate(String finishedDate) {
		this.finishedDate = finishedDate;
	}

	public String getBillExportedDate() {
		return billExportedDate;
	}

	public void setBillExportedDate(String billExportedDate) {
		this.billExportedDate = billExportedDate;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountCreatedDate() {
		return accountCreatedDate;
	}

	public void setAccountCreatedDate(String accountCreatedDate) {
		this.accountCreatedDate = accountCreatedDate;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getFinalEstimatedCost() {
		return finalEstimatedCost;
	}

	public void setFinalEstimatedCost(String finalEstimatedCost) {
		this.finalEstimatedCost = finalEstimatedCost;
	}

	public List<PivDetail> getPivHistoryList() {
		return pivHistoryList;
	}

	public void setPivHistoryList(List<PivDetail> pivHistoryList) {
		this.pivHistoryList = pivHistoryList;
	}

	public List<Approval> getApprovalHistory() {
		return approvalHistory;
	}

	public void setApprovalHistory(List<Approval> approvalHistory) {
		this.approvalHistory = approvalHistory;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getDeviSectrait() {
		return deviSectrait;
	}

	public void setDeviSectrait(String deviSectrait) {
		this.deviSectrait = deviSectrait;
	}

	public String getGsDivision() {
		return gsDivision;
	}

	public void setGsDivision(String gsDivision) {
		this.gsDivision = gsDivision;
	}

	public String getElectorate() {
		return electorate;
	}

	public void setElectorate(String electorate) {
		this.electorate = electorate;
	}

	public String getServiceDeponame() {
		return serviceDeponame;
	}

	public void setServiceDeponame(String serviceDeponame) {
		this.serviceDeponame = serviceDeponame;
	}

	

	public String getContractDemand() {
		return contractDemand;
	}

	public void setContractDemand(String contractDemand) {
		this.contractDemand = contractDemand;
	}

	public String getRepresentative() {
		return representative;
	}

	public void setRepresentative(String representative) {
		this.representative = representative;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getRepresentativeContact() {
		return representativeContact;
	}

	public void setRepresentativeContact(String representativeContact) {
		this.representativeContact = representativeContact;
	}

	public String getStdEstimateStatus() {
		return stdEstimateStatus;
	}

	public void setStdEstimateStatus(String stdEstimateStatus) {
		this.stdEstimateStatus = stdEstimateStatus;
	}







}
