package job.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import job.ejb.SpestcntDaoRemote;
import job.model.Spestcnt;
import job.model.SpestcntPK;

public class SpestcntEjb implements SpestcntDaoRemote {
	private Context context;
	private SpestcntDaoRemote dao; 
	public SpestcntEjb() {
		super();
		this.dao=lookupDao();
		
	}
	
	private SpestcntDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpestcntDaoRemote dao = (SpestcntDaoRemote) context.lookup("SpestcntDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	
	
	@Override
	public void createSpestcnt(Spestcnt spestcnt, String webAppName) {
		dao.createSpestcnt(spestcnt, webAppName);
		
	}

	@Override
	public List<Spestcnt> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}

	@Override
	public void updateSpestcnt(Spestcnt spestcnt, String webAppName) {
		dao.updateSpestcnt(spestcnt, webAppName);
		
	}

	@Override
	public void removeSpestcnt(Spestcnt spestcnt, String webAppName) {
		dao.removeSpestcnt(spestcnt, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Spestcnt findByContractorId(String contractorId,String deptId, String webAppName) throws PersistenceException {
		return dao.findByContractorId(contractorId,deptId, webAppName);
	}
	
	@Override
	public Spestcnt findById(SpestcntPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}
	
	@Override
	public String getNextContractorId(String deptId, String webAppName) {
		return dao.getNextContractorId(deptId, webAppName);
	}

	@Override
	public void createSpestcntAutoId(Spestcnt spestcnt, String webAppName) {
		dao.createSpestcntAutoId(spestcnt, webAppName);
		
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpestcntEjb ejb=new SpestcntEjb();
		//System.out.println(ejb.getAll("510.20", "SMCTesting"));
		//System.out.println(ejb.getNextContractorId("510.20", "SMCTesting"));
		//System.out.println(ejb.findByContractorId("1", "SMCTesting"));
		SpestcntPK id=new SpestcntPK();
		id.setContractorId("0001");
		id.setDeptId("541.10");
		System.out.println(ejb.findById(id, "R1"));
		//System.out.println(ejb.findByContractorId("0001", "R1"));
		SpestcntPK id2=new SpestcntPK();
		id2.setContractorId(null);
		id2.setDeptId("510.20");
		Spestcnt spestcnt=new Spestcnt();
		spestcnt.setId(id2);
		//ejb.createSpestcntAutoId(spestcnt);
		SpestcntPK id3=new SpestcntPK();
		id3.setContractorId("0015");
		id3.setDeptId("510.20");
		//System.out.println(ejb.findById(id, "SMCTesting"));

	}

	
	

	@Override
	public List<Spestcnt> getContractorByStatus(String deptId, String status,
			String webAppName) {
		return dao.getContractorByStatus(deptId, status, webAppName);
	}
	

	
	

	

}
