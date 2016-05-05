package estimate.ejb;



import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import piv.model.TempTb;


import util.emSelect.EmSelector;


import estimate.model.Pcestdtt;
import estimate.model.Pegschdmt;

/**
 * Session Bean implementation class PegschdmtDao
 */
@Stateless
public class PegschdmtDao extends EmSelector implements PegschdmtDaoRemote, PegschdmtDaoLocal {


	@Override
	public void createPegschdmt(Pegschdmt pegschdmt, String webAppName) throws PersistenceException{
		getEntityManager(webAppName).persist(pegschdmt);
		
	}

	@Override
	public void updatePegschdmt(Pegschdmt pegschdmt, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "UPDATE Pegschdmt g SET g.noOfItem = :noOfItem,g.nodeDes=:nodeDes WHERE TRIM(g.id.estimateNo) = :estimateNo AND TRIM(g.id.deptId) = :deptId  AND TRIM(g.id.nodeId) = :nodeId ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", pegschdmt.getId().getEstimateNo().trim());
		query.setParameter("nodeId", pegschdmt.getId().getNodeId().trim());
		query.setParameter("deptId",pegschdmt.getId().getDeptId().trim());
		query.setParameter("noOfItem", pegschdmt.getNoOfItem());
		query.setParameter("nodeDes", pegschdmt.getNodeDes());
		
	
		query.executeUpdate();		
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Pegschdmt> findByEstimationNo(String estNo,String deptId, String webAppName){
		String qryStr = "SELECT g FROM Pegschdmt g WHERE TRIM(id.estimateNo) = :estimateNo AND TRIM(id.deptId) = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", estNo.trim());
		query.setParameter("deptId", deptId);
	
		List<Pegschdmt> list = query.getResultList();		
		if (list.isEmpty())
			return null;
        else 
        	return list;
       
	}
	
	@Override
	public void deletePegschdmt(Pegschdmt pegschdmt, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "DELETE Pegschdmt g WHERE TRIM(g.id.estimateNo) = :estimateNo AND TRIM(g.id.deptId) = :deptId ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", pegschdmt.getId().getEstimateNo().trim());
		//query.setParameter("nodeId", pegschdmt.getId().getNodeId().trim());
		query.setParameter("deptId",pegschdmt.getId().getDeptId().trim());
		//query.setParameter("noOfItem", pegschdmt.getNoOfItem());
		//query.setParameter("nodeDes", pegschdmt.getNodeDes());
		
	
		query.executeUpdate();		
		
	}
	
	
}
