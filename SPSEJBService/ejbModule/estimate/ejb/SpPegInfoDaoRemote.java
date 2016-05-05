package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.SpPegInfo;
import estimate.model.SpPegInfoPK;
import estimate.model.Spnorms;
import estimate.model.SpnormsPK;

/**
 * Author: Dinusha Nirmalie
 * Created: March 01, 2012 11:23:42 AM
 */
@Remote
public interface SpPegInfoDaoRemote {
	void createSpPegInfo(SpPegInfo spPegInf, String webAppName)throws PersistenceException;
	List<SpPegInfo> getAll(String webAppName)throws PersistenceException;
	void updateSpPegInfo(SpPegInfo spPegInfo, String webAppName)  ;
	void removeSpPegInfo(SpPegInfo spPegInfo, String webAppName)  ;
	void removeAll(String webAppName);
	SpPegInfo findById(SpPegInfoPK id, String webAppName) throws PersistenceException ;
	
	public List<SpPegInfo> getPegChildrensByParentId(String parentId,String masterId,String webAppName) throws PersistenceException;
	public void updateDescription(String id,String name,String masterId,
			String webAppName) throws PersistenceException;
	public void deletePegItem(String id,String masterId,String webAppName) throws PersistenceException;
}
