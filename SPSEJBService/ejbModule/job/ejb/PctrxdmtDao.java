package job.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import job.model.Pcestdmt;
import job.model.Pctrxdmt;
import job.model.PctrxdmtPK;
import job.model.Pctrxhmt;

import util.emSelect.EmSelector;

/**
 * Session Bean implementation class PctrxdmtDao
 */
@Stateless
public class PctrxdmtDao extends EmSelector implements PctrxdmtDaoRemote, PctrxdmtDaoLocal {

    /**
     * Default constructor. 
     */
    public PctrxdmtDao() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void createPctrxdmt(Pctrxdmt pctrxdmt, String webAppName) {
		// TODO Auto-generated method stub
		getEntityManager(webAppName).persist(pctrxdmt);
	}

	@Override
	public void updatePctrxdmt(Pctrxdmt pctrxdmt, String webAppName) {
		// TODO Auto-generated method stub
		getEntityManager(webAppName).merge(pctrxdmt);
	}

	@Override
	public void removePctrxdmt(Pctrxdmt pctrxdmt, String webAppName) {
		// TODO Auto-generated method stub
		pctrxdmt=getEntityManager(webAppName).merge(pctrxdmt);
		getEntityManager(webAppName).remove(pctrxdmt);
	}

	@Override
	public void removeAll(String webAppName) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa");
	}

	@Override
	public Pctrxdmt findById(PctrxdmtPK id, String webAppName) {
		return getEntityManager(webAppName).find(Pctrxdmt.class, id);
        
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pctrxdmt findBy3PK(String deptId, String docNo, String docPf,long seqNo, String webAppName) {
		
		//String qryStr = "SELECT g FROM Pctrxdmt g WHERE TRIM(g.id.deptId) = :deptId AND TRIM(g.id.docNo) = :docNo AND TRIM(g.id.docPf) = :docPf  AND TRIM(g.id.seqNo) = :seqNo";
		String qryStr = "SELECT g FROM Pctrxdmt g WHERE g.id.deptId = :deptId AND TRIM(g.id.docNo) = :docNo AND TRIM(g.id.docPf) = :docPf AND g.id.seqNo = :seqNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("docNo", docNo);
		query.setParameter("docPf", docPf);
		query.setParameter("seqNo", seqNo);
		List<Pctrxdmt> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}
	
	
	
	
	

}
