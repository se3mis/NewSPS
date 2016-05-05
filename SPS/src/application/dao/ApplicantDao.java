package application.dao;

//import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


import application.model.Applicant;

public class ApplicantDao {
	
	private EntityManager em;
	
	public ApplicantDao() {
        
    }

    public ApplicantDao(EntityManagerFactory emf) {
        em = emf.createEntityManager();
    }
    
    public void createApplicant(Applicant applicant) {
       
        em.getTransaction().begin();
        em.persist(applicant);
        em.getTransaction().commit();
        
    }
    
    @SuppressWarnings("unchecked")
	public List<Applicant> getAll() {
        Query query = em.createQuery("select a from Applicant a");
        List<Applicant> list = query.getResultList();
        return list;
    }
    
   /* public Applicant findById(String id) {
        return em.find(Applicant.class, id);
    }*/
    
    public Applicant findById(String id,EntityManager em) {
        return em.find(Applicant.class, id);
    }
    
    public void updateApplicant(Applicant applicant)  {
        em.getTransaction().begin();
        em.merge(applicant);
        em.getTransaction().commit();
    }

    public void removeApplicant(Applicant applicant)  {
        em.getTransaction().begin();
        em.remove(applicant);
        em.getTransaction().commit();
    }
    
    @SuppressWarnings("unchecked")
	public void removeAll() {
        em.getTransaction().begin();
        Query query = em.createQuery("select a from ActorGenEntity a");
        List<Applicant> list = query.getResultList();
        Iterator<Applicant> it = list.iterator();
        while (it.hasNext()) {
            Applicant applicant = it.next();
            em.remove(applicant);
        }
        em.getTransaction().commit();
    }
    public void close() {
        em.close();
    }
    

	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SMC");    
        EntityManager em = emf.createEntityManager();
        //Applicant applicant =new Applicant(	getIdNumber(), getIdType(), getFirstName(), getLastName(), getStreetAddress(), getSuburb(), getCity(), getPreferefLanguage());
        //em.persist(applicant);
        //.em.find(11, 'NID');
        List<Applicant> listApplicants = em.createNativeQuery("select * from APPLICANT",Applicant.class).getResultList();
        
        for (Applicant applicant : listApplicants) {
        	System.out.println("id: " + applicant.getIdNo());
            System.out.println("id type    : " + applicant.getIdType());
            System.out.println("first name     : " + applicant.getFirstName());
        	System.out.println("last name   : " + applicant.getLastName());
            System.out.println("address     : " + applicant.getStreetAddress());
            System.out.println("---------------------------------------");
        } 
        
        List<Applicant> listApplicants2 = em.createNamedQuery("Applicant.findAll").getResultList();
        
        for (Applicant applicant : listApplicants2) {
        	System.out.println("id: " + applicant.getIdNo());
            System.out.println("id type    : " + applicant.getIdType());
            System.out.println("first name     : " + applicant.getFirstName());
        	System.out.println("last name   : " + applicant.getLastName());
            System.out.println("address     : " + applicant.getStreetAddress());
            System.out.println("***************************************");
        } 
        
List<Applicant> listApplicants3 = em.createQuery("select i from Applicant i").getResultList();
        
        for (Applicant applicant : listApplicants3) {
        	System.out.println("id: " + applicant.getIdNo());
            System.out.println("id type    : " + applicant.getIdType());
            System.out.println("first name     : " + applicant.getFirstName());
        	System.out.println("last name   : " + applicant.getLastName());
            System.out.println("address     : " + applicant.getStreetAddress());
            System.out.println("###############################");
        } 
        ApplicantDao applicantManager= new ApplicantDao(emf);
        List<Applicant> list=applicantManager.getAll();
        
        for (Applicant applicant : list) {
        	System.out.println("id: " + applicant.getIdNo());
            System.out.println("id type    : " + applicant.getIdType());
            System.out.println("first name     : " + applicant.getFirstName());
        	System.out.println("last name   : " + applicant.getLastName());
            System.out.println("address     : " + applicant.getStreetAddress());
            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%");
        } 
        System.out.println(applicantManager.findById("33", em));
        
        //Applicant applicant =new Applicant(	"33", "NIC", "Gayani", "Kumari", "Panadura", "Moratuwa", "Colombo", "sin");
        //BigDecimal i = new BigDecimal("1"); 
        //BigDecimal j = new BigDecimal("1542"); 
        //BigDecimal k = new BigDecimal("15244"); 
        
        //Applicant applicant =new Applicant("22","NIC", "Dileepa", "Waduge", "92", "Dalugama", "Kelaniya",null, null, null, "wadda", "si", "Y", "N", null, null, null, null, null, null);
      //Applicant applicant2 =new Applicant("22", "NIC", "Dileepa", "Waduge", "92", "Dalugama", "Kelaniya", "wadda") ;
        System.out.println("7777");
        //applicantManager.createApplicant(applicant);
        //System.out.println("8888");
        
        
        
	}

}
