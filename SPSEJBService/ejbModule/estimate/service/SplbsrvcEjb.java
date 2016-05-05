package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SplbsrvcDaoRemote;
import estimate.model.Splbsrvc;
import estimate.model.SplbsrvcPK;

public class SplbsrvcEjb implements SplbsrvcDaoRemote{
	private Context context;
	private SplbsrvcDaoRemote dao; 
	public SplbsrvcEjb() {
		super();
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
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SplbsrvcEjb  ejb=new SplbsrvcEjb();
		System.out.println(ejb);

	}

	@Override
	public void createSplbsrvc(Splbsrvc splbsrvc, String webAppName) {
		dao.createSplbsrvc(splbsrvc, webAppName);
		
	}

	@Override
	public List<Splbsrvc> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}

	@Override
	public void updateSplbsrvc(Splbsrvc splbsrvc, String webAppName) {
		dao.updateSplbsrvc(splbsrvc, webAppName);
		
	}

	@Override
	public void removeSplbsrvc(Splbsrvc splbsrvc, String webAppName) {
		dao.removeSplbsrvc(splbsrvc, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Splbsrvc findById(SplbsrvcPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}

}
