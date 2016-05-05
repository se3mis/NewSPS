package estimate.service;

import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpestedyConDaoRemote;
import estimate.ejb.SpestedyDaoRemote;
import estimate.model.Spestedy;
import estimate.model.SpestedyCons;
import estimate.model.SpestedyConsPK;
import estimate.model.SpestedyPK;

public class SpestedyConEjb implements SpestedyConEjbI {

	

	private Context context;
	private SpestedyConDaoRemote dao; 
	private String region=null;
	
	public SpestedyConEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}
	
	private SpestedyConDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpestedyConDaoRemote dao = (SpestedyConDaoRemote) context.lookup("SpestedyConDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void createSpestedyCon(SpestedyCons spestedy) {
		dao.createSpestedyCon(spestedy, region);
		
	}
	
	@Override
	public void createSpestedyConAutoId(SpestedyCons spestedy) {
		dao.createSpestedyConAutoId(spestedy, region);
		
	}

	@Override
	public void updateSpestedyCon(SpestedyCons spestedy) {
		dao.updateSpestedyCon(spestedy, region);
		
	}

	@Override
	public void removeSpestedyCon(SpestedyCons spestedy) {
		dao.removeSpestedyCon(spestedy, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public SpestedyCons findById(SpestedyConsPK id) throws PersistenceException {
		return dao.findById(id, region);
	}

	

	@Override
	public List<SpestedyCons> getAll() {
		return dao.getAll(region);
	}
	
	@Override
	public List<SpestedyCons> getAll(String deptId) {
		return dao.getAll(deptId, region);
	}
	
	@Override
	public List<SpestedyCons> getAll(String deptId, Date appointmentDate) {
		return dao.getAll(deptId, appointmentDate, region);
	}

	@Override
	public List<SpestedyCons> getAll(String deptId, Date appointmentDate, String userId) {
		return dao.getAll(deptId, appointmentDate, userId, region);
	}
	
	@Override
	public List<SpestedyCons> getAll(String deptId, Date appointmentDate, String allocatedTo, String status) {
		return dao.getAll(deptId, appointmentDate, allocatedTo, status , region);
	}
	
	@Override
	public List<SpestedyCons> getAll(String deptId, Date appointmentDate, String allocatedTo, String status, String estimateNo) {
		return dao.getAll(deptId, appointmentDate, allocatedTo, status, estimateNo, region);
	}

	@Override
	public List<SpestedyCons> getAtiveAppoinments(String deptId, String allocatedTo) {
		return dao.getAtiveAppoinments(deptId, allocatedTo, region);
	}

	@Override
	public List<SpestedyCons> getUnAttendedAppoinments(String deptId, String allocatedTo) {
		return dao.getUnAttendedAppoinments(deptId, allocatedTo, region);
	}

	@Override
	public List<SpestedyCons> getFailedAppoinments(String deptId, String allocatedTo) {
		return dao.getFailedAppoinments(deptId, allocatedTo, region);
	}
	
	@Override
	public void changeStatusAppointments(String applicationNo, String deptId, String status) {
		dao.changeStatusAppointments(applicationNo, deptId, status, region);
		
	}
	
	@Override
	public String getNextAppointmentId(String deptId) {
		return dao.getNextAppointmentId(deptId, region);
	}
	
	@Override
	public void makeAnAppointment(SpestedyCons spestedy) {
		dao.makeAnAppointment(spestedy, region);
		
	}
	
	@Override
	public SpestedyCons getAppointment(String deptId, String AppointmentNo) {
		return dao.getAppointment(deptId, AppointmentNo, region);
	}
	

	
	

	@Override
	public List<String> getVisitedAppoinments(String deptId, String allocatedTo, String applicationType) {
		return dao.getVisitedAppoinments(deptId, allocatedTo, applicationType, region);
	}

	@Override
	public List<String> getVisitedAppoinments(String deptId, String allocatedTo, String applicationType, String applicationSubType) {
		return dao.getVisitedAppoinments(deptId, allocatedTo, applicationType, applicationSubType, region);
	}

	@Override
	public List<String> getFailedReferenceNoList(String deptId,
			String allocatedTo) {
		return dao.getFailedReferenceNoList(deptId, allocatedTo, region);
	}
	
	
	@Override
	public List<SpestedyCons> getAppointmentByUser(String referenceNo,String estimateNo, String deptId,String loginUser) {
		return dao.getAppointmentByUser(referenceNo,estimateNo, deptId,loginUser, region);
	}
	
	@Override
	public SpestedyCons getAppointmentByEstimateNo(String referenceNo,String estimateNo, String deptId) {
		return dao.getAppointmentByEstimateNo(referenceNo,estimateNo, deptId, region);
	}
	
	@Override
	public void makeAnAppointment(List<SpestedyCons> list) {
		dao.makeAnAppointment(list, region);
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpestedyConEjb ejb=new SpestedyConEjb("R2");
		/**System.out.println(ejb.getNextAppointmentId("510.20"));
		
		System.out.println(ejb.getVisitedAppoinments("443.20", "es"));
		System.out.println(ejb.getVisitedAppoinments("443.20", "es", "TC"));
		System.out.println(ejb.getVisitedAppoinments("510.20", "es", "TC", "T1"));
		System.out.println(ejb.getVisitedAppoinments("514.20", "ES123").size());
		System.out.println(ejb.getVisitedAppoinments("514.20", "ES123", "TC"));
		System.out.println(ejb.getVisitedAppoinments("514.20", "ES123", "TC", "T1"));
		System.out.println(ejb.getVisitedAppoinments("514.20", "ES123", "MT"));
		System.out.println(ejb.getVisitedAppoinmentsForMT_SA("514.20", "ES123"));
		System.out.println(ejb.getVisitedAppoinments("514.20", "ES123", "TC", "T1"));
*/
		System.out.println("" + ejb.getAppointmentByEstimateNo("430.00/RE/13/0167", "430.25/RE/13/0167/1", "430.25"));

	}

	@Override
	public List<String> getAppoinmentsByStatus(String deptId,
			String allocatedTo, String applicationType, String status) {
		return dao.getAppoinmentsByStatus(deptId, allocatedTo, applicationType, status, region);
	}

	@Override
	public List<String> getAppoinmentsByStatus(String deptId,
			String allocatedTo, String applicationType,
			String applicationSubType, String status) {
		return dao.getAppoinmentsByStatus(deptId, allocatedTo, applicationType, applicationSubType, status, region);
	}

	@Override
	public List<String> getVisitedAppoinmentsForMT_SA(String deptId, String allocatedTo) {
		return dao.getVisitedAppoinmentsForMT_SA(deptId, allocatedTo, region);
	}

	@Override
	public List<String> loadApplicationRefnos(String allocatedTo,String deptId, String status) {
		return dao.loadApplicationRefnos(allocatedTo,deptId,status,region);
	}

	@Override
	public List<String> getVisitedAppoinments(String deptId, String allocatedTo) {
		return dao.getVisitedAppoinments(deptId,allocatedTo,region);
	}

	@Override
	public List<SpestedyCons> getAppointmentForReference(String deptId,
			String referenceNo, String webAppName) {
		return dao.getAppointmentForReference(deptId,referenceNo, region);
	}

	@Override
	public void changeStatusAppointmentsByUser(String applicationNo,
			String deptId, String status, String allocatedTo) {
		dao.changeStatusAppointmentsByUser(applicationNo, deptId, status,allocatedTo, region);
		
	}

	@Override
	public List<SpestedyCons> getAtiveAppoinmentsforDept(String deptId,
			String webAppName) {
		// TODO Auto-generated method stub
		return dao.getAtiveAppoinmentsforDept(deptId,region);
	}

	
	@Override
	public void changeStatusAppointByWorkEstimate(String applicationNo,
			String deptId, String status, String workestimateNo) {
		dao.changeStatusAppointByWorkEstimate(applicationNo,deptId,status,workestimateNo,region);
		
	}

	@Override
	public void updateWorkEstimateNumber(String applicationNo, String deptId,
			String status, String workestimateNo) {
		dao.updateWorkEstimateNumber(applicationNo,deptId,status,workestimateNo,region);
		
	}
	@Override
	public void addAppointment(SpestedyCons spestedy) {
		dao.addAppointment(spestedy, region);
		
	}
	
	

}
