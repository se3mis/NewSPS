package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;


import estimate.model.Spstaymt;
import estimate.model.SpstaymtPK;

@Remote
public interface SpstaymtDaoRemote {
	void createSpstaymt(Spstaymt spstaymt, String webAppName);
	List<Spstaymt> getAll(String webAppName);
	void updateSpstaymt(Spstaymt spstaymt, String webAppName);
	void removeSpstaymt(Spstaymt spstaymt, String webAppName);
	void removeAll(String webAppName);
	Spstaymt findById(SpstaymtPK id, String webAppName) throws PersistenceException ;
	//List<MaterialGrid> getStayMaterialGrid( String deptId, int noOfStays, String webAppName);
	List<Spstaymt> getAll(String deptId, String webAppName);

}
