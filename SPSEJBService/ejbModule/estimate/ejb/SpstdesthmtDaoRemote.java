package estimate.ejb;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;


import estimate.model.Spstdesthmt;
import estimate.model.SpstdesthmtPK;

@Remote
public interface SpstdesthmtDaoRemote {
	void createSpstdesthmt(Spstdesthmt spstdesthmt , String webAppName);
	List<Spstdesthmt> getAll(String webAppName) ;
	void updateSpstdesthmt(Spstdesthmt spstdesthmt , String webAppName)  ;
	void removeSpstdesthmt(Spstdesthmt spstdesthmt , String webAppName)  ;
	void removeAll(String webAppName) ;
	Spstdesthmt findById(SpstdesthmtPK id, String webAppName) throws PersistenceException;
	public List<String> loadStandEstmatenos(String costCenter,List<Long> status,String userName,String webAppName) throws PersistenceException;
	public List<Spstdesthmt> loadStandEstmateDetails(String costCenter,List<Long> status,String userName,String webAppName) throws PersistenceException;
	public List<Spstdesthmt> loadStandEstmateDetails(String costCenter,List<Long> status,String userName,String appType,String webAppName) throws PersistenceException;
	public List<String> loadStandEstmateDetailsType(String costCenter,List<Long> status,String userName,String applicationType,String webAppName) throws PersistenceException;
	public boolean updateEstimateStatus(String estimateNumber,String costCenter,Long status,String approvedBy,Date approvedDate ,String rejectedBy,Date rejectedDate,String planningByPE,Date planningDatePE,String valiadteByEE,Date valiadteDateEE,String valiadteByCE,Date valiadteDateCE,String rejectedReasonCE,String rejectedReasonPE,
			String rejectedReasonEE,String webAppName) throws PersistenceException;
	public int updateSendForCMStatus(String estimateNo,String deptId,Long status,String postDeptId,String webAppName) throws PersistenceException;
	public List<String> loadConsMaintenanceEstimaNos(String costCenter,List<Long> status,String webAppName) throws PersistenceException;
	public int updateAllocatedEstimateStatus(String estimateNumber,String postIdCenter, Long status, String webAppName)throws PersistenceException;
	public List<Spstdesthmt> loadConMainSentList(String costCenter,String postId,List<Long> status,String webAppName) throws PersistenceException;
	public int updateEstimateStatus(String estimateNumber,
			String deptId, Long status, String webAppName)
			throws PersistenceException ;
	
	//public void loadStandEst(String costCenter,List<Long> status,String userName,String webAppName) throws PersistenceException{
}
