package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;

import estimate.model.Approval;

@Remote
public interface ApprovalDaoRemote {
	//void createApprovals(Approval approval , String webAppName);

	void createAutoIdApprovals(Approval approval, String webAppName);
	List<Approval> findByReferenceNo(String referenceNo, String webAppName);
	void createAutoIdSEstmateApprovals(Approval approval, String webAppName);
	void createAutoIdConstructionEstimateApprovals(Approval approval, String webAppName);

}
