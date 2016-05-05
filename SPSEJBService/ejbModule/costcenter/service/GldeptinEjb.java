package costcenter.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import costcenter.ejb.GldeptinDaoRemote;
import costcenter.model.Gldeptin;

public class GldeptinEjb implements GldeptinDaoRemote{
	private Context context;
	private GldeptinDaoRemote dao; 
	
	
	public GldeptinEjb() {
		super();
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
	public void createGldeptin(Gldeptin gldeptin, String webAppName) {
		dao.createGldeptin(gldeptin, webAppName);
		
	}

	@Override
	public List<Gldeptin> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public void updateGldeptin(Gldeptin gldeptin, String webAppName) {
		dao.updateGldeptin(gldeptin, webAppName);
		
	}

	@Override
	public void removeGldeptin(Gldeptin gldeptin, String webAppName) {
		dao.removeGldeptin(gldeptin, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Gldeptin getFindByDeptId(String deptId, String webAppName) {
		return dao.getFindByDeptId(deptId, webAppName);
	}
	
	@Override
	public Gldeptin findById(String deptId, String webAppName) throws PersistenceException {
		return dao.findById(deptId, webAppName);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GldeptinEjb ejb=new GldeptinEjb();
		System.out.println(ejb.findById("441.50", "SMCTesting"));

	}

	

}
