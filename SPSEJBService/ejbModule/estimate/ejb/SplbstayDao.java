package estimate.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import estimate.model.Splbstay;
import estimate.model.SplbstayPK;

import util.emSelect.EmSelector;

/**
 * Session Bean implementation class SplbstayDao
 */
@Stateless
public class SplbstayDao extends EmSelector implements SplbstayDaoRemote, SplbstayDaoLocal {

    /**
     * Default constructor. 
     */
    public SplbstayDao() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void createSplbstay(Splbstay splbstay, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).persist(splbstay);
    		
    	}

    	@SuppressWarnings("unchecked")
    	@Override
    	public List<Splbstay> getAll(String deptId, String webAppName) {
    		//getEntityManager(webAppName);
    		String qryStr = "select a from Splbstay a where a.id.deptId=:deptId";
    		Query query = getEntityManager(webAppName).createQuery(qryStr);
    		query.setParameter("deptId", deptId);
    		List<Splbstay> list = query.getResultList();
    		return list;

    	}

    	@Override
    	public void updateSplbstay(Splbstay splbstay, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).merge(splbstay);
    		
    	}

    	@Override
    	public void removeSplbstay(Splbstay splbstay, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).remove(splbstay);
    		
    	}

    	@Override
    	public void removeAll(String webAppName) {
    		//getEntityManager(webAppName);
    		throw new UnsupportedOperationException("Not supported yet.");

    		
    	}

    	@SuppressWarnings("unchecked")
    	@Override
    	public Splbstay findById(SplbstayPK id, String webAppName) throws PersistenceException {
    		//getEntityManager(webAppName);
    		String qryStr = "select a from Splbstay a where a.id.deptId = :deptId AND a.id.labourCode = :labourCode";
    		Query query = getEntityManager(webAppName).createQuery(qryStr);
    		query.setParameter("deptId", id.getDeptId());
    		query.setParameter("labourCode", id.getLabourCode());
    		List<Splbstay> list = query.getResultList();
    		if (list.isEmpty())
    			return null;
    		else if (list.size() == 1)
    			return list.get(0);
    		throw new NonUniqueResultException();

    	}

	

}
