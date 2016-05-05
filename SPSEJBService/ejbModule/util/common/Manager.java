package util.common;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class Manager
 */
@Stateless
public class Manager implements ManagerRemote, ManagerLocal {
	@Resource
	SessionContext context;
	@PersistenceContext(unitName="SMCTesting")
	private EntityManager emSMCTesting;
	@PersistenceContext(unitName="SMCR1")
	private EntityManager emSMCR1;
	
	@Override
	public EntityManager getEntityManager(String webAppName){
		if (webAppName.equals("R1"))
				return emSMCR1;	
		else return emSMCTesting;	
			
		
		
	}

    /**
     * Default constructor. 
     */
    public Manager() {
        // TODO Auto-generated constructor stub
    }

}
