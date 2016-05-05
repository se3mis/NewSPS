package estimate.ejb;
import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import estimate.model.SpPegInfo;
import estimate.model.SpPointdmt;
import estimate.model.Spnorms;
import estimate.model.SpnormsPK;

/**
 * Author: Dinusha Nirmalie
 * Created: March 01, 2012 11:23:42 AM
 */
@Local
public interface SpPointdmtDaoLocal {
	List<SpPointdmt> getAll(String webAppName)throws PersistenceException;
	//void updateSpPegInfo(SpPegInfo spPegInfo, String webAppName)  ;
	//void removeSpPegInfo(SpPegInfo spPegInfo, String webAppName)  ;
	//void removeAll(String webAppName);
	//SpPegInfo findById(SpPegInfoPK id, String webAppName) throws PersistenceException ;
	
	

}
