package masters.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import job.model.Pcestdmt;
import util.emSelect.EmSelector;
import masters.model.Bank;
import masters.model.ProvinceDetailMaster;


/**
 * Session Bean implementation class BankBean
 */
@Stateless
public class ProvinceDetailsMasterDao extends EmSelector implements ProvinceDetailsMasterRemote, ProvinceDetailsMasterLocal {
	
    /**
     * Default constructor. 
     */
    public ProvinceDetailsMasterDao() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void createProvinceDetailsMaster(
			ProvinceDetailMaster provinceDetailsMaster, String webAppName) {
		getEntityManager(webAppName).persist(provinceDetailsMaster);
		
		
	}

	@Override
	public void updateProvinceDetailsMaster(
			ProvinceDetailMaster provinceDetailsMaster, String webAppName) {
		getEntityManager(webAppName).merge(provinceDetailsMaster);
		
	}

	@Override
	public String findCodeName(String code, String deptId, String codeType,
			String webAppName) throws PersistenceException {
		String qryStr = "select p.codeName from ProvinceDetailMaster p where p.id.codeType=:codeType and p.id.code=:code and p.id.deptId=:deptId "; 
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("codeType", codeType);
		query.setParameter("code", code);
        List<String> list = query.getResultList();
        if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
		
      
	}
	@Override
	public String findCode(String codename, String deptId, String codeType,
			String webAppName) throws PersistenceException {
		String qryStr = "select p.id.code from ProvinceDetailMaster p where p.id.codeType=:codeType and p.codeName=:codename and p.id.deptId=:deptId "; 
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("codeType", codeType);
		query.setParameter("codename", codename);
        List<String> list = query.getResultList();
        if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
		
      
	}
	@Override
	public List<ProvinceDetailMaster> getAllCodeDetails(String codeType,
			String deptId, String webAppName) {
		String qryStr = "select p from ProvinceDetailMaster p where p.id.codeType=:codeType and  p.id.deptId=:deptId "; 
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("codeType", codeType);
		
        List<ProvinceDetailMaster> list = query.getResultList();
        if (list.isEmpty()){
			return null;
        }
        else{
        	return list;
        }
        
	}

	@Override
	public List<String> getAllCodeNames(String codeType, String deptId,
			String webAppName) {
		String qryStr = "select p.name from ProvinceDetailMaster p where p.id.codeType=:codeType and  p.id.deptId=:deptId "; 
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("codeType", codeType);
		
        List<String> list = query.getResultList();
        if (list.isEmpty()){
			return null;
        }
        else{
        	return list;
        }
	}
    
	@Override
	public List<String> getAllCodes(String codeType, String deptId,
			String webAppName) {
		String qryStr = "select p.id.code from ProvinceDetailMaster p where p.id.codeType=:codeType and  p.id.deptId=:deptId "; 
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("codeType", codeType);
		
        List<String> list = query.getResultList();
        if (list.isEmpty()){
			return null;
        }
        else{
        	return list;
        }
	}
	
	@Override
	public String getBranchType(String deptId,
			String webAppName) {
		String qryStr = "select b.id.branchCode from ProvinceBranch b where b.id.deptId=:deptId "; 
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		
        List<String> list = query.getResultList();
   
        
        if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        else 
        	return null;
       
        
	}

	@Override
	public String getJobTypeCode(String deptId,String branchCode,
			String webAppName) {
		String qryStr = "select b.id.jobTypeCode from ProvinceBranchJobType b where b.id.deptId=:deptId and  b.id.branchCode=:branchCode"; 
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("branchCode", branchCode);
        List<String> list = query.getResultList();
        if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        else 
        	return null;
	}
	
	@Override
	public List<String> getJobTypeByDeptId(String deptId,
			String webAppName) {
		String qryStr = "select b.jobType from ProvinceBranchJobType b where b.id.deptId=:deptId "; 
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		
        List<String> list = query.getResultList();
        if (list.isEmpty()){
			return null;
        }
        else{
        	return list;
        }
	}
	@Override
	public String getEstimateTypeByDeptId(String deptId,
			String webAppName) {
		String qryStr = "select b.estimateType from ProvinceBranchJobType b where b.id.deptId=:deptId "; 
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		
        List<String> list = query.getResultList();
        if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        else 
        	return null;
	}
	@Override
	public String getCommercialId(String deptId,
			String webAppName) {
		String qryStr = "select b.commercialId from ProvinceBranch b where b.id.deptId=:deptId "; 
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		
        List<String> list = query.getResultList();
        if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        else 
        	return null;
	}
}
