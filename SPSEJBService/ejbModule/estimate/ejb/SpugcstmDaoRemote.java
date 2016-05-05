package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.Spugcstm;
import estimate.model.SpugcstmPK;

@Remote
public interface SpugcstmDaoRemote {
	void createSpugcstm(Spugcstm spugcstm, String webAppName);
	List<Spugcstm> getAll(String webAppName) ;
	void updateSpugcstm(Spugcstm spugcstm, String webAppName)  ;
	void removeSpugcstm(Spugcstm spugcstm, String webAppName)  ;
	void removeAll(String webAppName);
	Spugcstm findById(SpugcstmPK id, String webAppName) throws PersistenceException ;
	List<Spugcstm> getAll(String deptId, String webAppName);
	
	

}
