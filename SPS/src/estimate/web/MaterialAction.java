package estimate.web;

import java.util.ArrayList;
//import java.util.List;
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
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import estimate.model.LabourGrid;
import estimate.model.MaterialGrid;
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
public class MaterialAction extends ActionSupport  implements SessionAware, ParameterAware,ServletResponseAware,ServletRequestAware{

	private ArrayList<ArrayList<String>> matList;
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
	
	

	private void addDefMaterials()
	{
		try
		{
			System.out.println("##$$ addDefMaterials 1");
			costCenterNo = (String)sessionMap.get("costCenterNo");
			EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
			if(wiringType!=null && wiringType.length()>0)
			{
				if(isLoopService!=null && isLoopService.equals("Y"))
					selectMatCodeMap = estimateEjb.getDefaultLoopMaterialGrid(costCenterNo,new Long(phaseDb).longValue(),new Long(connectionTypeDb).longValue(),wiringType);
				else
					selectMatCodeMap = estimateEjb.getDefaultMaterialGrid(costCenterNo,new Long(phaseDb).longValue(),new Long(connectionTypeDb).longValue(),wiringType);
				sessionMap.put("SelectMaterialList",selectMatCodeMap);
			}
			System.out.println("##$$ addDefMaterials 2"+selectMatCodeMap);
			if(selectMatCodeMap==null)
				selectMatCodeMap = new LinkedHashMap();
			displayServiceWireMaterials();
			displayConductorMaterials();
			displayStayMaterials();
			displayStrutMaterials();
			displayPoleMaterials();
			
			
			calculatePoleLabour();
			calculateServiceWireLabour();
			calculateConductorLabour();
			calculateStrutLabour();
			calculateStayLabour();
			
			MaterialGrid grid = new MaterialGrid();
			grid.setEstimateCost(new BigDecimal(labCost));
			grid.setEstimateQty(new BigDecimal(labHrs));
			grid.setMatNm("LABOUR COST");
			grid.setResCat(new BigDecimal(3));
			grid.setResCd("LABOUR");
			grid.setResType("LABOUR-COST");
			grid.setUnitPrice(new BigDecimal(labourRate));
			grid.setUom("HRS.");
			selectMatCodeMap.put("LABOUR",grid);
			
			double ohCost = labHrs*new BigDecimal(overheadRate).doubleValue();
			MaterialGrid grid1 = new MaterialGrid();
			grid1.setEstimateCost(new BigDecimal(ohCost));
			grid1.setEstimateQty(new BigDecimal(labHrs));
			grid1.setMatNm("OVERHEAD COST");
			grid1.setResCat(new BigDecimal(2));
			grid1.setResCd("OVERHEAD");
			grid1.setResType("OVERHEAD-COST");
			grid1.setUnitPrice(new BigDecimal(overheadRate));
			grid1.setUom("HRS.");
			selectMatCodeMap.put("OVERHEAD",grid1);
			
			/*
			double tCost = new BigDecimal(transportRate).doubleValue()*new BigDecimal(distanceToServicePlace).doubleValue();
			MaterialGrid grid2 = new MaterialGrid();
			grid2.setEstimateCost(new BigDecimal(tCost));
			grid2.setEstimateQty(new BigDecimal(distanceToServicePlace));
			grid2.setMatNm("TRANSPORT COST");
			grid2.setResCat(new BigDecimal(2));
			grid2.setResCd("TRANSPORT");
			grid2.setResType("TRANSPORT-COST");
			grid2.setUnitPrice(new BigDecimal(transportRate));
			grid2.setUom("KM.");
			selectMatCodeMap.put("TRANSPORT",grid2);
			*/
			SpratesmPK spratesmpK = new SpratesmPK();
			spratesmpK.setApplicationType("NC");
			spratesmpK.setDeptId(costCenterNo);
			Spratesm spratesm = estimateEjb.getSpratesm(spratesmpK);
			double transportAmt = 0;
			if(spratesm!=null)
			{
				double distance = Double.parseDouble(distanceToServicePlace);
				double limit = spratesm.getFixedTransportDistance().doubleValue();
				if(distance<limit)
					transportAmt = spratesm.getFixedTransportAmt().doubleValue();
				else
					transportAmt = spratesm.getTransportRate().doubleValue()*distance;
			}
			
			MaterialGrid grid2 = new MaterialGrid();
			grid2.setEstimateCost(new BigDecimal(0));
			grid2.setEstimateQty(new BigDecimal(0));
			grid2.setMatNm("TRANSPORT COST");
			grid2.setResCat(new BigDecimal(2));
			grid2.setResCd("TRANSPORT");
			grid2.setResType("TRANSPORT-COST");
			grid2.setUnitPrice(new BigDecimal("1.00"));
			grid2.setUom("NO.");
			grid2.setEstimateQty(new BigDecimal(transportAmt));
			grid2.setEstimateCost(new BigDecimal(transportAmt));
			selectMatCodeMap.put("TRANSPORT",grid2);
			
			sessionMap.put("SelectMaterialList", selectMatCodeMap);
			
			System.out.println("##$$ addDefMaterials 3 "+selectMatCodeMap);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public String displayMaterials()
	{
		try
		{
			if(isMatClicked!=null && isMatClicked.equals("true"))
			{
				System.out.println("show mats");
				costCenterNo = (String)sessionMap.get("costCenterNo");
				//if(lineLength!=null && lineLength.trim().length()>0)
				//{
					LinkedHashMap selMatTmp = (LinkedHashMap)sessionMap.get("SelectMaterialList");
					Set matKeySet = null;
					ArrayList selList = new ArrayList();
					if(selMatTmp!=null)
					{
						matKeySet = selMatTmp.keySet();
						selList = new ArrayList(matKeySet);
					}
					
					EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
					matList = estimateEjb.getAvailableMaterials(costCenterNo,selList);
				//}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "display";
	}
	
	public String displayMaterials1()
	{
		try
		{
			if(isMatClicked!=null && isMatClicked.equals("true"))
			{
				System.out.println("show mats");
				costCenterNo = (String)sessionMap.get("costCenterNo");
				//if(lineLength!=null && lineLength.trim().length()>0)
				//{
					LinkedHashMap selMatTmp = (LinkedHashMap)sessionMap.get("SelectMaterialList");
					Set matKeySet = null;
					ArrayList selList = new ArrayList();
					if(selMatTmp!=null)
					{
						matKeySet = selMatTmp.keySet();
						selList = new ArrayList(matKeySet);
					}
					
					EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
					matList = estimateEjb.getAvailableMaterials(costCenterNo,selList);
				//}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "display";
	}
	
	
		
	public String addMaterials()
	{
		try
		{
			
			//select and add materials
			if(isAddMatClicked!=null && isAddMatClicked.equals("true")){
				selectMatCodeMap = (LinkedHashMap)sessionMap.get("SelectMaterialList");
				if(selectMatCodeMap!=null && selectMatCodeMap.size()>0){
					Iterator it = selectMatCodeMap.keySet().iterator();
					int i=0;
					double totalCost = 0;
					while(it.hasNext()){
						String matCode = (String)it.next();
						MaterialGrid tmpLst = (MaterialGrid)selectMatCodeMap.get(matCode);
						
						String qty = "";
						if(tmpLst.getEstimateQty()!=null) 
							qty = tmpLst.getEstimateQty().toString();
						String cost = "";
						if(tmpLst.getEstimateCost()!=null) 
							cost = tmpLst.getEstimateCost().toString();
						String cusQty = "";
						if(tmpLst.getCustomerQty()!=null) 
							cusQty = tmpLst.getCustomerQty().toString();
						String cusCost = "";
						if(tmpLst.getCustomerCost()!=null) 
							cusCost = tmpLst.getCustomerCost().toString();
						if(txtSelQty!=null && txtSelQty.length>0)
							qty = txtSelQty[i];
						if(txtSelMatCost!=null && txtSelMatCost.length>0)
							cost = txtSelMatCost[i];
						if(txtCusQty!=null && txtCusQty.length>0)
							cusQty = txtCusQty[i];
						if(txtSelCusCost!=null && txtSelCusCost.length>0)
							cusCost = txtSelCusCost[i];
						if(qty!=null && qty.length()>0)
							tmpLst.setEstimateQty(new BigDecimal(qty));
						if(cusQty!=null && cusQty.length()>0)
							tmpLst.setCustomerQty(new BigDecimal(cusQty));
						if(cost!=null && cost.length()>0){
							tmpLst.setEstimateCost(new BigDecimal(nf.parse(cost).doubleValue()));
							totalCost = totalCost+nf.parse(cost).doubleValue();
						}
						if(cusCost!=null && cusCost.length()>0){
							tmpLst.setCustomerCost(new BigDecimal(nf.parse(cusCost).doubleValue()));
							//totalCost = totalCost+nf.parse(cost).doubleValue();
						}
						totalDetailCost = nf.format(totalCost);
						
						selectMatCodeMap.put(matCode,tmpLst);
						//selectMatList.add(tmpLst);
						i++;
					}//while
					//selectMatList = selMatTmp;
				}
			
				
				if(chkMatCode!=null){//adding materials from new list
					sessionMap.put("IsMaterialsAdd", "Y");
					if(selectMatCodeMap==null)
						selectMatCodeMap = new LinkedHashMap();
					for(int i=0;i<chkMatCode.length;i++){
						StringTokenizer st = new StringTokenizer(chkMatCode[i],"###");
						MaterialGrid tmpAL = new MaterialGrid();
						String index = st.nextToken();
						String matCode = st.nextToken();
						String uom  = st.nextToken();
						String up  = st.nextToken();
						String matName  = st.nextToken();
						
						tmpAL.setResCd(matCode);
						tmpAL.setMatNm(matName);
						tmpAL.setUom(uom);
						tmpAL.setUnitPrice(new BigDecimal(up));
						tmpAL.setResCat(new BigDecimal(1));
						tmpAL.setResType("MAT-COST");
													
						//selectMatList.add(tmpAL);
						selectMatCodeMap.put(matCode,tmpAL);
					}
				}

			}else {//display default or selected materials
				String isNewEst = (String)sessionMap.get("IsNewEstimate");
				String isMatAdd = (String)sessionMap.get("IsMaterialsAdd");
				String isDefMatLoad = (String)sessionMap.get("IsDefMatLoad");
				if(isDefMatLoad==null && (isNewEst==null || isNewEst.equals("Y"))){
					isDefMatLoad = "N";
					addDefMaterials();
					sessionMap.put("IsDefMatLoad",isDefMatLoad);
					
				}else{
					selectMatCodeMap = (LinkedHashMap)sessionMap.get("SelectMaterialList");
					
					if(selectMatCodeMap!=null && selectMatCodeMap.size()>0){
						Iterator it = selectMatCodeMap.keySet().iterator();
						int i=0;
						double totalCost = 0;
						double matCostTotal = 0;
						
						itemCodeList = "";
						itemDescList = "";
						uomList = "";
						priceList = "";
						costList = "";
						qtyList = "";
						nonMatCodeList = "";
						nonMatCostList = "";
						
						nf.setGroupingUsed(true);
						nf.setMaximumFractionDigits(2);
						nf.setMinimumFractionDigits(2);
						
						while(it.hasNext()){
							String matCode = (String)it.next();
							MaterialGrid tmpLst = (MaterialGrid)selectMatCodeMap.get(matCode);
							
							String qty = "0";
							if(tmpLst.getEstimateQty()!=null) 
								qty = tmpLst.getEstimateQty().toString();
							if(txtSelQty!=null && txtSelQty.length>0)
								qty = txtSelQty[i];
							if(qty!=null && qty.length()>0)
								tmpLst.setEstimateQty(new BigDecimal(qty));
							
							String cost = "0";
							if(tmpLst.getEstimateCost()!=null) 
								cost = tmpLst.getEstimateCost().toString();
							if(txtSelMatCost!=null && txtSelMatCost.length>0)
								cost = txtSelMatCost[i];
							if(cost!=null && cost.length()>0){
								tmpLst.setEstimateCost(new BigDecimal(nf.parse(cost).doubleValue()));
								totalCost = totalCost+nf.parse(cost).doubleValue();
							}
							
							String cusQty = "0";
							if(tmpLst.getCustomerQty()!=null) 
								cusQty = tmpLst.getCustomerQty().toString();
							if(txtCusQty!=null && txtCusQty.length>0)
								cusQty = txtCusQty[i];
							if(cusQty!=null && cusQty.length()>0)
								tmpLst.setCustomerQty(new BigDecimal(cusQty));
							
							String cusCost = "0";
							if(tmpLst.getCustomerCost()!=null) 
								cusCost = tmpLst.getCustomerCost().toString();
							if(txtSelCusCost!=null && txtSelCusCost.length>0)
								cusCost = txtSelCusCost[i];
							if(cusCost!=null && cusCost.length()>0){
								tmpLst.setCustomerCost(new BigDecimal(nf.parse(cusCost).doubleValue()));
								//totalCost = totalCost+nf.parse(cost).doubleValue();
							}
							if(tmpLst.getResType().equals("MAT-COST")){
								itemCodeList = itemCodeList+"###"+matCode;
								itemDescList = itemDescList+"###"+tmpLst.getMatNm();
								uomList = uomList+"###"+tmpLst.getUom();
								priceList = priceList+"###"+nf.format(tmpLst.getUnitPrice());
								//System.out.println("def mat cost :"+cost);
								if(cost!=null && cost.trim().length()>0)
									costList = costList+"###"+nf.format(new BigDecimal(cost));
								qtyList = qtyList+"###"+nf.format(new BigDecimal(qty));
								matCostTotal = matCostTotal+nf.parse(cost).doubleValue();
							}else{
								nonMatCodeList = nonMatCodeList+"###"+tmpLst.getMatNm();
								if(cost!=null && cost.trim().length()>0)
									nonMatCostList = nonMatCostList+"###"+nf.format(new BigDecimal(cost));
							}
							
							selectMatCodeMap.put(matCode,tmpLst);
							//selectMatList.add(tmpLst);
							i++;
						}
						totalDetailCost = nf.format(totalCost);
						totalMatCost = nf.format(matCostTotal);
						//selectMatList = selMatTmp;
					}
				}
			}
			if(selectMatCodeMap!=null && selectMatCodeMap.size()>0)
			{
				sessionMap.put("SelectMaterialList", selectMatCodeMap);
				selectMatList = selectMatCodeMap.values();
			}
			else
			{
				selectMatList = null;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "add";
	}
	
	private void displayServiceWireMaterials()
	{
		System.out.println("displayServiceWireMaterials ####################");
		try
		{
			selectMatCodeMap = (LinkedHashMap)sessionMap.get("SelectMaterialList");
			if(selectMatCodeMap==null)
				selectMatCodeMap = new LinkedHashMap();
			
			if(serviceLength!=null && serviceLength.trim().length()>0)
			{
				EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
				List servMatList = estimateEjb.getServiceWireMaterialGrid(costCenterNo,Long.parseLong(phaseDb), Long.parseLong(connectionTypeDb), wiringType, new Double(serviceLength));
				double totalCost = 0;
				for(int i=0;i<servMatList.size();i++)
				{
					MaterialGrid mg = (MaterialGrid)servMatList.get(i);
					String matCode = mg.getResCd();
					double estQty = mg.getEstimateQty().doubleValue();
					double unitPrice = mg.getUnitPrice().doubleValue();
					double estCost = estQty*unitPrice;
					if(selectMatCodeMap.containsKey(matCode))					{
						MaterialGrid mgExist = (MaterialGrid)selectMatCodeMap.get(matCode);
						double newEstQty = estQty+mgExist.getEstimateQty().doubleValue();
						mg.setEstimateQty(new BigDecimal(newEstQty));
						double newEstCost = estCost+mgExist.getEstimateQty().doubleValue()*mgExist.getUnitPrice().doubleValue();
						mg.setEstimateCost(new BigDecimal(newEstCost));
					}
					else
					{
						mg.setEstimateCost(new BigDecimal(estCost));
						totalCost = totalCost+estCost;
						totalDetailCost = nf.format(totalCost);
					}
					selectMatCodeMap.put(matCode,mg);
				}
			}
			sessionMap.put("SelectMaterialList",selectMatCodeMap);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	private void displayConductorMaterials()
	{
		System.out.println("displayConductorMaterials #####################");
		try
		{
			selectMatCodeMap = (LinkedHashMap)sessionMap.get("SelectMaterialList");
			if(selectMatCodeMap==null)
				selectMatCodeMap = new LinkedHashMap();
			
			if(conductorLength!=null && conductorLength.trim().length()>0)
			{
				EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
				List condtMatList = estimateEjb.getConductorMaterialGrid(costCenterNo, Long.parseLong(phaseDb), Long.parseLong(connectionTypeDb), wiringType, conductorType, new Double(conductorLength));
				double totalCost = 0;
				for(int i=0;i<condtMatList.size();i++)
				{
					MaterialGrid mg = (MaterialGrid)condtMatList.get(i);
					String matCode = mg.getResCd();
					//double estCost = mg.getEstimateCost().doubleValue();
					double estQty = mg.getEstimateQty().doubleValue();
					double unitPrice = mg.getUnitPrice().doubleValue();
					double estCost = estQty*unitPrice;
					if(selectMatCodeMap.containsKey(matCode))
					{
						MaterialGrid mgExist = (MaterialGrid)selectMatCodeMap.get(matCode);
						double newEstQty = estQty+mgExist.getEstimateQty().doubleValue();
						mg.setEstimateQty(new BigDecimal(newEstQty));
						double newEstCost = estCost+mgExist.getEstimateQty().doubleValue()*mgExist.getUnitPrice().doubleValue();
						mg.setEstimateCost(new BigDecimal(newEstCost));
					}
					else
					{
						mg.setEstimateCost(new BigDecimal(estCost));
					}
					totalCost = totalCost+estCost;
					selectMatCodeMap.put(matCode,mg);
				}
				totalDetailCost = nf.format(totalCost);
			}
			sessionMap.put("SelectMaterialList",selectMatCodeMap);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	private void displayStayMaterials()
	{
		System.out.println("displayStayMaterials ########################");
		try
		{
			selectMatCodeMap = (LinkedHashMap)sessionMap.get("SelectMaterialList");
			if(selectMatCodeMap==null)
				selectMatCodeMap = new LinkedHashMap();
			
			if(stayNo!=null && stayNo.trim().length()>0)
			{
				EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
				List stayMatList = estimateEjb.getStayMaterialGrid(costCenterNo, Integer.parseInt(stayNo));
				double totalCost = 0;
				for(int i=0;i<stayMatList.size();i++)
				{
					MaterialGrid mg = (MaterialGrid)stayMatList.get(i);
					String matCode = mg.getResCd();
					//double estCost = mg.getEstimateCost().doubleValue();
					double estQty = mg.getEstimateQty().doubleValue();
					double unitPrice = mg.getUnitPrice().doubleValue();
					double estCost = estQty*unitPrice;
					if(selectMatCodeMap.containsKey(matCode))
					{
						MaterialGrid mgExist = (MaterialGrid)selectMatCodeMap.get(matCode);
						double newEstQty = estQty+mgExist.getEstimateQty().doubleValue();
						mg.setEstimateQty(new BigDecimal(newEstQty));
						double newEstCost = estCost+mgExist.getEstimateQty().doubleValue()*mgExist.getUnitPrice().doubleValue();
						mg.setEstimateCost(new BigDecimal(newEstCost));
					}
					else
					{
						mg.setEstimateCost(new BigDecimal(estCost));
					}
					totalCost = totalCost+estCost;
					selectMatCodeMap.put(matCode,mg);
				}
				totalDetailCost = nf.format(totalCost);
			}
			sessionMap.put("SelectMaterialList",selectMatCodeMap);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	private void displayStrutMaterials()
	{
		System.out.println("displayStrutMaterials #########################"+strutsNo);
		try
		{
			selectMatCodeMap = (LinkedHashMap)sessionMap.get("SelectMaterialList");
			if(selectMatCodeMap==null)
				selectMatCodeMap = new LinkedHashMap();
			
			if(strutsNo!=null && strutsNo.trim().length()>0)
			{
				EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
				List strutMatList = estimateEjb.getStrutMaterialGrid(costCenterNo, Integer.parseInt(strutsNo));
				double totalCost = 0;
				for(int i=0;i<strutMatList.size();i++)
				{
					MaterialGrid mg = (MaterialGrid)strutMatList.get(i);
					String matCode = mg.getResCd();
					//double estCost = mg.getEstimateCost().doubleValue();
					double estQty = mg.getEstimateQty().doubleValue();
					double unitPrice = mg.getUnitPrice().doubleValue();
					double estCost = estQty*unitPrice;
					if(selectMatCodeMap.containsKey(matCode))
					{
						MaterialGrid mgExist = (MaterialGrid)selectMatCodeMap.get(matCode);
						double newEstQty = estQty+mgExist.getEstimateQty().doubleValue();
						mg.setEstimateQty(new BigDecimal(newEstQty));
						double newEstCost = estCost+mgExist.getEstimateQty().doubleValue()*mgExist.getUnitPrice().doubleValue();
						mg.setEstimateCost(new BigDecimal(newEstCost));
					}
					else
					{
						mg.setEstimateCost(new BigDecimal(estCost));
					}
					totalCost = totalCost+estCost;
					selectMatCodeMap.put(matCode,mg);
				}
				totalDetailCost = nf.format(totalCost);
			}
			sessionMap.put("SelectMaterialList",selectMatCodeMap);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	private void displayPoleMaterials()
	{
		System.out.println("displayPoleMaterials ##################");
		try
		{
			selectMatCodeMap = (LinkedHashMap)sessionMap.get("SelectMaterialList");
			if(selectMatCodeMap==null)
				selectMatCodeMap = new LinkedHashMap();
			
			EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
			SpsetpolEjb spsetpolEjb = new SpsetpolEjb(getSessionKey("region"));
			List<Spsetpol> poleList = spsetpolEjb.getPoleList(applicationNo, costCenterNo);
			if(poleList!=null && poleList.size()>0)
			{
				double totalCost = 0;
				for(int i=0;i<poleList.size();i++)
				{
					Spsetpol spsetpol = (Spsetpol)poleList.get(i);
					
					List poleMatList = estimateEjb.getPoleTypeMaterialGrid(costCenterNo, Long.parseLong(phaseDb), spsetpol.getId().getPoleType(), spsetpol.getId().getFromConductor(), spsetpol.getId().getToConductor(),spsetpol.getMatQty().intValue());
						
					for(int j=0;j<poleMatList.size();j++)
					{
						MaterialGrid mg = (MaterialGrid)poleMatList.get(j);
						String matCode = mg.getResCd();
						//double estCost = mg.getEstimateCost().doubleValue();
						double estQty = mg.getEstimateQty().doubleValue();
						double unitPrice = mg.getUnitPrice().doubleValue();
						double estCost = estQty*unitPrice;
						
						if(selectMatCodeMap.containsKey(matCode))
								
						{
							MaterialGrid mgExist = (MaterialGrid)selectMatCodeMap.get(matCode);
							double newEstQty = estQty+mgExist.getEstimateQty().doubleValue();
							mg.setEstimateQty(new BigDecimal(newEstQty));
							double newEstCost = estCost+mgExist.getEstimateQty().doubleValue()*mgExist.getUnitPrice().doubleValue();
							mg.setEstimateCost(new BigDecimal(newEstCost));
						}
						else
						{
							mg.setEstimateCost(new BigDecimal(estCost));
						}
						
						totalCost = totalCost+estCost;
						selectMatCodeMap.put(matCode,mg);
					}
					if(spsetpol.getId().getPointType().equalsIgnoreCase("NEW"))
					{
						String poleCode = spsetpol.getId().getMatCd().trim();
						double poleQty = spsetpol.getMatQty().doubleValue();
						MaterialGrid tmpMG = estimateEjb.getMaterialGridByMatCd(costCenterNo, poleCode, poleQty);
						if(tmpMG!=null)
						{
							if(selectMatCodeMap.containsKey(poleCode))
							{
								MaterialGrid mgExist = (MaterialGrid)selectMatCodeMap.get(poleCode);
								double newEstQty = tmpMG.getEstimateQty().doubleValue()+mgExist.getEstimateQty().doubleValue();
								tmpMG.setEstimateQty(new BigDecimal(newEstQty));
								double newEstCost = tmpMG.getEstimateCost().doubleValue()+mgExist.getEstimateQty().doubleValue()*mgExist.getUnitPrice().doubleValue();
								tmpMG.setEstimateCost(new BigDecimal(newEstCost));
							}
							selectMatCodeMap.put(poleCode,tmpMG);	
						}
						
						
					}	
				}
				totalDetailCost = nf.format(totalCost);
			}
				
			
			sessionMap.put("SelectMaterialList",selectMatCodeMap);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public String removeMaterials()
	{
		try
		{
			System.out.println("removeMaterials");
			nf.setGroupingUsed(true);
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			double newTotMatCost = 0;
			selectMatCodeMap = (LinkedHashMap)sessionMap.get("SelectMaterialList");
			costCenterNo = (String)sessionMap.get("costCenterNo");
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
					
					String cusQty = "";
					if(tmpLst.getCustomerQty()!=null) 
						cusQty = tmpLst.getCustomerQty().toString();
					String cusCost = "";
					if(tmpLst.getCustomerCost()!=null) 
						cusCost = tmpLst.getCustomerCost().toString();
					if(txtCusQty!=null && txtCusQty.length>0)
						cusQty = txtCusQty[i];
					if(txtSelCusCost!=null && txtSelCusCost.length>0)
						cusCost = txtSelCusCost[i];
					if(cusQty!=null && cusQty.length()>0)
						tmpLst.setCustomerQty(new BigDecimal(cusQty));
					
					String cost = "";
					if(tmpLst.getEstimateCost()!=null) 
						cost = tmpLst.getEstimateCost().toString();
					if(txtSelMatCost!=null && txtSelMatCost.length>0)
						cost = txtSelMatCost[i];
					if(cost!=null && cost.length()>0)
					{
						tmpLst.setEstimateCost(new BigDecimal(nf.parse(cost).doubleValue()));
						totalCost = totalCost+nf.parse(cost).doubleValue();
					}
					if(cusCost!=null && cusCost.length()>0)
					{
						tmpLst.setCustomerCost(new BigDecimal(nf.parse(cusCost).doubleValue()));
						//totalCost = totalCost+nf.parse(cost).doubleValue();
					}
					totalDetailCost = nf.format(totalCost);
					
					selectMatCodeMap.put(matCode,tmpLst);
					
					i++;
				}
				//selectMatList = selMatTmp;
			}
			double dbDelMatCost = 0;
			if(chkSelMatCode!=null)//adding materials from new list
			{
				ArrayList removeList = null;
				String isNewEst = (String)sessionMap.get("IsNewEstimate");
				ArrayList savedMat = (ArrayList)sessionMap.get("SavedMaterialList");
				
				PcestdttEjb pcestdttEjb =  new PcestdttEjb(getSessionKey("region"));
				for(int i=0;i<chkSelMatCode.length;i++)
				{
					StringTokenizer st = new StringTokenizer(chkSelMatCode[i],"###");
					
					String indexStr = st.nextToken();
					String matCode = st.nextToken();
					selectMatCodeMap.remove(matCode);
					
					String tmpCost = txtSelMatCost[Integer.parseInt(indexStr)];
					double delMatCost = 0;
					if(tmpCost!=null && tmpCost.trim().length()>0)
						delMatCost = nf.parse(tmpCost).doubleValue();
					double matCost = nf.parse(totalDetailCost).doubleValue();
					newTotMatCost = matCost-delMatCost;
					totalDetailCost = nf.format(newTotMatCost);
					if(isNewEst!=null && isNewEst.equals("N"))
					{
						if(savedMat.contains(matCode))
						{
							//deleting materials
							PcestdttPK pcestdttPK = new PcestdttPK();
							pcestdttPK.setDeptId(costCenterNo);
							pcestdttPK.setEstimateNo(applicationNo);
							pcestdttPK.setResCd(matCode);
							pcestdttPK.setRevNo(2);
							Pcestdtt pcestdtt = new Pcestdtt();
							pcestdtt.setId(pcestdttPK);
							
							//pcestdttEjb.removePcestdtt(pcestdtt);
							if(removeList==null)
								removeList = new ArrayList();
							removeList.add(pcestdttPK);
							
							dbDelMatCost = dbDelMatCost+delMatCost;
						}
					}
					
				}
				if(removeList!=null)
				{
					PcesthttEjb pcesthttEjb = new PcesthttEjb(getSessionKey("region"));
					Pcesthtt pcesthtt = null;
					try
					{
						pcesthtt = pcesthttEjb.findByEstimationNo(applicationNo,costCenterNo);
						double newDetailCost = pcesthtt.getStdCost().doubleValue()- dbDelMatCost;
						pcesthtt.setStdCost(new BigDecimal(Double.toString(newDetailCost)));
						
					}
					catch(Exception e1){
						e1.printStackTrace();
					}
					EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
					estimateEjb.delete(removeList,pcesthtt);
				}
			}
			
			sessionMap.put("SelectMaterialList",selectMatCodeMap);
			selectMatList = selectMatCodeMap.values();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return "remove";
	}
	
	public String displayPoleMaterialParams()
	{
		poleList = new ArrayList();
		poleList.add("A0211 - Poles RC  8.3m 100kg");
		poleList.add("A0203 - Poles RC  6.0m ");
		return SUCCESS;
	}
	
	
	
	
	
	public String addPoleMaterials()
	{
		System.out.println("wwwwww-------------addPoleMaterials");
		try
		{
			
				
				nf.setGroupingUsed(true);
				nf.setMaximumFractionDigits(2);
				nf.setMinimumFractionDigits(2);
				
				selectMatList = new ArrayList();
				String isNewEst = (String)sessionMap.get("IsNewEstimate");
				String isDefault = (String)sessionMap.get("IsDefaultMasterAdd");
				
					
					selectMatCodeMap = (LinkedHashMap)sessionMap.get("SelectMaterialList");
					
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
							
							//String uom  = tmpLst.getUom();
							//String up  = tmpLst.getUnitPrice().toString();
							//String matName  = tmpLst.getMatNm();
							String qty = "";
							if(tmpLst.getEstimateQty()!=null) 
								qty = tmpLst.getEstimateQty().toString();
							String cost = "";
							if(tmpLst.getEstimateCost()!=null) 
								cost = tmpLst.getEstimateCost().toString();
							String cusQty = "";
							if(tmpLst.getCustomerQty()!=null) 
								cusQty = tmpLst.getCustomerQty().toString();
							String cusCost = "";
							if(tmpLst.getCustomerCost()!=null) 
								cusCost = tmpLst.getCustomerCost().toString();
							if(txtSelQty!=null && txtSelQty.length>0)
								qty = txtSelQty[i];
							if(txtSelMatCost!=null && txtSelMatCost.length>0)
								cost = txtSelMatCost[i];
							if(txtCusQty!=null && txtCusQty.length>0)
								cusQty = txtCusQty[i];
							if(txtSelCusCost!=null && txtSelCusCost.length>0)
								cusCost = txtSelCusCost[i];
							if(qty!=null && qty.length()>0)
								tmpLst.setEstimateQty(new BigDecimal(qty));
							if(cusQty!=null && cusQty.length()>0)
								tmpLst.setCustomerQty(new BigDecimal(cusQty));
							if(cost!=null && cost.length()>0)
							{
								tmpLst.setEstimateCost(new BigDecimal(nf.parse(cost).doubleValue()));
								totalCost = totalCost+nf.parse(cost).doubleValue();
							}
							if(cusCost!=null && cusCost.length()>0)
							{
								tmpLst.setCustomerCost(new BigDecimal(nf.parse(cusCost).doubleValue()));
								//totalCost = totalCost+nf.parse(cost).doubleValue();
							}
							totalDetailCost = nf.format(totalCost);
							
							selectMatCodeMap.put(matCode,tmpLst);
							selectMatList.add(tmpLst);
							i++;
						}
						//selectMatList = selMatTmp;
					}
				
					
					/////******* adding pole mats******************/
					EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
					int poleQty = Integer.parseInt(noOfPole);
					int poleCusQty = 0;
					if(noOfCusPole!=null && noOfCusPole.length()>0)
						poleCusQty = Integer.parseInt(noOfCusPole);
					int totalPoles = poleQty+poleCusQty;
					if(toConductor==null)
						toConductor = fromConductor;
					List poleMatList = estimateEjb.getPoleTypeMaterialGrid(costCenterNo, Long.parseLong(phaseDb), poleType, fromConductor, toConductor,totalPoles);
					double totalCost = 0;
					for(int j=0;j<poleMatList.size();j++)
					{
						MaterialGrid mg = (MaterialGrid)poleMatList.get(j);
						String matCode = mg.getResCd();
						//double estCost = mg.getEstimateCost().doubleValue();
						double estQty = mg.getEstimateQty().doubleValue();
						double unitPrice = mg.getUnitPrice().doubleValue();
						double estCost = estQty*unitPrice;
						double qtyPerPole = estQty/totalPoles;
						double cusQty = poleCusQty*qtyPerPole;
						if(poleCusQty>0)
						{
							mg.setCustomerQty(new BigDecimal(cusQty));
							mg.setCustomerCost(new BigDecimal(cusQty*unitPrice));
						}
						if(selectMatCodeMap.containsKey(matCode))
								
						{
							MaterialGrid mgExist = (MaterialGrid)selectMatCodeMap.get(matCode);
							double newEstQty = estQty+mgExist.getEstimateQty().doubleValue();
							mg.setEstimateQty(new BigDecimal(newEstQty));
							double newEstCost = estCost+mgExist.getEstimateQty().doubleValue()*mgExist.getUnitPrice().doubleValue();
							mg.setEstimateCost(new BigDecimal(newEstCost));
							if(mgExist.getCustomerQty()!=null)
							{
								double newCusQty = mg.getCustomerQty().doubleValue()+mgExist.getCustomerQty().doubleValue();
								mg.setCustomerQty(new BigDecimal(newCusQty));
							}
						}
						else
						{
							mg.setEstimateCost(new BigDecimal(estCost));
						}
						totalCost = totalCost+estCost;
						selectMatCodeMap.put(matCode,mg);
					}
					
					if(isWithPole!=null && isWithPole.equals("Y"))
					{
						StringTokenizer st = new StringTokenizer(poleCode,"-");
						poleCode = st.nextToken().trim();
						MaterialGrid tmpMG = estimateEjb.getMaterialGridByMatCd(costCenterNo, poleCode, new Double(totalPoles));
					 	
					 	if(tmpMG!=null)
					 	{
					 		if(poleCusQty>0)
						 	{
								tmpMG.setCustomerQty(new BigDecimal(poleCusQty));
								double cusCost = poleCusQty*tmpMG.getUnitPrice().doubleValue();
								tmpMG.setCustomerCost(new BigDecimal(cusCost));
						 	}
							if(selectMatCodeMap.containsKey(poleCode))
							{
								MaterialGrid mgExist = (MaterialGrid)selectMatCodeMap.get(poleCode);
								double newEstQty = tmpMG.getEstimateQty().doubleValue()+mgExist.getEstimateQty().doubleValue();
								tmpMG.setEstimateQty(new BigDecimal(newEstQty));
								double newEstCost = tmpMG.getEstimateCost().doubleValue()+mgExist.getEstimateQty().doubleValue()*mgExist.getUnitPrice().doubleValue();
								tmpMG.setEstimateCost(new BigDecimal(newEstCost));
								if(mgExist.getCustomerQty()!=null)
								{
									double newCusQty = tmpMG.getCustomerQty().doubleValue()+mgExist.getCustomerQty().doubleValue();
									tmpMG.setCustomerQty(new BigDecimal(newCusQty));
									double cusCost = newCusQty*tmpMG.getUnitPrice().doubleValue();
									tmpMG.setCustomerCost(new BigDecimal(cusCost));
								}
							}
					 	}
						selectMatCodeMap.put(poleCode,tmpMG);
					}
					
					
					
					poleType = "";
					fromConductor = "";
					toConductor = "";
					isWithPole = "";
					poleCode = "";
					noOfPole = "";
					
				
				if(selectMatCodeMap!=null && selectMatCodeMap.size()>0)
				{
					sessionMap.put("SelectMaterialList", selectMatCodeMap);
					selectMatList = selectMatCodeMap.values();
				}
				else
				{
					selectMatList = null;
				}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "success";
	}
	
	
	public String displayNPLMatCost()
	{
		System.out.println("displayNPLMatCost");
		try
		{
			if(isNplClicked!=null && isNplClicked.equals("true"))
			{
				
				otherCostList = new ArrayList();
				
				
				LinkedHashMap selMatTmp = (LinkedHashMap)sessionMap.get("SelectMaterialList");
				ArrayList selList = null;
				if(selMatTmp!=null)
				{
					selList = new ArrayList(selMatTmp.keySet());
				}
				
				costCenterNo = (String)sessionMap.get("costCenterNo");
		    	EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
		    	nplMatList = estimateEjb.getNPLMaterials(costCenterNo,selList);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    	return SUCCESS;
	}
	
	public String addNPLMatCost()
	{
		try{
			if(lineLength!=null && lineLength.trim().length()>0 && wiringType!=null && wiringType.length()>0)
			{
				
				nf.setGroupingUsed(true);
				nf.setMaximumFractionDigits(2);
				nf.setMinimumFractionDigits(2);
				
				selectMatList = new ArrayList();
				String isNewEst = (String)sessionMap.get("IsNewEstimate");
				String isDefault = (String)sessionMap.get("IsDefaultMasterAdd");
				
					
					selectMatCodeMap = (LinkedHashMap)sessionMap.get("SelectMaterialList");
					
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
							
							//String uom  = tmpLst.getUom();
							//String up  = tmpLst.getUnitPrice().toString();
							//String matName  = tmpLst.getMatNm();
							String qty = "";
							if(tmpLst.getEstimateQty()!=null) 
								qty = tmpLst.getEstimateQty().toString();
							String cost = "";
							if(tmpLst.getEstimateCost()!=null) 
								cost = tmpLst.getEstimateCost().toString();
							String cusQty = "";
							if(tmpLst.getCustomerQty()!=null) 
								cusQty = tmpLst.getCustomerQty().toString();
							String cusCost = "";
							if(tmpLst.getCustomerCost()!=null) 
								cusCost = tmpLst.getCustomerCost().toString();
							if(txtSelQty!=null && txtSelQty.length>0)
								qty = txtSelQty[i];
							if(txtSelMatCost!=null && txtSelMatCost.length>0)
								cost = txtSelMatCost[i];
							if(txtCusQty!=null && txtCusQty.length>0)
								cusQty = txtCusQty[i];
							if(txtSelCusCost!=null && txtSelCusCost.length>0)
								cusCost = txtSelCusCost[i];
							if(qty!=null && qty.length()>0)
								tmpLst.setEstimateQty(new BigDecimal(qty));
							if(cusQty!=null && cusQty.length()>0)
								tmpLst.setCustomerQty(new BigDecimal(cusQty));
							if(cost!=null && cost.length()>0)
							{
								tmpLst.setEstimateCost(new BigDecimal(nf.parse(cost).doubleValue()));
								totalCost = totalCost+nf.parse(cost).doubleValue();
							}
							if(cusCost!=null && cusCost.length()>0)
							{
								tmpLst.setCustomerCost(new BigDecimal(nf.parse(cusCost).doubleValue()));
								//totalCost = totalCost+nf.parse(cost).doubleValue();
							}
							totalDetailCost = nf.format(totalCost);
							
							selectMatCodeMap.put(matCode,tmpLst);
							selectMatList.add(tmpLst);
							i++;
						}
						//selectMatList = selMatTmp;
					}
				
					if(chkNplMatCode!=null)//adding materials from new list
					{
						if(selectMatCodeMap==null)
							selectMatCodeMap = new LinkedHashMap();
						for(int i=0;i<chkNplMatCode.length;i++)
						{
							StringTokenizer st = new StringTokenizer(chkNplMatCode[i],"###");
							MaterialGrid tmpAL = new MaterialGrid();
							String index = st.nextToken();
							String matCode = st.nextToken();
							String uom  = st.nextToken();
							String up  = st.nextToken();
							String matName  = st.nextToken();
							
							tmpAL.setResCd(matCode);
							tmpAL.setMatNm(matName);
							tmpAL.setUom(uom);
							tmpAL.setUnitPrice(new BigDecimal(up));
							tmpAL.setResCat(new BigDecimal(1));
							tmpAL.setResType("MAT-COST-OTHER");
							//tmpAL.add("");
							//tmpAL.add("");
							
							
							selectMatList.add(tmpAL);
							selectMatCodeMap.put(matCode,tmpAL);
							
						}
					}
	
				
				if(selectMatCodeMap!=null && selectMatCodeMap.size()>0)
				{
					sessionMap.put("SelectMaterialList", selectMatCodeMap);
					selectMatList = selectMatCodeMap.values();
				}
				else
				{
					selectMatList = null;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "addNPL";
	}
	
	public String displayOtherCost()
	{
		try
		{
			if(isOthClicked!=null && isOthClicked.equals("true"))
			{
				otherCostList = new ArrayList();
				
				
				LinkedHashMap selMatTmp = (LinkedHashMap)sessionMap.get("SelectMaterialList");
				ArrayList selList = null;
				if(selMatTmp!=null)
				{
					selList = new ArrayList(selMatTmp.keySet());
				}
				
				costCenterNo = (String)sessionMap.get("costCenterNo");
		    	EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
		    	otherCostList = estimateEjb.getAvailableOtherCosts(costCenterNo,selList);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    	return "other";
	}
	
	public String addOtherCost()
	{
		System.out.println("addOtherCost@@@");
		try
		{
			nf.setGroupingUsed(true);
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			
			selectMatCodeMap = (LinkedHashMap)sessionMap.get("SelectMaterialList");
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
					String cost = "";
					if(tmpLst.getEstimateCost()!=null) 
						cost = tmpLst.getEstimateCost().toString();
					if(txtSelQty!=null && txtSelQty.length>0)
						qty = txtSelQty[i];
					if(txtSelMatCost!=null && txtSelMatCost.length>0)
						cost = txtSelMatCost[i];
					if(qty!=null && qty.length()>0)
						tmpLst.setEstimateQty(new BigDecimal(qty));
					if(cost!=null && cost.length()>0)
					{
						tmpLst.setEstimateCost(new BigDecimal(nf.parse(cost).doubleValue()));
						totalCost = totalCost+nf.parse(cost).doubleValue();
					}
					
					String cusQty = "";
					if(tmpLst.getCustomerQty()!=null) 
						cusQty = tmpLst.getCustomerQty().toString();
					if(txtCusQty!=null && txtCusQty.length>0)
						cusQty = txtCusQty[i];
					if(cusQty!=null && cusQty.length()>0)
						tmpLst.setCustomerQty(new BigDecimal(cusQty));
					String cusCost = "";
					if(tmpLst.getCustomerCost()!=null) 
						cusCost = tmpLst.getCustomerCost().toString();
					if(txtSelCusCost!=null && txtSelCusCost.length>0)
						cusCost = txtSelCusCost[i];
					if(cusCost!=null && cusCost.length()>0)
					{
						tmpLst.setCustomerCost(new BigDecimal(nf.parse(cusCost).doubleValue()));
						//totalCost = totalCost+nf.parse(cost).doubleValue();
					}
					totalDetailCost = nf.format(totalCost);
					
					selectMatCodeMap.put(matCode,tmpLst);
					
					i++;
				}
				selectMatList = selectMatCodeMap.values();
			}
			if(chkResType!=null)//adding materials from new list
			{
				if(selectMatCodeMap==null)
					selectMatCodeMap = new LinkedHashMap();
				for(int i=0;i<chkResType.length;i++)
				{
					StringTokenizer st = new StringTokenizer(chkResType[i],"###");
					MaterialGrid tmpAL = new MaterialGrid();
					String index = st.nextToken();//0
					String resCode = st.nextToken();//1
					String resType  = st.nextToken();//2
					String resCat  = st.nextToken();//3
					String resName  = st.nextToken();//4
					
					
					tmpAL.setResCd(resCode);
					tmpAL.setMatNm(resName);
					tmpAL.setResType(resType);
					tmpAL.setResCat(new BigDecimal(resCat));
					tmpAL.setUom("NO.");
					tmpAL.setUnitPrice(new BigDecimal(1));
					String tmpCost = txtOtherCost[Integer.parseInt(index)];
					if(tmpCost!=null && tmpCost.trim().length()>0)
					{
						BigDecimal qty = new BigDecimal(nf.parse(tmpCost).doubleValue());
						tmpAL.setEstimateQty(qty);
						tmpAL.setEstimateCost(new BigDecimal(nf.parse(txtOtherCost[Integer.parseInt(index)]).doubleValue()));
					}
										
					selectMatCodeMap.put(resCode,tmpAL);
					
				}
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "othcost";
	}
	
	
	public String calLabourCost()
	{
		try
		{
			System.out.println("@@@@@@ calLabourCost @@@"+totalLabCost+"@@@");
			selectMatCodeMap = (LinkedHashMap)sessionMap.get("SelectMaterialList");
			
			if(selectMatCodeMap!=null && selectMatCodeMap.size()>0)
			{
				Iterator it = selectMatCodeMap.keySet().iterator();
				int i=0;
				double totalCost = 0;
				while(it.hasNext())
				{
					String matCode = (String)it.next();
					MaterialGrid tmpLst = (MaterialGrid)selectMatCodeMap.get(matCode);
					if(matCode.equals("LABOUR"))
					{
						tmpLst.setEstimateCost(new BigDecimal(nf.parse(totalLabCost).doubleValue()));
						tmpLst.setEstimateQty(new BigDecimal(nf.parse(totalLabHrs).doubleValue()));
						tmpLst.setUnitPrice(new BigDecimal(labourRate));
						tmpLst.setUom("HRS.");
					}
					else if(matCode.equals("OVERHEAD"))
					{
						
						tmpLst.setEstimateQty(new BigDecimal(nf.parse(totalLabHrs).doubleValue()));
						double ohCost = nf.parse(totalLabHrs).doubleValue()*(new BigDecimal(overheadRate).doubleValue());
						tmpLst.setEstimateCost(new BigDecimal(ohCost));
						tmpLst.setUnitPrice(new BigDecimal(overheadRate));
						tmpLst.setUom("HRS.");
					}
					else
					{
						String qty = "";
						if(tmpLst.getEstimateQty()!=null) 
							qty = tmpLst.getEstimateQty().toString();
						String cost = "";
						if(tmpLst.getEstimateCost()!=null) 
							cost = tmpLst.getEstimateCost().toString();
						String cusQty = "";
						if(tmpLst.getCustomerQty()!=null) 
							cusQty = tmpLst.getCustomerQty().toString();
						String cusCost = "";
						if(tmpLst.getCustomerCost()!=null) 
							cusCost = tmpLst.getCustomerCost().toString();
						if(txtSelQty!=null && txtSelQty.length>0)
							qty = txtSelQty[i];
						if(txtSelMatCost!=null && txtSelMatCost.length>0)
							cost = txtSelMatCost[i];
						if(txtCusQty!=null && txtCusQty.length>0)
							cusQty = txtCusQty[i];
						if(txtSelCusCost!=null && txtSelCusCost.length>0)
							cusCost = txtSelCusCost[i];
						if(qty!=null && qty.length()>0)
							tmpLst.setEstimateQty(new BigDecimal(qty));
						if(cusQty!=null && cusQty.length()>0)
							tmpLst.setCustomerQty(new BigDecimal(cusQty));
						if(cost!=null && cost.length()>0)
						{
							tmpLst.setEstimateCost(new BigDecimal(nf.parse(cost).doubleValue()));
							totalCost = totalCost+nf.parse(cost).doubleValue();
						}
						if(cusCost!=null && cusCost.length()>0)
						{
							tmpLst.setCustomerCost(new BigDecimal(nf.parse(cusCost).doubleValue()));
							//totalCost = totalCost+nf.parse(cost).doubleValue();
						}
					}
					
					totalDetailCost = nf.format(totalCost);
					
					selectMatCodeMap.put(matCode,tmpLst);
					//selectMatList.add(tmpLst);
					i++;
				}
				selectMatList = selectMatCodeMap.values();
				
			}
		}
		catch(Exception e){
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	public String refreshSelectMat()
	{
		try
		{
			selectMatCodeMap = (LinkedHashMap)sessionMap.get("SelectMaterialList");
			if(selectMatCodeMap!=null && selectMatCodeMap.size()>0)
			{
				Iterator it = selectMatCodeMap.keySet().iterator();
				int i=0;
				double totalCost = 0;
				while(it.hasNext())
				{
					String matCode = (String)it.next();
					MaterialGrid tmpLst = (MaterialGrid)selectMatCodeMap.get(matCode);
					
					String qty = "";
					if(tmpLst.getEstimateQty()!=null) 
						qty = tmpLst.getEstimateQty().toString();
					String cost = "";
					if(tmpLst.getEstimateCost()!=null) 
						cost = tmpLst.getEstimateCost().toString();
					String cusQty = "";
					if(tmpLst.getCustomerQty()!=null) 
						cusQty = tmpLst.getCustomerQty().toString();
					String cusCost = "";
					if(tmpLst.getCustomerCost()!=null) 
						cusCost = tmpLst.getCustomerCost().toString();
					if(txtSelQty!=null && txtSelQty.length>0)
						qty = txtSelQty[i];
					if(txtSelMatCost!=null && txtSelMatCost.length>0)
						cost = txtSelMatCost[i];
					if(txtCusQty!=null && txtCusQty.length>0)
						cusQty = txtCusQty[i];
					if(txtSelCusCost!=null && txtSelCusCost.length>0)
						cusCost = txtSelCusCost[i];
					if(qty!=null && qty.length()>0)
						tmpLst.setEstimateQty(new BigDecimal(qty));
					if(cusQty!=null && cusQty.length()>0)
						tmpLst.setCustomerQty(new BigDecimal(cusQty));
					if(cost!=null && cost.length()>0)
					{
						tmpLst.setEstimateCost(new BigDecimal(nf.parse(cost).doubleValue()));
						totalCost = totalCost+nf.parse(cost).doubleValue();
					}
					if(cusCost!=null && cusCost.length()>0)
					{
						tmpLst.setCustomerCost(new BigDecimal(nf.parse(cusCost).doubleValue()));
						//totalCost = totalCost+nf.parse(cost).doubleValue();
					}
					
					totalDetailCost = nf.format(totalCost);
					
					selectMatCodeMap.put(matCode,tmpLst);
					//selectMatList.add(tmpLst);
					i++;
				}
				selectMatList = selectMatCodeMap.values();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
			
		return SUCCESS;
	}
	
	
	private void calculatePoleLabour()
	{
		System.out.println("calculate pole labour ##################");
		try
		{
			EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
			SpsetpolEjb spsetpolEjb = new SpsetpolEjb(getSessionKey("region"));
			List<Spsetpol> poleList = spsetpolEjb.getPoleList(applicationNo, costCenterNo);
			if(poleList!=null && poleList.size()>0)
			{
				double totalCost = 0;
				for(int i=0;i<poleList.size();i++)
				{
					Spsetpol spsetpol = (Spsetpol)poleList.get(i);
					
					if(spsetpol.getId().getPointType().equalsIgnoreCase("NEW"))
					{
						double labRate = Double.parseDouble(labourRate);
						String poleCode = spsetpol.getId().getMatCd().trim();
						double poleQty = spsetpol.getMatQty().doubleValue();
						int poleQtyI = (int)poleQty;

						List<LabourGrid> poleLab = estimateEjb.getDefaultPoleLabour(costCenterNo,"NC", poleCode, poleQtyI);
						if(poleLab!=null)
						{
							for (int k=0;k<poleLab.size();k++)
							{
								LabourGrid lg = poleLab.get(k);
								//labCost = labCost+lg.getLabourCost().doubleValue();
								labHrs = labHrs+lg.getLabourHours().doubleValue();
								labCost = labCost+lg.getLabourHours().doubleValue()*labRate;
							}
						}
						/*if(distanceToServicePlace!=null && distanceToServicePlace.trim().length()>0)
						{
							double distance = Double.parseDouble(distanceToServicePlace);
							//double distance = 5;
							List<LabourGrid> poleTransLab = estimateEjb.getDefaultPoleTransportLabour(costCenterNo,"NC", poleCode, poleQtyI,distance);
							if(poleTransLab!=null)
							{
								for (int k=0;k<poleTransLab.size();k++)
								{
									LabourGrid lg = poleTransLab.get(k);
									//labCost = labCost+lg.getLabourCost().doubleValue();
									labHrs = labHrs+lg.getLabourHours().doubleValue();
									labCost = labCost+lg.getLabourHours().doubleValue()*labRate;
								}
							}
						}*/
					}	
				}
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	private void calculateServiceWireLabour()
	{
		System.out.println("calcultae service wire labour ####################");
		try
		{
			int noOfSpan = 0;
			if(spans!=null && spans.trim().length()>0)
				noOfSpan = Integer.parseInt(spans);
			
			double labRate = Double.parseDouble(labourRate);
			if(noOfSpan>1)
			{
				//last span
				EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
				List<LabourGrid> srvcLab = estimateEjb.getDefaultServiceWireLabour(costCenterNo,
						"NC", phaseDb, "LAST",Integer.toString(1));
				
				for(int i=0;i<srvcLab.size();i++)
				{
					LabourGrid lg = (LabourGrid)srvcLab.get(i);
					//labCost = labCost+lg.getLabourCost().doubleValue();
					labHrs = labHrs+lg.getLabourHours().doubleValue();
					labCost = labCost+lg.getLabourHours().doubleValue()*labRate;
				}
				
				//additional span
				List<LabourGrid> srvcLab2 = estimateEjb.getDefaultServiceWireLabour(costCenterNo,
						"NC", phaseDb, "OTHER",Integer.toString(noOfSpan-1));
				
				for(int i=0;i<srvcLab2.size();i++)
				{
					LabourGrid lg = (LabourGrid)srvcLab2.get(i);
					//labCost = labCost+lg.getLabourCost().doubleValue();
					labHrs = labHrs+lg.getLabourHours().doubleValue();
					labCost = labCost+lg.getLabourHours().doubleValue()*labRate;
				}
			}
			else if(noOfSpan==1)
			{
				//last span
				EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
				List<LabourGrid> srvcLab = estimateEjb.getDefaultServiceWireLabour(costCenterNo,
						"NC", phaseDb, "LAST",Integer.toString(1));
				
				for(int i=0;i<srvcLab.size();i++)
				{
					LabourGrid lg = (LabourGrid)srvcLab.get(i);
					//labCost = labCost+lg.getLabourCost().doubleValue();
					labHrs = labHrs+lg.getLabourHours().doubleValue();
					labCost = labCost+lg.getLabourHours().doubleValue()*labRate;
				}
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void calculateConductorLabour()
	{
		System.out.println("calculate conductor labour #####################");
		try
		{
			if(conductorLength!=null && conductorLength.trim().length()>0)
			{
				double labRate = Double.parseDouble(labourRate);
				EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
				List<LabourGrid> cndtLab = estimateEjb.getDefaultConductorLabour(costCenterNo,
						"NC", phaseDb, conductorType,
						 conductorLength);
				for(int i=0;i<cndtLab.size();i++)
				{
					LabourGrid lg = (LabourGrid)cndtLab.get(i);
					//labCost = labCost+lg.getLabourCost().doubleValue();
					labHrs = labHrs+lg.getLabourHours().doubleValue();
					labCost = labCost+lg.getLabourHours().doubleValue()*labRate;
				}
				
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	private void calculateStayLabour()
	{
		System.out.println("calculateStayLabour ########################");
		try
		{
			try
		{
			SpsetstyEjb spsetstyEjb = new SpsetstyEjb(getSessionKey("region"));
			List<Spsetsty> strytList = spsetstyEjb.getStayList(applicationNo,  costCenterNo);
			
			if(strytList!=null && strytList.size()>0)
			{
				double labRate = Double.parseDouble(labourRate);
				for(int i=0;i<strytList.size();i++)
				{
					Spsetsty spsetsty = strytList.get(i);
					String stayType = spsetsty.getId().getStayType();
					int matQty = spsetsty.getMatQty().intValue();
					
					EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
					List stayLabList = estimateEjb.getDefaultStayLabour(costCenterNo, "NC", stayType, matQty);
				
				
					if(stayLabList!=null)
					{
						for (int k=0;k<stayLabList.size();k++)
						{
							LabourGrid lg = (LabourGrid)stayLabList.get(k);
							//labCost = labCost+lg.getLabourCost().doubleValue();
							labHrs = labHrs+lg.getLabourHours().doubleValue();
							labCost = labCost+lg.getLabourHours().doubleValue()*labRate;
						}
					}
				}					
				
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	private void calculateStrutLabour()
	{
		System.out.println("displayStrutlabour #########################"+strutsNo);
		try
		{
			SpsetstuEjb spsetstuEjb = new SpsetstuEjb(getSessionKey("region"));
			List<Spsetstu> strutList = spsetstuEjb.getStrutList(applicationNo,  costCenterNo);
			
			if(strutList!=null && strutList.size()>0)
			{
				double labRate = Double.parseDouble(labourRate);
				for(int i=0;i<strutList.size();i++)
				{
					Spsetstu spsetstu = strutList.get(i);
					String matCode = spsetstu.getId().getMatCd();
					int matQty = spsetstu.getMatQty().intValue();
					
					EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
					List strutLabList = estimateEjb.getDefaultStrutLabour(costCenterNo, "NC", matCode.trim(), matQty);
				
				
					if(strutLabList!=null)
					{
						for (int k=0;k<strutLabList.size();k++)
						{
							LabourGrid lg = (LabourGrid)strutLabList.get(k);
							//labCost = labCost+lg.getLabourCost().doubleValue();
							labHrs = labHrs+lg.getLabourHours().doubleValue();
							labCost = labCost+lg.getLabourHours().doubleValue()*labRate;
						}
					}
				}					
				
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
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
