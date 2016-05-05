package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;


import estimate.ejb.SplbstrtDaoRemote;
import estimate.model.Splbstrt;
import estimate.model.SplbstrtPK;

public class SplbstrtEjb implements  SplbstrtEjbI  {
	private Context context;
	private SplbstrtDaoRemote dao;
	private String region=null;
	public SplbstrtEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}
	
	private SplbstrtDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SplbstrtDaoRemote dao = (SplbstrtDaoRemote) context.lookup("SplbstrtDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createSplbpole(Splbstrt Splbstrt, String webAppName) {
		dao.createSplbstrt(Splbstrt, region);
		
	}

	@Override
	public List<Splbstrt> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, region);
	}

	@Override
	public void updateSplbpole(Splbstrt Splbstrt, String webAppName) {
		dao.updateSplbstrt(Splbstrt, region);
		
	}

	@Override
	public void removeSplbpole(Splbstrt Splbstrt, String webAppName) {
		dao.removeSplbstrt(Splbstrt, region);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(region);
		
	}

	@Override
	public Splbstrt findById(SplbstrtPK id, String webAppName)
			throws PersistenceException {
		return dao.findById(id, region);
	}

}
