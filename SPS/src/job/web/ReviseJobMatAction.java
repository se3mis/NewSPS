package job.web;

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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

//import estimate.dao.PcesthttDao;
import estimate.model.MaterialGrid;
import job.model.Pcestdmt;
import job.model.PcestdmtPK;
import job.model.Pcesthmt;
import job.service.JobEjb;
import estimate.service.EstimateEjb;
import estimate.service.PcesthttEjb;
import job.service.PcestdmtEjb;
import job.service.PcesthmtEjb;

/**
 * @author Dell
 *
 */
public class ReviseJobMatAction extends ActionSupport  implements SessionAware, ParameterAware,ServletResponseAware,ServletRequestAware{

	private ArrayList<ArrayList<String>> matList;
	//private List matList;
	private Collection selectMatList;
	private Collection afterRemoveMatList;
	
	NumberFormat nf = NumberFormat.getInstance();
	
	private String lineLength;
	private String phaseDb;
	private String connectionTypeDb;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private Map<String, Object> sessionMap;
	
	private String costCenterNo;
	private String applicationNo;
	private String jobNo;
	private long revNo;
	
	private String[] chkMatCode;
	private String totalMatCost;
	private ArrayList otherCostList;
	private ArrayList nplMatList;
	
	private String[] chkSelMatCode;
	private String[] chkNplMatCode;
	private String[] txtSelQty;
	private String[] txtSelMatCost;
	private LinkedHashMap selectMatCodeMap;
	private String[] txtCusQty;
	
	private String[] chkResType;
	private String[] txtOtherCost;
	
	private String isUndoReject;
	
	private String isMatClicked;
	private String isOthClicked;
	private String isNplClicked;
	
	private String totalLabCost;
	private String itemCodeList;
	private String totalDetailCost;



	



	private String itemDescList;
	private String uomList;
	private String priceList;
	private String qtyList;
	private String costList;
	private String nonMatCodeList;
	private String nonMatCostList;	
	private String state;
	
	


	public String execute() throws Exception 
	{
		return SUCCESS;
	}
	
	

	public String displayMaterials()
	{
		try
		{
			if(isMatClicked!=null && isMatClicked.equals("true"))
			{
				System.out.println("revise show mats");
				//if(lineLength!=null && lineLength.trim().length()>0)
				//{
					LinkedHashMap selMatTmp = (LinkedHashMap)sessionMap.get("SelectMaterialList");
					Set matKeySet = null;
					if(selMatTmp!=null)
						matKeySet = selMatTmp.keySet();
					ArrayList selList = new ArrayList(matKeySet);
					
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
		System.out.println("revise add mats");
		try{
			if(lineLength!=null && lineLength.trim().length()>0)
			{
				
				nf.setGroupingUsed(true);
				nf.setMaximumFractionDigits(2);
				nf.setMinimumFractionDigits(2);
				
				selectMatList = new ArrayList();
				//String isNewEst = (String)sessionMap.get("IsNewEstimate");
				//String isDefault = (String)sessionMap.get("IsDefaultMasterAdd");
				
					
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
							
							String qty = "";
							if(tmpLst.getEstimateQty()!=null) 
								qty = tmpLst.getEstimateQty().toString();
							String cost = "0";
							if(tmpLst.getEstimateCost()!=null) 
								cost = tmpLst.getEstimateCost().toString();
							if(txtSelQty!=null && txtSelQty.length>0)
								qty = txtSelQty[i];
							if(txtSelMatCost!=null && txtSelMatCost.length>0)
								cost = txtSelMatCost[i];
							
							if(qty!=null && qty.length()>0)
								tmpLst.setEstimateQty(new BigDecimal(qty));
							if(cost!=null && cost.length()>0){
								tmpLst.setEstimateCost(new BigDecimal(nf.parse(cost).doubleValue()));
								totalCost = totalCost+nf.parse(cost).doubleValue();
							}
							//DW
							System.out.println("state8888888888888888888888888888888888888888888888888888 "+state);
							if (state.equals("print")){
								//System.out.println("#"+cost+"#");
								//System.out.println("#"+(cost!=null)+"#");
								//System.out.println("#"+(cost.trim().length()>0)+"#");
								//System.out.println("#"+(cost!=null && cost.trim().length()>0)+"#");
								if(tmpLst.getResType().equals("MAT-COST")){
									//cost = "0";
									itemCodeList = itemCodeList+"###"+matCode;
									itemDescList = itemDescList+"###"+tmpLst.getMatNm();
									uomList = uomList+"###"+tmpLst.getUom();
									priceList = priceList+"###"+nf.format(tmpLst.getUnitPrice());
									
									
									if(cost!="" || (cost!=null && cost.trim().length()>0))
										System.out.println("#"+cost+"#");
										costList = costList+"###"+nf.format(new BigDecimal(nf.parse(cost).doubleValue()));
									qtyList = qtyList+"###"+nf.format(new BigDecimal(qty));
									matCostTotal = matCostTotal+nf.parse(cost).doubleValue();
								}else{
									//cost = "0";
									nonMatCodeList = nonMatCodeList+"###"+tmpLst.getMatNm();
									if(cost!="" && cost!=null && cost.trim().length()>0)
										nonMatCostList = nonMatCostList+"###"+nf.format(new BigDecimal(nf.parse(cost).doubleValue()));
								}
							}
							//DW
							
							//totalMatCost = nf.format(totalCost);
							totalDetailCost = nf.format(totalCost);
							totalMatCost = nf.format(matCostTotal);
							selectMatCodeMap.put(matCode,tmpLst);
							selectMatList.add(tmpLst);
							i++;
						}
						
					}
				
					
					if(chkMatCode!=null) {//adding materials from new list
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
							//tmpAL.add("");
							//tmpAL.add("");
							
							
							selectMatList.add(tmpAL);
							selectMatCodeMap.put(matCode,tmpAL);
							
						}
					}
	
				}
				if(selectMatCodeMap!=null && selectMatCodeMap.size()>0){
					sessionMap.put("SelectMaterialList", selectMatCodeMap);
					selectMatList = selectMatCodeMap.values();
				}else {
					selectMatList = null;
				}
			
		}catch(Exception e){
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
			
			costCenterNo = (String)sessionMap.get("costCenterNo");
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
					totalMatCost = nf.format(totalCost);
					
					selectMatCodeMap.put(matCode,tmpLst);
					
					i++;
				}
				//selectMatList = selMatTmp;
			}
			if(chkSelMatCode!=null)//adding materials from new list
			{
				ArrayList removeList = null;
				String isNewEst = (String)sessionMap.get("IsNewEstimate");
				ArrayList savedMat = (ArrayList)sessionMap.get("SavedMaterialList");
				
				PcestdmtEjb pcestdmtEjb =  new PcestdmtEjb(getSessionKey("region"));
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
					double matCost = nf.parse(totalMatCost).doubleValue();
					double newTotMatCost = matCost-delMatCost;
					totalMatCost = nf.format(newTotMatCost);
					if(isNewEst!=null && isNewEst.equals("N"))
					{
						if(savedMat.contains(matCode))
						{
							//deleting materials
							PcestdmtPK pcestdmtPK = new PcestdmtPK();
							pcestdmtPK.setDeptId(costCenterNo);
							pcestdmtPK.setEstimateNo(applicationNo);
							pcestdmtPK.setResCd(matCode);
							pcestdmtPK.setRevNo(revNo);
							Pcestdmt pcestdtt = new Pcestdmt();
							pcestdtt.setId(pcestdmtPK);
							
							//pcestdttEjb.removePcestdtt(pcestdtt);
							if(removeList==null)
								removeList = new ArrayList();
							removeList.add(pcestdmtPK);
						}
					}
					
				}
				if(removeList!=null)
				{
					PcesthmtEjb pcesthmtEjb = new PcesthmtEjb(getSessionKey("region"));
					
					Pcesthmt pcesthmt=null;
					
					//Pcesthmt pcesthmt = pcesthmtEjb.findByJobNo(jobNo, costCenterNo);
					
					if(costCenterNo.equals("547.00")||costCenterNo.equals("547.10")||costCenterNo.equals("547.20")|| costCenterNo.equals("542.00") || costCenterNo.equals("542.10")|| costCenterNo.equals("542.20") || costCenterNo.equals("541.00")|| costCenterNo.equals("541.10")|| costCenterNo.equals("541.20") || costCenterNo.equals("548.00")|| costCenterNo.equals("548.10")|| costCenterNo.equals("548.20")){
						pcesthmt = pcesthmtEjb.findByJobNo(jobNo);
					}else{
						pcesthmt = pcesthmtEjb.findByJobNo(jobNo, costCenterNo);
					}
					
					
					pcesthmt.setStdCost(new BigDecimal(nf.parse(totalMatCost).longValue()));
					JobEjb jobEjb = new JobEjb(getSessionKey("region"));
					jobEjb.delete(removeList,pcesthmt);
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
	
	public String displayOtherCost()
	{
		try
		{
			
			if(isOthClicked!=null && isOthClicked.equals("true"))
			{
				System.out.println("revise display other cost");
				otherCostList = new ArrayList();
				
				LinkedHashMap selMatTmp = (LinkedHashMap)sessionMap.get("SelectMaterialList");
				Set matKeySet = null;
				ArrayList selList = null;
				if(selMatTmp!=null)
				{
					matKeySet = selMatTmp.keySet();
					selList = new ArrayList(matKeySet);
				}
				
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
		System.out.println("revise add other cost");
		try
		{
			nf.setGroupingUsed(true);
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			
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
					totalMatCost = nf.format(totalCost);
					
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
	
	public String displayNPLMatCost()
	{
		System.out.println("displayNPLMatCost");
		try
		{
			if(isNplClicked!=null && isNplClicked.equals("true"))
			{
				
				LinkedHashMap selMatTmp = (LinkedHashMap)sessionMap.get("SelectMaterialList");
				Set matKeySet = null;
				if(selMatTmp!=null)
					matKeySet = selMatTmp.keySet();
				ArrayList selList = new ArrayList(matKeySet);
				
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
		System.out.println("revise add npl mats");
		try{
			if(lineLength!=null && lineLength.trim().length()>0)
			{
				
				nf.setGroupingUsed(true);
				nf.setMaximumFractionDigits(2);
				nf.setMinimumFractionDigits(2);
				
				selectMatList = new ArrayList();
				
					
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
							totalMatCost = nf.format(totalCost);
							
							selectMatCodeMap.put(matCode,tmpLst);
							selectMatList.add(tmpLst);
							i++;
						}
						
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
							
							
							selectMatList.add(tmpAL);
							selectMatCodeMap.put(matCode,tmpAL);
							
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
		return "addNPL";
		
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
						tmpLst.setEstimateQty(new BigDecimal(nf.parse(totalLabCost).doubleValue()));
						//tmpLst.setEstimateQty(new BigDecimal(totalLabCost));
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
						if(txtSelQty!=null && txtSelQty.length>0)
							qty = txtSelQty[i];
						if(txtSelMatCost!=null && txtSelMatCost.length>0)
							cost = txtSelMatCost[i];
						if(txtCusQty!=null && txtCusQty.length>0)
							cusQty = txtCusQty[i];
						if(qty!=null && qty.length()>0)
							tmpLst.setEstimateQty(new BigDecimal(qty));
						if(cusQty!=null && cusQty.length()>0)
							tmpLst.setCustomerQty(new BigDecimal(cusQty));
						if(cost!=null && cost.length()>0)
						{
							tmpLst.setEstimateCost(new BigDecimal(nf.parse(cost).doubleValue()));
							totalCost = totalCost+nf.parse(cost).doubleValue();
						}
					}
					
					totalMatCost = nf.format(totalCost);
					
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
			System.out.println("@@@@@refreshSelectMat@@@@@@@@@@@@@@");
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
					if(txtSelQty!=null && txtSelQty.length>0)
						qty = txtSelQty[i];
					if(txtSelMatCost!=null && txtSelMatCost.length>0)
						cost = txtSelMatCost[i];
					if(txtCusQty!=null && txtCusQty.length>0)
						cusQty = txtCusQty[i];
					if(qty!=null && qty.length()>0)
						tmpLst.setEstimateQty(new BigDecimal(qty));
					if(cusQty!=null && cusQty.length()>0)
						tmpLst.setCustomerQty(new BigDecimal(cusQty));
					if(cost!=null && cost.length()>0)
					{
						tmpLst.setEstimateCost(new BigDecimal(nf.parse(cost).doubleValue()));
						totalCost = totalCost+nf.parse(cost).doubleValue();
					}
					
					
					totalMatCost = nf.format(totalCost);
					
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
	
	/************* Getters and Setters *************************/
	

	
	public String getSessionKey(String key) 
	{
       return getSession().get(key).toString();
	}
	
	public Map<String, Object> getSession() {
		return sessionMap;
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

	public String getTotalMatCost() {
		return totalMatCost;
	}

	public void setTotalMatCost(String totalMatCost) {
		this.totalMatCost = totalMatCost;
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



	public String getJobNo() {
		return jobNo;
	}



	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}



	public long getRevNo() {
		return revNo;
	}



	public void setRevNo(long revNo) {
		this.revNo = revNo;
	}



	public String getIsUndoReject() {
		return isUndoReject;
	}



	public void setIsUndoReject(String isUndoReject) {
		this.isUndoReject = isUndoReject;
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



	public String getIsNplClicked() {
		return isNplClicked;
	}



	public void setIsNplClicked(String isNplClicked) {
		this.isNplClicked = isNplClicked;
	}



	public ArrayList getNplMatList() {
		return nplMatList;
	}



	public void setNplMatList(ArrayList nplMatList) {
		this.nplMatList = nplMatList;
	}



	public String[] getTxtCusQty() {
		return txtCusQty;
	}



	public void setTxtCusQty(String[] txtCusQty) {
		this.txtCusQty = txtCusQty;
	}



	public String[] getChkNplMatCode() {
		return chkNplMatCode;
	}



	public void setChkNplMatCode(String[] chkNplMatCode) {
		this.chkNplMatCode = chkNplMatCode;
	}



	public String getTotalLabCost() {
		return totalLabCost;
	}



	public void setTotalLabCost(String totalLabCost) {
		this.totalLabCost = totalLabCost;
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
	
	public String getTotalDetailCost() {
		return totalDetailCost;
	}



	public void setTotalDetailCost(String totalDetailCost) {
		this.totalDetailCost = totalDetailCost;
	}
	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}

	
	
}
