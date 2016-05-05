package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import estimate.ejb.SpreconnDaoRemote; 
import estimate.model.Spreconn;

public class SpreconnEjb implements SpreconnEjbI {
	private Context context;
	private SpreconnDaoRemote dao; 
	private String region=null;
	
	public SpreconnEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}
	
	private SpreconnDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpreconnDaoRemote dao = (SpreconnDaoRemote) context.lookup("SpreconnDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	

	@Override
	public Spreconn findById(long disconnectDuration) {
		return dao.findById(disconnectDuration, region);
	}

	@Override
	public List<Spreconn> getAll() {
		return dao.getAll(region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpreconnEjb ejb=new SpreconnEjb("region");
		System.out.println(ejb.getAll());

	}

}