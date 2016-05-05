package inventory.service;

import java.math.BigDecimal;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import inventory.ejb.InwrhmapDaoRemote;

public class InwrhmapEjb implements  InwrhmapDaoRemote{
	private Context context;
	private InwrhmapDaoRemote dao; 
	public InwrhmapEjb() {
		super();
		this.dao=lookupDao();
		
	}

	private InwrhmapDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 InwrhmapDaoRemote dao = (InwrhmapDaoRemote) context.lookup("InwrhmapDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	@Override
	public String mapWarehouse(String deptId, String webAppName) {
		return dao.mapWarehouse(deptId, webAppName);
	}
	
	public static void main(String[] args){
		InwrhmapEjb ejb=new InwrhmapEjb();
		System.err.println(ejb.mapWarehouse("510.20", "SMCTesting"));
	}

	@Override
	public List<String> loadWarehouses(String deptId, String webAppName) {
		return dao.loadWarehouses(deptId, webAppName);
	}

	@Override
	public BigDecimal getConRateByDeptId(String deptId, String webAppName)
			throws Exception {
		return dao.getConRateByDeptId(deptId, webAppName);
	}

}
