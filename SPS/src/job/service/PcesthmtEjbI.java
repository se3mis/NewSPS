package job.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.Approval;

import job.model.Pcesthmt;
import job.model.PcesthmtPK;

//import job.model.Pcesthmt;
//import job.model.PcesthmtPK;

public interface PcesthmtEjbI {
	void createPcesthmt(Pcesthmt pcesthmt );
	void updatePcesthmt(Pcesthmt pcesthmt )  ;
	void removePcesthmt(Pcesthmt pcesthmt )  ;
	void removeAll() ;
	List<Pcesthmt> getAll() ;
	Pcesthmt findById(PcesthmtPK id) throws PersistenceException;
	Pcesthmt findByEstimationNo(String estimateNo,String deptId);
	Pcesthmt findByJobNo(String jobNo,String deptId);
	List<String>  findJobNoList(String deptId, Long status);
	List<Pcesthmt> getJobApprovalList(String deptId, Long status, String value);
	List<String> getJobNoList(String deptId, Long status);
	List<Pcesthmt> getJobDetailList(String deptId, Long status);
	void changeStatusPcesthmt(String jobNo,String deptId, Long status);
	String approveJob(String jobNo, String deptId, String authorityLevel, String userName, String applType);
	List<Pcesthmt> getJobApprovalListNew(String deptId, String authorityLevel);
	List<String> getJobNoApprovalListNew(String deptId, String authorityLevel);
	void validateJob(Pcesthmt pcesthmt, Approval approval);
	void rejectJob(Pcesthmt pcesthtt, Approval approval);
	Pcesthmt findByJobNo(String jobNo);
	Pcesthmt findByEstimationNo(String estimateNo);
	int updateReviseApproveDetails(String jobNo, String deptId,Long status, String rejectedBy, Date rejectedDate,String approvedByEE, Date approvedEEDate, String approvedByCE,Date approvedCEDate, String approvedByDGM, Date approvedDGMDate,String rejectedReason);
	public List<String> findJobNosList(String deptId, List<Long> status);
	public List<String> findEstNosList(String deptId, List<Long> status);
}
