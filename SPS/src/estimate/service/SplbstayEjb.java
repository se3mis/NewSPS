package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SplbstayDaoRemote;
import estimate.model.Splbstay;
import estimate.model.SplbstayPK;

public class SplbstayEjb implements SplbstayEjbI{
	private Context context;
	private SplbstayDaoRemote dao;
	private String region=null;
	public SplbstayEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}
	
	private SplbstayDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SplbstayDaoRemote dao = (SplbstayDaoRemote) context.lookup("SplbstayDao/remote");
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
	public void createSplbstay(Splbstay splbstay, String webAppName) {
		dao.createSplbstay(splbstay, region);
		
	}

	@Override
	public List<Splbstay> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, region);
	}

	@Override
	public void updateSplbstay(Splbstay splbstay, String webAppName) {
		dao.updateSplbstay(splbstay, region);
		
	}

	@Override
	public void removeSplbstay(Splbstay splbstay, String webAppName) {
		dao.removeSplbstay(splbstay, region);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Splbstay findById(SplbstayPK id, String webAppName)
			throws PersistenceException {
		return dao.findById(id, webAppName);
	}

}
