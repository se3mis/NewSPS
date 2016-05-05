package estimate.service;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;


import estimate.ejb.SpstdesthmtDaoRemote;
import estimate.ejb.SpstrutmDaoRemote;
import estimate.model.Spstdesthmt;
import estimate.model.SpstdesthmtPK;

@Remote
public class SpstdesthmtEjb implements SpstdesthmtEjbI{
	private Context context;
	private SpstdesthmtDaoRemote dao;
	private String region=null;
	
	public SpstdesthmtEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}
	
	private SpstdesthmtDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpstdesthmtDaoRemote dao = (SpstdesthmtDaoRemote) context.lookup("SpstdesthmtDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void createSpstdesthmt(Spstdesthmt spstdesthmt, String webAppName) {
		dao.createSpstdesthmt(spstdesthmt, webAppName);
		
	}

	@Override
	public List<Spstdesthmt> getAll(String webAppName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateSpstdesthmt(Spstdesthmt spstdesthmt, String webAppName) {
		 dao.updateSpstdesthmt(spstdesthmt, webAppName);
		
	}

	@Override
	public void removeSpstdesthmt(Spstdesthmt spstdesthmt, String webAppName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAll(String webAppName) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public Spstdesthmt findById(SpstdesthmtPK id, String webAppName)
			throws PersistenceException {
		return dao.findById(id, webAppName);
	}

	@Override
	public List<String> loadStandEstmatenos(String costCenter,List<Long> status, String webAppName)
			throws PersistenceException {
		// TODO Auto-generated method stub
		return dao.loadStandEstmatenos(costCenter,status,null, webAppName);
	}

	@Override
	public List<Spstdesthmt> loadStandEstmateDetails(String costCenter,
			List<Long> status, String webAppName) throws PersistenceException {
		return dao.loadStandEstmateDetails(costCenter,status,null, webAppName);
	}

	@Override
	public boolean updateEstimateStatus(String estimateNumber,String costCenter,Long status,String approvedBy,Date approvedDate ,String rejectedBy,Date rejectedDate,String planningByPE,Date planningDatePE,String valiadteByEE,Date valiadteDateEE,String valiadteByCE,Date valiadteDateCE,String rejectedReasonCE,String rejectedReasonPE,
			String rejectedReasonEE,String webAppName) throws PersistenceException{
		
		return dao.updateEstimateStatus(estimateNumber, costCenter, status,approvedBy,approvedDate,rejectedBy,rejectedDate,planningByPE,planningDatePE,valiadteByEE,valiadteDateEE,valiadteByCE,valiadteDateCE,rejectedReasonCE,rejectedReasonPE,rejectedReasonEE,webAppName);
		
	}

	@Override
	public int updateSendForCMStatus(String estimateNo, String deptId,
			Long status, String postDeptId, String webAppName)
			throws PersistenceException {
		return dao.updateSendForCMStatus(estimateNo, deptId, status,postDeptId,webAppName);
		
	}

	@Override
	public List<String> loadConsMaintenanceEstimaNos(String costCenter,
			List<Long> status, String webAppName) throws Exception {
		return dao.loadConsMaintenanceEstimaNos(costCenter, status,webAppName);
	}

	@Override
	public int updateAllocatedEstimateStatus(String estimateNumber,
			String postIdCenter, Long status, String webAppName)
			throws Exception {
		return dao.updateAllocatedEstimateStatus(estimateNumber,postIdCenter, status,webAppName);
	}

	@Override
	public List<String> loadStandEstmateDetailsType(String costCenter,
			List<Long> status, String userName, String applicationType,
			String webAppName) throws PersistenceException {
		// TODO Auto-generated method stub
		return dao.loadStandEstmateDetailsType(costCenter,status,userName,applicationType, webAppName);
		
	}

}
