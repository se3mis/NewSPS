package fa.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import fa.model.Fadetail;
import fa.model.FadetailPK;

@Remote
public interface FadetailDaoRemote {
	void createFadetail(Fadetail fadetail, String webAppName);

	void updateFadetail(Fadetail fadetail, String webAppName);

	void removeFadetail(Fadetail fadetail, String webAppName);

	List<Fadetail> getAll(String webAppName);

	void removeAll(String webAppName);

	Fadetail findById(FadetailPK id, String webAppName)
			throws PersistenceException;

}
