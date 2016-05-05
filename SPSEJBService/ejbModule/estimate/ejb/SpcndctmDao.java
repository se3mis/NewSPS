package estimate.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import util.emSelect.EmSelector;

import estimate.model.Spcndctm;
import estimate.model.SpcndctmPK;

/**
 * Session Bean implementation class SpcndctmDao
 */
@Stateless
public class SpcndctmDao extends EmSelector implements SpcndctmDaoRemote, SpcndctmDaoLocal {
	

	
    /**
     * Default constructor. 
     */
    public SpcndctmDao() {
        // TODO Auto-generated constructor stub
    }
    
    

	@Override
	public void createSpcndctm(Spcndctm spcndctm, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(spcndctm);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spcndctm> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spcndctm a ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<Spcndctm> list = query.getResultList();
        return list;
	}

	@Override
	public void updateSpcndctm(Spcndctm spcndctm, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(spcndctm);
		
	}

	@Override
	public void removeSpcndctm(Spcndctm spcndctm, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).remove(spcndctm);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Spcndctm findById(SpcndctmPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spcndctm a where a.id.phase  =:phase AND a.id.conductorType   =:conductorType  ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("phase", id.getPhase());
        query.setParameter("conductorType", id.getConductorType());
		List<Spcndctm> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}

	

}
