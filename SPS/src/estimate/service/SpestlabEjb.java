package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpestlabDaoRemote;
import estimate.model.LabourGrid;
import estimate.model.Spestlab;
import estimate.model.SpestlabPK;

public class SpestlabEjb implements SpestlabEjbI {
	private Context context;
	private SpestlabDaoRemote dao;
	private String region=null;
	
	public SpestlabEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}
	
	private SpestlabDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpestlabDaoRemote dao = (SpestlabDaoRemote) context.lookup("SpestlabDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	

	@Override
	public void createSpestlab(Spestlab spestlab) {
		dao.createSpestlab(spestlab, region);
		
	}

	@Override
	public List<Spestlab> getAll(String deptId) {
		return dao.getAll(deptId, region);
	}

	@Override
	public List<Spestlab> getAll(String estimateNo, String deptId) {
		return dao.getAll(estimateNo, deptId, region);
	}

	@Override
	public void updateSpestlab(Spestlab spestlab) {
		dao.updateSpestlab(spestlab, region);
		
	}

	@Override
	public void removeSpestlab(Spestlab spestlab) {
		dao.removeSpestlab(spestlab, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Spestlab findById(SpestlabPK id) throws PersistenceException {
		return dao.findById(id, region);
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpestlabEjb ejb=new SpestlabEjb("region");
		System.out.println(ejb.getAll("514.20/ENC/2011/0803", "514.20"));
		System.out.println(ejb.getSpestlabList("514.20/ENC/2011/0803", "514.20"));

	}

	@Override
	public List<LabourGrid> getSpestlabList(String estimateNo, String deptId) {
		return dao.getSpestlabList(estimateNo, deptId, region);
	}

	@Override
	public Spestlab findByEstimateNo(String estimateNo, String labourCode) {
		return dao.findByEstimateNo(estimateNo, labourCode, region);
	}

}
