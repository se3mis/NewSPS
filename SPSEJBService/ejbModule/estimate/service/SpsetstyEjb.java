package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpsetstyDaoRemote;
import estimate.model.Spsetsty;
import estimate.model.SpsetstyPK;

public class SpsetstyEjb implements SpsetstyDaoRemote{
	private Context context;
	private SpsetstyDaoRemote dao; 
	public SpsetstyEjb() {
		super();
		this.dao=lookupDao();
		
	}
	
	private SpsetstyDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpsetstyDaoRemote dao = (SpsetstyDaoRemote) context.lookup("SpsetstyDao/remote");
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
		SpsetstyEjb ejb=new SpsetstyEjb();
		System.err.println(ejb.getAll("510.20", "SMCTesting"));
		System.err.println(ejb.getStayList("510.20/SNL/2010/0003", "510.20", "SMCTesting"));
		SpsetstyPK id=new SpsetstyPK();
		id.setApplicationNo("510.20/SNL/2010/0003");
		id.setDeptId("510.20");
		id.setMatCd("A0211");
		Spsetsty spsetsty=new Spsetsty();
		spsetsty.setId(id);
		
		spsetsty=ejb.findById(id, "SMCTesting");
		System.err.println(spsetsty);
		ejb.removeSpsetsty(spsetsty, "SMCTesting");
		

	}

	@Override
	public void createSpsetsty(Spsetsty Spsetsty, String webAppName) {
		dao.createSpsetsty(Spsetsty, webAppName);
		
	}

	@Override
	public List<Spsetsty> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}

	@Override
	public void updateSpsetsty(Spsetsty Spsetsty, String webAppName) {
		dao.updateSpsetsty(Spsetsty, webAppName);
		
	}

	@Override
	public void removeSpsetsty(Spsetsty Spsetsty, String webAppName) {
		dao.removeSpsetsty(Spsetsty, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Spsetsty findById(SpsetstyPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}

	@Override
	public List<Spsetsty> getStayList(String applicationNo, String deptId, String webAppName) {
		return dao.getStayList(applicationNo, deptId, webAppName);
	}

	@Override
	public List<Spsetsty> getStayList(String applicationNo, String webAppName) {
		return dao.getStayList(applicationNo, webAppName);
	}

}
