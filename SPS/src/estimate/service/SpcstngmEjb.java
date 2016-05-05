package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpcstngmDaoRemote;
//import estimate.model.Spcstngm;
//import estimate.model.SpcstngmPK;
import estimate.model.Spcstngm;
import estimate.model.SpcstngmPK;

public class SpcstngmEjb implements SpcstngmEjbI{
	private Context context;
	private SpcstngmDaoRemote dao; 
	private String region=null;
	
	public SpcstngmEjb(String region) {
		super();
		this.region=region;
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
	public void createSpcstngm(Spcstngm spcstngm) {
		dao.createSpcstngm(spcstngm, region);
		
	}

	@Override
	public List<Spcstngm> getAll() {
		return dao.getAll(region);
	}
	
	@Override
	public List<Spcstngm> getAll(String deptId) {
		return null;
	}

	@Override
	public void updateSpcstngm(Spcstngm spcstngm) {
		dao.updateSpcstngm(spcstngm, region);
		
	}

	@Override
	public void removeSpcstngm(Spcstngm spcstngm) {
		dao.removeSpcstngm(spcstngm, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Spcstngm findById(SpcstngmPK id) throws PersistenceException {
		return dao.findById(id, region);
	}
	
	

	@Override
	public Spcstngm getSpcstngm(Long phase, Long connectionType,String  tariffCatCode,Long  length, String deptId) throws PersistenceException {
		return dao.getSpcstngm(phase, connectionType, tariffCatCode, length, deptId, region);
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpcstngmEjb ejb=new SpcstngmEjb("region");
		System.out.println(ejb.getAll());
		System.out.println(ejb.getSpcstngm(new Long("1"),new Long("30"), "DP", new Long("50"), "510.20"));


	}
	

}
