package estimate.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.mapping.Array;

import application.ejb.ApplicationDaoRemote;

import util.common.AppStatus;
import util.common.ApplicationStatus;
import util.common.AppointmentStatus;
import util.common.Format;
import util.emSelect.EmSelector;

import estimate.model.Spestedy;
import estimate.model.SpestedyCons;
import estimate.model.SpestedyPK;

/**
 * Session Bean implementation class SpestedyDao
 */
@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless
public class SpestedyDao extends EmSelector implements SpestedyDaoRemote, SpestedyDaoLocal {
	

	
	@Resource
	private SessionContext context;
	@EJB
	ApplicationDaoRemote applicationDaoRemote;
    /**
     * Default constructor. 
     */
    public SpestedyDao() {
        // TODO Auto-generated constructor stub
    }
    
   

	@Override
	public void createSpestedy(Spestedy spestedy, String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
		
	}
	
	@Override
	public void createSpestedyAutoId(Spestedy spestedy, String webAppName) {
		//getEntityManager(webAppName);
		SpestedyPK id=new SpestedyPK();
		id.setAppointmentId(getNextAppointmentId(spestedy.getId().getDeptId(), webAppName));
		id.setDeptId(spestedy.getId().getDeptId());
		spestedy.setId(id);
		getEntityManager(webAppName).persist(spestedy);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public void makeAnAppointment(Spestedy spestedy, String webAppName) {
		//getEntityManager(webAppName);
		try{
			createSpestedyAutoId(spestedy, webAppName);
		
			applicationDaoRemote.changeStatusApplication(spestedy.getReferenceNo(), spestedy.getId().getDeptId(),AppStatus.ALLOCATED, webAppName);
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public void makeAnAppointment(List<Spestedy> list, String webAppName) {
		//getEntityManager(webAppName);
		try{
			for(int i=0; i<=list.size()-1; i++){
				createSpestedyAutoId(list.get(i), webAppName);
				
				applicationDaoRemote.changeStatusApplication(list.get(i).getReferenceNo(), list.get(i).getId().getDeptId(),AppStatus.ALLOCATED, webAppName);
			}
			}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			context.setRollbackOnly();
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spestedy> getAll(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spestedy a WHERE a.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<Spestedy> list = query.getResultList();
        return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spestedy> getAll(String deptId, Date appointmentDate, String webAppName) {
		//getEntityManager(webAppName);
		System.out.println(deptId+" "+appointmentDate+" ");
		String qryStr = "select a from Spestedy a WHERE a.id.deptId=:deptId AND a.appointmentDate=:appointmentDate";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("appointmentDate", appointmentDate);
		List<Spestedy> list = query.getResultList();
        return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spestedy> getAll(String deptId, Date appointmentDate, String allocatedTo, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spestedy a WHERE a.id.deptId=:deptId AND a.appointmentDate=:appointmentDate AND (a.allocatedTo=:allocatedTo OR a.allocatedBy=:allocatedTo)";
		System.out.println(deptId+" "+appointmentDate+" "+allocatedTo);
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("appointmentDate", appointmentDate);
		query.setParameter("allocatedTo", allocatedTo);
		//System.out.println(qryStr);
		List<Spestedy> list = query.getResultList();
        return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spestedy> getAll(String deptId, Date appointmentDate, String allocatedTo, String status, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spestedy a WHERE a.id.deptId=:deptId AND a.appointmentDate=:appointmentDate AND (a.allocatedTo=:allocatedTo OR a.allocatedBy=:allocatedTo) AND a.status=:status";
		System.out.println(deptId+" "+appointmentDate+" "+allocatedTo);
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("appointmentDate", appointmentDate); 
		query.setParameter("allocatedTo", allocatedTo);
		query.setParameter("status", status);
		//System.out.println(qryStr);
		List<Spestedy> list = query.getResultList();
        return list;
	}
	
	/*@SuppressWarnings("unchecked")
	@Override
	public List<Spestedy> getAtiveAppoinments(String deptId, Date date, String allocatedTo, String webAppName) {
		Format format=new Format();
		String[] s=format.formatDate2(date).trim().toString().split(" ");
		//System.out.println("##############"+ s[0]+" " +s[1]+" "+s[2]+" ");
		Calendar calendar=Calendar.getInstance();
		//System.out.println(calendar.getTime());
		//calendar.set(Integer.parseInt(s[0]), Integer.parseInt(s[0])-1, Integer.parseInt(s[0]));
		//calendar.set(1990, 0, 20);
		//System.out.println(calendar.getTime());
		calendar.set(Integer.parseInt(s[0]), Integer.parseInt(s[1])-1, Integer.parseInt(s[2]));
		calendar.add(Calendar.DATE,14);
		System.out.println(calendar.getTime());
		String qryStr = "select a from Spestedy a WHERE a.id.deptId=:deptId AND a.appointmentDate BETWEEN :fromDate AND :toDate  AND a.allocatedTo=:allocatedTo AND a.status=:status";
		System.out.println(deptId+" "+date+" "+allocatedTo);
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("fromDate", date);
		query.setParameter("toDate", calendar.getTime()); 
		query.setParameter("allocatedTo", allocatedTo);
		query.setParameter("status", "A");
		System.out.println(qryStr);
		List<Spestedy> list = query.getResultList();
        return list;
	}*/
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spestedy> getAtiveAppoinments(String deptId, String allocatedTo, String webAppName) {
		//getEntityManager(webAppName);
		//Format format=new Format();
		//String[] s=format.formatDate2(date).trim().toString().split(" ");
		//System.out.println("##############"+ s[0]+" " +s[1]+" "+s[2]+" ");
		Calendar calendar=Calendar.getInstance();
		Date today=calendar.getTime();
		//System.out.println(calendar.getTime());
		//calendar.set(Integer.parseInt(s[0]), Integer.parseInt(s[0])-1, Integer.parseInt(s[0]));
		//calendar.set(1990, 0, 20);
		//System.out.println(calendar.getTime());
		//calendar.set(Integer.parseInt(s[0]), Integer.parseInt(s[1])-1, Integer.parseInt(s[2]));
		calendar.add(Calendar.DATE,14);
		System.out.println(calendar.getTime());
		String qryStr = "select a from Spestedy a WHERE a.id.deptId=:deptId AND  a.allocatedTo=:allocatedTo AND a.status=:status order by a.referenceNo";
		//String qryStr = "select a from Spestedy a WHERE a.id.deptId=:deptId AND a.appointmentDate BETWEEN :fromDate AND :toDate  AND a.allocatedTo=:allocatedTo AND a.status=:status order by a.referenceNo";
		System.out.println(deptId+" "+allocatedTo+" "+today);
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		//query.setParameter("fromDate", today);
		//query.setParameter("toDate", calendar.getTime()); 
		query.setParameter("allocatedTo", allocatedTo);
		query.setParameter("status", "A");
		//System.out.println(qryStr);
		List<Spestedy> list = query.getResultList();
        return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Spestedy> getAtiveAppoinmentsforDept(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		//Format format=new Format();
		//String[] s=format.formatDate2(date).trim().toString().split(" ");
		//System.out.println("##############"+ s[0]+" " +s[1]+" "+s[2]+" ");
		Calendar calendar=Calendar.getInstance();
		Date today=calendar.getTime();
		//System.out.println(calendar.getTime());
		//calendar.set(Integer.parseInt(s[0]), Integer.parseInt(s[0])-1, Integer.parseInt(s[0]));
		//calendar.set(1990, 0, 20);
		//System.out.println(calendar.getTime());
		//calendar.set(Integer.parseInt(s[0]), Integer.parseInt(s[1])-1, Integer.parseInt(s[2]));
		//calendar.add(Calendar.DATE,14);
		System.out.println(calendar.getTime());
		String qryStr = "select a from Spestedy a WHERE a.id.deptId=:deptId AND a.status=:status order by a.referenceNo ";
		
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		//query.setParameter("fromDate", today);
		//query.setParameter("toDate", calendar.getTime()); 
		
		query.setParameter("status", "A");
		//System.out.println(qryStr);
		List<Spestedy> list = query.getResultList();
        return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Spestedy> getUnAttendedAppoinments(String deptId, String allocatedTo, String webAppName) {
		//getEntityManager(webAppName);
		//Format format=new Format();
		//String[] s=format.formatDate2(date).trim().toString().split(" ");
		//System.out.println("##############"+ s[0]+" " +s[1]+" "+s[2]+" ");
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.DATE,-1);
		Date todate=calendar.getTime();
		//System.out.println("## " +todate);
		//calendar.set(Integer.parseInt(s[0]), Integer.parseInt(s[0])-1, Integer.parseInt(s[0]));
		//calendar.set(1990, 0, 20);
		//System.out.println(calendar.getTime());
		//calendar.set(Integer.parseInt(s[0]), Integer.parseInt(s[1])-1, Integer.parseInt(s[2]));
		calendar.add(Calendar.DATE,-28);
		Date fromate=calendar.getTime();
		//System.out.println("## " +fromate);
		String qryStr = "select a from Spestedy a WHERE a.id.deptId=:deptId AND a.appointmentDate BETWEEN :fromDate AND :toDate  AND (a.allocatedTo=:allocatedTo OR a.allocatedBy=:allocatedTo) AND a.status=:status order by a.referenceNo";
		//System.out.println(deptId+" "+allocatedTo+" "+todate);
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("fromDate", fromate);
		query.setParameter("toDate", todate); 
		query.setParameter("allocatedTo", allocatedTo);
		query.setParameter("status", "A");
		//System.out.println(qryStr);
		List<Spestedy> list = query.getResultList();
        return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spestedy> getFailedAppoinments(String deptId, String allocatedTo, String webAppName) {
		//Format format=new Format();
		//String[] s=format.formatDate2(date).trim().toString().split(" ");
		//System.out.println("##############"+ s[0]+" " +s[1]+" "+s[2]+" ");
		//getEntityManager(webAppName);
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.DATE,-0);
		Date todate=calendar.getTime();
		System.out.println("##@ " +todate);
		//calendar.set(Integer.parseInt(s[0]), Integer.parseInt(s[0])-1, Integer.parseInt(s[0]));
		//calendar.set(1990, 0, 20);
		//System.out.println(calendar.getTime());
		//calendar.set(Integer.parseInt(s[0]), Integer.parseInt(s[1])-1, Integer.parseInt(s[2]));
		calendar.add(Calendar.DATE,-180);
		Date fromate=calendar.getTime();
		System.out.println("##@ " +fromate);
		String qryStr = "select a from Spestedy a WHERE a.id.deptId=:deptId AND a.appointmentDate BETWEEN :fromDate AND :toDate  AND (a.allocatedTo=:allocatedTo OR a.allocatedBy=:allocatedTo) AND a.status=:status order by a.referenceNo";
		//System.out.println(deptId+" "+allocatedTo+" "+todate);
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("fromDate", fromate);
		query.setParameter("toDate", todate); 
		query.setParameter("allocatedTo", allocatedTo);
		query.setParameter("status", AppointmentStatus.FAILED);
		//System.out.println(qryStr);
		List<Spestedy> list = query.getResultList();
        return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getFailedReferenceNoList(String deptId, String allocatedTo, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a.referenceNo from Spestedy a WHERE a.id.deptId=:deptId AND (a.allocatedTo=:allocatedTo OR a.allocatedBy=:allocatedTo) AND a.status=:status order by a.referenceNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("allocatedTo", allocatedTo);
		query.setParameter("status", AppointmentStatus.FAILED);
		//System.out.println(qryStr);
		List<String> list = query.getResultList();
        return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Spestedy> getAll(String webAppName) {
		//getEntityManager(webAppName);
		Query query = getEntityManager(webAppName).createQuery("select a from Spestedy a ");
		List<Spestedy> list = query.getResultList();
        return list;
	}

	@Override
	public void updateSpestedy(Spestedy spestedy, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(spestedy);
		
	}

	@Override
	public void removeSpestedy(Spestedy spestedy, String webAppName) {
		//getEntityManager(webAppName);
		spestedy=getEntityManager(webAppName).merge(spestedy);
		getEntityManager(webAppName).remove(spestedy);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet.");
		
	}

	@Override
	public Spestedy findById(SpestedyPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		return getEntityManager(webAppName).find(Spestedy.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String getAppointmentStatus(String referenceNo, String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr="select status from Spestedy  i where i.referenceNo=:referenceNo AND i.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("referenceNo", referenceNo);
		query.setParameter("deptId", deptId);
		List<String> list= query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String getNextAppointmentId(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String sequence=null;
    	Calendar calendar=Calendar.getInstance();
    	Format format=new Format();
    	String[] s=format.formatDate2(calendar.getTime()).trim().toString().split(" ");
		String prefix=s[0].trim().substring(0, 4)+"/"+s[1].toString()+"/";
		
		String like=prefix+"%";
		System.out.println(like);
    	String strQuery="select a.id.appointmentId from Spestedy a where a.id.deptId =:deptId AND a.id.appointmentId LIKE :like ORDER BY 1 DESC";
    	Query query=getEntityManager(webAppName).createQuery(strQuery);//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
    	query.setParameter("deptId", deptId);
    	query.setParameter("like", like);
    	List<String> list=query.getResultList();
    	//System.out.println(list);
    	if (list.size()!=0){
    		sequence=query.getResultList().get(0).toString().trim();
    		System.out.println("^ "+sequence);
    		sequence=sequence.substring(8);
    		System.out.println("* "+sequence);
    		Integer i=Integer.parseInt(sequence)+1;
    		sequence=i.toString();
    		System.out.println("# "+sequence);
    	}else{
    		sequence="001";
    		System.out.println("$ "+sequence);
    		//System.out.println(sequence.substring(4));
    	}
    	if(sequence.length()==1)
    		return prefix+"00"+sequence;
    	else if (sequence.length()==2)
    		return prefix+"0"+sequence;
    	else return prefix+sequence;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spestedy> getAll(String deptId, Date appointmentDate, String allocatedTo, String status, String referenceNo, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spestedy a WHERE a.id.deptId=:deptId AND a.appointmentDate=:appointmentDate AND (a.allocatedTo=:allocatedTo OR a.allocatedBy=:allocatedTo) AND a.status=:status AND a.referenceNo=:referenceNo";
		System.out.println(deptId+" "+appointmentDate+" "+allocatedTo);
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("appointmentDate", appointmentDate); 
		query.setParameter("allocatedTo", allocatedTo);
		query.setParameter("status", status);
		query.setParameter("referenceNo", referenceNo);
		//System.out.println(qryStr);
		List<Spestedy> list = query.getResultList();
        return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getVisitedAppoinments(String deptId, String allocatedTo, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a.referenceNo from Spestedy a WHERE a.id.deptId=:deptId AND (a.allocatedTo=:allocatedTo OR a.allocatedBy=:allocatedTo) AND a.status=:status order by a.referenceNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("allocatedTo", allocatedTo);
		query.setParameter("status", AppointmentStatus.VISITED);
		//System.out.println(qryStr);
		List<String> list = query.getResultList();
        return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getVisitedAppoinmentsForMT_SA(String deptId, String allocatedTo, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a.referenceNo from Spestedy a , Application b WHERE  a.referenceNo=b.applicationNo AND (b.applicationType ='MT' OR b.applicationType ='SA' OR b.applicationType ='PS' OR b.applicationType ='LS' OR b.applicationType ='BD' OR b.applicationType='CC' OR b.applicationType='CS' OR b.applicationType='CO' OR b.applicationType='TR' OR b.applicationType='MR' OR b.applicationType='PF' OR b.applicationType='MF' OR b.applicationType='DB'  OR b.applicationType='RM') AND a.id.deptId=:deptId AND (a.allocatedTo=:allocatedTo OR a.allocatedBy=:allocatedTo) AND a.status=:status order by a.referenceNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		//query.setParameter("applicationType", applicationType);
		query.setParameter("deptId", deptId);
		query.setParameter("allocatedTo", allocatedTo);
		query.setParameter("status", AppointmentStatus.VISITED);
		//System.out.println(qryStr);
		List<String> list = query.getResultList();
        return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getVisitedAppoinments(String deptId, String allocatedTo, String applicationType, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a.referenceNo from Spestedy a , Application b WHERE  a.referenceNo=b.applicationNo AND b.applicationType=:applicationType AND a.id.deptId=:deptId AND (a.allocatedTo=:allocatedTo OR a.allocatedBy=:allocatedTo) AND a.status=:status order by a.referenceNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationType", applicationType);
		query.setParameter("deptId", deptId);
		query.setParameter("allocatedTo", allocatedTo);
		query.setParameter("status", AppointmentStatus.VISITED);
		//System.out.println(qryStr);
		List<String> list = query.getResultList();
        return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getVisitedAppoinments(String deptId, String allocatedTo, String applicationType , String applicationSubType, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a.referenceNo from Spestedy a , Application b WHERE  a.referenceNo=b.applicationNo AND b.applicationType=:applicationType AND b.applicationSubType=:applicationSubType AND a.id.deptId=:deptId AND (a.allocatedTo=:allocatedTo OR a.allocatedBy=:allocatedTo) AND a.status=:status order by a.referenceNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationType", applicationType);
		query.setParameter("applicationSubType", applicationSubType);
		query.setParameter("deptId", deptId);
		query.setParameter("allocatedTo", allocatedTo);
		query.setParameter("status", AppointmentStatus.VISITED);
		//System.out.println(qryStr);
		List<String> list = query.getResultList();
        return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAppoinmentsByStatus(String deptId, String allocatedTo, String applicationType, String status, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a.referenceNo from Spestedy a , Application b WHERE  a.referenceNo=b.applicationNo AND b.applicationType=:applicationType AND a.id.deptId=:deptId AND (a.allocatedTo=:allocatedTo OR a.allocatedBy=:allocatedTo) AND a.status=:status order by a.referenceNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationType", applicationType);
		query.setParameter("deptId", deptId);
		query.setParameter("allocatedTo", allocatedTo);
		query.setParameter("status", status);
		//System.out.println(qryStr);
		List<String> list = query.getResultList();
        return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAppoinmentsByStatus(String deptId, String allocatedTo, String applicationType , String applicationSubType, String status, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a.referenceNo from Spestedy a , Application b WHERE  a.referenceNo=b.applicationNo AND b.applicationType=:applicationType AND b.applicationSubType=:applicationSubType AND a.id.deptId=:deptId AND (a.allocatedTo=:allocatedTo OR a.allocatedBy=:allocatedTo) AND a.status=:status order by a.referenceNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationType", applicationType);
		query.setParameter("applicationSubType", applicationSubType);
		query.setParameter("deptId", deptId);
		query.setParameter("allocatedTo", allocatedTo);
		query.setParameter("status", status);
		//System.out.println(qryStr);
		List<String> list = query.getResultList();
        return list;
	}
	
	
	@Override
	public void changeStatusAppointments(String applicationNo, String deptId, String status, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "UPDATE Spestedy g SET g.status=:status WHERE TRIM(g.referenceNo)= :referenceNo AND g.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("status", status);
		query.setParameter("referenceNo", applicationNo.trim());
		query.setParameter("deptId", deptId);
		query.executeUpdate();
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Spestedy getAppointment(String deptId, String referenceNo, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spestedy a WHERE a.id.deptId=:deptId AND a.referenceNo=:referenceNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		query.setParameter("referenceNo", referenceNo.trim());
		List<Spestedy> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}



	@Override
	public List<String> loadApplicationRefnos(String userId, String costCenter,
			String webAppName) throws PersistenceException{
		String qryStr = "select a.referenceNo from Spestedy a WHERE a.id.deptId=:deptId AND a.allocatedTo=:userId and status=:status";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", costCenter);
		query.setParameter("userId", userId);
		query.setParameter("status", AppointmentStatus.ACTIVE);
		List<String> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else {
        	return list;
        }
		
	}
	



	@Override
	public int updateSpestedyStatus(String status, String referenceNo,
			String webAppName) {
		int updateStatus = 0;
		
		String   qryStr1 ="UPDATE Spestedy s SET s.status =:status WHERE s.referenceNo =:referenceNo";
		Query query1 = getEntityManager(webAppName).createQuery(qryStr1);
		query1.setParameter("status", status);
		query1.setParameter("referenceNo", referenceNo);
		
		updateStatus=query1.executeUpdate();
		
		return updateStatus;
	}
	
	@Override
	public List<Spestedy> getAppoinmentsByStatus(String deptId,	String applicationType, String status, String webAppName) {
		// TODO Auto-generated method stub
		String qryStr = "select a from Spestedy a , Application b WHERE  a.referenceNo=b.applicationNo AND b.applicationType=:applicationType AND a.id.deptId=:deptId AND a.status=:status order by a.referenceNo";
		System.out.println(qryStr);
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationType", applicationType);
		query.setParameter("deptId", deptId);
		
		query.setParameter("status", status);
		System.out.println(qryStr);
		List<Spestedy> list = query.getResultList();
        return list;
	}

	

}
