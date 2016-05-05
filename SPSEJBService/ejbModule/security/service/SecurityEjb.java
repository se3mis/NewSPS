package security.service;

import java.util.List;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import security.ejb.SecurityBeanRemote;

public class SecurityEjb implements SecurityBeanRemote{
	private Context context;
	private SecurityBeanRemote bean; 
	public SecurityEjb() {
		super();
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
	public String getPassword(String userName, String webAppName) {
		return bean.getPassword(userName, webAppName);
	}
	
	@Override
	public List<String> getAuthorizedCostCenters(String userName, String webAppName) {
		return bean.getAuthorizedCostCenters(userName, webAppName);
	}
	
	@Override
	public Boolean volidateLogin(String userName, String password, String webAppName) throws Exception {
		return bean.volidateLogin(userName, password, webAppName);
	}

	@Override
	public String getCostCenter(String userName, String webAppName) {
		return bean.getCostCenter(userName, webAppName);
	}
	
	@Override
	public String getAuthorizedLevel(String userName, String webAppName) {
		return bean.getAuthorizedLevel(userName, webAppName);
	}
	
	@Override
	public List<String> getUserList(String deptId, String userLevel, String webAppName) {
		return bean.getUserList(deptId, userLevel, webAppName);
	}
	@Override
	public List<String> getUserList(String deptId, List<String> userLevel, String webAppName) {
		return bean.getUserList(deptId, userLevel, webAppName);
	}
	
	public static void main (String[] args){
		SecurityEjb ejb=new SecurityEjb();
		//System.out.println(ejb.getPassword("es123"));
		//System.out.println(ejb.getCostCenter("518BCL1", "R3"));
		//System.out.println(ejb.getAuthorizedLevel("518BCL1", "R3"));
		//System.out.println(ejb.getAuthorizedCostCenters("518BCL1", "R3"));
		//System.out.println(ejb.getAuthorizedLevel("CECOLEAST", "R1"));
		//System.out.println(ejb.getCostCenter("CECOLEAST", "R1"));
		/*System.out.println(ejb.getAuthorizedCostCenters("CECOLEAST", "R1"));
		System.out.println(ejb.getAuthorizedLevel("515EE", "R4"));
		System.out.println(ejb.getCostCenter("515EE", "R4"));
		System.out.println(ejb.getAuthorizedCostCenters("515EE", "R4"));
		System.out.println(ejb.getUserList("424.10", "ES", "R3"));
		System.out.println(ejb.getAuthorizedLevel("ES321", "SMCTesting"));
		System.out.println(ejb.getAuthorizedLevel("ES321", "SMCTesting"));
		System.out.println(ejb.getAuthorizedCostCenters("es321", "SMCTesting"));
		System.out.println(ejb.getAuthorizedCostCenters("ea321", "SMCTesting"));
		System.out.println(ejb.getAuthorizedCostCenters("ae321", "SMCTesting"));
		System.out.println(ejb.getAuthorizedCostCenters("dgm321", "SMCTesting"));
		System.out.println(ejb.getAuthorizedCostCenters("agm123", "SMCTesting"));
		System.out.println(ejb.getUserList("443.20", "AGM", "SMCTesting"));
		System.out.println(ejb.getCostCenter("es123", "SMCTesting"));*/
		try {
			//System.out.println(ejb.volidateLogin("44250ES1", "thusitha2", "R2"));
			//System.out.println(ejb.volidateLogin("es123", "test123", "SMCTesting"));
			//System.out.println(ejb.getCostCenter("dgm123", "SMCTesting"));
			//System.out.println(ejb.getAuthorizedCostCenters("dgm123", "SMCTesting"));
			//List<String> userLevel = new ArrayList<String>();
			//userLevel.add("ES");
			//System.out.println(ejb.getUserList("530.00", userLevel, "R3"));
			System.out.println(ejb.getAuthorizedCostCenters("DGMNWP", "R1"));
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

	@Override
	public List<String> getAllUserList(String deptId, List<String> userLevel,
			String webAppName) {
		
		return bean.getAllUserList(deptId,userLevel, webAppName);
	}

	

	

	

	

}
