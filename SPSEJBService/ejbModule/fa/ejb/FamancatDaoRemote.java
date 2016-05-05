package fa.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import fa.model.Famancat;

@Remote
public interface FamancatDaoRemote {
	void createFamancat(Famancat famancat, String webAppName);
	void updateFamancat(Famancat famancat, String webAppName);
	void removeFamancat(Famancat famancat, String webAppName);
	List<Famancat> getAll(String webAppName);
	void removeAll(String webAppName);
	Famancat findById(String catCode, String webAppName) throws PersistenceException;

}
