package fa.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import fa.ejb.FaheaderDaoRemote;
import fa.model.Faheader;
import fa.model.FaheaderPK;

public class FaheaderEjb implements FaheaderEjbI{

	private Context context;
	private FaheaderDaoRemote dao; 
	private String region=null;
	
	public FaheaderEjb(String region) {
		super();
		this.region=region;
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
	public void createFaheader(Faheader faheader) {
		dao.createFaheader(faheader, region);
		
	}

	@Override
	public void updateFaheader(Faheader faheader) {
		dao.updateFaheader(faheader, region);
		
	}

	@Override
	public void removeFaheader(Faheader faheader) {
		dao.removeFaheader(faheader, region);
		
	}

	@Override
	public List<Faheader> getAll() {
		return dao.getAll(region);
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Faheader findById(FaheaderPK id)
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
