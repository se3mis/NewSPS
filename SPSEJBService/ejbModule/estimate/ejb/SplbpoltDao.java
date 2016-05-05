package estimate.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import estimate.model.Splbpolt;
import estimate.model.SplbpoltPK;

import util.emSelect.EmSelector;

/**
 * Session Bean implementation class SplbpoltDao
 */
@Stateless
public class SplbpoltDao extends EmSelector implements SplbpoltDaoRemote, SplbpoltDaoLocal {

    /**
     * Default constructor. 
     */
    public SplbpoltDao() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void createSplbpolt(Splbpolt splbpolt, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).persist(splbpolt);
    		
    	}

    	@SuppressWarnings("unchecked")
    	@Override
    	public List<Splbpolt> getAll(String deptId, String webAppName) {
    		//getEntityManager(webAppName);
    		String qryStr = "select a from Splbpolt a where a.id.deptId=:deptId";
    		Query query = getEntityManager(webAppName).createQuery(qryStr);
    		query.setParameter("deptId", deptId);
    		List<Splbpolt> list = query.getResultList();
    		return list;

    	}

    	@Override
    	public void updateSplbpolt(Splbpolt splbpolt, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).merge(splbpolt);
    		
    	}

    	@Override
    	public void removeSplbpolt(Splbpolt splbpolt, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).remove(splbpolt);
    		
    	}

    	@Override
    	public void removeAll(String webAppName) {
    		//getEntityManager(webAppName);
    		throw new UnsupportedOperationException("Not supported yet.");

    		
    	}

    	@SuppressWarnings("unchecked")
    	@Override
    	public Splbpolt findById(SplbpoltPK id, String webAppName) throws PersistenceException {
    		//getEntityManager(webAppName);
    		String qryStr = "select a from Splbpolt a where a.id.deptId = :deptId AND a.id.labourCode = :labourCode";
    		Query query = getEntityManager(webAppName).createQuery(qryStr);
    		query.setParameter("deptId", id.getDeptId());
    		query.setParameter("labourCode", id.getLabourCode());
    		List<Splbpolt> list = query.getResultList();
    		if (list.isEmpty())
    			return null;
    		else if (list.size() == 1)
    			return list.get(0);
    		throw new NonUniqueResultException();

    	}
    

}
