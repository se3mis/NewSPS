package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpsetstuDaoRemote;
import estimate.model.Spsetstu;
import estimate.model.SpsetstuPK;

public class SpsetstuEjb implements SpsetstuDaoRemote{
	private Context context;
	private SpsetstuDaoRemote dao; 
	public SpsetstuEjb() {
		super();
		this.dao=lookupDao();
		
	}
	
	private SpsetstuDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpsetstuDaoRemote dao = (SpsetstuDaoRemote) context.lookup("SpsetstuDao/remote");
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
		SpsetstuEjb ejb=new SpsetstuEjb();
		System.err.println(ejb.getAll("510.20", "SMCTesting"));
		System.err.println(ejb.getStrutsList("sacsd", "sdds", "SMCTesting"));

	}

	@Override
	public void createSpsetstu(Spsetstu Spsetstu, String webAppName) {
		dao.createSpsetstu(Spsetstu, webAppName);
		
	}

	@Override
	public List<Spsetstu> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}

	@Override
	public void updateSpsetstu(Spsetstu Spsetstu, String webAppName) {
		dao.updateSpsetstu(Spsetstu, webAppName);
		
	}

	@Override
	public void removeSpsetstu(Spsetstu Spsetstu, String webAppName) {
		dao.removeSpsetstu(Spsetstu, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Spsetstu findById(SpsetstuPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}

	@Override
	public List<Spsetstu> getStrutsList(String applicationNo, String deptId, String webAppName) {
		return dao.getStrutsList(applicationNo, deptId, webAppName);
	}

	@Override
	public List<Spsetstu> getStrutsList(String applicationNo, String webAppName) {
		return dao.getStrutsList(applicationNo, webAppName);
	}

}
