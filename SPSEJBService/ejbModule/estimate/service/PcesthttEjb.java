package estimate.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import job.model.JobRecord;
import com.model.App;
import costcenter.model.TestObject;
import estimate.ejb.PcesthttDaoRemote;
import estimate.model.Appestlim;
import estimate.model.Approval;
import estimate.model.Pcesthtt;
import estimate.model.PcesthttPK;
import estimate.model.ReferenceDeleteInfo;

public class PcesthttEjb implements PcesthttDaoRemote {
	private Context context;
	private PcesthttDaoRemote dao; 
	public PcesthttEjb() {
		super();
		this.dao=lookupDao();
		
	}
	
	private PcesthttDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 PcesthttDaoRemote dao = (PcesthttDaoRemote) context.lookup("PcesthttDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	@Override
	public void updatePcesthtt(Pcesthtt pcesthtt, String webAppName) {
		dao.updatePcesthtt(pcesthtt, webAppName);
		
	}

	@Override
	public Pcesthtt findByEstimationNo(String estimateNo,String deptId, String webAppName) {
		return dao.findByEstimationNo(estimateNo,deptId, webAppName);
	}
	@Override
	public Pcesthtt findById(String estimateNo,String deptId,Long revNo, String webAppName) {
		return dao.findById(estimateNo, deptId, revNo, webAppName);
	}
	
	@Override
	public List<App> getAppList(String webAppName) {
		return dao.getAppList(webAppName);
	}
	
	//@SuppressWarnings("unchecked")
	@Override
	public List<Pcesthtt> getEstimateList(String deptId,Long status, String webAppName) {
		return dao.getEstimateList(deptId,status, webAppName);
	}
	@Override
	public List<String> getEstimateNoList(String deptId, String webAppName) {
		return dao.getEstimateNoList(deptId, webAppName);
	}
	
	@Override
	public List<String> getEstimateNoList(String deptId, Long status, String webAppName) {
		return dao.getEstimateNoList(deptId, status, webAppName);
	}
	@Override
	public List<ReferenceDeleteInfo> getReferenceInfoList(String deptId, Long status, String webAppName) {
		return dao.getReferenceInfoList(deptId, status, webAppName);
	}
	@Override
	public List<JobRecord> getJobRecordInfoList(String deptId,Long status, String webAppName) {
		return dao.getJobRecordInfoList(deptId, status, webAppName);
	}
	@Override
	public List<Pcesthtt> getEstApprovalList(String deptId,Long status, String value, String webAppName) {
		return dao.getEstApprovalList(deptId, status, value, webAppName);
	}
	@Override
	public Pcesthtt findById(PcesthttPK id, String webAppName) {
		return dao.findById(id, webAppName);
	}
	
	@Override
	public void createPcesthtt(Pcesthtt pcesthtt, String webAppName) {
		dao.createPcesthtt(pcesthtt, webAppName);
		
	}

	@Override
	public List<Pcesthtt> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public void removePcesthtt(Pcesthtt pcesthtt, String webAppName) {
		dao.removePcesthtt(pcesthtt, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public List<Pcesthtt> getJobinfo(Long status, String webAppName) {
		return dao.getJobinfo(status, webAppName);
	}

	@Override
	public List<String> getEstimateNoList(String webAppName) {
		return dao.getEstimateNoList(webAppName);
	}

	@Override
	public Pcesthtt getJobDetails1(Long status, String webAppName) {
		return dao.getJobDetails1(status, webAppName);
	}

	@Override
	public Pcesthtt findByApplicationNo(String estimateNo, String deptId, String webAppName) {
		return dao.findByApplicationNo(estimateNo, deptId, webAppName);
	}
	
	@Override
	public List<TestObject> getEstimateNoList1(String deptId, String webAppName) {
		return dao.getEstimateNoList1(deptId, webAppName);
	}

	@Override
	public long getHttRevNo(String estimateNo, String deptId, String webAppName) {
		return dao.getHttRevNo(estimateNo, deptId, webAppName);
	}
	
	@Override
	public void changeStatusPcesthtt(String estimateNo, String deptId, Long status, String webAppName) {
		dao.changeStatusPcesthtt(estimateNo, deptId, status, webAppName);		
	}
	
	//@Override
	//public List<Pcesthtt> getEstApprovalReport(String deptId, BigDecimal status, String value, String webAppName) {
	//	return dao.getEstApprovalReport(deptId, status, value, webAppName);
	//}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PcesthttEjb ejb=new PcesthttEjb();
		PcesthttPK id2=new PcesthttPK();
		id2.setEstimateNo("510.20/ET1/2011/0001");
		id2.setDeptId("510.20");
		id2.setRevNo(2);
		System.out.println("EE 433 : "+ejb.getNextEstimateNo("711.00/CN/15/", "711.00","R3"));
		//System.out.println("EE 433 : "+ejb.findAppEstLimits("433.00", "EST", "DE", "EE", "R2"));
		//String sss = ejb.recommendEstimate("2013-124", "47", "CE", "52020CE","DGMCP", "DE","", "R4");
		//System.out.println("EE 433 : "+ sss);
		/**System.out.println("ApprovedEst"+ejb.getApprovedEst("521.20", "NC","521EE", "30", "30", "R4"));
		System.out.println("EE 433 : "+ejb.findAppEstLimits("433.00", "EST", "DE", "EE", "R2"));
		System.out.println("ApprovedEst"+ejb.getEstimateNoListMT_SA("514.20", new Long(75), "R1sss"));
		System.out.println("ApprovedEst"+ejb.getApprovedEst("513.10", "NC","51310ES1", "30", "30", "R3"));
		System.out.println(ejb.getEstApprovalList("541.00", new Long(47), "CE", "R1"));
		System.out.println("ES"+ejb.getEstNoApprovalListNew("423.00", "EE", "R1"));
		System.out.println("AGM"+ejb.getEstNoApprovalListNewST("514.20", "ES", "CR", "CP", "R11"));
		System.out.println("AGM"+ejb.getEstNoApprovalListNewST("514.20", "EA", "CR", "CP", "R11"));
		
		System.out.println("AGM"+ejb.findByEstimationNo("514.20/ENC/2011/0967", "R11"));
		System.out.println("AGM"+ejb.findByEstimationNo("514.20/ENC/2011/0967","514.20", "R11"));
		//System.out.println(ejb.getEstApprovalReport("514.20", new BigDecimal(46), "EA", "SMCTesting"));
		System.out.println("AGM"+ejb.getEstNoApprovalListNewST("514.20", "ES", "CR", "CC", "SMCTesting"));
		System.out.println(ejb.getEstNoApprovalListNewT("423.00", "EE", "NC", "R1"));
		System.out.println(ejb.findAppEstLimits("514.20", "EST", "NC", "ED", "dgdg"));
		//System.out.println(ejb.approveEstimate("514.20/ENC/2011/0006", "514.20", "ES", "ES123","R1dddd"));
		System.out.println(ejb.getEstApprovalList("514.20", new Long(45), "ES", "SMCTesting"));
		System.out.println(ejb.findAppEstLimits("514.20", "EST","NC", "ES", "SMCGG"));
		System.out.println(ejb.findById(id2, "SMCTesting"));
		System.out.println(ejb.findById("510.20/ET1/2011/0001", "510.20", new Long(2), "SMCTesting"));
		ejb.changeCostCenterNoPcesthtt("541.20/SNC/2011/0001", "541.00", "541.20", "SMCTesting");
		System.out.println(ejb.getEstimateNoList("443.20", new Long(44), "SMCTesting"));
		System.out.println(ejb.getEstimateNoListT("443.20", new Long(44), "NL", "SMCTesting"));
		System.out.println("$$ "+ejb.getEstimateNoListST("514.20", new Long(44), "NL", "", "SMCTesting"));
		System.out.println("ES"+ejb.getEstNoApprovalListNew("423.10", "ES", "SMCTesting"));
		System.out.println("ES"+ejb.getEstApprovalListNew("423.10", "ES", "SMCTesting"));
		System.out.println("EA"+ejb.getEstApprovalListNew("423.10", "EA", "SMCTesting"));
		System.out.println("AE"+ejb.getEstApprovalListNew("423.10", "AE", "SMCTesting"));
		System.out.println("DGM"+ejb.getEstApprovalListNew("423.10", "DGM", "SMCTesting"));
		System.out.println("AGM"+ejb.getEstApprovalListNew("423.10", "AGM", "SMCTesting"));
		System.out.println("##"+"ES"+ejb.getEstNoApprovalListNewT("423.10", "ES", "NL", "SMCTesting"));
		System.out.println("ES"+ejb.getEstNoApprovalListNewT("423.10", "ES", "NL", "SMCTesting"));
		System.out.println("EA"+ejb.getEstNoApprovalListNewT("423.10", "EA", "NL", "SMCTesting"));
		System.out.println("AE"+ejb.getEstNoApprovalListNewT("4423.10", "AE", "NL", "SMCTesting"));
		System.out.println("DGM"+ejb.getEstNoApprovalListNewT("423.10", "DGM", "NL", "SMCTesting"));
		System.out.println("AGM"+ejb.getEstNoApprovalListNewT("423.10", "AGM", "NL", "SMCTesting"));
		System.out.println("AGM"+ejb.getEstNoApprovalListNewST("514.20", "ES", "CR", "CC", "SMCTesting"));
		
		//System.out.println(ejb.approveEstimate("443.20/SNL/2011/0001", "443.20", "AGM"));
		
		//System.out.println(ejb.getReferenceInfoList("510.20", new BigDecimal(75)));
		//System.out.println(ejb.getEstimateNoList("510.20", new BigDecimal(75)));
		System.out.println("514.20 "+ejb.getEstApprovalList("514.20", new Long(44), "ES", "SMCTesting"));
		//ejb.changeStatusPcesthtt("443.20/SNL/2011/0004", "443.20", new BigDecimal(EstimateStatus.EST_POSTING));
		System.out.println(ejb.findByEstimationNo("510.20/SNL/2010/0006", "510.20", "SMCTesting"));
		//System.out.println(ejb.getEstimateNoList1("510.20", "SMCTesting").size());
		//System.out.println(ejb.getEstimateNoList1("510.20", "SMCTesting"));
		//System.out.println(ejb.getTEstimateNoList1("510.20"));
		//System.out.println(ejb.getEstimateNoList1("510.20").get(0).get("a"));
		//System.out.println(ejb.lookupDao().getJobDetails1(new BigDecimal(75)));
		//System.out.println(ejb.getAppList());
		//System.out.println(ejb.getJobDetails("440.20",new BigDecimal(75)));
		//System.out.println(ejb.findByEstimationNo("bxbxbxb","440.20"));
		//System.out.println(ejb.getEstimateNoList("440.20"));
		//System.out.println(ejb.getReferenceDeleteInfoList("440.20"));
		//System.out.println(ejb.getJobRecordInfoList("440.20", new BigDecimal(75)));
		System.out.println("596.00");
		System.out.println(ejb.getEstApprovalList("514.20", new Long(75), "ES", "SMCTesting").size());
		System.out.println(ejb.getEstApprovalList("514.20", new Long(75), "EA", "SMCTesting").size());
		System.out.println(ejb.getEstApprovalList("514.20", new Long(75), "EA", "SMCTesting").size());
		System.out.println(ejb.getEstApprovalList("514.20", new Long(75), "EE", "SMCTesting").size());
		System.out.println(ejb.getEstApprovalList("514.20", new Long(75), "DGM", "SMCTesting").size());
		System.out.println(ejb.getEstApprovalList("514.20", new Long(75), "AGM", "SMCTesting").size());
		System.out.println(ejb.getEstApprovalList("514.20", new Long(75), "ES", "SMCTesting"));
		System.out.println(ejb.getEstApprovalList("514.20", new Long(75), "EA", "SMCTesting"));
		System.out.println(ejb.getEstApprovalList("514.20", new Long(75), "EA", "SMCTesting"));
		System.out.println(ejb.getEstApprovalList("514.20", new Long(75), "EE", "SMCTesting"));
		System.out.println(ejb.getEstApprovalList("514.20", new Long(75), "DGM", "SMCTesting"));
		System.out.println(ejb.getEstApprovalList("514.20", new Long(75), "AGM", "SMCTesting"));
		System.out.println("514.20");
		PcesthttPK id = new PcesthttPK();
        id.setDeptId("510.20");
        id.setEstimateNo("BS/2009/002         ");
        id.setRevNo(1);
        System.out.println(ejb.findById(id, "SMCTesting"));
        //System.out.println(ejb.findByEstimationNo("BS/2009/002", "510.20"));
        //System.out.println(ejb.findById("BS/2009/002", "510.20", new Long("1")));
        //System.out.println(ejb.findByEstimationNo("ABC/2/2006", "501.20"));
        System.out.println(ejb.findByEstimationNo("080479", "440.20", "SMCTesting"));
        Pcesthtt pcesthtt=ejb.findByEstimationNo("080479", "440.20", "SMCTesting");
        //ejb.removePcesthtt(pcesthtt);
        
        */

	}

	@Override
	public List<Pcesthtt> getEstApprovalListNew(String deptId, String authorityLevel, String webAppName) {
		return dao.getEstApprovalListNew(deptId, authorityLevel, webAppName);
	}

	@Override
	public String approveEstimate(String estimateNo, String deptId, String authorityLevel, String userName,String assignedUserName,String applType,String comment,String webAppName) {
		return dao.approveEstimate(estimateNo, deptId, authorityLevel, userName,assignedUserName,applType,comment, webAppName);
		
	}

	@Override
	public List<String> getEstNoApprovalListNew(String deptId, String authorityLevel, String webAppName) {
		return dao.getEstNoApprovalListNew(deptId, authorityLevel, webAppName);
	}

	//@Override
	//public List<String> getEstimateNoList(String deptId, BigDecimal status, String applicationType, String webAppName) {
	//	return dao.getEstimateNoList(deptId, status, applicationType, webAppName);
	//}

	//@Override
	//public List<String> getEstNoApprovalListNew(String deptId, String authorityLevel, String applicationType, String webAppName) {
	//	return dao.getEstNoApprovalListNew(deptId, authorityLevel, applicationType, webAppName);
	//}

	@Override
	public void changeCostCenterNoPcesthtt(String estimateNo, String areaDeptId, String depotDeptId, String webAppName) {
		dao.changeCostCenterNoPcesthtt(estimateNo, areaDeptId, depotDeptId, webAppName);
		
	}

	//@Override
	//public List<String> getEstimateNoList(String deptId, BigDecimal status, String applicationType, String applicationSubType, String webAppName) {
	//	return dao.getEstimateNoList(deptId, status, applicationType, applicationSubType, webAppName);
	//}

	//@Override
	//public List<String> getEstNoApprovalListNew(String deptId, String authorityLevel, String applicationType, String applicationSubType, String webAppName) {
	//	return dao.getEstNoApprovalListNew(deptId, authorityLevel, applicationType, applicationSubType, webAppName);
	//}

	@Override
	public List<String> getEstimateNoListT(String deptId, Long status,
			String applicationType, String webAppName) {
		return dao.getEstimateNoListT(deptId, status, applicationType, webAppName);
	}

	@Override
	public List<String> getEstimateNoListST(String deptId, Long status,
			String applicationType, String applicationSubType, String webAppName) {
		return dao.getEstimateNoListST(deptId, status, applicationType, applicationSubType, webAppName);
	}

	@Override
	public List<String> getEstNoApprovalListNewT(String deptId,
			String authorityLevel, String applicationType, String webAppName) {
		return dao.getEstNoApprovalListNewT(deptId, authorityLevel, applicationType, webAppName);
	}

	@Override
	public List<String> getEstNoApprovalListNewST(String deptId,
			String authorityLevel, String applicationType,
			String applicationSubType, String webAppName) {
		return dao.getEstNoApprovalListNewST(deptId, authorityLevel, applicationType, applicationSubType, webAppName);
	}

	@Override
	public void validateEstimate(Pcesthtt pcesthtt, Approval approval,
			String webAppName) {
		dao.validateEstimate(pcesthtt, approval, webAppName);
		
	}

	@Override
	public void rejectEstimate(Pcesthtt pcesthtt, Approval approval,
			String webAppName) {
		dao.rejectEstimate(pcesthtt, approval, webAppName);
		
	}

	@Override
	public List<Pcesthtt> getEstimateList(String deptId, Long revNo,
			Long status, String webAppName) {
		return dao.getEstimateList(deptId, revNo, status, webAppName);
	}

	@Override
	public List<String> getEstimateNoList(String deptId, Long revNo,
			Long status, String webAppName) {
		return dao.getEstimateNoList(deptId, revNo, status, webAppName);
	}

	@Override
	public Appestlim findAppEstLimits(String costCenterNo, String approvalType,
			String applicationType, String userLevel, String webAppName) {
		return dao.findAppEstLimits(costCenterNo, approvalType,applicationType, userLevel, webAppName);
	}

	@Override
	public Pcesthtt findByEstimationNo(String estimateNo, String webAppName) {
		return dao.findByEstimationNo(estimateNo, webAppName);
	}

	@Override
	public List<String> getApprovedEst(String deptId,String applicationType,String approvedBy,
			String toStatus, String status, String webAppName) {
		return dao.getApprovedEst(deptId,applicationType,approvedBy, toStatus, status, webAppName);
	}

	@Override
	public void cancelApproval(String estimateNo, String deptId,
			Approval approval, String webAppName) {
		dao.cancelApproval(estimateNo, deptId, approval, webAppName);
		
	}

	@Override
	public List<String> getEstimateNoListMT_SA(String deptId, Long status, String webAppName) {
		return dao.getEstimateNoListMT_SA(deptId, status, webAppName);
	}

	@Override
	public List<String> getEstNoApprovalListNewMT_SA(String deptId, String authorityLevel, String webAppName) {
		return dao.getEstNoApprovalListNewMT_SA(deptId, authorityLevel, webAppName);
	}

	@Override
	public String approveEstimateMA_SA(String estimateNo, String deptId,
			String authorityLevel, String userName, String webAppName) {
		return dao.approveEstimateMA_SA(estimateNo, deptId, authorityLevel, userName, webAppName);
	}

	@Override
	public List<String> getApprovedEstMA_SA(String deptId, String approvedBy,
			String toStatus, String status, String webAppName) {
		return dao.getApprovedEstMA_SA(deptId, approvedBy, toStatus, status, webAppName);
	}

	@Override
	public List<Pcesthtt> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}
	public Pcesthtt findByEstimationNo(String estimateNo,String deptId,Long revNo, String webAppName){
		return dao.findByEstimationNo(estimateNo,deptId,revNo, webAppName);
	}

	@Override
	public int updateEstimateDetails(String estimateNo, String deptId,
			Long status, String rejectedBy, Date rejectedDate,
			String approvedByEE, Date approvedEEDate, String approvedByCE,
			Date approvedCEDate, String approvedByDGM, Date approvedDGMDate,String rejectedReason,
			String webAppName) {
		
		return dao.updateEstimateDetails(estimateNo, deptId, status, rejectedBy, rejectedDate, approvedByEE, approvedEEDate, approvedByCE, approvedCEDate, approvedByDGM, approvedDGMDate,rejectedReason, webAppName);
	}

	@Override
	public List<Pcesthtt> getEstimateList(String deptId, Long revNo,
			List<Long> status, String userId, String webAppName) {
		return dao.getEstimateList(deptId, revNo, status, userId, webAppName);
	}

	@Override
	public List<String> getEstimateNoList(String deptId, List<Long> status,
			String username, String webAppName) {
		return dao.getEstimateNoList(deptId, status,  username, webAppName);
	}

	@Override
	public String recommendEstimate(String estimateNo, String deptId,
			String authorityLevel, String userName, String assignedUserName,
			String applicationType ,String comment, String webAppName) {
		return dao.recommendEstimate( estimateNo,  deptId,
				 authorityLevel,  userName,  assignedUserName,
				 applicationType,comment,  webAppName);
	}

	@Override
	public List<Pcesthtt> getEstimateListForVSLAuthorizedCC(List<String> deptIds,
			Long revNo, List<Long> status, String userId, String webAppName) {
		
		return dao.getEstimateListForVSLAuthorizedCC(deptIds,revNo,status,userId,webAppName);
	}

	@Override
	public List<Pcesthtt> getEstimateListForAuthorizedCC(List<String> deptIds,
			Long revNo, List<Long> status, String userId,String likeEstimate, String webAppName) {
		// TODO Auto-generated method stub
		return dao.getEstimateListForAuthorizedCC(deptIds,revNo,status,userId,likeEstimate,webAppName);
	}

	@Override
	public List<String> getEstimateNoList(String deptId, Long revNo,
			List<Long> status, String userId, String webAppName) {
		return dao.getEstimateNoList(deptId,revNo,status,userId,webAppName);
	}

	@Override
	public long getMinHttRevNo(String estimateNo, String deptId,
			String webAppName) {
		// TODO Auto-generated method stub
		return dao.getMinHttRevNo(estimateNo, deptId, webAppName);
	}

	@Override
	public String getNextEstimateNo(String estimateNoPrefix, String deptId,
			String webAppName) {
		
		return dao.getNextEstimateNo(estimateNoPrefix, deptId, webAppName);
	}

	@Override
	public long getMaxHttRevNo(String estimateNo, String deptId,String webAppName) {
		// TODO Auto-generated method stub
		return dao.getMaxHttRevNo(estimateNo, deptId,webAppName);
	}

	

	

	

	

	

}
