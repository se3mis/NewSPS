package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.Spsetsty;
import estimate.model.SpsetstyPK;

@Remote
public interface SpsetstyDaoRemote {
	void createSpsetsty(Spsetsty Spsetsty, String webAppName);
	List<Spsetsty> getAll(String deptId, String webAppName);
	void updateSpsetsty(Spsetsty Spsetsty, String webAppName);
	void removeSpsetsty(Spsetsty Spsetsty, String webAppName);
	void removeAll(String webAppName);
	Spsetsty findById(SpsetstyPK id, String webAppName) throws PersistenceException;
	List<Spsetsty> getStayList(String applicationNo, String deptId, String webAppName);
	List<Spsetsty> getStayList(String applicationNo, String webAppName);


}
