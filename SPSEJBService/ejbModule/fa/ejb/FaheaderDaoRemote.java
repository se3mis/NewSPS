package fa.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import fa.model.Faheader;
import fa.model.FaheaderPK;

@Remote
public interface FaheaderDaoRemote {
	void createFaheader(Faheader faheader, String webAppName);

	void updateFaheader(Faheader faheader, String webAppName);

	void removeFaheader(Faheader faheader, String webAppName);

	List<Faheader> getAll(String webAppName);

	void removeAll(String webAppName);

	Faheader findById(FaheaderPK id, String webAppName)
			throws PersistenceException;

}
