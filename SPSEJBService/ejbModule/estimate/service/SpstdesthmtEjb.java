package estimate.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpstdesthmtDaoRemote;

import estimate.model.Spstdesthmt;
import estimate.model.SpstdesthmtPK;

public class SpstdesthmtEjb implements SpstdesthmtDaoRemote{

	private Context context;
	private SpstdesthmtDaoRemote dao; 
	public SpstdesthmtEjb() {
		super();
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
		// TODO Auto-generated method stub
		
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
	public List<String> loadStandEstmatenos(String costCenter,List<Long> status,String userName, String webAppName)
			throws PersistenceException {
		// TODO Auto-generated method stub
		return dao.loadStandEstmatenos(costCenter,status,userName, webAppName);
	}

	@Override
	public List<Spstdesthmt> loadStandEstmateDetails(String costCenter,
			List<Long> status,String username, String webAppName) throws PersistenceException {
		return dao.loadStandEstmateDetails(costCenter,status,username, webAppName);
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
			List<Long> status, String webAppName) throws PersistenceException {
		// TODO Auto-generated method stub
		return dao.loadConsMaintenanceEstimaNos(costCenter, status,webAppName);
	}

	@Override
	public int updateAllocatedEstimateStatus(String estimateNumber,
			String postIdCenter, Long status, String webAppName)
			throws PersistenceException {
		return dao.updateAllocatedEstimateStatus(estimateNumber,postIdCenter, status,webAppName);
	}

	@Override
	public List<Spstdesthmt> loadConMainSentList(String costCenter,
			String postId, List<Long> status, String webAppName)
			throws PersistenceException {
		// TODO Auto-generated method stub
		return dao.loadConMainSentList(costCenter, postId, status, webAppName);
	}

	@Override
	public int updateEstimateStatus(String estimateNumber, String deptId,
			Long status, String webAppName) throws PersistenceException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Spstdesthmt> loadStandEstmateDetails(String costCenter,
			List<Long> status, String userName, String appType,
			String webAppName) throws PersistenceException {
		return dao.loadStandEstmateDetails(costCenter,status,userName,appType, webAppName);

	}

	@Override
	public List<String> loadStandEstmateDetailsType(String costCenter,
			List<Long> status, String userName, String applicationType,
			String webAppName) throws PersistenceException {
		// TODO Auto-generated method stub
		return dao.loadStandEstmateDetailsType(costCenter,status,userName,applicationType, webAppName);
	}

	
	
}
