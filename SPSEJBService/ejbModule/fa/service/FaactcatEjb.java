package fa.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import fa.ejb.FaactcatDaoRemote;
import fa.model.Faactcat;

public class FaactcatEjb  implements FaactcatDaoRemote{
	private Context context;
	private FaactcatDaoRemote dao; 
	public FaactcatEjb() {
		super();
		this.dao=lookupDao();
		
	}

	private FaactcatDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 FaactcatDaoRemote dao = (FaactcatDaoRemote) context.lookup("FaactcatDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	

	@Override
	public void createFaactcat(Faactcat faactcat, String webAppName) {
		dao.createFaactcat(faactcat, webAppName);
		
	}

	@Override
	public void updateFaactcat(Faactcat faactcat, String webAppName) {
		dao.updateFaactcat(faactcat, webAppName);
		
	}

	@Override
	public void removeFaactcat(Faactcat faactcat, String webAppName) {
		dao.removeFaactcat(faactcat, webAppName);
		
	}

	@Override
	public List<Faactcat> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Faactcat findById(String faCatCode, String webAppName)
			throws PersistenceException {
		return dao.findById(faCatCode, webAppName);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	

}
