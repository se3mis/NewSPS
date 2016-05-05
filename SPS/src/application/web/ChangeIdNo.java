package application.web;

import java.util.Map;

import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import application.model.Applicant;
import application.model.Application;
import application.model.ApplicationPK;
import application.service.ApplicantEjb;
import application.service.ApplicationEjb;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ChangeIdNo extends ActionSupport implements SessionAware, ParameterAware {
	private static final String MSG_ERROR="ERROR";
	private static final String MSG_INFO="INFO";
	private static final String MSG_DONE="DONE";
	private static final String MSG_NONE="NONE";
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private String loggedInUserId;
	private String errorMessage;
	private String messageType=MSG_NONE;
	

	private String applicationId;
	private String costCenterNo;
	private String idNo;
	private String firstName;
	private String lastName;
	private String streetAddress;
	private String suburb;
	private String city;
	private String postalCode;
	private String telephoneNo;
	private String mobileNo;
	private String email;
	private String preferefLanguage;
	private String cebEmployee;
	private String isLoanApp;
	private String idNoNew;
	private String firstNameNew;
	private String lastNameNew;
	private String streetAddressNew;
	private String suburbNew;
	private String cityNew;
	private String postalCodeNew;
	private String telephoneNoNew;
	private String mobileNoNew;
	private String emailNew;
	private String preferefLanguageNew;
	private String cebEmployeeNew;
	private String isLoanAppNew;
	
	public String execute(){
		return SUCCESS;
	}
	
	public String changeIdNoDirect(){
		setLoggedData();
		setApplicationId(costCenterNo+"/"+"ANC"+"/2011/");
		setIsLoanApp("N");
		setIsLoanAppNew("N");
		return SUCCESS;
	}
	
	public String findAppId(){
		try{
			setLoggedData();
			ApplicationEjb applicationEjb=new ApplicationEjb(getSessionKey("region"));
			ApplicationPK id=new ApplicationPK();
			id.setApplicationId(applicationId);
			id.setDeptId(costCenterNo);
			System.out.println(applicationEjb.findByAppId(id));
			Application application=applicationEjb.findByAppId(id);
			
			if (application!=null){
				
				ApplicantEjb applicantEjb=new ApplicantEjb(getSessionKey("region"));
				Applicant applicant=applicantEjb.findById(application.getIdNo());
				if (applicant!=null){
					setIdNo(applicant.getIdNo());
					setFirstName(applicant.getFirstName());
					setLastName(applicant.getLastName());
					setStreetAddress(applicant.getStreetAddress());
					setCity(applicant.getCity());
					setSuburb(applicant.getSuburb());
					if (applicant.getPostalCode() != null)
						setPostalCode(applicant.getPostalCode().toString());
					if (applicant.getTelephoneNo() != null)
						setTelephoneNo(applicant.getTelephoneNo().toString());
					if (applicant.getMobileNo() != null)
						setMobileNo(applicant.getMobileNo().toString());
					setPreferefLanguage(applicant.getPreferredLanguage());
					setEmail(applicant.getEmail());
					setCebEmployee(applicant.getCebEmployee());
					return SUCCESS;
					
				}else {
					setMessageType(MSG_INFO);
					setErrorMessage("Applicant Id not found");
					return ERROR;
					
				}
				
			}else{
				setMessageType(MSG_INFO);
				setErrorMessage("Application Id not found");
				return ERROR;
				
			}
		} catch (RollbackException e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			//setErrorMessage(e.getMessage());
			return ERROR;
		} catch (PersistenceException e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			//setErrorMessage(e.getMessage());
			return ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			//setErrorMessage(e.getMessage());
			return ERROR;
		}finally{
			//getEm().close();
			//getEmf().close();
			//emf=null;
			//getEm().close();
			//em=null;
			//applicant=null;
			//applicantDao=null;
			
		}	
		
	}
	
	public String findIdNo() {
		try{
			Applicant applicant=null;
			ApplicantEjb applicantEjb=new ApplicantEjb(getSessionKey("region"));
			//setState(getState());
			//setHiddenSecondState("findIdNo");
			setLoggedData();
			//setPath(getPath());
			//System.out.println("findIdNo() "+ getIdNo());
			
			applicant=applicantEjb.findById(getIdNoNew());
			System.out.println("findIdNo()");
			if (applicant != null) {
				setIdNoNew(applicant.getIdNo());
				setFirstNameNew(applicant.getFirstName());
				setLastNameNew(applicant.getLastName());
				setStreetAddressNew(applicant.getStreetAddress());
				setCityNew(applicant.getCity());
				setSuburbNew(applicant.getSuburb());
				if (applicant.getPostalCode() != null)
					setPostalCodeNew(applicant.getPostalCode().toString());
				if (applicant.getTelephoneNo() != null)
					setTelephoneNoNew(applicant.getTelephoneNo().toString());
				if (applicant.getMobileNo() != null)
					setMobileNoNew(applicant.getMobileNo().toString());
				setPreferefLanguageNew(applicant.getPreferredLanguage());
				setEmailNew(applicant.getEmail());
				setCebEmployeeNew(applicant.getCebEmployee());
				return SUCCESS;
			} else{
				setMessageType(MSG_INFO);
				setErrorMessage("Applicant Id not found");
				//clearApplicantFields("no");
				return ERROR;
			}
			
		} catch (RollbackException e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setMessageType(MSG_ERROR);
			setErrorMessage(e.getMessage());
			return ERROR;
		} catch (PersistenceException e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setMessageType(MSG_ERROR);
			setErrorMessage(e.getMessage());
			return ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setMessageType(MSG_ERROR);
			setErrorMessage(e.getMessage());
			return ERROR;
		}finally{
			//getEm().close();
			//getEmf().close();
			//emf=null;
			//getEm().close();
			//em=null;
			//applicant=null;
			//applicantDao=null;
			
		}

	}
	
	
	public String change() {
		try{
			ApplicationEjb applicationEjb=new ApplicationEjb(getSessionKey("region"));
			ApplicationPK id=new ApplicationPK();
			id.setApplicationId(applicationId);
			id.setDeptId(costCenterNo);
			System.out.println(applicationEjb.findByAppId(id));
			Application application=applicationEjb.findByAppId(id);
			if (application!=null){
				application.setIdNo(idNoNew);
				applicationEjb.updateApplication(application);
				setMessageType(MSG_DONE);
				setErrorMessage("Application applicant Id has been changed successfully.");
				return "change";
			}else{
				setMessageType(MSG_ERROR);
				setErrorMessage("Application Id not found");
				return ERROR;
			}
			
			
			
		} catch (RollbackException e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setMessageType(MSG_ERROR);
			setErrorMessage(e.getMessage());
			return ERROR;
		} catch (PersistenceException e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setMessageType(MSG_ERROR);
			setErrorMessage(e.getMessage());
			return ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR MESSEGE" + e.getMessage());
			setMessageType(MSG_ERROR);
			setErrorMessage(e.getMessage());
			return ERROR;
		}finally{
			//getEm().close();
			//getEmf().close();
			//emf=null;
			//getEm().close();
			//em=null;
			//applicant=null;
			//applicantDao=null;
			
		}
	}
	
	public String close() {
		//setLoggedData();
		return "close";
	}
	
	public void setLoggedData() {
		setLoggedInUserId(getSessionKey("userName"));
		setCostCenterNo(getSessionKey("costCenterNo"));
        //setCsc(getSessionKey("costCenterName"));
        //setSmcType(getSessionKey("smcType"));
        //setWebAppName(Path.getWebAppName());
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

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getTelephoneNo() {
		return telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPreferefLanguage() {
		return preferefLanguage;
	}

	public void setPreferefLanguage(String preferefLanguage) {
		this.preferefLanguage = preferefLanguage;
	}

	public String getCebEmployee() {
		return cebEmployee;
	}

	public void setCebEmployee(String cebEmployee) {
		this.cebEmployee = cebEmployee;
	}

	public String getIsLoanApp() {
		return isLoanApp;
	}

	public void setIsLoanApp(String isLoanApp) {
		this.isLoanApp = isLoanApp;
	}

	public String getIdNoNew() {
		return idNoNew;
	}

	public void setIdNoNew(String idNoNew) {
		this.idNoNew = idNoNew;
	}

	public String getFirstNameNew() {
		return firstNameNew;
	}

	public void setFirstNameNew(String firstNameNew) {
		this.firstNameNew = firstNameNew;
	}

	public String getLastNameNew() {
		return lastNameNew;
	}

	public void setLastNameNew(String lastNameNew) {
		this.lastNameNew = lastNameNew;
	}

	public String getStreetAddressNew() {
		return streetAddressNew;
	}

	public void setStreetAddressNew(String streetAddressNew) {
		this.streetAddressNew = streetAddressNew;
	}

	public String getSuburbNew() {
		return suburbNew;
	}

	public void setSuburbNew(String suburbNew) {
		this.suburbNew = suburbNew;
	}

	public String getCityNew() {
		return cityNew;
	}

	public void setCityNew(String cityNew) {
		this.cityNew = cityNew;
	}

	public String getPostalCodeNew() {
		return postalCodeNew;
	}

	public void setPostalCodeNew(String postalCodeNew) {
		this.postalCodeNew = postalCodeNew;
	}

	public String getTelephoneNoNew() {
		return telephoneNoNew;
	}

	public void setTelephoneNoNew(String telephoneNoNew) {
		this.telephoneNoNew = telephoneNoNew;
	}

	public String getMobileNoNew() {
		return mobileNoNew;
	}

	public void setMobileNoNew(String mobileNoNew) {
		this.mobileNoNew = mobileNoNew;
	}

	public String getEmailNew() {
		return emailNew;
	}

	public void setEmailNew(String emailNew) {
		this.emailNew = emailNew;
	}

	public String getPreferefLanguageNew() {
		return preferefLanguageNew;
	}

	public void setPreferefLanguageNew(String preferefLanguageNew) {
		this.preferefLanguageNew = preferefLanguageNew;
	}

	public String getCebEmployeeNew() {
		return cebEmployeeNew;
	}

	public void setCebEmployeeNew(String cebEmployeeNew) {
		this.cebEmployeeNew = cebEmployeeNew;
	}

	public String getIsLoanAppNew() {
		return isLoanAppNew;
	}

	public void setIsLoanAppNew(String isLoanAppNew) {
		this.isLoanAppNew = isLoanAppNew;
	}

	
	
	
	
	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, String[]> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String[]> parameters) {
		this.parameters = parameters;
	}
	
	public String getLoggedInUserId() {
		return loggedInUserId;
	}

	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}
	
	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	
}
