package costcenter.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import costcenter.model.Area;
import costcenter.model.Glcompm;
import costcenter.model.Province;

/**
 * Session Bean implementation class Glcompm
 */
@Stateless
public class GlcompmDao implements GlcompmDaoRemote, GlcompmDaoLocal {
	//@PersistenceContext
	//private EntityManager em;
	//@PersistenceContext(unitName="SMCTesting")
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
    public GlcompmDao() {
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
	public List<Area> getAllAreas( String webAppName) {
    	//getEntityManager(webAppName);
		String qryStr ="SELECT new costcenter.model.Area(TRIM(a.compId) , TRIM(a.compNm), TRIM(a.parentId)) FROM Glcompm a WHERE a.compNm like :like";
		//String qryStr ="SELECT new map(TRIM(a.compId) , TRIM(a.compNm), TRIM(a.parentId)) FROM Glcompm a WHERE a.compNm like :like";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("like", "AREA%");
        List<Area> list = query.getResultList();
        //List<Map> list = query.getResultList();
        return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Province> getAllProvince( String webAppName) {
		//getEntityManager(webAppName);
		String qryStr ="SELECT DISTINCT new costcenter.model.Province(TRIM(a.parentId),TRIM(a.parentId)) FROM Glcompm a WHERE a.lvlNo =60";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		List<Province> list = query.getResultList();
        return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAreasForProvince(String deptid, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr ="select TRIM(com.compNm) from Glcompm com  where TRIM(com.parentId)  in (select TRIM(dept.compId) from Gldeptm dept where TRIM(dept.deptId)= :deptId) and com.status=2";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptid);
		List<String> list = query.getResultList();
        return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAreasByDeptId(String deptid, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr ="select TRIM(dept1.deptId) from Gldeptm dept1 where TRIM(dept1.compId)=(select TRIM(dept.compId) from Gldeptm dept where TRIM(dept.deptId)= :deptId)";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptid);
		List<String> list = query.getResultList();
        return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public String getAreaCodesByName(String deptid,String compNm, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr ="select dept.deptId from Gldeptm dept,Glcompm com  where TRIM(com.compId)=TRIM(dept.compId)  and TRIM(com.compNm)= :compNm " +
				"and dept.deptId like '%.00%' ";
		
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("compNm", compNm);
		List<String> list = query.getResultList();
		if(list != null && list.size()==1){
			return list.get(0);
		}
        return null;
	}
	
}
