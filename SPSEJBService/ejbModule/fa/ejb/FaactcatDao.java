package fa.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import fa.model.Faactcat;

import util.emSelect.EmSelector;

/**
 * Session Bean implementation class FaactcatDao
 */
@Stateless
public class FaactcatDao  extends EmSelector implements FaactcatDaoRemote, FaactcatDaoLocal {

    /**
     * Default constructor. 
     */
    public FaactcatDao() {
        // TODO Auto-generated constructor stub
    }
    @Override
	public void createFaactcat(Faactcat faactcat, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(faactcat);
		
	}

	@Override
	public void updateFaactcat(Faactcat faactcat, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(faactcat);
		
	}

	@Override
	public void removeFaactcat(Faactcat faactcat, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).remove(faactcat);
		
	}

	@Override
	public void removeAll( String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Faactcat> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select b from Faactcat b"; 
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<Faactcat> list = query.getResultList();
        return list;
	}
	@Override
	public Faactcat findById(String faCatCode, String webAppName)
			throws PersistenceException {
		return getEntityManager(webAppName).find(Faactcat.class, faCatCode);
	}
	
	

}
