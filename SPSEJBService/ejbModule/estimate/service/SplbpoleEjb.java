package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SplbpoleDaoRemote;
import estimate.model.Splbpole;
import estimate.model.SplbpolePK;

public class SplbpoleEjb implements SplbpoleDaoRemote{
	private Context context;
	private SplbpoleDaoRemote dao; 
	public SplbpoleEjb() {
		super();
		this.dao=lookupDao();
		
	}
	
	private SplbpoleDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SplbpoleDaoRemote dao = (SplbpoleDaoRemote) context.lookup("SplbpoleDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SplbpoleEjb  ejb=new SplbpoleEjb();
		System.out.println(ejb);

	}

	@Override
	public void createSplbpole(Splbpole splbpole, String webAppName) {
		dao.createSplbpole(splbpole, webAppName);
		
	}

	@Override
	public List<Splbpole> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}

	@Override
	public void updateSplbpole(Splbpole splbpole, String webAppName) {
		dao.updateSplbpole(splbpole, webAppName);
		
	}

	@Override
	public void removeSplbpole(Splbpole splbpole, String webAppName) {
		dao.removeSplbpole(splbpole, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Splbpole findById(SplbpolePK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}

}
