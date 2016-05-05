package costcenter.ejb;

import java.util.List;


import javax.ejb.Stateless;
//import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import costcenter.model.Gldeptm;

/**
 * Session Bean implementation class GldeptmDao
 */
@Stateless
public class GldeptmDao implements GldeptmDaoRemote, GldeptmDaoLocal {
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
    public GldeptmDao() {
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
	public String findDeptName(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT TRIM(g.deptNm) FROM Gldeptm g WHERE g.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<String> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}

	@Override
	public Gldeptm findGldeptm(Gldeptm gldeptm, String webAppName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gldeptm findGldeptm(String deptId, String webAppName) {
		//System.out.println(deptId);
		//getEntityManager(webAppName);
		return getEntityManager(webAppName).find(Gldeptm.class, deptId);
		 
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Gldeptm> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr="SELECT g FROM Gldeptm g"; 
		Query query=getEntityManager(webAppName).createQuery(qryStr);
		List<Gldeptm> list = query.getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAreaDeptIdList(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr="SELECT TRIM(g.compId) FROM Gldeptm g WHERE g.deptId = :deptId"; 
		Query query=getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		String compId = (String) query.getSingleResult();
		System.out.println(compId);
		
		qryStr="SELECT TRIM(a.compId) from Glcompm a where TRIM(a.compId) =:compId";
		query=getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("compId", compId);
		List<String> comIdList = query.getResultList();
		System.out.println(comIdList);
		//String compId
		qryStr="SELECT g.deptId FROM Gldeptm g WHERE TRIM(g.compId) IN (:compId)";
		//qryStr="Select  g.deptId  from  Gldeptm  g where TRIM(g.compId) in ( select a.compId from a Glcompm where TRIM(a.comp_id) =:compId)";

		query=getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("compId", compId);
		List<String> list=query.getResultList();
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findDgmDeptIdList(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr="SELECT TRIM(g.compId) FROM Gldeptm g WHERE g.deptId = :deptId"; 
		Query query=getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		String compId = (String) query.getSingleResult();
		System.out.println("compId "+compId);
		
		qryStr="SELECT TRIM(a.compId ) from Glcompm a where TRIM(a.parentId) =:compId";
		query=getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("compId", compId);
		List<String> parentIdList = query.getResultList();
		System.out.println("parentIdList "+parentIdList);
		//String compId
		qryStr="SELECT g.deptId FROM Gldeptm g WHERE TRIM(g.compId) IN (:parentIdList)";
		//qryStr="SELECT g.deptId from  Gldeptm WHERE g.compId in ( select comp_id from Glcompm where (comp_id ='WPS1'))";

		query=getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("parentIdList", parentIdList);
		List<String> list=query.getResultList();
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAgmDeptIdList(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr="SELECT TRIM(g.compId) FROM Gldeptm g WHERE g.deptId = :deptId"; 
		Query query=getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		String compId = (String) query.getSingleResult();
		System.out.println("compId "+compId);
		
		qryStr="SELECT TRIM(a.compId ) from Glcompm a where TRIM(a.grpComp) =:compId";
		query=getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("compId", compId);
		List<String> grpCompList = query.getResultList();
		System.out.println("parentIdList "+grpCompList);
		//String compId
		qryStr="SELECT g.deptId FROM Gldeptm g WHERE TRIM(g.compId) IN (:grpCompList)";
		//qryStr="SELECT g.deptId from  Gldeptm WHERE g.compId in ( select comp_id from Glcompm where (comp_id ='WPS1'))";

		query=getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("grpCompList", grpCompList);
		List<String> list=query.getResultList();
		
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAreaCodes(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select g.deptId from Gldeptm g where g.compId in (select c.compId from Glcompm c where c.parentId in(select g1.compId  from Gldeptm g1 where g1.deptId=:deptId)) order by g.deptId";
		//deptId = "510.00";
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
	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAreaCodeNames(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select g.deptNm from Gldeptm g where g.compId in (select c.compId from Glcompm c where c.parentId in(select g1.compId  from Gldeptm g1 where g1.deptId=:deptId)) order by g.deptId";
		//deptId = "510.00";
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
	

	//select dept_id, dept_nm from gldeptm where comp_id in (select comp_id from glcompm 

	//where comp_id=( select comp_id from gldeptm where dept_id='430.00')  or parent_id= ( select comp_id from gldeptm where dept_id='430.00')) 

	//and status=2

	//order by dept_id
	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAreaCodesForPost(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		//String qryStr = "select g.deptId from Gldeptm g where g.compId in (select c.compId from Glcompm c where c.parentId in(select g1.compId  from Gldeptm g1 where g1.deptId=:deptId) or c.compId ) order by g.deptId";
		String qryStr = "select g.deptId from Gldeptm g where g.compId in (select c.compId from Glcompm c where c.compId in(select g1.compId  from Gldeptm g1 where g1.deptId=:deptId) or c.parentId = (select g1.compId  from Gldeptm g1 where g1.deptId=:deptId)) and status = 2 order by g.deptId";
				
		//               select dept_id  from gldeptm where comp_id in (select comp_id from      glcompm  where comp_id=( select comp_id from gldeptm where dept_id='430.00')        or parent_id= ( select comp_id from gldeptm where dept_id='430.00')) and status=2 order by dept_id
		//deptId = "510.00";
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
	

	
	@SuppressWarnings("unchecked")
	public List<String> getPostDepartmentIds(String deptId,String webAppName) throws PersistenceException{
		//getEntityManager(webAppName);
		String qryStr = "SELECT g.deptId FROM Gldeptm g WHERE g.deptId like :deptId  order by g.deptId";
		//deptId = "510.00";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId+"%");
		List<String> list = query.getResultList();
		if (list.isEmpty()){
			return null;
		}
		else{
        	return list;
		}
		
		
    }

	

}
