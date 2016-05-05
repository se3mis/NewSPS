package job.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import util.common.EstimateStatus;
import application.model.WiringInfo;
import estimate.model.Approval;
import estimate.model.EstimateDisplay;
import estimate.model.MaterialGrid;

import estimate.model.Pcestdtt;
import estimate.model.PcestdttPK;
import estimate.model.Pcesthtt;
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

public class JobEjb implements  JobBeanRemote {
	private Context context;
	private JobBeanRemote bean; 
	public JobEjb() {
		super();
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
	public List<JobInfo> getJobInfos(String deptId, Long status, String webAppName) {
		return bean.getJobInfos(deptId, status, webAppName);
	}
	
	@Override
	public List<String> jobCloser(List<String> list, String deptId, Date closingDate, String webAppName) {
		return bean.jobCloser(list, deptId, closingDate, webAppName);
	}
	
	@Override
	public String jobCloser(String jobNo, String deptId, Date closingDate, String webAppName) {
		return bean.jobCloser(jobNo, deptId, closingDate, webAppName);
	}
	
	@Override
	public List<MaterialGrid> getMaterialGrid(String estimateNo, String deptId, String webAppName) {
		return bean.getMaterialGrid(estimateNo, deptId, webAppName);
	}
	
	@Override
	public void insert(Pcesthmt pcesthmt, Speststd speststd, List<Pcestdmt> list, String webAppName) {
		bean.insert(pcesthmt, speststd, list, webAppName);
		
	}

	@Override
	public void update(Pcesthmt pcesthmt, Speststd speststd, List<Pcestdmt> addlist, List<Pcestdmt> updList, Long newRevNo, String webAppName) {
		bean.update(pcesthmt, speststd, addlist, updList, newRevNo, webAppName);
		
	}

	@Override
	public void delete(List<PcestdmtPK> list, Pcesthmt inPcesthmt, String webAppName) {
		bean.delete(list, inPcesthmt, webAppName);
		
	}
	
	@Override
	public Speststd getSpeststd(String jobNo, String deptId, String webAppName) throws PersistenceException {
		return bean.getSpeststd(jobNo, deptId, webAppName);
	}
	
	@Override
	public StdDetail getStdDetail(String jobNo, String deptId, String webAppName) throws PersistenceException {
		return bean.getStdDetail(jobNo, deptId, webAppName);
	}

	@Override
	public WiringInfo getWiringInfo(String jobNo, String deptId, String webAppName) {
		return bean.getWiringInfo(jobNo, deptId, webAppName);
	}
	
	@Override
	public List<Pcfunddm> getFundSourceList(String deptId, String webAppName) {
		return bean.getFundSourceList(deptId, webAppName);
	}
	
	@Override
	public boolean isResTypeAvailable(String estimateNo, String deptId, Long year, Long month, String resType, String webAppName) {
		return bean.isResTypeAvailable(estimateNo, deptId, year, month, resType, webAppName);
	}
	
	@Override
	public void updateNewResType(Pcesthmt pcesthmt, List<Pcestdmt> addList, Long newRevNo, String webAppName) {
		bean.updateNewResType(pcesthmt,  addList, newRevNo, webAppName);
	}

	@Override
	public BigDecimal getResCat(String resType, String webAppName) {
		return bean.getResCat(resType, webAppName);
	}
	
	@Override
	public String forceJobCloser(String jobNo, String deptId, Date closingDate, String webAppName) {
		return bean.forceJobCloser(jobNo,deptId, closingDate, webAppName);
	}

	@Override
	public List<FinishedJobInfo> getFinishedJobsForBilling(String deptId, String  catCd,String webAppName) {
		return bean.getFinishedJobsForBilling(deptId, catCd, webAppName);
	}
	
	

	

	@Override
	public String toString() {
		return "JobEjb [context=" + context + ", bean=" + bean + "]";
	}

	@Override
	public List<Pcesthmt> getJobApprovalReport(String userName, String deptId, Long status, String value, String webAppName) {
		return bean.getJobApprovalReport(userName, deptId, status, value, webAppName);
	}

	@Override
	public ClosingJobInfo getClosingJobInfo(String jobNo, String deptId, String webAppName) {
		return bean.getClosingJobInfo(jobNo, deptId, webAppName);
	}

	@Override
	public Boolean isMaterialIssued(String jobNo, String deptId,BigDecimal issueTo, BigDecimal status, String webAppName) {
		return bean.isMaterialIssued(jobNo, deptId,issueTo, status, webAppName);
	}
	
	@Override
	public Boolean isMaterialIssued(String jobNo, String deptId, String webAppName) {
		return bean.isMaterialIssued(jobNo, deptId, webAppName);
		
	}

	@Override
	public List<PaySlipInfo> getPaySlipNoList(String deptId, String webAppName) {
		return bean.getPaySlipNoList(deptId, webAppName);
	}

	@Override
	public List<String> getSoftClosingJobNoList(String deptId, String webAppName) {
		return bean.getSoftClosingJobNoList(deptId, webAppName);
	}

	@Override
	public void softJobCloser(List<String> list, String deptId, String webAppName) {
		bean.softJobCloser(list, deptId, webAppName);
		
	}

	@Override
	public List<Pcesthmt> getJobApprovalReport(String userName, String deptId, String authorityLevel, String webAppName) {
		return bean.getJobApprovalReport(userName, deptId, authorityLevel, webAppName);
	}

	@Override
	public TempConnInfo getTempConnInfos(String jobNo, String deptId, String webAppName) {
		return bean.getTempConnInfos(jobNo, deptId, webAppName);
	}

	@Override
	public void alocateJob(List<Spestcnd> spsetCndList, Spestcnt spestcnt, String webAppName) {
		bean.alocateJob(spsetCndList, spestcnt, webAppName);
		
	}

	@Override
	public void deAloocateJob(List<SpestcndPK> idList, Spestcnt spestcnt, String webAppName) {
		bean.deAloocateJob(idList, spestcnt, webAppName);
		
	}

	@Override
	public void energizeJob(Spestsea spestsea, Spestcnd spestcnd,
			Spestcnt spestcnt, String webAppName) {
		bean.energizeJob(spestsea, spestcnd, spestcnt, webAppName);
		
	}

	@Override
	public List<Spestlab> getLaborDetails(String jobNo, String deptId, String webAppName) {
		return bean.getLaborDetails(jobNo, deptId, webAppName);
	}

	@Override
	public void billContractor(Spestcnd spestcnd, Spestbip spestbip, String webAppName) {
		bean.billContractor(spestcnd, spestbip, webAppName);
		
	}

	@Override
	public List<Pcesthmt> getJobDetailListByDateRange(Date fromDate,
			Date toDate, String deptId, String webAppName) {
		return bean.getJobDetailListByDateRange(fromDate, toDate, deptId, webAppName);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Calendar calendar=Calendar.getInstance();
		JobEjb ejb=new JobEjb();
		
		//System.out.println("SS "+ejb.getJobApprovalReportNew("511CE", "511.00", "CE", "R4"));
		//System.out.println(ejb.getJobInfos("423.40", new Long("22"), "R1"));
		try{
			//System.out.println("Grig : " +ejb.getMaterialGridNewJV("RE/12/2005", "501.20", "Testing"));
		}catch(Exception e){
			System.out.println("Grig : " + e);
		}
		/**System.out.println(ejb.getFinishedJobsForBilling("423.50", "SMC", "R1"));
		System.out.println("SS "+ejb.getLaborDetails("423.50/SMC/11/0699", "423.50", "R1"));
		System.out.println("SS "+ejb.getJobApprovalReportNew("ES42310", "423.10", "ES", "R1"));
		
		System.out.println("SS "+ejb.getJobApprovalReportNew("423EA", "423.00", "EA", "R1"));
		System.out.println(ejb.isMaterialIssued("514.20/SMC/11/0045", "514.20", "SMCTesting"));
		System.out.println(ejb.isMaterialIssued("514.20/SMC/11/0045", "514.20", new BigDecimal("4"), new BigDecimal("4"), "SMCTesting"));
		System.out.println(ejb.getJobInfos("514.20", new BigDecimal("1"),"SMCTesting"));
		System.out.println("SS "+ejb.getJobApprovalReportNew("ES42310", "423.10", "ES", "R1"));
		System.out.println(ejb.getJobDetailListByDateRange(calendar.getTime(),calendar.getTime(),"510.20", "SMCTesting"));
		
		System.out.println(ejb.getTempConnInfos("060298", "510.20", "SMCTesting"));
		System.out.println(ejb.getSoftClosingJobNoList("501.00", "SMCTesting"));
		System.out.println(ejb.getPaySlipNoList("501.00", "SMCTesting"));
		System.out.println(ejb.isMaterialIssued("443.10/MIA/10/0007 ", "443.10", new BigDecimal("4"), new BigDecimal("4"), "SMCTesting"));
		
		System.out.println(ejb.getClosingJobInfo("R02110","440.30", "SMCTesting"));
		System.out.println(ejb.getClosingJobInfo("060306","510.20", "SMCTesting"));
		*/
		//System.out.println(ejb.getJobInfos("510.20", status, webAppName));
		
		//System.out.println(ejb.getWiringInfo("010312", "510.20"));
		//System.out.println(ejb.getClosedJobsForBilling("510.20"));
		//System.out.println(ejb.getJobApprovalReport("es", "510.20", new BigDecimal(EstimateStatus.JOB_APPROVAL), "ES"));
		//System.out.println(ejb.findjobCloser("020369", "440.20", calendar.getTime()));
		//PcesthmtEjb ejb1= new PcesthmtEjb();
		//System.out.println(ejb.jobCloser("020256", "440.20", calendar.getTime()));
		//ejb.forceJobCloser("020256", "440.20", calendar.getTime());
		//System.out.println(ejb.jobCloser("020369", "440.20", calendar.getTime()));
		//System.out.println(ejb.jobCloser("020256", "440.20", calendar.getTime()));
		//Pcesthmt pcesthmt=ejb1.findByEstimationNo("510.20/SNL/2010/0010", "510.20");
		//System.out.println(pcesthmt);
		/*
		Pcestdmt pcestdmt = new Pcestdmt();
        PcestdmtPK pcestdmtPK = new PcestdmtPK();
        
        pcestdmtPK.setDeptId("510.20");
        pcestdmtPK.setEstimateNo("510.20/SNL/2010/0010");
        pcestdmtPK.setResCd("OVERHEAD");
        pcestdmtPK.setRevNo(3);
        
        pcestdmt.setId(pcestdmtPK);
        pcestdmt.setEstimateCost(new BigDecimal(200));
        pcestdmt.setEstimateQty(new BigDecimal(1));
        pcestdmt.setResType("OVERHEAD-COST");
        pcestdmt.setResCat(new BigDecimal(2));
        pcestdmt.setUnitPrice(new BigDecimal(1));
        pcestdmt.setUom("1");
        //pcestdmt.getId().setRevNo(new Long(3));
		List<Pcestdmt> list=new ArrayList<Pcestdmt>();
		System.out.println(pcestdmt);
		list.add(pcestdmt);
		//
		
		Pcestdmt pcestdmt2 = new Pcestdmt();
        PcestdmtPK pcestdmtPK2= new PcestdmtPK();
        
        pcestdmtPK2.setDeptId("510.20");
        pcestdmtPK2.setEstimateNo("510.20/SNL/2010/0010");
        pcestdmtPK2.setResCd("TRANSPORT");
        pcestdmtPK2.setRevNo(3);
        
        pcestdmt2.setId(pcestdmtPK2);
        pcestdmt2.setEstimateCost(new BigDecimal(200));
        pcestdmt2.setEstimateQty(new BigDecimal(1));
        pcestdmt2.setResType("TRANSPORT-COST");
        pcestdmt2.setResCat(new BigDecimal(2));
        pcestdmt2.setUnitPrice(new BigDecimal(1));
        pcestdmt2.setUom("1");
        //pcestdmt.getId().setRevNo(new Long(3));
		//List<Pcestdmt> list=new ArrayList<Pcestdmt>();
		System.out.println(pcestdmt2);
		
		list.add(pcestdmt2);
		
		Pcestdmt pcestdmt3 = new Pcestdmt();
        PcestdmtPK pcestdmtPK3= new PcestdmtPK();
        
        pcestdmtPK3.setDeptId("510.20");
        pcestdmtPK3.setEstimateNo("510.20/SNL/2010/0010");
        pcestdmtPK3.setResCd("00314");
        pcestdmtPK3.setRevNo(3);
        
        pcestdmt3.setId(pcestdmtPK2);
        pcestdmt3.setEstimateCost(new BigDecimal(200));
        pcestdmt3.setEstimateQty(new BigDecimal(1));
        pcestdmt3.setResType("MAT-COST");
        pcestdmt3.setResCat(new BigDecimal(2));
        pcestdmt3.setUnitPrice(new BigDecimal(1));
        pcestdmt3.setUom("1");
        list.add(pcestdmt3);
        //pcestdmt.getId().setRevNo(new Long(3));
		//List<Pcestdmt> list=new ArrayList<Pcestdmt>();
		System.out.println(pcestdmt3);
		
		
		ejb.updateNewResType(pcesthmt, list, new Long(3));
		*/
		
		//System.out.println(ejb.getWiringInfo("030058", "510.20"));
		//System.out.println(ejb.getJobInfos("510.20", new BigDecimal("1")));
		//System.out.println(ejb.getJobInfos("510.20", new BigDecimal("1")));
		//ejb.update(null, null, null, null,null);
		//System.out.println(ejb.getStdDetail("030058", "510.20"));

	}

	@Override
	public List<EstimateDisplay> getJobApprovalReportNew(String userName,
			String deptId, String authorityLevel, String webAppName) {
		return bean.getJobApprovalReportNew(userName, deptId, authorityLevel, webAppName);
	}

	@Override
	public void validateJob(Pcesthmt pcesthmt, Approval approval,
			String webAppName) {
		bean.validateJob(pcesthmt, approval, webAppName);
		
	}

	@Override
	public void rejectJob(Pcesthmt pcesthtt, Approval approval,
			String webAppName) {
		bean.rejectJob(pcesthtt, approval, webAppName);
		
	}

	@Override
	public void update(Pcesthmt pcesthmt, Speststd speststd,
			List<Pcestdmt> addlist, List<Pcestdmt> updList, Long newRevNo,
			List<Spestlab> insertLabList, List<Spestlab> updateLabList,
			String webAppName) {
		bean.update(pcesthmt, speststd, addlist, updList, newRevNo, insertLabList, updateLabList, webAppName);
		
	}

	@Override
	public void removeLabour(List<SpestlabPK> removeList, Pcesthmt pcesthmt,
			Pcestdmt pcestdmt, String webAppName) {
		bean.removeLabour(removeList, pcesthmt, pcestdmt, webAppName);
		
	}

	@Override
	public void removeMaterials(List<PcestdmtPK> list, Pcesthmt inPcesthmt,
			Speststd speststd, String webAppName) {
		bean.removeMaterials(list, inPcesthmt, speststd, webAppName);
		
	}

	@Override
	public void removeLabour(List<SpestlabPK> removeList, Pcesthmt pcesthmt,
			List<Pcestdmt> pcestdmtList, Speststd speststd, String webAppName) {
		bean.removeLabour(removeList, pcesthmt, pcestdmtList, speststd, webAppName);
		
	}

	@Override
	public List<MaterialGrid> getMaterialGridNew(String estimateNo,
			String deptId, String webAppName) {
		return bean.getMaterialGridNew(estimateNo, deptId, webAppName);
	}

	
	
	@Override
	public void updateReviseDetails(Pcesthmt pcesthmt, List<Pcestdmt> addlist,
			List<Pcestdmt> updList, List<Pcestdmt> deleList, String webAppName) {
		bean.updateReviseDetails(pcesthmt,addlist,updList,deleList,webAppName);
		
	}

	@Override
	public void updateRevisedEstimateCost(Pcesthmt pcesthmt,String estimateType, String deptId,
			String webAppName) throws PersistenceException {
		bean.updateRevisedEstimateCost(pcesthmt,estimateType,deptId,webAppName);
		
	}

	@Override
	public List<Pcesthmt> getRevisedJobDetailList(String deptId,
			List<Long> status, String userId, String webAppName) {
		return bean.getRevisedJobDetailList(deptId,status, userId, webAppName);
	}
	
	@Override
	public void updateAdditionalCostForRevisejob(Pcesthmt pcesthmt ,String deptId,BigDecimal toCost,String webAppName) {
		bean.updateAdditionalCostForRevisejob(pcesthmt,deptId, toCost, webAppName);
	}

	@Override
	public void updateReviseDetailsWithRevNo(Pcesthmt pcesthmt,
			List<Pcestdmt> addlist, List<Pcestdmt> updList,
			List<Pcestdmt> deleList, String webAppName) {
		bean.updateReviseDetailsWithRevNo(pcesthmt,addlist,updList,deleList,webAppName);
	}

	@Override
	public List<JobInfo> getJobInformations(String deptId, Long status,
			String webAppName) {
		return bean.getJobInformations(deptId,status,webAppName);
	}

	@Override
	public BigDecimal getTotalConsumerPayable(String estimateNo,
			String resType, String deptId, String webAppName)
			throws PersistenceException {
		return bean.getTotalConsumerPayable(estimateNo,resType,deptId, webAppName);
	}

	@Override
	public List<JobInfo> getJobInfomations(String deptId, String estimateNo,
			String webAppName) {
		return bean.getJobInfomations(deptId,estimateNo, webAppName);
	}

	@Override
	public void update(Pctrxhmt pctrxhmt, Pcesthmt pcesthmt,
			List<Pctrxdmt> addList, List<Pctrxdmt> upTrxList,
			List<Pcestdmt> updList, Long newRevNo, String webAppName) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	

	

	

	

	

	
	

	

	

}
