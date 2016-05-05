package job.ejb;


import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.PersistenceException;

import export.model.BillUpdateData;
import util.emSelect.EmSelector;
import job.model.Sparemap;
import job.model.Spestcnd;
import job.model.Spexphst;
import job.model.Spexpjob;
import job.model.SpexpjobPK;

/**
 * Session Bean implementation class ExportBean
 */
@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless
public class ExportBean extends EmSelector implements ExportBeanRemote, ExportBeanLocal {
	
	@Resource
	private SessionContext context;
	@EJB 
	SpexphstDaoRemote spexphstDaoRemote;
	@EJB 
	SpexpjobDaoRemote spexpjobDaoRemote;
	@EJB 
	PcesthmtDaoRemote pcesthmtDaoRemote;
	@EJB
	SparemapDaoRemote sparemapDaoRemote;
	@EJB
	SpestcndDaoRemote spestcndDaoRemote;
    /**
     * Default constructor. 
     */
    public ExportBean() {
        // TODO Auto-generated constructor stub
    }
	@Override
	public void createSpexphst(Spexphst spexphst, String webAppName) {
		spexphstDaoRemote.createSpexphst(spexphst, webAppName);
		
	}
	@Override
	public void updateSpexphst(Spexphst spexphst, String webAppName) {
		spexphstDaoRemote.updateSpexphst(spexphst, webAppName);
		
	}
	@Override
	public void removeSpexphst(Spexphst spexphst, String webAppName) {
		spexphstDaoRemote.removeSpexphst(spexphst, webAppName);
		
	}
	@Override
	public void removeAllSpexphst(String webAppName) {
		spexphstDaoRemote.removeAll(webAppName);
		
	}
	@Override
	public List<Spexphst> getAllSpexphst(String deptId, String webAppName) {
		return spexphstDaoRemote.getAll(deptId, webAppName);
	}
	@Override
	public Spexphst findById(String referenceNo, String webAppName) throws PersistenceException {
		return spexphstDaoRemote.findById(referenceNo, webAppName);
	}
	@Override
	public void createSpexpjob(Spexpjob spexpjob, String webAppName) {
		spexpjobDaoRemote.createSpexpjob(spexpjob, webAppName);
		
	}
	@Override
	public void updateSpexpjob(Spexpjob spexpjob, String webAppName) {
		spexpjobDaoRemote.updateSpexpjob(spexpjob, webAppName);
		
	}
	@Override
	public void removeSpexpjob(Spexpjob spexpjob, String webAppName) {
		spexpjobDaoRemote.removeSpexpjob(spexpjob, webAppName);
		
	}
	@Override
	public void removeAllSpexpjob(String webAppName) {
		spexpjobDaoRemote.removeAll(webAppName);
		
	}
	@Override
	public List<Spexpjob> getAllSpexpjob(String deptId, String webAppName) {
		return spexpjobDaoRemote.getAll(deptId, webAppName);
	}
	@Override
	public Spexpjob findById(SpexpjobPK id, String webAppName) throws PersistenceException {
		return spexpjobDaoRemote.findById(id, webAppName);
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public void insertExportData(Spexphst spexphst, List<Spexpjob> list, String webAppName) {
	try{      //Insert Spexphst
		String nextId=spexphstDaoRemote.createSpexphstAutoId(spexphst, webAppName);
	      //Insert Spexpjob after setting the ref no (pk of prev insert)
		for(int i=0; i<=list.size()-1; i++){
			list.get(i).setReferenceNo(nextId);
			spexpjobDaoRemote.createSpexpjob(list.get(i), webAppName);
			spestcndDaoRemote.updateIsExported(list.get(i).getId().getProjectNo(), list.get(i).getId().getDeptId(), "Y", webAppName);
			//pcesthmtDaoRemote.changeStatusPcesthmt(list.get(i).getId().getProjectNo(), list.get(i).getId().getDeptId(), new BigDecimal(EstimateStatus.JOB_EXPORTED));
		}
	      //Update status to 06 in PCESTHMT
	}catch (Exception e) {
		//e.printStackTrace();
		System.out.println(e.getMessage());
		context.setRollbackOnly();
		throw new RuntimeException(e);
	}
		
		
	}
	
	@Override
	public Sparemap findBySmcAreaCode(String smcAreaCode, String webAppName) throws PersistenceException {
		return sparemapDaoRemote.findById(smcAreaCode, webAppName);
	}
	@Override
	public void updateExportedJob(List<BillUpdateData> list, String webAppName) {
		spexpjobDaoRemote.updateExportedJob(list, webAppName);
		
	}
	@Override
	public List<String> getExportedJobWithoutAccNo(String deptId,
			String webAppName) {
		return spexpjobDaoRemote.getExportedJobWithoutAccNo(deptId, webAppName);
	}
	
	
	@Override
	public List<String> updateErrorData(String deptId, List<BillUpdateData> list, String webAppName) {
		List<String> updatedList =new ArrayList<String>();
		if (list!=null){
			for(int i=0; i<=list.size()-1; i++){
				//update Spexpjob
				SpexpjobPK id=new SpexpjobPK();
				id.setDeptId(list.get(i).getDeptId());
				id.setProjectNo(list.get(i).getProjectNo());
				Spexpjob spexpjob=spexpjobDaoRemote.findById(id, webAppName);
				spexpjobDaoRemote.removeSpexpjob(spexpjob, webAppName);
				//update Spestcnd
				spestcndDaoRemote.updateError(list.get(i).getProjectNo(), list.get(i).getDeptId(), list.get(i).getErrorDesc(), webAppName);
				//Spestcnd spestcnd =spestcndDaoRemote.findByJobNo(list.get(i).getProjectNo(), list.get(i).getDeptId(), webAppName).get(0);
				//spestcnd.setIsExported("N");
				//spestcnd.setErrorMsg(list.get(i).getErrorDesc());
				updatedList.add(list.get(i).getProjectNo());
			}
			return updatedList;
		}else{
			return null;
		}
	}
	@Override
	public List<BillUpdateData> getAccNoInfo(String deptId, String webAppName) {
		return spexpjobDaoRemote.getAccNoInfo(deptId, webAppName);
	}
	@Override
	public Spexpjob findByJobNo(String jobNo, String webAppName)
			throws PersistenceException {
		return spexpjobDaoRemote.findByJobNo(jobNo, webAppName);
	}
	@Override
	public Spexpjob findByJobNo(String jobNo, String deptId, String webAppName)
			throws PersistenceException {
		// TODO Auto-generated method stub
		return spexpjobDaoRemote.findByJobNo(jobNo, deptId, webAppName); 
	}
	
	


}
