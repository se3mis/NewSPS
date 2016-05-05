package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;


import estimate.model.Splbpolt;
import estimate.model.SplbpoltPK;
import estimate.ejb.SplbpoltDaoRemote;;
public class SplbpoltEjb implements SplbpoltEjbI {
	private Context context;
	private SplbpoltDaoRemote dao; 
	private String region=null;
	public SplbpoltEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupBean();
		
	}

	private SplbpoltDaoRemote lookupBean() {
		try
		{
			 context = new InitialContext();
			 SplbpoltDaoRemote bean = (SplbpoltDaoRemote) context.lookup("SplbpoltDao/remote");
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
		// TODO Auto-generated method stub

	}

	@Override
	public void createSplbpole(Splbpolt splbpole) {
		dao.createSplbpolt(splbpole, region);
		
	}

	@Override
	public List<Splbpolt> getAll(String deptId) {
		return null;
	}

	@Override
	public void updateSplbpole(Splbpolt splbpole) {
		dao.updateSplbpolt(splbpole, region);
		
	}

	@Override
	public void removeSplbpole(Splbpolt splbpole) {
		dao.removeSplbpolt(splbpole, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Splbpolt findById(SplbpoltPK id) throws PersistenceException {
		return dao.findById(id, region);
	}

	

}
