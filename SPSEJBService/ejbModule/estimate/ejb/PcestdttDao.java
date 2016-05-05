package estimate.ejb;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import job.model.Pcestdmt;

import util.common.Constants;
import util.emSelect.EmSelector;

import estimate.model.Pcestdtt;
import estimate.model.PcestdttPK;

/**
 * Session Bean implementation class PcestdttDao
 */
@Stateless
public class PcestdttDao extends EmSelector implements PcestdttDaoRemote, PcestdttDaoLocal {
	

	
    /**
     * Default constructor. 
     */
    public PcestdttDao() {
        // TODO Auto-generated constructor stub
    }

    
    
	@Override
	public void createPcestdtt(Pcestdtt pcestdtt, String webAppName) {
		getEntityManager(webAppName).persist(pcestdtt);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pcestdtt> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr ="select a from Pcestdtt a";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<Pcestdtt> list = query.getResultList();
        return list;
	}

	@Override
	public void updatePcestdtt(Pcestdtt pcestdtt, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(pcestdtt);
		
	}

	@Override
	public void removePcestdtt(Pcestdtt pcestdtt, String webAppName) {
		//getEntityManager(webAppName);
		pcestdtt=getEntityManager(webAppName).merge(pcestdtt);
		getEntityManager(webAppName).remove(pcestdtt);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa");
		
	}

	@Override
	public Pcestdtt findById(PcestdttPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		return getEntityManager(webAppName).find(Pcestdtt.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pcestdtt> findByEstimationNo(String estimateNo, String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT g FROM Pcestdtt g WHERE TRIM(g.id.estimateNo) = :estimateNo AND g.id.deptId = :deptId and g.id.revNo=:revNo order by g.id.resCd";
		//String qryStr = "SELECT g FROM Pcestdtt g WHERE TRIM(g.id.estimateNo) = :estimateNo AND g.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", estimateNo.trim());
		query.setParameter("deptId", deptId);
		query.setParameter("revNo", new Long("1"));
		List<Pcestdtt> list = query.getResultList();		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pcestdtt> findByEstimationNo(String estimateNo, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT g FROM Pcestdtt g WHERE TRIM(g.id.estimateNo) = :estimateNo and g.id.revNo=:revNo order by g.id.resCd";
		//String qryStr = "SELECT g FROM Pcestdtt g WHERE TRIM(g.id.estimateNo) = :estimateNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", estimateNo.trim());
		query.setParameter("revNo", new Long(1));
		List<Pcestdtt> list = query.getResultList();		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pcestdtt findBy3PK(String estimateNo, String deptId, String resCd, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT g FROM Pcestdtt g WHERE TRIM(g.id.estimateNo) = :estimateNo AND g.id.deptId = :deptId AND TRIM(g.id.resCd) =:resCd and g.id.revNo=:revNo";
		//String qryStr = "SELECT g FROM Pcestdtt g WHERE TRIM(g.id.estimateNo) = :estimateNo AND g.id.deptId = :deptId AND TRIM(g.id.resCd) =:resCd";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", estimateNo.trim());
		query.setParameter("deptId", deptId);
		query.setParameter("resCd", resCd);
		query.setParameter("revNo", new Long(1));
		List<Pcestdtt> list = query.getResultList();	
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}
	
	@Override
	public void changeCostCenterNoPcestdtt(String estimateNo,String areaDeptId, String depotDeptId , String webAppName) {
		//getEntityManager(webAppName);
		//System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		String qryStr = "UPDATE Pcestdtt g set g.id.deptId=:depotDeptId WHERE TRIM(g.id.estimateNo)= :estimateNo AND g.id.deptId = :areaDeptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("depotDeptId", depotDeptId);
		query.setParameter("estimateNo", estimateNo.trim());
		query.setParameter("areaDeptId", areaDeptId);
		query.executeUpdate();
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BigDecimal getSUM(String estimateNo, String webAppName) {
		String qryStr2 = "select sum(a.estimateCost) from Pcestdtt a where TRIM(a.id.estimateNo)=:estimateNo ";
		Query query2 = getEntityManager(webAppName).createQuery(qryStr2);
		query2.setParameter("estimateNo", estimateNo.trim());
		BigDecimal stdCost = (BigDecimal) query2.getSingleResult();
		//System.out.println(stdCost);
		return stdCost;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map> getSUMByResType(String estimateNo,String deptId, String webAppName) {
		String qryStr2 = "select new map(sum(a.estimateCost), a.resType) from Pcestdtt a where TRIM(a.id.estimateNo)=:estimateNo and TRIM(a.id.deptId)=:deptId group by a.resType";
		Query query2 = getEntityManager(webAppName).createQuery(qryStr2);
		query2.setParameter("estimateNo", estimateNo.trim());
		query2.setParameter("deptId", deptId);
		BigDecimal stdCost = null;
		List<Map> maplist = query2.getResultList();
		
		return maplist;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BigDecimal getSUMOfMATandMAT_OTHER(String estimateNo, String webAppName) {
		String qryStr2 = "select sum(a.estimateCost) from Pcestdtt a where TRIM(a.id.estimateNo)=:estimateNo and resType=:resType1 or resType=:resType2 ";
		Query query2 = getEntityManager(webAppName).createQuery(qryStr2);
		query2.setParameter("estimateNo", estimateNo.trim());
		query2.setParameter("resType1", Constants.RES_TYPE_MAT_COST);
		query2.setParameter("resType2", Constants.RES_TYPE_MAT_COST_OTHER);
		BigDecimal stdCost = (BigDecimal) query2.getSingleResult();
		//System.out.println(stdCost);
		return stdCost;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Long getRawCountOtherMat(String estimateNo,String deptId,String resCode, String webAppName) {
		String qryStr2 = "select count(*) as count from  Pcestdtt pc where pc.id.estimateNo=:estimateNo and pc.id.deptId=:deptId and pc.id.resCd=:resCode";
		Query query2 = getEntityManager(webAppName).createQuery(qryStr2);
		query2.setParameter("estimateNo", estimateNo.trim());
		query2.setParameter("deptId",deptId);
		query2.setParameter("resCode", resCode);
		Long stdCost = (Long) query2.getSingleResult();
		//System.out.println(stdCost);
		return stdCost;
	}
	@SuppressWarnings("unchecked")
	@Override
	public int updatePcestdttOtherMaterialSum(String estimateNo,String deptId,String resCode,BigDecimal estimateCost, String webAppName){
		String qryStr2 = "update Pcestdtt dt set dt.estimateCost = :estimateCost  where estimate_no=:estimateNo and dept_id=:deptId and res_cd=:resCode";
		Query query2 = getEntityManager(webAppName).createQuery(qryStr2);
		query2.setParameter("estimateNo", estimateNo.trim());
		query2.setParameter("deptId",deptId);
		query2.setParameter("resCode", resCode);
		query2.setParameter("estimateCost", estimateCost);
		int status = query2.executeUpdate();
		//System.out.println(stdCost);
		return status;
	}

	@Override
	public void changeUnitPtrice(String estimateNo, String resCd , String webAppName) {
		//getEntityManager(webAppName);
		//System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		String qryStr = "UPDATE Pcestdtt g set g.unitPrice=1 WHERE TRIM(g.id.estimateNo)= :estimateNo AND TRIM(g.id.resCd) = :resCd AND g.unitPrice<>1";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", estimateNo.trim());
		query.setParameter("resCd", resCd.trim());
		query.executeUpdate();
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pcestdtt> findByEstimationNo(String estimateNo, String deptId,Long revNo, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT g FROM Pcestdtt g WHERE TRIM(g.id.estimateNo) = :estimateNo AND g.id.deptId = :deptId and g.id.revNo=:revNo order by g.id.resCd";
		//String qryStr = "SELECT g FROM Pcestdtt g WHERE TRIM(g.id.estimateNo) = :estimateNo AND g.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", estimateNo.trim());
		query.setParameter("deptId", deptId);
		query.setParameter("revNo", revNo);
		List<Pcestdtt> list = query.getResultList();		
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Pcestdtt findBy3PK(String estimateNo, String deptId, String resCd,Long revNo, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT g FROM Pcestdtt g WHERE TRIM(g.id.estimateNo) = :estimateNo AND g.id.deptId = :deptId AND TRIM(g.id.resCd) =:resCd and g.id.revNo=:revNo";
		//String qryStr = "SELECT g FROM Pcestdtt g WHERE TRIM(g.id.estimateNo) = :estimateNo AND g.id.deptId = :deptId AND TRIM(g.id.resCd) =:resCd";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", estimateNo.trim());
		query.setParameter("deptId", deptId);
		query.setParameter("resCd", resCd);
		query.setParameter("revNo", revNo);
		List<Pcestdtt> list = query.getResultList();	
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}
	@SuppressWarnings("unchecked")
	@Override
	public Pcestdtt getdttByResCD(String estimateNo,String resCD, String webAppName) {
		String qryStr2 = "select a from Pcestdtt a where TRIM(a.id.estimateNo)=:estimateNo and TRIM(a.id.resCd)=:resCD and a.id.revNo=2";
		Query query2 = getEntityManager(webAppName).createQuery(qryStr2);
		query2.setParameter("estimateNo", estimateNo.trim());
		query2.setParameter("resCD", resCD);
		
		List<Pcestdtt> list = query2.getResultList();		
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}
	@Override
	public int updatePcestdttPercentageCost(String estimateNo, String deptId,
			String resCode, BigDecimal estimateCost, BigDecimal estimateQuantity,String webAppName) {
		String qryStr2 = "update Pcestdtt dt set dt.estimateCost = :estimateCost,dt.estimateQty = :estimateQuantity where TRIM(dt.id.estimateNo)=:estimateNo and TRIM(dt.id.deptId)=:deptId and TRIM(dt.id.resCd)=:resCode and dt.id.revNo=2";
		Query query2 = getEntityManager(webAppName).createQuery(qryStr2);
		query2.setParameter("estimateNo", estimateNo.trim());
		query2.setParameter("deptId",deptId.trim());
		query2.setParameter("resCode", resCode);
		query2.setParameter("estimateCost", estimateCost);
		query2.setParameter("estimateQuantity", estimateQuantity);
		int status = query2.executeUpdate();
		//System.out.println(stdCost);
		return status;
	}
}
