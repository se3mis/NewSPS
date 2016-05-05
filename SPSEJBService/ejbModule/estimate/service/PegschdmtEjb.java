package estimate.service;



import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;


import estimate.ejb.PegschdmtDaoRemote;

import estimate.model.Pegschdmt;

public class PegschdmtEjb implements PegschdmtDaoRemote{

	private Context context;
	private PegschdmtDaoRemote dao; 
	public PegschdmtEjb() {
		super();
		this.dao=lookupDao();
		
	}

	private PegschdmtDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 PegschdmtDaoRemote dao = (PegschdmtDaoRemote) context.lookup("PegschdmtDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void createPegschdmt(Pegschdmt pegschdmt, String webAppName) throws PersistenceException{
		dao.createPegschdmt(pegschdmt,webAppName);
		
	}

	@Override
	public List<Pegschdmt> findByEstimationNo(String estNo, String deptId,
			String webAppName) throws PersistenceException {
		return dao.findByEstimationNo(estNo,deptId,webAppName);
	}

	@Override
	public void updatePegschdmt(Pegschdmt pegschdmt, String webAppName)
			throws PersistenceException {
		dao.updatePegschdmt(pegschdmt,webAppName);
		
	}

	@Override
	public void deletePegschdmt(Pegschdmt pegschdmt, String webAppName) {
		dao.deletePegschdmt(pegschdmt,webAppName);
		
	}

	

	

}
