package job.ejb;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.Approval;

import job.model.Pcesthmt;
import job.model.PcesthmtPK;


@Remote
public interface PcesthmtDaoRemote {
	void createPcesthmt(Pcesthmt pcesthmt , String webAppName);
	void updatePcesthmt(Pcesthmt pcesthmt , String webAppName)  ;
	void removePcesthmt(Pcesthmt pcesthmt , String webAppName)  ;
	void removeAll(String webAppName) ;
	List<Pcesthmt> getAll(String webAppName) ;
	Pcesthmt findById(PcesthmtPK id, String webAppName) throws PersistenceException;
	Pcesthmt findByEstimationNo(String projectNo,String deptId, String webAppName);
	Pcesthmt findByJobNo(String jobNo,String deptId, String webAppName);
	List<String>  findJobNoList(String deptId, Long status, String webAppName);
	List<Pcesthmt> getJobApprovalList(String deptId,Long status, String value, String webAppName);
	List<String> getJobNoList(String deptId, Long status, String webAppName);
	List<Pcesthmt> getJobDetailList(String deptId, Long status, String webAppName);
	void changeStatusPcesthmt(String jobNo,String deptId, Long status, String webAppName);
	List<String> getNonAllocatedJobNoList(String deptId, Long status,List<String> list, String webAppName);
	List<BigDecimal> getNonAllocatedAmountList(String deptId, Long status,List<String> list, String webAppName);
	void updateRevNo(Pcesthmt pcesthmt, Long newRevNo, String webAppName);
	List<Pcesthmt> getAll(String deptId, String webAppName);
	String approveJob(String jobNo, String deptId, String authorityLevel, String userName, String appType, String webAppName);
	List<Pcesthmt> getJobApprovalListNew(String deptId, String authorityLevel, String webAppName);
	List<String> getJobNoApprovalListNew(String deptId, String authorityLevel, String webAppName);
	String getNextJobNo(String jobNoPrefix, String webAppName);
	void validateJob(Pcesthmt pcesthmt, Approval approval, String webAppName);
	void rejectJob(Pcesthmt pcesthtt, Approval approval, String webAppName);
	Pcesthmt findByJobNo(String jobNo, String webAppName);
	Pcesthmt findByEstimationNo(String estimateNo, String webAppName);
	List<String> getJobNoListST(String deptId, Long status,String applicationType, String applicationSubType, String webAppName);
	List<String> getJobNoApprovalListNewST(String deptId,String authorityLevel, String applicationType,String applicationSubType, String webAppName); 
	int updateReviseApproveDetails(String jobNo,String deptId,Long status, String rejectedBy ,Date rejectedDate,String approvedByEE ,Date approvedEEDate,String approvedByCE ,Date approvedCEDate,String approvedByDGM ,Date approvedDGMDate,String rejectedReasion, String webAppName);
	List<Pcesthmt> getRevisedJobDetailList(String deptId,List<Long> status, String userId, String webAppName);
	public List<String> findJobNosList(String deptId, List<Long> status, String webAppName);
	public List<String> findEstNosList(String deptId, List<Long> status, String webAppName);

}
