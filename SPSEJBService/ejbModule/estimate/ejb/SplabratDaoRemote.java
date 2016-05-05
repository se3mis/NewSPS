package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.LabourGrid;
import estimate.model.Splabrat;
import estimate.model.SplabratPK;

@Remote
public interface SplabratDaoRemote {
	void createSplabrat(Splabrat splabrat, String webAppName);
	List<Splabrat> getAll(String deptId, String webAppName) ;
	void updateSplabrat(Splabrat splabrat, String webAppName)  ;
	void removeSplabrat(Splabrat splabrat, String webAppName)  ;
	void removeAll(String webAppName);
	Splabrat findById(SplabratPK id, String webAppName) throws PersistenceException ;
	List<LabourGrid> getLabourGrid(String deptId, String applicationType, String webAppName);
	List<LabourGrid> getLabourGrid(String deptId, String applicationType,
			List<String> labourCodeList, String webAppName);
	void insertLabour(String fromDeptId, String todeptId, String webAppName);

}
