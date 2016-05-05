package fa.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import fa.model.Famcfeat;
import fa.model.FamcfeatPK;

import util.emSelect.EmSelector;

/**
 * Session Bean implementation class FamcfeatDao
 */
@Stateless
public class FamcfeatDao extends EmSelector implements FamcfeatDaoRemote, FamcfeatDaoLocal {

    /**
     * Default constructor. 
     */
    public FamcfeatDao() {
        // TODO Auto-generated constructor stub
    }
        
    @Override
    	public void createFamcfeat(Famcfeat famcfeat, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).persist(famcfeat);
    		
    	}

    	@Override
    	public void updateFamcfeat(Famcfeat famcfeat, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).merge(famcfeat);
    		
    	}

    	@Override
    	public void removeFamcfeat(Famcfeat famcfeat, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).remove(famcfeat);
    		
    	}

    	@Override
    	public void removeAll( String webAppName) {
    		//getEntityManager(webAppName);
    		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa");
    		
    	}

    	@SuppressWarnings("unchecked")
    	@Override
    	public List<Famcfeat> getAll(String webAppName) {
    		//getEntityManager(webAppName);
    		String qryStr = "select b from Famcfeat b"; 
    		Query query = getEntityManager(webAppName).createQuery(qryStr);
            List<Famcfeat> list = query.getResultList();
            return list;
    	}
    	
    	@Override
    	public Famcfeat findById(FamcfeatPK id, String webAppName) throws PersistenceException {
    		//getEntityManager(webAppName);
    		return getEntityManager(webAppName).find(Famcfeat.class, id);
    	}

}
