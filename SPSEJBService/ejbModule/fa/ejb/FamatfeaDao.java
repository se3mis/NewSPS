package fa.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import fa.model.Famatfea;
import fa.model.FamatfeaPK;

import util.emSelect.EmSelector;

/**
 * Session Bean implementation class FamatfeaDao
 */
@Stateless
public class FamatfeaDao extends EmSelector   implements FamatfeaDaoRemote, FamatfeaDaoLocal {

    /**
     * Default constructor. 
     */
    public FamatfeaDao() {
        // TODO Auto-generated constructor stub
    }
    
      
    @Override
    	public void createFamatfea(Famatfea famatfea, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).persist(famatfea);
    		
    	}

    	@Override
    	public void updateFamatfea(Famatfea famatfea, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).merge(famatfea);
    		
    	}

    	@Override
    	public void removeFamatfea(Famatfea famatfea, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).remove(famatfea);
    		
    	}

    	@Override
    	public void removeAll( String webAppName) {
    		//getEntityManager(webAppName);
    		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa");
    		
    	}

    	@SuppressWarnings("unchecked")
    	@Override
    	public List<Famatfea> getAll(String webAppName) {
    		//getEntityManager(webAppName);
    		String qryStr = "select b from Famatfea b"; 
    		Query query = getEntityManager(webAppName).createQuery(qryStr);
            List<Famatfea> list = query.getResultList();
            return list;
    	}
    	
    	@Override
    	public Famatfea findById(FamatfeaPK id, String webAppName) throws PersistenceException {
    		//getEntityManager(webAppName);
    		return getEntityManager(webAppName).find(Famatfea.class, id);
    	}

}
