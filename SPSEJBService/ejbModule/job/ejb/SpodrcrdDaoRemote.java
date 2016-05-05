package job.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import job.model.Spodrcrd;
import job.model.SpodrcrdPK;

@Remote
public interface SpodrcrdDaoRemote {
	void createSpodrcrd(Spodrcrd spodrcrd , String webAppName);
	void updateSpodrcrd(Spodrcrd spodrcrd , String webAppName)  ;
	void removeSpodrcrd(Spodrcrd spodrcrd , String webAppName)  ;
	void removeAll(String webAppName);
	List<Spodrcrd> getAll(String deptId, String webAppName) ; 
	Spodrcrd findById(SpodrcrdPK id, String webAppName) throws PersistenceException;
	Spodrcrd findByJobNo(String jobNo, String webAppName);

}
