package estimate.service;

import java.math.BigDecimal; 
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import piv.model.TempTb;

import costcenter.model.Gldeptm;

import job.model.Pcestdmt;
import job.model.Pcesthmt;
import job.model.Pcrstypm;
import job.service.PcesthmtEjb;

import util.common.EstimateStatus;
import util.common.Format;
import util.common.StandardEstimateStatus;


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

public class EstimateEjb implements EstimateBeanRemote{ 
	private Context context;
	private EstimateBeanRemote bean; 
	public EstimateEjb() {
		super();
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

	public static void main(String[] args){
		EstimateEjb bean=new EstimateEjb();
		List<String> list=new ArrayList<String>();
		list.add("541.20/ENC/2011/0001");
		List<EstimateDisplay> tesmpList = null;
		
		System.out.println("E : "+  bean.getConfirmPIVList("501.00",55L,"BS",null,"Testing"));
		//tesmpList   =  bean.getApprovedEstimateList("510.20",StandardEstimateStatus.APPROVED_ESTIMATES.getKey(), "BS", null,"TEST");
		/**try {
			//System.out.println("E "+bean.getAvailableMaterials("441.40", null, "R2"));
			//System.out.println("E "+bean.getDefaultLoopMaterialGrid("541.00", new Long(1), new Long(30), "UG", "R1"));
			
			//System.out.println("E "+bean.getMaterialGridByMatCd("554.10", "A0203", new Double("1"), "R2"));
			//System.out.println(bean.getDefaultMaterialGrid("554.10",  new Long(1), new Long(30), "OH", "R3"));
			System.out.println(bean.getServiceWireMaterialGrid("554.10",  new Long(1), new Long(30), "OH",  new Double(26), "R3"));
			System.out.println(bean.getConductorMaterialGrid("554.10",  new Long(1), new Long(30), "OH", "FLY",  new Double(26), "R3"));
			System.out.println("@ " +bean.getPoleTypeMaterialGrid("443.50", new Long(1), "INTERMEDIATE", "FLY", "FLY", 4, "R2"));
			System.out.println(bean.getStrutMaterialGrid("423.50", 2, "R1"));
			System.out.println(bean.getStayMaterialGrid("423.50", 1, "R1"));
			//System.out.println("## "+bean.getConductorMaterialGrid("423.50", new Long(1), new Long(30), "OH", "ABC", new Double(60), "R2"));
			//System.out.println("1 "+bean.getDefaultConductorLabour("423.50", "NC", "1", "ABC", "10", "R2"));
			//System.out.println("2 "+bean.getDefaultServiceWireLabour("423.50", "NC", "1", "LAST", "10", "R2"));
			//System.out.println("3 "+bean.getDefaultPoleLabour("423.50", "NC", "A0203", 10, "R2"));
			//System.out.println("4 "+bean.getDefaultPoleTransportLabour("423.50", "NC", "A0211", 10, new Double(10), "R2"));
			//System.out.println("5 "+bean.getDefaultStrutLabour("423.50", "NC", "A0203", 10, "R2"));
			//System.out.println("6 "+bean.getDefaultStayLabour("423.50", "NC", "NORMAL", 10, "R2"));
			//System.out.println("@ "+bean.getMaterialGridByMatCd("423.50", "A0203", new Double(4), "R2"));
			//System.out.println(bean.getEstimateList("423.50", new BigDecimal(EstimateStatus.EST_REJECTED), "NC", "R2"));
			//System.out.println(bean.getPoleTypeMaterialGrid("423.50", new Long(1), "INTERMEDIATE", "FLY", "FLY", 4, "R2"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		//PcesthmtEjb pcesthmtEjb=new PcesthmtEjb();
		PcestdttEjb pcestdttEjb=new PcestdttEjb();
		//Pcesthtt pcesthtt=pcesthttEjb.findByApplicationNo("514.20/ENC/2011/0823", "514.20", "kjbckzb");
		//bean.updateLineLength("R4");
		//Pcesthmt pcesthmt=pcesthmtEjb.findByJobNo("514.20/SMC/11/0011", "514.20", "kjbckzb");
		//PcestdttPK id=new PcestdttPK();
		//id.setEstimateNo("514.20/ENC/2011/0019");
		//System.out.println("pcesthtt "+pcesthtt);
		//System.out.println("pcesthmt "+pcesthmt);
		//List<Pcestdtt> list2=pcestdttEjb.findByEstimationNo("514.20/ENC/2011/0823", "514.20", "kjbckzb");
		//System.out.println("list2 "+list2);
		//bean.updatePcprjbal(pcesthtt, list2, "kjbckzb");
		//bean.transfer(pcesthtt, list2, "kjbckzb");
		//System.out.println("lvjnjkv "+bean.getFundSourceList("517.50", "R3"));
		
		//bean.doProjectNoUpdate("R1");
		//bean.dojdfhdf("R1");
		
		/*		
		System.out.println("LABOUR DTT");
		bean.matCostUpdateDTT("LABOUR");
		System.out.println("D0610 DTT");
		bean.matCostUpdateDTT("D0610");
		System.out.println("LABOUR DMT");
		bean.matCostUpdateDMT("LABOUR");
		System.out.println("D0610 DMT");
		bean.matCostUpdateDMT("D0610");
		System.out.println("FINISHED");
		*/
		
		/*System.out.println("ENC DTT");
		bean.estimateCostUpdateDTT("ENC");
		System.out.println("ETC DTT");
		bean.estimateCostUpdateDTT("ETC");
		System.out.println("ECR DTT");
		bean.estimateCostUpdateDTT("ECR");
		System.out.println("ENC DMT");
		bean.estimateCostUpdateDMT("ENC");
		System.out.println("ETC DMT");
		bean.estimateCostUpdateDMT("ETC");
		System.out.println("ECR DMT");
		bean.estimateCostUpdateDMT("ECR");
		System.out.println("FINISHED");
        */
		//
	    
		//bean.transferDTT("422.40/ENC/2011/0116", "422.40", "R1");
		
		/*bean.transferDTT("422.40/ENC/2011/0212", "422.40", "R1");
			bean.transferDTT("422.40/ENC/2011/0155", "422.40", "R1");
	
		bean.transferDTT("422.20/ENC/2011/0643", "422.20", "R1");
		
		*/

		
		
		
		/**System.out.println("dfgdfgdfgdfgdfgdgdfgdg ");
		bean.doMatCostNoUpdateTem("514.20", "D0610", 100, "dfgdfg");
		Calendar calendar = Calendar.getInstance();
		Format format=new Format();
		Approval approval=new Approval();
		approval.setApprovalId(null);
		approval.setReferenceNo("423.10/ENC/2011/0939");
		approval.setDeptId("423.10");
		approval.setApprovalType("EST_DELE");
		approval.setApprovedLevel("DEO");
		approval.setApprovedBy("DEO42310");
		approval.setApprovedDate(calendar.getTime());
		approval.setApprovedTime(format.FormatTime());
		approval.setFromStatus(new BigDecimal(EstimateStatus.MODIFIED).toString());
		approval.setToStatus("00");*/
		
		//bean.delete("423.10/ENC/2011/0939", "423.10", approval, "R1");
		//System.out.println("rrrrr "+bean.getEstApprovalReport("ES123", "514.20", "ES", "CR", "R11")) ;
		//System.out.println("rrrrr "+bean.getEstApprovalStatusReport("ES123", "514.20", "ES", "CR", new BigDecimal("30"), "N", "R1"));
		
		
		/*System.out.println("lvjnjkv "+bean.getFundSource("547.00", "R1"));
		System.out.println("lvjnjkv "+bean.getEstApprovalStatusReport("ES123", "514.20", "ES", "NC", new BigDecimal(30), "N", "sdmlvnsdm"));
		System.out.println(bean.getDefaultMaterialGrid("423.50",  new Long(3), new Long(60), "OH", "R1"));
		System.out.println("@ " +bean.getPoleTypeMaterialGrid("423.50", new Long(1), "INTERMEDIATE", "FLY", "FLY", 4, "R1"));
		System.out.println(bean.getStrutMaterialGrid("423.50", 2, "R1"));
		System.out.println(bean.getStayMaterialGrid("423.50", 1, "R1"));
		System.out.println("## "+bean.getConductorMaterialGrid("514.20", new Long(1), new Long(30), "OH", "ABC", new Double(60), "SMCTesting"));
		System.out.println("1 "+bean.getDefaultConductorLabour("514.20", "NC", "1", "ABC", "10", "SMCTe"));
		System.out.println("2 "+bean.getDefaultServiceWireLabour("514.20", "NC", "1", "LAST", "10", "SMCTe"));
		System.out.println("3 "+bean.getDefaultPoleLabour("422.10", "NC", "A0211", 10, "R1"));
		System.out.println("4 "+bean.getDefaultPoleTransportLabour("514.20", "NC", "A0211", 10, new Double(10), "rrrrr"));
		System.out.println("5 "+bean.getDefaultStrutLabour("514.20", "NC", "A0203", 10, "R1222"));
		System.out.println("6 "+bean.getDefaultStayLabour("514.20", "NC", "NORMAL", 10, "R1222"));
		System.out.println("@ "+bean.getMaterialGridByMatCd("423.50", "A0203", new Double(4), "R1"));
		System.out.println(bean.getEstimateList("514.20", new BigDecimal(EstimateStatus.EST_REJECTED), "NC", "SMCTesting"));
		System.out.println(bean.getPoleTypeMaterialGrid("514.20", new Long(1), "INTERMEDIATE", "FLY", "FLY", 4, "SMCTesting"));
		System.out.println(bean.getStrutMaterialGrid("514.20", 2, "SMCTesting"));
		System.out.println(bean.getStayMaterialGrid("514.20", 1, "SMCTesting"));
		System.out.println(bean.getConductorMaterialGrid("514.20", new Long(3), new Long(30), "OH", "ABC", new Double(60), "SMCTesting"));
		System.out.println(bean.getServiceWireMaterialGrid("514.20", new Long(1), new Long(30), "OH", new Double(20), "SMCTesting"));
		//System.out.println(bean.getEstApprovalReport("es123", "510.20", "ES", "SMCTesting"));
		System.out.println(bean.getEstApprovalReport("es123", "510.20", "ES", "CR", "SMCTesting"));
		//System.out.println(bean.getEstApprovalReport("510.20", new BigDecimal(44), "DGM", "SMCTesting"));
		System.out.println("44 "+bean.getEstimateList("es123"+"514.20", new BigDecimal(EstimateStatus.EST_APPROVAL_AE), "SMCTesting"));
		System.out.println("44 "+bean.getEstimateList("es123"+"514.20", new BigDecimal(EstimateStatus.EST_APPROVAL_AE), "CR", "SMCTesting") );
		//System.out.println(bean.getEstApprovalReport("2 "+"ae123", "514.20", "ES", "SMCTesting"));
		System.out.println(bean.getMaterialGrid("3 "+"ABC/01/2006", "501.20", "SMCTesting"));
		//System.out.println(bean.getMaterialGridNew("ABC/01/2006", "501.20").size());
		//System.out.println(bean.getEstApprovalReport("4 "+"dgm123", "440.20", "DGM", "SMCTesting"));
		//System.out.println(bean.getEstApprovalReport("es", "510.20", "ES", "SMCTesting"));
		//System.out.println(bean.getEstApprovalReport("es123", "510.20", "ES", "SMCTesting"));
		//System.out.println(bean.getEstApprovalReport("510.20", new BigDecimal(44), "DGM", "SMCTesting"));
		//System.out.println(bean.getEstApprovalReport1("es123", "440.20", new BigDecimal(44), "ES", "SMCTesting"));
		//System.out.println(bean.getEstApprovalReport1("dgm123", "440.20", new BigDecimal(44), "DGM", "SMCTesting"));
		System.out.println(bean.getFundSource("501.20", "SMCTesting"));
		try {
			System.out.println("@#$ "+bean.getAvailableMaterials("547.00", null, "SMCTesting"));
			System.out.println("@#$ "+bean.getPoleTypeMaterialGrid("547.00", new Long(1), "INTERMEDIATE", "FLY", "FLY", 2, "SMCTesting").size());
			System.out.println("@#$ "+bean.getServiceWireMaterialGrid("547.00", new Long(1), new Long(30), "FLY", new Double(10), "SMCTesting"));
			System.out.println("@#$ "+bean.getAvailableOtherCosts("510.20", null, "SMCTesting"));
			
			System.out.println(bean.getNPLMaterials("510.20", null, "SMCTesting"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//System.out.println(bean.getFundSourceList("440.20"));
		//System.out.println(bean.getEstApprovalReport("510.20", new BigDecimal("75"), "AE"));
		//System.out.println(bean.getMaterialGridRecord("ABC/2/2006", "501.20","00101"));
		//System.out.println(bean.getMaterialGrid("ABC/2/2006", "501.20"));
		//System.out.println(bean.getMaterialGrid("510.20/SNL/2010/0003", "510.20"));
		//bean.delete(null);
		//System.out.println(bean.getDefaultList(new Long("1"), new Long("15"), new Long("0"), new Long("30"), "UG", "510.20"));
		//List<DefaultMatGrid> list = bean.getDefaultList(new Long("1"), new Long("15"), new Long("0"), new Long("30"), "UG" ,"510.20");
		//System.out.println(bean.getDefaultMaterialGrid("510.20", new Long("1"), new Long("15"), new Long("30"), "UG"));
		//*/
		//PcestdttPK id=new PcestdttPK();
		//id.setEstimateNo("510.20/SNL/2010/0003");
		//id.setDeptId("510.20");
		//id.setResCd("L0305");
		//List<PcestdttPK> list=new ArrayList<PcestdttPK>();
		//list.add(id);
		//bean.delete(list);
		
		//System.out.println(list.get(0));
		
	}
	
	
	private void matCostUpdateDMT(String matCd){
		//R1
		/*bean.doMatCostNoUpdate("422.10", matCd, 100,"R1");
		bean.doMatCostNoUpdate("422.20", matCd, 100,"R1");
		bean.doMatCostNoUpdate("422.30", matCd, 100,"R1");
		bean.doMatCostNoUpdate("422.40", matCd, 100,"R1");
		*/
		bean.doMatCostNoUpdate("423.10", matCd, 100,"R1");
		bean.doMatCostNoUpdate("423.20", matCd, 100,"R1");
		bean.doMatCostNoUpdate("423.40", matCd, 100,"R1");
		bean.doMatCostNoUpdate("423.50", matCd, 100,"R1");
		bean.doMatCostNoUpdate("424.10", matCd, 100,"R1");
		bean.doMatCostNoUpdate("424.20", matCd, 100,"R1");
		
		//R3
		/*bean.doMatCostNoUpdate("513.10", matCd, 100, "R3");
		bean.doMatCostNoUpdate("513.30", matCd, 100, "R3");
		bean.doMatCostNoUpdate("513.40", matCd, 100, "R3");
		bean.doMatCostNoUpdate("517.10",  matCd, 100, "R3");
		bean.doMatCostNoUpdate("517.20", matCd, 100,  "R3");
		bean.doMatCostNoUpdate("517.50", matCd, 100, "R3");
		bean.doMatCostNoUpdate("518.10", matCd, 100, "R3");
		bean.doMatCostNoUpdate("518.20", matCd, 100, "R3");
		*/
		//R4
		/*bean.doMatCostNoUpdate("511.10", matCd, 100,"R4");
		bean.doMatCostNoUpdate("511.20", matCd, 100,"R4");
		bean.doMatCostNoUpdate("515.20", matCd, 100,"R4");
		bean.doMatCostNoUpdate("515.30", matCd, 100,"R4");
		*/
	}
	
	private void matCostUpdateDTT(String matCd ){
		//R1
		/*
		bean.doMatCostNoUpdateTem("422.10", matCd, 18,"R1");
		bean.doMatCostNoUpdateTem("422.20", matCd, 18,"R1");
		bean.doMatCostNoUpdateTem("422.30", matCd, 18,"R1");
		bean.doMatCostNoUpdateTem("422.40", matCd, 18,"R1");
		*/
		bean.doMatCostNoUpdateTem("423.10", matCd, 100,"R1");
		bean.doMatCostNoUpdateTem("423.20", matCd, 18,"R1");
		bean.doMatCostNoUpdateTem("423.40", matCd, 18,"R1");
		bean.doMatCostNoUpdateTem("423.50", matCd, 18,"R1");
		bean.doMatCostNoUpdateTem("424.10", matCd, 18,"R1");
		bean.doMatCostNoUpdateTem("424.20", matCd, 18,"R1");
		
		//R3
		//R3
		/*bean.doMatCostNoUpdateTem("513.10", matCd, 100, "R3");
		bean.doMatCostNoUpdateTem("513.30", matCd, 100, "R3");
		bean.doMatCostNoUpdateTem("513.40", matCd, 100, "R3");
		bean.doMatCostNoUpdateTem("517.10", matCd, 100, "R3");
		bean.doMatCostNoUpdateTem("517.20", matCd, 100,  "R3");
		bean.doMatCostNoUpdateTem("517.50", matCd, 100, "R3");
		bean.doMatCostNoUpdateTem("518.10", matCd, 100, "R3");
		bean.doMatCostNoUpdateTem("518.20", matCd, 100, "R3");
		*/
		//R4
		/*bean.doMatCostNoUpdateTem("511.10", matCd, 100,"R4");
		bean.doMatCostNoUpdateTem("511.20", matCd, 100,"R4");
		bean.doMatCostNoUpdateTem("515.20", matCd, 100,"R4");
		bean.doMatCostNoUpdateTem("515.30", matCd, 100,"R4");
		*/
	}
	
	private void estimateCostUpdateDTT(String jobType){
		
		//bean.estimateCostUpdateTem("422.10", jobType, "R1");
		//bean.estimateCostUpdateTem("422.20", jobType, "R1");
		//bean.estimateCostUpdateTem("422.30", jobType, "R1");
		//bean.estimateCostUpdateTem("422.40", jobType, "R1");
		
		//bean.estimateCostUpdateTem("423.10", jobType, "R1");
		//bean.estimateCostUpdateTem("423.20", jobType, "R1");
		//bean.estimateCostUpdateTem("423.40", jobType, "R1");
		//bean.estimateCostUpdateTem("423.50", jobType, "R1");
		
		//bean.estimateCostUpdateTem("424.10", jobType, "R1");
		//bean.estimateCostUpdateTem("424.20", jobType, "R1");
		
		//R3
		
		/*bean.estimateCostUpdateTem("513.10", jobType, "R3");
		bean.estimateCostUpdateTem("513.30", jobType, "R3");
		bean.estimateCostUpdateTem("513.40", jobType, "R3");
		bean.estimateCostUpdateTem("517.10", jobType, "R3");
		bean.estimateCostUpdateTem("517.20", jobType,  "R3");
		bean.estimateCostUpdateTem("517.50", jobType, "R3");
		bean.estimateCostUpdateTem("518.10", jobType, "R3");
		bean.estimateCostUpdateTem("518.20", jobType, "R3");
		*/
		//R4
		/*bean.estimateCostUpdateTem("511.10", jobType, "R4");
		bean.estimateCostUpdateTem("511.20", jobType, "R4");
		bean.estimateCostUpdateTem("511.30", jobType, "R4");
		bean.estimateCostUpdateTem("515.20", jobType, "R4");
		bean.estimateCostUpdateTem("515.30", jobType, "R4");
		*/
		
		
	}
	
	private void estimateCostUpdateDMT(String jobType){
		//R1
		//bean.estimateCostUpdate("422.10", jobType, "R1");
		
		//bean.estimateCostUpdate("422.20", jobType, "R1");
		//bean.estimateCostUpdate("422.30", jobType, "R1");
		//bean.estimateCostUpdate("422.40", jobType, "R1");
		
		//bean.estimateCostUpdate("423.10", jobType, "R1");
		//bean.estimateCostUpdate("423.20", jobType, "R1");
		//bean.estimateCostUpdate("423.40", jobType, "R1");
		//bean.estimateCostUpdate("423.50", jobType, "R1");
		
		//bean.estimateCostUpdate("424.10", jobType, "R1");
		//bean.estimateCostUpdate("424.20", jobType, "R1");
		
		//R3
		/*bean.estimateCostUpdate("513.10", jobType, "R3");
		bean.estimateCostUpdate("513.30", jobType, "R3");
		bean.estimateCostUpdate("513.40", jobType, "R3");
		bean.estimateCostUpdate("517.10", jobType, "R3");
		bean.estimateCostUpdate("517.20", jobType, "R3");
		bean.estimateCostUpdate("517.50", jobType, "R3");
		bean.estimateCostUpdate("518.10", jobType, "R3");
		bean.estimateCostUpdate("518.20", jobType, "R3");
	    */
	
		//R4
		/*bean.estimateCostUpdate("511.10", "ENC", "R4");
		bean.estimateCostUpdate("511.20", "ENC", "R4");
		bean.estimateCostUpdate("511.30", "ENC", "R4");
		bean.estimateCostUpdate("515.20", "ENC", "R4");
		bean.estimateCostUpdate("515.30", "ENC", "R4");
		*/
	}
	
	@Override
	public List<MaterialGrid> getMaterialGrid(String estimateNo, String dept_id, String webAppName) {
		return bean.getMaterialGrid(estimateNo, dept_id, webAppName);
	}

	@Override
	public MaterialGrid getMaterialGridRecord(String estimateNo, String deptId, String matCd, String webAppName) {
		return bean.getMaterialGridRecord(estimateNo, deptId, matCd, webAppName);
	}
	
	@Override
	public void insert(Pcesthtt pcesthtt, Speststd speststd, List<Pcestdtt> list, String webAppName) {
		bean.insert(pcesthtt, speststd, list, webAppName);
		
	}

	@Override
	public void update(Pcesthtt pcesthtt, Speststd speststd, List<Pcestdtt> addlist, List<Pcestdtt> updList, String webAppName) {
		bean.update(pcesthtt, speststd, addlist, updList, webAppName);
		
	}

	@Override
	public void delete(List<PcestdttPK> list, Pcesthtt inPcesthtt, String webAppName) {
		bean.delete(list, inPcesthtt, webAppName);
		
	}
	
	@Override
	public List<DefaultMatGrid> getDefaultList(Long phase, Long connectionType, String wiringType, String dept_id, String webAppName) {
		return bean.getDefaultList(phase, connectionType, wiringType,dept_id, webAppName);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public ArrayList<ArrayList<String>> getAvailableOtherCosts(String deptId, ArrayList selMatSet, String webAppName) throws Exception {
		return bean.getAvailableOtherCosts(deptId, selMatSet, webAppName);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ArrayList<ArrayList<String>> getAvailableMaterials(String deptId, ArrayList selMatSet, String webAppName) throws Exception {
		return bean.getAvailableMaterials(deptId, selMatSet, webAppName);
	}

	@Override
	public LinkedHashMap<String, MaterialGrid> getDefaultMaterialGrid( String deptId, long phase, long conType, String wiringType, String webAppName) {
		return bean.getDefaultMaterialGrid(deptId, phase, conType, wiringType, webAppName);
	}
	
	//@Override
	//public List<Pcesthtt> getEstApprovalReport(String deptId, BigDecimal status, String value, String webAppName) {
	//	return bean.getEstApprovalReport(deptId, status, value, webAppName);
	//}
	
	@Override
	public List<Pcfunddm> getFundSourceList(String deptId, String webAppName) {
		return bean.getFundSourceList(deptId, webAppName);
	}
	
	@Override
	public List<FundSource> getFundSource(String deptId, String webAppName) {
		return bean.getFundSource(deptId, webAppName);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public ArrayList<ArrayList<String>> getNPLMaterials(String deptId, 	ArrayList selMatSet, String webAppName) throws Exception {
		return bean.getNPLMaterials(deptId, selMatSet, webAppName);
	}
	
	//@Override
	////public List<Pcesthtt> getEstApprovalReport1(String UserName, String deptId, BigDecimal status, String value, String webAppName) {
	//	return bean.getEstApprovalReport1(UserName, deptId, status, value, webAppName);
	//}


	
	

	//@Override
	//public List<EstimateDisplay> getEstApprovalReport(String userName, String deptId, String authorityLevel, String webAppName) {
	//	return bean.getEstApprovalReport(userName, deptId, authorityLevel, webAppName);
	//}

	@Override
	public void estimatePost(List<String> list, String costCenterNo, String webAppName) {
		bean.estimatePost(list, costCenterNo, webAppName);
		
	}

	@Override
	public List<EstimateDisplay> getEstimateList(String deptId, BigDecimal status, String webAppName) {
		return bean.getEstimateList(deptId, status, webAppName);
	}

	@Override
	public List<EstimateDisplay> getEstimateList(String deptId, BigDecimal status, String applicationType, String webAppName) {
		return bean.getEstimateList(deptId, status, applicationType, webAppName);
	}

	@Override
	public List<EstimateDisplay> getEstApprovalReport(String userName, String deptId, String authorityLevel, String applicationType, String webAppName) {
		return bean.getEstApprovalReport(userName, deptId, authorityLevel, applicationType, webAppName);
	}

	@Override
	public List<MaterialGrid> getConductorMaterialGrid(String deptId,
			long phase, long connectionType, String wiringType,
			String conductorType, Double conductorLength, String webAppName)
			throws PersistenceException {
		return bean.getConductorMaterialGrid(deptId, phase, connectionType, wiringType, conductorType, conductorLength, webAppName);
	}

	@Override
	public List<MaterialGrid> getServiceWireMaterialGrid(String deptId,
			long phase, long connectionType, String wiringType,
			Double serviceLength, String webAppName) {
		return bean.getServiceWireMaterialGrid(deptId, phase, connectionType, wiringType, serviceLength, webAppName);
	}

	@Override
	public List<MaterialGrid> getPoleTypeMaterialGrid(String deptId,
			long phase, String poleType, String fromConductor,
			String toConductor,int NoOfPoles, String webAppName) {
		return bean.getPoleTypeMaterialGrid(deptId, phase, poleType, fromConductor, toConductor,NoOfPoles, webAppName);
	}

	@Override
	public List<MaterialGrid> getStayMaterialGrid(String deptId, int noOfStays, String webAppName) {
		return bean.getStayMaterialGrid(deptId, noOfStays, webAppName);
	}

	@Override
	public List<MaterialGrid> getStrutMaterialGrid(String deptId, int noOfStruts, String webAppName) {
		return bean.getStrutMaterialGrid(deptId, noOfStruts, webAppName);
	}

	/*@Override
	public List<MaterialGrid> getMaterialGridNew(String estimateNo, String deptId, String webAppName) {
		return bean.getMaterialGridNew(estimateNo, deptId, webAppName);
	}*/
	
	

	@Override
	public MaterialGrid getMaterialGridByMatCd(String deptId, String matCd,
			Double noOfItems, String webAppName) {
		return bean.getMaterialGridByMatCd(deptId, matCd,noOfItems, webAppName);
	}

	@Override
	public void insert(Pcesthtt pcesthtt, Speststd speststd,
			List<Pcestdtt> list, List<Spestlab> listLabour, String webAppName) {
		bean.insert(pcesthtt, speststd, list, listLabour, webAppName);
		
	}

	@Override
	public void update(Pcesthtt pcesthtt, Speststd speststd,
			List<Pcestdtt> addlist, List<Pcestdtt> updList,
			List<Spestlab> addListLabour, List<Spestlab> updListLabour, String webAppName) {
		bean.update(pcesthtt, speststd, addlist, updList, addListLabour, updListLabour, webAppName);
		
	}

	@Override
	public void removeLabour(List<SpestlabPK> removeList, Pcesthtt pcesthtt, List<Pcestdtt> pcestdttList, Speststd speststd, String webAppName) {
		bean.removeLabour(removeList, pcesthtt, pcestdttList, speststd, webAppName);
		
	}
	
	@Override
	public void delete(String estimateNo, String deptId, Approval approval,String webAppName) {
		bean.delete(estimateNo, deptId, approval,webAppName);
		
	}
	
	@Override
	public List<LabourGrid> getDefaultPoleLabour(String deptId,
			String applicationType, String matCd, int noOfPoles,
			String webAppName) {
		return bean.getDefaultPoleLabour(deptId, applicationType, matCd, noOfPoles, webAppName);
	}
	
	

	@Override
	public List<LabourGrid> getDefaultServiceWireLabour(String deptId,
			String applicationType, String phase, String span_type,
			String noOfSpans, String webAppName) {
		return bean.getDefaultServiceWireLabour(deptId, applicationType, phase, span_type, noOfSpans, webAppName);
	}

	@Override
	public List<LabourGrid> getDefaultConductorLabour(String deptId,
			String applicationType, String phase, String conductorType,
			String wireLength, String webAppName) {
		return bean.getDefaultConductorLabour(deptId, applicationType, phase, conductorType, wireLength, webAppName);
	}
	
	@Override
	public List<LabourGrid> getDefaultStrutLabour(String deptId,
			String applicationType, String matCd, int noOfPoles,
			String webAppName) {
		return bean.getDefaultStrutLabour(deptId, applicationType, matCd, noOfPoles, webAppName);
	}
	
	@Override
	public List<LabourGrid> getDefaultStayLabour(String deptId,
			String applicationType, String stayType, int noOfStays,
			String webAppName) {
		return bean.getDefaultStayLabour(deptId, applicationType, stayType, noOfStays, webAppName);
	}
	
	@Override
	public List<LabourGrid> getDefaultPoleTransportLabour(String deptId,
			String applicationType, String matCd, int noOfPoles, Double distance ,
			String webAppName) {
		return bean.getDefaultPoleTransportLabour(deptId, applicationType, matCd, noOfPoles, distance ,webAppName);
	}
	
	
	

	@Override
	public void validateEstimate(Pcesthtt pcesthtt, Approval approval,
			String webAppName) {
		bean.validateEstimate(pcesthtt, approval, webAppName);
		
	}

	@Override
	public void rejectEstimate(Pcesthtt pcesthtt, Approval approval,
			String webAppName) {
		bean.rejectEstimate(pcesthtt, approval, webAppName);
		
	}

	@Override
	public String transfer(Pcesthtt pcesthtt, List<Pcestdtt> list, String cSCNo,
			String webAppName) { 
		return bean.transfer(pcesthtt, list, cSCNo, webAppName);
		
	}

	@Override
	public void updatePcprjbal(Pcesthtt pcesthtt, List<Pcestdtt> list,
			String webAppName) {
		bean.updatePcprjbal(pcesthtt, list, webAppName);
		
	}

	@Override
	public Spsecdep getSpsecdep(SpsecdepPK id, String webAppName) {
		return bean.getSpsecdep(id, webAppName);
		
	}

	@Override
	public Splpsvcm getSplpsvcm(SplpsvcmPK id, String webAppName) {
		return bean.getSplpsvcm(id, webAppName);
		
	}

	@Override
	public Spugcblm getSpugcblm(SpugcblmPK id, String webAppName) {
		return bean.getSpugcblm(id, webAppName);
		
	}

	@Override
	public LinkedHashMap<String, MaterialGrid> getDefaultLoopMaterialGrid(
			String deptId, long phase, long conType, String wiringType,
			String webAppName) {
		return bean.getDefaultLoopMaterialGrid(deptId, phase, conType, wiringType, webAppName);
	}

	@Override
	public List<EstimateDisplay> getEstApprovalStatusReport(String userName,
			String deptId, String authorityLevel, String applicationType,
			BigDecimal status, String pivType, String webAppName) {
		return bean.getEstApprovalStatusReport(userName, deptId, authorityLevel, applicationType, status, pivType, webAppName);
	}

	@Override
	public Spratesm getSpratesm(SpratesmPK id, String webAppName) {
		return bean.getSpratesm(id, webAppName);
	}

	@Override
	public void dojdfhdf(String webAppName) {
		bean.dojdfhdf(webAppName);
		
	}

	@Override
	public void doProjectNoUpdate(String webAppName) {
		bean.doProjectNoUpdate(webAppName);
		
	}

	@Override
	public void doMatCostNoUpdate(String deptId, String resCd, int length,String webAppName) {
		bean.doMatCostNoUpdate(deptId, resCd, length,webAppName);
		
	}

	@Override
	public void doMatCostNoUpdateTem(String deptId, String resCd, int length,
			String webAppName) {
		bean.doMatCostNoUpdateTem(deptId, resCd, length,webAppName);
		
	}

	@Override
	public void removeMaterials(List<PcestdttPK> list, Pcesthtt inPcesthtt,
			Speststd speststd, String webAppName) {
		bean.removeMaterials(list, inPcesthtt, speststd, webAppName);
		
	}

	@Override
	public void transferDTT(String estimateNo, String deptId, String webAppName) {
		bean.transferDTT(estimateNo, deptId, webAppName);
		
	}

	@Override
	public void estimateCostUpdateTem(String deptId, String jobType,
			String webAppName) {
		bean.estimateCostUpdateTem(deptId, jobType,  webAppName);
		
	}

	@Override
	public void estimateCostUpdate(String deptId, String jobType,
			String webAppName) {
		bean.estimateCostUpdate(deptId, jobType, webAppName);
		
	}

	@Override
	public void updateLineLength(String webAppName) {
		bean.updateLineLength(webAppName);
		
	}

	@Override
	public List<EstimateDisplay> getEstApprovalReportMT_SA(String userName,
			String deptId, String authorityLevel, String applicationType,
			String webAppName) {
		return getEstApprovalReportMT_SA(userName, deptId, authorityLevel, applicationType, webAppName);
	}

	@Override
	public List<EstimateDisplay> getEstimateListMA_SA(String deptId,
			BigDecimal status, String webAppName) {
		return bean.getEstimateListMA_SA(deptId, status, webAppName);
	}

	@Override
	public String changeCostCenter(String jobNo,String fromDeptId,
			String toDeptId, String webAppName) {
		return bean.changeCostCenter(jobNo,fromDeptId, toDeptId, webAppName);
	}

	@Override
	public List<Spnorms> getAvailableNorms(String webAppName) {
		return bean.getAvailableNorms(webAppName);
	}

	
	@Override
	public List<SpPegInfo> getPegChildrensByParentId(String parentId,String masterId,String webAppName) {
		return bean.getPegChildrensByParentId(parentId,masterId, webAppName);
	}
	
	/*@Override
	public List<SpPegInfo> loadApplicationRefno(String userId, String costCenter) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public List<String> loadApplicationRefnos(String userId, String costCenter,
			String webAppName) {
		return bean.loadApplicationRefnos(userId,costCenter,webAppName);
	}


	@Override
	public void insert(Spstdesthmt spstdesthmt,List<Spstdestdmt> lineTypelist,
			String webAppName) {
		bean.insert(spstdesthmt, lineTypelist, webAppName);
	}

	@Override
	public Spstdesthmt loadEstimateByApplicationNo(String costCenter,String applicationNo,String webAppName) {
		return bean.loadEstimateByApplicationNo(costCenter,applicationNo, webAppName);
	}
	

	
	public List<Spstdestdmt> loadEstimateDetailsByApplicaNo(String costCenter,String applicationNo, String webAppName){
		return bean.loadEstimateDetailsByApplicaNo(costCenter,applicationNo, webAppName);
	}

	
	@Override
	public int removeLineDetails(Spstdestdmt line, String webAppName) {
		return bean.removeLineDetails(line, webAppName);
		
	}

	@Override
	public int updateLineDetails(Spstdestdmt line, String webAppName) {
		return bean.updateLineDetails(line, webAppName);
		
	}

	@Override
	public int updateStdEstimateDetails(String applicationNo,
			BigDecimal secDeposit,BigDecimal totalCost, String webAppName) {
		return bean.updateStdEstimateDetails(applicationNo,secDeposit,totalCost, webAppName);
	}
	
	@Override
	public void insertLineDetails(Spstdestdmt line, String webAppName) {
		bean.insertLineDetails(line, webAppName);
	}

	
	@Override
	public List<String> loadStandEstmatenos(String costCenter,List<Long> status,String userName,String webAppName) {
		return bean.loadStandEstmatenos(costCenter,status,userName, webAppName);
	}

	@Override
	public int updateSpestedyStatus(String status, String referenceNo,
			String webAppName) {
		return bean.updateSpestedyStatus(status,referenceNo, webAppName);
	}

	

	public BigDecimal getLineCost(SpnormsPK lineId,String webAppName){
		return bean.getLineCost(lineId,webAppName);
	}

	public List<SpPointdmt> getPegResourceById(String id,String masterId,String webAppName){
		return bean.getPegResourceById(id,masterId,webAppName);
	}
	public List<String> loadResourceTypes(String webAppName){
		return bean.loadResourceTypes(webAppName);
	}
	public List<String> loadResourceCodes(String resourceType,String webAppName){
		return bean.loadResourceCodes(resourceType,webAppName);
	}
	public Pcrsgrpm loadResourceDetails(String resourType,String code,String webAppName){
		return bean.loadResourceDetails(resourType,code,webAppName);
	}

	@Override
	public List<Pcfunddm> getFundSourcesList(String deptId, String webAppName) {
		return bean.getFundSourcesList(deptId,webAppName);
	}
	@Override
	public List<String> getFundSources(String deptId, String webAppName) {
		return bean.getFundSources(deptId,webAppName);
	}
	@Override
	public List<String> getFundIds(String deptId, String fundSource,
			String webAppName) {
		return bean.getFundIds(deptId,fundSource,webAppName);
	}

	@Override
	public List<String> getCatCode(String deptId, String webAppName) {
		return bean.getCatCode(deptId,webAppName);
	}

	@Override
	public List<String> loadWarehouses(String deptId, String webAppName) {
		return bean.loadWarehouses(deptId,webAppName);
	}

	@Override
	public List<Pcrstypm> isResourceTypeExist(String resourType, String webAppName) {
		return bean.isResourceTypeExist(resourType,webAppName);
	}
	
	
	@Override
	public List<DetailEstimateDTO> getNPLMaterials(String deptId,String webAppName) throws Exception {
		return bean.getNPLMaterials(deptId,webAppName);
	}
	
	@Override
	public List<DetailEstimateDTO> getOtherResources(String deptId,
			String webAppName) throws Exception {
		return bean.getOtherResources(deptId,webAppName);
	}

	@Override
	public List<DetailEstimateDTO> getAvailableOtherMaterials(
			String deptId, String warehouse, String webAppName)
			throws Exception {
		return bean.getAvailableOtherMaterials(deptId,warehouse,webAppName);
	}
	
	@Override
	public List<DetailEstimateDTO> getAvailableOtherMaterials(
			String deptId, String warehouse,List<String> list,  String webAppName)
			throws Exception {
		return bean.getAvailableOtherMaterials(deptId,warehouse,list,webAppName);
	}


	/*@Override
	public void insertDetailEst(Pcesthtt pcesthtt, List<Pcestdtt> list,TempTb tempTb,
			String webAppName) throws Exception {
		bean.insertDetailEst(pcesthtt,list,tempTb,webAppName);
		
	}*/
	@Override
	public void addAdditionalCost(Pcesthtt pcesthtt,String estimateNo ,String deptId,BigDecimal toCost,String webAppName) throws Exception {
		bean.addAdditionalCost(pcesthtt,estimateNo,deptId,toCost,webAppName);
		
	}
	public  void insertPIVDetails(TempTb tempTb,String webAppName)throws Exception{
		bean.insertPIVDetails(tempTb,webAppName);
	}
	public  TempTb findPIVDetails(String estimationNo,String deptId,String webAppName)throws Exception{
		return bean.findPIVDetails(estimationNo,deptId, webAppName);
	}

	
	/*@Override
	public void updateWorkEstimateDetails(Pcesthtt pcesthtt, Speststd speststd,
			List<Pcestdtt> addlist, List<Pcestdtt> updList,
			List<Pcestdtt> deleList, String webAppName) {
		bean.updateWorkEstimateDetails(pcesthtt,speststd,addlist,updList,deleList,webAppName);
		
	}*/
	@Override
	public List<Spstdesthmt> loadStandEstmateDetails(String costCenter,List<Long> status,String userName,String webAppName) {
		return bean.loadStandEstmateDetails(costCenter,status,userName,webAppName);
		
	}

	@Override
	public boolean updateEstimateStatus(String estimateNumber,String costCenter,Long status,String approvedBy,Date approvedDate ,String rejectedBy,Date rejectedDate,String planningByPE,Date planningDatePE,String valiadteByEE,Date valiadteDateEE,String valiadteByCE,Date valiadteDateCE,String rejectedReasonCE,String rejectedReasonPE,
			String rejectedReasonEE,String webAppName) throws PersistenceException {
		return bean.updateEstimateStatus(estimateNumber, costCenter, status,approvedBy,approvedDate,rejectedBy,rejectedDate,planningByPE,planningDatePE,valiadteByEE,valiadteDateEE,valiadteByCE,valiadteDateCE,rejectedReasonCE,rejectedReasonPE,rejectedReasonEE,webAppName);
	}

	public void insertDetailEst(Pcesthtt pcesthtt, List<Pcestdtt> list,
			TempTb tb, List<Pegschdmt> peglist,String appointedUserName,String fileRef, String webAppName) throws Exception {
		bean.insertDetailEst(pcesthtt,list,tb,peglist,appointedUserName,fileRef,webAppName);
		
	}

	public void updateWorkEstimateDetails(Pcesthtt pcesthtt, Speststd speststd,
			List<Pcestdtt> addlist, List<Pcestdtt> updList,
			List<Pcestdtt> deleList,List<Pegschdmt> addedPegschdmtList,List<Pegschdmt> upadtedPegschdmtList,TempTb temptb, String webAppName) {
		bean.updateWorkEstimateDetails(pcesthtt,speststd,addlist,updList,deleList,addedPegschdmtList,upadtedPegschdmtList,temptb,webAppName);
		
	}

	public void updateEstimateDetails(Spstdesthmt spstdesthmt,
			List<Spstdestdmt> insertlist, List<Spstdestdmt> updatelist,
			String webAppName) throws PersistenceException {
		bean.updateEstimateDetails(spstdesthmt,insertlist,updatelist,webAppName);
		
	}

	@Override
	public List<EstimateDisplay> getRejectedEstimateList(String deptId,Long status,String appType,String userId,String userLevel, String webAppName) throws PersistenceException{
		// TODO Auto-generated method stub
		return bean.getRejectedEstimateList(deptId,status,appType,userId,userLevel,webAppName);
	}

	@Override
	public List<EstimateDisplay> getApprovedEstimateList(String deptId,
			Long status, String appType, String userId, String webAppName)
			throws PersistenceException {
		return bean.getApprovedEstimateList(deptId,status,appType,userId,webAppName);
	}

	@Override
	public List<EstimateDisplay> getEstimateListToBeApproved(String deptId,
			List<Long> status, String appType, String userId, String webAppName)
			throws PersistenceException {
		return bean.getEstimateListToBeApproved(deptId,status,appType,userId,webAppName);
	}

	@Override
	public List<EstimateDisplay> getEstimateListToBeValidate(String deptId,List<Long> status,String appType,String username, String webAppName)
			throws PersistenceException {
		return bean.getEstimateListToBeValidate(deptId,status,appType,username,webAppName);
	}

	@Override
	public String getWorkEstimateNo(String estimateNoPrefix,
			String stdEstimateNo, String deptId, String webAppName)
			throws PersistenceException {
		return bean.getWorkEstimateNo(estimateNoPrefix,stdEstimateNo,deptId,webAppName);
	}

	@Override
	public List<Pcesthtt> getDetailEstimatesList(String deptId, List<Long> status,String assignTo,
			String userId, String webAppName) throws PersistenceException {
		
		return bean.getDetailEstimatesList(deptId,status,userId,assignTo,webAppName);
	}

	@Override
	public void updateDetailEstimateCost(String estimateNo, String estimateType,String deptId,
			String webAppName) throws PersistenceException {
		bean.updateDetailEstimateCost(estimateNo,estimateType,deptId,webAppName);
		
	}

	@Override
	public void updateStandardEstimateCost(String estimateNo, String deptId,
			String webAppName) throws PersistenceException {
		bean.updateStandardEstimateCost(estimateNo,deptId,webAppName);
		
	}

	@Override
	public String getNextPegItemId(String parentId,String masterId ,String webAppName) {
		// TODO Auto-generated method stub
		return bean.getNextPegItemId(parentId,masterId,webAppName);
	}

	@Override
	public String transferToHmt(Pcesthtt pcesthtt, List<Pcestdtt> list,
			String cSCNo, String newJobNo, String webAppName)
			throws PersistenceException {
		return bean.transferToHmt(pcesthtt,list,cSCNo,newJobNo,webAppName);
	}

	@Override
	public String approveStandardEstimate(String estimateNo, String deptId,
			String authorityLevel, String userName, String assignedUserName, String applicationType,
			String webAppName) {
		// TODO Auto-generated method stub
		return bean.approveStandardEstimate(estimateNo,deptId,authorityLevel,userName,assignedUserName,applicationType,webAppName);
	}

	@Override
	public String rejectStandardEstimate(String estimateNo, Approval approval,
			String webAppName) {
		// TODO Auto-generated method stub
		return bean.rejectStandardEstimate(estimateNo,approval,webAppName);
	}
	public List<String> getFirstFiftyJobList(String deptId,List<Long> status, String webAppName){
		return bean.getFirstFiftyJobList(deptId,status,webAppName);
	}

	@Override
	public List<Spstdesthmt> loadConMainSentList(String costCenter,
			String postID, List<Long> status, String webAppName) {
		return bean.loadConMainSentList(costCenter,postID,status, webAppName);
		// TODO Auto-generated method stub
		
	}

	@Override
	public BigDecimal getTotalConsumerPayable(String estimateNo,
			String resType, String deptId, String webAppName) {
		return bean.getTotalConsumerPayable(estimateNo,resType,deptId, webAppName);
	}

	@Override
	public List<SpNormsGroup> getChildrensByParentId(String parentId,
			String webAppname) {
		// TODO Auto-generated method stub
		return bean.getChildrensByParentId(parentId, webAppname);
	}

	@Override
	public List<SpNormsGroup> getAllSpNormsGroups(String webAppname) {
		// TODO Auto-generated method stub
		return bean.getAllSpNormsGroups(webAppname);
	}

	@Override
	public List<Spnorms> getNormsChildrensByParentId(String parentId,
			String webAppname) {
		// TODO Auto-generated method stub
		return bean.getNormsChildrensByParentId(parentId, webAppname);
	}

	@Override
	public List<AllocationSummaryDisplay> findEstimateSummary(
			String applicationNo, String deptId, String webAppName)
			throws PersistenceException {
		// TODO Auto-generated method stub
		return bean.findEstimateSummary(applicationNo, deptId, webAppName);
	}

	@Override
	public List<Pcesthtt> getVSLEstimatesList(String deptId, List<Long> status,
			String userId, String webAppName) {
		// TODO Auto-generated method stub
		return bean.getVSLEstimatesList(deptId, status, userId, webAppName);
	}

	@Override
	public String recommendStandardEstimate(String estimateNo, String deptId,String authorityLevel, String userName,String com, String assignedUserName,String applicationType,String sinNo, String webAppName) {
		// TODO Auto-generated method stub
		return bean.recommendStandardEstimate(estimateNo, deptId, authorityLevel, userName, com,assignedUserName, applicationType, sinNo,webAppName);
	}

	@Override
	public List<Pcesthtt> getEstimateListForAuthorizedCC(Long revNo, List<Long> status, String userId, String likeEstimate , String webAppName) {
		// TODO Auto-generated method stub
		return bean.getEstimateListForAuthorizedCC( revNo, status, userId,likeEstimate, webAppName);
	}

	@Override
	public int deleteEstimate(Pcesthtt htt,List<Pcestdtt> dtt,TempTb temptb,EstimateReference ref, String webAppName)
			throws Exception {
		return bean.deleteEstimate(htt,dtt,temptb,ref,webAppName);
		
	}

	@Override
	public String updateAndValidate(Pcesthtt pcesthtt, List<Pcestdtt> addlist,
			List<Pcestdtt> updList, List<Pcestdtt> deleList,
			List<Pegschdmt> addedPegschdmtList,
			List<Pegschdmt> updatedPegschdmtList, TempTb tempTb,
			String authorityLevel, String userName, String comment,String estimateType,
			String webAppName) {
		return bean.updateAndValidate(pcesthtt, addlist, updList, deleList, addedPegschdmtList, updatedPegschdmtList, tempTb, authorityLevel, userName, comment,estimateType, webAppName);
	}

	@Override
	public boolean checkEstimateNoExist(String wtdestimateNo, String deptId,
			String webAppName) {
		return bean.checkEstimateNoExist(wtdestimateNo, deptId, webAppName);
	}

	@Override
	public List<FundSource> getFundSourceJV(String deptId, String webAppName) {
		return bean.getFundSourceJV(deptId, webAppName);
	}

	@Override
	public List<Spstdesthmt> loadStandEstmateDetailsType(String costCenter,
			List<Long> status, String userName, String appType,
			String webAppName) {
		return bean.loadStandEstmateDetailsType(costCenter,status,userName,appType,webAppName);
	}

	@Override
	public List<Pcesthtt> getDetailEstimatesListOrderByFundSource(
			String deptId, List<Long> status, String userId, String assignTo,
			String webAppName) throws PersistenceException {
		return bean.getDetailEstimatesListOrderByFundSource(deptId,status,userId,assignTo,webAppName);
		
	}

	@Override
	public List<EstimateDisplay> getStdEstimateList(String deptId,
			List<Long> status, String appType, String userId, String webAppName)
			throws PersistenceException {
		// TODO Auto-generated method stub
		
		return bean.getStdEstimateList(deptId,status,appType,userId,webAppName);
	}

	@Override
	public List<EstimateDisplay> getConfirmPIVList(String deptId, Long status,
			String appType, String userId, String webAppName)
			throws PersistenceException {
		// TODO Auto-generated method stub
		return bean.getConfirmPIVList(deptId, status, appType, userId, webAppName);
	}

	@Override
	public List<EstimateDisplay> getEstimateListToBeValidateForPL(
			String deptId, List<Long> status, String username, String webAppName)
			throws PersistenceException {
		// TODO Auto-generated method stub
		return bean.getEstimateListToBeValidateForPL(deptId,status,username,webAppName);
		
	}
	
  
	
	/*@Override
	public List<EstimateTypeMaster> getEstimateTypesbyDeptId(String deptId,
			String webAppName) throws PersistenceException {
		
		return bean.getEstimateTypesbyDeptId(deptId,webAppName);
	}
*/
	

	/*@Override
	public void updateEstimateDetails(Spstdesthmt spstdesthmt,
			List<Spstdestdmt> insertlist, List<Spstdestdmt> updatelist,
			String webAppName) throws PersistenceException {
		bean.updateEstimateDetails(spstdesthmt,insertlist,updatelist,webAppName);
		
	}*/
	 
}
