package job.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import job.ejb.SpexphstDaoRemote;
import job.model.Spexphst;

public class SpexphstEjb implements SpexphstDaoRemote{
	private Context context;
	private SpexphstDaoRemote dao; 
	public SpexphstEjb() {
		super();
		this.dao=lookupDao();
		
	}

	private SpexphstDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpexphstDaoRemote dao = (SpexphstDaoRemote) context.lookup("SpexphstDao/remote");
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
		SpexphstEjb ejb= new SpexphstEjb();
		System.out.println(ejb.getNextAppointmentId("510.20", "SMCTesting"));

	}

	@Override
	public void createSpexphst(Spexphst spexphst, String webAppName) {
		dao.createSpexphst(spexphst, webAppName);
		
	}
	
	

	@Override
	public void updateSpexphst(Spexphst spexphst, String webAppName) {
		dao.updateSpexphst(spexphst, webAppName);
		
	}

	@Override
	public void removeSpexphst(Spexphst spexphst, String webAppName) {
		dao.removeSpexphst(spexphst, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public List<Spexphst> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}

	@Override
	public Spexphst findById(String referenceNo, String webAppName) throws PersistenceException {
		return dao.findById(referenceNo, webAppName);
	}

	@Override
	public String getNextAppointmentId(String deptId, String webAppName) {
		return dao.getNextAppointmentId(deptId, webAppName);
	}

	@Override
	public String createSpexphstAutoId(Spexphst spexphst, String webAppName) {
		return dao.createSpexphstAutoId(spexphst, webAppName);
		
	}

}
