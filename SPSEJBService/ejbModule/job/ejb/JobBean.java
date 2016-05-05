package job.ejb;

import inventory.ejb.InmatmDaoRemote;
import inventory.ejb.IntrhmtDaoRemote;
import inventory.ejb.InwrhmapDaoRemote;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import security.ejb.SecurityBeanRemote;
import util.common.Constants;
import util.common.EstimateStatus;
import util.common.Format;
import util.common.JobProcessStatus;
import util.common.StandardEstimateStatus;
import util.emSelect.EmSelector;



//import estimate.ejb.PcfunddmDaoLocal;
import estimate.ejb.ApprovalDaoRemote;
import estimate.ejb.CostCalculationMasterDaoRemote;
import estimate.ejb.PcfunddmDaoRemote;
import estimate.ejb.SpestlabDaoRemote;
import estimate.ejb.SpeststdDaoRemote;
import estimate.model.MaterialGrid;
//import estimate.model.Pcestdtt;
//import estimate.model.PcestdttPK;
//i//mport estimate.model.Pcesthtt;
import estimate.model.Approval;
import estimate.model.EstimateDisplay;
//import estimate.model.Pcestdtt;
//import estimate.model.Pcesthtt;
import estimate.model.CostCalculationMaster;

import estimate.model.Pcestdtt;
import estimate.model.PcestdttPK;
import estimate.model.Pcesthtt;
import estimate.model.Pcfunddm;
import estimate.model.Spestlab;
import estimate.model.SpestlabPK;
import estimate.model.Speststd;
import estimate.model.SpeststdPK;
import estimate.service.EstimateEjb;

import Applicant.ejb.ApplicantDaoRemote;
import application.ejb.ApplicationReferenceDaoRemote;
import application.ejb.WiringLandDetailDaoRemote;
import application.model.ApplicationReference;
import application.model.WiringInfo;

import job.model.FinishedJobInfo;
import job.model.ClosingJobInfo;
import job.model.JobInfo;
import job.model.PaySlipInfo;
import job.model.Pcestdmt;
import job.model.PcestdmtPK;
import job.model.Pcesthmt;
import job.model.Pcprjbal;
import job.model.PcprjbalPK;
import job.model.Pctrxdmt;
import job.model.Pctrxhmt;
import job.model.Spestbip;
import job.model.Spestcnd;
import job.model.SpestcndPK;
import job.model.Spestcnt;
import job.model.Spestsea;
import job.model.StdDetail;
import job.model.TempConnInfo;
import job.service.JobEjb;


/**
 * Session Bean implementation class JobBean
 */
@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless
public class JobBean extends EmSelector implements JobBeanRemote, JobBeanLocal {
	
	@Resource
	private SessionContext context;
	@EJB 
	PcesthmtDaoRemote pcesthmtDaoRemote;
	@EJB
	PcestdmtDaoRemote pcestdmtDaoRemote;
	@EJB
	InmatmDaoRemote inmatmDaoRemote;
	@EJB
	ApplicationReferenceDaoRemote applicationReferenceDaoRemote;
	@EJB
	WiringLandDetailDaoRemote wiringLandDetailDaoRemote;
	@EJB
	ApplicantDaoRemote applicantDaoRemote;
	@EJB
	SpestcndDaoRemote spestcndDaoRemote;
	@EJB
	SpestcntDaoRemote spestcntDaoRemote;
	@EJB
	SpestbipDaoRemote spestbipDaoRemote;
	@EJB
	SpestseaDaoRemote spestseaDaoRemote;
	@EJB
	SpeststdDaoRemote speststdDaoRemote;
	@EJB
	SpestlabDaoRemote spestlabDaoRemote;
	@EJB
	PcfunddmDaoRemote pcfunddmDaoRemote;
	@EJB
	PcprjbalDaoRemote pcprjbalDaoRemote;
	@EJB
	SecurityBeanRemote securityBeanRemote;
    @EJB
    IntrhmtDaoRemote intrhmtDaoRemote;
    @EJB
	ApprovalDaoRemote approvalDaoRemote;
    @EJB
    InwrhmapDaoRemote inwrhmapDaoRemote;
	@EJB
	CostCalculationMasterDaoRemote costCalculationMasterDaoRemote;
	@EJB
    PctrxhmtDaoRemote pctrxhmtDaoRemote;
    @EJB
    PctrxdmtDaoRemote pctrxdmtDaoRemote;

	/**
     * Default constructor. 
     */
    public JobBean() {
        
    }
    
    
    
	/*@Override
	public List<JobInfo> getJobInfos(String deptId,BigDecimal status) {
		try{
		List<JobInfo> infos=new ArrayList<JobInfo>();
		List<String> alist=spestcndDaoRemote.getAllocatedJobNolist(deptId);
		//List<String> list=pcesthmtDaoRemote.getJobNoList(deptId, status);
		List<String> list=pcesthmtDaoRemote.getNonAllocatedJobNoList(deptId, status, alist);
		//System.out.println(list);
		List<BigDecimal> bdList=pcesthmtDaoRemote.getNonAllocatedAmountList(deptId, status, alist);
		//System.out.println(bdList);
		//System.out.println(alist.toString().substring(1, alist.toString().length()-1));
		Iterator<String> it = list.iterator();
		//System.out.println(it);
		Iterator<BigDecimal> bdIt = bdList.iterator();
		//System.out.println(bdIt.next());
		//BigDecimal amount2 = bdIt.next();
		//System.out.println(amount2);
		while (it.hasNext()) 
        { 
			JobInfo info=new JobInfo();
			String  jobNo=it.next();
			//System.out.println("333");
			//BigDecimal amount=bdIt.next();
			//BigDecimal amount = (BigDecimal)bdIt.next();
			//System.out.println("3");
			ApplicationReference appRef=applicationReferenceDaoRemote.findByJobNo(jobNo);
			//System.out.println("4");
			info.setProjectNo(jobNo);
			//System.out.println("5");
			//System.out.println(appRef);
			//System.out.println(appRef.getIdNo());
			info.setFullName(appRef.getIdNo());
			//Object o=bdIt.next();
			//System.out.println(o);
			infos.add(info);
        }
					
		return infos;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<JobInfo> getJobInfos(String deptId,Long status, String webAppName) {
		//getEntityManager(webAppName);
		List<JobInfo> rList= new ArrayList<JobInfo>();
		try{
			List<String> aList=spestcndDaoRemote.getAllocatedJobNolist(deptId, webAppName);
			List<String> blist=new ArrayList<String>();
			//System.out.println(aList);
			//System.out.println(aList.size());
			String e=null;
			//for(int i=0; i<=aList.size()-1;i++){
			//	System.out.println(aList.get(i));
			//	e="'"+aList.get(i)+"'";
			//	blist.add(e.trim());
			//}
			String notIn=blist.toString().substring(1, blist.toString().length()-1);
			String qryStr = null;
			
			if (aList==null || aList.size()==0  ){
				qryStr = " SELECT new Map(TRIM(pm.projectNo) ,pm.stdCost ,ap.firstName, ap.lastName)"+
	            " from Pcesthmt pm ,ApplicationReference ar, Applicant  ap"+
	            " where pm.id.deptId = :deptId "+
	            " AND pm.status = :status "+
	            " and  TRIM(pm.projectNo) = TRIM(ar.projectno) "+
	            " and  ar.idNo = ap.idNo "+
	            " order by pm.projectNo ";
			}else {
				qryStr = " SELECT new Map(TRIM(pm.projectNo) ,pm.stdCost ,ap.firstName, ap.lastName)"+
	            " from Pcesthmt pm ,ApplicationReference ar, Applicant  ap"+
	            " where pm.id.deptId = :deptId "+
	            " AND pm.status = :status "+
	            //" AND TRIM(pm.projectNo) NOT IN ("+notIn+") "+
	            " and  TRIM(pm.projectNo) = TRIM(ar.projectno) "+
	            " and  ar.idNo = ap.idNo "+
	            " order by pm.projectNo ";
			}
			Query query = getEntityManager(webAppName).createQuery(qryStr);
			query.setParameter("deptId", deptId);
			query.setParameter("status", status);
			List<Map> list= query.getResultList();
			Iterator it=list.iterator();
			while(it.hasNext()){
				Map map=(Map) it.next();
				JobInfo jobInfo=new JobInfo(map);
				//System.out.println(jobInfo);
				rList.add(jobInfo);
			}
	
					
		return rList;
		}catch (Exception e) {
			e.printStackTrace();
			//throw new Exception(e);
			return null;
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<JobInfo> getJobInformations(String deptId,Long status, String webAppName) {
		//getEntityManager(webAppName);
		List<JobInfo> rList= new ArrayList<JobInfo>();
		try{
			List<String> aList=spestcndDaoRemote.getAllocatedJobNolist(deptId, webAppName);
			List<String> blist=new ArrayList<String>();
			//System.out.println(aList);
			//System.out.println(aList.size());
			String e=null;
			//for(int i=0; i<=aList.size()-1;i++){
			//	System.out.println(aList.get(i));
			//	e="'"+aList.get(i)+"'";
			//	blist.add(e.trim());
			//}
			String notIn=blist.toString().substring(1, blist.toString().length()-1);
			String qryStr = null;
			
			
			/*select a.full_name,a.last_name from estimate_referencebs esref,pcesthtt hmt,application_reference appref,applications b,applicant a
			where
			trim(hmt.estimate_no) = esref.westimate_no 
			and
			trim(appref.application_no)=trim(esref.sestimate_no)
			and
			b.application_id = appref.application_id
			and 
			b.id_no = a.id_no
			and 
			esref.dept_id = hmt.dept_id
			--and
			--appref.dept_id = esref.dept_id
			and

			trim(hmt.estimate_no) = '510.20/12/0121'
				*/
			if (aList==null || aList.size()==0  ){
				qryStr = " SELECT new Map(TRIM(pm.projectNo) ,pm.stdCost,b.firstName, b.lastName)"+
	            " from Pcesthmt pm ,Application a,Applicant b,EstimateReference esRefBS,ApplicationReference apRef"+
	            " where trim(pm.id.estimateNo) = esRefBS.id.workEstimateNo "+
	            " and trim(apRef.applicationNo)=trim(esRefBS.id.standardEstimateNo) "+
	            " and a.id.applicationId = apRef.id.applicationId "+
	            " and b.idNo = a.idNo "+
	            " and esRefBS.id.deptId = pm.id.deptId "+
	            " and pm.id.deptId = :deptId "+
	            " and pm.status = :status "+
	          //  " and  TRIM(pm.projectNo) = TRIM(ar.projectno) "+
	            //" and  ar.idNo = ap.idNo "+
	            " order by pm.projectNo ";
			}else {
				qryStr = " SELECT new Map(TRIM(pm.projectNo) ,pm.stdCost,b.firstName, b.lastName)"+
	            " from Pcesthmt pm ,Application a,Applicant b,EstimateReference esRefBS,ApplicationReference apRef"+
	            " where trim(pm.id.estimateNo) = esRefBS.id.workEstimateNo "+
	            " and trim(apRef.applicationNo)=trim(esRefBS.id.standardEstimateNo) "+
	            " and a.id.applicationId = apRef.id.applicationId "+
	            " and b.idNo = a.idNo "+
	            " and esRefBS.id.deptId = pm.id.deptId "+
	            " and pm.id.deptId = :deptId "+
	            " and pm.status = :status "+
	          //  " and  TRIM(pm.projectNo) = TRIM(ar.projectno) "+
	            //" and  ar.idNo = ap.idNo "+
	            " order by pm.projectNo ";
			}
			Query query = getEntityManager(webAppName).createQuery(qryStr);
			query.setParameter("deptId", deptId);
			query.setParameter("status", status);
			List<Map> list= query.getResultList();
			Iterator it=list.iterator();
			while(it.hasNext()){
				Map map=(Map) it.next();
				JobInfo jobInfo=new JobInfo(map);
				//System.out.println(jobInfo);
				rList.add(jobInfo);
			}
	
					
		return rList;
		}catch (Exception e) {
			e.printStackTrace();
			//throw new Exception(e);
			return null;
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<JobInfo> getJobInfomations(String deptId,String estimateNo, String webAppName) {
		//getEntityManager(webAppName);
		List<JobInfo> rList= new ArrayList<JobInfo>();
		try{
		
			String qryStr = null;
	
			qryStr = " SELECT new Map(TRIM(pm.id.estimateNo),TRIM(pm.projectNo),TRIM(esRefBS.id.standardEstimateNo) ,b.fullName,pm.stdCost,pm.fundSource,pm.etimateDt" +
					",pm.descr,con.devSec,s.totalCost,con.areaCode,con.electorate,con.district,con.ServiceDepoName,pm.entBy,cnd.id.contractorId,cnd.allocatedUser,s.jobName,TRIM(pm.id.estimateNo))"+
            " from Pcesthtt pm ,Pcesthmt pt ,Application a,Applicant b,EstimateReference esRefBS,ApplicationReference apRef,"+
            " Spstdesthmt s,WiringLandDetailCon con,Spestcnd cnd  "+
            " where trim(pm.id.estimateNo) = esRefBS.id.workEstimateNo "+
            " and trim(apRef.applicationNo)=trim(esRefBS.id.standardEstimateNo) "+
            " and a.id.applicationId = apRef.id.applicationId "+
            " and b.idNo = a.idNo "+
            " and TRIM(s.id.stdNo) = apRef.applicationNo "+
            " and TRIM(a.id.applicationId)=apRef.id.applicationId "+
            " and TRIM(a.id.applicationId)=con.id.applicationId "+
            " and esRefBS.id.deptId = pm.id.deptId "+
            //" and trim(cnt.id.contractorId)=trim(cnd.id.contractorId) "+
            //" and trim(cnt.deptId)=trim(cnd.deptId) "+
            " and trim(cnd.id.projectNo)=trim(pt.projectNo) "+
            " and trim(cnd.id.deptId)=trim(pt.id.deptId) "+
            " and trim(pm.id.deptId)=trim(pt.id.deptId) "+
            " and trim(pm.id.estimateNo)=trim(pt.id.estimateNo) "+
            " and pm.id.deptId = :deptId "+
            " and pm.id.estimateNo = :estimateNo "+
            " order by pm.projectNo ";
			
			Query query = getEntityManager(webAppName).createQuery(qryStr);
			query.setParameter("deptId", deptId);
			query.setParameter("estimateNo", estimateNo);
			List<Map> list= query.getResultList();
			Iterator it=list.iterator();
			while(it.hasNext()){
				Map map=(Map) it.next();
				JobInfo jobInfo=new JobInfo(map);
				//System.out.println(jobInfo);
				rList.add(jobInfo);
			}
	
					
		return rList;
		}catch (Exception e) {
			e.printStackTrace();
			//throw new Exception(e);
			return null;
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<JobInfo> getJobInfos(String deptId,Long status, String notFundID, String webAppName) {
		//getEntityManager(webAppName);
		List<JobInfo> rList= new ArrayList<JobInfo>();
		try{
			List<String> aList=spestcndDaoRemote.getAllocatedJobNolist(deptId, webAppName);
			List<String> blist=new ArrayList<String>();
			String e=null;
			for(int i=0; i<=aList.size()-1;i++){
				e="'"+aList.get(i)+"'";
				blist.add(e.trim());
			}
			String notIn=blist.toString().substring(1, blist.toString().length()-1);
			String qryStr = null;
			
			if (aList==null || aList.size()==0  ){
				qryStr = " SELECT new Map(TRIM(pm.projectNo) ,pm.stdCost ,ap.firstName, ap.lastName)"+
	            " from Pcesthmt pm ,ApplicationReference ar, Applicant  ap"+
	            " where pm.id.deptId = :deptId "+
	            " AND pm.status = :status "+
	            " and  TRIM(pm.projectNo) = TRIM(ar.projectno) "+
	            " and  ar.idNo = ap.idNo "+
	            " order by pm.projectNo ";
			}else {
				qryStr = " SELECT new Map(TRIM(pm.projectNo) ,pm.stdCost ,ap.firstName, ap.lastName)"+
	            " from Pcesthmt pm ,ApplicationReference ar, Applicant  ap"+
	            " where pm.id.deptId = :deptId "+
	            " AND pm.status = :status "+
	            " AND TRIM(pm.projectNo) NOT IN ("+notIn+")"+
	            " and  TRIM(pm.projectNo) = TRIM(ar.projectno) "+
	            " and  ar.idNo = ap.idNo "+
	            " order by pm.projectNo ";
			}
			Query query = getEntityManager(webAppName).createQuery(qryStr);
			query.setParameter("deptId", deptId);
			query.setParameter("status", status);
			List<Map> list= query.getResultList();
			Iterator it=list.iterator();
			while(it.hasNext()){
				Map map=(Map) it.next();
				JobInfo jobInfo=new JobInfo(map);
				//System.out.println(jobInfo);
				rList.add(jobInfo);
			}
	
					
		return rList;
		}catch (Exception e) {
			e.printStackTrace();
			//throw new Exception(e);
			return null;
		}
	}
	
	@Override
	public List<MaterialGrid> getMaterialGrid(String estimateNo, String deptId, String webAppName) {
		List<MaterialGrid> gridList=new ArrayList<MaterialGrid>();
		MaterialGrid  materialGrid = null;
		List<Pcestdmt> list=(List<Pcestdmt>) pcestdmtDaoRemote.findByEstimationNo(estimateNo, deptId, webAppName); 
		//System.out.println("########################### "+list);
		//System.out.println(list.size());
		Iterator<Pcestdmt> it = list.iterator();		
        while (it.hasNext()) 
        { 
        	materialGrid= new MaterialGrid();
        	Pcestdmt pcestdmt = it.next();
        	//System.out.println(pcestdtt);
        	//System.out.println(pcestdtt.getId().getResCd().trim()+" ##################################");
        	materialGrid.setResType(pcestdmt.getResType().trim());
        	materialGrid.setResCd(pcestdmt.getId().getResCd().trim());
        	//materialGrid.setMatNm(inmatmDaoRemote.findName(pcestdtt.getId().getResCd()).trim());
        	//System.out.println(inmatmDaoRemote.findName(pcestdtt.getId().getResCd().trim()).trim()+"   "+"00101");
        	//System.out.println(inmatmDaoRemote.findName(pcestdtt.getId().getResCd().trim()).equals("00101"));
        	//System.out.println(pcestdtt.getResCat());
        	//System.out.println("pcestdtt getResCat() new BigDecimal1"+ pcestdtt.getResCat()==new BigDecimal("1"));
        	if (pcestdmt.getResCat().equals(new BigDecimal("1"))){
        		materialGrid.setMatNm(inmatmDaoRemote.findName(pcestdmt.getId().getResCd().trim(), webAppName).trim());
        	}else materialGrid.setMatNm(pcestdmt.getId().getResCd().trim());
        	//materialGrid.setMatNm(inmatmDaoRemote.findName("B0424"));
        	
        	materialGrid.setResCat(pcestdmt.getResCat());
        	materialGrid.setUom(pcestdmt.getUom());
        	materialGrid.setUnitPrice(pcestdmt.getUnitPrice());
        	materialGrid.setEstimateQty(pcestdmt.getEstimateQty());
        	materialGrid.setEstimateCost(pcestdmt.getEstimateCost());
        	materialGrid.setCustomerQty(pcestdmt.getCustomerQty());
        	materialGrid.setMntQty(pcestdmt.getMntQty());
        	//if (pcestdmt.getMntQty()!=null)
        	//	materialGrid.setMntCost(pcestdmt.getMntQty().multiply(pcestdmt.getUnitPrice()));
        	//else
        	//	materialGrid.setMntQty(new BigDecimal(0));
        	//	materialGrid.setMntCost(new BigDecimal(0));
        	gridList.add(materialGrid);
        }
		return gridList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MaterialGrid> getMaterialGridNew(String estimateNo, String deptId, String webAppName) {
		//getEntityManager(webAppName);
		/**List<MaterialGrid> gridList=new ArrayList<MaterialGrid>();
		String qryStr ="SELECT new estimate.model.MaterialGrid(TRIM(p.resType), TRIM(p.id.resCd), p.resCat, TRIM(i.matNm), TRIM(p.uom), p.tolerance, p.unitPrice, p.estimateQty, p.estimateCost, p.customerQty, p.damageQty, p.mntQty)  from Pcestdmt p , Inmatm i WHERE TRIM(p.id.estimateNo)=:estimateNo AND p.id.deptId=:deptId AND p.resCat=:resCat  AND p.id.resCd=i.matCd ORDER BY p.id.resCd"; 
		Query query=getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", estimateNo);
		query.setParameter("deptId", deptId);
		query.setParameter("resCat", new BigDecimal(1));
		List<MaterialGrid> list1=query.getResultList();
		gridList.addAll(list1);
		//System.out.println("$$ "+list1.size());
		
		      qryStr ="SELECT new estimate.model.MaterialGrid(TRIM(p.resType), TRIM(p.id.resCd), p.resCat, TRIM(p.id.resCd), TRIM(p.uom), p.tolerance, p.unitPrice, p.estimateQty, p.estimateCost, p.customerQty, p.damageQty, p.mntQty)  from Pcestdmt p  WHERE TRIM(p.id.estimateNo)=:estimateNo AND p.id.deptId=:deptId AND p.resCat <> 1 ORDER BY p.id.resCd"; 
		query=getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", estimateNo);
		query.setParameter("deptId", deptId);
		//query.setParameter("resCat", new BigDecimal(1));
		List<MaterialGrid> list2=query.getResultList();
		gridList.addAll(list2);
		//System.out.println("$$ "+list2.size());
		//System.out.println("$$ "+gridList.size());
		list1=null;
		list2=null;
		return gridList;*/
		
		//getEntityManager(webAppName);
		List<MaterialGrid> gridList=new ArrayList<MaterialGrid>();
		String qryStr ="SELECT new estimate.model.MaterialGrid(TRIM(p.resType), TRIM(p.id.resCd), p.resCat, TRIM(i.matNm), TRIM(p.uom), p.tolerance, p.unitPrice, p.estimateQty, p.estimateCost, p.customerQty,p.commitedQty,p.commitedCost,p.damageQty, p.mntQty)  from Pcestdmt p , Inmatm i WHERE TRIM(p.id.estimateNo)=:estimateNo AND p.id.deptId=:deptId AND p.resCat=:resCat  AND p.id.resCd=i.matCd ORDER BY p.id.resCd"; 
		Query query=getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", estimateNo.trim());
		query.setParameter("deptId", deptId);
		query.setParameter("resCat", new BigDecimal(1));
		List<MaterialGrid> list1=query.getResultList();
		gridList.addAll(list1);
		//System.out.println("$$ "+list1.size());
		
		qryStr ="SELECT new estimate.model.MaterialGrid(TRIM(p.resType), TRIM(p.id.resCd), p.resCat, TRIM(p.id.resCd), TRIM(p.uom), p.tolerance, p.unitPrice, p.estimateQty, p.estimateCost, p.customerQty,p.commitedQty,p.commitedCost, p.damageQty, p.mntQty)  from Pcestdmt p  WHERE TRIM(p.id.estimateNo)=:estimateNo AND p.id.deptId=:deptId AND p.resCat <> 1 ORDER BY p.id.resCd"; 
		query=getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", estimateNo.trim());
		query.setParameter("deptId", deptId);
		//query.setParameter("resCat", new BigDecimal(1));
		List<MaterialGrid> list2=query.getResultList();
		gridList.addAll(list2);
		//System.out.println("$$ "+list2.size());
		//System.out.println("$$ "+gridList.size());
		list1=null;
		list2=null;
		return gridList;

		

	}
	
	@Override
	public List<String> jobCloser(List<String> list, String deptId, Date closingDate, String webAppName) {
		List<String> returnList=new ArrayList<String>();
		String x = "";
		String y = "";
		String z = "";
		String temp=null;
		
		for (int i=0; i<=list.size()-1;i++){
			temp =jobCloser(list.get(i), deptId, closingDate, webAppName);
			if (temp.charAt(0)=='#'){
				x=x+temp;
			}else if (temp.charAt(0)=='$'){
				y=y+temp;
			}else{
				z=z+temp+"@";
			}
			
		}
        //String qryStr4 = "select label_9 from pcesthmt where label_9 != 'A' and project_no = '" + jobNumber + "' and dept_id = '" + getSessionBean1().getLoggedInCostCenter() + "'";
		returnList.add(x);
		returnList.add(y);
		returnList.add(z);
		return returnList;
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public String forceJobCloser(String jobNo, String deptId, Date closingDate, String webAppName) {
		//getEntityManager(webAppName);
		try{
			SimpleDateFormat yearFormat=new SimpleDateFormat("yyyy");
			SimpleDateFormat MonthFormat=new SimpleDateFormat("MM");
			Long year=Long.parseLong(yearFormat.format(closingDate));
			Long month=Long.parseLong(MonthFormat.format(closingDate));
			//month=new Long(1);
			System.out.println(year+month);
			//String qryStr=null;
			//Query query; 
			System.out.println("start "+deptId+" "+jobNo+"@");
			//command = "select fund_id,estimate_no --from pcesthmt where project_no = '" + jobNumber + "' and dept_id = '" + getSessionBean1().getLoggedInCostCenter() + "'";
			String qryStr = "select new map(TRIM(hm.fundId), TRIM(hm.id.estimateNo)) from Pcesthmt hm where TRIM(hm.projectNo) = :jobNo and hm.id.deptId = :deptId";
			//System.out.println(pcesthmtDaoRemote.findByJobNo(jobNo, deptId));
			Query query = getEntityManager(webAppName).createQuery(qryStr);
			query.setParameter("jobNo", jobNo);
			query.setParameter("deptId", deptId);
			Map map= null;
			//String map2 = null;
			List<Map> listMap1=query.getResultList();
			System.out.println(listMap1);
			
			if (listMap1.isEmpty()){
				
			}
				//return null;
	        else if (listMap1.size() == 1)
	        	map=listMap1.get(0);
			System.out.println(map);
			String fundId = map.values().toArray()[1].toString();
			String estimateNo = map.values().toArray()[0].toString();
			System.out.println(map);
			System.out.println(fundId+estimateNo);
			
			//command = "select cl_jbs,op_jbs,trans_jbs,add_jbs from pcwipbal where fund_id = '" + fund_id + "' and dept_id = '" + getSessionBean1().getLoggedInCostCenter() + "' and log_yr = '" + year + "' and log_mth = '" + moth + "'";
			qryStr = "select new map(bal.clJbs,bal.opJbs,bal.transJbs,bal.addJbs) from Pcwipbal bal where TRIM(bal.id.fundId) =:fundId AND TRIM(bal.id.deptId) = :deptId AND bal.id.logYr = :year AND bal.id.logMth = :month";
			query = getEntityManager(webAppName).createQuery(qryStr);
			query.setParameter("fundId", fundId);
			query.setParameter("deptId", deptId);
			query.setParameter("year", year);
			query.setParameter("month", month);
			//List<Map> list=query.getResultList();
			//System.out.println(list.size());
			map=(Map) query.getSingleResult();
			//System.out.println(map);
			Integer clBal=Integer.parseInt(map.values().toArray()[3].toString());
			Integer opBal=Integer.parseInt(map.values().toArray()[2].toString());
			Integer transJbs=Integer.parseInt(map.values().toArray()[1].toString());
			Integer addJbs=Integer.parseInt(map.values().toArray()[0].toString());
			System.out.println(map);
			System.out.println(clBal+opBal+transJbs+addJbs);
			
			//qryStr ="SELECT p FROM Pcesthmt WHERE p.project_no =:jobNo AND p.id.deptId = :deptId";
			Pcesthmt pcesthmt=pcesthmtDaoRemote.findByJobNo(jobNo, deptId, webAppName);
			System.out.println(pcesthmt.getStatus()+" "+pcesthmt.getLabel9()+" "+pcesthmt.getConfDt() );
			//Date date=pcesthmt.getConfDt();
			qryStr ="UPDATE Pcesthmt hm SET hm.status =:status, hm.label9 =:label9 , hm.confDt =:closingDate WHERE TRIM(hm.projectNo) =:jobNo AND hm.id.deptId =:deptId";
			//qryStr ="UPDATE Pcesthmt hm SET hm.status =:status , hm.label9 =:label9 WHERE TRIM(hm.projectNo) =:jobNo AND hm.id.deptId =:deptId";
			//qryStr ="UPDATE Pcesthmt hm SET hm.status =:status WHERE TRIM(hm.projectNo) =:jobNo AND hm.id.deptId =:deptId";
			query = getEntityManager(webAppName).createQuery(qryStr);
			query.setParameter("status", new BigDecimal(3));
			query.setParameter("label9", "T");
			query.setParameter("closingDate", closingDate);
			query.setParameter("jobNo", jobNo);
			query.setParameter("deptId", deptId);
			query.executeUpdate();
			//pcesthmt=pcesthmtDaoRemote.findByJobNo(jobNo, deptId);
			//System.out.println("After update "+pcesthmt.getStatus()+" "+pcesthmt.getLabel9());
			//Date date=pcesthmt.getConfDt();
			//pcesthmt.setStatus(new BigDecimal(3));
			//pcesthmt.setLabel9("T");
			//pcesthmt.setConfDt(closingDate);
			//pcesthmtDaoRemote.updatePcesthmt(pcesthmt);
			//pcesthmt=pcesthmtDaoRemote.findByJobNo(jobNo, deptId);
			//System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"+pcesthmt.getStatus()+" "+pcesthmt.getLabel9()+" "+pcesthmt.getConfDt());
			//pcesthmt.setStatus(new BigDecimal(1));
			//pcesthmt.setLabel9(null);
			//pcesthmt.setConfDt(date);
			
			//pcesthmtDaoRemote.updatePcesthmt(pcesthmt);
			//pcesthmt=pcesthmtDaoRemote.findByJobNo(jobNo, deptId);
			//System.out.println("#####################################\n"+pcesthmt.getStatus()+" "+pcesthmt.getLabel9()+" "+pcesthmt.getConfDt());
			//System.out.println(pcesthmtDaoRemote.findByJobNo(jobNo, deptId).getStatus());
			pcesthmt=null;
			
			qryStr = "UPDATE Pcwiph ph set ph.flag = :flag WHERE  TRIM(ph.id.projectNo) = :jobNo";
			query = getEntityManager(webAppName).createQuery(qryStr);
			query.setParameter("flag", "T");
			query.setParameter("jobNo", jobNo);
			query.executeUpdate();
			System.out.println("dddddd");
			
			Integer tranJobNew = transJbs + 1;
			Integer newClBal = opBal + addJbs - tranJobNew;
			
			
			qryStr = "UPDATE Pcwipbal bal set bal.clJbs =:clJbs , bal.transJbs =:transJbs  WHERE TRIM(bal.id.fundId) =:fundId AND TRIM(bal.id.deptId) = :deptId AND bal.id.logYr = :year AND bal.id.logMth = :month";
			query = getEntityManager(webAppName).createQuery(qryStr);
			query.setParameter("clJbs", new BigDecimal(newClBal));//////newClBal
			query.setParameter("transJbs", new BigDecimal(tranJobNew));/////tranJobNew
			query.setParameter("fundId", fundId);
			query.setParameter("deptId", deptId);
			query.setParameter("year", year);
			query.setParameter("month", month);
			query.executeUpdate();
			//System.out.println("#############################");  
			int x = 0;
	        int intMonth = month.intValue();
	        int monthCal = intMonth + 1;
	        for (x = monthCal; x < 13; x++) {
	        	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");  
	        	System.out.println(x);            
	            //command = "select cl_jbs,op_jbs,trans_jbs,add_jbs from pcwipbal where fund_id = trim('" + fund_id + "') and dept_id = '" + getSessionBean1().getLoggedInCostCenter() + "' and log_yr = '" + year + "' and log_mth = '" + month_cal + "'";
				qryStr = "select new map(bal.clJbs,bal.opJbs,bal.transJbs,bal.addJbs) from Pcwipbal bal where TRIM(bal.id.fundId) =:fundId AND TRIM(bal.id.deptId) = :deptId AND bal.id.logYr = :year AND bal.id.logMth = :month";
				query = getEntityManager(webAppName).createQuery(qryStr);
				query.setParameter("fundId", fundId);
				query.setParameter("deptId", deptId);
				query.setParameter("year", year);
				query.setParameter("month", new Long(monthCal));
				map=(Map) query.getSingleResult();
				System.out.println(map);
				if (map!=null){
					clBal=Integer.parseInt(map.values().toArray()[3].toString());
					opBal=Integer.parseInt(map.values().toArray()[2].toString());
					transJbs=Integer.parseInt(map.values().toArray()[1].toString());
					addJbs=Integer.parseInt(map.values().toArray()[0].toString());
					System.out.println("clBal "+clBal);
		            System.out.println("opBal "+opBal);
		            System.out.println("transJbs "+transJbs);
		            System.out.println("addJbs "+addJbs);
					}
				opBal = newClBal;
	            int clbalnew = opBal + addJbs - transJbs;
	            
	            
	            //command = "update pcwipbal set op_jbs = '" + op_bal + "' ,cl_jbs = '" + clbalnew + "'  where fund_id = '" + fund_id + "' and dept_id = '" + getSessionBean1().getLoggedInCostCenter() + "' and log_yr = '" + year + "' and log_mth = '" + month_cal + "'";
	            qryStr = "UPDATE Pcwipbal bal set bal.opJbs =:opJbs , bal.clJbs =:clJbs  WHERE TRIM(bal.id.fundId) =:fundId AND TRIM(bal.id.deptId) = :deptId AND bal.id.logYr = :year AND bal.id.logMth = :month";
				query = getEntityManager(webAppName).createQuery(qryStr);
				query.setParameter("opJbs", new BigDecimal(opBal));
				query.setParameter("clJbs", new BigDecimal(clbalnew));//////clbalnew
				query.setParameter("fundId", fundId);
				query.setParameter("deptId", deptId);
				query.setParameter("year", year);
				query.setParameter("month", new Long(monthCal));
				query.executeUpdate();
				
				newClBal = clbalnew;
				monthCal++;
				
				
	        
	        
	        }//FOR LOOP
	        //String com1 = "select sum(commited_cost) as total from pcestdmt where estimate_no = '" + estimate_no + "' and dept_id = '" + getSessionBean1().getLoggedInCostCenter() + "' and res_cat =1";
	        qryStr ="SELECT SUM(p.commitedCost) AS total from Pcestdmt p where TRIM(p.id.estimateNo) = :estimateNo AND p.id.deptId = :deptId AND p.resCat =1";
	        query = getEntityManager(webAppName).createQuery(qryStr);
			query.setParameter("estimateNo", estimateNo);
			query.setParameter("deptId", deptId);
			List<BigDecimal> total=query.getResultList();
			//System.out.println("#############################");
			System.out.println(total.get(0));//null
			Double opBalfu = 0.0;
			Double clBalfu = 0.0;
			Double addJobfu = 0.0;
			Double tranJobfu = 0.0;
			Double clbalNew = 0.0;
			Double transAmt = 0.0;
			Double dTotal = 0.0;
			if (total.get(0)!=null){
				dTotal=Double.parseDouble(total.get(0).toString());
			} 
			//String com = "select res_cd,res_cat,commited_cost from pcestdmt where estimate_no = '" + estimate_no + "' and dept_id = '" + getSessionBean1().getLoggedInCostCenter() + "'";
			qryStr ="select new map(TRIM(dmt.id.resCd),dmt.resCat,dmt.commitedCost) from Pcestdmt dmt where TRIM(dmt.id.estimateNo) = :estimateNo and dmt.id.deptId =:deptId" ;
			//qryStr ="select dmt from Pcestdmt dmt where dmt.id.estimateNo = :estimateNo and dmt.id.deptId =:deptId" ;
			query = getEntityManager(webAppName).createQuery(qryStr);
			System.out.println("estimateNo deptId"+estimateNo+" "+deptId);
			query.setParameter("estimateNo", estimateNo);
			query.setParameter("deptId", deptId);
			List<Map> listMap= query.getResultList();
			//map= (Map)query.getSingleResult();
			//System.out.println(map);
			System.out.println("listdd "+listMap);
			
			if (listMap.size()>0){////row5  
				System.out.println("fundId "+fundId+" "+deptId+" "+year+" "+month+"$");
				//command = "select op_bal,add_amt,trans_amt,cl_bal from pcwipfu where fund_id = '" + fund_id + "' and dept_id = '" + getSessionBean1().getLoggedInCostCenter() + "' and log_yr = '" + year + "' and log_mth = '" + moth + "' and res_cd ='MATERIAL'";
				qryStr = "select new map(fu.opBal,fu.addAmt,fu.transAmt,fu.clBal) from Pcwipfu fu where TRIM(fu.id.fundId) =:fundId and TRIM(fu.id.deptId) = :deptId and fu.id.logYr = :year and fu.id.logMth = :month and TRIM(fu.id.resCd) =:resCd";
				//qryStr = "select new map(fu.opBal,fu.addAmt,fu.transAmt,fu.clBal) from Pcwipfu fu where TRIM(fu.id.fundId) =:fundId AND fu.id.deptId = :deptId";
				query = getEntityManager(webAppName).createQuery(qryStr);
				query.setParameter("fundId", fundId);
				query.setParameter("deptId", deptId);
				query.setParameter("year", year);
				query.setParameter("month", month);
				query.setParameter("resCd", "MATERIAL");
				
				//map=(Map)query.getSingleResult();
				List<Map> listMap2=query.getResultList();
				System.out.println("listMap2 "+listMap2);
				//Integer e=1.2;
				if (!listMap2.isEmpty()){
					if(listMap2.size()==1){
						opBalfu=Double.parseDouble(map.values().toArray()[3].toString());
						addJobfu=Double.parseDouble(map.values().toArray()[2].toString());
						tranJobfu=Double.parseDouble(map.values().toArray()[1].toString());
						clBalfu=Double.parseDouble(map.values().toArray()[0].toString());
						System.out.println("listMap2 "+opBalfu+" "+addJobfu+" "+tranJobfu+" "+clBalfu);
					}
					
				}
				
				tranJobfu = tranJobfu + dTotal;
	            clbalNew = opBalfu + addJobfu - tranJobfu;
	            System.out.println("tranJobfu "+tranJobfu+" "+clbalNew);
	            //command = "update pcwipfu set cl_bal = '" + clbal_new + "' , trans_amt = '" + tran_jobfu + "'  where fund_id = '" + fund_id + "' and dept_id = '" + getSessionBean1().getLoggedInCostCenter() + "' and log_yr = '" + year + "' and log_mth = '" + moth + "' and res_cd ='MATERIAL'";
	            qryStr = "UPDATE Pcwipfu fu SET fu.clBal =:clbalNew , fu.transAmt =:tranJobfu where fu.id.fundId =:fundId and TRIM(fu.id.deptId) = :deptId and fu.id.logYr = :year and fu.id.logMth = :month and TRIM(fu.id.resCd) =:resCd";
	            query = getEntityManager(webAppName).createQuery(qryStr);
	            query.setParameter("clbalNew", new BigDecimal(clbalNew));
	            System.out.println("clbalNew");
	            query.setParameter("tranJobfu", new BigDecimal(tranJobfu));
	            query.setParameter("fundId", fundId);
	            System.out.println("fundId");
	            query.setParameter("deptId", deptId);
				query.setParameter("year", year);
				query.setParameter("month", month);
				query.setParameter("resCd", "MATERIAL");
				query.executeUpdate();
				//System.out.print("############################################################");
				
				int xx = 0;
				intMonth = month.intValue();
	            monthCal = intMonth + 1;
	            for (xx = monthCal; xx < 13; xx++) {//For loop T
	            	//command = "select op_bal,add_amt,trans_amt,cl_bal from pcwipfu where fund_id = '" + fund_id + "' and dept_id = '" + getSessionBean1().getLoggedInCostCenter() + "' and log_yr = '" + year + "' and log_mth = '" + month_cal + "' and res_cd ='MATERIAL'";
	            	qryStr = "select new map(fu.opBal,fu.addAmt,fu.transAmt,fu.clBal) from Pcwipfu fu where TRIM(fu.id.fundId) =:fundId and TRIM(fu.id.deptId) = :deptId and fu.id.logYr = :year and fu.id.logMth = :month and TRIM(fu.id.resCd) =:resCd";
					query = getEntityManager(webAppName).createQuery(qryStr);
					//System.out.print(qryStr);
					query.setParameter("fundId", fundId);
					query.setParameter("deptId", deptId);
					query.setParameter("year", year);
					query.setParameter("month", new Long(monthCal));
					query.setParameter("resCd", "MATERIAL");
					map=(Map)query.getSingleResult();
					if (!map.isEmpty()){
						opBalfu=Double.parseDouble(map.values().toArray()[3].toString());
						addJobfu=Double.parseDouble(map.values().toArray()[2].toString());
						tranJobfu=Double.parseDouble(map.values().toArray()[1].toString());
						clBalfu=Double.parseDouble(map.values().toArray()[0].toString());
						
						}
					opBalfu = clbalNew;
	                double clbalNewFu = opBalfu + addJobfu - tranJobfu;
	                
	                //command = "update pcwipfu set op_bal = '" + op_balfu + "' , cl_bal = '" + clbalnewfu + "'  where fund_id = '" + fund_id + "' and dept_id = '" + getSessionBean1().getLoggedInCostCenter() + "' and log_yr = '" + year + "' and log_mth = '" + month_cal + "' and res_cd ='MATERIAL'";
	                qryStr = "UPDATE Pcwipfu fu SET fu.opBal =:opBal , fu.clBal =:clBal where TRIM(fu.id.fundId) =:fundId and TRIM(fu.id.deptId) = :deptId and fu.id.logYr = :year and fu.id.logMth = :month and TRIM(fu.id.resCd) =:resCd";
	                query = getEntityManager(webAppName).createQuery(qryStr);
	                //System.out.print("UPDATE");
	                query.setParameter("opBal", new BigDecimal(opBal));
					query.setParameter("clBal", new BigDecimal(clBal));
					query.setParameter("fundId", fundId);
					query.setParameter("deptId", deptId);
					query.setParameter("year", year);
					query.setParameter("month", new Long(monthCal));
					query.setParameter("resCd", "MATERIAL");
					query.executeUpdate();
					
					
					clbalNew = clbalNewFu;
					monthCal++;
					
					
	            }//for loop T end
	            int i=listMap.size();
	            int j=0;
	            //String resCd;
	            do { //do Start
	            	Map mapTemp=listMap.get(j);
	            	System.out.print(mapTemp);
	            	String resCd=mapTemp.values().toArray()[2].toString().trim();
	            	System.out.print(resCd);
	            	BigDecimal resCat=new BigDecimal(mapTemp.values().toArray()[1].toString());
	            	System.out.print(resCat);
	            	if (resCd != null) {//if 034
	            		if (Integer.valueOf(String.valueOf(resCat)).intValue() == 1) {//if 036
	            			System.out.print("resCat is equal to 1");
	            		} else {//036
	            			//command = "select op_bal,add_amt,trans_amt,cl_bal from pcwipfu where fund_id = '" + fund_id + "' and dept_id = '" + getSessionBean1().getLoggedInCostCenter() + "' and log_yr = '" + year + "' and log_mth = '" + moth + "' and res_cd ='" + strSec5 + "'";
	            			qryStr = "select new map(fu.opBal,fu.addAmt,fu.transAmt,fu.clBal) from Pcwipfu fu where TRIM(fu.id.fundId) =:fundId and TRIM(fu.id.deptId) = :deptId and fu.id.logYr = :year and fu.id.logMth = :month and TRIM(fu.id.resCd) =:resCd";
	        				query = getEntityManager(webAppName).createQuery(qryStr);
	        				System.out.print(fundId+" "+deptId+" "+year+" "+month+" "+resCd);
	        				query.setParameter("fundId", fundId);
	        				query.setParameter("deptId", deptId);
	        				query.setParameter("year", year);
	        				query.setParameter("month", month);
	        				query.setParameter("resCd", resCd);
	        				map=(Map)query.getSingleResult();
	        				System.out.print(map);
	        				if (!map.isEmpty()){//if 37
	        					opBalfu=Double.parseDouble(map.values().toArray()[3].toString());
	        					addJobfu=Double.parseDouble(map.values().toArray()[2].toString());
	        					tranJobfu=Double.parseDouble(map.values().toArray()[1].toString());
	        					clBalfu=Double.parseDouble(map.values().toArray()[0].toString());
	        					
	        				}// if end 37
	        				//com1 = "select commited_cost as total from pcestdmt where estimate_no = '" + estimate_no + "' and dept_id = '" + getSessionBean1().getLoggedInCostCenter() + "' and res_cd ='" + strSec5 + "'";
	        				qryStr = "select dmt.commitedCost from Pcestdmt dmt where TRIM(dmt.id.estimateNo) = :estimateNo and dmt.id.deptId =:deptId and TRIM(dmt.id.resCd) =:resCd";
	        				query = getEntityManager(webAppName).createQuery(qryStr);
	        				query.setParameter("estimateNo", estimateNo);
	        				query.setParameter("deptId", deptId);
	        				query.setParameter("resCd", resCd);
	        				List<BigDecimal> listcommitedCost= query.getResultList();
	        				System.out.print(listcommitedCost);
	        				double total2 = 0;
	        				BigDecimal commitedCost=null;
	        				if (listcommitedCost.isEmpty())
	        					commitedCost=null;
	        		        else if (listcommitedCost.size() == 1)
	        		        	commitedCost=listcommitedCost.get(0);
	        		        // throw new NonUniqueResultException();
	        		        if (commitedCost != null) {// if 39
	        		        	total2 = Double.parseDouble(commitedCost.toString());
	                        }//39
	        		        System.out.print("total2 "+total2);
	        		        tranJobfu = tranJobfu + total2;
	                        clbalNew = opBalfu + addJobfu - tranJobfu; 
	                        //command = "update pcwipfu set cl_bal = '" + clbal_new + "' , trans_amt = '" + tran_jobfu + "'  where fund_id = trim('" + fund_id + "') and dept_id = '" + getSessionBean1().getLoggedInCostCenter() + "' and log_yr = '" + year + "' and log_mth = '" + moth + "' and res_cd ='" + strSec5 + "'";
	                        qryStr = "UPDATE Pcwipfu fu SET fu.clBal =:clBal, fu.transAmt =:transAmt  where TRIM(fu.id.fundId) =:fundId and TRIM(fu.id.deptId) = :deptId and fu.id.logYr = :year and fu.id.logMth = :month and TRIM(fu.id.resCd) =:resCd";
	                        query = getEntityManager(webAppName).createQuery(qryStr);
	                        query.setParameter("clBal", new BigDecimal(clbalNew));
	        				query.setParameter("transAmt", new BigDecimal(tranJobfu));
	                        query.setParameter("fundId", fundId);
	        				query.setParameter("deptId", deptId);
	        				query.setParameter("year", year);
	        				query.setParameter("month", month);
	        				query.setParameter("resCd", resCd);
	        				query.executeUpdate();
	        				System.out.print(fundId+" "+deptId+" "+year+" "+month+" "+resCd);
	        				int y = 0;
	        				intMonth = month.intValue();
	                        monthCal = intMonth + 1;
	                        for (y = monthCal; y < 13; y++) {// For Loop 041
	                        	//command = "select op_bal,add_amt,trans_amt,cl_bal from pcwipfu where fund_id = '" + fund_id + "' and dept_id = '" + getSessionBean1().getLoggedInCostCenter() + "' and log_yr = '" + year + "' and log_mth = '" + month_cal + "' and res_cd ='" + strSec5 + "'";
	                        	qryStr = "select new map(fu.opBal,fu.addAmt,fu.transAmt,fu.clBal) from Pcwipfu fu where TRIM(fu.id.fundId) =:fundId and TRIM(fu.id.deptId) = :deptId and fu.id.logYr = :year and fu.id.logMth = :month and TRIM(fu.id.resCd) =:resCd";
	            				query = getEntityManager(webAppName).createQuery(qryStr);
	            				query.setParameter("fundId", fundId);
	            				query.setParameter("deptId", deptId);
	            				query.setParameter("year", year);
	            				query.setParameter("month", month);
	            				query.setParameter("resCd", resCd);
	            				map=(Map)query.getSingleResult();
	            				if (!map.isEmpty()){//if 42
	            					opBalfu=Double.parseDouble(map.values().toArray()[3].toString());
	            					addJobfu=Double.parseDouble(map.values().toArray()[2].toString());
	            					tranJobfu=Double.parseDouble(map.values().toArray()[1].toString());
	            					clBalfu=Double.parseDouble(map.values().toArray()[0].toString());
	            					
	            				}// if end 42
	            				System.out.print("select");
	            				opBalfu = clbalNew;
	                            double clbalnewfu = opBalfu + addJobfu - tranJobfu;
	                            //command = "update pcwipfu set op_bal = '" + op_balfu + "' , cl_bal = '" + clbalnewfu + "'  where fund_id = '" + fund_id + "' and dept_id = '" + getSessionBean1().getLoggedInCostCenter() + "' and log_yr = '" + year + "' and log_mth = '" + month_cal + "' and res_cd ='" + strSec5 + "'";
	                            qryStr = "UPDATE Pcwipfu fu SET fu.opBal =:opBal, fu.clBal =:clBal  where TRIM(fu.id.fundId) =:fundId and TRIM(fu.id.deptId) = :deptId and fu.id.logYr = :year and fu.id.logMth = :month and TRIM(fu.id.resCd) =:resCd";
	                            query = getEntityManager(webAppName).createQuery(qryStr);
	                            query.setParameter("opBal", new BigDecimal(opBalfu));
	            				query.setParameter("clBal", new BigDecimal(clbalnewfu));
	                            query.setParameter("fundId", fundId);
	            				query.setParameter("deptId", deptId);
	            				query.setParameter("year", year);
	            				query.setParameter("month", month);
	            				query.setParameter("resCd", resCd);
	            				query.executeUpdate();
	            				System.out.print("UPDATE");
	            				clbalNew = clbalnewfu;
	                            monthCal++;
	                        	
	                        }// For Loop end 041
	                        
	            			
	            		} //else 036
	            		
	            	}// if end 034
	            	
	            	
	            	
	            	j=j+1;
	            } while (j<i);//do end
				
				
			}//row5 
			return "#"+jobNo;
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			//throw new RuntimeException(e);
			return "$"+jobNo;
		}
		
	
	}

	@Override
	public void insert(Pcesthmt pcesthmt, Speststd speststd, List<Pcestdmt> list, String webAppName) {
		try{
			if(pcesthmt != null){
				pcesthmtDaoRemote.createPcesthmt(pcesthmt, webAppName);
			}
			if(speststd != null){
				speststdDaoRemote.createSpeststd(speststd, webAppName);
			}
			if(list != null && list.size() > 0){
				for(int i=0; i<=list.size()-1; i++){
					pcestdmtDaoRemote.createPcestdmt(list.get(i), webAppName);
				}
			}
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
	}
	//has to be deleted
	@Override
	public void update(Pcesthmt pcesthmt, Speststd speststd, List<Pcestdmt> addlist, List<Pcestdmt> updList, Long newRevNo, String webAppName) {
		try{
			pcesthmtDaoRemote.updatePcesthmt(pcesthmt, webAppName);
			pcesthmtDaoRemote.updateRevNo(pcesthmt, newRevNo, webAppName);
			speststdDaoRemote.updateSpeststd(speststd, webAppName);
			
			if (addlist!=null){
				for(int i=0; i<=addlist.size()-1; i++){
					pcestdmtDaoRemote.createPcestdmt(addlist.get(i), webAppName);
				}
				updateNewResType(pcesthmt, addlist, newRevNo, webAppName);
			}
			if (updList!=null){
			for(int j=0; j<=updList.size()-1; j++){
				pcestdmtDaoRemote.updatePcestdmt(updList.get(j), webAppName);
			}}
			if (updList!=null){
				pcestdmtDaoRemote.updateRevNo(updList.get(0), newRevNo, webAppName);
				
			}
			
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	public void update(Pcesthmt pcesthmt, Speststd speststd, List<Pcestdmt> addlist, List<Pcestdmt> updList, Long newRevNo, List<Spestlab> insertLabList, List<Spestlab> updateLabList, String webAppName) {
		try{
			pcesthmtDaoRemote.updatePcesthmt(pcesthmt, webAppName);
			pcesthmtDaoRemote.updateRevNo(pcesthmt, newRevNo, webAppName);
			speststdDaoRemote.updateSpeststd(speststd, webAppName);
			
			if (addlist!=null){
				for(int i=0; i<=addlist.size()-1; i++){
					pcestdmtDaoRemote.createPcestdmt(addlist.get(i), webAppName);
				}
				updateNewResType(pcesthmt, addlist, newRevNo, webAppName);
			}
			if (updList!=null){
			for(int j=0; j<=updList.size()-1; j++){
				pcestdmtDaoRemote.updatePcestdmt(updList.get(j), webAppName);
			}}
			if (updList!=null){
				pcestdmtDaoRemote.updateRevNo(updList.get(0), newRevNo, webAppName);
				
			}
			//new 
			if (insertLabList!=null){
			for(int k=0; k<=insertLabList.size()-1; k++){
				spestlabDaoRemote.createSpestlab(insertLabList.get(k), webAppName);
			}}
			if (updateLabList!=null){
				for(int n=0; n<=updateLabList.size()-1; n++){
					spestlabDaoRemote.updateSpestlab(updateLabList.get(n), webAppName);
				}}
			
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void delete(List<PcestdmtPK> list, Pcesthmt inPcesthmt, String webAppName) {
		try{
			pcesthmtDaoRemote.updatePcesthmt(inPcesthmt, webAppName);
			for(int i=0; i<=list.size()-1; i++){
				PcestdmtPK pcestdttPK=list.get(i);
				Pcestdmt pcestdmt=pcestdmtDaoRemote.findBy3PK(pcestdttPK.getEstimateNo(), pcestdttPK.getDeptId(), pcestdttPK.getResCd(), webAppName);
				pcestdmtDaoRemote.removePcestdmt(pcestdmt, webAppName); 
			}
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Speststd getSpeststd(String jobNo,String deptId, String webAppName) throws PersistenceException {
		Pcesthmt pcesthmt=pcesthmtDaoRemote.findByJobNo(jobNo, deptId, webAppName);
		SpeststdPK id=new SpeststdPK(); 
		id.setEstimateNo(pcesthmt.getId().getEstimateNo().trim());
		id.setDeptId(deptId);
		return speststdDaoRemote.findById(id, webAppName);
	}
	
	@Override
	public StdDetail getStdDetail(String jobNo,String deptId, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		//System.out.println(pcesthmtDaoRemote.findByJobNo(jobNo, deptId).getId().getEstimateNo().length());
		String qryStr ="SELECT new job.model.StdDetail(serviceDistance, lineLength, connectorType, poleNo, stayNo ,strutNo, span) FROM Speststd s WHERE s.id.estimateNo =:estimateNo AND s.id.deptId =:deptId ";
		//String qryStr ="SELECT S FROM Speststd s WHERE TRIM(s.id.estimateNo) =:estimateNo AND s.id.deptId =:deptId ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", pcesthmtDaoRemote.findByJobNo(jobNo, deptId, webAppName).getId().getEstimateNo().trim());
		query.setParameter("deptId", deptId);
		StdDetail stdDetail= (StdDetail) query.getSingleResult();
		//List<Speststd> list= query.getResultList();
		//System.out.println(stdDetail);
		return stdDetail;
	}

	@Override
	public WiringInfo getWiringInfo(String jobNo, String deptId, String webAppName) {
		ApplicationReference applicationReference=applicationReferenceDaoRemote.findByJobNo(jobNo,deptId, webAppName);
		//Pcestdmt pcestdmt=pcestdmtDaoRemote.findByJobNo(jobNo, deptId);
		//System.out.println(pcestdmt.getId().getEstimateNo()+" "+pcestdmt.getId().getDeptId());
		//applicationReference.getId().getApplicationId();
		WiringInfo wiringInfo=wiringLandDetailDaoRemote.getWiringInfo(applicationReference.getId().getApplicationId(), deptId, webAppName);
		return wiringInfo;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@SuppressWarnings({ "unchecked"})
	@Override
	public String jobCloser(String jobNo, String deptId, Date closingDate , String webAppName) {
		//getEntityManager(webAppName);
		try{	
		//SimpleDateFormat yearFormat=new SimpleDateFormat("yyyy");
		//SimpleDateFormat MonthFormat=new SimpleDateFormat("MM");
		//int year  =2010;
		//int month =11;
		//System.out.println(yearFormat.format(closingDate));
		//Long year=Long.parseLong(yearFormat.format(closingDate));
		//System.out.println(Integer.parseInt(MonthFormat.format(closingDate)));
		//Long month=Long.parseLong(MonthFormat.format(closingDate));
		//month=new Long(1);
		//long year = closingDate.getTime();
        //String month = close_date.substring(5, 7);
        //int moth = Integer.valueOf(month).intValue();
		//System.out.println(year+month);
		StringBuffer strBuff = new StringBuffer();
		String qryStr="select TRIM(h.id.docNo) from Pctrxhtt h, Pctrxdtt d where h.id.docNo=d.id.docNo and h.id.docPf = d.id.docPf and h.id.deptId=d.id.deptId  and h.status <> 0 and TRIM(d.projectNo)=:jobNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        query.setParameter("jobNo", jobNo);
        List<String> docNolist1=query.getResultList();
        System.out.println(docNolist1);
        if (docNolist1.size()!=0){
        	strBuff.append("Job Number : " + jobNo+"\n");
        	for (int i=0; i<=docNolist1.size()-1;i++ ){
        		strBuff.append("PCESTHTT : " + docNolist1.get(i)+"\n");
        	}
        }
		
		qryStr=null;
		query=null;
		qryStr="select  TRIM(h.id.docNo) from Pctrxhmt h, Pctrxdtt dt where h.id.docNo=dt.id.docNo and h.id.docPf = dt.id.docPf and h.id.deptId=dt.id.deptId and h.status <> 2 and TRIM(dt.projectNo) =:jobNo";
		query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("jobNo", jobNo);
        List<String> docNolist2=query.getResultList();
		System.out.println(docNolist2);
		if (docNolist2.size()!=0){
        	strBuff.append("Job Number : " + jobNo+"\n");
        	for (int i=0; i<=docNolist2.size()-1;i++ ){
        		strBuff.append("PCESTHMT : " + docNolist2.get(i)+"\n");
        	}
        }
		qryStr=null;
		query=null;
		qryStr = "select TRIM(m.id.docNo) from Mtreqhmt m where m.id.deptId =:deptId and status <> 8 and status <> 9 and TRIM(req_source)=:jobNo";
		query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("jobNo", jobNo);
        List<String> docNolist3=query.getResultList();
		System.out.println(docNolist3);
		if (docNolist3.size()!=0){
        	strBuff.append("Job Number : " + jobNo+"\n");
        	for (int i=0; i<=docNolist3.size()-1;i++ ){
        		strBuff.append("MTREQHMT : " + docNolist3.get(i)+"\n");
        	}
        }
		qryStr=null;
		query=null;
		qryStr = "select TRIM(a.label9) from Pcesthmt a where TRIM(a.label9) != :flag and TRIM(a.projectNo) =:jobNo AND a.id.deptId = :deptId";
        query = getEntityManager(webAppName).createQuery(qryStr);
        query.setParameter("flag", "A");
        query.setParameter("jobNo", jobNo);
        query.setParameter("deptId", deptId);
        List<String> flaglist=query.getResultList();
        System.out.println(flaglist);
        if (flaglist.size()!=0){
        	strBuff.append("Job Number : " + jobNo+"\n");
        	for (int i=0; i<=flaglist.size()-1;i++ ){
        		strBuff.append("Flag : " + flaglist.get(i)+"\n");
        	}
        }
        qryStr=null;
		query=null;
		
		if ((docNolist1.size() > 0) || (docNolist2.size() > 0 || docNolist3.size() > 0 || flaglist.size() > 0)) {
			System.out.println(strBuff);
			return strBuff.toString();
		}else {//else main
			return forceJobCloser(jobNo, deptId,closingDate, webAppName);
			//return null;
		}//else main
		
	}catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				context.setRollbackOnly();
				throw new RuntimeException(e);
	}	
		
	
	}
	public static void main(String[] args){
		JobEjb bean=new JobEjb();
		bean.getClosingJobInfo("510.20/12.008", "510.20", "TEST");
		try {
			//System.out.println("E "+bean.getAvailableMaterials("441.40", null, "R2"));
			//System.out.println("E "+bean.getDefaultLoopMaterialGrid("541.00", new Long(1), new Long(30), "UG", "R1"));
			
			//System.out.println("E "+bean.getMaterialGridByMatCd("554.10", "A0203", new Double("1"), "R2"));
			//System.out.println(bean.getDefaultMaterialGrid("554.10",  new Long(1), new Long(30), "OH", "R3"));
			System.out.println("hi");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public List<Pcfunddm> getFundSourceList(String deptId, String webAppName) {
		return pcfunddmDaoRemote.getFundSourceList(deptId, webAppName);
	}
	
	@Override
	public void updateNewResType(Pcesthmt pcesthmt, List<Pcestdmt> addList, Long newRevNo, String webAppName){
		//try {
			//System.out.println("1");
		
	        int i=addList.size();
	        int j=0;
	        //System.out.println("11");
	        do{
	        	//System.out.println("111");
	        	String resType = addList.get(j).getResType().trim();
	        	
	            BigDecimal resCat = getResCat(resType, webAppName);
	            //System.out.println(resCat);
	            String estimateNo = pcesthmt.getId().getEstimateNo();
	            String deptId = pcesthmt.getId().getDeptId();
	            String projectNo = pcesthmt.getProjectNo();
	            //System.out.println("2");
	            
	            //deptId
	            //String resType = 
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
	            Long year= new Long(sdf.format(pcesthmt.getReviseDt()).substring(0, 4).trim());
	            Long month =new Long(sdf.format(pcesthmt.getReviseDt()).substring(5, 7).trim());
	            //int monthInt = month.intValue();
	            //System.out.println("3");
	            //System.out.println(new BigDecimal(1).equals(new BigDecimal(1)));
	            if (resCat!=null){
	            if(resCat.equals(new BigDecimal(1))){//if 1
	            	
	            }else {//if 1
	            	if(isResTypeAvailable(estimateNo, deptId, year, month, resType, webAppName)){
	            		//System.out.println("4");
	            		
	            	}else{
	            		int index=1;
	            		//System.out.println("5");
	            		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd");
	            		//sdf.format(pcesthmt.getReviseDt());
	            		//Calendar calendar=Calendar.getInstance();
	            		//calendar.getTime().
	                    for(int k=0; k < 12; k++){
	                    	//System.out.println("6");
	                    	Pcprjbal pcprjbal=new Pcprjbal();
	                    	PcprjbalPK id=new PcprjbalPK();
	                    	
	                    	id.setEstimateNo(estimateNo);
	                    	id.setDeptId(deptId);
	                    	id.setResType(resType);
	                    	id.setYrInd(year);
	                    	id.setMthInd(index);
	                    	pcprjbal.setId(id);
	                    	pcprjbal.setCatCd(pcesthmt.getCatCd());
	                    	pcprjbal.setRevNo(new BigDecimal(newRevNo));
	                    	pcprjbal.setResCat(resCat);
	                    	pcprjbal.setOpBal(new BigDecimal(0));
	                    	pcprjbal.setClBal(new BigDecimal(0));
	                    	pcprjbal.setProjectNo(projectNo);
	                    	//System.out.println("@@@@@@@@@@kosdlksdnf "+pcprjbal);
	                    	pcprjbalDaoRemote.createPcprjbal(pcprjbal, webAppName);
	                    	index++;
	                    	//System.out.println(index);
	
	           	
	                    }
	            		
	            	}
	            	
	            	
	            }//if 1 else
	            }
	            
	            
	        j++;	
	        }while(j<i);
	//	}catch (Exception e) {
	//		
	//	}
        
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean isResTypeAvailable(String estimateNo,String deptId, Long year, Long month, String resType, String webAppName) {
		//getEntityManager(webAppName);
		//System.out.println("isResTypeAvailable");
		//command = "select * from pcprjbal  where estimate_no = '" + estimateNo  + "' and dept_id = '" + dptID  + "' and yr_ind = '"+ yearLogin + "' and mth_ind = '"+ month_int + "' and res_type = '"+ resType +"'";
		String qryStr="select bal from Pcprjbal bal where TRIM(bal.id.estimateNo)= :estimateNo and bal.id.deptId= :deptId  and bal.id.yrInd =:yrInd and bal.id.mthInd =:mthInd and TRIM(bal.id.resType)= :resType";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("estimateNo", estimateNo);
		query.setParameter("deptId", deptId);
		query.setParameter("yrInd", year);
		query.setParameter("mthInd", month);
		query.setParameter("resType", resType);
		List<String> list=query.getResultList();
		System.out.print(list);
		if (list.isEmpty())
			return false;
        else if (list.size() == 1)
        	return true;
        throw new NonUniqueResultException();
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BigDecimal getResCat(String resType, String webAppName) {
		//getEntityManager(webAppName);
		System.out.print("getResCat "+resType+" "+resType.length());
        //
        
		//String tmpstrResCat ="";
        //String command = "select res_cat from pcrstypm where res_type = " + "'" + resType  + "'" ;
        String qryStr="select m.resCat from Pcrstypm m where TRIM(m.resType) = :resType";
        Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("resType", resType.toString());
		List<BigDecimal> list=query.getResultList();
		System.out.print(list);
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
		
     
    }
	
	@SuppressWarnings({"unchecked" })
	@Override
	public List<FinishedJobInfo> getFinishedJobsForBilling(String deptId, String  catCd,String webAppName) {
		//getEntityManager(webAppName);  
		//System.out.println("deptId "+deptId);
          //List resultList = null;
          try
          {
                String qryStr = " select new job.model.FinishedJobInfo(TRIM(s.id.projectNo), TRIM(p.id.estimateNo) , a1.applicationType, a2.firstName, a2.lastName "+
                                        " ,w.serviceStreetAddress,w.serviceSuburb,w.serviceCity, a2.idNo, a1.submitDate, a1.id.applicationId) "+
                                        " from Pcesthmt p, Application a1, Applicant a2 , WiringLandDetail w , Spestcnd s "+
                                        " where (s.status = :status1 OR  s.status = :status2 )"+
                                        " and (s.isExported <> :isExported OR s.isExported is null )"+
                                        " and s.id.projectNo = p.projectNo "+
                                        " and p.id.estimateNo = a1.applicationNo "+
                                        " and a1.idNo = a2.idNo "+
                                        " and w.id.applicationId = a1.id.applicationId "+
                                        " and TRIM(p.catCd) = :catCd "+
                                        " and p.id.deptId = :deptId ";
                      
                Query query = getEntityManager(webAppName).createQuery(qryStr);
                query.setParameter("status1", JobProcessStatus.FINISHED_ENAGIZED);
                query.setParameter("status2", JobProcessStatus.BILLED);
                query.setParameter("isExported", "Y");
                query.setParameter("catCd", catCd);
                query.setParameter("deptId", deptId);
                                  
                List<FinishedJobInfo> list = query.getResultList();
                /*if(!list.isEmpty())
                {
                      resultList = new ArrayList();
                      for(int i=0;i<list.size();i++)
                      {
                            Map map = list.get(i);
                            ArrayList tmpList = new ArrayList();
                            
                            String name = map.values().toArray()[0].toString()+" "+map.values().toArray()[8].toString();
                            
                            String address = map.values().toArray()[7].toString()+", "+
                            map.values().toArray()[6].toString()+", "+
                            map.values().toArray()[5].toString()+", "+
                            map.values().toArray()[10].toString();
                            
                            tmpList.add(map.values().toArray()[4].toString());//proj no
                            tmpList.add(map.values().toArray()[1].toString());//app type
                            tmpList.add(name);
                            tmpList.add(address);
                            tmpList.add(map.values().toArray()[9].toString());//id no
                            tmpList.add(map.values().toArray()[2].toString());//app date
                            tmpList.add(map.values().toArray()[3].toString());//app no
                            resultList.add(tmpList);
                      }
                }*/
                return list;
          }
          catch(Exception ex)
          {
                ex.printStackTrace();
                throw new RuntimeException(ex);
          }
          
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pcesthmt> getJobApprovalReport(String userName, String deptId, Long status,	String value, String webAppName) {
		//getEntityManager(webAppName);
		List<String> authorizedList=securityBeanRemote.getAuthorizedCostCenters(userName, webAppName);
		System.out.print(authorizedList);
		if (authorizedList.size()==0){
			authorizedList.add(deptId);
		}
		System.out.print(authorizedList);
		Query query=null;
		if (value.equals("ES")){
			//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) =:deptId AND g.status = :status  AND stdCost <= 25000  order by g.id.estimateNo");
			//query.setParameter("deptId", deptId);
			//query.setParameter("status", status);
			return null;
	    }else if (value.equals("EA")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND g.stdCost > 25000 AND g.stdCost <= 50000 order by g.id.estimateNo");//BETWEEN 15 and 19
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND g.stdCost > 0 AND g.stdCost <= 50000 order by g.id.estimateNo");//BETWEEN 15 and 19
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND stdCost BETWEEN 25000 and 50000");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			List<Pcesthmt> list = query.getResultList();
			return list;
	    }else if (value.equals("AE") ||value.equals("EE") ){
	    	//List<String> areaDeptIdList=gldeptmDaoRemote.findAreaDeptIdList(deptId);
	    	//areaDeptIdList=null;
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND stdCost > 50000 AND stdCost <= 100000 order by g.id.estimateNo");
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:areaDeptIdList)  AND g.status = :status");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			List<Pcesthmt> list = query.getResultList();
			return list;
	    }else if (value.equals("DGM")){
	    	//List<String> dgmDeptIdList=gldeptmDaoRemote.findDgmDeptIdList(deptId);
	    	System.out.print(authorizedList);
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND stdCost > 100000 AND stdCost <= 200000 order by g.id.estimateNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			List<Pcesthmt> list = query.getResultList();
			return list;
	    }else if (value.equals("AGM")){
	    	//List<String> agmDeptIdList=gldeptmDaoRemote.findAgmDeptIdList(deptId);
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND 200000 < stdCost order by g.id.estimateNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			List<Pcesthmt> list = query.getResultList();
			return list;
	    }else{
	    	return null;
	    }
		//query.setParameter("deptId", deptId);
		//query.setParameter("status", status);
		
	}
	//query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId)= :deptId AND  p.status = :status AND a.applicationType=:applicationType order by p.id.estimateNo");
	@SuppressWarnings("unchecked")
	@Override
	public List<Pcesthmt> getJobApprovalReport(String userName, String deptId,	String authorityLevel, String webAppName) {
		//getEntityManager(webAppName);
		BigDecimal status = null;
		if (authorityLevel.equals("ES")){
			status=new BigDecimal(55); 
	    }else if (authorityLevel.equals("EA")){
	    	status=new BigDecimal(56); 
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	status=new BigDecimal(57); 
	    }else if (authorityLevel.equals("CE")  ){
	    	status=new BigDecimal(61); 	
	    }else if (authorityLevel.equals("DGM")){
	    	status=new BigDecimal(58); 
	    }else if (authorityLevel.equals("AGM")){
	    	status=new BigDecimal(59); 
	    }
		List<String> authorizedList=securityBeanRemote.getAuthorizedCostCenters(userName, webAppName);
		System.out.print(authorizedList);
		if (authorizedList.size()==0){
			authorizedList.add(deptId);
		}
		System.out.print(authorizedList);
		Query query=null;
		if (authorityLevel.equals("ES")){
			query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) =:deptId AND g.status = :status  order by g.projectNo");
			//query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.id.estimateNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthtt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId)= :deptId AND  p.status = :status AND a.applicationType=:applicationType order by p.id.estimateNo");
			query.setParameter("deptId", deptId);
			query.setParameter("status", status);
			List<Pcesthmt> list = query.getResultList();
			return list;
			//return null;
	    }else if (authorityLevel.equals("EA")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND g.stdCost > 25000 AND g.stdCost <= 50000 order by g.id.estimateNo");//BETWEEN 15 and 19
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND g.stdCost > 0 AND g.stdCost <= 50000 order by g.id.estimateNo");//BETWEEN 15 and 19
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND stdCost BETWEEN 25000 and 50000");
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  order by g.projectNo");//BETWEEN 15 and 19
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			List<Pcesthmt> list = query.getResultList();
			return list;
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	//List<String> areaDeptIdList=gldeptmDaoRemote.findAreaDeptIdList(deptId);
	    	//areaDeptIdList=null;
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND stdCost > 50000 AND stdCost <= 100000 order by g.id.estimateNo");
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:areaDeptIdList)  AND g.status = :status");
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  order by g.projectNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			List<Pcesthmt> list = query.getResultList();
			return list;
	    }else if (authorityLevel.equals("CE")){
	    	//List<String> areaDeptIdList=gldeptmDaoRemote.findAreaDeptIdList(deptId);
	    	//areaDeptIdList=null;
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND stdCost > 50000 AND stdCost <= 100000 order by g.id.estimateNo");
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:areaDeptIdList)  AND g.status = :status");
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  order by g.projectNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			List<Pcesthmt> list = query.getResultList();
			return list;	
	    }else if (authorityLevel.equals("DGM")){
	    	//List<String> dgmDeptIdList=gldeptmDaoRemote.findDgmDeptIdList(deptId);
	    	System.out.print(authorizedList);
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND stdCost > 100000 AND stdCost <= 200000 order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  order by g.projectNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			List<Pcesthmt> list = query.getResultList();
			return list;
	    }else if (authorityLevel.equals("AGM")){
	    	//List<String> agmDeptIdList=gldeptmDaoRemote.findAgmDeptIdList(deptId);
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND 200000 < stdCost order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  order by g.projectNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			List<Pcesthmt> list = query.getResultList();
			return list;
	    }else{
	    	return null;
	    }
		//query.setParameter("deptId", deptId);
		//query.setParameter("status", status);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EstimateDisplay> getJobApprovalReportNew(String userName, String deptId,	String authorityLevel, String webAppName) {
		//getEntityManager(webAppName);
		BigDecimal status = null;
		if (authorityLevel.equals("ES")){
			status=new BigDecimal(55); 
	    }else if (authorityLevel.equals("EA")){
	    	status=new BigDecimal(56); 
	    }else if (authorityLevel.equals("AE") || authorityLevel.equals("EE") ){
	    	status=new BigDecimal(57); 
	    }else if (authorityLevel.equals("CE")  ){
	    	status=new BigDecimal(61); 	
	    }else if (authorityLevel.equals("DGM")){
	    	status=new BigDecimal(58); 
	    }else if (authorityLevel.equals("AGM")){
	    	status=new BigDecimal(59); 
	    }
		List<String> authorizedList=securityBeanRemote.getAuthorizedCostCenters(userName, webAppName);
		System.out.print(authorizedList);
		if (authorizedList.size()==0){
			authorizedList.add(deptId);
		}
		System.out.print(authorizedList);
		Query query=null;
		if (authorityLevel.equals("ES")){
			//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) =:deptId AND g.status = :status  order by g.id.estimateNo");
			query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.projectNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthmt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId)= :deptId AND  p.status = :status order by p.projectNo");
			query.setParameter("deptId", deptId);
			query.setParameter("status", status);
			List<EstimateDisplay> list = query.getResultList();
			return list;
			//return null;
	    }else if (authorityLevel.equals("EA")){
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND g.stdCost > 25000 AND g.stdCost <= 50000 order by g.id.estimateNo");//BETWEEN 15 and 19
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND g.stdCost > 0 AND g.stdCost <= 50000 order by g.id.estimateNo");//BETWEEN 15 and 19
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId)= :deptId AND g.status = :status  AND stdCost BETWEEN 25000 and 50000");
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  order by g.id.estimateNo");//BETWEEN 15 and 19
	    	query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.projectNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthmt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId) IN (:authorizedList) AND  p.status = :status order by p.projectNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			System.out.print(query);
			List<EstimateDisplay> list = query.getResultList();
			return list;
	    }else if (authorityLevel.equals("AE") ||authorityLevel.equals("EE") ){
	    	//List<String> areaDeptIdList=gldeptmDaoRemote.findAreaDeptIdList(deptId);
	    	//areaDeptIdList=null;
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND stdCost > 50000 AND stdCost <= 100000 order by g.id.estimateNo");
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:areaDeptIdList)  AND g.status = :status");
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.projectNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthmt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId) IN (:authorizedList) AND  p.status = :status order by p.projectNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			List<EstimateDisplay> list = query.getResultList();
			return list;
	    }else if (authorityLevel.equals("CE") ){
	    	//List<String> areaDeptIdList=gldeptmDaoRemote.findAreaDeptIdList(deptId);
	    	//areaDeptIdList=null;
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND stdCost > 50000 AND stdCost <= 100000 order by g.id.estimateNo");
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:areaDeptIdList)  AND g.status = :status");
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.projectNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthmt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId) IN (:authorizedList) AND  p.status = :status order by p.projectNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			List<EstimateDisplay> list = query.getResultList();
			return list;	
	    }else if (authorityLevel.equals("DGM")){
	    	//List<String> dgmDeptIdList=gldeptmDaoRemote.findDgmDeptIdList(deptId);
	    	System.out.print(authorizedList);
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND stdCost > 100000 AND stdCost <= 200000 order by g.id.estimateNo");
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.projectNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthmt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId) IN (:authorizedList) AND  p.status = :status order by p.projectNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			List<EstimateDisplay> list = query.getResultList();
			return list;
	    }else if (authorityLevel.equals("AGM")){
	    	//List<String> agmDeptIdList=gldeptmDaoRemote.findAgmDeptIdList(deptId);
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  AND 200000 < stdCost order by g.id.estimateNo");
	    	//query = getEntityManager(webAppName).createQuery("SELECT g FROM Pcesthmt g WHERE trim(g.id.deptId) IN (:authorizedList) AND g.status = :status  order by g.id.estimateNo");
	    	query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.EstimateDisplay(TRIM(p.projectNo), p.id.deptId, a.applicationType, a.applicationSubType, p.entBy, p.etimateDt, p.stdCost, s.totalCost, p.fundId, p.fundSource, p.descr, p.rejctUid,  p.rejctDt, p.rejectReason ) FROM Pcesthmt p, Application a, Speststd s WHERE TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=a.applicationNo AND trim(p.id.deptId) IN (:authorizedList) AND  p.status = :status order by p.projectNo");
	    	query.setParameter("authorizedList", authorizedList);
			query.setParameter("status", status);
			List<EstimateDisplay> list = query.getResultList();
			return list;
	    }else{
	    	return null;
	    }
		//query.setParameter("deptId", deptId);
		//query.setParameter("status", status);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public ClosingJobInfo getClosingJobInfo(String jobNo, String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr="SELECT NEW job.model.ClosingJobInfo(TRIM(hmt.projectNo), TRIM(hmt.id.estimateNo), TRIM(hmt.id.deptId), TRIM(hmt.fundSource), TRIM(hmt.catCd), TRIM(hmt.descr), hmt.stdCost, hmt.status ) FROM Pcesthmt hmt WHERE TRIM(hmt.projectNo) = :jobNo AND hmt.id.deptId =:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("jobNo", jobNo);
		query.setParameter("deptId", deptId);
		List<ClosingJobInfo> list=query.getResultList();
		System.out.print(list);
		if (list.isEmpty())
			return null;
		else if (list.size() == 1){
			
			BigDecimal commitedCost;
			qryStr="SELECT SUM(dmt.commitedCost) FROM Pcestdmt dmt WHERE TRIM(dmt.id.estimateNo) = :estimateNo AND dmt.id.deptId =:deptId";
			query =getEntityManager(webAppName).createQuery(qryStr);
			query.setParameter("estimateNo", jobNo);
			query.setParameter("deptId", list.get(0).getEstimateNo().trim());
			List<BigDecimal> list1=query.getResultList();
			System.out.print(list1);
			if (list1.isEmpty())
				commitedCost=null;
	        else if (list1.size() == 1)
	        	commitedCost=list1.get(0);
	        else 
	        	commitedCost=null;
			list.get(0).setCommitedCost(commitedCost);
			return list.get(0);
			}
		throw new NonUniqueResultException();
		
		
		
		//BigDecimal commitedCost;
		//String qryStr="SELECT SUM(dmt.commitedCost) FROM Pcestdmt dmt WHERE TRIM(dmt.projectNo) = :jobNo AND dmt.id.deptId =:deptId";
		//Query query =getEntityManager(webAppName).createQuery(qryStr);
		//query.setParameter("jobNo", jobNo);
		//query.setParameter("deptId", deptId);
		//List<BigDecimal> list1=query.getResultList();
		//System.out.print(list1);
		//if (list1.isEmpty())
		//	commitedCost=null;
        //else if (list1.size() == 1)
        //	commitedCost=list1.get(0);
        //else 
        //	commitedCost=null;
		//
		//if (commitedCost!=null){
		//	qryStr="SELECT NEW job.model.ClosingJobInfo(hmt.projectNo, hmt.id.estimateNo, hmt.id.deptId, hmt.fundSource, hmt.catCd, hmt.stdCost, hmt.status ) FROM Pcesthmt hmt WHERE hmt.projectNo = :jobNo AND hmt.id.deptId =:deptId";
		//	query = getEntityManager(webAppName).createQuery(qryStr);
		//	query.setParameter("jobNo", jobNo);
		//	query.setParameter("deptId", deptId);
		//	List<ClosingJobInfo> list=query.getResultList();
		//	System.out.print(list);
		//	if (list.isEmpty())
		//		return null;
		//	else if (list.size() == 1){
		//		list.get(0).setCommitedCost(commitedCost);
		//		return list.get(0);
		//		}
		//	throw new NonUniqueResultException();
		//}else{
		//	throw new NonUniqueResultException();
		//}
	}

	@Override
	public Boolean isMaterialIssued(String jobNo, String deptId,BigDecimal issueTo, BigDecimal status, String webAppName) {
		return intrhmtDaoRemote.isMaterialIssued(jobNo, deptId, issueTo, status, webAppName);
	}
	
	//@SuppressWarnings({ "unchecked", "rawtypes" })
//	@Override
	/*public List<String> getSoftClosingJobNoList(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		//TRIM(hmt.projectNo) = :jobNo AND
		//String qryStr="SELECT DISTINCT hmt.id.docNo FROM Pctrxdmt hmt WHERE  hmt.id.deptId =:deptId ORDER BY hmt.id.docNo";
		//List<PaySlipInfo> infos=new ArrayList<PaySlipInfo>();
		List<String> jobNoList=new ArrayList<String>();
		Format format=new Format();
		String qryStr="SELECT DISTINCT NEW MAP(hmt.id.docNo, hmt.pymtAmt, dmt.chqDt) FROM Cbpmthmt hmt, Cbchqdmt dmt WHERE  hmt.id.deptId =:deptId AND TRIM(hmt.id.docPf) =:docPf AND hmt.status =4 AND hmt.id.docNo=dmt.id.pymtDocno AND hmt.id.deptId=dmt.id.deptId  ORDER BY hmt.id.docNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("docPf", "PS-ONLINE");
		//query.setParameter("status", 4L);
		List<Map> listMap=query.getResultList();
		for(int i=0; i<=listMap.size()-1; i++){
			//PaySlipInfo paySlipInfo=new PaySlipInfo();
			qryStr="SELECT TRIM(dmt.projectNo) FROM Pctrxdmt dmt, Pcesthmt hmt WHERE  TRIM(dmt.id.docNo)=:docNo AND dmt.id.deptId =:deptId AND TRIM(dmt.id.docPf) =:docPf  AND dmt.id.deptId =hmt.id.deptId AND dmt.projectNo =hmt.projectNo AND  hmt.status=1 ORDER BY dmt.id.docNo, dmt.projectNo";
			query = getEntityManager(webAppName).createQuery(qryStr);
			query.setParameter("docNo", listMap.get(i).values().toArray()[2].toString().trim());
			query.setParameter("deptId", deptId);
			query.setParameter("docPf", "PS-ONLINE");
			//query.setParameter("status", 1L);
			
			List<String> list2=query.getResultList();
			//System.out.println(list2);
			//paySlipInfo.setDocNo(listMap.get(i).values().toArray()[2].toString().trim());
			//paySlipInfo.setJobNos(list2);
			//paySlipInfo.setPaySlipAmount(new BigDecimal(listMap.get(i).values().toArray()[1].toString()));
			//paySlipInfo.setChequeDate(format.StrToDate(listMap.get(i).values().toArray()[0].toString()));
			//infos.add(paySlipInfo);
			jobNoList.addAll(list2);
		}
		
		return jobNoList;
	}*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<String> getSoftClosingJobNoList(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		//TRIM(hmt.projectNo) = :jobNo AND
		//String qryStr="SELECT DISTINCT hmt.id.docNo FROM Pctrxdmt hmt WHERE  hmt.id.deptId =:deptId ORDER BY hmt.id.docNo";
		//List<PaySlipInfo> infos=new ArrayList<PaySlipInfo>();
		List<String> jobNoList=new ArrayList<String>();
		Format format=new Format();
		String qryStr="SELECT DISTINCT NEW MAP(hmt.id.docNo, hmt.pymtAmt, dmt.chqDt) FROM Cbpmthmt hmt, Cbchqdmt dmt WHERE  hmt.id.deptId =:deptId AND TRIM(hmt.id.docPf) =:docPf AND hmt.status =4 AND hmt.id.docNo=dmt.id.pymtDocno AND hmt.id.deptId=dmt.id.deptId  ORDER BY hmt.id.docNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("docPf", "PS-ONLINE");
		//query.setParameter("status", 4L);
		List<Map> listMap=query.getResultList();
		for(int i=0; i<=listMap.size()-1; i++){
			//PaySlipInfo paySlipInfo=new PaySlipInfo();
			qryStr="SELECT TRIM(dmt.projectNo) FROM Pctrxdmt dmt, Pcesthmt hmt WHERE  TRIM(dmt.id.docNo)=:docNo AND dmt.id.deptId =:deptId AND TRIM(dmt.id.docPf) =:docPf  AND dmt.id.deptId =hmt.id.deptId AND dmt.projectNo =hmt.projectNo AND  hmt.status=1 ORDER BY dmt.id.docNo, dmt.projectNo";
			query = getEntityManager(webAppName).createQuery(qryStr);
			query.setParameter("docNo", listMap.get(i).values().toArray()[2].toString().trim());
			query.setParameter("deptId", deptId);
			query.setParameter("docPf", "PS-ONLINE");
			//query.setParameter("status", 1L);
			
			List<String> list2=query.getResultList();
			//System.out.println(list2);
			//paySlipInfo.setDocNo(listMap.get(i).values().toArray()[2].toString().trim());
			//paySlipInfo.setJobNos(list2);
			//paySlipInfo.setPaySlipAmount(new BigDecimal(listMap.get(i).values().toArray()[1].toString()));
			//paySlipInfo.setChequeDate(format.StrToDate(listMap.get(i).values().toArray()[0].toString()));
			//infos.add(paySlipInfo);
			jobNoList.addAll(list2);
		}
		
		return jobNoList;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<PaySlipInfo> getPaySlipNoList(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		//TRIM(hmt.projectNo) = :jobNo AND
		//String qryStr="SELECT DISTINCT hmt.id.docNo FROM Pctrxdmt hmt WHERE  hmt.id.deptId =:deptId ORDER BY hmt.id.docNo";
		List<PaySlipInfo> infos=new ArrayList<PaySlipInfo>();
		Format format=new Format();
		String qryStr="SELECT DISTINCT NEW MAP(hmt.id.docNo, hmt.pymtAmt, dmt.chqDt) FROM Cbpmthmt hmt, Cbchqdmt dmt WHERE  hmt.id.deptId =:deptId AND TRIM(hmt.id.docPf) =:docPf AND hmt.status =4 AND hmt.id.docNo=dmt.id.pymtDocno AND hmt.id.deptId=dmt.id.deptId  ORDER BY hmt.id.docNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("docPf", "PS-ONLINE");
		//query.setParameter("status", new BigDecimal(4));
		List<Map> listMap=query.getResultList();
		for(int i=0; i<=listMap.size()-1; i++){
			PaySlipInfo paySlipInfo=new PaySlipInfo();
			qryStr="SELECT TRIM(dmt.projectNo) FROM Pctrxdmt dmt, Pcesthmt hmt WHERE  TRIM(dmt.id.docNo)=:docNo AND dmt.id.deptId =:deptId AND TRIM(dmt.id.docPf) =:docPf  AND dmt.id.deptId =hmt.id.deptId AND dmt.projectNo =hmt.projectNo AND  hmt.status=1 ORDER BY dmt.id.docNo";
			query = getEntityManager(webAppName).createQuery(qryStr);
			query.setParameter("docNo", listMap.get(i).values().toArray()[2].toString().trim());
			query.setParameter("deptId", deptId);
			query.setParameter("docPf", "PS-ONLINE");
			//query.setParameter("status", new BigDecimal(1));
			
			List<String> list2=query.getResultList();
			//System.out.println(list2);
			paySlipInfo.setDocNo(listMap.get(i).values().toArray()[2].toString().trim());
			paySlipInfo.setJobNos(list2);
			paySlipInfo.setPaySlipAmount(new BigDecimal(listMap.get(i).values().toArray()[1].toString()));
			paySlipInfo.setChequeDate(format.StrToDate(listMap.get(i).values().toArray()[0].toString()));
			infos.add(paySlipInfo);
			
		}
		
		return infos;
	}
	
	@Override
	public void softJobCloser(List<String> list , String  deptId, String webAppName ) {
		try{
			for (int i=0; i<=list.size()-1;i++){
				pcesthmtDaoRemote.changeStatusPcesthmt(list.get(i), deptId, new Long(4), webAppName);
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public TempConnInfo getTempConnInfos(String jobNo, String deptId, String webAppName) {
		//getEntityManager(webAppName);
		//List<TempConnInfo> infos=new ArrayList<TempConnInfo>();//ORDER BY p.projectNo  
		String qryStr="SELECT new job.model.TempConnInfo" +
				"(TRIM(p.projectNo), TRIM(p.id.estimateNo), p.prjAssDt, " +
				"w.serviceStreetAddress, w.serviceSuburb, w.serviceCity, w.servicePostalCode, w.connectionType, w.phase, " +
				"n.idNo, n.firstName, n.lastName, " +//n.mobileNo, n.telephoneNo, 
				"a.submitDate, a.fromDate, a.durationInDays, " +
				"s.totalCost, s.securityDeposit, s.addlDeposit, s.damageCost, " +
				"d.id.pivNo, d.pivDate, d.pivAmount, c.id.contractorId)" +
				"   FROM Pcesthmt p, Application a ,WiringLandDetail w, " +
				"Speststd s, PivDetail d, Applicant n ,Spestcnd c " +
				"WHERE  TRIM(p.projectNo) =:jobNo AND p.id.deptId =:deptId AND TRIM(c.id.projectNo)=:jobNo AND TRIM(p.id.estimateNo)=a.applicationNo AND a.id.applicationId=w.id.applicationId AND a.idNo=n.idNo AND TRIM(p.id.estimateNo)=s.id.estimateNo AND TRIM(p.id.estimateNo)=d.referenceNo AND d.referenceType=:referenceType and d.pivSeqNo=:pivSeqNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("jobNo", jobNo);
		query.setParameter("deptId", deptId);
		query.setParameter("referenceType", "EST");
		query.setParameter("pivSeqNo", new BigDecimal(1));
		List<TempConnInfo> list=query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void alocateJob(List<Spestcnd> spsetCndList, Spestcnt spestcnt, String webAppName) {
		try{
			for (int i=0; i<=spsetCndList.size()-1;i++){
				spestcndDaoRemote.createSpestcnd(spsetCndList.get(i), webAppName);
				pcesthmtDaoRemote.changeStatusPcesthmt(spsetCndList.get(i).getId().getProjectNo().trim(), spsetCndList.get(i).getId().getDeptId().trim(), new Long(EstimateStatus.JOB_ONGOING), webAppName);
				
			}
			spestcntDaoRemote.updateSpestcnt(spestcnt, webAppName);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deAloocateJob(List<SpestcndPK> idList, Spestcnt spestcnt, String webAppName) {
		try{
			for (int i=0; i<=idList.size()-1;i++){
				
				Spestcnd spestcnd=spestcndDaoRemote.findById(idList.get(i), webAppName);
				spestcndDaoRemote.removeSpestcnd(spestcnd, webAppName);
				pcesthmtDaoRemote.changeStatusPcesthmt(idList.get(i).getProjectNo().trim(), idList.get(i).getDeptId().trim(), new Long(EstimateStatus.JOB_POSTED), webAppName);
			}
			spestcntDaoRemote.updateSpestcnt(spestcnt, webAppName);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void energizeJob(Spestsea spestsea, Spestcnd spestcnd, Spestcnt spestcnt, String webAppName) {
		try{
			if (spestsea!=null){
				spestseaDaoRemote.createSpestsea(spestsea, webAppName);
			}
			spestcndDaoRemote.updateSpestcnd(spestcnd, webAppName);
			spestcntDaoRemote.updateSpestcnt(spestcnt, webAppName);
			//to be remove later
			Pcesthmt pcesthmt=pcesthmtDaoRemote.findByJobNo(spestcnd.getId().getProjectNo(), webAppName);
			System.out.println("#################################### "+spestcnd.getId().getProjectNo()+" "+pcesthmt);
			if (pcesthmt!=null){
				spestlabDaoRemote.insertTransportOnDMT(pcesthmt.getId().getEstimateNo(), spestcnd.getId().getDeptId(), webAppName);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public List<Spestlab> getLaborDetails(String jobNo, String deptId, String webAppName) {
		Pcesthmt pcesthmt=pcesthmtDaoRemote.findByJobNo(jobNo, deptId, webAppName);
		return spestlabDaoRemote.getAll(pcesthmt.getId().getEstimateNo(), deptId,webAppName);
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void billContractor(Spestcnd spestcnd, Spestbip spestbip, String webAppName) {
		try{
			spestcndDaoRemote.updateSpestcnd(spestcnd, webAppName);
			spestbipDaoRemote.createSpestbip(spestbip, webAppName);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pcesthmt> getJobDetailListByDateRange(Date fromDate, Date toDate, String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Pcesthmt a , Spestcnd s WHERE s.id.projectNo = a.projectNo AND a.id.deptId =:deptId AND s.status = :status AND s.finishedDate BETWEEN :fromDate AND :toDate  ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("status", JobProcessStatus.FINISHED_ENAGIZED);
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		List<Pcesthmt> list = query.getResultList();
        return list;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void validateJob(Pcesthmt pcesthmt, Approval approval, String webAppName) {
		try{
			pcesthmtDaoRemote.updatePcesthmt(pcesthmt, webAppName);
			approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			
			
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void rejectJob(Pcesthmt pcesthtt, Approval approval, String webAppName) {
		try{
			pcesthmtDaoRemote.updatePcesthmt(pcesthtt, webAppName);
			approvalDaoRemote.createAutoIdApprovals(approval, webAppName);
			
			
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void removeLabour(List<SpestlabPK> removeList, Pcesthmt pcesthmt, Pcestdmt pcestdmt, String webAppName) {
		try{
			if (removeList!=null){
				
				for(int i=0; i<=removeList.size()-1; i++){
					Spestlab spestlab=spestlabDaoRemote.findById(removeList.get(i), webAppName);
					if (spestlab!=null){
						spestlabDaoRemote.removeSpestlab(spestlab, webAppName);
					}
					
				}
			}
			pcesthmtDaoRemote.updatePcesthmt(pcesthmt, webAppName);
			pcestdmtDaoRemote.updatePcestdmt(pcestdmt, webAppName);
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}



	@Override
	public Boolean isMaterialIssued(String jobNo, String deptId,
			String webAppName) {
		return intrhmtDaoRemote.isMaterialIssued(jobNo, deptId, webAppName);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void removeMaterials(List<PcestdmtPK> list, Pcesthmt inPcesthmt, Speststd speststd, String webAppName) {
		//List<String> list2=new ArrayList<String>();
		//list2.add("11");
		try{
			pcesthmtDaoRemote.updatePcesthmt(inPcesthmt, webAppName);
			for(int i=0; i<=list.size()-1; i++){
				PcestdmtPK pcestdttPK=list.get(i);
				Pcestdmt pcestdtt=pcestdmtDaoRemote.findBy3PK(pcestdttPK.getEstimateNo(), pcestdttPK.getDeptId(), pcestdttPK.getResCd(), webAppName);
				pcestdmtDaoRemote.removePcestdmt(pcestdtt, webAppName);
				if (speststd!=null)
				speststdDaoRemote.updateSpeststd(speststd, webAppName);
				
			}
			
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}


	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void removeLabour(List<SpestlabPK> removeList, Pcesthmt pcesthmt, List<Pcestdmt> pcestdmtList, Speststd speststd, String webAppName) {
		try{
			if (removeList!=null){
				
				for(int i=0; i<=removeList.size()-1; i++){
					Spestlab spestlab=spestlabDaoRemote.findById(removeList.get(i), webAppName);
					if (spestlab!=null){
						spestlabDaoRemote.removeSpestlab(spestlab, webAppName);
					}
					
				}
			}
			pcesthmtDaoRemote.updatePcesthmt(pcesthmt, webAppName);
			//pcestdttDaoRemote.updatePcestdtt(pcestdtt, webAppName);
			if (pcestdmtList!=null){
				for(int k=0; k<=pcestdmtList.size()-1; k++){
					//System.out.println("444444444444444444"+listLabour.get(j));
					pcestdmtDaoRemote.updatePcestdmt(pcestdmtList.get(k), webAppName);
				}}
			if (speststd!=null){
				speststdDaoRemote.updateSpeststd(speststd, webAppName);
			}
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateReviseDetails(Pcesthmt pcesthmt, List<Pcestdmt> addlist, List<Pcestdmt> updList,List<Pcestdmt> deleList, String webAppName) {
		try{
			long newRevNo = pcesthmt.getId().getRevNo()+1;
			pcesthmtDaoRemote.updatePcesthmt(pcesthmt, webAppName);
			//pcesthmtDaoRemote.updateRevNo(pcesthmt, newRevNo, webAppName);
			if (addlist!=null){
				for(int i=0; i<=addlist.size()-1; i++){
					pcestdmtDaoRemote.createPcestdmt(addlist.get(i), webAppName);
				}
			}
			if (updList!=null){
				for(int j=0; j<=updList.size()-1; j++){
					pcestdmtDaoRemote.updatePcestdmt(updList.get(j), webAppName);
				}
			}
	
			if (deleList!=null){	
				for(int j=0; j<=deleList.size()-1; j++){
					pcestdmtDaoRemote.removePcestdmt(deleList.get(j), webAppName);
				}
			}
			/*List<Pcestdmt> updateList= pcestdmtDaoRemote.findByEstimationNo(pcesthmt.getId().getEstimateNo(), pcesthmt.getId().getDeptId(), webAppName);
			for(Pcestdmt dmt : updateList){
				
					pcestdmtDaoRemote.updateRevNo(dmt, newRevNo, webAppName);
				
			}*/
			//updateAdditionalCostForRevisejob(pcesthmt,pcesthmt.getId().getDeptId(),pcesthmt.getStdCost(),webAppName);
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateReviseDetailsWithRevNo(Pcesthmt pcesthmt, List<Pcestdmt> addlist, List<Pcestdmt> updList,List<Pcestdmt> deleList, String webAppName) {
		try{
			long newRevNo = pcesthmt.getId().getRevNo()+1;
			
			pcesthmtDaoRemote.updatePcesthmt(pcesthmt, webAppName);
			pcesthmtDaoRemote.updateRevNo(pcesthmt, newRevNo, webAppName);
			if (addlist!=null){
				for(int i=0; i<=addlist.size()-1; i++){
					pcestdmtDaoRemote.createPcestdmt(addlist.get(i), webAppName);
				}
			}
			if (updList!=null){
				for(int j=0; j<=updList.size()-1; j++){
					pcestdmtDaoRemote.updatePcestdmt(updList.get(j), webAppName);
				}
			}
	
			if (deleList!=null){	
				for(int j=0; j<=deleList.size()-1; j++){
					pcestdmtDaoRemote.removePcestdmt(deleList.get(j), webAppName);
				}
			}
			List<Pcestdmt> updateList= pcestdmtDaoRemote.findByEstimationNo(pcesthmt.getId().getEstimateNo(), pcesthmt.getId().getDeptId(), webAppName);
			for(Pcestdmt dmt : updateList){
				
					pcestdmtDaoRemote.updateRevNo(dmt, newRevNo, webAppName);
				
			}
			//updateAdditionalCostForRevisejob(pcesthmt,pcesthmt.getId().getDeptId(),pcesthmt.getStdCost(),webAppName);
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
		
	}
	public void updateAdditionalCostForRevisejob(Pcesthmt pcesthmt ,String deptId,BigDecimal toCost,String webAppName){
		
		BigDecimal totalCost = new BigDecimal(0.0);
	    if(pcesthmt != null){
	    	BigDecimal conrate = new BigDecimal(0.0);
			try {
				conrate = inwrhmapDaoRemote.getConRateByDeptId(deptId,webAppName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	if(conrate != null){
	    		try {
					totalCost = getPcestdmtCONTENGENCIESCost(pcesthmt.getId().getEstimateNo().trim(),deptId,toCost.doubleValue(),conrate.doubleValue(),webAppName);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		pcesthmt.setStdCost(totalCost);
	        	pcesthmtDaoRemote.updatePcesthmt(pcesthmt, webAppName);
	    	}
	    	
	        
	    	try {
				checkPcestdmtOtherMaterialSum(pcesthmt.getId().getEstimateNo(),deptId,toCost,webAppName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	
	private BigDecimal getPcestdmtCONTENGENCIESCost(String estimateNo , String deptId,double toCost,double conrat,String webAppName)throws Exception {
		 
/*	    // double totalcost = 0.0;
	    // double preConCost = 0.0;
	     double finalCost =  0.0;
	     
	     BigDecimal  preConCost = pcestdmtDaoRemote.getSUMByResType(estimateNo,Constants.RES_TYPE_CONTINGENCIES_COST,webAppName);
	     BigDecimal  totalcost =  pcestdmtDaoRemote.getSUMOfMATandMAT_OTHER(estimateNo, webAppName);
	    
	     double totalcostWithConr = 0.0;
	      if(totalcost != null)   {
	    	  totalcostWithConr = totalcost.doubleValue() * conrat;
	      }
	    
	     
	     double estimateQty = totalcostWithConr ;
	     double estimateCost = totalcostWithConr ;
	     if(estimateCost < Constants.MINIMUM_ESTIMATE_COST.doubleValue()){
	    	 estimateCost = Constants.MINIMUM_ESTIMATE_COST.doubleValue();
	     }
	       
	     if(preConCost != null && preConCost == totalcost ){
	         finalCost =  toCost;
	      }else{
	         if(preConCost != null){
	        	 toCost = toCost - preConCost.doubleValue();
	        	 if(totalcost != null){
	        		 toCost = totalcost.doubleValue() + toCost;
	        	 }
	             finalCost =  toCost; 
	         }
	        
	         
	      }
	    Pcestdmt dmt = pcestdmtDaoRemote.findBy3PK(estimateNo.trim(), deptId, Constants.RES_CODE_CONTINGENCIES_COST, webAppName);
	    //Pcestdtt dtt = populatePcestdttInsertCONTENGENCIESDetails(estimateNo,deptId,new BigDecimal(estimateCost));
	    if(dmt != null){
	    	dmt.setEstimateCost(new BigDecimal(estimateCost));
	    	pcestdmtDaoRemote.updatePcestdmt(dmt, webAppName);
	    }
	    return new BigDecimal(finalCost);
		
		//return conrate;
*/	    return new BigDecimal("0");
	}
	private BigDecimal checkPcestdmtOtherMaterialSum(String estimateNo , String deptId,BigDecimal estimateCost,String webAppName)throws Exception {
		
		/*BigDecimal  othermatSum = pcestdmtDaoRemote.getSUMByResType(estimateNo,webAppName,Constants.RES_TYPE_MAT_COST_OTHER);
		
	    if (othermatSum != null && othermatSum.doubleValue() !=0.0 ) {
	    	Long count=pcestdmtDaoRemote.getRawCountOtherMat(estimateNo,deptId,Constants.RES_CODE_OTHER_MATERIALS,webAppName);
	        if(count == 0){
	            insertPcestdmtOtherMaterialSum(estimateNo , deptId,estimateCost, webAppName);
	        }else{
	        	
	        	pcestdmtDaoRemote.updatePcestdmtOtherMaterialSum(estimateNo, deptId, Constants.RES_CODE_OTHER_MATERIALS, estimateCost, webAppName);
	        }
	
	    
	        
	    }
	    */
		
		return new BigDecimal("0");
		
	}
	private BigDecimal insertPcestdmtOtherMaterialSum(String estimateNo , String deptId,BigDecimal estimateCost,String webAppName)throws Exception {
		
		
	    
		Pcestdmt pcestdmt = new Pcestdmt();
		
		PcestdmtPK pcestdmtPk = new PcestdmtPK();
		pcestdmtPk.setDeptId(deptId);
		pcestdmtPk.setEstimateNo(estimateNo);
		pcestdmtPk.setResCd(Constants.RES_CODE_OTHER_MATERIALS);
		pcestdmtPk.setRevNo(Constants.REV_NO);
		
		pcestdmt.setId(pcestdmtPk);
		
		pcestdmt.setResCat(new BigDecimal(Constants.RES_CAT_CONTIGENCIES));
		pcestdmt.setEstimateCost(estimateCost);
		pcestdmt.setEstimateQty(estimateCost);
		pcestdmt.setResType(Constants.RES_TYPE_OTHER_MATERIALS);
		pcestdmt.setUom(Constants.OTHER_MATERIALS_UOM);
		pcestdmt.setUnitPrice(Constants.UNIT_PRICE_OTHER_MATERIALS);
		pcestdmt.setNormDefault(Constants.NORM_DEFAULT);
		pcestdmt.setGenRes(Constants.GEN_RES);
		pcestdmt.setTolerance(Constants.TOLLERANCE_OTHER_MATERIALS);
		pcestdmtDaoRemote.createPcestdmt(pcestdmt, webAppName);
		
		return estimateCost;
		
	}

	public void updateRevisedEstimateCost(Pcesthmt pcesthmt,String estimateType,String deptId,String webAppName) throws PersistenceException{
		try {
			
			
			addAdditionalCostEntries(pcesthmt, estimateType, deptId, webAppName);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new PersistenceException("Error in Cost Calculation");
		}
		/*String qryStr2 = "select sum(a.estimateCost) from Pcestdmt a where TRIM(a.id.estimateNo)=:estimateNo AND TRIM(a.id.deptId)=:deptId";
		Query query2 = getEntityManager(webAppName).createQuery(qryStr2);
		query2.setParameter("estimateNo", pcesthmt.getId().getEstimateNo().trim());
		query2.setParameter("deptId", deptId);
		//query2.setParameter("revNo", "2");
		BigDecimal stdCost = (BigDecimal) query2.getSingleResult();
		System.out.println(stdCost);
		boolean status = false;
		Pcesthmt hmt=pcesthmtDaoRemote.findByJobNo(pcesthmt.getProjectNo().trim(), deptId,webAppName);
		if (hmt!=null){
			hmt.setStdCost(stdCost);
			//System.out.println(pcesthmt);
			System.out.println();
			pcesthmtDaoRemote.updatePcesthmt(hmt, webAppName);
		}*/
		
	}
public void addAdditionalCostEntries(Pcesthmt pcesthmt,String estimateType ,String deptId,String webAppName) throws Exception{
		
		
		
		List<CostCalculationMaster> masterCostList =  costCalculationMasterDaoRemote.findByEstimateType(estimateType, deptId, webAppName);
		if(masterCostList != null && masterCostList.size() > 0){
			for(CostCalculationMaster master : masterCostList){
				if(master.getId().getEntryResCode().equalsIgnoreCase("TOTAL")){
					updateRevisedTotalCost(pcesthmt, master.getCombinationEntry(), deptId, webAppName);
				}else{
					double estimateCost = populateCostForNewEntry(pcesthmt.getId().getEstimateNo(), master, deptId, webAppName);
					//if(estimateCost != 0.0){
						//Pcestdmt dmtMaintence=pcestdmtDaoRemote.updatePcestdmtPercentageCost(pcesthmt.getId().getEstimateNo(),deptId,Constants.RES_CD_INTEREST, stdCost,webAppName);
						Pcestdmt dmtEntry = pcestdmtDaoRemote.getdmtByResCD(pcesthmt.getId().getEstimateNo(), master.getId().getEntryResCode(), webAppName);
						
						if (dmtEntry==null){
							populatePcestdttNewEntry(pcesthmt.getId().getEstimateNo(), master.getEntryType(), master.getId().getEntryResCode(), deptId, new BigDecimal(estimateCost), webAppName);
						}else{
							pcestdmtDaoRemote.updatePcestdmtPercentageCost(pcesthmt.getId().getEstimateNo(), deptId, master.getId().getEntryResCode(), new BigDecimal(estimateCost), new BigDecimal(estimateCost), webAppName);
						}
					//}
					
				}
			}
		}
		
		Pcestdmt dmtMATEntry = pcestdmtDaoRemote.getdmtByResCD(pcesthmt.getId().getEstimateNo(), Constants.MAT_RESOUR_CODE, webAppName);
		if(dmtMATEntry == null){
			insertAdditionalMATCostForDetailEstimate(pcesthmt.getId().getEstimateNo(),deptId,webAppName);
		}
		
    	

	}
public void insertAdditionalMATCostForDetailEstimate(String estimateNo , String deptId,String webAppName) throws Exception {

 	String genRes="F";
 	String normDefault="F";
 	Pcestdmt pcestdtt = new Pcestdmt();
	
	PcestdmtPK pcestdmtPk = new PcestdmtPK();
	pcestdmtPk.setDeptId(deptId);
	pcestdmtPk.setEstimateNo(estimateNo);
	pcestdmtPk.setResCd(Constants.MAT_RESOUR_CODE);
	pcestdmtPk.setRevNo(Constants.REV_NO);
	
	pcestdtt.setId(pcestdmtPk);
	
	pcestdtt.setResCat(new BigDecimal(2));
	pcestdtt.setEstimateCost(new BigDecimal(0.0));
	pcestdtt.setEstimateQty(new BigDecimal(0.0));
	pcestdtt.setResType(Constants.MAT_RESOUR_TYPE);
	pcestdtt.setUom(Constants.MAT_UOM);
	pcestdtt.setUnitPrice(new BigDecimal(0.0));
	pcestdtt.setNormDefault(normDefault);
	pcestdtt.setGenRes(genRes);
	
	pcestdmtDaoRemote.createPcestdmt(pcestdtt, webAppName);
}
	public void updateRevisedTotalCost(Pcesthmt pcesthmt1,String resType,String deptId,String webAppName) throws PersistenceException{
	Pcesthmt pcesthmt=pcesthmtDaoRemote.findByJobNo(pcesthmt1.getProjectNo(), deptId, webAppName);
	if (pcesthmt != null){
		/*try {
			//updateAdditionalCost(pcesthtt, deptId, pcesthtt.getStdCost(), webAppName);
		} catch (Exception e) {
			LOGGER.error("Error in Additional Cost calculation Estimate No:"+estimateNo);
		}*/
		StringBuffer qryStr2 = new StringBuffer();
		qryStr2.append("select sum(a.estimateCost) from Pcestdmt a where TRIM(a.id.estimateNo)=:estimateNo AND TRIM(a.id.deptId)=:deptId AND trim(resType) <> 'MAT' ");
		
		if(resType != null){
			qryStr2.append("AND TRIM(resType) <> 'OTHER-MATERIAL' ");
		}
		
		Query query2 = getEntityManager(webAppName).createQuery(qryStr2.toString());
		query2.setParameter("estimateNo", pcesthmt.getId().getEstimateNo().trim());
		query2.setParameter("deptId", deptId);
		//query2.setParameter("resType", resType);
		/*if(resType != null){
			
			query2.setParameter("resType", resType.trim());
		}*/
		BigDecimal stdCost = (BigDecimal) query2.getSingleResult();
		System.out.println(stdCost);
		boolean status = false;
		
		
		pcesthmt.setStdCost(stdCost);
		//System.out.println(pcesthmt);
		System.out.println();
		pcesthmtDaoRemote.updatePcesthmt(pcesthmt, webAppName);
	}
	
}
	private double populateCostForNewEntry(String estimateNo,CostCalculationMaster masterDetails,String deptId ,String webAppName){
		String paramsList = null;
		if(masterDetails != null && masterDetails.getCombinationEntry() != null){
			
			StringBuffer qryStr = new StringBuffer();	
			
			double estimateCost =0;
			//String qryStr1 = "select sum(a.estimateCost) from Pcestdtt a where TRIM(a.id.estimateNo)=:estimateNo AND TRIM(a.id.deptId)=:deptId";
			if(masterDetails.getFundId() == null){
				String qryStr1 = "select sum(a.estimateCost) from Pcestdmt a where TRIM(a.id.estimateNo)=:estimateNo AND TRIM(a.id.deptId)=:deptId ";
				//StringBuffer qryStr = new StringBuffer();
				qryStr.append(qryStr1);
				//if(masterDetails.getCombinationEntry() != null){
					qryStr.append( " and a.resType in (");
					qryStr.append( masterDetails.getCombinationEntry());
					qryStr.append(")");
			 }else{
			
			String qryStr1 = "select sum(a.estimateCost) from Pcestdmt a where TRIM(a.id.estimateNo)=:estimateNo AND TRIM(a.id.deptId)=:deptId ";
			
			qryStr.append(qryStr1);
			//if(masterDetails.getCombinationEntry() != null){
				qryStr.append( " and a.resType in (");
				qryStr.append( masterDetails.getCombinationEntry());
				qryStr.append(")");
			}
			BigDecimal stdCost1 =null;
			if(qryStr.length() != 0){
				Query query1 = getEntityManager(webAppName).createQuery(qryStr.toString());
				query1.setParameter("estimateNo", estimateNo.trim());
				
				query1.setParameter("deptId", deptId.trim());
				//query1.setParameter("priority", masterDetails.getPriority());
				
				stdCost1 = (BigDecimal) query1.getSingleResult();
			}
			System.out.println(stdCost1);
			//stdCost1 = new BigDecimal(122321.00);
			boolean status = false;
			
			if(stdCost1 != null){
			//Pcestdmt dmtMaintence=pcestdmtDaoRemote.updatePcestdmtPercentageCost(pcesthmt.getId().getEstimateNo(),deptId,Constants.RES_CD_INTEREST, stdCost,webAppName);
				//Pcestdmt dmtEntry = pcestdttDaoRemote.getdttByResCD(estimateNo, masterDetails.getId().getEntryResCode(), webAppName);
				
				//if (dmtEntry!=null){
					
					estimateCost = stdCost1.doubleValue()*masterDetails.getPercentage(); 
					if(masterDetails.getId().getEntryResCode().equals(Constants.RES_CODE_CONTINGENCIES_COST)){
						if(estimateCost < Constants.MINIMUM_ESTIMATE_COST.doubleValue()){
							estimateCost = Constants.MINIMUM_ESTIMATE_COST.doubleValue();
						}
					}
					//pcestdmtDaoRemote.updatePcestdmtPercentageCost(estimateNo, deptId, masterDetails.getEntryCode(), new BigDecimal(estimateCost), webAppName);
			
				//}
			}
			return estimateCost;
		}
		return 0;
	}
private int populatePcestdttNewEntry(String estimateNo,String resType,String resCode,String deptId ,BigDecimal estimateCost,String webAppName){
		
		Pcestdmt pcestdmt = new Pcestdmt();
		
		PcestdmtPK pcestdmtPk = new PcestdmtPK();
		pcestdmtPk.setDeptId(deptId);
		pcestdmtPk.setEstimateNo(estimateNo);
		pcestdmtPk.setResCd(resCode);
		pcestdmtPk.setRevNo(Constants.REV_NO);
		
		pcestdmt.setId(pcestdmtPk);
		
		pcestdmt.setResCat(new BigDecimal(Constants.RES_CAT_DEFAULT));
		pcestdmt.setEstimateCost(estimateCost);
		pcestdmt.setEstimateQty(estimateCost);
		pcestdmt.setResType(resType);
		pcestdmt.setUom(Constants.MAT_UOM);
		pcestdmt.setUnitPrice(Constants.UNIT_PRICE_DEFAULT);
		pcestdmt.setNormDefault(Constants.NORM_DEFAULT);
		pcestdmt.setGenRes(Constants.GEN_RES);
		pcestdmt.setTolerance(Constants.TOLLERANCE__DEFAULT);
		
		pcestdmtDaoRemote.createPcestdmt(pcestdmt, webAppName);
		//int status = pcestdttDaoRemote.updatePcestdttPercentageCost(estimateNo, deptId, resCode, estimateCost, webAppName);
		
		return 0;
		
	}
	public void updateRevisedEstimateCostGI_TCIEstimates(Pcesthmt pcesthmt,String deptId,String webAppName) throws PersistenceException{
		
		String qryStr1 = "select sum(a.estimateCost) from Pcestdmt a where TRIM(a.id.estimateNo)=:estimateNo AND TRIM(a.id.deptId)=:deptId and resType in ('MAT-COST','MAT-COST-OTHER','MAT-SUNDRIES')";
		Query query1 = getEntityManager(webAppName).createQuery(qryStr1);
		query1.setParameter("estimateNo", pcesthmt.getId().getEstimateNo().trim());
		query1.setParameter("deptId", deptId);
		
		BigDecimal stdCost1 = (BigDecimal) query1.getSingleResult();
		System.out.println(stdCost1);
		boolean status = false;
		
		if(stdCost1 != null){
		//Pcestdmt dmtMaintence=pcestdmtDaoRemote.updatePcestdmtPercentageCost(pcesthmt.getId().getEstimateNo(),deptId,Constants.RES_CD_INTEREST, stdCost,webAppName);
			Pcestdmt dmtInterest = pcestdmtDaoRemote.getdmtByResCD(pcesthmt.getId().getEstimateNo(), Constants.RES_CD_INTEREST, webAppName);
			
			if (dmtInterest!=null){
				double estimateCost =0;
				estimateCost = stdCost1.doubleValue()*Constants.RES_CD_INTEREST_PERCENTAGE; 
				dmtInterest.setEstimateCost(new BigDecimal(estimateCost));
				pcestdmtDaoRemote.updatePcestdmt(dmtInterest,webAppName);
		
			}
			
			Pcestdmt dmtMaintenance = pcestdmtDaoRemote.getdmtByResCD(pcesthmt.getId().getEstimateNo(), Constants.RES_CD_MAINTENANCE, webAppName);
			
			if (dmtMaintenance!=null){
				double estimateCost =0;
				estimateCost = stdCost1.doubleValue()*Constants.RES_CD_MAINTENANCE_PERCENTAGE; 
				dmtMaintenance.setEstimateCost(new BigDecimal(estimateCost));
				pcestdmtDaoRemote.updatePcestdmt(dmtMaintenance,webAppName);
				
			}
			Pcestdmt dmtDepreciation = pcestdmtDaoRemote.getdmtByResCD(pcesthmt.getId().getEstimateNo(), Constants.RES_CD_DEPRECIATION, webAppName);
			
			if (dmtDepreciation!=null){
				double estimateCost =0;
				estimateCost = stdCost1.doubleValue()*Constants.RES_CD_DEPRECIATION_PERCENTAGE; 
				dmtDepreciation.setEstimateCost(new BigDecimal(estimateCost));
				pcestdmtDaoRemote.updatePcestdmt(dmtDepreciation,webAppName);
				
			}
			String qryStr2 = "select sum(a.estimateCost) from Pcestdmt a where TRIM(a.id.estimateNo)=:estimateNo AND TRIM(a.id.deptId)=:deptId and resType in ('LABOUR-COST')";
			Query query2 = getEntityManager(webAppName).createQuery(qryStr2);
			query2.setParameter("estimateNo", pcesthmt.getId().getEstimateNo().trim());
			query2.setParameter("deptId", deptId);
			//query2.setParameter("revNo", "2");
			BigDecimal stdCost2 = (BigDecimal) query2.getSingleResult();
			
			Pcestdmt dmtLabour = pcestdmtDaoRemote.getdmtByResCD(pcesthmt.getId().getEstimateNo(), Constants.RES_CD_50_LABOUR, webAppName);
			
			if (dmtLabour!=null){
				double estimateCost =0;
				estimateCost = stdCost2.doubleValue()*Constants.RES_CD_50_LABOUR_PERCENTAGE; 
				dmtLabour.setEstimateCost(new BigDecimal(estimateCost));
				pcestdmtDaoRemote.updatePcestdmt(dmtLabour,webAppName);
				
			}
			Pcestdmt dmtDemageBreakers = pcestdmtDaoRemote.getdmtByResCD(pcesthmt.getId().getEstimateNo(), Constants.RES_CD_DAMAGES_BREAKAGES, webAppName);
			
			if (dmtDemageBreakers!=null){
				double estimateCost =0;
				estimateCost = stdCost1.doubleValue()*Constants.RES_CD_DAMAGES_BREAKAGES_PERCENTAGE; 
				dmtDemageBreakers.setEstimateCost(new BigDecimal(estimateCost));
				pcestdmtDaoRemote.updatePcestdmt(dmtDemageBreakers,webAppName);
				
			}
			
			String qryStr3 = "select sum(a.estimateCost) from Pcestdmt a where TRIM(a.id.estimateNo)=:estimateNo AND TRIM(a.id.deptId)=:deptId and resType in ('DEPRECIATION 5%','INTEREST 7%','MAINTENANCE 3%','DAM-BREAKAGES 10%')";
			Query query3 = getEntityManager(webAppName).createQuery(qryStr3);
			query3.setParameter("estimateNo", pcesthmt.getId().getEstimateNo().trim());
			query3.setParameter("deptId", deptId);
			
			BigDecimal stdCost3 = (BigDecimal) query3.getSingleResult();
			
			Pcestdmt dmtAD = pcestdmtDaoRemote.getdmtByResCD(pcesthmt.getId().getEstimateNo(), Constants.RES_CD_AD, webAppName);
			
			if (dmtAD!=null){
				double estimateCost =0;
				estimateCost = stdCost3.doubleValue()*Constants.RES_CD_AD_PERCENTAGE; 
				dmtAD.setEstimateCost(new BigDecimal(estimateCost));
				pcestdmtDaoRemote.updatePcestdmt(dmtAD,webAppName);
				
			}
			
			String qryStr4 = "select sum(a.estimateCost) from Pcestdmt a where TRIM(a.id.estimateNo)=:estimateNo AND TRIM(a.id.deptId)=:deptId and resType in ('DEPRECIATION 5%','INTEREST 7%','MAINTENANCE 3%','DAM-BREAKAGES 10%','LABOUR-COST','50% LABOUR','5% A-D')";
			Query query4 = getEntityManager(webAppName).createQuery(qryStr4);
			query4.setParameter("estimateNo", pcesthmt.getId().getEstimateNo().trim());
			query4.setParameter("deptId", deptId);
			//query2.setParameter("revNo", "2");
			BigDecimal stdCost4 = (BigDecimal) query4.getSingleResult();
			
			Pcestdmt dmtBoardCharges = pcestdmtDaoRemote.getdmtByResCD(pcesthmt.getId().getEstimateNo(), Constants.RES_CD_AG, webAppName);
			
			if (dmtBoardCharges!=null){
				double estimateCost =0;
				estimateCost = stdCost4.doubleValue()*Constants.RES_CD_AG_PERCENTAGE; 
				dmtBoardCharges.setEstimateCost(new BigDecimal(estimateCost));
				pcestdmtDaoRemote.updatePcestdmt(dmtBoardCharges,webAppName);
				
			}
			String qryStr5 = "select sum(a.estimateCost) from Pcestdmt a where TRIM(a.id.estimateNo)=:estimateNo AND TRIM(a.id.deptId)=:deptId and resType in ('DEPRECIATION 5%','INTEREST 7%','MAINTENANCE 3%','DAM-BREAKAGES 10%','LABOUR-COST','50% LABOUR','5% A-D','20% A-G')";
			Query query5 = getEntityManager(webAppName).createQuery(qryStr5);
			query5.setParameter("estimateNo", pcesthmt.getId().getEstimateNo().trim());
			query5.setParameter("deptId", deptId);
			//query2.setParameter("revNo", "2");
			BigDecimal stdCost5 = (BigDecimal) query5.getSingleResult();
			
			Pcestdmt dmtVAT = pcestdmtDaoRemote.getdmtByResCD(pcesthmt.getId().getEstimateNo(), Constants.RES_CD_VAT, webAppName);
			
			if (dmtVAT!=null){
				double estimateCost =0;
				estimateCost = stdCost5.doubleValue()*Constants.RES_CD_VAT_PERCENTAGE; 
				dmtVAT.setEstimateCost(new BigDecimal(estimateCost));
				pcestdmtDaoRemote.updatePcestdmt(dmtVAT,webAppName);
				
			}
		}
		Pcesthmt hmt=pcesthmtDaoRemote.findByJobNo(pcesthmt.getProjectNo().trim(), deptId,webAppName);
		if(hmt != null){
			String qryStr6 = "select sum(a.estimateCost) from Pcestdmt a where TRIM(a.id.estimateNo)=:estimateNo AND TRIM(a.id.deptId)=:deptId";
			Query query6 = getEntityManager(webAppName).createQuery(qryStr6);
			query6.setParameter("estimateNo", pcesthmt.getId().getEstimateNo().trim());
			query6.setParameter("deptId", deptId);
			
			BigDecimal stdCost6 = (BigDecimal) query6.getSingleResult();
			System.out.println(stdCost6);
			hmt.setStdCost(stdCost6);
		
			pcesthmtDaoRemote.updatePcesthmt(hmt, webAppName);
		
		}
		
	}
public void updateRevisedEstimateCostGI_TPJ_TPV_TPLEstimates(Pcesthmt pcesthmt,String deptId,String webAppName) throws PersistenceException{
															
		String qryStr1 = "select sum(a.estimateCost) from Pcestdmt a where TRIM(a.id.estimateNo)=:estimateNo AND TRIM(a.id.deptId)=:deptId and resType in ('MAT-COST','TRANSPORT-COST','MAT-SUNDRIES','LABOUR-COST')";
		Query query1 = getEntityManager(webAppName).createQuery(qryStr1);
		query1.setParameter("estimateNo", pcesthmt.getId().getEstimateNo().trim());
		query1.setParameter("deptId", deptId);
		
		BigDecimal stdCost1 = (BigDecimal) query1.getSingleResult();
		System.out.println(stdCost1);
		boolean status = false;
		
		if(stdCost1 != null){
		//Pcestdmt dmtMaintence=pcestdmtDaoRemote.updatePcestdmtPercentageCost(pcesthmt.getId().getEstimateNo(),deptId,Constants.RES_CD_INTEREST, stdCost,webAppName);
			Pcestdmt dmtBoardCharges = pcestdmtDaoRemote.getdmtByResCD(pcesthmt.getId().getEstimateNo(), Constants.RES_CD_30_AG, webAppName);
			
			if (dmtBoardCharges!=null){
				double estimateCost =0;
				estimateCost = stdCost1.doubleValue()*Constants.RES_CD__30_AG_PERCENTAGE; 
				dmtBoardCharges.setEstimateCost(new BigDecimal(estimateCost));
				pcestdmtDaoRemote.updatePcestdmt(dmtBoardCharges,webAppName);
		
			}
																																							
			String qryStr5 = "select sum(a.estimateCost) from Pcestdmt a where TRIM(a.id.estimateNo)=:estimateNo AND TRIM(a.id.deptId)=:deptId and resType in ('MAT-COST','TRANSPORT-COST','MAT-SUNDRIES','LABOUR-COST','CEB 30%')";
			Query query5 = getEntityManager(webAppName).createQuery(qryStr5);
			query5.setParameter("estimateNo", pcesthmt.getId().getEstimateNo().trim());
			query5.setParameter("deptId", deptId);
			//query2.setParameter("revNo", "2");
			BigDecimal stdCost5 = (BigDecimal) query5.getSingleResult();
			
			Pcestdmt dmtVAT = pcestdmtDaoRemote.getdmtByResCD(pcesthmt.getId().getEstimateNo(), Constants.RES_CD_VAT, webAppName);
			
			if (dmtVAT!=null){
				double estimateCost =0;
				estimateCost = stdCost5.doubleValue()*Constants.RES_CD_VAT_PERCENTAGE; 
				dmtVAT.setEstimateCost(new BigDecimal(estimateCost));
				pcestdmtDaoRemote.updatePcestdmt(dmtVAT,webAppName);
				
			}
		}
		Pcesthmt hmt=pcesthmtDaoRemote.findByJobNo(pcesthmt.getProjectNo().trim(), deptId,webAppName);
		if(hmt != null){
			String qryStr6 = "select sum(a.estimateCost) from Pcestdmt a where TRIM(a.id.estimateNo)=:estimateNo AND TRIM(a.id.deptId)=:deptId";
			Query query6 = getEntityManager(webAppName).createQuery(qryStr6);
			query6.setParameter("estimateNo", pcesthmt.getId().getEstimateNo().trim());
			query6.setParameter("deptId", deptId);
			
			BigDecimal stdCost6 = (BigDecimal) query6.getSingleResult();
			System.out.println(stdCost6);
			hmt.setStdCost(stdCost6);
		
			pcesthmtDaoRemote.updatePcesthmt(hmt, webAppName);
		
		}
		
	}
	public void updateRevisedEstimateCostGI_TPCEstimates(Pcesthmt pcesthmt,String deptId,String webAppName) throws PersistenceException{
	
	String qryStr1 = "select sum(a.estimateCost) from Pcestdmt a where TRIM(a.id.estimateNo)=:estimateNo AND TRIM(a.id.deptId)=:deptId and resType in ('MAT-COST','TRANSPORT-COST','MAT-SUNDRIES','LABOUR-COST')";
	Query query1 = getEntityManager(webAppName).createQuery(qryStr1);
	query1.setParameter("estimateNo", pcesthmt.getId().getEstimateNo().trim());
	query1.setParameter("deptId", deptId);
	
	BigDecimal stdCost1 = (BigDecimal) query1.getSingleResult();
	System.out.println(stdCost1);
	boolean status = false;
	
	if(stdCost1 != null){
	//Pcestdmt dmtMaintence=pcestdmtDaoRemote.updatePcestdmtPercentageCost(pcesthmt.getId().getEstimateNo(),deptId,Constants.RES_CD_INTEREST, stdCost,webAppName);
		Pcestdmt dmtBoardCharges = pcestdmtDaoRemote.getdmtByResCD(pcesthmt.getId().getEstimateNo(), Constants.RES_CD_AG, webAppName);
		
		if (dmtBoardCharges!=null){
			double estimateCost =0;
			estimateCost = stdCost1.doubleValue()*Constants.RES_CD_AG_PERCENTAGE; 
			dmtBoardCharges.setEstimateCost(new BigDecimal(estimateCost));
			pcestdmtDaoRemote.updatePcestdmt(dmtBoardCharges,webAppName);
	
		}
																																						
		String qryStr5 = "select sum(a.estimateCost) from Pcestdmt a where TRIM(a.id.estimateNo)=:estimateNo AND TRIM(a.id.deptId)=:deptId and resType in ('MAT-COST','TRANSPORT-COST','MAT-SUNDRIES','LABOUR-COST','CEB 30%')";
		Query query5 = getEntityManager(webAppName).createQuery(qryStr5);
		query5.setParameter("estimateNo", pcesthmt.getId().getEstimateNo().trim());
		query5.setParameter("deptId", deptId);
		//query2.setParameter("revNo", "2");
		BigDecimal stdCost5 = (BigDecimal) query5.getSingleResult();
		
		Pcestdmt dmtVAT = pcestdmtDaoRemote.getdmtByResCD(pcesthmt.getId().getEstimateNo(), Constants.RES_CD_VAT, webAppName);
		
		if (dmtVAT!=null){
			double estimateCost =0;
			estimateCost = stdCost5.doubleValue()*Constants.RES_CD_VAT_PERCENTAGE; 
			dmtVAT.setEstimateCost(new BigDecimal(estimateCost));
			pcestdmtDaoRemote.updatePcestdmt(dmtVAT,webAppName);
			
		}
	}
	Pcesthmt hmt=pcesthmtDaoRemote.findByJobNo(pcesthmt.getProjectNo().trim(), deptId,webAppName);
	if(hmt != null){
		String qryStr6 = "select sum(a.estimateCost) from Pcestdmt a where TRIM(a.id.estimateNo)=:estimateNo AND TRIM(a.id.deptId)=:deptId";
		Query query6 = getEntityManager(webAppName).createQuery(qryStr6);
		query6.setParameter("estimateNo", pcesthmt.getId().getEstimateNo().trim());
		query6.setParameter("deptId", deptId);
		
		BigDecimal stdCost6 = (BigDecimal) query6.getSingleResult();
		System.out.println(stdCost6);
		hmt.setStdCost(stdCost6);
	
		pcesthmtDaoRemote.updatePcesthmt(hmt, webAppName);
	
	}
	
}
	public List<Pcesthmt> getRevisedJobDetailList(String deptId,
			List<Long> status, String userId, String webAppName) {
		// TODO Auto-generated method stub
		return pcesthmtDaoRemote.getRevisedJobDetailList(deptId,status, userId, webAppName);
	}

	public BigDecimal getTotalConsumerPayable(String estimateNo,String resType,String deptId,String webAppName) throws PersistenceException{
		
		/*try {
			//updateAdditionalCost(pcesthtt, deptId, pcesthtt.getStdCost(), webAppName);
		} catch (Exception e) {
			LOGGER.error("Error in Additional Cost calculation Estimate No:"+estimateNo);
		}*/
		StringBuffer qryStr2 = new StringBuffer();
		qryStr2.append("select sum(a.estimateCost) from Pcestdmt a where TRIM(a.id.estimateNo)=:estimateNo AND TRIM(a.id.deptId)=:deptId AND TRIM(a.id.revNo)=:revNo ");
		
		if(resType != null){
			qryStr2.append("AND resType <> :resType");
		}
		
		Query query2 = getEntityManager(webAppName).createQuery(qryStr2.toString());
		query2.setParameter("estimateNo", estimateNo.trim());
		query2.setParameter("deptId", deptId);
		query2.setParameter("revNo", "2");
		if(resType != null){
			
			query2.setParameter("resType", resType);
		}
		BigDecimal stdCost = (BigDecimal) query2.getSingleResult();
		return stdCost;
	
}



	@Override
	public void update(Pctrxhmt pctrxhmt, Pcesthmt pcesthmt,
			List<Pctrxdmt> addList, List<Pctrxdmt> upTrxList,
			List<Pcestdmt> updList, Long newRevNo, String webAppName) {
		
		try{
			pcesthmtDaoRemote.updatePcesthmt(pcesthmt, webAppName);
			pcesthmtDaoRemote.updateRevNo(pcesthmt, newRevNo, webAppName);
			pctrxhmtDaoRemote.updatePctrxhmt(pctrxhmt, webAppName);
			
			if (updList!=null){
			for(int j=0; j<=updList.size()-1; j++){
				pcestdmtDaoRemote.updatePcestdmt(updList.get(j), webAppName);
			}}
			if (updList!=null){
				pcestdmtDaoRemote.updateRevNo(updList.get(0), newRevNo, webAppName);
				
			}
			if (addList!=null){
				for(int j=0; j<=addList.size()-1; j++){
					pctrxdmtDaoRemote.createPctrxdmt(addList.get(j), webAppName);
			}}
			if (upTrxList!=null){
				for(int j=0; j<=upTrxList.size()-1; j++){
					pctrxdmtDaoRemote.updatePctrxdmt(upTrxList.get(j), webAppName);
			}}
			
			
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
	
		
	}



	

		

}
