package job.service;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


import job.ejb.PctrxhmtDaoRemote;

import job.model.Pctrxhmt;
import job.model.PctrxhmtPK;


public class PctrxhmtEjb implements PctrxhmtDaoRemote {
	
	private Context context;
	private PctrxhmtDaoRemote dao; 
	public PctrxhmtEjb() {
		super();
		this.dao=lookupDao();
		
	}

	private PctrxhmtDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 PctrxhmtDaoRemote dao = (PctrxhmtDaoRemote) context.lookup("PctrxhmtDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void createPctrxhmt(Pctrxhmt pctrxhmt, String webAppName) {
		// TODO Auto-generated method stub
		dao.createPctrxhmt(pctrxhmt, webAppName);
	}

	@Override
	public void updatePctrxhmt(Pctrxhmt pctrxhmt, String webAppName) {
		// TODO Auto-generated method stub
		dao.updatePctrxhmt(pctrxhmt, webAppName);
	}

	@Override
	public void removePctrxhmt(Pctrxhmt pctrxhmt, String webAppName) {
		// TODO Auto-generated method stub
		dao.removePctrxhmt(pctrxhmt, webAppName);
	}

	@Override
	public void removeAll(String webAppName) {
		// TODO Auto-generated method stub
		dao.removeAll(webAppName);
	}

	@Override
	public Pctrxhmt findById(PctrxhmtPK id, String webAppName) {
		return dao.findById(id, webAppName);
	}

	@Override
	public Pctrxhmt findBy3PK(String deptId, String docNo, String docPf,
			String webAppName) {
		// TODO Auto-generated method stub
		return dao.findBy3PK(deptId,docNo,docPf,webAppName);
	}

	

}
