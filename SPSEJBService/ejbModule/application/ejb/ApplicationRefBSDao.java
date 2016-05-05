package application.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import util.emSelect.EmSelector;

import application.model.Application;
import application.model.ApplicationRefBS;
import application.model.ApplicationRefBSPK;
import application.model.ApplicationReference;
import application.model.ApplicationReferencePK;

/**
 * Session Bean implementation class ApplicationReferenceDao
 */
@Stateless
public class ApplicationRefBSDao extends EmSelector implements ApplicationRefBSDaoRemote, ApplicationRefBSDaoLocal {
	//@PersistenceContext
	//private EntityManager em;
	

	
	 /**
     * Default constructor. 
     */
    public ApplicationRefBSDao() {
        // TODO Auto-generated constructor stub
    }
	
      
	@Override
	public ApplicationRefBS findByAppId(ApplicationRefBSPK id, String webAppName) {
		//getEntityManager(webAppName);
		return getEntityManager(webAppName).find(ApplicationRefBS.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ApplicationRefBS> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr ="select a from ApplicationRefBS a";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<ApplicationRefBS> list = query.getResultList();
        return list;

	}

	@Override
	public void createApplicationReference(ApplicationRefBS applicationReference, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(applicationReference);
		
	}

	@Override
	public void updateApplicationReference(ApplicationRefBS applicationReference, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(applicationReference);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public ApplicationRefBS findByApplicationNo(String applicationNo,String deptid, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		//String qryStr = "SELECT g FROM ApplicationReference g WHERE TRIM(applicationNo) = :applicationNo AND g.id.deptId = :deptId";
		String qryStr = "SELECT g FROM ApplicationRefBS g WHERE TRIM(g.id.applicationNo) = :applicationNo";
		
		if(deptid != null){
			qryStr = qryStr +" AND TRIM(g.id.deptId) = :deptId";
		}
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		
		query.setParameter("applicationNo", applicationNo.trim());
		if(deptid != null){
			query.setParameter("deptId", deptid);
		}
		
		List<ApplicationRefBS> list = query.getResultList();		
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();

	}

	@Override
	public void removeApplicationReference(ApplicationRefBS applicationReference, String webAppName) {
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
    	String like=applicationNoPrefix+"%";
    	String strQuery="select APPLICATION_NO from APPLICATION_REFBS where APPLICATION_NO like '"+like+"' ORDER BY 1 DESC";
    	Query query=getEntityManager(webAppName).createNativeQuery(strQuery);//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
    	List<String> list=query.getResultList();
    	if (list.size()!=0){
    		sequence=query.getResultList().get(0).toString().trim();
    		System.out.println(sequence);
    		//sequence=sequence.substring(16);//total 20
    		sequence=sequence.substring(14);//total 18
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
	public String getNextJobNo(String jobNoPrefix, String webAppName) {
		//getEntityManager(webAppName);
    	String sequence=null;
    	String like=jobNoPrefix+"%";
    	String strQuery="select a.projectno from ApplicationRefBS a " +
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
	public ApplicationRefBS findByJobNo(String jobNo, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "SELECT g FROM ApplicationRefBS g WHERE TRIM(projectNo) = :jobNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("jobNo", jobNo);
		List<ApplicationRefBS> list = query.getResultList();		
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int updateEstimationNo(String applicationNo, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "UPDATE ApplicationRefBS SET ESTIMATION_NO=:applicationNo WHERE APPLICATION_NO = :applicationNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationNo", applicationNo);

		int status=query.executeUpdate();
		return status;
	}


	

}
