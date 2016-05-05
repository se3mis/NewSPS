package estimate.service;

import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.MaterialGrid;
import estimate.model.Spcndmat;
import estimate.model.SpcndmatPK;

public interface SpcndmatEjbI {
	void createSpcndmat(Spcndmat spcndmat);
	List<Spcndmat> getAll() ;
	void updateSpcndmat(Spcndmat spcndmat)  ;
	void removeSpcndmat(Spcndmat spcndmat)  ;
	void removeAll();
	Spcndmat findById(SpcndmatPK id) throws PersistenceException ;
	List<MaterialGrid> getConductorMaterialGrid(String deptId,long phase, long connectionType, String wiringType, String conductorType, Double conductorLength) throws PersistenceException ;

}
