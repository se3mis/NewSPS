package job.service;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import export.model.BillUpdateData;
import export.model.MisSmcMaster;
import export.service.ExportBillEjb;

import job.ejb.ExportBeanRemote;
import job.model.Sparemap;
import job.model.Spexphst;
import job.model.Spexpjob;
import job.model.SpexpjobPK;

public class ExportEjb implements ExportEjbI{
	private Context context;
	private ExportBeanRemote bean; 
	private String region=null;
	
	public ExportEjb(String region) {
		super();
		this.region=region;
		this.bean=lookupDao();
		
	}

	private ExportBeanRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 ExportBeanRemote dao = (ExportBeanRemote) context.lookup("ExportBean/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	


	@Override
		public void createSpexphst(Spexphst spexphst) {
			bean.createSpexphst(spexphst, region);
			
		}

		@Override
		public void updateSpexphst(Spexphst spexphst) {
			bean.updateSpexphst(spexphst, region);
			
		}

		@Override
		public void removeSpexphst(Spexphst spexphst) {
			bean.removeSpexphst(spexphst, region);
			
		}

		@Override
		public void removeAllSpexphst() {
			bean.removeAllSpexphst(region);
			
		}

		@Override
		public List<Spexphst> getAllSpexphst(String deptId) {
			return bean.getAllSpexphst(deptId, region);
		}

		@Override
		public Spexphst findById(String referenceNo) throws PersistenceException {
			return bean.findById(referenceNo, region);
		}

		@Override
		public void createSpexpjob(Spexpjob spexpjob) {
			bean.createSpexpjob(spexpjob, region);
			
		}

		@Override
		public void updateSpexpjob(Spexpjob spexpjob) {
			bean.updateSpexpjob(spexpjob, region);
			
		}

		@Override
		public void removeSpexpjob(Spexpjob spexpjob) {
			bean.removeSpexpjob(spexpjob, region);
			
		}

		@Override
		public void removeAllSpexpjob() {
			bean.removeAllSpexpjob(region);
			
		}

		@Override
		public List<Spexpjob> getAllSpexpjob(String deptId) {
			return bean.getAllSpexpjob(deptId, region);
		}

		@Override
		public Spexpjob findById(SpexpjobPK id) throws PersistenceException {
			return bean.findById(id, region);
		}

		@Override
		public void insertExportData(Spexphst Spexphst, List<Spexpjob> list) {
			bean.insertExportData(Spexphst, list, region);
			
		}

		@Override
		public Sparemap findBySmcAreaCode(String smcAreaCode) throws PersistenceException {
			return bean.findBySmcAreaCode(smcAreaCode, region);
		}
		
		@Override
		public void updateExportedJob(List<BillUpdateData> list) {
			bean.updateExportedJob(list, region);
			
		}
		
		
		
		/**
		 * @param args
		 */
		public static void main(String[] args) {
			ExportEjb exportEjb=new ExportEjb("R1");
			//ExportBillEjb exportBillEjb=new ExportBillEjb();
			//List<BillUpdateData> list=exportBillEjb.getBillUpdateData("423.50", "U");
			//System.out.print(list);
			//exportEjb.updateExportedJob(list);
			System.out.println("AccNoInfo "+exportEjb.getAccNoInfo("423.50"));
			ExportBillEjb billEjb=new ExportBillEjb();
			List<String> jobNoList=exportEjb.getExportedJobWithoutAccNo("423.10");
			System.out.println("jobNoList "+jobNoList);
			System.out.println(jobNoList.size());
			System.out.println(jobNoList.isEmpty());
			if(!jobNoList.isEmpty()){
				List<BillUpdateData> list =billEjb.getAccountNos("423.10", jobNoList, "U");
				System.out.println(list);
				System.out.println(list.size());
				exportEjb.updateExportedJob(list);
			}
			System.out.println(billEjb.getErrorInfo("514.20", "S"));
			List<BillUpdateData> errorList = billEjb.getErrorInfo("514.20", "S");
			System.out.println("errorList "+errorList);
			List<String> List3=new ArrayList<String>();
			if(!errorList.isEmpty()){
				List3=exportEjb.updateErrorData("514.20", errorList); 
			}
			if(!List3.isEmpty()){
				//MisSmcMaster 
				System.out.println(List3);
				billEjb.removeByjobNo(List3);
			}
			//System.out.print(ejb.getAllSpexphst("510.20"));
			//System.out.print(ejb.getAllSpexphst("510.20"));

		}

		@Override
		public List<String> getExportedJobWithoutAccNo(String deptId) {
			return bean.getExportedJobWithoutAccNo(deptId, region);
		}

		@Override
		public List<String> updateErrorData(String deptId, List<BillUpdateData> list) {
			return bean.updateErrorData(deptId, list, region);
		}

		@Override
		public List<BillUpdateData> getAccNoInfo(String deptId) {
			return bean.getAccNoInfo(deptId, region);
		}

		@Override
		public Spexpjob findByJobNo(String jobNo)throws PersistenceException {
			return bean.findByJobNo(jobNo, region);
		}

		@Override
		public Spexpjob findByJobNo(String jobNo, String deptId) throws PersistenceException {
			return bean.findByJobNo(jobNo, deptId, region);
		}

		
}
