package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SplbstrtDaoRemote;
import estimate.model.Splbstrt;
import estimate.model.SplbstrtPK;

public class SplbstrtEjb implements SplbstrtDaoRemote  {
	private Context context;
	private SplbstrtDaoRemote dao; 
	public SplbstrtEjb() {
		super();
		this.dao=lookupBean();
		
	}

	private SplbstrtDaoRemote lookupBean() {
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
		SplbstrtEjb ejb=new SplbstrtEjb();
		System.out.println(ejb.getAll("514.20", "ljfh"));

	}

	@Override
	public void createSplbstrt(Splbstrt splbstrt, String webAppName) {
		dao.createSplbstrt(splbstrt, webAppName);
		
	}

	@Override
	public List<Splbstrt> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}

	@Override
	public void updateSplbstrt(Splbstrt splbstrt, String webAppName) {
		dao.updateSplbstrt(splbstrt, webAppName);
		
	}

	@Override
	public void removeSplbstrt(Splbstrt splbstrt, String webAppName) {
		dao.removeSplbstrt(splbstrt, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Splbstrt findById(SplbstrtPK id, String webAppName)
			throws PersistenceException {
		return dao.findById(id, webAppName);
	}

}
