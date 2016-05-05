package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SplbsrvcDaoRemote;
import estimate.model.Splbsrvc;
import estimate.model.SplbsrvcPK;

public class SplbsrvcEjb implements SplbsrvcEjbI {
	private Context context;
	private SplbsrvcDaoRemote dao;
	private String region=null;
	
	public SplbsrvcEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}
	
	private SplbsrvcDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SplbsrvcDaoRemote dao = (SplbsrvcDaoRemote) context.lookup("SplbsrvcDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	

	@Override
	public void createSplbsrvc(Splbsrvc splbsrvc) {
		dao.createSplbsrvc(splbsrvc, region);
		
	}

	@Override
	public List<Splbsrvc> getAll(String deptId) {
		return dao.getAll(deptId, region);
	}

	@Override
	public void updateSplbsrvc(Splbsrvc splbsrvc) {
		dao.updateSplbsrvc(splbsrvc, region);
		
	}

	@Override
	public void removeSplbsrvc(Splbsrvc splbsrvc) {
		dao.removeSplbsrvc(splbsrvc, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Splbsrvc findById(SplbsrvcPK id) throws PersistenceException {
		return dao.findById(id, region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SplbsrvcEjb ejb=new SplbsrvcEjb("region");

	}

}
