package security.web;

import java.util.Map;

import masters.service.ProvinceDetailsMasterEjb;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.SessionAware;

import security.service.SecurityEjb;
import util.common.Constants;
import util.common.Path;

import com.opensymphony.xwork2.ActionSupport;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport implements SessionAware{
	/**
	 * 
	 */
	private String costCenterNo;
	
	private String userName;
	private String password;
	private String region;
	private String errorMessage;
	private String loggedInUserId;
	private String msgUserId = null;
	private String msgPassword = null;
	private Map<String, Object> session;
	private String  branchType;
	private String logInUserLevel;
	
	private static final Log log = LogFactory.getLog(LoginAction.class);
    

	public String execute() {
		try{
			setErrorMessage(null);
			//System.out.println(getSession());
			getSession().put("region", getRegion());
			//System.out.println(getSession());
			SecurityEjb ejb=new SecurityEjb(getSessionKey("region"));
			//System.out.println(System.getProperty("os.name"));
			//System.out.println(System.getProperty("os.name").equals("Linux"));
			//System.out.println(System.getProperty("os.name").equals("Windows XP"));
			//System.out.println(System.getProperty("os.name"));
			//log.info("Log system is working");
			//LOG.info(this+" testion");
			//LOG.fatal("falal errot");
			//
			
			
			//setUserName("dileepa");
			//setPassword("dileepa");
			//
			//putNgetSessionUserName();
			/*
			 * if (this.userName.equals("d") && this.password.equals("d")) { return
			 * "success"; } else { return "error"; }
			 */
			
			//setRegion(Path.getWebAppName());
//			if (getUsername().length()==0 && getPassword().length()==0) {
//				setUserName("dileepa");
//				setPassword("dileepa");
//				putNgetSessionUserName(ejb);
//				return SUCCESS;
//			} else if (getUsername().equals("es") && getPassword().equals("es")) {
//				putNgetSessionUserName(ejb);
//				return SUCCESS;
//			} else if (getUsername().equals("ea") && getPassword().equals("ea")) {
//				putNgetSessionUserName(ejb);
//				return SUCCESS;
//			} else if (getUsername().equals("ae") && getPassword().equals("ae")) {
//				putNgetSessionUserName(ejb);
//				return SUCCESS;
//			} else if (getUsername().equals("ee") && getPassword().equals("ee")) {
//				putNgetSessionUserName(ejb);
//				return SUCCESS;
//			} else if (getUsername().equals("dgm") && getPassword().equals("dgm")) {
//				putNgetSessionUserName(ejb);
//				return SUCCESS;
//			} else if (getUsername().equals("agm") && getPassword().equals("agm")) {
//				putNgetSessionUserName(ejb);
//				return SUCCESS;	
//			} else if (getUsername().equals("d") && getPassword().equals("d")) {
//				putNgetSessionUserName(ejb);
//				return SUCCESS;	
//			} else 
			 
			   
			   
				if (ejb.volidateLogin(getUsername(), getPassword())==true){
				System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&##");
					if (putNgetSessionUserName(ejb)!=null){
						//System.out.println("##");
						if(ejb.getCostCenter(getLoggedInUserId()) != null){
							setCostCenterNo(ejb.getCostCenter(getLoggedInUserId()));
							ProvinceDetailsMasterEjb promasEjb = new ProvinceDetailsMasterEjb();
							   String branchType = promasEjb.getBranchType(getCostCenterNo(), getSessionKey("region"));
							   if(branchType != null){
								  setBranchType(branchType);
								 
							   }else{
								   setBranchType(Constants.DEFAULT_BRANCHCODE);
								   //getSession().put("region", getRegion());
							   }
							   getSession().put("branchType", getBranchType());
						}else{
							setErrorMessage("You are not authorized for this Cost center.");
							return ERROR;
						}
						
						//e.printStackTrace();
						return SUCCESS;
					}else{
						setErrorMessage("Please enter a USER LEVEL for this user. contact MIS IT Staff");
						//e.printStackTrace();
						return ERROR;
					}
				
			}else 
				setErrorMessage("Username Password does not match");
				return ERROR;
		}catch (Exception e) {
			if (e!=null){
				//if (e.getMessage().equals("org.hibernate.exception.GenericJDBCException: Cannot open connection")){
				//	setErrorMessage("ERROR:- "+"Database Server is Down. Please inform DB Admin through this no. 112481260");
				//}else{
				//	setErrorMessage("ERROR:- "+e.getMessage());
				//}
			}
				
			
			
			e.printStackTrace();
			return ERROR;
		}
		
	}
	public String login() {
		
		
		
		
		
		
		return SUCCESS;
	
		
	}
	
    /**
     * 
     */
    public String putNgetSessionUserName(SecurityEjb ejb) {
        //log.info("pp "+getSession());
    	setSession(ActionContext.getContext().getSession());
    	//log.info("pp "+getSession());
		getSession().put("userName", getUsername().toUpperCase());
		//log.info("pp "+getSession());
		//getSession().put("userRole", getUsername());
		if (getUsername().equals("es")){
			getSession().put("userRole", "ES");
			setLoggedInUserId(getSession().get("userName").toString());
			return getLoggedInUserId();
		}else if (getUsername().equals("ea")){
			getSession().put("userRole", "EA");
			setLoggedInUserId(getSession().get("userName").toString());
			return getLoggedInUserId();
		}else if (getUsername().equals("ae")){
			getSession().put("userRole", "AE");
			setLoggedInUserId(getSession().get("userName").toString());
			return getLoggedInUserId();
		}else if (getUsername().equals("ee")){
			getSession().put("userRole", "EE");
			setLoggedInUserId(getSession().get("userName").toString());
			return getLoggedInUserId();
		}else if (getUsername().equals("dgm")){
			getSession().put("userRole", "DGM");
			setLoggedInUserId(getSession().get("userName").toString());
			return getLoggedInUserId();
		}else if (getUsername().equals("agm")){
			getSession().put("userRole", "AGM");
			setLoggedInUserId(getSession().get("userName").toString());
			return getLoggedInUserId();
		}else if (getUsername().equals("dileepa")){
			getSession().put("userRole", "AGM");
			setLoggedInUserId(getSession().get("userName").toString());
			return getLoggedInUserId();
		}else {
			
			String userAuthorizedLevel=ejb.getAuthorizedLevel(userName);
			log.info("userAuthorizedLevel##"+userAuthorizedLevel);
			if (userAuthorizedLevel!=null){
				getSession().put("userRole", ejb.getAuthorizedLevel(userName));
				setLogInUserLevel(ejb.getAuthorizedLevel(userName));
				getSession().put("region", getRegion());
				setLoggedInUserId(getSession().get("userName").toString());
				return userAuthorizedLevel;
			}else{
				return null;
			}
			
			
		}
		
    }
    
    public String getUsername() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBranchType() {
		return branchType;
	}
	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setMsgPassword(String msgPassword) {
		this.msgPassword = msgPassword;
	}

	public String getMsgPassword() {
		return msgPassword;
	}

	public void setMsgUserId(String msgUserId) {
		this.msgUserId = msgUserId;
	}

	public String getMsgUserId() {
		return msgUserId;
	}

	public void setLoggedInUserId(String welcomeUser) {
		this.loggedInUserId = welcomeUser;
	}

	public String getLoggedInUserId() {
		return loggedInUserId;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
     * @return the session
     */
    public Map<String, Object> getSession() {
        return session;
    }

    /**
     * @param session the session to set
     */
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
    
    public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	public String getSessionKey(String key) {
        return getSession().get(key).toString();
    }
	public String getCostCenterNo() {
		return costCenterNo;
	}
	public void setCostCenterNo(String costCenterNo) {
		this.costCenterNo = costCenterNo;
	}
	
	public String getLogInUserLevel() {
		return logInUserLevel;
	}
	public void setLogInUserLevel(String logInUserLevel) {
		this.logInUserLevel = logInUserLevel;
	}
}