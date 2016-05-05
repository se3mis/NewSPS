package fa.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import fa.model.Fasubcat;

import util.emSelect.EmSelector;

/**
 * Session Bean implementation class FasubcatDao
 */
@Stateless
public class FasubcatDao extends EmSelector implements FasubcatDaoRemote, FasubcatDaoLocal {

    /**
     * Default constructor. 
     */
    public FasubcatDao() {
        // TODO Auto-generated constructor stub
    }

        
    @Override
    	public void createFasubcat(Fasubcat fasubcat, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).persist(fasubcat);
    		
    	}

    	@Override
    	public void updateFasubcat(Fasubcat fasubcat, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).merge(fasubcat);
    		
    	}

    	@Override
    	public void removeFasubcat(Fasubcat fasubcat, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).remove(fasubcat);
    		
    	}

    	@Override
    	public void removeAll( String webAppName) {
    		//getEntityManager(webAppName);
    		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa");
    		
    	}

    	@SuppressWarnings("unchecked")
    	@Override
    	public List<Fasubcat> getAll(String webAppName) {
    		//getEntityManager(webAppName);
    		String qryStr = "select b from Fasubcat b"; 
    		Query query = getEntityManager(webAppName).createQuery(qryStr);
            List<Fasubcat> list = query.getResultList();
            return list;
    	}
    	
    	@Override
    	public Fasubcat findById(String subCatCode, String webAppName) throws PersistenceException {
    		//getEntityManager(webAppName);
    		return getEntityManager(webAppName).find(Fasubcat.class, subCatCode);
    	}
}
