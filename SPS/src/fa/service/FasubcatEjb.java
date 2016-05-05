package fa.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import fa.ejb.FasubcatDaoRemote;
import fa.model.Fasubcat;

public class FasubcatEjb implements FasubcatEjbI{

	private Context context;
	private FasubcatDaoRemote dao; 
	private String region=null;
	
	public FasubcatEjb(String region) {
		super();
		this.region=region;
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
	public void createFasubcat(Fasubcat fasubcat) {
		dao.createFasubcat(fasubcat, region);
		
	}

	@Override
	public void updateFasubcat(Fasubcat fasubcat) {
		dao.updateFasubcat(fasubcat, region);
		
	}

	@Override
	public void removeFasubcat(Fasubcat fasubcat) {
		dao.removeFasubcat(fasubcat, region);
		
	}

	@Override
	public List<Fasubcat> getAll() {
		return dao.getAll(region);
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Fasubcat findById(String subCatCode)
			throws PersistenceException {
		return dao.findById(subCatCode, region);
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
