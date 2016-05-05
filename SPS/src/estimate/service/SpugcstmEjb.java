package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpugcstmDaoRemote;
import estimate.model.Spugcstm;
import estimate.model.SpugcstmPK;

public class SpugcstmEjb implements SpugcstmEjbI {
	private Context context;
	private SpugcstmDaoRemote dao;
	private String region=null;
	
	public SpugcstmEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}

	private SpugcstmDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpugcstmDaoRemote dao = (SpugcstmDaoRemote) context.lookup("SpugcstmDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	

	@Override
	public void createSpugcstm(Spugcstm spugcstm) {
		dao.createSpugcstm(spugcstm, region);
		
	}

	@Override
	public Spugcstm findById(SpugcstmPK id) throws PersistenceException {
		return dao.findById(id, region);
	}

	@Override
	public List<Spugcstm> getAll() {
		return dao.getAll(region);
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public void removeSpugcstm(Spugcstm spugcstm) {
		dao.removeSpugcstm(spugcstm, region);
		
	}

	@Override
	public void updateSpugcstm(Spugcstm spugcstm) {
		dao.updateSpugcstm(spugcstm, region);
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpugcstmEjb ejb=new SpugcstmEjb("region");
		System.out.println(ejb.getAll());

	}

}
