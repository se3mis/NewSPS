package estimate.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.PcestdttDaoRemote;
//import estimate.model.Pcestdtt;
//import estimate.model.PcestdttPK;
import estimate.model.Pcestdtt;
import estimate.model.PcestdttPK;

public class PcestdttEjb implements PcestdttEjbI {
	private Context context;
	private PcestdttDaoRemote dao; 
	private String region=null;
	
	public PcestdttEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}

	private PcestdttDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 PcestdttDaoRemote dao = (PcestdttDaoRemote) context.lookup("PcestdttDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void createPcestdtt(Pcestdtt pcestdtt) {
		dao.createPcestdtt(pcestdtt, region);
		
	}

	@Override
	public List<Pcestdtt> getAll() {
		return dao.getAll(region);
	}

	@Override
	public void updatePcestdtt(Pcestdtt pcestdtt) {
		dao.updatePcestdtt(pcestdtt, region);
		
	}

	@Override
	public void removePcestdtt(Pcestdtt pcestdtt) {
		dao.removePcestdtt(pcestdtt, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Pcestdtt findById(PcestdttPK id) throws PersistenceException {
		return dao.findById(id, region);
	}
	
	@Override
	public Pcestdtt findBy3PK(String estimateNo, String deptId, String resCd) {
		return dao.findBy3PK(estimateNo, deptId, resCd, region);
	}

	@Override
	public List<Pcestdtt> findByEstimationNo(String estimateNo, String deptId) {
		return dao.findByEstimationNo(estimateNo, deptId, region);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PcestdttEjb ejb=new PcestdttEjb("region");
		//System.out.print(ejb.getAll());
		System.out.print(ejb.findByEstimationNo("test123", "440.20"));

	}

	@Override
	public List<Pcestdtt> findByEstimationNo(String estimateNo) {
		return dao.findByEstimationNo(estimateNo, region);
	}

	@Override
	public List<Pcestdtt> findByEstimationNo(String estimateNo, String deptId,
			Long revNo) {
		return dao.findByEstimationNo(estimateNo,deptId,revNo, region);
	}

	@Override
	public Pcestdtt findBy3PK(String estimateNo, String deptId, String resCd,
			Long revNo, String webAppName) {
		return dao.findBy3PK(estimateNo,deptId,resCd,revNo, region);
	}

	@Override
	public List<Map> getSUMByResType(String estimateNo, String deptId) {
		return dao.getSUMByResType(estimateNo,deptId,region);
	}


}
