package fa.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import fa.model.Famatfea;
import fa.model.FamatfeaPK;

@Remote
public interface FamatfeaDaoRemote {
	void createFamatfea(Famatfea famatfea, String webAppName);

	void updateFamatfea(Famatfea famatfea, String webAppName);

	void removeFamatfea(Famatfea famatfea, String webAppName);

	List<Famatfea> getAll(String webAppName);

	void removeAll(String webAppName);

	Famatfea findById(FamatfeaPK id, String webAppName)
			throws PersistenceException;

}
