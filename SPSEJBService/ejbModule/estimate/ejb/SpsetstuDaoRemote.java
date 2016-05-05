package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;
import estimate.model.Spsetstu;
import estimate.model.SpsetstuPK;

@Remote
public interface SpsetstuDaoRemote {
	void createSpsetstu(Spsetstu Spsetstu, String webAppName);
	List<Spsetstu> getAll(String deptId, String webAppName);
	void updateSpsetstu(Spsetstu Spsetstu, String webAppName);
	void removeSpsetstu(Spsetstu Spsetstu, String webAppName);
	void removeAll(String webAppName);
	Spsetstu findById(SpsetstuPK id, String webAppName) throws PersistenceException;
	List<Spsetstu> getStrutsList(String applicationNo, String deptId, String webAppName);
	List<Spsetstu> getStrutsList(String applicationNo, String webAppName);
}
