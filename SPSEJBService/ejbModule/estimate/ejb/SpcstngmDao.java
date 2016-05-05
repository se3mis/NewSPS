package estimate.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import util.emSelect.EmSelector;

import estimate.model.Spcstngm;
import estimate.model.SpcstngmPK;

/**
 * Session Bean implementation class SpcstngmDao
 */
@Stateless
public class SpcstngmDao extends EmSelector implements SpcstngmDaoRemote, SpcstngmDaoLocal {
	

	
	/**
     * Default constructor. 
     */
    public SpcstngmDao() {
        // TODO Auto-generated constructor stub
    }
    
    

	@Override
	public void createSpcstngm(Spcstngm spcstngm, String webAppName) {
		getEntityManager(webAppName).persist(spcstngm);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spcstngm> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spcstngm a";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<Spcstngm> list = query.getResultList();
        return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spcstngm> getAll(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spcstngm a where a.id.deptId =:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        query.setParameter("deptId", deptId);
		List<Spcstngm> list = query.getResultList();
        return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override 
	public Spcstngm getSpcstngm(Long phase, Long connectionType,String  tariffCatCode,Long  length, String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT s from Spcstngm s where s.id.phase =:phase and s.id.connectionType =:connectionType and s.id.tariffCatCode =:tariffCatCode and s.id.fromLength <= :length and s.id.toLength >= :length and s.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        query.setParameter("phase", phase);
        query.setParameter("connectionType", connectionType);
        query.setParameter("tariffCatCode", tariffCatCode);
        query.setParameter("length", length);
        query.setParameter("deptId", deptId);
		List<Spcstngm> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}

	@Override
	public void updateSpcstngm(Spcstngm spcstngm, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(spcstngm);
		
	}

	@Override
	public void removeSpcstngm(Spcstngm spcstngm, String webAppName) {
		//getEntityManager(webAppName);
		spcstngm=getEntityManager(webAppName).merge(spcstngm);
		getEntityManager(webAppName).remove(spcstngm);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@Override
	public Spcstngm findById(SpcstngmPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		return getEntityManager(webAppName).find(Spcstngm.class, id);
	}

}
