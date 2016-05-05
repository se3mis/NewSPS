package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpcstngmDaoRemote ;
import estimate.model.Spcstngm;
import estimate.model.SpcstngmPK;

public class SpcstngmEjb implements SpcstngmDaoRemote {
	private Context context;
	private SpcstngmDaoRemote dao; 
	public SpcstngmEjb() {
		super();
		this.dao=lookupDao();
		
	}
	
	private SpcstngmDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpcstngmDaoRemote dao = (SpcstngmDaoRemote) context.lookup("SpcstngmDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	public void createSpcstngm(Spcstngm spcstngm, String webAppName) {
		dao.createSpcstngm(spcstngm, webAppName);
		
	}

	@Override
	public List<Spcstngm> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}
	
	@Override
	public List<Spcstngm> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}

	@Override
	public void updateSpcstngm(Spcstngm spcstngm, String webAppName) {
		dao.updateSpcstngm(spcstngm, webAppName);
		
	}

	@Override
	public void removeSpcstngm(Spcstngm spcstngm, String webAppName) {
		dao.removeSpcstngm(spcstngm, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Spcstngm findById(SpcstngmPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}
	
	@Override
	public Spcstngm getSpcstngm(Long phase, Long connectionType,String  tariffCatCode,Long  length, String deptId, String webAppName) {
		return dao.getSpcstngm(phase, connectionType, tariffCatCode, length, deptId, webAppName);
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpcstngmEjb ejb=new SpcstngmEjb();
		System.out.println(ejb.getAll("SMCTesting"));
		System.out.println(ejb.getSpcstngm(new Long("1"),new Long("30"), "DP", new Long("50"), "423.10", "R1" ));

	}

	

	



}
