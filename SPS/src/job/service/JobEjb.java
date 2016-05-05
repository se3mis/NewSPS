package job.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import application.model.WiringInfo;

import estimate.model.Approval;
import estimate.model.EstimateDisplay;
import estimate.model.MaterialGrid;
import estimate.model.Pcfunddm;
import estimate.model.Spestlab;
import estimate.model.SpestlabPK;
import estimate.model.Speststd;

import job.ejb.JobBeanRemote;
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

public class JobEjb implements JobEjbI{
	

	private Context context;
	private JobBeanRemote bean; 
	private String region=null;
	
	public JobEjb(String region) {
		super();
		this.region=region;
		this.bean=lookupDao();
		
	}

	private JobBeanRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 JobBeanRemote bean = (JobBeanRemote) context.lookup("JobBean/remote");
			 return bean; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	

	@Override
	public List<JobInfo> getJobInfos(String deptId, Long status) {
		return bean.getJobInfos(deptId, status, region);
	}
	
	@Override
	public List<MaterialGrid> getMaterialGrid(String estimateNo, String deptNo) {
		return bean.getMaterialGrid(estimateNo, deptNo, region);
	}
	
	@Override
	public void delete(List<PcestdmtPK> list, Pcesthmt pcesthmt) {
		bean.delete(list, pcesthmt, region);
		
	}

	@Override
	public void insert(Pcesthmt pcesthmt, Speststd speststd, List<Pcestdmt> list) {
		bean.insert(pcesthmt, speststd, list, region);
		
	}

	@Override
	public void update(Pcesthmt pcesthmt, Speststd speststd, List<Pcestdmt> addList,
			List<Pcestdmt> updList , Long newRevNo) {
		bean.update(pcesthmt, speststd, addList, updList, newRevNo, region);
		
	}
	
	@Override
	public Speststd getSpeststd(String jobNo, String deptNo) throws PersistenceException {
		return bean.getSpeststd(jobNo, deptNo, region);
	}
	
	@Override
	public StdDetail getStdDetail(String jobNo, String deptId) throws PersistenceException {
		return bean.getStdDetail(jobNo, deptId, region);
	}

	@Override
	public WiringInfo getWiringInfo(String jobNo, String deptId) {
		return bean.getWiringInfo(jobNo, deptId, region);
	}

	@Override
	public List<String> jobCloser(List<String> list, String deptId, Date closingDate) {
		return bean.jobCloser(list, deptId, closingDate, region);
	}

	@Override
	public String jobCloser(String jobNo, String deptId, Date closingDate) {
		return bean.jobCloser(jobNo, deptId, closingDate, region);
	}

	@Override
	public List<Pcfunddm> getFundSourceList(String deptId) {
		return bean.getFundSourceList(deptId, region);
	}
	
	@Override
	public boolean isResTypeAvailable(String estimateNo,String deptId, Long year, Long month, String resType) {
		return bean.isResTypeAvailable(estimateNo, deptId, year, month, resType, region);
	}

	

	@Override
	public BigDecimal getResCat(String resType) {
		return bean.getResCat(resType, region);
	}

	@Override
	public void updateNewResType(Pcesthmt pcesthmt, List<Pcestdmt> addlist, Long newRevNo) {
		bean.updateNewResType(pcesthmt, addlist, newRevNo, region);
		
	}

	@Override
	public String forceJobCloser(String deptId, String jobNo, Date closingDate) {
		return bean.forceJobCloser(deptId, jobNo, closingDate, region);
	}

	@Override
	public List<FinishedJobInfo> getFinishedJobsForBilling(String deptId, String catCd) {
		return bean.getFinishedJobsForBilling(deptId, catCd, region);
	}

	@Override
	public List<Pcesthmt> getJobApprovalReport(String userName, String deptId, Long status, String value) {
		return bean.getJobApprovalReport(userName, deptId, status, value, region);
	}

	@Override
	public ClosingJobInfo getClosingJobInfo(String jobNo, String deptId) {
		return bean.getClosingJobInfo(jobNo, deptId, region);
	}

	

	@Override
	public List<PaySlipInfo> getPaySlipNoList(String deptId) {
		return bean.getPaySlipNoList(deptId, region);
	}

	@Override
	public List<String> getSoftClosingJobNoList(String deptId) {
		return bean.getSoftClosingJobNoList(deptId, region);
	}

	@Override
	public void softJobCloser(List<String> list, String deptId) {
		bean.softJobCloser(list, deptId, region);
		
	}

	@Override
	public List<Pcesthmt> getJobApprovalReport(String userName, String deptId, String authorityLevel) {
		return bean.getJobApprovalReport(userName, deptId, authorityLevel, region);
	}

	@Override
	public TempConnInfo getTempConnInfos(String jobNo, String deptId) {
		return bean.getTempConnInfos(jobNo, deptId, region);
	}

	@Override
	public void alocateJob(List<Spestcnd> spsetCndList , Spestcnt spestcnt) {
		bean.alocateJob(spsetCndList, spestcnt, region);
		
	}

	@Override
	public void deAloocateJob(List<SpestcndPK> idList , Spestcnt spestcnt) {
		bean.deAloocateJob(idList, spestcnt, region);
		
	}

	@Override
	public void energizeJob(Spestsea spestsea, Spestcnd spestcnd , Spestcnt spestcnt) {
		bean.energizeJob(spestsea, spestcnd, spestcnt, region);
		
	}

	@Override
	public List<Spestlab> getLaborDetails(String jobNo, String deptId) {
		return bean.getLaborDetails(jobNo, deptId, region);
	}

	@Override
	public void billContractor(Spestcnd spestcnd , Spestbip spestbip) {
		bean.billContractor(spestcnd, spestbip, region);
		
	}

	@Override
	public List<Pcesthmt> getJobDetailListByDateRange(Date fromDate,
			Date toDate, String deptId) {
		return bean.getJobDetailListByDateRange(fromDate, toDate, deptId, region);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JobEjb ejb=new JobEjb("R4");
		//ejb.jobCloser(arg0)
		System.out.println(ejb.getJobApprovalReportNew("511EE", "511.00", "CE"));
		//System.out.println((ejb.getJobApprovalReport(userName, deptId, status, value)),
		System.out.println(ejb.getWiringInfo("030058", "510.20"));
		System.out.println(ejb.getJobInfos("510.20", new Long("1")));
		//System.out.println(ejb.getStdDetail("030058", "510.20"));
		System.out.println(ejb.getTempConnInfos("060298", "510.20"));

	}

	@Override
	public List<EstimateDisplay> getJobApprovalReportNew(String userName,
			String deptId, String authorityLevel) { 
		return bean.getJobApprovalReportNew(userName, deptId, authorityLevel, region);
	}

	@Override
	public void validateJob(Pcesthmt pcesthmt, Approval approval) {
		bean.validateJob(pcesthmt, approval, region);
		
	}

	@Override
	public void rejectJob(Pcesthmt pcesthmt, Approval approval) {
		bean.rejectJob(pcesthmt, approval, region);
		
	}

	@Override
	public void update(Pcesthmt pcesthmt, Speststd speststd,
			List<Pcestdmt> addlist, List<Pcestdmt> updList, Long newRevNo,
			List<Spestlab> insertLabList, List<Spestlab> updateLabList) {
		bean.update(pcesthmt, speststd, addlist, updList, newRevNo, insertLabList, updateLabList, region);
		
	}

	@Override
	public void removeLabour(List<SpestlabPK> removeList, Pcesthmt pcesthmt,
			Pcestdmt pcestdmt) {
		bean.removeLabour(removeList, pcesthmt, pcestdmt, region);
		
	}

	@Override
	public Boolean isMaterialIssued(String jobNo, String deptId) {
		return bean.isMaterialIssued(jobNo, deptId, region);
	}
	

	@Override
	public void updateReviseDetails(Pcesthmt pcesthmt, List<Pcestdmt> addlist,
			List<Pcestdmt> updList, List<Pcestdmt> deleList) throws Exception {
		bean.updateReviseDetails(pcesthmt, addlist,updList,deleList, region);
		
	}

	
	@Override
	public void updateRevisedEstimateCost(Pcesthmt pcesthmt,String estimateType, String deptId) {
		bean.updateRevisedEstimateCost(pcesthmt,estimateType, deptId,region);
		
	}

	@Override
	public List<Pcesthmt> getRevisedJobDetailList(String deptId,
			List<Long> status, String userId) throws Exception {
		
		return bean.getRevisedJobDetailList(deptId, status, userId, region);
	}

	@Override
	public Boolean isMaterialIssued(String jobNo, String deptId,
			BigDecimal issueTo, Long status) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void updateAdditionalCostForRevisejob(Pcesthmt pcesthmt ,String deptId,BigDecimal toCost) {
		bean.updateAdditionalCostForRevisejob(pcesthmt,deptId, toCost, region);
	}
	@Override
	public void updateReviseDetailsWithRevNo(Pcesthmt pcesthmt,
			List<Pcestdmt> addlist, List<Pcestdmt> updList,
			List<Pcestdmt> deleList) {
		bean.updateReviseDetailsWithRevNo(pcesthmt,addlist,updList,deleList,region);
	}
	@Override
	public List<JobInfo> getJobInformations(String deptId, Long status){
		return bean.getJobInformations(deptId,status,region);
	}
	@Override
	public BigDecimal getTotalConsumerPayable(String estimateNo,
			String resType, String deptId) {
		return bean.getTotalConsumerPayable(estimateNo,resType,deptId, region);
	}
	@Override
	public List<JobInfo> getJobInfomations(String deptId, String estimateNo) {
		return bean.getJobInfomations(deptId,estimateNo, region);
	}

	@Override
	public void update(Pctrxhmt pctrxhmt, Pcesthmt pcesthmt,
			List<Pctrxdmt> addList, List<Pctrxdmt> upTrxList,
			List<Pcestdmt> updList, Long newRevNo) {
		bean.update(pctrxhmt,pcesthmt,addList,upTrxList,updList, newRevNo,region);

		
	}

	@Override
	public List<MaterialGrid> getMaterialGridNew(String estimateNo,
			String deptId) {
		return bean.getMaterialGridNew(estimateNo, deptId, region);

	}

	
}
