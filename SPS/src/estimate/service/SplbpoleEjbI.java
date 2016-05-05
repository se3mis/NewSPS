package estimate.service;

import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.Splbpole;
import estimate.model.SplbpolePK;

public interface SplbpoleEjbI {
	void createSplbpole(Splbpole splbpole);
	List<Splbpole> getAll(String deptId) ;
	void updateSplbpole(Splbpole splbpole)  ;
	void removeSplbpole(Splbpole splbpole)  ;
	void removeAll();
	Splbpole findById(SplbpolePK id) throws PersistenceException ;


}
