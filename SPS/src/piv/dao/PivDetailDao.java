package piv.dao;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import piv.model.PivDetail;
import piv.model.PivDetailPK;





public class PivDetailDao {
	private EntityManager em;
	
	public PivDetailDao() {
    }

    public PivDetailDao(EntityManagerFactory emf) {
        em = emf.createEntityManager();
    }
    
    private void createPiv(PivDetail pivDetail ) {
       
        em.getTransaction().begin();
        em.persist(pivDetail);
        em.getTransaction().commit();
        
    }
    
    public void createPivNoCommit(PivDetail pivDetail,EntityManager em ) {
        em.persist(pivDetail);
                
    }
    
    @SuppressWarnings("unchecked")
	private List<PivDetail> getAll() {
        Query query = em.createQuery("select a from PivDetail a");
        List<PivDetail> list = query.getResultList();
        return list;
    }
    
    @SuppressWarnings("unchecked")
	public List<PivDetail> getAll(EntityManager em) {
        Query query = em.createQuery("select a from PivDetail a");
        List<PivDetail> list = query.getResultList();
        return list;
    }
    
    /*public PivDetail findById(PivDetailPK id) {
        return em.find(PivDetail.class, id);
    }*/
    
    public PivDetail findById(PivDetailPK id,EntityManager em) throws PersistenceException{
        return em.find(PivDetail.class, id);
    }
    
    private void updatePivDetail(PivDetail pivDetail)  {
        em.getTransaction().begin();
        em.merge(pivDetail);
        em.getTransaction().commit();
    }
    
    public void updatePivDetailNoCommit(PivDetail pivDetail,EntityManager em)  {
        em.merge(pivDetail);
        
    }

    private void removePivDetail(PivDetail pivDetail)  {
        em.getTransaction().begin();
        em.remove(pivDetail);
        em.getTransaction().commit();
    }
    
    public void removePivDetailNoCommit(PivDetail pivDetail,EntityManager em)  {
        em.remove(pivDetail);
    }
    
    @SuppressWarnings("unchecked")
	private void removeAll() {
        em.getTransaction().begin();
        Query query = em.createQuery("select a from PivDetail a");
        List<PivDetail> list = query.getResultList();
        Iterator<PivDetail> it = list.iterator();
        while (it.hasNext()) {
        	PivDetail pivDetail = it.next();
            em.remove(pivDetail);
        }
        em.getTransaction().commit();
    }
    public static void main(String[] args){
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("SMC");    
        EntityManager em = emf.createEntityManager();
        PivDetailDao pivDetailDao =new PivDetailDao(emf);
        //PivDetail pivDetail =new PivDetail();
        PivDetailPK pivDetailPK =new PivDetailPK();
        pivDetailPK.setDeptId("510.20");
        //Long i= new Long("11");
        pivDetailPK.setPivNo("2");
        System.out.println("find  "+pivDetailDao.findById(pivDetailPK,em));
        System.out.println("getALL  "+pivDetailDao.getAll());
    }
}
