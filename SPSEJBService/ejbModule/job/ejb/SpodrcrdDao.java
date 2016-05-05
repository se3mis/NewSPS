package job.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import util.emSelect.EmSelector;

import job.model.Spodrcrd;
import job.model.SpodrcrdPK;

/**
 * Session Bean implementation class SpodrcrdDao
 */
@Stateless
public class SpodrcrdDao extends EmSelector implements SpodrcrdDaoRemote, SpodrcrdDaoLocal {

	
    /**
     * Default constructor. 
     */
    public SpodrcrdDao() {
        // TODO Auto-generated constructor stub
    }
    
    
	@Override
	public void createSpodrcrd(Spodrcrd spodrcrd, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(spodrcrd);
		
	}

	@Override
	public void updateSpodrcrd(Spodrcrd spodrcrd, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(spodrcrd);
		
	}

	@Override
	public void removeSpodrcrd(Spodrcrd spodrcrd, String webAppName) {
		//getEntityManager(webAppName);
		spodrcrd=getEntityManager(webAppName).merge(spodrcrd);
		getEntityManager(webAppName).remove(spodrcrd);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spodrcrd> getAll(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spodrcrd a WHERE a.id.deptId =:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<Spodrcrd> list = query.getResultList();
        return list;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Spodrcrd findById(SpodrcrdPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spodrcrd a WHERE TRIM(a.id.projectNo)=:jobNo AND a.id.deptId =:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("jobNo", id.getProjectNo());
		query.setParameter("deptId", id.getDeptId());
		List<Spodrcrd> list = query.getResultList();		
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Spodrcrd findByJobNo(String jobNo, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spodrcrd a WHERE TRIM(a.id.projectNo)=:jobNo ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("jobNo", jobNo);
		List<Spodrcrd> list = query.getResultList();		
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
		
	}

}
