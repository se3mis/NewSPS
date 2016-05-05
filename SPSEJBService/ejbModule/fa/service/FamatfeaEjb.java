package fa.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import fa.ejb.FamatfeaDaoRemote;
import fa.model.Famatfea;
import fa.model.FamatfeaPK;


public class FamatfeaEjb implements FamatfeaDaoRemote{
	private Context context;
	private FamatfeaDaoRemote dao; 
	public FamatfeaEjb() {
		super();
		this.dao=lookupDao();
		
	}

	private FamatfeaDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 FamatfeaDaoRemote dao = (FamatfeaDaoRemote) context.lookup("FamatfeaDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	

	@Override
	public void createFamatfea(Famatfea famatfea, String webAppName) {
		dao.createFamatfea(famatfea, webAppName);
		
	}

	@Override
	public void updateFamatfea(Famatfea famatfea, String webAppName) {
		dao.updateFamatfea(famatfea, webAppName);
		
	}

	@Override
	public void removeFamatfea(Famatfea famatfea, String webAppName) {
		dao.removeFamatfea(famatfea, webAppName);
		
	}

	@Override
	public List<Famatfea> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Famatfea findById(FamatfeaPK id, String webAppName)
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
