package estimate.service;

import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.Splbpolt;
import estimate.model.SplbpoltPK;

public interface SplbpoltEjbI {
	void createSplbpole(Splbpolt splbpole);
	List<Splbpolt> getAll(String deptId) ;
	void updateSplbpole(Splbpolt splbpole)  ;
	void removeSplbpole(Splbpolt splbpole)  ;
	void removeAll();
	Splbpolt findById(SplbpoltPK id) throws PersistenceException ;



}
