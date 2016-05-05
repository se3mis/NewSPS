package estimate.service;

import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.SpestedyCons;
import estimate.model.SpestedyConsPK;

public interface SpestedyConEjbI {
	void createSpestedyCon(SpestedyCons SpestedyCon);
	void createSpestedyConAutoId(SpestedyCons SpestedyCon);
	void updateSpestedyCon(SpestedyCons SpestedyCon)  ;
	void removeSpestedyCon(SpestedyCons SpestedyCon)  ;
	void removeAll() ;
	SpestedyCons findById(SpestedyConsPK id) throws PersistenceException ;
	List<SpestedyCons> getAll();
	List<SpestedyCons> getAll(String deptId);
	List<SpestedyCons> getAll(String deptId, Date appointmentDate);
	List<SpestedyCons> getAll(String deptId, Date appointmentDate, String allocatedTo);
	List<SpestedyCons> getAll(String deptId, Date appointmentDate, String allocatedTo, String status);
	List<SpestedyCons> getAll(String deptId, Date appointmentDate, String allocatedTo, String status, String estimateNo);
	String getNextAppointmentId(String deptId);
	//List<SpestedyCon> getAtiveAppoinments(String deptId, Date today, String allocatedTo, String status);
	List<SpestedyCons> getAtiveAppoinments(String deptId, String allocatedTo);
	List<SpestedyCons> getUnAttendedAppoinments(String deptId, String allocatedTo);
	List<SpestedyCons> getFailedAppoinments(String deptId, String allocatedTo);
	List<String> getVisitedAppoinments(String deptId, String allocatedTo);
	List<String> getVisitedAppoinments(String deptId, String allocatedTo, String applicationType);
	List<String> getVisitedAppoinments(String deptId, String allocatedTo, String applicationType, String applicationSubType);

	//List<String> getVisitedAppoinments(String deptId, String allocatedTo, String applicationType);
	//List<String> getVisitedAppoinments(String deptId, String allocatedTo,String applicationType, String applicationSubType);
	void makeAnAppointment(SpestedyCons SpestedyCon);
	SpestedyCons getAppointment(String deptId, String AppointmentNo);
	List<String> getFailedReferenceNoList(String deptId, String allocatedTo);
	List<SpestedyCons> getAppointmentByUser(String referenceNo,String estimateNo, String deptId,String loginUser);
	SpestedyCons getAppointmentByEstimateNo(String referenceNo,String estimateNo, String deptId);
	
	void makeAnAppointment(List<SpestedyCons> list);
	List<String> getAppoinmentsByStatus(String deptId, String allocatedTo,
			String applicationType, String status);
	List<String> getAppoinmentsByStatus(String deptId, String allocatedTo,
			String applicationType, String applicationSubType, String status);
	List<String> getVisitedAppoinmentsForMT_SA(String deptId,
			String allocatedTo);	
	public List<String> loadApplicationRefnos(String allocatedTo,String deptId, String status);
	public void changeStatusAppointments(String applicationNo, String deptId, String status);
	List<SpestedyCons> getAppointmentForReference(String deptId,
			String referenceNo, String webAppName);
	void changeStatusAppointmentsByUser(String applicationNo,
			String deptId, String status, String allocatedTo);
	List<SpestedyCons> getAtiveAppoinmentsforDept(String deptId,
			String webAppName);
	void changeStatusAppointByWorkEstimate(String applicationNo,
			String deptId, String status, String workestimateNo);
	 void updateWorkEstimateNumber(String applicationNo, String deptId,
				String status, String workestimateNo);
	 void addAppointment(SpestedyCons spestedy);
}
