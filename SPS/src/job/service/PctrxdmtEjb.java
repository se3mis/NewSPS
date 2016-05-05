package job.service;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import job.ejb.PctrxdmtDaoRemote;
import job.model.Pctrxdmt;
import job.model.PctrxdmtPK;

public class PctrxdmtEjb implements PctrxdmtEjbI{
	
	
	private Context context;
	private PctrxdmtDaoRemote dao; 
	private String region=null;
	
	
	public PctrxdmtEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}

	private PctrxdmtDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 PctrxdmtDaoRemote dao = (PctrxdmtDaoRemote) context.lookup("PctrxdmtDao/remote");
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
	public void updatePctrxdmt(Pctrxdmt pctrxdmt) {
		// TODO Auto-generated method stub
		dao.updatePctrxdmt(pctrxdmt, region);
	}

	@Override
	public void removePctrxdmt(Pctrxdmt pctrxdmt) {
		// TODO Auto-generated method stub
		dao.removePctrxdmt(pctrxdmt, region);
	}

	@Override
	public void removeAll(String webAppName) {
		
	}

	@Override
	public Pctrxdmt findById(PctrxdmtPK id) {
		return dao.findById(id, region);
	}

	@Override
	public Pctrxdmt findBy3PK(String deptId, String docNo, String docPf,long seqNo) {
		return dao.findBy3PK(deptId, docNo, docPf, seqNo , region);
	}

	@Override
	public void createPctrxdmt(Pctrxdmt pctrxdmt) {
		// TODO Auto-generated method stub
		dao.createPctrxdmt(pctrxdmt, region);
	}


	
	

}
