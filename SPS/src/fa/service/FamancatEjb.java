package fa.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import fa.ejb.FamancatDaoRemote;
import fa.model.Famancat;

public class FamancatEjb implements FamancatEjbI {

	private Context context;
	private FamancatDaoRemote dao; 
	private String region=null;
	
	public FamancatEjb(String region) {
		super();
		this.region=region;
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
	public void createFamancat(Famancat famancat) {
		dao.createFamancat(famancat, region);
		
	}

	@Override
	public void updateFamancat(Famancat famancat) {
		dao.updateFamancat(famancat, region);
		
	}

	@Override
	public void removeFamancat(Famancat famancat) {
		dao.removeFamancat(famancat, region);
		
	}

	@Override
	public List<Famancat> getAll() {
		return dao.getAll(region);
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Famancat findById(String catCode)
			throws PersistenceException {
		return dao.findById(catCode, region);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
