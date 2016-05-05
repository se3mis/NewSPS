package estimate.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;



import util.emSelect.EmSelector;

import estimate.model.Splbstrt;
import estimate.model.SplbstrtPK;

/**
 * Session Bean implementation class SplbstrtDao
 */
@Stateless
public class SplbstrtDao extends EmSelector implements SplbstrtDaoRemote, SplbstrtDaoLocal {

    /**
     * Default constructor. 
     */
    public SplbstrtDao() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void createSplbstrt(Splbstrt splbpole, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).persist(splbpole);
    		
    	}

    	@SuppressWarnings("unchecked")
    	@Override
    	public List<Splbstrt> getAll(String deptId, String webAppName) {
    		//getEntityManager(webAppName);
    		String qryStr = "select a from Splbstrt a where a.id.deptId=:deptId";
    		Query query = getEntityManager(webAppName).createQuery(qryStr);
    		query.setParameter("deptId", deptId);
    		List<Splbstrt> list = query.getResultList();
    		return list;

    	}

    	@Override
    	public void updateSplbstrt(Splbstrt splbpole, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).merge(splbpole);
    		
    	}

    	@Override
    	public void removeSplbstrt(Splbstrt splbpole, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).remove(splbpole);
    		
    	}

    	@Override
    	public void removeAll(String webAppName) {
    		//getEntityManager(webAppName);
    		throw new UnsupportedOperationException("Not supported yet.");

    		
    	}

    	@SuppressWarnings("unchecked")
    	@Override
    	public Splbstrt findById(SplbstrtPK id, String webAppName) throws PersistenceException {
    		//getEntityManager(webAppName);
    		String qryStr = "select a from Splbstrt a where a.id.deptId = :deptId AND a.id.labourCode = :labourCode";
    		Query query = getEntityManager(webAppName).createQuery(qryStr);
    		query.setParameter("deptId", id.getDeptId());
    		query.setParameter("labourCode", id.getLabourCode());
    		List<Splbstrt> list = query.getResultList();
    		if (list.isEmpty())
    			return null;
    		else if (list.size() == 1)
    			return list.get(0);
    		throw new NonUniqueResultException();

    	}

}
