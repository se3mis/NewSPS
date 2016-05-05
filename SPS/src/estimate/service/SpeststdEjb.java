package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpeststdDaoRemote;
//import estimate.model.Speststd;
//import estimate.model.SpeststdPK;
import estimate.model.Speststd;
import estimate.model.SpeststdPK;


public class SpeststdEjb implements SpeststdEjbI{

	private Context context;
	private SpeststdDaoRemote dao; 
	private String region=null;
	
	public SpeststdEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}
	
	private SpeststdDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpeststdDaoRemote dao = (SpeststdDaoRemote) context.lookup("SpeststdDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	

	@Override
	public void createSpeststd(Speststd speststd) throws PersistenceException {
		dao.createSpeststd(speststd, region);
		
	}

	@Override
	public List<Speststd> getAll() throws PersistenceException {
		return dao.getAll(region);
	}

	@Override
	public List<Speststd> getAll(String deptId) throws PersistenceException {
		return dao.getAll(deptId, region);
	}

	@Override
	public void updateSpeststd(Speststd speststd) throws PersistenceException {
		dao.updateSpeststd(speststd, region);
		
	}

	@Override
	public void removeSpeststd(Speststd speststd) throws PersistenceException {
		dao.removeSpeststd(speststd, region);
		
	}

	@Override
	public void removeAll() throws PersistenceException {
		dao.removeAll(region);

		
	}

	@Override
	public Speststd findById(SpeststdPK id) throws PersistenceException {
		return dao.findById(id, region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpeststdEjb ejb=new SpeststdEjb("region");
		System.out.println(ejb.getAll());
		System.out.println(ejb.getAll("514.20"));

	}

	@Override
	public Speststd findByEstimateNo(String estimateNo) {
		return dao.findByEstimateNo(estimateNo, region);
	}
	
	

	

}
