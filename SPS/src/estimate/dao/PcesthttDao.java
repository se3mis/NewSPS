package estimate.dao;




import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import application.model.Application;
import costcenter.model.Gldeptm;
import estimate.model.Pcesthtt;
import estimate.model.PcesthttPK;
public class PcesthttDao {
	//@PersistenceContext(unitName="Tutorial")
	//  private EntityManager em; 

private EntityManager em;
private List<String> referenceDeleteList;
public List<String> getReferenceDeleteList() {
	return referenceDeleteList;
}
	public PcesthttDao() {
    }

    public PcesthttDao(EntityManagerFactory emf) {
        em = emf.createEntityManager();
    }
    
    private void createAPcesthtt(Pcesthtt pcesthtt) {
        em.getTransaction().begin();
        em.persist(pcesthtt);
        em.getTransaction().commit();
        
    }
    public void createAPcesthtt(Pcesthtt pcesthtt, EntityManager em) {
        em.persist(pcesthtt);
         
    }
    
    @SuppressWarnings("unchecked")
	private List<Pcesthtt> getAll() {
        Query query = em.createQuery("select a from Application a");
        List<Pcesthtt> list = query.getResultList();
        return list;
    }
    
    @SuppressWarnings("unchecked")
	public List<Pcesthtt> getAll(EntityManager em) {
        Query query = em.createQuery("select a from Application a");
        List<Pcesthtt> list = query.getResultList();
        return list;
    }
    
    private void updatePcesthtt(Pcesthtt pcesthtt)  {
        em.getTransaction().begin();
        em.merge(pcesthtt);
        em.getTransaction().commit();
    }
    
    public void updatePcesthtt(Pcesthtt pcesthtt,EntityManager em)  {
        em.merge(pcesthtt);
        
    }
    
    private void removePcesthtt(Pcesthtt pcesthtt)  {
        em.getTransaction().begin();
        em.remove(pcesthtt);
        em.getTransaction().commit();
    }
    
    public void removePcesthtt(Pcesthtt pcesthtt,EntityManager em)  {
        em.remove(pcesthtt);
        
    }
    
    @SuppressWarnings("unchecked")
	private void removeAll() {
        em.getTransaction().begin();
        Query query = em.createQuery("select a from Pcesthtt a");
        List<Application> list = query.getResultList();
        Iterator<Application> it = list.iterator();
        while (it.hasNext()) {
        	Application application = it.next();
            em.remove(application);
        }
        em.getTransaction().commit();
    }
    
    public Pcesthtt findById(PcesthttPK id,EntityManager em) throws PersistenceException{
        return em.find(Pcesthtt.class, id);
    }
    
    @SuppressWarnings("unchecked")
	public Pcesthtt findByEstNoNCCN(String estimateNo, String costCenterNo,EntityManager em) throws PersistenceException{
    	List<Pcesthtt> pcesthttList = em.createQuery("SELECT g FROM Pcesthtt g WHERE g.id.estimateNo = :estimateNo AND g.id.deptId = :deptId").setParameter("estimateNo", estimateNo).setParameter("deptId", costCenterNo).getResultList();
    	//pcesthttList.get(0);
    	return pcesthttList.get(0);
    }
    
    @SuppressWarnings("unchecked")
	public String findDeptName(String deptId,EntityManager em) throws PersistenceException{
        List<Gldeptm> gldeptmList = em.createQuery("SELECT g FROM Gldeptm g WHERE g.deptId = :deptId").setParameter("deptId", deptId).getResultList();
		gldeptmList.get(0).getDeptNm();
             
        return gldeptmList.get(0).getDeptNm().trim();
    }
    @SuppressWarnings("unchecked")
	private List<Pcesthtt> getJobinfo(BigDecimal status,EntityManager emf) {
		Query query = emf.createQuery("SELECT g FROM Pcesthtt g WHERE g.status = :status");
		query.setParameter("status", status);
		List<Pcesthtt> resultList = query.getResultList();
				
		return resultList;
	}
    
	@SuppressWarnings("unchecked")
	public List<Pcesthtt>  findByEstimationNo(String estimateNo,  EntityManager emf) 
	{
		String qryStr = "SELECT g FROM Pcesthtt g WHERE g.id.estimateNo = :estimateNo";
		Query query = emf.createQuery(qryStr);
		query.setParameter("estimateNo", estimateNo);
		List<Pcesthtt> list = query.getResultList();		
		
		return list;

	}
		
	@SuppressWarnings("unchecked")
	public List<String> getEstimateNoList(EntityManager emf) {
		Query query = emf.createQuery("SELECT g FROM Pcesthtt g order by g.id.estimateNo");
		List<Pcesthtt> noList = query.getResultList();
		List<String> estimateNumberList = new  ArrayList<String>();

		Iterator<Pcesthtt> it = noList.iterator();		
        while (it.hasNext()) 
        { 
        	estimateNumberList.add( it.next().getId().getEstimateNo());
        }
		return estimateNumberList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Pcesthtt> getJobDetails(EntityManager emf) {						
		return getJobinfo(new BigDecimal(75),emf);
	}
    public static void main(String[] args){
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("SMC");
    	EntityManager em = emf.createEntityManager();
    	PcesthttDao pcesthttDao=new PcesthttDao();
    	System.out.println(pcesthttDao.findByEstNoNCCN("M86081              ", "440.30", em));
    	
    	
    }

}
