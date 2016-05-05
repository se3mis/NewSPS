package job.ejb;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import util.common.Format;
import util.emSelect.EmSelector;

import job.model.BillDetail;
import job.model.BillDetailPK;
import job.model.Spestcnd;
import job.model.SpestcndPK;

/**
 * Session Bean implementation class SpestcndDao
 */
@Stateless
public class SpestcndDao extends EmSelector implements SpestcndDaoRemote, SpestcndDaoLocal {
	
	/**
     * Default constructor. 
     */
    public SpestcndDao() {
        // TODO Auto-generated constructor stub
    }
    
    
	
	@Override
	public void createSpestcnd(Spestcnd spestcnd, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(spestcnd);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spestcnd> getAll(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spestcnd a WHERE a.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		//Query query = getEntityManager(webAppName).createQuery("select a from Spestcnd a");
		query.setParameter("deptId", deptId);
		List<Spestcnd> list = query.getResultList();
	    return list;

	}

	@Override
	public void updateSpestcnd(Spestcnd spestcnd, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(spestcnd);
		
	}

	@Override
	public void removeSpestcnd(Spestcnd spestcnd, String webAppName) {
		//getEntityManager(webAppName);
		spestcnd=getEntityManager(webAppName).merge(spestcnd);
		getEntityManager(webAppName).remove(spestcnd);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spestcnd> findByContractorId(String contractorId, String deptId , String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spestcnd a WHERE a.id.contractorId = :contractorId AND a.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("contractorId", contractorId);
		query.setParameter("deptId", deptId);
		List<Spestcnd> list = query.getResultList();
	    return list;
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spestcnd> findByJobNo(String jobNo, String deptId, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spestcnd a WHERE TRIM(a.id.projectNo) = :jobNo AND a.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("jobNo", jobNo);
		query.setParameter("deptId", deptId);
		List<Spestcnd> list = query.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Spestcnd findByJobNo(String jobNo, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spestcnd a WHERE TRIM(a.id.projectNo) = :jobNo ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("jobNo", jobNo);
		List<Spestcnd> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public Spestcnd findById(SpestcndPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spestcnd a WHERE a.id.contractorId = :contractorId AND TRIM(a.id.projectNo) = :jobNo AND a.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("contractorId", id.getContractorId());
		query.setParameter("jobNo", id.getProjectNo());
		query.setParameter("deptId", id.getDeptId());
		List<Spestcnd> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spestcnd> getJobList(String deptId, String status, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spestcnd a WHERE a.id.deptId = :deptId AND a.status = :status order by a.id.projectNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		List<Spestcnd> list = query.getResultList();
		return list;
	}
	
	/*@SuppressWarnings("unchecked")
	@Override
	public List<String> getJobListNoByType(String deptId, String status, String catCd, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a.id.projectNo from Spestcnd a, Pcesthmt p WHERE a.id.projectNo=p.id.projectNo AND p.catCd=:catCd AND a.id.deptId = :deptId AND a.status = :status order by a.id.projectNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("catCd", catCd);
		query.setParameter("status", status);
		List<String> list = query.getResultList();
		return list;
	}*/
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spestcnd> getJobListByType(String deptId, String status, String applicationType , String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spestcnd a , Application b, Pcesthmt c WHERE a.id.deptId = :deptId AND a.status = :status AND a.id.projectNo=c.projectNo AND c.id.estimateNo=b.applicationNo AND b.applicationType = :applicationType order by a.id.projectNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		query.setParameter("applicationType", applicationType);
		List<Spestcnd> list = query.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getJobNoListByType(String deptId, String status, String applicationType, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select TRIM(a.id.projectNo) from Spestcnd a , Application b, Pcesthmt c WHERE a.id.deptId = :deptId AND a.status = :status AND a.id.projectNo=c.projectNo AND c.id.estimateNo=b.applicationNo AND b.applicationType = :applicationType order by a.id.projectNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		query.setParameter("applicationType", applicationType);
		List<String> list = query.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getJobNoListByType(String deptId, String applicationType, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select TRIM(a.id.projectNo) from Spestcnd a , Application b, Pcesthmt c WHERE a.id.deptId = :deptId AND (a.status = :statusA OR a.status = :statusE)  AND a.id.projectNo=c.projectNo AND c.id.estimateNo=b.applicationNo AND b.applicationType = :applicationType order by a.id.projectNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("statusA", "A");
		query.setParameter("statusE", "E");
		query.setParameter("applicationType", applicationType);
		List<String> list = query.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getJobNoListBySubType(String deptId, String status,String applicationType,  String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select TRIM(a.id.projectNo) from Spestcnd a , Application b, Pcesthmt c WHERE a.id.deptId = :deptId AND a.status = :status AND a.id.projectNo=c.projectNo AND TRIM(c.id.estimateNo)=b.applicationNo AND b.applicationType = :applicationType AND (b.applicationSubType = :subTypeR OR b.applicationSubType = :subTypeP OR b.applicationSubType = :subTypeC OR b.applicationSubType = :subTypeN OR b.applicationSubType = :subTypeC1 OR b.applicationSubType = :subTypeC2 OR b.applicationSubType = :subTypeC3) order by a.id.projectNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		query.setParameter("applicationType", applicationType);
		query.setParameter("subTypeP", "CP");
		query.setParameter("subTypeR", "CR"); 
		query.setParameter("subTypeC", "CC"); 
		query.setParameter("subTypeN", "PM"); 
		query.setParameter("subTypeC1", "C1"); 
		query.setParameter("subTypeC2", "C2");
		query.setParameter("subTypeC3", "C3"); 
		List<String> list = query.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getJobNoListBySubTypeCM_OT(String deptId, String status,String applicationType,  String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select TRIM(a.id.projectNo) from Spestcnd a , Application b, Pcesthmt c WHERE a.id.deptId = :deptId AND a.status = :status AND a.id.projectNo=c.projectNo AND c.id.estimateNo=b.applicationNo AND b.applicationType = :applicationType AND (b.applicationSubType = :subTypeM OR b.applicationSubType = :subTypeO ) order by a.id.projectNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		query.setParameter("applicationType", applicationType);
		query.setParameter("subTypeM", "CM");
		query.setParameter("subTypeO", "OT"); 
		List<String> list = query.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spestcnd> getJobList(String contractorId, String deptId, String status, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spestcnd a WHERE a.id.contractorId = :contractorId AND a.id.deptId = :deptId AND a.status = :status order by a.id.projectNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("contractorId", contractorId);
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		List<Spestcnd> list = query.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spestcnd> getJobListForLabour(String contractorId, String deptId, String status, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select distinct a from Spestcnd a, Spestlab b, Pcesthmt c WHERE a.id.projectNo=c.projectNo AND c.id.estimateNo=b.id.estimateNo AND a.id.contractorId = :contractorId AND a.id.deptId = :deptId AND a.status = :status  order by a.id.projectNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("contractorId", contractorId);
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		List<Spestcnd> list = query.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spestcnd> getJobList(String contractorId, String deptId, String status, String applicationType, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spestcnd a , Application b, Pcesthmt c WHERE a.id.contractorId = :contractorId AND a.id.deptId = :deptId AND a.id.projectNo=c.projectNo AND c.id.estimateNo=b.applicationNo AND b.applicationType = :applicationType order by a.id.projectNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("contractorId", contractorId);
		query.setParameter("deptId", deptId);
		query.setParameter("applicationType", applicationType);
		List<Spestcnd> list = query.getResultList();
		return list;
	}

	/*@SuppressWarnings("unchecked")
	@Override
	public List<Spestcnd> getJobList(String contractorId, String deptId, String allocate, String isBilled) throws PersistenceException {
		String qryStr = "select a from Spestcnd a WHERE a.id.contractorId = :contractorId AND a.id.deptId = :deptId AND a.allocate = :allocate AND a.isBilled = :isBilled";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("contractorId", contractorId);
		query.setParameter("deptId", deptId);
		query.setParameter("allocate", allocate);
		query.setParameter("isBilled", isBilled);
		List<Spestcnd> list = query.getResultList();
		return list;
	}*/
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllocatedJobNolist(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select TRIM(a.id.projectNo) from Spestcnd a WHERE a.id.deptId = :deptId order by a.id.projectNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<String> list = query.getResultList();
	    return list;
	}
	
	@Override
	public void updateIsExported(String jobNo,String deptId, String isExported, String webAppName) {
		//getEntityManager(webAppName);
		//String qryStr = "select TRIM(a.id.projectNo) from Spestcnd a WHERE a.id.deptId = :deptId";
		String   qryStr ="UPDATE Spestcnd s SET s.isExported =:isExported WHERE TRIM(s.id.projectNo) =:jobNo AND s.id.deptId =:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("isExported", isExported);
		query.setParameter("jobNo", jobNo);
		query.setParameter("deptId", deptId);
		query.executeUpdate();
	    
	}
	
	@Override
	public void updateError(String jobNo,String deptId, String errorDesc, String webAppName) {
		//getEntityManager(webAppName);
		//String qryStr = "select TRIM(a.id.projectNo) from Spestcnd a WHERE a.id.deptId = :deptId";
		String   qryStr ="UPDATE Spestcnd s SET s.isExported =:isExported, s.errorMsg=:errorDesc WHERE TRIM(s.id.projectNo) =:jobNo AND s.id.deptId =:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("isExported", "N");
		query.setParameter("jobNo", jobNo);
		query.setParameter("errorDesc", errorDesc);
		query.setParameter("deptId", deptId);
		query.executeUpdate();
	    
	}
	
	
	@Override
	public void createBillDetail(BillDetail billDetail, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(billDetail);
		
	}
	
	
	@Override
	public String createBillDetailAutoId(String[] selectedProjectNoList, String deptId, String userName, String billNoPrefix, String contractorId, String webAppName) {
		//getEntityManager(webAppName);
		Calendar calendar=Calendar.getInstance();
		Format format=new Format();
		String billNo=getNextBillNo(billNoPrefix, webAppName);
		for(int i=0;i<selectedProjectNoList.length;i++){
			System.out.println(selectedProjectNoList[i]);
			BillDetailPK id=new BillDetailPK();
			BillDetail billDetail=new BillDetail();
			id.setDeptId(deptId);
			id.setProjectNo(selectedProjectNoList[i].trim());
			billDetail.setId(id);
			billDetail.setBillNo(billNo);
			billDetail.setContractorId(contractorId);
			billDetail.setAddUser(userName);
			billDetail.setAddDate(calendar.getTime());
			billDetail.setAddTime(format.FormatTime());
			getEntityManager(webAppName).persist(billDetail);
			changeStatusSpestcnd(selectedProjectNoList[i].trim(), deptId, "B", webAppName);
		}
		return billNo;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public BillDetail getBillNoByProjectNo(String projectNo, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from BillDetail a WHERE TRIM(a.id.projectNo) = :projectNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		//Query query = getEntityManager(webAppName).createQuery("select a from Spestcnd a");
		query.setParameter("projectNo", projectNo);
		List<BillDetail> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BillDetail> getBillDetailByBillNo(String billNo, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from BillDetail a WHERE a.billNo = :billNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		//Query query = getEntityManager(webAppName).createQuery("select a from Spestcnd a");
		query.setParameter("billNo", billNo);
		List<BillDetail> list = query.getResultList();
	    return list;

	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getProjectNoListByBillNo(String billNo, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select TRIM(a.id.projectNo) from BillDetail a WHERE TRIM(a.billNo) = :billNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		//Query query = getEntityManager(webAppName).createQuery("select a from Spestcnd a");
		query.setParameter("billNo", billNo.trim());
		List<String> list = query.getResultList();
	    return list;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BillDetail> getBillDetail(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from BillDetail a WHERE a.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		//Query query = getEntityManager(webAppName).createQuery("select a from Spestcnd a");
		query.setParameter("deptId", deptId);
		List<BillDetail> list = query.getResultList();
	    return list;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BillDetail> getBillDetail(String deptId, Date fromDate, Date toDate,String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from BillDetail a WHERE a.id.deptId = :deptId AND a.addDate BETWEEN :fromDate AND :toDate";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		//Query query = getEntityManager(webAppName).createQuery("select a from Spestcnd a");
		query.setParameter("deptId", deptId);
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate); 
		List<BillDetail> list = query.getResultList();
	    return list;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getBillNoList(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select DISTINCT a.billNo from BillDetail a WHERE a.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		//Query query = getEntityManager(webAppName).createQuery("select a from Spestcnd a");
		query.setParameter("deptId", deptId);
		List<String> list = query.getResultList();
	    return list;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getBillNoList(String deptId, Date fromDate, Date toDate,String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select DISTINCT a.billNo from BillDetail a WHERE a.id.deptId = :deptId AND a.addDate BETWEEN :fromDate AND :toDate";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		//Query query = getEntityManager(webAppName).createQuery("select a from Spestcnd a");
		query.setParameter("deptId", deptId);
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate); 
		List<String> list = query.getResultList();
	    return list;

	}
	

	@Override
	public void updateBillDetail(BillDetail billDetail, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(billDetail);
		
	}

	@Override
	public void removeBillDetail(BillDetail billDetail, String deptId, String webAppName) {
		//getEntityManager(webAppName);
		billDetail=getEntityManager(webAppName).merge(billDetail);
		getEntityManager(webAppName).remove(billDetail);
		changeStatusSpestcnd(billDetail.getId().getProjectNo().trim(), deptId, "F", webAppName);
		
	}
	
	/*@Override
	public void removeBillDetailList(List<String> removeList, String deptId, String webAppName) {
		//getEntityManager(webAppName);
		if (removeList!=null){
			for(int i=0; i<=removeList.size()-1; i++){
				BillDetail billDetail=getBillNoByProjectNo(removeList.get(i).trim(), webAppName);
				billDetail=getEntityManager(webAppName).merge(billDetail);
				getEntityManager(webAppName).remove(billDetail);
				changeStatusSpestcnd(removeList.get(i).trim(), deptId, "F", webAppName);
			}
		}
		
		
	}*/
	
	
	@Override
	public void removeBillDetailListByBillNo(String billNo, String deptId, String webAppName) {
		//getEntityManager(webAppName);
		List<String> removeList=getProjectNoListByBillNo(billNo, webAppName);
		System.out.println(removeList);
		if (removeList!=null){
			for(int i=0; i<=removeList.size()-1; i++){
				BillDetail billDetail=getBillNoByProjectNo(removeList.get(i).trim(), webAppName);
				
				billDetail=getEntityManager(webAppName).merge(billDetail);
				getEntityManager(webAppName).remove(billDetail);
				changeStatusSpestcnd(removeList.get(i).trim(), deptId, "F", webAppName);
			}
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String getNextBillNo(String billNoPrefix, String webAppName) {
		//getEntityManager(webAppName);
		String sequence=null;
    	String like=billNoPrefix+"%";
    	String strQuery="select BILL_NO from BILL_DETAIL where BILL_NO like '"+like+"' ORDER BY 1 DESC";
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
    		return billNoPrefix+"000"+sequence;
    	else if (sequence.length()==2)
    		return billNoPrefix+"00"+sequence;
    	else if (sequence.length()==3)
    		return billNoPrefix+"0"+sequence;
    	else return billNoPrefix+sequence;
	}
	
	
	@Override
	public void changeStatusSpestcnd(String projectNo,String deptId, String status, String webAppName) {
		//getEntityManager(webAppName);
		//System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		String qryStr = "UPDATE Spestcnd g set g.status=:status WHERE TRIM(g.id.projectNo)= :projectNo AND g.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("status", status);
		query.setParameter("projectNo", projectNo);
		query.setParameter("deptId", deptId);
		query.executeUpdate();
		
	}
	
	
	

	

}
