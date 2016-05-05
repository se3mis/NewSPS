package consumer.service;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import consumer.ejb.ConsumerDaoRemote;
import consumer.model.Consumer;

public class ConsumerEjb implements ConsumerDaoRemote {
	private Context context;
	private ConsumerDaoRemote dao; 
		
	public ConsumerEjb() {
		super();
		this.dao=lookupDao();
	}

	private ConsumerDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 ConsumerDaoRemote dao = (ConsumerDaoRemote) context.lookup("ConsumerDao/remote");
			 //System.out.println(dao);
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	

	@Override
	public void update(String ccc) {
		dao.update(ccc);
		
	}

	@Override
	public Consumer findApplicationRefNo(String applicationRefNo,
			String deptId, String webAppName) {
		return dao.findApplicationRefNo(applicationRefNo, deptId, webAppName);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConsumerEjb ejb=new ConsumerEjb();
		//System.out.println(ejb.findApplicationRefNo("LAND/2011/2", "510.20", "Testing"));
		int sum = 0,x;
		x=1;
		while(x<10){
			sum +=x;
			++x;
		}
		System.out.println("this is the sum  : "+ sum);
		

	}

}
