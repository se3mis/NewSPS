package estimate.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.emSelect.EmSelector;
import estimate.model.TariffCategory;

/**
 * Session Bean implementation class TariffCategoryDao
 */
@Stateless
public class TariffCategoryDao extends EmSelector implements TariffCategoryDaoRemote, TariffCategoryDaoLocal {
	
    /**
     * Default constructor. 
     */
    public TariffCategoryDao() {
        // TODO Auto-generated constructor stub
    }
    
   
	@Override
	public void createTariffCategory(TariffCategory tariffCategory, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(tariffCategory);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TariffCategory> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from TariffCategory a";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<TariffCategory> list = query.getResultList();
        return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getTariffCategoryList(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a.tariffCatCode from TariffCategory a";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<String> list = query.getResultList();
        return list;
	}

	@Override
	public void updateTariffCategory(TariffCategory tariffCategory, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(tariffCategory);
		
	}

	@Override
	public void removeTariffCategory(TariffCategory tariffCategory, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).remove(tariffCategory);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@Override
	public TariffCategory findById(String tariffCatCode, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		// TODO Auto-generated method stub
		return null;
	}

}
