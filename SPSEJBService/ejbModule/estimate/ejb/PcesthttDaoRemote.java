package estimate.ejb;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import job.model.JobRecord;

import com.model.App;

import costcenter.model.TestObject;

import estimate.model.Appestlim;
import estimate.model.Approval;
import estimate.model.Pcesthtt;
import estimate.model.PcesthttPK;
import estimate.model.ReferenceDeleteInfo;

@Remote
public interface PcesthttDaoRemote {
	void createPcesthtt(Pcesthtt pcesthtt, String webAppName);
	List<Pcesthtt> getAll(String webAppName) ;
	void updatePcesthtt(Pcesthtt pcesthtt, String webAppName)  ;
	void removePcesthtt(Pcesthtt pcesthtt, String webAppName)  ;
	void removeAll(String webAppName) ;
	Pcesthtt findById(PcesthttPK id, String webAppName) throws PersistenceException;
	Pcesthtt findById(String estimateNo,String deptId,Long revNo, String webAppName);
	List<Pcesthtt> getJobinfo(Long status, String webAppName) ;
	Pcesthtt  findByEstimationNo(String estimateNo,String deptId, String webAppName);
	List<String> getEstimateNoList(String webAppName);
	Pcesthtt getJobDetails1(Long status, String webAppName) ;
	List<App> getAppList(String webAppName);
	List<Pcesthtt> getEstimateList(String deptId,Long status, String webAppName);
	List<String> getEstimateNoList(String deptId, String webAppName);
	List<String> getEstimateNoList(String deptId, Long status, String webAppName);
	//List<String> getEstimateNoList(String deptId, BigDecimal status, String applicationType, String webAppName);
	List<ReferenceDeleteInfo> getReferenceInfoList(String deptId, Long status, String webAppName);
	List<JobRecord> getJobRecordInfoList(String deptId,Long status , String webAppName);
	List<Pcesthtt> getEstApprovalList(String deptId,Long status, String value, String webAppName);
	Pcesthtt  findByApplicationNo(String estimateNo,String deptId, String webAppName);
	List<TestObject> getEstimateNoList1(String deptId, String webAppName);
	long getHttRevNo(String estimateNo, String deptId, String webAppName);
	//List<Pcesthtt> getEstApprovalReport(String deptId, BigDecimal status, String value, String webAppName);
	void changeStatusPcesthtt(String estimateNo, String deptId, Long status, String webAppName);
	List<Pcesthtt> getEstApprovalListNew(String deptId, String authorityLevel, String webAppName);
	String approveEstimate(String estimateNo, String deptId, String authorityLevel, String userName, String assignedUserName,String applType,String comment,String webAppName);
	List<String> getEstNoApprovalListNew(String deptId, String authorityLevel, String webAppName);
	//List<String> getEstNoApprovalListNew(String deptId, String authorityLevel, String applicationType, String webAppName);
	void changeCostCenterNoPcesthtt(String estimateNo, String areaDeptId, String depotDeptId, String webAppName);
	//List<String> getEstimateNoList(String deptId, BigDecimal status, String applicationType, String applicationSubType, String webAppName);
	//List<String> getEstNoApprovalListNew(String deptId, String authorityLevel, String applicationType, String applicationSubType, String webAppName);
	List<String> getEstimateNoListT(String deptId, Long status, String applicationType, String webAppName);
	List<String> getEstimateNoListST(String deptId, Long status, String applicationType, String applicationSubType, String webAppName);
	List<String> getEstNoApprovalListNewT(String deptId, String authorityLevel,
			String applicationType, String webAppName);
	List<String> getEstNoApprovalListNewST(String deptId,
			String authorityLevel, String applicationType,
			String applicationSubType, String webAppName);
	void validateEstimate(Pcesthtt pcesthtt, Approval approval,
			String webAppName);
	void rejectEstimate(Pcesthtt pcesthtt, Approval approval, String webAppName);
	List<Pcesthtt> getEstimateList(String deptId, Long revNo,
			Long status, String webAppName);
	List<String> getEstimateNoList(String deptId, Long revNo,
			Long status, String webAppName);
	Appestlim findAppEstLimits(String costCenterNo, String approvalType,String applicationType,
			String userLevel, String webAppName);
	Pcesthtt findByEstimationNo(String estimateNo, String webAppName);
	List<String> getApprovedEst(String deptId,String applicationType,String approvedBy,
			String toStatus, String status, String webAppName);
	void cancelApproval(String estimateNo, String deptId, Approval approval, String webAppName);
	List<String> getEstimateNoListMT_SA(String deptId, Long status, String webAppName);
	List<String> getEstNoApprovalListNewMT_SA(String deptId, String authorityLevel, String webAppName);
	String approveEstimateMA_SA(String estimateNo, String deptId,
			String authorityLevel, String userName, String webAppName);
	List<String> getApprovedEstMA_SA(String deptId, String approvedBy,
			String toStatus, String status, String webAppName);
	List<Pcesthtt> getAll(String deptId, String webAppName); 
	public Pcesthtt findByEstimationNo(String estimateNo,String deptId,Long revNo, String webAppName);
	int updateEstimateDetails(String estimateNo,String deptId,Long status, String rejectedBy ,Date rejectedDate,String approvedByEE ,Date approvedEEDate,String approvedByCE ,Date approvedCEDate,String approvedByDGM ,Date approvedDGMDate,String rejectedReason, String webAppName);
	public List<Pcesthtt> getEstimateList(String deptId, Long revNo, List<Long> status,String userId, String webAppName);
	List<String> getEstimateNoList(String deptId, List<Long> status,String username, String webAppName);
	String recommendEstimate(String estimateNo, String deptId,	String authorityLevel, String userName, String assignedUserName, String applicationType,String comment,String webAppName) ;
	//public List<String> getEstimateNoList(String deptId, List<Long> status,String username, String webAppName);
	List<Pcesthtt> getEstimateListForVSLAuthorizedCC(List<String> deptIds, Long revNo, List<Long> status,String userId, String webAppName);
	public List<Pcesthtt> getEstimateListForAuthorizedCC(List<String> deptIds, Long revNo, List<Long> status,String userId,String likeEstimate, String webAppName);
	List<String> getEstimateNoList(String deptId, Long revNo, List<Long> status,String userId, String webAppName);
	long getMinHttRevNo(String estimateNo,String deptId, String webAppName);
	String getNextEstimateNo(String estimateNoPrefix,String deptId, String webAppName);
	public long getMaxHttRevNo(String estimateNo,String deptId, String webAppName);
		
}
