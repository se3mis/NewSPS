package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.Splbstrt;
import estimate.model.SplbstrtPK;

@Remote
public interface SplbstrtDaoRemote {
	void createSplbstrt(Splbstrt splbstrt, String webAppName);
	List<Splbstrt> getAll(String deptId, String webAppName) ;
	void updateSplbstrt(Splbstrt splbstrt, String webAppName)  ;
	void removeSplbstrt(Splbstrt splbstrt, String webAppName)  ;
	void removeAll(String webAppName);
	Splbstrt findById(SplbstrtPK id, String webAppName) throws PersistenceException ;



}
