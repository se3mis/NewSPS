package consumer.service;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import consumer.ejb.ConsumerDaoRemote;
import consumer.model.Consumer;

public class ConsumerEjb implements ConsumerEjbI  {
	private Context context;
	private ConsumerDaoRemote dao; 
	private String region=null;	
	public ConsumerEjb(String region) {
		super();
		this.region=region;
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
	public Consumer findApplicationRefNo(String applicationRefNo, String deptId) {
		return dao.findApplicationRefNo(applicationRefNo, deptId, region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConsumerEjb ejb=new ConsumerEjb("sgsg");
		System.out.println(ejb.findApplicationRefNo("LAND/2011/2", "510.20"));

	}

}
