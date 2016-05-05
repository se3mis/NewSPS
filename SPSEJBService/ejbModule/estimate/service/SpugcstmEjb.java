package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpugcstmDaoRemote;
import estimate.model.Spugcstm;
import estimate.model.SpugcstmPK;

public class SpugcstmEjb implements SpugcstmDaoRemote{
	private Context context;
	private SpugcstmDaoRemote dao; 
	public SpugcstmEjb() {
		super();
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
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpugcstmEjb  ejb=new SpugcstmEjb();
		System.out.println(ejb);
		System.out.println(ejb.getAll("sdkbd"));

	}

	@Override
	public void createSpugcstm(Spugcstm spugcstm, String webAppName) {
		dao.createSpugcstm(spugcstm, webAppName);
		
	}

	@Override
	public List<Spugcstm> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public void updateSpugcstm(Spugcstm spugcstm, String webAppName) {
		dao.updateSpugcstm(spugcstm, webAppName);
		
	}

	@Override
	public void removeSpugcstm(Spugcstm spugcstm, String webAppName) {
		dao.updateSpugcstm(spugcstm, webAppName);
		
		
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Spugcstm findById(SpugcstmPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}

	@Override
	public List<Spugcstm> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}

}
