package job.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import util.emSelect.EmSelector;


import job.model.Pctrxhmt;
import job.model.PctrxhmtPK;

/**
 * Session Bean implementation class PctrxhmtDao
 */
@Stateless
public class PctrxhmtDao extends EmSelector implements PctrxhmtDaoRemote, PctrxhmtDaoLocal {

    /**
     * Default constructor. 
     */
    public PctrxhmtDao() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void createPctrxhmt(Pctrxhmt pctrxhmt, String webAppName) {
		// TODO Auto-generated method stub
		getEntityManager(webAppName).persist(pctrxhmt);
	}

	@Override
	public void updatePctrxhmt(Pctrxhmt pctrxhmt, String webAppName) {
		// TODO Auto-generated method stub
		getEntityManager(webAppName).merge(pctrxhmt);
	}

	@Override
	public void removePctrxhmt(Pctrxhmt pctrxhmt, String webAppName) {
		// TODO Auto-generated method stub
		pctrxhmt=getEntityManager(webAppName).merge(pctrxhmt);
		getEntityManager(webAppName).remove(pctrxhmt);
	}

	@Override
	public void removeAll(String webAppName) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa");
	}

	@Override
	public Pctrxhmt findById(PctrxhmtPK id , String webAppName) {
		// TODO Auto-generated method stub
		String qryStr = "SELECT g FROM Pctrxhmt g WHERE TRIM(g.id.deptId) = :deptId AND g.id.docNo = :docNo AND g.id.docPf = :docPf";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", id.getDeptId());
		query.setParameter("docNo", id.getDocNo());
		query.setParameter("docPf", id.getDocPf());
		List<Pctrxhmt> list = query.getResultList();		
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();

	}

	@Override
	public Pctrxhmt findBy3PK(String deptId, String docNo, String docPf, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT g FROM Pctrxhmt g WHERE g.id.deptId = :deptId AND TRIM(g.id.docNo) =:docNo  AND TRIM(g.id.docPf) =:docPf";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("docNo", docNo);
		query.setParameter("docPf", docPf);
		List<Pctrxhmt> list = query.getResultList();	
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();

	}

	
    
   

}
