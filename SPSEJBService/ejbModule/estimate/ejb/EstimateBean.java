package estimate.ejb;

import inventory.ejb.InmatmDaoRemote;
import inventory.ejb.InwrhmapDaoRemote;
import inventory.ejb.InwrhmtmDaoRemote;
import inventory.model.Inwrhmtm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import job.ejb.PcestdmtDaoRemote;
import job.ejb.PcesthmtDaoRemote;
import job.ejb.PcprjbalDaoRemote;
import job.ejb.PcrstypmDaoRemote;
import job.model.Pcestdmt;
import job.model.PcestdmtPK;
import job.model.Pcesthmt;
import job.model.PcesthmtPK;
import job.model.Pcprjbal;
import job.model.PcprjbalPK;
import job.model.Pcrstypm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import piv.ejb.TempTbDaoRemote;
import piv.model.TempTb;
import security.ejb.SecurityBeanRemote;
import util.common.AppStatus;
import util.common.AppointmentStatus;
import util.common.Constants;
import util.common.EstimateStatus;
import util.common.Format;
import util.common.PIVStatus;
import util.common.StandardEstimateStatus;
import util.emSelect.EmSelector;
import application.ejb.ApplicationDaoRemote;
import application.ejb.ApplicationRefBSDaoRemote;
import application.ejb.ApplicationReferenceDaoRemote;
import application.ejb.WiringLandDetailDaoRemote;
import application.model.ApplicationReference;
import application.model.CityMap;
import costcenter.ejb.GldeptmDaoRemote;
import estimate.dto.DetailEstimateDTO;
import estimate.model.AllocationSummaryDisplay;
import estimate.model.Appestlim;
import estimate.model.Approval;
import estimate.model.CostCalculationMaster;
import estimate.model.DefaultMatGrid;
import estimate.model.EstimateDisplay;
import estimate.model.EstimateReference;
import estimate.model.EstimateReferencePK;
import estimate.model.FundSource;
import estimate.model.LabourGrid;
import estimate.model.MaterialGrid;
import estimate.model.Pcestdtt;
import estimate.model.PcestdttPK;
import estimate.model.Pcesthtt;
import estimate.model.Pcfunddm;
import estimate.model.Pcrsgrpm;
import estimate.model.Pegschdmt;

import estimate.model.SpNormsGroup;
import estimate.model.SpPegInfo;
import estimate.model.SpPointdmt;
import estimate.model.SpestedyCons;
import estimate.model.SpestedyConsPK;
import estimate.model.Spestlab;
import estimate.model.SpestlabPK;
import estimate.model.Spestmtm;
import estimate.model.Speststd;
import estimate.model.SpeststdPK;
import estimate.model.Splpsvcm;
import estimate.model.SplpsvcmPK;
import estimate.model.Spnorms;
import estimate.model.SpnormsPK;
import estimate.model.Spratesm;
import estimate.model.SpratesmPK;
import estimate.model.Spsecdep;
import estimate.model.SpsecdepPK;
import estimate.model.Spserest;
import estimate.model.Spstdestdmt;
import estimate.model.Spstdesthmt;
import estimate.model.SpstdesthmtPK;
import estimate.service.SpstdesthmtEjb;

/**
 * Session Bean implementation class JobBean
 */
@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless
public class EstimateBean extends EmSelector implements EstimateBeanRemote,
		EstimateBeanLocal {
	private Log LOGGER = LogFactory.getLog(EstimateBean.class);
	@Resource
	private SessionContext context;
	@EJB
	InmatmDaoRemote inmatmDaoRemote;
	@EJB
	InwrhmtmDaoRemote inwrhmtmDaoRemote;
	@EJB
	PcesthttDaoRemote pcesthttDaoRemote;
	@EJB
	PcestdttDaoRemote pcestdttDaoRemote;
	@EJB
	SpestlabDaoRemote spestlabDaoRemote;
	@EJB
	SpeststdDaoRemote speststdDaoRemote;
	@EJB
	SpestmtmDaoRemote spestmtmDaoRemote;
	@EJB
	GldeptmDaoRemote gldeptmDaoRemote;
	@EJB
	PcfunddmDaoRemote pcfunddmDaoRemote;
	@EJB
	ApplicationDaoRemote applicationDaoRemote;
	@EJB
	WiringLandDetailDaoRemote wiringLandDetailDaoRemote;
	@EJB
	SecurityBeanRemote securityBeanRemote;
	@EJB
	SpestedyDaoRemote spestedyDaoRemote;
	@EJB
	InwrhmapDaoRemote inwrhmapDaoRemote;
	@EJB
	ApprovalDaoRemote approvalDaoRemote;
	@EJB
	PcesthmtDaoRemote pcesthmtDaoRemote;
	@EJB
	PcestdmtDaoRemote  pcestdmtDaoRemote;
	@EJB
	ApplicationReferenceDaoRemote applicationReferenceDaoRemote;
	@EJB
	PcprjbalDaoRemote pcprjbalDaoRemote;
	
	@EJB
	SpnormsDaoRemote normsDaoRemote;
	
	@EJB
	SpPegInfoDaoRemote spPegInfoDaoRemote;
	
	@EJB
	SpstdesthmtDaoRemote spstdesthmtDaoRemote;
	
	@EJB
	SpstdestdmtDaoRemote spstdestdmtDaoRemote;
	
	@EJB
	SpPointdmtDaoRemote spPointdmtDaoRemote;
	
	@EJB
	PcrstypmDaoRemote pcrstypmDaoRemote;
	
	
	@EJB
	PcjbtypmDaoRemote pcjbtypmDaoRemote;
	
	@EJB
	PcrsgrpmDaoRemote pcrsgrpmDaoRemote;
	
	@EJB
	TempTbDaoRemote tempTbDaoRemote;
	
	@EJB
	EstimateReferenceDaoRemote estimateReferenceDaoRemote;
	
	@EJB
	ApplicationRefBSDaoRemote applicationRefBSDaoRemote;
	
	@EJB
	SpestedyConDaoRemote spestedyConDaoRemote;
	
	@EJB
	CostCalculationMasterDaoRemote costCalculationMasterDaoRemote;
	
	@EJB
	SpnormsGroupDaoRemote normsGroupDaoRemote;
	
	@EJB
	PegschdmtDaoRemote pegschdmtDao;
	
	private Format format;
	/*@EJB
	EstimateTypeMasterDao estimateTypeMasterDao;*/
	
    /**
     * Default constructor. 
     */
    public EstimateBean() {
        // TODO Auto-generated constructor stub
    }
    
    
	
    /*@Override
	public List<MaterialGrid> getMaterialGrid(String estimateNo, String deptId) {
		List<MaterialGrid> gridList=new ArrayList<MaterialGrid>();
		MaterialGrid  materialGrid = null;
		List<Pcestdtt> list=pcestdttDaoRemote.findByEstimationNo(estimateNo, deptId);
		//System.out.println(list);
		//System.out.println(list.size());
		Iterator<Pcestdtt> it = list.iterator();		
        while (it.hasNext()) 
        { 
        	materialGrid= new MaterialGrid();
        	Pcestdtt pcestdtt = it.next();
        	//System.out.println(pcestdtt);
        	//System.out.println(pcestdtt.getId().getResCd().trim()+" ##################################");
        	materialGrid.setResType(pcestdtt.getResType().trim());
        	materialGrid.setResCd(pcestdtt.getId().getResCd().trim());
        	//materialGrid.setMatNm(inmatmDaoRemote.findName(pcestdtt.getId().getResCd()).trim());
        	//System.out.println(inmatmDaoRemote.findName(pcestdtt.getId().getResCd().trim()).trim()+"   "+"00101");
        	//System.out.println(inmatmDaoRemote.findName(pcestdtt.getId().getResCd().trim()).equals("00101"));
        	//System.out.println(pcestdtt.getResCat());
        	//System.out.println("pcestdtt getResCat() new BigDecimal1"+ pcestdtt.getResCat()==new BigDecimal("1"));
        	if (pcestdtt.getResCat().equals(new BigDecimal("1"))){
        		materialGrid.setMatNm(inmatmDaoRemote.findName(pcestdtt.getId().getResCd().trim()).trim());
        	}else materialGrid.setMatNm(pcestdtt.getId().getResCd().trim());
        	//materialGrid.setMatNm(inmatmDaoRemote.findName("B0424"));
        	
        	materialGrid.setResCat(pcestdtt.getResCat());
        	materialGrid.setUom(pcestdtt.getUom());
        	materialGrid.setUnitPrice(pcestdtt.getUnitPrice());
        	materialGrid.setEstimateQty(pcestdtt.getEstimateQty());
        	materialGrid.setEstimateCost(pcestdtt.getEstimateCost());
        	materialGrid.setCustomerQty(pcestdtt.getCustomerQty());
        	gridList.add(materialGrid);
        }

		
		return gridList;
		

	}*/
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MaterialGrid> getMaterialGrid(String estimateNo, String deptId, String webAppName) {
		//getEntityManager(webAppName);
		List<MaterialGrid> gridList=new ArrayList<MaterialGrid>();
		String qryStr ="SELECT new estimate.model.MaterialGrid(TRIM(p.resType), TRIM(p.id.resCd), p.resCat, TRIM(i.matNm), TRIM(p.uom), p.tolerance, p.unitPrice, p.estimateQty, p.estimateCost, p.customerQty, p.damageQty, p.mntQty)  from Pcestdtt p , Inmatm i WHERE TRIM(p.id.estimateNo)=:estimateNo AND p.id.deptId=:deptId AND p.resCat=:resCat  AND p.id.resCd=i.matCd ORDER BY p.id.resCd"; 
		Query query=getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", estimateNo);
		query.setParameter("deptId", deptId);
		query.setParameter("resCat", new BigDecimal(1));
		List<MaterialGrid> list1=query.getResultList();
		gridList.addAll(list1);
		//System.out.println("$$ "+list1.size());
		
		      qryStr ="SELECT new estimate.model.MaterialGrid(TRIM(p.resType), TRIM(p.id.resCd), p.resCat, TRIM(p.id.resCd), TRIM(p.uom), p.tolerance, p.unitPrice, p.estimateQty, p.estimateCost, p.customerQty, p.damageQty, p.mntQty)  from Pcestdtt p  WHERE TRIM(p.id.estimateNo)=:estimateNo AND p.id.deptId=:deptId AND p.resCat <> 1 ORDER BY p.id.resCd"; 
		query=getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", estimateNo);
		query.setParameter("deptId", deptId);
		//query.setParameter("resCat", new BigDecimal(1));
		List<MaterialGrid> list2=query.getResultList();
		gridList.addAll(list2);
		//System.out.println("$$ "+list2.size());
		//System.out.println("$$ "+gridList.size());
		list1=null;
		list2=null;
		return gridList;
		

	}
	
		
	@Override
	public MaterialGrid getMaterialGridRecord(String estimateNo, String deptId, String resCd, String webAppName) {
		MaterialGrid  materialGrid = null;
		Pcestdtt pcestdtt=pcestdttDaoRemote.findBy3PK(estimateNo, deptId, resCd, webAppName);
		//System.out.println(list);
		//System.out.println(list.size());
		//Iterator<Pcestdtt> it = list.iterator();		
        //while (it.hasNext()) 
        //{ 
        	materialGrid= new MaterialGrid();
        	//Pcestdtt pcestdtt = it.next();
        	materialGrid.setResType(pcestdtt.getResType().trim());
        	materialGrid.setResCd(pcestdtt.getId().getResCd().trim());
        	materialGrid.setMatNm(inmatmDaoRemote.findName(resCd, webAppName).trim());
        	materialGrid.setResCat(pcestdtt.getResCat());
        	materialGrid.setUom(pcestdtt.getUom());
        	materialGrid.setUnitPrice(pcestdtt.getUnitPrice());
        	materialGrid.setEstimateQty(pcestdtt.getEstimateQty());
        	materialGrid.setEstimateCost(pcestdtt.getEstimateCost());
        	//list.add(materialGrid);
       //}

		
		return materialGrid;
		//return null;
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public void insert(Pcesthtt pcesthtt, Speststd speststd, List<Pcestdtt> list, String webAppName) {
		try{
			pcesthttDaoRemote.createPcesthtt(pcesthtt, webAppName);
			speststdDaoRemote.createSpeststd(speststd, webAppName);
			for(int i=0; i<=list.size()-1; i++){
				pcestdttDaoRemote.createPcestdtt(list.get(i), webAppName);
			}
		
			applicationDaoRemote.changeStatusApplication(pcesthtt.getId().getEstimateNo(), pcesthtt.getId().getDeptId(), AppStatus.ESTIMATED, webAppName);
			spestedyDaoRemote.changeStatusAppointments(pcesthtt.getId().getEstimateNo(), pcesthtt.getId().getDeptId(), AppointmentStatus.ESTIMATED, webAppName);
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public void insert(Pcesthtt pcesthtt, Speststd speststd, List<Pcestdtt> list, List<Spestlab> listLabour	, String webAppName) {
		try{
			pcesthttDaoRemote.createPcesthtt(pcesthtt, webAppName);
			speststdDaoRemote.createSpeststd(speststd, webAppName);
			//System.out.println("99999999999999999"+speststd);
			if (list!=null){
			for(int i=0; i<=list.size()-1; i++){
				pcestdttDaoRemote.createPcestdtt(list.get(i), webAppName);
			}}
			if (listLabour!=null){
			for(int j=0; j<=listLabour.size()-1; j++){
				//System.out.println("444444444444444444"+listLabour.get(j));
				spestlabDaoRemote.createSpestlab(listLabour.get(j), webAppName);
			}}
		
			
			applicationDaoRemote.changeStatusApplication(pcesthtt.getId().getEstimateNo(), pcesthtt.getId().getDeptId(),AppStatus.ESTIMATED, webAppName);
			spestedyDaoRemote.changeStatusAppointments(pcesthtt.getId().getEstimateNo(), pcesthtt.getId().getDeptId(), AppointmentStatus.ESTIMATED, webAppName);
			spestlabDaoRemote.insertTransportOnDTT(pcesthtt.getId().getEstimateNo(), pcesthtt.getId().getDeptId(), webAppName);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Pcesthtt pcesthtt, Speststd speststd, List<Pcestdtt> addlist, List<Pcestdtt> updList, String webAppName) {
		try{
			pcesthttDaoRemote.updatePcesthtt(pcesthtt, webAppName);
			
			speststdDaoRemote.updateSpeststd(speststd, webAppName);
			
			if (addlist!=null){
			for(int i=0; i<=addlist.size()-1; i++){
				pcestdttDaoRemote.createPcestdtt(addlist.get(i), webAppName);
			}}
			if (updList!=null){
			for(int j=0; j<=updList.size()-1; j++){
				pcestdttDaoRemote.updatePcestdtt(updList.get(j), webAppName);
			}}
			
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Pcesthtt pcesthtt, Speststd speststd, List<Pcestdtt> addlist, List<Pcestdtt> updList, List<Spestlab> addListLabour,List<Spestlab> updListLabour, String webAppName) {
		try{
			pcesthttDaoRemote.updatePcesthtt(pcesthtt, webAppName);
			speststdDaoRemote.updateSpeststd(speststd, webAppName);
			if (addlist!=null){
			for(int i=0; i<=addlist.size()-1; i++){
				pcestdttDaoRemote.createPcestdtt(addlist.get(i), webAppName);
			}}
			if (updList!=null){
			for(int j=0; j<=updList.size()-1; j++){
				pcestdttDaoRemote.updatePcestdtt(updList.get(j), webAppName);
			}}
			if (addListLabour!=null){
				for(int k=0; k<=addListLabour.size()-1; k++){
					spestlabDaoRemote.updateSpestlab(addListLabour.get(k), webAppName);
				}}
			if (updListLabour!=null){
				for(int n=0; n<=updListLabour.size()-1; n++){
					spestlabDaoRemote.updateSpestlab(updListLabour.get(n), webAppName);
				}}
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}
	
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(List<PcestdttPK> list, Pcesthtt inPcesthtt, String webAppName) {
		//List<String> list2=new ArrayList<String>();
		//list2.add("11");
		try{
			pcesthttDaoRemote.updatePcesthtt(inPcesthtt, webAppName);
			for(int i=0; i<=list.size()-1; i++){
				PcestdttPK pcestdttPK=list.get(i);
				Pcestdtt pcestdtt=pcestdttDaoRemote.findBy3PK(pcestdttPK.getEstimateNo(), pcestdttPK.getDeptId(), pcestdttPK.getResCd(), webAppName);
				pcestdttDaoRemote.removePcestdtt(pcestdtt, webAppName);
				
			}
			
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	//public List<DefaultMatGrid> getDefaultList(Long phase, Long connectionType, Long fromLength, Long toLength, String wiringType,String deptId, String webAppName) {
	public List<DefaultMatGrid> getDefaultList(Long phase, Long connectionType, String wiringType,String deptId, String webAppName) {
		//getEntityManager(webAppName);
		DefaultMatGrid defaultMatGrid=null;
		List<DefaultMatGrid> gridList=new ArrayList<DefaultMatGrid>();
		try{
			//String qryStr = "SELECT g FROM Spestmtm g WHERE g.id.wiringType = :wiringType AND g.id.phase = :phase AND g.id.connectionType = :connectionType AND g.id.fromLength = :fromLength AND g.id.toLength = :toLength";
			String qryStr = "SELECT g FROM Spestmtm g WHERE g.id.wiringType = :wiringType AND g.id.phase = :phase AND g.id.connectionType = :connectionType";
			Query query = getEntityManager(webAppName).createQuery(qryStr);
			query.setParameter("wiringType", wiringType);
			query.setParameter("phase", phase);
			query.setParameter("connectionType", connectionType);
			//query.setParameter("fromLength", fromLength);
			//query.setParameter("toLength", toLength);
			List<Spestmtm>  list = query.getResultList();	
			System.out.println(list);
			//deptId="501.11";
			Iterator<Spestmtm> it = list.iterator();
			  while (it.hasNext()) 
		        { 
				  	defaultMatGrid= new DefaultMatGrid();
				  	Spestmtm spestmtm = it.next();
				  	//System.out.println("################################## "+spestmtm);
				  	//System.out.println(spestmtm.getId().getMatCd().trim().equals("K1026"));
				  	defaultMatGrid.setMatCd(spestmtm.getId().getMatCd().trim());
				  	//System.out.println(defaultMatGrid.getMatCd());
				  	defaultMatGrid.setMatNm(inmatmDaoRemote.findName(defaultMatGrid.getMatCd(), webAppName).trim());
				  	//System.out.println(defaultMatGrid.getMatNm());
				  	Inwrhmtm inwrhmtm=inwrhmtmDaoRemote.findByMatCd(defaultMatGrid.getMatCd(), deptId, webAppName);
				  	defaultMatGrid.setUom(inwrhmtm.getUomCd());
				  	defaultMatGrid.setUnitPrice(inwrhmtm.getUnitPrice());
				  	
				  	//System.out.println(spestmtm.getMatQty());
				  	//System.out.println(defaultMatGrid.getUnitPrice());
				  	defaultMatGrid.setMatQty(spestmtm.getMatQty());
				  	//System.out.println(defaultMatGrid.getMatQty().multiply(defaultMatGrid.getUnitPrice()));
				  	defaultMatGrid.setCost(defaultMatGrid.getMatQty().multiply(defaultMatGrid.getUnitPrice()));
				  	
				  	gridList.add(defaultMatGrid);
		        }
			//List<Spestmtm> list=spestmtmDaoRemote.estimateMaterials(phase, connectionType, fromLength, toLength);
			//List list=spestmtmDaoRemote.estimateMaterials(phase, connectionType, fromLength, toLength);
			System.out.println("############################### "+list.get(0).getId().getPhase());
			return gridList;
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
			//return null;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	//public LinkedHashMap<String,MaterialGrid> getDefaultMaterialGrid(String deptId, long phase, long conType, long length,String wiringType) 
	public LinkedHashMap<String,MaterialGrid> getDefaultMaterialGrid(String deptId, long phase, long conType, String wiringType, String webAppName) {
		//getEntityManager(webAppName);
		String warehouse=inwrhmapDaoRemote.mapWarehouse(deptId, webAppName);
		LinkedHashMap<String,MaterialGrid> selectMatCodeMap = null;
		try
		{
			String qryStr = " SELECT new Map(TRIM(s.id.matCd), wm.uomCd, s.matQty, wm.unitPrice, s.matQty*wm.unitPrice as cost, m.matNm )"+
							" from Spestmtm s ,Inmatm m, Inwrhmtm wm"+
							" where s.id.deptId = :deptId "+
							" and wm.id.deptId = :warehouse "+
							" and s.id.phase = :phase "+
							" and s.id.connectionType = :conType "+
							//" and s.id.fromLength< :length "+
							//" and s.id.toLength>= :length "+
							" and s.id.wiringType= :wiringType "+
							" and s.id.matCd = m.matCd "+
							" and wm.status IN ('2','7') "+
                            " and m.status ='2' "+
							" and TRIM(wm.id.gradeCd) ='NEW' "+
							" and wm.id.matCd = m.matCd  ";
							

			Query query = getEntityManager(webAppName).createQuery(qryStr);
			query.setParameter("deptId", deptId);
			query.setParameter("warehouse", warehouse);
			
			query.setParameter("phase", phase);
			query.setParameter("conType", conType);
			//query.setParameter("length", length);
			//query.setParameter("length", length);
			query.setParameter("wiringType", wiringType);
			
			List<Map> list = query.getResultList();
			if(!list.isEmpty())
			{
				selectMatCodeMap =  new LinkedHashMap<String,MaterialGrid>();
				for(int i=0;i<list.size();i++)
				{
					Map map = list.get(i);
					MaterialGrid grid = new MaterialGrid();
					grid.setEstimateCost(new BigDecimal(map.values().toArray()[4].toString()));//
					grid.setEstimateQty(new BigDecimal(map.values().toArray()[1].toString()));
					grid.setMatNm(map.values().toArray()[5].toString().trim());//
					grid.setResCat(new BigDecimal(1));
					grid.setResType("MAT-COST");
					grid.setUnitPrice(new BigDecimal(map.values().toArray()[0].toString()));
					grid.setUom(map.values().toArray()[2].toString());//
					grid.setResCd(map.values().toArray()[3].toString());//
					selectMatCodeMap.put(map.values().toArray()[3].toString(),grid);
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return selectMatCodeMap;

	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public ArrayList<ArrayList<String>> getAvailableMaterials(String deptId, ArrayList selMatSet, String webAppName) throws Exception {
		//getEntityManager(webAppName);
		String warehouse=inwrhmapDaoRemote.mapWarehouse(deptId, webAppName);
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% "+warehouse); 
		ArrayList<ArrayList<String>> resultList = null;
		List<Map> list = null;
          
          String qryStr = " SELECT new Map(TRIM(wm.id.matCd) , wm.uomCd, wm.unitPrice, TRIM(m.matNm) )"+
                                  " from Inwrhmtm wm,Inmatm m "+
                                  //" where wm.id.deptId = :deptId "+
                                  " where wm.id.deptId = :warehouse "+
                                  " and  wm.id.matCd = m.matCd "+
                                  " and m.matCd NOT LIKE :NPL "+
                                  " and wm.status in ('2','7') "+
                                  " and m.status ='2' "+
                                  " and TRIM(wm.id.gradeCd) ='NEW' "+
                                  " order by wm.id.matCd ";

          Query query = getEntityManager(webAppName).createQuery(qryStr);
          //query.setParameter("deptId", deptId);
          query.setParameter("warehouse", warehouse);
          query.setParameter("NPL", "NPL%");
          //System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");      
          list = query.getResultList();
          if(!list.isEmpty())
          {
                resultList =  new ArrayList<ArrayList<String>>();
                for(int i=0;i<list.size();i++)
                {
                      Map map = list.get(i);
                      String matCd = map.values().toArray()[3].toString();
                      String matNm = map.values().toArray()[0].toString();
                      String uom = map.values().toArray()[2].toString();
                      String up = map.values().toArray()[1].toString();
                      
                      if(selMatSet==null || !selMatSet.contains(matCd) || selMatSet.size()==0)
                      {
                            ArrayList<String> tmpList = new ArrayList<String>();
                            tmpList.add(matCd);
                            tmpList.add(matNm);
                            tmpList.add(uom);
                            tmpList.add(up);
                            resultList.add(tmpList);
                      }
                }
          }
          
          return resultList;
  }
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public ArrayList<ArrayList<String>> getNPLMaterials(String deptId, ArrayList selMatSet, String webAppName)throws Exception {
		//getEntityManager(webAppName);
		ArrayList<ArrayList<String>> resultList = null;
		List<Map> list = null;
          
          String qryStr = " SELECT new Map(TRIM(wm.id.matCd) , wm.uomCd, wm.unitPrice, TRIM(m.matNm) )"+
                                  " from Inwrhmtm wm,Inmatm m "+
                                  " where wm.id.deptId = :deptId "+
                                  " and  wm.id.matCd = m.matCd "+
                                  " and m.matCd LIKE :NPL "+
                                  " order by wm.id.matCd ";

          Query query = getEntityManager(webAppName).createQuery(qryStr);
          query.setParameter("deptId", deptId);
          query.setParameter("NPL", "NPL%");
          //System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");      
          list = query.getResultList();
          if(!list.isEmpty())
          {
                resultList =  new ArrayList<ArrayList<String>>();
                for(int i=0;i<list.size();i++)
                {
                      Map map = list.get(i);
                      String matCd = map.values().toArray()[3].toString();
                      String matNm = map.values().toArray()[0].toString();
                      String uom = map.values().toArray()[2].toString();
                      String up = map.values().toArray()[1].toString();
                      
                      if(selMatSet==null || !selMatSet.contains(matCd))
                      {
                            ArrayList<String> tmpList = new ArrayList<String>();
                            tmpList.add(matCd);
                            tmpList.add(matNm);
                            tmpList.add(uom);
                            tmpList.add(up);
                            resultList.add(tmpList);
                      }
                }
          }
          
          return resultList;
  }
 
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
    public ArrayList<ArrayList<String>> getAvailableOtherCosts(String deptId, ArrayList selMatSet, String webAppName) throws Exception {
		//getEntityManager(webAppName);
		ArrayList<ArrayList<String>> resultList = null;
    
          String qryStr = " select new Map(TRIM(p.resType),TRIM(p.resType),p.resCat,TRIM(p.typeNm))  "+
                                  " from Pcrstypm  p "+
                                  " where TRIM(p.resType) !='MAT-COST' "+
                                  " and TRIM(p.resType) !='LABOUR-COST' "+
                                  " and TRIM(p.resType) !='TRANSPORT-COST' "+
                                  " and TRIM(p.resType) !='OVERHEAD-COST' "+
                                  " order by p.resType ";
                
          Query query = getEntityManager(webAppName).createQuery(qryStr);
          //query.setParameter("deptId", deptId);
          
          List<Map> list = query.getResultList();
          if(!list.isEmpty())
          {
                resultList =  new ArrayList<ArrayList<String>>();
                for(int i=0;i<list.size();i++)
                {
                      Map<String,String> map = list.get(i);
                      String resCd = map.values().toArray()[3].toString();
                      String resNm = map.values().toArray()[0].toString();
                      String resType = map.values().toArray()[2].toString();
                      String resCat = map.values().toArray()[1].toString();
                      
                      if(selMatSet==null || !selMatSet.contains(resCd))
                      {
                            ArrayList<String> tmpList = new ArrayList<String>();
                            tmpList.add(resCd);
                            tmpList.add(resType);
                            tmpList.add(resCat);
                            tmpList.add(resNm);
                            resultList.add(tmpList);
                      }
                }
          }
          
          return resultList;
  }
	
	/*@SuppressWarnings("unchecked")
	@Override
	public List<Pcesthtt> getEstApprovalReport(String deptId, BigDecimal status,	String value, String webAppName) {
		//getEntityManager(webAppName);
		//List<String> authorizedList=securityBeanRemote.getAuthorizedCostCenters(userName)
		Query query=null;
		if (value.equals("ES")){
			query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND stdCost <= 25000  order by g.id.estimateNo");
			query.setParameter("deptId", deptId);
			query.setParameter("status", status);
			List<Pcesthtt> list = query.getResultList();
			return list;
	    }else if (value.equals("EA")){
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND g.stdCost > 25000 AND g.stdCost <= 50000 order by g.id.estimateNo");//BETWEEN 15 and 19
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND stdCost BETWEEN 25000 and 50000");
	    	query.setParameter("deptId", deptId);
			query.setParameter("status", status);
			List<Pcesthtt> list = query.getResultList();
			return list;
	    }else if (value.equals("AE") ||value.equals("EE") ){
	    	List<String> areaDeptIdList=gldeptmDaoRemote.findAreaDeptIdList(deptId, webAppName);
	    	//areaDeptIdList=null;
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:areaDeptIdList) AND g.status = :status  AND stdCost > 50000 AND stdCost <= 100000 order by g.id.estimateNo");
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:areaDeptIdList)  AND g.status = :status");
	    	query.setParameter("areaDeptIdList", areaDeptIdList);
			query.setParameter("status", status);
			List<Pcesthtt> list = query.getResultList();
			return list;
	    }else if (value.equals("DGM")){
	    	List<String> dgmDeptIdList=gldeptmDaoRemote.findDgmDeptIdList(deptId, webAppName);
	    	System.out.print(dgmDeptIdList);
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:dgmDeptIdList) AND g.status = :status  AND stdCost > 100000 AND stdCost <= 200000 order by g.id.estimateNo");
	    	query.setParameter("dgmDeptIdList", dgmDeptIdList);
			query.setParameter("status", status);
			List<Pcesthtt> list = query.getResultList();
			return list;
	    }else if (value.equals("AGM")){
	    	List<String> agmDeptIdList=gldeptmDaoRemote.findAgmDeptIdList(deptId, webAppName);
	    	
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:agmDeptIdList) AND g.status = :status  AND 200000 < stdCost order by g.id.estimateNo");
	    	query.setParameter("agmDeptIdList", agmDeptIdList);
			query.setParameter("status", status);
			List<Pcesthtt> list = query.getResultList();
			return list;
	    }else{
	    	return null;
	    }
		//query.setParameter("deptId", deptId);
		//query.setParameter("status", status);
		
	}*/
	
	/*@SuppressWarnings("unchecked")
	@Override
	public List<EstimateDisplay> getEstApprovalReport(String userName, String deptId, String authorityLevel, String webAppName) {
		BigDecimal status = null;
		if (authorityLevel.equals("ES")){
			status=new BigDecimal(44); 
	    }else if (authorityLevel.equals("EA")){
	    	status=new BigDecimal(45); 
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	status=new BigDecimal(46); 
	    }else if (authorityLevel.equals("DGM")){
	    	status=new BigDecimal(47); 
	    }else if (authorityLevel.equals("AGM")){
	    	status=new BigDecimal(48); 
	    }
		List<String> authorizedList=securityBeanRemote.getAuthorizedCostCenters(userName);
		System.out.print(authorizedList);
		if (authorizedList.size()==0){
			authorizedList.add(deptId);
		}
		System.out.print(authorizedList);
		Query query=null;
		if (authorityLevel.equals("ES")){
			//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) =:deptId AND g.status = :status  AND stdCost <= 25000  order by g.id.estimateNo");
			query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) =:deptId AND g.status = :status order by g.id.estimateNo");
			query.setParameter("deptId", deptId);
			query.setParameter("status", status);
	    }else if (authorityLevel.equals("EA")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND g.stdCost > 25000 AND g.stdCost <= 50000 order by g.id.estimateNo");//BETWEEN 15 and 19
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND stdCost BETWEEN 25000 and 50000");
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  order by g.id.estimateNo");//BETWEEN 15 and 19
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	//List<String> areaDeptIdList=gldeptmDaoRemote.findAreaDeptIdList(deptId);
	    	//areaDeptIdList=null;
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND stdCost > 50000 AND stdCost <= 100000 order by g.id.estimateNo");
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:areaDeptIdList)  AND g.status = :status");
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  order by g.id.estimateNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
	    }else if (authorityLevel.equals("DGM")){
	    	//List<String> dgmDeptIdList=gldeptmDaoRemote.findDgmDeptIdList(deptId);
	    	System.out.print(authorizedList);
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND stdCost > 100000 AND stdCost <= 200000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  order by g.id.estimateNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
	    }else if (authorityLevel.equals("AGM")){
	    	//List<String> agmDeptIdList=gldeptmDaoRemote.findAgmDeptIdList(deptId);
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND 200000 < stdCost order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  order by g.id.estimateNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
	    }
		//query.setParameter("deptId", deptId);
		//query.setParameter("status", status);
		List<EstimateDisplay> list = query.getResultList();
		return list;
	}*/
	
	/*@SuppressWarnings("unchecked") 
	@Override 
	public List<EstimateDisplay> getEstApprovalReport(String userName, String deptId, String authorityLevel, String webAppName) {
		//getEntityManager(webAppName);
		BigDecimal status = null;
		if (authorityLevel.equals("ES")){
			status=new BigDecimal(44); 
	    }else if (authorityLevel.equals("EA")){
	    	status=new BigDecimal(45); 
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	status=new BigDecimal(46); 
	    }else if (authorityLevel.equals("CE") ){
	    	status=new BigDecimal(47); 
	    }else if (authorityLevel.equals("DGM")){
	    	status=new BigDecimal(48); 
	    }else if (authorityLevel.equals("AGM")){
	    	status=new BigDecimal(49); 
	    }
		List<String> authorizedList=securityBeanRemote.getAuthorizedCostCenters(userName, webAppName);
		System.out.print(authorizedList);
		if (authorizedList.size()==0){
			authorizedList.add(deptId);
		}
		System.out.print(authorizedList);
		Query query=null;
		//String qryStr ="SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId)= :deptId AND  p.status = :status";
		if (authorityLevel.equals("ES")){
			//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) =:deptId AND g.status = :status  AND stdCost <= 25000  order by g.id.estimateNo");
			query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId)= :deptId AND  p.status = :status order by p.id.estimateNo");
			query.setParameter("deptId", deptId);
			query.setParameter("status", status);
			List<EstimateDisplay> list = query.getResultList();
			return list;
	    }else if (authorityLevel.equals("EA")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND g.stdCost > 25000 AND g.stdCost <= 50000 order by g.id.estimateNo");//BETWEEN 15 and 19
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND stdCost BETWEEN 25000 and 50000");
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  order by g.id.estimateNo");//BETWEEN 15 and 19
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  order by g.id.estimateNo");//BETWEEN 15 and 19
	    	query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId) IN (:authorizedList) AND  p.status = :status order by p.id.estimateNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			List<EstimateDisplay> list = query.getResultList();
			return list;
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	//List<String> areaDeptIdList=gldeptmDaoRemote.findAreaDeptIdList(deptId);
	    	//areaDeptIdList=null;
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND stdCost > 50000 AND stdCost <= 100000 order by g.id.estimateNo");
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:areaDeptIdList)  AND g.status = :status");
	    	query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId) IN (:authorizedList) AND  p.status = :status order by p.id.estimateNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			List<EstimateDisplay> list = query.getResultList();
			return list;
	    }else if (authorityLevel.equals("CE")  ){
	    	//List<String> areaDeptIdList=gldeptmDaoRemote.findAreaDeptIdList(deptId);
	    	//areaDeptIdList=null;
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND stdCost > 50000 AND stdCost <= 100000 order by g.id.estimateNo");
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:areaDeptIdList)  AND g.status = :status");
	    	query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId) IN (:authorizedList) AND  p.status = :status order by p.id.estimateNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			List<EstimateDisplay> list = query.getResultList();
			return list;	
	    }else if (authorityLevel.equals("DGM")){
	    	//List<String> dgmDeptIdList=gldeptmDaoRemote.findDgmDeptIdList(deptId);
	    	System.out.print(authorizedList);
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND stdCost > 100000 AND stdCost <= 200000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId) IN (:authorizedList) AND  p.status = :status order by p.id.estimateNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			List<EstimateDisplay> list = query.getResultList();
			return list;
	    }else if (authorityLevel.equals("AGM")){
	    	//List<String> agmDeptIdList=gldeptmDaoRemote.findAgmDeptIdList(deptId);
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND 200000 < stdCost order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId) IN (:authorizedList) AND  p.status = :status order by p.id.estimateNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			List<EstimateDisplay> list = query.getResultList();
			return list;
	    }else {
	    	return null;
	    }
		//query.setParameter("deptId", deptId);
		//query.setParameter("status", status);
		
	}*/
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EstimateDisplay> getEstApprovalReport(String userName, String deptId, String authorityLevel, String applicationType, String webAppName) {
		//getEntityManager(webAppName);
		BigDecimal status = null;
		if (authorityLevel.equals("ES")){
			status=new BigDecimal(44); 
	    }else if (authorityLevel.equals("EA")){
	    	status=new BigDecimal(45); 
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	status=new BigDecimal(46); 
	    }else if (authorityLevel.equals("CE")  ){
	    	status=new BigDecimal(47); 
	    }else if (authorityLevel.equals("DGM")){
	    	status=new BigDecimal(48); 
	    }else if (authorityLevel.equals("AGM")){
	    	status=new BigDecimal(49); 
	    }
		List<String> authorizedList=securityBeanRemote.getAuthorizedCostCenters(userName, webAppName);
		System.out.print(userName+" "+" "+webAppName+" "+authorizedList);
		if (authorizedList.size()==0){
			authorizedList.add(deptId);
		}
		System.out.print(authorizedList);
		Query query=null;
		//String qryStr ="SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId)= :deptId AND  p.status = :status";
		if (authorityLevel.equals("ES")){
			//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) =:deptId AND g.status = :status  AND stdCost <= 25000  order by g.id.estimateNo");
			query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId)= :deptId AND  p.status = :status AND a.applicationType=:applicationType order by p.id.estimateNo");
			query.setParameter("deptId", deptId);
			query.setParameter("status", status);
			query.setParameter("applicationType", applicationType);
			List<EstimateDisplay> list = query.getResultList();
			return list;
	    }else if (authorityLevel.equals("EA")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND g.stdCost > 25000 AND g.stdCost <= 50000 order by g.id.estimateNo");//BETWEEN 15 and 19
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND stdCost BETWEEN 25000 and 50000");
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  order by g.id.estimateNo");//BETWEEN 15 and 19
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  order by g.id.estimateNo");//BETWEEN 15 and 19
	    	query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId) IN (:authorizedList) AND  p.status = :status AND a.applicationType=:applicationType order by p.id.estimateNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			query.setParameter("applicationType", applicationType);
			List<EstimateDisplay> list = query.getResultList();
			return list;
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	//List<String> areaDeptIdList=gldeptmDaoRemote.findAreaDeptIdList(deptId);
	    	//areaDeptIdList=null;
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND stdCost > 50000 AND stdCost <= 100000 order by g.id.estimateNo");
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:areaDeptIdList)  AND g.status = :status");
	    	query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId) IN (:authorizedList) AND  p.status = :status AND a.applicationType=:applicationType  order by p.id.estimateNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			query.setParameter("applicationType", applicationType);
			List<EstimateDisplay> list = query.getResultList();
			return list;
	    }else if (authorityLevel.equals("CE") ){
	    	//List<String> areaDeptIdList=gldeptmDaoRemote.findAreaDeptIdList(deptId);
	    	//areaDeptIdList=null;
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND stdCost > 50000 AND stdCost <= 100000 order by g.id.estimateNo");
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:areaDeptIdList)  AND g.status = :status");
	    	query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId) IN (:authorizedList) AND  p.status = :status AND a.applicationType=:applicationType  order by p.id.estimateNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			query.setParameter("applicationType", applicationType);
			List<EstimateDisplay> list = query.getResultList();
			System.out.println("CE");
			return list;	
	    }else if (authorityLevel.equals("DGM")){
	    	//List<String> dgmDeptIdList=gldeptmDaoRemote.findDgmDeptIdList(deptId);
	    	System.out.print(authorizedList);
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND stdCost > 100000 AND stdCost <= 200000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId) IN (:authorizedList) AND  p.status = :status AND a.applicationType=:applicationType  order by p.id.estimateNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			query.setParameter("applicationType", applicationType);
			List<EstimateDisplay> list = query.getResultList();
			return list;
	    }else if (authorityLevel.equals("AGM")){
	    	//List<String> agmDeptIdList=gldeptmDaoRemote.findAgmDeptIdList(deptId);
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND 200000 < stdCost order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId) IN (:authorizedList) AND  p.status = :status AND a.applicationType=:applicationType  order by p.id.estimateNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			query.setParameter("applicationType", applicationType);
			List<EstimateDisplay> list = query.getResultList();
			return list;
	    }else{
	    	return null;
	    }
		//query.setParameter("deptId", deptId);
		//query.setParameter("status", status);
		
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<EstimateDisplay> getEstApprovalReportMT_SA(String userName, String deptId, String authorityLevel, String applicationType, String webAppName) {
		//getEntityManager(webAppName);
		BigDecimal status = null;
		if (authorityLevel.equals("ES")){
			status=new BigDecimal(44); 
	    }else if (authorityLevel.equals("EA")){
	    	status=new BigDecimal(45); 
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	status=new BigDecimal(46); 
	    }else if (authorityLevel.equals("CE")  ){
	    	status=new BigDecimal(47); 
	    }else if (authorityLevel.equals("DGM")){
	    	status=new BigDecimal(48); 
	    }else if (authorityLevel.equals("AGM")){
	    	status=new BigDecimal(49); 
	    }
		List<String> authorizedList=securityBeanRemote.getAuthorizedCostCenters(userName, webAppName);
		System.out.print(userName+" "+" "+webAppName+" "+authorizedList);
		if (authorizedList.size()==0){
			authorizedList.add(deptId);
		}
		System.out.print(authorizedList);
		Query query=null;
		//String qryStr ="SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId)= :deptId AND  p.status = :status";
		if (authorityLevel.equals("ES")){
			//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) =:deptId AND g.status = :status  AND stdCost <= 25000  order by g.id.estimateNo");
			query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId)= :deptId AND  p.status = :status AND (a.applicationType='MT' OR a.applicationType='LS' OR a.applicationType='PS' OR a.applicationType='SA' OR a.applicationType='BD' OR a.applicationType='CC'  OR a.applicationType='CS' OR a.applicationType= 'CO'  OR a.applicationType='TR'  OR a.applicationType='TP'  OR a.applicationType='MR' OR a.applicationType= 'PF' OR a.applicationType= 'MF' OR a.applicationType= 'DB' OR a.applicationType= 'RM') order by p.id.estimateNo");
			query.setParameter("deptId", deptId);
			query.setParameter("status", status);
			//query.setParameter("applicationType", applicationType);
			List<EstimateDisplay> list = query.getResultList();
			return list;
	    }else if (authorityLevel.equals("EA")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND g.stdCost > 25000 AND g.stdCost <= 50000 order by g.id.estimateNo");//BETWEEN 15 and 19
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND stdCost BETWEEN 25000 and 50000");
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  order by g.id.estimateNo");//BETWEEN 15 and 19
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  order by g.id.estimateNo");//BETWEEN 15 and 19
	    	query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId) IN (:authorizedList) AND  p.status = :status AND (a.applicationType='MT' OR a.applicationType='LS' OR a.applicationType='PS' OR a.applicationType='SA' OR a.applicationType='BD' OR a.applicationType='CC'  OR a.applicationType='CS' OR a.applicationType= 'CO'  OR a.applicationType='TR'  OR a.applicationType='TP'  OR a.applicationType='MR' OR a.applicationType= 'PF' OR a.applicationType= 'MF' OR a.applicationType= 'DB' OR a.applicationType= 'RM') order by p.id.estimateNo");
	    	query.setParameter("authorizedList", authorizedList);
	    	query.setParameter("status", status);
	    	//query.setParameter("applicationType", applicationType);
	    	List<EstimateDisplay> list = query.getResultList();
			return list;
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	//List<String> areaDeptIdList=gldeptmDaoRemote.findAreaDeptIdList(deptId);
	    	//areaDeptIdList=null;
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND stdCost > 50000 AND stdCost <= 100000 order by g.id.estimateNo");
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:areaDeptIdList)  AND g.status = :status");
	    	query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId) IN (:authorizedList) AND  p.status = :status AND (a.applicationType='MT' OR a.applicationType='LS' OR a.applicationType='PS' OR a.applicationType='SA' OR a.applicationType='BD' OR a.applicationType='CC'  OR a.applicationType='CS' OR a.applicationType= 'CO'  OR a.applicationType='TR'  OR a.applicationType='TP'  OR a.applicationType='MR' OR a.applicationType= 'PF' OR a.applicationType= 'MF' OR a.applicationType= 'DB' OR a.applicationType= 'RM') order by p.id.estimateNo");
	    	query.setParameter("authorizedList", authorizedList);
	    	query.setParameter("status", status);
	    	//query.setParameter("applicationType", applicationType);
	    	List<EstimateDisplay> list = query.getResultList();
			return list;
	    }else if (authorityLevel.equals("CE") ){
	    	//List<String> areaDeptIdList=gldeptmDaoRemote.findAreaDeptIdList(deptId);
	    	//areaDeptIdList=null;
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND stdCost > 50000 AND stdCost <= 100000 order by g.id.estimateNo");
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:areaDeptIdList)  AND g.status = :status");
	    	query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId) IN (:authorizedList) AND  p.status = :status AND (a.applicationType='MT' OR a.applicationType='LS' OR a.applicationType='PS' OR a.applicationType='SA' OR a.applicationType='BD' OR a.applicationType='CC'  OR a.applicationType='CS' OR a.applicationType= 'CO'  OR a.applicationType='TR'  OR a.applicationType='TP'  OR a.applicationType='MR' OR a.applicationType= 'PF' OR a.applicationType= 'MF' OR a.applicationType= 'DB' OR a.applicationType= 'RM') order by p.id.estimateNo");
	    	query.setParameter("authorizedList", authorizedList);
	    	query.setParameter("status", status);
	    	//query.setParameter("applicationType", applicationType);
	    	List<EstimateDisplay> list = query.getResultList();
	    	System.out.println("CE");
	    	return list;	
	    }else if (authorityLevel.equals("DGM")){
	    	//List<String> dgmDeptIdList=gldeptmDaoRemote.findDgmDeptIdList(deptId);
	    	System.out.print(authorizedList);
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND stdCost > 100000 AND stdCost <= 200000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId) IN (:authorizedList) AND  p.status = :status AND (a.applicationType='MT' OR a.applicationType='LS' OR a.applicationType='PS' OR a.applicationType='SA' OR a.applicationType='BD' OR a.applicationType='CC'  OR a.applicationType='CS' OR a.applicationType= 'CO'  OR a.applicationType='TR'  OR a.applicationType='TP'  OR a.applicationType='MR' OR a.applicationType= 'PF' OR a.applicationType= 'MF' OR a.applicationType= 'DB' OR a.applicationType= 'RM') order by p.id.estimateNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			//query.setParameter("applicationType", applicationType);
			List<EstimateDisplay> list = query.getResultList();
			return list;
	    }else if (authorityLevel.equals("AGM")){
	    	//List<String> agmDeptIdList=gldeptmDaoRemote.findAgmDeptIdList(deptId);
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND 200000 < stdCost order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId) IN (:authorizedList) AND  p.status = :status AND (a.applicationType='MT' OR a.applicationType='LS' OR a.applicationType='PS' OR a.applicationType='SA' OR a.applicationType='BD' OR a.applicationType='CC'  OR a.applicationType='CS' OR a.applicationType= 'CO'  OR a.applicationType='TR'  OR a.applicationType='TP'  OR a.applicationType='MR' OR a.applicationType= 'PF' OR a.applicationType= 'MF' OR a.applicationType= 'DB' OR a.applicationType= 'RM') order by p.id.estimateNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			//query.setParameter("applicationType", applicationType);
			List<EstimateDisplay> list = query.getResultList();
			return list;
	    }else{
	    	return null;
	    }
		//query.setParameter("deptId", deptId);
		//query.setParameter("status", status);
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EstimateDisplay> getEstApprovalStatusReport(String userName, String deptId, String authorityLevel, String applicationType, BigDecimal status, String isLoanApp, String webAppName) {
		//getEntityManager(webAppName);
		//status = null;
		//List<String> authorizedList=securityBeanRemote.getAuthorizedCostCenters(userName, webAppName);
		//System.out.print(authorizedList);
		////if (authorizedList.size()==0){
		//	authorizedList.add(deptId);
		//}
		//System.out.print(authorizedList);
		
		
		
		Query query=null;
		query = getEntityManager(webAppName).createQuery("SELECT a.referenceNo FROM PivDetail a WHERE a.id.deptId=:deptId AND  a.referenceType=:referenceType and a.status=:status");//ORDER BY , a.applicationSubType ASC a.id.applicationNo DESC
		query.setParameter("deptId", deptId);
		if (isLoanApp.equals("N"))
			query.setParameter("referenceType", "EST");
		else
			query.setParameter("referenceType", "ELN");
		query.setParameter("status", PIVStatus.NEW);
		List<String> list1 = query.getResultList();
		System.out.println("dl.d.l "+list1+" "+list1.size());
		
		
		//
		if (list1.size()!=0) {
			query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId)= :deptId AND  p.status = :status AND a.applicationType=:applicationType AND a.isLoanApp=:isLoanApp AND TRIM(p.id.estimateNo) NOT IN(:list) order by TRIM(p.id.estimateNo) ");
			query.setParameter("deptId", deptId);
			query.setParameter("status", status);
			query.setParameter("applicationType", applicationType);
			query.setParameter("isLoanApp", isLoanApp);
			query.setParameter("list", list1);
			List<EstimateDisplay> list = query.getResultList();
			return list;
		}else{
			query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId)= :deptId AND  p.status = :status AND a.applicationType=:applicationType AND a.isLoanApp=:isLoanApp order by TRIM(p.id.estimateNo) ");
			query.setParameter("deptId", deptId);
			query.setParameter("status", status);
			query.setParameter("applicationType", applicationType);
			query.setParameter("isLoanApp", isLoanApp);
			List<EstimateDisplay> list = query.getResultList();
			return list;
		}
		
		/*
		 
		if (authorityLevel.equals("ES")){
			//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) =:deptId AND g.status = :status  AND stdCost <= 25000  order by g.id.estimateNo");
			query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId)= :deptId AND  p.status = :status AND a.applicationType=:applicationType order by p.id.estimateNo");
			query.setParameter("deptId", deptId);
			query.setParameter("status", status);
			query.setParameter("applicationType", applicationType);
			List<EstimateDisplay> list = query.getResultList();
			return list;
	    }else if (authorityLevel.equals("EA")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND g.stdCost > 25000 AND g.stdCost <= 50000 order by g.id.estimateNo");//BETWEEN 15 and 19
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND stdCost BETWEEN 25000 and 50000");
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  order by g.id.estimateNo");//BETWEEN 15 and 19
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  order by g.id.estimateNo");//BETWEEN 15 and 19
	    	query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId) IN (:authorizedList) AND  p.status = :status AND a.applicationType=:applicationType order by p.id.estimateNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			query.setParameter("applicationType", applicationType);
			List<EstimateDisplay> list = query.getResultList();
			return list;
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	//List<String> areaDeptIdList=gldeptmDaoRemote.findAreaDeptIdList(deptId);
	    	//areaDeptIdList=null;
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND stdCost > 50000 AND stdCost <= 100000 order by g.id.estimateNo");
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:areaDeptIdList)  AND g.status = :status");
	    	query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId) IN (:authorizedList) AND  p.status = :status AND a.applicationType=:applicationType  order by p.id.estimateNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			query.setParameter("applicationType", applicationType);
			List<EstimateDisplay> list = query.getResultList();
			return list;
	    }else if (authorityLevel.equals("CE") ){
	    	//List<String> areaDeptIdList=gldeptmDaoRemote.findAreaDeptIdList(deptId);
	    	//areaDeptIdList=null;
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND stdCost > 50000 AND stdCost <= 100000 order by g.id.estimateNo");
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:areaDeptIdList)  AND g.status = :status");
	    	query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId) IN (:authorizedList) AND  p.status = :status AND a.applicationType=:applicationType  order by p.id.estimateNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			query.setParameter("applicationType", applicationType);
			List<EstimateDisplay> list = query.getResultList();
			return list;	
	    }else if (authorityLevel.equals("DGM")){
	    	//List<String> dgmDeptIdList=gldeptmDaoRemote.findDgmDeptIdList(deptId);
	    	System.out.print(authorizedList);
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND stdCost > 100000 AND stdCost <= 200000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId) IN (:authorizedList) AND  p.status = :status AND a.applicationType=:applicationType  order by p.id.estimateNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			query.setParameter("applicationType", applicationType);
			List<EstimateDisplay> list = query.getResultList();
			return list;
	    }else if (authorityLevel.equals("AGM")){
	    	//List<String> agmDeptIdList=gldeptmDaoRemote.findAgmDeptIdList(deptId);
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND 200000 < stdCost order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId) IN (:authorizedList) AND  p.status = :status AND a.applicationType=:applicationType  order by p.id.estimateNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			query.setParameter("applicationType", applicationType);
			List<EstimateDisplay> list = query.getResultList();
			return list;
	    }else{
	    	return null;
	    }
		//query.setParameter("deptId", deptId);
		//query.setParameter("status", status);
		*/
	}
	
	/*@SuppressWarnings("unchecked")
	@Override
	public List<Pcesthtt> getEstApprovalReport1(String userName, String deptId, BigDecimal status,	String value, String webAppName) {
		//getEntityManager(webAppName);
		List<String> authorizedList=securityBeanRemote.getAuthorizedCostCenters(userName, webAppName);
		System.out.print(authorizedList);
		if (authorizedList.size()==0){
			authorizedList.add(deptId);
		}
		System.out.print(authorizedList);
		Query query=null;
		if (value.equals("ES")){
			query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) =:deptId AND g.status = :status  AND stdCost <= 25000  order by g.id.estimateNo");
			query.setParameter("deptId", deptId);
			query.setParameter("status", status);
			List<Pcesthtt> list = query.getResultList();
			return list;
	    }else if (value.equals("EA")){
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND g.stdCost > 25000 AND g.stdCost <= 50000 order by g.id.estimateNo");//BETWEEN 15 and 19
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND stdCost BETWEEN 25000 and 50000");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			List<Pcesthtt> list = query.getResultList();
			return list;
	    }else if (value.equals("AE") ||value.equals("EE") ){
	    	//List<String> areaDeptIdList=gldeptmDaoRemote.findAreaDeptIdList(deptId);
	    	//areaDeptIdList=null;
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND stdCost > 50000 AND stdCost <= 100000 order by g.id.estimateNo");
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:areaDeptIdList)  AND g.status = :status");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			List<Pcesthtt> list = query.getResultList();
			return list;
	    }else if (value.equals("DGM")){
	    	//List<String> dgmDeptIdList=gldeptmDaoRemote.findDgmDeptIdList(deptId);
	    	System.out.print(authorizedList);
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND stdCost > 100000 AND stdCost <= 200000 order by g.id.estimateNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			List<Pcesthtt> list = query.getResultList();
			return list;
	    }else if (value.equals("AGM")){
	    	//List<String> agmDeptIdList=gldeptmDaoRemote.findAgmDeptIdList(deptId);
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthtt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND 200000 < stdCost order by g.id.estimateNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			List<Pcesthtt> list = query.getResultList();
			return list;
	    }else {
	    	return null;
	    }
		//query.setParameter("deptId", deptId);
		//query.setParameter("status", status);
		
	}*/
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FundSource> getFundSource(String deptId, String webAppName) {
		//getEntityManager(webAppName);  
		//List<FundSource>  resultList = null;
		String warehouse=inwrhmapDaoRemote.mapWarehouse(deptId, webAppName);
			List<FundSource> list = null;
	      try
	      {
	            String qryStr = " select new estimate.model.FundSource(TRIM(d.id.fundSource),TRIM(d.id.fundId), TRIM(h.name)) "+
	                                    " from Pcfunddm d, Pcfundhm h "+
	                                    " where d.id.deptId = :deptId " +
	                                    " and d.id.fundSource = h.fundSource "+
	                                    " order by h.name ";
	                                    
	            
	            Query query = getEntityManager(webAppName).createQuery(qryStr);
	            query.setParameter("deptId", warehouse);
	              
	             // List<Map> list = query.getResultList();
	            list = query.getResultList();
	            System.out.println(list.size());
	            /*  if(!list.isEmpty())
	                  {
	                        resultList =  new ArrayList<FundSource>();
	                        for(int i=0;i<list.size();i++)
	                        {
	                              Map map = list.get(i);
	                              String name  = map.values().toArray()[0].toString();
	                              String fundSource = map.values().toArray()[2].toString();
	                              String fundId = map.values().toArray()[1].toString();
	                              
	                              FundSource fs = new FundSource();
	                              fs.setFundId(fundId);
	                              fs.setFundSource(fundSource);
	                              fs.setName(name);
	                              resultList.add(fs);
	                              
	                        }
	                  }*/
	      }
	      catch(Exception e){
	            
	            e.printStackTrace();
	      }
	      return list;
	    }
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FundSource> getFundSourceJV(String deptId, String webAppName) {
		//getEntityManager(webAppName);  
		//List<FundSource>  resultList = null;
		String warehouse=inwrhmapDaoRemote.mapWarehouse(deptId, webAppName);
			List<FundSource> list = null;
	      try
	      {
	    	  String qryStr = " select new estimate.model.FundSource(TRIM(d.id.fundSource),TRIM(d.id.fundId), TRIM(h.name)) "+
              " from Pcfunddm d, Pcfundhm h "+
              " where d.id.deptId = :deptId " +
              " and d.id.fundSource = h.fundSource "+
              " order by h.name ";
	            
	            Query query = getEntityManager(webAppName).createQuery(qryStr);
	            query.setParameter("deptId", deptId);
	              
	             // List<Map> list = query.getResultList();
	            list = query.getResultList();
	            System.out.println(list.size());
	            /*  if(!list.isEmpty())
	                  {
	                        resultList =  new ArrayList<FundSource>();
	                        for(int i=0;i<list.size();i++)
	                        {
	                              Map map = list.get(i);
	                              String name  = map.values().toArray()[0].toString();
	                              String fundSource = map.values().toArray()[2].toString();
	                              String fundId = map.values().toArray()[1].toString();
	                              
	                              FundSource fs = new FundSource();
	                              fs.setFundId(fundId);
	                              fs.setFundSource(fundSource);
	                              fs.setName(name);
	                              resultList.add(fs);
	                              
	                        }
	                  }*/
	      }
	      catch(Exception e){
	            
	            e.printStackTrace();
	      }
	      return list;
	    }

	
	@Override
	public List<Pcfunddm> getFundSourceList(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		//String warehouse=inwrhmapDaoRemote.mapWarehouse(deptId, webAppName);
		return pcfunddmDaoRemote.getFundSourceList(deptId, webAppName);
		
	}
	
	@Override
	public void estimatePost(List<String> list, String costCenterNo, String webAppName) {
		//getEntityManager(webAppName);
		try{
			System.out.println("!!!!!!!!!! "+list+" "+costCenterNo);
			CityMap cityMap=new CityMap();
			for(int j=0; j<=list.size()-1; j++){
					//System.out.println("222222222222 "+list+" "+costCenterNo);
				if (costCenterNo.equals("541.00") || costCenterNo.equals("542.00") || costCenterNo.equals("547.00") || costCenterNo.equals("548.00")){
					System.out.println("333333333333 "+list+" "+costCenterNo);
					String depotDeptId=cityMap.depotDirect(costCenterNo, list.get(j));
					//System.out.println("444444444444 "+list+" "+costCenterNo+" "+depotDeptId);
					pcesthttDaoRemote.changeStatusPcesthtt(list.get(j), costCenterNo,new  Long(EstimateStatus.EST_POSTED), webAppName);
					pcesthttDaoRemote.changeCostCenterNoPcesthtt(list.get(j), costCenterNo, depotDeptId, webAppName);
					pcestdttDaoRemote.changeCostCenterNoPcestdtt(list.get(j), costCenterNo, depotDeptId, webAppName);
					//System.out.println("555555555555 "+list+" "+costCenterNo);
				}else{
					System.out.println("6666666666666 "+list+" "+costCenterNo);
					pcesthttDaoRemote.changeStatusPcesthtt(list.get(j), costCenterNo,new  Long(EstimateStatus.EST_POSTED), webAppName);
				}
			}
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EstimateDisplay> getEstimateList(String deptId,BigDecimal status, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr ="SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId)= :deptId AND  p.status = :status order by p.id.estimateNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		List<EstimateDisplay> list = query.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EstimateDisplay> getEstimateList(String deptId,BigDecimal status, String applicationType, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr ="SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId)= :deptId AND  p.status = :status AND a.applicationType=:applicationType order by p.id.estimateNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		query.setParameter("applicationType", applicationType);
		List<EstimateDisplay> list = query.getResultList();
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	
	public List<Pcesthtt> getDetailEstimatesList(String deptId,List<Long> status, String userId,String assignTo, String webAppName) {
		//getEntityManager(webAppName);
		//String qryStr ="SELECT p FROM Pcesthtt p WHERE trim(p.id.deptId)= :deptId AND  p.status = :status AND p.entBy=:entryBy AND order by p.id.estimateNo";
		StringBuffer buff = new StringBuffer();
		buff.append("SELECT p FROM Pcesthtt p WHERE trim(p.id.deptId)= :deptId AND  p.status in (:status) AND trim(p.id.revNo)=:revNo ");
		if(userId != null){
			buff.append(" AND trim(p.entBy)=:entryBy ");
		}
		if(assignTo != null){
			buff.append(" AND trim(p.aprUid5)=:assignTo ");
		}
		
		buff.append(" order by p.id.estimateNo");
		Query query = getEntityManager(webAppName).createQuery(buff.toString());
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		query.setParameter("revNo", "2");
		if(userId != null){
			query.setParameter("entryBy", userId.trim());
		}
		if(assignTo != null){
			query.setParameter("assignTo", assignTo.trim());
		}
		List<Pcesthtt> list = query.getResultList();
		return list;
	}
	
	public List<Pcesthtt> getDetailEstimatesListOrderByFundSource(String deptId,List<Long> status, String userId,String assignTo, String webAppName) {
		//getEntityManager(webAppName);
		//String qryStr ="SELECT p FROM Pcesthtt p WHERE trim(p.id.deptId)= :deptId AND  p.status = :status AND p.entBy=:entryBy AND order by p.id.estimateNo";
		StringBuffer buff = new StringBuffer();
		buff.append("SELECT p FROM Pcesthtt p WHERE trim(p.id.deptId)= :deptId AND  p.status in (:status) AND trim(p.id.revNo)=:revNo ");
		if(userId != null){
			buff.append(" AND trim(p.entBy)=:entryBy ");
		}
		if(assignTo != null){
			buff.append(" AND trim(p.aprUid5)=:assignTo ");
		}
		
		
		buff.append(" order by p.fundSource");
		Query query = getEntityManager(webAppName).createQuery(buff.toString());
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		query.setParameter("revNo", "2");
		if(userId != null){
			query.setParameter("entryBy", userId.trim());
		}
		if(assignTo != null){
			query.setParameter("assignTo", assignTo.trim());
		}
		List<Pcesthtt> list = query.getResultList();
		return list;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	
	public List<Pcesthtt> getVSLEstimatesList(String deptId,List<Long> status, String userId, String webAppName) {
		//getEntityManager(webAppName);
		//String qryStr ="SELECT p FROM Pcesthtt p WHERE trim(p.id.deptId)= :deptId AND  p.status = :status AND p.entBy=:entryBy AND order by p.id.estimateNo";
		StringBuffer buff = new StringBuffer();
		buff.append("SELECT p FROM Pcesthtt p WHERE trim(p.id.deptId)= :deptId AND  p.status in (:status) AND trim(p.id.revNo)=:revNo AND p.id.estimateNo like '%SPS%' ");
		if(userId != null){
			buff.append(" AND trim(p.entBy)=:entryBy ");
		}
		
		buff.append(" order by p.id.estimateNo");
		Query query = getEntityManager(webAppName).createQuery(buff.toString());
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		query.setParameter("revNo", "2");
		if(userId != null){
			query.setParameter("entryBy", userId.trim());
		}
		List<Pcesthtt> list = query.getResultList();
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<EstimateDisplay> getEstimateListMA_SA(String deptId,BigDecimal status, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr ="SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId)= :deptId AND  p.status = :status AND (a.applicationType='MT' OR a.applicationType='LS' OR a.applicationType='PS' OR a.applicationType='SA' OR a.applicationType='BD' OR a.applicationType='CC' OR a.applicationType='CS' OR a.applicationType='CO' OR a.applicationType='TR' OR a.applicationType='MR' OR a.applicationType='PF' OR a.applicationType='MF' OR a.applicationType='DB'  OR a.applicationType='RM') order by p.id.estimateNo ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		//query.setParameter("applicationType", applicationType);
		List<EstimateDisplay> list = query.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MaterialGrid> getServiceWireMaterialGrid(String deptId, long phase, long connectionType, String wiringType, Double serviceLength, String webAppName) {
		//getEntityManager(webAppName);
		String warehouse=inwrhmapDaoRemote.mapWarehouse(deptId, webAppName);
		String qryStr = "select new estimate.model.MaterialGrid (TRIM(b.id.matCd), TRIM(c.matNm), b.unitPrice, b.uomCd, a.measurementFactor, a.extraQty) " +
				"from Spsvcwrm a, Inwrhmtm b, Inmatm c " +
				"where a.id.matCd=b.id.matCd " +
				"AND b.id.matCd=c.id.matCd " +
				"AND b.id.deptId= :warehouse " +
				"AND a.id.deptId= :deptId " +
				"AND a.id.phase = :phase " +
				"AND  a.id.connectionType = :connectionType " +
				"AND  a.id.wiringType = :wiringType " +
				" and b.status ='2' "+
                " and c.status ='2' "+
				" and TRIM(b.id.gradeCd) ='NEW' ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("phase", phase);
		query.setParameter("connectionType", connectionType);
		query.setParameter("wiringType", wiringType);
		query.setParameter("warehouse", warehouse);
		query.setParameter("deptId", deptId);
		List<MaterialGrid> list = query.getResultList();
		for(int i=0; i<=list.size()-1; i++){
			list.get(i).setEstimateQty(new BigDecimal ((Double.parseDouble(list.get(i).getMeasurementFactor().toString())*serviceLength)+Double.parseDouble(list.get(i).getExtraQty().toString())));
		}
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MaterialGrid> getConductorMaterialGrid(String deptId,long phase, long connectionType, String wiringType, String conductorType, Double conductorLength, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String warehouse=inwrhmapDaoRemote.mapWarehouse(deptId, webAppName);
		String qryStr = "select new estimate.model.MaterialGrid (TRIM(b.id.matCd), TRIM(c.matNm), b.unitPrice, b.uomCd ,a.measurementFactor) " +
				" from Spcndmat a, Inwrhmtm b, Inmatm c " +
				" where a.id.matCd=b.id.matCd " +
				" AND b.id.matCd=c.id.matCd " +
				" AND b.id.deptId= :warehouse " +
				" AND a.id.deptId= :deptId " +
				" AND a.id.phase = :phase " +
				" AND  a.id.connectionType = :connectionType " +
				" AND  a.id.wiringType = :wiringType " +
				" AND b.status ='2' "+
                " AND c.status ='2' "+
				" AND TRIM(b.id.gradeCd) ='NEW' "+
				" AND  a.id.conductorType = :conductorType ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("warehouse", warehouse);
		query.setParameter("deptId", deptId);
		query.setParameter("phase", phase);
		query.setParameter("connectionType", connectionType);
		query.setParameter("wiringType", wiringType);
		query.setParameter("conductorType", conductorType);
		List<MaterialGrid> list = query.getResultList();
		//return list;
		for(int i=0; i<=list.size()-1; i++){
			list.get(i).setEstimateQty(new BigDecimal (Double.parseDouble(list.get(i).getMeasurementFactor().toString())*conductorLength));
		}
				
		return list;
		
	}
	
	/*@SuppressWarnings("unchecked")
	@Override
	public List<MaterialGrid> getPoleTypeMaterialGrid(String deptId, long phase, String poleType, String fromConductor, String toConductor, int NoOfPoles, String webAppName) {
		//getEntityManager(webAppName);
		String warehouse=inwrhmapDaoRemote.mapWarehouse(deptId, webAppName);
		String qryStr = "select new estimate.model.MaterialGrid (TRIM(b.id.matCd), TRIM(c.matNm), b.unitPrice, a.matQty, b.uomCd ) " +
				"from Sppolemt a, Inwrhmtm b, Inmatm c " +
				"where a.id.matCd=b.id.matCd " +
				"AND b.id.matCd=c.id.matCd " +
				"AND b.id.deptId= :warehouse " +
				"AND a.id.deptId= :deptId " +
				"AND a.id.phase = :phase " +
				"AND a.id.poleType = :poleType " +
				"AND  a.id.fromConductor = :fromConductor " +
				"AND  a.id.toConductor = :toConductor " ;
		Query query = getEntityManager(webAppName).createQuery(qryStr);	
		query.setParameter("warehouse", warehouse);
		query.setParameter("deptId", deptId);
		query.setParameter("phase", phase);
		query.setParameter("poleType", poleType);
		query.setParameter("fromConductor", fromConductor);
		query.setParameter("toConductor", toConductor);
		List<MaterialGrid> list = query.getResultList();
		for(int i=0; i<=list.size()-1; i++){
			list.get(i).setEstimateQty(new BigDecimal (Double.parseDouble(list.get(i).getEstimateQty().toString())*NoOfPoles));
			list.get(i).setEstimateCost(new BigDecimal (Double.parseDouble(list.get(i).getEstimateQty().toString())*Double.parseDouble(list.get(i).getUnitPrice().toString())));
		}
		return list;
	}*/
	@SuppressWarnings("unchecked")
	@Override
	public List<MaterialGrid> getPoleTypeMaterialGrid(String deptId, long phase, String poleType, String fromConductor, String toConductor, int NoOfPoles, String webAppName) {
		//getEntityManager(webAppName);
		String warehouse=inwrhmapDaoRemote.mapWarehouse(deptId, webAppName);
		String qryStr = "select new estimate.model.MaterialGrid (TRIM(b.id.matCd), TRIM(c.matNm), b.unitPrice, a.matQty, b.uomCd ) " +
				"from Sppolemt a, Inwrhmtm b, Inmatm c " +
				"where a.id.matCd=b.id.matCd " +
				"AND b.id.matCd=c.id.matCd " +
				"AND b.id.deptId= :warehouse " +
				"AND a.id.deptId= :warehouse " +
				"AND a.id.phase = :phase " +
				"AND a.id.poleType = :poleType " +
				"AND  a.id.fromConductor = :fromConductor " +
				"AND  a.id.toConductor = :toConductor " +
				" AND b.status ='2' "+
                " AND c.status ='2' "+
				" AND TRIM(b.id.gradeCd) ='NEW' ";
				
		Query query = getEntityManager(webAppName).createQuery(qryStr);	
		query.setParameter("warehouse", warehouse);
		//query.setParameter("deptId", deptId);
		query.setParameter("phase", phase);
		query.setParameter("poleType", poleType);
		query.setParameter("fromConductor", fromConductor);
		query.setParameter("toConductor", toConductor);
		List<MaterialGrid> list = query.getResultList();
		for(int i=0; i<=list.size()-1; i++){
			list.get(i).setEstimateQty(new BigDecimal (Double.parseDouble(list.get(i).getEstimateQty().toString())*NoOfPoles));
			list.get(i).setEstimateCost(new BigDecimal (Double.parseDouble(list.get(i).getEstimateQty().toString())*Double.parseDouble(list.get(i).getUnitPrice().toString())));
		}
		return list;
	}

	
	
	/*@SuppressWarnings("unchecked")
	@Override
	public List<MaterialGrid> getStayMaterialGrid(String deptId, int noOfStays, String webAppName) {
		//getEntityManager(webAppName);
		String warehouse=inwrhmapDaoRemote.mapWarehouse(deptId, webAppName);
		System.out.println(warehouse);
		
		String qryStr = "select new estimate.model.MaterialGrid (TRIM(b.id.matCd), TRIM(c.matNm), b.unitPrice, a.matQty, b.uomCd ) " +
		"from Spstaymt a, Inwrhmtm b, Inmatm c " +
		"where a.id.matCd=b.id.matCd " +
		"AND b.id.matCd=c.id.matCd " +
		"AND b.id.deptId= :warehouse "+
		"AND a.id.deptId= :deptId ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("warehouse", warehouse);
		query.setParameter("deptId", deptId);
		List<MaterialGrid> list = query.getResultList();
		for(int i=0; i<=list.size()-1; i++){
			list.get(i).setEstimateQty(new BigDecimal (Double.parseDouble(list.get(i).getEstimateQty().toString())*noOfStays));
			list.get(i).setEstimateCost(new BigDecimal (Double.parseDouble(list.get(i).getEstimateQty().toString())*Double.parseDouble(list.get(i).getUnitPrice().toString())));
		}
		return list;
	}*/
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MaterialGrid> getStayMaterialGrid(String deptId, int noOfStays, String webAppName) {
		//getEntityManager(webAppName);
		String warehouse=inwrhmapDaoRemote.mapWarehouse(deptId, webAppName);
		System.out.println(warehouse);
		
		String qryStr = "select new estimate.model.MaterialGrid (TRIM(b.id.matCd), TRIM(c.matNm), b.unitPrice, a.matQty, b.uomCd ) " +
		"from Spstaymt a, Inwrhmtm b, Inmatm c " +
		"where a.id.matCd=b.id.matCd " +
		"AND b.id.matCd=c.id.matCd " +
		"AND b.id.deptId= :warehouse "+
		"AND a.id.deptId= :warehouse "+
		" AND b.status ='2' "+
        " AND c.status ='2' "+
		" AND TRIM(b.id.gradeCd) ='NEW' ";
		
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("warehouse", warehouse);
		//query.setParameter("deptId", deptId);
		List<MaterialGrid> list = query.getResultList();
		for(int i=0; i<=list.size()-1; i++){
			list.get(i).setEstimateQty(new BigDecimal (Double.parseDouble(list.get(i).getEstimateQty().toString())*noOfStays));
			list.get(i).setEstimateCost(new BigDecimal (Double.parseDouble(list.get(i).getEstimateQty().toString())*Double.parseDouble(list.get(i).getUnitPrice().toString())));
		}
		return list;
	}
	
	
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<MaterialGrid> getStrutMaterialGrid(String deptId, int noOfStruts, String webAppName) {
		//getEntityManager(webAppName);
		String warehouse=inwrhmapDaoRemote.mapWarehouse(deptId, webAppName);
		System.out.println(warehouse);
		
		String qryStr = "select new estimate.model.MaterialGrid (TRIM(b.id.matCd), TRIM(c.matNm), b.unitPrice, a.matQty, b.uomCd ) " +
		"from Spstrutm a, Inwrhmtm b, Inmatm c " +
		"where a.id.matCd=b.id.matCd " +
		"AND b.id.matCd=c.id.matCd " +
		"AND b.id.deptId= :warehouse "+
		"AND a.id.deptId= :deptId "+
		" AND b.status ='2' "+
        " AND c.status ='2' "+
		" AND TRIM(b.id.gradeCd) ='NEW' ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("warehouse", warehouse);
		query.setParameter("deptId", deptId);
		List<MaterialGrid> list = query.getResultList();
		for(int i=0; i<=list.size()-1; i++){
			list.get(i).setEstimateQty(new BigDecimal (Double.parseDouble(list.get(i).getEstimateQty().toString())*noOfStruts));
			list.get(i).setEstimateCost(new BigDecimal (Double.parseDouble(list.get(i).getEstimateQty().toString())*Double.parseDouble(list.get(i).getUnitPrice().toString())));
		}
		return list;
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public MaterialGrid getMaterialGridByMatCd(String deptId, String matCd, Double noOfItems, String webAppName) {
		//getEntityManager(webAppName);
		String warehouse=inwrhmapDaoRemote.mapWarehouse(deptId, webAppName);
		System.out.println(warehouse);
		
		String qryStr = "select new estimate.model.MaterialGrid (TRIM(b.id.matCd), TRIM(c.matNm), b.unitPrice, b.uomCd ) " +
		"from Inwrhmtm b, Inmatm c " +
		"where b.id.matCd=c.id.matCd " +
		"AND TRIM(b.id.matCd)=:matCd " +
		" and b.status ='2' "+
        " and c.status ='2' "+
        " and TRIM(b.id.gradeCd) ='NEW' "+
		"AND b.id.deptId= :warehouse ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("matCd", matCd);
		query.setParameter("warehouse", warehouse);
		List<MaterialGrid> list = query.getResultList();
		for(int i=0; i<=list.size()-1; i++){
			list.get(i).setEstimateQty(new BigDecimal (noOfItems));
			list.get(i).setEstimateCost(new BigDecimal (Double.parseDouble(list.get(i).getEstimateQty().toString())*Double.parseDouble(list.get(i).getUnitPrice().toString())));
		}
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void removeLabour(List<SpestlabPK> removeList, Pcesthtt pcesthtt, List<Pcestdtt> pcestdttList, Speststd speststd, String webAppName) {
		try{
			if (removeList!=null){
				
				for(int i=0; i<=removeList.size()-1; i++){
					Spestlab spestlab=spestlabDaoRemote.findById(removeList.get(i), webAppName);
					if (spestlab!=null){
						spestlabDaoRemote.removeSpestlab(spestlab, webAppName);
					}
					
				}
			}
			pcesthttDaoRemote.updatePcesthtt(pcesthtt, webAppName);
			//pcestdttDaoRemote.updatePcestdtt(pcestdtt, webAppName);
			if (pcestdttList!=null){
				for(int k=0; k<=pcestdttList.size()-1; k++){
					//System.out.println("444444444444444444"+listLabour.get(j));
					pcestdttDaoRemote.updatePcestdtt(pcestdttList.get(k), webAppName);
				}}
			if (speststd!=null){
				speststdDaoRemote.updateSpeststd(speststd, webAppName);
			}
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(String estimateNo, String deptId, Approval approval,String webAppName) {
		try{
			//if (addlist!=null){
			//for(int i=0; i<=addlist.size()-1; i++){
				//System.out.println("estimateNo "+estimateNo);
				Pcesthtt pcesthtt=pcesthttDaoRemote.findByEstimationNo(estimateNo, deptId, webAppName);
				SpeststdPK id=new SpeststdPK();
				id.setEstimateNo(estimateNo);
				id.setDeptId(deptId);
				Speststd speststd=speststdDaoRemote.findById(id, webAppName);
				//System.out.println(pcesthtt);
				//SpestlabPK pk=SpestlabPK();
				//pk.s
				List<Spestlab> spestlabList=spestlabDaoRemote.getAll(estimateNo, deptId, webAppName);
				
				String qryStr = "select a.id.pivNo from PivDetail a where a.referenceNo=:estimateNo AND a.id.deptId= :deptId AND a.status <> :status and (a.referenceType=:referenceType1 OR a.referenceType=:referenceType2)  ";
				Query query = getEntityManager(webAppName).createQuery(qryStr);
				query.setParameter("estimateNo", estimateNo);
				query.setParameter("deptId", deptId);
				query.setParameter("status", PIVStatus.DESTROYED);
				query.setParameter("referenceType1", "EST");
				query.setParameter("referenceType2", "ELN");
				List<String> list=query.getResultList();
				System.out.println(list);
				if(list.size()==0){
					if (pcesthtt.getStatus().equals(new BigDecimal(EstimateStatus.MODIFIED))){
						pcesthttDaoRemote.removePcesthtt(pcesthtt, webAppName);
						
						qryStr = "delete from Pcestdtt a where TRIM(a.id.estimateNo)=:estimateNo AND a.id.deptId= :deptId";
						query = getEntityManager(webAppName).createQuery(qryStr);
						query.setParameter("estimateNo", estimateNo);
						query.setParameter("deptId", deptId);
						query.executeUpdate();
						speststdDaoRemote.removeSpeststd(speststd, webAppName);
						//applicationDaoRemote.changeStatusApplication(pcesthtt.getId().getEstimateNo(), pcesthtt.getId().getDeptId(), AppStatus.ALLOCATED, webAppName);
						//applicationDaoRemote.changeStatusApplication(pcesthtt.getId().getEstimateNo(), pcesthtt.getId().getDeptId(), AppStatus.SERVICE_EST_CREATED, webAppName);
						spestedyDaoRemote.changeStatusAppointments(pcesthtt.getId().getEstimateNo().trim(), pcesthtt.getId().getDeptId(), AppointmentStatus.VISITED, webAppName);
						approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
						if (spestlabList!=null){
							for(int j=0; j<=spestlabList.size()-1; j++){
								spestlabDaoRemote.removeSpestlab(spestlabList.get(j), webAppName);
							}
						}
						
						
						//return "#Done";
					}else{
						//return "@Can not delete";
					}
				}else {
					//return "@PIV has been issued for estimate";
					
				}
				
				
			//}
				
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<LabourGrid> getDefaultPoleLabour(String deptId, String applicationType, String matCd, int noOfPoles, String webAppName) {
		//getEntityManager(webAppName);
		//String warehouse=inwrhmapDaoRemote.mapWarehouse(deptId, webAppName);
		//System.out.println(warehouse); 
		
		String qryStr = "select new estimate.model.LabourGrid (a.id.labourCode,  b.activityCode, b.activityName, TRIM(a.matCd),concat(concat(concat(b.activityName,' ('),trim(a.matCd)),')')," +
		"a.unitPrice, a.labourHours) from Splbpole a, Splaborm b where b.activityCode = a.activityCode and a.id.deptId = :deptId and a.applicationType = :applicationType and TRIM(a.matCd) = :matCd " ;
		//String qryStr = "select new map (a.id.labourCode, a.activity_name, trim(a.mat_cd) , a.unit_price) from Splbpole a, Splaborm b" ;
		//"a.unit_price) from Splbpole a, Splaborm b where b.activityCode = a.activityCode and a.id.dept_id = '422.10' and a.application_type = 'NC' and a.mat_cd = 'A0211' " ;
		
		
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		//query.setParameter("warehouse", warehouse);
		query.setParameter("deptId", deptId);
		query.setParameter("applicationType", applicationType);
		query.setParameter("matCd", matCd);
		
		List<LabourGrid> list = query.getResultList();
		//System.out.println("### "+list+" "+ noOfPoles);
			
		for(int i=0; i<=list.size()-1; i++){
			list.get(i).setItemQty(new BigDecimal(noOfPoles));
			list.get(i).setLabourCost(new BigDecimal (Double.parseDouble(list.get(i).getUnitPrice().toString())*noOfPoles));
			list.get(i).setLabourHours(new BigDecimal (Double.parseDouble(list.get(i).getUnitLabourHrs().toString())*noOfPoles));
		}
		
		return list;
	}
	
	@SuppressWarnings({ "unchecked" })//(dept_id, application_type, phase, span_type, desc , qty)
	@Override
	public List<LabourGrid> getDefaultServiceWireLabour(String deptId, String applicationType, String phase, String spanType, String noOfSpans, String webAppName) {
		//getEntityManager(webAppName);
		//String warehouse=inwrhmapDaoRemote.mapWarehouse(deptId, webAppName);
		//System.out.println(warehouse); 
		//
		String qryStr = "select  new estimate.model.LabourGrid (a.id.labourCode, b.activityCode, b.activityName,  " +
		" a.phase, a.spanType, a.unitPrice, a.labourHours) from Splbsrvc a, Splaborm b where b.activityCode = a.activityCode " +
		" and a.id.deptId = :deptId and a.applicationType = :applicationType and phase = :phase and a.spanType =:spanType " ;
		//String qryStr = "select new map (a.id.labourCode, a.activity_name, trim(a.mat_cd) , a.unit_price) from Splbpole a, Splaborm b" ;
		//"a.unit_price) from Splbpole a, Splaborm b where b.activityCode = a.activityCode and a.id.dept_id = '422.10' and a.application_type = 'NC' and a.mat_cd = 'A0211' " ;
		
		
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		//query.setParameter("warehouse", warehouse);
		query.setParameter("deptId", deptId);
		query.setParameter("applicationType", applicationType);
		query.setParameter("phase", phase);
		query.setParameter("spanType", spanType);
		
		
		List<LabourGrid> list = query.getResultList();
		System.out.println("### "+list+" "+ noOfSpans);
			
		for(int i=0; i<=list.size()-1; i++){
			list.get(i).setItemQty(new BigDecimal(noOfSpans));
			list.get(i).setLabourCost(new BigDecimal (Double.parseDouble(list.get(i).getUnitPrice().toString())*Double.parseDouble(noOfSpans)));
			list.get(i).setLabourHours(new BigDecimal (Double.parseDouble(list.get(i).getUnitLabourHrs().toString())*Double.parseDouble(noOfSpans)));
		}
		
		return list;
	}
	
	
	@SuppressWarnings({ "unchecked" })//(dept_id, application_type, phase,conductor_type, desc , qty)
	@Override
	public List<LabourGrid> getDefaultConductorLabour(String deptId, String applicationType, String phase, String conductorType, String wireLength, String webAppName) {
		//getEntityManager(webAppName);
		//String warehouse=inwrhmapDaoRemote.mapWarehouse(deptId, webAppName);
		//System.out.println(warehouse); 
		//
		String qryStr = "select  new estimate.model.LabourGrid (a.id.labourCode, b.activityCode, b.activityName,  " +
		" a.phase, a.conductorType, a.unitPrice, a.labourHours) from Splbcndt a, Splaborm b where b.activityCode = a.activityCode " +
		" and a.id.deptId = :deptId and a.applicationType = :applicationType and phase = :phase and a.conductorType =:conductorType " ;
		//String qryStr = "select new map (a.id.labourCode, a.activity_name, trim(a.mat_cd) , a.unit_price) from Splbpole a, Splaborm b" ;
		//"a.unit_price) from Splbpole a, Splaborm b where b.activityCode = a.activityCode and a.id.dept_id = '422.10' and a.application_type = 'NC' and a.mat_cd = 'A0211' " ;
		
		
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		//query.setParameter("warehouse", warehouse);
		query.setParameter("deptId", deptId);
		query.setParameter("applicationType", applicationType);
		query.setParameter("phase", phase);
		query.setParameter("conductorType", conductorType);
		
		
		List<LabourGrid> list = query.getResultList();
		//System.out.println("### getDefaultConductorLabour"+list+" "+ wireLength);
			
		for(int i=0; i<=list.size()-1; i++){
			list.get(i).setItemQty(new BigDecimal(wireLength));
			list.get(i).setLabourCost(new BigDecimal (Double.parseDouble(list.get(i).getUnitPrice().toString())*Double.parseDouble(wireLength)));
			list.get(i).setLabourHours(new BigDecimal (Double.parseDouble(list.get(i).getUnitLabourHrs().toString())*Double.parseDouble(wireLength)));
		}
		//System.out.println("###2 getDefaultConductorLabour"+list+" "+ wireLength);
		return list;
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<LabourGrid> getDefaultStrutLabour(String deptId, String applicationType, String matCd, int noOfPoles, String webAppName) {
		//getEntityManager(webAppName);
		//String warehouse=inwrhmapDaoRemote.mapWarehouse(deptId, webAppName);
		//System.out.println(warehouse); 
		
		String qryStr = "select new estimate.model.LabourGrid (a.id.labourCode,  b.activityCode, b.activityName, TRIM(a.matCd),concat(concat(concat(b.activityName,' ('),trim(a.matCd)),')')," +
		"a.unitPrice, a.labourHours) from Splbstrt a, Splaborm b where b.activityCode = a.activityCode and a.id.deptId = :deptId and a.applicationType = :applicationType and TRIM(a.matCd) = :matCd " ;
		//String qryStr = "select new map (a.id.labourCode, a.activity_name, trim(a.mat_cd) , a.unit_price) from Splbpole a, Splaborm b" ;
		//"a.unit_price) from Splbpole a, Splaborm b where b.activityCode = a.activityCode and a.id.dept_id = '422.10' and a.application_type = 'NC' and a.mat_cd = 'A0211' " ;
		
		
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		//query.setParameter("warehouse", warehouse);
		query.setParameter("deptId", deptId);
		query.setParameter("applicationType", applicationType);
		query.setParameter("matCd", matCd);
		
		List<LabourGrid> list = query.getResultList();
		//System.out.println("### "+list+" "+ noOfPoles);
			
		for(int i=0; i<=list.size()-1; i++){
			list.get(i).setItemQty(new BigDecimal(noOfPoles));
			list.get(i).setLabourCost(new BigDecimal (Double.parseDouble(list.get(i).getUnitPrice().toString())*noOfPoles));
			list.get(i).setLabourHours(new BigDecimal (Double.parseDouble(list.get(i).getUnitLabourHrs().toString())*noOfPoles));
		}
		
		return list;
	}
	
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<LabourGrid> getDefaultStayLabour(String deptId, String applicationType, String stayType, int noOfStays, String webAppName) {
		//getEntityManager(webAppName);
		//String warehouse=inwrhmapDaoRemote.mapWarehouse(deptId, webAppName);
		//System.out.println(warehouse); 
		
		String qryStr = "select new estimate.model.LabourGrid (a.id.labourCode,  b.activityCode, b.activityName, TRIM(a.stayType),concat(concat(concat(b.activityName,' ('),trim(a.stayType)),')')," +
		"a.unitPrice, a.labourHours) from Splbstay a, Splaborm b where b.activityCode = a.activityCode and a.id.deptId = :deptId and a.applicationType = :applicationType and TRIM(a.stayType) = :stayType " ;
		//String qryStr = "select new map (a.id.labourCode, a.activity_name, trim(a.mat_cd) , a.unit_price) from Splbpole a, Splaborm b" ;
		//"a.unit_price) from Splbpole a, Splaborm b where b.activityCode = a.activityCode and a.id.dept_id = '422.10' and a.application_type = 'NC' and a.mat_cd = 'A0211' " ;
		
		
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		//query.setParameter("warehouse", warehouse);
		query.setParameter("deptId", deptId);
		query.setParameter("applicationType", applicationType);
		query.setParameter("stayType", stayType);
		
		List<LabourGrid> list = query.getResultList();
		//System.out.println("### "+list+" "+ noOfPoles);
			
		for(int i=0; i<=list.size()-1; i++){
			list.get(i).setItemQty(new BigDecimal(noOfStays));
			list.get(i).setLabourCost(new BigDecimal (Double.parseDouble(list.get(i).getUnitPrice().toString())*noOfStays));
			list.get(i).setLabourHours(new BigDecimal (Double.parseDouble(list.get(i).getUnitLabourHrs().toString())*noOfStays));
		}
		
		return list;
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<LabourGrid> getDefaultPoleTransportLabour(String deptId, String applicationType, String matCd, int noOfPoles, Double distance ,String webAppName) {
		//getEntityManager(webAppName);
		//String warehouse=inwrhmapDaoRemote.mapWarehouse(deptId, webAppName);
		//System.out.println(warehouse); 
		
		String qryStr = "select new estimate.model.LabourGrid (a.id.labourCode,  b.activityCode, b.activityName, TRIM(a.matCd),concat(concat(concat(b.activityName,' ('),trim(a.matCd)),')')," +
		"a.unitPrice, a.labourHours) from Splbpolt a, Splaborm b where b.activityCode = a.activityCode and a.id.deptId = :deptId and a.applicationType = :applicationType and TRIM(a.matCd) = :matCd " ;
		//String qryStr = "select new map (a.id.labourCode, a.activity_name, trim(a.mat_cd) , a.unit_price) from Splbpole a, Splaborm b" ;
		//"a.unit_price) from Splbpole a, Splaborm b where b.activityCode = a.activityCode and a.id.dept_id = '422.10' and a.application_type = 'NC' and a.mat_cd = 'A0211' " ;
		
		
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		//query.setParameter("warehouse", warehouse);
		query.setParameter("deptId", deptId);
		query.setParameter("applicationType", applicationType);
		query.setParameter("matCd", matCd);
		
		List<LabourGrid> list = query.getResultList();
		//System.out.println("### "+list+" "+ noOfPoles);
			
		for(int i=0; i<=list.size()-1; i++){
			list.get(i).setItemQty(new BigDecimal(noOfPoles));
			list.get(i).setLabourCost(new BigDecimal (Double.parseDouble(list.get(i).getUnitPrice().toString())*noOfPoles*distance));
			list.get(i).setLabourHours(new BigDecimal (Double.parseDouble(list.get(i).getUnitLabourHrs().toString())*noOfPoles));
		}
		
		return list;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void validateEstimate(Pcesthtt pcesthtt, Approval approval, String webAppName) {
		try{
			pcesthttDaoRemote.updatePcesthtt(pcesthtt, webAppName);
			approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			
			
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void rejectEstimate(Pcesthtt pcesthtt, Approval approval, String webAppName) {
		try{
			pcesthttDaoRemote.updatePcesthtt(pcesthtt, webAppName);
			approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			
			
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String transfer(Pcesthtt pcesthtt, List<Pcestdtt> list, String cSCNo, String webAppName) {
		try{
			System.out.println(pcesthtt.getStatus().toString().equals(EstimateStatus.EST_POSTING));
			if(pcesthtt.getStatus().toString().equals(EstimateStatus.EST_POSTING)){
				//
				//WiringLandDetailPK wiringLandDetailPK=new WiringLandDetailPK();
				//wiringLandDetailPK.setApplicationId(pcesthtt.getId().);
				//wiringLandDetailPK.setDeptId(deptId);
				//wiringLandDetailDaoRemote.findByAppId(id, webAppName);
				//pcestdttDaoRemote.changeUnitPtrice(pcesthtt.getId().getEstimateNo(), "LABOUR", webAppName);
				//pcestdttDaoRemote.changeUnitPtrice(pcesthtt.getId().getEstimateNo(), "OVERHEAD", webAppName);
				Format format=new Format();
				String categoryCode=pcesthtt.getCatCd().trim();
				//String jobNoPrefix=pcesthtt.getId().getDeptId()+"/"+categoryCode+"/"+format.getYear().substring(format.getYear().length()-2, format.getYear().length())+"/";
				String jobNoPrefix=cSCNo+"/"+categoryCode+"/"+format.getYear().substring(format.getYear().length()-2, format.getYear().length())+"/";
				//String nextSequence=pcesthmtDaoRemote.getNextJobNo(jobNoPrefix, webAppName);
				String nextSequence=applicationReferenceDaoRemote.getNextJobNo(jobNoPrefix, webAppName);
				System.out.println(nextSequence);
				String newJobNo=jobNoPrefix+nextSequence;
				PcesthmtPK id=new PcesthmtPK();
				id.setEstimateNo(pcesthtt.getId().getEstimateNo());
				//id.setDeptId(pcesthtt.getId().getDeptId());
				id.setDeptId(cSCNo);
				//id.setRevNo(pcesthtt.getId().getRevNo());//
				id.setRevNo(new Long(2));//
				//Update pcesthtt job No
				pcesthtt.setProjectNo(newJobNo);//set new Job No
				Calendar calendar = Calendar.getInstance();
				pcesthtt.setPrjAssDt(calendar.getTime());
				//
				Pcesthmt pcesthmt=new Pcesthmt(id, pcesthtt.getActualUnits(), 
						pcesthtt.getAllocPaid(), pcesthtt.getAllocSettle(), pcesthtt.getAprDt1(), 
						pcesthtt.getAprDt2(), pcesthtt.getAprDt3(), pcesthtt.getAprDt4(), pcesthtt.getAprDt5(), pcesthtt.getAprUid1(), 
						pcesthtt.getAprUid2(), pcesthtt.getAprUid3(), pcesthtt.getAprUid4(), pcesthtt.getAprUid5(), 
						pcesthtt.getCatCd(), pcesthtt.getClientNm(), pcesthtt.getConfBy(), pcesthtt.getConfDt(), 
						pcesthtt.getContNo(), pcesthtt.getControlled(), pcesthtt.getCustContrib(), 
						pcesthtt.getDescr(), pcesthtt.getEntBy(), pcesthtt.getEntDt(), new BigDecimal(pcesthtt.getEstType().trim()), 
						pcesthtt.getEtimateDt(), pcesthtt.getFundContrib(), pcesthtt.getFundId(), 
						pcesthtt.getFundSource(), pcesthtt.getLabel1(), pcesthtt.getLabel10(), pcesthtt.getLabel2(), 
						pcesthtt.getLabel3(), pcesthtt.getLabel4(), pcesthtt.getLabel5(), pcesthtt.getLabel6(), 
						pcesthtt.getLabel7(), pcesthtt.getLabel8(), pcesthtt.getLabel9(), pcesthtt.getLogId(), 
						pcesthtt.getNormDefault(), pcesthtt.getPaidAmt(), pcesthtt.getPartPcnt(), 
						pcesthtt.getPartialAmt(), pcesthtt.getPartialPmt(), pcesthtt.getPriority(), 
						pcesthtt.getPrjAssDt(), pcesthtt.getProjectNo(), pcesthtt.getRejctDt(), pcesthtt.getRejctUid(), 
						pcesthtt.getRejectReason(), pcesthtt.getRevReason(), pcesthtt.getReviseDt(), 
						pcesthtt.getReviseEst(), pcesthtt.getReviseUid(), pcesthtt.getSettledAmt(), 
						new Long(EstimateStatus.EST_POSTED), pcesthtt.getStdCost(), pcesthtt.getSubCont(), 
						pcesthtt.getSupCd(), pcesthtt.getTaxAmt(), pcesthtt.getTaxPcnt(), pcesthtt.getTmplId());
				pcesthmtDaoRemote.createPcesthmt(pcesthmt, webAppName);
				//Update pcesthtt rev No & status
				//pcesthtt.getId().setRevNo(new Long(1));
				pcesthtt.setStatus(new Long(EstimateStatus.EST_POSTED));
				pcesthttDaoRemote.updatePcesthtt(pcesthtt, webAppName);
				System.out.println(list!=null);
				if (list!=null){
					System.out.println();
					for(int i=0; i<=list.size()-1; i++){
						PcestdmtPK id2=new PcestdmtPK();
						id2.setEstimateNo(list.get(i).getId().getEstimateNo());
						//id2.setDeptId(list.get(i).getId().getDeptId());
						id2.setDeptId(cSCNo);
						id2.setResCd(list.get(i).getId().getResCd());
						id2.setRevNo(new Long(2));
						Pcestdmt pcestdmt =new Pcestdmt(id2, list.get(i).getApprovedCost(), list.get(i).getApprovedQty(), list.get(i).getCommitedCost(), list.get(i).getCommitedQty(), list.get(i).getCustomerQty(), list.get(i).getDamageQty(), list.get(i).getEstimateCost(), list.get(i).getEstimateQty(), list.get(i).getGenRes(), list.get(i).getIssuedCost(), list.get(i).getIssuedQty(), list.get(i).getNormDefault(), list.get(i).getRequestedCost(), list.get(i).getRequestedQty(), list.get(i).getResCat(), list.get(i).getResType(), list.get(i).getReturnedCost(), list.get(i).getReturnedQty(), list.get(i).getTolerance(), list.get(i).getUnitPrice(), list.get(i).getUom(), list.get(i).getMntQty());
						//System.out.println("pcestdmt "+pcestdmt);
						pcestdmtDaoRemote.createPcestdmt(pcestdmt, webAppName);
					}
					}else{
						
					}
				//pcestdmtDaoRemote.changeUnitPtrice(pcesthtt.getId().getEstimateNo(), "LABOUR", webAppName);
				//pcestdmtDaoRemote.changeUnitPtrice(pcesthtt.getId().getEstimateNo(), "OVERHEAD", webAppName);
				//Update ApplicationReference
				ApplicationReference applicationReference=applicationReferenceDaoRemote.findByApplicationNo(pcesthtt.getId().getEstimateNo().trim(), webAppName);
				//System.out.println(applicationReference);
				applicationReference.setProjectno(newJobNo);
				applicationReferenceDaoRemote.updateApplicationReference(applicationReference, webAppName);
				//Update Pcprjbal
				updatePcprjbal(pcesthtt, list, webAppName);
				return newJobNo;
			}else {
				return null;
			}
			
			
			
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String transferToHmt(Pcesthtt pcesthtt, List<Pcestdtt> list, String cSCNo,String estimateNo, String webAppName) {
		try{
			System.out.println(pcesthtt.getStatus().toString().equals(EstimateStatus.EST_POSTING));
			if(pcesthtt.getStatus().toString().equals(EstimateStatus.EST_APPROVED)){
				
				Format format=new Format();
				String categoryCode=pcesthtt.getCatCd().trim();
				//String jobNoPrefix=pcesthtt.getId().getDeptId()+"/"+categoryCode+"/"+format.getYear().substring(format.getYear().length()-2, format.getYear().length())+"/";
				
				// JOB NUMBER GENERATING
				//String jobNoPrefix=cSCNo+"/"+jobPrefix+"/"+format.getYear()+"/";
				String jobNoPrefix = null;

				String newJobNo =null;
				//EstimateReference estimateReference=estimateReferenceDaoRemote.findByWorkEstimateNo(pcesthtt.getId().getEstimateNo().trim(),cSCNo ,webAppName);
				if(estimateNo.contains(Constants.SPS_ESTIMATE_CODE)){
					newJobNo =  estimateNo.trim().replace(Constants.SPS_ESTIMATE_CODE, Constants.SPS_JOB_NUMBER_CODE);
				}/*else if(estimateReference != null){
						jobNoPrefix = cSCNo+"/"+getFormat().getYear()+"/";
					//EstimateReference estimateReference=estimateReferenceDaoRemote.findByWorkEstimateNo(pcesthtt.getId().getEstimateNo().trim(),cSCNo ,webAppName);
					//if(estimateReference != null){
						String nextSequence=estimateReferenceDaoRemote.getNextJobNo(jobNoPrefix, webAppName);
						System.out.println(nextSequence);
						newJobNo=jobNoPrefix+nextSequence;
					//}
				}*/else{
					setFormat(new Format());
					jobNoPrefix = cSCNo+"/"+getFormat().getYear()+"/";
					String nextSequence=pcesthmtDaoRemote.getNextJobNo(jobNoPrefix, webAppName);
					System.out.println(nextSequence);
					newJobNo=jobNoPrefix+nextSequence;
				}
				//return format;
				
				//jobNoPrefix = getReferenceForJobNumber(cSCNo,estimateNo);
				
				/*String nextSequence=estimateReferenceDaoRemote.getNextJobNo(jobNoPrefix, webAppName);
				System.out.println(nextSequence);
				String newJobNo=jobNoPrefix+nextSequence;*/
				
				
				PcesthmtPK id=new PcesthmtPK();
				id.setEstimateNo(pcesthtt.getId().getEstimateNo());
				//id.setDeptId(pcesthtt.getId().getDeptId());oO
				id.setDeptId(cSCNo);
				//id.setRevNo(pcesthtt.getId().getRevNo());//
				id.setRevNo(new Long(3));//
				//Update pcesthtt job No
				pcesthtt.setProjectNo(newJobNo);//set new Job No
				Calendar calendar = Calendar.getInstance();
				pcesthtt.setPrjAssDt(calendar.getTime());
				//
				Pcesthmt pcesthmt=new Pcesthmt(id, pcesthtt.getActualUnits(), 
						pcesthtt.getAllocPaid(), pcesthtt.getAllocSettle(), pcesthtt.getAprDt1(), 
						pcesthtt.getAprDt2(), pcesthtt.getAprDt3(), pcesthtt.getAprDt4(), pcesthtt.getAprDt5(), pcesthtt.getAprUid1(), 
						pcesthtt.getAprUid2(), pcesthtt.getAprUid3(), pcesthtt.getAprUid4(), pcesthtt.getAprUid5(), 
						pcesthtt.getCatCd(), pcesthtt.getClientNm(), pcesthtt.getConfBy(), pcesthtt.getConfDt(), 
						pcesthtt.getContNo(), pcesthtt.getControlled(), pcesthtt.getCustContrib(), 
						pcesthtt.getDescr(), pcesthtt.getEntBy(), pcesthtt.getEntDt(), new BigDecimal(pcesthtt.getEstType().trim()), 
						pcesthtt.getEtimateDt(), pcesthtt.getFundContrib(), pcesthtt.getFundId(), 
						pcesthtt.getFundSource(), pcesthtt.getLabel1(), pcesthtt.getLabel10(), pcesthtt.getLabel2(), 
						pcesthtt.getLabel3(), pcesthtt.getLabel4(), pcesthtt.getLabel5(), pcesthtt.getLabel6(), 
						pcesthtt.getLabel7(), pcesthtt.getLabel8(), pcesthtt.getLabel9(), pcesthtt.getLogId(), 
						pcesthtt.getNormDefault(), pcesthtt.getPaidAmt(), pcesthtt.getPartPcnt(), 
						pcesthtt.getPartialAmt(), pcesthtt.getPartialPmt(), pcesthtt.getPriority(), 
						pcesthtt.getPrjAssDt(), pcesthtt.getProjectNo(), pcesthtt.getRejctDt(), pcesthtt.getRejctUid(), 
						pcesthtt.getRejectReason(), pcesthtt.getRevReason(), pcesthtt.getReviseDt(), 
						pcesthtt.getReviseEst(), pcesthtt.getReviseUid(), pcesthtt.getSettledAmt(), 
						new Long(EstimateStatus.EST_POSTED), pcesthtt.getStdCost(), pcesthtt.getSubCont(), 
						pcesthtt.getSupCd(), pcesthtt.getTaxAmt(), pcesthtt.getTaxPcnt(), pcesthtt.getTmplId());
				pcesthmtDaoRemote.createPcesthmt(pcesthmt, webAppName);
				//Update pcesthtt rev No & status
				//pcesthtt.getId().setRevNo(new Long(1));
				pcesthtt.setStatus(new Long(EstimateStatus.JOB_CREATED));
				pcesthttDaoRemote.updatePcesthtt(pcesthtt, webAppName);
				System.out.println(list!=null);
				if (list!=null){
					System.out.println();
					for(int i=0; i<=list.size()-1; i++){
						PcestdmtPK id2=new PcestdmtPK();
						id2.setEstimateNo(list.get(i).getId().getEstimateNo());
						//id2.setDeptId(list.get(i).getId().getDeptId());
						id2.setDeptId(cSCNo);
						id2.setResCd(list.get(i).getId().getResCd());
						id2.setRevNo(new Long(3));
						Pcestdmt pcestdmt =new Pcestdmt(id2, list.get(i).getApprovedCost(), list.get(i).getApprovedQty(), list.get(i).getCommitedCost(), list.get(i).getCommitedQty(), list.get(i).getCustomerQty(), list.get(i).getDamageQty(), list.get(i).getEstimateCost(), list.get(i).getEstimateQty(), list.get(i).getGenRes(), list.get(i).getIssuedCost(), list.get(i).getIssuedQty(), list.get(i).getNormDefault(), list.get(i).getRequestedCost(), list.get(i).getRequestedQty(), list.get(i).getResCat(), list.get(i).getResType(), list.get(i).getReturnedCost(), list.get(i).getReturnedQty(), list.get(i).getTolerance(), list.get(i).getUnitPrice(), list.get(i).getUom(), list.get(i).getMntQty());
						//System.out.println("pcestdmt "+pcestdmt);
						pcestdmtDaoRemote.createPcestdmt(pcestdmt, webAppName);
					}
					}else{
						
					}
				//pcestdmtDaoRemote.changeUnitPtrice(pcesthtt.getId().getEstimateNo(), "LABOUR", webAppName);
				//pcestdmtDaoRemote.changeUnitPtrice(pcesthtt.getId().getEstimateNo(), "OVERHEAD", webAppName);
				//Update ApplicationReference
				
				EstimateReference estimateReference=estimateReferenceDaoRemote.findByWorkEstimateNo(pcesthtt.getId().getEstimateNo().trim(),cSCNo ,webAppName);
				if(estimateReference != null){
					estimateReference.setProjectno(newJobNo);
					estimateReferenceDaoRemote.updateEstimateReference(estimateReference, webAppName);
					
					if(estimateReference.getId().getStandardEstimateNo() != null){
						SpstdesthmtPK pk = new SpstdesthmtPK();
						pk.setApplicationNo(estimateReference.getId().getStandardEstimateNo());
						pk.setStdNo(estimateReference.getId().getStandardEstimateNo());
						String costCenter = cSCNo.substring(0, 3);
						String commCost = costCenter.concat(".00");
						pk.setDeptId(commCost);
						Spstdesthmt hmt = spstdesthmtDaoRemote.findById(pk, webAppName);
						if(hmt != null){
							ApplicationReference applicationReference=applicationReferenceDaoRemote.findByApplicationNo(estimateReference.getId().getStandardEstimateNo(), webAppName);
							if(applicationReference != null){
								applicationReference.setProjectno(newJobNo);
								applicationReferenceDaoRemote.updateApplicationReference(applicationReference,webAppName );
							}
							
						}
					}
					
					//Update Pcprjbal
				}
				
				//updatePcprjbal(pcesthtt, list, webAppName);
				return newJobNo;
			}else {
				return null;
			}
			
			
			
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
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
	public Format getFormat() {
		return format;
	}
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updatePcprjbal(Pcesthtt pcesthtt,List<Pcestdtt> list, String webAppName) {
		Format format=new Format();
		Boolean hasMatCostDone=false;
		String estimateNo=pcesthtt.getId().getEstimateNo();
		String deptId=pcesthtt.getId().getDeptId();
		Long revNo=pcesthtt.getId().getRevNo();
		String catCd=pcesthtt.getCatCd();
		String projectNo=pcesthtt.getProjectNo();
		
		if (list!=null){
			for(int i=0; i<=list.size()-1; i++){
				
				if(!list.get(i).getResCat().toString().equals("1") || !hasMatCostDone){
					if (list.get(i).getResCat().toString().equals("1")){
						hasMatCostDone=true;
					}	
					for(int j=0; j < 12; j++){
						Pcprjbal pcprjbal=new Pcprjbal();
						PcprjbalPK id=new PcprjbalPK();
						id.setEstimateNo(estimateNo);
						id.setDeptId(deptId);
						id.setResType(list.get(i).getResType());
						id.setYrInd(new Long (format.getYear()));
						id.setMthInd(new Long(j+1));
						//
						pcprjbal.setId(id);
						pcprjbal.setRevNo(new BigDecimal(revNo));
						pcprjbal.setResCat(list.get(i).getResCat());
						pcprjbal.setCatCd(catCd);
						pcprjbal.setOpBal(new BigDecimal(0));
						pcprjbal.setClBal(new BigDecimal(0));
						pcprjbal.setProjectNo(projectNo);
						pcprjbalDaoRemote.createPcprjbal(pcprjbal, webAppName);
						
					}
				}
				
				
			}
		}
		
		
	}
	
	@Override
	public Spsecdep getSpsecdep(SpsecdepPK id, String webAppName) {
		 return getEntityManager(webAppName).find(Spsecdep.class, id);
			
		
		
	}
	@Override
	public Spugcblm getSpugcblm(SpugcblmPK id, String webAppName) {
		return getEntityManager(webAppName).find(Spugcblm.class, id);
			
		
		
	}
	@Override
	public Splpsvcm getSplpsvcm(SplpsvcmPK id, String webAppName) {
		return getEntityManager(webAppName).find(Splpsvcm.class, id);
			
	}
	
	@Override
	public Spratesm getSpratesm (SpratesmPK id, String webAppName) {
		return getEntityManager(webAppName).find(Spratesm.class, id); 
			
		
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	//public LinkedHashMap<String,MaterialGrid> getDefaultMaterialGrid(String deptId, long phase, long conType, long length,String wiringType) 
	public LinkedHashMap<String,MaterialGrid> getDefaultLoopMaterialGrid(String deptId, long phase, long conType, String wiringType, String webAppName) {
		//getEntityManager(webAppName);
		String warehouse=inwrhmapDaoRemote.mapWarehouse(deptId, webAppName);
		LinkedHashMap<String,MaterialGrid> selectMatCodeMap = null;
		try
		{
			String qryStr = " SELECT new Map(TRIM(s.id.matCd), wm.uomCd, s.matQty, wm.unitPrice, s.matQty*wm.unitPrice as cost, m.matNm )"+
							" from Sploopmt s ,Inmatm m, Inwrhmtm wm"+
							" where s.id.deptId = :deptId "+
							" and wm.id.deptId = :warehouse "+
							" and s.id.phase = :phase "+
							" and s.id.connectionType = :conType "+
							//" and s.id.fromLength< :length "+
							//" and s.id.toLength>= :length "+
							" and s.id.wiringType= :wiringType "+
							" and s.id.matCd = m.matCd "+
							" and wm.id.matCd = m.matCd  ";
							

			Query query = getEntityManager(webAppName).createQuery(qryStr);
			query.setParameter("deptId", deptId);
			query.setParameter("warehouse", warehouse);
			query.setParameter("phase", phase);
			query.setParameter("conType", conType);
			//query.setParameter("length", length);
			//query.setParameter("length", length);
			query.setParameter("wiringType", wiringType);
			
			List<Map> list = query.getResultList();
			if(!list.isEmpty())
			{
				selectMatCodeMap =  new LinkedHashMap<String,MaterialGrid>();
				for(int i=0;i<list.size();i++)
				{
					Map map = list.get(i);
					MaterialGrid grid = new MaterialGrid();
					grid.setEstimateCost(new BigDecimal(map.values().toArray()[4].toString()));//
					grid.setEstimateQty(new BigDecimal(map.values().toArray()[1].toString()));
					grid.setMatNm(map.values().toArray()[5].toString().trim());//
					grid.setResCat(new BigDecimal(1));
					grid.setResType("MAT-COST");
					grid.setUnitPrice(new BigDecimal(map.values().toArray()[0].toString()));
					grid.setUom(map.values().toArray()[2].toString());//
					grid.setResCd(map.values().toArray()[3].toString());//
					selectMatCodeMap.put(map.values().toArray()[3].toString(),grid);
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return selectMatCodeMap;

	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void dojdfhdf(String webAppName){
		String qryStr = "select new map(p.referenceNo, p.id.deptId, p.confirmedDate) from PivDetail p, Pcesthmt m where p.referenceNo=m.id.estimateNo and p.referenceType='EST' and  p.confirmedDate is not null and m.prjAssDt is null order by p.referenceNo, p.id.deptId";
		//String qryStr = "select p.reference_no, p.confirmed_date from PIV_DETAIL p, PCESTHMT m where p.reference_no=m.estimate_no and p.reference_type='EST' and p.confirmed_date is not null and m.prj_ass_dt is null ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		List<Map> list = query.getResultList();
		Format format=new Format();
		System.out.println(list+" " +list.size() );
		int g=0;
		for(int i=0;i<list.size();i++){
		//for(int i=0;i<100;i++){
			Map map = list.get(i);
			System.out.println(map+" " +map );
			String referenceNo=map.values().toArray()[2].toString();
			String deptId=map.values().toArray()[1].toString();
			String confirmedDate=map.values().toArray()[0].toString();
			System.out.println(referenceNo+" " +deptId +" " +confirmedDate);
			Pcesthmt pcesthmt=pcesthmtDaoRemote.findByEstimationNo(referenceNo, deptId, webAppName);
			if(pcesthmt!=null){
				pcesthmt.setPrjAssDt(format.StrToDate(confirmedDate)) ;
				pcesthmtDaoRemote.updatePcesthmt(pcesthmt, webAppName);
			}
			//Pcesthtt pcesthtt=pcesthttDaoRemote.findByEstimationNo(referenceNo, deptId, webAppName);
			//if(pcesthtt!=null){
			//	pcesthtt.setPrjAssDt(format.StrToDate(confirmedDate)) ;
			//	pcesthttDaoRemote.updatePcesthtt(pcesthtt, webAppName);
			//}
			//g++;
			
		}
		System.out.println(g);
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void doProjectNoUpdate(String webAppName){
		//String qryStr = "select new map(p.referenceNo, p.id.deptId, p.confirmedDate) from PivDetail p, Pcesthmt m where p.referenceNo=m.id.estimateNo and p.referenceType='ELN' and  p.confirmedDate is not null and m.prjAssDt is null order by p.referenceNo, p.id.deptId";
		String qryStr = "select new map(a.applicationNo, b.projectNo) from ApplicationReference a, Pcesthmt b where a.projectno is null and a.applicationNo=TRIM(b.id.estimateNo) and b.id.deptId='423.10'";
		//String qryStr = "select p.reference_no, p.confirmed_date from PIV_DETAIL p, PCESTHMT m where p.reference_no=m.estimate_no and p.reference_type='EST' and p.confirmed_date is not null and m.prj_ass_dt is null ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		List<Map> list = query.getResultList();
		Format format=new Format();
		System.out.println(list+" " +list.size() );
		int g=0;
		for(int i=0;i<list.size();i++){
			Map map = list.get(i);
			//String referenceNo=map.values().toArray()[2].toString();
			String applicationNo=map.values().toArray()[1].toString();
			String projectNo=map.values().toArray()[0].toString();
			
			ApplicationReference applicationReference =applicationReferenceDaoRemote.findByApplicationNo(applicationNo, webAppName);
			if(applicationReference!=null){
				//if (applicationReference.getProjectno()!=null){
				System.out.println(applicationNo +" " +projectNo);
				applicationReference.setProjectno(projectNo) ;
				applicationReferenceDaoRemote.updateApplicationReference(applicationReference, webAppName);
				//}
			}
			//Pcesthtt pcesthtt=pcesthttDaoRemote.findByEstimationNo(referenceNo, deptId, webAppName);
			//if(pcesthtt!=null){
			//	pcesthtt.setPrjAssDt(format.StrToDate(confirmedDate)) ;
			//	pcesthttDaoRemote.updatePcesthtt(pcesthtt, webAppName);
			//}
			//g++;
			
		}
		System.out.println(g);
		
	}
	 
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void doMatCostNoUpdate(String deptId,String resCd, int length, String webAppName){  
		System.out.println("doMatCostNoUpdate ");
		//String qryStr = "select new map(p.referenceNo, p.id.deptId, p.confirmedDate) from PivDetail p, Pcesthmt m where p.referenceNo=m.id.estimateNo and p.referenceType='ELN' and  p.confirmedDate is not null and m.prjAssDt is null order by p.referenceNo, p.id.deptId";
		String qryStr = "select new map(h.projectNo,d.id.estimateNo,d.id.deptId ,d.unitPrice, d.estimateQty,d.estimateCost) from  Pcestdmt d, Pcesthmt h where (h.id.estimateNo like '%/ENC/2011/%' OR h.id.estimateNo like '%/ECR/2011/%' OR h.id.estimateNo like '%/ETC/2011/%') and TRIM(d.id.estimateNo)=h.id.estimateNo and  TRIM(d.id.resCd)= :resCd and d.id.deptId in(:deptId) and   trim(d.estimateCost) <> trim(d.estimateQty*d.unitPrice) order by  h.projectNo";
		//String qryStr = "select p.reference_no, p.confirmed_date from PIV_DETAIL p, PCESTHMT m where p.reference_no=m.estimate_no and p.reference_type='EST' and p.confirmed_date is not null and m.prj_ass_dt is null ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("resCd", resCd);
		query.setParameter("deptId", deptId);
		List<Map> list = query.getResultList();
		Format format=new Format();
		System.out.println("$$$$$$$$$$$$$$$$$ "+list+" "+list.size());
		int g=0;
		
		int k=0;
		if (list.size() >= length){
			k=length;
			
		}else{
			k=list.size();
		}
		//for(int i=0;i<list.size();i++){
		for(int i=0;i<k;i++){
			Map map = list.get(i);
			//String referenceNo=map.values().toArray()[2].toString();
			
			String projectNo=map.values().toArray()[3].toString();
			String estimateNo=map.values().toArray()[2].toString();
			String deptId2=map.values().toArray()[1].toString();
			String unitPrice=map.values().toArray()[0].toString();
			String estimateQty=map.values().toArray()[5].toString();
			String estimateCost=map.values().toArray()[4].toString();
			//System.out.println(map);
			System.out.println(projectNo+" "+estimateNo+" "+deptId2+" "+unitPrice+" "+estimateQty+" "+estimateCost);
			//ApplicationReference applicationReference =applicationReferenceDaoRemote.findByApplicationNo(applicationNo, webAppName);
			Pcestdmt pcestdmt=pcestdmtDaoRemote.findBy3PK(estimateNo, deptId, resCd, webAppName);
			
			if(pcestdmt!=null){
				//if (applicationReference.getProjectno()!=null){
				System.out.println(i +" " +"yes ");
				Double eCost=Double.parseDouble(unitPrice.trim())*Double.parseDouble(estimateQty.trim()) ;
				//System.out.println(eCost) ;
				//System.out.println(new BigDecimal(eCost)) ;
				BigDecimal bdeCost=new BigDecimal(eCost).setScale(2, BigDecimal.ROUND_HALF_UP) ;
				System.out.println(bdeCost) ;
				//System.out.println(String.valueOf(Double.parseDouble(unitPrice)*Double.parseDouble(estimateQty))) ;
				//System.out.println(new BigDecimal(String.valueOf(Double.parseDouble(unitPrice)*Double.parseDouble(estimateQty)))) ;
				pcestdmt.setEstimateCost(bdeCost) ;
				//System.out.println(pcestdmt) ;
				pcestdmtDaoRemote.updatePcestdmt(pcestdmt, webAppName);
				
				String qryStr2 = "select sum(a.estimateCost) from Pcestdmt a where TRIM(a.id.estimateNo)=:estimateNo ";
				Query query2 = getEntityManager(webAppName).createQuery(qryStr2);
				query2.setParameter("estimateNo", estimateNo);
				BigDecimal stdCost = (BigDecimal) query2.getSingleResult();
				System.out.println(stdCost);
				Pcesthmt pcesthmt=pcesthmtDaoRemote.findByEstimationNo(estimateNo, deptId2, webAppName);
				if (pcesthmt!=null){
					pcesthmt.setStdCost(stdCost);
					//System.out.println(pcesthmt);
					System.out.println();
					pcesthmtDaoRemote.updatePcesthmt(pcesthmt, webAppName);
				}
				//}
			}
			//Pcesthtt pcesthtt=pcesthttDaoRemote.findByEstimationNo(referenceNo, deptId, webAppName);
			//if(pcesthtt!=null){
			//	pcesthtt.setPrjAssDt(format.StrToDate(confirmedDate)) ;
			//	pcesthttDaoRemote.updatePcesthtt(pcesthtt, webAppName);
			//}
			//g++;
			
		}
		System.out.println(g);
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void doMatCostNoUpdateTem(String deptId,String resCd, int length, String webAppName){  
		System.out.println("doMatCostNoUpdate ");
		//String qryStr = "select new map(p.referenceNo, p.id.deptId, p.confirmedDate) from PivDetail p, Pcesthtt m where p.referenceNo=m.id.estimateNo and p.referenceType='ELN' and  p.confirmedDate is not null and m.prjAssDt is null order by p.referenceNo, p.id.deptId";
		String qryStr = "select new map(h.projectNo,d.id.estimateNo,d.id.deptId ,d.unitPrice, d.estimateQty,d.estimateCost) from  Pcestdtt d, Pcesthtt h where (h.id.estimateNo like  '%/ENC/2011/%' OR h.id.estimateNo like'%/ECR/2011/%' OR h.id.estimateNo  like '%/ETC/2011/%') and TRIM(d.id.estimateNo)=h.id.estimateNo and  TRIM(d.id.resCd)= :resCd and d.id.deptId in(:deptId) and   trim(d.estimateCost) <> trim(d.estimateQty*d.unitPrice) and h.id.revNo='1' order by  d.id.estimateNo";
		//String qryStr = "select p.reference_no, p.confirmed_date from PIV_DETAIL p, PCESTHtT m where p.reference_no=m.estimate_no and p.reference_type='EST' and p.confirmed_date is not null and m.prj_ass_dt is null ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("resCd", resCd);
		query.setParameter("deptId", deptId);
		List<Map> list = query.getResultList();
		Format format=new Format();
		System.out.println("$$$$$$$$$$$$$$$$$ "+list+" "+list.size());
		int g=0;
		int k=0;
		if (list.size() >= length){
			k=length;
			
		}else{
			k=list.size();
		}
		//for(int i=0;i<list.size();i++){
		for(int i=0;i<k;i++){
			Map map = list.get(i);
			//String referenceNo=map.values().toArray()[2].toString();
			
			//String projectNo =null;//=map.values().toArray()[3].toString();
			String estimateNo=map.values().toArray()[2].toString();
			String deptId2=map.values().toArray()[1].toString();
			String unitPrice=map.values().toArray()[0].toString();
			String estimateQty=map.values().toArray()[5].toString();
			String estimateCost=map.values().toArray()[4].toString();
			//System.out.println(map);
			System.out.println(estimateNo+" "+deptId2+" "+unitPrice+" "+estimateQty+" "+estimateCost);
			//ApplicationReference applicationReference =applicationReferenceDaoRemote.findByApplicationNo(applicationNo, webAppName);
			Pcestdtt pcestdtt=pcestdttDaoRemote.findBy3PK(estimateNo, deptId, resCd, webAppName);
			
			if(pcestdtt!=null){
				//if (applicationReference.getProjectno()!=null){
				System.out.println(i +" " +"yes ");
				Double eCost=Double.parseDouble(unitPrice.trim())*Double.parseDouble(estimateQty.trim()) ;
				//System.out.println(eCost) ;
				//System.out.println(new BigDecimal(eCost)) ;
				BigDecimal bdeCost=new BigDecimal(eCost).setScale(2, BigDecimal.ROUND_HALF_UP) ;
				System.out.println(bdeCost);
				//System.out.println(String.valueOf(Double.parseDouble(unitPrice)*Double.parseDouble(estimateQty))) ;
				//System.out.println(new BigDecimal(String.valueOf(Double.parseDouble(unitPrice)*Double.parseDouble(estimateQty)))) ;
				pcestdtt.setEstimateCost(bdeCost) ;
				//System.out.println(pcestdtt) ;
				pcestdttDaoRemote.updatePcestdtt(pcestdtt, webAppName);
				
				String qryStr2 = "select sum(a.estimateCost) from Pcestdtt a where TRIM(a.id.estimateNo)=:estimateNo ";
				Query query2 = getEntityManager(webAppName).createQuery(qryStr2);
				query2.setParameter("estimateNo", estimateNo);
				BigDecimal stdCost = (BigDecimal) query2.getSingleResult();
				System.out.println(stdCost);
				Pcesthtt pcesthtt=pcesthttDaoRemote.findByEstimationNo(estimateNo, deptId2, webAppName);
				if (pcesthtt!=null){
					pcesthtt.setStdCost(stdCost);
					//System.out.println(pcesthtt);
					System.out.println();
					pcesthttDaoRemote.updatePcesthtt(pcesthtt, webAppName);
				}
				//}
			}
			//Pcesthtt pcesthtt=pcesthttDaoRemote.findByEstimationNo(referenceNo, deptId, webAppName);
			//if(pcesthtt!=null){
			//	pcesthtt.setPrjAssDt(format.StrToDate(confirmedDate)) ;
			//	pcesthttDaoRemote.updatePcesthtt(pcesthtt, webAppName);
			//}
			//g++;
			
		}
		System.out.println(g);
		
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void estimateCostUpdateTem(String deptId, String jobType, String webAppName){  
		System.out.println("doMatCostNoUpdate ");
		jobType="%/"+jobType+"/2011/%";
		//String qryStr = "select new map(p.referenceNo, p.id.deptId, p.confirmedDate) from PivDetail p, Pcesthtt m where p.referenceNo=m.id.estimateNo and p.referenceType='ELN' and  p.confirmedDate is not null and m.prjAssDt is null order by p.referenceNo, p.id.deptId";
		String qryStr = "select TRIM(h.id.estimateNo)  from  Pcesthtt h where h.id.estimateNo like  :jobType and  h.id.deptId =:deptId and h.id.revNo='1'  ";
		//String qryStr = "SELECT  new map(SUM(PCESTDTT.ESTIMATE_COST), PCESTHTT.STD_COST, PCESTDTT.ESTIMATE_NO, PCESTHTT.PROJECT_NO ) FROM PCESTDTT JOIN PCESTHTT ON PCESTDTT.ESTIMATE_NO = PCESTHTT.ESTIMATE_NO WHERE PCESTDTT.ESTIMATE_NO IN (SELECT DISTINCT PCESTHTT.ESTIMATE_NO FROM PCESTHTT) AND PCESTDTT.DEPT_ID = :deptId  AND PCESTDTT.REV_NO = 1 AND PCESTHTT.REV_NO = 1 AND PCESTHTT.ESTIMATE_NO like :jobType GROUP BY PCESTDTT.ESTIMATE_NO, PCESTHTT.PROJECT_NO, PCESTHTT.STD_COST having PCESTHTT.STD_COST <> SUM(PCESTDTT.ESTIMATE_COST)";
		//		"	//String qryStr = "select new map(h.projectNo,d.id.estimateNo,d.id.deptId ,d.unitPrice, d.estimateQty,d.estimateCost) from  Pcestdtt d, Pcesthtt h where d.id.estimateNo=h.id.estimateNo and  TRIM(d.id.resCd)= :resCd and d.id.deptId in(:deptId) and   trim(d.estimateCost) <> trim(d.estimateQty*d.unitPrice) order by  d.id.estimateNo";
		//String qryStr = "select p.reference_no, p.confirmed_date from PIV_DETAIL p, PCESTHtT m where p.reference_no=m.estimate_no and p.reference_type='EST' and p.confirmed_date is not null and m.prj_ass_dt is null ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("jobType", jobType);
		query.setParameter("deptId", deptId);
		List<String> list = query.getResultList();
		
		Format format=new Format();
		System.out.println("$$$$$$$$$$$$$$$$$ "+list+" "+list.size());
		int g=0;
		for(int i=0;i<list.size();i++){
		//for(int i=0;i<10;i++){
			//Map map = list.get(i);
			//String estimateNo=map.values().toArray()[2].toString();
			//String deptId2=map.values().toArray()[1].toString();
			//String unitPrice=map.values().toArray()[0].toString();
			//String estimateQty=map.values().toArray()[5].toString();
			//String estimateCost=map.values().toArray()[4].toString();
			//System.out.println(estimateNo+" "+deptId2+" "+unitPrice+" "+estimateQty+" "+estimateCost);
			//ApplicationReference applicationReference =applicationReferenceDaoRemote.findByApplicationNo(applicationNo, webAppName);
			List<Pcestdtt> pcestdttList=pcestdttDaoRemote.findByEstimationNo(list.get(i), deptId, webAppName);
			
			if(pcestdttList!=null){
				
				String qryStr2 = "select sum(a.estimateCost) from Pcestdtt a where TRIM(a.id.estimateNo)=:estimateNo  and a.id.revNo='1' ";
				Query query2 = getEntityManager(webAppName).createQuery(qryStr2);
				query2.setParameter("estimateNo", list.get(i));
				BigDecimal estimateCost = (BigDecimal) query2.getSingleResult();
				//System.out.println(estimateCost);
				Pcesthtt pcesthtt=pcesthttDaoRemote.findByEstimationNo(list.get(i), webAppName);
				if (pcesthtt!=null){
					if (estimateCost!=null){
					//System.out.println(pcesthtt.getStdCost());
						if (!estimateCost.equals(pcesthtt.getStdCost())){
							System.out.println(list.get(i));
							System.out.println(pcesthtt.getStdCost());
							System.out.println(estimateCost);
							pcesthtt.setStdCost(estimateCost);
							pcesthttDaoRemote.updatePcesthtt(pcesthtt, webAppName);
						}
					
					}
				}
				//}
			}
			//Pcesthtt pcesthtt=pcesthttDaoRemote.findByEstimationNo(referenceNo, deptId, webAppName);
			//if(pcesthtt!=null){
			//	pcesthtt.setPrjAssDt(format.StrToDate(confirmedDate)) ;
			//	pcesthttDaoRemote.updatePcesthtt(pcesthtt, webAppName);
			//}
			//g++;
			
		}
		System.out.println(g);
		
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void estimateCostUpdate(String deptId, String jobType, String webAppName){  
		System.out.println("doMatCostNoUpdate ");
		jobType="%/"+jobType+"/2011/%";
		//String qryStr = "select new map(p.referenceNo, p.id.deptId, p.confirmedDate) from PivDetail p, Pcesthtt m where p.referenceNo=m.id.estimateNo and p.referenceType='ELN' and  p.confirmedDate is not null and m.prjAssDt is null order by p.referenceNo, p.id.deptId";
		String qryStr = "select TRIM(h.id.estimateNo)  from  Pcesthmt h where  h.id.estimateNo like :jobType and  h.id.deptId=:deptId ";
		//String qryStr = "select new map(h.projectNo,d.id.estimateNo,d.id.deptId ,d.unitPrice, d.estimateQty,d.estimateCost) from  Pcestdmt d, Pcesthmt h where d.id.estimateNo=h.id.estimateNo and  TRIM(d.id.resCd)= :resCd and d.id.deptId in(:deptId) and   trim(d.estimateCost) <> trim(d.estimateQty*d.unitPrice) order by  d.id.estimateNo";
		//String qryStr = "select p.reference_no, p.confirmed_date from PIV_DETAIL p, PCESThmt m where p.reference_no=m.estimate_no and p.reference_type='EST' and p.confirmed_date is not null and m.prj_ass_dt is null ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("jobType", jobType);
		query.setParameter("deptId", deptId);
		List<String> list = query.getResultList();
		Format format=new Format();
		System.out.println("$$$$$$$$$$$$$$$$$ "+list+" "+list.size());
		int g=0;
		for(int i=0;i<list.size();i++){
			
			
			//ApplicationReference applicationReference =applicationReferenceDaoRemote.findByApplicationNo(applicationNo, webAppName);
			List<Pcestdmt> pcestdmtList=pcestdmtDaoRemote.findByEstimationNo(list.get(i), deptId, webAppName);
			
			if(pcestdmtList!=null){
				
				String qryStr2 = "select sum(a.estimateCost) from Pcestdmt a where TRIM(a.id.estimateNo)=:estimateNo ";
				Query query2 = getEntityManager(webAppName).createQuery(qryStr2);
				query2.setParameter("estimateNo", list.get(i));
				BigDecimal estimateCost = (BigDecimal) query2.getSingleResult();
				//System.out.println(estimateCost);
				Pcesthmt pcesthmt=pcesthmtDaoRemote.findByEstimationNo(list.get(i), deptId, webAppName);
				if (pcesthmt!=null){
					if (estimateCost!=null){
					//System.out.println(pcesthmt.getStdCost());
						if (!estimateCost.equals(pcesthmt.getStdCost())){
							System.out.println(list.get(i));
							System.out.println(pcesthmt.getStdCost());
							System.out.println(estimateCost);
							pcesthmt.setStdCost(estimateCost);
							pcesthmtDaoRemote.updatePcesthmt(pcesthmt, webAppName);
						}
					
					}
				}
				//}
			}
			//Pcesthmt pcesthmt=pcesthmtDaoRemote.findByEstimationNo(referenceNo, deptId, webAppName);
			//if(pcesthmt!=null){
			//	pcesthmt.setPrjAssDt(format.StrToDate(confirmedDate)) ;
			//	pcesthttDaoRemote.updatePcesthtt(pcesthtt, webAppName);
			//}
			//g++;
			
		}
		System.out.println(g);
		
	}
	
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void removeMaterials(List<PcestdttPK> list, Pcesthtt inPcesthtt, Speststd speststd, String webAppName) {
		//List<String> list2=new ArrayList<String>();
		//list2.add("11");
		try{
			pcesthttDaoRemote.updatePcesthtt(inPcesthtt, webAppName);
			for(int i=0; i<=list.size()-1; i++){
				PcestdttPK pcestdttPK=list.get(i);
				Pcestdtt pcestdtt=pcestdttDaoRemote.findBy3PK(pcestdttPK.getEstimateNo(), pcestdttPK.getDeptId(), pcestdttPK.getResCd(), webAppName);
				pcestdttDaoRemote.removePcestdtt(pcestdtt, webAppName);
				if (speststd!=null)
				speststdDaoRemote.updateSpeststd(speststd, webAppName);
				
			}
			
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}
	
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void transferDTT(String estimateNo, String deptId, String webAppName) {
		try{
			System.out.println("transferDTT");
			//if(pcesthtt.getStatus().toString().equals(EstimateStatus.EST_POSTING)){
				//
				//WiringLandDetailPK wiringLandDetailPK=new WiringLandDetailPK();
				//wiringLandDetailPK.setApplicationId(pcesthtt.getId().);
				//wiringLandDetailPK.setDeptId(deptId);
				//wiringLandDetailDaoRemote.findByAppId(id, webAppName);
				
				//Format format=new Format();
				//String categoryCode=pcesthtt.getCatCd().trim();
				//String jobNoPrefix=pcesthtt.getId().getDeptId()+"/"+categoryCode+"/"+format.getYear().substring(format.getYear().length()-2, format.getYear().length())+"/";
				//String jobNoPrefix=cSCNo+"/"+categoryCode+"/"+format.getYear().substring(format.getYear().length()-2, format.getYear().length())+"/";
				//String nextSequence=pcesthmtDaoRemote.getNextJobNo(jobNoPrefix, webAppName);
				//String nextSequence=applicationReferenceDaoRemote.getNextJobNo(jobNoPrefix, webAppName);
				//String newJobNo=jobNoPrefix+nextSequence;
				//PcesthmtPK id=new PcesthmtPK();
				//id.setEstimateNo(pcesthtt.getId().getEstimateNo());
				//id.setDeptId(pcesthtt.getId().getDeptId());
				//id.setDeptId(cSCNo);
				//id.setRevNo(pcesthtt.getId().getRevNo());//
				//id.setRevNo(new Long(2));//
				//Update pcesthtt job No
				//pcesthtt.setProjectNo(newJobNo);//set new Job No
				//Calendar calendar = Calendar.getInstance();
				//pcesthtt.setPrjAssDt(calendar.getTime());
				//
				//Pcesthmt pcesthmt=new Pcesthmt(id, pcesthtt.getActualUnits(), 
				//		pcesthtt.getAllocPaid(), pcesthtt.getAllocSettle(), pcesthtt.getAprDt1(), 
				//		pcesthtt.getAprDt2(), pcesthtt.getAprDt3(), pcesthtt.getAprDt4(), pcesthtt.getAprDt5(), pcesthtt.getAprUid1(), 
					//	pcesthtt.getAprUid2(), pcesthtt.getAprUid3(), pcesthtt.getAprUid4(), pcesthtt.getAprUid5(), 
				//		pcesthtt.getCatCd(), pcesthtt.getClientNm(), pcesthtt.getConfBy(), pcesthtt.getConfDt(), 
				//		pcesthtt.getContNo(), pcesthtt.getControlled(), pcesthtt.getCustContrib(), 
				//		pcesthtt.getDescr(), pcesthtt.getEntBy(), pcesthtt.getEntDt(), new BigDecimal(pcesthtt.getEstType().trim()), 
				//		pcesthtt.getEtimateDt(), pcesthtt.getFundContrib(), pcesthtt.getFundId(), 
				//		pcesthtt.getFundSource(), pcesthtt.getLabel1(), pcesthtt.getLabel10(), pcesthtt.getLabel2(), 
				//		pcesthtt.getLabel3(), pcesthtt.getLabel4(), pcesthtt.getLabel5(), pcesthtt.getLabel6(), 
				//		pcesthtt.getLabel7(), pcesthtt.getLabel8(), pcesthtt.getLabel9(), pcesthtt.getLogId(), 
				//		pcesthtt.getNormDefault(), pcesthtt.getPaidAmt(), pcesthtt.getPartPcnt(), 
				//		pcesthtt.getPartialAmt(), pcesthtt.getPartialPmt(), pcesthtt.getPriority(), 
				//		pcesthtt.getPrjAssDt(), pcesthtt.getProjectNo(), pcesthtt.getRejctDt(), pcesthtt.getRejctUid(), 
				//		pcesthtt.getRejectReason(), pcesthtt.getRevReason(), pcesthtt.getReviseDt(), 
				//		pcesthtt.getReviseEst(), pcesthtt.getReviseUid(), pcesthtt.getSettledAmt(), 
				//		new BigDecimal(EstimateStatus.EST_POSTED), pcesthtt.getStdCost(), pcesthtt.getSubCont(), 
				//		pcesthtt.getSupCd(), pcesthtt.getTaxAmt(), pcesthtt.getTaxPcnt(), pcesthtt.getTmplId());
				//pcesthmtDaoRemote.createPcesthmt(pcesthmt, webAppName);
				//Update pcesthtt rev No & status
				//pcesthtt.getId().setRevNo(new Long(1));
				//pcesthtt.setStatus(new BigDecimal(EstimateStatus.EST_POSTED));
				//pcesthttDaoRemote.updatePcesthtt(pcesthtt, webAppName);
				//System.out.println(list!=null);
				
				Pcesthtt pcesthtt= pcesthttDaoRemote.findByEstimationNo(estimateNo, deptId, webAppName);
				System.out.println("pcesthtt "+pcesthtt);
				List<Pcestdtt> list= pcestdttDaoRemote.findByEstimationNo(estimateNo, deptId, webAppName);
				System.out.println("list "+list);
				if (list!=null){
					//System.out.println();
					for(int i=0; i<=list.size()-1; i++){
						PcestdmtPK id2=new PcestdmtPK();
						id2.setEstimateNo(list.get(i).getId().getEstimateNo());
						//id2.setDeptId(list.get(i).getId().getDeptId());
						id2.setDeptId(deptId);
						id2.setResCd(list.get(i).getId().getResCd());
						id2.setRevNo(new Long(2));
						Pcestdmt pcestdmt =new Pcestdmt(id2, list.get(i).getApprovedCost(), list.get(i).getApprovedQty(), list.get(i).getCommitedCost(), list.get(i).getCommitedQty(), list.get(i).getCustomerQty(), list.get(i).getDamageQty(), list.get(i).getEstimateCost(), list.get(i).getEstimateQty(), list.get(i).getGenRes(), list.get(i).getIssuedCost(), list.get(i).getIssuedQty(), list.get(i).getNormDefault(), list.get(i).getRequestedCost(), list.get(i).getRequestedQty(), list.get(i).getResCat(), list.get(i).getResType(), list.get(i).getReturnedCost(), list.get(i).getReturnedQty(), list.get(i).getTolerance(), list.get(i).getUnitPrice(), list.get(i).getUom(), list.get(i).getMntQty());
						System.out.println("pcestdmt "+pcestdmt);
						pcestdmtDaoRemote.createPcestdmt(pcestdmt, webAppName);
					}
					}else{
						
					}
				//Update ApplicationReference
				//ApplicationReference applicationReference=applicationReferenceDaoRemote.findByApplicationNo(pcesthtt.getId().getEstimateNo(), webAppName);
				//System.out.println(applicationReference);
				//applicationReference.setProjectno(newJobNo);
				//applicationReferenceDaoRemote.updateApplicationReference(applicationReference, webAppName);
				//Update Pcprjbal
				updatePcprjbal(pcesthtt, list, webAppName);
				//return newJobNo;
			//}else {
			//	return null;
			//}
			
			
			
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}
	@Override
	@SuppressWarnings("unchecked")
	public void updateLineLength(String webAppName){
		String qryStr = "select a from Spserest a WHERE  a.id.applicationNo like '%/ECR/2011/%' order by a.id.applicationNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		List<Spserest> list = query.getResultList();
		//return list;
		
		for(int i=0; i<=list.size()-1; i++){
		System.out.println(i+" "+list.get(i).getId().getApplicationNo()+"  "+list.get(i).getTotalLength());
			String   qryStr2 ="UPDATE Speststd s SET s.lineLength =:lineLength WHERE s.id.estimateNo =:estimateNo ";
		Query query2 = getEntityManager(webAppName).createQuery(qryStr2);
		query2.setParameter("lineLength", new BigDecimal(list.get(i).getTotalLength()));
		query2.setParameter("estimateNo", list.get(i).getId().getApplicationNo());
		query2.executeUpdate();
		}
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public String changeCostCenter(String jobNo,String fromDeptId, String toDeptId,String webAppName){
		try{
			
			Pcesthmt pcesthmt=pcesthmtDaoRemote.findByJobNo(jobNo, fromDeptId, webAppName);
			Format format=new Format();
			String year=format.getYear().substring(format.getYear().length()-2, format.getYear().length());
			
			if (pcesthmt!=null){
				String nextJobNo=toDeptId+"/"+pcesthmt.getCatCd().trim()+"/"+year+"/"+applicationReferenceDaoRemote.getNextJobNo(toDeptId+"/"+pcesthmt.getCatCd().trim()+"/"+year+"/", webAppName);
				System.out.println("nextJobNo "+nextJobNo);
				String estimateNo=pcesthmt.getId().getEstimateNo().trim();
				String   qryStr ="UPDATE Pcesthmt a SET a.projectNo=:jobNo, a.id.deptId=:toDeptId WHERE  TRIM(a.id.estimateNo)= :estimateNo and TRIM(a.id.deptId)=:fromDeptId";
				Query query = getEntityManager(webAppName).createQuery(qryStr);
				query.setParameter("jobNo", nextJobNo);
				query.setParameter("toDeptId", toDeptId);
				query.setParameter("fromDeptId", fromDeptId);
				query.setParameter("estimateNo", estimateNo);
				int s1=query.executeUpdate();
				System.out.println("s1 "+s1);
				
				qryStr ="UPDATE Pcestdmt a SET a.id.deptId=:toDeptId WHERE  TRIM(a.id.estimateNo)= :estimateNo and TRIM(a.id.deptId)=:fromDeptId";
				query = getEntityManager(webAppName).createQuery(qryStr);
				query.setParameter("toDeptId", toDeptId);
				query.setParameter("fromDeptId", fromDeptId);
				query.setParameter("estimateNo", estimateNo);
				int s2=query.executeUpdate();
				System.out.println("s2 "+s2);
				
				qryStr ="UPDATE Pcesthtt a SET a.projectNo=:jobNo WHERE  TRIM(a.id.estimateNo)= :estimateNo";
				query = getEntityManager(webAppName).createQuery(qryStr);
				query.setParameter("jobNo", nextJobNo);
				query.setParameter("estimateNo", estimateNo);
				int s3=query.executeUpdate();
				System.out.println("s3 "+s3);
				
				qryStr ="UPDATE ApplicationReference a SET projectno=:jobNo WHERE  TRIM(a.applicationNo)= :estimateNo";
				query = getEntityManager(webAppName).createQuery(qryStr);
				query.setParameter("jobNo", nextJobNo);
				query.setParameter("estimateNo", estimateNo);
				int s4=query.executeUpdate();
				System.out.println("s4 "+s4);
						
				qryStr ="UPDATE Pcprjbal a SET projectNo=:jobNo WHERE  TRIM(a.id.estimateNo)= :estimateNo";
				query = getEntityManager(webAppName).createQuery(qryStr);
				query.setParameter("jobNo", nextJobNo);
				query.setParameter("estimateNo", estimateNo);
				int s5=query.executeUpdate();
				System.out.println("s5 "+s5);
						
				return nextJobNo;
				
			}else{
				return null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
			
		}
		
		
		
		
			
		
	}
	
	/**
	 * Author: Dinusha Nirmalie
	 * Created: Febrary 01, 2012 11:23:42 AM
	 *
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public  List<Spnorms> getAvailableNorms(String webAppName){
		try{
			List<Spnorms> list = normsDaoRemote.getAll(webAppName);
	          
			return list;
		}catch(Exception e){
			LOGGER.info("Exception Occured in getAvailableNorms" + e);
			e.printStackTrace();
		}
		return null;
        
		
  }


	/**
	 * Author: Dinusha Nirmalie
	 * Created: March 01, 2012 11:23:42 AM
	 *
	 */
	@Override
	public List<SpPegInfo> getPegChildrensByParentId(String parentId,String masterId,String webAppName) {
		
		try{
			List<SpPegInfo>  list = spPegInfoDaoRemote.getPegChildrensByParentId(parentId,masterId, webAppName);	

			return list;
		}catch(Exception e){
			LOGGER.info("Exception Occured in getPegChildrensByParentId" + e);
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public List<String> loadApplicationRefnos(String userId, String costCenter,
			String webAppName) {
		try{
			List<String>  list = spestedyDaoRemote.loadApplicationRefnos(userId, costCenter, webAppName);

			return list;
		}catch(Exception e){
			LOGGER.info("Exception Occured in loadApplicationRefno" + e);
			e.printStackTrace();
		}
		return null;
	}
	
	





	//@Override
	/*public List<SpPegInfo> loadApplicationRefno(String userId, String costCenter,String webAppName) {
		try{
			//String qryStr = "SELECT g FROM Spestmtm g WHERE g.id.wiringType = :wiringType AND g.id.phase = :phase AND g.id.connectionType = :connectionType AND g.id.fromLength = :fromLength AND g.id.toLength = :toLength";
			String qryStr = "select distinct appnumber from appalloc where userid=:userId and dept_id =:costCenter and appnumber IS NOT NULL ORDER BY appnumber ASC";
			Query query = getEntityManager(webAppName).createQuery(qryStr);
			query.setParameter("userId", userId);
			query.setParameter("costCenter", costCenter);

			List<SpPegInfo>  list = query.getResultList();	
			
		
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}*/
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public void insert(Spstdesthmt spstdesthmt,List<Spstdestdmt> lineTypelist,  String webAppName) {
		try{
			spstdesthmtDaoRemote.createSpstdesthmt(spstdesthmt, webAppName);
			for(Spstdestdmt dmt : lineTypelist){
				spstdestdmtDaoRemote.createSpstdestdmt(dmt, webAppName);
			}
			spestedyDaoRemote.updateSpestedyStatus(AppointmentStatus.SERVICE_EST_CREATED,spstdesthmt.getId().getApplicationNo(), webAppName);
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}


	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public void updateEstimateDetails(Spstdesthmt spstdesthmt,List<Spstdestdmt> insertlist,List<Spstdestdmt> updatelist,  String webAppName) {
		try{
			if(spstdesthmt != null){
			 spstdesthmtDaoRemote.updateSpstdesthmt(spstdesthmt, webAppName);
			}
			if(insertlist != null){
				for(Spstdestdmt dmt : insertlist){
					spstdestdmtDaoRemote.createSpstdestdmt(dmt, webAppName);
				}
			}
			if(updatelist != null){
				for(Spstdestdmt dmt : updatelist){
					
					System.out.println("getLineDescription:"+ dmt.getLineDescription());

					System.out.println("getLength:"+ dmt.getLength());
					System.out.println("getLineCost:"+ dmt.getLineCost());
					System.out.println("getEstmateCost:"+ dmt.getEstmateCost());
					spstdestdmtDaoRemote.updateSpstdestdmt(dmt, webAppName);
				}
			}
			//spestedyDaoRemote.updateSpestedyStatus(AppointmentStatus.SERVICE_EST_CREATED,spstdesthmt.getId().getApplicationNo(), webAppName);
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public Spstdesthmt loadEstimateByApplicationNo(String costCenter ,String applicationNo, String webAppName) {
		Spstdesthmt applicatioDetails = null;
		try{
			SpstdesthmtPK sps = new SpstdesthmtPK();
			sps.setApplicationNo(applicationNo);
			sps.setStdNo(applicationNo);
			sps.setDeptId(costCenter);
			applicatioDetails = spstdesthmtDaoRemote.findById(sps, webAppName);
			//for(Spstdestdmt dmt : lineTypelist){
			//	spstdestdmtDaoRemote.createSpstdestdmt(dmt, webAppName);
			//}
	
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		return applicatioDetails;
	}
	
	@Override
	public List<Spstdestdmt> loadEstimateDetailsByApplicaNo(String costCenter ,String applicationNo, String webAppName) {
		List<Spstdestdmt> applicatioDetails = null;
		try{
			SpstdesthmtPK sps = new SpstdesthmtPK();
			sps.setApplicationNo(applicationNo);
			sps.setStdNo(applicationNo);
			sps.setDeptId(costCenter);
			applicatioDetails = spstdestdmtDaoRemote.findByApplicationNo(applicationNo, webAppName);
			//for(Spstdestdmt dmt : lineTypelist){
			//	spstdestdmtDaoRemote.createSpstdestdmt(dmt, webAppName);
			//}
	
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		return applicatioDetails;
	}

	@Override
	public void insertLineDetails(Spstdestdmt line, String webAppName) {
		spstdestdmtDaoRemote.createSpstdestdmt(line, webAppName);
	}

	@Override
	public int updateStdEstimateDetails(String applicationNo,BigDecimal secDeposit,BigDecimal totalCost, String webAppName) {
		int status = 0;
		if(secDeposit != null){
			String   qryStr1 ="UPDATE Spstdesthmt s SET s.secDeposit =:secDeposit,s.totalCost =:totalCost WHERE s.id.applicationNo =:applicationNo ";
			Query query1 = getEntityManager(webAppName).createQuery(qryStr1);
			query1.setParameter("secDeposit", secDeposit);
			query1.setParameter("totalCost", totalCost);
			query1.setParameter("applicationNo", applicationNo);
	
			status=query1.executeUpdate();
		}
		
		/*for(Spstdestdmt line : linetypes){
			
			String   qryStr2 ="UPDATE Spstdestdmt s SET s.estmateCost =:estmateCost,s.length =:length WHERE s.id.applicationNo =:applicationNo and s.id.lineType=:lineid ";
			Query query2 = getEntityManager(webAppName).createQuery(qryStr2);
			query2.setParameter("estmateCost", line.getEstmateCost());
			query2.setParameter("length", line.getLength());
			query2.setParameter("applicationNo", applicationNo);
			query2.setParameter("lineid", line.getId().getLineType());
			
			status=query2.executeUpdate();
		}*/
		return status;
	}


	@Override
	public int removeLineDetails(Spstdestdmt line, String webAppName) {
		
		System.out.println("#############################################7");
		int status = 0;
		/**try{
				String qryStr = "DELETE FROM Spstdestdmt spsdmt WHERE spsdmt.id.applicationNo = :applicationNo and spsdmt.id.lineType=:lineType";
	            Query query = getEntityManager(webAppName).createQuery(qryStr);
	            query.setParameter("applicationNo", line.getId().getApplicationNo());
	            query.setParameter("lineType", line.getId().getLineType());
	            //List<Spstdestdmt> list = query.getResultList();
	           
	            status=query.executeUpdate();
			
			
		}catch (Exception e){
			LOGGER.info("Exception Occured in findByApplicationNo" + e);
			e.printStackTrace();
		}*/
		line = getEntityManager(webAppName).merge(line);
		getEntityManager(webAppName).remove(line);
		return status;
		
	}
	@Override
	public int updateLineDetails(Spstdestdmt line, String webAppName) {
		int status = 0;
		if(line != null){
			String   qryStr1 ="UPDATE Spstdestdmt s SET s.length =:length,s.estmateCost =:estmateCost WHERE s.id.applicationNo =:applicationNo and s.id.lineType =:lineid";
			Query query1 = getEntityManager(webAppName).createQuery(qryStr1);
			query1.setParameter("length", line.getLength());
			query1.setParameter("estmateCost", line.getEstmateCost());
			query1.setParameter("applicationNo", line.getId().getApplicationNo());
			query1.setParameter("lineid", line.getId().getLineType());
			status=query1.executeUpdate();
		}
		return status;
	}



	@Override
	public List<String> loadStandEstmatenos(String costCenter,List<Long> status,String userName,String webAppName) {
		try{
			List<String>  list = spstdesthmtDaoRemote.loadStandEstmatenos(costCenter,status,userName, webAppName);

			return list;
		}catch(Exception e){
			LOGGER.info("Exception Occured in loadApplicationRefno" + e);
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	@Override
	public List<Spstdesthmt> loadStandEstmateDetails(String costCenter,List<Long> status,String userName,String webAppName) {
		try{
			List<Spstdesthmt>  list = spstdesthmtDaoRemote.loadStandEstmateDetails(costCenter,status,userName, webAppName);

			return list;
		}catch(Exception e){
			LOGGER.info("Exception Occured in loadApplicationRefno" + e);
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Spstdesthmt> loadConMainSentList(String costCenter,String postID,List<Long> status,String webAppName) {
		try{
			List<Spstdesthmt>  list = spstdesthmtDaoRemote.loadConMainSentList(costCenter,postID,status, webAppName);

			return list;
		}catch(Exception e){
			LOGGER.info("Exception Occured in loadApplicationRefno" + e);
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public int updateSpestedyStatus(String status,String referenceNo, String webAppName) {
		int status1 = spestedyDaoRemote.updateSpestedyStatus(status,referenceNo, webAppName);

		return status1;
	}
	
	@Override
	public BigDecimal getLineCost(SpnormsPK lineId,String webAppName) {
		try{
			BigDecimal cost = new BigDecimal(normsDaoRemote.findById(lineId, webAppName).getStandardCost());
			return cost;
		}catch(Exception e){
			LOGGER.info("Exception Occured in loadApplicationRefno" + e);
			e.printStackTrace();
		}
		return null;

	}
	
	@Override
	public List<SpPointdmt> getPegResourceById(String resourceType,String masterId,String webAppName) {
		try{
			
			List<SpPointdmt>  list = spPointdmtDaoRemote.getPegResourceById(resourceType,masterId, webAppName);

			return list;
		}catch(Exception e){
			LOGGER.info("Exception Occured in loadApplicationRefno" + e);
			e.printStackTrace();
		}
		return null;

	}



	@Override
	public List<Pcrstypm> isResourceTypeExist(String resourType,String webAppName) {
		try{
			
			List<Pcrstypm>  list = pcrstypmDaoRemote.isResourceTypeExist(resourType,webAppName);

			return list;
		}catch(Exception e){
			LOGGER.info("Exception Occured in loadResourceTypes" + e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<String> loadResourceTypes(String webAppName) {
		try{
			
			List<String>  list = pcrstypmDaoRemote.loadResourceTypes(webAppName);

			return list;
		}catch(Exception e){
			LOGGER.info("Exception Occured in loadResourceTypes" + e);
			e.printStackTrace();
		}
		return null;
	}


	/*@Override
	public List<String> loadResourceCodes(String resourType, String webAppName) {
		try{
			
			List<String>  list = spPointdmtDaoRemote.loadResourceCodes(resourType, webAppName);

			return list;
		}catch(Exception e){
			LOGGER.info("Exception Occured in loadApplicationRefno" + e);
			e.printStackTrace();
		}
		return null;
	}*/
	@Override
	public Pcrsgrpm loadResourceDetails( String resourType,String code,String webAppName) {
		try{
			
			
			
			Pcrsgrpm  list = pcrsgrpmDaoRemote.getResourceDetails(resourType, code,webAppName);

			return list;
		}catch(Exception e){
			LOGGER.info("Exception Occured in loadApplicationRefno" + e);
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public List<String> getFundIds(String deptId,String fundSource, String webAppName) {
		//getEntityManager(webAppName);
		
		return pcfunddmDaoRemote.getFundIds(deptId,fundSource, webAppName);
		
	}
	
	@Override
	public List<String> getCatCode(String warehouse, String webAppName) {
	
		return pcjbtypmDaoRemote.getCatCode(warehouse, webAppName);
		
	}

	@Override
	public List<String> loadWarehouses(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		List<String> warehouses=inwrhmapDaoRemote.loadWarehouses(deptId, webAppName);
		return warehouses;
		
	}

	@Override
	public List<String> loadResourceCodes(String resourType, String webAppName) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
    public List<DetailEstimateDTO> getOtherResources(String deptId, String webAppName) throws Exception {
		//getEntityManager(webAppName);
		List<DetailEstimateDTO> resultList = null;
    
          String qryStr = " select new Map(TRIM(pc.resCd),TRIM(pc.resUom),TRIM(p.resType),pc.unitPrice,TRIM(pc.resNm))  "+
                                  " from Pcrstypm  p,Pcrsgrpm pc "+
                                  " where TRIM(p.resType) !='MAT-COST' "+
                                  " and TRIM(p.resType) !='MAT-COST-OTHER' "+
                                  " and TRIM(p.resType) !='DEPRECIATION 5%' "+
                                  " and TRIM(p.resType) !='INTEREST 7%' "+
                                  " and TRIM(p.resType) !='MAINTENANCE 3%' "+
                                  " and TRIM(p.resType) !='DAM-BREAKAGES 10%' "+
                                  " and TRIM(p.resType) !='50% LABOUR' "+
                                  " and TRIM(p.resType) !='5% A-D' "+
                                  " and TRIM(p.resType) !='20% A-G' "+
                                  " and TRIM(p.resType) !='5% A-G' "+
                                  " and TRIM(p.resType) !='50% OF LABOUR' "+
                                 // " and TRIM(p.resType) !='12% VAT' "+
                                  " and TRIM(p.resType) !='CEB 30%' "+
                                  " and TRIM(p.resType) !='VAT 20%' "+
                                  " and TRIM(p.resType) !='20 CEB %' "+
                                  " and TRIM(p.resType) !='2% NBT' "+
                                  " and pc.resLvl = 1 "+
                                  " and pc.status = 2 "+
                                  " and p.status = 2 "+
                                  " and  pc.resType = p.resType "+
                                  " order by p.resType ";
          
          Query query = getEntityManager(webAppName).createQuery(qryStr);
          //query.setParameter("deptId", deptId);
          
          List<Map> list = query.getResultList();
          if(!list.isEmpty())
          {
                resultList =  new ArrayList<DetailEstimateDTO>();
                for(int i=0;i<list.size();i++)
                {
                      Map<String,String> map = list.get(i);
                      DetailEstimateDTO dto = new DetailEstimateDTO();
                      if(map.values().toArray()[3] != null){
                    	  dto.setResourceCode(map.values().toArray()[3].toString());
                      }else{
                    	  dto.setResourceCode("");
                      }
                    // System.out.println(map.values().toArray()[0].toString()+" "+ map.values().toArray()[0].toString() +" "+ map.values().toArray()[1].toString()+" "+map.values().toArray()[2].toString()+" "+map.values().toArray()[3].toString()+" "+map.values().toArray()[4].toString());
                      if(map.values().toArray()[2] != null){
                    	  dto.setUom(map.values().toArray()[2].toString());
                      }else{
                    	  dto.setUom("1");
                      }
                      if(map.values().toArray()[1] != null){
                    	  dto.setResourceType(map.values().toArray()[1].toString());
                      }else{
                    	  dto.setResourceType(map.values().toArray()[4].toString());
                      }
                      if(map.values().toArray()[0] != null){
                    	  dto.setUnitPrice(new BigDecimal(map.values().toArray()[0].toString()));
                      }else{
                    	  dto.setUnitPrice(new BigDecimal(1));
                      }
                     
                      
                      dto.setResourceName(map.values().toArray()[4].toString());
                      
                     
                      resultList.add(dto);
                     
                }
          }
          
          return resultList;
  }
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public List<DetailEstimateDTO> getNPLMaterials(String deptId, String webAppName)throws Exception {
		//getEntityManager(webAppName);
		List<DetailEstimateDTO> resultList = null;
		List<Map> list = null;
          
          String qryStr = " SELECT new Map(TRIM(wm.id.matCd) , wm.uomCd, wm.unitPrice, TRIM(m.matNm) )"+
                                  " from Inwrhmtm wm,Inmatm m "+
                                  " where wm.id.deptId = :deptId "+
                                  " and  wm.id.matCd = m.matCd "+
                                  " and m.matCd LIKE :NPL "+
                                  " order by wm.id.matCd ";

          Query query = getEntityManager(webAppName).createQuery(qryStr);
          query.setParameter("deptId", deptId);
          query.setParameter("NPL", "NPL%");
          //System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");      
          list = query.getResultList();
          if(!list.isEmpty())
          {
                resultList =  new ArrayList<DetailEstimateDTO>();
                for(int i=0;i<list.size();i++)
                {
                      Map map = list.get(i);
                      //String matCd = map.values().toArray()[3].toString();
                      //String matNm = map.values().toArray()[0].toString();
                     // String uom = map.values().toArray()[2].toString();
                      //String up = map.values().toArray()[1].toString();
                      
                      DetailEstimateDTO dto = new DetailEstimateDTO();
                      dto.setResourceCode(map.values().toArray()[3].toString());
                      dto.setResourceName(map.values().toArray()[0].toString());
                      dto.setUom(map.values().toArray()[2].toString());
                      dto.setUnitPrice(new BigDecimal((map.values().toArray()[1].toString())));
                      

                      resultList.add(dto);
                      
                }
          }
          
          return resultList;
  }
 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public List<DetailEstimateDTO> getAvailableOtherMaterials(String deptId,String warehouse, String webAppName) throws Exception {
		//getEntityManager(webAppName);
		//String warehouse=inwrhmapDaoRemote.mapWarehouse(deptId, webAppName);
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% "+warehouse); 
		List<DetailEstimateDTO> resultList = null;
		List<Map> list = null;
          
          String qryStr = " SELECT new Map(TRIM(wm.id.matCd) , wm.uomCd, wm.unitPrice, TRIM(m.matNm) )"+
                                  " from Inwrhmtm wm,Inmatm m "+
                                  //" where wm.id.deptId = :deptId "+
                                  " where wm.id.deptId = :warehouse "+
                                  " and  wm.id.matCd = m.matCd "+
                                  " and m.matCd NOT LIKE :NPL "+
                                  " and wm.status  in ('2','7') "+
                                  " and m.status ='2' "+
                                  " and TRIM(wm.id.gradeCd) ='NEW' "+
                                  " order by wm.id.matCd ";

          Query query = getEntityManager(webAppName).createQuery(qryStr);
          //query.setParameter("deptId", deptId);
          query.setParameter("warehouse", warehouse);
          query.setParameter("NPL", "NPL%");
          //System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");      
          list = query.getResultList();
          if(!list.isEmpty())
          {
                resultList =  new ArrayList<DetailEstimateDTO>();
                for(int i=0;i<list.size();i++)
                {
                      Map map = list.get(i);
                      //String matCd = map.values().toArray()[3].toString();
                      //String matNm = map.values().toArray()[0].toString();
                      //String uom = map.values().toArray()[2].toString();
                      //String up = map.values().toArray()[1].toString();
                      
                      DetailEstimateDTO dto = new DetailEstimateDTO();
                      dto.setResourceCode(map.values().toArray()[3].toString());
                      dto.setResourceName(map.values().toArray()[0].toString());
                      dto.setUom(map.values().toArray()[2].toString());
                      //System.out.println("Hi Hi Hi unit price");
                      dto.setUnitPrice(new BigDecimal((map.values().toArray()[1].toString())));
                      

                      resultList.add(dto);
                }
          }
          
          return resultList;
  }
	
	
	public List<DetailEstimateDTO> getAvailableOtherMaterials(String deptId,String warehouse,List<String> listObj, String webAppName) throws Exception {
		//getEntityManager(webAppName);
		//String warehouse=inwrhmapDaoRemote.mapWarehouse(deptId, webAppName);
		String strObj = "";
		System.out.println("Hiiii Hiiii Hiii " + listObj); 
		if(listObj != null ){
			if(listObj.size()>0){
			System.out.println("Hiiii Hiiii Hiii " + listObj.size()); 
			for(int i=0 ;i < listObj.size();i++ ){
				String str = "";
				if(i == listObj.size() -1 ){
					str = "'" + listObj.get(i) +"'";
				}else{
					str = "'" + listObj.get(i) +"',";
				}
				strObj = strObj + str;
				System.out.println("Hiiii Hiiii Hiii " + strObj); 
				//System.out.println("Hiiii Hiiii Hiii " + listObj.get(0)); 
				//System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% "+warehouse); 
				
			}
		}else{
			strObj ="";
		}
		}else{
			strObj ="";
		}
		System.out.println("Hiiii Hiiii Hiii " + strObj); 
		List<DetailEstimateDTO> resultList = null;
		List<Map> list = null;
		String qryStr ="";
		if(strObj ==""){
			if(deptId.equalsIgnoreCase("656.00")){
				qryStr = "SELECT new Map(TRIM(wm.id.matCd) , wm.uomCd, p.id.unitPrice, TRIM(m.matNm) )"+
	            " from Inwrhmtm wm,Inmatm m ,Inpodmt p"+
	            //" where wm.id.deptId = :deptId "+
	            " where wm.id.deptId = :warehouse "+
	            " and  wm.id.matCd = m.matCd "+
	            " and  wm.id.matCd = p.id.matCd"+
	            " and m.matCd NOT LIKE :NPL "+
	            " and wm.status  in ('2','7') "+
	            " and m.status ='2' "+
	            " and TRIM(wm.id.gradeCd) ='NEW' "+
	            " order by wm.id.matCd ";
				
			}else{
				qryStr = " SELECT new Map(TRIM(wm.id.matCd) , wm.uomCd, wm.unitPrice, TRIM(m.matNm) )"+
	            " from Inwrhmtm wm,Inmatm m "+
	            //" where wm.id.deptId = :deptId "+
	            " where wm.id.deptId = :warehouse "+
	            " and  wm.id.matCd = m.matCd "+
	            " and m.matCd NOT LIKE :NPL "+
	            " and wm.status  in ('2','7') "+
	            " and m.status ='2' "+
	            " and TRIM(wm.id.gradeCd) ='NEW' "+
	            " order by wm.id.matCd ";
			}
			
		}else{
			if(deptId.equalsIgnoreCase("656.00")){
				qryStr = " SELECT new Map(TRIM(wm.id.matCd) , wm.uomCd, p.id.unitPrice, TRIM(m.matNm) )"+
	            " from Inwrhmtm wm,Inmatm m ,Inpodmt p"+
	            //" where wm.id.deptId = :deptId "+
	            " where wm.id.deptId = :warehouse "+
	            " and  wm.id.matCd = m.matCd "+
	            " and  wm.id.matCd = p.id.matCd"+
	            " and  wm.id.matCd NOT IN("+strObj+") "+
	            " and m.matCd NOT LIKE :NPL "+
	            " and wm.status  in ('2','7') "+
	            " and m.status ='2' "+
	            " and TRIM(wm.id.gradeCd) ='NEW' "+
	            " order by wm.id.matCd ";
				
			}else{
			
			qryStr = " SELECT new Map(TRIM(wm.id.matCd) , wm.uomCd, wm.unitPrice, TRIM(m.matNm) )"+
            " from Inwrhmtm wm,Inmatm m "+
            //" where wm.id.deptId = :deptId "+
            " where wm.id.deptId = :warehouse "+
            " and  wm.id.matCd = m.matCd "+
            " and  wm.id.matCd NOT IN("+strObj+") "+
            " and m.matCd NOT LIKE :NPL "+
            " and wm.status  in ('2','7') "+
            " and m.status ='2' "+
            " and TRIM(wm.id.gradeCd) ='NEW' "+
            " order by wm.id.matCd ";
			}
		}
          
          
          System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% "+qryStr); 
          // " and  wm.id.matCd NOT IN("+strObj+") "+
          
          Query query = getEntityManager(webAppName).createQuery(qryStr);
          //query.setParameter("deptId", deptId);
          query.setParameter("warehouse", warehouse);
          query.setParameter("NPL", "NPL%");
          //System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");      
          list = query.getResultList();
          System.out.println("Hiiii Hiiii Hiii 2"); 
          System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% "+qryStr); 
          System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% "+list);
          if(!list.isEmpty())
          {
                resultList =  new ArrayList<DetailEstimateDTO>();
                for(int i=0;i<list.size();i++)
                {
                      Map map = list.get(i);
                      //String matCd = map.values().toArray()[3].toString();
                      //String matNm = map.values().toArray()[0].toString();
                      //String uom = map.values().toArray()[2].toString();
                      //String up = map.values().toArray()[1].toString();
                      
                      DetailEstimateDTO dto = new DetailEstimateDTO();
                      dto.setResourceCode(map.values().toArray()[3].toString());
                      dto.setResourceName(map.values().toArray()[0].toString());
                      dto.setUom(map.values().toArray()[2].toString());
                      //System.out.println("Hi Hi Hi unit price");
                      dto.setUnitPrice(new BigDecimal((map.values().toArray()[1].toString())));
                      

                      resultList.add(dto);
                }
          }
          
          return resultList;
  }
	
	
	




	@Override
	public void insertDetailEst(Pcesthtt pcesthtt, List<Pcestdtt> list,TempTb tb ,List<Pegschdmt> peglist,String appointedUserName,String fileRef, String webAppName) throws Exception {
		try{
		
			if(pcesthtt != null){
				pcesthttDaoRemote.createPcesthtt(pcesthtt, webAppName);
			}
			
			if (list!=null){
				for(int i=0; i<=list.size()-1; i++){
					pcestdttDaoRemote.createPcestdtt(list.get(i), webAppName);
				}
			}
			if (peglist!=null && peglist.size() > 0){
				for(int i=0; i<=peglist.size()-1; i++){
					pegschdmtDao.createPegschdmt(peglist.get(i), webAppName);
				}
			}

			insertPIVDetails(tb,webAppName);
			
		
			EstimateReference refe = estimateReferenceDaoRemote.findByWorkEstimateNo(pcesthtt.getId().getEstimateNo(),pcesthtt.getId().getDeptId(), webAppName);
			
			 SpestedyCons spestedyCons = new SpestedyCons();
			
			if(refe != null){
			
				
				 List<SpestedyCons> spestedyCons1 = spestedyConDaoRemote.getAppointmentByUser(refe.getId().getStandardEstimateNo(),pcesthtt.getId().getEstimateNo(),pcesthtt.getId().getDeptId(),null,webAppName);
				 if(spestedyCons1 == null){
					 List<SpestedyCons> spestedyCons2 = spestedyConDaoRemote.getAppointmentByUser(refe.getId().getStandardEstimateNo(),null,pcesthtt.getId().getDeptId(),null,webAppName);
					 
					 if(spestedyCons2 != null && spestedyCons2.size() == 1 ){
						 spestedyCons = spestedyCons2.get(0);
						 if(spestedyCons.getWestimateNo() == null){
							 spestedyConDaoRemote.updateWorkEstimateNumber(refe.getId().getStandardEstimateNo().trim(), pcesthtt.getId().getDeptId(), AppointmentStatus.DETAIL_EST_CREATED,pcesthtt.getId().getEstimateNo(),webAppName);
						 }else if(spestedyCons.getWestimateNo().trim().equalsIgnoreCase(pcesthtt.getId().getEstimateNo())){
							 spestedyConDaoRemote.changeStatusAppointByWorkEstimate(refe.getId().getStandardEstimateNo().trim(), pcesthtt.getId().getDeptId(), AppointmentStatus.DETAIL_EST_CREATED,pcesthtt.getId().getEstimateNo(),webAppName);
						 }
						
					 }else{
						 
					 }
					
				 }else if(spestedyCons1 != null && spestedyCons1.size() > 0 && spestedyCons1.get(0) != null){
					 spestedyCons = spestedyCons1.get(0);
					 if(spestedyCons.getStatus() !=null && spestedyCons.getStatus().equalsIgnoreCase(AppointmentStatus.ACTIVE_APPOINMENT_STATUS)){
						 spestedyConDaoRemote.changeStatusAppointByWorkEstimate(refe.getId().getStandardEstimateNo().trim(),pcesthtt.getId().getDeptId(), AppointmentStatus.DETAIL_EST_CREATED,pcesthtt.getId().getEstimateNo(),webAppName);
					 }
					
				 }else{
					 if(appointedUserName != null && appointedUserName.equalsIgnoreCase("-1")){//to auto save allocated user when no allocation function
						 appointedUserName = pcesthtt.getEntBy();
					 }
					 if (appointedUserName != null)
					 {		Format format=new Format();
						    spestedyCons = new SpestedyCons();
						 	SpestedyConsPK id= new SpestedyConsPK();
						 	id.setDeptId(pcesthtt.getId().getDeptId());
						 	spestedyCons.setId(id);	
							Calendar calandar= Calendar.getInstance();
							spestedyCons.setAppointmentDate(format.StrToDate(pcesthtt.getEtimateDt().toString().substring(0, 10)));
							//spestedyCons.setDescription(getDescription());
							if(pcesthtt.getDescr() != null){
								if(pcesthtt.getDescr().length() > 100){
									spestedyCons.setDescription(pcesthtt.getDescr().substring(0, 100));
								}else{
									spestedyCons.setDescription(pcesthtt.getDescr());
								}
							}
							spestedyCons.setTimeSession("Morning");
							spestedyCons.setAllocatedTo(appointedUserName);	
							spestedyCons.setAllocatedBy(pcesthtt.getEntBy());
							spestedyCons.setAllocatedDate(calandar.getTime());
							spestedyCons.setAllocatedTime(format.FormatTime());
							spestedyCons.setAppoinmentType(AppointmentStatus.listappointmentType);
							spestedyCons.setStatus(AppointmentStatus.DETAIL_EST_CREATED);
							spestedyCons.setReferenceNo(pcesthtt.getId().getEstimateNo());
							spestedyCons.setWestimateNo(pcesthtt.getId().getEstimateNo());
							spestedyConDaoRemote.makeAnAppointment(spestedyCons,webAppName);
							//ejbspCon.changeStatusAppointByWorkEstimate(getEstimateNo(), pcesthtt.getId().getDeptId(),getEstimateNo(), AppointmentStatus.DETAIL_EST_CREATED);
					 }
				 }
				 refe.setStatus("1");
				 refe.setEntryBy(pcesthtt.getEntBy());
				 refe.setFileReference(fileRef);
				 estimateReferenceDaoRemote.updateEstimateReference(refe, webAppName);
				
			}else{
				
				 List<SpestedyCons> spestedyCons1 = spestedyConDaoRemote.getAppointmentByUser(pcesthtt.getId().getEstimateNo(),pcesthtt.getId().getEstimateNo(),pcesthtt.getId().getDeptId(),null,webAppName);
				 if(spestedyCons1 != null && spestedyCons1.size() == 1 ){
					 spestedyCons = spestedyCons1.get(0);
					 spestedyConDaoRemote.changeStatusAppointByWorkEstimate(pcesthtt.getId().getEstimateNo().trim(), pcesthtt.getId().getDeptId(), AppointmentStatus.DETAIL_EST_CREATED,pcesthtt.getId().getEstimateNo(),webAppName);
					 
				 }
				 
			}
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
	}
	
	public void insertAdditionalMATCostForDetailEstimate(String estimateNo , String deptId,String webAppName) throws Exception {

         	String genRes="F";
         	String normDefault="F";
         	Pcestdtt pcestdtt = new Pcestdtt();
			
			PcestdttPK pcestdttPk = new PcestdttPK();
			pcestdttPk.setDeptId(deptId);
			pcestdttPk.setEstimateNo(estimateNo);
			pcestdttPk.setResCd(Constants.MAT_RESOUR_CODE);
			pcestdttPk.setRevNo(Constants.REV_NO);
			
			pcestdtt.setId(pcestdttPk);
			
			pcestdtt.setResCat(new BigDecimal(2));
			pcestdtt.setEstimateCost(new BigDecimal(0.0));
			pcestdtt.setEstimateQty(new BigDecimal(0.0));
			pcestdtt.setResType(Constants.MAT_RESOUR_TYPE);
			pcestdtt.setUom(Constants.MAT_UOM);
			pcestdtt.setUnitPrice(new BigDecimal(0.0));
			pcestdtt.setNormDefault(normDefault);
			pcestdtt.setGenRes(genRes);
			
			pcestdttDaoRemote.createPcestdtt(pcestdtt, webAppName);
	}
	
	public void getConRateByDeptId(String deptId,String webAppName) throws Exception {
		inwrhmapDaoRemote.getConRateByDeptId(deptId, webAppName);
     	
	}
	public void addAdditionalCost(Pcesthtt pcesthtt,String estimateNo ,String deptId,BigDecimal toCost,String webAppName) throws Exception{
		
		//*************************comment for SAN-TEST *************************
		/*BigDecimal totalCost = new BigDecimal(0.0);
		BigDecimal conrate = inwrhmapDaoRemote.getConRateByDeptId(deptId,webAppName);
    	if(conrate != null){
    		totalCost = insertPcestdttCONTENGENCIESDetails(estimateNo,deptId,toCost,conrate,webAppName);
    		pcesthtt.setStdCost(totalCost);
        	pcesthttDaoRemote.updatePcesthtt(pcesthtt, webAppName);
    	}
    	checkPcestdttOtherMaterialSum(estimateNo,deptId,toCost,webAppName);*/
    	
    	
    	//insertAdditionalMATCostForDetailEstimate(estimateNo,deptId,webAppName);
    	
		/*BigDecimal totalCost = new BigDecimal(0.0);
		PcesthttPK pcesthttPK = new PcesthttPK();
        pcesthttPK.setDeptId(deptId);
        pcesthttPK.setEstimateNo(estimateNo);
        pcesthttPK.setRevNo(Constants.REV_NO);
        
        Pcesthtt pcesthtt = pcesthttDaoRemote.findById(pcesthttPK,webAppName);
        
        if(pcesthtt1 != null){
        	BigDecimal conrate = inwrhmapDaoRemote.getConRateByDeptId(deptId,webAppName);
        	if(conrate != null){
        		totalCost = getPcestdttCONTENGENCIESCost(estimateNo,deptId,toCost.doubleValue(),conrate.doubleValue(),webAppName);
        		pcesthtt1.setStdCost(totalCost);
            	pcesthttDaoRemote.updatePcesthtt(pcesthtt1, webAppName);
        	}
        	
            
        	checkPcestdttOtherMaterialSum(estimateNo,deptId,toCost,webAppName);
        }else{
        	BigDecimal conrate = inwrhmapDaoRemote.getConRateByDeptId(deptId,webAppName);
        	if(conrate != null){
        		totalCost = insertPcestdttCONTENGENCIESDetails(estimateNo,deptId,toCost,conrate,webAppName);
        		pcesthtt1.setStdCost(totalCost);
            	pcesthttDaoRemote.updatePcesthtt(pcesthtt1, webAppName);
        	}
        	checkPcestdttOtherMaterialSum(estimateNo,deptId,toCost,webAppName);
        	insertAdditionalMATCostForDetailEstimate(estimateNo,deptId,webAppName);
        }*/
	}
	public void addAdditionalCostEntries(String estimateNo,String estimateType ,String deptId,String webAppName) throws Exception{
		
		
		
		List<CostCalculationMaster> masterCostList =  costCalculationMasterDaoRemote.findByEstimateType(estimateType, deptId, webAppName);
		if(masterCostList != null && masterCostList.size() > 0){
			for(CostCalculationMaster master : masterCostList){
				if(master.getId().getEntryResCode().equalsIgnoreCase("TOTAL")){
					updateDetail1EstimateCost(estimateNo, master.getCombinationEntry(), deptId, webAppName);
				}else{
					double estimateCost = populateCostForNewEntry(estimateNo, master, deptId, webAppName);
					//if(estimateCost != 0.0){
						//Pcestdmt dmtMaintence=pcestdmtDaoRemote.updatePcestdmtPercentageCost(pcesthmt.getId().getEstimateNo(),deptId,Constants.RES_CD_INTEREST, stdCost,webAppName);
						Pcestdtt dttEntry = pcestdttDaoRemote.getdttByResCD(estimateNo, master.getId().getEntryResCode(), webAppName);
						
						if (dttEntry==null){
							populatePcestdttNewEntry(estimateNo, master.getEntryType(), master.getId().getEntryResCode(), deptId, new BigDecimal(estimateCost), webAppName);
						}else{
							pcestdttDaoRemote.updatePcestdttPercentageCost(estimateNo, deptId, master.getId().getEntryResCode(), new BigDecimal(estimateCost), new BigDecimal(estimateCost), webAppName);
						}
					//}
					
				}
			}
		}
		
		Pcestdtt dttMATEntry = pcestdttDaoRemote.getdttByResCD(estimateNo, Constants.MAT_RESOUR_CODE, webAppName);
		if(dttMATEntry == null){
			insertAdditionalMATCostForDetailEstimate(estimateNo,deptId,webAppName);
		}
		
	}
	
	public void updateAdditionalCost(Pcesthtt pcesthtt ,String deptId,BigDecimal toCost,String webAppName) throws Exception{
		/*List<CostCalculationMaster> masterCostList =  costCalculationMasterDaoRemote.findByEstimateType(estimateType, deptId, webAppName);
		if(masterCostList != null && masterCostList.size() > 0){
			for(CostCalculationMaster master : masterCostList){
				if(master.getId().getEntryResCode().equalsIgnoreCase("TOTAL")){
					updateDetail1EstimateCost(pcesthtt.getId().getEstimateNo(), master.getCombinationEntry(), deptId, webAppName);
				}else{
					
					
					double estimateCost = populateCostForNewEntry(pcesthtt.getId().getEstimateNo(), master, deptId, webAppName);
					if(estimateCost != 0.0){
						populatePcestdttNewEntry(pcesthtt.getId().getEstimateNo(), master.getEntryType(), master.getId().getEntryResCode(), deptId, new BigDecimal(estimateCost), webAppName);
					}
					
				}
			}
		}*/
		
    	
    	insertAdditionalMATCostForDetailEstimate(pcesthtt.getId().getEstimateNo(),deptId,webAppName);
    	
    	
    	
		BigDecimal totalCost = new BigDecimal(0.0);
        if(pcesthtt != null){
        	BigDecimal conrate = inwrhmapDaoRemote.getConRateByDeptId(deptId,webAppName);
        	
        	if(conrate != null){
        		totalCost = getPcestdttCONTENGENCIESCost(pcesthtt.getId().getEstimateNo(),deptId,toCost.doubleValue(),conrate.doubleValue(),webAppName);
        		pcesthtt.setStdCost(totalCost);
            	pcesthttDaoRemote.updatePcesthtt(pcesthtt, webAppName);
        	}
        	
            
        	checkPcestdttOtherMaterialSum(pcesthtt.getId().getEstimateNo(),deptId,toCost,webAppName);
        }
	}
	/*public void updateAdditionalCost(Pcesthtt pcesthtt ,String deptId,BigDecimal toCost,String webAppName) throws Exception{
		
		BigDecimal totalCost = new BigDecimal(0.0);
        if(pcesthtt != null){
        	BigDecimal conrate = inwrhmapDaoRemote.getConRateByDeptId(deptId,webAppName);
        	
        	if(conrate != null){
        		totalCost = getPcestdttCONTENGENCIESCost(pcesthtt.getId().getEstimateNo(),deptId,toCost.doubleValue(),conrate.doubleValue(),webAppName);
        		pcesthtt.setStdCost(totalCost);
            	pcesthttDaoRemote.updatePcesthtt(pcesthtt, webAppName);
        	}
        	
            
        	checkPcestdttOtherMaterialSum(pcesthtt.getId().getEstimateNo(),deptId,toCost,webAppName);
        }
	}*/
	private BigDecimal getPcestdttCONTENGENCIESCost(String estimateNo , String deptId,double toCost,double conrat,String webAppName)throws Exception {
		 
        // double totalcost = 0.0;
        // double preConCost = 0.0;
         double finalCost =  0.0;
         
        // BigDecimal  preConCost = pcestdttDaoRemote.getSUMByResType(estimateNo,Constants.RES_TYPE_CONTINGENCIES_COST,webAppName);
         BigDecimal  totalcost =  pcestdttDaoRemote.getSUMOfMATandMAT_OTHER(estimateNo, webAppName);
        
         double totalcostWithConr = 0.0;
          if(totalcost != null)   {
        	  totalcostWithConr = totalcost.doubleValue() * conrat;
          }
        
         
         double estimateQty = totalcostWithConr ;
         double estimateCost = totalcostWithConr ;
         if(estimateCost < Constants.MINIMUM_ESTIMATE_COST.doubleValue()){
        	 estimateCost = Constants.MINIMUM_ESTIMATE_COST.doubleValue();
         }
           
        /* if(preConCost != null && preConCost == totalcost ){
             finalCost =  toCost;
          }else{
             if(preConCost != null){
            	 toCost = toCost - preConCost.doubleValue();
            	 if(totalcost != null){
            		 toCost = totalcost.doubleValue() + toCost;
            	 }
                 finalCost =  toCost; 
             }
            
             
          }*/
        Pcestdtt dtt = pcestdttDaoRemote.findBy3PK(estimateNo.trim(), deptId, Constants.RES_CODE_CONTINGENCIES_COST,Constants.REV_NO, webAppName);
        //Pcestdtt dtt = populatePcestdttInsertCONTENGENCIESDetails(estimateNo,deptId,new BigDecimal(estimateCost));
        if(dtt != null){
        	dtt.setEstimateCost(new BigDecimal(estimateCost));
        	pcestdttDaoRemote.updatePcestdtt(dtt, webAppName);
        }
        return new BigDecimal(finalCost);
		
		//return conrate;
	
	}
	private BigDecimal getPcestdmtCONTENGENCIESCost(String estimateNo , String deptId,double toCost,double conrat,String webAppName)throws Exception {
		 
      /*  // double totalcost = 0.0;
        // double preConCost = 0.0;
         double finalCost =  0.0;
         
         BigDecimal  preConCost = pcestdmtDaoRemote.getSUMByResType(estimateNo,Constants.RES_TYPE_CONTINGENCIES_COST,webAppName);
         BigDecimal  totalcost =  pcestdmtDaoRemote.getSUMOfMATandMAT_OTHER(estimateNo, webAppName);
        
         double totalcostWithConr = 0.0;
          if(totalcost != null)   {
        	  totalcostWithConr = totalcost.doubleValue() * conrat;
          }
        
         
         double estimateQty = totalcostWithConr ;
         double estimateCost = totalcostWithConr ;
         if(estimateCost < Constants.MINIMUM_ESTIMATE_COST.doubleValue()){
        	 estimateCost = Constants.MINIMUM_ESTIMATE_COST.doubleValue();
         }
           
         if(preConCost != null && preConCost == totalcost ){
             finalCost =  toCost;
          }else{
             if(preConCost != null){
            	 toCost = toCost - preConCost.doubleValue();
            	 if(totalcost != null){
            		 toCost = totalcost.doubleValue() + toCost;
            	 }
                 finalCost =  toCost; 
             }
            
             
          }
        Pcestdmt dmt = pcestdmtDaoRemote.findBy3PK(estimateNo.trim(), deptId, Constants.RES_CODE_CONTINGENCIES_COST, webAppName);
        //Pcestdtt dtt = populatePcestdttInsertCONTENGENCIESDetails(estimateNo,deptId,new BigDecimal(estimateCost));
        if(dmt != null){
        	dmt.setEstimateCost(new BigDecimal(estimateCost));
        	pcestdmtDaoRemote.updatePcestdmt(dmt, webAppName);
        }
        return new BigDecimal(finalCost);
		
		//return conrate;
*/	
		return new BigDecimal(0);
	}
	private BigDecimal insertPcestdttCONTENGENCIESDetails(String estimateNo , String deptId,BigDecimal toCost,BigDecimal conrat,String webAppName)throws Exception {

        BigDecimal totalCost = new BigDecimal(0.0);
        
        totalCost =  pcestdttDaoRemote.getSUMOfMATandMAT_OTHER(estimateNo, webAppName);
        double totalcostWithConr = 0.0;
        //BigDecimal conrate = inwrhmapDaoRemote.getConRateByDeptId(deptId,webAppName);
        if(totalCost != null){
	        totalcostWithConr = totalCost.doubleValue();
	        if(conrat != null){
	        	 totalcostWithConr = totalCost.doubleValue() * conrat.doubleValue();
	             
	        }
        }
        double estimateQty = totalcostWithConr ;
        double estimateCost = totalcostWithConr ;
        if(estimateCost < Constants.MINIMUM_ESTIMATE_COST.doubleValue()){
        	estimateCost = Constants.MINIMUM_ESTIMATE_COST.doubleValue();
        }

        totalCost = new BigDecimal(toCost.doubleValue() +totalcostWithConr);
        
        Pcestdtt dtt = populatePcestdttInsertCONTENGENCIESDetails(estimateNo,deptId,new BigDecimal(estimateCost));
		pcestdttDaoRemote.createPcestdtt(dtt, webAppName);
        
        return totalCost;

	
	}
	
	private Pcestdtt populatePcestdttInsertCONTENGENCIESDetails(String estimateNo,String deptId ,BigDecimal estimateCost){
		
		Pcestdtt pcestdtt = new Pcestdtt();
		
		PcestdttPK pcestdttPk = new PcestdttPK();
		pcestdttPk.setDeptId(deptId);
		pcestdttPk.setEstimateNo(estimateNo);
		pcestdttPk.setResCd(Constants.RES_CODE_CONTINGENCIES_COST);
		pcestdttPk.setRevNo(Constants.REV_NO);
		
		pcestdtt.setId(pcestdttPk);
		
		pcestdtt.setResCat(new BigDecimal(Constants.RES_CAT_CONTIGENCIES));
		pcestdtt.setEstimateCost(estimateCost);
		pcestdtt.setEstimateQty(estimateCost);
		pcestdtt.setResType(Constants.RES_TYPE_CONTINGENCIES_COST);
		pcestdtt.setUom(Constants.CONTIGENCIES_UOM);
		pcestdtt.setUnitPrice(Constants.UNIT_PRICE_CONTIGENCIES);
		pcestdtt.setNormDefault(Constants.NORM_DEFAULT);
		pcestdtt.setGenRes(Constants.GEN_RES);
		pcestdtt.setTolerance(Constants.TOLLERANCE_CONTIGENCIES);
		return pcestdtt;
		
	}
	
	private int populatePcestdttNewEntry(String estimateNo,String resType,String resCode,String deptId ,BigDecimal estimateCost,String webAppName){
		
		Pcestdtt pcestdtt = new Pcestdtt();
		
		PcestdttPK pcestdttPk = new PcestdttPK();
		pcestdttPk.setDeptId(deptId);
		pcestdttPk.setEstimateNo(estimateNo);
		pcestdttPk.setResCd(resCode);
		pcestdttPk.setRevNo(Constants.REV_NO);
		
		pcestdtt.setId(pcestdttPk);
		
		pcestdtt.setResCat(new BigDecimal(Constants.RES_CAT_DEFAULT));
		pcestdtt.setEstimateCost(estimateCost);
		pcestdtt.setEstimateQty(estimateCost);
		pcestdtt.setResType(resType);
		pcestdtt.setUom(Constants.MAT_UOM);
		pcestdtt.setUnitPrice(Constants.UNIT_PRICE_DEFAULT);
		pcestdtt.setNormDefault(Constants.NORM_DEFAULT);
		pcestdtt.setGenRes(Constants.GEN_RES);
		pcestdtt.setTolerance(Constants.TOLLERANCE__DEFAULT);
		
		pcestdttDaoRemote.createPcestdtt(pcestdtt, webAppName);
		//int status = pcestdttDaoRemote.updatePcestdttPercentageCost(estimateNo, deptId, resCode, estimateCost, webAppName);
		
		return 0;
		
	}
	private double populateCostForNewEntry(String estimateNo,CostCalculationMaster masterDetails,String deptId ,String webAppName){
		String paramsList = null;
		if(masterDetails != null && masterDetails.getCombinationEntry() != null){
			
			
			double estimateCost =0;
			//String qryStr1 = "select sum(a.estimateCost) from Pcestdtt a where TRIM(a.id.estimateNo)=:estimateNo AND TRIM(a.id.deptId)=:deptId";
			
			//}
			StringBuffer qryStr = new StringBuffer();	
			Pcesthtt pcesthtt = null;
			BigDecimal rebateCost = null;
			if(masterDetails.getFundId() == null){
				String qryStr1 = "select sum(a.estimateCost) from Pcestdtt a where TRIM(a.id.estimateNo)=:estimateNo AND TRIM(a.id.deptId)=:deptId AND a.id.revNo=2 ";
				//StringBuffer qryStr = new StringBuffer();
				qryStr.append(qryStr1);
				//if(masterDetails.getCombinationEntry() != null){
					qryStr.append( " and a.resType in (");
					qryStr.append( masterDetails.getCombinationEntry());
					qryStr.append(")");
			 }else{
				pcesthtt=pcesthttDaoRemote.findByEstimationNo(estimateNo, deptId,2L, webAppName);
				
				if (pcesthtt != null && pcesthtt.getFundId() != null && masterDetails.getFundId() != null && pcesthtt.getFundId().trim().equalsIgnoreCase(masterDetails.getFundId())){
					rebateCost = pcesthtt.getPartialAmt();
					if(masterDetails.getCombinationEntry().equalsIgnoreCase("ALL") && masterDetails.getFundId() != null ){
						String qryStr1 = "select sum(a.estimateCost) from Pcestdtt a where TRIM(a.id.estimateNo)=:estimateNo AND TRIM(a.id.deptId)=:deptId AND a.id.revNo=2";
						
						qryStr.append(qryStr1);
						
					}
				}
			 }
			BigDecimal stdCost1 = null;
			if(qryStr.length() != 0){
				Query query1 = getEntityManager(webAppName).createQuery(qryStr.toString());
				query1.setParameter("estimateNo", estimateNo.trim());
				
				query1.setParameter("deptId", deptId.trim());
				
				//query1.setParameter("priority", masterDetails.getPriority());
				
				stdCost1 = (BigDecimal) query1.getSingleResult();
				System.out.println(stdCost1);
			}
			
			//stdCost1 = new BigDecimal(122321.00);
			boolean status = false;
			
			if(stdCost1 != null){
			//Pcestdmt dmtMaintence=pcestdmtDaoRemote.updatePcestdmtPercentageCost(pcesthmt.getId().getEstimateNo(),deptId,Constants.RES_CD_INTEREST, stdCost,webAppName);
				//Pcestdmt dmtEntry = pcestdttDaoRemote.getdttByResCD(estimateNo, masterDetails.getId().getEntryResCode(), webAppName);
				
				//if (dmtEntry!=null){
					if(rebateCost !=null && pcesthtt != null && pcesthtt.getFundId() != null && masterDetails.getFundId() != null && pcesthtt.getFundId().trim().equalsIgnoreCase(masterDetails.getFundId())){
						stdCost1 = new BigDecimal(stdCost1.doubleValue() - rebateCost.doubleValue());
					}else{
						estimateCost = stdCost1.doubleValue()*masterDetails.getPercentage(); 
					}
					if(masterDetails.getId().getEntryResCode().equals(Constants.RES_CODE_CONTINGENCIES_COST)){
						if(estimateCost < Constants.MINIMUM_ESTIMATE_COST.doubleValue()){
							estimateCost = Constants.MINIMUM_ESTIMATE_COST.doubleValue();
						}
					}
					//pcestdmtDaoRemote.updatePcestdmtPercentageCost(estimateNo, deptId, masterDetails.getEntryCode(), new BigDecimal(estimateCost), webAppName);
			
				//}
			}
			return estimateCost;
		}
		return 0;
	}
	
	private String getParamList(String paramsString){
		String[] params= paramsString.split("/");
		List<String> parameterList = new ArrayList<String>();
		String finalString = null;
		if(params != null){
			for(int i=0 ; i<params.length ; i++){
				parameterList.add(params[i]);
			}
		}
		if(parameterList != null && parameterList.size() > 0){
			finalString = getCSVDids(parameterList);
		}
		
		return finalString;
		
	}
	public String getCSVDids(List<String> item ) {
	    StringBuffer sb = new StringBuffer();
	    Iterator<String> iterator = item.iterator();
	    while (iterator.hasNext()) {
	        sb.append("'").append(iterator.next()).append("'");
	    	//sb.append(iterator.next());
	        if (iterator.hasNext()){
	        	sb.append(",");
	        }	            
	    }
	
	    return sb.toString();
	}
	private BigDecimal checkPcestdttOtherMaterialSum(String estimateNo , String deptId,BigDecimal estimateCost,String webAppName)throws Exception {
		
		/**BigDecimal  othermatSum = pcestdttDaoRemote.getSUMByResType(estimateNo,Constants.RES_TYPE_MAT_COST_OTHER,webAppName);
		
        if (othermatSum != null && othermatSum.doubleValue() !=0.0 ) {
        	Long count=pcestdttDaoRemote.getRawCountOtherMat(estimateNo,deptId,Constants.RES_CODE_OTHER_MATERIALS,webAppName);
            if(count == 0){
                insertPcestdttOtherMaterialSum(estimateNo , deptId,estimateCost, webAppName);
            }else{
            	
            	pcestdttDaoRemote.updatePcestdttOtherMaterialSum(estimateNo, deptId, Constants.RES_CODE_OTHER_MATERIALS, estimateCost, webAppName);
            }

        
            
        }*/
		/**String resType="";
		double  othermatSum= 0.0;
		List<Map>  mapCostList = pcestdttDaoRemote.getSUMByResType(estimateNo,Constants.RES_TYPE_MAT_COST_OTHER,webAppName);
		for(int i=0 ; i<mapCostList.size() ; i++){
			resType = mapCostList.get(i).values().toArray()[0].toString();
			
			if(resType != null && resType.trim().equalsIgnoreCase("MAT-COST-OTHER")){
				othermatSum = new BigDecimal(mapCostList.get(i).values().toArray()[1].toString()).doubleValue();
				//packet.put("materialCost",new BigDecimal(mapCostList.get(0).values().toArray()[0].toString()));
			}
		}
		
        if (othermatSum !=0.0 ) {
        	Long count=pcestdttDaoRemote.getRawCountOtherMat(estimateNo,deptId,Constants.RES_CODE_OTHER_MATERIALS,webAppName);
            if(count == 0){
                insertPcestdttOtherMaterialSum(estimateNo , deptId,estimateCost, webAppName);
            }else{
            	
            	pcestdttDaoRemote.updatePcestdttOtherMaterialSum(estimateNo, deptId, Constants.RES_CODE_OTHER_MATERIALS, estimateCost, webAppName);
            }

        
            
        }
        BigDecimal objBigDecimal = new BigDecimal(othermatSum);
		
		return objBigDecimal;*/
		
		return null;
		
		
	}

	private BigDecimal insertPcestdttOtherMaterialSum(String estimateNo , String deptId,BigDecimal estimateCost,String webAppName)throws Exception {
		
		
        
		Pcestdtt pcestdtt = new Pcestdtt();
		
		PcestdttPK pcestdttPk = new PcestdttPK();
		pcestdttPk.setDeptId(deptId);
		pcestdttPk.setEstimateNo(estimateNo);
		pcestdttPk.setResCd(Constants.RES_CODE_OTHER_MATERIALS);
		pcestdttPk.setRevNo(Constants.REV_NO);
		
		pcestdtt.setId(pcestdttPk);
		
		pcestdtt.setResCat(new BigDecimal(Constants.RES_CAT_CONTIGENCIES));
		pcestdtt.setEstimateCost(estimateCost);
		pcestdtt.setEstimateQty(estimateCost);
		pcestdtt.setResType(Constants.RES_TYPE_OTHER_MATERIALS);
		pcestdtt.setUom(Constants.OTHER_MATERIALS_UOM);
		pcestdtt.setUnitPrice(Constants.UNIT_PRICE_OTHER_MATERIALS);
		pcestdtt.setNormDefault(Constants.NORM_DEFAULT);
		pcestdtt.setGenRes(Constants.GEN_RES);
		pcestdtt.setTolerance(Constants.TOLLERANCE_OTHER_MATERIALS);
		pcestdttDaoRemote.createPcestdtt(pcestdtt, webAppName);
		
		return estimateCost;
		
	}

	public  void insertPIVDetails(TempTb tempTb,String webAppName)throws Exception{
		tempTbDaoRemote.createTempTb(tempTb, webAppName);
	}
	@Override
	public  TempTb findPIVDetails(String estimationNo,String deptId,String webAppName)throws Exception{
		return tempTbDaoRemote.findById(estimationNo,deptId, webAppName);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateWorkEstimateDetails(Pcesthtt pcesthtt, Speststd speststd, List<Pcestdtt> addlist, List<Pcestdtt> updList,List<Pcestdtt> deleList,List<Pegschdmt> addedPegschdmtList,List<Pegschdmt> updatedPegschdmtList,TempTb tempTb, String webAppName) {
		try{
			pcesthttDaoRemote.updatePcesthtt(pcesthtt, webAppName);
			if(speststd != null){
				speststdDaoRemote.updateSpeststd(speststd, webAppName);
			}
			if (addlist!=null){
				for(int i=0; i<=addlist.size()-1; i++){
					pcestdttDaoRemote.createPcestdtt(addlist.get(i), webAppName);
				}
			}
			if (updList!=null){
				for(int j=0; j<=updList.size()-1; j++){
					pcestdttDaoRemote.updatePcestdtt(updList.get(j), webAppName);
				}
			}

			if (deleList!=null){	
				for(int j=0; j<=deleList.size()-1; j++){
					pcestdttDaoRemote.removePcestdtt(deleList.get(j), webAppName);
				}
			}
			if (addedPegschdmtList!=null && addedPegschdmtList.size() > 0){	
				for(int j=0; j<=addedPegschdmtList.size()-1; j++){
					pegschdmtDao.createPegschdmt(addedPegschdmtList.get(j), webAppName);
				}
			}
			if (updatedPegschdmtList!=null && updatedPegschdmtList.size() > 0){	
				for(int j=0; j<=updatedPegschdmtList.size()-1; j++){
					pegschdmtDao.updatePegschdmt(updatedPegschdmtList.get(j), webAppName);
				}
			}
			if(tempTb != null){
				tempTbDaoRemote.updateTempTb(tempTb, webAppName);
			}
			//****************comment for SAN-TEST*****************
			//updateAdditionalCost(pcesthtt,pcesthtt.getId().getDeptId(),pcesthtt.getStdCost(),webAppName);
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}

	public boolean updateEstimateStatus(String estimateNumber,String costCenter,Long status,String approvedBy,Date approvedDate ,String rejectedBy,Date rejectedDate,String planningByPE,Date planningDatePE,String valiadteByEE,Date valiadteDateEE,String valiadteByCE,Date valiadteDateCE,String rejectedReasonCE,String rejectedReasonPE,
			String rejectedReasonEE,String webAppName) throws PersistenceException{
		
		return spstdesthmtDaoRemote.updateEstimateStatus(estimateNumber, costCenter, status,approvedBy,approvedDate,rejectedBy,rejectedDate,planningByPE,planningDatePE,valiadteByEE,valiadteDateEE,valiadteByCE,valiadteDateCE,rejectedReasonCE,rejectedReasonPE,rejectedReasonEE,webAppName);
		
	}
	//main menu
	@SuppressWarnings("unchecked")
	@Override
	public List<EstimateDisplay> getRejectedEstimateList(String deptId,Long status,String appType,String userId,String userLevel, String webAppName) throws PersistenceException{
		//getEntityManager(webAppName);
		//String qryStr ="SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Spstdesthmt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId)= :deptId AND  p.status = :status AND a.applicationType= :applicationType and order by p.id.estimateNo ";
		String qryStr = null;
		if(userLevel.equalsIgnoreCase("EE")){
			qryStr ="SELECT new estimate.model.EstimateDisplay(TRIM(s.id.stdNo), s.id.deptId, a.applicationType, a.applicationSubType,s.rejectedBy, s.rejectedDate, s.secDeposit, s.totalCost, con.fundSource, s.description, s.rejectedBy,  s.rejectedDate, s.rejReasonEE ,ap.fullName, con.ServiceDepoName,con.schemeName,con.schemeNo,con.electorate,con.representative,con.repContact1) FROM Spstdesthmt s, Application a,Applicant ap,WiringLandDetailCon con,ApplicationReference ref WHERE TRIM(s.id.stdNo)=ref.applicationNo AND TRIM(a.id.applicationId)=ref.id.applicationId AND TRIM(a.id.applicationId)=con.id.applicationId AND TRIM(a.idNo)=ap.idNo AND trim(s.id.deptId)= :deptId AND  s.status = :status AND a.applicationType= :applicationType and s.entryBy = :userId order by s.id.stdNo";	
			
		}else if(userLevel.equalsIgnoreCase("CE")){
			qryStr ="SELECT new estimate.model.EstimateDisplay(TRIM(s.id.stdNo), s.id.deptId, a.applicationType, a.applicationSubType,s.rejectedBy, s.rejectedDate, s.secDeposit, s.totalCost, con.fundSource, s.description, s.rejectedBy,  s.rejectedDate, s.rejReasonCE,ap.fullName,con.ServiceDepoName,con.schemeName,con.schemeNo,con.electorate,con.representative,con.repContact1 ) FROM Spstdesthmt s, Application a,Applicant ap,WiringLandDetailCon con,ApplicationReference ref WHERE TRIM(s.id.stdNo)=ref.applicationNo AND TRIM(a.id.applicationId)=ref.id.applicationId AND TRIM(a.id.applicationId)=con.id.applicationId AND TRIM(a.idNo)=ap.idNo AND trim(s.id.deptId)= :deptId AND  s.status = :status AND a.applicationType= :applicationType and s.entryBy = :userId order by s.id.stdNo";	
			
		}else if(userLevel.equalsIgnoreCase("PE")){
			qryStr ="SELECT new estimate.model.EstimateDisplay(TRIM(s.id.stdNo), s.id.deptId, a.applicationType, a.applicationSubType,s.rejectedBy, s.rejectedDate, s.secDeposit, s.totalCost, con.fundSource, s.description, s.rejectedBy,  s.rejectedDate, s.rejReasonPE,ap.fullName,con.ServiceDepoName,con.schemeName,con.schemeNo,con.electorate,con.representative,con.repContact1 ) FROM Spstdesthmt s, Application a,Applicant ap,WiringLandDetailCon con,ApplicationReference ref WHERE TRIM(s.id.stdNo)=ref.applicationNo AND TRIM(a.id.applicationId)=ref.id.applicationId AND TRIM(a.id.applicationId)=con.id.applicationId AND TRIM(a.idNo)=ap.idNo AND trim(s.id.deptId)= :deptId AND  s.status = :status AND a.applicationType= :applicationType and s.entryBy = :userId order by s.id.stdNo";	
			
		}else if(userLevel.equalsIgnoreCase("DGM")){
			qryStr ="SELECT new estimate.model.EstimateDisplay(TRIM(s.id.stdNo), s.id.deptId, a.applicationType, a.applicationSubType,s.rejectedBy, s.rejectedDate, s.secDeposit, s.totalCost, con.fundSource, s.description, s.rejectedBy,  s.rejectedDate, s.rejReasonCE ,ap.fullName,con.ServiceDepoName,con.schemeName,con.schemeNo,con.electorate,con.representative,con.repContact1) FROM Spstdesthmt s, Application a,Applicant ap,WiringLandDetailCon con,ApplicationReference ref WHERE TRIM(s.id.stdNo)=ref.applicationNo AND TRIM(a.id.applicationId)=ref.id.applicationId AND TRIM(a.id.applicationId)=con.id.applicationId AND TRIM(a.idNo)=ap.idNo AND trim(s.id.deptId)= :deptId AND  s.status = :status AND a.applicationType= :applicationType and s.entryBy = :userId order by s.id.stdNo";	
			
		}else{
			qryStr ="SELECT new estimate.model.EstimateDisplay(TRIM(s.id.stdNo), s.id.deptId, a.applicationType, a.applicationSubType,s.rejectedBy, s.rejectedDate, s.secDeposit, s.totalCost, con.fundSource, s.description, s.rejectedBy,  s.rejectedDate, s.rejReasonEE,ap.fullName,con.ServiceDepoName,con.schemeName,con.schemeNo,con.electorate,con.representative,con.repContact1 ) FROM Spstdesthmt s, Application a,Applicant ap,WiringLandDetailCon con,ApplicationReference ref WHERE TRIM(s.id.stdNo)=ref.applicationNo AND TRIM(a.id.applicationId)=ref.id.applicationId AND TRIM(a.id.applicationId)=con.id.applicationId AND TRIM(a.idNo)=ap.idNo AND trim(s.id.deptId)= :deptId AND  s.status = :status AND a.applicationType= :applicationType and s.entryBy = :userId order by s.id.stdNo";	
			
		}
		
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		query.setParameter("applicationType", appType);
		query.setParameter("userId", userId);
		//query.setParameter("applicationType", applicationType);
		List<EstimateDisplay> list = query.getResultList();
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<EstimateDisplay> getApprovedEstimateList(String deptId,Long status,String appType,String userId, String webAppName)throws PersistenceException {
		//getEntityManager(webAppName);
		//String qryStr ="SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Spstdesthmt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId)= :deptId AND  p.status = :status AND a.applicationType= :applicationType and order by p.id.estimateNo ";
		//String qryStr ="SELECT new estimate.model.EstimateDisplay(TRIM(s.id.stdNo), s.id.deptId, a.applicationType, a.applicationSubType,s.approvedBy, s.approvedDate, s.secDeposit, s.totalCost, con.fundSource, s.description, s.rejectedBy,  s.rejectedDate, s.rejReasonEE ) FROM Spstdesthmt s, Application a,WiringLandDetailCon con,ApplicationReference ref WHERE TRIM(s.id.stdNo)=ref.applicationNo AND TRIM(a.id.applicationId)=ref.id.applicationId AND TRIM(a.id.applicationId)=con.id.applicationId AND trim(s.id.deptId)= :deptId AND  s.status = :status AND a.applicationType= :applicationType and s.entryBy = :userId order by s.id.stdNo";	
		String qryStr = "SELECT new estimate.model.EstimateDisplay(TRIM(s.id.stdNo), s.id.deptId, a.applicationType, a.applicationSubType,s.approvedBy, s.approvedDate, s.secDeposit, s.totalCost, con.fundSource, s.description, s.entryBy,  s.entryDate, s.rejReasonEE ,ap.fullName,con.ServiceDepoName,con.schemeName,con.schemeNo,con.electorate,con.representative,con.repContact1) FROM Spstdesthmt s, Application a,Applicant ap,WiringLandDetailCon con,ApplicationReference ref WHERE TRIM(s.id.stdNo)=ref.applicationNo AND TRIM(a.id.applicationId)=ref.id.applicationId AND TRIM(a.id.applicationId)=con.id.applicationId AND TRIM(a.idNo)=ap.idNo AND  trim(s.id.deptId)= :deptId AND a.applicationType= :applicationType  AND s.entryBy = :userId AND  s.status = :status order by s.id.stdNo";
		StringBuffer buff = new StringBuffer();
		buff.append("SELECT new estimate.model.EstimateDisplay(TRIM(s.id.stdNo), s.id.deptId, a.applicationType, a.applicationSubType,s.approvedBy, s.approvedDate, s.secDeposit, s.totalCost, con.fundSource, s.description, s.entryBy,  s.entryDate, s.rejReasonEE ,ap.fullName,con.ServiceDepoName,con.schemeName,con.schemeNo,con.electorate,con.representative,con.repContact1) FROM Spstdesthmt s, Application a,Applicant ap,WiringLandDetailCon con,ApplicationReference ref WHERE TRIM(s.id.stdNo)=ref.applicationNo AND TRIM(a.id.applicationId)=ref.id.applicationId AND TRIM(a.id.applicationId)=con.id.applicationId AND TRIM(a.idNo)=ap.idNo AND trim(s.id.deptId)= :deptId AND a.applicationType= :applicationType ");;
		if(userId != null){
			buff.append(" AND s.entryBy = :userId ");
		}
		buff.append(" AND  s.status = :status order by s.id.stdNo");
		Query query = getEntityManager(webAppName).createQuery(buff.toString());
		query.setParameter("deptId", deptId);
		
		query.setParameter("applicationType", appType);
		if(userId != null){
			query.setParameter("userId", userId);
		}
		query.setParameter("status", status);
		//query.setParameter("applicationType", applicationType);
		List<EstimateDisplay> list = query.getResultList();
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EstimateDisplay> getConfirmPIVList(String deptId,Long status,String appType,String userId, String webAppName)throws PersistenceException {
		//getEntityManager(webAppName);
		//String qryStr ="SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Spstdesthmt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId)= :deptId AND  p.status = :status AND a.applicationType= :applicationType and order by p.id.estimateNo ";
		//String qryStr ="SELECT new estimate.model.EstimateDisplay(TRIM(s.id.stdNo), s.id.deptId, a.applicationType, a.applicationSubType,s.approvedBy, s.approvedDate, s.secDeposit, s.totalCost, con.fundSource, s.description, s.rejectedBy,  s.rejectedDate, s.rejReasonEE ) FROM Spstdesthmt s, Application a,WiringLandDetailCon con,ApplicationReference ref WHERE TRIM(s.id.stdNo)=ref.applicationNo AND TRIM(a.id.applicationId)=ref.id.applicationId AND TRIM(a.id.applicationId)=con.id.applicationId AND trim(s.id.deptId)= :deptId AND  s.status = :status AND a.applicationType= :applicationType and s.entryBy = :userId order by s.id.stdNo";	
		String qryStr = "SELECT new estimate.model.EstimateDisplay(TRIM(s.id.stdNo), s.id.deptId, a.applicationType, a.applicationSubType,s.approvedBy, s.approvedDate, s.secDeposit, s.totalCost, con.fundSource, s.description, s.entryBy,  s.entryDate, s.rejReasonEE ,ap.fullName,con.ServiceDepoName,con.schemeName,con.schemeNo,con.electorate,con.representative,con.repContact1,piv.id.pivNo) FROM Spstdesthmt s, Application a,Applicant ap,WiringLandDetailCon con,ApplicationReference ref,PivDetail piv WHERE piv.status = 'N' AND TRIM(s.id.stdNo)=piv.referenceNo AND TRIM(s.id.stdNo)=ref.applicationNo AND TRIM(a.id.applicationId)=ref.id.applicationId AND TRIM(a.id.applicationId)=con.id.applicationId AND TRIM(a.idNo)=ap.idNo AND  trim(s.id.deptId)= :deptId AND a.applicationType= :applicationType  AND s.entryBy = :userId AND  s.status = :status order by s.id.stdNo";
		StringBuffer buff = new StringBuffer();
		buff.append("SELECT new estimate.model.EstimateDisplay(TRIM(s.id.stdNo), s.id.deptId, a.applicationType, a.applicationSubType,s.approvedBy, s.approvedDate, s.secDeposit, s.totalCost, con.fundSource, s.description, s.entryBy,  s.entryDate, s.rejReasonEE ,ap.fullName,con.ServiceDepoName,con.schemeName,con.schemeNo,con.electorate,con.representative,con.repContact1,piv.id.pivNo) FROM Spstdesthmt s, Application a,Applicant ap,WiringLandDetailCon con,ApplicationReference ref ,PivDetail piv WHERE piv.status = 'N' AND TRIM(s.id.stdNo)=piv.referenceNo AND TRIM(s.id.stdNo)=ref.applicationNo AND TRIM(a.id.applicationId)=ref.id.applicationId AND TRIM(a.id.applicationId)=con.id.applicationId AND TRIM(a.idNo)=ap.idNo AND trim(s.id.deptId)= :deptId AND a.applicationType= :applicationType ");;
		if(userId != null){
			buff.append(" AND s.entryBy = :userId ");
		}
		buff.append(" AND  s.status = :status order by s.id.stdNo");
		
		Query query = getEntityManager(webAppName).createQuery(buff.toString());
		query.setParameter("deptId", deptId);
		
		query.setParameter("applicationType", appType);
		if(userId != null){
			query.setParameter("userId", userId);
		}
		query.setParameter("status", status);
		System.out.println("status"+ buff.toString() );
		//query.setParameter("applicationType", applicationType);
		List<EstimateDisplay> list = query.getResultList();
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EstimateDisplay> getStdEstimateList(String deptId,List<Long> status,String appType,String userId, String webAppName)throws PersistenceException {
		//getEntityManager(webAppName);
		//String qryStr ="SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Spstdesthmt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId)= :deptId AND  p.status = :status AND a.applicationType= :applicationType and order by p.id.estimateNo ";
		//String qryStr ="SELECT new estimate.model.EstimateDisplay(TRIM(s.id.stdNo), s.id.deptId, a.applicationType, a.applicationSubType,s.approvedBy, s.approvedDate, s.secDeposit, s.totalCost, con.fundSource, s.description, s.rejectedBy,  s.rejectedDate, s.rejReasonEE ) FROM Spstdesthmt s, Application a,WiringLandDetailCon con,ApplicationReference ref WHERE TRIM(s.id.stdNo)=ref.applicationNo AND TRIM(a.id.applicationId)=ref.id.applicationId AND TRIM(a.id.applicationId)=con.id.applicationId AND trim(s.id.deptId)= :deptId AND  s.status = :status AND a.applicationType= :applicationType and s.entryBy = :userId order by s.id.stdNo";	
		String qryStr = "SELECT new estimate.model.EstimateDisplay(TRIM(s.id.stdNo), s.id.deptId, a.applicationType, a.applicationSubType,s.approvedBy, s.approvedDate, s.secDeposit, s.totalCost, con.fundSource, s.description, s.entryBy,  s.entryDate, s.rejReasonEE ,ap.fullName,con.ServiceDepoName,con.schemeName,con.schemeNo,con.electorate,con.representative,con.repContact1) FROM Spstdesthmt s, Application a,Applicant ap,WiringLandDetailCon con,ApplicationReference ref WHERE TRIM(s.id.stdNo)=ref.applicationNo AND TRIM(a.id.applicationId)=ref.id.applicationId AND TRIM(a.id.applicationId)=con.id.applicationId AND TRIM(a.idNo)=ap.idNo AND  trim(s.id.deptId)= :deptId AND a.applicationType= :applicationType  AND s.entryBy = :userId AND  s.status in (:status) order by s.id.stdNo";
		StringBuffer buff = new StringBuffer();
		buff.append("SELECT new estimate.model.EstimateDisplay(TRIM(s.id.stdNo), s.id.deptId, a.applicationType, a.applicationSubType,s.approvedBy, s.approvedDate, s.secDeposit, s.totalCost, con.fundSource, s.description, s.entryBy,  s.entryDate, s.rejReasonEE ,ap.fullName,con.ServiceDepoName,con.schemeName,con.schemeNo,con.electorate,con.representative,con.repContact1) FROM Spstdesthmt s, Application a,Applicant ap,WiringLandDetailCon con,ApplicationReference ref WHERE TRIM(s.id.stdNo)=ref.applicationNo AND TRIM(a.id.applicationId)=ref.id.applicationId AND TRIM(a.id.applicationId)=con.id.applicationId AND TRIM(a.idNo)=ap.idNo AND trim(s.id.deptId)= :deptId AND a.applicationType= :applicationType ");;
		if(userId != null){
			buff.append(" AND s.entryBy = :userId ");
		}
		buff.append(" AND  s.status in (:status) order by s.id.stdNo");
		Query query = getEntityManager(webAppName).createQuery(buff.toString());
		query.setParameter("deptId", deptId);
		
		query.setParameter("applicationType", appType);
		if(userId != null){
			query.setParameter("userId", userId);
		}
		query.setParameter("status", status);
		//query.setParameter("applicationType", applicationType);
		List<EstimateDisplay> list = query.getResultList();
		return list;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EstimateDisplay> getEstimateListToBeApproved(String deptId,List<Long> status,String appType,String userId, String webAppName) {
		//getEntityManager(webAppName);
		//String qryStr ="SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Spstdesthmt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId)= :deptId AND  p.status = :status AND a.applicationType= :applicationType and order by p.id.estimateNo ";
		//String qryStr ="SELECT new estimate.model.EstimateDisplay(TRIM(s.id.stdNo), s.id.deptId, a.applicationType, a.applicationSubType,s.entryBy, s.entryDate, s.secDeposit, s.totalCost, con.fundSource, s.description, s.rejectedBy,  s.rejectedDate, s.rejReasonEE ) FROM Spstdesthmt s, Application a,WiringLandDetailCon con,ApplicationReference ref WHERE TRIM(s.id.stdNo)=ref.applicationNo AND TRIM(a.id.applicationId)=ref.id.applicationId AND TRIM(a.id.applicationId)=con.id.applicationId AND trim(s.id.deptId)= :deptId AND  s.status = :status AND a.applicationType= :applicationType and s.entryBy = :userId order by s.id.stdNo";	
		String qryStr = "SELECT new estimate.model.EstimateDisplay(TRIM(s.id.stdNo), s.id.deptId, a.applicationType, a.applicationSubType,s.entryBy, s.entryDate, s.secDeposit, s.totalCost, con.fundSource, s.description, s.rejectedBy,  s.rejectedDate, s.rejReasonEE ,ap.fullName,con.ServiceDepoName,con.schemeName,con.schemeNo,con.electorate,con.representative,con.repContact1) FROM Spstdesthmt s, Application a,Applicant ap,WiringLandDetailCon con,ApplicationReference ref WHERE TRIM(s.id.stdNo)=ref.applicationNo AND TRIM(a.id.applicationId)=ref.id.applicationId AND TRIM(a.id.applicationId)=con.id.applicationId AND TRIM(a.idNo)=ap.idNo AND trim(s.id.deptId)= :deptId AND a.applicationType= :applicationType AND s.entryBy = :userId AND  s.status in (:status) order by s.id.stdNo";
		//if(userId != null){
		//	buff.append(" AND s.entryBy = :userId ");
		//}
		
		System.out.println("gayani");
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		//query.setParameter("status", status);
		query.setParameter("applicationType", appType);
		query.setParameter("userId", userId);
		query.setParameter("status", status);
		//query.setParameter("applicationType", applicationType);
		System.out.println("gayani : "+ qryStr);
		List<EstimateDisplay> list = query.getResultList();
		System.out.println("gayani : "+ list);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EstimateDisplay> getEstimateListToBeValidate(String deptId,List<Long> status,String appType,String username, String webAppName)throws PersistenceException {
		//getEntityManager(webAppName);
		//String qryStr ="SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Spstdesthmt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId)= :deptId AND  p.status = :status AND a.applicationType= :applicationType and order by p.id.estimateNo ";
		//String qryStr ="SELECT new estimate.model.EstimateDisplay(TRIM(s.id.stdNo), s.id.deptId, a.applicationType, a.applicationSubType,s.entryBy, s.entryDate, s.secDeposit, s.totalCost, con.fundSource, s.description, s.rejectedBy,  s.rejectedDate, s.rejReasonEE ) FROM Spstdesthmt s, Application a,WiringLandDetailCon con,ApplicationReference ref WHERE TRIM(s.id.stdNo)=ref.applicationNo AND TRIM(a.id.applicationId)=ref.id.applicationId AND TRIM(a.id.applicationId)=con.id.applicationId AND trim(s.id.deptId)= :deptId AND  s.status = :status AND a.applicationType= :applicationType and s.entryBy = :userId order by s.id.stdNo";	
		//String qryStr = "SELECT new estimate.model.EstimateDisplay(TRIM(s.id.stdNo), s.id.deptId, a.applicationType, a.applicationSubType,s.entryBy, s.entryDate, s.secDeposit, s.totalCost, con.fundSource, s.description, s.rejectedBy,  s.rejectedDate, s.rejReasonEE ,ap.fullName,con.ServiceDepoName,con.schemeName,con.schemeNo,con.electorate,con.representative,con.repContact1) FROM Spstdesthmt s, Application a,Applicant ap,WiringLandDetailCon con,ApplicationReference ref WHERE TRIM(s.id.stdNo)=ref.applicationNo AND TRIM(a.id.applicationId)=ref.id.applicationId AND TRIM(a.id.applicationId)=con.id.applicationId AND TRIM(a.idNo)=ap.idNo AND trim(s.id.deptId)= :deptId AND a.applicationType= :applicationType AND  s.status in (:status) AND s.assignTo=:assignTo order by s.id.stdNo";
		//if(userId != null){
		//	buff.append(" AND s.entryBy = :userId ");
		//}
		StringBuffer buff = new StringBuffer();
		buff.append("SELECT new estimate.model.EstimateDisplay(TRIM(s.id.stdNo), s.id.deptId, a.applicationType, a.applicationSubType,s.entryBy, s.entryDate, s.secDeposit, s.totalCost, con.fundSource, s.description, s.rejectedBy,  s.rejectedDate, s.rejReasonEE ,ap.fullName,con.ServiceDepoName,con.schemeName,con.schemeNo,con.electorate,con.representative,con.repContact1) FROM Spstdesthmt s, Application a,Applicant ap,WiringLandDetailCon con,ApplicationReference ref WHERE TRIM(s.id.stdNo)=ref.applicationNo AND TRIM(a.id.applicationId)=ref.id.applicationId AND TRIM(a.id.applicationId)=con.id.applicationId AND TRIM(a.idNo)=ap.idNo AND trim(s.id.deptId)= :deptId AND a.applicationType= :applicationType AND  s.status in (:status) ");
		if(username != null){
			buff.append(" AND s.assignTo=:assignTo  ");
		}
		buff.append(" order by s.id.stdNo");
		Query query = getEntityManager(webAppName).createQuery(buff.toString());
		query.setParameter("deptId", deptId);
		//query.setParameter("status", status);
		query.setParameter("applicationType", appType);
		
		query.setParameter("status", status);
		if(username != null){
			query.setParameter("assignTo", username);
		}
		
		
		//query.setParameter("applicationType", applicationType);
		List<EstimateDisplay> list = query.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EstimateDisplay> getEstimateListToBeValidateForPL(String deptId,List<Long> status,String username, String webAppName)throws PersistenceException {
		//getEntityManager(webAppName);
		//String qryStr ="SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Spstdesthmt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId)= :deptId AND  p.status = :status AND a.applicationType= :applicationType and order by p.id.estimateNo ";
		//String qryStr ="SELECT new estimate.model.EstimateDisplay(TRIM(s.id.stdNo), s.id.deptId, a.applicationType, a.applicationSubType,s.entryBy, s.entryDate, s.secDeposit, s.totalCost, con.fundSource, s.description, s.rejectedBy,  s.rejectedDate, s.rejReasonEE ) FROM Spstdesthmt s, Application a,WiringLandDetailCon con,ApplicationReference ref WHERE TRIM(s.id.stdNo)=ref.applicationNo AND TRIM(a.id.applicationId)=ref.id.applicationId AND TRIM(a.id.applicationId)=con.id.applicationId AND trim(s.id.deptId)= :deptId AND  s.status = :status AND a.applicationType= :applicationType and s.entryBy = :userId order by s.id.stdNo";	
		//String qryStr = "SELECT new estimate.model.EstimateDisplay(TRIM(s.id.stdNo), s.id.deptId, a.applicationType, a.applicationSubType,s.entryBy, s.entryDate, s.secDeposit, s.totalCost, con.fundSource, s.description, s.rejectedBy,  s.rejectedDate, s.rejReasonEE ,ap.fullName,con.ServiceDepoName,con.schemeName,con.schemeNo,con.electorate,con.representative,con.repContact1) FROM Spstdesthmt s, Application a,Applicant ap,WiringLandDetailCon con,ApplicationReference ref WHERE TRIM(s.id.stdNo)=ref.applicationNo AND TRIM(a.id.applicationId)=ref.id.applicationId AND TRIM(a.id.applicationId)=con.id.applicationId AND TRIM(a.idNo)=ap.idNo AND trim(s.id.deptId)= :deptId AND a.applicationType= :applicationType AND  s.status in (:status) AND s.assignTo=:assignTo order by s.id.stdNo";
		//if(userId != null){
		//	buff.append(" AND s.entryBy = :userId ");
		//}
		StringBuffer buff = new StringBuffer();
		buff.append("SELECT new estimate.model.EstimateDisplay(TRIM(s.id.stdNo), s.id.deptId, a.applicationType, a.applicationSubType,s.entryBy, s.entryDate, s.secDeposit, s.totalCost, con.fundSource, s.description, s.rejectedBy,  s.rejectedDate, s.rejReasonEE ,ap.fullName,con.ServiceDepoName,con.schemeName,con.schemeNo,con.electorate,con.representative,con.repContact1) FROM Spstdesthmt s, Application a,Applicant ap,WiringLandDetailCon con,ApplicationReference ref WHERE TRIM(s.id.stdNo)=ref.applicationNo AND TRIM(a.id.applicationId)=ref.id.applicationId AND TRIM(a.id.applicationId)=con.id.applicationId AND TRIM(a.idNo)=ap.idNo AND trim(s.id.deptId)= :deptId AND  s.status in (:status) ");
		if(username != null){
			buff.append(" AND s.assignTo=:assignTo  ");
		}
		buff.append(" order by s.id.stdNo");
		Query query = getEntityManager(webAppName).createQuery(buff.toString());
		query.setParameter("deptId", deptId);
		//query.setParameter("status", status);
		//query.setParameter("applicationType", appType);
		
		query.setParameter("status", status);
		if(username != null){
			query.setParameter("assignTo", username);
		}
		
		
		//query.setParameter("applicationType", applicationType);
		List<EstimateDisplay> list = query.getResultList();
		return list;
	}




	@Override
	public String getWorkEstimateNo(String estimateNoPrefix,String stdEstimateNo,String deptId, String webAppName)throws PersistenceException {
		
		// to do this part is only for maintaining unique id throught the application
		String nextsquence = null;
		String nextWorkestimateNo = null;
		if(stdEstimateNo != null && stdEstimateNo.length() == Constants.DEFAULT_COMM_REF_LENGTH){
			nextsquence=estimateReferenceDaoRemote.getNextEstimateNoViaCommReference(estimateNoPrefix, webAppName);
			nextWorkestimateNo=estimateNoPrefix+nextsquence;
		}else{
			nextsquence=estimateReferenceDaoRemote.getNextEstimateNo(estimateNoPrefix, webAppName);
			nextWorkestimateNo=estimateNoPrefix+nextsquence;
		}
		
		
		//application.setApplicationNo(nextApplicationNo);
		
		//applicationDaoRemote.updateApplication(application,webAppName);
		
		//this part for update estimate_refernceBs
		EstimateReference estimateReference=new EstimateReference();
		EstimateReferencePK ide=new EstimateReferencePK();
		if(stdEstimateNo != null){
			ide.setStandardEstimateNo(stdEstimateNo);
		}else{
			ide.setStandardEstimateNo(nextWorkestimateNo);
		}
		ide.setDeptId(deptId);
		
		ide.setWorkEstimateNo(nextWorkestimateNo);
		estimateReference.setId(ide);
		//appReference.setIdNo(application.getIdNo());//Identity Card No
	
		estimateReferenceDaoRemote.createEstimateReference(estimateReference,webAppName);
		
		return nextWorkestimateNo;
	}
	/*public void updateDetailEstimateCost(String estimateNo,String deptId,String webAppName) throws PersistenceException{
		Pcesthtt pcesthtt=pcesthttDaoRemote.findByEstimationNo(estimateNo, deptId,2L, webAppName);
		if (pcesthtt != null){
			try {
				updateAdditionalCost(pcesthtt, deptId, pcesthtt.getStdCost(), webAppName);
			} catch (Exception e) {
				LOGGER.error("Error in Additional Cost calculation Estimate No:"+estimateNo);
			}
			
			String qryStr2 = "select sum(a.estimateCost) from Pcestdtt a where TRIM(a.id.estimateNo)=:estimateNo AND TRIM(a.id.deptId)=:deptId AND TRIM(a.id.revNo)=:revNo ";
			Query query2 = getEntityManager(webAppName).createQuery(qryStr2);
			query2.setParameter("estimateNo", estimateNo.trim());
			query2.setParameter("deptId", deptId);
			query2.setParameter("revNo", "2");
			BigDecimal stdCost = (BigDecimal) query2.getSingleResult();
			System.out.println(stdCost);
			boolean status = false;
			oad
			
			pcesthtt.setStdCost(stdCost);
			//System.out.println(pcesthmt);
			System.out.println();
			pcesthttDaoRemote.updatePcesthtt(pcesthtt, webAppName);
		}
		
	}*/
	public void updateDetail1EstimateCost(String estimateNo,String resType,String deptId,String webAppName) throws PersistenceException{
		Pcesthtt pcesthtt=pcesthttDaoRemote.findByEstimationNo(estimateNo, deptId,2L, webAppName);
		if (pcesthtt != null){
			/*try {
				//updateAdditionalCost(pcesthtt, deptId, pcesthtt.getStdCost(), webAppName);
			} catch (Exception e) {
				LOGGER.error("Error in Additional Cost calculation Estimate No:"+estimateNo);
			}*/
			StringBuffer qryStr2 = new StringBuffer();
			qryStr2.append("select sum(a.estimateCost) from Pcestdtt a where TRIM(a.id.estimateNo)=:estimateNo AND TRIM(a.id.deptId)=:deptId AND TRIM(a.id.revNo)=:revNo ");
			
			if(resType != null){
				qryStr2.append("AND trim(resType) <> 'OTHER-MATERIAL'");
			}
			
			Query query2 = getEntityManager(webAppName).createQuery(qryStr2.toString());
			query2.setParameter("estimateNo", estimateNo.trim());
			query2.setParameter("deptId", deptId);
			query2.setParameter("revNo", "2");
			/*if(resType != null){
				
				query2.setParameter("resType", resType);
			}*/
			BigDecimal stdCost = (BigDecimal) query2.getSingleResult();
			System.out.println(stdCost);
			boolean status = false;
			
			
			pcesthtt.setStdCost(stdCost);
			//System.out.println(pcesthmt);
			System.out.println();
			pcesthttDaoRemote.updatePcesthtt(pcesthtt, webAppName);
		}
		
	}
	public void updateStandardEstimateCost(String estimateNo,String deptId,String webAppName) throws PersistenceException{
		BigDecimal stdCostConpayable = new BigDecimal("0");
		BigDecimal stdCost = new BigDecimal("0");
		BigDecimal cebCost = new BigDecimal("0");
		BigDecimal rebateCost = new BigDecimal("0");
		BigDecimal vatCost = new BigDecimal("0");
		BigDecimal nbtCost = new BigDecimal("0");
		
		System.out.println("hi : " +vatCost);
		System.out.println("hi : " +nbtCost);
		String qryStrVat = "select sum(a.vatcost) from Spstdesthmt a where TRIM(a.id.stdNo)=:estimateNo AND TRIM(a.id.deptId)=:deptId ";
		Query queryVat = getEntityManager(webAppName).createQuery(qryStrVat);
		queryVat.setParameter("estimateNo", estimateNo);
		queryVat.setParameter("deptId", deptId);
		
		vatCost = (BigDecimal) queryVat.getSingleResult();
		
		String qryStrNbt = "select sum(a.nbtcost) from Spstdesthmt a where TRIM(a.id.stdNo)=:estimateNo AND TRIM(a.id.deptId)=:deptId ";
		Query queryNbt= getEntityManager(webAppName).createQuery(qryStrNbt);
		queryNbt.setParameter("estimateNo", estimateNo);
		queryNbt.setParameter("deptId", deptId);
		
		nbtCost = (BigDecimal) queryNbt.getSingleResult();
	
		System.out.println("hi 2: " +vatCost);
		System.out.println("hi 2: " +nbtCost);
		String qryStr2 = "select sum(a.estmateCost) from Spstdestdmt a where TRIM(a.id.stdNo)=:estimateNo AND TRIM(a.deptId)=:deptId ";
		Query query2 = getEntityManager(webAppName).createQuery(qryStr2);
		query2.setParameter("estimateNo", estimateNo);
		query2.setParameter("deptId", deptId);
		
		stdCost = (BigDecimal) query2.getSingleResult();
		
		String qryStr3 = "select sum(a.estmateCost) from Spstdestdmt a where TRIM(a.id.stdNo)=:estimateNo AND TRIM(a.deptId)=:deptId and a.id.lineType like '%CEB%'";
		Query query3 = getEntityManager(webAppName).createQuery(qryStr3);
		query3.setParameter("estimateNo", estimateNo);
		query3.setParameter("deptId", deptId);
		
		cebCost = (BigDecimal) query3.getSingleResult();
		
		String qryStr4 = "select sum(a.estmateCost) from Spstdestdmt a where TRIM(a.id.stdNo)=:estimateNo AND TRIM(a.deptId)=:deptId and a.id.lineType like '%RB%'";
		Query query4 = getEntityManager(webAppName).createQuery(qryStr4);
		query4.setParameter("estimateNo", estimateNo);
		query4.setParameter("deptId", deptId);
		
		rebateCost = (BigDecimal) query4.getSingleResult();
		if(cebCost == null){
			cebCost = new BigDecimal("0");
		}
		if(stdCost == null){
			stdCost = new BigDecimal("0");
		}
		if(rebateCost == null){
			rebateCost = new BigDecimal("0");
		}
		if(vatCost == null){
			vatCost = new BigDecimal("0");
		}
		if(nbtCost == null){
			nbtCost = new BigDecimal("0");
		}
		
		double toconpay = (stdCost.doubleValue()+nbtCost.doubleValue()+vatCost.doubleValue());
		
		//stdCostConpayable = new BigDecimal(stdCost.doubleValue()- (cebCost.doubleValue() + rebateCost.doubleValue()));
		//stdCost = new BigDecimal(stdCost.doubleValue()-rebateCost.doubleValue());
		stdCostConpayable = new BigDecimal(toconpay - (cebCost.doubleValue() + rebateCost.doubleValue()));
		stdCost = new BigDecimal(toconpay - rebateCost.doubleValue());
		
		
		System.out.println("hi : " +stdCost);
		boolean status = false;
		SpstdesthmtPK spstdesthmtPK = new SpstdesthmtPK();
		spstdesthmtPK.setStdNo(estimateNo);
		spstdesthmtPK.setApplicationNo(estimateNo);
		spstdesthmtPK.setDeptId(deptId);
		Spstdesthmt spstdesthmt=spstdesthmtDaoRemote.findById(spstdesthmtPK, webAppName);
		if (spstdesthmt!=null){
			spstdesthmt.setTotalCost(stdCost);
			spstdesthmt.setToConpay(stdCostConpayable);
			spstdesthmt.setRebateCost(rebateCost);
			spstdesthmt.setCebCost(cebCost);
			//spstdesthmt.setVatcost(vatCost);
			
			//System.out.println(pcesthmt);
			System.out.println();
			spstdesthmtDaoRemote.updateSpstdesthmt(spstdesthmt, webAppName);
		}
		
	}
	
	
	public String getNextPegItemId(String parentId,String masterId,
			String webAppName){
		String idsParent =null;
		  String idsParentConsta = null;
		  String idsParentNum =null;
		  
		  String idsChild =null;
		  
		  String idsChildConsta = null; 
		  String idsChildNum =null;
		  
		  String nextId = null;
	  List <SpPegInfo> spList = spPegInfoDaoRemote.getPegChildrensByParentId(parentId,masterId ,webAppName);
	  if(spList != null && spList.size() == 0 ){
	
  			nextId = parentId.concat("1");
	  }else{
		  ArrayList<Integer> arrayList = new ArrayList<Integer>();
			for(SpPegInfo sp : spList){
				idsChild = sp.getId().getPegItemId().substring(0, parentId.length());
				idsChildNum = sp.getId().getPegItemId().substring(parentId.length(), sp.getId().getPegItemId().length());
					
					
					arrayList.add(Integer.parseInt(idsChildNum));
			}
			Integer i = Collections.max(arrayList);
			nextId = idsChild.concat(String.valueOf(i.intValue()+1));
	  }
	  
	  return nextId;
  }
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public String recommendStandardEstimate(String estimateNo, String deptId,	String authorityLevel, String userName,String com, String assignedUserName, String applicationType,String sinNo,String webAppName) {
		try{
			
			Appestlim appestlim=pcesthttDaoRemote.findAppEstLimits(deptId, "SES",applicationType, authorityLevel, webAppName);
			System.out.println("applicationType "+applicationType);
			if (appestlim!=null){
				Double limitFrom=Double.parseDouble(String.valueOf(appestlim.getId().getLimitFrom()));
				Double limitTo=Double.parseDouble(String.valueOf(appestlim.getId().getLimitTo()));
				String statusFrom=String.valueOf(appestlim.getId().getStatusFrom());
				String statusTo=String.valueOf(appestlim.getId().getStatusTo());
				System.out.println("appestlim "+appestlim +"sin : "+ sinNo);
				
				Double dbTotalCost=0.0;
				SpstdesthmtPK pk = new SpstdesthmtPK();
				pk.setApplicationNo(estimateNo.trim());
				pk.setStdNo(estimateNo.trim());
				pk.setDeptId(deptId);
				Spstdesthmt hmt = spstdesthmtDaoRemote.findById(pk, webAppName);
				System.out.println("hii");
				Calendar calendar = Calendar.getInstance();
				Format format=new Format();
				Approval approval=new Approval();
				approval.setApprovalId(null);
				approval.setReferenceNo(estimateNo.trim());
				approval.setDeptId(deptId);
				System.out.println("hiiiii");
				approval.setApprovedLevel(authorityLevel);
				approval.setApprovedBy(userName);
				approval.setApprovedDate(calendar.getTime());
				approval.setApprovedTime(format.FormatTime());
				System.out.println("hiii4");
				Double totalCost = null;
				if (hmt == null){
					System.out.println("hiii5");
				}else if (hmt != null){
					System.out.println("hiii6");
		        	if(hmt.getTotalCost() != null ){
		        		//dbTotalCost=Double.parseDouble(totalCost.toString());
		        		approval.setStandardCost(hmt.getTotalCost());
		        		dbTotalCost = hmt.getTotalCost().doubleValue();
		        	}else{
						approval.setStandardCost(new BigDecimal("0"));
						dbTotalCost = 0.0;
					}
		        	String status = hmt.getStatus().toString();
		        	System.out.println("hiii4"+ status);
					    if (status.equals(EstimateStatus.EST_APPROVAL_ES) && authorityLevel.equals("ES") ){
						String postinguserLevel = null;
						if(assignedUserName != null){
							postinguserLevel = securityBeanRemote.getAuthorizedLevel(assignedUserName,webAppName);
						}
						if(postinguserLevel.equalsIgnoreCase("EE")){
							System.out.println("hiiiee");
							//changeStatusPcesthtt(estimateNo, deptId, new Long(statusTo), webAppName);
		        			hmt.setStatus(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey());
		        			hmt.setAssignTo(assignedUserName);
		        			//hmt.setCom_ee(com);
		        			spstdesthmtDaoRemote.updateSpstdesthmt(hmt, webAppName);
		        			approval.setFromStatus(status);
		            		approval.setToStatus(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey().toString());
		            		approval.setApprovalType("SES_VLDT");
		            		approvalDaoRemote.createAutoIdConstructionEstimateApprovals(approval, webAppName);
		            		return "#Sent to "+forewordTo(statusTo.toString())+  "for Approval";
						}else if(postinguserLevel.equalsIgnoreCase("PE")){
							//changeStatusPcesthtt(estimateNo, deptId, new Long(statusTo), webAppName);
		        			hmt.setStatus(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey());
		        			hmt.setAssignTo(assignedUserName);
		        			//hmt.setCom_pe(com);
		        			spstdesthmtDaoRemote.updateSpstdesthmt(hmt, webAppName);
		        			approval.setFromStatus(status);
		            		approval.setToStatus(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey().toString());
		            		approval.setApprovalType("SES_VLDT");
		            		approvalDaoRemote.createAutoIdConstructionEstimateApprovals(approval, webAppName);
		            		return "#Sent to "+forewordTo(statusTo.toString())+  "for Approval";
						}else if(postinguserLevel.equalsIgnoreCase("ES")){
							hmt.setStatus(StandardEstimateStatus.TO_BE_CHECKED_BY_ELECTRICAL_SUPIRINTENDENT.getKey());
		        			hmt.setAssignTo(assignedUserName);
		        			spstdesthmtDaoRemote.updateSpstdesthmt(hmt, webAppName);
		        			approval.setFromStatus(status);
		            		approval.setToStatus(StandardEstimateStatus.TO_BE_CHECKED_BY_ELECTRICAL_SUPIRINTENDENT.getKey().toString());
		            		approval.setApprovalType("SES_VLDT");
		            		approvalDaoRemote.createAutoIdConstructionEstimateApprovals(approval, webAppName);
		            		return "#Sent to "+forewordTo(statusTo.toString())+  "for Approval";
						}
						else {
		        			return "@Can not Approve this";
						}
		        	
		    	    	
		    	    }else if (status.equals(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey().toString()) && authorityLevel.equals("EE") ){
	        			
		    	    	
		    	    		hmt.setStatus(Long.parseLong(statusTo));
		    	    		hmt.setAssignTo(assignedUserName);
		    	    		hmt.setCom_ee(com);
		    	    		hmt.setSinNo(sinNo);
		        			spstdesthmtDaoRemote.updateSpstdesthmt(hmt, webAppName);
		        			
		        			
							approval.setFromStatus(statusFrom);
							approval.setToStatus(statusTo);
							approval.setApprovalType("SES_VLDT");
							//setErrorMessage("DONE:- Successfully Posted for CE");
							approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
							
							System.out.println(Long.parseLong(statusTo));
							if(Long.parseLong(statusTo) == 20){
								return "#Sent to "+forewordTo(statusTo)+  "for Validation";
							}
							return "#Sent to "+forewordTo(statusTo)+  "for Approval";
							
		    	    	
		    	    	
		    	    }else if (status.equals(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey().toString()) && authorityLevel.equals("PE") ){
		    	    	
			    	    		hmt.setStatus(Long.parseLong(statusTo));
			        			hmt.setAssignTo(assignedUserName);
			        			hmt.setCom_pe(com);
			        			hmt.setSinNo(sinNo);
			        			spstdesthmtDaoRemote.updateSpstdesthmt(hmt, webAppName);
			        			
			        			
								approval.setFromStatus(statusFrom);
								approval.setToStatus(statusTo);
								approval.setApprovalType("SES_VLDT");
								//setErrorMessage("DONE:- Successfully Posted for CE");
								approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
								return "#Sent to "+forewordTo(statusTo)+  "for Approval";
							
		    	    	
		    	    }else if (status.equals(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_CE.getKey().toString()) && authorityLevel.equals("PCE") ){
	        			
		    	    	
		    	    		hmt.setStatus(Long.parseLong(statusTo));
		        			hmt.setAssignTo(assignedUserName);
		        			hmt.setCom_pce(com);
		        			hmt.setSinNo(sinNo);
		        			spstdesthmtDaoRemote.updateSpstdesthmt(hmt, webAppName);
		        			
		        			
							approval.setFromStatus(statusFrom);
							approval.setToStatus(statusTo);
							approval.setApprovalType("SES_VLDT");
							//setErrorMessage("DONE:- Successfully Posted for CE");
							approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
							return "#Sent to "+forewordTo(statusTo)+  "for Approval";
						
		    	    	
		    	    }else if (status.equals(StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey().toString()) && authorityLevel.equals("CE") ){
		    	    	
		    	    	
		    	    		hmt.setStatus(Long.parseLong(statusTo));
		        			hmt.setAssignTo(assignedUserName);
		        			hmt.setCom_ce(com);
		        			hmt.setSinNo(sinNo);
		        			spstdesthmtDaoRemote.updateSpstdesthmt(hmt, webAppName);
		    	    		//spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey(),null,null,null,null,null,null,  null,null, userName,new Date(),null,null,null,webAppName);
							
		        			approval.setFromStatus(statusFrom);
							approval.setToStatus(statusTo);
							
							approval.setApprovalType("SES_VLDT");
							approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
							return "#Sent to "+forewordTo(statusTo)+  "for Approval";
						
		    	    }else if (status.equals(StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey().toString()) && authorityLevel.equals("DGM")){
		    	    	
		    	    	
							hmt.setStatus(Long.parseLong(statusTo));
		        			hmt.setAssignTo(assignedUserName);
		        			hmt.setCom_dgm(com);
		        			hmt.setSinNo(sinNo);
		        			spstdesthmtDaoRemote.updateSpstdesthmt(hmt, webAppName);
		        			
		        			approval.setFromStatus(statusFrom);
							approval.setToStatus(statusTo);
							approval.setApprovalType("SES_APRV");
							approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
							return "#Approved";
						
		    	    	
		    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_AGM) && authorityLevel.equals("AGM")){
		    	    	
		    	    
		    	    		//************* comment for SAN_TEST ****************
		    	    		hmt.setStatus(Long.parseLong(statusTo));
		        			hmt.setAssignTo(assignedUserName);
		        			spstdesthmtDaoRemote.updateSpstdesthmt(hmt, webAppName);
		        			
		    	    		approval.setFromStatus(statusFrom);
		            		approval.setToStatus(statusTo);
		            		approval.setApprovalType("DES_APRV");
		            		approvalDaoRemote.createAutoIdConstructionEstimateApprovals(approval, webAppName);
		            		return "#Approved";
		    	    	
		    	    }else {
		    	    	return "@Can not Approve this";
		    	    }
				} 	
			    	    	
						/*if( (Constants.EE_APPROVE_LIMIT_MIN <= totalCost) && (totalCost < Constants.EE_APPROVE_LIMIT_MAX )){
							//status= estimateEjb.updateEstimateStatus(estimateNo, costCenterNo, StandardEstimateStatus.APPROVED_ESTIMATES.getKey(),userId,new Date(),null,null,null,null,null,null,null,null,null,null,null, region);
							spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.APPROVED_ESTIMATES.getKey(),userName,new Date(),null,null,null,null,null,null,null,null,null,null,null,webAppName);
							
							approval.setFromStatus(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey().toString());
							approval.setToStatus(StandardEstimateStatus.APPROVED_ESTIMATES.getKey().toString());
							
							approval.setApprovalType("SES_APRV");
							approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
							return "#Approved";
							
						}else{
							//status= estimateEjb.updateEstimateStatus(estimateNo, costCenterNo, StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey(),null,null,null,null,null,null,userId,new Date(),null,null,null,null,null, region);
							spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey(),null,null,null,null,null,null,  userName,new Date(),null,null,null,null,null,webAppName);
							
							approval.setFromStatus(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey().toString());
							approval.setToStatus(StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey().toString());
							approval.setApprovalType("SES_VLDT");
							//setErrorMessage("DONE:- Successfully Posted for CE");
							approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
							return "#Sent to "+forewordTo(StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey().toString())+  "for Approval";
							
						}
					//}
					
				}else if(authorityLevel.equalsIgnoreCase("CE")){
					
					if( (Constants.CE_APPROVE_LIMIT_MIN <= totalCost) && (totalCost < Constants.CE_APPROVE_LIMIT_MAX )){
						//status= estimateEjb.updateEstimateStatus(estimateNo, costCenterNo, StandardEstimateStatus.APPROVED_ESTIMATES.getKey(),userId,new Date(),null,null,null,null,null,null,null,null,null,null,null, region);
						spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.APPROVED_ESTIMATES.getKey(),userName,new Date(),null,null,null,null,  null,null,null,null,null,null,null,webAppName);
						
						approval.setFromStatus(StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey().toString());
						approval.setToStatus(StandardEstimateStatus.APPROVED_ESTIMATES.getKey().toString());
						approval.setApprovalType("SES_APRV");
						approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
						return "#Approved";
						
					}else{
						//status= estimateEjb.updateEstimateStatus(estimateNo, costCenterNo, StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey(),null,null,null,null,null,null,null,null,userId,new Date(),null,null,null, region); 
						spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey(),null,null,null,null,null,null,  null,null, userName,new Date(),null,null,null,webAppName);
						
						approval.setFromStatus(StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey().toString());
						approval.setToStatus(StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey().toString());
						
						approval.setApprovalType("SES_VLDT");
						approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
						return "#Sent to "+forewordTo(StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey().toString())+  "for Approval";
						
					}
					
				}else if(authorityLevel.equalsIgnoreCase("PE")){
					
					if(applicationType.equalsIgnoreCase(Constants.PLANNING)){
						//status= estimateEjb.updateEstimateStatus(estimateNo, costCenterNo, StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey(),null,null,null,null,userId,new Date(),null,null,null,null,null,null,null, region);
						spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey(),null,null,null,null, userName,new Date(),  null,null, null,null,null,null,null,webAppName);
						
						approval.setFromStatus(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey().toString());
						approval.setToStatus(StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey().toString());
						
						approval.setApprovalType("SES_VLDT");
						approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
						return "#Sent to "+forewordTo(StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey().toString())+  "for Approval";
						
	
					}else{
					
						//status= estimateEjb.updateEstimateStatus(estimateNo, costCenterNo, StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey(),null,null,null,null,userId,new Date(),null,null,null,null,null,null,null, region);
						spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey(),null,null,null,null, userName,new Date(),  null,null, null,null,null,null,null,webAppName);
						
						approval.setFromStatus(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey().toString());
						approval.setToStatus(StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey().toString());
						approval.setApprovalType("SES_VLDT");
						approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
						return "#Sent to "+forewordTo(StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey().toString())+  "for Approval";
						
					}
					
				}else if(authorityLevel.equalsIgnoreCase("DGM")){
					
					if(applicationType.equalsIgnoreCase(Constants.PLANNING) || applicationType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I)){
						//status= estimateEjb.updateEstimateStatus(estimateNo, costCenterNo, StandardEstimateStatus.PIVII_CONFIRMATION.getKey(),null,null,null,null,userId,new Date(),null,null,null,null,null,null,null, region);
						//by passing PIV II paying for RE Projects
						spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.PIVII_CONFIRMATION.getKey(),null,null,null,null, userName,new Date(),  null,null, null,null,null,null,null,webAppName);
						
						approval.setFromStatus(StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey().toString());
						approval.setToStatus(StandardEstimateStatus.PIVII_CONFIRMATION.getKey().toString());
						approval.setApprovalType("SES_APRV");
						approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
						return "#Approved";
						
					}else{
					
						//status= estimateEjb.updateEstimateStatus(estimateNo, costCenterNo, StandardEstimateStatus.APPROVED_ESTIMATES.getKey(),userId,new Date(),null,null,null,null,null,null,null,null,null,null,null, region);
						spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.APPROVED_ESTIMATES.getKey(),userName,new Date(),null,null, null,null,  null,null, null,null,null,null,null,webAppName);
						
						approval.setFromStatus(StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey().toString());
						approval.setToStatus(StandardEstimateStatus.APPROVED_ESTIMATES.getKey().toString());
						approval.setApprovalType("SES_APRV");
						approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
						return "#Approved";
						
					}*/
				//}
			//	appejb.createAutoIdApprovals(approval);
		        	
			
				
			}else{
				return "@Please contact the MIS Staff.";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "@Please contact the MIS Staff.";
		}
		return "@Can not Approve this";
		
	}
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public String approveStandardEstimate(String estimateNo, String deptId,	String authorityLevel, String userName, String assignedUserName, String applicationType,String webAppName) {
		try{
			
			Appestlim appestlim=pcesthttDaoRemote.findAppEstLimits(deptId, "SES",applicationType, authorityLevel, webAppName);
			System.out.println("appestlim rr"+appestlim);
			if (appestlim!=null){
				System.out.println("appestlim  approve: "+appestlim);
				Double limitFrom=Double.parseDouble(String.valueOf(appestlim.getId().getLimitFrom()));
				Double limitTo=Double.parseDouble(String.valueOf(appestlim.getId().getLimitTo()));
				String statusFrom=String.valueOf(appestlim.getId().getStatusFrom());
				String statusTo=String.valueOf(appestlim.getId().getStatusTo());
				System.out.println("appestlim "+appestlim);
				
				Double dbTotalCost=0.0;
				SpstdesthmtPK pk = new SpstdesthmtPK();
				pk.setApplicationNo(estimateNo.trim());
				pk.setStdNo(estimateNo.trim());
				pk.setDeptId(deptId);
				Spstdesthmt hmt = spstdesthmtDaoRemote.findById(pk, webAppName);
				
				Calendar calendar = Calendar.getInstance();
				Format format=new Format();
				Approval approval=new Approval();
				approval.setApprovalId(null);
				approval.setReferenceNo(estimateNo.trim());
				approval.setDeptId(deptId);
				
				approval.setApprovedLevel(authorityLevel);
				approval.setApprovedBy(userName);
				approval.setApprovedDate(calendar.getTime());
				approval.setApprovedTime(format.FormatTime());
			
				Double totalCost = null;
				if (hmt == null){
					
				}else if (hmt != null){
					
		        	if(hmt.getTotalCost() != null ){
		        		//dbTotalCost=Double.parseDouble(totalCost.toString());
		        		approval.setStandardCost(hmt.getTotalCost());
		        		dbTotalCost = hmt.getTotalCost().doubleValue();
		        	}else{
						approval.setStandardCost(new BigDecimal("0"));
						dbTotalCost = 0.0;
					}
		        	String status = hmt.getStatus().toString();
			
					if (status.equals(EstimateStatus.EST_APPROVAL_ES) && authorityLevel.equals("ES") ){
		    			
		    	   
						String postinguserLevel = null;
						if(assignedUserName != null){
							postinguserLevel = securityBeanRemote.getAuthorizedLevel(assignedUserName,webAppName);
						}
						
						if(postinguserLevel != null && postinguserLevel.equalsIgnoreCase("EE")){
							//changeStatusPcesthtt(estimateNo, deptId, new Long(statusTo), webAppName);
		        			hmt.setStatus(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey());
		        			hmt.setAssignTo(assignedUserName);
		        			spstdesthmtDaoRemote.updateSpstdesthmt(hmt, webAppName);
		        			approval.setFromStatus(status);
		            		approval.setToStatus(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey().toString());
		            		approval.setApprovalType("SES_VLDT");
		            		approvalDaoRemote.createAutoIdConstructionEstimateApprovals(approval, webAppName);
		            		return "#Sent to "+forewordTo(statusTo.toString())+  "for Approval";
						}else if(postinguserLevel != null && postinguserLevel.equalsIgnoreCase("PE")){
							//changeStatusPcesthtt(estimateNo, deptId, new Long(statusTo), webAppName);
		        			hmt.setStatus(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey());
		        			hmt.setAssignTo(assignedUserName);
		        			spstdesthmtDaoRemote.updateSpstdesthmt(hmt, webAppName);
		        			approval.setFromStatus(status);
		            		approval.setToStatus(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey().toString());
		            		approval.setApprovalType("SES_VLDT");
		            		approvalDaoRemote.createAutoIdConstructionEstimateApprovals(approval, webAppName);
		            		return "#Sent to "+forewordTo(statusTo.toString())+  "for Approval";
						}else if(postinguserLevel != null && postinguserLevel.equalsIgnoreCase("ES")){
							hmt.setStatus(StandardEstimateStatus.TO_BE_CHECKED_BY_ELECTRICAL_SUPIRINTENDENT.getKey());
		        			hmt.setAssignTo(assignedUserName);
		        			spstdesthmtDaoRemote.updateSpstdesthmt(hmt, webAppName);
		        			approval.setFromStatus(status);
		            		approval.setToStatus(StandardEstimateStatus.TO_BE_CHECKED_BY_ELECTRICAL_SUPIRINTENDENT.getKey().toString());
		            		approval.setApprovalType("SES_VLDT");
		            		approvalDaoRemote.createAutoIdConstructionEstimateApprovals(approval, webAppName);
		            		return "#Sent to "+forewordTo(statusTo.toString())+  "for Approval";
						}
						else {
		        			return "@Can not Approve this";
						}
		        	
		    	    	
		    	    }else if (status.equals(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey().toString()) && authorityLevel.equals("EE") ){
	        			
		    	    	if ( dbTotalCost <= limitTo){
		    	    		
		    	    		//spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.APPROVED_ESTIMATES.getKey(),userName,new Date(),null,null,null,null,null,null,null,null,null,null,null,webAppName);
		    	    		
		    	    		if(applicationType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I) ){
		    	    			hmt.setStatus(StandardEstimateStatus.PIVII_CONFIRMATION.getKey());
							}else{
								hmt.setStatus(StandardEstimateStatus.APPROVED_ESTIMATES.getKey());
							}
		    	    		
		    	    		hmt.setAssignTo(assignedUserName);
		    	    		hmt.setApprovedBy(userName);
		    	    		hmt.setApprovedDate(new Date());
		        			spstdesthmtDaoRemote.updateSpstdesthmt(hmt, webAppName);
		        			
							approval.setFromStatus(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey().toString());
							if(applicationType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I) ){
			    	    			approval.setToStatus(StandardEstimateStatus.PIVII_CONFIRMATION.getKey().toString());
							}else{
								approval.setToStatus(StandardEstimateStatus.APPROVED_ESTIMATES.getKey().toString());
							}
							
							//spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.APPROVED_ESTIMATES.getKey(),userName,new Date(),null,null,null,null,null,null,null,null,null,null,null,webAppName);
		    	    		
							approval.setApprovalType("SES_APRV");
							approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
							return "#Approved";
							
							
		    	    		
		    	    	}else if (dbTotalCost > limitTo) {
		    	    		
		    	    		//spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey(),null,null,null,null,null,null,  userName,new Date(),null,null,null,null,null,webAppName);
		    	    		hmt.setStatus(Long.parseLong(statusTo));
		        			hmt.setAssignTo(assignedUserName);
		        			spstdesthmtDaoRemote.updateSpstdesthmt(hmt, webAppName);
		        			
		        			
							approval.setFromStatus(statusFrom);
							approval.setToStatus(statusTo);
							approval.setApprovalType("SES_VLDT");
							//setErrorMessage("DONE:- Successfully Posted for CE");
							approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
							
							if(Long.parseLong(statusTo) == 20){
								return "#Sent to "+forewordTo(statusTo)+  "for Validation";
							}
							return "#Sent to "+forewordTo(statusTo)+  "for Approval";
							
							
		        			
			        	}else {
			        			return "@Can not Approve this";
			        	}
		    	    	
		    	    }else if (status.equals(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey().toString()) && authorityLevel.equals("PE") ){
		    	    	if(applicationType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION) || applicationType.equalsIgnoreCase(Constants.BS) || applicationType.equalsIgnoreCase(Constants.LAND) || applicationType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I)){
	    	    			hmt.setStatus(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey());
	    	    			return "#Recommended";
						}
		    	    	
		    	    	if ( dbTotalCost <= limitTo){
		    	    		
		    	    		//spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.APPROVED_ESTIMATES.getKey(),userName,new Date(),null,null,null,null,null,null,null,null,null,null,null,webAppName);
		    	    		
		    	    		if(applicationType.equalsIgnoreCase(Constants.PLANNING)){
		    	    			hmt.setStatus(StandardEstimateStatus.PIVII_CONFIRMATION.getKey());
							}else{
								hmt.setStatus(StandardEstimateStatus.APPROVED_ESTIMATES.getKey());
							}
		    	    		hmt.setApprovedBy(userName);
		    	    		hmt.setApprovedDate(new Date());
		        			hmt.setAssignTo(assignedUserName);
		        			spstdesthmtDaoRemote.updateSpstdesthmt(hmt, webAppName);
		        			
							approval.setFromStatus(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey().toString());
							approval.setToStatus(StandardEstimateStatus.PIVII_CONFIRMATION.getKey().toString());
							
							//spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.APPROVED_ESTIMATES.getKey(),userName,new Date(),null,null,null,null,null,null,null,null,null,null,null,webAppName);
		    	    		
							
							approval.setApprovalType("SES_APRV");
							approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
							return "#Approved";
							
							
		    	    		
		    	    	}else if (dbTotalCost > limitTo) {
		    	    		
		    	    		//spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey(),null,null,null,null,null,null,  userName,new Date(),null,null,null,null,null,webAppName);
		    	    		hmt.setStatus(Long.parseLong(statusTo));
		        			hmt.setAssignTo(assignedUserName);
		        			spstdesthmtDaoRemote.updateSpstdesthmt(hmt, webAppName);
		        			
		        			
							approval.setFromStatus(statusFrom);
							approval.setToStatus(statusTo);
							approval.setApprovalType("SES_VLDT");
							//setErrorMessage("DONE:- Successfully Posted for CE");
							approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
							return "#Sent to "+forewordTo(statusTo)+  "for Approval";
							
							
		        			
			        	}else {
			        			return "@Can not Approve this";
			        	}
		    	    	
		    	    }else if (status.equals(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_CE.getKey().toString()) && authorityLevel.equals("PCE") ){
	        			
		    	    	if (dbTotalCost <= limitTo){
		    	    		
		    	    		//spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.APPROVED_ESTIMATES.getKey(),userName,new Date(),null,null,null,null,null,null,null,null,null,null,null,webAppName);
		    	    		if(applicationType.equalsIgnoreCase(Constants.PLANNING)){
		    	    			hmt.setStatus(StandardEstimateStatus.PIVII_CONFIRMATION.getKey());
							}else{
								hmt.setStatus(StandardEstimateStatus.APPROVED_ESTIMATES.getKey());
							}
		        			hmt.setAssignTo(assignedUserName);
		        			hmt.setApprovedBy(userName);
		    	    		hmt.setApprovedDate(new Date());
		        			spstdesthmtDaoRemote.updateSpstdesthmt(hmt, webAppName);
		        			
							approval.setFromStatus(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_CE.getKey().toString());
							approval.setToStatus(StandardEstimateStatus.PIVII_CONFIRMATION.getKey().toString());
							
							//spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.APPROVED_ESTIMATES.getKey(),userName,new Date(),null,null,null,null,null,null,null,null,null,null,null,webAppName);
		    	    		
							approval.setApprovalType("SES_APRV");
							approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
							return "#Approved";
							
							
		    	    		
		    	    	}else if (dbTotalCost > limitTo) {
		    	    		
		    	    		//spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey(),null,null,null,null,null,null,  userName,new Date(),null,null,null,null,null,webAppName);
		    	    		hmt.setStatus(Long.parseLong(statusTo));
		        			hmt.setAssignTo(assignedUserName);
		        			spstdesthmtDaoRemote.updateSpstdesthmt(hmt, webAppName);
		        			
		        			
							approval.setFromStatus(statusFrom);
							approval.setToStatus(statusTo);
							approval.setApprovalType("SES_VLDT");
							//setErrorMessage("DONE:- Successfully Posted for CE");
							approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
							return "#Sent to "+forewordTo(statusTo)+  "for Approval";
							
							
		        			
			        	}else {
			        			return "@Can not Approve this";
			        	}
		    	    	
		    	    }else if (status.equals(StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey().toString()) && authorityLevel.equals("CE") ){
		    	    	
		    	    	if (dbTotalCost <= limitTo){
		    	    		
		    	    		if(applicationType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I)){
		    	    			hmt.setStatus(StandardEstimateStatus.PIVII_CONFIRMATION.getKey());
							}else{
								hmt.setStatus(StandardEstimateStatus.APPROVED_ESTIMATES.getKey());
							}
		    	    		hmt.setApprovedBy(userName);
		    	    		hmt.setApprovedDate(new Date());
		        			hmt.setAssignTo(assignedUserName);
		        			spstdesthmtDaoRemote.updateSpstdesthmt(hmt, webAppName);
		        			
		    	    		//spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.APPROVED_ESTIMATES.getKey(),userName,new Date(),null,null,null,null,  null,null,null,null,null,null,null,webAppName);
							
							approval.setFromStatus(StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey().toString());
							if(applicationType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I)){
								approval.setToStatus(StandardEstimateStatus.PIVII_CONFIRMATION.getKey().toString());
							}else{
								approval.setToStatus(StandardEstimateStatus.APPROVED_ESTIMATES.getKey().toString());
							}
							
							//spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.APPROVED_ESTIMATES.getKey(),userName,new Date(),null,null,null,null,null,null,null,null,null,null,null,webAppName);
		    	    		
							
							
							approval.setApprovalType("SES_APRV");
							approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
							return "#Approved";
							
							
		    	    		
		    	    	}else if (dbTotalCost > limitTo) {
		    	    		hmt.setStatus(Long.parseLong(statusTo));
		        			hmt.setAssignTo(assignedUserName);
		        			spstdesthmtDaoRemote.updateSpstdesthmt(hmt, webAppName);
		    	    		//spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey(),null,null,null,null,null,null,  null,null, userName,new Date(),null,null,null,webAppName);
							
		        			approval.setFromStatus(statusFrom);
							approval.setToStatus(statusTo);
							
							approval.setApprovalType("SES_VLDT");
							approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
							return "#Sent to "+forewordTo(statusTo)+  "for Approval";
							
							
		        			
		        		}else {
		        			return "@Can not Approve this";
		        		}	
		    	    }else if (status.equals(StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey().toString()) && authorityLevel.equals("DGM")){
		    	    	
		    	    	
	        			
		    	    	/*if(applicationType.equalsIgnoreCase(Constants.PLANNING) || applicationType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I)){
							//status= estimateEjb.updateEstimateStatus(estimateNo, costCenterNo, StandardEstimateStatus.PIVII_CONFIRMATION.getKey(),null,null,null,null,userId,new Date(),null,null,null,null,null,null,null, region);
							//by passing PIV II paying for RE Projects
							//spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.PIVII_CONFIRMATION.getKey(),null,null,null,null, userName,new Date(),  null,null, null,null,null,null,null,webAppName);
							
							hmt.setStatus(StandardEstimateStatus.PIVII_CONFIRMATION.getKey());
		        			hmt.setAssignTo(assignedUserName);
		        			spstdesthmtDaoRemote.updateSpstdesthmt(hmt, webAppName);
		        			
							approval.setFromStatus(StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey().toString());
							approval.setToStatus(StandardEstimateStatus.PIVII_CONFIRMATION.getKey().toString());
							approval.setApprovalType("SES_APRV");
							approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
							return "#Approved";
							
						}else{*/
						
							//status= estimateEjb.updateEstimateStatus(estimateNo, costCenterNo, StandardEstimateStatus.APPROVED_ESTIMATES.getKey(),userId,new Date(),null,null,null,null,null,null,null,null,null,null,null, region);
							//spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.APPROVED_ESTIMATES.getKey(),userName,new Date(),null,null, null,null,  null,null, null,null,null,null,null,webAppName);
							
							hmt.setStatus(Long.parseLong(statusTo));
		        			hmt.setAssignTo(assignedUserName);
		        			hmt.setApprovedBy(userName);
		    	    		hmt.setApprovedDate(new Date());
		        			spstdesthmtDaoRemote.updateSpstdesthmt(hmt, webAppName);
		        			
		        			approval.setFromStatus(statusFrom);
							approval.setToStatus(statusTo);
							
							//spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.APPROVED_ESTIMATES.getKey(),userName,new Date(),null,null,null,null,null,null,null,null,null,null,null,webAppName);
		    	    		
							approval.setApprovalType("SES_APRV");
							approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
							return "#Approved";
							
						//}
		    	    	
		    	    }else if (status.equals(EstimateStatus.EST_APPROVAL_AGM) && authorityLevel.equals("AGM")){
		    	    	
		    	    	if (dbTotalCost > limitFrom){
		    	    		//************* comment for SAN_TEST ****************
		    	    		hmt.setStatus(Long.parseLong(statusTo));
		        			hmt.setAssignTo(assignedUserName);
		        			hmt.setApprovedBy(userName);
		    	    		hmt.setApprovedDate(new Date());
		        			spstdesthmtDaoRemote.updateSpstdesthmt(hmt, webAppName);
		        			
		    	    		approval.setFromStatus(statusFrom);
		            		approval.setToStatus(statusTo);
		            		approval.setApprovalType("DES_APRV");
		            		
		            		//spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.APPROVED_ESTIMATES.getKey(),userName,new Date(),null,null,null,null,null,null,null,null,null,null,null,webAppName);
		    	    		
		            		approvalDaoRemote.createAutoIdConstructionEstimateApprovals(approval, webAppName);
		            		return "#Approved";
		    	    	} else {
		    	    		return "@Can not Approve this";
		    	    	}
		    	    }else {
		    	    	return "@Can not Approve this";
		    	    }
				} 	
			    	    	
						/*if( (Constants.EE_APPROVE_LIMIT_MIN <= totalCost) && (totalCost < Constants.EE_APPROVE_LIMIT_MAX )){
							//status= estimateEjb.updateEstimateStatus(estimateNo, costCenterNo, StandardEstimateStatus.APPROVED_ESTIMATES.getKey(),userId,new Date(),null,null,null,null,null,null,null,null,null,null,null, region);
							spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.APPROVED_ESTIMATES.getKey(),userName,new Date(),null,null,null,null,null,null,null,null,null,null,null,webAppName);
							
							approval.setFromStatus(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey().toString());
							approval.setToStatus(StandardEstimateStatus.APPROVED_ESTIMATES.getKey().toString());
							
							approval.setApprovalType("SES_APRV");
							approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
							return "#Approved";
							
						}else{
							//status= estimateEjb.updateEstimateStatus(estimateNo, costCenterNo, StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey(),null,null,null,null,null,null,userId,new Date(),null,null,null,null,null, region);
							spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey(),null,null,null,null,null,null,  userName,new Date(),null,null,null,null,null,webAppName);
							
							approval.setFromStatus(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey().toString());
							approval.setToStatus(StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey().toString());
							approval.setApprovalType("SES_VLDT");
							//setErrorMessage("DONE:- Successfully Posted for CE");
							approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
							return "#Sent to "+forewordTo(StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey().toString())+  "for Approval";
							
						}
					//}
					
				}else if(authorityLevel.equalsIgnoreCase("CE")){
					
					if( (Constants.CE_APPROVE_LIMIT_MIN <= totalCost) && (totalCost < Constants.CE_APPROVE_LIMIT_MAX )){
						//status= estimateEjb.updateEstimateStatus(estimateNo, costCenterNo, StandardEstimateStatus.APPROVED_ESTIMATES.getKey(),userId,new Date(),null,null,null,null,null,null,null,null,null,null,null, region);
						spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.APPROVED_ESTIMATES.getKey(),userName,new Date(),null,null,null,null,  null,null,null,null,null,null,null,webAppName);
						
						approval.setFromStatus(StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey().toString());
						approval.setToStatus(StandardEstimateStatus.APPROVED_ESTIMATES.getKey().toString());
						approval.setApprovalType("SES_APRV");
						approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
						return "#Approved";
						
					}else{
						//status= estimateEjb.updateEstimateStatus(estimateNo, costCenterNo, StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey(),null,null,null,null,null,null,null,null,userId,new Date(),null,null,null, region); 
						spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey(),null,null,null,null,null,null,  null,null, userName,new Date(),null,null,null,webAppName);
						
						approval.setFromStatus(StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey().toString());
						approval.setToStatus(StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey().toString());
						
						approval.setApprovalType("SES_VLDT");
						approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
						return "#Sent to "+forewordTo(StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey().toString())+  "for Approval";
						
					}
					
				}else if(authorityLevel.equalsIgnoreCase("PE")){
					
					if(applicationType.equalsIgnoreCase(Constants.PLANNING)){
						//status= estimateEjb.updateEstimateStatus(estimateNo, costCenterNo, StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey(),null,null,null,null,userId,new Date(),null,null,null,null,null,null,null, region);
						spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey(),null,null,null,null, userName,new Date(),  null,null, null,null,null,null,null,webAppName);
						
						approval.setFromStatus(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey().toString());
						approval.setToStatus(StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey().toString());
						
						approval.setApprovalType("SES_VLDT");
						approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
						return "#Sent to "+forewordTo(StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey().toString())+  "for Approval";
						
	
					}else{
					
						//status= estimateEjb.updateEstimateStatus(estimateNo, costCenterNo, StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey(),null,null,null,null,userId,new Date(),null,null,null,null,null,null,null, region);
						spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey(),null,null,null,null, userName,new Date(),  null,null, null,null,null,null,null,webAppName);
						
						approval.setFromStatus(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey().toString());
						approval.setToStatus(StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey().toString());
						approval.setApprovalType("SES_VLDT");
						approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
						return "#Sent to "+forewordTo(StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey().toString())+  "for Approval";
						
					}
					
				}else if(authorityLevel.equalsIgnoreCase("DGM")){
					
					if(applicationType.equalsIgnoreCase(Constants.PLANNING) || applicationType.equalsIgnoreCase(Constants.RUTAL_ELECTRIFICATION_I)){
						//status= estimateEjb.updateEstimateStatus(estimateNo, costCenterNo, StandardEstimateStatus.PIVII_CONFIRMATION.getKey(),null,null,null,null,userId,new Date(),null,null,null,null,null,null,null, region);
						//by passing PIV II paying for RE Projects
						spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.PIVII_CONFIRMATION.getKey(),null,null,null,null, userName,new Date(),  null,null, null,null,null,null,null,webAppName);
						
						approval.setFromStatus(StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey().toString());
						approval.setToStatus(StandardEstimateStatus.PIVII_CONFIRMATION.getKey().toString());
						approval.setApprovalType("SES_APRV");
						approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
						return "#Approved";
						
					}else{
					
						//status= estimateEjb.updateEstimateStatus(estimateNo, costCenterNo, StandardEstimateStatus.APPROVED_ESTIMATES.getKey(),userId,new Date(),null,null,null,null,null,null,null,null,null,null,null, region);
						spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), deptId, StandardEstimateStatus.APPROVED_ESTIMATES.getKey(),userName,new Date(),null,null, null,null,  null,null, null,null,null,null,null,webAppName);
						
						approval.setFromStatus(StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey().toString());
						approval.setToStatus(StandardEstimateStatus.APPROVED_ESTIMATES.getKey().toString());
						approval.setApprovalType("SES_APRV");
						approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
						return "#Approved";
						
					}*/
				//}
			//	appejb.createAutoIdApprovals(approval);
		        	
			
				
			}else{
				return "@Please contact the MIS Staff.";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "@Please contact the MIS Staff.";
		}
		return "@Can not Approve this";
		
	}
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String rejectStandardEstimate(String estimateNo,  Approval approval, String webAppName) {
		try{
			if(approval.getApprovedLevel().equalsIgnoreCase("EE")){
				
				//status= estimateEjb.updateEstimateStatus(estimateNo, costCenterNo, StandardEstimateStatus.REJECTED_BY_ELECTRICAL_ENG.getKey(),null,null,userId,new Date(),null,null,null,null,null,null,null,null,getRejectedReason(), region);
				spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), approval.getDeptId(), StandardEstimateStatus.REJECTED_BY_ELECTRICAL_ENG.getKey(),null,null,approval.getApprovedBy(), approval.getApprovedDate(),null,null, null,null,  null,null, null,null,approval.getReason(),webAppName);
				approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
				
				return "Rejected By EE";
			}else if(approval.getApprovedLevel().equalsIgnoreCase("CE")){
			
				//status= estimateEjb.updateEstimateStatus(estimateNo, costCenterNo, StandardEstimateStatus.REJECTED_BY_CE.getKey(),null,null,userId,new Date(),null,null,null,null,null,null,getRejectedReason(),null,null, region); 
				spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), approval.getDeptId(), StandardEstimateStatus.REJECTED_BY_CE.getKey(),null,null,approval.getApprovedBy(), approval.getApprovedDate(),null,null, null,null,  null,null,approval.getReason(),null,null,webAppName);
				approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
				
				return "DONE:- Rejected By CE";
			}else if(approval.getApprovedLevel().equalsIgnoreCase("PE")){
			
				//status= estimateEjb.updateEstimateStatus(estimateNo, costCenterNo, StandardEstimateStatus.REJECTED_BY_PLANNING_ENG.getKey(),null,null,userId,new Date(),null,null,null,null,null,null,null,getRejectedReason(),null, region);
				spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), approval.getDeptId(), StandardEstimateStatus.REJECTED_BY_PLANNING_ENG.getKey(),null,null,approval.getApprovedBy(), approval.getApprovedDate(),null,null, null,null,  null,null,null,approval.getReason(),null,webAppName);
				
				approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
				
				return "DONE:- Rejected By PE";
			}else if(approval.getApprovedLevel().equalsIgnoreCase("DGM")){
				
				//status= estimateEjb.updateEstimateStatus(estimateNo, costCenterNo, StandardEstimateStatus.REJECTED_BY_DGM.getKey(),null,null,userId,new Date(),null,null,null,null,null,null,null,null,getRejectedReason(), region);
				spstdesthmtDaoRemote.updateEstimateStatus(estimateNo.trim(), approval.getDeptId(), StandardEstimateStatus.REJECTED_BY_DGM.getKey(),null,null,approval.getApprovedBy(), approval.getApprovedDate(),null,null, null,null,  null,null,null,null,approval.getReason(),webAppName);
				approvalDaoRemote.createAutoIdSEstmateApprovals(approval, webAppName);
				
				return "DONE:- Rejected By DGM";
			}
			
			
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
		
			return "@Please contact the MIS Staff.";
		}
		
		return "@Can not Approve this";
			
		
	}
	private String forewordTo(String status){
		if (status.equals(StandardEstimateStatus.TO_BE_VALIDATED_BY_ELECTRICAL_ENGINEER.getKey().toString())){
			return " Electrical Engineer ";
		}else if (status.equals(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_ENGINEER.getKey().toString())){
			return " Planning Engineer ";
		}else if (status.equals(StandardEstimateStatus.TO_BE_VALIDATED_BY_PLANNING_CE.getKey().toString())){
			return " Planning Chief Engineer ";
		}else if (status.equals(StandardEstimateStatus.TO_BE_VALIDATED_BY_CE.getKey().toString())){
			return " Cheif Engineer ";
		}else if (status.equals(StandardEstimateStatus.TO_BE_APPROVED_BY_DGM.getKey().toString())){
			return " DGM ";
		} /**else if (status.equals(StandardEstimateStatus.TO_BE_APPROVED_BY_AGM.getKey().toString())){
			return " AGM ";
		}*/else {
			return " ";
		}
		
	}
	private String forewordDESTo(String status){
		if (status.equals(EstimateStatus.EST_APPROVAL_EA)){
			return " Engineering Assistant ";
		}else if (status.equals(EstimateStatus.EST_APPROVAL_ES)){
			return " Electrical Spirintdendant ";
		}else if (status.equals(EstimateStatus.EST_APPROVAL_ES)){
			return " Electrical Spirintdendant ";
		}else if (status.equals(EstimateStatus.EST_APPROVAL_EE)){
			return " Electrical Engineer ";
		}else if (status.equals(EstimateStatus.EST_APPROVAL_CE)){
			return " Cheif Engineer";
		}else if (status.equals(EstimateStatus.EST_APPROVAL_DGM)){
			return " DGM ";
		}else if (status.equals(EstimateStatus.EST_APPROVAL_AGM)){
			return " AGM ";
		} else {
			return " ";
		}
		
	}


	@Override
	public List<String> getFundSources(String deptId, String webAppName) {
		// TODO Auto-generated method stub
		return pcfunddmDaoRemote.getFundSources(deptId, webAppName);
	}



	@Override
	public List<Pcfunddm> getFundSourcesList(String deptId, String webAppName) {
		return pcfunddmDaoRemote.getFundSourceList(deptId, webAppName);
	}



	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateDetailEstimateCost(String estimateNo, String estimateType,String deptId,
			String webAppName) throws PersistenceException {
		try {
			
			
			addAdditionalCostEntries(estimateNo, estimateType, deptId, webAppName);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public List<String> getFirstFiftyJobList(String deptId,List<Long> status,  String webAppName) {
		//getEntityManager(webAppName);
		//String qryStr ="SELECT p FROM Pcesthtt p WHERE trim(p.id.deptId)= :deptId AND  p.status = :status AND p.entBy=:entryBy AND order by p.id.estimateNo";
		StringBuffer buff = new StringBuffer();
		buff.append("SELECT p.projectNo FROM Pcesthmt p,Application a WHERE trim(p.id.estimateNo)= trim(a.applicationNo) AND a.applicationSubType='FF' AND trim(p.id.deptId)= :deptId AND  p.status in (:status)");
		
		buff.append(" order by p.id.estimateNo");
		Query query = getEntityManager(webAppName).createQuery(buff.toString());
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		
		
		List<String> list = query.getResultList();
		return list;
	}
	public BigDecimal getTotalConsumerPayable(String estimateNo,String resType,String deptId,String webAppName) throws PersistenceException{
		
			/*try {
				//updateAdditionalCost(pcesthtt, deptId, pcesthtt.getStdCost(), webAppName);
			} catch (Exception e) {
				LOGGER.error("Error in Additional Cost calculation Estimate No:"+estimateNo);
			}*/
			StringBuffer qryStr2 = new StringBuffer();
			qryStr2.append("select sum(a.estimateCost) from Pcestdtt a where TRIM(a.id.estimateNo)=:estimateNo AND TRIM(a.id.deptId)=:deptId AND TRIM(a.id.revNo)=:revNo ");
			
			if(resType != null){
				qryStr2.append("AND resType <> :resType");
			}
			
			Query query2 = getEntityManager(webAppName).createQuery(qryStr2.toString());
			query2.setParameter("estimateNo", estimateNo.trim());
			query2.setParameter("deptId", deptId);
			query2.setParameter("revNo", "2");
			if(resType != null){
				
				query2.setParameter("resType", resType);
			}
			BigDecimal stdCost = (BigDecimal) query2.getSingleResult();
			return stdCost;
		
	}
	@Override
	public List<SpNormsGroup> getChildrensByParentId(String parentId,String webAppname) {
		// TODO Auto-generated method stub
		return normsGroupDaoRemote.getChildrensByParentId(parentId,webAppname);
	}
	@Override
	public List<SpNormsGroup> getAllSpNormsGroups(String webAppname) {
		// TODO Auto-generated method stub
		return normsGroupDaoRemote.getAll(webAppname);
	}
	
	@Override
	public List<Spnorms> getNormsChildrensByParentId(String parentId,String webAppname) {
		// TODO Auto-generated method stub
		return normsDaoRemote.getChildrensByParentId(parentId,webAppname);
	}
//	@Override
//	public List<EstimateTypeMaster> getEstimateTypesbyDeptId(String deptId, String webAppName)
//			throws PersistenceException {
//		List<EstimateTypeMaster> list = estimateTypeMasterDao.findByDeptId(deptId, webAppName);
//		//List<String> list =new ArrayList<String>();
//		/*for(EstimateTypeMaster master : list){
//			list.add(arg0);
//		}*/
//		return estimateTypeMasterDao.findByDeptId(deptId, webAppName);
//	}
	
	@Override
	public List<AllocationSummaryDisplay> findEstimateSummary(String applicationNo,String deptId, String webAppName)
	throws PersistenceException{
		// TODO Auto-generated method stub
		return spstdestdmtDaoRemote.findEstimateSummary(applicationNo,deptId,webAppName);
	}

	@Override
	public List<Pcesthtt> getEstimateListForAuthorizedCC(Long revNo, List<Long> status, String userId,String likeEstimate, String webAppName) {
		List<String> authorizedids = securityBeanRemote.getAuthorizedCostCenters(userId, webAppName);
		
		return pcesthttDaoRemote.getEstimateListForAuthorizedCC(authorizedids,revNo,status,userId,likeEstimate,webAppName);
	}

	
	public void setFormat(Format format) {
		this.format = format;
	}
	public int deleteEstimate(Pcesthtt htt,List<Pcestdtt> dtts,TempTb temptb,EstimateReference ref, String webAppName){
		try{
			if(htt != null){
				if(dtts != null){
					for(Pcestdtt dtt : dtts){
						pcestdttDaoRemote.removePcestdtt(dtt, webAppName);
					}
				}
				
				pcesthttDaoRemote.removePcesthtt(htt, webAppName);
				
				if(ref != null){
					estimateReferenceDaoRemote.removEstimateReference(ref, webAppName);
				}
				
				if(temptb != null){
					tempTbDaoRemote.removTempTb(temptb, webAppName);
					
				}
				
				List<Pegschdmt> pegschdmts = pegschdmtDao.findByEstimationNo(htt.getId().getEstimateNo(), htt.getId().getDeptId(), webAppName);
				if(pegschdmts != null && pegschdmts.size() > 0){
					
					for(Pegschdmt pegschdmt : pegschdmts){
						pegschdmtDao.deletePegschdmt(pegschdmt, webAppName);
					}
					
				}
			}
			
			return 1;
		}catch(Exception e){
			context.setRollbackOnly();
			
			//return "@Please contact the MIS Staff.";
			return 0;
		}
		
		
		
		

		
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public String updateAndValidate(Pcesthtt pcesthtt, List<Pcestdtt> addlist, List<Pcestdtt> updList,List<Pcestdtt> deleList,List<Pegschdmt> addedPegschdmtList,List<Pegschdmt> updatedPegschdmtList,TempTb tempTb, String authorityLevel, String userName,String comment,String estimateType,String webAppName) {
		try{
			System.out.println("DES : " +pcesthtt.getId().getDeptId() +" "+  estimateType +" "+ authorityLevel);
			Appestlim appestlim= pcesthttDaoRemote.findAppEstLimits(pcesthtt.getId().getDeptId(), "DES",estimateType, authorityLevel, webAppName);
			System.out.println("DES : " +appestlim );
			if (appestlim!=null){
				Double limitFrom=Double.parseDouble(String.valueOf(appestlim.getId().getLimitFrom()));
				Double limitTo=Double.parseDouble(String.valueOf(appestlim.getId().getLimitTo()));
				String statusFrom=String.valueOf(appestlim.getId().getStatusFrom());
				String statusTo=String.valueOf(appestlim.getId().getStatusTo());
			
				
				Query query=null;
			
				
				//Approvals
				Calendar calendar = Calendar.getInstance();
				Format format=new Format();
				Approval approval=new Approval();
				approval.setApprovalId(null);
				approval.setReferenceNo(pcesthtt.getId().getEstimateNo().trim());
				approval.setDeptId(pcesthtt.getId().getDeptId());
				approval.setReason(comment);
				approval.setApprovedLevel(authorityLevel);
				approval.setApprovedBy(userName);
				approval.setApprovedDate(calendar.getTime());
				approval.setApprovedTime(format.FormatTime());
				pcesthtt.setStatus(new Long(statusFrom));
			
				updateWorkEstimateDetails(pcesthtt, null, addlist, updList, deleList, addedPegschdmtList, updatedPegschdmtList, tempTb, webAppName);
				updateDetailEstimateCost(pcesthtt.getId().getEstimateNo().trim(), estimateType,pcesthtt.getId().getDeptId(),webAppName);
				approval.setFromStatus(statusFrom);
				approval.setToStatus(statusFrom);
				approval.setApprovalType("DES_VLDT");
				approvalDaoRemote.createAutoIdConstructionEstimateApprovals(approval, webAppName);
				return "#Successfully updated";
			}else{
				return "@Can not Approve this";
			}
		}catch (Exception e) {
			e.printStackTrace();
			
			return "@Please contact the MIS Staff.";
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean checkEstimateNoExist(String wtdestimateNo,String deptId,String webAppName) {
		estimateReferenceDaoRemote.checkEstimateNoExist(wtdestimateNo, wtdestimateNo, deptId, webAppName);
		String sequence=null;
    	String like=wtdestimateNo+"%";
    	String strQuery="select * from PCESTHTT where TRIM(ESTIMATE_NO) like '"+like+"' and DEPT_ID='"+deptId+"'";
    	Query query=getEntityManager(webAppName).createNativeQuery(strQuery);//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
    	
    	List<String> list=query.getResultList();
    	if(list != null && list.size() > 0){
    		return true;
    	}else{
    		String strQuery1="select * from PCESTHMT where TRIM(ESTIMATE_NO) like '"+like+"' and DEPT_ID='"+deptId+"'";
        	Query query1=getEntityManager(webAppName).createNativeQuery(strQuery1);//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
        	
        	List<String> list1=query1.getResultList();
        	if(list != null && list.size() > 0){
        		return true;
        	}else{
        		return false;
    		}
    	

	    }
	}



	@Override
	public List<Spstdesthmt> loadStandEstmateDetailsType(String costCenter,
			List<Long> status, String userName, String appType,
			String webAppName) {
       try{
			
			//System.out.println("Hiii 2");
			List<Spstdesthmt>  list = spstdesthmtDaoRemote.loadStandEstmateDetails(costCenter,status,userName,appType, webAppName);
			//System.out.println("Hiii 3");
			return list;
		}catch(Exception e){
			LOGGER.info("Exception Occured in loadApplicationRefno" + e);
			e.printStackTrace();
		}
		return null;

	}
	
	
	

}
