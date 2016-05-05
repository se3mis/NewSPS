package piv.ejb;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import job.ejb.PcesthmtDaoRemote;
import estimate.ejb.PcesthttDaoRemote;
import estimate.ejb.SpestedyDaoRemote;
import application.ejb.ApplicationDaoRemote;
import application.ejb.SpapplonDaoRemote;
import application.model.Application;
import application.model.SpapplonPK;
import piv.model.PivDetail;
import piv.model.PivDetailPK;
import util.common.EstimateStatus;

/**
 * Session Bean implementation class PivBean
 */
@Stateless
public class PivBean implements PivBeanRemote, PivBeanLocal{
	@Resource
	private SessionContext context;
	
	@EJB
	PivDetailDaoRemote pivDetailDaoRemote;
	@EJB
	ApplicationDaoRemote applicationDaoRemote;
    @EJB
    PcesthttDaoRemote pcesthttDaoRemote;
    @EJB
    PcesthmtDaoRemote pcesthmtDaoRemote;
    @EJB
    SpapplonDaoRemote spapplonDaoRemote;
    @EJB
    SpestedyDaoRemote spestedyDaoRemote;
	/**
     * Default constructor. 
     */
    public PivBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void confirmEstPivDetail(PivDetail pivDetail, String webAppName) {
		try{
			//System.out.println("############################################################");
			pivDetailDaoRemote.updatePivDetail(pivDetail, webAppName);
			//System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			pcesthttDaoRemote.changeStatusPcesthtt(pivDetail.getReferenceNo(), pivDetail.getId().getDeptId(), new Long(EstimateStatus.EST_POSTING), webAppName);
			//System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}catch (Exception e) {
			e.printStackTrace();
			context.setRollbackOnly();
		}	
		
	}
	@Override
	public void confirmEstPivDetail(PivDetail pivDetail, Boolean value, String webAppName) {
		try{
			//System.out.println("############################################################");
			
			pivDetailDaoRemote.updatePivDetail(pivDetail, webAppName);
			//System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			if (pivDetail.getReferenceType().equals("ELN")){
				SpapplonPK id=new SpapplonPK();
				id.setApplicationNo(pivDetail.getReferenceNo());
				id.setDeptId(pivDetail.getId().getDeptId());
				spapplonDaoRemote.calculateLoanAmounts(id, new Double(pivDetail.getPivAmount().toString()), new Double("60"), new Double("12"), webAppName);
			}
			if (value==true)
			pcesthttDaoRemote.changeStatusPcesthtt(pivDetail.getReferenceNo(), pivDetail.getId().getDeptId(), new Long(EstimateStatus.EST_POSTING), webAppName);
			//System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}catch (Exception e) {
			e.printStackTrace();
			context.setRollbackOnly();
		}	
		
	}
	
	
	//@Override
	public void confirmEstPivDetail123(PivDetail pivDetail, Boolean value, String webAppName) {
		try{
			//System.out.println("############################################################");
			
			pivDetailDaoRemote.updatePivDetail(pivDetail, webAppName);
			//System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			if (pivDetail.getReferenceType().equals("ENL")){
				SpapplonPK id=new SpapplonPK();
				id.setApplicationNo(pivDetail.getReferenceNo());
				id.setDeptId(pivDetail.getId().getDeptId());
				spapplonDaoRemote.calculateLoanAmounts(id, new Double(pivDetail.getPivAmount().toString()), new Double("60"), new Double("12"), webAppName);
			}
			if (value==true)
			pcesthttDaoRemote.changeStatusPcesthtt(pivDetail.getReferenceNo(), pivDetail.getId().getDeptId(), new Long(EstimateStatus.EST_POSTING), webAppName);
			//System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}catch (Exception e) {
			e.printStackTrace();
			context.setRollbackOnly();
		}	
		
	}
	
	@Override
	public void confirmJobPivDetail(PivDetail pivDetail, String webAppName) {
		try{
			//System.out.println("############################################################");
			pivDetailDaoRemote.updatePivDetail(pivDetail, webAppName);
			//System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			pcesthmtDaoRemote.changeStatusPcesthmt(pivDetail.getReferenceNo(), pivDetail.getId().getDeptId(), new Long(EstimateStatus.JOB_ONGOING), webAppName);
			//System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}catch (Exception e) {
			e.printStackTrace();
			context.setRollbackOnly();
		}	
		
	}

	@Override
	public void confirm(PivDetail pivDetail, Application application, String webAppName) {
		try{
			pivDetailDaoRemote.updatePivDetail(pivDetail, webAppName);
			applicationDaoRemote.updateApplication(application, webAppName);
		}catch (Exception e) {
			e.printStackTrace();
			context.setRollbackOnly();
		}	
		
	}

	@Override
	public void createPiv(PivDetail pivDetail, String webAppName) {
		pivDetailDaoRemote.createPiv(pivDetail, webAppName);
		
	}

	@Override
	public PivDetail findById(PivDetailPK id, String webAppName) {
		return pivDetailDaoRemote.findById(id, webAppName);
	}

	@Override
	public void updatePivDetail(PivDetail pivDetail, String webAppName) {
		pivDetailDaoRemote.updatePivDetail(pivDetail, webAppName);
		
	}

	@Override
	public List<PivDetail> getAll(String webAppName) {
		return pivDetailDaoRemote.getAll(webAppName);
	}
	
	@Override
	public List<PivDetail> getAll(String deptId, String webAppName) {
		return pivDetailDaoRemote.getAll(deptId, webAppName);
	}
	
	@Override
	public List<PivDetail> getPivDetail(String deptId, String status, String webAppName) {
		return pivDetailDaoRemote.getPivDetail(deptId, status, webAppName);
	}

	

	@Override
	public void removePivDetail(PivDetail pivDetail, String webAppName) {
		pivDetailDaoRemote.removePivDetail(pivDetail, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		pivDetailDaoRemote.removeAll(webAppName);
		
	}

	@Override
	public boolean canGeneratePiv(String deptId, String estimateNo, String referenceType, String webAppName) {
		return pivDetailDaoRemote.canGeneratePiv(deptId, estimateNo, referenceType, webAppName);
	}

	@Override
	public PivDetail findByReferenceNo(String deptId, String referenceNo, String referenceType, String webAppName) {
		return pivDetailDaoRemote.findByReferenceNo(deptId, referenceNo, referenceType, webAppName);
	}

	@Override
	public int getMaxPivSeqNo(String deptId, String referenceNo, String referenceType, String webAppName) {
		return pivDetailDaoRemote.getMaxPivSeqNo(deptId, referenceNo, referenceType, webAppName);
	}

	@Override
	public String createPivAutoId(PivDetail pivDetail, String pivNoPrefix, String webAppName) {
		return pivDetailDaoRemote.createPivAutoId(pivDetail, pivNoPrefix, webAppName);
	}

	@Override
	public void IssuePiv(PivDetail pivDetail, String webAppName) {
		try{
			pivDetailDaoRemote.createPiv(pivDetail, webAppName);
			applicationDaoRemote.changeStatusById(pivDetail.getId().getPivNo(), pivDetail.getId().getDeptId(), "I", webAppName);
		}catch (Exception e) {
			e.printStackTrace();
			context.setRollbackOnly();
		}	
		
	}

	@Override
	public void confirmReInspectionPiv(PivDetail pivDetail, String webAppName) {
		try{
			pivDetailDaoRemote.updatePivDetail(pivDetail, webAppName);
			spestedyDaoRemote.changeStatusAppointments(pivDetail.getReferenceNo(), pivDetail.getId().getDeptId(), "A", webAppName);
			//applicationDaoRemote.changeStatusById(pivDetail.getId().getPivNo(), pivDetail.getId().getDeptId(), "I");
		}catch (Exception e) {
			e.printStackTrace();
			context.setRollbackOnly();
		}	
		
	}

	/*@Override
	public void saveReInspectionPiv(PivDetail pivDetail, String pivNoPrefix) {
		try{
			pivDetailDaoRemote.createPivAutoId(pivDetail, pivNoPrefix);
			spestedyDaoRemote.changeStatusAppointments(pivDetail.getReferenceNo(), pivDetail.getId().getDeptId(), "A");
			//applicationDaoRemote.changeStatusById(pivDetail.getId().getPivNo(), pivDetail.getId().getDeptId(), "I");
		}catch (Exception e) {
			e.printStackTrace();
			context.setRollbackOnly();
		}	
		
	}*/

	

}
