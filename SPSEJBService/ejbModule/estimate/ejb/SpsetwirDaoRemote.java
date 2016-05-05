package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.Spsetwir;
import estimate.model.SpsetwirPK;

@Remote
public interface SpsetwirDaoRemote {
	void createSpsetwir(Spsetwir spsetwir, String webAppName);
	List<Spsetwir> getAll(String deptId, String webAppName);
	void updateSpsetwir(Spsetwir spsetwir, String webAppName);
	void removeSpsetwir(Spsetwir spsetwir, String webAppName);
	void removeAll(String webAppName);
	Spsetwir findById(SpsetwirPK id, String webAppName) throws PersistenceException ;
	List<Spsetwir> getWireList(String applicationNo, String deptId, String webAppName)
			throws PersistenceException;
	List<Spsetwir> getWireList(String applicationNo, String webAppName)
			throws PersistenceException;

}
