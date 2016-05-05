package piv.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import application.model.Application;

import piv.ejb.PivBeanRemote;
import piv.model.PivDetail;
import piv.model.PivDetailPK;

public class PivEjb implements PivBeanRemote {
	private Context context;
	private PivBeanRemote bean; 
	
	public PivEjb() {
		super();
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
	public void confirmEstPivDetail(PivDetail pivDetail, String webAppName) {
		bean.confirmEstPivDetail(pivDetail, webAppName);
	}

	@Override
	public void confirm(PivDetail pivDetail, Application application, String webAppName) { 
		bean.confirm(pivDetail, application, webAppName);
	}
	
	@Override
	public void createPiv(PivDetail pivDetail, String webAppName) {
		bean.createPiv(pivDetail, webAppName);
		
	}


	@Override
	public PivDetail findById(PivDetailPK id, String webAppName) {
		return bean.findById(id, webAppName);
	}


	@Override
	public void updatePivDetail(PivDetail pivDetail, String webAppName) {
		bean.updatePivDetail(pivDetail, webAppName);
		
	}


	@Override
	public List<PivDetail> getAll( String webAppName) {
		return bean.getAll(webAppName);
	}

	@Override
	public List<PivDetail> getPivDetail(String deptId, String status, String webAppName) {
		return bean.getPivDetail(deptId, status, webAppName);
	}


	@Override
	public List<PivDetail> getAll(String deptId, String webAppName) {
		return bean.getAll(deptId, webAppName);
	}


	@Override
	public void removePivDetail(PivDetail pivDetail, String webAppName) {
		bean.removePivDetail(pivDetail, webAppName);
		
	}


	@Override
	public void removeAll(String webAppName) {
		bean.removeAll(webAppName);
		
	}
	
	@Override
	public boolean canGeneratePiv(String deptId, String estimateNo, String referenceType, String webAppName) {
		return bean.canGeneratePiv(deptId, estimateNo, referenceType, webAppName);
	}
	
	@Override
	public PivDetail findByReferenceNo(String deptId, String referenceNo, String referenceType, String webAppName) {
		return bean.findByReferenceNo(deptId, referenceNo, referenceType, webAppName);
	}
	
	@Override
	public void confirmJobPivDetail(PivDetail pivDetail, String webAppName) {
		bean.confirmJobPivDetail(pivDetail, webAppName);
		
	}

	@Override
	public int getMaxPivSeqNo(String deptId, String referenceNo, String referenceType, String webAppName) {
		return bean.getMaxPivSeqNo(deptId, referenceNo, referenceType, webAppName);
	}
	
	@Override
	public String createPivAutoId(PivDetail pivDetail, String pivNoPrefix, String webAppName) {
		return bean.createPivAutoId(pivDetail, pivNoPrefix, webAppName);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PivEjb ejb= new PivEjb();
		System.out.println(ejb.getAll("510.20"));
		System.out.println(ejb.findByReferenceNo("3333", "510.20", "APP", "SMCTesting"));
		System.out.println(ejb.canGeneratePiv("510.20", "3333", "APP", "SMCTesting"));

	}

	@Override
	public void confirmEstPivDetail(PivDetail pivDetail, Boolean value, String webAppName) {
		bean.confirmEstPivDetail(pivDetail, value, webAppName);
		
	}

	@Override
	public void IssuePiv(PivDetail pivDetail, String webAppName) {
		bean.IssuePiv(pivDetail, webAppName);
		
	}

	@Override
	public void confirmReInspectionPiv(PivDetail pivDetail, String webAppName) {
		bean.confirmReInspectionPiv(pivDetail, webAppName);
		
	}

	

	

	

	


	

}
