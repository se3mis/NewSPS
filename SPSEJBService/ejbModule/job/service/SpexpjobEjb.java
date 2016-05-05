package job.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import export.model.BillUpdateData;

import job.ejb.SpexpjobDaoRemote;
import job.model.Spexpjob;
import job.model.SpexpjobPK;

public class SpexpjobEjb implements SpexpjobDaoRemote{
	private Context context;
	private SpexpjobDaoRemote dao; 
	public SpexpjobEjb() {
		super();
		this.dao=lookupDao();
		
	}

	private SpexpjobDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpexpjobDaoRemote dao = (SpexpjobDaoRemote) context.lookup("SpexpjobDao/remote");
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
		SpexpjobEjb ejb=new SpexpjobEjb();
		System.out.println(ejb.getExportedJobWithoutAccNo("423.50", "R1").size());

	}

	@Override
	public void createSpexpjob(Spexpjob spexpjob, String webAppName) {
		dao.createSpexpjob(spexpjob, webAppName);
		
	}

	@Override
	public void updateSpexpjob(Spexpjob spexpjob, String webAppName) {
		dao.updateSpexpjob(spexpjob, webAppName);
		
	}

	@Override
	public void removeSpexpjob(Spexpjob spexpjob, String webAppName) {
		dao.removeSpexpjob(spexpjob, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public List<Spexpjob> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}

	@Override
	public Spexpjob findById(SpexpjobPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}

	@Override
	public void updateExportedJob(List<BillUpdateData> list, String webAppName) {
		dao.updateExportedJob(list, webAppName);
		
	}

	@Override
	public List<String> getExportedJobWithoutAccNo(String deptId,
			String webAppName) {
		return dao.getExportedJobWithoutAccNo(deptId, webAppName);
	}

	@Override
	public List<BillUpdateData> getAccNoInfo(String deptId, String webAppName) {
		return dao.getAccNoInfo(deptId, webAppName);
	}

	@Override
	public Spexpjob findByJobNo(String jobNo, String webAppName)
			throws PersistenceException {
		return dao.findByJobNo(jobNo, webAppName);
	}

	@Override
	public Spexpjob findByJobNo(String jobNo, String deptId, String webAppName)
			throws PersistenceException {
		return dao.findByJobNo(jobNo, deptId, webAppName);
	}

}
