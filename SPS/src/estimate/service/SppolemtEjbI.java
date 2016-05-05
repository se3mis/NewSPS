package estimate.service;

import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.Sppolemt;
import estimate.model.SppolemtPK;

public interface SppolemtEjbI {
	void createSppolemt(Sppolemt sppolemt);
	List<Sppolemt> getAll() ;
	void updateSppolemt(Sppolemt sppolemt)  ;
	void removeSppolemt(Sppolemt sppolemt)  ;
	void removeAll();
	Sppolemt findById(SppolemtPK id) throws PersistenceException ;

}
