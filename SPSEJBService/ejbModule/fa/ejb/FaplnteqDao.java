package fa.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import fa.model.Faplnteq;
import fa.model.FaplnteqPK;

import util.emSelect.EmSelector;

/**
 * Session Bean implementation class FaplnteqDao
 */
@Stateless
public class FaplnteqDao extends EmSelector implements FaplnteqDaoRemote, FaplnteqDaoLocal {

    /**
     * Default constructor. 
     */
    public FaplnteqDao() {
        // TODO Auto-generated constructor stub
    }
    
        
    @Override
    	public void createFaplnteq(Faplnteq faplnteq, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).persist(faplnteq);
    		
    	}

    	@Override
    	public void updateFaplnteq(Faplnteq faplnteq, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).merge(faplnteq);
    		
    	}

    	@Override
    	public void removeFaplnteq(Faplnteq faplnteq, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).remove(faplnteq);
    		
    	}

    	@Override
    	public void removeAll( String webAppName) {
    		//getEntityManager(webAppName);
    		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa");
    		
    	}

    	@SuppressWarnings("unchecked")
    	@Override
    	public List<Faplnteq> getAll(String webAppName) {
    		//getEntityManager(webAppName);
    		String qryStr = "select b from Faplnteq b"; 
    		Query query = getEntityManager(webAppName).createQuery(qryStr);
            List<Faplnteq> list = query.getResultList();
            return list;
    	}
    	
    	@Override
    	public Faplnteq findById(FaplnteqPK id, String webAppName) throws PersistenceException {
    		//getEntityManager(webAppName);
    		return getEntityManager(webAppName).find(Faplnteq.class, id);
    	}

}
