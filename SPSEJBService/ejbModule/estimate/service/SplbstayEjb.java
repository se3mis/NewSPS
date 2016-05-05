package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SplbstayDaoRemote;
import estimate.model.Splbstay;
import estimate.model.SplbstayPK;

public class SplbstayEjb implements SplbstayDaoRemote {
	private Context context;
	private SplbstayDaoRemote dao; 
	public SplbstayEjb() {
		super();
		this.dao=lookupBean();
		
	}

	private SplbstayDaoRemote lookupBean() {
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
		SplbstayEjb ejb=new SplbstayEjb();
		System.out.println(ejb.getAll("514.20", "lknflsa"));

	}

	@Override
	public void createSplbstay(Splbstay splbstay, String webAppName) {
		dao.createSplbstay(splbstay, webAppName);
		
	}

	@Override
	public List<Splbstay> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}

	@Override
	public void updateSplbstay(Splbstay splbstay, String webAppName) {
		dao.updateSplbstay(splbstay, webAppName);
		
	}

	@Override
	public void removeSplbstay(Splbstay splbstay, String webAppName) {
		dao.removeSplbstay(splbstay, webAppName);
		
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
