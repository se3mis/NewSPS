package application.dao;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import application.model.WiringLandDetail;
import application.model.WiringLandDetailPK;

public class WiringLandDetailDao {
	private EntityManager em;

	public WiringLandDetailDao(EntityManagerFactory emf) {
		em = emf.createEntityManager();
	}

	public WiringLandDetailDao() {
		
	}

	private void createWiringLandDetail(WiringLandDetail wiringLandDetail) {
		em.getTransaction().begin();
		em.persist(wiringLandDetail);
		em.getTransaction().commit();

	}
	
	public void createWiringLandDetailNocommint(WiringLandDetail wiringLandDetail, EntityManager em) {
		em.persist(wiringLandDetail);
		
	}

	@SuppressWarnings("unchecked")
	private List<WiringLandDetail> getAll() {
		Query query = em.createQuery("select a from WiringLandDetail a");
		List<WiringLandDetail> list = query.getResultList();
		return list;
	}

	/*public WiringLandDetail findByAppId(long applicationId) {
		return em.find(WiringLandDetail.class, applicationId);

	}*/
	
	public WiringLandDetail findByAppId(WiringLandDetailPK id,EntityManager em) {
		return em.find(WiringLandDetail.class, id);

	}
	
	private void updateWiringLandDetail(WiringLandDetail wiringLandDetail)  {
        em.getTransaction().begin();
        em.merge(wiringLandDetail);
        em.getTransaction().commit();
    }
	public void updateWiringLandDetailNoCommit(WiringLandDetail wiringLandDetail, EntityManager em)  {
        em.merge(wiringLandDetail);
        
    }
	
	private void removeWiringLandDetail(WiringLandDetail wiringLandDetail)  {
        em.getTransaction().begin();
        em.remove(wiringLandDetail);
        em.getTransaction().commit();
    }
	
	@SuppressWarnings("unchecked")
	private void removeAll() {
        em.getTransaction().begin();
        Query query = em.createQuery("select a from WiringLandDetail a");
        List<WiringLandDetail> list = query.getResultList();
        Iterator<WiringLandDetail> it = list.iterator();
        while (it.hasNext()) {
        	WiringLandDetail application = it.next();
            em.remove(application);
        }
        em.getTransaction().commit();
    }
	
	 public void close() {
	        em.close();
	    }
	
	public static void main(String[] args){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SMC");
		EntityManager em = emf.createEntityManager();
		WiringLandDetailDao wiringLandDetailDao= new WiringLandDetailDao(emf);
		//Long longApplicationId= new Long("11");
		WiringLandDetail wiringLandDetail;
		WiringLandDetailPK  wiringLandDetailPK=new WiringLandDetailPK();
		wiringLandDetailPK.setApplicationId("510.20/SMNL/2010/26");
		wiringLandDetailPK.setDeptId("510.20");
		wiringLandDetail=wiringLandDetailDao.findByAppId(wiringLandDetailPK, em);
		System.out.println(wiringLandDetail);
		
		BigDecimal i = new BigDecimal("1"); 
        BigDecimal j = new BigDecimal("15"); 
        BigDecimal k = new BigDecimal("4"); 
        BigDecimal g = new BigDecimal("15"); 
        BigDecimal p = new BigDecimal("3"); 
		
		wiringLandDetailDao.removeWiringLandDetail(wiringLandDetail);
		System.out.println("Removed");
						       
        //wiringLandDetail =new WiringLandDetail(wiringLandDetailPK, "aa", "aa", "sas", i, "O", "Y", "N", "N", "1234", "123456", j, j, j, j, k, k, k, k, p, k, g , "15", "Y");
		wiringLandDetailDao.createWiringLandDetail(wiringLandDetail);
		System.out.println("Created");
		wiringLandDetail.setAssessmentNo("5678");
		wiringLandDetailDao.updateWiringLandDetail(wiringLandDetail);
		System.out.println("updated");
		System.out.println(wiringLandDetail);
		
	}

}
