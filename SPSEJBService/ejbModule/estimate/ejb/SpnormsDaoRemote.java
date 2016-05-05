package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.Spnorms;
import estimate.model.SpnormsPK;


@Remote
public interface SpnormsDaoRemote {
	void createSpnorms(Spnorms spnorms, String webAppName);
	List<Spnorms> getAll(String webAppName) ;
	void updateSpnorms(Spnorms spnorms, String webAppName)  ;
	void removeSpnorms(Spnorms spnorms, String webAppName)  ;
	void removeAll(String webAppName);
	Spnorms findById(SpnormsPK id, String webAppName) throws PersistenceException ;
	List<Spnorms> getChildrensByParentId(String parentId,
			String webAppName)throws PersistenceException ;
	
	

}
