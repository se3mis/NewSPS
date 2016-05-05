package estimate.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.PersistenceException;

import piv.model.TempTb;

import job.model.Pcestdmt;
import job.model.Pcesthmt;
import job.model.Pcrstypm;

import estimate.dto.ApproveDetails;
import estimate.dto.DetailEstimateDTO;
import estimate.ejb.Spugcblm;
import estimate.ejb.SpugcblmPK;
import estimate.model.AllocationSummaryDisplay;
import estimate.model.Approval;
import estimate.model.DefaultMatGrid;
import estimate.model.EstimateDisplay;

import estimate.model.EstimateReference;
import estimate.model.FundSource;
import estimate.model.LabourGrid;
import estimate.model.MaterialGrid;
import estimate.model.Pcrsgrpm;
import estimate.model.Pegschdmt;
import estimate.model.SpNormsGroup;
import estimate.model.SpPegInfo;
import estimate.model.SpPointdmt;
import estimate.model.Spnorms;
import estimate.model.Pcestdtt;
import estimate.model.PcestdttPK;
import estimate.model.Pcesthtt;
import estimate.model.Pcfunddm;
import estimate.model.Spestlab;
import estimate.model.SpestlabPK;
import estimate.model.Speststd;
import estimate.model.Splpsvcm;
import estimate.model.SplpsvcmPK;
import estimate.model.SpnormsPK;
import estimate.model.Spratesm;
import estimate.model.SpratesmPK;
import estimate.model.Spsecdep;
import estimate.model.SpsecdepPK;
import estimate.model.Spstdestdmt;
import estimate.model.Spstdesthmt;

@SuppressWarnings("rawtypes")
public interface EstimateEjbI {
	List<MaterialGrid> getMaterialGrid(String estimateNo, String deptId);
	MaterialGrid getMaterialGridRecord(String estimateNo, String deptId , String matCd);
	void insert(Pcesthtt pcesthtt, Speststd speststd, List<Pcestdtt> list);
	void insert(Pcesthtt pcesthtt, Speststd speststd, List<Pcestdtt> list, List<Spestlab> listLabour);
	void update(Pcesthtt pcesthtt, Speststd speststd, List<Pcestdtt> addlist, List<Pcestdtt> updList);
	void update(Pcesthtt pcesthtt, Speststd speststd, List<Pcestdtt> addlist, List<Pcestdtt> updList, List<Spestlab> addLabourList, List<Spestlab> updLabourList);
	void delete(List<PcestdttPK> list,Pcesthtt pcesthtt );
	List<DefaultMatGrid> getDefaultList(Long phase, Long connectionType, String wiringType, String deptId);
	ArrayList<ArrayList<String>> getAvailableOtherCosts(String deptId, ArrayList selMatSet) throws Exception;
	ArrayList<ArrayList<String>> getAvailableMaterials(String deptId, ArrayList selMatSet) throws Exception;
	LinkedHashMap<String, MaterialGrid> getDefaultMaterialGrid(String deptId, long phase, long conType, String wiringType);
	List<Pcfunddm> getFundSourceList(String deptId);
	List<FundSource> getFundSource(String deptId);
	List<FundSource> getFundSourceJV(String deptId);
	ArrayList<ArrayList<String>> getNPLMaterials(String deptId, ArrayList selMatSet) throws Exception;
	//List<Pcesthtt> getEstApprovalReport1(String UserName, String deptId,BigDecimal status, String value);
	//List<EstimateDisplay> getEstApprovalReport(String userName, String deptId, String authorityLevel);
	List<EstimateDisplay> getEstApprovalReport(String userName, String deptId, String authorityLevel, String applicationType);
	void estimatePost(List<String> list, String costCenterNo);
	List<EstimateDisplay> getEstimateList(String deptId, BigDecimal status);
	List<EstimateDisplay> getEstimateList(String deptId, BigDecimal status, String applicationType);
	List<MaterialGrid> getConductorMaterialGrid(String deptId, long phase,
			long connectionType, String wiringType, String conductorType,
			Double conductorLength) throws PersistenceException;
	List<MaterialGrid> getServiceWireMaterialGrid(String deptId, long phase,
			long connectionType, String wiringType, Double serviceLength);
	List<MaterialGrid> getPoleTypeMaterialGrid(String deptId, long phase,
			String poleType, String fromConductor, String toConductor, int noOfPoles);
	List<MaterialGrid> getStrutMaterialGrid(String deptId, int noOfStruts);

	List<MaterialGrid> getStayMaterialGrid(String deptId, int noOfStays);
	MaterialGrid getMaterialGridByMatCd(String deptId, String matCd, Double noOfItems);
	void removeLabour(List<SpestlabPK> removeList, Pcesthtt pcesthtt, List<Pcestdtt> pcestdttList, Speststd speststd);
	void delete(String estimateNo, String deptId,Approval approval);
	List<LabourGrid> getDefaultPoleLabour(String deptId,
			String applicationType, String matCd, int noOfPoles);
	List<LabourGrid> getDefaultStrutLabour(String deptId,
			String applicationType, String matCd, int noOfPoles);
	List<LabourGrid> getDefaultStayLabour(String deptId,
			String applicationType, String stayType, int noOfStays);
	List<LabourGrid> getDefaultPoleTransportLabour(String deptId,
			String applicationType, String matCd, int noOfPoles, Double distance);
	List<LabourGrid> getDefaultServiceWireLabour(String deptId,
			String applicationType, String phase, String span_type,
			String noOfSpans);
	List<LabourGrid> getDefaultConductorLabour(String deptId,
			String applicationType, String phase, String conductorType,
			String wireLength);
	String transfer(Pcesthtt pcesthtt, List<Pcestdtt> list, String cSCNo);
	Spsecdep getSpsecdep(SpsecdepPK id);
	Splpsvcm getSplpsvcm(SplpsvcmPK id);
	Spugcblm getSpugcblm(SpugcblmPK id);
	LinkedHashMap<String, MaterialGrid> getDefaultLoopMaterialGrid(
			String deptId, long phase, long conType, String wiringType);
	List<EstimateDisplay> getEstApprovalStatusReport(String userName,
			String deptId, String authorityLevel, String applicationType,
			BigDecimal status, String isLoanApp);
	Spratesm getSpratesm(SpratesmPK id);
	void removeMaterials(List<PcestdttPK> list, Pcesthtt inPcesthtt,
			Speststd speststd);
	List<EstimateDisplay> getEstApprovalReportMT_SA(String userName,
			String deptId, String authorityLevel, String applicationType);
	List<EstimateDisplay> getEstimateListMA_SA(String deptId,
			BigDecimal status);
	
	public List<Spnorms> getAvailableNorms() throws Exception;
	public List<SpPegInfo> getPegChildrensByParentId(String parentId,String masterId) throws Exception;
	
	public List<String> loadApplicationRefnos(String userId, String costCenter);

	public void insert(Spstdesthmt spstdesthmt,List<Spstdestdmt> lineTypelist,
			String webAppName);
	public Spstdesthmt loadEstimateByApplicationNo(String costCenter,String applicationNo,String webAppName);
	public List<Spstdestdmt> loadEstimateDetailsByApplicaNo(String costCenter,String applicationNo, String webAppName);
	public int removeLineDetails(Spstdestdmt line, String webAppName);
	public int updateLineDetails(Spstdestdmt line, String webAppName);
	public void updateStdEstimateDetails(String applicationNo,BigDecimal secDeposit,BigDecimal totalCost, String webAppName);
	
	public List<String> loadStandEstmatenos(String costCenter,List<Long> status, String webAppName);
	public BigDecimal getLineCost(SpnormsPK lineId,String webAppName);
	public List<SpPointdmt> getPegResourceById(String id,String masterId,String webAppName);
	public List<String> loadResourceTypes(String webAppName);
	public List<String> loadResourceCodes(String resourceType,String webAppName);
	public Pcrsgrpm loadResourceDetails(String resourceType,String resourceCode,String webAppName);
	public List<String> loadWarehouses(String deptId, String webAppName);
	public List<String> getFundSources(String deptId, String webAppName);
	public List<String> getFundIds(String deptId,String fundSource, String webAppName);
	public List<String> getCatCode(String wareSource, String webAppName);
	public List<Pcrstypm> isResourceTypeExist(String resourType, String webAppName);
	public List<DetailEstimateDTO> getOtherMaterials(String deptId, String webAppName) throws Exception;
	public List<DetailEstimateDTO> getNPLMaterials(String deptId, String webAppName) throws Exception;
	public List<DetailEstimateDTO> getAvailableOtherMaterials(String deptId,String warehouse, String webAppName) throws Exception;
	public List<DetailEstimateDTO> getAvailableOtherMaterials(String deptId,String warehouse,List<String> list, String webAppName) throws Exception;
	public void insertDetailEst(Pcesthtt pcesthtt,List<Pcestdtt> list,TempTb tb ,List<Pegschdmt> peglist,String appointedUserName,String fileRef, String webAppName) throws Exception;
	public void addAdditionalCost(Pcesthtt pcesthtt,String estimateNo ,String deptId,BigDecimal toCost,String webAppName) throws Exception;
	public  void insertPIVDetails(TempTb tempTb,String webAppName)throws Exception;
	public  TempTb findPIVDetails(String estimationNo,String deptId,String webAppName)throws Exception;
	//public Pcesthtt findByEstimationNo(String estimateNo,String deptId,Long revNo, String webAppName)throws Exception;
	public void updateWorkEstimateDetails(Pcesthtt pcesthtt, Speststd speststd,List<Pcestdtt> addlist, List<Pcestdtt> updList,List<Pcestdtt> deleList,List<Pegschdmt> addedPegschdmtList,List<Pegschdmt> upadtedPegschdmtList,TempTb temptb, String webAppName)throws Exception;
	public  List<Spstdesthmt> loadStandEstmateDetails(String costCenter,List<Long> status,String userName,String webAppName)throws Exception;
	//public boolean updateEstimateStatus(String estimateNumber,String costCenter, Long status,ApproveDetails approveDetails, String webAppName);
	public boolean updateEstimateStatus(String estimateNumber,
			String costCenter, Long status,String approvedBy,Date approvedDate ,String rejectedBy,
			Date rejectedDate,String planningByPE,Date planningDatePE,String valiadteByEE,Date valiadteDateEE,String valiadteByCE,Date valiadteDateCE,String rejectedReasonCE,String rejectedReasonPE,
			String rejectedReasonEE, String webAppName);
	public List<EstimateDisplay> getRejectedEstimateList(String deptId,
			Long status, String appType, String userId, String userLevel,String webAppName)throws Exception;
	public List<EstimateDisplay> getApprovedEstimateList(String deptId,
			Long status, String appType, String userId, String webAppName)
			throws Exception;
	public List<EstimateDisplay> getConfirmPIVList(String deptId,
			Long status, String appType, String userId, String webAppName)
			throws Exception;

	public List<EstimateDisplay> getStdEstimateList(String deptId,
			List<Long> status, String appType, String userId, String webAppName)
			throws Exception;
	public List<EstimateDisplay> getEstimateListToBeApproved(String deptId,
			List<Long> status, String appType, String userId, String webAppName)
			throws Exception;
	List<EstimateDisplay> getEstimateListToBeValidate(String deptId,List<Long> status, String appType,String userName, String webAppName)throws Exception;
	public void updateEstimateDetails(Spstdesthmt spstdesthmt,List<Spstdestdmt> insertlist,List<Spstdestdmt> updatelist,  String webAppName)throws Exception;
	public String getWorkEstimateNo(String estimateNoPrefix,String stdEstimateNo, String deptId, String webAppName)throws Exception;
	public List<Pcesthtt> getDetailEstimatesList(String deptId, List<Long> status,
			String userId,String assignTo, String webAppName)  throws Exception;
	public List<Pcesthtt> getDetailEstimatesListOrderByFundSource(String deptId, List<Long> status,
			String userId,String assignTo, String webAppName)  throws Exception;
	public void updateDetailEstimateCost(String estimateNo,String estimateType, String deptId,
			String webAppName) throws Exception ;
	public void updateStandardEstimateCost(String estimateNo, String deptId,
			String webAppName) throws Exception;
	//public void updateReviseDetails(Pcesthmt pcesthmt, List<Pcestdmt> addlist,
		//	List<Pcestdmt> updList, List<Pcestdmt> deleList, String webAppName)throws Exception;;
	public String changeCostCenter(String jobNo, String fromDeptId, String toDeptId);
	public String transferToHmt(Pcesthtt pcesthtt, List<Pcestdtt> list,
			String cSCNo, String newJobNo)
			throws Exception;
	public String approveStandardEstimate(String estimateNo, String deptId,	String authorityLevel, String userName, String userNamePostedTo, String applicationType)throws Exception;;
	String rejectStandardEstimate(String estimateNo, Approval approval) throws Exception;
	public List<Pcfunddm> getFundSourcesList(String deptId, String webAppName);
	//List<EstimateTypeMaster> getEstimateTypesbyDeptId(String deptId, String webAppName)
	//throws Exception;
	List<String> getFirstFiftyJobList(String deptId,List<Long> status);
	public List<Spstdesthmt> loadConMainSentList(String costCenter,
			String postID, List<Long> status);
	BigDecimal getTotalConsumerPayable(String estimateNo,
			String resType, String deptId);
	public List<SpNormsGroup> getChildrensByParentId(String parentId);
	List<SpNormsGroup> getAllSpNormsGroups(String webAppname);
	List<Spnorms> getNormsChildrensByParentId(String parentId);
	List<AllocationSummaryDisplay> findEstimateSummary(
			String applicationNo, String deptId);
	List<String> loadStandEstmatenos(String costCenter,List<Long> status,String userName,String webAppName);
	 List<Pcesthtt> getVSLEstimatesList(String deptId, List<Long> status,
				String userId);
	 String recommendStandardEstimate(String estimateNo, String deptId,String authorityLevel, String userName,String com, String assignedUserName,String applicationType,String sinNo);
	 public List<Pcesthtt> getEstimateListForVSLAuthorizedCC(
				Long revNo, List<Long> status, String userId, String likeEstimate);
	 int deleteEstimate(Pcesthtt htt,List<Pcestdtt> dtt,TempTb temptb,EstimateReference ref)
		throws Exception;
	 String updateAndValidate(Pcesthtt pcesthtt, List<Pcestdtt> addlist,
				List<Pcestdtt> updList, List<Pcestdtt> deleList,
				List<Pegschdmt> addedPegschdmtList,
				List<Pegschdmt> updatedPegschdmtList, TempTb tempTb,
				String authorityLevel, String userName, String comment,String estimateType);
	 boolean checkEstimateNoExist(String wtdestimateNo, String deptId,
				String webAppName);
	 public  List<Spstdesthmt> loadStandEstmateDetailsType(String costCenter,List<Long> status,String userName,String appType,String webAppName)throws Exception;
	 public List<EstimateDisplay> getEstimateListToBeValidateForPL(String deptId, List<Long> status, String username, String webAppName) throws Exception;
			
		


}
