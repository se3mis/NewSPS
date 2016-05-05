package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.Splbcndt;
import estimate.model.SplbcndtPK;

@Remote
public interface SplbcndtDaoRemote {
	void createSplbcndt(Splbcndt splbcndt, String webAppName);
	List<Splbcndt> getAll(String deptId, String webAppName) ;
	void updateSplbcndt(Splbcndt splbcndt, String webAppName)  ;
	void removeSplbcndt(Splbcndt splbcndt, String webAppName)  ;
	void removeAll(String webAppName);
	Splbcndt findById(SplbcndtPK id, String webAppName) throws PersistenceException ;


}
