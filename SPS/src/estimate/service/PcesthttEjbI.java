package estimate.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.Approval;
import estimate.model.Pcesthtt;
import estimate.model.PcesthttPK;
import estimate.model.ReferenceDeleteInfo;

//import estimate.model.Pcesthtt;
//import estimate.model.PcesthttPK;
//import estimate.model.ReferenceDeleteInfo;

public interface PcesthttEjbI {
	void createAPcesthtt(Pcesthtt pcesthtt);
	List<Pcesthtt> getAll() ;
	void updatePcesthtt(Pcesthtt pcesthtt);
	void removePcesthtt(Pcesthtt pcesthtt);
	//void removeAll() ;
	Pcesthtt findById(PcesthttPK id) throws PersistenceException;
	Pcesthtt findById(String estimateNo,String deptId,Long revNo);
	//List<Pcesthtt> getJobinfo(BigDecimal status) ;
	Pcesthtt  findByEstimationNo(String estimateNo,String deptId);
	List<String> getEstimateNoList(String deptId);
	List<Pcesthtt> getEstimateList(String deptId,Long status);
	List<String> getEstimateNoList(String deptId, Long status);
	//List<String> getEstimateNoList(String deptId, BigDecimal status, String applicationType);
	//List<String> getEstimateNoList(String deptId, BigDecimal status, String applicationType, String applicationSubType);
	List<ReferenceDeleteInfo> getReferenceInfoList(String deptId, Long status);
	//List<Pcesthtt> getEstApprovalList(String deptId,BigDecimal status, String value);
	long getHttRevNo(String estimateNo, String deptId);
	void changeStatusPcesthtt(String estimateNo, String deptId, Long status);
	List<Pcesthtt> getEstApprovalListNew(String deptId, String value);
	//List<String> getEstNoApprovalListNew(String deptId, String value);
	//List<String> getEstNoApprovalListNew(String deptId, String value, String applicationType);
	//List<String> getEstNoApprovalListNew(String deptId, String value, String applicationType, String applicationSubType);
	String  approveEstimate(String estimateNo, String deptId, String authorityLevel, String userName,String assignedUserName,String applType,String comment);//used
	List<String> getEstimateNoListT(String deptId, Long status, String applicationType);//used
	List<String> getEstimateNoListST(String deptId, Long status, String applicationType, String applicationSubType);//used
	List<String> getEstNoApprovalListNewT(String deptId, String authorityLevel, String applicationType);//used
	List<String> getEstNoApprovalListNewST(String deptId, String authorityLevel, String applicationType, String applicationSubType);//used
	void validateEstimate(Pcesthtt pcesthtt, Approval approval);
	void rejectEstimate(Pcesthtt pcesthtt, Approval approval);
	List<Pcesthtt> getEstimateList(String deptId, Long revNo, Long status);
	List<String> getEstimateNoList(String deptId, Long revNo, Long status);
	Pcesthtt findByEstimationNo(String estimateNo); 
	List<String> getApprovedEst(String deptId, String applicationType,String approvedBy, String toStatus, String status); 
	void cancelApproval(String estimateNo, String deptId, Approval approval); 
	List<String> getEstimateNoListMT_SA(String deptId, Long status);
	List<String> getEstNoApprovalListNewMT_SA(String deptId, String authorityLevel); 
	String approveEstimateMA_SA(String estimateNo, String deptId,
			String authorityLevel, String userName); 
	List<String> getApprovedEstMA_SA(String deptId, String approvedBy,
			String toStatus, String status);
	public Pcesthtt findByEstimationNo(String estimateNo,String deptId,Long revNo);
	public int updateEstimateDetails(String estimateNo, String deptId,
			BigDecimal status, String rejectedBy, Date rejectedDate,
			String approvedByEE, Date approvedEEDate, String approvedByCE,
			Date approvedCEDate, String approvedByDGM, Date approvedDGMDate,String rejectedReason,
			String webAppName);
	public List<Pcesthtt> getEstimateList(String deptId, Long revNo,
			List<Long> status, String userId);
	List<String> getEstimateNoList(String deptId, List<Long> status,
			String username) ;
	String recommendEstimate(String estimateNo, String deptId,
			String authorityLevel, String userName, String assignedUserName,
			String applicationType,String comment) ;
	List<String> getEstimateNoList(String deptId, Long revNo,
			List<Long> status, String userId);
	long getMinHttRevNo(String estimateNo, String deptId);
	public String getNextEstimateNo(String estimateNoPrefix, String deptId,
			String webAppName) ;
	public long getMaxHttRevNo(String estimateNo,String deptId, String webAppName);
}
