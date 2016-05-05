package job.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;

import application.model.WiringInfo;

import estimate.model.Approval;
import estimate.model.EstimateDisplay;
import estimate.model.MaterialGrid;
import estimate.model.Pcfunddm;
import estimate.model.Spestlab;
import estimate.model.SpestlabPK;
import estimate.model.Speststd;

import job.model.ClosingJobInfo;
import job.model.FinishedJobInfo;
import job.model.JobInfo;
import job.model.PaySlipInfo;
import job.model.Pcestdmt;
import job.model.PcestdmtPK;
import job.model.Pcesthmt;
import job.model.Pctrxdmt;
import job.model.Pctrxhmt;
import job.model.Spestbip;
import job.model.Spestcnd;
import job.model.SpestcndPK;
import job.model.Spestcnt;
import job.model.Spestsea;
import job.model.StdDetail;
import job.model.TempConnInfo;

public interface JobEjbI { 
	List<JobInfo> getJobInfos(String deptId,Long status);
	List<String> jobCloser(List<String> list, String deptId, Date closingDate);
	//List<JobInfo> getJobInfos2(String deptId, BigDecimal status);
	List<MaterialGrid> getMaterialGrid(String estimateNo, String deptId);
	List<MaterialGrid> getMaterialGridNew(String estimateNo, String deptId);
	void insert(Pcesthmt pcesthmt, Speststd speststd, List<Pcestdmt> list);
	void update(Pcesthmt pcesthmt, Speststd speststd, List<Pcestdmt> addlist, List<Pcestdmt> updList, Long newRevNo);
	void delete(List<PcestdmtPK> list, Pcesthmt inPcesthmt);
	Speststd getSpeststd(String jobNo, String deptId) throws PersistenceException;
	StdDetail getStdDetail(String jobNo, String deptId) throws PersistenceException;
	WiringInfo getWiringInfo(String jobNo, String deptId );
	String jobCloser(String jobNo, String deptId, Date closingDate);
	List<Pcfunddm> getFundSourceList(String deptId);
	boolean isResTypeAvailable(String estimateNo, String deptId, Long year, Long month, String resType);
	void updateNewResType(Pcesthmt pcesthmt, List<Pcestdmt> addList, Long newRevNo);
	BigDecimal getResCat(String resType);
	String forceJobCloser(String deptId, String jobNo, Date closingDate);
	List<FinishedJobInfo> getFinishedJobsForBilling(String deptId, String catCd);
	List<Pcesthmt> getJobApprovalReport(String userName, String deptId, Long status, String value);
	ClosingJobInfo getClosingJobInfo(String jobNo, String deptId);
	Boolean isMaterialIssued(String jobNo, String deptId,BigDecimal issueTo, Long status);
	Boolean isMaterialIssued(String jobNo, String deptId);
	List<PaySlipInfo> getPaySlipNoList(String deptId);
	List<String> getSoftClosingJobNoList(String deptId);
	void softJobCloser(List<String> list, String deptId);
	List<Pcesthmt> getJobApprovalReport(String userName, String deptId, String authorityLevel);
	TempConnInfo getTempConnInfos(String jobNo, String deptId);
	void alocateJob(List<Spestcnd> spsetCndList , Spestcnt spestcnt);
	void deAloocateJob(List<SpestcndPK> idList , Spestcnt spestcnt);
	void energizeJob(Spestsea spestsea, Spestcnd spestcnd , Spestcnt spestcnt);
	List<Spestlab> getLaborDetails (String jobNo, String deptId);
	void billContractor(Spestcnd spestcnd , Spestbip spestbip);
	List<Pcesthmt> getJobDetailListByDateRange(Date fromDate,Date toDate,String deptId);
	List<EstimateDisplay> getJobApprovalReportNew(String userName,
			String deptId, String authorityLevel);
	void validateJob(Pcesthmt pcesthmt, Approval approval);
	void rejectJob(Pcesthmt pcesthmt, Approval approval);
	void update(Pcesthmt pcesthmt, Speststd speststd, List<Pcestdmt> addlist,
			List<Pcestdmt> updList, Long newRevNo,
			List<Spestlab> insertLabList, List<Spestlab> updateLabList);
	void removeLabour(List<SpestlabPK> removeList, Pcesthmt pcesthmt, Pcestdmt pcestdmt);
	
	public void updateReviseDetails(Pcesthmt pcesthmt, List<Pcestdmt> addlist,
			List<Pcestdmt> updList, List<Pcestdmt> deleList)throws Exception;
	
	public void updateRevisedEstimateCost(Pcesthmt pcesthmt,String estimateType, String deptId);
	public List<Pcesthmt> getRevisedJobDetailList(String deptId,
			List<Long> status, String userId)throws Exception;
	public void updateAdditionalCostForRevisejob(Pcesthmt pcesthmt ,String deptId,BigDecimal toCost)throws Exception;
	public void updateReviseDetailsWithRevNo(Pcesthmt pcesthmt,
			List<Pcestdmt> addlist, List<Pcestdmt> updList,
			List<Pcestdmt> deleList)throws Exception;
	List<JobInfo> getJobInformations(String deptId, Long status);
	BigDecimal getTotalConsumerPayable(String estimateNo,
			String resType, String deptId);
	public List<JobInfo> getJobInfomations(String deptId, String estimateNo);
	void update(Pctrxhmt pctrxhmt , Pcesthmt pcesthmt,List<Pctrxdmt> addList,List<Pctrxdmt> upTrxList, List<Pcestdmt> updList, Long newRevNo);
	

}
