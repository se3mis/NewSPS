package job.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import util.emSelect.EmSelector;

import job.model.Spestsea;
import job.model.SpestseaPK;

/**
 * Session Bean implementation class SpestseaDao
 */
@Stateless
public class SpestseaDao extends EmSelector implements SpestseaDaoRemote, SpestseaDaoLocal {
	
    /**
     * Default constructor. 
     */
    public SpestseaDao() {
        // TODO Auto-generated constructor stub
    }
    
    

	@Override
	public void createSpestsea(Spestsea spestsea, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(spestsea);
		
	}

	@Override
	public List<Spestsea> getAll(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateSpestsea(Spestsea spestsea, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(spestsea);
		
	}

	@Override
	public void removeSpestsea(Spestsea spestsea, String webAppName) {
		//getEntityManager(webAppName);
		spestsea=getEntityManager(webAppName).merge(spestsea);
		getEntityManager(webAppName).remove(spestsea);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Spestsea findById(SpestseaPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "SELECT a FROM Spestsea a WHERE TRIM(a.id.projectNo) =:projectNo AND  a.id.deptId =:deptId ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("projectNo", id.getProjectNo());
		query.setParameter("deptId", id.getDeptId());
		List<Spestsea> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Spestsea findByJobNo(String  jobNo, String webAppName)  {
		//getEntityManager(webAppName);
		String qryStr = "SELECT a FROM Spestsea a WHERE TRIM(a.id.projectNo) =:projectNo ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("projectNo", jobNo);
		List<Spestsea> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spestsea> getSealNoList(String jobNo, String deptId , String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "SELECT a FROM Spestsea a WHERE a.id.projectNo =:projectNo AND  a.id.deptId =:deptId  ORDER BY a.di.sealId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("projectNo", jobNo);
		query.setParameter("deptId", deptId);
		List<Spestsea> list = query.getResultList();
        return list;
	}

}
