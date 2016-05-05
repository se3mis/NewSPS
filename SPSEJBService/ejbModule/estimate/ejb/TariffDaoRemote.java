package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.Tariff;

@Remote
public interface TariffDaoRemote {
	void createTariff(Tariff tariff, String webAppName);
	List<Tariff> getAll(String webAppName) ;
	void updateTariff(Tariff tariff, String webAppName)  ;
	void removeTariff(Tariff tariff, String webAppName)  ;
	void removeAll(String webAppName) ;
	Tariff findById(String tariffCode, String webAppName) throws PersistenceException ;
	List<String> getTariffCodeList(String webAppName);
	
}
