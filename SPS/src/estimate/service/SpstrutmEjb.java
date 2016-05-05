package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpstrutmDaoRemote;
import estimate.model.MaterialGrid;
import estimate.model.Spstrutm;
import estimate.model.SpstrutmPK;

public class SpstrutmEjb implements SpstrutmEjbI{
	private Context context;
	private SpstrutmDaoRemote dao;
	private String region=null;
	
	public SpstrutmEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}
	
	private SpstrutmDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpstrutmDaoRemote dao = (SpstrutmDaoRemote) context.lookup("SpstrutmDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	

	@Override
	public void createSpstrutm(Spstrutm spstrutm) {
		dao.createSpstrutm(spstrutm, region);
		
	}

	@Override
	public List<Spstrutm> getAll() {
		return dao.getAll(region);
	}

	@Override
	public void updateSpstrutm(Spstrutm spstrutm) {
		dao.updateSpstrutm(spstrutm, region);
		
	}

	@Override
	public void removeSpstrutm(Spstrutm spstrutm) {
		dao.removeSpstrutm(spstrutm, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	/*@Override
	public Spstrutm findById(String matCd) throws PersistenceException {
		return dao.findById(matCd, region);
	}*/

	@Override
	public Spstrutm findById(SpstrutmPK id) throws PersistenceException {
		return dao.findById(id, region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpstrutmEjb ejb=new SpstrutmEjb("region");
		System.err.println(ejb);

	}

	

}
