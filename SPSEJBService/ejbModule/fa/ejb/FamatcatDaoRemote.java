package fa.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import fa.model.Famatcat;
import fa.model.FamatcatPK;

@Remote
public interface FamatcatDaoRemote {
	void createFamatcat(Famatcat famatcat, String webAppName);

	void updateFamatcat(Famatcat famatcat, String webAppName);

	void removeFamatcat(Famatcat famatcat, String webAppName);

	List<Famatcat> getAll(String webAppName);

	void removeAll(String webAppName);

	Famatcat findById(FamatcatPK id, String webAppName)
			throws PersistenceException;

}
