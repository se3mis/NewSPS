package application.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import util.emSelect.EmSelector;

import application.model.ApplicationReference;
import application.model.ApplicationReferencePK;

/**
 * Session Bean implementation class ApplicationReferenceDao
 */
@Stateless
public class ApplicationReferenceDao extends EmSelector implements ApplicationReferenceDaoRemote, ApplicationReferenceDaoLocal {
	//@PersistenceContext
	//private EntityManager em;
	

	
	 /**
     * Default constructor. 
     */
    public ApplicationReferenceDao() {
        // TODO Auto-generated constructor stub
    }
	
      
	@Override
	public ApplicationReference findByAppId(ApplicationReferencePK id, String webAppName) {
		//getEntityManager(webAppName);
		return getEntityManager(webAppName).find(ApplicationReference.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ApplicationReference> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr ="select a from ApplicationReference a";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<ApplicationReference> list = query.getResultList();
        return list;

	}

	@Override
	public void createApplicationReference(ApplicationReference applicationReference, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(applicationReference);
		
	}

	@Override
	public void updateApplicationReference(ApplicationReference applicationReference, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(applicationReference);
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public ApplicationReference findByApplicationNo(String applicationNo, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		//String qryStr = "SELECT g FROM ApplicationReference g WHERE TRIM(applicationNo) = :applicationNo AND g.id.deptId = :deptId";
		String qryStr = "SELECT g FROM ApplicationReference g WHERE TRIM(applicationNo) = :applicationNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationNo", applicationNo.trim());
		List<ApplicationReference> list = query.getResultList();		
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();

	}

	@Override
	public void removeApplicationReference(ApplicationReference applicationReference, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).remove(applicationReference);
		
	}
	
	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa");
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String getNextApplicationNo(String applicationNoPrefix, String webAppName) {
		//getEntityManager(webAppName);
		String sequence=null;
		System.out.println("getNextApplicationNo");
    	String like=applicationNoPrefix+"%";
    	
    	String strQuery="select APPLICATION_NO from APPLICATION_REFERENCE where APPLICATION_NO like '"+like+"' ORDER BY 1 DESC";
    	
    	Query query=getEntityManager(webAppName).createNativeQuery(strQuery);//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
    	List<String> list=query.getResultList();
    	
    	if (list.size()!=0){
    		
    		sequence=query.getResultList().get(0).toString().trim();
    		
    		System.out.println(sequence);
    		
    		//sequence=sequence.substring(16);//total 20
    		//sequence=sequence.substring(14);//total 18
    		//sequence=sequence.substring(13);//total 18
    		//430.25/CNT/13/0073
    		System.out.println("char: " );
    		char deptStr =' ';
    		try{
    			deptStr = sequence.charAt(10);
    		}catch(Exception e){
    			System.out.println("char: " + e);
    		}
    		System.out.println("char: " + deptStr);
    		System.out.println("char: " + sequence);
    		if(deptStr == '/'){
    			sequence=sequence.substring(14);
    		}else{
    			System.out.println("charddd: ");
    			sequence=sequence.substring(13);
    		}
    		
    		
    		Integer i = new Integer(0);
    		
    		i=Integer.parseInt(sequence)+1;
    		
    		sequence=i.toString();
    		
    	}else{
    		sequence="0001";
    		System.out.println(sequence);
    	}
    	System.out.println("getNextApplicationNo : " + sequence);
    	
    	if(sequence.length()==1)
    		return "000"+sequence;
    	else if (sequence.length()==2)
    		return "00"+sequence;
    	else if (sequence.length()==3)
    		return "0"+sequence;
    	else return sequence;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String getNextJobNo(String jobNoPrefix, String webAppName) {
		//getEntityManager(webAppName);
    	String sequence=null;
    	String like=jobNoPrefix+"%";
    	String strQuery="select a.projectno from ApplicationReference a " +
    			"where a.projectno like :like ORDER BY 1 DESC";
    	Query query=getEntityManager(webAppName).createQuery(strQuery);//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
    	query.setParameter("like", like);
    	List<String> list=query.getResultList();
    	System.out.println(list);
    	if (list.size()!=0){
    		sequence=query.getResultList().get(0).toString().trim();
    		System.out.println(sequence);
    		sequence=sequence.substring(14);//total 18 chars
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
	@Override
	public ApplicationReference findByJobNo(String jobNo, String deptId,String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "SELECT g FROM ApplicationReference g WHERE TRIM(g.projectno) = :jobNo and g.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("jobNo", jobNo);
		query.setParameter("deptId", deptId);
		List<ApplicationReference> list = query.getResultList();		
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}
	/*@SuppressWarnings("unchecked")
	@Override
	public ApplicationReference findByApplicationId(String applicationId, String deptId,String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		//String qryStr = "SELECT g FROM ApplicationReference g WHERE TRIM(applicationNo) = :applicationNo AND g.id.deptId = :deptId";
		String qryStr = "SELECT g FROM ApplicationReference g WHERE TRIM(g.id.applicationId) = :applicationId AND TRIM(g.id.deptId) = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationId", applicationId.trim());
		query.setParameter("deptId", deptId.trim());
		List<ApplicationReference> list = query.getResultList();		
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();

	}*/
	
	

}
