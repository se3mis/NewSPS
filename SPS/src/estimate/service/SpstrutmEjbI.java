package estimate.service;

import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.MaterialGrid;
import estimate.model.Spstrutm;
import estimate.model.SpstrutmPK;

public interface SpstrutmEjbI {
	void createSpstrutm(Spstrutm spstrutm);
	List<Spstrutm> getAll();
	void updateSpstrutm(Spstrutm spstrutm);
	void removeSpstrutm(Spstrutm spstrutm);
	void removeAll();
	Spstrutm findById(SpstrutmPK id) throws PersistenceException ;
	//List<MaterialGrid> getStrutMaterialGrid(String deptId, int noOfStruts);

}
