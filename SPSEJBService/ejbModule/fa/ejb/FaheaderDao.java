package fa.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import fa.model.Faheader;
import fa.model.FaheaderPK;

import util.emSelect.EmSelector;

/**
 * Session Bean implementation class FaheaderDao
 */
@Stateless
public class FaheaderDao extends EmSelector implements  FaheaderDaoRemote, FaheaderDaoLocal {

    /**
     * Default constructor. 
     */
    public FaheaderDao() {
        // TODO Auto-generated constructor stub
    }
    
        
    @Override
    	public void createFaheader(Faheader faheader, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).persist(faheader);
    		
    	}

    	@Override
    	public void updateFaheader(Faheader faheader, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).merge(faheader);
    		
    	}

    	@Override
    	public void removeFaheader(Faheader faheader, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).remove(faheader);
    		
    	}

    	@Override
    	public void removeAll( String webAppName) {
    		//getEntityManager(webAppName);
    		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa");
    		
    	}

    	@SuppressWarnings("unchecked")
    	@Override
    	public List<Faheader> getAll(String webAppName) {
    		//getEntityManager(webAppName);
    		String qryStr = "select b from Faheader b"; 
    		Query query = getEntityManager(webAppName).createQuery(qryStr);
            List<Faheader> list = query.getResultList();
            return list;
    	}
    	
    	@Override
    	public Faheader findById(FaheaderPK id, String webAppName) throws PersistenceException {
    		//getEntityManager(webAppName);
    		return getEntityManager(webAppName).find(Faheader.class, id);
    	}

}
