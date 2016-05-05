package estimate.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import util.emSelect.EmSelector;

import estimate.model.FundSource;
import estimate.model.Pcfunddm;

/**
 * Session Bean implementation class PcfunddmDao
 */
@Stateless
public class PcfunddmDao implements PcfunddmDaoRemote, PcfunddmDaoLocal {
	@PersistenceContext(unitName="SMCTesting")
	private EntityManager emSMCTesting;
	@PersistenceContext(unitName="SMCR1")
	private EntityManager emSMCR1;
	@PersistenceContext(unitName="SMCR2")
	private EntityManager emSMCR2;
	@PersistenceContext(unitName="SMCR3")
	private EntityManager emSMCR3;
	@PersistenceContext(unitName="SMCR4")
	private EntityManager emSMCR4;
	@PersistenceContext(unitName="SMCAM")
	private EntityManager emSMCAM;
	public EntityManager getEntityManager(String webAppName){
		if (webAppName.equals("R1"))
				return emSMCR1;	
		else if (webAppName.equals("R2"))
			return emSMCR2;
		else if (webAppName.equals("R3"))
			return emSMCR3;
		else if (webAppName.equals("R4"))
			return emSMCR4;
		else if (webAppName.equals("AM"))
			return emSMCAM;
		else return emSMCTesting;	
	}
    /**
     * Default constructor. 
     */
    public PcfunddmDao() {
        // TODO Auto-generated constructor stub
    }
    
   

	@SuppressWarnings("unchecked")
	@Override
	public List<Pcfunddm> getFundSourceList(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT dm FROM Pcfunddm dm WHERE dm.id.deptId =:deptId"; 
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		
		List<Pcfunddm> list = query.getResultList();
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getApplicationTypes(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		//String qryStr = "SELECT new estimate.model.FundSource(TRIM(dm.id.fundSource),TRIM(dm.id.fundId), TRIM(h.name)) FROM Pcfunddm dm WHERE dm.id.deptId =:deptId and status =2 and fund_source IS NOT NULL ORDER BY fund_source ASC"; 
		String qryStr = "SELECT TRIM(dm.id.fundSource) FROM Pcfunddm dm WHERE dm.id.deptId =:deptId and dm.status =2 and dm.id.fundSource IS NOT NULL ORDER BY dm.id.fundSource ASC"; 
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		//query.setParameter("status", 2);
		List<String> list = query.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getFundSources(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT TRIM(dm.id.fundSource) FROM Pcfunddm dm WHERE TRIM(dm.id.deptId) =:deptId and status=2 and dm.id.fundSource is not null ORDER By dm.id.fundSource ASC"; 
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId.trim());

		List<String> list = query.getResultList();
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getFundIds(String deptId,String foundsource, String webAppName) {
		//getEntityManager(webAppName);
		StringBuffer buff = new StringBuffer();
		buff.append("SELECT TRIM(dm.id.fundId) FROM Pcfunddm dm WHERE dm.id.deptId =:deptId");
		if(foundsource != null){
			buff.append(" and TRIM(dm.id.fundSource)=:fundSource");
		}
		
		Query query = getEntityManager(webAppName).createQuery(buff.toString());
		query.setParameter("deptId", deptId);
		if(foundsource != null){
			query.setParameter("fundSource", foundsource);
		}
		List<String> list = query.getResultList();
		return list;
	}
	
}
