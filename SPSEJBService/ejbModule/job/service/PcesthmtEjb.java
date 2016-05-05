package job.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.model.Approval;

import util.common.EstimateStatus;

import job.ejb.PcesthmtDaoRemote;
import job.model.Pcesthmt;
import job.model.PcesthmtPK;


public class PcesthmtEjb implements PcesthmtDaoRemote{

	private Context context;
	private PcesthmtDaoRemote dao; 
	
	
	public PcesthmtEjb() {
		super();
		this.dao=lookupDao();
		
	}

	private PcesthmtDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 PcesthmtDaoRemote dao = (PcesthmtDaoRemote) context.lookup("PcesthmtDao/remote");
			// System.out.println("got dao "+dao.getM);
			 //System.out.println(dao.findGldeptm(""));
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	

	@Override
	public void createPcesthmt(Pcesthmt pcesthmt, String webAppName) {
		dao.createPcesthmt(pcesthmt, webAppName);
		
	}

	@Override
	public void updatePcesthmt(Pcesthmt pcesthmt, String webAppName) {
		dao.updatePcesthmt(pcesthmt, webAppName);
		
	}

	@Override
	public void removePcesthmt(Pcesthmt pcesthmt, String webAppName) {
		dao.removePcesthmt(pcesthmt, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public List<Pcesthmt> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public Pcesthmt findById(PcesthmtPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}

	@Override
	public Pcesthmt findByEstimationNo(String estimateNo, String deptId, String webAppName) {
		return dao.findByEstimationNo(estimateNo, deptId, webAppName);
	}

	@Override
	public Pcesthmt findByJobNo(String jobNo, String deptId, String webAppName) {
		return dao.findByJobNo(jobNo, deptId, webAppName);
	}

	@Override
	public List<String> findJobNoList(String deptId, Long status, String webAppName) {
		return dao.findJobNoList(deptId, status, webAppName);
	}

	@Override
	public List<Pcesthmt> getJobApprovalList(String deptId, Long status, String value, String webAppName) {
		return dao.getJobApprovalList(deptId, status, value, webAppName);
	}
	
	@Override
	public List<String> getJobNoList(String deptId, Long status, String webAppName) {
		return dao.getJobNoList(deptId,status, webAppName);
	}

	@Override
	public List<Pcesthmt> getJobDetailList(String deptId, Long status, String webAppName) {
		return dao.getJobDetailList(deptId,status, webAppName);
	}

	@Override
	public void changeStatusPcesthmt(String jobNo, String deptId, Long status, String webAppName) {
		dao.changeStatusPcesthmt(jobNo, deptId, status, webAppName);
		
	}
	
	@Override
	public List<String> getNonAllocatedJobNoList(String deptId, Long status, List<String> aList, String webAppName) {
		return dao.getNonAllocatedJobNoList(deptId, status, aList, webAppName);
	}

	@Override
	public List<BigDecimal> getNonAllocatedAmountList(String deptId, Long status, List<String> list, String webAppName) {
		return dao.getNonAllocatedAmountList(deptId, status, list, webAppName);
	}
	
	@Override
	public void updateRevNo(Pcesthmt pcesthmt, Long newRevNo, String webAppName) {
		dao.updateRevNo(pcesthmt, newRevNo, webAppName);
		
	}

	@Override
	public List<Pcesthmt> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}
	
	

	@Override
	public String approveJob(String jobNo, String deptId, String authorityLevel, String userName, String appType, String webAppName) {
		return dao.approveJob(jobNo, deptId, authorityLevel, userName, appType,webAppName);
	}

	@Override
	public List<Pcesthmt> getJobApprovalListNew(String deptId, String authorityLevel, String webAppName) {
		return dao.getJobApprovalListNew(deptId, authorityLevel, webAppName);
	}

	@Override
	public List<String> getJobNoApprovalListNew(String deptId, String authorityLevel, String webAppName) {
		return dao.getJobNoApprovalListNew(deptId, authorityLevel, webAppName);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PcesthmtEjb ejb=new PcesthmtEjb();
		PcesthmtPK id=new PcesthmtPK();
		id.setEstimateNo("0075/2009SPF");
		id.setDeptId("510.20");
		id.setRevNo(2);
		//System.out.println(ejb.getNextJobNo("542.10/SMC/11/", "R1"));
		//System.out.println(ejb.getNextJobNo("542.20/SMC/11/", "R1"));
		//System.out.println(ejb.approveJob("541.20/SMC/11/1501", "541.20", "ES", "54120ES1", false, "R1"));
		/*System.out.println(ejb.getJobDetailList("423.40", new BigDecimal("5"), "R1"));
		System.out.println(ejb.findByJobNo("423.50/SMC/11/0660", "423.50", "R1"));
		System.out.println(ejb.findByJobNo("423.50/SMC/11/0660", "R1"));
		System.out.println(ejb.getNextJobNo("423.10/SMC/11/", "R1"));
		System.out.println(ejb.findById(id, "SMCTesting"));
		System.out.println(ejb.getJobNoApprovalListNew("510.20", "ES", "SMCTesting"));
		System.out.println(ejb.getJobApprovalListNew("510.20", "ES", "SMCTesting"));
		System.out.println(ejb.getJobApprovalListNew("510.20", "EA", "SMCTesting"));
		System.out.println(ejb.getJobApprovalListNew("510.20", "AE", "SMCTesting"));
		System.out.println(ejb.getJobApprovalListNew("510.20", "DGM", "SMCTesting"));
		System.out.println(ejb.getJobApprovalListNew("510.20", "AGM", "SMCTesting"));
		System.out.println(ejb.approveJob("060305", "510.20", "ES", "es", false,"SMCTesting"));
		*/
		//System.out.println(ejb.approveJob("010530", "510.20", "EA"));
		//System.out.println(ejb.approveJob("010530", "510.20", "AE"));
		//System.out.println(ejb.approveJob("010530", "510.20", "DGM"));
		//System.out.println(ejb.approveJob("010530", "510.20", "AGM"));
		/*System.out.println(ejb.getJobNoList("510.20", new BigDecimal("4"), "SMCTesting"));
		System.out.println(ejb.getJobApprovalList("443.00", new BigDecimal(EstimateStatus.JOB_APPROVAL_ES), "EA", "SMCTesting"));
		System.out.println(ejb.findByJobNo("020256", "440.20", "SMCTesting"));
		System.out.println(ejb.findByJobNo("060298", "510.20", "SMCTesting"));
		System.out.println(ejb.getJobNoList("510.20", new BigDecimal("5"), "SMCTesting"));
		System.out.println("3476583475734567345 "+ejb.getJobApprovalList("510.20", new BigDecimal("55"), "ES", "SMCTesting"));
		System.out.println(ejb.findByEstimationNo("030058", "510.20", "SMCTesting"));
		*/
		//System.out.println(ejb.getAll("510.20", "SMCTesting"));
		//Pcesthmt pcesthmt=ejb.findByEstimationNo("030058", "510.20", "SMCTesting");
		//ejb.updateRevNo(pcesthmt, new Long("12"));
		
		//System.out.println(ejb.getJobNoList("510.20", new BigDecimal("1"), "SMCTesting"));
		List<String> alist=new ArrayList<String>();
		alist.add("030058");
		alist.add("050027");
		alist.add("010261");
		//System.out.println(alist);
		List<Long> statuses = new ArrayList<Long>();
		statuses.add(new Long(EstimateStatus.JOB_ONGOING));
		System.out.println("xxx");
		System.out.println(ejb.findEstNosList("501.20", statuses, "CTesting"));
		//System.out.println(ejb.getNonAllocatedAmountList("510.20", new BigDecimal("1"), alist, "SMCTesting"));
		//System.out.println(ejb.findJobNoList("440.20" , new BigDecimal("5")));
		//System.out.println(ejb.findByJobNo("080541", "440.20"));
		//System.out.println(ejb.getJobApprovalList("440.20", new BigDecimal("75"), "ES"));
		//System.out.println(ejb.getJobNoList("440.20", new BigDecimal("1")));
		//System.out.println(ejb.getJobDetailList("440.20", new BigDecimal("1")).size());
		
	}

	@Override
	public String getNextJobNo(String jobNoPrefix, String webAppName) {
		return dao.getNextJobNo(jobNoPrefix, webAppName);
	}

	@Override
	public void validateJob(Pcesthmt pcesthmt, Approval approval,
			String webAppName) {
		dao.validateJob(pcesthmt, approval, webAppName);
		
	}

	@Override
	public void rejectJob(Pcesthmt pcesthtt, Approval approval,
			String webAppName) {
		dao.rejectJob(pcesthtt, approval, webAppName);
		
	}

	@Override
	public Pcesthmt findByJobNo(String jobNo, String webAppName) {
		return dao.findByJobNo(jobNo, webAppName);
	}

	@Override
	public Pcesthmt findByEstimationNo(String estimateNo, String webAppName) {
		return dao.findByEstimationNo(estimateNo, webAppName);
	}

	@Override
	public List<String> getJobNoListST(String deptId, Long status,
			String applicationType, String applicationSubType, String webAppName) {
		return dao.getJobNoListST(deptId, status, applicationType, applicationSubType, webAppName);
	}

	@Override
	public List<String> getJobNoApprovalListNewST(String deptId,
			String authorityLevel, String applicationType,
			String applicationSubType, String webAppName) {
		return dao.getJobNoApprovalListNewST(deptId, authorityLevel, applicationType, applicationSubType, webAppName);
	}



	@Override
	public List<Pcesthmt> getRevisedJobDetailList(String deptId,
			List<Long> status, String userId, String webAppName) {
		// TODO Auto-generated method stub
		return dao.getRevisedJobDetailList(deptId,status, userId, webAppName);
	}

	@Override
	public int updateReviseApproveDetails(String jobNo, String deptId,
			Long status, String rejectedBy, Date rejectedDate,
			String approvedByEE, Date approvedEEDate, String approvedByCE,
			Date approvedCEDate, String approvedByDGM, Date approvedDGMDate,
			String rejectedReason, String webAppName) {
		return dao.updateReviseApproveDetails(jobNo, deptId, status,rejectedBy,rejectedDate,approvedByEE,approvedEEDate,approvedByCE, approvedCEDate, approvedByDGM,approvedDGMDate,rejectedReason,webAppName);
	}

	@Override
	public List<String> findJobNosList(String deptId, List<Long> status,
			String webAppName) {
		// TODO Auto-generated method stub
		return dao.findJobNosList(deptId, status, webAppName);
	}

	@Override
	public List<String> findEstNosList(String deptId, List<Long> status,
			String webAppName) {
		// TODO Auto-generated method stub
		return dao.findEstNosList(deptId, status, webAppName);
	}

	

	

	

}
