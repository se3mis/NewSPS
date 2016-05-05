package estimate.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.emSelect.EmSelector;
import estimate.model.Tariff;

/**
 * Session Bean implementation class Tariff
 */
@Stateless
public class TariffDao extends EmSelector implements TariffDaoRemote, TariffDaoLocal {
	/**
     * Default constructor. 
     */
    public TariffDao() {
        // TODO Auto-generated constructor stub
    }
    
    

	@Override
	public void createTariff(Tariff tariff, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).find(Tariff.class, tariff);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tariff> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Tariff a";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<Tariff> list = query.getResultList();
        return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getTariffCodeList(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a.tariffCode from Tariff a";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<String> list = query.getResultList();
        return list;
	}

	@Override
	public void updateTariff(Tariff tariff, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(tariff);
		
	}

	@Override
	public void removeTariff(Tariff tariff, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).remove(tariff);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@Override
	public Tariff findById(String tariffCode, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		return getEntityManager(webAppName).find(Tariff.class, tariffCode);
	}

}
