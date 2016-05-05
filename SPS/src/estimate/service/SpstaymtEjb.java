package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpstaymtDaoRemote;
import estimate.model.MaterialGrid;
import estimate.model.Spstaymt;
import estimate.model.SpstaymtPK;

public class SpstaymtEjb implements SpstaymtEjbI{
	private Context context;
	private SpstaymtDaoRemote dao; 
	private String region=null;
	
	public SpstaymtEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}
	
	private SpstaymtDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpstaymtDaoRemote dao = (SpstaymtDaoRemote) context.lookup("SpstaymtDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	

	@Override
	public void createSpstaymt(Spstaymt spstaymt) {
		dao.createSpstaymt(spstaymt, region);
		
	}

	@Override
	public List<Spstaymt> getAll() {
		return dao.getAll(region);
	}

	@Override
	public void updateSpstaymt(Spstaymt spstaymt) {
		dao.updateSpstaymt(spstaymt, region);
		
	}

	@Override
	public void removeSpstaymt(Spstaymt spstaymt) {
		dao.removeSpstaymt(spstaymt, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	/*@Override
	public Spstaymt findById(String matCd) throws PersistenceException {
		return dao.findById(matCd, region);
	}*/

	@Override
	public Spstaymt findById(SpstaymtPK id) throws PersistenceException {
		return dao.findById(id, region);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpstaymtEjb ejb=new SpstaymtEjb("region");
		System.err.println(ejb);

	}
	

}
