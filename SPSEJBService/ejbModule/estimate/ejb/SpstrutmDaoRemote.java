package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;


import estimate.model.Spstrutm;
import estimate.model.SpstrutmPK;

@Remote
public interface SpstrutmDaoRemote {
	void createSpstrutm(Spstrutm spstrutm, String webAppName);
	List<Spstrutm> getAll(String webAppName);
	void updateSpstrutm(Spstrutm spstrutm, String webAppName);
	void removeSpstrutm(Spstrutm spstrutm, String webAppName);
	void removeAll(String webAppName);
	Spstrutm findById(SpstrutmPK id, String webAppName) throws PersistenceException ;
	//List<MaterialGrid> getStrutMaterialGrid( String deptId, int noOfStruts, String webAppName); 
	List<Spstrutm> getAll(String deptId, String webAppName);

}
