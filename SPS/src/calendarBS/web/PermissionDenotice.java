package calendarBS.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import util.common.DenoticePermissionTypes;
import com.opensymphony.xwork2.ActionSupport;
import estimate.model.Spestpem;
import estimate.model.SpestpemPK;
import estimate.service.SpestpemEjb;

public class PermissionDenotice extends ActionSupport implements SessionAware, ParameterAware{

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(PermissionDenotice.class);
	private static String newPath="";
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private String costCenterNo;
	private String csc;
	private String path;
	private String estimateNo;
	private String statusMsgErr;
	private String statusMsg;
	private String hid_DnoticeChk;
	private String hid_RDAChk;
	private String hid_PSDChk;
	private String hid_MuncipalChk;
	private String hid_PoliceChk;	
	private Date  denoticeIssuedDate;
	private Date  denoticeClearedDate;
	private Date  issuedDateRDA;
	private Date  clearedDateRDA;
	private Date  issuedDatePSD;
	private Date  clearedDatePSD;
	private Date  issuedDateMuncipal;
	private Date  clearedDateMuncipal;
	private Date  issuedDatePolice;
	private Date  clearedDatePolice;	
	private String hid_isFind;	
	private String chkdeNotice;
	private String chkRDA;
	private String chkPSD;
	private String chkMuncipal;
	private String chkPolice;	
	private String found_hid_DnoticeChk;
	private String found_hid_RDAChk;
	private String found_hid_PSDChk;
	private String found_hid_MuncipalChk;
	private String found_hid_PoliceChk;
	private String hid_isNew;	
	

	public String getHid_isNew() {
		return hid_isNew;
	}
	public void setHid_isNew(String hid_isNew) {
		this.hid_isNew = hid_isNew;
	}
	public String getFound_hid_DnoticeChk() {
		return found_hid_DnoticeChk;
	}
	public void setFound_hid_DnoticeChk(String found_hid_DnoticeChk) {
		this.found_hid_DnoticeChk = found_hid_DnoticeChk;
	}
	public String getFound_hid_RDAChk() {
		return found_hid_RDAChk;
	}
	public void setFound_hid_RDAChk(String found_hid_RDAChk) {
		this.found_hid_RDAChk = found_hid_RDAChk;
	}
	public String getFound_hid_PSDChk() {
		return found_hid_PSDChk;
	}
	public void setFound_hid_PSDChk(String found_hid_PSDChk) {
		this.found_hid_PSDChk = found_hid_PSDChk;
	}
	public String getFound_hid_MuncipalChk() {
		return found_hid_MuncipalChk;
	}
	public void setFound_hid_MuncipalChk(String found_hid_MuncipalChk) {
		this.found_hid_MuncipalChk = found_hid_MuncipalChk;
	}
	public String getFound_hid_PoliceChk() {
		return found_hid_PoliceChk;
	}
	public void setFound_hid_PoliceChk(String found_hid_PoliceChk) {
		this.found_hid_PoliceChk = found_hid_PoliceChk;
	}
	public String getChkdeNotice() {
		return chkdeNotice;
	}
	public void setChkdeNotice(String chkdeNotice) {
		this.chkdeNotice = chkdeNotice;
	}
	public String getChkRDA() {
		return chkRDA;
	}
	public void setChkRDA(String chkRDA) {
		this.chkRDA = chkRDA;
	}
	public String getChkPSD() {
		return chkPSD;
	}
	public void setChkPSD(String chkPSD) {
		this.chkPSD = chkPSD;
	}
	public String getChkMuncipal() {
		return chkMuncipal;
	}
	public void setChkMuncipal(String chkMuncipal) {
		this.chkMuncipal = chkMuncipal;
	}
	public String getChkPolice() {
		return chkPolice;
	}
	public void setChkPolice(String chkPolice) {
		this.chkPolice = chkPolice;
	}
	public String getHid_isFind() {
		return hid_isFind;
	}
	public void setHid_isFind(String hid_isFind) {
		this.hid_isFind = hid_isFind;
	}
	public Date getDenoticeIssuedDate() {
		return denoticeIssuedDate;
	}
	public void setDenoticeIssuedDate(Date denoticeIssuedDate) {
		this.denoticeIssuedDate = denoticeIssuedDate;
	}
	public Date getDenoticeClearedDate() {
		return denoticeClearedDate;
	}
	public void setDenoticeClearedDate(Date denoticeClearedDate) {
		this.denoticeClearedDate = denoticeClearedDate;
	}
	public Date getIssuedDateRDA() {
		return issuedDateRDA;
	}
	public void setIssuedDateRDA(Date issuedDateRDA) {
		this.issuedDateRDA = issuedDateRDA;
	}
	public Date getClearedDateRDA() {
		return clearedDateRDA;
	}
	public void setClearedDateRDA(Date clearedDateRDA) {
		this.clearedDateRDA = clearedDateRDA;
	}
	public Date getIssuedDatePSD() {
		return issuedDatePSD;
	}
	public void setIssuedDatePSD(Date issuedDatePSD) {
		this.issuedDatePSD = issuedDatePSD;
	}
	public Date getClearedDatePSD() {
		return clearedDatePSD;
	}
	public void setClearedDatePSD(Date clearedDatePSD) {
		this.clearedDatePSD = clearedDatePSD;
	}
	public Date getIssuedDateMuncipal() {
		return issuedDateMuncipal;
	}
	public void setIssuedDateMuncipal(Date issuedDateMuncipal) {
		this.issuedDateMuncipal = issuedDateMuncipal;
	}
	public Date getClearedDateMuncipal() {
		return clearedDateMuncipal;
	}
	public void setClearedDateMuncipal(Date clearedDateMuncipal) {
		this.clearedDateMuncipal = clearedDateMuncipal;
	}
	public Date getIssuedDatePolice() {
		return issuedDatePolice;
	}
	public void setIssuedDatePolice(Date issuedDatePolice) {
		this.issuedDatePolice = issuedDatePolice;
	}
	public Date getClearedDatePolice() {
		return clearedDatePolice;
	}
	public void setClearedDatePolice(Date clearedDatePolice) {
		this.clearedDatePolice = clearedDatePolice;
	}
	public String getHid_DnoticeChk() {
		return hid_DnoticeChk;
	}
	public void setHid_DnoticeChk(String hid_DnoticeChk) {
		this.hid_DnoticeChk = hid_DnoticeChk;
	}
	public String getHid_RDAChk() {
		return hid_RDAChk;
	}
	public void setHid_RDAChk(String hid_RDAChk) {
		this.hid_RDAChk = hid_RDAChk;
	}
	public String getHid_PSDChk() {
		return hid_PSDChk;
	}
	public void setHid_PSDChk(String hid_PSDChk) {
		this.hid_PSDChk = hid_PSDChk;
	}
	public String getHid_MuncipalChk() {
		return hid_MuncipalChk;
	}
	public void setHid_MuncipalChk(String hid_MuncipalChk) {
		this.hid_MuncipalChk = hid_MuncipalChk;
	}
	public String getHid_PoliceChk() {
		return hid_PoliceChk;
	}
	public void setHid_PoliceChk(String hid_PoliceChk) {
		this.hid_PoliceChk = hid_PoliceChk;
	}
	public String getStatusMsgErr() {
		return statusMsgErr;
	}
	public void setStatusMsgErr(String statusMsgErr) {
		this.statusMsgErr = statusMsgErr;
	}
	public String getStatusMsg() {
		return statusMsg;
	}
	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}
	public String getEstimateNo() {
		return estimateNo;
	}
	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getLoggedInUserId() {
		return loggedInUserId;
	}
	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}
	public String getCostCenterNo() {
		return costCenterNo;
	}
	public void setCostCenterNo(String costCenterNo) {
		this.costCenterNo = costCenterNo;
	}
	public String getCsc() {
		return csc;
	}
	public void setCsc(String csc) {
		this.csc = csc;
	}
	public Map<String, String[]> getParameters() {
		return parameters;
	}
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters = parameters;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public static String getNewpath() {
		return newPath;
	}
	
	public void setLoggedData() {       
        log.info(getSession());
        setLoggedInUserId(getSessionKey("userName"));
        setCostCenterNo(getSessionKey("costCenterNo"));
        setCsc(getSessionKey("costCenterName"));       
    }	
		
	public String getSessionKey(String key) {
        return getSession().get(key).toString();
    }
	
	public String close(){
		setLoggedData();
		return "closed";
	}
	
	public String execute(){
		try{	
			hid_isNew = "isNew";
			newPath="Calendar>New D-Notice/Permission";
			setLoggedData();
			setPath(newPath);
			costCenterNo = getSessionKey("costCenterNo");
		}catch(Exception ex){
			
		}
		return "success";
	}
	
	public String modify(){
		try{	
			hid_isNew = "";
			newPath="Calendar>Update D-Notice/Permission";
			setLoggedData();
			setPath(newPath);
			costCenterNo = getSessionKey("costCenterNo");
		}catch(Exception ex){
			
		}
		return "success";
	}
	
	public String savePermission(){
		try{
			SpestpemEjb pemejb=new SpestpemEjb(getSessionKey("region"));		
		
			
			if(hid_isFind==null || hid_isFind=="" || hid_isFind.length()==0){
				
				List<Spestpem> spestpemList = new ArrayList<Spestpem>();
				SpestpemEjb ejb=new SpestpemEjb(getSessionKey("region"));				
				List<Spestpem> spestpemList1=ejb.getPermissions(estimateNo.trim(),getSessionKey("costCenterNo").trim());		
				
				if(spestpemList1!=null){
					statusMsgErr = "Record already exists.Go to modify page to update";					
					reSetForm(false);					
					return "success";
				}
				
				if(chkdeNotice!=null && chkdeNotice.equals("true")){
					SpestpemPK dnoticeid=new SpestpemPK();
					dnoticeid.setApplicationNo(estimateNo);
					dnoticeid.setDeptId(getSessionKey("costCenterNo"));
					dnoticeid.setPermissionType(DenoticePermissionTypes.DENOTICE);
					
					Spestpem spestpemDnot = new Spestpem();
					spestpemDnot.setId(dnoticeid);
					spestpemDnot.setIssuesDate(denoticeIssuedDate);					
					if(denoticeClearedDate!=null ){
						spestpemDnot.setClearDate(denoticeClearedDate);
					}
					spestpemList.add(spestpemDnot);
				}
							
				if(chkRDA!=null && chkRDA.equals("true")){
					
					SpestpemPK rdaid=new SpestpemPK();
					rdaid.setApplicationNo(estimateNo);
					rdaid.setDeptId(getSessionKey("costCenterNo"));
					rdaid.setPermissionType(DenoticePermissionTypes.RDA);
					
					Spestpem spestpemRda = new Spestpem();
					spestpemRda.setId(rdaid);
					spestpemRda.setIssuesDate(issuedDateRDA);					
					if(clearedDateRDA!=null ){
						spestpemRda.setClearDate(clearedDateRDA);
					}
					spestpemList.add(spestpemRda);
				}
				
				if(chkPSD!=null && chkPSD.equals("true")){
					SpestpemPK rdpsd=new SpestpemPK();
					rdpsd.setApplicationNo(estimateNo);
					rdpsd.setDeptId(getSessionKey("costCenterNo"));
					rdpsd.setPermissionType(DenoticePermissionTypes.PSD);
					
					Spestpem spestpemPsd = new Spestpem();
					spestpemPsd.setId(rdpsd);
					spestpemPsd.setIssuesDate(issuedDatePSD);					
					if(clearedDatePSD!=null ){
						spestpemPsd.setClearDate(clearedDatePSD);
					}
					spestpemList.add(spestpemPsd);
				}
				
				if(chkMuncipal!=null && chkMuncipal.equals("true")){
					SpestpemPK idMuncipal=new SpestpemPK();
					idMuncipal.setApplicationNo(estimateNo);
					idMuncipal.setDeptId(getSessionKey("costCenterNo"));
					idMuncipal.setPermissionType(DenoticePermissionTypes.MUNCIPAL);
					
					Spestpem spestpemMuncipal = new Spestpem();
					spestpemMuncipal.setId(idMuncipal);
					spestpemMuncipal.setIssuesDate(issuedDateMuncipal);					
					if(clearedDateMuncipal!=null ){
						spestpemMuncipal.setClearDate(clearedDateMuncipal);
					}
					spestpemList.add(spestpemMuncipal);
				}
				
				if(chkPolice!=null && chkPolice.equals("true")){
					SpestpemPK idPolice=new SpestpemPK();
					idPolice.setApplicationNo(estimateNo);
					idPolice.setDeptId(getSessionKey("costCenterNo"));
					idPolice.setPermissionType(DenoticePermissionTypes.POLICE);
					
					Spestpem spestpemPolice = new Spestpem();
					spestpemPolice.setId(idPolice);
					spestpemPolice.setIssuesDate(issuedDatePolice);					
					if(clearedDatePolice!=null ){
						spestpemPolice.setClearDate(clearedDatePolice);
					}
					spestpemList.add(spestpemPolice);
					
				}
				pemejb.createSpestpem(spestpemList);
				statusMsg = "The records saved successfully";
				statusMsgErr="";
			}else{
				// Updating an existing record
				List<SpestpemPK> spestpemRemoveList = new ArrayList<SpestpemPK>();
				List<Spestpem> spestpemUpdateList = new ArrayList<Spestpem>();
				
				if(found_hid_DnoticeChk!=null && found_hid_DnoticeChk.length()>0){
					SpestpemPK remDnoticeid=new SpestpemPK();
					remDnoticeid.setApplicationNo(estimateNo);
					remDnoticeid.setDeptId(getSessionKey("costCenterNo"));
					remDnoticeid.setPermissionType(DenoticePermissionTypes.DENOTICE);
					
					spestpemRemoveList.add(remDnoticeid);			
				}
				if(found_hid_RDAChk!=null && found_hid_RDAChk.length()>0){
					SpestpemPK remrdaid=new SpestpemPK();
					remrdaid.setApplicationNo(estimateNo);
					remrdaid.setDeptId(getSessionKey("costCenterNo"));
					remrdaid.setPermissionType(DenoticePermissionTypes.RDA);
					
					spestpemRemoveList.add(remrdaid);
				}
				if(found_hid_PSDChk!=null &&  found_hid_PSDChk.length()>0){
					SpestpemPK remrdpsd=new SpestpemPK();
					remrdpsd.setApplicationNo(estimateNo);
					remrdpsd.setDeptId(getSessionKey("costCenterNo"));
					remrdpsd.setPermissionType(DenoticePermissionTypes.PSD);
			
					spestpemRemoveList.add(remrdpsd);
				}
				if(found_hid_MuncipalChk!=null && found_hid_MuncipalChk.length()>0){
					SpestpemPK remidMuncipal=new SpestpemPK();
					remidMuncipal.setApplicationNo(estimateNo);
					remidMuncipal.setDeptId(getSessionKey("costCenterNo"));
					remidMuncipal.setPermissionType(DenoticePermissionTypes.MUNCIPAL);
					
					spestpemRemoveList.add(remidMuncipal);
			
				}
				if(found_hid_PoliceChk!=null  && found_hid_PoliceChk.length()>0){
					SpestpemPK remidPolice=new SpestpemPK();
					remidPolice.setApplicationNo(estimateNo);
					remidPolice.setDeptId(getSessionKey("costCenterNo"));
					remidPolice.setPermissionType(DenoticePermissionTypes.POLICE);
					
					spestpemRemoveList.add(remidPolice);
				
				}
				
				pemejb.removeSpestpem(spestpemRemoveList);
				
				if(chkdeNotice!=null && chkdeNotice.equals("true")){
					SpestpemPK dnoticeid=new SpestpemPK();
					dnoticeid.setApplicationNo(estimateNo);
					dnoticeid.setDeptId(getSessionKey("costCenterNo"));
					dnoticeid.setPermissionType(DenoticePermissionTypes.DENOTICE);
					
					Spestpem spestpemDnot = new Spestpem();
					spestpemDnot.setId(dnoticeid);
					spestpemDnot.setIssuesDate(denoticeIssuedDate);					
					if(denoticeClearedDate!=null ){
						spestpemDnot.setClearDate(denoticeClearedDate);
					}
					spestpemUpdateList.add(spestpemDnot);
				}				
			
				if(chkRDA!=null && chkRDA.equals("true")){
					
					SpestpemPK rdaid=new SpestpemPK();
					rdaid.setApplicationNo(estimateNo);
					rdaid.setDeptId(getSessionKey("costCenterNo"));
					rdaid.setPermissionType(DenoticePermissionTypes.RDA);
					
					Spestpem spestpemRda = new Spestpem();
					spestpemRda.setId(rdaid);
					spestpemRda.setIssuesDate(issuedDateRDA);					
					if(clearedDateRDA!=null ){
						spestpemRda.setClearDate(clearedDateRDA);
					}
					spestpemUpdateList.add(spestpemRda);
				}
				
				if(chkPSD!=null && chkPSD.equals("true")){
					SpestpemPK rdpsd=new SpestpemPK();
					rdpsd.setApplicationNo(estimateNo);
					rdpsd.setDeptId(getSessionKey("costCenterNo"));
					rdpsd.setPermissionType(DenoticePermissionTypes.PSD);
					
					Spestpem spestpemPsd = new Spestpem();
					spestpemPsd.setId(rdpsd);
					spestpemPsd.setIssuesDate(issuedDatePSD);					
					if(clearedDatePSD!=null ){
						spestpemPsd.setClearDate(clearedDatePSD);
					}
					spestpemUpdateList.add(spestpemPsd);
				}
				
				if(chkMuncipal!=null && chkMuncipal.equals("true")){
					SpestpemPK idMuncipal=new SpestpemPK();
					idMuncipal.setApplicationNo(estimateNo);
					idMuncipal.setDeptId(getSessionKey("costCenterNo"));
					idMuncipal.setPermissionType(DenoticePermissionTypes.MUNCIPAL);
					
					Spestpem spestpemMuncipal = new Spestpem();
					spestpemMuncipal.setId(idMuncipal);
					spestpemMuncipal.setIssuesDate(issuedDateMuncipal);					
					if(clearedDateMuncipal!=null ){
						spestpemMuncipal.setClearDate(clearedDateMuncipal);
					}
					spestpemUpdateList.add(spestpemMuncipal);
				}
				
				if(chkPolice!=null && chkPolice.equals("true")){
					SpestpemPK idPolice=new SpestpemPK();
					idPolice.setApplicationNo(estimateNo);
					idPolice.setDeptId(getSessionKey("costCenterNo"));
					idPolice.setPermissionType(DenoticePermissionTypes.POLICE);
					
					Spestpem spestpemPolice = new Spestpem();
					spestpemPolice.setId(idPolice);
					spestpemPolice.setIssuesDate(issuedDatePolice);					
					if(clearedDatePolice!=null ){
						spestpemPolice.setClearDate(clearedDatePolice);
					}
					spestpemUpdateList.add(spestpemPolice);
					
				}
				pemejb.createSpestpem(spestpemUpdateList);				
				statusMsg = "The records updated successfully";
				statusMsgErr="";
			}
			
				
		}catch(Exception ex){
			System.out.println("Exception..................");
			statusMsg="";
			statusMsgErr = "Error in saving ";
			System.out.println(ex);
		}
		setLoggedData();
		reSetForm(false);		
		return "success";
	}
	
	public String viewDeNoticePermissionDetails(){
		try{
			setLoggedData();
			SpestpemEjb ejb=new SpestpemEjb(getSessionKey("region"));				
			List<Spestpem> spestpemList=ejb.getPermissions(estimateNo.trim(),getSessionKey("costCenterNo").trim());		
			
			if(spestpemList!=null){
				setDeNoticePermissionDetails(spestpemList);
			}
				
			if(spestpemList.size()==0)
				statusMsg = "No records found";
			else
				hid_isFind = "Found";
			

		}catch(Exception ex){
			
		}
		return "success";
	}
	
	public void setDeNoticePermissionDetails(List<Spestpem> spestpemlist){
		
		Iterator<Spestpem> it = spestpemlist.iterator();
		hid_DnoticeChk = "";
		hid_RDAChk= "";
		hid_PSDChk= "";
		hid_MuncipalChk= "";
		hid_PoliceChk= "";
		
		statusMsg ="";			
		statusMsgErr="";
		reSetForm(true);
        while (it.hasNext()) {        	 
        	Spestpem spestpem=it.next();
        	String permissionType = spestpem.getId().getPermissionType();
        	if(permissionType.equals(DenoticePermissionTypes.DENOTICE)){
        		hid_DnoticeChk= "filled";
        		found_hid_DnoticeChk= "filled";
        		if(spestpem.getIssuesDate()!=null)
        			denoticeIssuedDate = spestpem.getIssuesDate();
        		if(spestpem.getClearDate()!=null)
        			denoticeClearedDate = spestpem.getClearDate();  
        	}else if(permissionType.equals(DenoticePermissionTypes.RDA)){
        		hid_RDAChk= "filled";
        		found_hid_RDAChk= "filled";
        		if(spestpem.getIssuesDate()!=null)
        			issuedDateRDA = spestpem.getIssuesDate();
        		if(spestpem.getClearDate()!=null)
        			clearedDateRDA = spestpem.getClearDate();  
        	}else if(permissionType.equals(DenoticePermissionTypes.PSD)){ 
        		hid_PSDChk = "filled";
        		found_hid_PSDChk = "filled";
        		if(spestpem.getIssuesDate()!=null)       			
        			issuedDatePSD = spestpem.getIssuesDate();        		     			
        		if(spestpem.getClearDate()!=null)
        			clearedDatePSD = spestpem.getClearDate();        		 
        	}else if(permissionType.equals(DenoticePermissionTypes.MUNCIPAL)){
        		hid_MuncipalChk = "filled";
        		found_hid_MuncipalChk = "filled";
        		if(spestpem.getIssuesDate()!=null)
        			issuedDateMuncipal = spestpem.getIssuesDate();
        		if(spestpem.getClearDate()!=null)
        			clearedDateMuncipal = spestpem.getClearDate();   
        		
        	}else if(permissionType.equals(DenoticePermissionTypes.POLICE)){
        		hid_PoliceChk = "filled";
        		found_hid_PoliceChk = "filled";
        		if(spestpem.getIssuesDate()!=null)
        			issuedDatePolice = spestpem.getIssuesDate();
        		if(spestpem.getClearDate()!=null)
        			clearedDatePolice = spestpem.getClearDate(); 
        	}
        			       	        	        	
          } 
	}
	
	public void reSetForm(boolean isFromFind){
		hid_isFind ="";
		hid_DnoticeChk = "";
		hid_RDAChk= "";
		hid_PSDChk= "";
		hid_MuncipalChk= "";
		hid_PoliceChk= "";
		
		found_hid_DnoticeChk="";
		found_hid_RDAChk="";
		found_hid_PSDChk="";
		found_hid_MuncipalChk="";
		found_hid_PoliceChk="";
		
		
		if(!isFromFind)
			estimateNo = "";	
    	denoticeIssuedDate = null;    	
    	denoticeClearedDate = null;     	
    	issuedDateRDA = null;    	
    	clearedDateRDA = null;           			
    	issuedDatePSD = null;		     			
    	clearedDatePSD = null;  		 
    	issuedDateMuncipal = null;
    	clearedDateMuncipal = null;     		   	
    	issuedDatePolice = null;    		
    	clearedDatePolice = null;    	
    	
    	
	}
}

