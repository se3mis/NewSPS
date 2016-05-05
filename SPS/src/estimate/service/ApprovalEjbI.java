package estimate.service;

import java.util.List;

import estimate.model.Approval;

public interface ApprovalEjbI {
	void createAutoIdApprovals(Approval approval);
	List<Approval> findByReferenceNo(String referenceNo);
	void createAutoIdSEstmateApprovals(Approval approval);
}
