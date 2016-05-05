package masters.service;

import java.util.List;

import javax.persistence.PersistenceException;

import masters.model.Bank;
import masters.model.BankBranch;
import masters.model.BankBranchPK;
import masters.model.ProvinceDetailMaster;

public interface ProvinceDetailsMasterEjbI {
	public void createProvinceDetailsMaster(ProvinceDetailMaster provinceDetailsMaster, String webAppName);
	public void updateProvinceDetailsMaster(ProvinceDetailMaster provinceDetailsMaster, String webAppName);
	public String findCodeName(String code,String deptId,String codeType, String webAppName) throws PersistenceException;
	public List<ProvinceDetailMaster> getAllCodeDetails( String codeType,String deptId,String webAppName);
	public List<String> getAllCodes(String codeType,String deptId,String webAppName);
	public List<String> getAllCodeNames(String codeType, String deptId,
			String webAppName);
	public String findCode(String codename, String deptId,
			String codeType, String webAppName) throws Exception;
	String getBranchType(String deptId,
			String webAppName);
	List<String> getJobTypeByDeptId(String deptId,
			String webAppName) throws Exception;
	
	String getCommercialId(String deptId,
			String webAppName) throws Exception;
	String getEstimateTypeByDeptId(String deptId, String webAppName);
	String getJobTypeCode(String deptId,String branchCode, String webAppName) ;
}
