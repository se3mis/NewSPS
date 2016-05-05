package estimate.service;

import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.Speststd;
import estimate.model.SpeststdPK;

//import estimate.model.Speststd;
//import estimate.model.SpeststdPK;

public interface SpeststdEjbI {
	void createSpeststd(Speststd speststd) throws PersistenceException;
	List<Speststd> getAll() throws PersistenceException;
	List<Speststd> getAll(String deptId) throws PersistenceException;
	void updateSpeststd(Speststd speststd) throws PersistenceException ;
	void removeSpeststd(Speststd speststd) throws PersistenceException ;
	void removeAll() throws PersistenceException ;
	Speststd findById(SpeststdPK id) throws PersistenceException ;
	Speststd findByEstimateNo(String estimateNo); 


}
