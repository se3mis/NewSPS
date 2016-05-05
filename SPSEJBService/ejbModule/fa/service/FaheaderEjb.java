package fa.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import fa.ejb.FaheaderDaoRemote;
import fa.model.Faheader;
import fa.model.FaheaderPK;

public class FaheaderEjb implements FaheaderDaoRemote{
	private Context context;
	private FaheaderDaoRemote dao; 
	public FaheaderEjb() {
		super();
		this.dao=lookupDao();
		
	}

	private FaheaderDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 FaheaderDaoRemote dao = (FaheaderDaoRemote) context.lookup("FaheaderDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	

	@Override
	public void createFaheader(Faheader faheader, String webAppName) {
		dao.createFaheader(faheader, webAppName);
		
	}

	@Override
	public void updateFaheader(Faheader faheader, String webAppName) {
		dao.updateFaheader(faheader, webAppName);
		
	}

	@Override
	public void removeFaheader(Faheader faheader, String webAppName) {
		dao.removeFaheader(faheader, webAppName);
		
	}

	@Override
	public List<Faheader> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Faheader findById(FaheaderPK id, String webAppName)
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
