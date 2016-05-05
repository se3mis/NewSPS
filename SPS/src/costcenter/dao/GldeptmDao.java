package costcenter.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import costcenter.model.Gldeptm;
public class GldeptmDao {
	//@PersistenceContext(unitName="Tutorial")
	//  private EntityManager em; 

private EntityManager em;
	
	public GldeptmDao() {
    }

    public GldeptmDao(EntityManagerFactory emf) {
        em = emf.createEntityManager();
    }
    public Gldeptm findById(String deptId,EntityManager em) throws PersistenceException{
        return em.find(Gldeptm.class, deptId);
    }
    @SuppressWarnings("unchecked")
	public String findDeptName(String deptId,EntityManager em) throws PersistenceException{
        List<Gldeptm> gldeptmList = em.createQuery("SELECT g FROM Gldeptm g WHERE g.deptId = :deptId").setParameter("deptId", deptId).getResultList();
		
		
        gldeptmList.get(0).getDeptNm();
             
        return gldeptmList.get(0).getDeptNm().trim();
    }
    
    public static void main(String[] args){
    	//@PersistenceContext  EntityManager em;

    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("SMC");
    	EntityManager em = emf.createEntityManager();
    	GldeptmDao gldeptmDao=new GldeptmDao();
    	System.out.println(gldeptmDao.findDeptName("440.00", em).trim().length());
    	System.out.println(gldeptmDao.findDeptName("440.00", em).trim());
    	
    }

}
