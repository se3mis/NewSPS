package estimate.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import util.emSelect.EmSelector;
import estimate.model.Spestmtm;
import estimate.model.SpestmtmPK;

/**
 * Session Bean implementation class SpestmtmDao
 */
@Stateless
public class SpestmtmDao extends EmSelector implements SpestmtmDaoRemote, SpestmtmDaoLocal {
	
	
    /**
     * Default constructor. 
     */
    public SpestmtmDao() {
        // TODO Auto-generated constructor stub
    }
    
    
	@Override
	public void createSpestmtm(Spestmtm spestmtm, String webAppName) {
		getEntityManager(webAppName);
		getEntityManager(webAppName).persist(spestmtm);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spestmtm> getAll(String webAppName) {
		getEntityManager(webAppName);
		String qryStr = "select a from Spestmtm a";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<Spestmtm> list = query.getResultList();
        return list;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spestmtm> getAll(String deptId, String webAppName) {
		getEntityManager(webAppName);
		String qryStr = "select a from Spestmtm a WHERE a.id.deptId =:deptId ORDER BY a.id.phase, a.id.connectionType, a.id.wiringType, a.id.matCd";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<Spestmtm> list = query.getResultList();
        return list;

	}

	@Override
	public void updateSpestmtm(Spestmtm spestmtm, String webAppName) {
		getEntityManager(webAppName);
		getEntityManager(webAppName).merge(spestmtm);
		
	}

	@Override
	public void removeSpestmtm(Spestmtm spestmtm, String webAppName) {
		getEntityManager(webAppName);
		spestmtm=getEntityManager(webAppName).merge(spestmtm);
		getEntityManager(webAppName).remove(spestmtm);
		
		
	}

	@Override
	public void removeAll(String webAppName) {
		getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");

		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Spestmtm findById(SpestmtmPK id, String webAppName) throws PersistenceException {//TRIM(g.id.matCd) = :matCd AND
		getEntityManager(webAppName);
		String qryStr = "select g from Spestmtm g WHERE  TRIM(g.id.matCd) = :matCd AND g.id.wiringType = :wiringType AND g.id.phase = :phase AND g.id.connectionType = :connectionType AND g.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("phase", id.getPhase());
		query.setParameter("connectionType", id.getConnectionType());
		//query.setParameter("fromLength", id.getFromLength());
		//query.setParameter("toLength", id.getToLength());
		query.setParameter("deptId", id.getDeptId());
		//System.out.print(id.getMatCd().length());
		query.setParameter("wiringType", id.getWiringType());
		query.setParameter("matCd", id.getMatCd());
		List<Spestmtm> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spestmtm> estimateMaterials(Long phase, Long connectionType, String wiringType,String deptId, String webAppName) {
		getEntityManager(webAppName);
		String qryStr = "SELECT g FROM Spestmtm g WHERE g.id.phase = :phase AND g.id.connectionType = :connectionType AND g.id.wiringType = :wiringType AND g.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("phase", phase);
		query.setParameter("connectionType", connectionType);
		//query.setParameter("fromLength", fromLength);
		//query.setParameter("toLength", toLength);
		query.setParameter("wiringType", wiringType);
		query.setParameter("deptId", deptId);
		List<Spestmtm> list = query.getResultList();		
		return list;
		
	}

	

}
