package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import estimate.ejb.SpreconnDaoRemote;
import estimate.model.Spreconn;

public class SpreconnEjb implements SpreconnDaoRemote {
	private Context context;
	private SpreconnDaoRemote dao; 
	public SpreconnEjb() {
		super();
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
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpreconnEjb ejb=new SpreconnEjb();
		System.out.println(ejb.getAll("SMCTesting"));

	}

	@Override
	public Spreconn findById(long disconnectDuration, String webAppName) {
		return dao.findById(disconnectDuration, webAppName);
	}

	@Override
	public List<Spreconn> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}

}
