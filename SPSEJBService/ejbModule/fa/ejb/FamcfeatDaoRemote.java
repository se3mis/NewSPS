package fa.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import fa.model.Famcfeat;
import fa.model.FamcfeatPK;

@Remote
public interface FamcfeatDaoRemote {
	void createFamcfeat(Famcfeat famcfeat, String webAppName);

	void updateFamcfeat(Famcfeat famcfeat, String webAppName);

	void removeFamcfeat(Famcfeat famcfeat, String webAppName);

	List<Famcfeat> getAll(String webAppName);

	void removeAll(String webAppName);

	Famcfeat findById(FamcfeatPK id, String webAppName)
			throws PersistenceException;

}
