package estimate.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.emSelect.EmSelector;
import estimate.model.Spsetsty;
import estimate.model.SpsetstyPK;

/**
 * Session Bean implementation class SpsetstyDao
 */
@Stateless
public class SpsetstyDao extends EmSelector implements SpsetstyDaoRemote, SpsetstyDaoLocal {
	
	//@Resource
	//private SessionContext context;

    /**
     * Default constructor. 
     */
    public SpsetstyDao() {
        // TODO Auto-generated constructor stub
    }
    
    

	@Override
	public void createSpsetsty(Spsetsty Spsetsty, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(Spsetsty);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spsetsty> getAll(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spsetsty a where a.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<Spsetsty> list = query.getResultList();
    
    return list;

	}

	@Override
	public void updateSpsetsty(Spsetsty Spsetsty, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(Spsetsty);
		
	}

	@Override
	public void removeSpsetsty(Spsetsty Spsetsty, String webAppName) {
		//getEntityManager(webAppName);
		Spsetsty=getEntityManager(webAppName).merge(Spsetsty);
		getEntityManager(webAppName).remove(Spsetsty);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Spsetsty findById(SpsetstyPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spsetsty a where a.id.applicationNo = :applicationNo AND  a.id.deptId = :deptId AND TRIM(a.id.matCd)= :matCd ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationNo", id.getApplicationNo());
		query.setParameter("deptId", id.getDeptId());
		query.setParameter("matCd", id.getMatCd());
		List<Spsetsty> list = query.getResultList();
        if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spsetsty> getStayList(String applicationNo, String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spsetsty a where a.id.applicationNo = :applicationNo AND  a.id.deptId = :deptId ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationNo", applicationNo);
		query.setParameter("deptId", deptId);
		List<Spsetsty> list = query.getResultList();
        return list;
        
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spsetsty> getStayList(String applicationNo, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spsetsty a where a.id.applicationNo = :applicationNo  ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationNo", applicationNo);
		List<Spsetsty> list = query.getResultList();
        return list;
        
	}

}
