package estimatecr.web;

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
import estimate.model.Spestlab;
import estimate.model.SpestlabPK;
import estimate.model.Spserest;
import estimate.model.SpserestPK;
import estimate.model.Spsetpol;
import estimate.model.Spsetstu;
import estimate.model.Spsetsty;
import estimate.service.EstimateEjb;
import estimate.service.PcestdttEjb;
import estimate.service.PcesthttEjb;
import estimate.service.SplabratEjb;
import estimate.service.SpserestEjb;
import estimate.service.SpsetpolEjb;
import estimate.service.SpsetstuEjb;
import estimate.service.SpsetstyEjb;


/**
 * @author Dell
 *
 */
public class LabourActionCR extends ActionSupport  implements SessionAware, ParameterAware,ServletResponseAware,ServletRequestAware{

	private ArrayList<ArrayList<String>> matList;
	private List<LabourGrid> labourList;
	//private List matList;
	//private Collection selectMatList;
	private Collection selectLabList;
	private Collection afterRemoveMatList;
	/*private Map<String, Object> session;
	public Map<String, Object> getSession() {
		return session;
	}*/

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
	
	private String[] chkLabCode;
	private String[] chkNplMatCode;
	private String totalLabCost;
//	private ArrayList otherCostList;
	//private ArrayList nplMatList;
	//private ArrayList poleMatList;
	//private ArrayList poleList;
	
	private String[] chkSelLabCode;
	private String[] txtSelLabQty;
	private String[] txtSelLabHrs;
	private String[] txtSelLabCost;
	///private LinkedHashMap selectMatCodeMap;
	private LinkedHashMap selectLabCodeMap;
	private String[] txtCusQty;
	
	private String[] chkResType;
	private String[] txtOtherCost;
	
	private String isUndoReject;
	
	private String isMatClicked;
	private String isOthClicked;
	private String isNplClicked;
	//private String isAddMatClicked;
	private String isPoleMatClicked;
	private String isPoleMatParamClicked;
	private String isAddLabClicked;
	private String isShowLabLinkClicked;
	private String isRemoveLabClicked;
	
	//pole params variables
	private String poleType;
	private String fromConductor;
	private String toConductor;
	private String isWithPole;
	private String poleCode;
	private String noOfPole;
	
	private String labourRate;
	private String transportRate;
	private String overheadRate;
	
	private String labourCodeList;
	private String labourDescList;
	private String labourUomList;
	private String labourPriceList;
	private String labourQtyList;
	private String labourCostList;
	
	String isRemoveFinish = "false";
	//private LinkedHashMap selectLabCodeMap;
		
	public String execute() throws Exception 
	{
		return SUCCESS;
	}
	
	

	
	
	public String displayAvailLabour() throws Exception 
	{
		try
		{
			if(isShowLabLinkClicked!=null && isShowLabLinkClicked.equals("true"))
			{
				ArrayList selList = null;
				LinkedHashMap selLabTmp = (LinkedHashMap)sessionMap.get("SelectLabourList");
				if(selLabTmp!=null)
				{
					selList = new ArrayList(selLabTmp.keySet());
					if(selList.size()==0)
						selList = null;
				}
				SplabratEjb splabratEjb = new SplabratEjb(sessionMap.get("region").toString());
				labourList = splabratEjb.getLabourGrid(costCenterNo, "CR",selList);
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	
	
	
	
	
		
	public String displaySelectLabour()
	{
		String retVal = "select";
		try
		{
			if(isAddLabClicked!=null && isAddLabClicked.equals("true"))
			{
				System.out.println("adding labour codes ###1"+sessionMap.get("SelectLabourList"));
				selectLabCodeMap = (LinkedHashMap)sessionMap.get("SelectLabourList");
				if(selectLabCodeMap!=null && selectLabCodeMap.size()>0)
				{
					Iterator it = selectLabCodeMap.keySet().iterator();
					int i=0;
					double totalCost = 0;
					while(it.hasNext())
					{
						String matCode = (String)it.next();
						LabourGrid tmpLst = (LabourGrid)selectLabCodeMap.get(matCode);
						
						String qty = "";
						if(tmpLst.getItemQty()!=null) 
							qty = tmpLst.getItemQty().toString();
						if(txtSelLabQty!=null && txtSelLabQty.length>0)
							qty = txtSelLabQty[i];
						if(qty!=null && qty.length()>0)
							tmpLst.setItemQty(new BigDecimal(qty));
						
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
						
						String cusQty = "";
						if(txtCusQty!=null && txtCusQty.length>0)
							cusQty = txtCusQty[i];
						
						String hrs = "";
						if(txtSelLabHrs!=null && txtSelLabHrs.length>0)
							hrs = txtSelLabHrs[i];
						if(hrs!=null && hrs.length()>0)
							tmpLst.setLabourHours(new BigDecimal(hrs));
						
						totalLabCost = nf.format(totalCost);
						
						selectLabCodeMap.put(matCode,tmpLst);
						//selectMatList.add(tmpLst);
						i++;
					}
					//selectMatList = selMatTmp;
				}
			
				
				if(chkLabCode!=null)//adding labour codes from new list
				{
					sessionMap.put("IsMaterialsAdd", "Y");
					if(selectLabCodeMap==null)
						selectLabCodeMap = new LinkedHashMap();
					double labRate = Double.parseDouble(labourRate);
					for(int i=0;i<chkLabCode.length;i++)
					{
						StringTokenizer st = new StringTokenizer(chkLabCode[i],"###");
						LabourGrid lg = new LabourGrid();
						
						String index = st.nextToken();
						String labCode = st.nextToken();
						String ul  = st.nextToken();
						String up  = st.nextToken();
						String labName  = st.nextToken();
						
						lg.setDescription(labName);
						lg.setUnitPrice(new BigDecimal(Double.toString(nf.parse(up).doubleValue())));
						lg.setCebUnitPrice(new BigDecimal(labRate));
						lg.setUnitLabourHrs(new BigDecimal(ul));
						lg.setLabourCode(labCode);
						
						//selectMatList.add(tmpAL);
						selectLabCodeMap.put(labCode,lg);
					}
				}
				System.out.println("adding labour codes 2 ###"+sessionMap.get("SelectLabourList"));
				sessionMap.put("SelectLabourList",selectLabCodeMap);
				System.out.println("adding labour codes 3 ###"+sessionMap.get("SelectLabourList"));
				retVal = "select";
			}
			else if(isRemoveLabClicked!=null && isRemoveLabClicked.equals("true"))
			{
				/*System.out.println("remove labour 1 ###"+sessionMap.get("SelectLabourList"));
				double newTotLabCost = 0;
				selectLabCodeMap = (LinkedHashMap)sessionMap.get("SelectLabourList");
				System.out.println("selectLabCodeMap 1 "+selectLabCodeMap);
				if(selectLabCodeMap!=null && selectLabCodeMap.size()>0)
				{
					Iterator it = selectLabCodeMap.keySet().iterator();
					int i=0;
					double totalCost = 0;
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
							tmpLst.setLabourHours(new BigDecimal(hrs));
						
						String cost = "";
						if(tmpLst.getCebLabourCost()!=null) 
							cost = tmpLst.getCebLabourCost().toString();
						if(txtSelLabCost!=null && txtSelLabCost.length>0)
							cost = txtSelLabCost[i];
						if(cost!=null && cost.length()>0)
						{
							tmpLst.setCebLabourCost(new BigDecimal(nf.parse(cost).doubleValue()));
							totalCost = totalCost+nf.parse(cost).doubleValue();
						}
						totalLabCost = nf.format(totalCost);
						
						selectLabCodeMap.put(matCode,tmpLst);
						
						i++;
					}
					//selectMatList = selMatTmp;
				}
				System.out.println("selectLabCodeMap 2 "+selectLabCodeMap);
				double delCost = 0;
				if(chkSelLabCode!=null)//removing materials 
				{
					ArrayList<SpestlabPK> removeList = null;
					String isNewEst = (String)sessionMap.get("IsNewEstimate");
					ArrayList savedMat = (ArrayList)sessionMap.get("SavedLabourList");
					PcestdttEjb pcestdttEjb =  new PcestdttEjb(getSessionKey("region"));
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
								delCost = delCost+delLabCost;
							}
						}
						
					}
					System.out.println("selectLabCodeMap 3 "+selectLabCodeMap);
					if(removeList!=null)
					{
						PcesthttEjb pcesthttEjb = new PcesthttEjb(getSessionKey("region"));
						Pcesthtt pcesthtt = null;
						try
						{
							pcesthtt = pcesthttEjb.findByEstimationNo(applicationNo,costCenterNo);
							double preStdCost = pcesthtt.getStdCost().doubleValue();
							double newStdCost = preStdCost - delCost;
							pcesthtt.setStdCost(new BigDecimal(newStdCost));
							
						}
						catch(Exception e1){
							e1.printStackTrace();
						}
						EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
						//estimateEjb.removeLabour(removeList,pcesthtt);
					}
				}
				LinkedHashMap selectMatCodeMap = (LinkedHashMap)sessionMap.get("SelectMaterialList");
				MaterialGrid tmpLst = (MaterialGrid)selectMatCodeMap.get("LABOUR");
				tmpLst.setEstimateQty(new BigDecimal(newTotLabCost));
				tmpLst.setEstimateCost(new BigDecimal(newTotLabCost));
				System.out.println("selectLabCodeMap 3 "+selectLabCodeMap);
				sessionMap.put("SelectLabourList",selectLabCodeMap);
				sessionMap.put("SelectMaterialList",selectMatCodeMap);
				selectLabList = selectLabCodeMap.values();
				retVal = "select";
				isRemoveFinish = "true";*/
				
			}
			else//display default labour or saved labour
			{
				
				String isNewEst = (String)sessionMap.get("IsNewEstimate");
				String isDefLabLoad = (String)sessionMap.get("IsDefLabLoad");
				if(isDefLabLoad==null && (isNewEst==null || isNewEst.equals("Y")))
				{
					isDefLabLoad = "N";
					//addDefLabour();
					sessionMap.put("IsDefLabLoad",isDefLabLoad);
				}
				
				selectLabCodeMap = (LinkedHashMap)sessionMap.get("SelectLabourList");
				if(selectLabCodeMap!=null && selectLabCodeMap.size()>0)
				{
					Iterator it = selectLabCodeMap.keySet().iterator();
					int i=0;
					double totalCost = 0;
					
					labourCodeList = "";
					labourDescList = "";
					labourUomList = "";
					labourPriceList = "";
					labourCostList = "";
					labourQtyList = "";
					
					nf.setGroupingUsed(true);
					nf.setMaximumFractionDigits(2);
					nf.setMinimumFractionDigits(2);
					
					while(it.hasNext())
					{
						String labCode = (String)it.next();
						LabourGrid tmpLst = (LabourGrid)selectLabCodeMap.get(labCode);
						
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
							tmpLst.setLabourHours(new BigDecimal(hrs));
						
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
						
						
						
						labourCodeList = labourCodeList+"###"+labCode;
						labourDescList = labourDescList+"###"+tmpLst.getDescription();
						labourUomList = labourUomList+"###HRS.";
						labourPriceList = labourPriceList+"###"+nf.format(Double.parseDouble(labourRate));
						//System.out.println("def mat cost :"+cost);
						if(cost!=null && cost.trim().length()>0)
							labourCostList = labourCostList+"###"+nf.format(new BigDecimal(cost));
						labourQtyList = labourQtyList+"###"+nf.format(new BigDecimal(qty));
						
						
						
						selectLabCodeMap.put(labCode,tmpLst);
						i++;
					}
					totalLabCost = nf.format(totalCost);
				}
				retVal = "select";
			}
			if(selectLabCodeMap!=null && selectLabCodeMap.size()>0)
			{
				sessionMap.put("SelectLabourList", selectLabCodeMap);
				selectLabList = selectLabCodeMap.values();
			}
			else
			{
				selectLabList = null;
			}
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			retVal = ERROR;
		}
		return retVal;
	}
	
	private void addDefLabour()
	{
		costCenterNo = (String)sessionMap.get("costCenterNo");
		EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
		
		displayPoleLabour();
		displayServiceWireLabour();
		displayConductorLabour();
		displayStrutLabour();
		displayStayLabour();
	}
	
	
	private void displayPoleLabour()
	{
		System.out.println("displayPolelabour ##################");
		try
		{
			selectLabCodeMap = (LinkedHashMap)sessionMap.get("SelectLabourList");
			if(selectLabCodeMap==null)
				selectLabCodeMap = new LinkedHashMap();
			
			EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
			SpsetpolEjb spsetpolEjb = new SpsetpolEjb(getSessionKey("region"));
			List<Spsetpol> poleList = spsetpolEjb.getPoleList(applicationNo, costCenterNo);
			if(poleList!=null && poleList.size()>0)
			{
				double labRate = Double.parseDouble(labourRate);
				for(int i=0;i<poleList.size();i++)
				{
					Spsetpol spsetpol = (Spsetpol)poleList.get(i);
					
					
					if(spsetpol.getId().getPointType().equalsIgnoreCase("NEW"))
					{
						String poleCode = spsetpol.getId().getMatCd().trim();
						double poleQty = spsetpol.getMatQty().doubleValue();
						int poleQtyI = (int)poleQty;
						
						List<LabourGrid> poleLab = estimateEjb.getDefaultPoleLabour(costCenterNo,"NC", poleCode, poleQtyI);
						if(poleLab!=null)
						{
							for (int k=0;k<poleLab.size();k++)
							{
								LabourGrid lg = poleLab.get(k);
								double cebLabCost = lg.getUnitLabourHrs().doubleValue()*lg.getItemQty().doubleValue()*labRate;
								System.out.println("cebLabCost "+cebLabCost);
								lg.setCebLabourCost(new BigDecimal(Double.toString(cebLabCost)));
								lg.setCebUnitPrice(new BigDecimal(Double.toString(labRate)));
								
								if(selectLabCodeMap.get(lg.getLabourCode())!=null)
								{
									LabourGrid tmpLg = (LabourGrid)selectLabCodeMap.get(lg.getLabourCode());
									double newLabCost = tmpLg.getLabourCost().doubleValue()+lg.getLabourCost().doubleValue();
									double newQty = tmpLg.getItemQty().doubleValue()+lg.getItemQty().doubleValue();
									
									lg.setCebLabourCost(new BigDecimal(Double.toString(newLabCost)));
									lg.setItemQty(new BigDecimal(Double.toString(newQty)));
								}
								System.out.println("labour grid "+lg);
								selectLabCodeMap.put(lg.getLabourCode(),lg);
							}
						}
						if(distanceToServicePlace!=null && distanceToServicePlace.trim().length()>0)
						{
							double distance = Double.parseDouble(distanceToServicePlace);
							//double distance = 5;
							List<LabourGrid> poleTransLab = estimateEjb.getDefaultPoleTransportLabour(costCenterNo,"NC", poleCode, poleQtyI,distance);
							if(poleTransLab!=null)
							{
								for (int k=0;k<poleTransLab.size();k++)
								{
									LabourGrid lg = poleTransLab.get(k);
									lg.setCebLabourCost(new BigDecimal(Double.toString(lg.getUnitLabourHrs().doubleValue()*lg.getItemQty().doubleValue()*labRate)));
									lg.setCebUnitPrice(new BigDecimal(Double.toString(labRate)));
									if(selectLabCodeMap.get(lg.getLabourCode())!=null)
									{
										LabourGrid tmpLg = (LabourGrid)selectLabCodeMap.get(lg.getLabourCode());
										double newLabCost = tmpLg.getLabourCost().doubleValue()+lg.getLabourCost().doubleValue();
										double newQty = tmpLg.getItemQty().doubleValue()+lg.getItemQty().doubleValue();
										
										lg.setCebLabourCost(new BigDecimal(Double.toString(newLabCost)));
										lg.setItemQty(new BigDecimal(Double.toString(newQty)));
									}
									selectLabCodeMap.put(lg.getLabourCode(),lg);
								}
							}
						}
						
					}	
				}
				
			}
				
			
			sessionMap.put("SelectLabourList",selectLabCodeMap);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	private void displayServiceWireLabour()
	{
		System.out.println("displayServiceWireLabour ####################");
		try
		{
			selectLabCodeMap = (LinkedHashMap)sessionMap.get("SelectLabourList");
			if(selectLabCodeMap==null)
				selectLabCodeMap = new LinkedHashMap();
			int noOfSpan = 0;
			if(spans!=null && spans.trim().length()>0)
				noOfSpan = Integer.parseInt(spans);
			
			
			System.out.println("noOfSpan "+noOfSpan);
			double labRate = Double.parseDouble(labourRate);
			if(noOfSpan>1)
			{
				//last span
				EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
				
				List<LabourGrid> srvcLab1 = estimateEjb.getDefaultServiceWireLabour(costCenterNo,
						"NC", phaseDb, "LAST",Integer.toString(1));
				
				for(int i=0;i<srvcLab1.size();i++)
				{
					LabourGrid lg = (LabourGrid)srvcLab1.get(i);
					lg.setCebLabourCost(new BigDecimal(Double.toString(lg.getUnitLabourHrs().doubleValue()*lg.getItemQty().doubleValue()*labRate)));
					lg.setCebUnitPrice(new BigDecimal(Double.toString(labRate)));
					selectLabCodeMap.put(lg.getLabourCode(),lg);
				}
				
				//additional span
				List<LabourGrid> srvcLab2 = estimateEjb.getDefaultServiceWireLabour(costCenterNo,
						"NC", phaseDb, "OTHER",Integer.toString(noOfSpan-1));
				
				for(int i=0;i<srvcLab2.size();i++)
				{
					LabourGrid lg = (LabourGrid)srvcLab2.get(i);
					lg.setCebLabourCost(new BigDecimal(Double.toString(lg.getUnitLabourHrs().doubleValue()*lg.getItemQty().doubleValue()*labRate)));
					lg.setCebUnitPrice(new BigDecimal(Double.toString(labRate)));
					selectLabCodeMap.put(lg.getLabourCode(),lg);
				}
			}
			else if(noOfSpan==1)
			{
				//last span
				EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
				
				List<LabourGrid> srvcLab1 = estimateEjb.getDefaultServiceWireLabour(costCenterNo,
						"NC", phaseDb, "LAST",Integer.toString(1));
				
				for(int i=0;i<srvcLab1.size();i++)
				{
					LabourGrid lg = (LabourGrid)srvcLab1.get(i);
					lg.setCebLabourCost(new BigDecimal(Double.toString(lg.getUnitLabourHrs().doubleValue()*lg.getItemQty().doubleValue()*labRate)));
					lg.setCebUnitPrice(new BigDecimal(Double.toString(labRate)));
					selectLabCodeMap.put(lg.getLabourCode(),lg);
				}
			}
			sessionMap.put("SelectLabourList",selectLabCodeMap);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void displayConductorLabour()
	{
		System.out.println("displayConductorLabour #####################");
		try
		{
			selectLabCodeMap = (LinkedHashMap)sessionMap.get("SelectLabourList");
			if(selectLabCodeMap==null)
				selectLabCodeMap = new LinkedHashMap();
			
			if(conductorLength!=null && conductorLength.trim().length()>0)
			{
				double labRate = Double.parseDouble(labourRate);
				EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
				List<LabourGrid> cndtLab = estimateEjb.getDefaultConductorLabour(costCenterNo,
						"NC", phaseDb, conductorType,
						conductorLength);
				
				double totalCost = 0;
				for(int i=0;i<cndtLab.size();i++)
				{
					LabourGrid lg = (LabourGrid)cndtLab.get(i);
					lg.setCebLabourCost(new BigDecimal(Double.toString(lg.getUnitLabourHrs().doubleValue()*lg.getItemQty().doubleValue()*labRate)));
					lg.setCebUnitPrice(new BigDecimal(Double.toString(labRate)));
					selectLabCodeMap.put(lg.getLabourCode(),lg);
				}
				
			}
			sessionMap.put("SelectLabourList",selectLabCodeMap);
			
		}
		catch(Exception e)
		{
			//e.printStackTrace();
		}
		
	}
	
	
	private void displayStayLabour()
	{
		System.out.println("displayStayLabour ########################");
		try
		{
			try
			{
			selectLabCodeMap = (LinkedHashMap)sessionMap.get("SelectLabourList");
			if(selectLabCodeMap==null)
				selectLabCodeMap = new LinkedHashMap();
			
			SpsetstyEjb spsetstyEjb = new SpsetstyEjb(getSessionKey("region"));
			List<Spsetsty> staytList = spsetstyEjb.getStayList(applicationNo,  costCenterNo);
			
			if(staytList!=null && staytList.size()>0)
			{
				double labRate = Double.parseDouble(labourRate);
				for(int i=0;i<staytList.size();i++)
				{
					Spsetsty spsetsty = staytList.get(i);
					String stayType = spsetsty.getId().getStayType();
					int matQty = spsetsty.getMatQty().intValue();
					
					EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
					List stayLabList = estimateEjb.getDefaultStayLabour(costCenterNo, "NC", stayType, matQty);
				
					if(stayLabList!=null)
					{
						for (int k=0;k<stayLabList.size();k++)
						{
							LabourGrid lg = (LabourGrid)stayLabList.get(k);
							lg.setCebLabourCost(new BigDecimal(Double.toString(lg.getUnitLabourHrs().doubleValue()*lg.getItemQty().doubleValue()*labRate)));
							lg.setCebUnitPrice(new BigDecimal(Double.toString(labRate)));
							if(selectLabCodeMap.get(lg.getLabourCode())!=null)
							{
								LabourGrid tmpLg = (LabourGrid)selectLabCodeMap.get(lg.getLabourCode());
								double newLabCost = tmpLg.getLabourCost().doubleValue()+lg.getLabourCost().doubleValue();
								double newQty = tmpLg.getItemQty().doubleValue()+lg.getItemQty().doubleValue();
								
								lg.setCebLabourCost(new BigDecimal(Double.toString(newLabCost)));
								lg.setItemQty(new BigDecimal(Double.toString(newQty)));
							}
							selectLabCodeMap.put(lg.getLabourCode(),lg);
						}
					}
				}					
				sessionMap.put("SelectLabourList",selectLabCodeMap);
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
	
	
	private void displayStrutLabour()
	{
		System.out.println("displayStrutlabour #########################"+strutsNo);
		try
		{
			selectLabCodeMap = (LinkedHashMap)sessionMap.get("SelectLabourList");
			if(selectLabCodeMap==null)
				selectLabCodeMap = new LinkedHashMap();
			
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
					//List strutLabList = estimateEjb.getDefaultStrutLabour("514.20", "NC", "A0203", 2);
					List strutLabList = estimateEjb.getDefaultStrutLabour(costCenterNo, "NC", matCode.trim(), matQty);
					
					if(strutLabList!=null)
					{
						for (int k=0;k<strutLabList.size();k++)
						{
							LabourGrid lg = (LabourGrid)strutLabList.get(k);
							lg.setCebLabourCost(new BigDecimal(Double.toString(lg.getUnitLabourHrs().doubleValue()*lg.getItemQty().doubleValue()*labRate)));
							lg.setCebUnitPrice(new BigDecimal(Double.toString(labRate)));
							selectLabCodeMap.put(lg.getLabourCode(),lg);
						}
					}
				}					
				sessionMap.put("SelectLabourList",selectLabCodeMap);
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	public String removeLabour()
	{
		try
		{
			double newTotLabCost = 0;
			selectLabCodeMap = (LinkedHashMap)sessionMap.get("SelectLabourList");
			if(selectLabCodeMap!=null && selectLabCodeMap.size()>0)
			{
				Iterator it = selectLabCodeMap.keySet().iterator();
				int i=0;
				double totalCost = 0;
				while(it.hasNext())
				{
					String matCode = (String)it.next();
					Spestlab tmpLst = (Spestlab)selectLabCodeMap.get(matCode);
					
					//String uom  = tmpLst.getUom();
					String up  = tmpLst.getUnitPrice().toString();
					String labName  = tmpLst.getActivityDescription();
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
						tmpLst.setLabourHours(new BigDecimal(hrs));
					
					String cost = "";
					if(tmpLst.getLabourCost()!=null) 
						cost = tmpLst.getLabourCost().toString();
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
			double delCost = 0;
			if(chkSelLabCode!=null)//adding materials from new list
			{
				ArrayList<SpestlabPK> removeList = null;
				String isNewEst = (String)sessionMap.get("IsNewEstimate");
				ArrayList savedMat = (ArrayList)sessionMap.get("SavedLabourList");
				PcestdttEjb pcestdttEjb =  new PcestdttEjb(getSessionKey("region"));
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
							delCost = delCost+delLabCost;
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
						double preStdCost = pcesthtt.getStdCost().doubleValue();
						double newStdCost = preStdCost - delCost;
						pcesthtt.setStdCost(new BigDecimal(newStdCost));
						
					}
					catch(Exception e1){
						e1.printStackTrace();
					}
					EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
					//estimateEjb.removeLabour(removeList,pcesthtt);
				}
			}
			LinkedHashMap selectMatCodeMap = (LinkedHashMap)sessionMap.get("SelectMaterialList");
			MaterialGrid tmpLst = (MaterialGrid)selectMatCodeMap.get("LABOUR");
			tmpLst.setEstimateQty(new BigDecimal(newTotLabCost));
			tmpLst.setEstimateCost(new BigDecimal(newTotLabCost));
			
			sessionMap.put("SelectLabourList",selectLabCodeMap);
			sessionMap.put("SelectMaterialList",selectMatCodeMap);
			selectLabList = selectLabCodeMap.values();
			isRemoveFinish = "true";
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	public String addLabourCosts()
	{
		System.out.println("addLabourCosts ######################");
		try
		{
			nf.setGroupingUsed(true);
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			
			selectLabCodeMap = (LinkedHashMap)sessionMap.get("SelectLabourList");
			if(selectLabCodeMap!=null && selectLabCodeMap.size()>0)
			{
				sessionMap.put("IsDefaultMasterAdd", "Y");
				Iterator it = selectLabCodeMap.keySet().iterator();
				int i=0;
				double totalCost = 0;
				while(it.hasNext())
				{
					String matCode = (String)it.next();
					Spestlab tmpLst = (Spestlab)selectLabCodeMap.get(matCode);
					
					String qty = "";
					if(tmpLst.getItemQty()!=null) 
						qty = tmpLst.getItemQty().toString();
					if(txtSelLabQty!=null && txtSelLabQty.length>0)
						qty = txtSelLabQty[i];
					if(qty!=null && qty.length()>0)
						tmpLst.setItemQty(new BigDecimal(qty));
					
					String cost = "";
					if(tmpLst.getLabourCost()!=null) 
						cost = tmpLst.getLabourCost().toString();
					if(txtSelLabCost!=null && txtSelLabCost.length>0)
						cost = txtSelLabCost[i];
					if(cost!=null && cost.length()>0)
					{
						tmpLst.setCebLabourCost(new BigDecimal(nf.parse(cost).doubleValue()));
						totalCost = totalCost+nf.parse(cost).doubleValue();
					}
					
					String cusQty = "";
					if(txtCusQty!=null && txtCusQty.length>0)
						cusQty = txtCusQty[i];
					
					String hrs = "";
					if(tmpLst.getLabourHours()!=null) 
						hrs = tmpLst.getLabourHours().toString();
					if(txtSelLabHrs!=null && txtSelLabHrs.length>0)
						hrs = txtSelLabHrs[i];
					if(hrs!=null && hrs.length()>0)
						tmpLst.setLabourHours(new BigDecimal(hrs));
					
					totalLabCost = nf.format(totalCost);
					
					selectLabCodeMap.put(matCode,tmpLst);
					
					i++;
				}
				selectLabList = selectLabCodeMap.values();
			}
			
			if(chkLabCode!=null)//adding materials from new list
			{
				sessionMap.put("IsMaterialsAdd", "Y");
				if(selectLabCodeMap==null)
					selectLabCodeMap = new LinkedHashMap();
				for(int i=0;i<chkLabCode.length;i++)
				{
					StringTokenizer st = new StringTokenizer(chkLabCode[i],"###");
					Spestlab spestlab = new Spestlab();
					SpestlabPK spestlabPK = new SpestlabPK();
					String index = st.nextToken();
					String labCode = st.nextToken();
					String up  = st.nextToken();
					String labName  = st.nextToken();
					
					spestlab.setActivityDescription(labName);
					spestlab.setUnitPrice(new BigDecimal(nf.parse(up).doubleValue()));
					spestlabPK.setLabourCode(labCode);
					spestlab.setId(spestlabPK);
					//selectMatList.add(tmpAL);
					selectLabCodeMap.put(labCode,spestlab);
				}
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	
	
	public ArrayList<ArrayList<String>> getMatList() {
		return matList;
	}

	public void setMatList(ArrayList<ArrayList<String>> matList) {
		this.matList = matList;
	}

	

	public Collection getSelectLabList() {
		return selectLabList;
	}





	public void setSelectLabList(Collection selectLabList) {
		this.selectLabList = selectLabList;
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

	public Map<String, Object> getSession() {
		return sessionMap;
	}
	
	public String getTotalLabCost() {
		return totalLabCost;
	}





	public void setTotalLabCost(String totalLabCost) {
		this.totalLabCost = totalLabCost;
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






	public List<LabourGrid> getLabourList() {
		return labourList;
	}





	public void setLabourList(List<LabourGrid> labourList) {
		this.labourList = labourList;
	}





	public String getIsAddLabClicked() {
		return isAddLabClicked;
	}





	public void setIsAddLabClicked(String isAddLabClicked) {
		this.isAddLabClicked = isAddLabClicked;
	}





	public String[] getChkLabCode() {
		return chkLabCode;
	}





	public void setChkLabCode(String[] chkLabCode) {
		this.chkLabCode = chkLabCode;
	}





	public LinkedHashMap getSelectLabCodeMap() {
		return selectLabCodeMap;
	}





	public void setSelectLabCodeMap(LinkedHashMap selectLabCodeMap) {
		this.selectLabCodeMap = selectLabCodeMap;
	}





	public String getIsShowLabLinkClicked() {
		return isShowLabLinkClicked;
	}





	public void setIsShowLabLinkClicked(String isShowLabLinkClicked) {
		this.isShowLabLinkClicked = isShowLabLinkClicked;
	}





	public String getIsRemoveLabClicked() {
		return isRemoveLabClicked;
	}





	public void setIsRemoveLabClicked(String isRemoveLabClicked) {
		this.isRemoveLabClicked = isRemoveLabClicked;
	}





	public String[] getChkSelLabCode() {
		return chkSelLabCode;
	}





	public void setChkSelLabCode(String[] chkSelLabCode) {
		this.chkSelLabCode = chkSelLabCode;
	}





	public String getIsRemoveFinish() {
		return isRemoveFinish;
	}
	
	public String getSessionKey(String key) 
	{
       return getSession().get(key).toString();
	}





	public void setIsRemoveFinish(String isRemoveFinish) {
		this.isRemoveFinish = isRemoveFinish;
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





	public String[] getTxtSelLabHrs() {
		return txtSelLabHrs;
	}





	public void setTxtSelLabHrs(String[] txtSelLabHrs) {
		this.txtSelLabHrs = txtSelLabHrs;
	}





	public String getLabourCodeList() {
		return labourCodeList;
	}





	public void setLabourCodeList(String labourCodeList) {
		this.labourCodeList = labourCodeList;
	}





	public String getLabourDescList() {
		return labourDescList;
	}





	public void setLabourDescList(String labourDescList) {
		this.labourDescList = labourDescList;
	}





	public String getLabourUomList() {
		return labourUomList;
	}





	public void setLabourUomList(String labourUomList) {
		this.labourUomList = labourUomList;
	}





	public String getLabourPriceList() {
		return labourPriceList;
	}





	public void setLabourPriceList(String labourPriceList) {
		this.labourPriceList = labourPriceList;
	}





	public String getLabourQtyList() {
		return labourQtyList;
	}





	public void setLabourQtyList(String labourQtyList) {
		this.labourQtyList = labourQtyList;
	}





	public String getLabourCostList() {
		return labourCostList;
	}





	public void setLabourCostList(String labourCostList) {
		this.labourCostList = labourCostList;
	}


	

	
}
