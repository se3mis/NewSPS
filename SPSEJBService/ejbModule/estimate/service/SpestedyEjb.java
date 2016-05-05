package estimate.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpestedyDaoRemote;
import estimate.model.Spestedy;
import estimate.model.SpestedyPK;

public class SpestedyEjb implements SpestedyDaoRemote {
	private Context context;
	private SpestedyDaoRemote dao; 
	public SpestedyEjb() {
		super();
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
	public void createSpestedy(Spestedy spestedy, String webAppName) {
		dao.createSpestedy(spestedy, webAppName);
		
	}
	
	@Override
	public void createSpestedyAutoId(Spestedy spestedy, String webAppName) {
		dao.createSpestedyAutoId(spestedy, webAppName);
		
	}

	@Override
	public void updateSpestedy(Spestedy spestedy, String webAppName) {
		dao.updateSpestedy(spestedy, webAppName);
		
	}

	@Override
	public void removeSpestedy(Spestedy spestedy, String webAppName) {
		dao.removeSpestedy(spestedy, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Spestedy findById(SpestedyPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}

	

	@Override
	public List<Spestedy> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}
	
	@Override
	public List<Spestedy> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}
	
	@Override
	public String getNextAppointmentId(String deptId, String webAppName) {
		return dao.getNextAppointmentId(deptId, webAppName);
	}
	
	@Override
	public List<Spestedy> getAll(String deptId, Date appointmentDate, String webAppName) {
		return dao.getAll(deptId, appointmentDate, webAppName);
	}

	@Override
	public List<Spestedy> getAll(String deptId, Date appointmentDate, String userId, String webAppName) {
		return dao.getAll(deptId, appointmentDate, userId, webAppName);
	}
	
	@Override
	public List<Spestedy> getAll(String deptId, Date appointmentDate, String allocatedTo, String status, String webAppName) {
		return dao.getAll(deptId, appointmentDate, allocatedTo, status, webAppName);
	}
	
	/*@Override
	public List<Spestedy> getAtiveAppoinments(String deptId, Date today, String allocatedTo, String status, String webAppName) {
		return dao.getAtiveAppoinments(deptId, today, allocatedTo, status, webAppName);
	}*/
	
	@Override
	public List<Spestedy> getAtiveAppoinments(String deptId, String allocatedTo, String webAppName) {
		return dao.getAtiveAppoinments(deptId, allocatedTo, webAppName);
	}
	
	@Override
	public List<Spestedy> getUnAttendedAppoinments(String deptId, String allocatedTo, String webAppName) {
		return dao.getUnAttendedAppoinments(deptId, allocatedTo, webAppName);
	}
	
	@Override
	public List<Spestedy> getFailedAppoinments(String deptId, String allocatedTo, String webAppName) {
		return dao.getFailedAppoinments(deptId, allocatedTo, webAppName);
	}
	
	@Override
	public List<String> getVisitedAppoinments(String deptId, String allocatedTo, String webAppName) {
		return dao.getVisitedAppoinments(deptId, allocatedTo, webAppName);
	}

	@Override
	public void makeAnAppointment(Spestedy spestedy, String webAppName) {
		dao.makeAnAppointment(spestedy, webAppName);
		
	}
	
	@Override
	public List<Spestedy> getAll(String deptId, Date appointmentDate, String allocatedTo, String status, String estimateNo, String webAppName) {
		return dao.getAll(deptId, appointmentDate, allocatedTo, status, estimateNo, webAppName);
	}

	@Override
	public void changeStatusAppointments(String applicationNo, String deptId, String status, String webAppName) {
		dao.changeStatusAppointments(applicationNo, deptId, status, webAppName);
		
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpestedyEjb ejb=new SpestedyEjb();
		Date date=new Date();
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.DATE,4);
		System.out.println(calendar.getTime());
		
		System.out.println(ejb.getAtiveAppoinments("422.30", "ES42230", "R1"));
		System.out.println(ejb.getVisitedAppoinments("514.20", "ES123", "Rfff2").size());
		System.out.println(ejb.getVisitedAppoinments("514.20", "ES123", "NC", "Rfff2").size());
		System.out.println(ejb.getVisitedAppoinmentsForMT_SA("514.20",  "ES123", "Rfff2"));
		/*System.out.println(ejb.getAppointmentStatus("422.10/ECR/2011/0001", "422.10", "R1"));
		
		System.out.println(ejb.getAll("423.10", calendar.getTime(), "ES42310", "A", "R1"));
		System.out.println(ejb.getAppointmentStatus("514.20/ENC/2011/0005", "514.20", "SMCTesting"));
		System.out.println(ejb.getFailedAppoinments("514.20", "ES123", "SMCTesting"));
		System.out.println(ejb.getVisitedAppoinments("443.20", "es123", "NL"));
		System.out.println(ejb.getVisitedAppoinments("443.20", "es123", "NL", " "));
		System.out.println(ejb.getNextAppointmentId("443.20", "SMCTesting"));
		ejb.changeStatusAppointments("443.20/SNL/2011/0001", "443.20", "E", "SMCTesting");
		*/
		//System.out.println(ejb.getAll("510.20", calendar.getTime()));
		//System.out.println(ejb.getAll("510.20", calendar.getTime(), "es"));
		//System.out.println(ejb.getAll("510.20", calendar.getTime(), "es", "F"));
		//List<Spestedy> list= ejb.getAtiveAppoinments("510.20", "es");
		//System.out.println(list);
		//List<Spestedy> list2= ejb.getUnAttendedAppoinments("510.20", "es");
		//System.out.println(list2);
		//List<Spestedy> list3= ejb.getFailedAppoinments("510.20", "es");
		//System.out.println(list3);
		//calendar.g 
		//list.get(0).getAppointmentDate();
		Spestedy spestedy=new Spestedy();
		SpestedyPK id=new SpestedyPK();
		id.setDeptId("510.20"); 
		Date dt=calendar.getTime();
		spestedy.setId(id);         
                                                             
		spestedy.setAppointmentDate(dt);

		spestedy.setDescription("New appointment"); 
		spestedy.setTimeSession("Morning");
		spestedy.setAllocatedBy("es");  
		spestedy.setAllocatedTo("es");    
		spestedy.setAllocatedTime(null); 
		spestedy.setAppoinmentType("ES.VISIT"); 
		spestedy.setStatus("q");
		spestedy.setReferenceNo("510.20/SNL/2010/1007"); 
		//ejb.makeAnAppointment(spestedy);

		

	}

	@Override
	public String toString() {
		return "SpestedyEjb [context=" + context + ", dao=" + dao + "]";
	}

	@Override
	public Spestedy getAppointment(String deptId, String AppointmentNo, String webAppName) {
		return dao.getAppointment(deptId, AppointmentNo, webAppName);
	}

	@Override
	public List<String> getVisitedAppoinments(String deptId, String allocatedTo, String applicationType, String webAppName) {
		return dao.getVisitedAppoinments(deptId, allocatedTo, applicationType);
	}

	@Override
	public List<String> getVisitedAppoinments(String deptId, String allocatedTo, String applicationType, String applicationSubType, String webAppName) {
		return dao.getVisitedAppoinments(deptId, allocatedTo, applicationType, applicationSubType);
	}

	@Override
	public List<String> getFailedReferenceNoList(String deptId,
			String allocatedTo, String webAppName) {
		return dao.getFailedReferenceNoList(deptId, allocatedTo, webAppName);
	}

	@Override
	public String getAppointmentStatus(String applicationNo, String deptId,
			String webAppName) {
		return dao.getAppointmentStatus(applicationNo, deptId, webAppName);
	}

	@Override
	public void makeAnAppointment(List<Spestedy> list, String webAppName) {
		dao.makeAnAppointment(list, webAppName);
		
	}

	@Override
	public List<String> getAppoinmentsByStatus(String deptId,
			String allocatedTo, String applicationType, String status,
			String webAppName) {
		return dao.getAppoinmentsByStatus(deptId, allocatedTo, applicationType, status, webAppName);
	}

	@Override
	public List<String> getAppoinmentsByStatus(String deptId,
			String allocatedTo, String applicationType,
			String applicationSubType, String status, String webAppName) {
		return dao.getAppoinmentsByStatus(deptId, allocatedTo, applicationType, applicationSubType, status, webAppName);
	}

	@Override
	public List<String> getVisitedAppoinmentsForMT_SA(String deptId, String allocatedTo, String webAppName) {
		return dao.getVisitedAppoinmentsForMT_SA(deptId, allocatedTo, webAppName);
	}

	@Override
	public List<String> loadApplicationRefnos(String userId, String costCenter,
			String webAppName) {
		return dao.loadApplicationRefnos(userId, costCenter, webAppName);
	}

	
	@Override
	public int updateSpestedyStatus(String status, String referenceNo,
			String webAppName) {
		return dao.updateSpestedyStatus(status,referenceNo, webAppName);
	}

	@Override
	public List<Spestedy> getAtiveAppoinmentsforDept(String deptId,
			String webAppName) {
		// TODO Auto-generated method stub
		return dao.getAtiveAppoinmentsforDept(deptId,webAppName);
	}

	@Override
	public List<Spestedy> getAppoinmentsByStatus(String deptId,
			String applicationType, String status, String webAppName) {
		return dao.getAppoinmentsByStatus(deptId, applicationType, status, webAppName);

	}

	

	

	

	

	

	

	

	

	

	

}
