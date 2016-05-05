package costcenter.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;


import costcenter.model.Gldeptin;

/**
 * Session Bean implementation class GldeptinDao
 */
@Stateless
public class GldeptinDao implements GldeptinDaoRemote, GldeptinDaoLocal {
	//@PersistenceContext
	//private EntityManager //getEntityManager(webAppName);
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

	

    /**
     * Default constructor. 
     */
    public GldeptinDao() {
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

	@Override
	public void createGldeptin(Gldeptin gldeptin, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(gldeptin);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Gldeptin> getAll( String webAppName) {
		//getEntityManager(webAppName);
		String qryStr ="select a from Gldeptin a";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<Gldeptin> list = query.getResultList();
        return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Gldeptin getFindByDeptId(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Gldeptin a WHERE a.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<Gldeptin> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}

	@Override
	public void updateGldeptin(Gldeptin gldeptin, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(gldeptin);
		
	}

	@Override
	public void removeGldeptin(Gldeptin gldeptin, String webAppName) {
		//getEntityManager(webAppName);
		gldeptin=getEntityManager(webAppName).merge(gldeptin);
		getEntityManager(webAppName).remove(gldeptin);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa");
		
	}

	@Override
	public Gldeptin findById(String deptId, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		return getEntityManager(webAppName).find(Gldeptin.class, deptId);
	}

	

}
