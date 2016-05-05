package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;


import estimate.ejb.SpsetwirDaoRemote;
import estimate.model.Spsetwir;
import estimate.model.SpsetwirPK;

public class SpsetwirEjb implements SpsetwirDaoRemote{
	private Context context;
	private SpsetwirDaoRemote dao; 
	public SpsetwirEjb() {
		super();
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
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpsetwirEjb ejb=new SpsetwirEjb();
		System.err.println(ejb);

	}

	@Override
	public void createSpsetwir(Spsetwir spsetwir, String webAppName) {
		dao.createSpsetwir(spsetwir, webAppName);
		
	}

	@Override
	public List<Spsetwir> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}

	@Override
	public void updateSpsetwir(Spsetwir spsetwir, String webAppName) {
		dao.updateSpsetwir(spsetwir, webAppName);
		
	}

	@Override
	public void removeSpsetwir(Spsetwir spsetwir, String webAppName) {
		dao.removeSpsetwir(spsetwir, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Spsetwir findById(SpsetwirPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}

	@Override
	public List<Spsetwir> getWireList(String applicationNo, String deptId, String webAppName) throws PersistenceException {
		return dao.getWireList(applicationNo, deptId, webAppName);
	}

	@Override
	public List<Spsetwir> getWireList(String applicationNo, String webAppName)
			throws PersistenceException {
		return dao.getWireList(applicationNo, webAppName);
	}

}
