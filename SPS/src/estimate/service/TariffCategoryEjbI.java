package estimate.service;

import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.TariffCategory;

///import estimate.model.TariffCategory;

public interface TariffCategoryEjbI {
	void createTariffCategory(TariffCategory tariffCategory);
	void updateTariffCategory(TariffCategory tariffCategory)  ;
	void removeTariffCategory(TariffCategory tariffCategory)  ;
	void removeAll() ;
	TariffCategory findById(String tariffCatCode) throws PersistenceException ;
	List<TariffCategory> getAll() ;
	List<String> getTariffCategoryList();


}
