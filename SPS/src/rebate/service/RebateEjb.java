package rebate.service;

import java.math.BigDecimal;
import java.util.List;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import rebate.ejb.RebateBeanRemote;



import rebate.model.Sprebate;
import rebate.model.SprebatePK;

public class RebateEjb implements RebateEjbI{
	
	private Context context;
	private RebateBeanRemote dao; 
	private String region=null;
	
	
	public RebateEjb(String region) {
		super();
		this.region=region;
		dao=lookupRebateDao();
		
	}

	
	private RebateBeanRemote lookupRebateDao() {
		try
		{
			 context = new InitialContext();
			 RebateBeanRemote dao = (RebateBeanRemote) context.lookup("RebateBean/remote");
			 return dao; 
			 
		} catch (NamingException e){
			e.printStackTrace();
			
			throw new RuntimeException(e);
		}
		
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RebateEjb obj = new RebateEjb("region");
		List<Sprebate> hh = obj.findByEstimationNo("GAYANITEST812","501.20" , "Testing");
		System.out.println("hii"+ hh);
	}

	@Override
	public void createRebate(Sprebate sprebate, String webAppName) {
		// TODO Auto-generated method stub
		dao.createRebate(sprebate, region);
	}

	@Override
	public List<Sprebate> getAll(String webAppName) {
		// TODO Auto-generated method stub
		return dao.getAll(region);
	}

	@Override
	public void updateRebate(Sprebate sprebate, String webAppName) {
		// TODO Auto-generated method stub
		dao.updateRebate(sprebate, region);
	}

	@Override
	public void removeRebate(Sprebate sprebate, String webAppName) {
		// TODO Auto-generated method stub
		dao.removeRebate(sprebate,region);
	}

	@Override
	public void removeAll(String webAppName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Sprebate findById(SprebatePK id, String webAppName)
			throws PersistenceException {
		// TODO Auto-generated method stub
		return dao.findById(id, region);
	}

	@Override
	public List<Sprebate> findByEstimationNo(String estimateNo, String deptId,
			String webAppName) {
		// TODO Auto-generated method stub
		return dao.findByEstimationNo(estimateNo, deptId, region);
	}

	@Override
	public Sprebate findBy3PK(String estimateNo, String deptId, String resCd,
			String webAppName) {
		// TODO Auto-generated method stub
		return dao.findBy3PK(estimateNo, deptId, resCd, region);
	}

	@Override
	public List<Sprebate> findByEstimationNo(String estimateNo,
			String webAppName) {
		// TODO Auto-generated method stub
		return dao.findByEstimationNo(estimateNo, region);
	}


	@Override
	public BigDecimal getSUMRebate(String estimateNo, String webAppName) {
		// TODO Auto-generated method stub
		return dao.getSUMRebate(estimateNo, region);
	}


	@Override
	public List<String> findByEstimationNoStr(String estimateNo, String deptId,
			String webAppName) {
		// TODO Auto-generated method stub
		return dao.findByEstimationNoStr(estimateNo, deptId, region);
	}


	@Override
	public String getNextDocNo(String applicationNoPrefix, String webAppName) {
		// TODO Auto-generated method stub
		return dao.getNextDocNo(applicationNoPrefix, webAppName);
	}

}
