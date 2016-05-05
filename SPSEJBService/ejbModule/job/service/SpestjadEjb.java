package job.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import job.ejb.SpestjadDaoRemote;
import job.model.Spestjad;
import job.model.SpestjadPK;


public class SpestjadEjb implements SpestjadDaoRemote{
	private Context context;
	private SpestjadDaoRemote dao; 
	public SpestjadEjb() {
		super();
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
	public List<Spestjad> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}

	@Override
	public Spestjad findById(SpestjadPK id, String webAppName) {
		return dao.findById(id, webAppName);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpestjadEjb ejb =new SpestjadEjb();
		System.out.println(ejb);

	}

}
