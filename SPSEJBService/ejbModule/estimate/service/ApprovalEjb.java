package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import estimate.ejb.ApprovalDaoRemote;
import estimate.model.Approval;

public class ApprovalEjb implements ApprovalDaoRemote{
	private Context context;
	private ApprovalDaoRemote dao; 
	public ApprovalEjb() {
		super();
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createAutoIdApprovals(Approval approval, String webAppName) {
		dao.createAutoIdApprovals(approval, webAppName);
		
	}

	@Override
	public List<Approval> findByReferenceNo(String referenceNo,
			String webAppName) {
		return dao.findByReferenceNo(referenceNo, webAppName);
	}

	@Override
	public void createAutoIdSEstmateApprovals(Approval approval,
			String webAppName) {
		dao.createAutoIdSEstmateApprovals(approval, webAppName);
		
	}

	@Override
	public void createAutoIdConstructionEstimateApprovals(Approval approval,
			String webAppName) {
		dao.createAutoIdConstructionEstimateApprovals(approval, webAppName);
		
	}

}
