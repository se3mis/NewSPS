package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpsetpolDaoRemote;
import estimate.model.Spsetpol;
import estimate.model.SpsetpolPK;

public class SpsetpolEjb implements  SpsetpolDaoRemote  {
	private Context context;
	private SpsetpolDaoRemote dao; 
	public SpsetpolEjb() {
		super();
		this.dao=lookupDao();
		
	}
	
	private SpsetpolDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpsetpolDaoRemote dao = (SpsetpolDaoRemote) context.lookup("SpsetpolDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)	{
		SpsetpolEjb ejb=new SpsetpolEjb();
		System.err.println(ejb.getAll("gfg", "SMCTesting"));
		System.err.println(ejb.getPoleList("sgg", "dfgbf", "SMCTesting"));

	}

	@Override
	public void createSpsetpol(Spsetpol spestsmt, String webAppName) {
		dao.createSpsetpol(spestsmt, webAppName);
		
	}

	@Override
	public List<Spsetpol> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}

	@Override
	public void updateSpsetpol(Spsetpol spestsmt, String webAppName) {
		dao.updateSpsetpol(spestsmt, webAppName);
		
	}

	@Override
	public void removeSpsetpol(Spsetpol spestsmt, String webAppName) {
		dao.removeSpsetpol(spestsmt, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Spsetpol findById(SpsetpolPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}

	@Override
	public List<Spsetpol> getPoleList(String applicationNo, String deptId, String webAppName) throws PersistenceException {
		return dao.getPoleList(applicationNo, deptId, webAppName);
	}

	@Override
	public void createSpsetpolList(List<Spsetpol> list, String webAppName) {
		dao.createSpsetpolList(list, webAppName);
		
	}

	@Override
	public List<Spsetpol> getPoleList(String applicationNo, String webAppName)
			throws PersistenceException {
		return dao.getPoleList(applicationNo, webAppName);
	}

}
