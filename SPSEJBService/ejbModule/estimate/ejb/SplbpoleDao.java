package estimate.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import util.emSelect.EmSelector;

import estimate.model.Splbpole;
import estimate.model.SplbpolePK;

/**
 * Session Bean implementation class SplbpoleDao
 */
@Stateless
public class SplbpoleDao extends EmSelector  implements SplbpoleDaoRemote, SplbpoleDaoLocal {
	

	
    /**
     * Default constructor. 
     */
    public SplbpoleDao() {
        // TODO Auto-generated constructor stub
    }

    
    
	@Override
	public void createSplbpole(Splbpole splbpole, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(splbpole);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Splbpole> getAll(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Splbpole a where a.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<Splbpole> list = query.getResultList();
		return list;

	}

	@Override
	public void updateSplbpole(Splbpole splbpole, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(splbpole);
		
	}

	@Override
	public void removeSplbpole(Splbpole splbpole, String webAppName) {
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
	public Splbpole findById(SplbpolePK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Splbpole a where a.id.deptId = :deptId AND a.id.labourCode = :labourCode";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", id.getDeptId());
		query.setParameter("labourCode", id.getLabourCode());
		List<Splbpole> list = query.getResultList();
		if (list.isEmpty())
			return null;
		else if (list.size() == 1)
			return list.get(0);
		throw new NonUniqueResultException();

	}

}
