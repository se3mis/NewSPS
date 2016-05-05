package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.AllocationSummary;
import estimate.model.Spnorms;
import estimate.model.SpnormsPK;


@Remote
public interface AllocationSummaryDaoRemote {
	void createAllocationSummary(AllocationSummary allocationSummary, String webAppName);
	List<AllocationSummary> getAll(String webAppName) ;
	void updateAllocationSummary(AllocationSummary spnorms, String webAppName)  ;
	void removeAllocationSummary(AllocationSummary spnorms, String webAppName)  ;
	void removeAll(String webAppName);
	AllocationSummary findById(AllocationSummary id, String webAppName) throws PersistenceException ;
	
	void createAutoIdAllocationSummary(AllocationSummary allocationSummary, String webAppName);
}
