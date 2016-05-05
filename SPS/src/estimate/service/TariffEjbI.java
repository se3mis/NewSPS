package estimate.service;

import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.Tariff;

//import estimate.model.Tariff;


public interface TariffEjbI {
	void createTariff(Tariff tariff);
	List<Tariff> getAll() ;
	void updateTariff(Tariff tariff)  ;
	void removeTariff(Tariff tariff)  ;
	void removeAll() ;
	Tariff findById(String tariffCode) throws PersistenceException ;
	List<String> getTariffCodeList();


}
