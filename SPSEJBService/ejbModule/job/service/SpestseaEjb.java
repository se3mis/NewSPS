package job.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import job.ejb.SpestseaDaoRemote;
import job.model.Spestsea;
import job.model.SpestseaPK;

public class SpestseaEjb implements SpestseaDaoRemote  {

	private Context context;
		private SpestseaDaoRemote dao; 
		public SpestseaEjb() {
			super();
			this.dao=lookupDao();
			
		}

		private SpestseaDaoRemote lookupDao() {
			try
			{
				 context = new InitialContext();
				 SpestseaDaoRemote dao = (SpestseaDaoRemote) context.lookup("SpestseaDao/remote");
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
		SpestseaEjb ejb=new SpestseaEjb();
		SpestseaPK spestseaPK= new SpestseaPK();
		spestseaPK.setDeptId("514.20");
		spestseaPK.setProjectNo("514.20/CRJ/11/0001");
		System.out.println(ejb.findById(spestseaPK, "ertert"));
		System.out.println(ejb.findByJobNo("514.20/CRJ/11/0001", "webAppNam"));

	}

	@Override
	public void createSpestsea(Spestsea spestsea, String webAppName) {
		dao.createSpestsea(spestsea, webAppName);
		
	}

	@Override
	public List<Spestsea> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}

	@Override
	public void updateSpestsea(Spestsea spestsea, String webAppName) {
		dao.updateSpestsea(spestsea, webAppName);
		
	}

	@Override
	public void removeSpestsea(Spestsea spestsea, String webAppName) {
		dao.removeSpestsea(spestsea, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Spestsea findById(SpestseaPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}

	@Override
	public List<Spestsea> getSealNoList(String jobNo, String deptId, String webAppName) throws PersistenceException {
		return dao.getSealNoList(jobNo, deptId, webAppName);
	}

	@Override
	public Spestsea findByJobNo(String jobNo, String webAppName) {
		return dao.findByJobNo(jobNo, webAppName);
	}

}
