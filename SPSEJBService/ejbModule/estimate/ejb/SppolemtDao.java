package estimate.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import util.emSelect.EmSelector;
import estimate.model.Sppolemt;
import estimate.model.SppolemtPK;

/**
 * Session Bean implementation class SppolemtDao
 */
@Stateless
public class SppolemtDao extends EmSelector implements SppolemtDaoRemote, SppolemtDaoLocal {
	/**
     * Default constructor. 
     */
    public SppolemtDao() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void createSppolemt(Sppolemt sppolemt, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(sppolemt);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sppolemt> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Sppolemt a ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		List<Sppolemt> list = query.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Sppolemt> getAll(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Sppolemt a where a.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<Sppolemt> list = query.getResultList();
		return list;
	}

	@Override
	public void updateSppolemt(Sppolemt sppolemt, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(sppolemt);
		
	}

	@Override
	public void removeSppolemt(Sppolemt sppolemt, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).remove(sppolemt);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Sppolemt findById(SppolemtPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Sppolemt a where a.id.deptId = :deptId AND a.id.phase = :phase AND  a.id.poleType = :poleType AND  a.id.fromConductor = :fromConductor AND  a.id.toConductor = :toConductor AND  TRIM(a.id.matCd) = :matCd";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", id.getDeptId());
		query.setParameter("phase", id.getPhase());
		query.setParameter("poleType", id.getPoleType());
		query.setParameter("fromConductor", id.getFromConductor());
		query.setParameter("toConductor", id.getToConductor());
		query.setParameter("matCd", id.getMatCd());
		List<Sppolemt> list = query.getResultList();
		if (list.isEmpty())
			return null;
		else if (list.size() == 1)
			return list.get(0);
		throw new NonUniqueResultException();
	}

}
