package estimate.service;

import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.MaterialGrid;
import estimate.model.Spstaymt;
import estimate.model.SpstaymtPK;

public interface SpstaymtEjbI {
	void createSpstaymt(Spstaymt spstaymt);
	List<Spstaymt> getAll() ;
	void updateSpstaymt(Spstaymt spstaymt)  ;
	void removeSpstaymt(Spstaymt spstaymt)  ;
	void removeAll();
	Spstaymt findById(SpstaymtPK  id) throws PersistenceException ;
	//List<MaterialGrid> getStayMaterialGrid(String deptId, int noOfStays);

}
