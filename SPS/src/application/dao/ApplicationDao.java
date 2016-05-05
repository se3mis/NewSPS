package application.dao;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import application.model.Application;
import application.model.ApplicationPK;

public class ApplicationDao {
	private EntityManager em;

    public ApplicationDao(EntityManagerFactory emf) {
        em = emf.createEntityManager();
    }
    public ApplicationDao() {
		
	}
    private void createApplication(Application application) {
        
        em.getTransaction().begin();
        em.persist(application);
        em.getTransaction().commit();
        
    }
    public void createApplicationNoCommit(Application application, EntityManager em) {
        em.persist(application);
         
    }
    
     
    public String getNextID(EntityManager em) {
    	Query query=em.createNativeQuery("SELECT APPLICATIONS_SEQ.NEXTVAL FROM DUAL");//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
    	String sequence=query.getResultList().get(0).toString();
    	if(sequence.length()==1)
    		return "000"+sequence;
    	else if (sequence.length()==2)
    		return "00"+sequence;
    	else if (sequence.length()==3)
    		return "0"+sequence;
    	else return sequence;
	}
    public String getNextID() {
    	Query query=em.createNativeQuery("SELECT APPLICATIONS_SEQ.NEXTVAL FROM DUAL");//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
    	String sequence=query.getResultList().get(0).toString();
    	//System.out.println("4444444444444444444444444455555555556666666"+ query.getResultList().get(0));
    	if(sequence.length()==1)
    		return "000"+sequence;
    	else if (sequence.length()==2)
    		return "00"+sequence;
    	else if (sequence.length()==3)
    		return "0"+sequence;
    	else return sequence;
	}
    
    @SuppressWarnings("unchecked")
	public String getNextAppId(String cosCenterNo, String sysName, String ApplicationName, String year, EntityManager em) {
    	String sequence=null;
    	String like=cosCenterNo+"/"+sysName+ApplicationName+"/"+year+"/%";
    	String strQuery="select APPLICATION_ID from APPLICATIONS where APPLICATION_ID like '"+like+"' ORDER BY 1 DESC";
    	//System.out.println(strQuery);
    	Query query=em.createNativeQuery(strQuery);//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
    	List list=query.getResultList();
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
    
    @SuppressWarnings("unchecked")
	public String getNextAppId(String applicationIdFormatPrefix, EntityManager em) {
    	String sequence=null;
    	String like=applicationIdFormatPrefix+"%";
    	String strQuery="select APPLICATION_ID from APPLICATIONS where APPLICATION_ID like '"+like+"' ORDER BY 1 DESC";
    	System.out.println(strQuery);
    	Query query=em.createNativeQuery(strQuery);//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
    	List list=query.getResultList();
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
    
    
    
    @SuppressWarnings("unchecked")
	private List<Application> getAll() {
        Query query = em.createQuery("select a from Application a");
        List<Application> list = query.getResultList();
        return list;
    }
    
    @SuppressWarnings("unchecked")
	public List<Application> getAll(EntityManager em) {
        Query query = em.createQuery("select a from Application a");
        List<Application> list = query.getResultList();
        return list;
    }
    
   /* public Application findByAppId(long applicationId) {
        return em.find(Application.class, applicationId);
        
    }*/
    
    public Application findByAppId(ApplicationPK id, EntityManager em) throws PersistenceException {
        return em.find(Application.class, id);
        
    }
    
    @SuppressWarnings("unchecked")
	public Application findByApplicationNo(String applicationNo, EntityManager em) throws PersistenceException {
    	//Query query = em.createQuery("select a from Application a");
    	List<Application> listPcesthtt2 = em.createQuery("select i from Application  i where i.applicationNo=:applicationNo").setParameter("applicationNo", applicationNo).getResultList();
    	return listPcesthtt2.get(0);
        
    }
    
    private void updateApplicant(Application application)  {
        em.getTransaction().begin();
        em.merge(application);
        em.getTransaction().commit();
    }
    
    public void updateApplicantNoCommit(Application application,EntityManager em)  {
        em.merge(application);
        
    }

    private void removeApplicant(Application application)  {
        em.getTransaction().begin();
        em.remove(application);
        em.getTransaction().commit();
    }
    
    @SuppressWarnings("unchecked")
	private void removeAll() {
        em.getTransaction().begin();
        Query query = em.createQuery("select a from Application a");
        List<Application> list = query.getResultList();
        Iterator<Application> it = list.iterator();
        while (it.hasNext()) {
        	Application application = it.next();
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
		int i=2;
		String x =i==2? "equal":"not";
		System.out.println(x);
        ApplicationDao applicationDao=new  ApplicationDao(emf);
		//Long l= new Long("11");
        //System.out.println(applicationDao.findByAppId("510.20/SMNL/2010/26",em));
        System.out.println(applicationDao.getNextAppId("510.20", "SM","NL","2010",em));
        System.out.println(applicationDao.getAll());
        System.out.println(applicationDao.findByApplicationNo("510.20/SMNL/2010/0002",em));
        
	}

}