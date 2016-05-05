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
import estimate.model.SpstrutmPK;

public class SpstaymtEjb implements SpstaymtDaoRemote{
	private Context context;
	private SpstaymtDaoRemote dao; 
	public SpstaymtEjb() {
		super();
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
	public void createSpstaymt(Spstaymt spstaymt, String webAppName) {
		dao.createSpstaymt(spstaymt, webAppName);
		
	}

	@Override
	public List<Spstaymt> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public void updateSpstaymt(Spstaymt spstaymt, String webAppName) {
		dao.updateSpstaymt(spstaymt, webAppName);
		
	}

	@Override
	public void removeSpstaymt(Spstaymt spstaymt, String webAppName) {
		dao.removeSpstaymt(spstaymt, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	/*@Override
	public Spstaymt findById(SpstrutmPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}*/

	@Override
	public Spstaymt findById(SpstaymtPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpstaymtEjb ejb=new SpstaymtEjb();
		System.err.println(ejb);

	}

	@Override
	public List<Spstaymt> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}

	

}
