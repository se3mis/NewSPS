package job.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import job.ejb.SpodrcrdDaoRemote;
import job.model.Spodrcrd;
import job.model.SpodrcrdPK;

public class SpodrcrdEjb implements SpodrcrdDaoRemote{
	private Context context;
	private SpodrcrdDaoRemote dao; 
	public SpodrcrdEjb() {
		super();
		this.dao=lookupDao();
		
	}

	private SpodrcrdDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpodrcrdDaoRemote dao = (SpodrcrdDaoRemote) context.lookup("SpodrcrdDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	public void createSpodrcrd(Spodrcrd spodrcrd, String webAppName) {
		dao.createSpodrcrd(spodrcrd, webAppName);
		
	}

	@Override
	public void updateSpodrcrd(Spodrcrd spodrcrd, String webAppName) {
		dao.updateSpodrcrd(spodrcrd, webAppName);
		
	}

	@Override
	public void removeSpodrcrd(Spodrcrd spodrcrd, String webAppName) {
		dao.removeSpodrcrd(spodrcrd, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public List<Spodrcrd> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}

	@Override
	public Spodrcrd findById(SpodrcrdPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpodrcrdEjb ejb=new SpodrcrdEjb();
		System.out.println(ejb);

	}

	@Override
	public Spodrcrd findByJobNo(String jobNo, String webAppName) {
		return dao.findByJobNo(jobNo, webAppName);
	}

	

}
