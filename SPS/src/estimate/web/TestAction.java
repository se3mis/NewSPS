package estimate.web;

import java.util.ArrayList;
//import java.util.List;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Collection;
import java.util.StringTokenizer;
import java.math.BigDecimal;
import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import estimate.model.LabourGrid;
import estimate.model.MaterialGrid;
import estimate.model.Spnorms;
import estimate.model.Pcestdtt;
import estimate.model.PcestdttPK;
import estimate.model.Pcesthtt;
import estimate.model.Spratesm;
import estimate.model.SpratesmPK;
import estimate.model.Spsetpol;
import estimate.model.Spsetstu;
import estimate.model.Spsetsty;
import estimate.service.EstimateEjb;
import estimate.service.PcestdttEjb;
import estimate.service.PcesthttEjb;
import estimate.service.SpsetpolEjb;
import estimate.service.SpsetstuEjb;
import estimate.service.SpsetstyEjb;


/**
 * @author Dell
 *
 */
@SuppressWarnings("serial")
public class TestAction extends ActionSupport  implements SessionAware, ParameterAware,ServletResponseAware,ServletRequestAware{

	private ArrayList<ArrayList<String>> matList;
	
	private Map<String,List<String>> normsMap = new HashMap<String,List<String>>();
	
	private List<String> applicationNoRefs;
	
	private String yourSearchEngine;
	private String yourMonth;
	//private List matList;
	private Collection selectMatList;
	private Collection afterRemoveMatList;
	
	NumberFormat nf = NumberFormat.getInstance();
	
	private String lineLength;
	private String phaseDb;
	private String connectionTypeDb;
	private String wiringType;
	private String serviceLength;
	private String conductorLength;
	private String conductorType;
	private String stayNo;
	private String strutsNo;
	private String spans;
	private String distanceToServicePlace;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private Map<String, Object> sessionMap;
	
	
	private String costCenterNo;
	private String applicationNo;
	
	private String isLoopService;
	
	private String[] chkMatCode;
	private String[] chkNplMatCode;
	private String totalDetailCost;
	private ArrayList otherCostList;
	private ArrayList nplMatList;
	private ArrayList poleMatList;
	private ArrayList poleList;
	
	private String[] chkSelMatCode;
	private String[] txtSelQty;
	private String[] txtSelMatCost;
	private LinkedHashMap selectMatCodeMap;
	private String[] txtCusQty;
	private String[] txtSelCusCost;
	private String totalLabCost;
	private String totalLabHrs;
	
	private String[] chkResType;
	private String[] txtOtherCost;
	
	private String isUndoReject;
	
	private String isMatClicked;
	private String isOthClicked;
	private String isNplClicked;
	private String isAddMatClicked;
	private String isPoleMatClicked;
	private String isPoleMatParamClicked;
	//private String isLineLenEntered;
	
	//pole params variables
	private String poleType;
	private String fromConductor;
	private String toConductor;
	private String isWithPole;
	private String poleCode;
	private String noOfPole;
	private String noOfCusPole;
		
	private String itemCodeList;
	private String itemDescList;
	private String uomList;
	private String priceList;
	private String qtyList;
	private String costList;
	private String nonMatCodeList;
	private String nonMatCostList;
	
	private String labourRate;
	private String transportRate;
	private String overheadRate;
	private String totalMatCost;
	
	
	double labCost = 0;
	double labHrs = 0;
	
	public String execute() throws Exception 
	{
		return SUCCESS;
	}
	
	
	public String getYourSearchEngine() {
		return yourSearchEngine;
	}


	public void setYourSearchEngine(String yourSearchEngine) {
		this.yourSearchEngine = yourSearchEngine;
	}


	public String getYourMonth() {
		return yourMonth;
	}


	public void setYourMonth(String yourMonth) {
		this.yourMonth = yourMonth;
	}


	public String displayNorms()
	{
		try
		{
			//if(isMatClicked!=null && isMatClicked.equals("true"))
			//{

				ArrayList<ArrayList<String>> resultList = null;
		
					EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
					//matList = estimateEjb.getAvailableNorms();
					List<Spnorms> list = estimateEjb.getAvailableNorms();
					 resultList =  new ArrayList<ArrayList<String>>();
					for(int i=0; i<=list.size()-1; i++){

						 ArrayList<String> tmpList = new ArrayList<String>();
                         tmpList.add(list.get(i).getId().getLineSectionTypeId());
                         tmpList.add(list.get(i).getUom());
                         tmpList.add(String.valueOf(list.get(i).getStandardCost()));
                         tmpList.add(list.get(i).getDescription());
                         resultList.add(tmpList);
                         normsMap.put(list.get(i).getId().getLineSectionTypeId(), tmpList);
                         //ServletActionContext.getRequest().getSession().put("normsMap", normsMap);
                         //ServletActionContext.getRequest().setAttribute("normsMap", normsMap);
                         //ServletActionContext.getRequest().setAttribute("normsMap", normsMap);
                         ServletActionContext.getRequest().getSession().setAttribute("normsMap", normsMap);
                         
					}
					matList = resultList;

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "display";
	}
	
	
	/*public String loadApplicationRefNumbers()
	{
		try
		{
			//if(isMatClicked!=null && isMatClicked.equals("true"))
			//{

					ArrayList<ArrayList<String>> resultList = null;
		
					EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
					//matList = estimateEjb.getAvailableNorms();
					List<Spnorms> list = estimateEjb.getAvailableNorms();
					 resultList =  new ArrayList<ArrayList<String>>();
					for(int i=0; i<=list.size()-1; i++){

						 ArrayList<String> tmpList = new ArrayList<String>();
                         tmpList.add(list.get(i).getId().getLineSectionTypeId());
                         tmpList.add(list.get(i).getUom());
                         
                         resultList.add(tmpList);
                         //normsMap.put(list.get(i).getId().getLineSectionTypeId(), tmpList);
                         //ServletActionContext.getRequest().getSession().put("normsMap", normsMap);
                         //ServletActionContext.getRequest().setAttribute("normsMap", normsMap);
                         //ServletActionContext.getRequest().setAttribute("normsMap", normsMap);
                         //.getRequest().getSession().setAttribute("normsMap", normsMap);
                         
					}
					applicationNoRefs = resultList;

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "success";
	}*/
	

	
public List<String> getApplicationNoRefs() {
	return applicationNoRefs;
}


public void setApplicationNoRefs(List<String> applicationNoRefs) {
	this.applicationNoRefs = applicationNoRefs;
}





	/************ Getters and Setters *********************************/
	
	public ArrayList<ArrayList<String>> getMatList() {
		return matList;
	}

	public void setMatList(ArrayList<ArrayList<String>> matList) {
		this.matList = matList;
	}

	public Collection getSelectMatList() {
		return selectMatList;
	}

	public void setSelectMatList(Collection selectMatList) {
		this.selectMatList = selectMatList;
	}

	public String getLineLength() {
		return lineLength;
	}

	public void setLineLength(String lineLength) {
		this.lineLength = lineLength;
	}
	
	public Collection getAfterRemoveMatList() {
		return afterRemoveMatList;
	}

	public void setAfterRemoveMatList(Collection afterRemoveMatList) {
		this.afterRemoveMatList = afterRemoveMatList;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
	
	public HttpServletRequest getServletRequest(){
		return request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	public HttpServletResponse getServletResponse(){
		return response;
	}
	
	@Override
	public void setParameters(Map<String, String[]> arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		// TODO Auto-generated method stub
		this.sessionMap = sessionMap;
	}

	public String[] getChkMatCode() {
		return chkMatCode;
	}

	public void setChkMatCode(String[] chkMatCode) {
		this.chkMatCode = chkMatCode;
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

	public String[] getChkSelMatCode() {
		return chkSelMatCode;
	}

	public void setChkSelMatCode(String[] chkSelMatCode) {
		this.chkSelMatCode = chkSelMatCode;
	}

	public String getCostCenterNo() {
		return costCenterNo;
	}

	public void setCostCenterNo(String costCenterNo) {
		this.costCenterNo = costCenterNo;
	}

	public String getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public ArrayList getOtherCostList() {
		return otherCostList;
	}

	public void setOtherCostList(ArrayList otherCostList) {
		this.otherCostList = otherCostList;
	}

	public String[] getChkResType() {
		return chkResType;
	}

	public void setChkResType(String[] chkResType) {
		this.chkResType = chkResType;
	}

	public String[] getTxtOtherCost() {
		return txtOtherCost;
	}

	public void setTxtOtherCost(String[] txtOtherCost) {
		this.txtOtherCost = txtOtherCost;
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

	public String[] getTxtCusQty() {
		return txtCusQty;
	}

	public void setTxtCusQty(String[] txtCusQty) {
		this.txtCusQty = txtCusQty;
	}

	public String getWiringType() {
		return wiringType;
	}

	public void setWiringType(String wiringType) {
		this.wiringType = wiringType;
	}

	public String getIsUndoReject() {
		return isUndoReject;
	}

	public void setIsUndoReject(String isUndoReject) {
		this.isUndoReject = isUndoReject;
	}

	public ArrayList getNplMatList() {
		return nplMatList;
	}

	public void setNplMatList(ArrayList nplMatList) {
		this.nplMatList = nplMatList;
	}

	public String[] getChkNplMatCode() {
		return chkNplMatCode;
	}

	public void setChkNplMatCode(String[] chkNplMatCode) {
		this.chkNplMatCode = chkNplMatCode;
	}

	public String getIsNplClicked() {
		return isNplClicked;
	}

	public void setIsNplClicked(String isNplClicked) {
		this.isNplClicked = isNplClicked;
	}

	public String getIsMatClicked() {
		return isMatClicked;
	}

	public void setIsMatClicked(String isMatClicked) {
		this.isMatClicked = isMatClicked;
	}

	public String getIsOthClicked() {
		return isOthClicked;
	}

	public void setIsOthClicked(String isOthClicked) {
		this.isOthClicked = isOthClicked;
	}

	public String getIsAddMatClicked() {
		return isAddMatClicked;
	}

	public void setIsAddMatClicked(String isAddMatClicked) {
		this.isAddMatClicked = isAddMatClicked;
	}

	public ArrayList getPoleMatList() {
		return poleMatList;
	}

	public void setPoleMatList(ArrayList poleMatList) {
		this.poleMatList = poleMatList;
	}

	public ArrayList getPoleList() {
		return poleList;
	}

	public void setPoleList(ArrayList poleList) {
		this.poleList = poleList;
	}

	public String getIsPoleMatClicked() {
		return isPoleMatClicked;
	}

	public void setIsPoleMatClicked(String isPoleMatClicked) {
		this.isPoleMatClicked = isPoleMatClicked;
	}

	public String getIsPoleMatParamClicked() {
		return isPoleMatParamClicked;
	}

	public void setIsPoleMatParamClicked(String isPoleMatParamClicked) {
		this.isPoleMatParamClicked = isPoleMatParamClicked;
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

	public String getIsWithPole() {
		return isWithPole;
	}

	public void setIsWithPole(String isWithPole) {
		this.isWithPole = isWithPole;
	}

	public String getPoleCode() {
		return poleCode;
	}

	public void setPoleCode(String poleCode) {
		this.poleCode = poleCode;
	}

	public String getNoOfPole() {
		return noOfPole;
	}

	public void setNoOfPole(String noOfPole) {
		this.noOfPole = noOfPole;
	}



	public String getServiceLength() {
		return serviceLength;
	}



	public void setServiceLength(String serviceLength) {
		this.serviceLength = serviceLength;
	}



	public String getConductorLength() {
		return conductorLength;
	}



	public void setConductorLength(String conductorLength) {
		this.conductorLength = conductorLength;
	}



	public String getConductorType() {
		return conductorType;
	}



	public void setConductorType(String conductorType) {
		this.conductorType = conductorType;
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



	public String getTotalLabCost() {
		return totalLabCost;
	}



	public void setTotalLabCost(String totalLabCost) {
		this.totalLabCost = totalLabCost;
	}



	public String getItemCodeList() {
		return itemCodeList;
	}



	public void setItemCodeList(String itemCodeList) {
		this.itemCodeList = itemCodeList;
	}



	public String getUomList() {
		return uomList;
	}



	public void setUomList(String uomList) {
		this.uomList = uomList;
	}



	public String getPriceList() {
		return priceList;
	}



	public void setPriceList(String priceList) {
		this.priceList = priceList;
	}



	public String getQtyList() {
		return qtyList;
	}



	public void setQtyList(String qtyList) {
		this.qtyList = qtyList;
	}



	public String getCostList() {
		return costList;
	}



	public void setCostList(String costList) {
		this.costList = costList;
	}



	public String getItemDescList() {
		return itemDescList;
	}



	public void setItemDescList(String itemDescList) {
		this.itemDescList = itemDescList;
	}
	public String getSessionKey(String key) 
	{
       return getSessionMap().get(key).toString();
	}
	
	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}



	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}



	public String getNoOfCusPole() {
		return noOfCusPole;
	}



	public void setNoOfCusPole(String noOfCusPole) {
		this.noOfCusPole = noOfCusPole;
	}



	public String[] getTxtSelCusCost() {
		return txtSelCusCost;
	}



	public void setTxtSelCusCost(String[] txtSelCusCost) {
		this.txtSelCusCost = txtSelCusCost;
	}



	public String getSpans() {
		return spans;
	}



	public void setSpans(String spans) {
		this.spans = spans;
	}



	public String getDistanceToServicePlace() {
		return distanceToServicePlace;
	}



	public void setDistanceToServicePlace(String distanceToServicePlace) {
		this.distanceToServicePlace = distanceToServicePlace;
	}



	public String getIsLoopService() {
		return isLoopService;
	}



	public void setIsLoopService(String isLoopService) {
		this.isLoopService = isLoopService;
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



	public String getTotalLabHrs() {
		return totalLabHrs;
	}



	public void setTotalLabHrs(String totalLabHrs) {
		this.totalLabHrs = totalLabHrs;
	}



	public String getTotalDetailCost() {
		return totalDetailCost;
	}



	public void setTotalDetailCost(String totalDetailCost) {
		this.totalDetailCost = totalDetailCost;
	}



	public String getTotalMatCost() {
		return totalMatCost;
	}



	public void setTotalMatCost(String totalMatCost) {
		this.totalMatCost = totalMatCost;
	}



	public String getNonMatCodeList() {
		return nonMatCodeList;
	}



	public void setNonMatCodeList(String nonMatCodeList) {
		this.nonMatCodeList = nonMatCodeList;
	}



	public String getNonMatCostList() {
		return nonMatCostList;
	}



	public void setNonMatCostList(String nonMatCostList) {
		this.nonMatCostList = nonMatCostList;
	}




	

	
}
