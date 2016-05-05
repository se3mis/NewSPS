package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.Splbsrvc;
import estimate.model.SplbsrvcPK;

@Remote
public interface SplbsrvcDaoRemote {
	void createSplbsrvc(Splbsrvc splbsrvc, String webAppName);
	List<Splbsrvc> getAll(String deptId, String webAppName);
	void updateSplbsrvc(Splbsrvc splbsrvc, String webAppName);
	void removeSplbsrvc(Splbsrvc splbsrvc, String webAppName);
	void removeAll(String webAppName);
	Splbsrvc findById(SplbsrvcPK id, String webAppName) throws PersistenceException ;


}
