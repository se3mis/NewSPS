package estimate.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;


import util.common.Constants;
import util.common.EstimateStatus;
import util.common.Format;
import util.common.PivPrefixType;

import application.ejb.WiringLandDetailDaoRemote;
import application.model.CityMap;
import application.service.WiringLandDetailEjb;

import com.opensymphony.xwork2.ActionSupport;

import estimate.model.EstimateReference;
import estimate.model.Pcestdtt;
import estimate.model.Pcesthtt;
import estimate.service.EstimateEjb;
import estimate.service.EstimateReferenceEjb;
import estimate.service.PcestdttEjb;
import estimate.service.PcesthttEjb;


@SuppressWarnings("serial")
public class JobCreator extends ActionSupport implements SessionAware, ParameterAware{
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
	
	
	private Format format;
	


	public String execute(){
		setLoggedData();
		setState("SELECTED");
		System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
		PcesthttEjb ejb=new PcesthttEjb(getSessionKey("region"));
		HttpServletRequest request = ServletActionContext.getRequest();
		String costCenter = (String) request.getSession().getAttribute("cSCNo");
		if(costCenter != null){
			costCenterNo = costCenter;
		}
		List<String> estimateNoList=ejb.getEstimateNoList(costCenterNo, new Long(2), new Long(EstimateStatus.EST_APPROVED));
		setEstimateNoList(estimateNoList);
		//System.out.println(ejb.getEstimateNoList(costCenterNo, new BigDecimal(EstimateStatus.EST_POSTING)));
		if (costCenterNo.equals("541.00") || costCenterNo.equals("542.00") || costCenterNo.equals("547.00") || costCenterNo.equals("548.00")) {
			WiringLandDetailEjb  ejbWiringLandDetail=new WiringLandDetailEjb(getSessionKey("region"));
			String serviceCity=ejbWiringLandDetail.getServiceCity(estimateNo, getSessionKey("costCenterNo"));
			System.out.println(serviceCity);
			CityMap cityMap=new CityMap();
			setcSCNo(cityMap.mapCity(costCenterNo, serviceCity));
		}else setcSCNo(costCenterNo);
		setJobNo("");
		return SUCCESS;
		
	}
	
	public String jobCreatorDirectdashBoard(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String sessionKey= (String) request.getSession().getAttribute("region");
		String estimateNo=request.getParameter("estNo");
		String costCen=request.getParameter("costCen");
		if(estimateNo!=null && estimateNo.length()>0 && costCen!=null && costCen.length()>0){
			setEstimateNo(estimateNo);
			setCostCenterNo(costCen);
			
		}
		request.getSession().setAttribute("estNo",estimateNo != null ? estimateNo : "");
		request.getSession().setAttribute("cSCNo",costCen != null ? costCen : "");
		//setGenerator("ON");
		setState("NEW");
		setLoggedData();
		System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
		PcesthttEjb ejb=new PcesthttEjb(getSessionKey("region"));
		estimateNoList = new ArrayList<String>();
		estimateNoList.add(estimateNo);
		
		setEstimateNoList(estimateNoList);
		//System.out.println(ejb.getEstimateNoList(costCenterNo, new BigDecimal(EstimateStatus.EST_POSTING)));
		return SUCCESS;
		
	}
	
	public String jobCreatorDirect(){
		setGenerator("ON");
		setState("NEW");
		setLoggedData();
		System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
		PcesthttEjb ejb=new PcesthttEjb(getSessionKey("region"));
		System.out.println(costCenterNo);
		
		List<String> estimateNoList=ejb.getEstimateNoList(costCenterNo, new Long(2), new Long(EstimateStatus.EST_APPROVED));
		//comment for SAN_TEST
		//List<String> estimateNoList=ejb.getEstimateNoList(costCenterNo, new Long(2), new Long(EstimateStatus.EST_POSTED));
		
		setEstimateNoList(estimateNoList);
		//System.out.println(ejb.getEstimateNoList(costCenterNo, new BigDecimal(EstimateStatus.EST_POSTING)));
		return SUCCESS;
		
	}
	
	public String generateJobNo() throws Exception{
		setLoggedData();
		EstimateEjb ejbEstimateEjb=new EstimateEjb(getSessionKey("region"));
		PcesthttEjb ejbPcesthttEjb=new PcesthttEjb(getSessionKey("region"));
		PcestdttEjb ejbPcestdttEjb=new PcestdttEjb(getSessionKey("region"));
		//Pcesthtt pcesthtt=ejbPcesthttEjb.findByEstimationNo(estimateNo, costCenterNo);
		System.out.println(estimateNo+"#"+costCenterNo+"#");
		Pcesthtt pcesthtt=ejbPcesthttEjb.findByEstimationNo(estimateNo, cSCNo,new Long(2));
		List<Pcestdtt> list=ejbPcestdttEjb.findByEstimationNo(estimateNo, cSCNo,new Long(2));
		//System.out.println(pcesthtt);
		//System.out.println(list);
		
		//if(getJobNo() != null && getJobNo().length() != 0){
			//String jobNoPrefix = null;
			//jobNoPrefix = getReferenceForJobNumber(cSCNo,estimateNo);
			//jobNoPrefix=cSCNo+"/"+getFormat().getYear()+"/";
			
			setJobNo(ejbEstimateEjb.transferToHmt(pcesthtt, list, cSCNo,estimateNo));
			//System.out.println(costCenterNo);
			List<String> estimateNoList=ejbPcesthttEjb.getEstimateNoList(costCenterNo, new Long(2), new Long(EstimateStatus.EST_APPROVED));
			setEstimateNoList(estimateNoList);
			//List<String> estimateNoList=new ArrayList<String>();
			estimateNoList.add(estimateNo);
			setEstimateNoList(estimateNoList);
			setMessageType(MSG_DONE);
			setErrorMessage("Job number: "+getJobNo() +" for Estimate no:" + estimateNo);
			//setGenerator("OFF");
			setState("GENERATED");
		//}
		
		return SUCCESS;
		
	}
	public String getReferenceForJobNumber(String cSCNo,String applicationId){
		
		
		String format =null;
		if(applicationId.contains(Constants.SPS_ESTIMATE_CODE)){
			format =  applicationId.trim().replace(Constants.SPS_ESTIMATE_CODE, Constants.SPS_JOB_NUMBER_CODE).substring(0,14);
		}else{
			format = cSCNo+"/"+getFormat().getYear()+"/";
		}
		return format;
		
	}
	/*public String createJob() throws Exception{
		setLoggedData();
		EstimateEjb ejbEstimateEjb=new EstimateEjb(getSessionKey("region"));
		PcesthttEjb ejbPcesthttEjb=new PcesthttEjb(getSessionKey("region"));
		PcestdttEjb ejbPcestdttEjb=new PcestdttEjb(getSessionKey("region"));
		//Pcesthtt pcesthtt=ejbPcesthttEjb.findByEstimationNo(estimateNo, costCenterNo);
		System.out.println(estimateNo+"#"+costCenterNo+"#");
		Pcesthtt pcesthtt=ejbPcesthttEjb.findByEstimationNo(estimateNo, costCenterNo,new Long(2));
		List<Pcestdtt> list=ejbPcestdttEjb.findByEstimationNo(estimateNo, costCenterNo,new Long(2));
		//System.out.println(pcesthtt);
		//System.out.println(list);
		
		if(getJobNo() != null && getJobNo().length() != 0){
			//comment for SAN_TEST
			//String jobNoPrefix=cSCNo+"/"+PivPrefixType.getJOB_XXX_Type(getSessionKey("smcType"))+"/"+getFormat().getYear()+"/";
		
			//setJobNo(ejbEstimateEjb.transferToHmt(pcesthtt, list, cSCNo,jobNoPrefix));
			
			ejbEstimateEjb.transferToHmt(pcesthtt, list, cSCNo,getJobNo());
			System.out.println(costCenterNo);
			List<String> estimateNoList=ejbPcesthttEjb.getEstimateNoList(costCenterNo, new Long(2), new Long(EstimateStatus.EST_POSTED));
			setEstimateNoList(estimateNoList);
			//List<String> estimateNoList=new ArrayList<String>();
			estimateNoList.add(estimateNo);
			setEstimateNoList(estimateNoList);
			
			setGenerator("OFF");
			setState("GENERATED");
		}
		
		return SUCCESS;
		
	}*/
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
		HttpServletRequest request = ServletActionContext.getRequest();
		String costCenter = (String) request.getSession().getAttribute("cSCNo");
		if(costCenter != null){
			costCenterNo = costCenter;
			//setCostCenterNo(costCenterNo);
		}
		
		setcSCNo(costCenterNo);
		
		setLoggedInUserId(getSessionKey("userName"));
        setCostCenterName(getSessionKey("costCenterName"));
        setFormat(new Format());
        
        
        
        
    }
	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
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
	
	

	
	
}
