package job.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import job.model.Spestbip;
import job.model.SpestbipPK;

@Remote
public interface SpestbipDaoRemote {
	void createSpestbip(Spestbip  spestbip, String webAppName);
	List<Spestbip> getAll(String deptId, String webAppName);
	void updateSpestbip(Spestbip spestbip, String webAppName)  ;
	void removeSpestbip(Spestbip spestbip, String webAppName)  ;
	void removeAll(String webAppName) ;
	Spestbip findById(SpestbipPK id, String webAppName) throws PersistenceException ;

}
