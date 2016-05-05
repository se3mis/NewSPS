package job.web;

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
import estimate.model.Spsetpol;
import estimate.service.EstimateEjb;
import estimate.service.PcestdttEjb;
import estimate.service.PcesthttEjb;
import estimate.service.SplabratEjb;
import estimate.service.SpsetpolEjb;


/**
 * @author Dell
 *
 */
public class ReviseJobLabourAction extends ActionSupport  implements SessionAware, ParameterAware,ServletResponseAware,ServletRequestAware{

	private ArrayList<ArrayList<String>> matList;
	private List<LabourGrid> labourList;
	//private List matList;
	//private Collection selectMatList;
	private Collection selectLabList;
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
	
	String isRemoveFinish = "false";
		
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
				SplabratEjb splabratEjb = new SplabratEjb(getSessionKey("region"));
				labourList = splabratEjb.getLabourGrid(costCenterNo, "NC",selList);
				
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
						String cost = "";
						if(tmpLst.getLabourCost()!=null) 
							cost = tmpLst.getLabourCost().toString();
						String cusQty = "";
						
						if(txtSelLabQty!=null && txtSelLabQty.length>0)
							qty = txtSelLabQty[i];
						if(txtSelLabCost!=null && txtSelLabCost.length>0)
							cost = txtSelLabCost[i];
						if(txtCusQty!=null && txtCusQty.length>0)
							cusQty = txtCusQty[i];
						if(qty!=null && qty.length()>0)
							tmpLst.setItemQty(new BigDecimal(qty));
						if(cost!=null && cost.length()>0)
						{
							tmpLst.setLabourCost(new BigDecimal(nf.parse(cost).doubleValue()));
							totalCost = totalCost+nf.parse(cost).doubleValue();
						}
						totalLabCost = nf.format(totalCost);
						
						selectLabCodeMap.put(matCode,tmpLst);
						//selectMatList.add(tmpLst);
						i++;
					}
					//selectMatList = selMatTmp;
				}
			
				
				if(chkLabCode!=null)//adding materials from new list
				{
					sessionMap.put("IsMaterialsAdd", "Y");
					if(selectLabCodeMap==null)
						selectLabCodeMap = new LinkedHashMap();
					for(int i=0;i<chkLabCode.length;i++)
					{
						StringTokenizer st = new StringTokenizer(chkLabCode[i],"###");
						LabourGrid lg = new LabourGrid();
						
						String index = st.nextToken();
						String labCode = st.nextToken();
						String up  = st.nextToken();
						String labName  = st.nextToken();
						
						lg.setDescription(labName);
						lg.setUnitPrice(new BigDecimal(nf.parse(up).doubleValue()));
						lg.setLabourCode(labCode);
						
						selectLabCodeMap.put(labCode,lg);
					}
				}
				retVal = "select";
			}
			else if(isRemoveLabClicked!=null && isRemoveLabClicked.equals("true"))
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
						
						String cost = "";
						if(tmpLst.getLabourCost()!=null) 
							cost = tmpLst.getLabourCost().toString();
						if(txtSelLabCost!=null && txtSelLabCost.length>0)
							cost = txtSelLabCost[i];
						if(cost!=null && cost.length()>0)
						{
							tmpLst.setLabourCost(new BigDecimal(nf.parse(cost).doubleValue()));
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
							//pcesthtt = pcesthttEjb.findByEstimationNo(applicationNo,costCenterNo);
							pcesthtt = pcesthttEjb.findByEstimationNo(applicationNo);
							if(costCenterNo.equals("547.00")||costCenterNo.equals("547.10")||costCenterNo.equals("547.20")|| costCenterNo.equals("542.00") || costCenterNo.equals("542.10")|| costCenterNo.equals("542.20") || costCenterNo.equals("541.00")|| costCenterNo.equals("541.10")|| costCenterNo.equals("541.20") || costCenterNo.equals("548.00")|| costCenterNo.equals("548.10")|| costCenterNo.equals("548.20")){
								pcesthtt = pcesthttEjb.findByEstimationNo(applicationNo);
							}else{
								pcesthtt = pcesthttEjb.findByEstimationNo(applicationNo,costCenterNo);
							}
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
				retVal = "select";
				isRemoveFinish = "true";
				
			}
			else
			{
				System.out.println("dispay select labour %%%%%%%%%%%");
				//String isNewEst = (String)sessionMap.get("IsNewEstimate");
				//String isMatAdd = (String)sessionMap.get("IsMaterialsAdd");
				/*if(isMatAdd==null && (isNewEst==null || isNewEst.equals("Y")))
				{
					if(wiringType!=null && wiringType.length()>0)
					{
						//addDefMaterials();
					}
					
				}
				else
				{*/
					selectLabCodeMap = (LinkedHashMap)sessionMap.get("SelectLabourList");
					
					if(selectLabCodeMap!=null && selectLabCodeMap.size()>0)
					{
						Iterator it = selectLabCodeMap.keySet().iterator();
						int i=0;
						double totalCost = 0;
						while(it.hasNext())
						{
							String labCode = (String)it.next();
							LabourGrid tmpLst = (LabourGrid)selectLabCodeMap.get(labCode);
							
							String qty = "";
							if(tmpLst.getItemQty()!=null) 
								qty = tmpLst.getItemQty().toString();
							String cost = "";
							if(tmpLst.getLabourCost()!=null) 
								cost = tmpLst.getLabourCost().toString();
							
							if(txtSelLabQty!=null && txtSelLabQty.length>0)
								qty = txtSelLabQty[i];
							if(txtSelLabCost!=null && txtSelLabCost.length>0)
								cost = txtSelLabCost[i];
							
							if(qty!=null && qty.length()>0)
								tmpLst.setItemQty(new BigDecimal(qty));
							if(cost!=null && cost.length()>0)
							{
								tmpLst.setLabourCost(new BigDecimal(nf.parse(cost).doubleValue()));
								totalCost = totalCost+nf.parse(cost).doubleValue();
							}
							totalLabCost = nf.format(totalCost);
							
							selectLabCodeMap.put(labCode,tmpLst);
							i++;
						}
						
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
					
					String cost = "";
					if(tmpLst.getLabourCost()!=null) 
						cost = tmpLst.getLabourCost().toString();
					if(txtSelLabCost!=null && txtSelLabCost.length>0)
						cost = txtSelLabCost[i];
					if(cost!=null && cost.length()>0)
					{
						tmpLst.setLabourCost(new BigDecimal(nf.parse(cost).doubleValue()));
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
						//pcesthtt = pcesthttEjb.findByEstimationNo(applicationNo,costCenterNo);
						pcesthtt = pcesthttEjb.findByEstimationNo(applicationNo);
						if(costCenterNo.equals("547.00")||costCenterNo.equals("547.10")||costCenterNo.equals("547.20")|| costCenterNo.equals("542.00") || costCenterNo.equals("542.10")|| costCenterNo.equals("542.20") || costCenterNo.equals("541.00")|| costCenterNo.equals("541.10")|| costCenterNo.equals("541.20") || costCenterNo.equals("548.00")|| costCenterNo.equals("548.10")|| costCenterNo.equals("548.20")){
							pcesthtt = pcesthttEjb.findByEstimationNo(applicationNo);
						}else{
							pcesthtt = pcesthttEjb.findByEstimationNo(applicationNo,costCenterNo);
						}
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
					String cost = "";
					if(tmpLst.getLabourCost()!=null) 
						cost = tmpLst.getLabourCost().toString();
					String cusQty = "";
					
					if(txtSelLabQty!=null && txtSelLabQty.length>0)
						qty = txtSelLabQty[i];
					if(txtSelLabCost!=null && txtSelLabCost.length>0)
						cost = txtSelLabCost[i];
					if(txtCusQty!=null && txtCusQty.length>0)
						cusQty = txtCusQty[i];
					if(qty!=null && qty.length()>0)
						tmpLst.setItemQty(new BigDecimal(qty));
					if(cost!=null && cost.length()>0)
					{
						tmpLst.setLabourCost(new BigDecimal(nf.parse(cost).doubleValue()));
						totalCost = totalCost+nf.parse(cost).doubleValue();
					}
					
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
	
	/************* Getters and Setters ***************/
	
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





	public void setIsRemoveFinish(String isRemoveFinish) {
		this.isRemoveFinish = isRemoveFinish;
	}



	

	
}
