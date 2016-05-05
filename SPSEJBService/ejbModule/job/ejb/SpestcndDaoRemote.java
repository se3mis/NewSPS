package job.ejb;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import job.model.BillDetail;
import job.model.Spestcnd;
import job.model.SpestcndPK;

@Remote
public interface SpestcndDaoRemote {
	void createSpestcnd(Spestcnd  spestcnd, String webAppName);
	List<Spestcnd> getAll(String deptId, String webAppName);
	void updateSpestcnd(Spestcnd spestcnd, String webAppName)  ;
	void removeSpestcnd(Spestcnd spestcnd, String webAppName)  ;
	void removeAll(String webAppName) ;
	Spestcnd findById(SpestcndPK id, String webAppName) throws PersistenceException ;
	List<Spestcnd> findByContractorId(String contractorId, String deptId, String webAppName) throws PersistenceException ;
	List<Spestcnd> getJobList(String deptId, String status, String webAppName) throws PersistenceException;
	List<Spestcnd> getJobList(String contractorId, String deptId, String status, String webAppName) throws PersistenceException;
	List<Spestcnd> getJobList(String contractorId,String deptId, String status, String applicationType, String webAppName) throws PersistenceException ;
	List<Spestcnd> findByJobNo(String jobNo, String deptId, String webAppName) throws PersistenceException ;
	//Spestcnd findByJobNo1(String jobNo, String deptId, String webAppName) ;
	List<String> getAllocatedJobNolist(String deptId, String webAppName);
	List<Spestcnd> getJobListByType(String deptId, String status, String applicationType, String webAppName) throws PersistenceException;
	List<String> getJobNoListByType(String deptId, String status, String applicationType, String webAppName) throws PersistenceException;
	List<String> getJobNoListByType(String deptId, String applicationType, String webAppName)
			throws PersistenceException;
	void updateIsExported(String jobNo, String deptId, String isExported, String webAppName);
	/*List<String> getJobListNoByType(String deptId, String status, String catCd,
			String webAppName) throws PersistenceException;*/
	List<String> getJobNoListBySubType(String deptId, String status,String applicationType, String webAppName)
			throws PersistenceException;
	void updateError(String jobNo, String deptId, String errorDesc,
			String webAppName);
	List<Spestcnd> getJobListForLabour(String contractorId, String deptId,
			String status, String webAppName) throws PersistenceException;
	Spestcnd findByJobNo(String jobNo, String webAppName)
			throws PersistenceException;
	String createBillDetailAutoId(String[] selectedProjectNoList, String deptId,
			String userName, String billNoPrefix, String contractorId,String webAppName);
	BillDetail getBillNoByProjectNo(String projectNo, String webAppName);
	List<BillDetail> getBillDetailByBillNo(String billNo, String webAppName);
	List<BillDetail> getBillDetail(String deptId, String webAppName);
	List<String> getBillNoList(String deptId, String webAppName);
	void updateBillDetail(BillDetail billDetail, String webAppName);
	void removeBillDetail(BillDetail billDetail,  String deptId,String webAppName);
	//void removeBillDetailList(List<String> removeList,  String deptId,String webAppName);
	String getNextBillNo(String billNoPrefix, String webAppName);
	void changeStatusSpestcnd(String projectNo, String deptId, String status,
			String webAppName);
	void createBillDetail(BillDetail billDetail, String webAppName);
	List<String> getBillNoList(String deptId, Date fromDate, Date toDate,
			String webAppName);
	List<BillDetail> getBillDetail(String deptId, Date fromDate, Date toDate,
			String webAppName);
	List<String> getProjectNoListByBillNo(String billNo, String webAppName);
	void removeBillDetailListByBillNo(String billNo, String deptId,
			String webAppName);
	List<String> getJobNoListBySubTypeCM_OT(String deptId, String status,
			String applicationType, String webAppName)
			throws PersistenceException;
	

}
