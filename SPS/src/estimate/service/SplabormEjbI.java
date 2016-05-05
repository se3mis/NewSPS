package estimate.service;

import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.Splaborm;

public interface SplabormEjbI {
	void createSplaborm(Splaborm splaborm);
	List<Splaborm> getAll() ;
	void updateSplaborm(Splaborm splaborm)  ;
	void removeSplaborm(Splaborm splaborm)  ;
	void removeAll();
	Splaborm findById(String labourcode) throws PersistenceException ;


}
