package piv.ejb;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import estimate.ejb.SpstdestdmtDaoRemote;
import estimate.ejb.SpstdesthmtDaoRemote;
import estimate.model.Spstdesthmt;

import piv.model.PivDetail;
import application.ejb.ApplicationDaoRemote;
import application.model.Application;


/**
 * Session Bean implementation class PivTransactionBean
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PivTransactionBean implements PivTransactionBeanRemote, PivTransactionBeanLocal {
	@Resource
	private SessionContext context;
	
	@EJB
	PivDetailDaoRemote pivDetailDaoRemote;
	@EJB
	ApplicationDaoRemote applicationDaoRemote;
	
	@EJB
	SpstdesthmtDaoRemote spstdesthmtDaoRemote;
	
    /**
     * Default constructor. 
     */
    public PivTransactionBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void confirm(PivDetail pivDetail, Application application, String webAppName) {
		try{
			pivDetailDaoRemote.updatePivDetail(pivDetail, webAppName);
			applicationDaoRemote.updateApplication(application, webAppName);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%% "+e.getMessage());
			context.setRollbackOnly();
		
		}	
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void confirm(PivDetail pivDetail, Spstdesthmt spstdesthmt, String webAppName) {
		try{
			if(pivDetail != null){
				pivDetailDaoRemote.updatePivDetail(pivDetail, webAppName);
			}
			
			if(spstdesthmt != null){
				spstdesthmtDaoRemote.updateSpstdesthmt(spstdesthmt, webAppName);
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%% "+e.getMessage());
			context.setRollbackOnly();
		
		}	
		
	}

}
