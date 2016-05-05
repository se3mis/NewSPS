package costcenter.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import util.common.EstimateStatus;

import costcenter.ejb.GldeptinDaoRemote;
import costcenter.model.Gldeptin;
import estimate.web.Estimate;

public class GldeptinEjb implements GldeptinEjbI {
	private Context context;
	private GldeptinDaoRemote dao; 
	private String region=null;
	
	
	public GldeptinEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		//lookupDao2();
	}

	private GldeptinDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 GldeptinDaoRemote dao = (GldeptinDaoRemote) context.lookup("GldeptinDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	

	@Override
	public void createGldeptin(Gldeptin gldeptin) {
		dao.createGldeptin(gldeptin, region);
		
	}

	@Override
	public List<Gldeptin> getAll() {
		return dao.getAll(region);
	}

	@Override
	public void updateGldeptin(Gldeptin gldeptin) {
		dao.updateGldeptin(gldeptin, region);
		
	}

	@Override
	public void removeGldeptin(Gldeptin gldeptin) {
		dao.removeGldeptin(gldeptin, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Gldeptin getFindByDeptId(String deptId) {
		return dao.getFindByDeptId(deptId, region);
	}
	
	@Override
	public Gldeptin findById(String deptId) throws PersistenceException {
		return dao.findById(deptId, region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GldeptinEjb ejb=new GldeptinEjb("region");
		System.out.println(ejb);
	

	}


}
