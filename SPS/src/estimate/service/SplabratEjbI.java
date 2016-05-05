package estimate.service;

import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.LabourGrid;
import estimate.model.Splabrat;
import estimate.model.SplabratPK;

public interface SplabratEjbI {
	void createSplabrat(Splabrat splabrat);
	List<Splabrat> getAll(String deptId) ;
	void updateSplabrat(Splabrat splabrat)  ;
	void removeSplabrat(Splabrat splabrat)  ;
	void removeAll();
	Splabrat findById(SplabratPK id) throws PersistenceException ;
	List<LabourGrid> getLabourGrid(String deptId, String applicationType);
	List<LabourGrid> getLabourGrid(String deptId, String applicationType, List<String> labourCodeLst);


}
