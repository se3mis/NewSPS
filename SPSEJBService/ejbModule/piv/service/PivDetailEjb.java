package piv.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import piv.ejb.PivDetailDaoRemote;
import piv.model.PivDetail;
import piv.model.PivDetailPK;

public class PivDetailEjb implements PivDetailDaoRemote {
	
	private Context context;
	private PivDetailDaoRemote dao; 
	
	public PivDetailEjb() {
		super();
		dao=lookupGldeptmDao();
		
	}

	
	private PivDetailDaoRemote lookupGldeptmDao() {
		try
		{
			 context = new InitialContext();
			 PivDetailDaoRemote dao = (PivDetailDaoRemote) context.lookup("PivDetailDao/remote");
			 return dao; 
			 
		} catch (NamingException e){
			e.printStackTrace();
			
			throw new RuntimeException(e);
		}
		
	}
	

	public void createPiv(PivDetail pivDetail, String webAppName) {
		dao.createPiv(pivDetail, webAppName);
		
	}

	public PivDetail findById(PivDetailPK id, String webAppName) {
		return dao.findById(id, webAppName);
	}

	public void updatePivDetail(PivDetail pivDetail, String webAppName) {
		dao.updatePivDetail(pivDetail, webAppName);
		
	}
	
	@Override
	public List<PivDetail> getAll(String webAppName) {
		return dao.getAll( webAppName);
	}
	
	@Override
	public List<PivDetail> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}
	
	@Override
	public List<PivDetail> getPivDetail(String deptId, String status, String webAppName) {
		return dao.getPivDetail(deptId, status, webAppName);
	}


	


	@Override
	public void removePivDetail(PivDetail pivDetail, String webAppName) {
		dao.removePivDetail(pivDetail, webAppName);
		
	}


	@Override
	public void removeAll( String webAppName) {
		dao.removeAll( webAppName);
		
	}
	
	@Override
	public boolean canGeneratePiv(String deptId, String estimateNo, String referenceType, String webAppName) {
		return dao.canGeneratePiv(deptId, estimateNo, referenceType, webAppName);
	}
	
	@Override
	public PivDetail findByReferenceNo(String deptId,String referenceNo,String referenceType, String webAppName) {
		return dao.findByReferenceNo(deptId, referenceNo,referenceType, webAppName);
	} 
	@Override
	public int getMaxPivSeqNo(String deptId, String referenceNo, String referenceType, String webAppName) {
		return dao.getMaxPivSeqNo(deptId, referenceNo, referenceType, webAppName);
	}

	@Override
	public String getNextPivNo(String pivNoPrefix, String webAppName) {
		return dao.getNextPivNo(pivNoPrefix, webAppName);
	}
	
	@Override
	public String createPivAutoId(PivDetail pivDetail, String pivNoPrefix, String webAppName) {
		return dao.createPivAutoId(pivDetail, pivNoPrefix, webAppName);
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PivDetailEjb ejb=new PivDetailEjb();
		PivDetailPK id=new PivDetailPK();
		id.setPivNo("R8");
		id.setDeptId("510.20");
		//System.out.println(ejb.getPivDetailByRefNo("514.20", "514.20/ENC/2011/0023", "RIP", "SMCTesting"));
		//System.out.println(ejb.findById(id, "SMCTesting"));
		//System.out.println(ejb.getAll( "SMCTesting"));
		//423.20/ENC/2011/1409
		System.out.println(ejb.getPivDetailByStatus("514.20", "514.20/ANC/2011/0384", "N", "dslfnsdjf"));
		System.out.println(ejb.findByReferenceNo("423.20/ENC/2011/1440", "EST", "R1"));
		System.out.println(ejb.hasActivePiv("514.20", "514.20/ENC/2011/0972", "EST", "tetet"));
		System.out.println(ejb.getNewPivNos("514.20", "EST", "N",  "tetet"));
		System.out.println(ejb.getNextPivNo("PIV/423.10/ENC/2011", "R1"));
		System.out.println(ejb.getPivDetailByRefNo("514.20", "514.20/ENC/2011/0947", "ELN", "SMCTesting"));
		System.out.println(ejb.getPivDetailByRefNo("514.20", "514.20/ENC/2011/0947", "SMCTesting"));
		System.out.println(ejb.findByReferenceNo("514.20", "514.20/ENC/2011/0947", "ELN", "SMCTesting"));
		System.out.println(ejb.findByReferenceNo("514.20/ENC/2011/0947", "ELN", "SMCTesting"));
		

	}


	@Override
	public List<PivDetail> getPivDetail(String deptId, String referenceType,
			String status, String webAppName) {
		return dao.getPivDetail(deptId, referenceType, status, webAppName);
	}


	@Override
	public List<PivDetail> getPivDetailByRefNo(String deptId,
			String referenceNo, String referenceType, String webAppName) {
		return dao.getPivDetailByRefNo(deptId, referenceNo, referenceType, webAppName);
	}


	@Override
	public PivDetail findByReferenceNo(String referenceNo,
			String referenceType, String webAppName) {
		return dao.findByReferenceNo(referenceNo, referenceType, webAppName);
	}


	@Override
	public PivDetail findByPivNo(String pivNo, String webAppName) {
		return dao.findByPivNo(pivNo, webAppName);
	}


	@Override
	public List<PivDetail> getPivDetailByRefNo(String deptId,
			String referenceNo, String webAppName) {
		return dao.getPivDetailByRefNo(deptId, referenceNo, webAppName);
	}


	@Override
	public List<PivDetail> getPivDetailByRefNoExcludingDestroy(String deptId,
			String referenceNo, String webAppName) {
		return dao.getPivDetailByRefNoExcludingDestroy(deptId, referenceNo, webAppName);
	}


	@Override
	public List<PivDetail> getPivDetailByRefNoExcludingDestroy(String deptId,
			String referenceNo, String referenceType, String webAppName) {
		return dao.getPivDetailByRefNoExcludingDestroy(deptId, referenceNo, referenceType, webAppName);
	}


	@Override
	public PivDetail findByReferenceNoIncludingSD(String deptId,
			String referenceNo, String referenceType, String webAppName) {
		return dao.findByReferenceNoIncludingSD(deptId, referenceNo, referenceType, webAppName);
	}


	@Override
	public PivDetail findByReferenceNoIncludingSD(String referenceNo,
			String referenceType, String webAppName) {
		return dao.findByReferenceNoIncludingSD(referenceNo, referenceType, webAppName);
	}


	@Override
	public boolean hasActivePiv(String deptId, String referenceNo,
			String referenceType, String webAppName) {
		return dao.hasActivePiv(deptId, referenceNo, referenceType, webAppName);
	}


	@Override
	public List<String> getNewPivNos(String deptId, String referenceType,
			String status, String webAppName) {
		return dao.getNewPivNos(deptId, referenceType, status, webAppName);
	}


	@Override
	public PivDetail getPivDetailByStatus(String deptId, String referenceNo,
			String status, String webAppName) {
		return dao.getPivDetailByStatus(deptId, referenceNo, status, webAppName);
	}


	@Override
	public PivDetail getPivDetailByStatusNRefType(String deptId,
			String referenceNo, String referenceType, String status,
			String webAppName) {
		
		return dao.getPivDetailByStatusNRefType(deptId, referenceNo, referenceType, status, webAppName);
	}


	


	


	


	


	


	


	


	

}
