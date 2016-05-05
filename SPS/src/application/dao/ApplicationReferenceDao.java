package application.dao;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import application.model.ApplicationReference;
import application.model.ApplicationReferencePK;

public class ApplicationReferenceDao {
	private EntityManager em;

    public ApplicationReferenceDao(EntityManagerFactory emf) {
        em = emf.createEntityManager();
    }
    
    public ApplicationReferenceDao() {
		
	}

	private void createApplicationReference(ApplicationReference applicationReference) {
        
        em.getTransaction().begin();
        em.persist(applicationReference);
        em.getTransaction().commit();
        
    }
    public void createApplicationReferenceNoCommit(ApplicationReference applicationReference, EntityManager em) {
        em.persist(applicationReference);
         
    }
    
    @SuppressWarnings("unchecked")
	private List<ApplicationReference> getAll() {
        Query query = em.createQuery("select a from ApplicationReference a");
        List<ApplicationReference> list = query.getResultList();
        return list;
    }
    /*public ApplicationReference findByAppId(long applicationId) {
        return em.find(ApplicationReference.class, applicationId);
        
    }*/
    
    public ApplicationReference findByAppId(ApplicationReferencePK id, EntityManager em) {
        return em.find(ApplicationReference.class, id);
        
    }
    public ApplicationReference findByAppRefNo(long applicationNo) {
        return em.find(ApplicationReference.class, applicationNo);
        
    }
    private void updateApplicationReference(ApplicationReference applicationReference)  {
        em.getTransaction().begin();
        em.merge(applicationReference);
        em.getTransaction().commit();
    }
    
    public void updateApplicationReferenceNoCommit(ApplicationReference applicationReference,EntityManager em)  {
        em.merge(applicationReference);
        
    }
    
    private void removeApplicationReference(ApplicationReference applicationReference)  {
        em.getTransaction().begin();
        em.remove(applicationReference);
        em.getTransaction().commit();
    }
    
    public void removeApplicationReferenceNoCommit(ApplicationReference applicationReference,EntityManager em)  {
        em.remove(applicationReference);
        
    }
    @SuppressWarnings("unchecked")
	private void removeAll() {
        em.getTransaction().begin();
        Query query = em.createQuery("select a from ApplicationReference a");
        List<ApplicationReference> list = query.getResultList();
        Iterator<ApplicationReference> it = list.iterator();
        while (it.hasNext()) {
        	ApplicationReference applicationReference = it.next();
            em.remove(applicationReference);
        }
        em.getTransaction().commit();
    }
    
    @SuppressWarnings("unchecked")
	public String getNextApplicationNo(String applicationNoPrefix, EntityManager em) {
    	String sequence=null;
    	String like=applicationNoPrefix+"%";
    	String strQuery="select APPLICATION_NO from APPLICATION_REFERENCE where APPLICATION_NO like '"+like+"' ORDER BY 1 DESC";
    	System.out.println(strQuery);
    	Query query=em.createNativeQuery(strQuery);//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
    	List<String> list=query.getResultList();
    	if (list.size()!=0){
    		sequence=query.getResultList().get(0).toString().trim();
    		System.out.println(sequence);
    		sequence=sequence.substring(17);
    		Integer i=Integer.parseInt(sequence)+1;
    		sequence=i.toString();
    		System.out.println(sequence);
    	}else{
    		sequence="0001";
    		System.out.println(sequence);
    	}
    	if(sequence.length()==1)
    		return "000"+sequence;
    	else if (sequence.length()==2)
    		return "00"+sequence;
    	else if (sequence.length()==3)
    		return "0"+sequence;
    	else return sequence;
	}
    
    public void close() {
        em.close();
    }
    
    public static void main(String[] args){
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("SMC");
    	EntityManager em = emf.createEntityManager();
    	ApplicationReferenceDao applicationReferenceDao=new ApplicationReferenceDao(emf);
    	//Long l= new Long("4554");
    	ApplicationReference applicationReference=new ApplicationReference();
    	ApplicationReferencePK applicationReferencePK= new ApplicationReferencePK();
    	applicationReferencePK.setApplicationId("510.20/SMNL/2010/26");
    	applicationReferencePK.setDeptId("510.20");
    	applicationReference=applicationReferenceDao.findByAppId(applicationReferencePK,em);
    	System.out.println(applicationReference);
    	applicationReferenceDao.getAll();
    	System.out.println(applicationReferenceDao.getNextApplicationNo("510.20/SMNL/2010/", em));
    	System.out.println(applicationReferenceDao.getNextApplicationNo("510.20/SMNL/2010/", em));
    }

}
