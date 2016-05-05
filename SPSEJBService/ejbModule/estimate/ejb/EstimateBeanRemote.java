package estimate.ejb;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;


import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import piv.model.TempTb;

import job.model.Pcestdmt;
import job.model.Pcesthmt;
import job.model.Pcrstypm;

import costcenter.model.Gldeptm;

import estimate.dto.DetailEstimateDTO;
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

@Remote
@SuppressWarnings("rawtypes")
public interface EstimateBeanRemote {
	List<MaterialGrid> getMaterialGrid(String estimateNo, String deptId, String webAppName);
	MaterialGrid getMaterialGridRecord(String estimateNo, String deptId , String matCd, String webAppName);
	void insert(Pcesthtt pcesthtt, Speststd speststd, List<Pcestdtt> list, String webAppName);
	void update(Pcesthtt pcesthtt, Speststd speststd, List<Pcestdtt> addlist, List<Pcestdtt> updList, String webAppName);
	void delete(List<PcestdttPK> list, Pcesthtt inPcesthtt, String webAppName); 
	List<DefaultMatGrid> getDefaultList(Long phase, Long connectionType, String wiringType,String dept_id, String webAppName);
	ArrayList<ArrayList<String>> getAvailableOtherCosts(String deptId, ArrayList selMatSet, String webAppName) throws Exception;
	ArrayList<ArrayList<String>> getAvailableMaterials(String deptId, ArrayList selMatSet, String webAppName) throws Exception;
	LinkedHashMap<String, MaterialGrid> getDefaultMaterialGrid(String deptId, long phase, long conType, String wiringType, String webAppName);
	//List<Pcesthtt> getEstApprovalReport(String deptId, BigDecimal status, String value, String webAppName);
	List<Pcfunddm> getFundSourceList(String deptId, String webAppName);
	List<FundSource> getFundSource(String deptId, String webAppName);
	List<FundSource> getFundSourceJV(String deptId, String webAppName);
	ArrayList<ArrayList<String>> getNPLMaterials(String deptId, ArrayList selMatSet, String webAppName) throws Exception;
	//List<Pcesthtt> getEstApprovalReport1(String UserName, String deptId,BigDecimal status, String value, String webAppName);
	//List<EstimateDisplay> getEstApprovalReport(String userName, String deptId, String authorityLevel, String webAppName);
	void estimatePost(List<String> list, String costCenterNo, String webAppName);
	//List<MaterialGrid> getMaterialGridNew(String estimateNo, String deptId, String webAppName);
	//List<Pcesthtt> getEstimateList(String deptId, BigDecimal status, String webAppName);
	List<EstimateDisplay> getEstimateList(String deptId, BigDecimal status, String webAppName);
	List<EstimateDisplay> getEstimateList(String deptId, BigDecimal status,
			String applicationType, String webAppName);
	List<EstimateDisplay> getEstApprovalReport(String userName, String deptId, String authorityLevel, String applicationType, String webAppName);
	List<MaterialGrid> getConductorMaterialGrid(String deptId, long phase,
			long connectionType, String wiringType, String conductorType,
			Double conductorLength, String webAppName) throws PersistenceException;
	List<MaterialGrid> getServiceWireMaterialGrid(String deptId, long phase,
			long connectionType, String wiringType, Double serviceLength, String webAppName);
	List<MaterialGrid> getPoleTypeMaterialGrid(String deptId, long phase,
			String poleType, String fromConductor, String toConductor, int NoOfPoles, String webAppName);
	List<MaterialGrid> getStayMaterialGrid(String deptId, int noOfStays, String webAppName);
	List<MaterialGrid> getStrutMaterialGrid(String deptId, int noOfStruts, String webAppName);
	MaterialGrid getMaterialGridByMatCd(String deptId, String matCd,Double noOfItems, String webAppName);
	void insert(Pcesthtt pcesthtt, Speststd speststd, List<Pcestdtt> list,
			List<Spestlab> listLabour, String webAppName);
	void update(Pcesthtt pcesthtt, Speststd speststd, List<Pcestdtt> addlist,
			List<Pcestdtt> updList, List<Spestlab> addListLabour,
			List<Spestlab> updListLabour, String webAppName);
	void removeLabour(List<SpestlabPK> removeList,Pcesthtt pcesthtt, List<Pcestdtt> pcestdttList , Speststd speststd, String webAppName);
	void delete(String estimateNo, String deptId, Approval approval,String webAppName);
	List<LabourGrid> getDefaultPoleLabour(String deptId,
			String applicationType, String matCd, int noOfPoles,
			String webAppName);
	List<LabourGrid> getDefaultServiceWireLabour(String deptId,
			String applicationType, String phase, String span_type,
			String noOfSpans, String webAppName);
	List<LabourGrid> getDefaultConductorLabour(String deptId,
			String applicationType, String phase, String conductorType,
			String wireLength, String webAppName);
	List<LabourGrid> getDefaultStrutLabour(String deptId,
			String applicationType, String matCd, int noOfPoles,
			String webAppName);
	List<LabourGrid> getDefaultStayLabour(String deptId,
			String applicationType, String stayType, int noOfStays,
			String webAppName);
	List<LabourGrid> getDefaultPoleTransportLabour(String deptId,
			String applicationType, String matCd, int noOfPoles,Double distance ,
			String webAppName);
	void validateEstimate(Pcesthtt pcesthtt, Approval approval,
			String webAppName);
	void rejectEstimate(Pcesthtt pcesthtt, Approval approval, String webAppName);
	String transfer(Pcesthtt pcesthtt, List<Pcestdtt> list, String cSCNo,
			String webAppName);
	void updatePcprjbal(Pcesthtt pcesthtt, List<Pcestdtt> list,
			String webAppName);
	Spsecdep getSpsecdep(SpsecdepPK id, String webAppName);
	Splpsvcm getSplpsvcm(SplpsvcmPK id, String webAppName);
	Spugcblm getSpugcblm(SpugcblmPK id, String webAppName);
	LinkedHashMap<String, MaterialGrid> getDefaultLoopMaterialGrid(
			String deptId, long phase, long conType, String wiringType,
			String webAppName);
	List<EstimateDisplay> getEstApprovalStatusReport(String userName,
			String deptId, String authorityLevel, String applicationType,
			BigDecimal status, String isLoanApp, String webAppName);
	Spratesm getSpratesm(SpratesmPK id, String webAppName);
	void dojdfhdf(String webAppName);
	void doProjectNoUpdate(String webAppName);
	void doMatCostNoUpdate(String deptId,String resCd, int length,String webAppName);
	void doMatCostNoUpdateTem(String deptId, String resCd, int length,String webAppName);
	void removeMaterials(List<PcestdttPK> list, Pcesthtt inPcesthtt,
			Speststd speststd, String webAppName);
	void transferDTT(String estimateNo, String deptId, String webAppName);
	void estimateCostUpdateTem(String deptId, String jobType, String webAppName);
	void estimateCostUpdate(String deptId, String jobType, String webAppName);
	void updateLineLength(String webAppName);
	List<EstimateDisplay> getEstApprovalReportMT_SA(String userName,
			String deptId, String authorityLevel, String applicationType,
			String webAppName);
	List<EstimateDisplay> getEstimateListMA_SA(String deptId,
			BigDecimal status, String webAppName);
	String changeCostCenter(String jobNo,String fromDeptId,
			String toDeptId, String webAppName);
	public List<Spnorms> getAvailableNorms(String webAppName);
	public List<SpPegInfo> getPegChildrensByParentId(String parentId,String masterId,String webAppName);
	
	public List<String> loadApplicationRefnos(String userId,String costCenter,String webAppName);
	
	void insert(Spstdesthmt spstdesthmt, List<Spstdestdmt> lineTypelist,  String webAppName);
	public Spstdesthmt loadEstimateByApplicationNo(String costCenter,String applicationNo,
			String webAppName);

	public List<Spstdestdmt> loadEstimateDetailsByApplicaNo(String costCenter,String applicationNo, String webAppName);
	
	public int updateStdEstimateDetails(String applicationNo,BigDecimal secDeposit,BigDecimal totalCost, String webAppName);
	
	public int removeLineDetails(Spstdestdmt line, String webAppName);
	
	public int updateLineDetails(Spstdestdmt line, String webAppName);
	public void insertLineDetails(Spstdestdmt line, String webAppName);
	public List<String> loadStandEstmatenos(String costCenter,List<Long> status, String userName,String webAppName);
	public int updateSpestedyStatus(String status,String referenceNo, String webAppName);
	public BigDecimal getLineCost(SpnormsPK lineId,String webAppName);
	public List<SpPointdmt> getPegResourceById(String id,String masterId,String webAppName);
	
	public List<String> loadResourceTypes(String webAppName);
	public List<String> loadResourceCodes(String resourType,String webAppName);
	public Pcrsgrpm loadResourceDetails( String resourType,String code,String webAppName);
	public List<String> getFundSources(String deptId, String webAppName);
	public List<String> getFundIds(String deptId,String fundSource, String webAppName);
	public List<String> getCatCode(String deptId, String webAppName);
	public List<String> loadWarehouses(String deptId, String webAppName);
	public List<Pcrstypm> isResourceTypeExist(String resourType,String webAppName);
	public List<DetailEstimateDTO> getOtherResources(String deptId, String webAppName) throws Exception;
	public List<DetailEstimateDTO> getNPLMaterials(String deptId, String webAppName)throws Exception;
	public List<DetailEstimateDTO> getAvailableOtherMaterials(String deptId,String warehouse, String webAppName) throws Exception;
	public List<DetailEstimateDTO> getAvailableOtherMaterials(String deptId,String warehouse,List<String> matcd, String webAppName) throws Exception;
	public void insertDetailEst(Pcesthtt pcesthtt, List<Pcestdtt> list,TempTb tb ,List<Pegschdmt> peglist,String appointedUserName,String fileRef, String webAppName) throws Exception;
	public void addAdditionalCost(Pcesthtt pcesthtt,String estimateNo ,String deptId,BigDecimal toCost,String webAppName) throws Exception;
	public  void insertPIVDetails(TempTb tempTb,String webAppName)throws Exception;
	public  TempTb findPIVDetails(String estimationNo,String deptId,String webAppName)throws Exception;
	public void updateWorkEstimateDetails(Pcesthtt pcesthtt, Speststd speststd, List<Pcestdtt> addlist, List<Pcestdtt> updList,List<Pcestdtt> deleList,List<Pegschdmt> addedPegschdmtList,List<Pegschdmt> upadtedPegschdmtList,TempTb tempTb, String webAppName);
	public List<Spstdesthmt> loadStandEstmateDetails(String costCenter,List<Long> status,String userName,String webAppName);
	//public boolean updateEstimateStatus(String estimateNumber,String costCenter,Long status,String webAppName) throws PersistenceException;
	public void updateEstimateDetails(Spstdesthmt spstdesthmt,List<Spstdestdmt> insertlist,List<Spstdestdmt> updatelist,  String webAppName)throws PersistenceException;
	public boolean updateEstimateStatus(String estimateNumber,String costCenter,Long status,String approvedBy,Date approvedDate ,String rejectedBy,Date rejectedDate,String planningByPE,Date planningDatePE,String valiadteByEE,Date valiadteDateEE,
			String valiadteByCE,Date valiadteDateCE,String rejectedReasonCE,String rejectedReasonPE,
			String rejectedReasonEE,String webAppName) throws PersistenceException;
	public List<EstimateDisplay> getRejectedEstimateList(String deptId,Long status,String appType,String userId,String userLevel, String webAppName)throws PersistenceException;
	public List<EstimateDisplay> getApprovedEstimateList(String deptId,Long status,String appType,String userId, String webAppName)throws PersistenceException;
	public List<EstimateDisplay> getConfirmPIVList(String deptId,Long status,String appType,String userId, String webAppName)throws PersistenceException;
	
	public List<EstimateDisplay> getStdEstimateList(String deptId,List<Long> status,String appType,String userId, String webAppName)throws PersistenceException;
	public List<EstimateDisplay> getEstimateListToBeApproved(String deptId,List<Long> status,String appType,String userId, String webAppName)throws PersistenceException;
	public List<EstimateDisplay> getEstimateListToBeValidate(String deptId,List<Long> status,String appType,String username, String webAppName)throws PersistenceException;
	public List<EstimateDisplay> getEstimateListToBeValidateForPL(String deptId,List<Long> status,String username, String webAppName)throws PersistenceException;
	
	public String getWorkEstimateNo(String estimateNoPrefix,String stdEstimateNo,String deptId, String webAppName)throws PersistenceException;
	public List<Pcesthtt> getDetailEstimatesList(String deptId,List<Long> status, String userId,String assignTo, String webAppName)throws PersistenceException;
	public List<Pcesthtt> getDetailEstimatesListOrderByFundSource(String deptId,List<Long> status, String userId,String assignTo, String webAppName)throws PersistenceException;
	
	public void updateDetailEstimateCost(String estimateNo,String deptId,String estimateType,String webAppName) throws PersistenceException;
	public void updateStandardEstimateCost(String estimateNo,String deptId,String webAppName) throws PersistenceException;
	public String getNextPegItemId(String parentId,String masterId,
			String webAppName);
	public String transferToHmt(Pcesthtt pcesthtt, List<Pcestdtt> list, String cSCNo,String newJobNo, String webAppName)throws PersistenceException;
	
	String approveStandardEstimate(String estimateNo, String deptId,	String authorityLevel, String userName, String assignedUserName, String applicationType,String webAppName);
	String rejectStandardEstimate(String estimateNo,  Approval approval, String webAppName);
	List<Pcfunddm> getFundSourcesList(String deptId, String webAppName);
	/*List<EstimateTypeMaster> getEstimateTypesbyDeptId(String deptId, String webAppName)
	throws PersistenceException;*/
	List<String> getFirstFiftyJobList(String deptId,List<Long> status, String webAppName);
	public List<Spstdesthmt> loadConMainSentList(String costCenter,String postID,List<Long> status,String webAppName);
	BigDecimal getTotalConsumerPayable(String estimateNo,String resType,String deptId,String webAppName);
	List<SpNormsGroup> getChildrensByParentId(String parentId,String webAppname);
	List<SpNormsGroup> getAllSpNormsGroups(String webAppname);
	List<Spnorms> getNormsChildrensByParentId(String parentId,String webAppname);
	List<AllocationSummaryDisplay> findEstimateSummary(String applicationNo,String deptId, String webAppName)
	throws PersistenceException;
	List<Pcesthtt> getVSLEstimatesList(String deptId,List<Long> status, String userId, String webAppName) ;
	
	//String recommendStandardEstimate(String estimateNo, String deptId,	String authorityLevel, String userName, String assignedUserName, String applicationType,String webAppName);
	String recommendStandardEstimate(String estimateNo, String deptId,	String authorityLevel, String userName,String comment, String assignedUserName, String applicationType,String sinNo,String webAppName);
	
	
	
	public List<Pcesthtt> getEstimateListForAuthorizedCC(Long revNo, List<Long> status, String userId,String likeEstimate, String webAppName);
	
	public int deleteEstimate(Pcesthtt htt,List<Pcestdtt> dtt,TempTb temptb,EstimateReference ref, String webAppName)throws Exception;
	String updateAndValidate(Pcesthtt pcesthtt, List<Pcestdtt> addlist, List<Pcestdtt> updList,List<Pcestdtt> deleList,List<Pegschdmt> addedPegschdmtList,List<Pegschdmt> updatedPegschdmtList,TempTb tempTb, String authorityLevel, String userName,String comment,String estimateType,String webAppName);
	boolean checkEstimateNoExist(String wtdestimateNo,String deptId,String webAppName);
	public List<Spstdesthmt> loadStandEstmateDetailsType(String costCenter,List<Long> status,String userName,String appType,String webAppName);
	

}
