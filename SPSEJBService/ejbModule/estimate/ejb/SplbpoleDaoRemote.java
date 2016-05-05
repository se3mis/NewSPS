package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.Splbpole;
import estimate.model.SplbpolePK;

@Remote
public interface SplbpoleDaoRemote {
	void createSplbpole(Splbpole splbpole, String webAppName);
	List<Splbpole> getAll(String deptId, String webAppName) ;
	void updateSplbpole(Splbpole splbpole, String webAppName)  ;
	void removeSplbpole(Splbpole splbpole, String webAppName)  ;
	void removeAll(String webAppName);
	Splbpole findById(SplbpolePK id, String webAppName) throws PersistenceException ;


}
