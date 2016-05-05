package estimate.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.emSelect.EmSelector;


import estimate.model.Spsetstu;
import estimate.model.SpsetstuPK;

/**
 * Session Bean implementation class SpsetstuDao
 */
@Stateless
public class SpsetstuDao extends EmSelector implements SpsetstuDaoRemote, SpsetstuDaoLocal {
	
    /**
     * Default constructor. 
     */
    public SpsetstuDao() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void createSpsetstu(Spsetstu Spsetstu, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(Spsetstu);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spsetstu> getAll(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spsetstu a where a.id.deptId=:deptId ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<Spsetstu> list = query.getResultList();
        return list;
	}

	@Override
	public void updateSpsetstu(Spsetstu spsetstu, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(spsetstu);
		
	}

	@Override
	public void removeSpsetstu(Spsetstu spsetstu, String webAppName) {
		//getEntityManager(webAppName);
		spsetstu=getEntityManager(webAppName).merge(spsetstu);
		getEntityManager(webAppName).remove(spsetstu);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Spsetstu findById(SpsetstuPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spsetstu a where a.id.applicationNo = :applicationNo AND  a.id.deptId = :deptId AND TRIM(a.id.matCd)= :matCd ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationNo", id.getApplicationNo());
		query.setParameter("deptId", id.getDeptId());
		query.setParameter("matCd", id.getMatCd());
		List<Spsetstu> list = query.getResultList();
        if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spsetstu> getStrutsList(String applicationNo, String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spsetstu a where a.id.applicationNo = :applicationNo AND  a.id.deptId = :deptId ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationNo", applicationNo);
		query.setParameter("deptId", deptId);
		List<Spsetstu> list = query.getResultList();
        return list;
        
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spsetstu> getStrutsList(String applicationNo, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spsetstu a where a.id.applicationNo = :applicationNo ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationNo", applicationNo);
		List<Spsetstu> list = query.getResultList();
        return list;
        
	}

}
