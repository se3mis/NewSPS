package job.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.model.SpestedyCons;

import job.ejb.SpestbipDaoRemote;
import job.model.Spestbip;
import job.model.SpestbipPK;

public class SpestbipEjb  implements SpestbipEjbI{

	private Context context;
	private SpestbipDaoRemote dao;
	private String region=null;
	
	public SpestbipEjb(String region) {
		super();
		this.region=region;
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
	public void createSpestbip(Spestbip spestbip) {
		dao.createSpestbip(spestbip, region);
		
	}

	@Override
	public List<Spestbip> getAll(String deptId) {
		return dao.getAll(deptId, region);
	}

	@Override
	public void updateSpestbip(Spestbip spestbip) {
		dao.updateSpestbip(spestbip, region);
		
	}

	@Override
	public void removeSpestbip(Spestbip spestbip) {
		dao.removeSpestbip(spestbip, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Spestbip findById(SpestbipPK id) throws PersistenceException {
		return dao.findById(id, region);
	}

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpestbipEjb ejb=new SpestbipEjb("region");
		System.out.print(ejb);

	}
	
}
