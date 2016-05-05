package fa.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import fa.ejb.FaactcatDaoRemote;
import fa.model.Faactcat;

public class FaactcatEjb implements FaactcatEjbI{

	private Context context;
	private FaactcatDaoRemote dao; 
	private String region=null;
	
	public FaactcatEjb(String region) {
		super();
		this.region=region;
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
	public void createFaactcat(Faactcat faactcat) {
		dao.createFaactcat(faactcat, region);
		
	}

	@Override
	public void updateFaactcat(Faactcat faactcat) {
		dao.updateFaactcat(faactcat, region);
		
	}

	@Override
	public void removeFaactcat(Faactcat faactcat) {
		dao.removeFaactcat(faactcat, region);
		
	}

	@Override
	public List<Faactcat> getAll() {
		return dao.getAll(region);
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Faactcat findById(String faCatCode)
			throws PersistenceException {
		return dao.findById(faCatCode, region);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
