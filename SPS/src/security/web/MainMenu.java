package security.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import job.model.Pcesthmt;
import job.service.JobEjb;
import job.service.PcesthmtEjb;

import masters.service.ProvinceDetailsMasterEjb;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import security.model.MsgPool;
import util.common.AppStatus;
import util.common.AppointmentStatus;
import util.common.Constants;
import util.common.EstimateStatus;
import util.common.StandardEstimateStatus;
import util.common.UserRole;

import application.model.Application;
import application.service.ApplicationEjb;

import com.opensymphony.xwork2.ActionSupport;

import costcenter.service.CostCenterEjb;

import estimate.ejb.EstimateBean;
import estimate.model.ApplicationDisplay;
import estimate.model.EstimateDisplay;
import estimate.model.Pcesthtt;
import estimate.model.Spestedy;
import estimate.model.SpestedyCons;
import estimate.model.Spstdesthmt;
import estimate.service.EstimateEjb;
import estimate.service.PcesthttEjb;
import estimate.service.SpestedyConEjb;
import estimate.service.SpestedyEjb;

@SuppressWarnings("serial")
public class MainMenu extends ActionSupport implements SessionAware, ParameterAware{
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private String loggedInCostCenterNo;
	private String loggedInUserLevel;
	private String costCenterNo;
	private String  errorMessage;
	private String  smcType;
	private String  branchType;
	private String  estimateType;
	private String  jobType;
	private List<EstimateDisplay> rejectedList;
	private List<Pcesthmt> rejectedRevisedJobsList;
	private List<EstimateDisplay> approveList;
	private List<EstimateDisplay> tobeValidateList;
	private int tobeValidateListCount;
	private List<Pcesthmt> approvedRevisedJobsList;
	private List<Pcesthmt> revisedJobsList;
	private List<Spestedy> ativeAppoinmentsList;
	private int ativeAppoinmentsListCount;
	private List<Spestedy> unAttendedAppoinments;
	private List<Spestedy> failedAppoinments;
	private List<SpestedyCons> ativeAppoinmentsListConMain;
	private int ativeAppoinmentsListConMainCount;
	private List<SpestedyCons> unAttendedAppoinmentsConMain;
	private List<SpestedyCons> failedAppoinmentsConMain;
	private List<EstimateDisplay> approvedEstimateList;
	private int approvedEstimateListCount;
	private List<Pcesthtt> tobeRecommendWorkEsList;
	private List<MsgPool> msgPoolList;
	public List<Pcesthtt> getTobeRecommendWorkEsList() {
		return tobeRecommendWorkEsList;
	}



	public void setTobeRecommendWorkEsList(List<Pcesthtt> tobeRecommendWorkEsList) {
		this.tobeRecommendWorkEsList = tobeRecommendWorkEsList;
	}

	private int tobeRecommendWorkEsListCount;

	

	public int getTobeRecommendWorkEsListCount() {
		return tobeRecommendWorkEsListCount;
	}



	public void setTobeRecommendWorkEsListCount(int tobeRecommendWorkEsListCount) {
		this.tobeRecommendWorkEsListCount = tobeRecommendWorkEsListCount;
	}

	private List<Pcesthtt> rejectedWorkEList;
	private int rejectedWorkEListCount;
	private List<Pcesthtt> approvedWorkEstimateList;
	private List<Pcesthtt> tobeValidateWorkEsList;
	private int tobeValidateWorkEsListCount;
	private List<Pcesthtt> tobeApprovedWorkEsList;
	private List<EstimateDisplay> needToIssuePivIIList;
	private int needToIssuePivIIListCount;
	
	private List<EstimateDisplay> needToConfirmByCashPivIIList;
	private List<EstimateDisplay> needToConfirmByChequePivIIList;
	private List<ApplicationDisplay> toBeAllocatedList;
	private int toBeAllocatedListCount;
	private List<Spstdesthmt> toBeAllocatedConMainList;
	
	private List<Spstdesthmt> toBeAllocatedPlanningList;
	private int toBeAllocatedConMainListCount;
	private int toBeAllocatedPLListCount;
	
	private List<EstimateDisplay> sEsendForValidationList;
	private List<Spstdesthmt> sEsendForConMainList;
	private int sEsendForConMainListCount;
	private List<Spstdesthmt> conMainSentList;
	private List<Pcesthmt> toBeApprovedRevisedJobsList;
	private List<Pcesthmt> tobeValidateRevisedJobsList;
	
	private List<Spestedy>  tobeEnteredStdEstimates;
	private int tobeEnteredStdEstimatesCount;
	private List<SpestedyCons>  tobeEnteredDetailEstimates;
	private int  tobeEnteredDetailEstimatesCount;
	private List<Pcesthtt>  tobeCreateJobNos;
	private List<Pcesthtt>  tobeCreateJobNosVSL;

	public String execute(){
		System.out.print(getErrorMessage());
		setErrorMessage(getErrorMessage());
		System.out.print(getSession());
		setLoggedData();
		
		System.out.print(getLoggedInUserLevel());
		if (getLoggedInUserLevel().equals("DEO") || getLoggedInUserLevel().equals("ADMIN"))
		{
			if( branchType.equalsIgnoreCase(Constants.BRANCHTYPE_COMMERCIAL)){
					setPIVIIList();
					setPIVIIListConfirmCashPayment();
			}
		}
		if (getLoggedInUserLevel().equals("ACCNTREV") || getLoggedInUserLevel().equals("ADMIN"))
		{
			if(  branchType.equalsIgnoreCase(Constants.BRANCHTYPE_COMMERCIAL)){
					setPIVIIListConfirmChequePayment();
			}
		}
		//if (getLoggedInUserLevel().equals("DEO") || getLoggedInUserLevel().equals("ES") ||getLoggedInUserLevel().equals("CE") || getLoggedInUserLevel().equals("EE") || getLoggedInUserLevel().equals("PE") || getLoggedInUserLevel().equals("PCE") || getLoggedInUserLevel().equals("ADMIN"))
		if (getLoggedInUserLevel().equals("DEO") || getLoggedInUserLevel().equals("PE") || getLoggedInUserLevel().equals("ES") || getLoggedInUserLevel().equals("CE") || getLoggedInUserLevel().equals("EE") || getLoggedInUserLevel().equals("ADMIN"))
			
		{
			if( branchType.equalsIgnoreCase(Constants.BRANCHTYPE_COMMERCIAL)|| branchType.equalsIgnoreCase(Constants.PLANNING)){
					setToBeAllocatedList();
					setSendForConsMainList();
					//setConsMainSendList(); // removed 0n 29/05/2013
			}
			if( branchType.equalsIgnoreCase(Constants.BRANCHTYPE_ENERGY_MANAGEMENT) 
					|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_DISTRIBUTION_AND_MAINTENANCE) 
					|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_MAINTENANCE)
					|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_CONSTRUCTION) 
					|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_UNIT)){
						setToBeAllocatedList();
						setSendForConsMainList();
						setToBeAllocatedPLListList();
						//setConsMainSendList(); // removed 0n 29/05/2013//uncommented 10012014
				}
			if( branchType.equalsIgnoreCase(Constants.BRANCHTYPE_CONSTRUCTION)){
				System.out.println("Hiinew");
				setToBeAllocatedConsMainList();
				setToBeAllocatedPLListList();

				//setToBeAllocatedPLList();
     		}
			if( branchType.equalsIgnoreCase(Constants.BRANCHTYPE_ENERGY_MANAGEMENT) 
					|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_DISTRIBUTION_AND_MAINTENANCE) 
					|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_MAINTENANCE)
					|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_CONSTRUCTION) 
					|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_PHM)
					|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AC_UNIT)
					|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_UNIT) ){
				System.out.println("Hii setToBeAllocatedPLListList");
				setToBeAllocatedMaintenanceList();
				setToBeAllocatedConsMainList();//gayani
				setToBeAllocatedPLListList();
				
				
			}
		}
		
		if(branchType.equalsIgnoreCase(Constants.BRANCHTYPE_COMMERCIAL)){
			if (getLoggedInUserLevel().equals("ES") || getLoggedInUserLevel().equals("EE")  || getLoggedInUserLevel().equals("PE")  || getLoggedInUserLevel().equals("PCE")  || getLoggedInUserLevel().equals("ADMIN"))
			{
				setRejectedEstimates();
			
				setToBeApprovedEstimates();
			}
			
		}
		if(branchType.equalsIgnoreCase(Constants.BRANCHTYPE_ENERGY_MANAGEMENT) 
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_DISTRIBUTION_AND_MAINTENANCE) 
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_MAINTENANCE)
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_CONSTRUCTION) 
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_PHM)
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AC_UNIT) 
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_UNIT)
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_GOVERNMENT_INSTALLATION)){
			
			if (getLoggedInUserLevel().equals("DEO") || getLoggedInUserLevel().equals("ES") || getLoggedInUserLevel().equals("EE") || getLoggedInUserLevel().equals("PE") || getLoggedInUserLevel().equals("PCE") || getLoggedInUserLevel().equals("ADMIN"))
			{
				setRejectedWorkEstimates();
				setRejectedRevisedJobs();
				
			}
			if (getLoggedInUserLevel().equals("EE") || getLoggedInUserLevel().equals("CE") || getLoggedInUserLevel().equals("DGM") || getLoggedInUserLevel().equals("ADMIN") || getLoggedInUserLevel().equals("PCE"))
			{
				setToBeApprovedWorkEstimates();
				setToBeApproveRevisedJobs();
			}
		}
		
		if(branchType.equalsIgnoreCase(Constants.PLANNING) || branchType.equalsIgnoreCase(Constants.BRANCHTYPE_CONSTRUCTION) || branchType.equalsIgnoreCase(Constants.BRANCHTYPE_PHM) || branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AC_UNIT)){
			if (getLoggedInUserLevel().equals("PCE"))
			{
				setRejectedEstimates();
			
				setToBeApprovedEstimates();
			}
			
		}
		
		
		
		if( branchType.equalsIgnoreCase(Constants.BRANCHTYPE_COMMERCIAL) || branchType.equalsIgnoreCase(Constants.PLANNING)){
			if (getLoggedInUserLevel().equals("ES") || getLoggedInUserLevel().equals("PE") ||getLoggedInUserLevel().equals("EE") || getLoggedInUserLevel().equals("ADMIN"))
			{
				setAtiveAppoinments();
				
			}else if(getLoggedInUserLevel().equals("DEO")){
				settobeEnteredStdEstimates();
			}
			
		}
		if( 	branchType.equalsIgnoreCase(Constants.BRANCHTYPE_ENERGY_MANAGEMENT) 
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_DISTRIBUTION_AND_MAINTENANCE) 
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_MAINTENANCE)
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_CONSTRUCTION) 
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_PHM) 
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AC_UNIT) 
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_UNIT)
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_GOVERNMENT_INSTALLATION)){
			if (getLoggedInUserLevel().equals("ES") || getLoggedInUserLevel().equals("EE") || getLoggedInUserLevel().equals("ADMIN"))
			{
				setAtiveAppoinmentsForConMain();
			}else if(getLoggedInUserLevel().equals("DEO")){
				setTobeEnteredConEstimates();
			}
		}
		if(getLoggedInUserLevel().equals("ES") || getLoggedInUserLevel().equals("ADMIN") || getLoggedInUserLevel().equals("EE")  || getLoggedInUserLevel().equals("PE") || getLoggedInUserLevel().equals("PCE") || getLoggedInUserLevel().equals("CE")  || getLoggedInUserLevel().equals("DGM")){
			if( branchType.equalsIgnoreCase(Constants.BRANCHTYPE_COMMERCIAL)){
					setToBeValidatedEstimates();
			}
		}	
		if (getLoggedInUserLevel().equals("ES") || getLoggedInUserLevel().equals("EE") || getLoggedInUserLevel().equals("PCE") || getLoggedInUserLevel().equals("CE") ||  getLoggedInUserLevel().equals("DGM") || getLoggedInUserLevel().equals("AGM") ||getLoggedInUserLevel().equals("ADMIN"))
		{
			
			System.out.println("Hii PCE Revise");
			if(  branchType.equalsIgnoreCase(Constants.BRANCHTYPE_ENERGY_MANAGEMENT) 
					|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_DISTRIBUTION_AND_MAINTENANCE) 
					|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_MAINTENANCE)
					|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_CONSTRUCTION) 
					|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_PHM) 
					|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AC_UNIT)
					|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_UNIT)
					|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_GOVERNMENT_INSTALLATION)){
				System.out.println("Hii PCE Revise");
					setToBeValidateWorkEstimates();
					setToBeReccomandWorkEstimates();
					setToBeValidateRevisedJobs();
			}
		}
		
		
		if(branchType.equalsIgnoreCase(Constants.BRANCHTYPE_COMMERCIAL)){
				setApprovedEstimates();
		}
		if( branchType.equalsIgnoreCase(Constants.BRANCHTYPE_ENERGY_MANAGEMENT) 
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_DISTRIBUTION_AND_MAINTENANCE) 
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_MAINTENANCE)
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_CONSTRUCTION) 
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_PHM)
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AC_UNIT)
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_AREA_UNIT)
				|| branchType.equalsIgnoreCase(Constants.BRANCHTYPE_GOVERNMENT_INSTALLATION)){
				setRevisedJobsList();
				//setApprovedWorkEstimates(); // removed 0n 29/05/2013
				//setApprovedRevisedJobs();// removed 0n 29/05/2013
			}
			
			//***************************************for VSL jobs****************************************
			/*if( 	costCenterNo.equalsIgnoreCase("521.10") || costCenterNo.equalsIgnoreCase("521.20") ||
					costCenterNo.equalsIgnoreCase("521.30") || costCenterNo.equalsIgnoreCase("521.40") || 
					costCenterNo.equalsIgnoreCase("521.50") || costCenterNo.equalsIgnoreCase("522.10") || 
					costCenterNo.equalsIgnoreCase("522.20") || costCenterNo.equalsIgnoreCase("522.40") || 
					costCenterNo.equalsIgnoreCase("522.50") || costCenterNo.equalsIgnoreCase("522.60") ||
					costCenterNo.equalsIgnoreCase("523.10") || costCenterNo.equalsIgnoreCase("523.40") ||
					costCenterNo.equalsIgnoreCase("523.60") || costCenterNo.equalsIgnoreCase("523.70") || costCenterNo.equalsIgnoreCase("524.10") ||  
					costCenterNo.equalsIgnoreCase("524.20") || costCenterNo.equalsIgnoreCase("524.30") || 
					costCenterNo.equalsIgnoreCase("524.40") || costCenterNo.equalsIgnoreCase("524.50") ||
					costCenterNo.equalsIgnoreCase("525.10") || costCenterNo.equalsIgnoreCase("525.20") || 
					costCenterNo.equalsIgnoreCase("525.30") || costCenterNo.equalsIgnoreCase("526.10") || 
					costCenterNo.equalsIgnoreCase("526.20") || costCenterNo.equalsIgnoreCase("526.30") || 
					
					costCenterNo.equalsIgnoreCase("526.40")
					//costCenterNo.equalsIgnoreCase("514.00") || costCenterNo.equalsIgnoreCase("514.10") || 
					//costCenterNo.equalsIgnoreCase("514.20") || costCenterNo.equalsIgnoreCase("514.30") || 
					//costCenterNo.equalsIgnoreCase("514.40") || costCenterNo.equalsIgnoreCase("514.50") || 
					//costCenterNo.equalsIgnoreCase("514.80") || costCenterNo.equalsIgnoreCase("514.90") 
					){
					
					if (getLoggedInUserLevel().equals("ES") || getLoggedInUserLevel().equals("EE") || getLoggedInUserLevel().equals("ADMIN"))
					{
						setRejectedVSLEstimates();
						setRejectedRevisedJobs();
						
					}
					if (getLoggedInUserLevel().equals("ES") || getLoggedInUserLevel().equals("EE") || getLoggedInUserLevel().equals("CE") || getLoggedInUserLevel().equals("DGM") || getLoggedInUserLevel().equals("ADMIN"))
					{
						setToBeApprovedVSLEstimates();
						setToBeApproveRevisedJobs();
					}
				}
			if (getLoggedInUserLevel().equals("ES") ||  getLoggedInUserLevel().equals("EE") || getLoggedInUserLevel().equals("CE") ||  getLoggedInUserLevel().equals("DGM") || getLoggedInUserLevel().equals("ADMIN"))
			{
				
				if( costCenterNo.equalsIgnoreCase("521.10") || costCenterNo.equalsIgnoreCase("521.20") ||
					costCenterNo.equalsIgnoreCase("521.30") || costCenterNo.equalsIgnoreCase("521.40") || 
					costCenterNo.equalsIgnoreCase("521.50") || costCenterNo.equalsIgnoreCase("522.10") || 
					costCenterNo.equalsIgnoreCase("522.20") || costCenterNo.equalsIgnoreCase("522.40") || 
					costCenterNo.equalsIgnoreCase("522.50") || costCenterNo.equalsIgnoreCase("522.60") ||
					costCenterNo.equalsIgnoreCase("523.10") || costCenterNo.equalsIgnoreCase("523.40") ||  
					costCenterNo.equalsIgnoreCase("523.60") || 
					costCenterNo.equalsIgnoreCase("523.70") || costCenterNo.equalsIgnoreCase("524.10") || 
					costCenterNo.equalsIgnoreCase("524.20") || costCenterNo.equalsIgnoreCase("524.30") || 
					costCenterNo.equalsIgnoreCase("524.40") || costCenterNo.equalsIgnoreCase("524.50") ||
					costCenterNo.equalsIgnoreCase("525.10") || costCenterNo.equalsIgnoreCase("525.20") || 
					costCenterNo.equalsIgnoreCase("525.30") || costCenterNo.equalsIgnoreCase("526.10") || 
					costCenterNo.equalsIgnoreCase("526.20") || costCenterNo.equalsIgnoreCase("526.30") || 
					costCenterNo.equalsIgnoreCase("526.40") 
					//costCenterNo.equalsIgnoreCase("514.00") || costCenterNo.equalsIgnoreCase("514.10") || 
					//costCenterNo.equalsIgnoreCase("514.20") || costCenterNo.equalsIgnoreCase("514.30") || 
					//costCenterNo.equalsIgnoreCase("514.40") || costCenterNo.equalsIgnoreCase("514.50") || 
					//costCenterNo.equalsIgnoreCase("514.80") || costCenterNo.equalsIgnoreCase("514.90")
					){
						setToBeValidateVSLEstimates();
						
						setToBeValidateRevisedJobs();
				}
			}
			if( costCenterNo.equalsIgnoreCase("521.10") || costCenterNo.equalsIgnoreCase("521.20") ||
				costCenterNo.equalsIgnoreCase("521.30") || costCenterNo.equalsIgnoreCase("521.40") || 
				costCenterNo.equalsIgnoreCase("521.50") || costCenterNo.equalsIgnoreCase("522.10") || 
				costCenterNo.equalsIgnoreCase("522.20") || costCenterNo.equalsIgnoreCase("522.40") || 
				costCenterNo.equalsIgnoreCase("522.50") || costCenterNo.equalsIgnoreCase("522.60") ||
				costCenterNo.equalsIgnoreCase("523.10") || costCenterNo.equalsIgnoreCase("523.40") || 
				costCenterNo.equalsIgnoreCase("523.60") || 
				costCenterNo.equalsIgnoreCase("523.70") || costCenterNo.equalsIgnoreCase("524.10") || 
				costCenterNo.equalsIgnoreCase("524.20") || costCenterNo.equalsIgnoreCase("524.30") || 
				costCenterNo.equalsIgnoreCase("524.40") || costCenterNo.equalsIgnoreCase("524.50") ||
				costCenterNo.equalsIgnoreCase("525.10") || costCenterNo.equalsIgnoreCase("525.20") || 
				costCenterNo.equalsIgnoreCase("525.30") || costCenterNo.equalsIgnoreCase("526.10") || 
				costCenterNo.equalsIgnoreCase("526.20") || costCenterNo.equalsIgnoreCase("526.30") ||
				//costCenterNo.equalsIgnoreCase("514.00") || costCenterNo.equalsIgnoreCase("514.10") || 
				//costCenterNo.equalsIgnoreCase("514.20") || costCenterNo.equalsIgnoreCase("514.30") || 
				//costCenterNo.equalsIgnoreCase("514.40") || costCenterNo.equalsIgnoreCase("514.50") || 
				//costCenterNo.equalsIgnoreCase("514.80") || costCenterNo.equalsIgnoreCase("514.90") ||
				costCenterNo.equalsIgnoreCase("526.40") ){
						setRevisedJobsList();
						//setApprovedVSLEstimates(); // removed 0n 29/05/2013
						//setApprovedRevisedJobs(); // removed 0n 29/05/2013
				}*/
		
			/*if( getLoggedInUserLevel().equals("ACCNT") && ( costCenterNo.equalsIgnoreCase("530.20") || costCenterNo.equalsIgnoreCase("530.00") ||
					costCenterNo.equalsIgnoreCase("530.30") || costCenterNo.equalsIgnoreCase("520.00") ||
					costCenterNo.equalsIgnoreCase("710.00") || costCenterNo.equalsIgnoreCase("780.00") ||
					costCenterNo.equalsIgnoreCase("510.20") || costCenterNo.equalsIgnoreCase("510.30") || costCenterNo.equalsIgnoreCase("510.00") ||
					costCenterNo.equalsIgnoreCase("450.20") || costCenterNo.equalsIgnoreCase("450.30") || costCenterNo.equalsIgnoreCase("450.00") ||
					costCenterNo.equalsIgnoreCase("550.20") || costCenterNo.equalsIgnoreCase("550.30") || costCenterNo.equalsIgnoreCase("550.00") ||
					costCenterNo.equalsIgnoreCase("543.00") || costCenterNo.equalsIgnoreCase("543.10") || 
					costCenterNo.equalsIgnoreCase("543.20") || costCenterNo.equalsIgnoreCase("543.40") || 
					costCenterNo.equalsIgnoreCase("520.20") || costCenterNo.equalsIgnoreCase("520.30") || costCenterNo.equalsIgnoreCase("440.00") ||
					costCenterNo.equalsIgnoreCase("510.20") || costCenterNo.equalsIgnoreCase("510.30") || costCenterNo.equalsIgnoreCase("440.20") ||
					costCenterNo.equalsIgnoreCase("501.20") || costCenterNo.equalsIgnoreCase("501.30") || costCenterNo.equalsIgnoreCase("501.00") ||
					costCenterNo.equalsIgnoreCase("440.20") || costCenterNo.equalsIgnoreCase("440.30") || 
					costCenterNo.equalsIgnoreCase("420.20") || costCenterNo.equalsIgnoreCase("420.30") || costCenterNo.equalsIgnoreCase("420.90") ||
					costCenterNo.equalsIgnoreCase("540.20") || costCenterNo.equalsIgnoreCase("540.70") || 
					costCenterNo.equalsIgnoreCase("540.30") || costCenterNo.equalsIgnoreCase("544.10") || 
					costCenterNo.equalsIgnoreCase("544.21") || costCenterNo.equalsIgnoreCase("544.22") || 
					costCenterNo.equalsIgnoreCase("544.23") || costCenterNo.equalsIgnoreCase("544.24") ||
					costCenterNo.equalsIgnoreCase("544.30") || costCenterNo.equalsIgnoreCase("544.31") || 
					costCenterNo.equalsIgnoreCase("544.32") || costCenterNo.equalsIgnoreCase("545.05") ||
					costCenterNo.equalsIgnoreCase("545.10") || costCenterNo.equalsIgnoreCase("545.20") || 
					costCenterNo.equalsIgnoreCase("545.21") || costCenterNo.equalsIgnoreCase("545.22") || 
					costCenterNo.equalsIgnoreCase("545.23"))){
						setTobeCreateJobNos();
			}
			if( getLoggedInUserLevel().equals("ACCNT") && (costCenterNo.equalsIgnoreCase("520.00") || costCenterNo.equalsIgnoreCase("520.20") || costCenterNo.equalsIgnoreCase("520.30") || costCenterNo.equalsIgnoreCase("521.10") || costCenterNo.equalsIgnoreCase("521.20") ||
					costCenterNo.equalsIgnoreCase("521.30") || costCenterNo.equalsIgnoreCase("521.40") || 
					costCenterNo.equalsIgnoreCase("521.50") || costCenterNo.equalsIgnoreCase("522.10") || 
					costCenterNo.equalsIgnoreCase("522.20") || costCenterNo.equalsIgnoreCase("522.40") || 
					costCenterNo.equalsIgnoreCase("522.50") || costCenterNo.equalsIgnoreCase("522.60") ||
					costCenterNo.equalsIgnoreCase("523.10") || costCenterNo.equalsIgnoreCase("523.40") || 
					costCenterNo.equalsIgnoreCase("514.20") || costCenterNo.equalsIgnoreCase("523.60") || 
					costCenterNo.equalsIgnoreCase("523.70") || costCenterNo.equalsIgnoreCase("524.10") || 
					costCenterNo.equalsIgnoreCase("524.20") || costCenterNo.equalsIgnoreCase("524.30") || 
					costCenterNo.equalsIgnoreCase("524.40") || costCenterNo.equalsIgnoreCase("524.50") ||
					costCenterNo.equalsIgnoreCase("525.10") || costCenterNo.equalsIgnoreCase("525.20") || 
					costCenterNo.equalsIgnoreCase("525.30") || costCenterNo.equalsIgnoreCase("526.10") || 
					costCenterNo.equalsIgnoreCase("526.20") || costCenterNo.equalsIgnoreCase("526.30") || 
					costCenterNo.equalsIgnoreCase("526.40") )){
						setTobeCreateJobNosVSL();
			}*/
			
			
		return SUCCESS;
		
	}
	
	
	
	public String setToBeValidateWECon(){
		System.out.print("hiii jq");
		setLoggedData();
		System.out.print(getErrorMessage());
		setErrorMessage(getErrorMessage());
		System.out.print(getSession());
		
		setToBeValidateWorkEstimates();
		return SUCCESS;
	}
	
	public String setToBeRecommendWECon(){
		System.out.print("hiii jq");
		setLoggedData();
		System.out.print(getErrorMessage());
		setErrorMessage(getErrorMessage());
		System.out.print(getSession());
		
		setToBeReccomandWorkEstimates();
		return SUCCESS;
	}

	
	public String setToBeValidateWEConWPSII(){
		System.out.print("hiii jq");
		setLoggedData();
		System.out.print(getErrorMessage());
		setErrorMessage(getErrorMessage());
		System.out.print(getSession());
		
		setToBeValidateWorkEstimates();
		return SUCCESS;
	}
	
	
	public String activeAppointmentsCon(){
		System.out.print("hiii jq");
		setLoggedData();
		System.out.print(getErrorMessage());
		setErrorMessage(getErrorMessage());
		System.out.print(getSession());
		
		setAtiveAppoinmentsForConMain();
		return SUCCESS;
	}
	
	
	public String toBeAllocatedConsMainList(){
		System.out.print("hiii jq");
		setLoggedData();
		System.out.print(getErrorMessage());
		setErrorMessage(getErrorMessage());
		System.out.print(getSession());
		
		setToBeAllocatedConsMainList();
		//setToBeAllocatedPLList();
		return SUCCESS;
	}
	
	public String toBeAllocatedPLList(){
		System.out.print("hiii toBeAllocatedPLList");
		setLoggedData();
		System.out.print(getErrorMessage());
		setErrorMessage(getErrorMessage());
		System.out.print(getSession());
		
		setToBeAllocatedPLListList();
		//setToBeAllocatedPLList();
		return SUCCESS;
	}
	
	public String rejectedWorkEstimate(){
		System.out.print("hiii jq");
		setLoggedData();
		System.out.print(getErrorMessage());
		setErrorMessage(getErrorMessage());
		System.out.print(getSession());
		
		setRejectedWorkEstimates();
		return SUCCESS;
	}
	
	
	public String appSetPIVIIList(){
		System.out.print("hiii jq");
		setLoggedData();
		System.out.print(getErrorMessage());
		setErrorMessage(getErrorMessage());
		System.out.print(getSession());
		
		setPIVIIList();
		return SUCCESS;
	}
	
	public String appEstimateTobeValidate(){
		System.out.print("hiii jq");
		setLoggedData();
		System.out.print(getErrorMessage());
		setErrorMessage(getErrorMessage());
		System.out.print(getSession());
		
		setToBeValidatedEstimates();
		return SUCCESS;
	}
	
	public String appToBeAllocatedList(){
		System.out.print("hiii jqddd");
		setLoggedData();
		System.out.print(getErrorMessage());
		setErrorMessage(getErrorMessage());
		System.out.print(getSession());
		
		setToBeAllocatedList();
		return SUCCESS;
	}
	
	
	
	
	public String stdApprovedEstimates(){
		System.out.print("hiii jqddd");
		setLoggedData();
		System.out.print(getErrorMessage());
		setErrorMessage(getErrorMessage());
		System.out.print(getSession());
		
		setApprovedEstimates();
		return SUCCESS;
	}
	
	public String stdSendForConsMainList(){
		System.out.print("hiii jqddd");
		setLoggedData();
		System.out.print(getErrorMessage());
		setErrorMessage(getErrorMessage());
		System.out.print(getSession());
		
		setSendForConsMainList();
		return SUCCESS;
	}
	
	
	
	public String stdTobeEnteredStdEstimates(){
		System.out.print("hiii jqddd");
		setLoggedData();
		System.out.print(getErrorMessage());
		setErrorMessage(getErrorMessage());
		System.out.print(getSession());
		
		settobeEnteredStdEstimates();
		return SUCCESS;
	}
	
	
	
	public String worSetTobeEnteredConEstimates(){
		System.out.print("hiii jqddd");
		setLoggedData();
		System.out.print(getErrorMessage());
		setErrorMessage(getErrorMessage());
		System.out.print(getSession());
		
		setTobeEnteredConEstimates();
		return SUCCESS;
	}
	
	public String stdSetAtiveAppoinments(){
		System.out.print("hiii jqddd");
		setLoggedData();
		System.out.print(getErrorMessage());
		setErrorMessage(getErrorMessage());
		System.out.print(getSession());
		
		setAtiveAppoinments();
		return SUCCESS;
	}
	
	
	
	public String toBeAllocatedConsMainListItself(){
		System.out.print("hiii jq toBeAllocatedConsMainListItself");
		setLoggedData();
		System.out.print(getErrorMessage());
		setErrorMessage(getErrorMessage());
		System.out.print(getSession());
		setToBeAllocatedMaintenanceList();
		return SUCCESS;
	}
	
	public String getSessionKey(String key) {
		return getSession().get(key).toString();
	}
	
	public void setLoggedData() {
		   System.out.println(getSession());
		   setCostCenterNo(getSessionKey("costCenterNo"));
		   setLoggedInUserId(getSession().get("userName").toString());
		   setLoggedInUserLevel( getSessionKey("userRole"));
		   setSmcType(getSessionKey("smcType"));
		   setEstimateType(getSessionKey("estimateType"));
		   setBranchType(getSessionKey("branchType"));
		   /*ProvinceDetailsMasterEjb promasEjb = new ProvinceDetailsMasterEjb();
		   String branchType = promasEjb.getBranchType(getCostCenterNo(), getSessionKey("region"));
		   if(branchType != null){
			  setBranchType(branchType);
		   }else{
			   setBranchType(Constants.DEFAULT_ESTIMATE_TYPE);
		   }*/
		   /*String jobType = promasEjb.getJobTypeByDeptId(getCostCenterNo(), getSessionKey("region"))
		   if(jobType != null){
				  setJobType(jobType);
		   }else{
				 // setBranchType(Constants.DEFAULT_BRANCHTYPE);
				  setJobType(Constants.DEFAULT_BRANCHTYPE);
		   }*/
	   }
	
	private void setPIVIIList()
	{	
		EstimateEjb ejb=new EstimateEjb(getSessionKey("region"));	
		List<EstimateDisplay> tesmpList = null;
		System.out.print(getCostCenterNo());
		this.needToIssuePivIIList = new  ArrayList<EstimateDisplay>();
		/*if (getSessionKey("smcType").equals("MT"))
			tesmpList   = ejb.getEstimateListMA_SA(getCostCenterNo(), new BigDecimal(EstimateStatus.EST_REJECTED));
		else*/
		//tesmpList   = ejb.getEstimateList(getCostCenterNo(), new BigDecimal(StandardEstimateStatus.APPROVED_ESTIMATES.getKey()), getSessionKey("smcType"));
		try {
			tesmpList   =  ejb.getApprovedEstimateList(getCostCenterNo(),StandardEstimateStatus.APPROVED_ESTIMATES.getKey(), getSessionKey("smcType"), null,getSessionKey("region"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.print(tesmpList);
		if (tesmpList!=null){
			needToIssuePivIIListCount = tesmpList.size();
			this.needToIssuePivIIList.addAll(tesmpList);
			if (this.needToIssuePivIIList.size() == 0)
				this.needToIssuePivIIList = null;
			}else {
				this.needToIssuePivIIList = null;
		}
	}
	private void setPIVIIListConfirmCashPayment()
	{	
		EstimateEjb ejb=new EstimateEjb(getSessionKey("region"));	
		List<EstimateDisplay> tesmpList = null;
		System.out.print(getCostCenterNo());
		this.needToConfirmByCashPivIIList = new  ArrayList<EstimateDisplay>();
		/*if (getSessionKey("smcType").equals("MT"))
			tesmpList   = ejb.getEstimateListMA_SA(getCostCenterNo(), new BigDecimal(EstimateStatus.EST_REJECTED));
		else*/
		//tesmpList   = ejb.getEstimateList(getCostCenterNo(), new BigDecimal(StandardEstimateStatus.APPROVED_ESTIMATES.getKey()), getSessionKey("smcType"));
		try {
			tesmpList   =  ejb.getConfirmPIVList(getCostCenterNo(),StandardEstimateStatus.PIVII_TO_BE_CONFIRMED_BY_DEO.getKey(), getSessionKey("smcType"), null,getSessionKey("region"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.print(tesmpList);
		if (tesmpList!=null){
			this.needToConfirmByCashPivIIList.addAll(tesmpList);
			if (this.needToConfirmByCashPivIIList.size() == 0)
				this.needToConfirmByCashPivIIList = null;
			}else {
				this.needToConfirmByCashPivIIList = null;
		}
	}
	private void setPIVIIListConfirmChequePayment()
	{	
		EstimateEjb ejb=new EstimateEjb(getSessionKey("region"));	
		List<EstimateDisplay> tesmpList = null;
		System.out.print(getCostCenterNo());
		this.needToConfirmByChequePivIIList = new  ArrayList<EstimateDisplay>();
		/*if (getSessionKey("smcType").equals("MT"))
			tesmpList   = ejb.getEstimateListMA_SA(getCostCenterNo(), new BigDecimal(EstimateStatus.EST_REJECTED));
		else*/
		//tesmpList   = ejb.getEstimateList(getCostCenterNo(), new BigDecimal(StandardEstimateStatus.APPROVED_ESTIMATES.getKey()), getSessionKey("smcType"));
		try {
			String authoCostCenter =getCostCenterNo().substring(0,3);
			String costCenter = authoCostCenter.concat(".00");
			//tesmpList   =  ejb.getApprovedEstimateList(costCenter,StandardEstimateStatus.PIVII_TO_BE_CONFIRMED_BY_ACCOUNT_REV.getKey(), getSessionKey("smcType"), null,getSessionKey("region"));
			tesmpList   =  ejb.getConfirmPIVList(costCenter,StandardEstimateStatus.PIVII_TO_BE_CONFIRMED_BY_ACCOUNT_REV.getKey(), getSessionKey("smcType"), null,getSessionKey("region"));
			System.out.print("count : " + tesmpList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.print(tesmpList);
		if (tesmpList!=null){
			this.needToConfirmByChequePivIIList.addAll(tesmpList);
			if (this.needToConfirmByChequePivIIList.size() == 0)
				this.needToConfirmByChequePivIIList = null;
			}else {
				this.needToConfirmByChequePivIIList = null;
		}
	}
	private void setSendForValidationList()
	{	
		EstimateEjb ejb=new EstimateEjb(getSessionKey("region"));	
		List<EstimateDisplay> tesmpList = null;
		System.out.print(getCostCenterNo());
		this.sEsendForValidationList = new  ArrayList<EstimateDisplay>();
		/*if (getSessionKey("smcType").equals("MT"))
			tesmpList   = ejb.getEstimateListMA_SA(getCostCenterNo(), new BigDecimal(EstimateStatus.EST_REJECTED));
		else*/
		tesmpList   = ejb.getEstimateList(getCostCenterNo(), new BigDecimal(StandardEstimateStatus.APPROVED_ESTIMATES.getKey()), getSessionKey("smcType"));
		try {
			//tesmpList   =  ejb.getApprovedEstimateList(getCostCenterNo(),StandardEstimateStatus.APPROVED_ESTIMATES.getKey(), getSessionKey("smcType"), null,getSessionKey("region"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.print(tesmpList);
		if (tesmpList!=null){
			this.sEsendForValidationList.addAll(tesmpList);
			if (this.sEsendForValidationList.size() == 0)
				this.sEsendForValidationList = null;
			}else {
				this.sEsendForValidationList = null;
		}
	}
	private void setSendForConsMainList()
	{	
		EstimateEjb ejb=new EstimateEjb(getSessionKey("region"));	
		List<Spstdesthmt> tesmpList = null;
		System.out.print(getCostCenterNo());
		this.sEsendForConMainList = new  ArrayList<Spstdesthmt>();
		List<Long> status = new ArrayList<Long>();
		status.add(StandardEstimateStatus.PIVII_CONFIRMATION.getKey());
		//tesmpList   = ejb.getEstimateList(getCostCenterNo(), new BigDecimal(StandardEstimateStatus.PIVII_CONFIRMATION.getKey()), getSessionKey("smcType"));
		try {
			tesmpList = ejb.loadStandEstmateDetailsType(costCenterNo, status,null,getSmcType(), getSessionKey("region"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.print(tesmpList);
		if (tesmpList!=null){
			sEsendForConMainListCount = tesmpList.size();
			this.sEsendForConMainList.addAll(tesmpList);
			
			if (this.sEsendForConMainList.size() == 0)
				this.sEsendForConMainList = null;
			}else {
				this.sEsendForConMainList = null;
		}
	}
	private void setConsMainSendList()
	{	
		EstimateEjb ejb=new EstimateEjb(getSessionKey("region"));	
		List<Spstdesthmt> tesmpList = null;
		System.out.print(getCostCenterNo());
		this.conMainSentList = new  ArrayList<Spstdesthmt>();
		List<Long> status = new ArrayList<Long>();
		status.add(StandardEstimateStatus.SEND_FOR_CONSTRUCTION_MAINTENANCE.getKey());
		status.add(StandardEstimateStatus.JOB_ALLOCATED.getKey());
		//tesmpList   = ejb.getEstimateList(getCostCenterNo(), new BigDecimal(StandardEstimateStatus.PIVII_CONFIRMATION.getKey()), getSessionKey("smcType"));
		try {
			System.out.println("sss : "+ status + " " +getSmcType() + costCenterNo );
			
				tesmpList = ejb.loadStandEstmateDetailsType(costCenterNo, status,null,getSmcType(), getSessionKey("region"));
				
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.print(tesmpList);
		if (tesmpList!=null){
			this.conMainSentList.addAll(tesmpList);
			if (this.conMainSentList.size() == 0)
				this.conMainSentList = null;
			}else {
				this.conMainSentList = null;
		}
	}
	
	private void setToBeAllocatedConsMainList()
	{	
		
		EstimateEjb ejb=new EstimateEjb(getSessionKey("region"));	
		List<Spstdesthmt> tesmpList = null;
		System.out.print(getCostCenterNo());
		this.toBeAllocatedConMainList = new  ArrayList<Spstdesthmt>();
		List<Long> status = new ArrayList<Long>();
		status.add(StandardEstimateStatus.SEND_FOR_CONSTRUCTION_MAINTENANCE.getKey());
		//tesmpList   = ejb.getEstimateList(getCostCenterNo(), new BigDecimal(StandardEstimateStatus.PIVII_CONFIRMATION.getKey()), getSessionKey("smcType"));
		try {
			//String commCost = costCenterNo.substring(0, 2).concat("0.00");
			//if(getCostCenterNo().equals("434.60")){
				//commCost ="430.00";
			//}
			ProvinceDetailsMasterEjb promasEjb = new ProvinceDetailsMasterEjb();
			
			String commCost = promasEjb.getCommercialId(getCostCenterNo(),  getSessionKey("region"));
			
			if(commCost == null){
				commCost = costCenterNo.substring(0, 2).concat("0.00");
			}
			if(commCost.equals("550.00")){
				tesmpList = ejb.loadConMainSentList("550.14",costCenterNo, status);
			}
			
			System.out.print("comcost: " +commCost + " cost center : " +  costCenterNo);
			tesmpList = ejb.loadConMainSentList(commCost,costCenterNo, status);
			System.out.print("status : " +status + " tmp list : " +  tesmpList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.print(tesmpList);
		if (tesmpList!=null){
			toBeAllocatedConMainListCount = tesmpList.size();
			this.toBeAllocatedConMainList.addAll(tesmpList);
			if (this.toBeAllocatedConMainList.size() == 0)
				this.toBeAllocatedConMainList = null;
			}else {
				this.toBeAllocatedConMainList = null;
		}
	}
	
	
	public void setToBeAllocatedPLListList()
	{	
		System.out.print("Hii setToBeAllocatedPLListList ");
		EstimateEjb ejb=new EstimateEjb(getSessionKey("region"));	
		List<Spstdesthmt> tesmpList = null;
		System.out.print(getCostCenterNo());
		//this.toBeAllocatedPLList = new  ArrayList<Spstdesthmt>();
		this.toBeAllocatedPlanningList = new  ArrayList<Spstdesthmt>();
		//toBeAllocatedList
		List<Long> status = new ArrayList<Long>();
		status.add(StandardEstimateStatus.SEND_FOR_CONSTRUCTION_MAINTENANCE.getKey());
		//tesmpList   = ejb.getEstimateList(getCostCenterNo(), new BigDecimal(StandardEstimateStatus.PIVII_CONFIRMATION.getKey()), getSessionKey("smcType"));
		try {
			//String commCost = costCenterNo.substring(0, 2).concat("0.00");
			//if(getCostCenterNo().equals("434.60")){
				//commCost ="430.00";
			//}
			ProvinceDetailsMasterEjb promasEjb = new ProvinceDetailsMasterEjb();
			
			String commCost = promasEjb.getCommercialId(getCostCenterNo(),  getSessionKey("region"));
			
			
			if(commCost.equals("550.00")){
				tesmpList = ejb.loadConMainSentList("550.14",costCenterNo, status);
			}
			
			System.out.print("comcost: " +commCost + " cost center : " +  costCenterNo);
			//tesmpList = ejb.loadConMainSentList(commCost,costCenterNo, status);
			System.out.print("status : " +status + " tmp list : " +  tesmpList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.print(tesmpList);
		if (tesmpList!=null){
			toBeAllocatedPLListCount = tesmpList.size();
			this.toBeAllocatedPlanningList.addAll(tesmpList);
			if (this.toBeAllocatedPlanningList.size() == 0)
				this.toBeAllocatedPlanningList = null;
			}else {
				this.toBeAllocatedPlanningList = null;
		}
		System.out.println("----------------------------------------------");
		System.out.print("toBeAllocatedPLList : " + toBeAllocatedPlanningList);
		System.out.print("toBeAllocatedPLListCount : " + toBeAllocatedPLListCount);
	}
	
	
	private void setToBeAllocatedMaintenanceList()
	{	
		
		ApplicationEjb appEjb=new ApplicationEjb(getSessionKey("region"));
		
		List<ApplicationDisplay> appListCPJobs = null;
		List<ApplicationDisplay> appListAllOther = null;
		System.out.print(getCostCenterNo());
		this.toBeAllocatedList = new  ArrayList<ApplicationDisplay>();
		/*if (getSessionKey("smcType").equals("MT"))
			tesmpList   = ejb.getEstimateListMA_SA(getCostCenterNo(), new BigDecimal(EstimateStatus.EST_REJECTED));
		else*/
		//tesmpList   = ejb.getEstimateList(getCostCenterNo(), new BigDecimal(StandardEstimateStatus.APPROVED_ESTIMATES.getKey()), getSessionKey("smcType"));
		try {
			//tesmpList   =  ejb.getApprovedEstimateList(getCostCenterNo(),StandardEstimateStatus.APPROVED_ESTIMATES.getKey(), getSessionKey("smcType"), null,getSessionKey("region"));
			
			//tesmpList= appEjb.getApplicationList(costCenterNo, "C");
	
			   ProvinceDetailsMasterEjb promasEjb = new ProvinceDetailsMasterEjb();
			   String commercialId = promasEjb.getCommercialId(getCostCenterNo(),  getSessionKey("region"));
			   System.out.println("hi :"+ commercialId);
			   
			   if(commercialId != null){
				   appListCPJobs = appEjb.getToBeAllocatedApplicationList(commercialId, AppStatus.REQUEST_FOR_ESTIMATION,Constants.JOBTYPE_COMMERCIAL_CP);
			   }
			   
			   
			   System.out.println("hi :"+ costCenterNo +"gi : "+ AppStatus.CONFIRMED);
			   appListAllOther = appEjb.getToBeAllocatedApplicationList(costCenterNo, AppStatus.CONFIRMED,getSmcType());
			   System.out.println("hi :"+ appListAllOther);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.print(tesmpList);
		if (appListAllOther!=null){
			
			this.toBeAllocatedList.addAll(appListAllOther);
			
		}if (appListCPJobs!=null){
			
			this.toBeAllocatedList.addAll(appListCPJobs);
			
		}
		
		if (this.toBeAllocatedList.size() == 0){
			this.toBeAllocatedList = null;
		}else{
			toBeAllocatedListCount =toBeAllocatedList.size(); 
		}
		
	}
	
	private void setToBeAllocatedList()
	{	
		ApplicationEjb appEjb=new ApplicationEjb(getSessionKey("region"));
		
		List<ApplicationDisplay> tesmpList = null;
		//List<ApplicationDisplay> tesmpList = null;
		System.out.print(getCostCenterNo());
		this.toBeAllocatedList = new  ArrayList<ApplicationDisplay>();
		/*if (getSessionKey("smcType").equals("MT"))
			tesmpList   = ejb.getEstimateListMA_SA(getCostCenterNo(), new BigDecimal(EstimateStatus.EST_REJECTED));
		else*/
		//tesmpList   = ejb.getEstimateList(getCostCenterNo(), new BigDecimal(StandardEstimateStatus.APPROVED_ESTIMATES.getKey()), getSessionKey("smcType"));
		try {
			//tesmpList   =  ejb.getApprovedEstimateList(getCostCenterNo(),StandardEstimateStatus.APPROVED_ESTIMATES.getKey(), getSessionKey("smcType"), null,getSessionKey("region"));
			
			//tesmpList= appEjb.getApplicationList(costCenterNo, "C");
			

			//status.add(AppStatus.REQUEST_FOR_ESTIMATION);
			System.out.println("SMC Type type: "+getSmcType());
			tesmpList= appEjb.getToBeAllocatedApplicationList(costCenterNo, AppStatus.CONFIRMED,getSmcType());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.print(tesmpList);
		if (tesmpList!=null){
			toBeAllocatedListCount =tesmpList.size(); 
			this.toBeAllocatedList.addAll(tesmpList);
			if (this.toBeAllocatedList.size() == 0)
				this.toBeAllocatedList = null;
			}else {
				this.toBeAllocatedList = null;
		}
	}
	private void setRejectedEstimates()
	{	
		EstimateEjb ejb=new EstimateEjb(getSessionKey("region"));	
		List<EstimateDisplay> tesmpList = null;
		System.out.print(getCostCenterNo());
		this.rejectedList = new  ArrayList<EstimateDisplay>();
		/*if (getSessionKey("smcType").equals("MT"))
			tesmpList   = ejb.getEstimateListMA_SA(getCostCenterNo(), new BigDecimal(EstimateStatus.EST_REJECTED));
		else*/
			tesmpList   = ejb.getRejectedEstimateList(getCostCenterNo(), StandardEstimateStatus.REJECTED_BY.getKey(), getSessionKey("smcType"),getLoggedInUserId(),getLoggedInUserLevel(),getSessionKey("region") );
		System.out.print(tesmpList);
		if (tesmpList!=null){
			this.rejectedList.addAll(tesmpList);
			if (this.rejectedList.size() == 0)
				this.rejectedList = null;
			}else {
				this.rejectedList = null;
		}
	}

	/*private void setRejectedAprovedJobEstimates()
	{	
		PcesthmtEjb ejb=new PcesthmtEjb(getSessionKey("region"));		

		List<Pcesthmt> tesmpList = null;
		this.rejectedRevisedJobsList = new  ArrayList<Pcesthmt>();
		
			tesmpList   = ejb.getJobDetailList (getCostCenterNo(), new BigDecimal(EstimateStatus.JOB_REJECTED) );
		if (tesmpList!=null){
			this.rejectedRevisedJobsList.addAll(tesmpList);
			if (this.rejectedRevisedJobsList.size() == 0)
				this.rejectedRevisedJobsList = null;
			}else {
				this.rejectedRevisedJobsList = null;
			}
		

	}*/
	
	private void setToBeApprovedEstimates()	{
		EstimateEjb ejb=new EstimateEjb(getSessionKey("region"));		
		List<EstimateDisplay> tesmpList = null;
		this.approveList = new  ArrayList<EstimateDisplay>();	
		List<Long> status = new ArrayList<Long>();
		//status.add(StandardEstimateStatus.NEW_APPLICATION.getKey());
		status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey());
		status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey());
		status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_CE.getKey());
		status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey());
		status.add(StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey());
		try {
			System.out.println("gayani main menu");
			tesmpList   =  ejb.getEstimateListToBeApproved(getCostCenterNo(),status ,getSessionKey("smcType"),getLoggedInUserId(),getSessionKey("region"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (tesmpList!=null){
			this.approveList.addAll(tesmpList);
			if (this.approveList.size() == 0)
				this.approveList = null;
			}else {
				this.approveList = null;
		}
	}
	private void setToBeValidatedEstimates(){
		EstimateEjb ejb=new EstimateEjb(getSessionKey("region"));		
		List<EstimateDisplay> tesmpList = null;
		this.tobeValidateList = new  ArrayList<EstimateDisplay>();	
		List<Long> status = new ArrayList<Long>();
		String username = null;
		if(getSessionKey("userRole").equalsIgnoreCase("EE")){
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey());
			username = getLoggedInUserId();
		}else if(getSessionKey("userRole").equalsIgnoreCase("ES")){
			status.add(StandardEstimateStatus.TO_BE_CHECKED_BY_ELECTRICAL_SUPIRINTENDENT.getKey());
			//status.add(StandardEstimateStatus.TO_BE_CHECKED_BY_ELECTRICAL_SUPIRINTENDENT2.getKey());
			//username = getLoggedInUserId();
		}else if(getSessionKey("userRole").equalsIgnoreCase("CE")){
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey());
			
			
		}else if(getSessionKey("userRole").equalsIgnoreCase("PE")){
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey());
			
		}else if(getSessionKey("userRole").equalsIgnoreCase("PCE")){
			status.add(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_CE.getKey());
			
		}else if(getSessionKey("userRole").equalsIgnoreCase("DGM")){
			status.add(StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey());
			
		}
		
		
		try {
			if(getSessionKey("userRole").equalsIgnoreCase("PE")){
				//
				tesmpList   =  ejb.getEstimateListToBeValidateForPL(getCostCenterNo(),status ,username,getSessionKey("region"));
				
			}else{
				tesmpList   =  ejb.getEstimateListToBeValidate(getCostCenterNo(),status ,getSessionKey("smcType"),username,getSessionKey("region"));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (tesmpList!=null){
			tobeValidateListCount = tesmpList.size();
			this.tobeValidateList.addAll(tesmpList);
			if (this.tobeValidateList.size() == 0)
				this.tobeValidateList = null;
			}else {
				this.tobeValidateList = null;
		}
	}
	
	private void setApprovedEstimates()
	{
		EstimateEjb ejb=new EstimateEjb(getSessionKey("region"));		
		List<EstimateDisplay> tesmpList = null;
		this.approvedEstimateList = new  ArrayList<EstimateDisplay>();				
		try {
			if(getSessionKey("userRole").equalsIgnoreCase("DEO")){
				tesmpList   =  ejb.getApprovedEstimateList(getCostCenterNo(),StandardEstimateStatus.APPROVED_ESTIMATES.getKey(), getSessionKey("smcType"), null,getSessionKey("region"));
			}else{
				tesmpList   =  ejb.getApprovedEstimateList(getCostCenterNo(),StandardEstimateStatus.APPROVED_ESTIMATES.getKey(), getSessionKey("smcType"), getLoggedInUserId(),getSessionKey("region"));
			}
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (tesmpList!=null){
			approvedEstimateListCount = tesmpList.size();
			this.approvedEstimateList.addAll(tesmpList);
			if (this.approvedEstimateList.size() == 0)
				this.approvedEstimateList = null;
			}else {
				this.approvedEstimateList = null;
		}
		
	}

	private void setRejectedRevisedJobs()
	{
		
		JobEjb ejb=new JobEjb(getSessionKey("region"));
		List<Pcesthmt> tesmpList = null;
		this.rejectedRevisedJobsList = new  ArrayList<Pcesthmt>();
		List<Long> statuses = new ArrayList<Long>();
		statuses.add(new Long(EstimateStatus.JOB_REJECTED));
		try {
			tesmpList   = ejb.getRevisedJobDetailList(getCostCenterNo(),statuses,getLoggedInUserId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (tesmpList!=null){
			this.rejectedRevisedJobsList.addAll(tesmpList);	
			if (this.rejectedRevisedJobsList.size() == 0)
				this.rejectedRevisedJobsList = null;
			}else {
				this.rejectedRevisedJobsList = null;
			}
	}
	
	private void setToBeApproveRevisedJobs()
	{
		
		JobEjb ejb=new JobEjb(getSessionKey("region"));
		List<Pcesthmt> tesmpList = null;
		this.toBeApprovedRevisedJobsList = new  ArrayList<Pcesthmt>();
		List<Long> statuses = new ArrayList<Long>();
		List<Long> status = new ArrayList<Long>();
		statuses.add(new Long(EstimateStatus.JOB_APPROVAL_EE));
		//List<Long> statuses = new ArrayList<Long>();
		
		if(getSessionKey("userRole").equalsIgnoreCase("EE")){
			//status = StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey();
			status.add(new Long(EstimateStatus.JOB_APPROVAL_EE));
			statuses.addAll(status);
		}else if(getSessionKey("userRole").equalsIgnoreCase("CE")){
			//status = StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey();
			status.add(new Long(EstimateStatus.JOB_APPROVAL_CE));
			statuses.addAll(status);
		}/*else if(getSessionKey("userRole").equalsIgnoreCase("PE")){
			//status = StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey();
			statuses.add(new Long(EstimateStatus.JOB_APPROVAL_DGM));
		}*/else if(getSessionKey("userRole").equalsIgnoreCase("DGM")){
			status.add(new Long(EstimateStatus.JOB_APPROVAL_DGM));
			//status = StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey();
			statuses.addAll(status);
		}
		try {
			tesmpList   = ejb.getRevisedJobDetailList(getCostCenterNo(),statuses,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (tesmpList!=null){
			this.toBeApprovedRevisedJobsList.addAll(tesmpList);	
			if (this.toBeApprovedRevisedJobsList.size() == 0)
				this.toBeApprovedRevisedJobsList = null;
			}else {
				this.toBeApprovedRevisedJobsList = null;
			}
	}
	private void setToBeValidateRevisedJobs()
	{
		
		JobEjb ejb=new JobEjb(getSessionKey("region"));
		List<Pcesthmt> tesmpList = null;
		this.tobeValidateRevisedJobsList = new  ArrayList<Pcesthmt>();
		List<Long> statuses = new ArrayList<Long>();
		List<Long> status = new ArrayList<Long>();
		statuses.add(new Long(EstimateStatus.JOB_APPROVAL_EE));
		if(getSessionKey("userRole").equalsIgnoreCase("EE")){
			status.add(new Long(EstimateStatus.JOB_APPROVAL_EE));
			statuses.addAll(status);
		}else if(getSessionKey("userRole").equalsIgnoreCase("CE")){
			status.add(new Long(EstimateStatus.JOB_APPROVAL_CE));
			statuses.addAll(status);
		}else if(getSessionKey("userRole").equalsIgnoreCase("PCE")){
			status.add(new Long(EstimateStatus.JOB_APPROVAL_CE));
			statuses.addAll(status);
		}/*else if(getSessionKey("userRole").equalsIgnoreCase("PE")){
			//status = StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey();
			statuses.add(new Long(EstimateStatus.JOB_APPROVAL_DGM));
		}*/else if(getSessionKey("userRole").equalsIgnoreCase("DGM")){
			status.add(new Long(EstimateStatus.JOB_APPROVAL_DGM));
			statuses.addAll(status);
		}
		try {
			tesmpList   = ejb.getRevisedJobDetailList(getCostCenterNo(),statuses,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (tesmpList!=null){
			System.out.println("Hii PCE Revise 1 " + tesmpList.size());
			this.tobeValidateRevisedJobsList.addAll(tesmpList);	
			if (this.tobeValidateRevisedJobsList.size() == 0)
				this.tobeValidateRevisedJobsList = null;
			}else {
				this.tobeValidateRevisedJobsList = null;
			}
		
		System.out.println("Hii PCE Revise 13 " );
	}
	private void setApprovedRevisedJobs()
	{
		
		JobEjb ejb=new JobEjb(getSessionKey("region"));
		List<Pcesthmt> tesmpList = null;
		this.approvedRevisedJobsList = new  ArrayList<Pcesthmt>();
		List<Long> statuses = new ArrayList<Long>();
		statuses.add(new Long(EstimateStatus.JOB_ONGOING));
		try {
			tesmpList   = ejb.getRevisedJobDetailList(getCostCenterNo(),statuses,getLoggedInUserId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (tesmpList!=null){
			this.approvedRevisedJobsList.addAll(tesmpList);	
			if (this.approvedRevisedJobsList.size() == 0)
				this.approvedRevisedJobsList = null;
			}else {
				this.approvedRevisedJobsList = null;
			}
	}
	
	private void setRevisedJobsList()
	{
		JobEjb ejb=new JobEjb(getSessionKey("region"));	
		List<Pcesthmt> tesmpList = null;
		this.revisedJobsList = new  ArrayList<Pcesthmt>();
		String loggedUserLevel = getSessionKey("userRole");
		List<Long> statuses = new ArrayList<Long>();
		statuses.add(new Long(EstimateStatus.JOB_RIVISION));
		//if(loggedUserLevel!=UserRole.ENGINEERINGSUPIRINTED){
		try {
			tesmpList   = ejb.getRevisedJobDetailList(getCostCenterNo(),statuses,getLoggedInUserId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			if (tesmpList!=null){
				this.revisedJobsList.addAll(tesmpList);
				if (this.revisedJobsList.size() == 0)
					this.revisedJobsList = null;
				}else {
					this.revisedJobsList = null;
				}		
		//}else{
		//	this.revisedJobsList = null;
		//}
		
	}
	
	
	private void setAtiveAppoinments()
	{
		String  allocatedTo= getLoggedInUserId();
		String deptId = getCostCenterNo();
		SpestedyEjb ejb = new SpestedyEjb(getSessionKey("region"));
		 
		this.ativeAppoinmentsList = new  ArrayList<Spestedy>();
		 this.ativeAppoinmentsList = ejb.getAtiveAppoinments( deptId,  allocatedTo);
		 if(this.ativeAppoinmentsList != null){
			 ativeAppoinmentsListCount = this.ativeAppoinmentsList.size();
		 }
		 if (this.ativeAppoinmentsList.size() == 0)
				this.ativeAppoinmentsList = null;
		 
		/*  unAttendedAppoinments = new  ArrayList<Spestedy>();
		  unAttendedAppoinments = ejb.getUnAttendedAppoinments( deptId,  allocatedTo);
			 if (this.unAttendedAppoinments.size() == 0)
					this.unAttendedAppoinments = null;
			 
			 
		  failedAppoinments = new  ArrayList<Spestedy>();
		  failedAppoinments = ejb.getFailedAppoinments( deptId,  allocatedTo);
			 if (this.failedAppoinments.size() == 0)
					this.failedAppoinments = null;*/
	}
	private void settobeEnteredStdEstimates()
	{
		String  allocatedTo= getLoggedInUserId();
		String deptId = getCostCenterNo();
		SpestedyEjb ejb = new SpestedyEjb(getSessionKey("region"));
		 
		this.tobeEnteredStdEstimates = new  ArrayList<Spestedy>();
		 //this.tobeEnteredStdEstimates = ejb.getAtiveAppoinmentsforDept( deptId);
		this.tobeEnteredStdEstimates = ejb.getAppoinmentsByStatus( deptId,getSmcType(),AppointmentStatus.ACTIVE);
		if(this.tobeEnteredStdEstimates != null){
			tobeEnteredStdEstimatesCount = tobeEnteredStdEstimates.size();
		}
		
		if (this.tobeEnteredStdEstimates.size() == 0)
				this.tobeEnteredStdEstimates = null;
		
		 
		/*  unAttendedAppoinments = new  ArrayList<Spestedy>();
		  unAttendedAppoinments = ejb.getUnAttendedAppoinments( deptId,  allocatedTo);
			 if (this.unAttendedAppoinments.size() == 0)
					this.unAttendedAppoinments = null;
			 
			 
		  failedAppoinments = new  ArrayList<Spestedy>();
		  failedAppoinments = ejb.getFailedAppoinments( deptId,  allocatedTo);
			 if (this.failedAppoinments.size() == 0)
					this.failedAppoinments = null;*/
	}
	private void setAtiveAppoinmentsForConMain()
	{
		String  allocatedTo= getLoggedInUserId();
		String deptId = getCostCenterNo();
		SpestedyConEjb ejb = new SpestedyConEjb(getSessionKey("region"));
		 
		this.ativeAppoinmentsListConMain = new  ArrayList<SpestedyCons>();
		 this.ativeAppoinmentsListConMain = ejb.getAtiveAppoinments( deptId,  allocatedTo);
		 if(this.ativeAppoinmentsListConMain != null){
			 ativeAppoinmentsListConMainCount = this.ativeAppoinmentsListConMain.size();
		 }
		 if (this.ativeAppoinmentsListConMain.size() == 0)
				this.ativeAppoinmentsListConMain = null;
		 
		 /* unAttendedAppoinmentsConMain = new  ArrayList<SpestedyCons>();
		  unAttendedAppoinmentsConMain = ejb.getUnAttendedAppoinments( deptId,  allocatedTo);
			 if (this.unAttendedAppoinmentsConMain.size() == 0)
					this.unAttendedAppoinmentsConMain = null;
			 
			 
		  failedAppoinmentsConMain = new  ArrayList<SpestedyCons>();
		  failedAppoinmentsConMain = ejb.getFailedAppoinments( deptId,  allocatedTo);
			 if (this.failedAppoinmentsConMain.size() == 0)
					this.failedAppoinmentsConMain = null;*/
	}
	private void setTobeEnteredConEstimates()
	{
		//String  allocatedTo= getLoggedInUserId();
		String deptId = getCostCenterNo();
		SpestedyConEjb ejb = new SpestedyConEjb(getSessionKey("region"));
		 
		this.tobeEnteredDetailEstimates = new  ArrayList<SpestedyCons>();
		 this.tobeEnteredDetailEstimates = ejb.getAtiveAppoinmentsforDept( deptId,  getSessionKey("region"));
		 if(this.tobeEnteredDetailEstimates != null){
			 tobeEnteredDetailEstimatesCount = this.tobeEnteredDetailEstimates.size();
		 }
		 
		 if (this.tobeEnteredDetailEstimates.size() == 0)
				this.tobeEnteredDetailEstimates = null;
		 
		 /* unAttendedAppoinmentsConMain = new  ArrayList<SpestedyCons>();
		  unAttendedAppoinmentsConMain = ejb.getUnAttendedAppoinments( deptId,  allocatedTo);
			 if (this.unAttendedAppoinmentsConMain.size() == 0)
					this.unAttendedAppoinmentsConMain = null;
			 
			 
		  failedAppoinmentsConMain = new  ArrayList<SpestedyCons>();
		  failedAppoinmentsConMain = ejb.getFailedAppoinments( deptId,  allocatedTo);
			 if (this.failedAppoinmentsConMain.size() == 0)
					this.failedAppoinmentsConMain = null;*/
	}
	private void setTobeCreateJobNos()
	{
		String  allocatedTo= getLoggedInUserId();
		String username = getSession().get("userName").toString();
		EstimateEjb  ejb = new EstimateEjb(getSessionKey("region"));
		List<Long> status = new ArrayList<Long>();
		status.add(new Long(EstimateStatus.EST_APPROVED));
		this.tobeCreateJobNos = new  ArrayList<Pcesthtt>();
		 this.tobeCreateJobNos = ejb.getEstimateListForVSLAuthorizedCC(Constants.REV_NO,status,username,null);
		 if (this.tobeCreateJobNos.size() == 0)
				this.tobeCreateJobNos = null;
		 
		/*  unAttendedAppoinments = new  ArrayList<Spestedy>();
		  unAttendedAppoinments = ejb.getUnAttendedAppoinments( deptId,  allocatedTo);
			 if (this.unAttendedAppoinments.size() == 0)
					this.unAttendedAppoinments = null;
			 
			 
		  failedAppoinments = new  ArrayList<Spestedy>();
		  failedAppoinments = ejb.getFailedAppoinments( deptId,  allocatedTo);
			 if (this.failedAppoinments.size() == 0)
					this.failedAppoinments = null;*/
	}
	private void setTobeCreateJobNosVSL()
	{
		//String  allocatedTo= getLoggedInUserId();
		String deptId = getCostCenterNo();
		String username = getSession().get("userName").toString();
		//PcesthttEjb ejb = new PcesthttEjb(getSessionKey("region"));
		List<Long> status = new ArrayList<Long>();
		status.add(new Long(EstimateStatus.EST_APPROVED));
		EstimateEjb  ejb = new EstimateEjb(getSessionKey("region"));
		this.tobeCreateJobNosVSL = new  ArrayList<Pcesthtt>();
		 this.tobeCreateJobNosVSL = ejb.getEstimateListForVSLAuthorizedCC(Constants.REV_NO, status,username,"SPS");
		 if (this.tobeCreateJobNosVSL.size() == 0)
				this.tobeCreateJobNosVSL = null;
		 
		/*  unAttendedAppoinments = new  ArrayList<Spestedy>();
		  unAttendedAppoinments = ejb.getUnAttendedAppoinments( deptId,  allocatedTo);
			 if (this.unAttendedAppoinments.size() == 0)
					this.unAttendedAppoinments = null;
			 
			 
		  failedAppoinments = new  ArrayList<Spestedy>();
		  failedAppoinments = ejb.getFailedAppoinments( deptId,  allocatedTo);
			 if (this.failedAppoinments.size() == 0)
					this.failedAppoinments = null;*/
	}
	
	public String getBranchType() {
		return branchType;
	}



	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}



	public String getJobType() {
		return jobType;
	}



	public void setJobType(String jobType) {
		this.jobType = jobType;
	}



	public String getCostCenterNo() {
		return costCenterNo;
	}
	public void setCostCenterNo(String costCenterNo) {
		this.costCenterNo = costCenterNo;
	}
	
	public String getLoggedInUserId() {
		return loggedInUserId;
	}

	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}

	public String getLoggedInCostCenterNo() {
		return loggedInCostCenterNo;
	}

	public void setLoggedInCostCenterNo(String loggedInCostCenterNo) {
		this.loggedInCostCenterNo = loggedInCostCenterNo;
	}

	public String getLoggedInUserLevel() {
		return loggedInUserLevel;
	}

	public void setLoggedInUserLevel(String loggedInUserLevel) {
		this.loggedInUserLevel = loggedInUserLevel;
	}

	public List<EstimateDisplay> getRejectedList() {
		return rejectedList;
	}

	public void setRejectedList(List<EstimateDisplay> rejectedList) {
		this.rejectedList = rejectedList;
	}

	public List<Pcesthmt> getRejectedRevisedJobsList() {
		return rejectedRevisedJobsList;
	}

	public void setRejectedRevisedJobsList(List<Pcesthmt> rejectedRevisedJobsList) {
		this.rejectedRevisedJobsList = rejectedRevisedJobsList;
	}
	
	public List<EstimateDisplay> getApproveList() {
		return approveList;
	}

	public void setApproveList(List<EstimateDisplay> approveList) {
		this.approveList = approveList;
	}

	

	
	
	public List<SpestedyCons> getAtiveAppoinmentsListConMain() {
		return ativeAppoinmentsListConMain;
	}



	public void setAtiveAppoinmentsListConMain(
			List<SpestedyCons> ativeAppoinmentsListConMain) {
		this.ativeAppoinmentsListConMain = ativeAppoinmentsListConMain;
	}



	public List<SpestedyCons> getUnAttendedAppoinmentsConMain() {
		return unAttendedAppoinmentsConMain;
	}



	public void setUnAttendedAppoinmentsConMain(
			List<SpestedyCons> unAttendedAppoinmentsConMain) {
		this.unAttendedAppoinmentsConMain = unAttendedAppoinmentsConMain;
	}



	public List<SpestedyCons> getFailedAppoinmentsConMain() {
		return failedAppoinmentsConMain;
	}



	public void setFailedAppoinmentsConMain(
			List<SpestedyCons> failedAppoinmentsConMain) {
		this.failedAppoinmentsConMain = failedAppoinmentsConMain;
	}



	public Map<String, String[]> getParameters() {
		return parameters;
	}
	
	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters=parameters;
	}
	
	public Map<String, Object> getSession() {
        return session;

    }

	@Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

	public List<Spestedy> getAtiveAppoinmentsList() {
		return ativeAppoinmentsList;
	}

	public void setAtiveAppoinmentsList(List<Spestedy> ativeAppoinmentsList) {
		this.ativeAppoinmentsList = ativeAppoinmentsList;
	}

	public List<Spestedy> getUnAttendedAppoinments() {
		return unAttendedAppoinments;
	}

	public void setUnAttendedAppoinments(List<Spestedy> unAttendedAppoinments) {
		this.unAttendedAppoinments = unAttendedAppoinments;
	}

	public List<Spestedy> getFailedAppoinments() {
		return failedAppoinments;
	}

	public void setFailedAppoinments(List<Spestedy> failedAppoinments) {
		this.failedAppoinments = failedAppoinments;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public List<Pcesthmt> getRevisedJobsList() {
		return revisedJobsList;
	}

	public void setRevisedJobsList(List<Pcesthmt> revisedJobsList) {
		this.revisedJobsList = revisedJobsList;
	}
	
	public String getSmcType() {
		return smcType;
	}

	public void setSmcType(String smcType) {
		this.smcType = smcType;
	}
	
	public List<EstimateDisplay> getApprovedEstimateList() {
		return approvedEstimateList;
	}

	public void setApprovedEstimateList(List<EstimateDisplay> approvedEstimateList) {
		this.approvedEstimateList = approvedEstimateList;
	}



	public List<EstimateDisplay> getTobeValidateList() {
		return tobeValidateList;
	}



	public void setTobeValidateList(List<EstimateDisplay> tobeValidateList) {
		this.tobeValidateList = tobeValidateList;
	}

	public List<Pcesthtt> getRejectedWorkEList() {
		return rejectedWorkEList;
	}



	public void setRejectedWorkEList(List<Pcesthtt> rejectedWorkEList) {
		this.rejectedWorkEList = rejectedWorkEList;
	}



	public List<Spstdesthmt> getToBeAllocatedConMainList() {
		return toBeAllocatedConMainList;
	}



	public void setToBeAllocatedConMainList(
			List<Spstdesthmt> toBeAllocatedConMainList) {
		this.toBeAllocatedConMainList = toBeAllocatedConMainList;
	}



	public List<Pcesthtt> getApprovedWorkEstimateList() {
		return approvedWorkEstimateList;
	}



	public void setApprovedWorkEstimateList(List<Pcesthtt> approvedWorkEstimateList) {
		this.approvedWorkEstimateList = approvedWorkEstimateList;
	}



	public List<Pcesthtt> getTobeValidateWorkEsList() {
		return tobeValidateWorkEsList;
	}



	public void setTobeValidateWorkEsList(List<Pcesthtt> tobeValidateWorkEsList) {
		this.tobeValidateWorkEsList = tobeValidateWorkEsList;
	}



	public List<Pcesthtt> getTobeApprovedWorkEsList() {
		return tobeApprovedWorkEsList;
	}



	public void setTobeApprovedWorkEsList(List<Pcesthtt> tobeApprovedWorkEsList) {
		this.tobeApprovedWorkEsList = tobeApprovedWorkEsList;
	}



	public List<EstimateDisplay> getNeedToIssuePivIIList() {
		return needToIssuePivIIList;
	}



	public void setNeedToIssuePivIIList(List<EstimateDisplay> needToIssuePivIIList) {
		this.needToIssuePivIIList = needToIssuePivIIList;
	}



	


	public List<ApplicationDisplay> getToBeAllocatedList() {
		return toBeAllocatedList;
	}



	public void setToBeAllocatedList(List<ApplicationDisplay> toBeAllocatedList) {
		this.toBeAllocatedList = toBeAllocatedList;
	}



	public List<EstimateDisplay> getsEsendForValidationList() {
		return sEsendForValidationList;
	}



	public void setsEsendForValidationList(
			List<EstimateDisplay> sEsendForValidationList) {
		this.sEsendForValidationList = sEsendForValidationList;
	}



	public List<Spstdesthmt> getsEsendForConMainList() {
		return sEsendForConMainList;
	}



	public void setsEsendForConMainList(List<Spstdesthmt> sEsendForConMainList) {
		this.sEsendForConMainList = sEsendForConMainList;
	}



	public List<Pcesthtt> getTobeCreateJobNos() {
		return tobeCreateJobNos;
	}



	public void setTobeCreateJobNos(List<Pcesthtt> tobeCreateJonNos) {
		this.tobeCreateJobNos = tobeCreateJonNos;
	}



	public List<Pcesthmt> getApprovedRevisedJobsList() {
		return approvedRevisedJobsList;
	}



	public void setApprovedRevisedJobsList(List<Pcesthmt> approvedRevisedJobsList) {
		this.approvedRevisedJobsList = approvedRevisedJobsList;
	}



	public List<Pcesthmt> getToBeApprovedRevisedJobsList() {
		return toBeApprovedRevisedJobsList;
	}



	public void setToBeApprovedRevisedJobsList(
			List<Pcesthmt> toBeApprovedRevisedJobsList) {
		this.toBeApprovedRevisedJobsList = toBeApprovedRevisedJobsList;
	}



	public List<Pcesthmt> getTobeValidateRevisedJobsList() {
		return tobeValidateRevisedJobsList;
	}



	public void setTobeValidateRevisedJobsList(
			List<Pcesthmt> tobeValidateRevisedJobsList) {
		this.tobeValidateRevisedJobsList = tobeValidateRevisedJobsList;
	}



	public List<Spstdesthmt> getConMainSentList() {
		return conMainSentList;
	}



	public void setConMainSentList(List<Spstdesthmt> conMainSentList) {
		this.conMainSentList = conMainSentList;
	}



	public String getEstimateType() {
		return estimateType;
	}



	public void setEstimateType(String estimateType) {
		this.estimateType = estimateType;
	}



	public List<EstimateDisplay> getNeedToConfirmByCashPivIIList() {
		return needToConfirmByCashPivIIList;
	}



	public void setNeedToConfirmByCashPivIIList(
			List<EstimateDisplay> needToConfirmByCashPivIIList) {
		this.needToConfirmByCashPivIIList = needToConfirmByCashPivIIList;
	}



	public List<EstimateDisplay> getNeedToConfirmByChequePivIIList() {
		return needToConfirmByChequePivIIList;
	}



	public void setNeedToConfirmByChequePivIIList(
			List<EstimateDisplay> needToConfirmByChequePivIIList) {
		this.needToConfirmByChequePivIIList = needToConfirmByChequePivIIList;
	}



	public List<Spestedy> getTobeEnteredStdEstimates() {
		return tobeEnteredStdEstimates;
	}



	public void setTobeEnteredStdEstimates(List<Spestedy> tobeEnteredStdEstimates) {
		this.tobeEnteredStdEstimates = tobeEnteredStdEstimates;
	}



	public List<Pcesthtt> getTobeCreateJobNosVSL() {
		return tobeCreateJobNosVSL;
	}



	public void setTobeCreateJobNosVSL(List<Pcesthtt> tobeCreateJobNosVSL) {
		this.tobeCreateJobNosVSL = tobeCreateJobNosVSL;
	}



	public List<SpestedyCons> getTobeEnteredDetailEstimates() {
		return tobeEnteredDetailEstimates;
	}



	public void setTobeEnteredDetailEstimates(
			List<SpestedyCons> tobeEnteredDetailEstimates) {
		this.tobeEnteredDetailEstimates = tobeEnteredDetailEstimates;
	}



	private void setRejectedWorkEstimates()
	{	
		
		System.out.println("hiii");
		EstimateEjb ejb=new EstimateEjb(getSessionKey("region"));	
		List<Pcesthtt> tesmpList = null;
		System.out.print(getCostCenterNo());
		this.rejectedWorkEList = new  ArrayList<Pcesthtt>();
		List<Long> statuses = new ArrayList<Long>();
		statuses.add(new Long(EstimateStatus.EST_REJECTED));
			try {
				tesmpList   = ejb.getDetailEstimatesList(getCostCenterNo(), statuses,getLoggedInUserId(),null,getSessionKey("region"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("hiii : " +tesmpList );
		if (tesmpList!=null){
			rejectedWorkEListCount = tesmpList.size();
			this.rejectedWorkEList.addAll(tesmpList);
			if (this.rejectedWorkEList.size() == 0)
				this.rejectedWorkEList = null;
			}else {
				this.rejectedWorkEList = null;
		}
	}

	private void setApprovedWorkEstimates()
	{	
		EstimateEjb ejb=new EstimateEjb(getSessionKey("region"));		
		List<Pcesthtt> tesmpList = null;
		System.out.print(getCostCenterNo());
		this.approvedWorkEstimateList = new  ArrayList<Pcesthtt>();
		List<Long> statuses = new ArrayList<Long>();
		
		if(getCostCenterNo().equalsIgnoreCase("543.10") || getCostCenterNo().equalsIgnoreCase("543.20") || getCostCenterNo().equalsIgnoreCase("543.40")){
			statuses.add(new Long(EstimateStatus.EST_POSTED));
		}else{
			statuses.add(new Long(EstimateStatus.EST_POSTING));
		}
			try {
				tesmpList   = ejb.getDetailEstimatesList(getCostCenterNo(), statuses,null,null,getSessionKey("region"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (tesmpList!=null){
			this.approvedWorkEstimateList.addAll(tesmpList);
			if (this.approvedWorkEstimateList.size() == 0)
				this.approvedWorkEstimateList = null;
			}else {
				this.approvedWorkEstimateList = null;
		}
	}
	
	private void setToBeValidateWorkEstimates()
	{	
		System.out.println("hiiiPCE 1" );
		EstimateEjb ejb=new EstimateEjb(getSessionKey("region"));		
		List<Pcesthtt> tesmpList = null;
		System.out.print(getCostCenterNo());
		Long status = null;
		String assignTo = null;
		this.tobeValidateWorkEsList = new  ArrayList<Pcesthtt>();
		if(getSessionKey("userRole").equalsIgnoreCase("ES")){
			status = new Long(EstimateStatus.EST_APPROVAL_ES);
			if(getCostCenterNo().equalsIgnoreCase("430.20") 
					|| getCostCenterNo().equalsIgnoreCase("430.25") 
					|| getCostCenterNo().equalsIgnoreCase("430.30") 
					|| getCostCenterNo().equalsIgnoreCase("430.35")
					|| getCostCenterNo().equalsIgnoreCase("530.20")
					|| getCostCenterNo().equalsIgnoreCase("530.80")){
				assignTo = getLoggedInUserId();
			}
			//assignTo = getLoggedInUserId();
		}else if(getSessionKey("userRole").equalsIgnoreCase("EE")){
			status = new Long(EstimateStatus.EST_APPROVAL_EE);
			if(getCostCenterNo().equalsIgnoreCase("430.20") 
					|| getCostCenterNo().equalsIgnoreCase("430.25") 
					|| getCostCenterNo().equalsIgnoreCase("430.30") 
					|| getCostCenterNo().equalsIgnoreCase("430.35")
					|| getCostCenterNo().equalsIgnoreCase("530.20")
					|| getCostCenterNo().equalsIgnoreCase("530.80")
					|| getCostCenterNo().equalsIgnoreCase("550.30")){
				assignTo = getLoggedInUserId();
			}
			//assignTo = null;
		}else if(getSessionKey("userRole").equalsIgnoreCase("CE")){
			status = new Long(EstimateStatus.EST_APPROVAL_CE);
			
			
		}else if(getSessionKey("userRole").equalsIgnoreCase("PCE")){
			System.out.println("hiiiPCE");
			status = new Long(EstimateStatus.EST_APPROVAL_CE);
			
			
		}else if(getSessionKey("userRole").equalsIgnoreCase("DGM")){
			status = new Long(EstimateStatus.EST_APPROVAL_DGM);
			
		}else if(getSessionKey("userRole").equalsIgnoreCase("AGM")){
			System.out.println("hiii EST_APPROVAL_AGM");
			status = new Long(EstimateStatus.EST_APPROVAL_AGM);
			
		}
		
		List<Long> statuses = new ArrayList<Long>();
		statuses.add(status);
		
		try {
			if(getCostCenterNo().equalsIgnoreCase("501.20")){
				System.out.println("hiii");
				tesmpList   = ejb.getDetailEstimatesListOrderByFundSource(getCostCenterNo(), statuses,null,assignTo,getSessionKey("region"));
			}else{
				System.out.println("hiiiEST_APPROVAL_AGM");
				tesmpList   = ejb.getDetailEstimatesList(getCostCenterNo(), statuses,null,assignTo,getSessionKey("region"));
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (tesmpList!=null){
			tobeValidateWorkEsListCount = tesmpList.size();
			this.tobeValidateWorkEsList.addAll(tesmpList);
			if (this.tobeValidateWorkEsList.size() == 0)
				this.tobeValidateWorkEsList = null;
			}else {
				this.tobeValidateWorkEsList = null;
		}
	}
	private void setToBeApprovedWorkEstimates()	{
		EstimateEjb ejb=new EstimateEjb(getSessionKey("region"));			
		List<Pcesthtt> tesmpList = null;
		this.tobeApprovedWorkEsList = new  ArrayList<Pcesthtt>();
		List<Long> statuses = new ArrayList<Long>();
		//statuses.add(status);
		//List<Long> status = new ArrayList<Long>();
		statuses.add(new Long(EstimateStatus.EST_APPROVAL_EE));
		statuses.add(new Long(EstimateStatus.EST_APPROVAL_CE));
		statuses.add(new Long(EstimateStatus.EST_APPROVAL_DGM));
		//status.add(StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey());
		try {
			tesmpList   = ejb.getDetailEstimatesList(getCostCenterNo(), statuses,getLoggedInUserId(),null,getSessionKey("region"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (tesmpList!=null){
			this.tobeApprovedWorkEsList.addAll(tesmpList);
			if (this.tobeApprovedWorkEsList.size() == 0)
				this.tobeApprovedWorkEsList = null;
			}else {
				this.tobeApprovedWorkEsList = null;
		}
	}
	//for VSL jobs
	
	private void setRejectedVSLEstimates()
	{	
		EstimateEjb ejb=new EstimateEjb(getSessionKey("region"));	
		List<Pcesthtt> tesmpList = null;
		System.out.print(getCostCenterNo());
		this.rejectedWorkEList = new  ArrayList<Pcesthtt>();
		List<Long> statuses = new ArrayList<Long>();
		statuses.add(new Long(EstimateStatus.EST_REJECTED));
			try {
				tesmpList   = ejb.getVSLEstimatesList(getCostCenterNo(), statuses,getLoggedInUserId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (tesmpList!=null){
			this.rejectedWorkEList.addAll(tesmpList);
			if (this.rejectedWorkEList.size() == 0)
				this.rejectedWorkEList = null;
			}else {
				this.rejectedWorkEList = null;
		}
	}

	private void setApprovedVSLEstimates()
	{	
		EstimateEjb ejb=new EstimateEjb(getSessionKey("region"));		
		List<Pcesthtt> tesmpList = null;
		System.out.print(getCostCenterNo());
		this.approvedWorkEstimateList = new  ArrayList<Pcesthtt>();
		List<Long> statuses = new ArrayList<Long>();
		statuses.add(new Long(EstimateStatus.EST_POSTING));
			try {
				tesmpList   = ejb.getVSLEstimatesList(getCostCenterNo(), statuses,null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (tesmpList!=null){
			this.approvedWorkEstimateList.addAll(tesmpList);
			if (this.approvedWorkEstimateList.size() == 0)
				this.approvedWorkEstimateList = null;
			}else {
				this.approvedWorkEstimateList = null;
		}
	}
	
	private void setToBeValidateVSLEstimates()
	{	
		EstimateEjb ejb=new EstimateEjb(getSessionKey("region"));		
		List<Pcesthtt> tesmpList = null;
		System.out.print(getCostCenterNo());
		Long status = null;
		this.tobeValidateWorkEsList = new  ArrayList<Pcesthtt>();
		if(getSessionKey("userRole").equalsIgnoreCase("ES")){
			status = new Long(EstimateStatus.EST_APPROVAL_ES);
			
		}else if(getSessionKey("userRole").equalsIgnoreCase("EE")){
			status = new Long(EstimateStatus.EST_APPROVAL_EE);
			
		}else if(getSessionKey("userRole").equalsIgnoreCase("CE")){
			status = new Long(EstimateStatus.EST_APPROVAL_CE);
			
			
		}else if(getSessionKey("userRole").equalsIgnoreCase("DGM")){
			status = new Long(EstimateStatus.EST_APPROVAL_DGM);
			
		}
		
		List<Long> statuses = new ArrayList<Long>();
		statuses.add(status);
		
		try {
			tesmpList   = ejb.getVSLEstimatesList(getCostCenterNo(), statuses,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (tesmpList!=null){
			this.tobeValidateWorkEsList.addAll(tesmpList);
			if (this.tobeValidateWorkEsList.size() == 0)
				this.tobeValidateWorkEsList = null;
			}else {
				this.tobeValidateWorkEsList = null;
		}
	}
	private void setToBeApprovedVSLEstimates()	{
		EstimateEjb ejb=new EstimateEjb(getSessionKey("region"));			
		List<Pcesthtt> tesmpList = null;
		this.tobeApprovedWorkEsList = new  ArrayList<Pcesthtt>();
		List<Long> statuses = new ArrayList<Long>();
		//statuses.add(status);
		//List<Long> status = new ArrayList<Long>();
		statuses.add(new Long(EstimateStatus.EST_APPROVAL_EE));
		statuses.add(new Long(EstimateStatus.EST_APPROVAL_CE));
		statuses.add(new Long(EstimateStatus.EST_APPROVAL_DGM));
		//status.add(StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey());
		try {
			tesmpList   = ejb.getVSLEstimatesList(getCostCenterNo(), statuses,getLoggedInUserId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (tesmpList!=null){
			this.tobeApprovedWorkEsList.addAll(tesmpList);
			if (this.tobeApprovedWorkEsList.size() == 0)
				this.tobeApprovedWorkEsList = null;
			}else {
				this.tobeApprovedWorkEsList = null;
		}
	}


	public void setTobeValidateListCount(int tobeValidateListCount) {
		this.tobeValidateListCount = tobeValidateListCount;
	}


	public int getTobeValidateListCount() {
		return tobeValidateListCount;
	}


	public void setToBeAllocatedListCount(int toBeAllocatedListCount) {
		this.toBeAllocatedListCount = toBeAllocatedListCount;
	}


	public int getToBeAllocatedListCount() {
		return toBeAllocatedListCount;
	}


	public void setsEsendForConMainListCount(int sEsendForConMainListCount) {
		this.sEsendForConMainListCount = sEsendForConMainListCount;
	}


	public int getsEsendForConMainListCount() {
		return sEsendForConMainListCount;
	}

	//public void setTo

	public void setTobeEnteredStdEstimatesCount(int tobeEnteredStdEstimatesCount) {
		this.tobeEnteredStdEstimatesCount = tobeEnteredStdEstimatesCount;
	}


	public int getTobeEnteredStdEstimatesCount() {
		return tobeEnteredStdEstimatesCount;
	}


	public void setTobeEnteredDetailEstimatesCount(
			int tobeEnteredDetailEstimatesCount) {
		this.tobeEnteredDetailEstimatesCount = tobeEnteredDetailEstimatesCount;
	}


	public int getTobeEnteredDetailEstimatesCount() {
		return tobeEnteredDetailEstimatesCount;
	}


	public void setNeedToIssuePivIIListCount(int needToIssuePivIIListCount) {
		this.needToIssuePivIIListCount = needToIssuePivIIListCount;
	}


	public int getNeedToIssuePivIIListCount() {
		return needToIssuePivIIListCount;
	}
	
	public void setApprovedEstimateListCount(int approvedEstimateListCount) {
		this.approvedEstimateListCount = approvedEstimateListCount;
	}


	public int getApprovedEstimateListCount() {
		return approvedEstimateListCount;
	}
	
	public void setAtiveAppoinmentsListCount(int ativeAppoinmentsListCount) {
		this.ativeAppoinmentsListCount = ativeAppoinmentsListCount;
	}


	public int getAtiveAppoinmentsListCount() {
		return ativeAppoinmentsListCount;
	}
	
	
	public void setRejectedWorkEListCount(int rejectedWorkEListCount) {
		this.rejectedWorkEListCount = rejectedWorkEListCount;
	}


	public int getRejectedWorkEListCount() {
		return rejectedWorkEListCount;
	}
	
		
	public void setToBeAllocatedConMainListCount(int toBeAllocatedConMainListCount) {
		this.toBeAllocatedConMainListCount = toBeAllocatedConMainListCount;
	}


	public int getToBeAllocatedConMainListCount() {
		return toBeAllocatedConMainListCount;
	}
	
	
	
	public void setAtiveAppoinmentsListConMainCount(int ativeAppoinmentsListConMainCount) {
		this.ativeAppoinmentsListConMainCount = ativeAppoinmentsListConMainCount;
	}


	public int getAtiveAppoinmentsListConMainCount() {
		return ativeAppoinmentsListConMainCount;
	}
	
	
	
	public void setTobeValidateWorkEsListCount(int tobeValidateWorkEsListCount) {
		this.tobeValidateWorkEsListCount = tobeValidateWorkEsListCount;
	}


	public int getTobeValidateWorkEsListCount() {
		return tobeValidateWorkEsListCount;
	}
	
	private void setToBeReccomandWorkEstimates()
	{	
		System.out.println("hiiiPCE reccomand" );
		EstimateEjb ejb=new EstimateEjb(getSessionKey("region"));		
		List<Pcesthtt> tesmpList = null;
		System.out.print(getCostCenterNo());
		Long status = null;
		String assignTo = null;
		this.tobeRecommendWorkEsList = new  ArrayList<Pcesthtt>();
		if(getSessionKey("userRole").equalsIgnoreCase("PCE")){
			System.out.println("hiiiPCE reccomand");
			status = new Long(EstimateStatus.EST_APPROVAL_PLCE);
			
			
		}else if(getSessionKey("userRole").equalsIgnoreCase("CEC")){
			status = new Long(EstimateStatus.EST_APPROVAL_COMCE);
			
		}
		
		List<Long> statuses = new ArrayList<Long>();
		statuses.add(status);
		
		try {
			tesmpList   = ejb.getDetailEstimatesList(getCostCenterNo(), statuses,null,assignTo,getSessionKey("region"));
				
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (tesmpList!=null){
			tobeRecommendWorkEsListCount = tesmpList.size();
			this.tobeRecommendWorkEsList.addAll(tesmpList);
			if (this.tobeRecommendWorkEsList.size() == 0)
				this.tobeRecommendWorkEsList = null;
			}else {
				this.tobeRecommendWorkEsList = null;
		}
	}
	
	private void getMessages(){

        

        String region =getSessionKey("region");

        //MsgPoolEjb ejb=new MsgPoolEjb(region);

        CostCenterEjb costCenterejb=new CostCenterEjb(region);

        msgPoolList=new ArrayList<MsgPool>();

       //msgPoolList=costCenterejb.getMesseges(costCenterNo, loggedInUserId, loggedInUserLevel);

       //dashBoard="OFF";

        for (int i=0; i<msgPoolList.size();i++){

              if (msgPoolList.get(i).getMsgId().trim().equals("2013/May/09-002")){

                    if (msgPoolList.get(i).getMsg().trim().equals("ON"));

                        //  dashBoard="ON";

        }else{

             // System.out.println(dashBoard);

        }

        }

           //System.out.println("* "+ msgPoolList.get(i));

        if (this.msgPoolList.size() == 0)

              this.msgPoolList = null;

        

  }

	//toBeAllocatedPLListCount
	
	public void setToBeAllocatedPLListCount(int toBeAllocatedPLListCount) {
		this.toBeAllocatedPLListCount = toBeAllocatedPLListCount;
	}



	public int getToBeAllocatedPLListCount() {
		return toBeAllocatedPLListCount;
	}


	public void setToBeAllocatedPlanningList(List<Spstdesthmt> toBeAllocatedPLList) {
		this.toBeAllocatedPlanningList = toBeAllocatedPLList;
	}



	public List<Spstdesthmt> getToBeAllocatedPlanningList() {
		return toBeAllocatedPlanningList;
	}


}
