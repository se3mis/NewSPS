package fa.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import fa.model.Faactcat;

@Remote
public interface FaactcatDaoRemote {
	void createFaactcat(Faactcat faactcat, String webAppName);

	void updateFaactcat(Faactcat faactcat, String webAppName);

	void removeFaactcat(Faactcat faactcat, String webAppName);

	List<Faactcat> getAll(String webAppName);

	void removeAll(String webAppName);

	Faactcat findById(String faCatCode, String webAppName)
			throws PersistenceException;

}
