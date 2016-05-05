package fa.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import fa.ejb.FadetailDaoRemote;
import fa.model.Fadetail;
import fa.model.FadetailPK;

public class FadetailEjb implements FadetailDaoRemote{
	private Context context;
	private FadetailDaoRemote dao; 
	
	public FadetailEjb() {
		super();
		this.dao=lookupDao();
		
	}

	private FadetailDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 FadetailDaoRemote dao = (FadetailDaoRemote) context.lookup("FadetailDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	

	@Override
	public void createFadetail(Fadetail fadetail, String webAppName) {
		dao.createFadetail(fadetail, webAppName);
		
	}

	@Override
	public void updateFadetail(Fadetail fadetail, String webAppName) {
		dao.updateFadetail(fadetail, webAppName);
		
	}

	@Override
	public void removeFadetail(Fadetail fadetail, String webAppName) {
		dao.removeFadetail(fadetail, webAppName);
		
	}

	@Override
	public List<Fadetail> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Fadetail findById(FadetailPK id, String webAppName)
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
