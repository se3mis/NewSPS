package job.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;
import job.model.Spjobtcd;
//import job.model.SpjobtcdPK;
@Remote
public interface SpjobtcdDaoRemote {
	void createSpjobtcd(Spjobtcd  spjobtcd, String webAppName);
	List<Spjobtcd> getAll(String deptId, String webAppName);
	void updateSpjobtcd(Spjobtcd spjobtcd, String webAppName)  ;
	void removeSpjobtcd(Spjobtcd spjobtcd, String webAppName)  ;
	void removeAll(String webAppName) ;
	Spjobtcd findById(String jobNo, String deptId, String webAppName) throws PersistenceException ;

}
