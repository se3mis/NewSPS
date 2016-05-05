package security.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import security.ejb.SecurityBeanRemote;

public class SecurityEjb implements SecurityEjbI { 

	private Context context;
	private SecurityBeanRemote bean; 
	private String region=null;
	
	public SecurityEjb(String region) {
		super();
		this.region=region;
		this.bean=lookupDao();
		
	}

	private SecurityBeanRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SecurityBeanRemote dao = (SecurityBeanRemote) context.lookup("SecurityBean/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	@Override
	public String getPassword(String userName) {
		return bean.getPassword(userName, region);
	}
	
	@Override
	public List<String> getAuthorizedCostCenters(String userName) {
		return bean.getAuthorizedCostCenters(userName, region);
	}
	
	@Override
	public Boolean volidateLogin(String userName, String password) throws Exception {
		return bean.volidateLogin(userName, password, region);
	}

	@Override
	public String getCostCenter(String userName) {
		return bean.getCostCenter(userName, region);
	}

	@Override
	public String getAuthorizedLevel(String userName) {
		return bean.getAuthorizedLevel(userName, region);
	}
	
	@Override
	public List<String> getUserList(String deptId, String UserLevel) {
		return bean.getUserList(deptId, UserLevel, region);
	}
	@Override
	public List<String> getUserList(String deptId, List<String> UserLevel) {
		return bean.getUserList(deptId, UserLevel, region);
	}
	
	@Override
	public List<String> getAllUserList(String deptId, List<String> UserLevel) {
		return bean.getAllUserList(deptId, UserLevel, region);
	}
	public static void main (String[] args){
		SecurityEjb ejb=new SecurityEjb("region");
		System.out.println(ejb.getPassword("es123"));
		System.out.println(ejb.getAuthorizedCostCenters("dgm123"));
		try {
			System.out.println(ejb.volidateLogin("dgm123", "test123"));
			System.out.println(ejb.volidateLogin("es123", "test123"));
			System.out.println(ejb.getCostCenter("dgm123"));
			System.out.println(ejb.getAuthorizedLevel("dgm123"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getUserName(String userName, String webAppName) {
		// TODO Auto-generated method stub
		return bean.getUserName(userName, webAppName);
	}

	

}
