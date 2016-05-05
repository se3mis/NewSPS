package estimate.ejb;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import util.emSelect.EmSelector;

import estimate.model.Spestpem;
import estimate.model.SpestpemPK;

/**
 * Session Bean implementation class SpestpemDao
 */
@Stateless
public class SpestpemDao extends EmSelector implements SpestpemDaoRemote, SpestpemDaoLocal {
	

	
	@Resource
	private SessionContext context;
	
    /**
     * Default constructor. 
     */
    public SpestpemDao() {
        // TODO Auto-generated constructor stub
    }
    
   

	@Override
	public void createSpestpem(Spestpem spestpem, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(spestpem);
		
	}

	@Override
	public void createSpestpem(List<Spestpem> list, String webAppName) {
		//getEntityManager(webAppName);
		try{
			for(int i=0; i<=list.size()-1; i++){
				getEntityManager(webAppName).persist(list.get(i));
			}
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spestpem> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spestpem a";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<Spestpem> list = query.getResultList();
        return list;
	}

	@Override
	public void updateSpestpem(Spestpem spestpem, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(spestpem);
		
	}

	@Override
	public void removeSpestpem(Spestpem spestpem, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).remove(spestpem);
		
	}
	
	@Override
	public void removeSpestpem(List<SpestpemPK> list, String webAppName) {
		//getEntityManager(webAppName);
		try{
			for(int i=0; i<=list.size()-1; i++){
				Spestpem spestpem=getEntityManager(webAppName).find(Spestpem.class, list.get(i));
				getEntityManager(webAppName).remove(spestpem);
			}
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}
	

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spestpem> findById(SpestpemPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select s from Spestpem s where s.id.applicationNo=:applicationNo AND s.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationNo", id.getApplicationNo());
		query.setParameter("deptId", id.getDeptId());
        List<Spestpem> list = query.getResultList();
        return list;
        
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spestpem> getPermissions(String applicationNo, String deptId, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select s from Spestpem s where s.id.applicationNo=:applicationNo AND s.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationNo", applicationNo);
		query.setParameter("deptId", deptId);
        List<Spestpem> list = query.getResultList();
        return list;
        
	}

}
