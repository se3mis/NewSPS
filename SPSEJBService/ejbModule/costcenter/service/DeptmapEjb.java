package costcenter.service;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import costcenter.ejb.DeptmapmDaoRemote;


public class DeptmapEjb implements DeptmapmDaoRemote {
	private Context context;
	private DeptmapmDaoRemote dao; 
	
	
	public DeptmapEjb() {
		super();
		this.dao=lookupDao();
		//lookupDao2();
	}

	private DeptmapmDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 DeptmapmDaoRemote dao = (DeptmapmDaoRemote) context.lookup("DeptmapmDao/remote");
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
		DeptmapEjb ejb=new DeptmapEjb();
		System.err.println(ejb.findMappingDept("441.40", "SMCTesting"));

	}

	@Override
	public String findMappingDept(String deptId, String webAppName) {
		return dao.findMappingDept(deptId, webAppName);
	}

}
