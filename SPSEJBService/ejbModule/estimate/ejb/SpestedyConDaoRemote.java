package estimate.ejb;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.SpestedyCons;
import estimate.model.SpestedyConsPK;

@Remote
public interface SpestedyConDaoRemote {
	void createSpestedyCon(SpestedyCons spestedyCon, String webAppName);
	void createSpestedyConAutoId(SpestedyCons spestedyCon, String webAppName);
	void updateSpestedyCon(SpestedyCons spestedyCon, String webAppName)  ;
	void removeSpestedyCon(SpestedyCons spestedyCon, String webAppName)  ;
	void removeAll(String webAppName) ;
	SpestedyCons findById(SpestedyConsPK id, String webAppName) throws PersistenceException ;
	List<SpestedyCons> getAll(String webAppName);
	List<SpestedyCons> getAll(String deptId, String webAppName);
	List<SpestedyCons> getAll(String deptId, Date appointmentDate, String webAppName);
	List<SpestedyCons> getAll(String deptId, Date appointmentDate, String allocatedTo, String webAppName);
	String getNextAppointmentId(String deptId, String webAppName);
	List<SpestedyCons> getAll(String deptId, Date appointmentDate, String allocatedTo, String status, String webAppName);
	List<SpestedyCons> getAll(String deptId, Date appointmentDate, String allocatedTo, String status, String estimateNo, String webAppName);
	//List<SpestedyCon> getAtiveAppoinments(String deptId, Date today, String allocatedTo, String status, String webAppName);
	List<SpestedyCons> getAtiveAppoinments(String deptId, String allocatedTo, String webAppName);
	List<SpestedyCons> getUnAttendedAppoinments(String deptId, String allocatedTo, String webAppName);
	List<SpestedyCons> getFailedAppoinments(String deptId, String allocatedTo, String webAppName);
	List<String> getVisitedAppoinments(String deptId, String allocatedTo, String webAppName); 
	void makeAnAppointment(SpestedyCons SpestedyCon, String webAppName);
	void changeStatusAppointments(String applicationNo, String deptId, String status, String webAppName);
	SpestedyCons getAppointment(String deptId, String AppointmentNo, String webAppName);
	SpestedyCons getAppointmentByEstimateNo(String referenceNo,String westimateNo, String deptId,String webAppName);
	List<String> getVisitedAppoinments(String deptId, String allocatedTo,
			String applicationType, String webAppName);
	List<String> getVisitedAppoinments(String deptId, String allocatedTo,
			String applicationType, String applicationSubType, String webAppName);
	List<String> getFailedReferenceNoList(String deptId, String allocatedTo, String webAppName);
	List<SpestedyCons> getAppointmentByUser(String referenceNo,String westimateNo, String deptId,String loginUser, String webAppName) ;
	void makeAnAppointment(List<SpestedyCons> list, String webAppName);
	List<String> getAppoinmentsByStatus(String deptId, String allocatedTo,
			String applicationType, String status, String webAppName);
	List<String> getAppoinmentsByStatus(String deptId, String allocatedTo,
			String applicationType, String applicationSubType, String status,
			String webAppName);
	List<String> getVisitedAppoinmentsForMT_SA(String deptId,
			String allocatedTo, String webAppName);
	public List<String> loadApplicationRefnos(String userId,String costCenter,String status,String webAppName);
	
	public int updateSpestedyConStatus(String status,String referenceNo, String webAppName);
	List<SpestedyCons> getAppointmentForReference(String deptId, String referenceNo, String webAppName);
	void changeStatusAppointmentsByUser(String applicationNo, String deptId, String status,String allocatedTo, String webAppName);
	List<SpestedyCons> getAtiveAppoinmentsforDept(String deptId, String webAppName);
	public void changeStatusAppointByWorkEstimate(String applicationNo, String deptId, String status,String workestimateNo, String webAppName);
	 void updateWorkEstimateNumber(String applicationNo, String deptId, String status,String workestimateNo, String webAppName) ;
	 void addAppointment(SpestedyCons spestedy, String webAppName);
}
