package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SplbcndtDaoRemote;
import estimate.model.Splbcndt;
import estimate.model.SplbcndtPK;

public class SplbcndtEjb implements SplbcndtDaoRemote{
	private Context context;
	private SplbcndtDaoRemote dao; 
	public SplbcndtEjb() {
		super();
		this.dao=lookupDao();
		
	}
	
	private SplbcndtDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SplbcndtDaoRemote dao = (SplbcndtDaoRemote) context.lookup("SplbcndtDao/remote");
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
		SplbcndtEjb  ejb=new SplbcndtEjb();
		System.out.println(ejb);

	}

	@Override
	public void createSplbcndt(Splbcndt splbcndt, String webAppName) {
		dao.createSplbcndt(splbcndt, webAppName);
		
	}

	@Override
	public List<Splbcndt> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}

	@Override
	public void updateSplbcndt(Splbcndt splbcndt, String webAppName) {
		dao.updateSplbcndt(splbcndt, webAppName);
		
	}

	@Override
	public void removeSplbcndt(Splbcndt splbcndt, String webAppName) {
		dao.removeSplbcndt(splbcndt, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Splbcndt findById(SplbcndtPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}

}
