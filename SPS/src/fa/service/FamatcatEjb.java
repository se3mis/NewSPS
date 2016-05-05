package fa.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import fa.ejb.FamatcatDaoRemote;
import fa.model.Famatcat;
import fa.model.FamatcatPK;

public class FamatcatEjb implements FamatcatEjbI {

	private Context context;
	private FamatcatDaoRemote dao; 
	private String region=null;
	
	public FamatcatEjb(String region) {
		super();
		this.region=region;
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
	public void createFamatcat(Famatcat famatcat) {
		dao.createFamatcat(famatcat, region);
		
	}

	@Override
	public void updateFamatcat(Famatcat famatcat) {
		dao.updateFamatcat(famatcat, region);
		
	}

	@Override
	public void removeFamatcat(Famatcat famatcat) {
		dao.removeFamatcat(famatcat, region);
		
	}

	@Override
	public List<Famatcat> getAll() {
		return dao.getAll(region);
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Famatcat findById(FamatcatPK id)
			throws PersistenceException {
		return dao.findById(id, region);
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
