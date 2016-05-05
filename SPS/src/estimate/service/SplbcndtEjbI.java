package estimate.service;

import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.Splbcndt;
import estimate.model.SplbcndtPK;

public interface SplbcndtEjbI {
	void createSplbcndt(Splbcndt splbcndt);
	List<Splbcndt> getAll(String deptId) ;
	void updateSplbcndt(Splbcndt splbcndt)  ;
	void removeSplbcndt(Splbcndt splbcndt)  ;
	void removeAll();
	Splbcndt findById(SplbcndtPK id) throws PersistenceException ;


}
