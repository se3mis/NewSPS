package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.Splbpolt;
import estimate.model.SplbpoltPK;



@Remote
public interface SplbpoltDaoRemote {
	void createSplbpolt(Splbpolt splbpolt, String webAppName);
	List<Splbpolt> getAll(String deptId, String webAppName) ;
	void updateSplbpolt(Splbpolt splbpolt, String webAppName)  ;
	void removeSplbpolt(Splbpolt splbpolt, String webAppName)  ;
	void removeAll(String webAppName);
	Splbpolt findById(SplbpoltPK id, String webAppName) throws PersistenceException ;

}
