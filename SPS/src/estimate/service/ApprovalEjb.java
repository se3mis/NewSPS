package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import estimate.ejb.ApprovalDaoRemote;
import estimate.model.Approval;

public class ApprovalEjb implements ApprovalEjbI {
	private String region=null;
	private Context context;
	private ApprovalDaoRemote dao; 
	public ApprovalEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}

	private ApprovalDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 ApprovalDaoRemote dao = (ApprovalDaoRemote) context.lookup("ApprovalDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	@Override
	public void createAutoIdApprovals(Approval approval) {
		dao.createAutoIdApprovals(approval, region);
		
	}
	@Override
	public void createAutoIdSEstmateApprovals(Approval approval) {
		dao.createAutoIdSEstmateApprovals(approval, region);
		
	}

	@Override
	public List<Approval> findByReferenceNo(String referenceNo) {
		return dao.findByReferenceNo(referenceNo, region);
	}

}
