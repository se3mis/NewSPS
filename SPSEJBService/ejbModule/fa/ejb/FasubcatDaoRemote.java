package fa.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import fa.model.Fasubcat;

@Remote
public interface FasubcatDaoRemote {
	void createFasubcat(Fasubcat fasubcat, String webAppName);

	void updateFasubcat(Fasubcat fasubcat, String webAppName);

	void removeFasubcat(Fasubcat fasubcat, String webAppName);

	List<Fasubcat> getAll(String webAppName);

	void removeAll(String webAppName);

	Fasubcat findById(String subCatCode, String webAppName)
			throws PersistenceException;

}
