package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.Splaborm;

@Remote
public interface SplabormDaoRemote {
	void createSplaborm(Splaborm splaborm, String webAppName);
	List<Splaborm> getAll(String webAppName) ;
	void updateSplaborm(Splaborm splaborm, String webAppName)  ;
	void removeSplaborm(Splaborm splaborm, String webAppName)  ;
	void removeAll(String webAppName);
	Splaborm findById(String labourcode, String webAppName) throws PersistenceException ;

}
