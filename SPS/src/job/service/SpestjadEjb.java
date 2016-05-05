package job.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import job.ejb.SpestjadDaoRemote;
import job.model.Spestjad;
import job.model.SpestjadPK;

public class SpestjadEjb implements  SpestjadEjbI {
	private Context context;
	private SpestjadDaoRemote dao;
	private String region=null;
	
	public SpestjadEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}
	
	private SpestjadDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpestjadDaoRemote dao = (SpestjadDaoRemote) context.lookup("SpestjadDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	@Override 
	public List<Spestjad> getAll(String deptId) {
		return dao.getAll(deptId, region);
	}

	@Override
	public Spestjad findById(SpestjadPK id) {
		return dao.findById(id, region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpestjadEjb ejb =new SpestjadEjb("region");
		System.out.println(ejb);

	}


	
}
