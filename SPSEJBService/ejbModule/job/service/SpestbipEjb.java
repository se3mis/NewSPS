package job.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import job.ejb.SpestbipDaoRemote;
import job.model.Spestbip;
import job.model.SpestbipPK;

public class SpestbipEjb implements SpestbipDaoRemote {

	private Context context;
	private SpestbipDaoRemote dao; 
	public SpestbipEjb() {
		super();
		this.dao=lookupDao();
		
	}
	
	private SpestbipDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpestbipDaoRemote dao = (SpestbipDaoRemote) context.lookup("SpestbipDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}


	@Override
	public void createSpestbip(Spestbip spestbip, String webAppName) {
		dao.createSpestbip(spestbip, webAppName);
		
	}

	@Override
	public List<Spestbip> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}

	@Override
	public void updateSpestbip(Spestbip spestbip, String webAppName) {
		dao.updateSpestbip(spestbip, webAppName);
		
	}

	@Override
	public void removeSpestbip(Spestbip spestbip, String webAppName) {
		dao.removeSpestbip(spestbip, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Spestbip findById(SpestbipPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpestbipEjb ejb=new SpestbipEjb();
		System.out.print(ejb);

	}
	

}
