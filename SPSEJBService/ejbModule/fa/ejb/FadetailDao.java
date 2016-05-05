package fa.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import util.emSelect.EmSelector;

import fa.model.Fadetail;
import fa.model.FadetailPK;

/**
 * Session Bean implementation class FadetailDao
 */
@Stateless
public class FadetailDao extends EmSelector implements FadetailDaoRemote, FadetailDaoLocal {

    /**
     * Default constructor. 
     */
    public FadetailDao() {
        // TODO Auto-generated constructor stub
    }
        
    @Override
    	public void createFadetail(Fadetail fadetail, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).persist(fadetail);
    		
    	}

    	@Override
    	public void updateFadetail(Fadetail fadetail, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).merge(fadetail);
    		
    	}

    	@Override
    	public void removeFadetail(Fadetail fadetail, String webAppName) {
    		//getEntityManager(webAppName);
    		getEntityManager(webAppName).remove(fadetail);
    		
    	}

    	@Override
    	public void removeAll( String webAppName) {
    		//getEntityManager(webAppName);
    		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa");
    		
    	}

    	@SuppressWarnings("unchecked")
    	@Override
    	public List<Fadetail> getAll(String webAppName) {
    		//getEntityManager(webAppName);
    		String qryStr = "select b from Fadetail b"; 
    		Query query = getEntityManager(webAppName).createQuery(qryStr);
            List<Fadetail> list = query.getResultList();
            return list;
    	}
    	
    	@Override
    	public Fadetail findById(FadetailPK id, String webAppName) throws PersistenceException {
    		//getEntityManager(webAppName);
    		return getEntityManager(webAppName).find(Fadetail.class, id);
    	}

}
