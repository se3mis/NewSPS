package costcenter.ejb;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import costcenter.model.Area;
import costcenter.model.Gldeptin;
import costcenter.model.Gldeptm;
import costcenter.model.Province;

/**
 * Session Bean implementation class CostCenterBean
 */
@Stateless
public class CostCenterBean implements CostCenterBeanRemote, CostCenterBeanLocal {
	@Resource
	private SessionContext context;
	@EJB 
	GlcompmDaoRemote glcompmDaoRemote;
	@EJB 
	GldeptinDaoRemote gldeptinDaoRemote;
	@EJB 
	GldeptmDaoRemote gldeptmDaoRemote;
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
    public CostCenterBean() {
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
	public List<Area> getAllAreas(String webAppName) { 
		//getEntityManager(webAppName);
		return glcompmDaoRemote.getAllAreas(webAppName);
	}

	@Override
	public List<Province> getAllProvince(String webAppName) {
		//getEntityManager(webAppName);
		return glcompmDaoRemote.getAllProvince(webAppName);
	}

	@Override
	public void createGldeptin(Gldeptin gldeptin, String webAppName) {
		//getEntityManager(webAppName);
		gldeptinDaoRemote.createGldeptin(gldeptin,webAppName);
		
	}

	@Override
	public List<Gldeptin> getAllGldeptin(String webAppName) {
		//getEntityManager(webAppName);
		return gldeptinDaoRemote.getAll(webAppName);
	}

	@Override
	public void updateGldeptin(Gldeptin gldeptin, String webAppName) {
		//getEntityManager(webAppName);
		gldeptinDaoRemote.updateGldeptin(gldeptin,webAppName);
		
	}

	@Override
	public void removeGldeptin(Gldeptin gldeptin, String webAppName) {
		//getEntityManager(webAppName);
		gldeptinDaoRemote.removeGldeptin(gldeptin,webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		gldeptinDaoRemote.removeAll(webAppName);
		
	}

	@Override
	public Gldeptin findById(String deptId, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		return gldeptinDaoRemote.findById(deptId,webAppName);
	}

	@Override
	public Gldeptin getFindByDeptId(String deptId, String webAppName) {
		return gldeptinDaoRemote.getFindByDeptId(deptId,webAppName);
	}

	@Override
	public String findDeptName(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		return gldeptmDaoRemote.findDeptName(deptId, webAppName);
	}

	@Override
	public Gldeptm findGldeptm(Gldeptm gldeptm, String webAppName) {
		//getEntityManager(webAppName);
		return gldeptmDaoRemote.findGldeptm(gldeptm, webAppName);
	}

	@Override
	public Gldeptm findGldeptm(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		return gldeptmDaoRemote.findGldeptm(deptId, webAppName);
	}

	@Override
	public List<String> findAreaDeptIdList(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		return gldeptmDaoRemote.findAgmDeptIdList(deptId, webAppName);
	}

	@Override
	public List<String> findDgmDeptIdList(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		return gldeptmDaoRemote.findDgmDeptIdList(deptId, webAppName);
	}

	@Override
	public List<Gldeptm> getAllGldeptm(String webAppName) {
		//getEntityManager(webAppName);
		return gldeptmDaoRemote.getAll(webAppName);
	}

	@Override
	public List<String> findAgmDeptIdList(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		return gldeptmDaoRemote.findAgmDeptIdList(deptId, webAppName);
	}

}
