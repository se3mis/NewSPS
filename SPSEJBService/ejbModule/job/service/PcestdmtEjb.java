package job.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import job.ejb.PcestdmtDaoRemote;
import job.model.Pcestdmt;
import job.model.PcestdmtPK;


public class PcestdmtEjb implements PcestdmtDaoRemote{
	private Context context;
	private PcestdmtDaoRemote dao; 
	public PcestdmtEjb() {
		super();
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
	public void createPcestdmt(Pcestdmt pcestdmt, String webAppName) {
		dao.createPcestdmt(pcestdmt, webAppName);
		
	}

	@Override
	public void updatePcestdmt(Pcestdmt pcestdmt, String webAppName) {
		dao.updatePcestdmt(pcestdmt, webAppName);
		
	}

	@Override
	public void removePcestdmt(Pcestdmt pcestdmt, String webAppName) {
		dao.removePcestdmt(pcestdmt, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public List<Pcestdmt> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public Pcestdmt findById(PcestdmtPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}

	@Override
	public List<Pcestdmt> findByEstimationNo(String estimateNo, String deptId, String webAppName) {
		return dao.findByEstimationNo(estimateNo, deptId, webAppName);
	}

	@Override
	public Pcestdmt findByJobNo(String jobNo, String deptId, String webAppName) {
		return dao.findByJobNo(jobNo, deptId, webAppName);
	}

	@Override
	public List<String> findJobNoList(String deptId, BigDecimal status, String webAppName) {
		return dao.findJobNoList(deptId, status, webAppName);
	}
	
	@Override
	public Pcestdmt findBy3PK(String estimateNo, String deptId, String resCd, String webAppName) {
		return dao.findBy3PK(estimateNo, deptId, resCd, webAppName);
	}
	
	@Override
	public List<Pcestdmt> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}
	@Override
	public void updateRevNo(Pcestdmt pcestdmt, Long newRevNo, String webAppName) {
		dao.updateRevNo(pcestdmt, newRevNo, webAppName);
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PcestdmtEjb ejb=new PcestdmtEjb();
		//System.out.println(ejb.findByEstimationNo("517.10/ENC/2011/1103", "517.10", "R3"));
		System.out.println(ejb.findBy3PK("517.10/ENC/2011/1103", "517.10", "LABOUR","R3"));
		//System.out.print(ejb.findByJobNo("010312", "510.20", "SMCTesting"));
		
		

	}

	@Override
	public List<Pcestdmt> findByEstimationNo(String estimateNo,
			String webAppName) {
		return dao.findByEstimationNo(estimateNo, webAppName);
	}

	@Override
	public BigDecimal getSUM(String estimateNo, String webAppName) {
		return dao.getSUM(estimateNo, webAppName);
	}

	@Override
	public void changeUnitPtrice(String estimateNo, String resCd,
			String webAppName) {
		dao.changeUnitPtrice(estimateNo, resCd, webAppName);
		
	}

	@Override
	public List<Map> getSUMByResType(String estimateNo, String costCenter,
			String webAppName) throws PersistenceException {
		return dao.getSUMByResType(estimateNo, costCenter, webAppName);
	}

	@Override
	public BigDecimal getSUMOfMATandMAT_OTHER(String estimateNo,
			String webAppName) throws PersistenceException {
		return dao.getSUMOfMATandMAT_OTHER(estimateNo,  webAppName);
	}

	@Override
	public Long getRawCountOtherMat(String estimateNo, String deptId,
			String resCode, String webAppName) throws PersistenceException {
		return dao.getRawCountOtherMat(estimateNo,deptId, resCode, webAppName);
	}

	@Override
	public int updatePcestdmtOtherMaterialSum(String estimateNo, String deptId,
			String resCode, BigDecimal estimateCost, String webAppName)
			throws PersistenceException {
		return dao.updatePcestdmtOtherMaterialSum(estimateNo,deptId, resCode,estimateCost, webAppName);
	}

	@Override
	public int updatePcestdmtPercentageCost(String estimateNo, String deptId,
			String resCode, BigDecimal estimateCost, BigDecimal estimateQuantity, String webAppName) {
		return dao.updatePcestdmtPercentageCost(estimateNo,deptId, resCode,estimateCost,estimateQuantity, webAppName);
	}

	@Override
	public Pcestdmt getdmtByResCD(String estimateNo, String resCD,
			String webAppName) {
		return dao.getdmtByResCD(estimateNo,resCD, webAppName);
	}

	

	

	

	

}
