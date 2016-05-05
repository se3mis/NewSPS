package estimate.ejb;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.NonUniqueResultException;
import util.emSelect.EmSelector;
import estimate.model.Spsetpol;
import estimate.model.SpsetpolPK;

/**
 * Session Bean implementation class SpestsmtDao
 */
@Stateless
public class SpsetpolDao extends EmSelector implements SpsetpolDaoRemote, SpsetpolDaoLocal {
	
	@Resource
	private SessionContext context;
    /**
     * Default constructor. 
     */
    public SpsetpolDao() {
        // TODO Auto-generated constructor stub
    }
    
    

	@Override
	public void createSpsetpol(Spsetpol spsetpol, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(spsetpol);
		
	}
	
	@Override
	public void createSpsetpolList(List<Spsetpol> list, String webAppName) {
		//getEntityManager(webAppName);
		try{
			for(int i=0; i<=list.size()-1; i++){
				getEntityManager(webAppName).persist(list.get(i));
			}
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spsetpol> getAll(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spsetpol a  where a.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<Spsetpol> list = query.getResultList();
        return list;
	}

	@Override
	public void updateSpsetpol(Spsetpol spsetpol, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(spsetpol);
	}

	@Override
	public void removeSpsetpol(Spsetpol spsetpol, String webAppName) {
		//getEntityManager(webAppName);
		spsetpol=getEntityManager(webAppName).merge(spsetpol);
		getEntityManager(webAppName).remove(spsetpol);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Spsetpol findById(SpsetpolPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spsetpol a where a.id.applicationNo = :applicationNo AND  a.id.deptId = :deptId  AND a.id.pointType=:pointType AND a.id.poleType=:poleType AND a.id.fromConductor=:fromConductor AND a.id.toConductor=:toConductor AND  TRIM(a.id.matCd)= :matCd ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationNo", id.getApplicationNo());
		query.setParameter("deptId", id.getDeptId());
		query.setParameter("pointType", id.getPointType());
		query.setParameter("poleType", id.getPoleType());
		query.setParameter("fromConductor", id.getFromConductor());
		query.setParameter("toConductor", id.getToConductor());
		query.setParameter("matCd", id.getMatCd());
		List<Spsetpol> list = query.getResultList();
        if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spsetpol> getPoleList(String applicationNo, String deptId, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spsetpol a where a.id.applicationNo = :applicationNo AND  a.id.deptId = :deptId ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationNo", applicationNo);
		query.setParameter("deptId", deptId);
		List<Spsetpol> list = query.getResultList();
        return list;
        
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spsetpol> getPoleList(String applicationNo, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spsetpol a where a.id.applicationNo = :applicationNo ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationNo", applicationNo);
		List<Spsetpol> list = query.getResultList();
        return list;
        
	}
	
	

}
