package rebate.ejb;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import estimate.model.Pcestdtt;

import rebate.model.Sprebate;
import rebate.model.SprebatePK;
import util.common.Format;
import util.emSelect.EmSelector;

/**
 * Session Bean implementation class RebateBean
 */
@Stateless
public class RebateBean extends EmSelector implements RebateBeanRemote, RebateBeanLocal {

    
	private Format format = new Format();
	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}

	/**
     * Default constructor. 
     */
    public RebateBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void createRebate(Sprebate sprebate, String webAppName) {
		// TODO Auto-generated method stub
		getEntityManager(webAppName).persist(sprebate);
		
	}

	@Override
	public List<Sprebate> getAll(String webAppName) {
		// TODO Auto-generated method stub
		String qryStr ="select a from Sprebate a";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<Sprebate> list = query.getResultList();
        return list;
	}

	@Override
	public void updateRebate(Sprebate sprebate, String webAppName) {
		// TODO Auto-generated method stub
		getEntityManager(webAppName).merge(sprebate);
		
	}

	@Override
	public void removeRebate(Sprebate sprebate, String webAppName) {
		// TODO Auto-generated method stub
		System.out.println("populateSprebate Gayani :" + sprebate );
		sprebate = getEntityManager(webAppName).merge(sprebate);
		getEntityManager(webAppName).remove(sprebate);
	}

	@Override
	public void removeAll(String webAppName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Sprebate findById(SprebatePK id, String webAppName) throws PersistenceException {
		// TODO Auto-generated method stub
		return getEntityManager(webAppName).find(Sprebate.class, id);
	}

	@Override
	public List<Sprebate> findByEstimationNo(String estimateNo, String deptId,String webAppName) {
		// TODO Auto-generated method stub
		String qryStr = "SELECT g FROM Sprebate g WHERE TRIM(g.id.estimateNo) = :estimateNo AND g.id.deptId = :deptId order by g.id.resCd";
		//String qryStr = "SELECT g FROM Pcestdtt g WHERE TRIM(g.id.estimateNo) = :estimateNo AND g.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", estimateNo.trim());
		query.setParameter("deptId", deptId);
		//query.setParameter("revNo", new Long("2"));
		List<Sprebate> list = query.getResultList();		
		return list;
	}
	
	@Override
	public List<String> findByEstimationNoStr(String estimateNo, String deptId,String webAppName) {
		// TODO Auto-generated method stub
		String qryStr = "SELECT g FROM Sprebate g WHERE TRIM(g.id.estimateNo) = :estimateNo AND g.id.deptId = :deptId order by g.id.resCd";
		//String qryStr = "SELECT g FROM Pcestdtt g WHERE TRIM(g.id.estimateNo) = :estimateNo AND g.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", estimateNo.trim());
		query.setParameter("deptId", deptId);
		//query.setParameter("revNo", new Long("2"));
		List<String> list = query.getResultList();		
		return list;
	}


	@Override
	public Sprebate findBy3PK(String estimateNo, String deptId, String resCd,String webAppName) {
		// TODO Auto-generated method stub
		String qryStr = "SELECT g FROM Sprebate g WHERE TRIM(g.id.estimateNo) = :estimateNo AND g.id.deptId = :deptId AND TRIM(g.id.resCd) =:resCd and g.revNo=:revNo";
		//String qryStr = "SELECT g FROM Pcestdtt g WHERE TRIM(g.id.estimateNo) = :estimateNo AND g.id.deptId = :deptId AND TRIM(g.id.resCd) =:resCd";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", estimateNo.trim());
		query.setParameter("deptId", deptId);
		query.setParameter("resCd", resCd);
		query.setParameter("revNo", new BigDecimal("2"));
		List<Sprebate> list = query.getResultList();	
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}

	@Override
	public List<Sprebate> findByEstimationNo(String estimateNo,String webAppName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getSUMRebate(String estimateNo, String webAppName) {
		// TODO Auto-generated method stub
		String qryStr2 = "select sum(a.rebateCost) from Sprebate a where TRIM(a.id.estimateNo)=:estimateNo ";
		Query query2 = getEntityManager(webAppName).createQuery(qryStr2);
		query2.setParameter("estimateNo", estimateNo.trim());
		BigDecimal stdCost = (BigDecimal) query2.getSingleResult();
		//System.out.println(stdCost);
		return stdCost;
	}
	
	public String getNextDocNo(String applicationNoPrefix, String webAppName) {
		System.out.println("getNextApplicationNo 1");
		String sequence=null;
		System.out.println("getNextApplicationNo");
		Date obj = new Date();
		String year = getFormat().getYear();
		System.out.println("getNextApplicationNo " + year);
		String appPrefix = "20" +year+ "/";
		
    	String like=appPrefix+"%";
    	String strQuery="select DOC_NO from SPREBATE where DOC_NO like '"+like+"' ORDER BY 1 DESC";
    	
    	Query query=getEntityManager(webAppName).createNativeQuery(strQuery);//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
    	List<String> list=query.getResultList();
    	
    	if (list.size()!=0){
    		
    		sequence=query.getResultList().get(0).toString().trim();
    		
    		System.out.println(sequence);
    		
    		System.out.println("char1: " );
    		
    		System.out.println("char4: " + sequence);
    		
    		sequence=sequence.substring(5);
    		System.out.println("getNextApplicationNo 3  " + sequence);
    		
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
	
	

}
