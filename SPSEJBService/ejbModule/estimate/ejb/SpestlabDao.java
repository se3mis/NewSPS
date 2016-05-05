package estimate.ejb;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import job.ejb.PcestdmtDaoRemote;
import job.ejb.PcesthmtDaoLocal;
import job.ejb.PcesthmtDaoRemote;
import job.model.Pcestdmt;
import job.model.Pcesthmt;
import util.emSelect.EmSelector;
import estimate.model.LabourGrid;
import estimate.model.Pcestdtt;
import estimate.model.Pcesthtt;
import estimate.model.Spestlab;
import estimate.model.SpestlabPK;
import estimate.model.Speststd;
import estimate.model.Spserest;

/**
 * Session Bean implementation class SpestlabDao
 */
@Stateless
public class SpestlabDao extends EmSelector implements SpestlabDaoRemote, SpestlabDaoLocal {
	//@PersistenceContext(unitName="SMCTesting")
	//private EntityManager emSMCTesting;
	//@PersistenceContext(unitName="SMCR1")
	//private EntityManager emSMCR1;
	//@PersistenceContext(unitName="SMCR2")
	//private EntityManager emSMCR2;
	//@PersistenceContext(unitName="SMCR3")
	//private EntityManager emSMCR3;
	//@PersistenceContext(unitName="SMCR4")
	//private EntityManager emSMCR4;
	@Resource
	private SessionContext context;
	@EJB
	PcesthttDaoRemote pcesthttDaoRemote;
	@EJB
	PcesthmtDaoRemote pcesthmtDaoRemote;
	@EJB
	PcestdttDaoRemote pcestdttDaoRemote;
	@EJB
	PcestdmtDaoRemote pcestdmtDaoRemote;
	@EJB
	SpeststdDaoRemote speststdDaoRemote;
	@EJB
	SpserestDaoRemote spserestDaoRemote;


    /**
     * Default constructor. 
     */
    public SpestlabDao() {
        // TODO Auto-generated constructor stub
    }
    
    //private EntityManager getEntityManager(String webAppName){
	//	if (webAppName.equals("R1"))
	//			return emSMCR1;	
	//	else if (webAppName.equals("R2"))
	//		return emSMCR2;
	//	else if (webAppName.equals("R3"))
	//		return emSMCR3;
	//	else if (webAppName.equals("R4"))
	//		return emSMCR4;
	//	else return emSMCTesting;	
	//}

	@Override
	public void createSpestlab(Spestlab spestlab, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(spestlab);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spestlab> getAll(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spestlab a where a.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<Spestlab> list = query.getResultList();
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllByType(String deptId, String like, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select distinct TRIM(a.id.estimateNo) from Spestlab a where a.id.deptId=:deptId and a.id.estimateNo like :like ";
		//String qryStr = "select distinct TRIM(a.id.estimateNo) from Spestlab a where a.id.deptId=:deptId ";
		System.out.println(qryStr);
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("like", like);
		List<String> list = query.getResultList();
		
		System.out.println(list.size());
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spestlab> getAll(String estimateNo, String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spestlab a where a.id.estimateNo=:estimateNo AND a.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", estimateNo.trim());
		query.setParameter("deptId", deptId);
		List<Spestlab> list = query.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Spestlab findByEstimateNo(String estimateNo, String labourCode, String webAppName)  {
		String qryStr = "select a from Spestlab a where a.id.estimateNo=:estimateNo AND a.id.labourCode = :labourCode";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", estimateNo.trim());
		query.setParameter("labourCode", labourCode);
		List<Spestlab> list = query.getResultList();
		if (list.isEmpty())
			return null;
		else if (list.size() == 1)
			return list.get(0);
		throw new NonUniqueResultException();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabourGrid> getSpestlabList(String estimateNo, String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select new estimate.model.LabourGrid(a.id.labourCode, a.activityDescription, a.itemQty, a.labourCost, a.labourHours, a.unitLabourHrs, a.unitPrice, a.cebUnitPrice, a.cebLabourCost) from Spestlab a where a.id.estimateNo=:estimateNo AND a.id.deptId=:deptId";
		//String qryStr = "select a.id.labourCode, a.activityDescription, a.itemQty, a.labourCost, a.labourHours, a.unitLabourHrs, a.unitPrice from Spestlab a where a.id.estimateNo=:estimateNo AND a.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", estimateNo.trim());
		query.setParameter("deptId", deptId);
		List<LabourGrid> list = query.getResultList();
		System.out.println(list);
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public BigDecimal getSpestlabSUM(String estimateNo, String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select SUM(a.cebLabourCost) from Spestlab a where a.id.estimateNo=:estimateNo AND a.id.deptId=:deptId ";
		//String qryStr = "select a.id.labourCode, a.activityDescription, a.itemQty, a.labourCost, a.labourHours, a.unitLabourHrs, a.unitPrice from Spestlab a where a.id.estimateNo=:estimateNo AND a.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", estimateNo.trim());
		query.setParameter("deptId", deptId);
		BigDecimal sum = (BigDecimal) query.getSingleResult();
		return sum;
		//if (list.isEmpty())
		//	return null;
        //else if (list.size() == 1)
        //	return list.get(0);
        //throw new NonUniqueResultException();
	}

	@Override
	public void updateSpestlab(Spestlab spestlab, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(spestlab);
		
	}

	@Override
	public void removeSpestlab(Spestlab spestlab, String webAppName) {
		//getEntityManager(webAppName);
		spestlab=getEntityManager(webAppName).merge(spestlab);
		getEntityManager(webAppName).remove(spestlab);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");

		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Spestlab findById(SpestlabPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spestlab a where a.id.estimateNo=:estimateNo AND a.id.deptId = :deptId AND a.id.labourCode = :labourCode";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", id.getEstimateNo().trim());
		query.setParameter("deptId", id.getDeptId());
		query.setParameter("labourCode", id.getLabourCode());
		List<Spestlab> list = query.getResultList();
		if (list.isEmpty())
			return null;
		else if (list.size() == 1)
			return list.get(0);
		throw new NonUniqueResultException();

	} 
	
	
	//@SuppressWarnings("unchecked")
	@Override
	public void updateContractorLabourCostByEstNo(String estimateNo, String deptId, String webAppName)  {
		try{
			
			NumberFormat nf = NumberFormat.getInstance();
			nf.setGroupingUsed(true);
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			//List<Spestlab> list=getAll(deptId, webAppName);
			List<Spestlab> list=getAll(estimateNo.trim(), deptId, webAppName);
			for(int i=0;i<list.size();i++){
				System.out.println(list.get(i).getId().getEstimateNo());
				//BigDecimal labourHours=list.get(i).getLabourHours();
				
				BigDecimal labourCost=list.get(i).getLabourCost();
				System.out.println("1 "+labourCost);
				BigDecimal unitPrice=list.get(i).getUnitPrice();
				System.out.println("2 "+unitPrice);
				BigDecimal unitLabourHrs=list.get(i).getUnitLabourHrs();
				//BigDecimal labourHours=list.get(i).getLabourHours();
				BigDecimal itemQty=list.get(i).getItemQty();
				BigDecimal labourHours=BigDecimal.valueOf(itemQty.doubleValue()*unitLabourHrs.doubleValue());
				System.out.println("3 "+labourHours);
				
				//Double newdbLabourCost=Double.parseDouble(unitPrice.toString())*Double.parseDouble(labourHours.toString())/Double.parseDouble(unitLabourHrs.toString());
				Double newdbLabourCost1=0.00;
				Double newdbLabourCost=0.00;
				if (!unitLabourHrs.toString().equals("0")){
					newdbLabourCost1=Double.valueOf(unitPrice.doubleValue())*Double.valueOf(itemQty.doubleValue() );
					newdbLabourCost=Double.valueOf(newdbLabourCost1);
				}else{
					newdbLabourCost1=Double.valueOf(unitPrice.doubleValue()*list.get(i).getItemQty().doubleValue() );
					newdbLabourCost=Double.valueOf(newdbLabourCost1);
				}
				System.out.println("4 "+newdbLabourCost);
				//newdbLabourCost.
				BigDecimal newLabourCost=  BigDecimal.valueOf(newdbLabourCost);
				System.out.println("5");
				Number jhgsdhf = nf.parse(newdbLabourCost.toString());
				jhgsdhf.toString();
				if (!labourCost.toString().equals(newLabourCost.toString())){
					SpestlabPK id=new SpestlabPK();
					id.setDeptId(deptId);
					id.setEstimateNo(list.get(i).getId().getEstimateNo());
					id.setLabourCode(list.get(i).getId().getLabourCode());
					
					Spestlab spestlab=findById(id, webAppName);
					spestlab.setLabourCost(new BigDecimal(jhgsdhf.toString()));
					spestlab.setLabourHours(labourHours);
					System.out.println("#"+labourCost+" "+unitPrice+" "+labourHours+" "+unitLabourHrs+" "+ newdbLabourCost+"$ "+newLabourCost+" "+ jhgsdhf);
					updateSpestlab(spestlab, webAppName);
				}else{
					System.out.println("G"+labourCost+" "+unitPrice+" "+labourHours+" "+unitLabourHrs+" "+ newdbLabourCost+"S "+newLabourCost+" "+ jhgsdhf);
				}
				
				
				
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//@SuppressWarnings("unchecked")
	@Override
	public void updateContractorLabourCost(String deptId, String webAppName)  {
		try{
			NumberFormat nf = NumberFormat.getInstance();
			nf.setGroupingUsed(true);
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			List<Spestlab> list=getAll(deptId, webAppName);
			for(int i=0;i<list.size();i++){
				System.out.println(list.get(i).getId().getEstimateNo());
				BigDecimal labourCost=list.get(i).getLabourCost();
				BigDecimal unitPrice=list.get(i).getUnitPrice();
				//BigDecimal labourHours=list.get(i).getLabourHours();
				BigDecimal unitLabourHrs=list.get(i).getUnitLabourHrs();
				//BigDecimal labourHours=list.get(i).getLabourHours();
				BigDecimal itemQty=list.get(i).getItemQty();
				BigDecimal labourHours=BigDecimal.valueOf(itemQty.doubleValue()*unitLabourHrs.doubleValue());
				//System.out.println("1 "+labourCost);
				//Double newdbLabourCost=Double.parseDouble(unitPrice.toString())*Double.parseDouble(labourHours.toString())/Double.parseDouble(unitLabourHrs.toString());
				Double newdbLabourCost1=0.00;
				Double newdbLabourCost=0.00;
				//System.out.println();
				if (!unitLabourHrs.toString().equals("0")){
					newdbLabourCost1=Double.valueOf(unitPrice.doubleValue())*Double.valueOf(itemQty.doubleValue());
					newdbLabourCost=Double.valueOf(newdbLabourCost1);
				}else{
					newdbLabourCost1=Double.valueOf(unitPrice.doubleValue()*list.get(i).getItemQty().doubleValue());
					newdbLabourCost=Double.valueOf(newdbLabourCost1);
				}
				//System.out.println("2 "+newdbLabourCost);
				//newdbLabourCost.
				BigDecimal newLabourCost=  BigDecimal.valueOf(newdbLabourCost);
				//System.out.println("3 "+newLabourCost);
				Number jhgsdhf = nf.parse(newdbLabourCost.toString());
				jhgsdhf.toString();
				//System.out.println("4 "+jhgsdhf.toString());
				if (!labourCost.toString().equals(newLabourCost.toString())){
					System.out.println("####### NOT EQUAL ######");
					SpestlabPK id=new SpestlabPK();
					id.setDeptId(deptId);
					id.setEstimateNo(list.get(i).getId().getEstimateNo());
					id.setLabourCode(list.get(i).getId().getLabourCode());
					Spestlab spestlab=findById(id, webAppName);
					spestlab.setLabourCost(new BigDecimal(jhgsdhf.toString()));
					spestlab.setLabourHours(labourHours);
					System.out.println("#"+labourCost+" "+unitPrice+" "+labourHours+" "+unitLabourHrs+" "+ newdbLabourCost+"$ "+newLabourCost+" "+ jhgsdhf);
					updateSpestlab(spestlab, webAppName);
				}else{
					System.out.println("G"+labourCost+" "+unitPrice+" "+labourHours+" "+unitLabourHrs+" "+ newdbLabourCost+"S "+newLabourCost+" "+ jhgsdhf);
				}
				
				
				
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void updateDmtHmtForLabour(String deptId, String webAppName)  { 
		try{
			NumberFormat nf = NumberFormat.getInstance();
			nf.setGroupingUsed(true);
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			String overHeadRate="162.00";
			String labourRate="170.00";
			List<String> list=getAllByType(deptId, deptId+"/ENC/2011/%", webAppName);
			//List<Spestlab> list=getAll(estimateNo, deptId, webAppName);
			System.out.println(list);
			System.out.println(list.size());
			
			
			if (!list.isEmpty()){
				for(int i=0;i<list.size();i++){
				//for(int i=0;i<1;i++){
					System.out.println(i);
					Pcestdmt pcestdmtA0203=pcestdmtDaoRemote.findBy3PK(list.get(i), deptId, "A0203", webAppName);
					if (pcestdmtA0203!=null){
						insertTransport(pcestdmtA0203,list.get(i), deptId, "A0203", webAppName);
					}
					Pcestdmt pcestdmtA0211=pcestdmtDaoRemote.findBy3PK(list.get(i), deptId, "A0211", webAppName);
					if (pcestdmtA0211!=null){
						insertTransport(pcestdmtA0211,list.get(i), deptId, "A0211", webAppName);
					}
					Pcestdmt pcestdmtA0215=pcestdmtDaoRemote.findBy3PK(list.get(i), deptId, "A0215", webAppName);
					if (pcestdmtA0215!=null){
						insertTransport(pcestdmtA0215,list.get(i), deptId, "A0215", webAppName);
					}
					
					
					BigDecimal sum= getSpestlabSUM(list.get(i), deptId, webAppName);
					
					
					
					Pcestdmt pcestdmtLABOUR=pcestdmtDaoRemote.findBy3PK(list.get(i), deptId, "LABOUR", webAppName);
					Pcestdmt pcestdmtOVERHEAD=pcestdmtDaoRemote.findBy3PK(list.get(i), deptId, "OVERHEAD", webAppName);
					if (pcestdmtLABOUR!=null){
						pcestdmtLABOUR.setEstimateQty(sum);
						pcestdmtLABOUR.setEstimateCost(sum);
						pcestdmtDaoRemote.updatePcestdmt(pcestdmtLABOUR, webAppName);
						if (pcestdmtOVERHEAD!=null){
							
							Double ohDB= sum.doubleValue()/new Double(labourRate)* new Double(overHeadRate);
							Number oh = nf.parse(ohDB.toString());
							System.out.println("OverHead "+oh.toString());
							pcestdmtOVERHEAD.setEstimateQty(new BigDecimal(oh.toString()));
							pcestdmtOVERHEAD.setEstimateCost(new BigDecimal(oh.toString()));
							pcestdmtDaoRemote.updatePcestdmt(pcestdmtOVERHEAD, webAppName);
						}
						
						BigDecimal sumDMT=pcestdmtDaoRemote.getSUM(list.get(i), webAppName);
						System.out.println(sumDMT);
						Pcesthmt pcesthmt= pcesthmtDaoRemote.findByEstimationNo(list.get(i), webAppName);
						pcesthmt.setStdCost(sumDMT);
						pcesthmtDaoRemote.updatePcesthmt(pcesthmt, webAppName);
						System.out.println(sum+list.get(i)+" "+deptId+" "+sumDMT+" ");
					}else{
					
					}
					
					Pcestdtt pcestdttLABOUR=pcestdttDaoRemote.findBy3PK(list.get(i), deptId, "LABOUR", webAppName);
					Pcestdtt pcestdttOVERHEAD=pcestdttDaoRemote.findBy3PK(list.get(i), deptId, "OVERHEAD", webAppName);
					if (pcestdttLABOUR!=null){
						pcestdttLABOUR.setEstimateQty(sum);
						pcestdttLABOUR.setEstimateCost(sum);
						pcestdttDaoRemote.updatePcestdtt(pcestdttLABOUR, webAppName);
						if (pcestdttOVERHEAD!=null){
							
							Double ohDB= sum.doubleValue()/new Double(labourRate)* new Double(overHeadRate);
							Number oh = nf.parse(ohDB.toString());
							System.out.println("OverHead "+oh.toString());
							pcestdttOVERHEAD.setEstimateQty(new BigDecimal(oh.toString()));
							pcestdttOVERHEAD.setEstimateCost(new BigDecimal(oh.toString()));
							pcestdttDaoRemote.updatePcestdtt(pcestdttOVERHEAD, webAppName);
						}
						
						BigDecimal sumDMT=pcestdttDaoRemote.getSUM(list.get(i), webAppName);
						System.out.println(sumDMT);
						Pcesthtt Pcesthtt= pcesthttDaoRemote.findByEstimationNo(list.get(i), webAppName);
						Pcesthtt.setStdCost(sumDMT);
						pcesthttDaoRemote.updatePcesthtt(Pcesthtt, webAppName);
					}else{
						
					}
					
					
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void updateDmtHmtForLabour(String deptId, String webAppName,int from,int to)  { 
		try{
			NumberFormat nf = NumberFormat.getInstance();
			nf.setGroupingUsed(true);
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			String overHeadRate="162.00";
			String labourRate="170.00";
			List<String> list=getAllByType(deptId, deptId+"/ENC/2011/%", webAppName);
			//List<Spestlab> list=getAll(estimateNo, deptId, webAppName);
			System.out.println(list);
			System.out.println(list.size());
			
			if (to<=list.size()){
				
			}else{
				to=list.size();
			}
			
			if (!list.isEmpty()){
				for(int i=from;i<to;i++){
				//for(int i=0;i<1;i++){
					System.out.println(i);
					Pcestdmt pcestdmtA0203=pcestdmtDaoRemote.findBy3PK(list.get(i), deptId, "A0203", webAppName);
					if (pcestdmtA0203!=null){
						insertTransport(pcestdmtA0203,list.get(i), deptId, "A0203", webAppName);
					}
					Pcestdmt pcestdmtA0211=pcestdmtDaoRemote.findBy3PK(list.get(i), deptId, "A0211", webAppName);
					if (pcestdmtA0211!=null){
						insertTransport(pcestdmtA0211,list.get(i), deptId, "A0211", webAppName);
					}
					Pcestdmt pcestdmtA0215=pcestdmtDaoRemote.findBy3PK(list.get(i), deptId, "A0215", webAppName);
					if (pcestdmtA0215!=null){
						insertTransport(pcestdmtA0215,list.get(i), deptId, "A0215", webAppName);
					}
					
					
					BigDecimal sum= getSpestlabSUM(list.get(i), deptId, webAppName);
					
					
					
					Pcestdmt pcestdmtLABOUR=pcestdmtDaoRemote.findBy3PK(list.get(i), deptId, "LABOUR", webAppName);
					Pcestdmt pcestdmtOVERHEAD=pcestdmtDaoRemote.findBy3PK(list.get(i), deptId, "OVERHEAD", webAppName);
					if (pcestdmtLABOUR!=null){
						pcestdmtLABOUR.setEstimateQty(sum);
						pcestdmtLABOUR.setEstimateCost(sum);
						pcestdmtDaoRemote.updatePcestdmt(pcestdmtLABOUR, webAppName);
						if (pcestdmtOVERHEAD!=null){
							
							Double ohDB= sum.doubleValue()/new Double(labourRate)* new Double(overHeadRate);
							Number oh = nf.parse(ohDB.toString());
							System.out.println("OverHead "+oh.toString());
							pcestdmtOVERHEAD.setEstimateQty(new BigDecimal(oh.toString()));
							pcestdmtOVERHEAD.setEstimateCost(new BigDecimal(oh.toString()));
							pcestdmtDaoRemote.updatePcestdmt(pcestdmtOVERHEAD, webAppName);
						}
						
						BigDecimal sumDMT=pcestdmtDaoRemote.getSUM(list.get(i), webAppName);
						System.out.println(sumDMT);
						Pcesthmt pcesthmt= pcesthmtDaoRemote.findByEstimationNo(list.get(i), webAppName);
						pcesthmt.setStdCost(sumDMT);
						pcesthmtDaoRemote.updatePcesthmt(pcesthmt, webAppName);
						System.out.println(sum+list.get(i)+" "+deptId+" "+sumDMT+" ");
					}else{
					
					}
					
					Pcestdtt pcestdttLABOUR=pcestdttDaoRemote.findBy3PK(list.get(i), deptId, "LABOUR", webAppName);
					Pcestdtt pcestdttOVERHEAD=pcestdttDaoRemote.findBy3PK(list.get(i), deptId, "OVERHEAD", webAppName);
					if (pcestdttLABOUR!=null){
						pcestdttLABOUR.setEstimateQty(sum);
						pcestdttLABOUR.setEstimateCost(sum);
						pcestdttDaoRemote.updatePcestdtt(pcestdttLABOUR, webAppName);
						if (pcestdttOVERHEAD!=null){
							
							Double ohDB= sum.doubleValue()/new Double(labourRate)* new Double(overHeadRate);
							Number oh = nf.parse(ohDB.toString());
							System.out.println("OverHead "+oh.toString());
							pcestdttOVERHEAD.setEstimateQty(new BigDecimal(oh.toString()));
							pcestdttOVERHEAD.setEstimateCost(new BigDecimal(oh.toString()));
							pcestdttDaoRemote.updatePcestdtt(pcestdttOVERHEAD, webAppName);
						}
						
						BigDecimal sumDMT=pcestdttDaoRemote.getSUM(list.get(i), webAppName);
						System.out.println(sumDMT);
						Pcesthtt Pcesthtt= pcesthttDaoRemote.findByEstimationNo(list.get(i), webAppName);
						Pcesthtt.setStdCost(sumDMT);
						pcesthttDaoRemote.updatePcesthtt(Pcesthtt, webAppName);
					}else{
						
					}
					
					
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void updateDmtHmtForLabour(String estimateNo, String deptId, String webAppName)  {
		try{
			NumberFormat nf = NumberFormat.getInstance();
			nf.setGroupingUsed(true);
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			String overHeadRate="162.00";
			String labourRate="0";
			if (estimateNo.substring(7, 10).equals("ENC")){
				labourRate="170.00";
			}else{
				labourRate="241.00";
			}
			
			
			//List<String> list=getAllByType(deptId, deptId+"/ENC/2011/%", webAppName);
			List<Spestlab> list=getAll(estimateNo, deptId, webAppName);
			System.out.println(list);
			
			
			if (!list.isEmpty()){
				for(int i=0;i<list.size();i++){
				//for(int i=0;i<1;i++){
					
					Pcestdmt pcestdmtA0203=pcestdmtDaoRemote.findBy3PK(list.get(i).getId().getEstimateNo(), deptId, "A0203", webAppName);
					if (pcestdmtA0203!=null){
						insertTransport(pcestdmtA0203,list.get(i).getId().getEstimateNo(), deptId, "A0203", webAppName);
					}
					Pcestdmt pcestdmtA0211=pcestdmtDaoRemote.findBy3PK(list.get(i).getId().getEstimateNo(), deptId, "A0211", webAppName);
					if (pcestdmtA0211!=null){
						insertTransport(pcestdmtA0211,list.get(i).getId().getEstimateNo(), deptId, "A0211", webAppName);
					}
					Pcestdmt pcestdmtA0215=pcestdmtDaoRemote.findBy3PK(list.get(i).getId().getEstimateNo(), deptId, "A0215", webAppName);
					if (pcestdmtA0215!=null){
						insertTransport(pcestdmtA0215,list.get(i).getId().getEstimateNo(), deptId, "A0215", webAppName);
					}
					
					
					BigDecimal sum= getSpestlabSUM(list.get(i).getId().getEstimateNo(), deptId, webAppName);
					
					
					
					Pcestdmt pcestdmtLABOUR=pcestdmtDaoRemote.findBy3PK(list.get(i).getId().getEstimateNo(), deptId, "LABOUR", webAppName);
					Pcestdmt pcestdmtOVERHEAD=pcestdmtDaoRemote.findBy3PK(list.get(i).getId().getEstimateNo(), deptId, "OVERHEAD", webAppName);
					if (pcestdmtLABOUR!=null){
						pcestdmtLABOUR.setEstimateQty(sum);
						pcestdmtLABOUR.setEstimateCost(sum);
						pcestdmtDaoRemote.updatePcestdmt(pcestdmtLABOUR, webAppName);
						if (pcestdmtOVERHEAD!=null){
							
							Double ohDB= sum.doubleValue()/new Double(labourRate)* new Double(overHeadRate);
							Number oh = nf.parse(ohDB.toString());
							System.out.println("OverHead "+oh.toString());
							pcestdmtOVERHEAD.setEstimateQty(new BigDecimal(oh.toString()));
							pcestdmtOVERHEAD.setEstimateCost(new BigDecimal(oh.toString()));
							pcestdmtDaoRemote.updatePcestdmt(pcestdmtOVERHEAD, webAppName);
						}
						
						BigDecimal sumDMT=pcestdmtDaoRemote.getSUM(list.get(i).getId().getEstimateNo(), webAppName);
						System.out.println(sumDMT);
						Pcesthmt pcesthmt= pcesthmtDaoRemote.findByEstimationNo(list.get(i).getId().getEstimateNo(), webAppName);
						pcesthmt.setStdCost(sumDMT);
						pcesthmtDaoRemote.updatePcesthmt(pcesthmt, webAppName);
						System.out.println(sum+list.get(i).getId().getEstimateNo()+" "+deptId+" "+sumDMT+" ");
					}else{
					
					}
					
					Pcestdtt pcestdttLABOUR=pcestdttDaoRemote.findBy3PK(list.get(i).getId().getEstimateNo(), deptId, "LABOUR", webAppName);
					Pcestdtt pcestdttOVERHEAD=pcestdttDaoRemote.findBy3PK(list.get(i).getId().getEstimateNo(), deptId, "OVERHEAD", webAppName);
					if (pcestdttLABOUR!=null){
						pcestdttLABOUR.setEstimateQty(sum);
						pcestdttLABOUR.setEstimateCost(sum);
						pcestdttDaoRemote.updatePcestdtt(pcestdttLABOUR, webAppName);
						if (pcestdttOVERHEAD!=null){
							
							Double ohDB= sum.doubleValue()/new Double(labourRate)* new Double(overHeadRate);
							Number oh = nf.parse(ohDB.toString());
							System.out.println("OverHead "+oh.toString());
							pcestdttOVERHEAD.setEstimateQty(new BigDecimal(oh.toString()));
							pcestdttOVERHEAD.setEstimateCost(new BigDecimal(oh.toString()));
							pcestdttDaoRemote.updatePcestdtt(pcestdttOVERHEAD, webAppName);
						}
						
						BigDecimal sumDMT=pcestdttDaoRemote.getSUM(list.get(i).getId().getEstimateNo(), webAppName);
						System.out.println(sumDMT);
						Pcesthtt Pcesthtt= pcesthttDaoRemote.findByEstimationNo(list.get(i).getId().getEstimateNo(), webAppName);
						Pcesthtt.setStdCost(sumDMT);
						pcesthttDaoRemote.updatePcesthtt(Pcesthtt, webAppName);
					}else{
						
					}
					
					
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	@Override
	public void insertTransport(Pcestdmt pcestdmt,String estimateNo, String deptId, String resCd, String webAppName){ 
		//try{
			System.out.println("########################################insertTransport#########################");
			//Pcestdmt pcestdmt=pcestdmtDaoRemote.findBy3PK(estimateNo, deptId, resCd, webAppName);
			//Pcesthtt Pcesthtt= pcesthttDaoRemote.findByEstimationNo(estimateNo, webAppName);
			//Speststd speststd= speststdDaoRemote.findByEstimateNo(estimateNo, webAppName);
			Spserest spserest= spserestDaoRemote.findByApplicationNo(estimateNo, webAppName);
			if (resCd.equals("A0203")){
			System.out.println("gdfgdfgdf");
					if (pcestdmt!=null){
					
					Spestlab spestlab= new Spestlab();
					SpestlabPK spestlabPK = new SpestlabPK();
					spestlabPK.setDeptId(deptId);
					spestlabPK.setEstimateNo(estimateNo);
					spestlabPK.setLabourCode("SM1.2");
					Spestlab spestlab2=findById(spestlabPK, webAppName);
					System.out.println("spestlab2 "+spestlab2);
					if (spestlab2==null){
						spestlab.setId(spestlabPK);
						spestlab.setLabourHours(new BigDecimal(0));
						spestlab.setUnitLabourHrs(new BigDecimal(0));
						spestlab.setItemQty(new BigDecimal(pcestdmt.getEstimateQty().doubleValue()* spserest.getDistanceToSp().doubleValue()));
						spestlab.setActivityDescription("Transportation of 6.0m RC Pole (pole/km)");
						spestlab.setUnitPrice(new BigDecimal(18));
						spestlab.setLabourCost(new BigDecimal(spestlab.getItemQty().doubleValue()*Double.parseDouble("18")));
						spestlab.setCebUnitPrice(new BigDecimal(14.50));
						if (Double.parseDouble(spserest.getDistanceToSp().toString()) <=25){
							//spestlab.setCebLabourCost(new BigDecimal(360));
							spestlab.setCebLabourCost(new BigDecimal(0));
						}else{
							//spestlab.setCebLabourCost(new BigDecimal(spestlab.getItemQty().doubleValue()*Double.parseDouble("14.50")));
							spestlab.setCebLabourCost(new BigDecimal(0));
						}
						System.out.println("create ");
						createSpestlab(spestlab, webAppName);
					}
					
					
				}
					
			}else if (resCd.equals("A0211")){
				if (pcestdmt!=null){
					Spestlab spestlab= new Spestlab();
					SpestlabPK spestlabPK = new SpestlabPK();
					spestlabPK.setDeptId(deptId);
					spestlabPK.setEstimateNo(estimateNo);
					spestlabPK.setLabourCode("SM1.3");
					Spestlab spestlab2=findById(spestlabPK, webAppName);
					if (spestlab2==null){
						spestlab.setId(spestlabPK);
						spestlab.setLabourHours(new BigDecimal(0));
						spestlab.setUnitLabourHrs(new BigDecimal(0));
						spestlab.setItemQty(new BigDecimal(pcestdmt.getEstimateQty().doubleValue()* spserest.getDistanceToSp().doubleValue()));
						spestlab.setActivityDescription("Transportation of 8.3m/9.0m RC Pole (pole/km)");
						spestlab.setUnitPrice(new BigDecimal(20));
						spestlab.setLabourCost(new BigDecimal(spestlab.getItemQty().doubleValue()*Double.parseDouble("20")));
						spestlab.setCebUnitPrice(new BigDecimal(14.50));
						if (Double.parseDouble(spserest.getDistanceToSp().toString()) <=25){
							//spestlab.setCebLabourCost(new BigDecimal(360));
							spestlab.setCebLabourCost(new BigDecimal(0));
						}else{
							//spestlab.setCebLabourCost(new BigDecimal(spestlab.getItemQty().doubleValue()*Double.parseDouble("14.50")));
							spestlab.setCebLabourCost(new BigDecimal(0));
						}
						createSpestlab(spestlab, webAppName);
					}
				}
					
					
			}else if (resCd.equals("A0215")){
				if (pcestdmt!=null){
					Spestlab spestlab= new Spestlab();
					SpestlabPK spestlabPK = new SpestlabPK();
					spestlabPK.setDeptId(deptId);
					spestlabPK.setEstimateNo(estimateNo);
					spestlabPK.setLabourCode("SM1.3");
					Spestlab spestlab2=findById(spestlabPK, webAppName);
					if (spestlab2==null){
						spestlab.setId(spestlabPK);
						spestlab.setLabourHours(new BigDecimal(0));
						spestlab.setUnitLabourHrs(new BigDecimal(0));
						spestlab.setItemQty(new BigDecimal(pcestdmt.getEstimateQty().doubleValue()* spserest.getDistanceToSp().doubleValue()));
						spestlab.setActivityDescription("Transportation of 8.3m/9.0m RC Pole (pole/km)");
						spestlab.setUnitPrice(new BigDecimal(20));
						spestlab.setLabourCost(new BigDecimal(spestlab.getItemQty().doubleValue()*Double.parseDouble("20")));
						spestlab.setCebUnitPrice(new BigDecimal(14.50));
						if (Double.parseDouble(spserest.getDistanceToSp().toString()) <=25){
							//spestlab.setCebLabourCost(new BigDecimal(360));
							spestlab.setCebLabourCost(new BigDecimal(0));
						}else{
							//spestlab.setCebLabourCost(new BigDecimal(spestlab.getItemQty().doubleValue()*Double.parseDouble("14.50")));
							spestlab.setCebLabourCost(new BigDecimal(0));
						}
						createSpestlab(spestlab, webAppName);
					}		
						
				}
				
			}
		//}catch (Exception e) {
		//	e.printStackTrace();
		//}
		
	}
	
	
	@Override
	public void insertTransport(Pcestdtt pcestdtt,String estimateNo, String deptId, String resCd, String webAppName){ 
		System.out.println("########################################insertTransport#########################");
		//Pcestdtt pcestdtt=pcestdttDaoRemote.findBy3PK(estimateNo, deptId, resCd, webAppName);
		//Pcesthtt Pcesthtt= pcesthttDaoRemote.findByEstimationNo(estimateNo, webAppName);
		//Speststd speststd= speststdDaoRemote.findByEstimateNo(estimateNo, webAppName);
		Spserest spserest= spserestDaoRemote.findByApplicationNo(estimateNo, webAppName);
		if (resCd.equals("A0203")){
		System.out.println("gdfgdfgdf");
				if (pcestdtt!=null){
				
				Spestlab spestlab= new Spestlab();
				SpestlabPK spestlabPK = new SpestlabPK();
				spestlabPK.setDeptId(deptId);
				spestlabPK.setEstimateNo(estimateNo);
				spestlabPK.setLabourCode("SM1.2");
				Spestlab spestlab2=findById(spestlabPK, webAppName);
				if (spestlab2==null){
					spestlab.setId(spestlabPK);
					spestlab.setLabourHours(new BigDecimal(0));
					spestlab.setUnitLabourHrs(new BigDecimal(0));
					spestlab.setItemQty(new BigDecimal(pcestdtt.getEstimateQty().doubleValue()* spserest.getDistanceToSp().doubleValue()));
					spestlab.setActivityDescription("Transportation of 6.0m RC Pole (pole/km)");
					spestlab.setUnitPrice(new BigDecimal(18));
					spestlab.setLabourCost(new BigDecimal(spestlab.getItemQty().doubleValue()*Double.parseDouble("18")));
					spestlab.setCebUnitPrice(new BigDecimal(14.50));
					if (Double.parseDouble(spserest.getDistanceToSp().toString()) <=25){
						//spestlab.setCebLabourCost(new BigDecimal(360));
						spestlab.setCebLabourCost(new BigDecimal(0));
					}else{
						//spestlab.setCebLabourCost(new BigDecimal(spestlab.getItemQty().doubleValue()*Double.parseDouble("14.50")));
						spestlab.setCebLabourCost(new BigDecimal(0));
					}
					createSpestlab(spestlab, webAppName);
				}
				
				
			}
				
		}else if (resCd.equals("A0211")){
			if (pcestdtt!=null){
				Spestlab spestlab= new Spestlab();
				SpestlabPK spestlabPK = new SpestlabPK();
				spestlabPK.setDeptId(deptId);
				spestlabPK.setEstimateNo(estimateNo);
				spestlabPK.setLabourCode("SM1.3");
				Spestlab spestlab2=findById(spestlabPK, webAppName);
				if (spestlab2==null){
					spestlab.setId(spestlabPK);
					spestlab.setLabourHours(new BigDecimal(0));
					spestlab.setUnitLabourHrs(new BigDecimal(0));
					spestlab.setItemQty(new BigDecimal(pcestdtt.getEstimateQty().doubleValue()* spserest.getDistanceToSp().doubleValue()));
					spestlab.setActivityDescription("Transportation of 8.3m/9.0m RC Pole (pole/km)");
					spestlab.setUnitPrice(new BigDecimal(20));
					spestlab.setLabourCost(new BigDecimal(spestlab.getItemQty().doubleValue()*Double.parseDouble("20")));
					spestlab.setCebUnitPrice(new BigDecimal(14.50));
					if (Double.parseDouble(spserest.getDistanceToSp().toString()) <=25){
						//spestlab.setCebLabourCost(new BigDecimal(360));
						spestlab.setCebLabourCost(new BigDecimal(0));
					}else{
						//spestlab.setCebLabourCost(new BigDecimal(spestlab.getItemQty().doubleValue()*Double.parseDouble("14.50")));
						spestlab.setCebLabourCost(new BigDecimal(0));
					}
					createSpestlab(spestlab, webAppName);
				}
			}
				
				
		}else if (resCd.equals("A0215")){
			if (pcestdtt!=null){
				Spestlab spestlab= new Spestlab();
				SpestlabPK spestlabPK = new SpestlabPK();
				spestlabPK.setDeptId(deptId);
				spestlabPK.setEstimateNo(estimateNo);
				spestlabPK.setLabourCode("SM1.3");
				Spestlab spestlab2=findById(spestlabPK, webAppName);
				if (spestlab2==null){
					spestlab.setId(spestlabPK);
					spestlab.setLabourHours(new BigDecimal(0));
					spestlab.setUnitLabourHrs(new BigDecimal(0));
					spestlab.setItemQty(new BigDecimal(pcestdtt.getEstimateQty().doubleValue()* spserest.getDistanceToSp().doubleValue()));
					spestlab.setActivityDescription("Transportation of 8.3m/9.0m RC Pole (pole/km)");
					spestlab.setUnitPrice(new BigDecimal(20));
					spestlab.setLabourCost(new BigDecimal(spestlab.getItemQty().doubleValue()*Double.parseDouble("20")));
					spestlab.setCebUnitPrice(new BigDecimal(14.50));
					if (Double.parseDouble(spserest.getDistanceToSp().toString()) <=25){
						//spestlab.setCebLabourCost(new BigDecimal(360));
						spestlab.setCebLabourCost(new BigDecimal(0));
					}else{
						//spestlab.setCebLabourCost(new BigDecimal(spestlab.getItemQty().doubleValue()*Double.parseDouble("14.50")));
						spestlab.setCebLabourCost(new BigDecimal(0));
					}
					createSpestlab(spestlab, webAppName);
				}		
					
			}
			
		}
		
	}
	
	
	@Override
	public void insertTransport(String estimateNo, String deptId, String resCd, String webAppName){ 
		Pcestdmt pcestdmt=pcestdmtDaoRemote.findBy3PK(estimateNo, deptId, resCd, webAppName);
		//Pcesthtt Pcesthtt= pcesthttDaoRemote.findByEstimationNo(estimateNo, webAppName);
		//Speststd speststd= speststdDaoRemote.findByEstimateNo(estimateNo, webAppName);
		Spserest spserest= spserestDaoRemote.findByApplicationNo(estimateNo, webAppName);
		if (resCd.equals("A0203")){
		System.out.println("gdfgdfgdf");
				if (pcestdmt!=null){
				
				Spestlab spestlab= new Spestlab();
				SpestlabPK spestlabPK = new SpestlabPK();
				spestlabPK.setDeptId(deptId);
				spestlabPK.setEstimateNo(estimateNo);
				spestlabPK.setLabourCode("SM1.2");
				Spestlab spestlab2=findById(spestlabPK, webAppName);
				if (spestlab2==null){
					spestlab.setId(spestlabPK);
					spestlab.setLabourHours(new BigDecimal(0));
					spestlab.setUnitLabourHrs(new BigDecimal(0));
					spestlab.setItemQty(new BigDecimal(pcestdmt.getEstimateQty().doubleValue()* spserest.getDistanceToSp().doubleValue()));
					spestlab.setActivityDescription("Transportation of 6.0m RC Pole (pole/km)");
					spestlab.setUnitPrice(new BigDecimal(18));
					spestlab.setLabourCost(new BigDecimal(spestlab.getItemQty().doubleValue()*Double.parseDouble("18")));
					spestlab.setCebUnitPrice(new BigDecimal(14.50));
					if (Double.parseDouble(spserest.getDistanceToSp().toString()) <=25){
						//spestlab.setCebLabourCost(new BigDecimal(360));
						spestlab.setCebLabourCost(new BigDecimal(0));
					}else{
						//spestlab.setCebLabourCost(new BigDecimal(spestlab.getItemQty().doubleValue()*Double.parseDouble("14.50")));
						spestlab.setCebLabourCost(new BigDecimal(0));
					}
					createSpestlab(spestlab, webAppName);
				}
				
				
			}
				
		}else if (resCd.equals("A0211")){
			if (pcestdmt!=null){
				Spestlab spestlab= new Spestlab();
				SpestlabPK spestlabPK = new SpestlabPK();
				spestlabPK.setDeptId(deptId);
				spestlabPK.setEstimateNo(estimateNo);
				spestlabPK.setLabourCode("SM1.3");
				Spestlab spestlab2=findById(spestlabPK, webAppName);
				if (spestlab2==null){
					spestlab.setId(spestlabPK);
					spestlab.setLabourHours(new BigDecimal(0));
					spestlab.setUnitLabourHrs(new BigDecimal(0));
					spestlab.setItemQty(new BigDecimal(pcestdmt.getEstimateQty().doubleValue()* spserest.getDistanceToSp().doubleValue()));
					spestlab.setActivityDescription("Transportation of 8.3m/9.0m RC Pole (pole/km)");
					spestlab.setUnitPrice(new BigDecimal(20));
					spestlab.setLabourCost(new BigDecimal(spestlab.getItemQty().doubleValue()*Double.parseDouble("20")));
					spestlab.setCebUnitPrice(new BigDecimal(14.50));
					if (Double.parseDouble(spserest.getDistanceToSp().toString()) <=25){
						//spestlab.setCebLabourCost(new BigDecimal(360));
						spestlab.setCebLabourCost(new BigDecimal(0));
					}else{
						//spestlab.setCebLabourCost(new BigDecimal(spestlab.getItemQty().doubleValue()*Double.parseDouble("14.50")));
						spestlab.setCebLabourCost(new BigDecimal(0));
					}
					createSpestlab(spestlab, webAppName);
				}
			}
				
				
		}else if (resCd.equals("A0215")){
			if (pcestdmt!=null){
				Spestlab spestlab= new Spestlab();
				SpestlabPK spestlabPK = new SpestlabPK();
				spestlabPK.setDeptId(deptId);
				spestlabPK.setEstimateNo(estimateNo);
				spestlabPK.setLabourCode("SM1.3");
				Spestlab spestlab2=findById(spestlabPK, webAppName);
				if (spestlab2==null){
					spestlab.setId(spestlabPK);
					spestlab.setLabourHours(new BigDecimal(0));
					spestlab.setUnitLabourHrs(new BigDecimal(0));
					spestlab.setItemQty(new BigDecimal(pcestdmt.getEstimateQty().doubleValue()* spserest.getDistanceToSp().doubleValue()));
					spestlab.setActivityDescription("Transportation of 8.3m/9.0m RC Pole (pole/km)");
					spestlab.setUnitPrice(new BigDecimal(20));
					spestlab.setLabourCost(new BigDecimal(spestlab.getItemQty().doubleValue()*Double.parseDouble("20")));
					spestlab.setCebUnitPrice(new BigDecimal(14.50));
					if (Double.parseDouble(spserest.getDistanceToSp().toString()) <=25){
						//spestlab.setCebLabourCost(new BigDecimal(360));
						spestlab.setCebLabourCost(new BigDecimal(0));
					}else{
						//spestlab.setCebLabourCost(new BigDecimal(spestlab.getItemQty().doubleValue()*Double.parseDouble("14.50")));
						spestlab.setCebLabourCost(new BigDecimal(0));
					}
					createSpestlab(spestlab, webAppName);
				}		
					
			}
			
		}
		
	}
	
	@Override
	public void insertTransportOnDMT(String estimateNo, String deptId, String webAppName){
		System.out.println("########################################insertTransportOnSave#########################");
		Pcestdmt pcestdmtA0203=pcestdmtDaoRemote.findBy3PK(estimateNo.trim(), deptId, "A0203", webAppName);
		if (pcestdmtA0203!=null){
			insertTransport(pcestdmtA0203,estimateNo, deptId, "A0203", webAppName);
		}
		Pcestdmt pcestdmtA0211=pcestdmtDaoRemote.findBy3PK(estimateNo.trim(), deptId, "A0211", webAppName);
		System.out.println("pcestdmtA0211 "+pcestdmtA0211);
		if (pcestdmtA0211!=null){
			System.out.println("if pcestdmtA0211 ");
			insertTransport(pcestdmtA0211,estimateNo, deptId, "A0211", webAppName);
		}
		Pcestdmt pcestdmtA0215=pcestdmtDaoRemote.findBy3PK(estimateNo.trim(), deptId, "A0215", webAppName);
		if (pcestdmtA0215!=null){
			insertTransport(pcestdmtA0215,estimateNo, deptId, "A0215", webAppName);
		}
		
	}
	
	@Override
	public void insertTransportOnDTT(String estimateNo, String deptId, String webAppName){
		//System.out.println("########################################insertTransportOnSave#########################");
		Pcestdtt pcestdttA0203=pcestdttDaoRemote.findBy3PK(estimateNo.trim(), deptId, "A0203", webAppName);
		System.out.println("pcestdttA0203 "+pcestdttA0203);
		if (pcestdttA0203!=null){
			insertTransport(pcestdttA0203,estimateNo, deptId, "A0203", webAppName);
		}
		Pcestdtt pcestdttA0211=pcestdttDaoRemote.findBy3PK(estimateNo.trim(), deptId, "A0211", webAppName);
		System.out.println("pcestdttA0211 "+pcestdttA0211);
		if (pcestdttA0211!=null){
			System.out.println("if pcestdttA0211 "+pcestdttA0211);
			insertTransport(pcestdttA0211,estimateNo, deptId, "A0211", webAppName);
		}
		Pcestdtt pcestdttA0215=pcestdttDaoRemote.findBy3PK(estimateNo.trim(), deptId, "A0215", webAppName);
		System.out.println("pcestdttA0215 "+pcestdttA0215);
		if (pcestdttA0215!=null){
			insertTransport(pcestdttA0215,estimateNo, deptId, "A0215", webAppName);
		}
		
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public void updateLabourCode(String labourCode, String webAppName){
		//select SPESTLAB from SPESTLAB where  LABOUR_CODE in ('SM2.4')
		String qryStr = "select a from Spestlab a where a.id.labourCode=:labourCode order by a.id.estimateNo ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("labourCode", labourCode);
		List<Spestlab> list = query.getResultList();
		System.out.println(list.size());
		if (!list.isEmpty()){
			
			for(int i=0;i<list.size();i++){
				System.out.println(list.get(i).getId().getEstimateNo());
				updateContractorLabourCostByEstNo(list.get(i).getId().getEstimateNo(), list.get(i).getId().getDeptId(), webAppName);
				//updateDmtHmtForLabour(list.get(i).getId().getEstimateNo(), list.get(i).getId().getDeptId(), webAppName);
			}
			
		}
	}
	
	

	
	
	
	
	
	
	
	

	

}
