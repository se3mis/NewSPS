package util.common;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ManagerTest implements ManagerRemote{
	private Context context;
	private ManagerRemote bean; 
	public ManagerTest() {
		super();
		this.bean=lookupDao();
		
	}

	private ManagerRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 ManagerRemote bean = (ManagerRemote) context.lookup("Manager/remote");
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
		ManagerTest test=new ManagerTest();
		System.out.println(test);
		test.getEntityManager("SMCR1");
		
		

	}

	@Override
	public EntityManager getEntityManager(String webAppName) {
		return bean.getEntityManager(webAppName);
	}

}
