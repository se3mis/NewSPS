package estimate.service;

import java.util.List;

import javax.persistence.PersistenceException;


import estimate.model.LabourGrid;
import estimate.model.Spestlab;
import estimate.model.SpestlabPK;

public interface SpestlabEjbI {
	void createSpestlab(Spestlab spestlab);
	List<Spestlab> getAll(String deptId) ;
	List<Spestlab> getAll(String estimateNo, String deptId);
	void updateSpestlab(Spestlab spestlab);
	void removeSpestlab(Spestlab spestlab);
	void removeAll();
	Spestlab findById(SpestlabPK id) throws PersistenceException;
	List<LabourGrid> getSpestlabList(String estimateNo, String deptId );
	Spestlab findByEstimateNo(String estimateNo, String labourCode);

}
