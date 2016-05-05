package job.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import job.model.Pcrstypm;

@Remote
public interface PcrstypmDaoRemote {
	void createPcrstypm(Pcrstypm Pcrstypm, String webAppName);
	public List<String> loadResourceTypes(String webAppName)throws PersistenceException;
	public List<Pcrstypm> isResourceTypeExist(String resourType,String webAppName)throws PersistenceException;
}
