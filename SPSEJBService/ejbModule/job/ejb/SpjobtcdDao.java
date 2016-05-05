package job.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.emSelect.EmSelector;
import job.model.Spjobtcd;

/**
 * Session Bean implementation class Spjobtcd
 */
@Stateless
public class SpjobtcdDao extends EmSelector implements SpjobtcdDaoRemote, SpjobtcdDaoLocal {
	
	/**
     * Default constructor. 
     */
    public SpjobtcdDao() {
        // TODO Auto-generated constructor stub
    }

    
    
    
	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa");
		
	}

	@Override
	public void createSpjobtcd(job.model.Spjobtcd spjobtcd, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(spjobtcd);
		
	}

	@Override
	public void updateSpjobtcd(job.model.Spjobtcd spjobtcd, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(spjobtcd);
		
	}

	@Override
	public void removeSpjobtcd(job.model.Spjobtcd spjobtcd, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).remove(spjobtcd);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spjobtcd> getAll(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spjobtcd a WHERE a.id.deptId =:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<Spjobtcd> list = query.getResultList();
	    return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Spjobtcd findById(String jobNo, String deptId, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spjobtcd a WHERE TRIM(a.id.projectNo)=:jobNo AND a.id.deptId =:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("jobNo", jobNo);
		List<Spjobtcd> list = query.getResultList();
		if (list.isEmpty())
			return null;
	    else if (list.size() == 1)
	       	return list.get(0);
	    throw new NonUniqueResultException();
	}

}
