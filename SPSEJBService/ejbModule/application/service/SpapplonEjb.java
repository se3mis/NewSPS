package application.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import application.ejb.SpapplonDaoRemote;
import application.model.Spapplon;
import application.model.SpapplonPK;

public class SpapplonEjb implements SpapplonDaoRemote{
	private Context context;
	private SpapplonDaoRemote dao; 
	
	
	
	public SpapplonEjb() {
		super();
		this.dao=lookupDao();
	}

	private SpapplonDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpapplonDaoRemote dao = (SpapplonDaoRemote) context.lookup("SpapplonDao/remote");
			 //System.out.println(dao);
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
		SpapplonEjb ejb=new  SpapplonEjb();
		SpapplonPK id=new SpapplonPK();
		id.setApplicationNo("345345345");
		id.setDeptId("510.20");
		ejb.calculateLoanAmounts(id,new Double("30000"), new Double("60"), new Double("12"),"SMCTesting");

	}

	@Override
	public void createSpapplon(Spapplon spapplon, String webAppName) {
		dao.createSpapplon(spapplon, webAppName);
		
	}

	@Override
	public void updateSpapplon(Spapplon spapplon, String webAppName) {
		dao.updateSpapplon(spapplon, webAppName);
		
	}

	@Override
	public void removeSpapplon(Spapplon spapplon, String webAppName) {
		dao.removeSpapplon(spapplon, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll( webAppName);
		
	}

	@Override
	public List<Spapplon> getAll( String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public List<Spapplon> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}

	@Override
	public Spapplon findById(SpapplonPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}

	@Override
	public void calculateLoanAmounts(SpapplonPK id,double loanAmount,
			double noOfInstallments, double interestrate, String webAppName) {
		dao.calculateLoanAmounts(id,loanAmount, noOfInstallments, interestrate, webAppName);
		
	}

	@Override
	public Spapplon findByApplicationNo(String applicationNo, String webAppName) {
		return dao.findByApplicationNo(applicationNo, webAppName);
	}

}
