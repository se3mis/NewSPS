package piv.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import piv.ejb.PivDetailDaoRemote;
import piv.model.PivDetail;
import piv.model.PivDetailPK;


public class PivDetailEjb implements PivDetailEjbI {
	private Context context;
	private PivDetailDaoRemote dao; 
	private String region=null;
	
	
	public PivDetailEjb(String region) {
		super();
		this.region=region;
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
	

	@Override
	public void createPiv(PivDetail pivDetail) {
		dao.createPiv(pivDetail, region);
		
	}

	@Override
	public PivDetail findById(PivDetailPK id) {
		return dao.findById(id, region);
	}

	@Override
	public void updatePivDetail(PivDetail pivDetail) {
		dao.updatePivDetail(pivDetail, region);
		
	}
	
	@Override
	public PivDetail findByReferenceNo(String deptId,String referenceNo, String referenceType) {
		return dao.findByReferenceNo(deptId, referenceNo, referenceType, region);
	}
	
	@Override
	public int getMaxPivSeqNo(String deptId, String referenceNo, String referenceType) {
		return dao.getMaxPivSeqNo(deptId, referenceNo, referenceType, region);
	}
	@Override
	public String createPivAutoId(PivDetail pivDetail, String pivNoPrefix) {
		return dao.createPivAutoId(pivDetail, pivNoPrefix, region);
		
	}
	


	@Override
	public List<PivDetail> getAll() {
		return dao.getAll(region);
	}


	@Override
	public List<PivDetail> getAll(String deptId) {
		// TODO Auto-generated method stub
		return dao.getAll(deptId, region);
	}


	@Override
	public List<PivDetail> getPivDetail(String deptId, String status) {
		return dao.getPivDetail(deptId, status, region);
	}


	@Override
	public List<PivDetail> getPivDetail(String deptId, String referenceType,
			String status) {
		return dao.getPivDetail(deptId, referenceType, status, region);
	}


	@Override
	public List<PivDetail> getPivDetailByRefNo(String deptId,
			String referenceNo, String referenceType) {
		return dao.getPivDetailByRefNo(deptId, referenceNo, referenceType, region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PivDetailEjb ejb=new PivDetailEjb("region");
		PivDetailPK id=new PivDetailPK();
		id.setPivNo("R66");
		id.setDeptId("510.20");
		System.out.println(ejb.findById(id));
		PivDetail PivDetail = ejb.findById(id);
		PivDetail.setStatus("N");
		ejb.updatePivDetail(PivDetail);
		System.out.println(ejb.findById(id));
		System.out.println(ejb.findByReferenceNo("R66", "APP"));
		System.out.println(ejb.findByReferenceNo("510.20", "R66", "APP"));
	}


	@Override
	public PivDetail findByReferenceNo(String referenceNo, String referenceType) {
		return dao.findByReferenceNo(referenceNo, referenceType, region);
	}


	@Override
	public List<PivDetail> getPivDetailByRefNo(String deptId, String referenceNo) {
		return dao.getPivDetailByRefNo(deptId, referenceNo, region);
	}


	@Override
	public String getNextPivNo(String pivNoPrefix) {
		return dao.getNextPivNo(pivNoPrefix, region);
	}

	


	

}
