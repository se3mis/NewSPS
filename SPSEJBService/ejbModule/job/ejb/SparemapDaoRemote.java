package job.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import job.model.Sparemap;

@Remote
public interface SparemapDaoRemote {
	void createSparemap(Sparemap  sparemap, String webAppName);
	List<Sparemap> getAll(String webAppName);
	void updateSparemap(Sparemap sparemap, String webAppName)  ;
	void removeSparemap(Sparemap sparemap, String webAppName)  ;
	void removeAll(String webAppName) ;
	Sparemap findById(String smcAreaCode, String webAppName) throws PersistenceException ;

}
