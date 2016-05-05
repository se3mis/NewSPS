package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SppolemtDaoRemote;
import estimate.model.Sppolemt;
import estimate.model.SppolemtPK;

public class SppolemtEjb implements SppolemtDaoRemote  {
	private Context context;
	private SppolemtDaoRemote dao; 
	public SppolemtEjb() {
		super();
		this.dao=lookupDao();
		
	}
	
	private SppolemtDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SppolemtDaoRemote dao = (SppolemtDaoRemote) context.lookup("SppolemtDao/remote");
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
		SppolemtEjb ejb=new SppolemtEjb();
		System.err.println(ejb);

	}

	@Override
	public void createSppolemt(Sppolemt sppolemt, String webAppName) {
		dao.createSppolemt(sppolemt, webAppName);
		
	}

	@Override
	public List<Sppolemt> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public void updateSppolemt(Sppolemt sppolemt, String webAppName) {
		dao.updateSppolemt(sppolemt, webAppName);
		
	}

	@Override
	public void removeSppolemt(Sppolemt sppolemt, String webAppName) {
		dao.removeSppolemt(sppolemt, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Sppolemt findById(SppolemtPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}

	@Override
	public List<Sppolemt> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}

}
