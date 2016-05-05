package masters.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import masters.ejb.BankBeanRemote;
import masters.ejb.ProvinceDetailsMasterRemote;
import masters.model.Bank;
import masters.model.BankBranch;
import masters.model.BankBranchPK;
import masters.model.ProvinceDetailMaster;

public class ProvinceDetailsMasterEjb implements ProvinceDetailsMasterRemote{
	private Context context;
	private ProvinceDetailsMasterRemote bean; 
	public ProvinceDetailsMasterEjb() {
		super();
		this.bean=lookupDao();
		
	}

	private ProvinceDetailsMasterRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 ProvinceDetailsMasterRemote bean = (ProvinceDetailsMasterRemote) context.lookup("ProvinceDetailsMasterDao/remote");
			 return bean; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	@Override
	public void createProvinceDetailsMaster(ProvinceDetailMaster provinceDetailsMaster, String webAppName) {
		bean.createProvinceDetailsMaster(provinceDetailsMaster, webAppName);
		
	}

	@Override
	public void updateProvinceDetailsMaster(ProvinceDetailMaster provinceDetailsMaster, String webAppName) {
		bean.updateProvinceDetailsMaster(provinceDetailsMaster, webAppName);
		
	}

	
	@Override
	public String findCodeName(String code,String deptId,String codeType, String webAppName) throws PersistenceException {
		return bean.findCodeName(code, deptId,codeType,webAppName);
	}

	

	@Override
	public List<ProvinceDetailMaster> getAllCodeDetails( String codeType,String deptId,String webAppName) {
		return bean.getAllCodeDetails( codeType,deptId,webAppName);
	}

	@Override
	public List<String> getAllCodes(String codeType,String deptId,String webAppName) {
		return bean.getAllCodes(codeType,deptId, webAppName);
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ProvinceDetailsMasterEjb ejb=new ProvinceDetailsMasterEjb();
		System.out.println(ejb);
		Bank bank=new Bank();
		bank.setBankCode("4444");
		bank.setBankName("3333");
		
		//ejb.updateBank(bank, "SMCTesting");

	}

	@Override
	public List<String> getAllCodeNames(String codeType, String deptId,
			String webAppName) {
		return bean.getAllCodeNames(codeType,deptId, webAppName);
	}

	@Override
	public String findCode(String codename, String deptId,
			String codeType, String webAppName) throws PersistenceException {
		return bean.findCode(codename, deptId,codeType,webAppName);
	}

	@Override
	public String getBranchType(String deptId, String webAppName)
			throws PersistenceException {
		return bean.getBranchType( deptId,webAppName);
	}

	@Override
	public List<String> getJobTypeByDeptId(String deptId, String webAppName)
			throws PersistenceException {
		return bean.getJobTypeByDeptId(deptId,webAppName);
	}

	@Override
	public String getCommercialId(String deptId, String webAppName)
			throws PersistenceException {
		return bean.getCommercialId(deptId,webAppName);
	}

	@Override
	public String getEstimateTypeByDeptId(String deptId, String webAppName) {
		return bean.getEstimateTypeByDeptId(deptId,webAppName);
	}
	@Override
	public String getJobTypeCode(String deptId,String branchCode, String webAppName) {
		return bean.getJobTypeCode(deptId,branchCode,webAppName);
	}

}
