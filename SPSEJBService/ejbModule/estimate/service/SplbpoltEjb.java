package estimate.service;

import java.math.BigDecimal;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SplbpoltDaoRemote;
import estimate.model.Splbpolt;
import estimate.model.SplbpoltPK;

public class SplbpoltEjb implements SplbpoltDaoRemote{
	private Context context;
	private SplbpoltDaoRemote dao; 
	public SplbpoltEjb() {
		super();
		this.dao=lookupBean();
		
	}

	private SplbpoltDaoRemote lookupBean() {
		try
		{
			 context = new InitialContext();
			 SplbpoltDaoRemote dao = (SplbpoltDaoRemote) context.lookup("SplbpoltDao/remote");
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
		SplbpoltEjb ejb=new SplbpoltEjb();
		System.out.println(ejb.getAll("514.20", "skdbhfsdb"));
		
		Splbpolt splbpolt=new Splbpolt();
		SplbpoltPK id=new SplbpoltPK();
		id.setLabourCode("A11.2");
		id.setDeptId("514.20");
		System.out.println(ejb.findById(id, "skdbhfsdb"));
		splbpolt.setId(id);
		splbpolt.setActivityCode("TH030");
		splbpolt.setApplicationType("CR");
		splbpolt.setLabourHours(new BigDecimal("22"));
		splbpolt.setMatCd("J0365               ");
		splbpolt.setUnitPrice(new BigDecimal("11"));
		ejb.createSplbpolt(splbpolt, "dfgdfg");

	}

	@Override
	public void createSplbpolt(Splbpolt splbpolt, String webAppName) {
		dao.createSplbpolt(splbpolt, webAppName);
		
	}

	@Override
	public List<Splbpolt> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}

	@Override
	public void updateSplbpolt(Splbpolt splbpolt, String webAppName) {
		dao.updateSplbpolt(splbpolt, webAppName);
		
	}

	@Override
	public void removeSplbpolt(Splbpolt splbpolt, String webAppName) {
		dao.removeSplbpolt(splbpolt, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Splbpolt findById(SplbpoltPK id, String webAppName)
			throws PersistenceException {
		return dao.findById(id, webAppName);
	}

}
