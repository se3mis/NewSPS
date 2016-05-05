package piv.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import piv.ejb.PivBeanRemote;
import piv.model.PivDetail;
import piv.model.PivDetailPK;
import application.model.Application;

public class PivEjb implements PivEjbI {

	private Context context;
	private PivBeanRemote bean;
	private String region=null;
	
	
	public PivEjb(String region) {
		super();
		this.region=region;
		bean=lookupBean();
		
	}
	
	private PivBeanRemote lookupBean() {
		try
		{
			 context = new InitialContext();
			 PivBeanRemote dao = (PivBeanRemote) context.lookup("PivBean/remote");
			 return dao; 
	 		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	public void confirmEstPivDetail(PivDetail pivDetail) {
		bean.confirmEstPivDetail(pivDetail, region);
	}

	@Override
	public void confirm(PivDetail pivDetail, Application application) {
		bean.confirm(pivDetail, application, region);
	}
	
	@Override
	public void createPiv(PivDetail pivDetail) {
		bean.createPiv(pivDetail, region);
		
	}

	@Override
	public PivDetail findById(PivDetailPK id) {
		return bean.findById(id, region);
	}

	@Override
	public void updatePivDetail(PivDetail pivDetail) {
		bean.updatePivDetail(pivDetail, region);
		
	}

	@Override
	public List<PivDetail> getAll() {
		return bean.getAll(region);
	}

	@Override
	public List<PivDetail> getPivDetail(String deptId, String status) {
		return bean.getPivDetail(deptId, status, region);
	}

	@Override
	public List<PivDetail> getAll(String deptId) {
		return bean.getAll(deptId, region);
	}

	@Override
	public void removePivDetail(PivDetail pivDetail) {
		bean.removePivDetail(pivDetail, region);
		
	}

	@Override
	public void removeAll() {
		bean.removeAll(region);
		
	}
	
	@Override
	public boolean canGeneratePiv(String deptId, String estimateNo, String referenceType) {
		return bean.canGeneratePiv(deptId, estimateNo, referenceType, region);
	}
	
	@Override
	public PivDetail findByReferenceNo(String deptId, String referenceNo, String referenceType) {
		return bean.findByReferenceNo(deptId, referenceNo, referenceType, region);
	}
	
	@Override
	public int getMaxPivSeqNo(String deptId, String referenceNo, String referenceType) {
		return bean.getMaxPivSeqNo(deptId, referenceNo, referenceType, region);
	}
	
	

	@Override
	public void confirmJobPivDetail(PivDetail pivDetail) {
		bean.confirmJobPivDetail(pivDetail, region);
		
	}

	@Override
	public String createPivAutoId(PivDetail pivDetail, String pivNoPrefix) {
		return bean.createPivAutoId(pivDetail, pivNoPrefix, region);
	}

	@Override
	public void confirmEstPivDetail(PivDetail pivDetail, Boolean value) {
		bean.confirmEstPivDetail(pivDetail, value, region);
		
	}

	@Override
	public void confirmReInspectionPiv(PivDetail pivDetail) {
		bean.confirmReInspectionPiv(pivDetail, region);
		
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PivEjb ejb= new PivEjb("region");
		System.out.print(ejb.getAll("510.20"));

	}
	

	

	

}
