package inventory.service;

import java.math.BigDecimal;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import inventory.ejb.InwrhmtmDaoRemote;
import inventory.model.Inwrhmtm;

public class InwrhmtmEjb implements InwrhmtmEjbI{
	private Context context;
	private InwrhmtmDaoRemote dao; 
	private String region=null;
	
	public InwrhmtmEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}

	private InwrhmtmDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 InwrhmtmDaoRemote dao = (InwrhmtmDaoRemote) context.lookup("InwrhmtmDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	public Inwrhmtm findByMatCd(String matCd, String warehouse) {
		return dao.findByMatCd(matCd, warehouse, region);
	}
	
	public static void main(String[] args){
		InwrhmtmEjb ejb=new InwrhmtmEjb("region");
		System.out.println(ejb);
	}

	@Override
	public BigDecimal findUnitPriceByMatCd(String matCd, String webAppName) {
		return dao.findUnitPriceByMatCd(matCd,  region);
	}
	@Override
	public List<String> findNPLMatCds(String deptId, String webAppName) {
		// TODO Auto-generated method stub
		return dao.findNPLMatCds(deptId, webAppName);
	}

	@Override
	public List<String> findMatCds(String deptId, String webAppName) {
		// TODO Auto-generated method stub
		return dao.findMatCds(deptId, webAppName);
	}
	@Override
	public BigDecimal findUPByMatCd(String matCd, String warehouse) {
		return dao.findUPByMatCd(matCd,warehouse, region);
	}
}
