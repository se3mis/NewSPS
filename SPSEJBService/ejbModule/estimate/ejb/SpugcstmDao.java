package estimate.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.emSelect.EmSelector;
import estimate.model.Spugcstm;
import estimate.model.SpugcstmPK;

/**
 * Session Bean implementation class SpugcstmDao
 */
@Stateless
public class SpugcstmDao extends EmSelector implements SpugcstmDaoRemote, SpugcstmDaoLocal {
	
    /**
     * Default constructor. 
     */
    public SpugcstmDao() {
        // TODO Auto-generated constructor stub
    }

    
	@Override
	public void createSpugcstm(Spugcstm spugcstm, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(spugcstm);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spugcstm> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spugcstm a ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<Spugcstm> list = query.getResultList();
        return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spugcstm> getAll(String deptId, String webAppName){
		//getEntityManager(webAppName);
		String qryStr = "select a from Spugcstm a where a.id.deptid=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<Spugcstm> list = query.getResultList();
        return list;
	}

	@Override
	public void updateSpugcstm(Spugcstm spugcstm, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(spugcstm);
		
	}

	@Override
	public void removeSpugcstm(Spugcstm spugcstm, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).remove(spugcstm);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Spugcstm findById(SpugcstmPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spugcstm a where a.id.phase = :phase AND  a.id.connectionType = :connectionType AND  a.id.loopCable = :loopCable AND  a.id.category = :category ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("phase", id.getPhase());
		query.setParameter("connectionType", id.getConnectionType());
		query.setParameter("loopCable", id.getLoopCable());
		query.setParameter("category", id.getCategory());
        List<Spugcstm> list = query.getResultList();
        if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}

}
