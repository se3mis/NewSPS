package progressMonitoring.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;


import progressMonitoring.model.Pcmilem;

import util.emSelect.EmSelector;
/**
 * Session Bean implementation class PcinitialDao
 */
@Stateless
public class PcmilemDao  extends EmSelector implements PcmilemDaoRemote {

	public PcmilemDao()
	{
		
		super();
	}

	@Override
	public void createPcmilem(Pcmilem Pcmilem, String webAppName) {
		// TODO Auto-generated method stub
		getEntityManager(webAppName).persist(Pcmilem);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pcmilem> getAll(String webAppName) {
		// TODO Auto-generated method stub
		String qryStr = "select p from Pcmilem p"; 
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        
		List<Pcmilem> list = query.getResultList();
        return list;
	}

	@Override
	public List<Pcmilem> getMilestoneList(String deptId,
			String webAppName) {
		String qryStr = "select p from Pcmilem p where p.id.deptId=:deptId order by p.percentage ASC"; 
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<Pcmilem> list = query.getResultList();
	
        return list;
	}

	@Override
	public void updatePcmilem(Pcmilem pcmilem, String webAppName) {
		getEntityManager(webAppName).merge(pcmilem);

		
	}
	

}
