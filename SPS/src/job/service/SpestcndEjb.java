package job.service;

import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import util.common.JobProcessStatus;

import job.ejb.SpestcndDaoRemote;
import job.model.BillDetail;
import job.model.ClosingJobInfo;
import job.model.Pcesthmt;
import job.model.Spestcnd;
import job.model.SpestcndPK;
//import job.model.Spestcnd;

public class SpestcndEjb implements SpestcndEjbI {
	private Context context;
	private SpestcndDaoRemote dao; 
	private String region=null;
	
	public SpestcndEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}
	
	private SpestcndDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpestcndDaoRemote dao = (SpestcndDaoRemote) context.lookup("SpestcndDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	
	@Override
	public void createSpestcnd(Spestcnd spestcnd) {
		dao.createSpestcnd(spestcnd, region);
		
	}

	@Override
	public List<Spestcnd> getAll(String deptId) {
		return dao.getAll(deptId, region);
	}

	@Override
	public void updateSpestcnd(Spestcnd spestcnd) {
		dao.updateSpestcnd(spestcnd, region);
		
	}

	@Override
	public void removeSpestcnd(Spestcnd spestcnd) {
		dao.removeSpestcnd(spestcnd, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Spestcnd findById(SpestcndPK id) throws PersistenceException {
		return dao.findById(id, region);
	}

	@Override
	public List<Spestcnd> findByContractorId(String contractorId, String deptId) throws PersistenceException {
		return dao.findByContractorId(contractorId, deptId, region);
	}
	
	@Override
	public List<Spestcnd> getJobList(String contractorId, String deptId, String status) throws PersistenceException {
		return dao.getJobList(contractorId, deptId, status, region);
	}

	@Override
	public List<Spestcnd> getJobList(String contractorId, String deptId, String status, String applicationType) throws PersistenceException {
		return dao.getJobList(contractorId, deptId, status, applicationType, region);
	}

	@Override
	public List<Spestcnd> findByJobNo(String jobNo, String deptId) throws PersistenceException {
		return dao.findByJobNo(jobNo, deptId, region);
	}
	
	@Override
	public List<Spestcnd> getJobList(String deptId, String status) {
		return dao.getJobList(deptId, status, region);
	}

	
	
	

	@Override
	public List<Spestcnd> getJobListByType(String deptId, String status, String applicationType) throws PersistenceException {
		return dao.getJobListByType(deptId, status, applicationType, region);
	}

	@Override
	public List<String> getJobNoListByType(String deptId, String status, String applicationType) throws PersistenceException {
		return dao.getJobNoListByType(deptId, status, applicationType, region);
	}

	@Override
	public List<String> getJobNoListByType(String deptId, String applicationType)
			throws PersistenceException {
		return dao.getJobNoListByType(deptId, applicationType, region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpestcndEjb ejb =new SpestcndEjb("region");
		System.out.println(ejb.getNextBillNo("BIL/514.20/2011/")); 
		System.out.println(ejb.getBillDetailByBillNo("BIL/514.20/2011/0001")); 
		System.out.println(ejb.getAll("510.20")); 
		System.out.println(ejb.findByJobNo("RD 434","510.20")); 
		System.out.println(ejb.getJobList("RD 434", "510.20", JobProcessStatus.ALLOCATED)); 
		System.out.println(ejb.findByContractorId("RD 434", "510.20")); 
	}

	@Override
	public List<String> getJobNoListBySubType(String deptId, String status,String applicationType)
			throws PersistenceException {
		return dao.getJobNoListBySubType(deptId, status, applicationType, region);
	}

	@Override
	public List<Spestcnd> getJobListForLabour(String contractorId,
			String deptId, String status) throws PersistenceException {
		return dao.getJobListForLabour(contractorId, deptId, status, region);
	}

	@Override
	public Spestcnd findByJobNo(String jobNo) throws PersistenceException {
		return dao.findByJobNo(jobNo, region);
	}

	@Override
	public String createBillDetailAutoId(String[] selectedProjectNoList,
			String deptId, String userName, String billNoPrefix,String contractorId) {
		return dao.createBillDetailAutoId(selectedProjectNoList, deptId, userName, billNoPrefix, contractorId,region);
	}

	@Override
	public BillDetail getBillNoByProjectNo(String projectNo) {
		return dao.getBillNoByProjectNo(projectNo, region);
	}

	@Override
	public List<BillDetail> getBillDetailByBillNo(String billNo) {
		return dao.getBillDetailByBillNo(billNo, region);
	}

	@Override
	public List<BillDetail> getBillDetail(String deptId) {
		return dao.getBillDetail(deptId, region);
	}

	@Override
	public List<String> getBillNoList(String deptId) {
		return dao.getBillNoList(deptId, region);
	}

	@Override
	public void updateBillDetail(BillDetail billDetail) {
		dao.updateBillDetail(billDetail, region);
		
	}

	@Override
	public void removeBillDetail(BillDetail billDetail, String deptId) {
		dao.removeBillDetail(billDetail, deptId, region);
		
	}

	/*@Override
	public void removeBillDetailList(List<String> removeList,String deptId) {
		dao.removeBillDetailList(removeList, deptId, region);
		
	}*/

	@Override
	public String getNextBillNo(String billNoPrefix) {
		return dao.getNextBillNo(billNoPrefix, region);
	}

	@Override
	public void changeStatusSpestcnd(String projectNo, String deptId,
			String status) {
		dao.changeStatusSpestcnd(projectNo, deptId, status, region);
		
	}

	@Override
	public void createBillDetail(BillDetail billDetail) {
		dao.createBillDetail(billDetail, region);
		
	}

	@Override
	public List<String> getBillNoList(String deptId, Date fromDate, Date toDate) {
		return dao.getBillNoList(deptId, fromDate, toDate, region);
	}

	@Override
	public List<BillDetail> getBillDetail(String deptId, Date fromDate, Date toDate) {
		return dao.getBillDetail(deptId, fromDate, toDate, region);
	}

	@Override
	public List<String> getProjectNoListByBillNo(String billNo) {
		return dao.getProjectNoListByBillNo(billNo, region);
	}

	@Override
	public void removeBillDetailListByBillNo(String billNo, String deptId) {
		dao.removeBillDetailListByBillNo(billNo, deptId, region);
		
	}

	@Override
	public List<String> getJobNoListBySubTypeCM_OT(String deptId,
			String status, String applicationType)
			throws PersistenceException {
		return dao.getJobNoListBySubTypeCM_OT(deptId, status, applicationType, region);
	}

	/*@Override
	public List<String> getJobList(String deptId, List<String> statusList)
			throws PersistenceException {
		return dao.getJobList(deptId, statusList, region);
	}

	@Override
	public List<ClosingJobInfo> getPcesthmtList(String deptId, List<String> statusList)
			throws PersistenceException {
		return dao.getPcesthmtList(deptId, statusList, region);
	}*/



}
