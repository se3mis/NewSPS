package job.ejb;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import util.common.Constants;
import util.emSelect.EmSelector;
import job.model.Pcestdmt;
import job.model.PcestdmtPK;
import job.model.Pcesthmt;


/**
 * Session Bean implementation class PcestdmtDao 
 */
@Stateless
public class PcestdmtDao extends EmSelector implements PcestdmtDaoRemote, PcestdmtDaoLocal {
	
    /**
     * Default constructor. 
     */
    public PcestdmtDao() {
        // TODO Auto-generated constructor stub
    }
    
   

	@Override
	public void createPcestdmt(Pcestdmt pcestdmt, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(pcestdmt);
		
	}

	@Override
	public void updatePcestdmt(Pcestdmt pcestdmt, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(pcestdmt);
		
	}
	
	@Override
	public void updateRevNo(Pcestdmt pcestdmt, Long newRevNo, String webAppName) {
		//getEntityManager(webAppName);
		String qryString="UPDATE Pcestdmt a  SET a.id.revNo =:newRevNo WHERE TRIM(a.id.estimateNo)=:estimateNo AND a.id.deptId =:deptId AND a.id.revNo =:revNo";
		Query query = getEntityManager(webAppName).createQuery(qryString);
		query.setParameter("newRevNo", newRevNo);
		query.setParameter("estimateNo", pcestdmt.getId().getEstimateNo().trim());
		query.setParameter("deptId", pcestdmt.getId().getDeptId());
		query.setParameter("revNo", pcestdmt.getId().getRevNo());
        query.executeUpdate();
				
	}

	@Override
	public void removePcestdmt(Pcestdmt pcestdmt, String webAppName) {
		//getEntityManager(webAppName);
		pcestdmt=getEntityManager(webAppName).merge(pcestdmt);
		getEntityManager(webAppName).remove(pcestdmt);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pcestdmt> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select p from Pcestdmt p";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<Pcestdmt> list = query.getResultList();
        return list;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pcestdmt> getAll(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Pcestdmt a WHERE a.id.deptId =:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<Pcestdmt> list = query.getResultList();
        return list;

	}

	@Override
	public Pcestdmt findById(PcestdmtPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		return getEntityManager(webAppName).find(Pcestdmt.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pcestdmt> findByEstimationNo(String estimateNo, String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT g FROM Pcestdmt g WHERE TRIM(g.id.estimateNo) = :estimateNo AND g.id.deptId = :deptId order by g.id.resCd";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", estimateNo.trim());
		query.setParameter("deptId", deptId);
		List<Pcestdmt> list = query.getResultList();		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pcestdmt> findByEstimationNo(String estimateNo, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT g FROM Pcestdmt g WHERE TRIM(g.id.estimateNo) = :estimateNo ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", estimateNo.trim());
		List<Pcestdmt> list = query.getResultList();		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pcestdmt findByJobNo(String jobNo, String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT g FROM Pcestdmt g WHERE TRIM(g.projectNo) = :jobNo AND g.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("jobNo", jobNo);
		query.setParameter("deptId", deptId);
		List<Pcestdmt> list = query.getResultList();		
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();

	}
	
	
	

	@Override
	public List<String> findJobNoList(String deptId, BigDecimal status, String webAppName) {
		//getEntityManager(webAppName);
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pcestdmt findBy3PK(String estimateNo, String deptId, String resCd, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT g FROM Pcestdmt g WHERE TRIM(g.id.estimateNo) = :estimateNo AND g.id.deptId = :deptId AND TRIM(g.id.resCd) =:resCd";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", estimateNo.trim());
		query.setParameter("deptId", deptId);
		query.setParameter("resCd", resCd);
		List<Pcestdmt> list = query.getResultList();	
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BigDecimal getSUM(String estimateNo, String webAppName) {
		String qryStr2 = "select sum(a.estimateCost) from Pcestdmt a where TRIM(a.id.estimateNo)=:estimateNo ";
		Query query2 = getEntityManager(webAppName).createQuery(qryStr2);
		query2.setParameter("estimateNo", estimateNo.trim());
		BigDecimal stdCost = (BigDecimal) query2.getSingleResult();
		//System.out.println(stdCost);
		return stdCost;
	}
	
	@Override
	public void changeUnitPtrice(String estimateNo, String resCd , String webAppName) {
		//getEntityManager(webAppName);
		//System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		String qryStr = "UPDATE Pcestdmt g set g.unitPrice=1 WHERE TRIM(g.id.estimateNo)= :estimateNo AND TRIM(g.id.resCd) = :resCd AND g.unitPrice<>1";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", estimateNo.trim());
		query.setParameter("resCd", resCd.trim());
		query.executeUpdate();
		
	}
	 

	@SuppressWarnings("unchecked")
	@Override
	public List<Map> getSUMByResType(String estimateNo,String deptId, String webAppName) {
		String qryStr2 = "select new map(sum(a.estimateCost), a.resType) from Pcestdmt a where TRIM(a.id.estimateNo)=:estimateNo and TRIM(a.id.deptId)=:deptId group by a.resType";
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
		String qryStr2 = "select sum(a.estimateCost) from Pcestdmt a where TRIM(a.id.estimateNo)=:estimateNo and resType=:resType1 or resType=:resType2 ";
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
		String qryStr2 = "select count(*) as count from  Pcestdmt pc where pc.id.estimateNo=:estimateNo and pc.id.deptId=:deptId and pc.id.resCd=:resCode";
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
	public int updatePcestdmtOtherMaterialSum(String estimateNo,String deptId,String resCode,BigDecimal estimateCost, String webAppName){
		String qryStr2 = "update Pcestdmt dt set dt.estimateCost = :estimateCost  where estimate_no=:estimateNo and dept_id=:deptId and res_cd=:resCode";
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
	public int updatePcestdmtPercentageCost(String estimateNo, String deptId,
			String resCode, BigDecimal estimateCost, BigDecimal estimateQuantity, String webAppName) {
		String qryStr2 = "update Pcestdmt dt set dt.estimateCost = :estimateCost,dt.estimateQty = :estimateQuantity  where TRIM(dt.id.estimateNo)=:estimateNo and TRIM(dt.id.deptId)=:deptId and TRIM(dt.id.resCd)=:resCD";
		Query query2 = getEntityManager(webAppName).createQuery(qryStr2);
		query2.setParameter("estimateNo", estimateNo.trim());
		query2.setParameter("deptId",deptId.trim());
		query2.setParameter("resCD", resCode);
		query2.setParameter("estimateCost", estimateCost);
		query2.setParameter("estimateQuantity", estimateQuantity);
		int status = query2.executeUpdate();
		//System.out.println(stdCost);
		return status;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Pcestdmt getdmtByResCD(String estimateNo,String resCD, String webAppName) {
		String qryStr2 = "select a from Pcestdmt a where TRIM(a.id.estimateNo)=:estimateNo and TRIM(a.id.resCd)=:resCD ";
		Query query2 = getEntityManager(webAppName).createQuery(qryStr2);
		query2.setParameter("estimateNo", estimateNo.trim());
		query2.setParameter("resCD", resCD);
		
		List<Pcestdmt> list = query2.getResultList();		
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}
	
	
}
