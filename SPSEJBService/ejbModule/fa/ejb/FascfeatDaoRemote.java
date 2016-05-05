package fa.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import fa.model.Fascfeat;
import fa.model.FascfeatPK;

@Remote
public interface FascfeatDaoRemote {
	void createFascfeat(Fascfeat fascfeat, String webAppName);

	void updateFascfeat(Fascfeat fascfeat, String webAppName);

	void removeFascfeat(Fascfeat fascfeat, String webAppName);

	List<Fascfeat> getAll(String webAppName);

	void removeAll(String webAppName);

	Fascfeat findById(FascfeatPK id, String webAppName)
			throws PersistenceException;

}
