package job.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.emSelect.EmSelector;
import job.model.Sparemap;


/**
 * Session Bean implementation class SparemapDao
 */

@Stateless
public class SparemapDao extends EmSelector implements SparemapDaoRemote, SparemapDaoLocal {
	
	
    /**
     * Default constructor. 
     */
    public SparemapDao() {
        // TODO Auto-generated constructor stub
    }

    
	@Override
	public void createSparemap(Sparemap sparemap, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(sparemap);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sparemap> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Sparemap a";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		//Query query = getEntityManager(webAppName).createQuery("select a from Spestcnd a");
		List<Sparemap> list = query.getResultList();
	    return list;
	}

	@Override
	public void updateSparemap(Sparemap sparemap, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(sparemap);
		
	}

	@Override
	public void removeSparemap(Sparemap sparemap, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).remove(sparemap);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Sparemap findById(String smcAreaCode, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Sparemap a WHERE a.smcAreaCode = :smcAreaCode";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("smcAreaCode", smcAreaCode);
		List<Sparemap> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}

}
