package job.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import export.model.BillUpdateData;

import util.emSelect.EmSelector;

import job.model.Spexpjob;
import job.model.SpexpjobPK;

/**
 * Session Bean implementation class SpexpjobDao
 */
@Stateless
public class SpexpjobDao extends EmSelector implements SpexpjobDaoRemote, SpexpjobDaoLocal {
	
    /**
     * Default constructor. 
     */
    public SpexpjobDao() {
        // TODO Auto-generated constructor stub
    }
    
    

	@Override
	public void createSpexpjob(Spexpjob spexpjob, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(spexpjob);
		
	}

	@Override
	public void updateSpexpjob(Spexpjob spexpjob, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(spexpjob);
		
	}

	@Override
	public void removeSpexpjob(Spexpjob spexpjob, String webAppName) {
		//getEntityManager(webAppName);
		spexpjob=getEntityManager(webAppName).merge(spexpjob);
		getEntityManager(webAppName).remove(spexpjob);
		
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spexpjob> getAll(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spexpjob a WHERE a.id.deptId =:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<Spexpjob> list = query.getResultList();
        return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Spexpjob findById(SpexpjobPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spexpjob a WHERE a.id.deptId =:deptId and TRIM(a.id.projectNo)=:projectNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", id.getDeptId());
		query.setParameter("projectNo", id.getProjectNo());
		List<Spexpjob> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}
        
	@SuppressWarnings("unchecked")
    @Override
    public Spexpjob findByJobNo(String jobNo, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spexpjob a WHERE TRIM(a.id.projectNo)=:projectNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("projectNo", jobNo);
		List<Spexpjob> list = query.getResultList();
		if (list.isEmpty())
			return null;
		else if (list.size() == 1)
			return list.get(0);
        throw new NonUniqueResultException();	
		
		
	}
	
	@SuppressWarnings("unchecked")
    @Override
    public Spexpjob findByJobNo(String jobNo, String deptId, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spexpjob a WHERE a.id.deptId =:deptId and TRIM(a.id.projectNo)=:projectNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("projectNo", jobNo);
		query.setParameter("deptId", deptId);
		List<Spexpjob> list = query.getResultList();
		if (list.isEmpty())
			return null;
		else if (list.size() == 1)
			return list.get(0);
        throw new NonUniqueResultException();	
		
		
	}
	
	
	@Override
	public void updateExportedJob(List<BillUpdateData> list, String webAppName) {
		System.out.println("UpdateExportedJob");
		for(int i=0; i<=list.size()-1; i++){
			System.out.println("list.size() "+list.get(i));
			SpexpjobPK id=new SpexpjobPK();
			id.setProjectNo(list.get(i).getProjectNo());
			id.setDeptId(list.get(i).getDeptId());
			System.out.println("id "+id);
			Spexpjob spexpjob=findById(id, webAppName);
			System.out.println("spexpjob "+spexpjob);
			spexpjob.setAccCreatedDate(list.get(i).getAccCreatedDate());
			spexpjob.setAccountNo(list.get(i).getAccountNo());
			updateSpexpjob(spexpjob, webAppName);
			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getExportedJobWithoutAccNo(String deptId, String webAppName) {
		String qryStr = "select TRIM(a.id.projectNo) from Spexpjob a WHERE a.id.deptId =:deptId and a.accountNo is null ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<String> list = query.getResultList();
        return list;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BillUpdateData> getAccNoInfo(String deptId, String webAppName) {
		String qryStr = "select new export.model.BillUpdateData(TRIM(a.id.projectNo),a.id.deptId, a.accCreatedDate, a.accountNo) from Spexpjob a WHERE a.id.deptId =:deptId and a.accountNo is not null ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<BillUpdateData> list = query.getResultList();
        return list;
		
	}

}
