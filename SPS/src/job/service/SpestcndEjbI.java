package job.service;

import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;

import job.model.BillDetail;
import job.model.ClosingJobInfo;
import job.model.Pcesthmt;
import job.model.Spestcnd;
import job.model.SpestcndPK;

//import job.model.Spestcnd;

public interface SpestcndEjbI {
	void createSpestcnd(Spestcnd  spestcnd);
	List<Spestcnd> getAll(String deptId);
	void updateSpestcnd(Spestcnd spestcnd)  ;
	void removeSpestcnd(Spestcnd spestcnd)  ;
	void removeAll() ;
	Spestcnd findById(SpestcndPK id) throws PersistenceException ;
	List<Spestcnd> findByContractorId(String contractorId, String deptId) throws PersistenceException ;
	List<Spestcnd> getJobList(String deptId, String status);
	List<Spestcnd> getJobList(String contractorId,String deptId, String status) throws PersistenceException ;
	List<Spestcnd> getJobList(String contractorId,String deptId, String status, String applicationType) throws PersistenceException ;
	List<Spestcnd> findByJobNo(String jobNo, String deptId) throws PersistenceException ;
	List<Spestcnd> getJobListByType(String deptId, String status, String applicationType) throws PersistenceException;
	List<String> getJobNoListByType(String deptId, String status, String applicationType) throws PersistenceException;
	List<String> getJobNoListByType(String deptId, String applicationType) throws PersistenceException;
	List<String> getJobNoListBySubType(String deptId, String status, String applicationType)	throws PersistenceException;
	List<Spestcnd> getJobListForLabour(String contractorId, String deptId,
			String status) throws PersistenceException;
	Spestcnd findByJobNo(String jobNo) throws PersistenceException;
	String createBillDetailAutoId(String[] selectedProjectNoList, String deptId, String userName, String billNoPrefix, String contractorId);
	BillDetail getBillNoByProjectNo(String projectNo );
	List<BillDetail> getBillDetailByBillNo(String billNo );
	List<BillDetail> getBillDetail(String deptId );
	List<String> getBillNoList(String deptId );
	void updateBillDetail(BillDetail billDetail );
	void removeBillDetail(BillDetail billDetail,String deptId );
	//void removeBillDetailList(List<String> removeList, String deptId );
	String getNextBillNo(String billNoPrefix );
	void changeStatusSpestcnd(String projectNo, String deptId, String status);
	void createBillDetail(BillDetail billDetail );
	List<String> getBillNoList(String deptId, Date fromDate, Date toDate);
	List<BillDetail> getBillDetail(String deptId, Date fromDate, Date toDate);
	List<String> getProjectNoListByBillNo(String billNo);
	void removeBillDetailListByBillNo(String billNo, String deptId);
	List<String> getJobNoListBySubTypeCM_OT(String deptId, String status,
			String applicationType) throws PersistenceException;
	//List<String> getJobList(String deptId, List<String> statusList) throws PersistenceException;
	//List<ClosingJobInfo> getPcesthmtList(String deptId, List<String> statusList ) throws PersistenceException;

}
