package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpstrutmDaoRemote;
//import estimate.model.MaterialGrid;
import estimate.model.Spstrutm;
import estimate.model.SpstrutmPK;

public class SpstrutmEjb implements SpstrutmDaoRemote {
	private Context context;
	private SpstrutmDaoRemote dao; 
	public SpstrutmEjb() {
		super();
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
	public void createSpstrutm(Spstrutm spstrutm, String webAppName) {
		dao.createSpstrutm(spstrutm, webAppName);
		
	}

	@Override
	public List<Spstrutm> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public void updateSpstrutm(Spstrutm spstrutm, String webAppName) {
		dao.updateSpstrutm(spstrutm, webAppName);
		
	}

	@Override
	public void removeSpstrutm(Spstrutm spstrutm, String webAppName) {
		dao.removeSpstrutm(spstrutm, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	/*@Override
	public Spstrutm findById(String matCd, String webAppName) throws PersistenceException {
		return dao.findById(matCd, webAppName);
	}*/

	@Override
	public Spstrutm findById(SpstrutmPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpstrutmEjb ejb=new SpstrutmEjb();
		System.out.println(ejb);

	}

	@Override
	public List<Spstrutm> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}

	

}
