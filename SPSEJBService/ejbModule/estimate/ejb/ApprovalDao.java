package estimate.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import util.emSelect.EmSelector;

import estimate.model.Approval;

/**
 * Session Bean implementation class ApprovalsDao
 */
@Stateless
public class ApprovalDao extends EmSelector implements ApprovalDaoRemote, ApprovalDaoLocal {

    /**
     * Default constructor. 
     */
    public ApprovalDao() {
        // TODO Auto-generated constructor stub
    }

	//@Override
	public void createApprovals(Approval approval, String webAppName) {
		getEntityManager(webAppName).persist(approval);
		
	}
	@Override
	public void createAutoIdApprovals(Approval approval, String webAppName) {
		String nextSq=getNextAppId(approval.getReferenceNo().trim()+"-", webAppName);
		approval.setApprovalId(approval.getReferenceNo().trim()+"-"+nextSq);
		getEntityManager(webAppName).persist(approval);
		
	}
	@Override
	public void createAutoIdSEstmateApprovals(Approval approval, String webAppName) {
		String nextSq=getNextAppIdForSEstimate(approval.getReferenceNo().trim()+"-", webAppName);
		approval.setApprovalId(approval.getReferenceNo().trim()+"-"+nextSq);
		getEntityManager(webAppName).persist(approval);
		
	}
	@Override
	public void createAutoIdConstructionEstimateApprovals(Approval approval, String webAppName) {
		String nextSq=getNextAppIdForConstructionEstimate(approval.getReferenceNo().trim()+"-", webAppName);
		approval.setApprovalId(approval.getReferenceNo().trim()+"-"+nextSq);
		getEntityManager(webAppName).persist(approval);
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Approval> findByReferenceNo(String referenceNo, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT  a  FROM Approval a WHERE a.referenceNo=:referenceNo order by  a.approvalId  ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("referenceNo", referenceNo.trim());
		List<Approval> list = query.getResultList();		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	//@Override
	public String getNextAppId(String approvalIdPrefix, String webAppName) {
		//getEntityManager(webAppName);
    	String sequence=null;
    	String like=approvalIdPrefix+"%";
    	String strQuery="select a.id.approvalId from Approval a " +
    			"where a.id.approvalId like :like ORDER BY 1 DESC";
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
	@SuppressWarnings("unchecked")
	//@Override
	public String getNextAppIdForSEstimate(String approvalIdPrefix, String webAppName) {
		//getEntityManager(webAppName);
    	String sequence=null;
    	String like=approvalIdPrefix+"%";
    	String strQuery="select a.id.approvalId from Approval a " +
    			"where a.id.approvalId like :like ORDER BY 1 DESC";
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
	@SuppressWarnings("unchecked")
	//@Override
	public String getNextAppIdForConstructionEstimate(String approvalIdPrefix, String webAppName) {
		//getEntityManager(webAppName);
    	String sequence=null;
    	String like=approvalIdPrefix+"%";
    	String strQuery="select a.id.approvalId from Approval a " +
    			"where a.id.approvalId like :like ORDER BY 1 DESC";
    	Query query=getEntityManager(webAppName).createQuery(strQuery);	//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
    	query.setParameter("like", like);
    	List<String> list=query.getResultList();
    	if (list.size()!=0){
    		sequence=query.getResultList().get(0).toString().trim();
    		System.out.println(sequence);
    		//*******************comment for SAN_TEST******************
    		//sequence=sequence.substring(20);//total 20 chars
    		//Integer i=Integer.parseInt(sequence)+1;
    		//sequence=i.toString();
    		
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
