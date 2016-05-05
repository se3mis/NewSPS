package job.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import job.model.Spestsea;
import job.model.SpestseaPK;

@Remote
public interface SpestseaDaoRemote {
	void createSpestsea(Spestsea  spestsea, String webAppName);
	List<Spestsea> getAll(String deptId, String webAppName);
	void updateSpestsea(Spestsea spestsea, String webAppName)  ;
	void removeSpestsea(Spestsea spestsea, String webAppName)  ;
	void removeAll(String webAppName) ;
	Spestsea findById(SpestseaPK id, String webAppName) throws PersistenceException ;
	List<Spestsea> getSealNoList(String jobNo, String deptId, String webAppName) throws PersistenceException;
	Spestsea findByJobNo(String jobNo, String webAppName);

}
