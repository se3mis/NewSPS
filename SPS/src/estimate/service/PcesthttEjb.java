package estimate.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.PcesthttDaoRemote;
//import estimate.model.Pcesthtt;
//import estimate.model.PcesthttPK;
//import estimate.model.ReferenceDeleteInfo;
import estimate.model.Approval;
import estimate.model.Pcesthtt;
import estimate.model.PcesthttPK;
import estimate.model.ReferenceDeleteInfo;

public class PcesthttEjb implements PcesthttEjbI {
	private Context context;
	private PcesthttDaoRemote dao;
	private String region=null;
	
	public PcesthttEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
	}
	
	
	private PcesthttDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 PcesthttDaoRemote dao = (PcesthttDaoRemote) context.lookup("PcesthttDao/remote");
			 //System.out.println(applicantDao);
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}



	
	
	@Override
	public void createAPcesthtt(Pcesthtt pcesthtt) {
		dao.createPcesthtt(pcesthtt, region);
		
	}

	@Override
	public List<Pcesthtt> getAll() {
		return dao.getAll(region);
	}

	@Override
	public void updatePcesthtt(Pcesthtt pcesthtt) {
		dao.updatePcesthtt(pcesthtt, region);
		
	}

	@Override
	public void removePcesthtt(Pcesthtt pcesthtt) {
		dao.removePcesthtt(pcesthtt, region);
		
	}

	@Override
	public Pcesthtt findById(PcesthttPK id) throws PersistenceException {
		return dao.findById(id, region);
	}

	@Override
	public Pcesthtt findByEstimationNo(String estimateNo, String deptId) {
		return dao.findByEstimationNo(estimateNo, deptId, region);
	}

	@Override
	public List<Pcesthtt> getEstimateList(String deptId, Long status) {
		return dao.getEstimateList(deptId, status, region);
	}
	
	@Override
	public List<String> getEstimateNoList(String deptId, Long status) {
		return dao.getEstimateNoList(deptId, status, region);
	}
	
	@Override
	public List<ReferenceDeleteInfo> getReferenceInfoList(String deptId, Long status) {
		return dao.getReferenceInfoList(deptId, status, region);
	}
	
	//@Override
	//public List<Pcesthtt> getEstApprovalList(String deptId, BigDecimal status,String value) {
	//	return dao.getEstApprovalList(deptId, status, value, region);
	//}
	@Override
	public List<String> getEstimateNoList(String deptId) {
		return dao.getEstimateNoList(deptId, region);
	}
	
	@Override
	public long getHttRevNo(String estimateNo, String deptId) {
		return dao.getHttRevNo(estimateNo, deptId, region);
	}
	
	@Override
	public void changeStatusPcesthtt(String estimateNo, String deptId, Long status) {
		dao.changeStatusPcesthtt(estimateNo, deptId, status, region);
		
	}
	
	


	@Override
	public List<Pcesthtt> getEstApprovalListNew(String deptId, String authorityLevel) {
		return dao.getEstApprovalListNew(deptId, authorityLevel, region);
	}


	@Override
	public String approveEstimate(String estimateNo, String deptId, String authorityLevel, String userName, String assignUserName,String applType,String comment) {
		return dao.approveEstimate(estimateNo, deptId, authorityLevel, userName,assignUserName,applType,comment, region);
	}
		

	//@Override
	//public List<String> getEstNoApprovalListNew(String deptId, String authorityLevel) {
	//	return dao.getEstNoApprovalListNew(deptId, authorityLevel, region);
	//}


	//@Override
	//public List<String> getEstimateNoList(String deptId, BigDecimal status, String applicationType) {
	//	return getEstimateNoList(deptId, status, applicationType, region);
	//}


	//@Override
	//public List<String> getEstimateNoList(String deptId, BigDecimal status, String applicationType, String applicationSubType) {
	//	return getEstimateNoList(deptId, status, applicationType, applicationSubType, region);
	//}


	//@Override
	//public List<String> getEstNoApprovalListNew(String deptId, String value,
	//		String applicationType) {
	//	return dao.getEstNoApprovalListNew(deptId, deptId, applicationType, region);
	//}


	//@Override
	//public List<String> getEstNoApprovalListNew(String deptId, String value, String applicationType, String applicationSubType) {
	//	return dao.getEstNoApprovalListNew(deptId, deptId, applicationType, applicationSubType, region);
	//}


	@Override
	public List<String> getEstimateNoListT(String deptId, Long status,
			String applicationType) { 
		return dao.getEstimateNoListT(deptId, status, applicationType, region);
	}


	@Override
	public List<String> getEstimateNoListST(String deptId, Long status,
			String applicationType, String applicationSubType) {
		return dao.getEstimateNoListST(deptId, status, applicationType, applicationSubType, region);
	}


	@Override
	public List<String> getEstNoApprovalListNewT(String deptId,
			String authorityLevel, String applicationType) {
		return dao.getEstNoApprovalListNewT(deptId, authorityLevel, applicationType, region);
	}


	@Override
	public List<String> getEstNoApprovalListNewST(String deptId,
			String authorityLevel, String applicationType,
			String applicationSubType) {
		return dao.getEstNoApprovalListNewST(deptId, authorityLevel, applicationType, applicationSubType, region);
	}


	@Override
	public Pcesthtt findById(String estimateNo, String deptId, Long revNo) {
		return dao.findById(estimateNo, deptId, revNo, region);
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PcesthttEjb ejb=new PcesthttEjb("region");
		//System.out.println(ejb.lookupDao().getJobDetails1(new BigDecimal(75)));
		System.out.println(ejb.getEstimateList("440.20",new Long(75)));
		System.out.println(ejb.getEstimateList("440.20",new Long(75)) );
		System.out.println(ejb.getEstimateNoList("443.20", new Long(44)));
		System.out.println(ejb.getEstimateNoListT("443.20", new Long(44), "NL"));
		System.out.println(ejb.getEstimateNoListT("443.20", new Long(44), "NL"));
		System.out.println(ejb.getEstimateNoListST("443.20", new Long(44), "NL", ""));
		//
		System.out.println("ES"+ejb.getEstNoApprovalListNewT("443.20", "ES", "NL"));
		System.out.println("ES"+ejb.getEstNoApprovalListNewT("443.20", "ES", "NL"));
		System.out.println("EA"+ejb.getEstNoApprovalListNewT("443.20", "EA", "NL"));
		System.out.println("AE"+ejb.getEstNoApprovalListNewT("443.20", "AE", "NL"));
		System.out.println("DGM"+ejb.getEstNoApprovalListNewT("443.20", "DGM", "NL"));
		System.out.println("AGM"+ejb.getEstNoApprovalListNewT("443.20", "AGM", "NL"));
		System.out.println("AGM"+ejb.getEstNoApprovalListNewST("443.20", "AGM", "NL", " "));
		
		
		
		//System.out.println(ejb.findByEstimationNo("bxbxbxb","440.20"));
		//Pcesthtt pcesthtt= ejb.findByEstimationNo("bxbxbxb","440.20");
		//pcesthtt.setStatus(new BigDecimal(75));
		//ejb.updatePcesthtt(pcesthtt);
		//System.out.println(ejb.findByEstimationNo("bxbxbxb","440.20"));
		System.out.println("getReferenceDeleteInfoList "+ejb.getReferenceInfoList("440.20",new Long(75)));
		Pcesthtt pcesthtt2= ejb.findByEstimationNo("440.20/TEST/2010/004","440.20");
		pcesthtt2.setStatus(new Long(75));
		ejb.updatePcesthtt(pcesthtt2);
		System.out.println(ejb.findByEstimationNo("440.20/TEST/2010/004","440.20"));
		//System.out.println(ejb.getEstApprovalList("596.00", new BigDecimal(75), "ES"));
		System.out.println(ejb.getEstimateNoList("440.20"));
		//System.out.println(ejb.findByEstimationNo("SMC/2009/003", "510.20"));
		System.out.println(ejb.getEstimateList("510.20", new Long(31)));
	  	 
	}


	@Override
	public void validateEstimate(Pcesthtt pcesthtt, Approval approval) {
		dao.validateEstimate(pcesthtt, approval, region);
		
	}


	@Override
	public void rejectEstimate(Pcesthtt pcesthtt, Approval approval) {
		dao.rejectEstimate(pcesthtt, approval, region);
		
	}


	@Override
	public List<Pcesthtt> getEstimateList(String deptId, Long revNo,
			Long status) {
		return dao.getEstimateList(deptId, revNo, status, region);
	}


	@Override
	public List<String> getEstimateNoList(String deptId, Long revNo,
			Long status) {
		return dao.getEstimateNoList(deptId, revNo, status, region);
	}


	@Override
	public Pcesthtt findByEstimationNo(String estimateNo) {
		return dao.findByEstimationNo(estimateNo, region);
	}


	@Override
	public List<String> getApprovedEst(String deptId, String applicationType,String approvedBy, String toStatus,
			String status) {
		return dao.getApprovedEst(deptId, applicationType,approvedBy, toStatus, status, region);
	}


	@Override
	public void cancelApproval(String estimateNo, String deptId,
			Approval approval) {
		dao.cancelApproval(estimateNo, deptId, approval, region);
		
	}


	@Override
	public List<String> getEstimateNoListMT_SA(String deptId, Long status) {
		return dao.getEstimateNoListMT_SA(deptId, status, region);
	}


	@Override
	public List<String> getEstNoApprovalListNewMT_SA(String deptId,
			String authorityLevel) {
		return dao.getEstNoApprovalListNewMT_SA(deptId, authorityLevel, region);
	}


	@Override
	public String approveEstimateMA_SA(String estimateNo, String deptId,
			String authorityLevel, String userName) {
		return dao.approveEstimateMA_SA(estimateNo, deptId, authorityLevel, userName, region);
	}


	@Override
	public List<String> getApprovedEstMA_SA(String deptId, String approvedBy,
			String toStatus, String status) {
		return dao.getApprovedEstMA_SA(deptId, approvedBy, toStatus, toStatus, region);
	}
	public Pcesthtt findByEstimationNo(String estimateNo,String deptId,Long revNo){
		return dao.findByEstimationNo(estimateNo,deptId,revNo, region);
	}

	public int updateEstimateDetails(String estimateNo, String deptId,
			Long status, String rejectedBy, Date rejectedDate,
			String approvedByEE, Date approvedEEDate, String approvedByCE,
			Date approvedCEDate, String approvedByDGM, Date approvedDGMDate,String rejectedReason) {
		
		return dao.updateEstimateDetails(estimateNo, deptId, status, rejectedBy, rejectedDate, approvedByEE, approvedEEDate, approvedByCE, approvedCEDate, approvedByDGM, approvedDGMDate,rejectedReason, region);
	}


	public List<Pcesthtt> getEstimateList(String deptId, Long revNo,
			List<Long> status, String userId) {
		return dao.getEstimateList(deptId, revNo, status, userId, region);
	}


	@Override
	public int updateEstimateDetails(String estimateNo, String deptId,
			BigDecimal status, String rejectedBy, Date rejectedDate,
			String approvedByEE, Date approvedEEDate, String approvedByCE,
			Date approvedCEDate, String approvedByDGM, Date approvedDGMDate,
			String rejectedReason, String webAppName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getEstimateNoList(String deptId, List<Long> status,
			String username) {
		return dao.getEstimateNoList(deptId, status,  username,region);
	}
	
	@Override
	public String recommendEstimate(String estimateNo, String deptId,
			String authorityLevel, String userName, String assignedUserName,
			String applicationType,String comment) {
		return dao.recommendEstimate( estimateNo,  deptId,
				 authorityLevel,  userName,  assignedUserName,
				 applicationType,comment,  region);
	}
	@Override
	public List<String> getEstimateNoList(String deptId, Long revNo,
			List<Long> status, String userId) {
		return dao.getEstimateNoList(deptId,revNo,status,userId,region);
	}

	@Override
	public long getMinHttRevNo(String estimateNo, String deptId) {
		// TODO Auto-generated method stub
		return dao.getMinHttRevNo(estimateNo, deptId, region);
	}

	@Override
	public String getNextEstimateNo(String estimateNoPrefix, String deptId,
			String webAppName) {
		
		return dao.getNextEstimateNo(estimateNoPrefix, deptId, webAppName);
	}


	@Override
	public long getMaxHttRevNo(String estimateNo, String deptId,
			String webAppName) {
		// TODO Auto-generated method stub
		//return 0;
		return dao.getMaxHttRevNo(estimateNo, deptId,region);
	}
	
	
	


	


	

}
