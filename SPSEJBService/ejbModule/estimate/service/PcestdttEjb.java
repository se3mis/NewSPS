package estimate.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import job.model.Pcestdmt;

import estimate.ejb.PcestdttDaoRemote;
import estimate.model.Pcestdtt;
import estimate.model.PcestdttPK;

public class PcestdttEjb implements PcestdttDaoRemote{

	private Context context;
	private PcestdttDaoRemote dao; 
	public PcestdttEjb() {
		super();
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
	public void createPcestdtt(Pcestdtt pcestdtt, String webAppName) {
		dao.createPcestdtt(pcestdtt, webAppName);
		
	}

	@Override
	public List<Pcestdtt> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public void updatePcestdtt(Pcestdtt pcestdtt, String webAppName) {
		dao.updatePcestdtt(pcestdtt, webAppName);
		
	}

	@Override
	public void removePcestdtt(Pcestdtt pcestdtt, String webAppName) {
		dao.removePcestdtt(pcestdtt, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Pcestdtt findById(PcestdttPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}

	@Override
	public List<Pcestdtt> findByEstimationNo(String estimateNo, String deptId, String webAppName) {
		return dao.findByEstimationNo(estimateNo, deptId, webAppName);
	}
	
	@Override
	public Pcestdtt findBy3PK(String estimateNo, String deptId, String resCd, String webAppName) {
		return dao.findBy3PK(estimateNo, deptId, resCd, webAppName);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args){
		PcestdttEjb ejb=new PcestdttEjb();
		//System.out.print(ejb.getAll());
		//ejb.changeCostCenterNoPcestdtt("541.20/SNC/2011/0001", "541.00", "541.20", "SMCTesting");
		//System.out.println(ejb.findByEstimationNo("423.50/ENC/2011/1100", "R1"));
		//List<Pcestdtt> pcestdttList=ejb.findByEstimationNo("424.10/ENC/2011/1549", "424.10", "R1");
		//System.out.println("@"+ pcestdttList);
		//System.out.println("@"+ pcestdttList.get(0).getId().getRevNo());
		//System.out.println(ejb.findBy3PK("424.10/ENC/2011/1549", "424.10","D0610", "R1"));
		//System.out.println(ejb.findByEstimationNo("424.10/ENC/2011/1549", "R1"));
		System.out.println("##########");
		ejb.changeUnitPtrice("514.20/ENC/2011/0965", "LABOUR", "dfdg");
		ejb.changeUnitPtrice("514.20/ENC/2011/0965", "OVERHEAD", "dfdg");
		System.out.println("$$$$$$$$$$$");
		//System.out.println(ejb.findByEstimationNo("ABC/01/2006", "501.20", "SMCTesting"));
		//System.out.println(ejb.findBy3PK("ABC/01/2006", "501.20","B0230", "SMCTesting"));
		//System.out.println(ejb.findByEstimationNo("test123", "440.20","A0453"));
	}

	@Override
	public void changeCostCenterNoPcestdtt(String estimateNo, String areaDeptId, String depotDeptId, String webAppName) {
		dao.changeCostCenterNoPcestdtt(estimateNo, areaDeptId, depotDeptId, webAppName);
		
	}

	@Override
	public List<Pcestdtt> findByEstimationNo(String estimateNo,
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
	public  List<Map> getSUMByResType(String estimateNo, String deptId,
			String webAppName) {
		return dao.getSUMByResType(estimateNo,deptId,webAppName);
	}

	@Override
	public BigDecimal getSUMOfMATandMAT_OTHER(String estimateNo,
			String webAppName) {
		return dao.getSUMOfMATandMAT_OTHER(estimateNo,webAppName);
	}

	@Override
	public Long getRawCountOtherMat(String estimateNo, String deptId,
			String resCode, String webAppName) {
		return dao.getRawCountOtherMat(estimateNo,deptId,resCode,webAppName);
	}

	@Override
	public int updatePcestdttOtherMaterialSum(String estimateNo, String deptId,
			String resCode, BigDecimal estimateCost, String webAppName) {
		return dao.updatePcestdttOtherMaterialSum(estimateNo,deptId,resCode,estimateCost,webAppName);
	}

	@Override
	public List<Pcestdtt> findByEstimationNo(String estimateNo, String deptId,
			Long revNo, String webAppName) {
		return dao.findByEstimationNo(estimateNo,deptId,revNo,webAppName);
	}

	@Override
	public Pcestdtt findBy3PK(String estimateNo, String deptId, String resCd,
			Long revNo, String webAppName) {
		return dao.findBy3PK(estimateNo,deptId,resCd,revNo,webAppName);
	}

	@Override
	public Pcestdtt getdttByResCD(String estimateNo, String resCD,
			String webAppName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updatePcestdttPercentageCost(String estimateNo, String deptId,
			String resCode, BigDecimal estimateCost, BigDecimal estimateQuantity, String webAppName) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
