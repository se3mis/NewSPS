package estimate.service;

import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.Spestedy;
import estimate.model.SpestedyPK;

public interface SpestedyEjbI {
	void createSpestedy(Spestedy spestedy);
	void createSpestedyAutoId(Spestedy spestedy);
	void updateSpestedy(Spestedy spestedy)  ;
	void removeSpestedy(Spestedy spestedy)  ;
	void removeAll() ;
	Spestedy findById(SpestedyPK id) throws PersistenceException ;
	List<Spestedy> getAll();
	List<Spestedy> getAll(String deptId);
	List<Spestedy> getAll(String deptId, Date appointmentDate);
	List<Spestedy> getAll(String deptId, Date appointmentDate, String allocatedTo);
	List<Spestedy> getAll(String deptId, Date appointmentDate, String allocatedTo, String status);
	List<Spestedy> getAll(String deptId, Date appointmentDate, String allocatedTo, String status, String estimateNo);
	String getNextAppointmentId(String deptId);
	//List<Spestedy> getAtiveAppoinments(String deptId, Date today, String allocatedTo, String status);
	List<Spestedy> getAtiveAppoinments(String deptId, String allocatedTo);
	List<Spestedy> getUnAttendedAppoinments(String deptId, String allocatedTo);
	List<Spestedy> getFailedAppoinments(String deptId, String allocatedTo);
	List<String> getVisitedAppoinments(String deptId, String allocatedTo);
	List<String> getVisitedAppoinments(String deptId, String allocatedTo, String applicationType);
	List<String> getVisitedAppoinments(String deptId, String allocatedTo, String applicationType, String applicationSubType);

	//List<String> getVisitedAppoinments(String deptId, String allocatedTo, String applicationType);
	//List<String> getVisitedAppoinments(String deptId, String allocatedTo,String applicationType, String applicationSubType);
	void makeAnAppointment(Spestedy spestedy);
	Spestedy getAppointment(String deptId, String AppointmentNo);
	List<String> getFailedReferenceNoList(String deptId, String allocatedTo);
	String getAppointmentStatus(String referenceNo, String deptId);
	void makeAnAppointment(List<Spestedy> list);
	List<String> getAppoinmentsByStatus(String deptId, String allocatedTo,
			String applicationType, String status);
	List<String> getAppoinmentsByStatus(String deptId, String allocatedTo,
			String applicationType, String applicationSubType, String status);
	List<String> getVisitedAppoinmentsForMT_SA(String deptId,
			String allocatedTo);	
	public List<String> loadApplicationRefnos(String deptId, String allocatedTo);
	List<Spestedy> getAtiveAppoinmentsforDept(String deptId);
	List<Spestedy> getAppoinmentsByStatus(String deptId,String applicationType,String status);

}
