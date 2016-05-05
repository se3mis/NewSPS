package estimatetc.web;

import java.util.ArrayList;
//import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Collection;
import java.util.StringTokenizer;
import java.math.BigDecimal;
import java.text.NumberFormat;

//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

//import estimate.dao.PcesthttDao;
import estimate.dao.PcesthttDao;
import estimate.model.MaterialGrid;
import estimate.model.Pcestdtt;
import estimate.model.PcestdttPK;
import estimate.model.Pcesthtt;
import estimate.model.Speststd;
import estimate.model.SpeststdPK;
import estimate.model.Spratesm;
import estimate.model.SpratesmPK;
import estimate.service.EstimateEjb;
import estimate.service.PcestdttEjb;
import estimate.service.PcesthttEjb;
import estimate.service.SpeststdEjb;

/**
 * @author Dell
 *
 */
public class MaterialActionTC extends ActionSupport  implements SessionAware, ParameterAware,ServletResponseAware,ServletRequestAware{

	private ArrayList<ArrayList<String>> matList;
	//private List matList;
	private Collection selectMatList;
	private Collection afterRemoveMatList;
	
	NumberFormat nf = NumberFormat.getInstance();
	
	private String lineLength;
	private String phaseDb;
	private String connectionTypeDb;
	private String wiringType;
	private String distanceToServicePlace;
	private String tcType;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private Map<String, Object> sessionMap;
	
	


	private String costCenterNo;
	private String applicationNo;
	
	private String[] chkMatCode;
	private String[] chkNplMatCode;
	private String totalDetailCost;
	private ArrayList otherCostList;
	private ArrayList nplMatList;
	private String nonMatCodeList;
	private String nonMatCostList;
	private String totalMatCost;
	
	private String[] chkSelMatCode;
	private String[] txtSelQty;
	private String[] txtSelMatCost;
	private LinkedHashMap selectMatCodeMap;
	private String[] txtDamageQty;
	private String[] txtDamageCost;
	private String totalLabCost;
	private String totalLabHrs;
	private String totalDamageCost;
	
	
	private String[] chkResType;
	private String[] txtOtherCost;
	
	private String isUndoReject;
	
	private String isMatClicked;
	private String isOthClicked;
	private String isNplClicked;
	private String isAddMatClicked;
	
	private String fixedCost;
	private String damageCost;
	private String totalCost;
	private String subTotal;
	
	double labCost = 0;
	double labHrs = 0;
	
	private String labourRate;
	private String transportRate;
	private String overheadRate;
	//private String tcType;
	
	private String itemCodeList;
	private String itemDescList;
	private String uomList;
	private String priceList;
	private String qtyList;
	private String costList;
	
	public String execute() throws Exception 
	{
		return SUCCESS;
	}
	
	

	private void addDefMaterials()
	{
		costCenterNo = (String)sessionMap.get("costCenterNo");
		//EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
		//selectMatCodeMap = estimateEjb.getDefaultMaterialGrid(costCenterNo,new Long(phaseDb).longValue(),new Long(connectionTypeDb).longValue(),wiringType);
 	
		selectMatCodeMap = new LinkedHashMap();
		
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
		
		/*double tCost = new BigDecimal(transportRate).doubleValue()*new BigDecimal(distanceToServicePlace).doubleValue();
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
		EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
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
					System.out.println();
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
		System.out.println("add mats 0");
		try
		{
			//select and add materials
			if(isAddMatClicked!=null && isAddMatClicked.equals("true"))
			{
				selectMatCodeMap = (LinkedHashMap)sessionMap.get("SelectMaterialList");
				System.out.println("@@@ click on add to list ");
				//selectMatList = new ArrayList();
				if(selectMatCodeMap!=null && selectMatCodeMap.size()>0)
				{
					System.out.println("add mats 4");
					Iterator it = selectMatCodeMap.keySet().iterator();
					int i=0;
					double tCost = 0;
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
						String dmgQty = "";
						if(tmpLst.getCustomerQty()!=null) 
							dmgQty = tmpLst.getCustomerQty().toString();
						if(txtSelQty!=null && txtSelQty.length>0)
							qty = txtSelQty[i];
						if(txtSelMatCost!=null && txtSelMatCost.length>0)
							cost = txtSelMatCost[i];
						if(txtDamageQty!=null && txtDamageQty.length>0)
							dmgQty = txtDamageQty[i];
						if(qty!=null && qty.length()>0)
							tmpLst.setEstimateQty(new BigDecimal(qty));
						if(dmgQty!=null && dmgQty.length()>0)
							tmpLst.setDamageQty(new BigDecimal(dmgQty));
						if(cost!=null && cost.length()>0)
						{
							tmpLst.setEstimateCost(new BigDecimal(nf.parse(cost).doubleValue()));
							tCost = tCost+nf.parse(cost).doubleValue();
						}
						
						String dmgCost = "";
						if(tmpLst.getDamageCost()!=null) 
							dmgCost = tmpLst.getDamageCost().toString();
						if(txtDamageCost!=null && txtDamageCost.length>0)
							dmgCost = txtDamageCost[i];
						if(dmgCost!=null && dmgCost.length()>0)
						{
							tmpLst.setDamageCost(new BigDecimal(nf.parse(dmgCost).doubleValue()));
						}
						totalDetailCost = nf.format(tCost);
						
						selectMatCodeMap.put(matCode,tmpLst);
						//selectMatList.add(tmpLst);
						i++;
					}
					//selectMatList = selMatTmp;
				}
			
				
				if(chkMatCode!=null)//adding materials from new list
				{
					sessionMap.put("IsMaterialsAdd", "Y");
					System.out.println("add mats 5");
					if(selectMatCodeMap==null)
						selectMatCodeMap = new LinkedHashMap();
					for(int i=0;i<chkMatCode.length;i++)
					{
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

			}
			else
			{
				System.out.println("@@@ no action 0 ");
				String isNewEst = (String)sessionMap.get("IsNewEstimate");
				String isMatAdd = (String)sessionMap.get("IsMaterialsAdd");
				if(isMatAdd==null && (isNewEst==null || isNewEst.equals("Y")))
				{
					System.out.println("@@@ new estimate and display defaults ");
					if(lineLength!=null && lineLength.trim().length()>0 && wiringType!=null && wiringType.length()>0)
					{
						System.out.println("add mats 2");
						addDefMaterials();
					}
				}
				else
				{
					System.out.println("@@@ no action 1");
					selectMatCodeMap = (LinkedHashMap)sessionMap.get("SelectMaterialList");
					
					if(selectMatCodeMap!=null && selectMatCodeMap.size()>0)
					{
						System.out.println("add mats 4");
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
							String dmgQty = "";
							if(tmpLst.getCustomerQty()!=null) 
								dmgQty = tmpLst.getCustomerQty().toString();
							if(txtSelQty!=null && txtSelQty.length>0)
								qty = txtSelQty[i];
							if(txtSelMatCost!=null && txtSelMatCost.length>0)
								cost = txtSelMatCost[i];
							if(txtDamageQty!=null && txtDamageQty.length>0)
								dmgQty = txtDamageQty[i];
							if(qty!=null && qty.length()>0)
								tmpLst.setEstimateQty(new BigDecimal(qty));
							if(dmgQty!=null && dmgQty.length()>0)
								tmpLst.setDamageQty(new BigDecimal(dmgQty));
							if(cost!=null && cost.length()>0)
							{
								tmpLst.setEstimateCost(new BigDecimal(nf.parse(cost).doubleValue()));
								totalCost = totalCost+nf.parse(cost).doubleValue();
							}
							
							String dmgCost = "";
							if(tmpLst.getDamageCost()!=null) 
								dmgCost = tmpLst.getDamageCost().toString();
							if(txtDamageCost!=null && txtDamageCost.length>0)
								dmgCost = txtDamageCost[i];
							if(dmgCost!=null && dmgCost.length()>0)
							{
								tmpLst.setDamageCost(new BigDecimal(nf.parse(dmgCost).doubleValue()));
							}
							
							if(tmpLst.getResType().equals("MAT-COST"))
							{
								itemCodeList = itemCodeList+"###"+matCode;
								itemDescList = itemDescList+"###"+tmpLst.getMatNm();
								uomList = uomList+"###"+tmpLst.getUom();
								priceList = priceList+"###"+nf.format(tmpLst.getUnitPrice());
								//System.out.println("def mat cost :"+cost);
								if(cost!=null && cost.trim().length()>0)
									costList = costList+"###"+nf.format(new BigDecimal(cost));
								qtyList = qtyList+"###"+nf.format(new BigDecimal(qty));
								matCostTotal = matCostTotal+nf.parse(cost).doubleValue();
							}
							else
							{
								nonMatCodeList = nonMatCodeList+"###"+tmpLst.getMatNm();
								if(cost!=null && cost.trim().length()>0)
									nonMatCostList = nonMatCostList+"###"+nf.format(new BigDecimal(cost));
							}
							//totalDetailCost = nf.format(totalCost);
							
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
				System.out.println("add mats 6");
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
	
	public String removeMaterials()
	{
		try
		{
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
					
					String dmgQty = "";
					if(tmpLst.getCustomerQty()!=null) 
						dmgQty = tmpLst.getCustomerQty().toString();
					if(txtDamageQty!=null && txtDamageQty.length>0)
						dmgQty = txtDamageQty[i];
					if(dmgQty!=null && dmgQty.length()>0)
						tmpLst.setDamageQty(new BigDecimal(dmgQty));
					
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
					
					String dmgCost = "";
					if(tmpLst.getDamageCost()!=null) 
						dmgCost = tmpLst.getDamageCost().toString();
					if(txtDamageCost!=null && txtDamageCost.length>0)
						dmgCost = txtDamageCost[i];
					if(dmgCost!=null && dmgCost.length()>0)
					{
						tmpLst.setDamageCost(new BigDecimal(nf.parse(dmgCost).doubleValue()));
					}
					totalDetailCost = nf.format(totalCost);
					
					selectMatCodeMap.put(matCode,tmpLst);
					
					i++;
				}
				//selectMatList = selMatTmp;
			}
			double dbDelMatCost = 0;
			double dbDelDmgCost = 0;
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
					
					String tmpDmgCost = txtDamageCost[Integer.parseInt(indexStr)];
					double delDmgCost = 0;
					if(tmpDmgCost!=null && tmpDmgCost.trim().length()>0)
						delDmgCost = nf.parse(tmpDmgCost).doubleValue();
					
					double matCost = nf.parse(totalDetailCost).doubleValue();
					newTotMatCost = matCost-delMatCost;
					totalDetailCost = nf.format(newTotMatCost);
					System.out.println("del mat ### "+matCode);
					if(isNewEst!=null && isNewEst.equals("N"))
					{
						if(savedMat.contains(matCode))
						{
							System.out.println("delete from db ### "+matCode);
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
							dbDelDmgCost = dbDelDmgCost+delDmgCost;
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
										
					SpeststdPK speststdPK = new SpeststdPK();
					speststdPK.setDeptId(costCenterNo);
					speststdPK.setEstimateNo(applicationNo);
					SpeststdEjb speststdEJB = new SpeststdEjb(getSessionKey("region"));
					Speststd speststd = speststdEJB.findById(speststdPK);
					
					double savedTotalCost = speststd.getTotalCost().doubleValue();
					double savedDmgCost = speststd.getDamageCost().doubleValue();
						
					double newDmgCost = savedDmgCost - dbDelDmgCost;
					double newTotCost = savedTotalCost -  savedDmgCost + newDmgCost;
					//double contCost = totalForCont*0.05;
					//double newTotCost = totalForCont + contCost;
					
					speststd.setTotalCost(new BigDecimal(Double.toString(newTotCost)));
					speststd.setDamageCost(new BigDecimal(Double.toString(newDmgCost)));
										
					EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
					estimateEjb.removeMaterials(removeList,pcesthtt,speststd);
					
					
				}
			}
			
			sessionMap.put("SelectMaterialList",selectMatCodeMap);
			selectMatList = selectMatCodeMap.values();
			//double fc = newTotMatCost*0.2;
			//fixedCost = nf.format(fc);
			//fixedCost = "100.00";
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return "remove";
	}
	
	public String removeCarnivalMaterials()
	{
		try
		{
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
					
					String dmgQty = "";
					if(tmpLst.getCustomerQty()!=null) 
						dmgQty = tmpLst.getCustomerQty().toString();
					if(txtDamageQty!=null && txtDamageQty.length>0)
						dmgQty = txtDamageQty[i];
					if(dmgQty!=null && dmgQty.length()>0)
						tmpLst.setDamageQty(new BigDecimal(dmgQty));
					
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
					
					String dmgCost = "";
					if(tmpLst.getDamageCost()!=null) 
						dmgCost = tmpLst.getDamageCost().toString();
					if(txtDamageCost!=null && txtDamageCost.length>0)
						dmgCost = txtDamageCost[i];
					if(dmgCost!=null && dmgCost.length()>0)
					{
						tmpLst.setDamageCost(new BigDecimal(nf.parse(dmgCost).doubleValue()));
					}
					totalDetailCost = nf.format(totalCost);
					
					selectMatCodeMap.put(matCode,tmpLst);
					
					i++;
				}
				//selectMatList = selMatTmp;
			}
			
			double dbDelMatCost = 0;
			double dbDelDmgCost = 0;
			if(chkSelMatCode!=null)//adding materials from new list
			{
				
				ArrayList removeList = null;
				String isNewEst = (String)sessionMap.get("IsNewEstimate");
				ArrayList savedMat = (ArrayList)sessionMap.get("SavedMaterialList");
				
				PcestdttEjb pcestdttEjb =  new PcestdttEjb(getSessionKey("region"));
				
				/*double newDmgCost = nf.parse(totalDamageCost).doubleValue();
				double newFixedCost = nf.parse(fixedCost).doubleValue();
				double newSubTotal = nf.parse(subTotal).doubleValue();
				double newTotalCost = nf.parse(totalCost).doubleValue();*/
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
					
					String tmpDmgCost = txtDamageCost[Integer.parseInt(indexStr)];
					double delDmgCost = 0;
					if(tmpDmgCost!=null && tmpDmgCost.trim().length()>0)
						delDmgCost = nf.parse(tmpDmgCost).doubleValue();
					
					//String tmpCost = txtSelMatCost[Integer.parseInt(indexStr)];
					//double delMatCost = 0;
					//if(tmpCost!=null && tmpCost.trim().length()>0)
					//	delMatCost = nf.parse(tmpCost).doubleValue();
					double matCost = nf.parse(totalDetailCost).doubleValue();
					newTotMatCost = matCost-delMatCost;
					totalDetailCost = nf.format(newTotMatCost);
					//newFixedCost = newFixedCost - delMatCost;
					
					//String dmgCost = txtDamageCost[Integer.parseInt(indexStr)];
					//double delDmgCost = 0;
					//if(dmgCost!=null && dmgCost.trim().length()>0)
					//	delDmgCost = nf.parse(dmgCost).doubleValue();
					//newDmgCost = newDmgCost - delDmgCost;
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
							dbDelDmgCost = dbDelDmgCost+delDmgCost;
						}
					}
					
				}
				//newFixedCost = newFixedCost*0.2;
				//newTotalCost = newTotalCost - newFixedCost - newDmgCost;
				if(removeList!=null)
				{
					PcesthttEjb pcesthttEjb = new PcesthttEjb(getSessionKey("region"));
					Pcesthtt pcesthtt = null;
					try
					{
						pcesthtt = pcesthttEjb.findByEstimationNo(applicationNo,costCenterNo);
						double newDetailCost = pcesthtt.getStdCost().doubleValue()- dbDelMatCost;
						pcesthtt.setStdCost(new BigDecimal(Double.toString(newDetailCost)));
						
						/*SpeststdPK speststdPK = new SpeststdPK();
						speststdPK.setDeptId(costCenterNo);
						speststdPK.setEstimateNo(applicationNo);
						
						SpeststdEjb speststdEJB = new SpeststdEjb(getSessionKey("region"));
						Speststd speststd = speststdEJB.findById(speststdPK);
						speststd.setDamageCost(new BigDecimal(newDmgCost));
						speststd.setFixedCost(new BigDecimal(newFixedCost));
						speststd.setTotalCost(new BigDecimal(newTotalCost));*/
					}
					catch(Exception e1){
						e1.printStackTrace();
					}
					
					SpeststdPK speststdPK = new SpeststdPK();
					speststdPK.setDeptId(costCenterNo);
					speststdPK.setEstimateNo(applicationNo);
					SpeststdEjb speststdEJB = new SpeststdEjb(getSessionKey("region"));
					Speststd speststd = speststdEJB.findById(speststdPK);
					
					double savedOtherCost = speststd.getOtherCost().doubleValue();
					double savedTotalCost = speststd.getTotalCost().doubleValue();
					double savedDmgCost = speststd.getDamageCost().doubleValue();
						
					double newOthCost = savedOtherCost - dbDelMatCost*0.2;
					double newDmgCost = savedDmgCost - dbDelDmgCost;
					double newTotCost = savedTotalCost - savedOtherCost + newOthCost - savedDmgCost + newDmgCost;
					//double contCost = totalForCont*0.05;
					//double newTotCost = totalForCont + contCost;
					
					speststd.setTotalCost(new BigDecimal(Double.toString(newTotCost)));
					speststd.setOtherCost(new BigDecimal(Double.toString(newOthCost)));
					speststd.setDamageCost(new BigDecimal(Double.toString(newDmgCost)));
					//speststd.setContingencyCost(new BigDecimal(Double.toString(contCost)));
					System.out.println("newTotCost "+newTotCost);
					System.out.println("newOthCost "+newOthCost);
					
					EstimateEjb estimateEjb = new EstimateEjb(getSessionKey("region"));
					estimateEjb.removeMaterials(removeList,pcesthtt,speststd);
					//estimateEjb.delete(removeList,pcesthtt,speststd);
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
	
	
	public String displayNPLMatCost()
	{
		System.out.println("displayNPLMatCost");
		try
		{
			System.out.println("isNplClicked "+isNplClicked);
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
		System.out.println("add npl mats");
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
							String dmgQty = "";
							if(tmpLst.getCustomerQty()!=null) 
								dmgQty = tmpLst.getCustomerQty().toString();
							if(txtSelQty!=null && txtSelQty.length>0)
								qty = txtSelQty[i];
							if(txtSelMatCost!=null && txtSelMatCost.length>0)
								cost = txtSelMatCost[i];
							if(txtDamageQty!=null && txtDamageQty.length>0)
								dmgQty = txtDamageQty[i];
							if(qty!=null && qty.length()>0)
								tmpLst.setEstimateQty(new BigDecimal(qty));
							if(dmgQty!=null && dmgQty.length()>0)
								tmpLst.setDamageQty(new BigDecimal(dmgQty));
							if(cost!=null && cost.length()>0)
							{
								tmpLst.setEstimateCost(new BigDecimal(nf.parse(cost).doubleValue()));
								totalCost = totalCost+nf.parse(cost).doubleValue();
							}
							
							String dmgCost = "";
							if(tmpLst.getDamageCost()!=null) 
								dmgCost = tmpLst.getDamageCost().toString();
							if(txtDamageCost!=null && txtDamageCost.length>0)
								dmgCost = txtDamageCost[i];
							if(dmgCost!=null && dmgCost.length()>0)
							{
								tmpLst.setDamageCost(new BigDecimal(nf.parse(dmgCost).doubleValue()));
							}
							totalDetailCost = nf.format(totalCost);
							
							selectMatCodeMap.put(matCode,tmpLst);
							selectMatList.add(tmpLst);
							i++;
						}
						//selectMatList = selMatTmp;
					}
				
					System.out.println("chkNplMatCode "+chkNplMatCode);
					if(chkNplMatCode!=null)//adding materials from new list
					{
						if(selectMatCodeMap==null)
							selectMatCodeMap = new LinkedHashMap();
						for(int i=0;i<chkNplMatCode.length;i++)
						{
							System.out.println("chkNplMatCode[i] "+chkNplMatCode[i]);
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
				System.out.println("display other cost");
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
		System.out.println("add other cost");
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
					String dmgQty = "";
					if(tmpLst.getCustomerQty()!=null) 
						dmgQty = tmpLst.getCustomerQty().toString();
					if(txtDamageQty!=null && txtDamageQty.length>0)
						dmgQty = txtDamageQty[i];
					if(dmgQty!=null && dmgQty.length()>0)
						tmpLst.setDamageQty(new BigDecimal(dmgQty));
					
					String dmgCost = "";
					if(tmpLst.getDamageCost()!=null) 
						dmgCost = tmpLst.getDamageCost().toString();
					if(txtDamageCost!=null && txtDamageCost.length>0)
						dmgCost = txtDamageCost[i];
					if(dmgCost!=null && dmgCost.length()>0)
					{
						tmpLst.setDamageCost(new BigDecimal(nf.parse(dmgCost).doubleValue()));
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
			System.out.println("%%% calLabourCost 1 ");
			selectMatCodeMap = (LinkedHashMap)sessionMap.get("SelectMaterialList");
			System.out.println("%%% calLabourCost 2 "+txtDamageQty);
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
						if(txtSelQty!=null && txtSelQty.length>0)
							qty = txtSelQty[i];
						if(qty!=null && qty.length()>0)
							tmpLst.setEstimateQty(new BigDecimal(qty));
						
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
						
						String dmgQty = "";
						if(tmpLst.getDamageQty()!=null) 
							dmgQty = tmpLst.getDamageQty().toString();
						if(txtDamageQty!=null && txtDamageQty.length>0)
							dmgQty = txtDamageQty[i];
						if(dmgQty!=null && dmgQty.length()>0)
							tmpLst.setDamageQty(new BigDecimal(dmgQty));
						
						String dmgCost = "";
						if(tmpLst.getDamageCost()!=null) 
							dmgCost = tmpLst.getDamageCost().toString();
						if(txtDamageCost!=null && txtDamageCost.length>0)
							dmgCost = txtDamageCost[i];
						if(dmgCost!=null && dmgCost.length()>0)
						{
							tmpLst.setDamageCost(new BigDecimal(nf.parse(dmgCost).doubleValue()));
						}
						System.out.println(matCode+" : "+dmgCost);
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



	public String getFixedCost() {
		return fixedCost;
	}



	public void setFixedCost(String fixedCost) {
		this.fixedCost = fixedCost;
	}
	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}



	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	public String getSessionKey(String key) 
	{
       return getSessionMap().get(key).toString();
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



	public String getDistanceToServicePlace() {
		return distanceToServicePlace;
	}



	public void setDistanceToServicePlace(String distanceToServicePlace) {
		this.distanceToServicePlace = distanceToServicePlace;
	}



	

	public String[] getTxtDamageQty() {
		return txtDamageQty;
	}



	public void setTxtDamageQty(String[] txtDamageQty) {
		this.txtDamageQty = txtDamageQty;
	}



	public String getTotalLabCost() {
		return totalLabCost;
	}



	public void setTotalLabCost(String totalLabCost) {
		this.totalLabCost = totalLabCost;
	}



	public String getTotalLabHrs() {
		return totalLabHrs;
	}



	public void setTotalLabHrs(String totalLabHrs) {
		this.totalLabHrs = totalLabHrs;
	}



	public String getTcType() {
		return tcType;
	}



	public void setTcType(String tcType) {
		this.tcType = tcType;
	}



	public String[] getTxtDamageCost() {
		return txtDamageCost;
	}



	public void setTxtDamageCost(String[] txtDamageCost) {
		this.txtDamageCost = txtDamageCost;
	}


	public String getItemCodeList() {
		return itemCodeList;
	}

	public void setItemCodeList(String itemCodeList) {
		this.itemCodeList = itemCodeList;
	}

	public String getItemDescList() {
		return itemDescList;
	}

	public void setItemDescList(String itemDescList) {
		this.itemDescList = itemDescList;
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

	public String getUomList() {
		return uomList;
	}

	public void setUomList(String uomList) {
		this.uomList = uomList;
	}



	public String getTotalDamageCost() {
		return totalDamageCost;
	}



	public void setTotalDamageCost(String totalDamageCost) {
		this.totalDamageCost = totalDamageCost;
	}



	public String getDamageCost() {
		return damageCost;
	}



	public void setDamageCost(String damageCost) {
		this.damageCost = damageCost;
	}



	public String getTotalCost() {
		return totalCost;
	}



	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}



	public String getSubTotal() {
		return subTotal;
	}



	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}



	public String getTotalDetailCost() {
		return totalDetailCost;
	}



	public void setTotalDetailCost(String totalDetailCost) {
		this.totalDetailCost = totalDetailCost;
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



	public String getTotalMatCost() {
		return totalMatCost;
	}



	public void setTotalMatCost(String totalMatCost) {
		this.totalMatCost = totalMatCost;
	}




	
	
}
