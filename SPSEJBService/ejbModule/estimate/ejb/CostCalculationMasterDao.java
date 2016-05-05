package estimate.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import util.emSelect.EmSelector;

import estimate.model.Approval;
import estimate.model.CostCalculationMaster;
import estimate.model.CostCalculationMasterPK;
import estimate.model.SpPegInfo;
import estimate.model.SpPegInfoPK;
import estimate.model.Spnorms;
import estimate.model.SpnormsPK;
import estimate.model.Spstdesthmt;
import estimate.model.SpstdesthmtPK;

/**
 * Author: Dinusha Nirmalie
 * Created: March 01, 2012 11:23:42 AM
 */

@Stateless
public class CostCalculationMasterDao extends EmSelector implements CostCalculationMasterDaoRemote, CostCalculationMasterDaoLocal {

	 public CostCalculationMasterDao() {
	        // TODO Auto-generated constructor stub
	  }
	

	


	@Override
	public CostCalculationMaster findById(CostCalculationMasterPK id,
			String webAppName) throws PersistenceException {
		return getEntityManager(webAppName).find(CostCalculationMaster.class, id);
	}
	
	@Override
	public List<CostCalculationMaster> findByEstimateType(String estimateType,String deptId,
			String webAppName) throws PersistenceException {
		try{
			//String qryStr = "SELECT g FROM Spestmtm g WHERE g.id.wiringType = :wiringType AND g.id.phase = :phase AND g.id.connectionType = :connectionType AND g.id.fromLength = :fromLength AND g.id.toLength = :toLength";
			StringBuffer qryStr = new StringBuffer();
			qryStr.append("SELECT cost FROM CostCalculationMaster cost WHERE  cost.id.estimateType = :estimateType AND cost.id.deptId =:deptId AND  effectiveTo > SYSDATE AND effectiveFrom <= SYSDATE order by priority ASC");
			
			Query query = getEntityManager(webAppName).createQuery(qryStr.toString());
			
			query.setParameter("estimateType", estimateType);
			query.setParameter("deptId", deptId);
			
			List<CostCalculationMaster>  list = query.getResultList();	
			
		
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

   
}
