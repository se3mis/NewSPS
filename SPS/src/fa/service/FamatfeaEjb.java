package fa.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import fa.ejb.FamatfeaDaoRemote;
import fa.model.Famatfea;
import fa.model.FamatfeaPK;

public class FamatfeaEjb implements FamatfeaEjbI{

	private Context context;
	private FamatfeaDaoRemote dao; 
	private String region=null;
	
	public FamatfeaEjb(String region) {
		super();
		this.region=region;
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
	public void createFamatfea(Famatfea famatfea) {
		dao.createFamatfea(famatfea, region);
		
	}

	@Override
	public void updateFamatfea(Famatfea famatfea) {
		dao.updateFamatfea(famatfea, region);
		
	}

	@Override
	public void removeFamatfea(Famatfea famatfea) {
		dao.removeFamatfea(famatfea, region);
		
	}

	@Override
	public List<Famatfea> getAll() {
		return dao.getAll(region);
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Famatfea findById(FamatfeaPK id)
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
