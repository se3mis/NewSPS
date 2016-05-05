package security.web;

//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;

//import job.model.Pcesthmt;
//import job.service.PcesthmtEjb;

import masters.service.ProvinceDetailsMasterEjb;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.jboss.ws.metadata.wsse.Username;

import security.service.SecurityEjb;
import util.common.Constants;

 

import com.opensymphony.xwork2.ActionSupport;

import costcenter.service.GldeptinEjb;
import costcenter.service.GldeptmEjb;

import estimate.service.EstimateEjb;
//import estimate.model.Pcesthtt;
//import estimate.service.PcesthttEjb;



@SuppressWarnings("serial")
public class SelectCostCenter extends ActionSupport implements SessionAware, ParameterAware {
	private static final Log log = LogFactory.getLog(SelectCostCenter.class);
    private Map<String, Object> session;
	private String loggedInUserId;
	private String loggedInCostCenterNo;
	private Map <String, String[]> parameters;
	private String loggedInUserLevel;
	private String smcType;
	private String errorMessage;
	private List<String> estimateTypes = new ArrayList<String>();
	private String  branchType;
	private String  estimateType;
	//JSP Fields
	private String costCenterNo;
		
	public String execute() {
		try{
			System.out.println("ffffffffffffffffffffffff"+getSession().get("userRole"));
			GldeptmEjb gldeptmEjb=new GldeptmEjb(getSessionKey("region"));
			SecurityEjb ejb=new SecurityEjb(getSessionKey("region"));
			//setLoggedData();
			setLoggedInUserId(getSessionKey("userName"));
			setLoggedInUserLevel(getSessionKey("userRole"));
			
			//System.out.println(getCostCenterNo());
			//System.out.println(ejb.getCostCenter(getSession().get("userName").toString()).length());
			//System.out.println(ejb.getCostCenter(getSession().get("userName").toString()).equals(getCostCenterNo()));
			//String ccn=ejb.getCostCenter(loggedInUserId);
//			if (getCostCenterNo()==null || getCostCenterNo().length()==0 || getCostCenterNo().trim().equals("") ){
//				//System.out.println("GFHGGHFHFGKFKHFHGFHHFGGF");
//				setCostCenterNo("510.20");
//			}else if(getCostCenterNo().equals("541.00") || getCostCenterNo().equals("541.10") ||  getCostCenterNo().equals("541.20") || getCostCenterNo().equals("542.10")  || getCostCenterNo().equals("542.20") || getCostCenterNo().equals("547.10") || getCostCenterNo().equals("547.20") || getCostCenterNo().equals("548.10") || getCostCenterNo().equals("548.20") || getCostCenterNo().equals("541.00") || getCostCenterNo().equals("542.00") || getCostCenterNo().equals("547.00") || getCostCenterNo().equals("548.00")  ) {
//				System.out.println("kl;adsad;sad;asdmk;askld");
//				setCostCenterNo(getCostCenterNo())
//				
//			}else 
			//System.out.println(ejb.getAuthorizedCostCenters(loggedInUserId));
			
			 ProvinceDetailsMasterEjb promasEjb = new ProvinceDetailsMasterEjb();
			   String branchType = promasEjb.getBranchType(getCostCenterNo(), getSessionKey("region"));
			   if(branchType != null){
				  setBranchType(branchType);
			   }else{
				   setBranchType(Constants.DEFAULT_ESTIMATE_TYPE);
			   }
			  // System.out.println("branchType : "+branchType);
			   putSessionKey("branchType",getBranchType());
			   
			   String estimateType = promasEjb.getEstimateTypeByDeptId(getCostCenterNo(), getSessionKey("region"));
			   if(estimateType != null){
				  setEstimateType(estimateType);
			   }else{
				  setEstimateType(Constants.DEFAULT_ESTIMATE_TYPE);
			   }
			  // System.out.println("branchType : "+getEstimateType());
			   putSessionKey("estimateType",getEstimateType());
			   //getSession().put("branchType", getBranchType());
			log.info(loggedInUserId);
			if (ejb.getCostCenter(loggedInUserId).equals(getCostCenterNo()) || ejb.getAuthorizedCostCenters(loggedInUserId).contains(costCenterNo)){
				System.out.println("it is working" );
			}
			
			
			//if(ejb.getCostCenter(getSession().get("userName").toString()).equals(getCostCenterNo())) {
			//	System.out.println("kl;adsad;sad;asdmk;askld");
			//	setCostCenterNo(getCostCenterNo());
			//}
			else{
				setErrorMessage("You are not athorized User for this CSC");
				return ERROR;	
				}
			

			String deptName=null;
		
			/*EstimateEjb estimateEjb=new EstimateEjb(getSessionKey("region"));
			List<EstimateTypeMaster> masters = estimateEjb.getEstimateTypesbyDeptId(getCostCenterNo(), getSessionKey("region"));
			if(masters != null){
				for(EstimateTypeMaster master :masters ){
					estimateTypes.add(master.getId().getEstimateType());
				}
				
			}*/
			//setEmf(Persistence.createEntityManagerFactory(persistenceName));
			//setEm(emf.createEntityManager());
			//setEm(em);	
			//gldeptmDao= new GldeptmDao();
			//setLoggedData();
			
			putSessionKey("costCenterNo",getCostCenterNo());
			
			deptName=getCosCenterName(gldeptmEjb);
			putSessionKey("costCenterName", deptName);
			log.info(getSession());
			setLoggedInUserLevel( getSessionKey("userRole"));
			System.out.println("2 " +getLoggedInUserLevel());
			
			
			
			
			/*if (getLoggedInUserLevel() == "ES")
			{
				setRejectedEstimates();
				setRejectedAprovedJobEstimates();
			}*/
			
			/***** SAN **** Comment for SAN */
			System.out.println("it is working" + getSmcType());
				if(getSmcType() != null){
					/*if(getSmcType().contains("DE")){
						setSmcType("DE");
					}
					putSessionKey("smcType",getSmcType());
					return "success";*/
					
				/*	if(getSmcType().contains("DE")){
					setSmcType("DE");
					}*/
					putSessionKey("smcType",getSmcType());
					return "success";
				}else{
					
					putSessionKey("smcType","DE");
				}
				/*if (getSmcType().equals("BS")){
					putSessionKey("smcType",getSmcType());
					return "success";
				} else if (getSmcType().equals("RE")) {
					putSessionKey("smcType",getSmcType());
					return "success";
				} else if (getSmcType().equals("CR")) {
					putSessionKey("smcType",getSmcType());
					return "success";
				}
				
				
				else 
					putSessionKey("smcType",getSmcType());
					return "success";*/
			
			return "success";
		} catch (RollbackException e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE " + e.getMessage());
			setErrorMessage("ERROR MESSEGE " + e.getMessage());
			return ERROR;
		} catch (PersistenceException e) {
			e.printStackTrace();
			setErrorMessage("ERROR MESSEGE " + e.getMessage());
			System.out.println("ERROR MESSEGE " + e.getMessage());
			return ERROR;
		} catch (NullPointerException e) {
			e.printStackTrace();
			setErrorMessage("ERROR MESSEGE " + e.getMessage());
			System.out.println("ERROR MESSEGE " + e.getMessage());
			return ERROR;
		
		} catch (Exception e) {
			e.printStackTrace();
			setErrorMessage("ERROR MESSEGE " + e.getMessage());
			System.out.println("ERROR MESSEGE " + e.getMessage());
			return ERROR;
		}finally{
			//getEm().close();
			//getEmf().close();
			//emf=null;
			//em=null;
			//gldeptmDao=null;
			
		}
	       
	   }
	
	public String getSessionKey(String key) {
		return getSession().get(key).toString();
	}

	/*private void setRejectedEstimates()
	{	
		PcesthttEjb ejb=new PcesthttEjb(getSessionKey("region"));	
		List<Pcesthtt> tesmpList = null;
		this.rejectedList = new  ArrayList<Pcesthtt>();
		//tesmpList   = ejb.getEstApprovalList(getCostCenterNo(), new BigDecimal(31), "ES" );
		tesmpList   = ejb.getEstimateList(getCostCenterNo(), new BigDecimal(31)  );
		this.rejectedList.addAll(tesmpList);

 
		if (this.rejectedList.size() == 0)
			this.rejectedList = null;

	}*/
	/*
	private void setRejectedAprovedJobEstimates()
	{	
		PcesthmtEjb ejb=new PcesthmtEjb(getSessionKey("region"));		

		List<Pcesthmt> tesmpList = null;
		this.rejectedRevisedJobsList = new  ArrayList<Pcesthmt>();
		tesmpList   = ejb.getJobDetailList (getCostCenterNo(), new BigDecimal(41)    );
		//tesmpList   = ejb.getJobApprovalList(getCostCenterNo(), new BigDecimal(41), "ES" );
		this.rejectedRevisedJobsList.addAll(tesmpList);
		
		 
	 
		
 
		if (this.rejectedRevisedJobsList.size() == 0)
			this.rejectedRevisedJobsList = null;

	}*/
	
	
	private String getCosCenterName(GldeptmEjb gldeptmEjb) throws PersistenceException {
		log.info("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& "+gldeptmEjb.findDeptName(getCostCenterNo()));
		return gldeptmEjb.findDeptName(getCostCenterNo());
		
		//return gldeptmDao.findDeptName(getCostCenterNo(), getEm());
		
		
		
	}

	public String getCostCenterNo() {
		return costCenterNo;
	}

	public void setCostCenterNo(String costCenterNo) {
		this.costCenterNo = costCenterNo;
	}

	public String getLoggedInUserId() {
		return loggedInUserId;
	}

	public void setLoggedInUserId(String welcomeUser) {
		this.loggedInUserId = welcomeUser;
	}

	public String getLoggedInCostCenterNo() {
		return loggedInCostCenterNo;
	}

	public void setLoggedInCostCenterNo(String loggedInCostCenterNo) {
		this.loggedInCostCenterNo = loggedInCostCenterNo;
	}
	
	
	 public Map<String, Object> getSession() {
        return session;

    }

    @Override
    public void setSession(Map<String, Object> session) {
    	this.session = session; 
    }
   
   /* public void setLoggedData() {
	   System.out.println(getSession());
       //setLoggedInUserId(getSession().get("userName").toString());
       setLoggedInUserId(getSessionKey("userName"));
       setCostCenterNo(getSessionKey("costCenterNo"));
       ProvinceDetailsMasterEjb promasEjb = new ProvinceDetailsMasterEjb();
	   String branchType = promasEjb.getBranchType(getCostCenterNo(), getSessionKey("region"));
	   if(branchType != null){
		  setBranchType(branchType);
	   }else{
		   setBranchType(Constants.DEFAULT_BRANCHTYPE);
	   }
       
   }*/
   
   private void putSessionKey(String key, String value ){
	   getSession().put(key, value);
   
   }
   

public String getBranchType() {
	return branchType;
}

public void setBranchType(String branchType) {
	this.branchType = branchType;
}

@Override
public void setParameters(Map<String, String[]> parameters) {
	this.parameters=parameters;
}



private void setLogCostCenter(){
	log.info("###costCenterNo" + " " + getCostCenterNo());
	//String[] costCenterNoArray =(String[])getParameters().get("costCenterNo");
	//String costCenterNoValue=((costCenterNoArray!=null)?costCenterNoArray[0]:"NO");
	//setCostCenterNo(((costCenterNoArray!=null)?costCenterNoArray[0]:"NO"));
	//log.info("costCenterNo" + " " + getCostCenterNo());
	//costCenterNoArray=null;
}
 

public Map<String, String[]> getParameters() {
	return parameters;
}

public String getLoggedInUserLevel() {
	return loggedInUserLevel;
}

public void setLoggedInUserLevel(String loggedInUserLevel) {
	this.loggedInUserLevel = loggedInUserLevel;
}

public String getSmcType() {
	return smcType;
}

public void setSmcType(String smcType) {
	this.smcType = smcType;
}
public String getErrorMessage() {
	return errorMessage;
}

public void setErrorMessage(String errorMessage) {
	this.errorMessage = errorMessage;
}

public String getEstimateType() {
	return estimateType;
}

public void setEstimateType(String estimateType) {
	this.estimateType = estimateType;
}

}
