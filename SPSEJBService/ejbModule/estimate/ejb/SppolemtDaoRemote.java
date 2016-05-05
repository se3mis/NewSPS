package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.Sppolemt;
import estimate.model.SppolemtPK;

@Remote
public interface SppolemtDaoRemote {
	void createSppolemt(Sppolemt sppolemt, String webAppName);
	List<Sppolemt> getAll(String webAppName) ;
	void updateSppolemt(Sppolemt sppolemt, String webAppName)  ;
	void removeSppolemt(Sppolemt sppolemt, String webAppName)  ;
	void removeAll(String webAppName);
	Sppolemt findById(SppolemtPK id, String webAppName) throws PersistenceException ;
	List<Sppolemt> getAll(String deptId, String webAppName);

}
