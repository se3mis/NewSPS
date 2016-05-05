package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.TariffCategory;

@Remote
public interface TariffCategoryDaoRemote {
	void createTariffCategory(TariffCategory tariffCategory, String webAppName);
	List<TariffCategory> getAll(String webAppName) ;
	void updateTariffCategory(TariffCategory tariffCategory, String webAppName)  ;
	void removeTariffCategory(TariffCategory tariffCategory, String webAppName)  ;
	void removeAll(String webAppName) ;
	TariffCategory findById(String tariffCatCode, String webAppName) throws PersistenceException ;
	List<String> getTariffCategoryList(String webAppName);


}
