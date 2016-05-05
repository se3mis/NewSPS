package fa.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import fa.ejb.FadetailDaoRemote;
import fa.model.Fadetail;
import fa.model.FadetailPK;

public class FadetailEjb implements FadetailEjbI{

	private Context context;
	private FadetailDaoRemote dao; 
	private String region=null;
	
	public FadetailEjb(String region) {
		super();
		this.region=region;
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
	public void createFadetail(Fadetail fadetail) {
		dao.createFadetail(fadetail, region);
		
	}

	@Override
	public void updateFadetail(Fadetail fadetail) {
		dao.updateFadetail(fadetail, region);
		
	}

	@Override
	public void removeFadetail(Fadetail fadetail) {
		dao.removeFadetail(fadetail, region);
		
	}

	@Override
	public List<Fadetail> getAll() {
		return dao.getAll(region);
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Fadetail findById(FadetailPK id)
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
