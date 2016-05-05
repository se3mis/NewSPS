package progressMonitoring.ejb;

import java.util.List;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.Query;

import application.model.Application;

import progressMonitoring.model.Pcmiledates;
import progressMonitoring.model.Pcmilem;

import util.common.Format;
import util.emSelect.EmSelector;

@Stateless
public class PcmiledatesDao extends EmSelector implements PcmiledatesDaoRemote {
	@Resource
	private SessionContext context;
	public PcmiledatesDao()
	{
		
		super();
		
	}
	@Override
	public void delete(String deptId,String projectNumber,String webAppName){
		String qryStr ="";
		
		try{
			qryStr = "delete from Pcmiledates a where TRIM(a.id.projectNumber)=:projectNumber AND a.id.deptId= :deptId";
			Query query = getEntityManager(webAppName).createQuery(qryStr);
			query.setParameter("deptId", deptId);
			query.setParameter("projectNumber", projectNumber);
			query.executeUpdate();
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
	}
	
	
	@Override
	public void createpcmiledates(Pcmiledates pcmiledates, String webAppName) {
		// TODO Auto-generated method stub
		getEntityManager(webAppName).persist(pcmiledates);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pcmiledates> getAll(String webAppName) {
		// TODO Auto-generated method stub
		String qryStr = "select p.projectNo from Pcinitialdata p"; 
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		List<Pcmiledates> list = query.getResultList();
        return list;
	}

	@Override
	public void removePcmiledates(Pcmiledates pcmiledates, String webAppName) {
		//getEntityManager(webAppName);
		pcmiledates = getEntityManager(webAppName).merge(pcmiledates);
		getEntityManager(webAppName).remove(pcmiledates);
		
	}
	@Override
	public List<Pcmiledates> getDataForEstNumber(String webAppName,
			String value, String Type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatepcmiledates(Pcmiledates pcmiledates, String webAppName) {
		// TODO Auto-generated method stub
		getEntityManager(webAppName).merge(pcmiledates);
	}
	@Override
	public List<Pcmiledates> getMilestoneList(String deptId,String projectNumber,String webAppName) {
		String qryStr = "select p from Pcmiledates p where TRIM(p.id.deptId)=:deptId AND TRIM(p.id.projectNumber)=:projectNumber order by p.cpercentage DESC"; 
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId.trim());
		query.setParameter("projectNumber", projectNumber.trim());
		List<Pcmiledates> list = query.getResultList();
	
        return list;
	}
	@Override
	public Pcmiledates getMilestoneById(String deptId,String projectNumber,String miletoneId,String webAppName) {
		String qryStr = "select p from Pcmiledates p where TRIM(p.id.deptId)=:deptId AND TRIM(p.id.projectNumber)=:projectNumber AND TRIM(p.id.mileId)=:miletoneId order by p.cpercentage DESC"; 
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId.trim());
		query.setParameter("projectNumber", projectNumber.trim());
		query.setParameter("miletoneId", miletoneId.trim());
		List<Pcmiledates> list = query.getResultList();
		if(list != null && list.size() > 0){
			return list.get(0);
		}else{
			return null;
		}
		
	}
	
	@Override
	public Pcmiledates getMilestoneByDate(String deptId,String projectNumber,String miletoneId,Date enterDate,String webAppName) {
		
		
		System.out.println("sss: "+ deptId + "qryStr : "+projectNumber +"dd ;"+miletoneId +"ddd : ");
		String qryStr = "select p from Pcmiledates p where TRIM(p.id.deptId)=:deptId AND TRIM(p.id.projectNumber)=:projectNumber AND TRIM(p.id.mileId)=:miletoneId AND p.id.enterDate=:enterDate order by p.cpercentage DESC"; 
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId.trim());
		query.setParameter("projectNumber", projectNumber.trim());
		query.setParameter("miletoneId", miletoneId.trim());
		query.setParameter("enterDate", enterDate);
		System.out.println("sss: "+ query.toString() + "qryStr : "+qryStr);
		List<Pcmiledates> list = query.getResultList();
		if(list != null && list.size() > 0){
			return list.get(0);
		}else{
			return null;
		}
		
	}
	
	
}
