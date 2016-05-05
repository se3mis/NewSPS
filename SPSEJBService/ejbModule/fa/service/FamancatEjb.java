package fa.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import fa.ejb.FamancatDaoRemote;
import fa.model.Famancat;

public class FamancatEjb implements FamancatDaoRemote{
	private Context context;
	private FamancatDaoRemote dao; 
	public FamancatEjb() {
		super();
		this.dao=lookupDao();
		
	}

	private FamancatDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 FamancatDaoRemote dao = (FamancatDaoRemote) context.lookup("FamancatDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	

	@Override
	public void createFamancat(Famancat famancat, String webAppName) {
		dao.createFamancat(famancat, webAppName);
		
	}

	@Override
	public void updateFamancat(Famancat famancat, String webAppName) {
		dao.updateFamancat(famancat, webAppName);
		
	}

	@Override
	public void removeFamancat(Famancat famancat, String webAppName) {
		dao.removeFamancat(famancat, webAppName);
		
	}

	@Override
	public List<Famancat> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Famancat findById(String catCode, String webAppName)
			throws PersistenceException {
		return dao.findById(catCode, webAppName);
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
