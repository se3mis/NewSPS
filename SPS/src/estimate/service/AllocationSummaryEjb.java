package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.AllocationSummaryDaoRemote;
import estimate.ejb.ApprovalDaoRemote;
import estimate.model.AllocationSummary;
import estimate.model.Approval;

public class AllocationSummaryEjb implements AllocationSummaryEjbI {
	private String region=null;
	private Context context;
	private AllocationSummaryDaoRemote dao; 
	public AllocationSummaryEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}

	private AllocationSummaryDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 AllocationSummaryDaoRemote dao = (AllocationSummaryDaoRemote) context.lookup("AllocationSummaryDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void createAllocationSummary(AllocationSummary allocationSummary,
			String webAppName) {
		dao.createAllocationSummary(allocationSummary, webAppName);
		
	}

	@Override
	public List<AllocationSummary> getAll(String webAppName) {
		return dao.getAll( webAppName);
	}

	@Override
	public void updateAllocationSummary(AllocationSummary allocationSummary,
			String webAppName) {
		dao.updateAllocationSummary(allocationSummary, webAppName);
		
	}

	@Override
	public void removeAllocationSummary(AllocationSummary allocationSummary,
			String webAppName) {
		dao.removeAllocationSummary(allocationSummary, webAppName);
	}

	@Override
	public void removeAll(String webAppName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AllocationSummary findById(AllocationSummary id, String webAppName)
			throws PersistenceException {
		return dao.findById(id, webAppName);
	}

	@Override
	public void createAutoIdAllocationSummary(
			AllocationSummary allocationSummary, String webAppName) {
		 dao.createAutoIdAllocationSummary(allocationSummary,webAppName);
		
	}

}
