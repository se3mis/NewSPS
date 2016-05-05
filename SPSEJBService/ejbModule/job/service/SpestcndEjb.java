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
import job.model.Spestcnd;
import job.model.SpestcndPK;

public class SpestcndEjb implements SpestcndDaoRemote {
	private Context context;
	private SpestcndDaoRemote dao; 
	public SpestcndEjb() {
		super();
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
	public void createSpestcnd(Spestcnd spestcnd, String webAppName) {
		dao.createSpestcnd(spestcnd, webAppName);
		
	}

	@Override
	public List<Spestcnd> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}

	@Override
	public void updateSpestcnd(Spestcnd spestcnd, String webAppName) {
		dao.updateSpestcnd(spestcnd, webAppName);
		
	}

	@Override
	public void removeSpestcnd(Spestcnd spestcnd, String webAppName) {
		dao.removeSpestcnd(spestcnd, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Spestcnd findById(SpestcndPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}

	@Override
	public List<Spestcnd> findByContractorId(String contractorId, String deptId, String webAppName) throws PersistenceException {
		return dao.findByContractorId(contractorId, deptId, webAppName);
	}
	
	@Override
	public List<Spestcnd> getJobList(String contractorId, String deptId, String status, String webAppName) throws PersistenceException {
		return dao.getJobList(contractorId, deptId, status, webAppName);
	}

	@Override
	public List<Spestcnd> getJobList(String contractorId,String deptId, String status, String applicationType , String webAppName) throws PersistenceException {
		return dao.getJobList(contractorId, deptId, status, applicationType, webAppName);
	}

	@Override
	public List<Spestcnd> findByJobNo(String jobNo, String deptId, String webAppName) throws PersistenceException {
		return dao.findByJobNo(jobNo, deptId, webAppName);
	}
	
	@Override
	public List<String> getAllocatedJobNolist(String deptId, String webAppName) {
		return dao.getAllocatedJobNolist("510.20", webAppName);
	}
	
	

	@Override
	public List<Spestcnd> getJobList(String deptId, String status, String webAppName) throws PersistenceException {
		return dao.getJobList(deptId, status, webAppName);
	}

	@Override
	public List<Spestcnd> getJobListByType(String deptId, String status, String applicationType, String webAppName) throws PersistenceException {
		return dao.getJobListByType(deptId, status, applicationType, webAppName);
	}

	@Override
	public List<String> getJobNoListByType(String deptId, String status, String applicationType, String webAppName) throws PersistenceException {
		return dao.getJobNoListByType(deptId, status, applicationType, webAppName);
	}

	@Override
	public List<String> getJobNoListByType(String deptId, String applicationType, String webAppName) throws PersistenceException {
		return dao.getJobNoListByType(deptId, applicationType, webAppName);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpestcndEjb ejb=new SpestcndEjb();
		//System.out.println(ejb.getJobList("0005", "510.20", "F", "SMCTesting")); 
		//System.out.println(ejb.getAll("510.20", "SMCTesting")); 
		ejb.removeBillDetailListByBillNo("BIL/514.20/2011/0005", "514.20", "mnsafn");
		System.out.println(ejb.findByJobNo("060298", "SMCTesting"));
		SpestcndPK id=new SpestcndPK();
		id.setContractorId("0005");
		id.setProjectNo("060298");
		id.setDeptId("510.20");
		ejb.changeStatusSpestcnd("514.20/SMC/11/0136", "514.20", "B", "webAppName");
		//ejb.updateIsExported("jobNo", "deptId", "R1");
		System.out.println(ejb.getJobList("514.20", JobProcessStatus.ALLOCATED, "bjdkjasbd"));
		System.out.println(ejb.getJobNoListByType("514.20", JobProcessStatus.ALLOCATED, "NC", "bjdkjasbd"));
		System.out.println(ejb.getJobList("0003", "423.10", "F", "R1"));
		System.out.println(ejb.getJobNoListByType("423.50", "NC", "R1").size());
		System.out.println(ejb.getJobNoListBySubType("423.50", JobProcessStatus.ALLOCATED,"NC", "R1").size());
		System.out.println(ejb.getJobNoListBySubType("423.50", JobProcessStatus.ALLOCATED,"CR", "R1"));
		System.out.println(ejb.getJobNoListByType("423.10", "NC", "R1").size());
		System.out.println(ejb.getJobNoListBySubType("423.10", JobProcessStatus.ALLOCATED,"NC", "R1").size());
		System.out.println(ejb.getJobNoListBySubType("423.10", JobProcessStatus.ALLOCATED,"CR", "R1"));
		
		//System.out.println(ejb.findById(id, "SMCTesting"));
		//System.out.println(ejb.findByContractorId("RD 434", "510.20"));
		//System.out.println(ejb.getAllocatedJobNolist("510.20", "SMCTesting")); 
		//System.out.println(ejb.getJobList("0005", "510.20", "F", "NL", "SMCTesting"));
		//System.out.println("# "+ejb.getJobListByType("510.20", "F", "NL", "SMCTesting"));
		//System.out.println(ejb.getJobNoListByType("510.20", "NL", "SMCTesting"));
		

	}

	@Override
	public void updateIsExported(String jobNo, String deptId, String isExported, String webAppName) {
		dao.updateIsExported(jobNo, deptId, isExported,	webAppName);
		
	}

	@Override
	public List<String> getJobNoListBySubType(String deptId, String status,String applicationType, String webAppName)
			throws PersistenceException {
		return dao.getJobNoListBySubType(deptId, status,applicationType, webAppName) ;
	}

	@Override
	public void updateError(String jobNo, String deptId, String errorDesc,
			String webAppName) {
		dao.updateError(jobNo, deptId, errorDesc, webAppName);
		
	}

	@Override
	public List<Spestcnd> getJobListForLabour(String contractorId,
			String deptId, String status, String webAppName)
			throws PersistenceException {
		return dao.getJobListForLabour(contractorId, deptId, status, webAppName);
	}

	@Override
	public Spestcnd findByJobNo(String jobNo, String webAppName)
			throws PersistenceException {
		return dao.findByJobNo(jobNo, webAppName);
	}

	@Override
	public String createBillDetailAutoId(String[] selectedProjectNoList,
			String deptId, String userName, String billNoPrefix, String contractorId,
			String webAppName) {
		return dao.createBillDetailAutoId(selectedProjectNoList, deptId, userName, billNoPrefix, contractorId,webAppName);
		
	}

	@Override
	public BillDetail getBillNoByProjectNo(String projectNo, String webAppName) {
		return dao.getBillNoByProjectNo(projectNo, webAppName);
	}

	@Override
	public List<BillDetail> getBillDetailByBillNo(String billNo,
			String webAppName) {
		return dao.getBillDetailByBillNo(billNo, webAppName);
	}

	@Override
	public List<BillDetail> getBillDetail(String deptId, String webAppName) {
		return dao.getBillDetail(deptId, webAppName);
	}

	@Override
	public List<String> getBillNoList(String deptId, String webAppName) {
		return dao.getBillNoList(deptId, webAppName);
	}

	@Override
	public void updateBillDetail(BillDetail billDetail, String webAppName) {
		dao.updateBillDetail(billDetail, webAppName);
		
	}

	@Override
	public void removeBillDetail(BillDetail billDetail,  String deptId,String webAppName) {
		dao.removeBillDetail(billDetail, deptId,webAppName);
		
	}

	/*@Override
	public void removeBillDetailList(List<String> removeList,  String deptId,String webAppName) {
		dao.removeBillDetailList(removeList, deptId, webAppName);
		
	}*/

	@Override
	public String getNextBillNo(String billNoPrefix, String webAppName) {
		return dao.getNextBillNo(billNoPrefix, webAppName);
	}

	@Override
	public void changeStatusSpestcnd(String projectNo, String deptId,
			String status, String webAppName) {
		dao.changeStatusSpestcnd(projectNo, deptId, status, webAppName);
		
	}

	@Override
	public void createBillDetail(BillDetail billDetail, String webAppName) {
		dao.createBillDetail(billDetail, webAppName);
		
	}

	@Override
	public List<String> getBillNoList(String deptId, Date fromDate,
			Date toDate, String webAppName) {
		return dao.getBillNoList(deptId, fromDate, toDate, webAppName);
	}

	@Override
	public List<BillDetail> getBillDetail(String deptId, Date fromDate,
			Date toDate, String webAppName) {
		return dao.getBillDetail(deptId, fromDate, toDate, webAppName);
	}

	@Override
	public List<String> getProjectNoListByBillNo(String billNo,
			String webAppName) {
		return dao.getProjectNoListByBillNo(billNo, webAppName);
	}

	@Override
	public void removeBillDetailListByBillNo(String billNo, String deptId,
			String webAppName) {
		dao.removeBillDetailListByBillNo(billNo, deptId, webAppName);
		
	}

	@Override
	public List<String> getJobNoListBySubTypeCM_OT(String deptId,
			String status, String applicationType, String webAppName)
			throws PersistenceException {
		return dao.getJobNoListBySubTypeCM_OT(deptId, status, applicationType, webAppName);
	}

	/*@Override
	public List<String> getJobListNoByType(String deptId, String status,
			String catCd, String webAppName) throws PersistenceException {
		return dao.getJobListNoByType(deptId, status, catCd, webAppName);
	}*/

	

	

	

	

	
	

	

}
