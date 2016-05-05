package job.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.model.Approval;

import job.ejb.PcesthmtDaoRemote;
//import job.model.Pcesthmt;
//import job.model.PcesthmtPK;
import job.model.Pcesthmt;
import job.model.PcesthmtPK;

public class PcesthmtEjb implements PcesthmtEjbI {

	

	private Context context;
	private PcesthmtDaoRemote dao; 
	private String region=null;
	
	
	public PcesthmtEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}

	private PcesthmtDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 PcesthmtDaoRemote dao = (PcesthmtDaoRemote) context.lookup("PcesthmtDao/remote");
			 //System.out.println("got dao "+dao);
			 //System.out.println(dao.findGldeptm(""));
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void createPcesthmt(Pcesthmt pcesthmt) {
		dao.createPcesthmt(pcesthmt, region);
		
	}

	@Override
	public void updatePcesthmt(Pcesthmt pcesthmt) {
		dao.updatePcesthmt(pcesthmt, region);
		
	}

	@Override
	public void removePcesthmt(Pcesthmt pcesthmt) {
		dao.removePcesthmt(pcesthmt, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public List<Pcesthmt> getAll() {
		return dao.getAll(region);
	}

	@Override
	public Pcesthmt findById(PcesthmtPK id) throws PersistenceException {
		return dao.findById(id, region);
	}

	@Override
	public Pcesthmt findByEstimationNo(String estimateNo, String deptId) {
		return dao.findByEstimationNo(estimateNo, deptId, region);
	}

	@Override
	public Pcesthmt findByJobNo(String jobNo, String deptId) {
		return dao.findByJobNo(jobNo, deptId, region);
	}

	@Override
	public List<String> findJobNoList(String deptId, Long status) {
		return dao.findJobNoList(deptId, status, region);
	}

	@Override
	public List<Pcesthmt> getJobApprovalList(String deptId, Long status, 	String value) {
		return dao.getJobApprovalList(deptId, status, value, region);
	}
	
	@Override
	public List<String> getJobNoList(String deptId, Long status) {
		return dao.getJobNoList(deptId, status, region);
	}

	@Override
	public List<Pcesthmt> getJobDetailList(String deptId, Long status) {
		return dao.getJobDetailList(deptId, status, region);
	}
	
	@Override
	public void changeStatusPcesthmt(String jobNo, String deptId, Long status) {
		dao.changeStatusPcesthmt(jobNo, deptId, status, region);
		
	}

	

	@Override
	public String approveJob(String jobNo, String deptId, String authorityLevel, String userName, String applType) {
		return dao.approveJob(jobNo, deptId, authorityLevel, userName, applType, region);
	}

	@Override
	public List<Pcesthmt> getJobApprovalListNew(String deptId, 	String authorityLevel) {
		return dao.getJobApprovalListNew(deptId, authorityLevel, region);
	}

	@Override
	public List<String> getJobNoApprovalListNew(String deptId, String authorityLevel) {
		return dao.getJobNoApprovalListNew(deptId, authorityLevel, region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PcesthmtEjb ejb=new PcesthmtEjb("region");
		//System.out.println(ejb.findByEstimationNo("510.20/SNL/2010/0003", "510.20"));
		//System.out.println(ejb.findByEstimationNo("030058", "440.20"));
		//System.out.println(ejb.findJobNoList("440.20" , new Long("5")));
		//System.out.println(ejb.findByJobNo("080541", "440.20"));
		//System.out.println(ejb.getJobApprovalList("440.20",new Long("5"),"ES"));
		System.out.println(ejb.getJobNoList("440.20", new Long("1")));
		//System.out.println(ejb.getJobDetailList("440.20", new Long("1")).size());

	}

	@Override
	public void validateJob(Pcesthmt pcesthmt, Approval approval) {
		dao.validateJob(pcesthmt, approval, region);
		
	}

	@Override
	public void rejectJob(Pcesthmt pcesthtt, Approval approval) {
		dao.rejectJob(pcesthtt, approval, region);
		
	}

	@Override
	public Pcesthmt findByJobNo(String jobNo) {
		return dao.findByJobNo(jobNo, region);
	}

	@Override
	public Pcesthmt findByEstimationNo(String estimateNo) {
		return dao.findByEstimationNo(estimateNo, region);
	}

	@Override
	public int updateReviseApproveDetails(String jobNo, String deptId,
			Long status, String rejectedBy, Date rejectedDate,
			String approvedByEE, Date approvedEEDate, String approvedByCE,
			Date approvedCEDate, String approvedByDGM, Date approvedDGMDate,String rejectedReason) {
		return dao.updateReviseApproveDetails(jobNo, deptId, status,rejectedBy,rejectedDate,approvedByEE,approvedEEDate,approvedByCE, approvedCEDate, approvedByDGM,approvedDGMDate,rejectedReason,region);
	}

	@Override
	public List<String> findJobNosList(String deptId, List<Long> status) {
		// TODO Auto-generated method stub
		return dao.findJobNosList(deptId, status, region);
	}

	@Override
	public List<String> findEstNosList(String deptId, List<Long> status) {
		// TODO Auto-generated method stub
		return dao.findEstNosList(deptId, status, region);
	}
	

}
