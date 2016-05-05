package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.CostCalculationMaster;
import estimate.model.CostCalculationMasterPK;
import estimate.model.SpPegInfo;
import estimate.model.SpPegInfoPK;
import estimate.model.Spnorms;
import estimate.model.SpnormsPK;

/**
 * Author: Dinusha Nirmalie
 * Created: March 01, 2012 11:23:42 AM
 */
@Remote
public interface CostCalculationMasterDaoRemote {
	
	CostCalculationMaster findById(CostCalculationMasterPK id, String webAppName) throws PersistenceException ;
	List<CostCalculationMaster> findByEstimateType(String estimateType,String deptId,
			String webAppName)throws PersistenceException ;
}
