package estimate.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import util.emSelect.EmSelector;

import estimate.model.Splaborm;

/**
 * Session Bean implementation class SplabormDao
 */
@Stateless
public class SplabormDao extends EmSelector implements SplabormDaoRemote, SplabormDaoLocal {
	

	
    /**
     * Default constructor. 
     */
    public SplabormDao() {
        // TODO Auto-generated constructor stub
    }
    
    

	@Override
	public void createSplaborm(Splaborm splaborm, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(splaborm);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Splaborm> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Splaborm a ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<Splaborm> list = query.getResultList();
        return list;
	}

	@Override
	public void updateSplaborm(Splaborm splaborm, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(splaborm);
		
	}

	@Override
	public void removeSplaborm(Splaborm Splaborm, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).remove(Splaborm);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@Override
	public Splaborm findById(String labourcode, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		return getEntityManager(webAppName).find(Splaborm.class, labourcode);
	}

}
