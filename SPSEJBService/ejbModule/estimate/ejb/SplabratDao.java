package estimate.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.emSelect.EmSelector;
import estimate.model.LabourGrid;
import estimate.model.Splabrat;
import estimate.model.SplabratPK;


/**
 * Session Bean implementation class SplabratDao
 */
@Stateless
public class SplabratDao extends EmSelector implements SplabratDaoRemote, SplabratDaoLocal {
		
    /**
     * Default constructor. 
     */
    public SplabratDao() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void createSplabrat(Splabrat splabrat, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(splabrat);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Splabrat> getAll(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Splabrat a where a.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<Splabrat> list = query.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabourGrid> getLabourGrid(String deptId, String applicationType, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select new estimate.model.LabourGrid(a.id.labourCode, a.labourName, TRIM(a.description), a.unitPrice, a.labourHours) from Splabrat a where a.id.deptId=:deptId AND a.applicationType=:applicationType";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("applicationType", applicationType);
		List<LabourGrid> list = query.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabourGrid> getLabourGrid(String deptId, String applicationType, List<String> labourCodeList, String webAppName) {
		//getEntityManager(webAppName);
		if (labourCodeList!=null){
			//String qryStr = "select new estimate.model.LabourGrid(a.id.labourCode, a.labourName, TRIM(a.description), a.unitPrice, a.labourHours) from Splabrat a where a.id.deptId=:deptId AND a.applicationType=:applicationType AND a.id.labourCode NOT IN (:labourCodeList)";
			String qryStr = "select new estimate.model.LabourGrid(a.id.labourCode, a.labourName, TRIM(a.description), a.unitPrice, a.labourHours) from Splabrat a where a.id.deptId=:deptId AND a.id.labourCode NOT IN (:labourCodeList)";
			Query query = getEntityManager(webAppName).createQuery(qryStr);
			query.setParameter("deptId", deptId);
			//query.setParameter("applicationType", applicationType);
			query.setParameter("labourCodeList", labourCodeList);
			List<LabourGrid> list = query.getResultList();
			return list;
		}else{
			//String qryStr = "select new estimate.model.LabourGrid(a.id.labourCode, a.labourName, TRIM(a.description), a.unitPrice, a.labourHours) from Splabrat a where a.id.deptId=:deptId AND a.applicationType=:applicationType";
			String qryStr = "select new estimate.model.LabourGrid(a.id.labourCode, a.labourName, TRIM(a.description), a.unitPrice, a.labourHours) from Splabrat a where a.id.deptId=:deptId ";
			Query query = getEntityManager(webAppName).createQuery(qryStr);
			query.setParameter("deptId", deptId);
			//query.setParameter("applicationType", applicationType);
			List<LabourGrid> list = query.getResultList();
			return list;
		}
		
	}
	

	@Override
	public void updateSplabrat(Splabrat splabrat, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(splabrat);
		
	}

	@Override
	public void removeSplabrat(Splabrat splabrat, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).remove(splabrat);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Splabrat findById(SplabratPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Splabrat a where a.id.deptId = :deptId AND a.id.labourCode = :labourCode";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", id.getDeptId());
		query.setParameter("labourCode", id.getLabourCode());
		List<Splabrat> list = query.getResultList();
		if (list.isEmpty())
			return null;
		else if (list.size() == 1)
			return list.get(0);
		throw new NonUniqueResultException();
	}
	
	@Override
	public void insertLabour(String fromDeptId, String todeptId, String webAppName){
		
		List<Splabrat> list=getAll(fromDeptId, webAppName);
		if (list!=null){
			for(int i=0; i<=list.size()-1; i++){
				SplabratPK splabratPK= list.get(i).getId();
				splabratPK.setDeptId(todeptId);
				list.get(i).setId(splabratPK);
				createSplabrat(list.get(i), webAppName);
				System.out.println(i);
			}
		}
	}

}
