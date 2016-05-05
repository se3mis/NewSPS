package estimate.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import piv.model.PivDetail;
import piv.service.PivDetailEjb;

import util.common.EstimateStatus;
import util.common.Format;

import application.model.Applicant;
import application.model.Application;
import application.model.CityMap;
import application.model.WiringLandDetail;
import application.service.ApplicantEjb;
import application.service.ApplicationEjb;
import application.service.WiringLandDetailEjb;

import com.opensymphony.xwork2.ActionSupport;

import estimate.ejb.PcesthttDaoRemote;
import estimate.model.Approval;
import estimate.model.LabourGrid;
import estimate.model.MaterialGrid;
import estimate.model.Pcestdtt;
import estimate.model.Pcesthtt;
import estimate.model.Spestlab;
import estimate.model.Speststd;
import estimate.model.SpeststdPK;
import estimate.model.Spserest;
import estimate.model.SpserestPK;
import estimate.service.EstimateEjb;
import estimate.service.PcestdttEjb;
import estimate.service.PcesthttEjb;
import estimate.service.SpestlabEjb;
import estimate.service.SpeststdEjb;
import estimate.service.SpserestEjb;


@SuppressWarnings("serial")
public class EstimateViewer extends ActionSupport implements SessionAware, ParameterAware{
	private static final String MSG_ERROR="ERROR";
	private static final String MSG_INFO="INFO";
	private static final String MSG_DONE="DONE";
	private static final String MSG_NONE="NONE";
	private String errorMessage;
	private String messageType=MSG_NONE;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private String CostCenterName;
	private String estimateNo;
	private List<String> estimateNoList;
	private String generator="OFF";
	private String state;


	private String jobNo;
	private String costCenterNo;
	private String cSCNo;
	private List<MaterialGrid> materialGrid;
	private List<LabourGrid> labourGrid;
	//
	private String fullName;
	private String applicationDate;
	private String fullAddress;
	private String assessmentNo;
	private String neighboursAccNo;
	private String phase;
	private String connectionType;
	private String tariffCategoryCode;
	private String tariffCode;
	private String jobDescription;
	private String categoryCode;
	private String totalLineLength;
	private String conductorType;
	private String conductorLength;
	private String serviceType;
	private String serviceLength;
	private String wiringType;
	private String isLoopService;
	private String capitalCost;
	private String cableType;
	private String spans;
	private String noOfPoles;
	private String noOfStays;
	private String noOfStruts;
	private String sINNo;
	private String phaseColour;
	private String distanceToServicePlace;
	private String fundSource;
	//
	private String fixedCost;
	private String variableCost;
	private String subTotal;
	private String otherCostM;
	private String otherCostL;
	private String taxAmount;
	private String securityDeposit;
	private String estimatedTotalCost;
	
	
	public String execute(){
		setLoggedData();
		//setState("SELECTED");
		System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD "+ getEstimateNo()+estimateNo);
		//PcesthttEjb ejb=new PcesthttEjb(getSessionKey("region"));
		//List<String> estimateNoList=ejb.getEstimateNoList(costCenterNo, new Long(1), new BigDecimal(EstimateStatus.EST_POSTING));
		//setEstimateNoList(estimateNoList);
		//System.out.println(ejb.getEstimateNoList(costCenterNo, new BigDecimal(EstimateStatus.EST_POSTING)));
		if (costCenterNo.equals("541.00") || costCenterNo.equals("542.00") || costCenterNo.equals("547.00") || costCenterNo.equals("548.00")) {
			WiringLandDetailEjb  ejbWiringLandDetail=new WiringLandDetailEjb(getSessionKey("region"));
			String serviceCity=ejbWiringLandDetail.getServiceCity(estimateNo, getSessionKey("costCenterNo"));
			System.out.println(serviceCity);
			CityMap cityMap=new CityMap();
			setcSCNo(cityMap.mapCity(costCenterNo, serviceCity));
		}else setcSCNo(costCenterNo);
		setJobNo("");
		System.out.println("getState() "+getState());
		if (getState().equals("REVERSE_APPROVED_EST")){
			EstimateEjb ejbEstimateEjb=new EstimateEjb(getSessionKey("region"));
			materialGrid=ejbEstimateEjb.getMaterialGrid(estimateNo, costCenterNo);
			setMaterialGrid(materialGrid);
			SpestlabEjb  spestlabEjb=new SpestlabEjb(getSessionKey("region"));
			labourGrid=spestlabEjb.getSpestlabList(estimateNo, costCenterNo);
			setLabourGrid(labourGrid);
			//System.out.println("labourGrid "+labourGrid);
			///
			PcesthttEjb ejbPcesthtt=new PcesthttEjb(getSessionKey("region"));
			List<String> list=new ArrayList<String>();
			if (getSessionKey("smcType").equals("MT"))
				list=ejbPcesthtt.getApprovedEstMA_SA(costCenterNo,getSessionKey("userName"), "33", "33");
			else
				list=ejbPcesthtt.getApprovedEst(costCenterNo, getSessionKey("smcType"),getSessionKey("userName"), "30", "30");
			setEstimateNoList(list);
			setEstimateNo(estimateNo);
			/////
			
			
			
			ApplicantEjb applicantEjb=new ApplicantEjb(getSessionKey("region"));
			ApplicationEjb applicationEjb=new ApplicationEjb(getSessionKey("region"));
			WiringLandDetailEjb wiringLandDetailEjb=new WiringLandDetailEjb(getSessionKey("region"));
			PivDetailEjb pivDetailEjb=new PivDetailEjb(getSessionKey("region"));
			SpeststdEjb speststdEjb=new SpeststdEjb(getSessionKey("region"));
			PcesthttEjb pcesthttEjb=new PcesthttEjb(getSessionKey("region"));
			SpserestEjb spserestEjb=new SpserestEjb(getSessionKey("region"));
			//Applicant  Applicant=applicantEjb.
			
			Application application = applicationEjb.findByApplicationNo(estimateNo);
			if (application!=null){
				Applicant  Applicant=applicantEjb.findById(application.getIdNo());
				if (Applicant!=null){
					WiringLandDetail  wiringLandDetail=wiringLandDetailEjb.findByApplicationNo(estimateNo);
					if (wiringLandDetail!=null){
						//
						Speststd speststd=speststdEjb.findByEstimateNo(estimateNo);
						
						if(speststd!=null){
							setStandardDetail(speststd);
							//PivDetail pivDetail=pivDetailEjb.findByReferenceNo(estimateNo, "EST");
							//if (pivDetail!=null){
							//	setPivDetail(pivDetail);
							//}else {
							//	pivDetail=pivDetailEjb.findByReferenceNo(estimateNo, "ELN");
							//	setPivDetail(pivDetail);
							//}
							//if (pivDetail==null){
							//	pivDetail=pivDetailEjb.findByReferenceNo(estimateNo, "APP");
							//	setPivDetail(pivDetail);
							//}else{
							//	setMessageType(MSG_ERROR);
							//	setErrorMessage("ERROR:- PIV Detail Not Found");
							//	return ERROR;
							//}//
							Pcesthtt pcesthtt=pcesthttEjb.findByEstimationNo(estimateNo);
							if(pcesthtt!=null){
								setPcesthttDetail(pcesthtt);
								SpserestPK spserestPK=new SpserestPK();
								spserestPK.setApplicationNo(estimateNo);
								spserestPK.setDeptId(application.getId().getDeptId());
								Spserest spserest=spserestEjb.findById(spserestPK);
								if(spserest!=null){
									setSpserestDetail(spserest);
									
								}else{
									setMessageType(MSG_ERROR);
									setErrorMessage("ERROR:- spserest Detail Not Found");
									return ERROR;
								}
							}else{
								setMessageType(MSG_ERROR);
								setErrorMessage("ERROR:- Pcesthtt Detail Not Found");
								return ERROR;
							}
							
						}else{
							setMessageType(MSG_ERROR);
							setErrorMessage("ERROR:- Standard Costing Detail Not Found");
							return ERROR;
						}
						
					setWiringDetail(wiringLandDetail);
					}else{
						setMessageType(MSG_ERROR);
						setErrorMessage("ERROR:- Wiring Detail Not Found");
						return ERROR;
					}
				setApplicantDetail(Applicant);
				}else{
					setMessageType(MSG_ERROR);
					setErrorMessage("ERROR:- Applicant Detail Not Found");
					return ERROR;
				}
				
			setApplicationDetail(application);	
			}else{
				setMessageType(MSG_ERROR);
				setErrorMessage("ERROR:- Application Detail Not Found");
				return ERROR;
			}
			return SUCCESS;
			
		}else{
			return SUCCESS;
		}
		
		
		
		
	}
	
	
	


	





	private void setStandardDetail(Speststd speststd) {
		if (speststd.getFixedCost()!=null)
		setFixedCost(speststd.getFixedCost().toString());
		if (speststd.getVariableCost()!=null)
		setVariableCost(speststd.getVariableCost().toString());
		//setSubTotal(speststd.gets);
		if (speststd.getOtherCost()!=null)
		setOtherCostM(speststd.getOtherCost().toString());
		//setOtherCostL(otherCostL);
		//setTaxAmount(speststd.ge);
		if (speststd.getCapitalCost()!=null)
		setCapitalCost(speststd.getCapitalCost().toString());
		if (speststd.getSecurityDeposit()!=null)
		setSecurityDeposit(speststd.getSecurityDeposit().toString());
		if (speststd.getTotalCost()!=null)
		setEstimatedTotalCost(speststd.getTotalCost().toString());
		if (speststd.getIsLoopService()!=null)
		setIsLoopService(speststd.getIsLoopService());
		if (speststd.getStayNo()!=null)
		setNoOfStays(speststd.getStayNo().toString());
		if (speststd.getStrutNo()!=null)
		setNoOfStruts(speststd.getStrutNo().toString());
		if (speststd.getPoleNo()!=null)
		setNoOfPoles(speststd.getPoleNo().toString());
		if (speststd.getLineLength()!=null)
		setTotalLineLength(speststd.getLineLength().toString());
		setPhaseColour(speststd.getTransformColor());
	}


	private void setApplicationDetail(Application application) {
		Format format=new Format();
		setApplicationDate(format.formatDate(application.getAddDate()));
		
	}


	private void setApplicantDetail(Applicant applicant) {
		setFullName(applicant.getFirstName()+" "+applicant.getLastName());
		
		
	}


	private void setWiringDetail(WiringLandDetail wiringLandDetail) {
		setAssessmentNo(wiringLandDetail.getAssessmentNo());
		setNeighboursAccNo(wiringLandDetail.getNeighboursAccNo());
		setPhase(wiringLandDetail.getPhase().toString());
		setConnectionType(wiringLandDetail.getConnectionType().toString());
		setTariffCategoryCode(wiringLandDetail.getTariffCatCode());
		setTariffCode(wiringLandDetail.getTariffCode());
		setFullAddress(wiringLandDetail.getServiceStreetAddress() +" "+wiringLandDetail.getServiceSuburb()+" "+ wiringLandDetail.getServiceCity());
		
		
	}


	private void setPivDetail(PivDetail pivDetail) {
		// TODO Auto-generated method stub
		
	}
	
	private void setPcesthttDetail(Pcesthtt pcesthtt) {
		setCategoryCode(pcesthtt.getCatCd());
		setFundSource(pcesthtt.getFundSource());
	}
	
	private void setSpserestDetail(Spserest spserest) {
		setServiceLength(spserest.getServiceLength());
		setWiringType(spserest.getWiringType());
		if (spserest.getNoOfSpans()!=null)
		setSpans(spserest.getNoOfSpans().toString());
		setsINNo(spserest.getSin());
		setTotalLineLength(spserest.getTotalLength());
		if (spserest.getBareconLength()!=null)
		setConductorLength(spserest.getBareconLength().toString());
		setConductorType(spserest.getBareconType());
		setServiceType(spserest.getServiceWireType());
		if (spserest.getDistanceFromSs()!=null)
		setDistanceToServicePlace(spserest.getDistanceToSp().toString());
	}
	
	
	public String cancelApprovalDirect(){
		//setGenerator("ON");
		setState("REVERSE_APPROVED_EST");
		setLoggedData();
		System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
		//PcesthttEjb ejb=new PcesthttEjb(getSessionKey("region"));
		PcesthttEjb ejb=new PcesthttEjb(getSessionKey("region"));
		List<String> list=new ArrayList<String>();
		if (getSessionKey("smcType").equals("MT"))
			list=ejb.getApprovedEstMA_SA(costCenterNo,getSessionKey("userName"), "33", "33");
		else
			list=ejb.getApprovedEst(costCenterNo, getSessionKey("smcType"),getSessionKey("userName"), "30", "30");
		
		System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD "+list);
		setEstimateNoList(list);
		//EstimateEjb ejbEstimateEjb=new EstimateEjb(getSessionKey("region"));
		//ejbEstimateEjb.getMaterialGrid(estimateNo, costCenterNo);
		//System.out.println(costCenterNo);
		//materialGrid=ejbEstimateEjb.getMaterialGrid("514.20/ENC/2011/0931", "514.20");
		//setMaterialGrid(materialGrid);
		//SpestlabEjb  spestlabEjb=new SpestlabEjb(getSessionKey("region"));
		//labourGrid=spestlabEjb.getSpestlabList("514.20/ENC/2011/0931", "514.20");
		//setLabourGrid(labourGrid);
		System.out.println("labourGrid "+labourGrid);
		return SUCCESS;
		
	}


	public String estimateDisplayDirect(){
		//setGenerator("ON");
		//setState("NEW");
		setLoggedData();
		//System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
		//PcesthttEjb ejb=new PcesthttEjb(getSessionKey("region"));
		PcesthttEjb ejb=new PcesthttEjb(getSessionKey("region"));
		System.out.println(costCenterNo);
		List<String> estimateNoList=ejb.getEstimateNoList(costCenterNo, new Long(1), new Long(EstimateStatus.EST_POSTING));
		setEstimateNoList(estimateNoList);
		EstimateEjb ejbEstimateEjb=new EstimateEjb(getSessionKey("region"));
		ejbEstimateEjb.getMaterialGrid(estimateNo, costCenterNo);
		System.out.println(costCenterNo);
		materialGrid=ejbEstimateEjb.getMaterialGrid("514.20/ENC/2011/0931", "514.20");
		setMaterialGrid(materialGrid);
		SpestlabEjb  spestlabEjb=new SpestlabEjb(getSessionKey("region"));
		labourGrid=spestlabEjb.getSpestlabList("514.20/ENC/2011/0931", "514.20");
		setLabourGrid(labourGrid);
		System.out.println("labourGrid "+labourGrid);
		return SUCCESS;
		
	}
	
	public String jobCreatorDirect(){
		setGenerator("ON");
		setState("NEW");
		setLoggedData();
		System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
		PcesthttEjb ejb=new PcesthttEjb(getSessionKey("region"));
		System.out.println(costCenterNo);
		List<String> estimateNoList=ejb.getEstimateNoList(costCenterNo, new Long(1), new Long(EstimateStatus.EST_POSTING));
		estimateNoList.add("514.20/ENC/2011/0931");
		setEstimateNoList(estimateNoList);
		//System.out.println(ejb.getEstimateNoList(costCenterNo, new BigDecimal(EstimateStatus.EST_POSTING)));
		return SUCCESS;
		
	}
	
	public String generateJobNo(){
		EstimateEjb ejbEstimateEjb=new EstimateEjb(getSessionKey("region"));
		PcesthttEjb ejbPcesthttEjb=new PcesthttEjb(getSessionKey("region"));
		PcestdttEjb ejbPcestdttEjb=new PcestdttEjb(getSessionKey("region"));
		//Pcesthtt pcesthtt=ejbPcesthttEjb.findByEstimationNo(estimateNo, costCenterNo);
		System.out.println(estimateNo+"#"+costCenterNo+"#");
		Pcesthtt pcesthtt=ejbPcesthttEjb.findByEstimationNo(estimateNo, costCenterNo);
		List<Pcestdtt> list=ejbPcestdttEjb.findByEstimationNo(estimateNo, costCenterNo);
		//System.out.println(pcesthtt);
		//System.out.println(list);
		setJobNo(ejbEstimateEjb.transfer(pcesthtt, list, cSCNo));
		System.out.println(costCenterNo);
		//List<String> estimateNoList=ejbPcesthttEjb.getEstimateNoList(costCenterNo, new Long(1), new BigDecimal(EstimateStatus.EST_POSTING));
		//setEstimateNoList(estimateNoList);
		List<String> estimateNoList=new ArrayList<String>();
		estimateNoList.add(estimateNo);
		setEstimateNoList(estimateNoList);
		
		setGenerator("OFF");
		setState("GENERATED");
		return SUCCESS;
		
	}
	
	public String cancelApproval() {
		try{
			setLoggedData();
			SpeststdEjb speststdEjb=new SpeststdEjb(getSessionKey("region"));
			PcesthttEjb pcesthttEjb=new PcesthttEjb(getSessionKey("region"));
			//PivDetailEjb pivDetailEjb=new PivDetailEjb(getSessionKey("region"));
			Pcesthtt pcesthtt=pcesthttEjb.findByEstimationNo(estimateNo);
			
			//List <String> listNo= pivDetailEjb.getNewPivNos(pcesthtt.getId().getDeptId(), "EST", "N");
			//if ()
			//pcesthtt.getId().getDeptId();
			//listNo= pivDetailDaoRemote.getNewPivNos(deptId, "EST", "N", webAppName);
			//Approvals
			Calendar calendar = Calendar.getInstance();
			Format format=new Format();
			Approval approval=new Approval();
			approval.setApprovalId(null);
			approval.setReferenceNo(estimateNo.trim());
			approval.setDeptId(pcesthtt.getId().getDeptId());
			approval.setApprovalType("EST_CLAP");
			approval.setApprovedLevel(getSessionKey("userRole"));
			approval.setApprovedBy(getLoggedInUserId());
			approval.setApprovedDate(calendar.getTime());
			approval.setApprovedTime(format.FormatTime());
			if (getSessionKey("smcType").equals("MT"))
				approval.setFromStatus(new BigDecimal(EstimateStatus.EST_POSTING).toString());
			else
				approval.setFromStatus(new BigDecimal(EstimateStatus.EST_APPROVED).toString());
			approval.setToStatus(new BigDecimal(EstimateStatus.MODIFIED).toString());
			SpeststdPK id=new SpeststdPK();
			id.setDeptId(pcesthtt.getId().getDeptId());
			id.setEstimateNo(estimateNo.trim());
			approval.setStandardCost(speststdEjb.findById(id).getTotalCost());
			//approval.setDetailedCost(pcesthttEjb.findByEstimationNo(estimateNo.trim(), costCenterNo).getStdCost());
			approval.setDetailedCost(pcesthttEjb.findByEstimationNo(estimateNo).getStdCost());
			PcesthttEjb ejbPcesthtt=new PcesthttEjb(getSessionKey("region"));
			//ejbPcesthtt.changeStatusPcesthtt(estimateNo, costCenterNo, new BigDecimal(EstimateStatus.MODIFIED));
			ejbPcesthtt.cancelApproval(estimateNo, pcesthtt.getId().getDeptId(), approval);
			//////
			PcesthttEjb ejb=new PcesthttEjb(getSessionKey("region"));
			List<String> list=new ArrayList<String>();
			if (getSessionKey("smcType").equals("MT"))
				list=ejbPcesthtt.getApprovedEstMA_SA(costCenterNo,getSessionKey("userName"), "33", "33");
			else
				list=ejbPcesthtt.getApprovedEst(costCenterNo, getSessionKey("smcType"),getSessionKey("userName"), "30", "30");
			System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD "+list);
			setEstimateNoList(list);
			/////
			setMessageType(MSG_DONE);
			setErrorMessage("Estimate has been sent to modify status");
			return SUCCESS;
		}catch (Exception e) {
			e.printStackTrace();
			setMessageType(MSG_ERROR);
			setErrorMessage("ERROR:- "+e.getMessage());
			return ERROR;
		}
			
		
	}
	
	public String close() {
		setLoggedData();
		//getEm().close(); 
		//getEmf().close(); 
		return "close";
	}
	
	public void setLoggedData() {
        //log.info(getSession());
		setCostCenterNo(getSessionKey("costCenterNo"));
		/*if (costCenterNo.equals("541.00") || costCenterNo.equals("542.00") || costCenterNo.equals("547.00") || costCenterNo.equals("548.00")) {
		//	WiringLandDetailEjb  ejb=new WiringLandDetailEjb(getSessionKey("region"));
			String serviceCity=ejb.getServiceCity(estimateNo, getSessionKey("costCenterNo"));
			System.out.println(serviceCity);
			CityMap cityMap=new CityMap();
			setcSCNo(cityMap.mapCity(costCenterNo, serviceCity));
		}else*/
		setcSCNo(costCenterNo);
		setLoggedInUserId(getSessionKey("userName"));
        setCostCenterName(getSessionKey("costCenterName"));
       
        
        
        
        
    }
 	
 	public String getSessionKey(String key) {
        return getSession().get(key).toString();
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
	
	public Map<String, Object> getSession() {
		return session;

	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public Map <String, String[]> getParameters() {
		return parameters;
	}
		

	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters=parameters;
		
	}
	public String getCostCenterName() {
		return CostCenterName;
	}

	public void setCostCenterName(String costCenterName) {
		CostCenterName = costCenterName;
	}

	public String getLoggedInUserId() {
		return loggedInUserId;
	}

	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}

	public String getCostCenterNo() {
		return costCenterNo;
	}

	public void setCostCenterNo(String costCenterNo) {
		this.costCenterNo = costCenterNo;
	}
	
	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}
	
	public String getEstimateNo() {
		return estimateNo;
	}


	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
	}
	


	public List<String> getEstimateNoList() {
		return estimateNoList;
	}


	public void setEstimateNoList(List<String> estimateNoList) {
		this.estimateNoList = estimateNoList;
	}

	public String getGenerator() {
		return generator;
	}


	public void setGenerator(String generator) {
		this.generator = generator;
	}



	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}
	public String getcSCNo() {
		return cSCNo;
	}


	public void setcSCNo(String cSCNo) {
		this.cSCNo = cSCNo;
	}
	
	public List<MaterialGrid> getMaterialGrid() {
		return materialGrid;
	}


	public void setMaterialGrid(List<MaterialGrid> materialGrid2) {
		this.materialGrid = materialGrid2;
	}


	public List<LabourGrid> getLabourGrid() {
		return labourGrid;
	}


	public void setLabourGrid(List<LabourGrid> labourGrid) {
		this.labourGrid = labourGrid;
	}
	
	
	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getApplicationDate() {
		return applicationDate;
	}


	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}


	public String getFullAddress() {
		return fullAddress;
	}


	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
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


	public String getTariffCategoryCode() {
		return tariffCategoryCode;
	}


	public void setTariffCategoryCode(String tariffCategoryCode) {
		this.tariffCategoryCode = tariffCategoryCode;
	}


	public String getTariffCode() {
		return tariffCode;
	}


	public void setTariffCode(String tariffCode) {
		this.tariffCode = tariffCode;
	}


	public String getJobDescription() {
		return jobDescription;
	}


	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}


	public String getCategoryCode() {
		return categoryCode;
	}


	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}


	public String getTotalLineLength() {
		return totalLineLength;
	}


	public void setTotalLineLength(String totalLineLength) {
		this.totalLineLength = totalLineLength;
	}


	public String getConductorType() {
		return conductorType;
	}


	public void setConductorType(String conductorType) {
		this.conductorType = conductorType;
	}


	public String getConductorLength() {
		return conductorLength;
	}


	public void setConductorLength(String conductorLength) {
		this.conductorLength = conductorLength;
	}


	public String getServiceType() {
		return serviceType;
	}


	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}


	public String getServiceLength() {
		return serviceLength;
	}


	public void setServiceLength(String serviceLength) {
		this.serviceLength = serviceLength;
	}


	public String getWiringType() {
		return wiringType;
	}


	public void setWiringType(String wiringType) {
		this.wiringType = wiringType;
	}


	public String getIsLoopService() {
		return isLoopService;
	}


	public void setIsLoopService(String isLoopService) {
		this.isLoopService = isLoopService;
	}


	public String getCapitalCost() {
		return capitalCost;
	}


	public void setCapitalCost(String capitalCost) {
		this.capitalCost = capitalCost;
	}


	public String getCableType() {
		return cableType;
	}


	public void setCableType(String cableType) {
		this.cableType = cableType;
	}


	public String getSpans() {
		return spans;
	}


	public void setSpans(String spans) {
		this.spans = spans;
	}


	public String getNoOfPoles() {
		return noOfPoles;
	}


	public void setNoOfPoles(String noOfPoles) {
		this.noOfPoles = noOfPoles;
	}


	public String getNoOfStays() {
		return noOfStays;
	}


	public void setNoOfStays(String noOfStays) {
		this.noOfStays = noOfStays;
	}


	public String getNoOfStruts() {
		return noOfStruts;
	}


	public void setNoOfStruts(String noOfStruts) {
		this.noOfStruts = noOfStruts;
	}


	public String getsINNo() {
		return sINNo;
	}


	public void setsINNo(String sINNo) {
		this.sINNo = sINNo;
	}


	public String getPhaseColour() {
		return phaseColour;
	}


	public void setPhaseColour(String phaseColour) {
		this.phaseColour = phaseColour;
	}


	public String getDistanceToServicePlace() {
		return distanceToServicePlace;
	}


	public void setDistanceToServicePlace(String distanceToServicePlace) {
		this.distanceToServicePlace = distanceToServicePlace;
	}


	public String getFundSource() {
		return fundSource;
	}


	public void setFundSource(String fundSource) {
		this.fundSource = fundSource;
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


	public String getOtherCostM() {
		return otherCostM;
	}


	public void setOtherCostM(String otherCostM) {
		this.otherCostM = otherCostM;
	}


	public String getOtherCostL() {
		return otherCostL;
	}


	public void setOtherCostL(String otherCostL) {
		this.otherCostL = otherCostL;
	}


	public String getTaxAmount() {
		return taxAmount;
	}


	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}


	public String getSecurityDeposit() {
		return securityDeposit;
	}


	public void setSecurityDeposit(String securityDeposit) {
		this.securityDeposit = securityDeposit;
	}


	public String getEstimatedTotalCost() {
		return estimatedTotalCost;
	}


	public void setEstimatedTotalCost(String estimatedTotalCost) {
		this.estimatedTotalCost = estimatedTotalCost;
	}

	

	
	
}
