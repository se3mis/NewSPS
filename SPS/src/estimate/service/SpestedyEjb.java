package estimate.service;

import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpestedyDaoRemote;
import estimate.model.Spestedy;
import estimate.model.SpestedyPK;

public class SpestedyEjb implements SpestedyEjbI {

	

	private Context context;
	private SpestedyDaoRemote dao; 
	private String region=null;
	
	public SpestedyEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}
	
	private SpestedyDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpestedyDaoRemote dao = (SpestedyDaoRemote) context.lookup("SpestedyDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void createSpestedy(Spestedy spestedy) {
		dao.createSpestedy(spestedy, region);
		
	}
	
	@Override
	public void createSpestedyAutoId(Spestedy spestedy) {
		dao.createSpestedyAutoId(spestedy, region);
		
	}

	@Override
	public void updateSpestedy(Spestedy spestedy) {
		dao.updateSpestedy(spestedy, region);
		
	}

	@Override
	public void removeSpestedy(Spestedy spestedy) {
		dao.removeSpestedy(spestedy, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Spestedy findById(SpestedyPK id) throws PersistenceException {
		return dao.findById(id, region);
	}

	

	@Override
	public List<Spestedy> getAll() {
		return dao.getAll(region);
	}
	
	@Override
	public List<Spestedy> getAll(String deptId) {
		return dao.getAll(deptId, region);
	}
	
	@Override
	public List<Spestedy> getAll(String deptId, Date appointmentDate) {
		return dao.getAll(deptId, appointmentDate, region);
	}

	@Override
	public List<Spestedy> getAll(String deptId, Date appointmentDate, String userId) {
		return dao.getAll(deptId, appointmentDate, userId, region);
	}
	
	@Override
	public List<Spestedy> getAll(String deptId, Date appointmentDate, String allocatedTo, String status) {
		return dao.getAll(deptId, appointmentDate, allocatedTo, status , region);
	}
	
	@Override
	public List<Spestedy> getAll(String deptId, Date appointmentDate, String allocatedTo, String status, String estimateNo) {
		return dao.getAll(deptId, appointmentDate, allocatedTo, status, estimateNo, region);
	}

	@Override
	public List<Spestedy> getAtiveAppoinments(String deptId, String allocatedTo) {
		return dao.getAtiveAppoinments(deptId, allocatedTo, region);
	}

	@Override
	public List<Spestedy> getUnAttendedAppoinments(String deptId, String allocatedTo) {
		return dao.getUnAttendedAppoinments(deptId, allocatedTo, region);
	}

	@Override
	public List<Spestedy> getFailedAppoinments(String deptId, String allocatedTo) {
		return dao.getFailedAppoinments(deptId, allocatedTo, region);
	}
	
	@Override
	public List<String> getVisitedAppoinments(String deptId, String allocatedTo) {
		return dao.getVisitedAppoinments(deptId, allocatedTo, region); 
	}
	
	@Override
	public String getNextAppointmentId(String deptId) {
		return dao.getNextAppointmentId(deptId, region);
	}
	
	@Override
	public void makeAnAppointment(Spestedy spestedy) {
		dao.makeAnAppointment(spestedy, region);
		
	}
	
	@Override
	public Spestedy getAppointment(String deptId, String AppointmentNo) {
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
	public String getAppointmentStatus(String referenceNo, String deptId) {
		return dao.getAppointmentStatus(referenceNo, deptId, region);
	}
	
	@Override
	public void makeAnAppointment(List<Spestedy> list) {
		dao.makeAnAppointment(list, region);
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpestedyEjb ejb=new SpestedyEjb("region");
		//System.out.println(ejb.getNextAppointmentId("510.20"));
		//System.out.println(ejb.getVisitedAppoinments("443.20", "es"));
		//System.out.println(ejb.getVisitedAppoinments("443.20", "es", "TC"));
		//System.out.println(ejb.getVisitedAppoinments("510.20", "es", "TC", "T1"));
		//System.out.println(ejb.getVisitedAppoinments("514.20", "ES123").size());
		//System.out.println(ejb.getVisitedAppoinments("514.20", "ES123", "TC"));
		//System.out.println(ejb.getVisitedAppoinments("514.20", "ES123", "TC", "T1"));
		//System.out.println(ejb.getVisitedAppoinments("514.20", "ES123", "MT"));
		//System.out.println(ejb.getVisitedAppoinmentsForMT_SA("514.20", "ES123"));
		
		System.out.println(ejb.getAtiveAppoinmentsforDept("501.00"));
		//System.out.println(ejb.getAppoinmentsByStatus("501.00","","BS",));

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
	public List<String> loadApplicationRefnos(String deptId, String allocatedTo) {
		return dao.loadApplicationRefnos(deptId,allocatedTo,region);
	}

	@Override
	public List<Spestedy> getAtiveAppoinmentsforDept(String deptId) {
		// TODO Auto-generated method stub
		return dao.getAtiveAppoinmentsforDept(deptId,region);
	}

	@Override
	public List<Spestedy> getAppoinmentsByStatus(String deptId,
			String applicationType, String status) {
		return dao.getAppoinmentsByStatus(deptId,applicationType, status, region);

	}
	

	
		

}
