package fa.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import fa.model.Fascfeat;
import fa.model.FascfeatPK;

import util.emSelect.EmSelector;

/**
 * Session Bean implementation class FascfeatDao
 */
@Stateless
public class FascfeatDao extends EmSelector   implements FascfeatDaoRemote, FascfeatDaoLocal {

    /**
     * Default constructor. 
     */
    public FascfeatDao() {
        // TODO Auto-generated constructor stub
    }
    
      
    @Override
    	public void createFascfeat(Fascfeat fascfeat, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).persist(fascfeat);
    		
    	}

    	@Override
    	public void updateFascfeat(Fascfeat fascfeat, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).merge(fascfeat);
    		
    	}

    	@Override
    	public void removeFascfeat(Fascfeat fascfeat, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).remove(fascfeat);
    		
    	}

    	@Override
    	public void removeAll( String webAppName) {
    		//getEntityManager(webAppName);
    		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa");
    		
    	}

    	@SuppressWarnings("unchecked")
    	@Override
    	public List<Fascfeat> getAll(String webAppName) {
    		//getEntityManager(webAppName);
    		String qryStr = "select b from Fascfeat b"; 
    		Query query = getEntityManager(webAppName).createQuery(qryStr);
            List<Fascfeat> list = query.getResultList();
            return list;
    	}
    	
    	@Override
    	public Fascfeat findById(FascfeatPK id, String webAppName) throws PersistenceException {
    		//getEntityManager(webAppName);
    		return getEntityManager(webAppName).find(Fascfeat.class, id);
    	}

}
