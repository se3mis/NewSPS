package fa.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import fa.ejb.FamatcatDaoRemote;
import fa.model.Famatcat;
import fa.model.FamatcatPK;

public class FamatcatEjb implements FamatcatDaoRemote{
	private Context context;
	private FamatcatDaoRemote dao; 
	public FamatcatEjb() {
		super();
		this.dao=lookupDao();
		
	}

	private FamatcatDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 FamatcatDaoRemote dao = (FamatcatDaoRemote) context.lookup("FamatcatDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	

	@Override
	public void createFamatcat(Famatcat famatcat, String webAppName) {
		dao.createFamatcat(famatcat, webAppName);
		
	}

	@Override
	public void updateFamatcat(Famatcat famatcat, String webAppName) {
		dao.updateFamatcat(famatcat, webAppName);
		
	}

	@Override
	public void removeFamatcat(Famatcat famatcat, String webAppName) {
		dao.removeFamatcat(famatcat, webAppName);
		
	}

	@Override
	public List<Famatcat> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Famatcat findById(FamatcatPK id, String webAppName)
			throws PersistenceException {
		return dao.findById(id, webAppName);
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
