package costcenter.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;



/**
 * Session Bean implementation class DeptmapmDao
 */
@Stateless
public class DeptmapmDao implements DeptmapmDaoRemote, DeptmapmDaoLocal {
	//@PersistenceContext
	//private EntityManager em;
	/*@PersistenceContext(unitName="SMCTesting")*/
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


	
    /**
     * Default constructor. 
     */
    public DeptmapmDao() {
        // TODO Auto-generated constructor stub
    }
    
    private EntityManager getEntityManager(String webAppName){
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
    
	@SuppressWarnings("unchecked")
	@Override
	public String findMappingDept(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a.mapDept from Deptmapm a WHERE a.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<String> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}

}
