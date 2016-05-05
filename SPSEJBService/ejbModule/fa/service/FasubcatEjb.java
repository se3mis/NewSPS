package fa.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import fa.ejb.FasubcatDaoRemote;
import fa.model.Fasubcat;

public class FasubcatEjb implements FasubcatDaoRemote{
	private Context context;
	private FasubcatDaoRemote dao; 
	public FasubcatEjb() {
		super();
		this.dao=lookupDao();
		
	}

	private FasubcatDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 FasubcatDaoRemote dao = (FasubcatDaoRemote) context.lookup("FasubcatDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	

	@Override
	public void createFasubcat(Fasubcat fasubcat, String webAppName) {
		dao.createFasubcat(fasubcat, webAppName);
		
	}

	@Override
	public void updateFasubcat(Fasubcat fasubcat, String webAppName) {
		dao.updateFasubcat(fasubcat, webAppName);
		
	}

	@Override
	public void removeFasubcat(Fasubcat fasubcat, String webAppName) {
		dao.removeFasubcat(fasubcat, webAppName);
		
	}

	@Override
	public List<Fasubcat> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Fasubcat findById(String subCatCode, String webAppName)
			throws PersistenceException {
		return dao.findById(subCatCode, webAppName);
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
