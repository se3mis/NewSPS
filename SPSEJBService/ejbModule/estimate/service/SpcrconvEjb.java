package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import estimate.ejb.SpcrconvDaoRemote;
import estimate.model.Spcrconv;
import estimate.model.SpcrconvPK;

public class SpcrconvEjb implements SpcrconvDaoRemote{
	private Context context;
	private SpcrconvDaoRemote dao; 
	public SpcrconvEjb() {
		super();
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
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpcrconvEjb ejb=new SpcrconvEjb();
		System.out.println(ejb.getAll("SMCTesting"));
		SpcrconvPK id=new SpcrconvPK();
		id.setFromPhase(1);
		id.setToPhase(30);
		id.setFromConnectionType(3);
		id.setToConnectionType(30);
		System.out.println(ejb.findById(id, "SMCTesting"));
		System.out.println(ejb.getAll("SMCTesting"));

	}

	@Override
	public Spcrconv findById(SpcrconvPK id, String webAppName) {
		return dao.findById(id, webAppName);
	}

	@Override
	public List<Spcrconv> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}

}
