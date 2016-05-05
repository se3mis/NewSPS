package costcenter.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import costcenter.ejb.GldeptmDaoRemote;
//import costcenter.model.Gldeptm;
import costcenter.model.Gldeptm;


public class GldeptmEjb implements GldeptmEjbI {
	private Context context;
	private GldeptmDaoRemote gldeptmDao;  
	private String region=null;
	
	public GldeptmEjb(String region) {
		super();
		this.region=region;
		gldeptmDao=lookupGldeptmDao();
		
	}

	
	private GldeptmDaoRemote lookupGldeptmDao() {
		try
		{
			 context = new InitialContext();
			 GldeptmDaoRemote gldeptmDao = (GldeptmDaoRemote) context.lookup("GldeptmDao/remote");
			 return gldeptmDao; 
			 
		} catch (NamingException e){
			e.printStackTrace();
			
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public String findDeptName(String DeptId){
		return gldeptmDao.findDeptName(DeptId, region);
	}
	
	 


	@Override
	public Gldeptm findGldeptm(Gldeptm gldeptm) {
		gldeptmDao.findGldeptm(gldeptm, region);
		return null;
	}


	@Override
	public Gldeptm findGldeptm(String deptId) {
		return gldeptmDao.findGldeptm(deptId, region);
		 
	}
	public static void main(String[] args){
		 GldeptmEjb gldeptmEjb=new GldeptmEjb("region");
		 System.out.println(gldeptmEjb.findDeptName("430.11"));
		 System.out.println(gldeptmEjb.findAreaCodesForPost("430.00"));
	 }
	

	@Override
	public List<String> getPostDepartmentIds(String deptId, String webAppName)
			throws Exception {
		return gldeptmDao.getPostDepartmentIds(deptId, region);
	}
	@Override
	public List<String> findAreaCodeNames(String deptId, String webAppName)
			throws Exception {
		
		return gldeptmDao.findAreaCodeNames(deptId, webAppName);
	}


	@Override
	public List<String> findAreaCodesForPost(String deptId) {
		// TODO Auto-generated method stub
		return gldeptmDao.findAreaCodesForPost(deptId, region);
	}
}
