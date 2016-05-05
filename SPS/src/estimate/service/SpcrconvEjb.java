package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import estimate.ejb.SpcrconvDaoRemote;
import estimate.model.Spcrconv;
import estimate.model.SpcrconvPK;

public class SpcrconvEjb implements SpcrconvEjbI{
	private Context context;
	private SpcrconvDaoRemote dao;
	private String region=null;
	
	public SpcrconvEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}
	
	private SpcrconvDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpcrconvDaoRemote dao = (SpcrconvDaoRemote) context.lookup("SpcrconvDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	

	@Override
	public Spcrconv findById(SpcrconvPK id) {
		return dao.findById(id, region);
	}

	@Override
	public List<Spcrconv> getAll() {
		return dao.getAll(region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpcrconvEjb ejb=new SpcrconvEjb("region");
		System.out.println(ejb.getAll());

	}

}
