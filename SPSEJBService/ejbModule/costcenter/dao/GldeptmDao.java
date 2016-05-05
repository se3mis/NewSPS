package costcenter.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import costcenter.model.Gldeptm;
public class GldeptmDao {
	@PersistenceContext(unitName="Tutorial")
	 private EntityManager em; 

//private EntityManager em;
	
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

    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBsmc");
    	EntityManager em = emf.createEntityManager();
    	GldeptmDao gldeptmDao=new GldeptmDao();
    	System.out.println(gldeptmDao.findDeptName("440.00", em).trim().length());
    	System.out.println(gldeptmDao.findDeptName("440.00", em).trim());
    	//System.out.println(gldeptmDao.findApplicationType("440.00").trim());
    	
    }
    @SuppressWarnings("unchecked")
	public String findDepartmentId(String deptId,EntityManager em) throws PersistenceException{
        List<Gldeptm> gldeptmList = em.createQuery("SELECT g FROM Gldeptm g WHERE g.comp_id in (select c from glcompm where c.parent_id in(select gl  from gldeptm where gl.dept_id=:deptId)) order by g.dept_id").setParameter("deptId", deptId).getResultList();
	
        gldeptmList.get(0).getDeptId();
             
        return gldeptmList.get(0).getDeptId().trim();
    }
    @SuppressWarnings("unchecked")
	public List<String> getPostDepartmentIds(String deptId,EntityManager em) throws PersistenceException{
        List<String> gldeptmList = em.createQuery("SELECT g.deptId FROM Gldeptm g WHERE g.deptId like :deptId  order by g.dept_id").setParameter("deptId", deptId+"%").getResultList();
	
       
             
        return gldeptmList;
    }
}

