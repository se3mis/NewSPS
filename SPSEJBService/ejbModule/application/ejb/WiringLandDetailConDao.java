package application.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import estimate.model.Spstdestdmt;

import job.model.Pcesthmt;

import util.emSelect.EmSelector;

import application.model.WiringInfo;
import application.model.WiringLandDetail;
import application.model.WiringLandDetailCon;
import application.model.WiringLandDetailConPK;
import application.model.WiringLandDetailPK;

/**
 * Session Bean implementation class WiringLandDetailDao
 */
@Stateless
public class WiringLandDetailConDao extends EmSelector  implements WiringLandDetailConDaoLocal, WiringLandDetailConDaoRemote {
	//@PersistenceContext
	//private EntityManager em;
	

	
    /**
     * Default constructor. 
     */
    public WiringLandDetailConDao() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void createWiringLandDetailCon(WiringLandDetailCon wiringLandDetailCon, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(wiringLandDetailCon);
		
	}

	@Override
	public WiringLandDetailCon findByAppId(WiringLandDetailConPK id, String webAppName) {
		//getEntityManager(webAppName);
		return getEntityManager(webAppName).find(WiringLandDetailCon.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public WiringLandDetailCon findByApplicationNo(String applicationNo, String webAppName) {
		//getEntityManager(webAppName);
		
		String qryStr ="SELECT a FROM WiringLandDetailCon a, Application b where b.applicationNo=:applicationNo AND a.id.applicationId=b.id.applicationId  ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationNo", applicationNo);
		List<WiringLandDetailCon> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();

	}

	@Override
	public void updateWiringLandDetailCon(WiringLandDetailCon wiringLandDetail, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(wiringLandDetail);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WiringLandDetailCon> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr ="SELECT a FROM WiringLandDetailCon a";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		List<WiringLandDetailCon> list = query.getResultList();
		return list;

	}

	@Override
	public void removeWiringLandDetailCon(WiringLandDetailCon wiringLandDetail, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).remove(wiringLandDetail);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa If u want to use it");
		
	}

	@Override
	public WiringInfo getWiringInfo(String applicationId, String deptId, String webAppName) {
		//getEntityManager(webAppName);
		System.out.println(applicationId+deptId);
		System.out.println(applicationId+deptId);
		//String qryStr ="select new application.model.WiringInfo(connectionType, phase, a.customerType,customerCategory, tariffCatCode, tariffCode) from WiringLandDetail a WHERE a.id.applicationId=:applicationId AND a.id.deptId=:deptId";
		String qryStr ="select new application.model.WiringInfo(a.connectionType, a.phase,a.customerCategory, a.tariffCatCode, a.tariffCode) from WiringLandDetail a WHERE a.id.applicationId=:applicationId AND a.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		//WiringLandDetailPK id=new WiringLandDetailPK();
		//id.setApplicationId(applicationId);
		//id.setDeptId(deptId);
		//System.out.println(getEntityManager(webAppName).find(WiringLandDetail.class, id));
		//System.out.println(applicationId+deptId);
		query.setParameter("applicationId", applicationId);
		query.setParameter("deptId", deptId);
		System.out.println(applicationId+deptId);
		WiringInfo wiringInfo = (WiringInfo) query.getSingleResult();
		return wiringInfo;
	}
	
	
		

}
