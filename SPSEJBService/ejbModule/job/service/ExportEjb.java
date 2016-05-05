package job.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import export.model.BillUpdateData;

import job.ejb.ExportBeanRemote;
import job.model.Sparemap;
import job.model.Spexphst;
import job.model.Spexpjob;
import job.model.SpexpjobPK;

public class ExportEjb implements ExportBeanRemote{
	private Context context;
	private ExportBeanRemote bean; 
	public ExportEjb() {
		super();
		this.bean=lookupDao();
		
	}

	private ExportBeanRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 ExportBeanRemote dao = (ExportBeanRemote) context.lookup("ExportBean/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	

	@Override
	public void createSpexphst(Spexphst spexphst, String webAppName) {
		bean.createSpexphst(spexphst, webAppName);
		
	}

	@Override
	public void updateSpexphst(Spexphst spexphst, String webAppName) {
		bean.updateSpexphst(spexphst, webAppName);
		
	}

	@Override
	public void removeSpexphst(Spexphst spexphst, String webAppName) {
		bean.removeSpexphst(spexphst, webAppName);
		
	}

	@Override
	public void removeAllSpexphst(String webAppName) {
		bean.removeAllSpexphst(webAppName);
		
	}

	@Override
	public List<Spexphst> getAllSpexphst(String deptId, String webAppName) {
		return bean.getAllSpexphst(deptId, webAppName);
	}

	@Override
	public Spexphst findById(String referenceNo, String webAppName) throws PersistenceException {
		return bean.findById(referenceNo, webAppName);
	}

	@Override
	public void createSpexpjob(Spexpjob spexpjob, String webAppName) {
		bean.createSpexpjob(spexpjob, webAppName);
		
	}

	@Override
	public void updateSpexpjob(Spexpjob spexpjob, String webAppName) {
		bean.updateSpexpjob(spexpjob, webAppName);
		
	}

	@Override
	public void removeSpexpjob(Spexpjob spexpjob, String webAppName) {
		bean.removeSpexpjob(spexpjob, webAppName);
		
	}

	@Override
	public void removeAllSpexpjob(String webAppName) {
		bean.removeAllSpexpjob(webAppName);
		
	}

	@Override
	public List<Spexpjob> getAllSpexpjob(String deptId, String webAppName) {
		return bean.getAllSpexpjob(deptId, webAppName);
	}

	@Override
	public Spexpjob findById(SpexpjobPK id, String webAppName) throws PersistenceException {
		return bean.findById(id, webAppName);
	}

	@Override
	public void insertExportData(Spexphst Spexphst, List<Spexpjob> list, String webAppName) {
		bean.insertExportData(Spexphst, list, webAppName);
		
	}

	@Override
	public Sparemap findBySmcAreaCode(String smcAreaCode, String webAppName) throws PersistenceException {
		return bean.findBySmcAreaCode(smcAreaCode, webAppName);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExportEjb ejb=new ExportEjb();
		SpexpjobPK id=new SpexpjobPK();
		id.setDeptId("423.50");
		id.setProjectNo("423.50/SMC/11/0659");
		System.out.println(ejb.findById(id, "R1"));
		System.out.println(ejb.getAllSpexphst("510.20", "SMCTesting"));
		System.out.println(ejb.getAllSpexpjob("510.20", "SMCTesting"));

	}

	@Override
	public void updateExportedJob(List<BillUpdateData> list, String webAppName) {
		bean.updateExportedJob(list, webAppName);
		
	}

	@Override
	public List<String> getExportedJobWithoutAccNo(String deptId,
			String webAppName) {
		return bean.getExportedJobWithoutAccNo(deptId, webAppName);
	}

	@Override
	public List<String> updateErrorData(String deptId,
			List<BillUpdateData> list, String webAppName) {
		return bean.updateErrorData(deptId, list, webAppName);
	}

	@Override
	public List<BillUpdateData> getAccNoInfo(String deptId, String webAppName) {
		return bean.getAccNoInfo(deptId, webAppName);
	}

	@Override
	public Spexpjob findByJobNo(String jobNo, String webAppName)
			throws PersistenceException {
		return bean.findByJobNo(jobNo, webAppName);
	}

	@Override
	public Spexpjob findByJobNo(String jobNo, String deptId, String webAppName)
			throws PersistenceException {
		return bean.findByJobNo(jobNo, deptId, webAppName);
	}

	
}
