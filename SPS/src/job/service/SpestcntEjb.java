package job.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import job.ejb.SpestcntDaoRemote;
import job.model.Spestcnt;
import job.model.SpestcntPK;
//import job.model.Spestcnt;

public class SpestcntEjb implements SpestcntEjbI{
	private Context context;
	private SpestcntDaoRemote dao;
	private String region=null;
	
	public SpestcntEjb(String region) {
		super();
		this.region=region;
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
	public void createSpestcnt(Spestcnt spestcnt) {
		dao.createSpestcnt(spestcnt, region);
		
	}

	@Override
	public List<Spestcnt> getAll(String deptId) {
		return dao.getAll(deptId, region);
	}

	@Override
	public void updateSpestcnt(Spestcnt spestcnt) {
		dao.updateSpestcnt(spestcnt, region);
		
	}

	@Override
	public void removeSpestcnt(Spestcnt spestcnt) {
		dao.removeSpestcnt(spestcnt, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Spestcnt findByContractorId(String contractorId,String deptId) throws PersistenceException {
		return dao.findByContractorId(contractorId,deptId, region);
	}
	
	@Override
	public Spestcnt findById(SpestcntPK id) throws PersistenceException {
		return dao.findById(id, region);
	}
	

	@Override
	public void createSpestcntAutoId(Spestcnt spestcnt) {
		dao.createSpestcntAutoId(spestcnt, region);
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
SpestcntEjb ejb=new SpestcntEjb("region");
		
		Spestcnt spestcnt=new Spestcnt();
		//spestcnt.setContractorId("1234");
		//spestcnt.setContractorName("sdsdf");
		//spestcnt.setContractorAddress("jvjnsdfn");
		//spestcnt.setDeptId("510.20");
		//spestcnt.setJobInHand(new BigDecimal("2"));
		//spestcnt.setTotalAmount(new BigDecimal("55555"));
		//ejb.createSpestcnt(spestcnt);
		//spestcnt=ejb.findByContractorId("0002");
		System.out.println(spestcnt);
		//ejb.updateSpestcnt(spestcnt);
		System.out.println(ejb.getAll("510.20"));


	}

	@Override
	public List<Spestcnt> getContractorByStatus(String deptId, String status) {
		return dao.getContractorByStatus(deptId, status, region);
	}


	

}
