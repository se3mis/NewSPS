package job.service;

import java.math.BigDecimal;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import job.ejb.PctrxhmtDaoRemote;
import job.model.Pcesthmt;
import job.model.PcesthmtPK;
import job.model.Pctrxhmt;
import job.model.PctrxhmtPK;
import estimate.model.Approval;

public class PctrxhmtEjb implements PctrxhmtEjbI{
	
	private Context context;
	private PctrxhmtDaoRemote dao; 
	private String region=null;
	
	
	public PctrxhmtEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}

	private PctrxhmtDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 PctrxhmtDaoRemote dao = (PctrxhmtDaoRemote) context.lookup("PctrxhmtDao/remote");
			 //System.out.println("got dao "+dao);
			 //System.out.println(dao.findGldeptm(""));
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createPctrxhmt(Pctrxhmt pctrxhmt) {
		// TODO Auto-generated method stub
		dao.createPctrxhmt(pctrxhmt, region);
	}

	@Override
	public void updatePctrxhmt(Pctrxhmt pctrxhmt) {
		// TODO Auto-generated method stub
		dao.updatePctrxhmt(pctrxhmt, region);
	}

	@Override
	public void removePctrxhmt(Pctrxhmt pctrxhmt) {
		// TODO Auto-generated method stub
		dao.removePctrxhmt(pctrxhmt, region);
	}

	@Override
	public void removeAll(String webAppName) {
		// TODO Auto-generated method stub
		dao.removeAll(webAppName);
	}
	
	@Override
	public Pctrxhmt findById(PctrxhmtPK id) {
		return dao.findById(id, region);
	}

	@Override
	public Pctrxhmt findBy3PK(String deptId, String docPf, String docNo)
			throws PersistenceException {
		// TODO Auto-generated method stub
		return dao.findBy3PK(deptId,docPf,docNo, region);
	}

	
}
