package estimate.ejb;
import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.SpPegInfo;
import estimate.model.SpPointdmt;
import estimate.model.SpPointdmtPK;
import estimate.model.Spnorms;
import estimate.model.SpnormsPK;

/**
 * Author: Dinusha Nirmalie
 * Created: March 01, 2012 11:23:42 AM
 */
@Remote
public interface SpPointdmtDaoRemote {
	//void createSpPegInfo(Spnorms spnorms, String webAppName);
	List<SpPointdmt> getAll(String webAppName)throws PersistenceException;
	//void updateSpPegInfo(SpPegInfo spPegInfo, String webAppName)  ;
	//void removeSpPegInfo(SpPegInfo spPegInfo, String webAppName)  ;
	//void removeAll(String webAppName);
	//SpPegInfo findById(SpPegInfoPK id, String webAppName) throws PersistenceException ;
	
	public List<SpPointdmt> getPegResourceById(String id,String masterId,String webAppName) throws PersistenceException;
	public void createSpPointdmt(SpPointdmt spPointdmt, String webAppName)throws PersistenceException;
	public void updateSpPointdmt(SpPointdmt spPointdmt, String webAppName)throws PersistenceException;
	public void removeSpPointdmt(SpPointdmt spPointdmt, String webAppName)throws PersistenceException;
	public SpPointdmt findById(SpPointdmtPK id, String webAppName)
	throws PersistenceException;
	public void updateSpPointdmt(String lineTypeId, String resCode, BigDecimal quantity , String masterId, String webAppName)throws PersistenceException;
	public void removeSpPointdmt(String lineTypeId, String resCode, String masterId, String webAppName)throws PersistenceException;
	//public List<String> loadResourceCodes(String resourType,String webAppName)throws PersistenceException;
	//public SpPointdmt loadResourceDetails(String resourType,String code,String webAppName)throws PersistenceException;
}
