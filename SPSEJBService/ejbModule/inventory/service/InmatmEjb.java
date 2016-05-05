package inventory.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.model.MaterialPriceInfo;

import inventory.ejb.InmatmDaoRemote;
import inventory.model.Inmatm;
import inventory.model.MatInfo;

public class InmatmEjb implements InmatmDaoRemote{
	private Context context;
	private InmatmDaoRemote dao; 
	public InmatmEjb() {
		super();
		this.dao=lookupDao();
		
	}

	private InmatmDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 InmatmDaoRemote dao = (InmatmDaoRemote) context.lookup("InmatmDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	@Override
	public void createInmatm(Inmatm inmatm, String webAppName) {
		dao.createInmatm(inmatm, webAppName);
		
	}

	@Override
	public List<Inmatm> getAll1(String webAppName) {
		return dao.getAll1(webAppName);
	}

	@Override
	public void updateInmatm(Inmatm inmatm, String webAppName) {
		dao.updateInmatm(inmatm, webAppName);
		
	}

	@Override
	public void removeInmatm(Inmatm inmatm, String webAppName) {
		dao.removeInmatm(inmatm, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Inmatm findById(String matCd, String webAppName) throws PersistenceException {
		return dao.findById(matCd, webAppName);
	}

	@Override
	public String findName(String matCd, String webAppName) throws PersistenceException {
		return dao.findName(matCd, webAppName);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InmatmEjb ejb=new InmatmEjb();
		System.out.println(ejb.findName("K1026", "SMCTesting")+"#");
		//System.out.println(ejb.getMatListByCategory("K1026", "A"));

	}

	@Override
	public Inmatm findMatItem(String matCd, String webAppName)
			throws PersistenceException {
		return dao.findMatItem(matCd, webAppName);
	}

	/*@Override
	public List<MatInfo> getMatListByCategory(String deptId, String like, String webAppName) {
		return dao.getMatListByCategory(deptId, like);
	}*/
	

}
