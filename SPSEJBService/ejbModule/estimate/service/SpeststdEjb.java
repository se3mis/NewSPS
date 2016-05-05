package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpeststdDaoRemote;
import estimate.model.Speststd;
import estimate.model.SpeststdPK;

public class SpeststdEjb implements SpeststdDaoRemote {
	private Context context;
	private SpeststdDaoRemote dao; 
	public SpeststdEjb() {
		super();
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
	public void createSpeststd(Speststd speststd, String webAppName) throws PersistenceException {
		dao.createSpeststd(speststd, webAppName);
		
	}

	@Override
	public List<Speststd> getAll(String webAppName) throws PersistenceException {
		return dao.getAll(webAppName);
	}

	@Override
	public List<Speststd> getAll(String deptId, String webAppName) throws PersistenceException {
		return dao.getAll(deptId, webAppName);
	}

	@Override
	public void updateSpeststd(Speststd speststd, String webAppName) throws PersistenceException {
		dao.updateSpeststd(speststd, webAppName);
		
	}

	@Override
	public void removeSpeststd(Speststd speststd, String webAppName) throws PersistenceException {
		dao.removeSpeststd(speststd, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) throws PersistenceException {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Speststd findById(SpeststdPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpeststdEjb ejb=new SpeststdEjb();
		//System.out.println(ejb.getAll("SMCTesting"));
		//System.out.println(ejb.getAll("SMCTesting"));
		SpeststdPK id=new SpeststdPK();
		id.setDeptId("423.20");
		id.setEstimateNo("423.20/ENC/2011/1760");
		System.out.println("eee "+ejb.findById(id, "R1"));
		//System.out.println(ejb.getAll("SMCTesting"));
		SpeststdPK id2=new SpeststdPK();
		id2.setDeptId("541.00");
		id2.setEstimateNo("541.20/ENC/2011/0002");
		//System.out.println(ejb.findById(id2, "SMCTesting"));

	}

	@Override
	public Speststd findByEstimateNo(String estimateNo, String webAppName) {
		return dao.findByEstimateNo(estimateNo, webAppName);
	}

	

}
