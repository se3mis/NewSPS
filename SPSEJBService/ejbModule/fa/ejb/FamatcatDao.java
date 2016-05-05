package fa.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import fa.model.Famatcat;
import fa.model.FamatcatPK;

import util.emSelect.EmSelector;

/**
 * Session Bean implementation class Famatcat
 */
@Stateless
public class FamatcatDao extends EmSelector   implements FamatcatDaoRemote, FamatcatDaoLocal {

    /**
     * Default constructor. 
     */
    public FamatcatDao() {
        // TODO Auto-generated constructor stub
    }
    
      
    @Override
    	public void createFamatcat(Famatcat famatcat, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).persist(famatcat);
    		
    	}

    	@Override
    	public void updateFamatcat(Famatcat famatcat, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).merge(famatcat);
    		
    	}

    	@Override
    	public void removeFamatcat(Famatcat famatcat, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).remove(famatcat);
    		
    	}

    	@Override
    	public void removeAll( String webAppName) {
    		//getEntityManager(webAppName);
    		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa");
    		
    	}

    	@SuppressWarnings("unchecked")
    	@Override
    	public List<Famatcat> getAll(String webAppName) {
    		//getEntityManager(webAppName);
    		String qryStr = "select b from Famatcat b"; 
    		Query query = getEntityManager(webAppName).createQuery(qryStr);
            List<Famatcat> list = query.getResultList();
            return list;
    	}
    	
    	@Override
    	public Famatcat findById(FamatcatPK id, String webAppName) throws PersistenceException {
    		//getEntityManager(webAppName);
    		return getEntityManager(webAppName).find(Famatcat.class, id);
    	}

}
