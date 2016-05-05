package estimate.service;

import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.Splbstay;
import estimate.model.SplbstayPK;

public interface SplbstayEjbI {
	void createSplbstay(Splbstay splbstay, String webAppName);
	List<Splbstay> getAll(String deptId, String webAppName) ;
	void updateSplbstay(Splbstay splbstay, String webAppName)  ;
	void removeSplbstay(Splbstay splbstay, String webAppName)  ;
	void removeAll(String webAppName);
	Splbstay findById(SplbstayPK id, String webAppName) throws PersistenceException ;
}
