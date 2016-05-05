package estimate.ejb;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import estimate.model.Spestedy;
import estimate.model.SpestedyPK;

@Remote
public interface SpestedyDaoRemote {
	void createSpestedy(Spestedy spestedy, String webAppName);
	void createSpestedyAutoId(Spestedy spestedy, String webAppName);
	void updateSpestedy(Spestedy spestedy, String webAppName)  ;
	void removeSpestedy(Spestedy spestedy, String webAppName)  ;
	void removeAll(String webAppName) ;
	Spestedy findById(SpestedyPK id, String webAppName) throws PersistenceException ;
	List<Spestedy> getAll(String webAppName);
	List<Spestedy> getAll(String deptId, String webAppName);
	List<Spestedy> getAll(String deptId, Date appointmentDate, String webAppName);
	List<Spestedy> getAll(String deptId, Date appointmentDate, String allocatedTo, String webAppName);
	String getNextAppointmentId(String deptId, String webAppName);
	List<Spestedy> getAll(String deptId, Date appointmentDate, String allocatedTo, String status, String webAppName);
	List<Spestedy> getAll(String deptId, Date appointmentDate, String allocatedTo, String status, String estimateNo, String webAppName);
	//List<Spestedy> getAtiveAppoinments(String deptId, Date today, String allocatedTo, String status, String webAppName);
	List<Spestedy> getAtiveAppoinments(String deptId, String allocatedTo, String webAppName);
	List<Spestedy> getUnAttendedAppoinments(String deptId, String allocatedTo, String webAppName);
	List<Spestedy> getFailedAppoinments(String deptId, String allocatedTo, String webAppName);
	List<String> getVisitedAppoinments(String deptId, String allocatedTo, String webAppName); 
	void makeAnAppointment(Spestedy spestedy, String webAppName);
	void changeStatusAppointments(String applicationNo, String deptId, String status, String webAppName);
	Spestedy getAppointment(String deptId, String AppointmentNo, String webAppName);
	List<String> getVisitedAppoinments(String deptId, String allocatedTo,
			String applicationType, String webAppName);
	List<String> getVisitedAppoinments(String deptId, String allocatedTo,
			String applicationType, String applicationSubType, String webAppName);
	List<String> getFailedReferenceNoList(String deptId, String allocatedTo, String webAppName);
	String getAppointmentStatus(String referenceNo, String deptId,
			String webAppName);
	void makeAnAppointment(List<Spestedy> list, String webAppName);
	List<String> getAppoinmentsByStatus(String deptId, String allocatedTo,
			String applicationType, String status, String webAppName);
	List<String> getAppoinmentsByStatus(String deptId, String allocatedTo,
			String applicationType, String applicationSubType, String status,
			String webAppName);
	List<String> getVisitedAppoinmentsForMT_SA(String deptId,
			String allocatedTo, String webAppName);
	public List<String> loadApplicationRefnos(String userId,String costCenter,String webAppName);
	
	public int updateSpestedyStatus(String status,String referenceNo, String webAppName);
	List<Spestedy> getAtiveAppoinmentsforDept(String deptId, String webAppName);
	List<Spestedy> getAppoinmentsByStatus(String deptId, String applicationType, String status, String webAppName);
	

}
