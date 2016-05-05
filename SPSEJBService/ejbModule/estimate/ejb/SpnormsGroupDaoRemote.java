package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.SpNormsGroup;
import estimate.model.Spnorms;
import estimate.model.SpnormsPK;


@Remote
public interface SpnormsGroupDaoRemote {
	void createSpnormsGroup(SpNormsGroup spnorms, String webAppName);
	List<SpNormsGroup> getAll(String webAppName) ;
	void updateSpnormsGroup(SpNormsGroup spnorms, String webAppName)  ;
	void removeSpnormsGroup(SpNormsGroup spnorms, String webAppName)  ;
	void removeAll(String webAppName);
	SpNormsGroup findById(SpNormsGroup id, String webAppName) throws PersistenceException ;
	
	List<SpNormsGroup> getChildrensByParentId(String parentId,
			String webAppName);

}
