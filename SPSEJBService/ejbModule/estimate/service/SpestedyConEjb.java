package estimate.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpestedyConDaoRemote;
import estimate.ejb.SpestedyConDaoRemote;
import estimate.model.SpestedyCons;
import estimate.model.SpestedyConsPK;

public class SpestedyConEjb implements SpestedyConDaoRemote {
	private Context context;
	private SpestedyConDaoRemote dao; 
	public SpestedyConEjb() {
		super();
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
	public void createSpestedyCon(SpestedyCons SpestedyCon, String webAppName) {
		dao.createSpestedyCon(SpestedyCon, webAppName);
		
	}
	
	@Override
	public void createSpestedyConAutoId(SpestedyCons SpestedyCon, String webAppName) {
		dao.createSpestedyConAutoId(SpestedyCon, webAppName);
		
	}

	@Override
	public void updateSpestedyCon(SpestedyCons SpestedyCon, String webAppName) {
		dao.updateSpestedyCon(SpestedyCon, webAppName);
		
	}

	@Override
	public void removeSpestedyCon(SpestedyCons SpestedyCon, String webAppName) {
		dao.removeSpestedyCon(SpestedyCon, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public SpestedyCons findById(SpestedyConsPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}

	

	@Override
	public List<SpestedyCons> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}
	
	@Override
	public List<SpestedyCons> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}
	
	@Override
	public String getNextAppointmentId(String deptId, String webAppName) {
		return dao.getNextAppointmentId(deptId, webAppName);
	}
	
	@Override
	public List<SpestedyCons> getAll(String deptId, Date appointmentDate, String webAppName) {
		return dao.getAll(deptId, appointmentDate, webAppName);
	}

	@Override
	public List<SpestedyCons> getAll(String deptId, Date appointmentDate, String userId, String webAppName) {
		return dao.getAll(deptId, appointmentDate, userId, webAppName);
	}
	
	@Override
	public List<SpestedyCons> getAll(String deptId, Date appointmentDate, String allocatedTo, String status, String webAppName) {
		return dao.getAll(deptId, appointmentDate, allocatedTo, status, webAppName);
	}
	
	/*@Override
	public List<SpestedyCon> getAtiveAppoinments(String deptId, Date today, String allocatedTo, String status, String webAppName) {
		return dao.getAtiveAppoinments(deptId, today, allocatedTo, status, webAppName);
	}*/
	
	@Override
	public List<SpestedyCons> getAtiveAppoinments(String deptId, String allocatedTo, String webAppName) {
		return dao.getAtiveAppoinments(deptId, allocatedTo, webAppName);
	}
	
	@Override
	public List<SpestedyCons> getUnAttendedAppoinments(String deptId, String allocatedTo, String webAppName) {
		return dao.getUnAttendedAppoinments(deptId, allocatedTo, webAppName);
	}
	
	@Override
	public List<SpestedyCons> getFailedAppoinments(String deptId, String allocatedTo, String webAppName) {
		return dao.getFailedAppoinments(deptId, allocatedTo, webAppName);
	}
	
	@Override
	public List<String> getVisitedAppoinments(String deptId, String allocatedTo, String webAppName) {
		return dao.getVisitedAppoinments(deptId, allocatedTo, webAppName);
	}

	@Override
	public void makeAnAppointment(SpestedyCons SpestedyCon, String webAppName) {
		dao.makeAnAppointment(SpestedyCon, webAppName);
		
	}
	
	@Override
	public List<SpestedyCons> getAll(String deptId, Date appointmentDate, String allocatedTo, String status, String estimateNo, String webAppName) {
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
		SpestedyConEjb ejb=new SpestedyConEjb();
		//Date date=new Date();
		//Calendar calendar=Calendar.getInstance();
		//calendar.add(Calendar.DATE,4);
		System.out.println("hi: "+ ejb);
		
		System.out.println(ejb.getAppointmentByEstimateNo("430.00/RE/13/0167", "430.25/RE/13/0167/1","430.25", "R2"));
		//System.out.println(ejb.getVisitedAppoinments("514.20", "ES123", "Rfff2").size());
		//System.out.println(ejb.getVisitedAppoinments("514.20", "ES123", "NC", "Rfff2").size());
		//System.out.println(ejb.getVisitedAppoinmentsForMT_SA("514.20",  "ES123", "Rfff2"));
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
		//List<SpestedyCon> list= ejb.getAtiveAppoinments("510.20", "es");
		//System.out.println(list);
		//List<SpestedyCon> list2= ejb.getUnAttendedAppoinments("510.20", "es");
		//System.out.println(list2);
		//List<SpestedyCon> list3= ejb.getFailedAppoinments("510.20", "es");
		//System.out.println(list3);
		//calendar.g 
		//list.get(0).getAppointmentDate();
		/**SpestedyCons SpestedyCon=new SpestedyCons();
		SpestedyConsPK id=new SpestedyConsPK();
		id.setDeptId("510.20"); 
		Date dt=calendar.getTime();
		SpestedyCon.setId(id);         
                                                             
		SpestedyCon.setAppointmentDate(dt);

		SpestedyCon.setDescription("New appointment"); 
		SpestedyCon.setTimeSession("Morning");
		SpestedyCon.setAllocatedBy("es");  
		SpestedyCon.setAllocatedTo("es");    
		SpestedyCon.setAllocatedTime(null); 
		SpestedyCon.setAppoinmentType("ES.VISIT"); 
		SpestedyCon.setStatus("q");
		SpestedyCon.setReferenceNo("510.20/SNL/2010/1007"); 
		//ejb.makeAnAppointment(SpestedyCon);

		*/

	}

	@Override
	public String toString() {
		return "SpestedyConEjb [context=" + context + ", dao=" + dao + "]";
	}

	@Override
	public SpestedyCons getAppointment(String deptId, String AppointmentNo, String webAppName) {
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
	public List<SpestedyCons> getAppointmentByUser(String applicationNo,String westimateNo, String deptId,String loginUser,
			String webAppName) {
		return dao.getAppointmentByUser(applicationNo,westimateNo, deptId,loginUser, webAppName);
	}

	@Override
	public void makeAnAppointment(List<SpestedyCons> list, String webAppName) {
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
	public List<String> loadApplicationRefnos(String userId, String costCenter,String status,
			String webAppName) {
		return dao.loadApplicationRefnos(userId, costCenter, status,webAppName);
	}

	
	@Override
	public int updateSpestedyConStatus(String status, String referenceNo,
			String webAppName) {
		return dao.updateSpestedyConStatus(status,referenceNo, webAppName);
	}

	@Override
	public List<SpestedyCons> getAppointmentForReference(String deptId,
			String referenceNo,  String webAppName) {
		return dao.getAppointmentForReference(deptId,referenceNo, webAppName);
	}

	@Override
	public void changeStatusAppointmentsByUser(String applicationNo,
			String deptId, String status, String allocatedTo, String webAppName) {
		dao.changeStatusAppointmentsByUser(applicationNo, deptId,  status,  allocatedTo,  webAppName);
		
	}

	@Override
	public List<SpestedyCons> getAtiveAppoinmentsforDept(String deptId,
			String webAppName) {
		// TODO Auto-generated method stub
		return dao.getAtiveAppoinmentsforDept(deptId,webAppName);
	}

	@Override
	public void changeStatusAppointByWorkEstimate(String applicationNo,
			String deptId, String status, String workestimateNo,
			String webAppName) {
		dao.changeStatusAppointByWorkEstimate(applicationNo,deptId,status,workestimateNo,webAppName);
		
	}

	@Override
	public void updateWorkEstimateNumber(String applicationNo, String deptId,
			String status, String workestimateNo, String webAppName) {
		dao.updateWorkEstimateNumber(applicationNo,deptId,status,workestimateNo,webAppName);
		
	}

	@Override
	public void addAppointment(SpestedyCons spestedy, String webAppName) {
		dao.addAppointment(spestedy, webAppName);
		
	}

	@Override
	public SpestedyCons getAppointmentByEstimateNo(String referenceNo,
			String westimateNo, String deptId, String webAppName) {
		// TODO Auto-generated method stub
		return dao.getAppointmentByEstimateNo(referenceNo,westimateNo, deptId,webAppName);
	}

	

	

	

	

	

	

	

	

	

	

	

	

}
