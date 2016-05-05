package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SplabormDaoRemote;
import estimate.model.Splaborm;

public class SplabormEjb implements SplabormDaoRemote {
	private Context context;
	private SplabormDaoRemote dao; 
	public SplabormEjb() {
		super();
		this.dao=lookupDao();
		
	}
	
	private SplabormDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SplabormDaoRemote dao = (SplabormDaoRemote) context.lookup("SplabormDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void createSplaborm(Splaborm splaborm, String webAppName) {
		dao.createSplaborm(splaborm, webAppName);
		
	}

	@Override
	public List<Splaborm> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public void updateSplaborm(Splaborm splaborm, String webAppName) {
		dao.updateSplaborm(splaborm, webAppName);
		
	}

	@Override
	public void removeSplaborm(Splaborm splaborm, String webAppName) {
		dao.removeSplaborm(splaborm, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Splaborm findById(String labourcode, String webAppName) throws PersistenceException {
		return dao.findById(labourcode, webAppName);
	}
	
	public static void main(String[] args) {
		SplabormEjb ejb=new SplabormEjb();
		System.out.println(ejb);
		System.out.println(ejb.getAll("SMCTesting"));
		System.out.println(ejb.findById("gdfgdf", "SMCTesting"));
		

	}

}
