package estimate.service;



import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;


import estimate.ejb.PegschdmtDaoRemote;
import estimate.model.Pegschdmt;


public class PegschdmtEjb implements PegschdmtEjbI {
	private Context context;
	private PegschdmtDaoRemote dao;
	private String region=null;
	
	public PegschdmtEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupBean();
		
	}

	private PegschdmtDaoRemote lookupBean() {
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
	public void insert(Pegschdmt pegschdmt, String webAppName)
			throws PersistenceException {
		dao.createPegschdmt(pegschdmt,webAppName);
		
	}

	@Override
	public List<Pegschdmt> findByEstimationNo(String estNo, String deptId,
			String webAppName) {
		return dao.findByEstimationNo(estNo, deptId,region);
	}

	
}


