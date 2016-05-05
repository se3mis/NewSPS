package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SppolemtDaoRemote;
import estimate.model.Sppolemt;
import estimate.model.SppolemtPK;

public class SppolemtEjb implements SppolemtEjbI {
	private Context context;
	private SppolemtDaoRemote dao; 
	private String region=null;
	
	public SppolemtEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}
	
	private SppolemtDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SppolemtDaoRemote dao = (SppolemtDaoRemote) context.lookup("SppolemtDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	

	@Override
	public void createSppolemt(Sppolemt sppolemt) {
		dao.createSppolemt(sppolemt, region);
		
	}

	@Override
	public List<Sppolemt> getAll() {
		return dao.getAll(region);
	}

	@Override
	public void updateSppolemt(Sppolemt sppolemt) {
		dao.updateSppolemt(sppolemt, region);
		
	}

	@Override
	public void removeSppolemt(Sppolemt sppolemt) {
		dao.removeSppolemt(sppolemt, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Sppolemt findById(SppolemtPK id) throws PersistenceException {
		return dao.findById(id, region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SppolemtEjb ejb=new SppolemtEjb("region");
		System.err.println(ejb);

	}

}
