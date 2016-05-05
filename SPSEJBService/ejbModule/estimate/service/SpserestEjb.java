package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpserestDaoRemote;
import estimate.model.Spserest;
import estimate.model.SpserestPK;
import estimate.model.Spsetpol;
import estimate.model.SpsetpolPK;
import estimate.model.Spsetstu;
import estimate.model.SpsetstuPK;
import estimate.model.Spsetsty;
import estimate.model.SpsetstyPK;
import estimate.model.Spsetwir;
import estimate.model.SpsetwirPK;

public class SpserestEjb implements SpserestDaoRemote{
	private Context context;
	private SpserestDaoRemote dao; 
	public SpserestEjb() {
		super();
		this.dao=lookupDao();
		
	}
	
	private SpserestDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpserestDaoRemote dao = (SpserestDaoRemote) context.lookup("SpserestDao/remote");
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
		SpserestEjb  ejb=new SpserestEjb();
		System.out.println(ejb);

	}

	@Override
	public void createSpserest(Spserest Spserest, String webAppName) {
		dao.createSpserest(Spserest, webAppName);
		
	}

	@Override
	public List<Spserest> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}

	@Override
	public void updateSpserest(Spserest Spserest, String webAppName) {
		dao.updateSpserest(Spserest, webAppName);
		
	}

	@Override
	public void removeSpserest(Spserest Spserest, String webAppName) {
		dao.removeSpserest(Spserest, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Spserest findById(SpserestPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}

	@Override
	public void saveServiceEstimate(Spserest spserest, List<Spsetpol> poleList, List<Spsetstu> strutList, List<Spsetsty> stayList, List<Spsetwir> wireList,List<SpsetpolPK> delPoleList, List<SpsetstuPK> delStrutList, List<SpsetstyPK> delStayList, List<SpsetwirPK> delWireList, String webAppName) {
		dao.saveServiceEstimate(spserest, poleList, strutList, stayList, wireList, delPoleList, delStrutList, delStayList, delWireList, webAppName);
		
	}

	@Override
	public Spserest findByApplicationNo(String applicationNo, String webAppName) {
		return dao.findByApplicationNo(applicationNo, webAppName);
	}

}
