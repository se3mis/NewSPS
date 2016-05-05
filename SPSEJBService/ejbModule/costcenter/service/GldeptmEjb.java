package costcenter.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import costcenter.ejb.GldeptmDaoRemote;
import costcenter.model.Gldeptm;

public class GldeptmEjb implements GldeptmDaoRemote {
	private Context context;
	private GldeptmDaoRemote dao;

	public GldeptmEjb() {
		super();
		this.dao = lookupDao();
		// lookupDao2();
	}

	private GldeptmDaoRemote lookupDao() {
		try {
			context = new InitialContext();
			GldeptmDaoRemote dao = (GldeptmDaoRemote) context
					.lookup("GldeptmDao/remote");
			// System.out.println("got dao "+dao);
			// System.out.println(dao.findGldeptm(""));
			return dao;
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public String findDeptName(String deptId, String webAppName) {
		return dao.findDeptName(deptId, webAppName);
	}

	@Override
	public Gldeptm findGldeptm(Gldeptm gldeptm, String webAppName) {
		return dao.findGldeptm(gldeptm, webAppName);
	}

	@Override
	public Gldeptm findGldeptm(String deptId, String webAppName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gldeptm> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public List<String> findAreaDeptIdList(String deptId, String webAppName) {
		return dao.findAreaDeptIdList(deptId, webAppName);
	}

	@Override
	public List<String> findDgmDeptIdList(String deptId, String webAppName) {
		return dao.findDgmDeptIdList(deptId, webAppName);
	}

	@Override
	public List<String> findAgmDeptIdList(String deptId, String webAppName) {
		return dao.findAgmDeptIdList(deptId, webAppName);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GldeptmEjb ejb = new GldeptmEjb();
		System.out.println(ejb.findDeptName("510.20", "SMCTesting"));

		System.out.println(ejb.lookupDao().findGldeptm("514.20", "SMCTesting"));
		System.out.println(ejb.findAreaDeptIdList("440.00", "SMCTesting"));
		// System.out.println(ejb.getAll().size());
	}

	@Override
	public List<String> findAreaCodes(String deptId, String webAppName)
			throws PersistenceException {
		
		return dao.findAreaCodes(deptId, webAppName);
	}

	@Override
	public List<String> getPostDepartmentIds(String deptId, String webAppName)
			throws PersistenceException {
		return dao.getPostDepartmentIds(deptId, webAppName);
	}
	@Override
	public List<String> findAreaCodeNames(String deptId, String webAppName)
			throws PersistenceException {
		
		return dao.findAreaCodeNames(deptId, webAppName);
	}

	@Override
	public List<String> findAreaCodesForPost(String deptId, String webAppName) {
		// TODO Auto-generated method stub
		return dao.findAreaCodeNames(deptId, webAppName);
	}
	
	
}
