package estimate.service;

import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.Splbsrvc;
import estimate.model.SplbsrvcPK;

public interface SplbsrvcEjbI {
	void createSplbsrvc(Splbsrvc splbsrvc);
	List<Splbsrvc> getAll(String deptId) ;
	void updateSplbsrvc(Splbsrvc splbsrvc)  ;
	void removeSplbsrvc(Splbsrvc splbsrvc)  ;
	void removeAll();
	Splbsrvc findById(SplbsrvcPK id) throws PersistenceException ;


}
