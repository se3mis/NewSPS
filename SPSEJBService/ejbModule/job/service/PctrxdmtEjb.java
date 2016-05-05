package job.service;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import job.ejb.PctrxdmtDao;
import job.ejb.PctrxdmtDaoRemote;
import job.model.Pctrxdmt;
import job.model.PctrxdmtPK;

public class PctrxdmtEjb implements PctrxdmtDaoRemote{
	private Context context;
	private PctrxdmtDaoRemote dao; 
	
	
	public PctrxdmtEjb() {
		super();
		this.dao=lookupDao();
		
	}

	private PctrxdmtDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 PctrxdmtDaoRemote dao = (PctrxdmtDaoRemote) context.lookup("PctrxdmtDao/remote");
			// System.out.println("got dao "+dao.getM);
			 //System.out.println(dao.findGldeptm(""));
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	} 

	@Override
	public void createPctrxdmt(Pctrxdmt pctrxdmt, String webAppName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePctrxdmt(Pctrxdmt pctrxdmt, String webAppName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePctrxdmt(Pctrxdmt pctrxdmt, String webAppName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAll(String webAppName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pctrxdmt findById(PctrxdmtPK id, String webAppName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pctrxdmt findBy3PK(String deptId, String docNo, String docPf,
			long seqNo, String webAppName) {
		return dao.findBy3PK(deptId, docNo, docPf, seqNo, webAppName);
	}
	
	public static void main(String[] args) {
		Pctrxdmt ss = null;
		try{
		   PctrxdmtEjb XXX = new PctrxdmtEjb();
		   ss = XXX.findBy3PK( "514.20" ,  "MIS2000" , "JV_MIS", 1 ,"Testing");
		   System.out.println("HIIIIYYY" + ss);
		}catch(Exception e){
			System.out.println("HIIII : " + e.getStackTrace());
			e.printStackTrace();
			System.out.println("HIIIIYYY" + ss);
		}
		
	}

}
