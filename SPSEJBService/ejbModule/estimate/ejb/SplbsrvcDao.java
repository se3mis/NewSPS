package estimate.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import util.emSelect.EmSelector;
import estimate.model.Splbsrvc;
import estimate.model.SplbsrvcPK;

/**
 * Session Bean implementation class SplbsrvcDao
 */
@Stateless
public class SplbsrvcDao extends EmSelector implements SplbsrvcDaoRemote, SplbsrvcDaoLocal {
	//@PersistenceContext(unitName="SMCTesting")
	//private EntityManager emSMCTesting;
	//@PersistenceContext(unitName="SMCR1")
	//private EntityManager emSMCR1;
	//@PersistenceContext(unitName="SMCR2")
	//private EntityManager emSMCR2;
	//@PersistenceContext(unitName="SMCR3")
	//private EntityManager emSMCR3;
	//@PersistenceContext(unitName="SMCR4")
	//private EntityManager emSMCR4;

	

    /**
     * Default constructor. 
     */
    public SplbsrvcDao() {
        // TODO Auto-generated constructor stub
    }
    
    

	@Override
	public void createSplbsrvc(Splbsrvc splbsrvc, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(splbsrvc);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Splbsrvc> getAll(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Splbsrvc a where a.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<Splbsrvc> list = query.getResultList();
		return list;

	}

	@Override
	public void updateSplbsrvc(Splbsrvc splbsrvc, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(splbsrvc);
		
	}

	@Override
	public void removeSplbsrvc(Splbsrvc splbsrvc, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).remove(splbsrvc);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Splbsrvc findById(SplbsrvcPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Splbsrvc a where a.id.deptId = :deptId AND a.id.labourCode = :labourCode";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", id.getDeptId());
		query.setParameter("labourCode", id.getLabourCode());
		List<Splbsrvc> list = query.getResultList();
		if (list.isEmpty())
			return null;
		else if (list.size() == 1)
			return list.get(0);
		throw new NonUniqueResultException();

	}

}
