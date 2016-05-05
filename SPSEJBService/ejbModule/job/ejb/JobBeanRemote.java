package job.ejb;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import javax.persistence.PersistenceException;
import application.model.WiringInfo;
import estimate.model.Approval;
import estimate.model.EstimateDisplay;
import estimate.model.MaterialGrid;

import estimate.model.Pcfunddm;
import estimate.model.Spestlab;
import estimate.model.SpestlabPK;
import estimate.model.Speststd;
import job.model.FinishedJobInfo;
import job.model.ClosingJobInfo;
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

@Remote
public interface JobBeanRemote {
	List<JobInfo> getJobInfos(String deptId,Long status, String webAppName);
	List<String> jobCloser(List<String> list, String deptId, Date closingDate, String webAppName);
	//List<JobInfo> getJobInfos2(String deptId, BigDecimal status, String webAppName);
	List<MaterialGrid> getMaterialGrid(String estimateNo, String deptId, String webAppName);
	void insert(Pcesthmt pcesthmt, Speststd speststd, List<Pcestdmt> list, String webAppName);
	void update(Pcesthmt pcesthmt, Speststd speststd, List<Pcestdmt> addlist, List<Pcestdmt> updList, Long newRevNo, String webAppName);
	void delete(List<PcestdmtPK> list, Pcesthmt inPcesthmt, String webAppName);
	Speststd getSpeststd(String jobNo, String deptId, String webAppName) throws PersistenceException;
	StdDetail getStdDetail(String jobNo, String deptId, String webAppName) throws PersistenceException;
	WiringInfo getWiringInfo(String jobNo, String deptId , String webAppName);
	String jobCloser(String jobNo, String deptId, Date closingDate, String webAppName);
	List<Pcfunddm> getFundSourceList(String deptId, String webAppName);
	boolean isResTypeAvailable(String estimateNo, String deptId, Long year, Long month, String resType, String webAppName);
	void updateNewResType(Pcesthmt pcesthmt, List<Pcestdmt> addList, Long newRevNo, String webAppName);
	BigDecimal getResCat(String resType, String webAppName);
	String forceJobCloser(String deptId, String jobNo, Date closingDate, String webAppName);
	List<FinishedJobInfo> getFinishedJobsForBilling(String deptId, String  catCd,String webAppName);
	List<Pcesthmt> getJobApprovalReport(String userName, String deptId, Long status, String value, String webAppName);
	ClosingJobInfo getClosingJobInfo(String jobNo, String deptId, String webAppName);
	Boolean isMaterialIssued(String jobNo, String deptId,BigDecimal issueTo, BigDecimal status, String webAppName);
	List<PaySlipInfo> getPaySlipNoList(String deptId, String webAppName);
	List<String> getSoftClosingJobNoList(String deptId, String webAppName);
	void softJobCloser(List<String> list, String deptId, String webAppName);
	List<Pcesthmt> getJobApprovalReport(String userName, String deptId, String authorityLevel, String webAppName);
	TempConnInfo getTempConnInfos(String jobNo, String deptId, String webAppName);
	void alocateJob(List<Spestcnd> spsetCndList , Spestcnt spestcnt, String webAppName);
	void deAloocateJob(List<SpestcndPK> idList , Spestcnt spestcnt, String webAppName);
	void energizeJob(Spestsea spestsea, Spestcnd spestcnd , Spestcnt spestcnt, String webAppName);
	List<Spestlab> getLaborDetails (String jobNo, String deptId, String webAppName);
	void billContractor(Spestcnd spestcnd , Spestbip spestbip, String webAppName);
	List<Pcesthmt> getJobDetailListByDateRange(Date fromDate,Date toDate,String deptId, String webAppName);
	List<EstimateDisplay> getJobApprovalReportNew(String userName,
			String deptId, String authorityLevel, String webAppName);
	void validateJob(Pcesthmt pcesthmt, Approval approval, String webAppName);
	void rejectJob(Pcesthmt pcesthtt, Approval approval, String webAppName);
	void update(Pcesthmt pcesthmt, Speststd speststd, List<Pcestdmt> addlist,
			List<Pcestdmt> updList, Long newRevNo,
			List<Spestlab> insertLabList, List<Spestlab> updateLabList,
			String webAppName);
	void removeLabour(List<SpestlabPK> removeList, Pcesthmt pcesthmt,
			Pcestdmt pcestdmt, String webAppName);
	Boolean isMaterialIssued(String jobNo, String deptId, String webAppName);
	void removeMaterials(List<PcestdmtPK> list, Pcesthmt inPcesthmt,
			Speststd speststd, String webAppName);
	void removeLabour(List<SpestlabPK> removeList, Pcesthmt pcesthmt,
			List<Pcestdmt> pcestdmtList, Speststd speststd, String webAppName);
	List<MaterialGrid> getMaterialGridNew(String estimateNo, String deptId,
			String webAppName);
	
	public void updateReviseDetails(Pcesthmt pcesthmt, List<Pcestdmt> addlist, List<Pcestdmt> updList,List<Pcestdmt> deleList, String webAppName);
	public void updateRevisedEstimateCost(Pcesthmt pcesthmt,String estimateType, String deptId,
			String webAppName);
	public List<Pcesthmt> getRevisedJobDetailList(String deptId,
			List<Long> status, String userId, String webAppName);
	public void updateAdditionalCostForRevisejob(Pcesthmt pcesthmt ,String deptId,BigDecimal toCost,String webAppName);
	public void updateReviseDetailsWithRevNo(Pcesthmt pcesthmt, List<Pcestdmt> addlist, List<Pcestdmt> updList,List<Pcestdmt> deleList, String webAppName);
	List<JobInfo> getJobInformations(String deptId,Long status, String webAppName);
	public List<JobInfo> getJobInfomations(String deptId,String estimateNo, String webAppName);
	BigDecimal getTotalConsumerPayable(String estimateNo,String resType,String deptId,String webAppName) throws PersistenceException;
	void update(Pctrxhmt pctrxhmt,Pcesthmt pcesthmt,List<Pctrxdmt> addList, List<Pctrxdmt> upTrxList,  List<Pcestdmt> updList, Long newRevNo,
			String webAppName);

}
