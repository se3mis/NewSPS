package estimate.service;

import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.Spsetwir;
import estimate.model.SpsetwirPK;

public interface SpsetwirEjbI {
	void createSpsetwir(Spsetwir Spsetwir);
	List<Spsetwir> getAll(String deptId);
	void updateSpsetwir(Spsetwir Spsetwir);
	void removeSpsetwir(Spsetwir Spsetwir);
	void removeAll();
	Spsetwir findById(SpsetwirPK id) throws PersistenceException ;
	List<Spsetwir> getWireList(String applicationNo, String deptId);



}
