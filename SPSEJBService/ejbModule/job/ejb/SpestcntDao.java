package job.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import util.emSelect.EmSelector;

import job.model.Spestcnt;
import job.model.SpestcntPK;


/**
 * Session Bean implementation class SpestcntDao
 */
@Stateless
public class SpestcntDao extends EmSelector implements SpestcntDaoRemote, SpestcntDaoLocal {
	
    /**
     * Default constructor. 
     */
    public SpestcntDao() {
        // TODO Auto-generated constructor stub
    }

    
    
	@Override
	public void createSpestcnt(Spestcnt spestcnt, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(spestcnt);
		
	}
	
	@Override
	public void createSpestcntAutoId(Spestcnt spestcnt, String webAppName) {
		//getEntityManager(webAppName);
		SpestcntPK id=new SpestcntPK();
		id.setContractorId(getNextContractorId(spestcnt.getId().getDeptId(), webAppName));
		id.setDeptId(spestcnt.getId().getDeptId());
		spestcnt.setId(id);
		getEntityManager(webAppName).persist(spestcnt);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String getNextContractorId(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String sequence=null;
    	//String like=applicationNoPrefix+"%";
    	String strQuery="select a.id.contractorId from Spestcnt a where a.id.deptId= :deptId ORDER BY 1 DESC";
    	Query query=getEntityManager(webAppName).createQuery(strQuery);//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
    	query.setParameter("deptId", deptId);
    	List<String> list=query.getResultList();
    	//System.out.println(list);
    	if (list.size()!=0){
    		sequence=query.getResultList().get(0).toString().trim();
    		System.out.println(sequence);
    		//sequence=sequence.substring(17);
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
	public List<Spestcnt> getAll(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spestcnt a WHERE a.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<Spestcnt> list = query.getResultList();
	    return list;
	}

	@Override
	public void updateSpestcnt(Spestcnt spestcnt, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(spestcnt);
		
	}

	@Override
	public void removeSpestcnt(Spestcnt spestcnt, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).remove(spestcnt);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");

		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Spestcnt findByContractorId(String contractorId,String deptId, String webAppName) throws PersistenceException {
		String qryStr = "select a from Spestcnt a WHERE a.id.contractorId = :contractorId and a.status=1 and a.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("contractorId", contractorId);
		query.setParameter("deptId", deptId);
		
		List<Spestcnt> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();

		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Spestcnt findById(SpestcntPK id, String webAppName) throws PersistenceException {
		String qryStr = "select a from Spestcnt a WHERE a.id.contractorId = :contractorId  and a.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("contractorId", id.getContractorId());
		query.setParameter("deptId", id.getDeptId());
		List<Spestcnt> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
		
	}
	


	@SuppressWarnings("unchecked")
	@Override
	public List<Spestcnt> getContractorByStatus(String deptId, String status, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spestcnt a WHERE a.id.deptId = :deptId and status=:status";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		List<Spestcnt> list = query.getResultList();
	    return list;
	}
	
	

}
