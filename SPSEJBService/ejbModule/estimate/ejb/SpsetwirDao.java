package estimate.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import util.emSelect.EmSelector;

import estimate.model.Spsetwir;
import estimate.model.SpsetwirPK;

/**
 * Session Bean implementation class SpsetwirDao
 */
@Stateless
public class SpsetwirDao extends EmSelector implements SpsetwirDaoRemote, SpsetwirDaoLocal {
	
    /**
     * Default constructor. 
     */
    public SpsetwirDao() {
        // TODO Auto-generated constructor stub
    }
    
    

	@Override
	public void createSpsetwir(Spsetwir spsetwir, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(spsetwir);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spsetwir> getAll(String deptId, String webAppName) { 
		//getEntityManager(webAppName);
		String qryStr = "select a from Spsetwir a where a.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<Spsetwir> list = query.getResultList();
		return list;
	}

	@Override
	public void updateSpsetwir(Spsetwir spsetwir, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(spsetwir);
		
	}

	@Override
	public void removeSpsetwir(Spsetwir spsetwir, String webAppName) {
		//getEntityManager(webAppName);
		spsetwir=getEntityManager(webAppName).merge(spsetwir);
		getEntityManager(webAppName).remove(spsetwir);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Spsetwir findById(SpsetwirPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spsetwir a where a.id.applicationNo = :applicationNo AND  a.id.deptId = :deptId AND a.id.matCd= :matCd ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationNo", id.getApplicationNo());
		query.setParameter("deptId", id.getDeptId());
		query.setParameter("matCd", id.getMatCd());
		List<Spsetwir> list = query.getResultList();
        if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spsetwir> getWireList(String applicationNo, String deptId, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spsetwir a where a.id.applicationNo = :applicationNo AND  a.id.deptId = :deptId ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationNo", applicationNo);
		query.setParameter("deptId", deptId);
		List<Spsetwir> list = query.getResultList();
        return list;
        
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spsetwir> getWireList(String applicationNo, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spsetwir a where a.id.applicationNo = :applicationNo ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationNo", applicationNo);
		List<Spsetwir> list = query.getResultList();
        return list;
        
	}

}
