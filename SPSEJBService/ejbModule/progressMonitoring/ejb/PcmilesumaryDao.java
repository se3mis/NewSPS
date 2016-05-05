package progressMonitoring.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import progressMonitoring.model.Pcmiledates;
import progressMonitoring.model.Pcmilesumary;
import util.emSelect.EmSelector;

@Stateless
public class PcmilesumaryDao extends EmSelector implements PcmilesumaryDaoRemote {

	public PcmilesumaryDao(){
		
		super();
	}
	@Override
	public void createPcmilesumary(Pcmilesumary pcmilesumary, String webAppName) {
		// TODO Auto-generated method stub
		getEntityManager(webAppName).persist(pcmilesumary);
	}
	
	
	
	@Override
	public void removePcmilesumary(Pcmilesumary pcmilesumary, String webAppName) {
		//getEntityManager(webAppName);
		pcmilesumary = getEntityManager(webAppName).merge(pcmilesumary);
		getEntityManager(webAppName).remove(pcmilesumary);
		
	}


    @SuppressWarnings("unchecked")
	@Override
	public List<Pcmilesumary> getAll(String webAppName) {
		String qryStr = "select p.projectNo from Pcinitialdata p"; 
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		List<Pcmilesumary> list = query.getResultList();
        return list;
	}
    
    @Override
	public List<Pcmilesumary> getSummaryList(String deptId,String projectNumber, String webAppName) {
		String qryStr = "select p from Pcmilesumary p where TRIM(p.id.deptId)=:deptId AND TRIM(p.id.projectNo)=:projectNumber"; 
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId.trim());
		query.setParameter("projectNumber", projectNumber.trim());
		List<Pcmilesumary> list = query.getResultList();
        return list;
	}

	@Override
	public void updatePcmilesumary(Pcmilesumary pcmilesumary, String webAppName) {
		// TODO Auto-generated method stub
		getEntityManager(webAppName).merge(pcmilesumary);
	}
	@Override
	public Pcmilesumary getMilestoneList(String deptId,
			String projectNumber, String webAppName) {
		String qryStr = "select p from Pcmilesumary p where TRIM(p.id.deptId)=:deptId AND TRIM(p.id.projectNo)=:projectNumber"; 
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId.trim());
		query.setParameter("projectNumber", projectNumber.trim());
		List<Pcmilesumary> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
		
	}
	@Override
	public Pcmilesumary getMilestoneSummaryId(String deptId,
			String projectNumber,String milestoneId, String webAppName) {
		String qryStr = "select p from Pcmilesumary p where TRIM(p.id.deptId)=:deptId AND TRIM(p.id.projectNo)=:projectNumber AND TRIM(p.id.mileId)=:milestoneId "; 
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId.trim());
		query.setParameter("projectNumber", projectNumber.trim());
		query.setParameter("milestoneId", milestoneId.trim());
		List<Pcmilesumary> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
		
	}
	

}
