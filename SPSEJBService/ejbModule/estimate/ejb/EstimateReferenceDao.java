package estimate.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import estimate.model.EstimateReference;
import estimate.model.EstimateReferencePK;

import util.emSelect.EmSelector;


/**
 * Session Bean implementation class ApplicationReferenceDao
 */
@Stateless
public class EstimateReferenceDao extends EmSelector implements EstimateReferenceDaoRemote, EstimateReferenceDaoLocal {
	//@PersistenceContext
	//private EntityManager em;
	

	
	 /**
     * Default constructor. 
     */
    public EstimateReferenceDao() {
        // TODO Auto-generated constructor stub
    }
	
      
	@Override
	public EstimateReference findByAppId(EstimateReferencePK id, String webAppName) {
		//getEntityManager(webAppName);
		return getEntityManager(webAppName).find(EstimateReference.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EstimateReference> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr ="select a from EstimateReference a and a.status=1";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<EstimateReference> list = query.getResultList();
        return list;

	}
	
	@Override
	public void createEstimateReference(EstimateReference estimateReference, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(estimateReference);
		
	}

	@Override
	public void updateEstimateReference(EstimateReference estimateReference, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(estimateReference);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EstimateReference> findByStdEstimateNo(String stdestimateNo,String deptId, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		//String qryStr = "SELECT g FROM ApplicationReference g WHERE TRIM(applicationNo) = :applicationNo AND g.id.deptId = :deptId";
		String qryStr = "SELECT g FROM EstimateReference g WHERE TRIM(g.id.standardEstimateNo) = :stdestimateNo AND g.id.deptId = :deptId ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("stdestimateNo", stdestimateNo.trim());
		query.setParameter("deptId", deptId);
		List<EstimateReference> list = query.getResultList();		
		if (list.isEmpty())
			return null;
        else
        	return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EstimateReference> findByStdEstimateNoCom(String deptId, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		//String qryStr = "SELECT g FROM ApplicationReference g WHERE TRIM(applicationNo) = :applicationNo AND g.id.deptId = :deptId";
		String like = deptId+"%";
		String qryStr = "SELECT g FROM EstimateReference g WHERE g.id.deptId like :like ";
		System.out.println(qryStr);
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		
		query.setParameter("like", like);
		List<EstimateReference> list = query.getResultList();		
		if (list.isEmpty())
			return null;
        else
        	return list;
	}
	
	
		
	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa");
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String getNextEstimateNo(String applicationNoPrefix, String webAppName) {
		//getEntityManager(webAppName);
		String sequence=null;
    	String like=applicationNoPrefix+"%";
    	String strQuery="select WESTIMATE_NO from ESTIMATE_REFERENCEBS where WESTIMATE_NO like '"+like+"' ORDER BY 1 DESC ";
    	Query query=getEntityManager(webAppName).createNativeQuery(strQuery);//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
    	List<String> list=query.getResultList();
    	if (list.size()!=0){
    		sequence=query.getResultList().get(0).toString().trim();
    		System.out.println(sequence);
    		//sequence=sequence.substring(16);//total 20
    		sequence=sequence.substring(sequence.length()-4);//total 18
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
	public String getNextEstimateNoViaCommReference(String applicationNoPrefix, String webAppName) {
		//getEntityManager(webAppName);
		String sequence=null;
    	String like=applicationNoPrefix+"%";
    	String strQuery="select WESTIMATE_NO from ESTIMATE_REFERENCEBS where WESTIMATE_NO like '"+like+"' ORDER BY 1 DESC ";
    	Query query=getEntityManager(webAppName).createNativeQuery(strQuery);//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
    	List<String> list=query.getResultList();
    	if (list.size()!=0){
    		sequence=query.getResultList().get(0).toString().trim();
    		System.out.println(sequence);
    		//sequence=sequence.substring(16);//total 20
    		sequence=sequence.substring(18);//total 18
    		Integer i=Integer.parseInt(sequence)+1;
    		sequence=i.toString();
    		System.out.println(sequence);
    	}else{
    		sequence="1";
    		System.out.println(sequence);
    	}
    	if(sequence.length()==1)
    		return sequence;
    	else if (sequence.length()==2)
    		return sequence;
    	else if (Integer.parseInt(sequence) == 9999)
    		return "Error";
    	else return sequence;
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean checkEstimateNoExist(String stdestimateNo,String wtdestimateNo,String deptId,String webAppName) {
		String sequence=null;
    	String like=wtdestimateNo+"%";
    	String strQuery="select * from ESTIMATE_REFERENCEBS where WESTIMATE_NO like '"+like+"' and DEPT_ID='"+deptId+"' ORDER BY 1 DESC ";
    	Query query=getEntityManager(webAppName).createNativeQuery(strQuery);//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
    	List<String> list=query.getResultList();
    	if(list != null && list.size() > 0){
    		return true;
    	}else{
    		EstimateReferencePK id = new EstimateReferencePK();
    		id.setDeptId(deptId);
    		id.setStandardEstimateNo(stdestimateNo);
    		id.setWorkEstimateNo(wtdestimateNo);
    		EstimateReference refre = new EstimateReference();
    		refre.setId(id);
    		getEntityManager(webAppName).persist(refre);
    		return false;
    	}

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String getNextJobNo(String jobNoPrefix, String webAppName) {
		//getEntityManager(webAppName);
    	String sequence=null;
    	String like=jobNoPrefix+"%";
    	String strQuery="select a.projectno from EstimateReference a " +
    			"where a.projectno like :like ORDER BY 1 DESC";
    	Query query=getEntityManager(webAppName).createQuery(strQuery);//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
    	query.setParameter("like", like);
    	List<String> list=query.getResultList();
    	System.out.println(list);
    	if (list.size()!=0){
    		sequence=query.getResultList().get(0).toString().trim();
    		System.out.println(sequence);
    		sequence=sequence.substring(jobNoPrefix.length());//total 18 chars
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
	public EstimateReference findByJobNo(String jobNo, String deptId,String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "SELECT g FROM EstimateReference g WHERE TRIM(g.projectno) = :jobNo AND g.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("jobNo", jobNo);
		query.setParameter("deptId", deptId);
		List<EstimateReference> list = query.getResultList();		
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}


	@Override
	public int removEstimateReference(EstimateReference estimateReference,
			String webAppName) {
		String qryStr = "DELETE FROM EstimateReference g WHERE TRIM(g.id.workEstimateNo) = :wrEstimateNo and g.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("wrEstimateNo", estimateReference.getId().getWorkEstimateNo().trim());
		query.setParameter("deptId",estimateReference.getId().getDeptId());
		int status = query.executeUpdate();	
		return status;
		
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public EstimateReference findByWorkEstimateNo(String wrEstimateNo,String deptId, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		//String qryStr = "SELECT g FROM ApplicationReference g WHERE TRIM(applicationNo) = :applicationNo AND g.id.deptId = :deptId";
		String qryStr = "SELECT g FROM EstimateReference g WHERE TRIM(g.id.workEstimateNo) = :wrEstimateNo and g.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("wrEstimateNo", wrEstimateNo.trim());
		query.setParameter("deptId",deptId);
		List<EstimateReference> list = query.getResultList();		
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();

	}
	@SuppressWarnings("unchecked")
	@Override
	public List<EstimateReference> getWorkEstimatesBySEstimateNo(String sEstimateNo,String deptId,String webAppName) {
		//getEntityManager(webAppName);
		String qryStr ="select a from EstimateReference a WHERE TRIM(a.id.standardEstimateNo) = :sEstimateNo and a.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("sEstimateNo", sEstimateNo.trim());
		query.setParameter("deptId",deptId);
        List<EstimateReference> list = query.getResultList();
        if (list.isEmpty())
			return null;
        else 
        	return list;
        

	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EstimateReference> getActiveConstructionEstimates(String deptId,String webAppName) {
		//getEntityManager(webAppName);
		System.out.println("sql: ");
		String qryStr ="select a from EstimateReference a WHERE a.id.deptId=:deptId order by a.id.standardEstimateNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		//query.setParameter("sEstimateNo", sEstimateNo.trim());
		query.setParameter("deptId",deptId);
		System.out.println("sql: " +qryStr + "  select : " + query.toString());
        List<EstimateReference> list = query.getResultList();
        if (list.isEmpty())
			return null;
        else 
        	return list;
        

	}
	@SuppressWarnings("unchecked")
	@Override
	public String getNextFileRefNo(String fileRefPrefix,String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String sequence=null;
    	String like=fileRefPrefix+"%";
    	String strQuery="select FILE_REF from ESTIMATE_REFERENCEBS where DEPT_ID = '"+ deptId +"' and FILE_REF like '"+like+"' ORDER BY 1 DESC ";
    	Query query=getEntityManager(webAppName).createNativeQuery(strQuery);//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
    	List<String> list=query.getResultList();
    	//query.setParameter("deptId", deptId);
    	if (list.size()!=0){
    		sequence=query.getResultList().get(0).toString().trim();
    		System.out.println(sequence);
    		//sequence=sequence.substring(16);//total 20
    		sequence=sequence.substring(sequence.length()-4);//total 18
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
}
