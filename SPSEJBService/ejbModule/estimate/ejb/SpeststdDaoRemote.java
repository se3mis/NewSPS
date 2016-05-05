package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.Speststd;
import estimate.model.SpeststdPK;

@Remote
public interface SpeststdDaoRemote {
	void createSpeststd(Speststd speststd, String webAppName) throws PersistenceException;
	List<Speststd> getAll(String webAppName) throws PersistenceException;
	List<Speststd> getAll(String deptId, String webAppName) throws PersistenceException;
	void updateSpeststd(Speststd speststd, String webAppName) throws PersistenceException ;
	void removeSpeststd(Speststd speststd, String webAppName) throws PersistenceException ;
	void removeAll(String webAppName) throws PersistenceException ;
	Speststd findById(SpeststdPK id, String webAppName) throws PersistenceException ;
	Speststd findByEstimateNo(String estimateNo, String webAppName); 

}
