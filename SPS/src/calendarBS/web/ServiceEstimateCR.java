package calendarBS.web;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import job.web.ListObject;
import offDoc.web.PrintServiceEstimateForm;
import offDoc.web.ServiceEstimateForm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import util.common.Format;
import application.model.Applicant;
import application.model.Application;
import application.model.Spapplon;
import application.model.SpapplonPK;
import application.model.WiringLandDetail;
import application.model.WiringLandDetailPK;
import application.service.ApplicantEjb;
import application.service.ApplicationEjb;
import application.service.SpapplonEjb;
import application.service.WiringLandDetailEjb;
import com.opensymphony.xwork2.ActionSupport;
import estimate.model.Spserest;
import estimate.model.SpserestPK;
import estimate.model.Spsetpol;
import estimate.model.SpsetpolPK;
import estimate.model.Spsetstu;
import estimate.model.SpsetstuPK;
import estimate.model.Spsetsty;
import estimate.model.SpsetstyPK;
import estimate.model.Spsetwir;
import estimate.model.SpsetwirPK;
import estimate.service.SpestedyEjb;
import estimate.service.SpserestEjb;
import estimate.service.SpsetpolEjb;
import estimate.service.SpsetstuEjb;
import estimate.service.SpsetstyEjb;
import estimate.service.SpsetwirEjb;


public class ServiceEstimateCR extends ActionSupport implements SessionAware, ParameterAware{

	private static final Log log = LogFactory.getLog(ServiceEstimateCR.class);
	private static final String newPath="Calendar>Service Estimate CR";
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String path;
	private String loggedInUserId;
	private String costCenterNo;
	private String csc;
	private String statusMsgErr;
	private String appName;
	private String appArea;
	private String appAddress;
	private String appCSC;
	private String appTphone;
	private String appSerType;
	private String appTariff;
	private String appNo;
	private String CategoryName;
	private String totalLength;
	private String wiringType;
	private String hid_AddedStayList;
	private String hid_AddedStrutList;
	private String hid_AddedSerLengthsList;	
	private String hid_AddedPoleList;
	private String distancetoServicePlace;
	private List<ListObject> excavationDetailsList;
	private List<ListObject>  erectionDetailsList;
	private List<ListObject> insStaysDetailsList;
	private List<ListObject> wireDetailsList;
	private List<ListObject> conductorDetailsList;
	private List<ListObject> reapirExistingDetailsList;
	private List<ListObject> changeExistingDetailsList;
	private List<ListObject> removalDetailsList;
	private List<ListObject> removalPoleDetailsList;	
	private List<ListObject> cableTypeList;
	private List<String> insStaysTypeList;
	private String statusMsg;	
	private List<String>  poleTypeList;
	private List<String> polePointList;
	private List<String> conductorList;	
	private String hid_RemovePoles;
	private String hid_RemoveStruts;
	private String hid_RemoveStays;
	private String hid_RemoveSerLengths;
	private String wireType;
	private String poleType;
	private String fromConductor;
	private String toConductor;
	private String  noOfSpans;
	private String neighborsAcct;
	private String tarrifCode;
	private String polePointer;
	private String isLoopType;
	private String cableType;
	private String hid_CableType;
	private String connectionType;
	private String hid_SavedWireLengths;	
	private String substation;
	private String sin;
	private String distanceFrmSS;
	private String phase;
	private String transformerCapacity;
	private String poleNo;
	private String transformerLoad;
	private String transformerPeakLoad;
	private String feederControltype;
	private String hid_selectedStringPoles;
	private String hid_selectedStringStruts;
	private String hid_selectedStringStays;
	private String hid_selectedStringSerLengths;
	private String Category;
	private String Item;		                                                                              
	private String Quantity;
	private String Description;
	private String CategoryId;
	private String conversionLength;
	private String hid_ConversionLength;
	private String isConversion;
	
	
	public String getIsConversion() {
		return isConversion;
	}
	public void setIsConversion(String isConversion) {
		this.isConversion = isConversion;
	}
	public String getHid_ConversionLength() {
		return hid_ConversionLength;
	}
	public void setHid_ConversionLength(String hid_ConversionLength) {
		this.hid_ConversionLength = hid_ConversionLength;
	}
	public String getConversionLength() {
		return conversionLength;
	}
	public void setConversionLength(String conversionLength) {
		this.conversionLength = conversionLength;
	}
	public String getHid_SavedWireLengths() {
		return hid_SavedWireLengths;
	}
	public void setHid_SavedWireLengths(String hid_SavedWireLengths) {
		this.hid_SavedWireLengths = hid_SavedWireLengths;
	}
	public String getCableType() {
		return cableType;
	}
	public void setCableType(String cableType) {
		this.cableType = cableType;
	}
	public String getIsLoopType() {
		return isLoopType;
	}
	public void setIsLoopType(String isLoopType) {
		this.isLoopType = isLoopType;
	}
	public List<String> getPolePointList() {
		return polePointList;
	}	
	public String getHid_CableType() {
		return hid_CableType;
	}	
	public String getConnectionType() {
		return connectionType;
	}
	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}
	public void setHid_CableType(String hid_CableType) {
		this.hid_CableType = hid_CableType;
	}
	public void setPolePointList(List<String> polePointList) {
		this.polePointList = polePointList;
	}
	public List<String> getInsStaysTypeList() {
		return insStaysTypeList;
	}
	public void setInsStaysTypeList(List<String> insStaysTypeList) {
		this.insStaysTypeList = insStaysTypeList;
	}
	public String getDistancetoServicePlace() {
		return distancetoServicePlace;
	}
	public void setDistancetoServicePlace(String distancetoServicePlace) {
		this.distancetoServicePlace = distancetoServicePlace;
	}
	public String getTarrifCode() {
		return tarrifCode;
	}
	public void setTarrifCode(String tarrifCode) {
		this.tarrifCode = tarrifCode;
	}
	public String getNeighborsAcct() {
		return neighborsAcct;
	}
	
	public List<ListObject> getCableTypeList() {
		return cableTypeList;
	}
	public void setCableTypeList(List<ListObject> cableTypeList) {
		this.cableTypeList = cableTypeList;
	}
	public void setNeighborsAcct(String neighborsAcct) {
		this.neighborsAcct = neighborsAcct;
	}
	public String getNoOfSpans() {
		return noOfSpans;
	}
	public void setNoOfSpans(String noOfSpans) {
		this.noOfSpans = noOfSpans;
	}
	public String getHid_AddedPoleList() {
		return hid_AddedPoleList;
	}
	public void setHid_AddedPoleList(String hid_AddedPoleList) {
		this.hid_AddedPoleList = hid_AddedPoleList;
	}
	
	public String getPolePointer() {
		return polePointer;
	}
	public void setPolePointer(String polePointer) {
		this.polePointer = polePointer;
	}
	public String getHid_AddedStayList() {
		return hid_AddedStayList;
	}
	public void setHid_AddedStayList(String hid_AddedStayList) {
		this.hid_AddedStayList = hid_AddedStayList;
	}
	public String getHid_AddedStrutList() {
		return hid_AddedStrutList;
	}
	public void setHid_AddedStrutList(String hid_AddedStrutList) {
		this.hid_AddedStrutList = hid_AddedStrutList;
	}
	public String getHid_AddedSerLengthsList() {
		return hid_AddedSerLengthsList;
	}
	public void setHid_AddedSerLengthsList(String hid_AddedSerLengthsList) {
		this.hid_AddedSerLengthsList = hid_AddedSerLengthsList;
	}

	public String getWiringType() {
		return wiringType;
	}
	public void setWiringType(String wiringType) {
		this.wiringType = wiringType;
	}
	public String getTotalLength() {
		return totalLength;
	}
	public void setTotalLength(String totalLength) {
		this.totalLength = totalLength;
	}
	public String getCategoryName() {
		return CategoryName;
	}
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
	
	public String getStatusMsg() {
		return statusMsg;
	}
	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public String getWireType() {
		return wireType;
	}
	public void setWireType(String wireType) {
		this.wireType = wireType;
	}

	public String getHid_RemovePoles() {
		return hid_RemovePoles;
	}
	public void setHid_RemovePoles(String hid_RemovePoles) {
		this.hid_RemovePoles = hid_RemovePoles;
	}
	public String getHid_RemoveStruts() {
		return hid_RemoveStruts;
	}
	public void setHid_RemoveStruts(String hid_RemoveStruts) {
		this.hid_RemoveStruts = hid_RemoveStruts;
	}
	public String getHid_RemoveStays() {
		return hid_RemoveStays;
	}
	public void setHid_RemoveStays(String hid_RemoveStays) {
		this.hid_RemoveStays = hid_RemoveStays;
	}
	public String getHid_RemoveSerLengths() {
		return hid_RemoveSerLengths;
	}
	public void setHid_RemoveSerLengths(String hid_RemoveSerLengths) {
		this.hid_RemoveSerLengths = hid_RemoveSerLengths;
	}
	public String getPoleType() {
		return poleType;
	}
	public void setPoleType(String poleType) {
		this.poleType = poleType;
	}
	public String getFromConductor() {
		return fromConductor;
	}
	public void setFromConductor(String fromConductor) {
		this.fromConductor = fromConductor;
	}
	public String getToConductor() {
		return toConductor;
	}
	public void setToConductor(String toConductor) {
		this.toConductor = toConductor;
	}

	public List<String> getConductorList() {
		return conductorList;
	}
	public void setConductorList(List<String> conductorList) {
		this.conductorList = conductorList;
	}
	public List<String> getPoleTypeList() {
		return poleTypeList;
	}
	public void setPoleTypeList(List<String> poleTypeList) {
		this.poleTypeList = poleTypeList;
	}

	private List<ServiceEstimateDetails> list2;
	private ServiceEstimateDetailsList serviceEstimateDetailsList;

	private List<ServiceEstimateItemDetails> list1;
	private ServiceEstimateItemDetailsList serviceEstimatePoleDetailsList;

	private List<ServiceEstimateDetails> list3;
	private ServiceEstimateDetailsList serviceEstimateStayDetailsList;

	private List<ServiceEstimateDetails> list4;
	private ServiceEstimateDetailsList serviceEstimateServiceLenList;
	
	public String getHid_selectedStringStruts() {
		return hid_selectedStringStruts;
	}
	public void setHid_selectedStringStruts(String hid_selectedStringStruts) {
		this.hid_selectedStringStruts = hid_selectedStringStruts;
	}
	public String getHid_selectedStringStays() {
		return hid_selectedStringStays;
	}
	public void setHid_selectedStringStays(String hid_selectedStringStays) {
		this.hid_selectedStringStays = hid_selectedStringStays;
	}
	public String getHid_selectedStringSerLengths() {
		return hid_selectedStringSerLengths;
	}
	public void setHid_selectedStringSerLengths(String hid_selectedStringSerLengths) {
		this.hid_selectedStringSerLengths = hid_selectedStringSerLengths;
	}
	public List<ServiceEstimateDetails> getList4() {
		return list4;
	}
	public void setList4(List<ServiceEstimateDetails> list4) {
		this.list4 = list4;
	}
	public ServiceEstimateDetailsList getServiceEstimateServiceLenList() {
		return serviceEstimateServiceLenList;
	}
	public void setServiceEstimateServiceLenList(
			ServiceEstimateDetailsList serviceEstimateServiceLenList) {
		this.serviceEstimateServiceLenList = serviceEstimateServiceLenList;
	}

	private String  hid_isValueFilled;	


	public List<ServiceEstimateItemDetails> getList1() {
		return list1;
	}
	public void setList1(List<ServiceEstimateItemDetails> list1) {
		this.list1 = list1;
	}

	public List<ServiceEstimateDetails> getList3() {
		return list3;
	}
	public void setList3(List<ServiceEstimateDetails> list3) {
		this.list3 = list3;
	}
	public ServiceEstimateDetailsList getServiceEstimateStayDetailsList() {
		return serviceEstimateStayDetailsList;
	}
	public void setServiceEstimateStayDetailsList(
			ServiceEstimateDetailsList serviceEstimateStayDetailsList) {
		this.serviceEstimateStayDetailsList = serviceEstimateStayDetailsList;
	}
	public ServiceEstimateItemDetailsList getServiceEstimatePoleDetailsList() {
		return serviceEstimatePoleDetailsList;
	}
	public void setServiceEstimatePoleDetailsList(
			ServiceEstimateItemDetailsList serviceEstimatePoleDetailsList) {
		this.serviceEstimatePoleDetailsList = serviceEstimatePoleDetailsList;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getItem() {
		return Item;
	}
	public void setItem(String item) {
		Item = item;
	}
	public String getQuantity() {
		return Quantity;
	}
	public void setQuantity(String quantity) {
		Quantity = quantity;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getCategoryId() {
		return CategoryId;
	}
	public void setCategoryId(String categoryId) {
		CategoryId = categoryId;
	}

	public String getHid_selectedStringPoles() {
		return hid_selectedStringPoles;
	}
	public void setHid_selectedStringPoles(String hid_selectedStringPoles) {
		this.hid_selectedStringPoles = hid_selectedStringPoles;
	}
	public List<ListObject> getExcavationDetailsList() {
		return excavationDetailsList;
	}
	public void setExcavationDetailsList(List<ListObject> excavationDetailsList) {
		this.excavationDetailsList = excavationDetailsList;
	}
	public List<ListObject> getErectionDetailsList() {
		return erectionDetailsList;
	}
	public void setErectionDetailsList(List<ListObject> erectionDetailsList) {
		this.erectionDetailsList = erectionDetailsList;
	}
	public List<ListObject> getInsStaysDetailsList() {
		return insStaysDetailsList;
	}
	public void setInsStaysDetailsList(List<ListObject> insStaysDetailsList) {
		this.insStaysDetailsList = insStaysDetailsList;
	}
	public List<ListObject> getWireDetailsList() {
		return wireDetailsList;
	}
	public void setWireDetailsList(List<ListObject> wireDetailsList) {
		this.wireDetailsList = wireDetailsList;
	}
	public List<ListObject> getConductorDetailsList() {
		return conductorDetailsList;
	}
	public void setConductorDetailsList(List<ListObject> conductorDetailsList) {
		this.conductorDetailsList = conductorDetailsList;
	}
	public List<ListObject> getReapirExistingDetailsList() {
		return reapirExistingDetailsList;
	}
	public void setReapirExistingDetailsList(
			List<ListObject> reapirExistingDetailsList) {
		this.reapirExistingDetailsList = reapirExistingDetailsList;
	}
	public List<ListObject> getChangeExistingDetailsList() {
		return changeExistingDetailsList;
	}
	public void setChangeExistingDetailsList(
			List<ListObject> changeExistingDetailsList) {
		this.changeExistingDetailsList = changeExistingDetailsList;
	}
	public List<ListObject> getRemovalDetailsList() {
		return removalDetailsList;
	}
	public void setRemovalDetailsList(List<ListObject> removalDetailsList) {
		this.removalDetailsList = removalDetailsList;
	}
	public List<ListObject> getRemovalPoleDetailsList() {
		return removalPoleDetailsList;
	}
	public void setRemovalPoleDetailsList(List<ListObject> removalPoleDetailsList) {
		this.removalPoleDetailsList = removalPoleDetailsList;
	}
		
	public String getSubstation() {
		return substation;
	}
	public void setSubstation(String substation) {
		this.substation = substation;
	}
	public String getSin() {
		return sin;
	}
	public void setSin(String sin) {
		this.sin = sin;
	}
	public String getDistanceFrmSS() {
		return distanceFrmSS;
	}
	public void setDistanceFrmSS(String distanceFrmSS) {
		this.distanceFrmSS = distanceFrmSS;
	}
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
	public String getTransformerCapacity() {
		return transformerCapacity;
	}
	public void setTransformerCapacity(String transformerCapacity) {
		this.transformerCapacity = transformerCapacity;
	}
	public String getPoleNo() {
		return poleNo;
	}
	public void setPoleNo(String poleNo) {
		this.poleNo = poleNo;
	}
	public String getTransformerLoad() {
		return transformerLoad;
	}
	public void setTransformerLoad(String transformerLoad) {
		this.transformerLoad = transformerLoad;
	}
	public String getTransformerPeakLoad() {
		return transformerPeakLoad;
	}
	public void setTransformerPeakLoad(String transformerPeakLoad) {
		this.transformerPeakLoad = transformerPeakLoad;
	}
	public String getFeederControltype() {
		return feederControltype;
	}
	public void setFeederControltype(String feederControltype) {
		this.feederControltype = feederControltype;
	}	

	public Map<String, String[]> getParameters() {
		return parameters;
	}
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters = parameters;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public String getHid_isValueFilled() {
		return hid_isValueFilled;
	}
	public void setHid_isValueFilled(String hid_isValueFilled) {
		this.hid_isValueFilled = hid_isValueFilled;
	}

	public String getStatusMsgErr() {
		return statusMsgErr;
	}
	public void setStatusMsgErr(String statusMsgErr) {
		this.statusMsgErr = statusMsgErr;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppArea() {
		return appArea;
	}
	public void setAppArea(String appArea) {
		this.appArea = appArea;
	}	
	public String getAppAddress() {
		return appAddress;
	}
	public void setAppAddress(String appAddress) {
		this.appAddress = appAddress;
	}
	public String getAppCSC() {
		return appCSC;
	}
	public void setAppCSC(String appCSC) {
		this.appCSC = appCSC;
	}
	public String getAppTphone() {
		return appTphone;
	}
	public void setAppTphone(String appTphone) {
		this.appTphone = appTphone;
	}
	public String getAppSerType() {
		return appSerType;
	}
	public void setAppSerType(String appSerType) {
		this.appSerType = appSerType;
	}
	public String getAppTariff() {
		return appTariff;
	}
	public void setAppTariff(String appTariff) {
		this.appTariff = appTariff;
	}
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	public static String getNewpath() {
		return newPath;
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
	public String getCsc() {
		return csc;
	}
	public void setCsc(String csc) {
		this.csc = csc;
	}
	
	public Map<String, Object> getSession() {
		return session;	
	}
	
	public void setLoggedData() {       
        log.info(getSession());
        setLoggedInUserId(getSessionKey("userName"));
        setCostCenterNo(getSessionKey("costCenterNo"));
        setCsc(getSessionKey("costCenterName"));       
    }
	
	public String getSessionKey(String key) {
        return getSession().get(key).toString();
    }	
	
	public ServiceEstimateDetailsList getServiceEstimateDetailsList() {
		return serviceEstimateDetailsList;
	}
	public void setServiceEstimateDetailsList(
			ServiceEstimateDetailsList serviceEstimateDetailsList) {
		this.serviceEstimateDetailsList = serviceEstimateDetailsList;
	}

	public List<ServiceEstimateDetails> getList2() {
		return list2;
	}
	public void setList2(List<ServiceEstimateDetails> list2) {
		this.list2 = list2;
	}
	public String execute()
	{		
		try{	
			setLoggedData();
			hid_isValueFilled = "";
			Format format=new Format();
			String appNoPreFix = getSessionKey("costCenterNo")+"/"+getsmcType()+"/"+format.getYear()+"/";
			appNo = appNoPreFix;			
			setControls();		
			wireType = "SERVICE";
			wiringType = "OH";
			
			return "success";
		}catch(Exception ex){
			return "error";
		}
	}
	
	
	public String close(){
		return "closed";
	}
	
	
	private String getsmcType(){
		String type=getSessionKey("smcType");
		
		if(type.equals("NC"))
			type = "ENC";
		else if(type.equals("CR"))
			type = "ECR";
		else if(type.equals("TC"))
			type = "ETC";
		
		return type.trim();
	}
	//action invoke when Find button is clicked
	public String viewApplicantDetails()
	{
		try
		{		setLoggedData();		
				costCenterNo = getSessionKey("costCenterNo");
				ApplicationEjb appEjb = new ApplicationEjb(getSessionKey("region"));				
				Application appc = appEjb.findByApplicationNo(appNo,costCenterNo,getSessionKey("smcType"));
				
				
				if(appc==null)
				{
					statusMsgErr = "Application does not exist.";
					hid_isValueFilled = "";
					setControls();
					return "success";
					
				}
				else 
				{									
									
					Application application = appEjb.findByApplicationNo(appNo,costCenterNo);
					SpestedyEjb spesTedyEjb=new SpestedyEjb(getSessionKey("region")); 		
					String appointmentStatus = spesTedyEjb.getAppointmentStatus(appNo, costCenterNo) ;		
									
					if(appointmentStatus==null){
						statusMsgErr = "Cant save...You havent put an appointment for the application";
						hid_isValueFilled = "cantsave";
						
					}else if(appointmentStatus=="E" || appointmentStatus.equals("E")){
						statusMsgErr = "Cant save...You have already created an estimate for the application";
						hid_isValueFilled = "cantsave";						
					
					}else if(appointmentStatus=="F" || appointmentStatus.equals("F")){
						statusMsgErr = "Cant save...The appointment has failed";					
						hid_isValueFilled = "cantsave";						
					}else{
						hid_isValueFilled = "filled";	
					}
					
					if(application!=null){
						
						
						String hasAppLoan = application.getIsLoanApp();
						if(hasAppLoan!=null && hasAppLoan.equalsIgnoreCase("Y"))
						{
							SpapplonEjb spapplonEjb = new SpapplonEjb(getSessionKey("region"));
							SpapplonPK spapplonPK = new SpapplonPK();
							spapplonPK.setApplicationNo(appNo);
							spapplonPK.setDeptId(costCenterNo);							
							
							Spapplon spapplon = spapplonEjb.findById(spapplonPK);
							if(spapplon==null)
							{								
								statusMsgErr = "Cant save...Enter the loan details first";					
								hid_isValueFilled = "cantsave";	
							}
						}
						
						setApplicantData(application);					
						setWiringData(application);	
						setExistingDetails();
						
						serviceEstimatePoleDetailsList= new ServiceEstimateItemDetailsList();
				    	list1 = new ArrayList<ServiceEstimateItemDetails>(); 
						
						serviceEstimateDetailsList= new ServiceEstimateDetailsList();
				    	list2 = new ArrayList<ServiceEstimateDetails>(); 				    		    
				    	
				    	serviceEstimateStayDetailsList= new ServiceEstimateDetailsList();
				    	list3 = new ArrayList<ServiceEstimateDetails>(); 
				    	
				    	serviceEstimateServiceLenList= new ServiceEstimateDetailsList();
				    	list4 = new ArrayList<ServiceEstimateDetails>(); 				    
				          				    	
				    	//Set Pole Details
				    	SpsetpolEjb spsetpolEjb = new SpsetpolEjb(getSessionKey("region"));
				    	List<Spsetpol> poleList= spsetpolEjb.getPoleList(appNo,costCenterNo);
				    
				    	Iterator<Spsetpol> it1 = poleList.iterator();
						StringBuffer stayBuff=new StringBuffer();
						StringBuffer strutBuff=new StringBuffer();
						StringBuffer serLenBuff=new StringBuffer();
						StringBuffer poleBuff=new StringBuffer();
						
						while (it1.hasNext()) {        	 
							Spsetpol info=it1.next();
							ServiceEstimateItemDetails record1=new ServiceEstimateItemDetails(info.getId().getMatCd(),getCategoryDescription(info.getId().getMatCd()),info.getId().getPoleType(),info.getId().getFromConductor(),info.getId().getToConductor(),info.getMatQty().toString(),info.getId().getPointType());
					    	serviceEstimatePoleDetailsList.addList(list1,record1);
					    	if(poleBuff.toString().equals(""))
					    		poleBuff.append(info.getId().getMatCd().trim()+info.getId().getPoleType().trim()+info.getId().getFromConductor().trim()+info.getId().getToConductor().trim()+info.getId().getPointType().trim());	
					    	else
					    		poleBuff.append(","+info.getId().getMatCd().trim()+info.getId().getPoleType().trim()+info.getId().getFromConductor().trim()+info.getId().getToConductor().trim()+info.getId().getPointType().trim());					    	
						}
						
						//Set Struts Details
				    	SpsetstuEjb spsetstuEjb = new SpsetstuEjb(getSessionKey("region"));
				    	List<Spsetstu> strutsList= spsetstuEjb.getStrutList(appNo,costCenterNo);
				    
				    	Iterator<Spsetstu> it2 = strutsList.iterator();
						
						while (it2.hasNext()) {        	 
							Spsetstu info=it2.next();
							ServiceEstimateDetails record1=new ServiceEstimateDetails("",getCategoryDescription(info.getId().getMatCd()),info.getMatQty().toString(),"",info.getId().getMatCd());
							serviceEstimateDetailsList.addList(list2,record1); 
							
							if(strutBuff.toString().equals(""))
								strutBuff.append(info.getId().getMatCd().trim());
					    	else
					    		strutBuff.append(","+info.getId().getMatCd().trim());
						}	
				    		
				    	// Set Stay details			    	
						
				    	SpsetstyEjb spsetstyEjb = new SpsetstyEjb(getSessionKey("region"));
				    	List<Spsetsty> stayList= spsetstyEjb.getStayList(appNo,costCenterNo);				    
				    	Iterator<Spsetsty> it3 = stayList.iterator();
						
						while (it3.hasNext()) {        	 
							Spsetsty info=it3.next();
							ServiceEstimateDetails record1=new ServiceEstimateDetails("",getCategoryDescription(info.getId().getMatCd()),info.getMatQty().toString(),"",info.getId().getMatCd(),info.getId().getStayType());
							serviceEstimateStayDetailsList.addList(list3,record1); 
							if(stayBuff.toString().equals(""))
					    		stayBuff.append(info.getId().getMatCd().trim()+info.getId().getStayType());
					    	else
					    		stayBuff.append(","+info.getId().getMatCd().trim()+info.getId().getStayType());
							
						}									
				     				    		
						// Set Service Length details			    	
						
						SpsetwirEjb spsetwirEjb = new SpsetwirEjb(getSessionKey("region"));
				    	List<Spsetwir> wireList =  spsetwirEjb.getWireList(appNo,costCenterNo);				    
				    	Iterator<Spsetwir> it4 = wireList.iterator();
						
						while (it4.hasNext()) {        	 
							Spsetwir info=it4.next();
							ServiceEstimateDetails record1=new ServiceEstimateDetails("",info.getWireType(),info.getWireLength().toString(),"",info.getId().getMatCd());
							serviceEstimateServiceLenList.addList(list4,record1); 
							if(serLenBuff.toString().equals(""))
								serLenBuff.append(info.getId().getMatCd());
					    	else
					    		serLenBuff.append(","+info.getId().getMatCd());
							
						}									
				     
						hid_AddedStayList = stayBuff.toString();
						hid_AddedStrutList =strutBuff.toString();
						hid_AddedSerLengthsList = serLenBuff.toString();	    	
						hid_AddedPoleList = poleBuff.toString();
					}		
					setControls();
					System.out.println("No erorr.............................................................");
					return "success";
				}				
			
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("The error is............:"+ex);
			return "success";
		}
		
	}
	
	//set the application data to the form fields
	private void setApplicantData(Application application)
	{
		ApplicantEjb applicantEJB = new ApplicantEjb(getSessionKey("region"));
		Applicant applicant = applicantEJB.findById(application.getIdNo());
		appName = applicant.getFirstName()+" "+applicant.getLastName();
		//appAddress = applicant.getStreetAddress()+", "+applicant.getSuburb()+", "+applicant.getCity();
		
		appCSC= costCenterNo;
		//appArea	= applicant.getCity();
		if(applicant.getTelephoneNo()!=null)
			appTphone = applicant.getTelephoneNo().toString();	
		
	}
	
	//set wiring details to the form fields
	private void setWiringData(Application application)
	{
		WiringLandDetailEjb wlEJB = new WiringLandDetailEjb(getSessionKey("region"));
		WiringLandDetailPK wlPK = new WiringLandDetailPK();
		wlPK.setApplicationId(application.getId().getApplicationId());
		wlPK.setDeptId(costCenterNo);
		WiringLandDetail wld = wlEJB.findByAppId(wlPK);
		
		int phaseInt = wld.getPhase().intValue();
		
		if(phaseInt==1)
			appSerType  = "Single Phase";
		else
			appSerType  = "Three Phase";		
		
		if(wld.getConnectionType()!=null)
			connectionType = wld.getConnectionType().toString();
		
		if(wld.getNeighboursAccNo()!=null)
			neighborsAcct = wld.getNeighboursAccNo();
						
		appAddress = wld.getServiceStreetAddress()+"; "+wld.getServiceSuburb()+"; "+wld.getServiceCity();
		appArea	= wld.getServiceCity();		
		appTariff = wld.getTariffCatCode();
		tarrifCode = wld.getTariffCode();
		
	
		
	}
	
	public void setControls(){
		try{
			// Adding values to the controls
			
						
			excavationDetailsList = new ArrayList<ListObject>();
			erectionDetailsList = new ArrayList<ListObject>();
			insStaysDetailsList = new ArrayList<ListObject>();
			wireDetailsList = new ArrayList<ListObject>();
			conductorDetailsList= new ArrayList<ListObject>(); 	
			reapirExistingDetailsList = new ArrayList<ListObject>(); 
			changeExistingDetailsList = new ArrayList<ListObject>();
			removalDetailsList  = new ArrayList<ListObject>();	
			removalPoleDetailsList = new ArrayList<ListObject>();
			cableTypeList  = new ArrayList<ListObject>();
			poleTypeList =new ArrayList<String>();
			conductorList =new ArrayList<String>();
			insStaysTypeList =new ArrayList<String>();
			polePointList =new ArrayList<String>();
			
			poleTypeList.add("INTERMEDIATE");
			poleTypeList.add("TAPPING");
			
			conductorList.add("FLY");
			conductorList.add("ABC");
			conductorList.add("SERVICE");		
			
			polePointList.add("New");
			polePointList.add("Existing");
			
			// Adding poles details	
			
			ListObject li1 = new ListObject("A0203","Poles RC  6m 50kg");
			excavationDetailsList.add(li1);
			
			ListObject li2 = new ListObject("A0211","Poles RC  8.3m 100kg");
			excavationDetailsList.add(li2);
			
			//Adding struts	
			
			ListObject l3 = new ListObject("A0203","Poles RC  6m 50kg");
			erectionDetailsList.add(l3);
			
			ListObject l4 = new ListObject("A0211","Poles RC  8.3m 100kg");
			erectionDetailsList.add(l4);
			
			//Adding Stays
								
			ListObject l5 = new ListObject("B0805","Wire stay G.S.  7/2.65 mm (Grade 700)");
			insStaysDetailsList.add(l5);
			
			ListObject l6 = new ListObject("B0810","Wire stay G.S.  7/3.25 mm (Grade 700)");
			insStaysDetailsList.add(l6);
			
			ListObject l7 = new ListObject("B0815","Wire stay G.S.  7/4.00 mm (Grade 700)");
			insStaysDetailsList.add(l7);
		
								
			//Adding Service wire
			
			ListObject l8 = new ListObject("SERVICE","SERVICE");
			wireDetailsList.add(l8);
				
			//Adding Bare Conductor
			ListObject l9 = new ListObject("BARE","ABC");
			conductorDetailsList.add(l9);
			
			ListObject l10 = new ListObject("BARE","FLY");
			conductorDetailsList.add(l10);

			//Adding loop cable types
			ListObject l11 = new ListObject("162C","16mm2/2C");
			cableTypeList.add(l11);
			
			ListObject l12 = new ListObject("16XPLE","16mm2XPLE");
			cableTypeList.add(l12);
			
			ListObject l13 = new ListObject("35XPLE","35mm2XPLE");
			cableTypeList.add(l13);
			
			ListObject l14 = new ListObject("70XPLE","70mm2XPLE");
			cableTypeList.add(l14);
			
			ListObject l15 = new ListObject("95XPLE","95mm2XPLE");
			cableTypeList.add(l15);
			
			polePointer = "New";
			
			//Adding stay control types
			insStaysTypeList.add("NORMAL");
			insStaysTypeList.add("FLYING");
							
		
		}catch(Exception ex){
			
		}
	}
	
	private String getCategoryDescription(String categoryCode){
		String description="";
		String passedcategoryCode = categoryCode.trim();
		if(passedcategoryCode.equals("A0203"))
			description ="Poles RC  6m 50kg";
		else if (passedcategoryCode.equals("A0211"))
			description ="Poles RC  8.3m 100kg";
		else if (passedcategoryCode.equals("B0805"))		
			description ="Wire stay G.S.  7/2.65 mm (Grade 700)";
		else if (passedcategoryCode.equals("B0810"))
			description ="Wire stay G.S.  7/3.25 mm (Grade 700)";
		else if (passedcategoryCode.equals("B0815"))
			description ="Wire stay G.S.  7/4.00 mm (Grade 700)";
			
		
		return description;
	}
	
	// Set existing details
	public void setExistingDetails(){
		try{
			SpserestEjb ejb=new SpserestEjb(getSessionKey("region"));
			SpserestPK spserestPK=new SpserestPK();
			spserestPK.setApplicationNo(appNo);
			spserestPK.setDeptId(appCSC);			
			Spserest spserest = ejb.findById(spserestPK);
			
			if(spserest!=null){
				
				if(spserest.getSubstation()!=null )
					substation = spserest.getSubstation();
				if(spserest.getSin()!=null )
					sin = spserest.getSin();
				if(spserest.getDistanceFromSs()!=null )
					distanceFrmSS = spserest.getDistanceFromSs().toString();	
				if(spserest.getPhase()!=null )
					phase = spserest.getPhase();		
				if(spserest.getTransformerCapacity()!=null)
					transformerCapacity = spserest.getTransformerCapacity();
				if(spserest.getPoleno()!=null)
					poleNo = spserest.getPoleno();
				if(spserest.getTransformerLoad()!=null)
					transformerLoad =spserest.getTransformerLoad();
				if(spserest.getTransformerPeakLoad()!=null)
					transformerPeakLoad = spserest.getTransformerPeakLoad();
				if(spserest.getFeederControlType()!=null)
					feederControltype = spserest.getFeederControlType();
				if(spserest.getWiringType()!=null)
					wiringType=spserest.getWiringType();
				if(spserest.getTotalLength()!=null)
					totalLength=spserest.getTotalLength();
				if(spserest.getNoOfSpans()!=null)
					noOfSpans=spserest.getNoOfSpans().toString();	
				if(spserest.getDistanceToSp()!=null)
					distancetoServicePlace=spserest.getDistanceToSp().toString();	
				
				if(spserest.getLoopCable()!=null)
					isLoopType=spserest.getLoopCable().toString();	
				else
					isLoopType = "N";				
				
				if(appSerType.equals("Three Phase") && wiringType.equals("OH") && spserest.getConversionLength()!=null && isConversion.equals("Y") &&  !spserest.getConversionLength().toString().equals("0")){
					conversionLength = spserest.getConversionLength().toString();
					hid_ConversionLength = spserest.getConversionLength().toString();
				}else{
					conversionLength ="";
					hid_ConversionLength = "";
				}
								
				if(spserest.getCableType()!=null)
					hid_CableType = spserest.getCableType().trim();
				else
					hid_CableType = "";
			}
			}catch(Exception ex){
			System.out.println("Errorrrr............................:"+ex);
		}
	}
	
	public String save(){
		
		try{		
		setLoggedData();	
		//Saving		
		SpestedyEjb spesTedyEjb=new SpestedyEjb(getSessionKey("region")); 		
		String appointmentStatus = spesTedyEjb.getAppointmentStatus(appNo, appCSC) ;		
						
		if(appointmentStatus==null){
			statusMsgErr = "Cant save...You havent put an appointment for the application";
			clearForm();
			SetPage();
			hid_isValueFilled = "";
			return "success";
		}else if(appointmentStatus=="E" || appointmentStatus.equals("E")){
			statusMsgErr = "Cant save...You have already created an estimate for the application";
			clearForm();
			SetPage();
			hid_isValueFilled = "";
			return "success";
		}else if(appointmentStatus=="F" || appointmentStatus.equals("F")){
			statusMsgErr = "Cant save...The appointment has failed";
			clearForm();
			SetPage();
			hid_isValueFilled = "";
			return "success";
		}
		
		SpserestPK spserestPK= new SpserestPK();
		Spserest spserest = new Spserest();		
		spserestPK.setApplicationNo(appNo);
		spserestPK.setDeptId(appCSC);
		spserest.setId(spserestPK);	
		
		if(wiringType==null || wiringType.trim()=="" || wiringType.length()==0)
			spserest.setWiringType("OH");
		else
			spserest.setWiringType(wiringType);
				
		if(substation!=null && substation.length()>0)
			spserest.setSubstation(substation);
		if(sin!=null && sin.length()>0)
			spserest.setSin(sin);
		if(distanceFrmSS!=null && distanceFrmSS.length()>0)
			spserest.setDistanceFromSs(new BigDecimal(distanceFrmSS));
		if(phase!=null && phase.length()>0)
			spserest.setPhase(phase);		
		if(transformerCapacity!=null && transformerCapacity.length()>0)
			spserest.setTransformerCapacity(transformerCapacity);
		if(poleNo!=null && poleNo.length()>0)
			spserest.setPoleno(poleNo);
		if(transformerLoad!=null && transformerLoad.length()>0)
			spserest.setTransformerLoad(transformerLoad);
		if(transformerPeakLoad!=null && transformerPeakLoad.length()>0)
			spserest.setTransformerPeakLoad(transformerPeakLoad);
		if(feederControltype!=null && feederControltype.length()>0)
			spserest.setFeederControlType(feederControltype);
		if(totalLength!=null && totalLength.length()>0)
			spserest.setTotalLength(totalLength);
		if(noOfSpans!=null && noOfSpans.length()>0)
			spserest.setNoOfSpans(new BigDecimal(noOfSpans));
		if(distancetoServicePlace!=null && distancetoServicePlace.length()>0)
			spserest.setDistanceToSp(new BigDecimal(distancetoServicePlace));	
		if(isLoopType!=null)
			spserest.setLoopCable(isLoopType);	
		
		if(isLoopType!=null && wiringType!=null && isLoopType.equals("N") && wiringType.equals("UG"))
			spserest.setCableType(cableType);
		else
			spserest.setCableType(null);
					
		if(appSerType.equals("Three Phase") && wiringType.equals("OH") && conversionLength!=null &&  conversionLength.length()>0){
			spserest.setConversionLength(new BigDecimal(conversionLength));
		}else{
			spserest.setConversionLength(new BigDecimal(0));
		}
				
		SpserestEjb spserestEjb=new SpserestEjb(getSessionKey("region"));		
		
		//Saving Pole details		
		List<Spsetpol> spsetpolList =new ArrayList<Spsetpol>();
		if(hid_selectedStringPoles!=null && hid_selectedStringPoles.length()>0){
			String itemArray []= hid_selectedStringPoles.split(":");
			
			if(itemArray.length>0){
				for(int i=0;i<itemArray.length;i++){
					String itemDetailArray[]= itemArray[i].split(",");
				
					if(itemDetailArray.length>6){
						SpsetpolPK spsetpolPK = new SpsetpolPK();
						spsetpolPK.setApplicationNo(appNo.trim());
						spsetpolPK.setDeptId(appCSC);							
						
						if(itemDetailArray[0]!=null)
							spsetpolPK.setMatCd(itemDetailArray[0]);
						if(itemDetailArray[2]!=null)
							spsetpolPK.setFromConductor(itemDetailArray[2]);
						if(itemDetailArray[3]!=null)
							spsetpolPK.setToConductor(itemDetailArray[3]);
						if(itemDetailArray[5]!=null)
							spsetpolPK.setPoleType(itemDetailArray[5]);
						if(itemDetailArray[6]!=null)
							spsetpolPK.setPointType(itemDetailArray[6]);
											
																
						Spsetpol spsetpol = new Spsetpol();
						spsetpol.setId(spsetpolPK);
						if(itemDetailArray[4]!=null)
							spsetpol.setMatQty(new BigDecimal(itemDetailArray[4]));	
						spsetpolList.add(spsetpol);
					}				
					
				}
						
			}
		}else{
			spsetpolList = null;
		}
		
		
		//Saving Struts details		
		List<Spsetstu> spsetstrutsList =new ArrayList<Spsetstu>();
		if(hid_selectedStringStruts!=null && hid_selectedStringStruts.length()>0){
			String itemArray []= hid_selectedStringStruts.split(":");			
			if(itemArray.length>0){
				for(int i=0;i<itemArray.length;i++){
					String itemDetailArray[]= itemArray[i].split(",");
				
					if(itemDetailArray.length>2){
						SpsetstuPK spsetstuPK = new SpsetstuPK();
						spsetstuPK.setApplicationNo(appNo);
						spsetstuPK.setDeptId(appCSC);							
						
						if(itemDetailArray[0]!=null)
							spsetstuPK.setMatCd(itemDetailArray[0]);
						
						Spsetstu spsetstu = new Spsetstu();	
						spsetstu.setId(spsetstuPK);
						
						if(itemDetailArray[2]!=null)
							spsetstu.setMatQty(new BigDecimal(itemDetailArray[2]));
																
						spsetstrutsList.add(spsetstu);
					}				
					
				}
								
			}
		}else{
			spsetstrutsList = null;
		}
		
		//Saving Stay details		
		List<Spsetsty> spsetstayList =new ArrayList<Spsetsty>();
		if(hid_selectedStringStays!=null && hid_selectedStringStays.length()>0){
			String itemArray []= hid_selectedStringStays.split(":");
			if(itemArray.length>0){
				for(int i=0;i<itemArray.length;i++){
					String itemDetailArray[]= itemArray[i].split(",");
				
					if(itemDetailArray.length>4){
						SpsetstyPK spsetstyPK = new SpsetstyPK();
						spsetstyPK.setApplicationNo(appNo);
						spsetstyPK.setDeptId(appCSC);	
						
						if(itemDetailArray[4]!=null)
							spsetstyPK.setStayType(itemDetailArray[4].trim());
								
						if(itemDetailArray[0]!=null)
							spsetstyPK.setMatCd(itemDetailArray[0]);
						
						Spsetsty spsetsty = new Spsetsty();	
						spsetsty.setId(spsetstyPK);
						
						if(itemDetailArray[2]!=null)
							spsetsty.setMatQty(new BigDecimal(itemDetailArray[2]));
																
						spsetstayList.add(spsetsty);
					}	
				}	
			}
		}else{
			spsetstayList = null;
		}
		
		Double totalnewWireLength = 0.0;	
		//Saving service length	
		
		
			List<Spsetwir> spsetServiceLenList =new ArrayList<Spsetwir>();
			try{
			if(hid_selectedStringSerLengths!=null && hid_selectedStringSerLengths.length()>0){
				String itemArray []= hid_selectedStringSerLengths.split(":");	
				if(itemArray.length>0){
					for(int i=0;i<itemArray.length;i++){
						String itemDetailArray[]= itemArray[i].split(",");
						if(itemDetailArray.length>2){
							SpsetwirPK spsetwirPK = new SpsetwirPK();
							spsetwirPK.setApplicationNo(appNo);
							spsetwirPK.setDeptId(appCSC);							
							
							if(itemDetailArray[0]!=null)
								spsetwirPK.setMatCd(itemDetailArray[0]);
							
							Spsetwir spsetwir = new Spsetwir();	
							spsetwir.setId(spsetwirPK);
							
							if(itemDetailArray[2]!=null){
								spsetwir.setWireLength(new BigDecimal(itemDetailArray[2]));
								totalnewWireLength = totalnewWireLength + Double.valueOf(itemDetailArray[2]);
							}
								
							spsetwir.setWireType(itemDetailArray[1]);
							
							spsetServiceLenList.add(spsetwir);
						}	
					}	
				}
			}else{
				spsetServiceLenList = null;
			}
		}catch(Exception ex){
			statusMsgErr = "Error - Wire Lengths are not correct";
			clearForm();
			SetPage();
			return "success";
		}
						
		Double totalSavedWireLength = 0.0;			
		try{
			if(hid_SavedWireLengths!=null && hid_SavedWireLengths.length()>0){
				String serviceitemArray []= hid_SavedWireLengths.split(",");	
				if(serviceitemArray.length>0){
					for(int i=0;i<serviceitemArray.length;i++){
						
						Double tempLen = Double.valueOf(serviceitemArray[i]);
						totalSavedWireLength = totalSavedWireLength + tempLen;
					}
				}
		}	
		}catch(Exception ex){
			statusMsgErr = "Error - Wire Lengths are not correct";
			clearForm();
			SetPage();
			return "success";
		}
			
		Double finalTotLength = totalSavedWireLength+ totalnewWireLength;
		
		if(!finalTotLength.equals(Double.valueOf(totalLength))){
			statusMsgErr = "Error - Wire Lengths are not correct";
			clearForm();
			SetPage();
			return "success";
		}
		
		//Removing existing records			
		List<SpsetpolPK> spsetpolRemoveList =new ArrayList<SpsetpolPK>();
		List<SpsetstuPK> spsetstrutsRemoveList =new ArrayList<SpsetstuPK>();
		List<SpsetstyPK> spsetstayRemoveList =new ArrayList<SpsetstyPK>();
		List<SpsetwirPK> spsetServiceLenRemoveList =new ArrayList<SpsetwirPK>();
		
		if(hid_RemovePoles!=null && hid_RemovePoles!="" && hid_RemovePoles.trim().length()>0){
			String removePolearray [] = hid_RemovePoles.split(":");
			
			for(int i=0;i<removePolearray.length;i++){
				
				String itemarray [] = removePolearray[i].split(",");
				
				if(itemarray.length>3){
					SpsetpolPK spsetpolPK = new SpsetpolPK();
					spsetpolPK.setApplicationNo(appNo);
					spsetpolPK.setDeptId(appCSC);
					spsetpolPK.setPointType("New");
					spsetpolPK.setMatCd(itemarray[0].trim());
					spsetpolPK.setPoleType(itemarray[1].trim());
					spsetpolPK.setFromConductor(itemarray[2].trim());
					spsetpolPK.setToConductor(itemarray[3].trim());
					
					spsetpolRemoveList.add(spsetpolPK);
				}
				
			}
		}else{
			spsetpolRemoveList=null;
		}
		
		if(hid_RemoveStruts!=null && hid_RemoveStruts!="" && hid_RemoveStruts.trim().length()>0){
			String removeStrutsarray [] = hid_RemoveStruts.split(":");
			for(int i=0;i<removeStrutsarray.length;i++){
				SpsetstuPK spsetstuPK = new SpsetstuPK();
				spsetstuPK.setApplicationNo(appNo);
				spsetstuPK.setDeptId(appCSC);
				spsetstuPK.setMatCd(removeStrutsarray[i].trim());							
				spsetstrutsRemoveList.add(spsetstuPK);

			}
		}else{
			spsetstrutsRemoveList=null;
		}
		
		if(hid_RemoveStays!=null && hid_RemoveStays!="" && hid_RemoveStays.trim().length()>0){
			String removeStaysarray [] = hid_RemoveStays.split(":");
			 for(int i=0;i<removeStaysarray.length;i++){
				SpsetstyPK spsetstyPK = new SpsetstyPK();
				spsetstyPK.setApplicationNo(appNo);
				spsetstyPK.setDeptId(appCSC);
				if(removeStaysarray[i]!=null){
					String stayDetail [] = removeStaysarray[i].trim().split(",");
					if(stayDetail.length>1){
						spsetstyPK.setMatCd(stayDetail[0].trim());
						spsetstyPK.setStayType(stayDetail[1].trim());
					}					
				}											
				spsetstayRemoveList.add(spsetstyPK);	
				
			}
		}else{
			spsetstayRemoveList=null;
		}
		
		
		if(hid_RemoveSerLengths!=null && hid_RemoveSerLengths!="" && hid_RemoveSerLengths.trim().length()>0){
			String removeServiceLengthsarray [] = hid_RemoveSerLengths.split(":");
			
			for(int i=0;i<removeServiceLengthsarray.length;i++){
				
				SpsetwirPK spsetwirPK = new SpsetwirPK();
				spsetwirPK.setApplicationNo(appNo);
				spsetwirPK.setDeptId(appCSC);	
				spsetwirPK.setMatCd(removeServiceLengthsarray[i].trim());
				spsetServiceLenRemoveList.add(spsetwirPK);

			}
		}else{
			spsetServiceLenRemoveList=null;
		}
		
		spserestEjb.saveServiceEstimate(spserest, spsetpolList, spsetstrutsList, spsetstayList,spsetServiceLenList,spsetpolRemoveList,spsetstrutsRemoveList,spsetstayRemoveList,spsetServiceLenRemoveList);

		statusMsg = "Records saved successfully";
		
		
		
		}catch(Exception ex){
			statusMsgErr = "Error - Service Estimate was not created";
			ex.printStackTrace();
			//System.out.println("The error is.................:"+ex);
		}
		
		clearForm();
		SetPage();
		return "success";
	}
	
	public void SetPage(){
		
		try{	
			setLoggedData();
			hid_isValueFilled = "";
			Format format=new Format();
			String appNoPreFix = getSessionKey("costCenterNo")+"/"+getsmcType()+"/"+format.getYear();
			appNo = appNoPreFix;			
			setControls();					
			
			
		}catch(Exception ex){
			
		}
	}
	
	
	public String print(){
		try{
			setLoggedData();
			//Printing		
			ServiceEstimateForm sef = new ServiceEstimateForm();		
			sef.setArea(appArea);
			sef.setCSC(appCSC);				
			sef.setTelephoneNumber(appTphone);
			sef.setServiceType(appSerType);
			sef.setTariff(appTariff);
			sef.setAppNumber(appNo);
			sef.setNameWithInitials(appName);
			
			sef.setSubstation(substation);
			sef.setDistanceFromSS(distanceFrmSS);
			sef.setTransformerCapacity(transformerCapacity);
			sef.setTransformerPowerLoad_KVA(transformerLoad);
			sef.setFeederControlType(feederControltype);
			sef.setSIN(sin);
			sef.setPhase(phase);
			sef.setPoleNo(poleNo);
			
						
			String addArray[]=appAddress.split(";");
			if(addArray.length>2){
				sef.setStreetAddress(addArray[0]);
				sef.setSuburb(addArray[1]);
				sef.setCity(addArray[2]);
			}
		
			PrintServiceEstimateForm psef = new PrintServiceEstimateForm(sef);
			psef.Print(true);
		}catch(Exception ex){
			
		}
		
		clearForm();
		SetPage();
		return "success";
	}
	
	public void clearForm(){
		appNo = "";
		appArea = "";
		appCSC = "";
		appTphone = "";
		appSerType = "";
		appSerType = "";
		appName = "";
		appAddress = "";
		appTariff ="";
		wiringType= "";
		noOfSpans="";
			
		substation="";
		sin="";
		distanceFrmSS="";
		phase="";
		transformerCapacity="";
		poleNo="";
		transformerLoad="";
		transformerPeakLoad="";
		feederControltype="";
		
		hid_selectedStringPoles="";
		hid_selectedStringStruts="";
		hid_selectedStringStays="";
		hid_selectedStringSerLengths="";
		hid_RemoveStruts="";
		hid_RemoveStays="";
		hid_RemoveSerLengths="";
		hid_RemovePoles ="";
		hid_SavedWireLengths = "";
		
	}
}
