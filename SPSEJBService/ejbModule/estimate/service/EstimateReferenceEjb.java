package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.EstimateReferenceDaoRemote;
import estimate.model.EstimateReference;
import estimate.model.EstimateReferencePK;
import estimate.model.PcesthttPK;


import application.ejb.ApplicationReferenceDaoRemote;
import application.model.ApplicationReference;
import application.model.ApplicationReferencePK;

public class EstimateReferenceEjb implements EstimateReferenceDaoRemote{
	private Context context;
	private EstimateReferenceDaoRemote dao; 
	
	
	
	public EstimateReferenceEjb() {
		super();
		this.dao=lookupDao();
	}

	private EstimateReferenceDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 EstimateReferenceDaoRemote dao = (EstimateReferenceDaoRemote) context.lookup("EstimateReferenceDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	
	@Override
	public EstimateReference findByAppId(EstimateReferencePK id, String webAppName) {
		return dao.findByAppId(id, webAppName);
	}

	@Override
	public List<EstimateReference> getAll( String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public void createEstimateReference(	EstimateReference estimateReference, String webAppName) {
		dao.createEstimateReference(estimateReference, webAppName);
		
	}

	@Override
	public void updateEstimateReference(EstimateReference estimateReference, String webAppName) {
		dao.updateEstimateReference(estimateReference, webAppName);
		
	}

	@Override
	public List<EstimateReference> findByStdEstimateNo(String stdestimateNo,String deptId, String webAppName)throws PersistenceException {
		return dao.findByStdEstimateNo(stdestimateNo,deptId, webAppName);
	}
	
	@Override
	public List<EstimateReference> findByStdEstimateNoCom(String deptId, String webAppName)throws PersistenceException {
		return dao.findByStdEstimateNoCom(deptId, webAppName);
	}

	@Override
	public int removEstimateReference( EstimateReference estimateReference, String webAppName) {
		return dao.removEstimateReference(estimateReference, webAppName);
		
	}
	
	@Override
	public String getNextEstimateNo(String estimateNoPrefix, String webAppName) {
		return dao.getNextEstimateNo(estimateNoPrefix, webAppName);
	}

	@Override
	public void removeAll( String webAppName) {
		dao.removeAll(webAppName);
		
	}
	/*
	 * @P
	 */
			
	

	@Override
	public EstimateReference findByJobNo(String jobNo, String deptId, String webAppName) throws PersistenceException {
		return dao.findByJobNo(jobNo,deptId, webAppName);
	}

	@Override
	public String getNextJobNo(String jobNoPrefix, String webAppName) {
		return dao.getNextJobNo(jobNoPrefix, webAppName);
	}

	@Override
	public EstimateReference findByWorkEstimateNo(String wrEstimateNo,String deptId,
			String webAppName) throws PersistenceException {
		return dao.findByWorkEstimateNo(wrEstimateNo,deptId, webAppName);
	}

	@Override
	public List<EstimateReference> getWorkEstimatesBySEstimateNo(
			String sEstimateNo, String deptId, String webAppName)
			throws PersistenceException {
		return dao.getWorkEstimatesBySEstimateNo(sEstimateNo,deptId, webAppName);
	}

	@Override
	public boolean checkEstimateNoExist(String stdestimateNo,
			String wtdestimateNo, String deptId, String webAppName)
			throws PersistenceException {
		return dao.checkEstimateNoExist(stdestimateNo,wtdestimateNo,deptId, webAppName);
	}

	@Override
	public List<EstimateReference> getActiveConstructionEstimates(
			String deptId, String webAppName) throws PersistenceException {
		return dao.getActiveConstructionEstimates(deptId, webAppName);
	}

	@Override
	public String getNextEstimateNoViaCommReference(String applicationNoPrefix,
			String webAppName) throws PersistenceException {
		// TODO Auto-generated method stub
		return dao.getNextEstimateNoViaCommReference(applicationNoPrefix, webAppName);
	}

	@Override
	public String getNextFileRefNo(String fileRefPrefix, String deptId,
			String webAppName) {
		// TODO Auto-generated method stub
		return dao.getNextFileRefNo(fileRefPrefix,deptId, webAppName);
	}
	
	public static void main(String[] args) {
		EstimateReferenceEjb ejb=new EstimateReferenceEjb();
		
		System.out.println("std list : "+ejb.findByStdEstimateNoCom("530","R3"));
	}

	
	
	
	

}
