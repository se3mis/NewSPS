package estimate.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.emSelect.EmSelector;
import estimate.model.AllocationSummary;
import estimate.model.Approval;
import estimate.model.Pcestdtt;
import estimate.model.SpNormsGroup;
import estimate.model.SpPegInfo;
import estimate.model.Spnorms;
import estimate.model.SpnormsPK;


/**
 * Session Bean implementation class SpugcstmDao
 */
@Stateless
public class AllocationSummaryDao extends EmSelector implements AllocationSummaryDaoLocal, AllocationSummaryDaoRemote {
	
    /**
     * Default constructor. 
     */
    public AllocationSummaryDao() {
        // TODO Auto-generated constructor stub
    }

   

	@SuppressWarnings("unchecked")
	@Override
	public List<AllocationSummary> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from AllocationSummary a order by a.allocationSummaryId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<AllocationSummary> list = query.getResultList();
        return list;
	}
	
	
	@Override
	public void removeAllocationSummary(AllocationSummary allocationSummary, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).remove(allocationSummary);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public AllocationSummary findById(AllocationSummary id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		return getEntityManager(webAppName).find(AllocationSummary.class, id);
	}



	@Override
	public void createAllocationSummary(AllocationSummary allocationSummary, String webAppName) {
		// TODO Auto-generated method stub
		
		getEntityManager(webAppName).persist(allocationSummary);
	}



	@Override
	public void updateAllocationSummary(AllocationSummary allocationSummary, String webAppName) {
		// TODO Auto-generated method stub
		getEntityManager(webAppName).merge(allocationSummary);
	}
	
	@Override
	public void createAutoIdAllocationSummary(AllocationSummary allocationSummary, String webAppName) {
		String nextSq=getNextAllocationId(allocationSummary.getConstructionReference().trim()+"-", webAppName);
		allocationSummary.setAllocationSummaryId(allocationSummary.getEstimateNo().trim()+"-"+nextSq);
		getEntityManager(webAppName).persist(allocationSummary);
		
	}
	
	@SuppressWarnings("unchecked")
	//@Override
	public String getNextAllocationId(String approvalIdPrefix, String webAppName) {
		//getEntityManager(webAppName);
    	String sequence=null;
    	String like=approvalIdPrefix+"%";
    	String strQuery="select a.id.allocationSummaryId from AllocationSummary a " +
    			"where a.id.allocationSummaryId like :like ORDER BY 1 DESC";
    	Query query=getEntityManager(webAppName).createQuery(strQuery);	//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
    	query.setParameter("like", like);
    	List<String> list=query.getResultList();
    	if (list.size()!=0){
    		sequence=query.getResultList().get(0).toString().trim();
    		System.out.println(sequence);
    		sequence=sequence.substring(approvalIdPrefix.length());//total 20 chars
    		
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
