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


public class RebateEjb implements RebateBeanRemote{
	
	
	private Context context;
	private RebateBeanRemote bean; 
	public RebateEjb() {
		super();
		this.bean=lookupDao();
		
	}

	private RebateBeanRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 RebateBeanRemote bean = (RebateBeanRemote) context.lookup("RebateBean/remote");
			 return bean; 
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
		RebateEjb obj = new RebateEjb();
		//List<Sprebate> hh = obj.findByEstimationNo("GAYANITEST812","501.20" , "Testing");
		System.out.println("hiiiii");
		String ssss = obj.getNextDocNo("2014/", "testing");
		System.out.println("hii"+ ssss);
	}

	@Override
	public void createRebate(Sprebate sprebate, String webAppName) {
		// TODO Auto-generated method stub
		bean.createRebate(sprebate, webAppName);
	}

	@Override
	public List<Sprebate> getAll(String webAppName) {
		// TODO Auto-generated method stub
		return bean.getAll(webAppName);
	}

	@Override
	public void updateRebate(Sprebate sprebate, String webAppName) {
		// TODO Auto-generated method stub
		bean.updateRebate(sprebate, webAppName);
	}

	@Override
	public void removeRebate(Sprebate sprebate, String webAppName) {
		// TODO Auto-generated method stub
	     bean.removeRebate(sprebate,webAppName);
	}

	@Override
	public void removeAll(String webAppName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Sprebate findById(SprebatePK id, String webAppName) throws PersistenceException {
		// TODO Auto-generated method stub
		return bean.findById(id, webAppName);
	}

	@Override
	public List<Sprebate> findByEstimationNo(String estimateNo, String deptId,String webAppName) {
		// TODO Auto-generated method stub
		return bean.findByEstimationNo(estimateNo,deptId, webAppName);
	}

	@Override
	public Sprebate findBy3PK(String estimateNo, String deptId, String resCd,String webAppName) {
		// TODO Auto-generated method stub
		return bean.findBy3PK(estimateNo, deptId, resCd, webAppName);
	}

	@Override
	public List<Sprebate> findByEstimationNo(String estimateNo,String webAppName) {
		// TODO Auto-generated method stub
		return bean.findByEstimationNo(estimateNo, webAppName);
	}

	@Override
	public BigDecimal getSUMRebate(String estimateNo, String webAppName) {
		// TODO Auto-generated method stub
		return bean.getSUMRebate(estimateNo, webAppName);
	}

	@Override
	public List<String> findByEstimationNoStr(String estimateNo, String deptId,	String webAppName) {
		// TODO Auto-generated method stub
		return bean.findByEstimationNoStr(estimateNo,deptId, webAppName);
	}

	@Override
	public String getNextDocNo(String applicationNoPrefix, String webAppName) {
		// TODO Auto-generated method stub
		return bean.getNextDocNo(applicationNoPrefix, webAppName);
	}

}
