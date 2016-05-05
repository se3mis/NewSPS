package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpsetwirDaoRemote;
import estimate.model.Spsetwir;
import estimate.model.SpsetwirPK;

public class SpsetwirEjb implements  SpsetwirEjbI{
	private Context context;
	private SpsetwirDaoRemote dao; 
	private String region=null;
	
	public SpsetwirEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupBean();
		
	}

	private SpsetwirDaoRemote lookupBean() {
		try
		{
			 context = new InitialContext();
			 SpsetwirDaoRemote dao = (SpsetwirDaoRemote) context.lookup("SpsetwirDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	@Override
	public void createSpsetwir(Spsetwir Spsetwir) {
		dao.createSpsetwir(Spsetwir, region);
		
	}

	@Override
	public List<Spsetwir> getAll(String deptId) {
		return dao.getAll(deptId, region);
	}

	@Override
	public void updateSpsetwir(Spsetwir Spsetwir) {
		dao.updateSpsetwir(Spsetwir, region);
		
	}

	@Override
	public void removeSpsetwir(Spsetwir Spsetwir) {
		dao.removeSpsetwir(Spsetwir, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Spsetwir findById(SpsetwirPK id) throws PersistenceException {
		return dao.findById(id, region);
	}

	@Override
	public List<Spsetwir> getWireList(String applicationNo, String deptId) {
		return dao.getWireList(applicationNo, deptId, region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpsetwirEjb ejb=new SpsetwirEjb("region");
		System.err.println(ejb);

	}

}
