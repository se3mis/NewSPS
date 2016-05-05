package estimate.service;

import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.Splbstrt;
import estimate.model.SplbstrtPK;

public interface SplbstrtEjbI {
	void createSplbpole(Splbstrt Splbstrt, String webAppName);
	List<Splbstrt> getAll(String deptId, String webAppName) ;
	void updateSplbpole(Splbstrt Splbstrt, String webAppName)  ;
	void removeSplbpole(Splbstrt Splbstrt, String webAppName)  ;
	void removeAll(String webAppName);
	Splbstrt findById(SplbstrtPK id, String webAppName) throws PersistenceException ;

}
