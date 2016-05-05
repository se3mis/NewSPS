package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SplabormDaoRemote;
import estimate.model.Splaborm;

public class SplabormEjb implements SplabormEjbI {
	private Context context;
	private SplabormDaoRemote dao;
	private String region=null;
	
	public SplabormEjb(String region) {
		super();
		this.region=region;
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
	public void createSplaborm(Splaborm splaborm) {
		dao.createSplaborm(splaborm, region);
		
	}

	@Override
	public List<Splaborm> getAll() {
		return dao.getAll(region);
	}

	@Override
	public void updateSplaborm(Splaborm splaborm) {
		dao.updateSplaborm(splaborm, region);
		
	}

	@Override
	public void removeSplaborm(Splaborm splaborm) {
		dao.removeSplaborm(splaborm, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Splaborm findById(String labourcode) throws PersistenceException {
		return dao.findById(labourcode, region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SplabormEjb ejb=new SplabormEjb("region");
		System.out.println(ejb);

	}

}
