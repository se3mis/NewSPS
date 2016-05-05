package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;
import estimate.model.Spsetpol;
import estimate.model.SpsetpolPK;


@Remote
public interface SpsetpolDaoRemote {
	void createSpsetpol(Spsetpol spsetpol, String webAppName);
	List<Spsetpol> getAll(String deptId, String webAppName);
	void updateSpsetpol(Spsetpol spsetpol, String webAppName);
	void removeSpsetpol(Spsetpol spsetpol, String webAppName);
	void removeAll(String webAppName);
	Spsetpol findById(SpsetpolPK id, String webAppName) throws PersistenceException;
	List<Spsetpol> getPoleList(String applicationNo, String deptId, String webAppName)
			throws PersistenceException;
	void createSpsetpolList(List<Spsetpol> list, String webAppName);
	List<Spsetpol> getPoleList(String applicationNo, String webAppName)
			throws PersistenceException;

}
