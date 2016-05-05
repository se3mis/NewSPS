package estimate.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import util.emSelect.EmSelector;
import estimate.model.Splbcndt;
import estimate.model.SplbcndtPK;

/**
 * Session Bean implementation class SplbcndtDao
 */
@Stateless
public class SplbcndtDao extends EmSelector implements SplbcndtDaoRemote, SplbcndtDaoLocal {
	

	


    /**
     * Default constructor. 
     */
    public SplbcndtDao() {
        // TODO Auto-generated constructor stub
    }
    
    

	@Override
	public void createSplbcndt(Splbcndt splbcndt, String webAppName) {
		//getEntityManager(webAppName);
		// TODO Auto-generated method stub
		getEntityManager(webAppName).persist(splbcndt);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Splbcndt> getAll(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Splbcndt a where a.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<Splbcndt> list = query.getResultList();
		return list;
	}

	@Override
	public void updateSplbcndt(Splbcndt splbcndt, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(splbcndt);
		
	}

	@Override
	public void removeSplbcndt(Splbcndt splbcndt, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).remove(splbcndt);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");

		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Splbcndt findById(SplbcndtPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Splbcndt a where a.id.deptId = :deptId AND a.id.labourCode = :labourCode";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", id.getDeptId());
		query.setParameter("labourCode", id.getLabourCode());
		List<Splbcndt> list = query.getResultList();
		if (list.isEmpty())
			return null;
		else if (list.size() == 1)
			return list.get(0);
		throw new NonUniqueResultException();

	}

}
