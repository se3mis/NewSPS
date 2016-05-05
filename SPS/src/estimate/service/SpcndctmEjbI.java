package estimate.service;

import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.Spcndctm;
import estimate.model.SpcndctmPK;

public interface SpcndctmEjbI {
	void createSpcndctm(Spcndctm spcndctm);
	List<Spcndctm> getAll() ;
	void updateSpcndctm(Spcndctm spcndctm)  ;
	void removeSpcndctm(Spcndctm spcndctm)  ;
	void removeAll() ;
	Spcndctm findById(SpcndctmPK id) throws PersistenceException ;

}
