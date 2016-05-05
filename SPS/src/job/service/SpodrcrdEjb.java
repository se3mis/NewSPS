package job.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import job.ejb.SpodrcrdDaoRemote;
import job.model.Spodrcrd;
import job.model.SpodrcrdPK;

public class SpodrcrdEjb implements SpodrcrdEjbI{
	private Context context;
	private SpodrcrdDaoRemote dao; 
	private String region=null;
	
	public SpodrcrdEjb(String region) {
		super();
		this.region=region;
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
	public void createSpodrcrd(Spodrcrd spodrcrd) {
		dao.createSpodrcrd(spodrcrd, region);
		
	}

	@Override
	public void updateSpodrcrd(Spodrcrd spodrcrd) {
		dao.updateSpodrcrd(spodrcrd, region);
		
	}

	@Override
	public void removeSpodrcrd(Spodrcrd spodrcrd) {
		dao.removeSpodrcrd(spodrcrd, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public List<Spodrcrd> getAll(String deptId) {
		return dao.getAll(deptId, region);
	}

	@Override
	public Spodrcrd findById(SpodrcrdPK id) throws PersistenceException {
		return dao.findById(id, region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpodrcrdEjb ejb=new SpodrcrdEjb("region");
		System.out.println(ejb);

	}

	@Override
	public Spodrcrd findByJobNo(String jobNo) {
		return dao.findByJobNo(jobNo, region);
	}

	

}
