package estimate.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import util.emSelect.EmSelector;


import estimate.model.Pcesthtt;
import estimate.model.Speststd;
import estimate.model.SpeststdPK;

/**
 * Session Bean implementation class SpeststdDao
 */
@Stateless
public class SpeststdDao extends EmSelector implements SpeststdDaoRemote, SpeststdDaoLocal {
		
    /**
     * Default constructor. 
     */
    public SpeststdDao() {
        // TODO Auto-generated constructor stub
    }
    
    
	@Override
	public void createSpeststd(Speststd speststd, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(speststd);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Speststd> getAll(String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Speststd a";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<Speststd> list = query.getResultList();
        return list;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Speststd> getAll(String deptId, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Speststd a where a.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<Speststd> list = query.getResultList();
        
        return list;

	}

	@Override
	public void updateSpeststd(Speststd speststd, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(speststd);
		
	}

	@Override
	public void removeSpeststd(Speststd speststd, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		speststd=getEntityManager(webAppName).merge(speststd);
		getEntityManager(webAppName).remove(speststd);
		
	}

	@Override
	public void removeAll(String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Speststd findById(SpeststdPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "SELECT g FROM Speststd g WHERE TRIM(g.id.estimateNo) = :estimateNo AND g.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", id.getEstimateNo().trim());
		query.setParameter("deptId", id.getDeptId());
		List<Speststd> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Speststd findByEstimateNo(String estimateNo, String webAppName){
		//getEntityManager(webAppName);
		String qryStr = "SELECT g FROM Speststd g WHERE TRIM(g.id.estimateNo) = :estimateNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", estimateNo.trim());
		List<Speststd> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
		
	}

}
