package estimate.service;

import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.AllocationSummary;
import estimate.model.Approval;

public interface AllocationSummaryEjbI {
	void createAllocationSummary(AllocationSummary allocationSummary, String webAppName);
	List<AllocationSummary> getAll(String webAppName) ;
	void updateAllocationSummary(AllocationSummary spnorms, String webAppName)  ;
	void removeAllocationSummary(AllocationSummary spnorms, String webAppName)  ;
	void removeAll(String webAppName);
	AllocationSummary findById(AllocationSummary id, String webAppName) throws PersistenceException ;
	
	void createAutoIdAllocationSummary(AllocationSummary allocationSummary, String webAppName);
}
