package fa.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import fa.model.Faplnteq;
import fa.model.FaplnteqPK;

@Remote
public interface FaplnteqDaoRemote {
	void createFaplnteq(Faplnteq approvedSm, String webAppName);

	void updateFaplnteq(Faplnteq approvedSm, String webAppName);

	void removeFaplnteq(Faplnteq approvedSm, String webAppName);

	List<Faplnteq> getAll(String webAppName);

	void removeAll(String webAppName);

	Faplnteq findById(FaplnteqPK id, String webAppName)
			throws PersistenceException;

}
