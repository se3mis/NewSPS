package job.service;

import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import job.ejb.PcestdmtDaoRemote;
//import job.model.Pcestdmt;
//import job.model.PcestdmtPK;
import job.model.Pcestdmt;
import job.model.PcestdmtPK;

public class PcestdmtEjb implements PcestdmtEjbI{
	private Context context;
	private PcestdmtDaoRemote dao;
	private String region=null;
	
	public PcestdmtEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}

	private PcestdmtDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 PcestdmtDaoRemote dao = (PcestdmtDaoRemote) context.lookup("PcestdmtDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	public void createPcestdmt(Pcestdmt pcestdmt) {
		dao.createPcestdmt(pcestdmt, region);
		
	}

	@Override
	public void updatePcestdmt(Pcestdmt pcestdmt) {
		dao.updatePcestdmt(pcestdmt, region);
		
	}

	@Override
	public void removePcestdmt(Pcestdmt pcestdmt) {
		dao.removePcestdmt(pcestdmt, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public List<Pcestdmt> getAll() {
		return dao.getAll(region);
	}

	@Override
	public Pcestdmt findById(PcestdmtPK id) throws PersistenceException {
		return dao.findById(id, region);
	}

	@Override
	public List<Pcestdmt> findByEstimationNo(String estimateNo, String deptId) {
		return dao.findByEstimationNo(estimateNo, deptId, region);
	}
	
	@Override
	public Pcestdmt findBy3PK(String estimateNo, String deptId, String resCd) {
		return dao.findBy3PK(estimateNo, deptId, resCd, region);
	}

     
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PcestdmtEjb ejb=new PcestdmtEjb("region");
		System.out.print(ejb.findByEstimationNo("080541", "440.20"));
		

	}

	@Override
	public List<Pcestdmt> findByEstimationNo(String estimateNo) {
		return dao.findByEstimationNo(estimateNo, region);
	}


	@Override
	public Pcestdmt findByJobNo(String jobNo, String deptId) {
		return dao.findByJobNo(jobNo, deptId, region);
	}
	@Override
	public List<Map> getSUMByResType(String estimateNo, String deptId) {
		return dao.getSUMByResType(estimateNo,deptId,region);
	}


}
