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

public class SpserestEjb implements SpserestEjbI{
	private Context context;
	private SpserestDaoRemote dao;
	private String region=null;
	
	public SpserestEjb(String region) {
		super();
		this.region=region;
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



	

	@Override
	public void createSpserest(Spserest Spserest) {
		dao.createSpserest(Spserest, region);
		
	}

	@Override
	public List<Spserest> getAll(String deptId) {
		return dao.getAll(deptId, region);
	}

	@Override
	public void updateSpserest(Spserest Spserest) {
		dao.updateSpserest(Spserest, region);
		
	}

	@Override
	public void removeSpserest(Spserest Spserest) {
		dao.removeSpserest(Spserest, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Spserest findById(SpserestPK id) throws PersistenceException {
		return dao.findById(id, region);
	}

	@Override
	public void saveServiceEstimate(Spserest spserest, List<Spsetpol> poleList, List<Spsetstu> strutList, List<Spsetsty> stayList, List<Spsetwir> wireList,List<SpsetpolPK> delPoleList, List<SpsetstuPK> delStrutList, List<SpsetstyPK> delStayList, List<SpsetwirPK> delWireList) {
		dao.saveServiceEstimate(spserest, poleList, strutList, stayList, wireList, delPoleList, delStrutList, delStayList, delWireList, region);
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpserestEjb ejb=new SpserestEjb("region");
		System.out.println(ejb);
		System.out.println(ejb.getAll("dsdf"));
		

	}

	@Override
	public Spserest findByApplicationNo(String applicationNo) {
		return dao.findByApplicationNo(applicationNo, region);
	}

	
}
