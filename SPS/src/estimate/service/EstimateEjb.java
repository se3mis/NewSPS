package estimate.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import piv.model.TempTb;

import job.model.Pcestdmt;
import job.model.Pcesthmt;
import job.model.Pcrstypm;



import estimate.dto.DetailEstimateDTO;
import estimate.ejb.EstimateBeanRemote;
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


public class EstimateEjb implements EstimateEjbI {
	private Context context;
	private EstimateBeanRemote bean;
	private String region=null;
	
	public EstimateEjb(String region) {
		super();
		this.region=region;
		this.bean=lookupBean();
		
	}

	private EstimateBeanRemote lookupBean() {
		try
		{
			 context = new InitialContext();
			 EstimateBeanRemote bean = (EstimateBeanRemote) context.lookup("EstimateBean/remote");
			 return bean; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	

	@Override
	public List<MaterialGrid> getMaterialGrid(String estimateNo, String deptId) {
		return bean.getMaterialGrid(estimateNo, deptId, region);
	}

	@Override
	public MaterialGrid getMaterialGridRecord(String estimateNo, String deptId, String matCd) {
		return bean.getMaterialGridRecord(estimateNo, deptId, matCd, region);
	}
	
	@Override
	public void insert(Pcesthtt pcesthtt, Speststd speststd, List<Pcestdtt> list) {
		bean.insert(pcesthtt, speststd, list, region);
		
	}
	
	@Override
	public void update(Pcesthtt pcesthtt, Speststd speststd, List<Pcestdtt> addlist, List<Pcestdtt> updList) {
		bean.update(pcesthtt, speststd, addlist, updList, region);
		
	}

	@Override
	public void delete(List<PcestdttPK> list, Pcesthtt pcesthtt ) {
		bean.delete(list,pcesthtt, region);
		
	}
	
	@Override
	public List<DefaultMatGrid> getDefaultList(Long phase, Long connectionType , String wiringType, String deptId) {
		return bean.getDefaultList(phase, connectionType, wiringType, deptId, region);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public ArrayList<ArrayList<String>> getAvailableOtherCosts(String deptId, ArrayList selMatSet) throws Exception{
		return bean.getAvailableOtherCosts(deptId, selMatSet, region);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ArrayList<ArrayList<String>> getAvailableMaterials(String deptId, ArrayList selMatSet) throws Exception{
		return bean.getAvailableMaterials(deptId, selMatSet, region);
	}

	@Override
	public LinkedHashMap<String, MaterialGrid> getDefaultMaterialGrid(String deptId, long phase, long conType, String wiringType) {
		return bean.getDefaultMaterialGrid(deptId, phase, conType, wiringType, region);
	}
	
	@Override
	public List<Pcfunddm> getFundSourceList(String deptId) {
		return bean.getFundSourceList(deptId, region);
	}
	
	@Override
	public List<FundSource> getFundSource(String deptId) {
		return bean.getFundSource(deptId, region);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public ArrayList<ArrayList<String>> getNPLMaterials(String deptId, ArrayList selMatSet) throws Exception {
		return bean.getNPLMaterials(deptId, selMatSet, region);
	}
	
	

	//@Override
	//public List<Pcesthtt> getEstApprovalReport1(String UserName, String deptId, BigDecimal status, String value) {
	//	return bean.getEstApprovalReport1(UserName, deptId, status, value, region);
	//}

	//@Override
	//public List<EstimateDisplay> getEstApprovalReport(String userName, String deptId, 	String authorityLevel) {
	//	return bean.getEstApprovalReport(userName, deptId, authorityLevel, region);
	//}

	@Override
	public void estimatePost(List<String> list, String costCenterNo) {
		bean.estimatePost(list, costCenterNo, region);

	}

	@Override
	public List<EstimateDisplay> getEstimateList(String deptId, BigDecimal status) {
		
		return bean.getEstimateList(deptId, status, region);
	}

	@Override
	public List<EstimateDisplay> getEstApprovalReport(String userName, String deptId, String authorityLevel, String applicationType) {
		return bean.getEstApprovalReport(userName, deptId, authorityLevel, applicationType, region);
	}

	@Override
	public List<EstimateDisplay> getEstimateList(String deptId, BigDecimal status, String applicationType) {
		return bean.getEstimateList(deptId, status, applicationType, region);
	}

	@Override
	public List<MaterialGrid> getConductorMaterialGrid(String deptId,
			long phase, long connectionType, String wiringType,
			String conductorType, Double conductorLength)
			throws PersistenceException {
		return bean.getConductorMaterialGrid(deptId, phase, connectionType, wiringType, conductorType, conductorLength, region);
	}

	@Override
	public List<MaterialGrid> getServiceWireMaterialGrid(String deptId,
			long phase, long connectionType, String wiringType,
			Double serviceLength) {
		return bean.getServiceWireMaterialGrid(deptId, phase, connectionType, wiringType, serviceLength, region);
	}

	@Override
	public List<MaterialGrid> getPoleTypeMaterialGrid(String deptId,
			long phase, String poleType, String fromConductor,
			String toConductor, int noOfPoles) {
		return bean.getPoleTypeMaterialGrid(deptId, phase, poleType, fromConductor, toConductor, noOfPoles, region);
	}

	@Override
	public List<MaterialGrid> getStrutMaterialGrid(String deptId, int noOfStruts) {
		return bean.getStrutMaterialGrid(deptId, noOfStruts, region);
	}

	@Override
	public List<MaterialGrid> getStayMaterialGrid(String deptId, int noOfStays) {
		return bean.getStayMaterialGrid(deptId, noOfStays, region);
	}

	@Override
	public MaterialGrid getMaterialGridByMatCd(String deptId, 
			String matCd, Double noOfItems) {
		return bean.getMaterialGridByMatCd(deptId, matCd, noOfItems, region);
	}

	@Override
	public void insert(Pcesthtt pcesthtt, Speststd speststd,
			List<Pcestdtt> list, List<Spestlab> listLabour) {
		bean.insert(pcesthtt, speststd, list, listLabour, region);
		
	}

	@Override
	public void update(Pcesthtt pcesthtt, Speststd speststd,
			List<Pcestdtt> addlist, List<Pcestdtt> updList,
			List<Spestlab> addLabourList, List<Spestlab> updLabourList) {
		bean.update(pcesthtt, speststd, addlist, updList, addLabourList, updLabourList, region);
		
	}

	@Override
	public void removeLabour(List<SpestlabPK> removeList, Pcesthtt pcesthtt, List<Pcestdtt> pcestdttList, Speststd speststd) {
		bean.removeLabour(removeList, pcesthtt, pcestdttList, speststd, region);
		
	}
	
	@Override
	public void delete(String estimateNo, String deptId, Approval approval) {
		bean.delete( estimateNo,deptId, approval, region);
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EstimateEjb bean=new EstimateEjb("region");
		//System.out.println(bean.getMaterialGridRecord("ABC/2/2006", "501.20","00101"));
		//System.out.println(bean.getMaterialGrid("ABC/2/2006", "501.20"));
		//System.out.println("A0203 "+bean.getDefaultStrutLabour("514.20", "NC", "A0203", 10));
		//System.out.println("xxxx : "+ bean.loadStandEstmateDetails("501.20","","","",""));
		//System.out.println("xxxx : "+bean.getFundSource("514.20"));


	}

	@Override
	public List<LabourGrid> getDefaultPoleLabour(String deptId,
			String applicationType, String matCd, int noOfPoles) {
		return bean.getDefaultPoleLabour(deptId, applicationType, matCd, noOfPoles, region);
	}
	
	@Override
	public List<LabourGrid> getDefaultStrutLabour(String deptId,
			String applicationType, String matCd, int noOfPoles) {
		return bean.getDefaultStrutLabour(deptId, applicationType, matCd, noOfPoles, region);
	}
	
	@Override
	public List<LabourGrid> getDefaultStayLabour(String deptId,
			String applicationType, String stayType, int noOfStays) {
		return bean.getDefaultStayLabour(deptId, applicationType, stayType, noOfStays, region);
	}

	@Override
	public List<LabourGrid> getDefaultPoleTransportLabour(String deptId,
			String applicationType, String matCd, int noOfPoles, Double distance) {
		return bean.getDefaultPoleTransportLabour(deptId, applicationType, matCd, noOfPoles, distance, region);
	}

	@Override
	public List<LabourGrid> getDefaultServiceWireLabour(String deptId,
			String applicationType, String phase, String span_type,
			String wireLength) {
		return bean.getDefaultServiceWireLabour(deptId, applicationType, phase, span_type, wireLength, region);
	}

	@Override
	public List<LabourGrid> getDefaultConductorLabour(String deptId,
			String applicationType, String phase, String conductorType,
			String wireLength) {
		return bean.getDefaultConductorLabour(deptId, applicationType, phase, conductorType, wireLength, region);
	}

	@Override
	public String transfer(Pcesthtt pcesthtt, List<Pcestdtt> list,String cSCNo) {
		return bean.transfer(pcesthtt, list, cSCNo, region);
		
	}

	@Override
	public Spsecdep getSpsecdep(SpsecdepPK id) {
		return bean.getSpsecdep(id, region);
		
	}

	@Override
	public Splpsvcm getSplpsvcm(SplpsvcmPK id) {
		return bean.getSplpsvcm(id, region);
		
	}

	@Override
	public Spugcblm getSpugcblm(SpugcblmPK id) {
		return bean.getSpugcblm(id, region);
		
	}

	@Override
	public LinkedHashMap<String, MaterialGrid> getDefaultLoopMaterialGrid(
			String deptId, long phase, long conType, String wiringType) {
		return bean.getDefaultLoopMaterialGrid(deptId, phase, conType, wiringType, region);
	}

	@Override
	public List<EstimateDisplay> getEstApprovalStatusReport(String userName,
			String deptId, String authorityLevel, String applicationType,
			BigDecimal status, String isLoanApp) {
		return bean.getEstApprovalStatusReport(userName, deptId, authorityLevel, applicationType, status, isLoanApp, region);
	}

	@Override
	public Spratesm getSpratesm(SpratesmPK id) {
		return bean.getSpratesm(id, region);
	}

	@Override
	public void removeMaterials(List<PcestdttPK> list, Pcesthtt inPcesthtt,
			Speststd speststd) {
		bean.removeMaterials(list, inPcesthtt, speststd, region);
		
	}

	@Override
	public List<EstimateDisplay> getEstApprovalReportMT_SA(String userName,
			String deptId, String authorityLevel, String applicationType) {
		return bean.getEstApprovalReportMT_SA(userName, deptId, authorityLevel, applicationType, region);
	}

	@Override
	public List<EstimateDisplay> getEstimateListMA_SA(String deptId,
			BigDecimal status) {
		return bean.getEstimateListMA_SA(deptId, status, region);
	}

	@Override
	public List<Spnorms> getAvailableNorms() throws Exception{
		return bean.getAvailableNorms(region);
	}

	@Override
	public List<SpPegInfo> getPegChildrensByParentId(String parentId,String masterId) throws Exception {
		return bean.getPegChildrensByParentId(parentId ,masterId,region);
	}
	

	
	
	@Override
	public List<String> loadApplicationRefnos(String userId, String costCenter) {
		return bean.loadApplicationRefnos(userId, costCenter,region);
	}
	

	@Override
	public void insert(Spstdesthmt spstdesthmt,List<Spstdestdmt> lineTypelist ,
			String webAppName) {
		bean.insert(spstdesthmt, lineTypelist,region);
		
	}
	
	public Spstdesthmt loadEstimateByApplicationNo(String costCenter,String applicationNo,String webAppName){
		return bean.loadEstimateByApplicationNo(costCenter,applicationNo,region);
	}
	public List<Spstdestdmt> loadEstimateDetailsByApplicaNo(String costCenter,String applicationNo, String webAppName){
		return bean.loadEstimateDetailsByApplicaNo(costCenter,applicationNo,region);
	}
	
	public int  removeLineDetails(Spstdestdmt line, String webAppName){
		 return bean.removeLineDetails(line,region);
	}
	public int updateLineDetails(Spstdestdmt line, String webAppName){
		 return bean.updateLineDetails(line,region);
	}
	
	public void updateStdEstimateDetails(String applicationNo,BigDecimal secDeposit,BigDecimal totalCost ,String webAppName){
		 bean.updateStdEstimateDetails(applicationNo,secDeposit,totalCost,region);
	}
	public void insertLineDetails(Spstdestdmt line, String webAppName){
		 bean.insertLineDetails(line,region);
	}

	@Override
	public List<String> loadStandEstmatenos(String costCenter,List<Long> status, String webAppName) {
		return bean.loadStandEstmatenos(costCenter,status,null,region);
	}

	
	public BigDecimal getLineCost(SpnormsPK lineId,String webAppName){
		return bean.getLineCost(lineId,region);
	}

	@Override
	public List<SpPointdmt> getPegResourceById(String id,String masterId, String webAppName) {
		return bean.getPegResourceById(id,masterId,region);
	}
	
	public List<String> loadResourceTypes(String webAppName){
		return bean.loadResourceTypes(webAppName);
	}
	public List<String> loadResourceCodes(String resourceType,String webAppName){
		return bean.loadResourceCodes(resourceType,webAppName);
	}
	public Pcrsgrpm loadResourceDetails(String resourceType,String resourceCode,String webAppName){
		return bean.loadResourceDetails(resourceType,resourceCode,webAppName);
	}

	@Override
	public List<String> loadWarehouses(String deptId, String webAppName) {
		return bean.loadWarehouses(deptId,webAppName);
	}
	
	public List<String> getFundSources(String deptId, String webAppName){
		return bean.getFundSources(deptId,webAppName);
	}
	public List<Pcfunddm> getFundSourcesList(String deptId, String webAppName){
		return bean.getFundSourcesList(deptId,webAppName);
	}
	
	public List<String> getFundIds(String deptId,String fundSource, String webAppName){
		return bean.getFundIds(deptId,fundSource,webAppName);
	}
	
	public List<String> getCatCode(String wareSource, String webAppName){
		return bean.getCatCode(wareSource,webAppName);
	}
	public List<Pcrstypm> isResourceTypeExist(String resourType, String webAppName){
		return bean.isResourceTypeExist(resourType,webAppName);
	}
	public List<DetailEstimateDTO> getOtherResources(String deptId, String webAppName) throws Exception{
		return bean.getOtherResources(deptId,webAppName);
	}
	 public List<DetailEstimateDTO> getNPLMaterials(String deptId, String webAppName)throws Exception{
		 return bean.getNPLMaterials(deptId,webAppName);
	 }
	 public List<DetailEstimateDTO> getAvailableOtherMaterials(String deptId,String warehouse, String webAppName) throws Exception{
		 return bean.getAvailableOtherMaterials(deptId,warehouse,webAppName);
	 }
	 
	 public List<DetailEstimateDTO> getAvailableOtherMaterials(String deptId,String warehouse,List<String> list, String webAppName) throws Exception{
		 return bean.getAvailableOtherMaterials(deptId,warehouse,list,webAppName);
		 //return null;
	 }

	@Override
	public List<DetailEstimateDTO> getOtherMaterials(String deptId,
			String webAppName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertDetailEst(Pcesthtt pcesthtt,List<Pcestdtt> list,TempTb tb ,List<Pegschdmt> peglist,String appointedUserName,String fileRef, String webAppName) throws Exception {
		bean.insertDetailEst(pcesthtt,list,tb,peglist,appointedUserName,fileRef,webAppName);
		
	}
	@Override
	public void addAdditionalCost(Pcesthtt pcesthtt,String estimateNo ,String deptId,BigDecimal toCost,String webAppName) throws Exception {
		bean.addAdditionalCost(pcesthtt,estimateNo,deptId,toCost,webAppName);
		
	}

	@Override
	public void insertPIVDetails(TempTb tempTb, String webAppName)
			throws Exception {
		bean.insertPIVDetails(tempTb,webAppName);
		
		
	}
	public  TempTb findPIVDetails(String estimationNo,String deptId,String webAppName)throws Exception{
		return bean.findPIVDetails(estimationNo,deptId, webAppName);
	}

	@Override
	public void updateWorkEstimateDetails(Pcesthtt pcesthtt, Speststd speststd,
			List<Pcestdtt> addlist, List<Pcestdtt> updList,
			List<Pcestdtt> deleList,List<Pegschdmt> addedPegschdmtList,List<Pegschdmt> upadtedPegschdmtList,TempTb temptb, String webAppName) throws Exception {
		bean.updateWorkEstimateDetails(pcesthtt,speststd,addlist,updList,deleList, addedPegschdmtList,upadtedPegschdmtList,temptb,webAppName);
		
	}

	@Override
	public List<Spstdesthmt> loadStandEstmateDetails(String costCenter,
			List<Long> status,String userName, String webAppName) throws Exception {
		return bean.loadStandEstmateDetails(costCenter,status,userName,webAppName);
	}

	@Override
	public boolean updateEstimateStatus(String estimateNumber,
			String costCenter, Long status,String approvedBy,Date approvedDate ,String rejectedBy,
			Date rejectedDate,String planningByPE,Date planningDatePE,String valiadteByEE,Date valiadteDateEE,String valiadteByCE,Date valiadteDateCE,String rejectedReasonCE,String rejectedReasonPE,
			String rejectedReasonEE, String webAppName) {
		return bean.updateEstimateStatus(estimateNumber,costCenter,status,approvedBy,approvedDate,rejectedBy,rejectedDate,planningByPE,planningDatePE,valiadteByEE,valiadteDateEE,valiadteByCE,valiadteDateCE,rejectedReasonCE,rejectedReasonPE,rejectedReasonEE,webAppName);
	}

	@Override
	public List<EstimateDisplay> getRejectedEstimateList(String deptId,
			Long status, String appType, String userId, String userLevel,String webAppName) {
		// TODO Auto-generated method stub
		return bean.getRejectedEstimateList(deptId,status,appType,userId,userLevel,webAppName);
	}
	@Override
	public List<EstimateDisplay> getApprovedEstimateList(String deptId,
			Long status, String appType, String userId, String webAppName)
			throws Exception {
		return bean.getApprovedEstimateList(deptId,status,appType,userId,webAppName);
	}

	@Override
	public List<EstimateDisplay> getEstimateListToBeApproved(String deptId,
			List<Long> status, String appType, String userId, String webAppName)
			throws Exception {
		return bean.getEstimateListToBeApproved(deptId,status,appType,userId,webAppName);
	}

	@Override
	public List<EstimateDisplay> getEstimateListToBeValidate(String deptId,
			List<Long> status, String appType,String userName, String webAppName)
			throws Exception {
		return bean.getEstimateListToBeValidate(deptId,status,appType,userName,webAppName);
	}

	@Override
	public void updateEstimateDetails(Spstdesthmt spstdesthmt,
			List<Spstdestdmt> insertlist, List<Spstdestdmt> updatelist,
			String webAppName) throws Exception {
		bean.updateEstimateDetails(spstdesthmt,insertlist,updatelist,webAppName);
		
	}

	@Override
	public String getWorkEstimateNo(String estimateNoPrefix,
			String stdEstimateNo, String deptId, String webAppName)
			throws Exception {
		
		return bean.getWorkEstimateNo(estimateNoPrefix,stdEstimateNo,deptId,webAppName);
	}
	@Override
	public List<Pcesthtt> getDetailEstimatesList(String deptId, List<Long> status,
			String userId,String assignTo, String webAppName) throws Exception {
		
		return bean.getDetailEstimatesList(deptId,status,userId,assignTo,webAppName);
	}
	@Override
	public void updateDetailEstimateCost(String estimateNo,String estimateType, String deptId,
			String webAppName) throws Exception {
		bean.updateDetailEstimateCost(estimateNo,estimateType,deptId,webAppName);
		
	}

	@Override
	public void updateStandardEstimateCost(String estimateNo, String deptId,
			String webAppName) throws Exception {
		bean.updateStandardEstimateCost(estimateNo,deptId,webAppName);
		
	}

	/*@Override
	public void updateReviseDetails(Pcesthmt pcesthmt, List<Pcestdmt> addlist,
			List<Pcestdmt> updList, List<Pcestdmt> deleList, String webAppName)
			throws Exception {
		bean.updateReviseDetails(pcesthmt,addlist,updList,deleList,webAppName);
		
	}*/
	public String getNextPegItemId(String parentId,String masterId,
			String webAppName){
		return bean.getNextPegItemId(parentId,masterId,webAppName);
	}
	@Override
	public String changeCostCenter(String jobNo, String fromDeptId, String toDeptId) {
		return bean.changeCostCenter(jobNo, fromDeptId, toDeptId, region);
	}
	

	@Override
	public String transferToHmt(Pcesthtt pcesthtt, List<Pcestdtt> list,
			String cSCNo, String newJobNo) throws Exception {
		return bean.transferToHmt(pcesthtt,list,cSCNo,newJobNo,region);
	}
	
	@Override
	public String approveStandardEstimate(String estimateNo, String deptId,
			String authorityLevel, String userName, String userNamePostedTo, String applicationType)
			throws Exception {
		// TODO Auto-generated method stub
		return bean.approveStandardEstimate(estimateNo,deptId,authorityLevel,userName,userNamePostedTo,applicationType,region);
	}
	

	@Override
	public String rejectStandardEstimate(String estimateNo, Approval approval)
			throws Exception {
		return bean.rejectStandardEstimate(estimateNo,approval,region);
	}
	public List<String> getFirstFiftyJobList(String deptId,List<Long> status){
		return bean.getFirstFiftyJobList(deptId,status,region);
	}
	@Override
	public List<Spstdesthmt> loadConMainSentList(String costCenter,
			String postID, List<Long> status) {
		return bean.loadConMainSentList(costCenter,postID,status, region);
		// TODO Auto-generated method stub
		
	}
	@Override
	public BigDecimal getTotalConsumerPayable(String estimateNo,
			String resType, String deptId) {
		return bean.getTotalConsumerPayable(estimateNo,resType,deptId, region);
	}
	/*@Override
	public List<EstimateTypeMaster> getEstimateTypesbyDeptId(String deptId, String webAppName)
			throws Exception {
		
		return bean.getEstimateTypesbyDeptId(deptId, webAppName);
	}*/
	@Override
	public List<SpNormsGroup> getChildrensByParentId(String parentId) {
		// TODO Auto-generated method stub
		return bean.getChildrensByParentId(parentId, region);
	}

	public List<SpNormsGroup> getAllSpNormsGroups(String webAppname) {
		// TODO Auto-generated method stub
		return bean.getAllSpNormsGroups(webAppname);
	}
	public List<Spnorms> getNormsChildrensByParentId(String parentId){
		return bean.getNormsChildrensByParentId(parentId,region);
	}
	@Override
	public List<AllocationSummaryDisplay> findEstimateSummary(
			String applicationNo, String deptId)
			 {
		// TODO Auto-generated method stub
		return bean.findEstimateSummary(applicationNo, deptId, region);
	}
	@Override
	public List<String> loadStandEstmatenos(String costCenter,List<Long> status,String userName,String webAppName) {
		return bean.loadStandEstmatenos(costCenter,status,userName, webAppName);
	}
	@Override
	public List<Pcesthtt> getVSLEstimatesList(String deptId, List<Long> status,
			String userId) {
		// TODO Auto-generated method stub
		return bean.getVSLEstimatesList(deptId, status, userId, region);
	}
	@Override
	public String recommendStandardEstimate(String estimateNo, String deptId,String authorityLevel, String userName,String com, String assignedUserName,String applicationType,String sinNo) {
		// TODO Auto-generated method stub
		return bean.recommendStandardEstimate(estimateNo, deptId, authorityLevel, userName,com, assignedUserName, applicationType, sinNo,region);
	}
	@Override
	public List<Pcesthtt> getEstimateListForVSLAuthorizedCC(
			Long revNo, List<Long> status, String userId, String likeEstimate) {
		// TODO Auto-generated method stub
		return bean.getEstimateListForAuthorizedCC(revNo, status, userId,likeEstimate, region);
	}
	@Override
	public int deleteEstimate(Pcesthtt htt,List<Pcestdtt> dtt,TempTb temptb,EstimateReference ref)
			throws Exception {
		return bean.deleteEstimate(htt,dtt,temptb,ref,region);
		
	}
	@Override
	public String updateAndValidate(Pcesthtt pcesthtt, List<Pcestdtt> addlist,
			List<Pcestdtt> updList, List<Pcestdtt> deleList,
			List<Pegschdmt> addedPegschdmtList,
			List<Pegschdmt> updatedPegschdmtList, TempTb tempTb,
			String authorityLevel, String userName, String comment,String estimateType) {
		return bean.updateAndValidate(pcesthtt, addlist, updList, deleList, addedPegschdmtList, updatedPegschdmtList, tempTb, authorityLevel, userName, comment, estimateType, region);
	}
	@Override
	public boolean checkEstimateNoExist(String wtdestimateNo, String deptId,
			String webAppName) {
		return bean.checkEstimateNoExist(wtdestimateNo, deptId, webAppName);
	}

	@Override
	public List<FundSource> getFundSourceJV(String deptId) {
		// TODO Auto-generated method stub
		 return bean.getFundSourceJV(deptId,region);
	}
	
	@Override
	public List<Spstdesthmt> loadStandEstmateDetailsType(String costCenter,
			List<Long> status, String userName, String appType,
			String webAppName) throws Exception {
		return bean.loadStandEstmateDetailsType(costCenter,status,userName,appType,webAppName);
	}

	@Override
	public List<Pcesthtt> getDetailEstimatesListOrderByFundSource(
			String deptId, List<Long> status, String userId, String assignTo,
			String webAppName) throws Exception {
		return bean.getDetailEstimatesListOrderByFundSource(deptId,status,userId,assignTo,webAppName);
	}

	@Override
	public List<EstimateDisplay> getStdEstimateList(String deptId,
			List<Long> status, String appType, String userId, String webAppName)
			throws Exception {
		// TODO Auto-generated method stub
		return bean.getStdEstimateList(deptId,status,appType,userId,webAppName);
		//return null;
	}
	
	@Override
	public List<EstimateDisplay> getConfirmPIVList(String deptId, Long status,
			String appType, String userId, String webAppName)
			throws PersistenceException {
		// TODO Auto-generated method stub
		return bean.getConfirmPIVList(deptId, status, appType, userId, webAppName);
	}
	
	@Override
	public List<EstimateDisplay> getEstimateListToBeValidateForPL(String deptId, List<Long> status, String username, String webAppName)	throws PersistenceException {
		// TODO Auto-generated method stub
		return bean.getEstimateListToBeValidateForPL(deptId,status,username,webAppName);
		
	}
	


}


