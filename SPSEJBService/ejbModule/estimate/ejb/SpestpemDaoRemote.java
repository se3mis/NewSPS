package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.Spestpem;
import estimate.model.SpestpemPK;

@Remote
public interface SpestpemDaoRemote {
	void createSpestpem(Spestpem spestpem, String webAppName);
	void createSpestpem(List<Spestpem> list, String webAppName);
	List<Spestpem> getAll(String webAppName) ;
	void updateSpestpem(Spestpem spestpem, String webAppName)  ;
	void removeSpestpem(Spestpem spestpem, String webAppName)  ;
	void removeAll(String webAppName);
	List<Spestpem> findById(SpestpemPK id, String webAppName) throws PersistenceException ;
	void removeSpestpem(List<SpestpemPK> list, String webAppName);
	List<Spestpem> getPermissions(String applicationNo, String deptId, String webAppName)
			throws PersistenceException;
	

}
