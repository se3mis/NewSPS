package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.Spcndctm;
import estimate.model.SpcndctmPK;

@Remote
public interface SpcndctmDaoRemote {
	void createSpcndctm(Spcndctm spcndctm, String webAppName);
	List<Spcndctm> getAll(String webAppName) ;
	void updateSpcndctm(Spcndctm spcndctm, String webAppName)  ;
	void removeSpcndctm(Spcndctm spcndctm, String webAppName)  ;
	void removeAll(String webAppName) ;
	Spcndctm findById(SpcndctmPK id, String webAppName) throws PersistenceException ;
	
	

}
