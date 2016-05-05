package application.ejb;

import java.util.Date;
import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import estimate.model.ApplicationDisplay;

import util.common.PIVStatus;
import util.emSelect.EmSelector;

import application.model.Application;
import application.model.ApplicationPK;

/**
 * Session Bean implementation class ApplicationDao
 */
@Stateless
public class ApplicationDao extends EmSelector implements ApplicationDaoRemote, ApplicationDaoLocal {
	


    /**
     * Default constructor. 
     */
    public ApplicationDao() {
        // TODO Auto-generated constructor stub
    }
    
   

	@SuppressWarnings("unchecked")
	@Override
	public Application findByAppId(ApplicationPK id, String webAppName) {
		System.out.println("#############################################6");
		try{
			if (id.getDeptId().equals("541.00") || id.getDeptId().equals("542.00") || id.getDeptId().equals("547.00") || id.getDeptId().equals("548.00")) {
				String qryStr = "SELECT a FROM Application a WHERE a.id.applicationId = :applicationId ";
	            Query query = getEntityManager(webAppName).createQuery(qryStr);
	            query.setParameter("applicationId", id.getApplicationId());
	            List<Application> list = query.getResultList();
	            if (list.isEmpty())
	    			return null;
	            else if (list.size() == 1)
	            	return list.get(0);
	            throw new NonUniqueResultException();
			}else{
				return getEntityManager(webAppName).find(Application.class, id);
			}
			
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("4378787878787878787878787878787878787878787878");
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Application findByApplicationId(String applicationId, String webAppName) {
		System.out.println("#############################################7");
		try{
				String qryStr = "SELECT a FROM Application a WHERE a.id.applicationId = :applicationId ";
	            Query query = getEntityManager(webAppName).createQuery(qryStr);
	            query.setParameter("applicationId", applicationId);
	            List<Application> list = query.getResultList();
	            if (list.isEmpty())
	    			return null;
	            else if (list.size() == 1)
	            	return list.get(0);
	            throw new NonUniqueResultException();
			
			
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("4378787878787878787878787878787878787878787878");
			return null;
		}
	}

	@Override
	public void updateApplication(Application application, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(application);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Application> getAll( String webAppName) {
		//getEntityManager(webAppName);
		String qryStr="SELECT a FROM Application a";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<Application> list = query.getResultList();
        return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Application> getAll(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr="SELECT a FROM Application a WHERE a.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<Application> list = query.getResultList();
        return list;
	}

	@Override
	public void createApplication(Application application, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(application);
		
	}
	
	@Override
	public String createApplicationAutoId(Application application, String appIdPrefix, String webAppName) {
		//getEntityManager(webAppName);
		String nextAppId=appIdPrefix+getNextAppId(appIdPrefix, webAppName);
		
		ApplicationPK id=new ApplicationPK();
		id.setApplicationId(nextAppId);
		id.setDeptId(application.getId().getDeptId());
		application.setId(id);
		getEntityManager(webAppName).persist(application);
		return nextAppId;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Application findByApplicationNo(String applicationNo, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr="select i from Application  i where i.applicationNo=:applicationNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationNo", applicationNo.trim());
		List<Application> list= query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();

	}

	@Override
	public void removeApplication(Application application, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).remove(application);
		
	}

	@Override
	public void removeAll( String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa If u want to use it");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Application> getApplicationList(String deptId, String status, String webAppName) {
		//getEntityManager(webAppName);
		Query query = getEntityManager(webAppName).createQuery("SELECT a FROM Application a WHERE a.id.deptId=:deptId AND a.status=:status ORDER BY a.applicationNo");//ORDER BY a.id.applicationNo DESC
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		List<Application> list = query.getResultList();
        return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Application> getApplicationList(String deptId,  String status, String applicationType, String webAppName) {
		//getEntityManager(webAppName);
		Query query = getEntityManager(webAppName).createQuery("SELECT a FROM Application a WHERE a.id.deptId=:deptId AND a.applicationType=:applicationType AND a.status=:status ORDER BY a.applicationNo");//ORDER BY a.id.applicationNo DESC
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		query.setParameter("applicationType", applicationType);
		List<Application> list = query.getResultList();
        return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getApplicationIdList(String deptId, String applicationType, String webAppName) {
		//getEntityManager(webAppName);
		Query query = getEntityManager(webAppName).createQuery("SELECT a.referenceNo FROM PivDetail a WHERE a.id.deptId=:deptId AND  a.referenceType=:referenceType and a.status=:status ORDER BY a.referenceNo");//ORDER BY , a.applicationSubType ASC a.id.applicationNo DESC
		query.setParameter("deptId", deptId);
		query.setParameter("referenceType", "APP");
		query.setParameter("status", PIVStatus.NEW);
		List<String> list1 = query.getResultList();
		System.out.println("dl.d.l "+list1+" "+list1.size());
		List<String> list=null;
		//list1=null;
		if (list1.size()!=0) {
			query = getEntityManager(webAppName).createQuery("SELECT a.id.applicationId FROM Application a WHERE a.id.deptId=:deptId AND a.applicationType=:applicationType AND  (a.status=:statusN OR a.status=:statusM) and a.id.applicationId NOT IN (:list)  ORDER BY a.id.applicationId");//ORDER BY , a.applicationSubType ASC a.id.applicationNo DESC
			query.setParameter("deptId", deptId);
			query.setParameter("statusN", "N");
			query.setParameter("statusM", "M");
			query.setParameter("list", list1);
			query.setParameter("applicationType", applicationType);
			list = query.getResultList();
			
		}else {
			query = getEntityManager(webAppName).createQuery("SELECT a.id.applicationId FROM Application a WHERE a.id.deptId=:deptId AND a.applicationType=:applicationType AND  (a.status=:statusN OR a.status=:statusM) ORDER BY a.id.applicationId");//ORDER BY , a.applicationSubType ASC a.id.applicationNo DESC
			query.setParameter("deptId", deptId);
			query.setParameter("statusN", "N");
			query.setParameter("statusM", "M");
			query.setParameter("applicationType", applicationType);
			list = query.getResultList();
			
		}
		return list;
		 
	}
	
	@Override
	public int changeStatusApplication(String applicationNo, String deptId, String status, String webAppName){
		//getEntityManager(webAppName);
		String qryStr = "UPDATE Application g SET g.status=:status WHERE TRIM(g.applicationNo)= :applicationNo AND g.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("status", status);
		query.setParameter("applicationNo", applicationNo.trim());
		query.setParameter("deptId", deptId);
		int statusUpdate = query.executeUpdate();
		return statusUpdate;
	}
	
	@Override
	public void changeStatusById(String applicationId, String deptId, String status, String webAppName){
		//getEntityManager(webAppName);
		String qryStr = "UPDATE Application g SET g.status=:status WHERE TRIM(g.id.applicationId)= :applicationId AND g.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("status", status);
		query.setParameter("applicationId", applicationId);
		query.setParameter("deptId", deptId);
		query.executeUpdate();
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String getNextAppId(String appIdPrefix, String webAppName) {
		//getEntityManager(webAppName);
    	String sequence=null;
    	String like=appIdPrefix+"%";
    	String strQuery="select a.id.applicationId from Application a " +
    			"where a.id.applicationId like :like ORDER BY 1 DESC";
    	System.out.println("Hii" + strQuery);
    	Query query=getEntityManager(webAppName).createQuery(strQuery);//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
    	query.setParameter("like", like);
    	List<String> list=query.getResultList();
    	if (list.size()!=0){
    		sequence=query.getResultList().get(0).toString().trim();
    		System.out.println(sequence);
    		//sequence=sequence.substring(16);//total 20 chars   year 2012
    		sequence=sequence.substring(14);//total 18 chars  year 12 
    		Integer i=Integer.parseInt(sequence)+1;
    		sequence=i.toString();
    		System.out.println(sequence);
    		System.out.println("Hii" + sequence);
    	}else{
    		sequence="0001";
    		System.out.println("HIIII : "+ sequence);
    	}
    	if(sequence.length()==1)
    		return "000"+sequence;
    	else if (sequence.length()==2)
    		return "00"+sequence;
    	else if (sequence.length()==3)
    		return "0"+sequence;
    	else return sequence;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Application> getSearchResultSet(String deptId, String idNo, String applicationId, String applicationNo, String firstName, String lastName, String suburb, String city, Date fromDate, Date toDate, String fileRef, String webAppName) {
		//getEntityManager(webAppName);
		//a.id.applicationId, b.firstName, b.lastName, b.streetAddress, b.suburb, b.city, a.submitDate, a.confirmedBy, a.description, a.status
		String strQuery=null;
		Query query=null;
		
		if (deptId==null)
			deptId="%";
		else deptId=deptId+"%";
		
		if (idNo==null)
			idNo="%";
		else idNo=idNo+"%";
		System.err.println(idNo);
		if (applicationId==null)
			applicationId="%";
		else applicationId=applicationId+"%";
		
		if (applicationNo==null)
			applicationNo="%";
		else applicationNo=applicationNo+"%";
		
		if (firstName==null)
			firstName="%";
		else firstName=firstName+"%";
		
		if (lastName==null)
			lastName="%";
		else lastName=lastName+"%";
		
		if (suburb==null)
			suburb="%";
		else suburb=suburb+"%";
		
		if (city==null)
			city="%";
		else city=city+"%";
		
		//if (fromDate==null)
		//	fromDate="%";
		//else fromDate=fromDate+"%";
		
		//if (toDate==null)
		//	toDate="%";
		//else toDate=toDate+"%";
		
		
		if (fromDate==null || toDate==null){
			if (applicationNo.equals("%")){
				System.out.println("not equal");
				//, String , String , String , String , String , String , String , String , String )
				strQuery="select  a"+
				    //   (case  when a.status='N' then 'New Record'
			        //    when a.status='P'  then 'Paid'
			        //    when a.status='E' then 'Estimated'
	                //    when a.status='M' then 'Modified'
	                //    when a.status='A' then 'Allocated'
	                //    else NULL end  ) as Status

							" from  Application a, Applicant b "+
							" where a.id.deptId like :deptId "+
							" AND b.idNo= a.idNo " +
							" and  b.idNo like :idNo "+
							" and a.id.applicationId like :applicationId "+
							" and b.firstName like :firstName "+
							" and b.lastName like :lastName "+
							" and b.suburb like :suburb "+
							" and b.city like :city ";//: +
							//" BETWEEN :fromDate AND :toDate ";
					query=getEntityManager(webAppName).createQuery(strQuery);
					query.setParameter("deptId", deptId);
					query.setParameter("idNo", idNo);
					query.setParameter("applicationId", applicationId);
					query.setParameter("firstName", firstName);
					query.setParameter("lastName", lastName);
					query.setParameter("suburb", suburb);
					query.setParameter("city", city);
					//query.setParameter("fromDate", fromDate);
					//query.setParameter("toDate", toDate);
				
			}else{
				System.out.println("application No null");
				//, String , String , String , String , String , String , String , String , String )
				strQuery="select  a"+
				    //   (case  when a.status='N' then 'New Record'
			        //    when a.status='P'  then 'Paid'
			        //    when a.status='E' then 'Estimated'
	                //    when a.status='M' then 'Modified'
	                //    when a.status='A' then 'Allocated'
	                //    else NULL end  ) as Status

							" from  Application a, Applicant b "+
							" where a.id.deptId like :deptId "+
							" AND b.idNo= a.idNo " +
							" and  b.idNo like :idNo "+
							" and a.id.applicationId like :applicationId "+
							" and a.applicationNo like :applicationNo "+
							" and b.firstName like :firstName "+
							" and b.lastName like :lastName "+
							" and b.suburb like :suburb "+
							" and b.city like :city ";//: +
							//" BETWEEN :fromDate AND :toDate ";
					query=getEntityManager(webAppName).createQuery(strQuery);
					query.setParameter("deptId", deptId);
					query.setParameter("idNo", idNo);
					query.setParameter("applicationId", applicationId);
					query.setParameter("applicationNo", applicationNo);
					query.setParameter("firstName", firstName);
					query.setParameter("lastName", lastName);
					query.setParameter("suburb", suburb);
					query.setParameter("city", city);
					//query.setParameter("fromDate", fromDate);
					//query.setParameter("toDate", toDate);
				
			}
			
		
		}else if(fileRef != null && fileRef.length() > 0){
			strQuery="select  a"+
		    //   (case  when a.status='N' then 'New Record'
	        //    when a.status='P'  then 'Paid'
	        //    when a.status='E' then 'Estimated'
            //    when a.status='M' then 'Modified'
            //    when a.status='A' then 'Allocated'
            //    else NULL end  ) as Status

					" from  Application a, Applicant b ,EstimateReference bs "+
					" where a.id.deptId like :deptId "+
					" AND b.idNo= a.idNo " +
					" AND bs.id.standardEstimateNo= a.applicationNo " +
			
					" and bs.fileReference like :fileRef ";//: +
					//" BETWEEN :fromDate AND :toDate ";
			query=getEntityManager(webAppName).createQuery(strQuery);
			query.setParameter("deptId", deptId);
			/*query.setParameter("idNo", idNo);
			query.setParameter("applicationId", applicationId);
			query.setParameter("applicationNo", applicationNo);
			query.setParameter("firstName", firstName);
			query.setParameter("lastName", lastName);
			query.setParameter("suburb", suburb);
			query.setParameter("city", city);*/
			query.setParameter("fileRef", "%"+fileRef+"%");
		}else {
			
			if (applicationNo.equals("%")){
				strQuery="select  a"+
				//   (case  when a.status='N' then 'New Record'
				//    when a.status='P'  then 'Paid'
				//    when a.status='E' then 'Estimated'
				//    when a.status='M' then 'Modified'
				//    when a.status='A' then 'Allocated'
				//    else NULL end  ) as Status

					" from  Application a, Applicant b "+
					" where a.id.deptId like :deptId "+
					" AND b.idNo= a.idNo " +
					" and  b.idNo like :idNo "+
					" and a.id.applicationId like :applicationId "+
					" and b.firstName like :firstName "+
					" and b.lastName like :lastName "+
					" and b.suburb like :suburb "+
					" and b.city like :city "+
					" AND a.addDate BETWEEN :fromDate AND :toDate ";
					query=getEntityManager(webAppName).createQuery(strQuery);
					query.setParameter("deptId", deptId);
					query.setParameter("idNo", idNo);
					query.setParameter("applicationId", applicationId);
					query.setParameter("firstName", firstName);
					query.setParameter("lastName", lastName);
					query.setParameter("suburb", suburb);
					query.setParameter("city", city);
					query.setParameter("fromDate", fromDate);
					query.setParameter("toDate", toDate);
				
			}else{
				strQuery="select  a"+
				//   (case  when a.status='N' then 'New Record'
				//    when a.status='P'  then 'Paid'
				//    when a.status='E' then 'Estimated'
				//    when a.status='M' then 'Modified'
				//    when a.status='A' then 'Allocated'
				//    else NULL end  ) as Status

					" from  Application a, Applicant b "+
					" where a.id.deptId like :deptId "+
					" AND b.idNo= a.idNo " +
					" and  b.idNo like :idNo "+
					" and a.id.applicationId like :applicationId "+
					" and a.applicationNo like :applicationNo "+
					" and b.firstName like :firstName "+
					" and b.lastName like :lastName "+
					" and b.suburb like :suburb "+
					" and b.city like :city "+
					" AND a.addDate BETWEEN :fromDate AND :toDate ";
					query=getEntityManager(webAppName).createQuery(strQuery);
					query.setParameter("deptId", deptId);
					query.setParameter("idNo", idNo);
					query.setParameter("applicationId", applicationId);
					query.setParameter("applicationNo", applicationNo);
					query.setParameter("firstName", firstName);
					query.setParameter("lastName", lastName);
					query.setParameter("suburb", suburb);
					query.setParameter("city", city);
					query.setParameter("fromDate", fromDate);
					query.setParameter("toDate", toDate);
				
			}
			
		}
		
		
		
		List<Application> list=query.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Application> getSearchResults(String deptId, String idNo, String applicationId, String applicationNo, String firstName, String lastName, String suburb, String city, Date fromDate, Date toDate, String jobNo, String webAppName) {
		//getEntityManager(webAppName);
		//a.id.applicationId, b.firstName, b.lastName, b.streetAddress, b.suburb, b.city, a.submitDate, a.confirmedBy, a.description, a.status
		String strQuery=null;
		Query query=null;
		
		if (deptId==null)
			deptId="%";
		else deptId=deptId+"%";
		
		if (idNo==null)
			idNo="%";
		else idNo=idNo+"%";
		System.err.println(idNo);
		if (applicationId==null)
			applicationId="%";
		else applicationId=applicationId+"%";
		
		if (applicationNo==null)
			applicationNo="%";
		else applicationNo=applicationNo+"%";
		
		if (firstName==null)
			firstName="%";
		else firstName=firstName+"%";
		
		if (lastName==null)
			lastName="%";
		else lastName=lastName+"%";
		
		if (suburb==null)
			suburb="%";
		else suburb=suburb+"%";
		
		if (city==null)
			city="%";
		else city=city+"%";
		
		if (jobNo==null)
			jobNo="%";
		else jobNo=jobNo+"%";
		
		//if (fromDate==null)
		//	fromDate="%";
		//else fromDate=fromDate+"%";
		
		//if (toDate==null)
		//	toDate="%";
		//else toDate=toDate+"%";
		
		
		if (fromDate==null || toDate==null){
			if (!applicationNo.equals("%")){
				System.out.println("application No null");
				//, String , String , String , String , String , String , String , String , String )
				strQuery="select  a"+
				    //   (case  when a.status='N' then 'New Record'
			        //    when a.status='P'  then 'Paid'
			        //    when a.status='E' then 'Estimated'
	                //    when a.status='M' then 'Modified'
	                //    when a.status='A' then 'Allocated'
	                //    else NULL end  ) as Status

							" from  Application a, Applicant b "+
							" where a.id.deptId like :deptId "+
							" AND b.idNo= a.idNo " +
							" and  b.idNo like :idNo "+
							" and a.id.applicationId like :applicationId "+
							" and a.applicationNo like :applicationNo "+
							" and b.firstName like :firstName "+
							" and b.lastName like :lastName "+
							" and b.suburb like :suburb "+
							" and b.city like :city ";//: +
							//" BETWEEN :fromDate AND :toDate ";
					query=getEntityManager(webAppName).createQuery(strQuery);
					query.setParameter("deptId", deptId);
					query.setParameter("idNo", idNo);
					query.setParameter("applicationId", applicationId);
					query.setParameter("applicationNo", applicationNo);
					query.setParameter("firstName", firstName);
					query.setParameter("lastName", lastName);
					query.setParameter("suburb", suburb);
					query.setParameter("city", city);
					//query.setParameter("fromDate", fromDate);
					//query.setParameter("toDate", toDate);
				
				
				
			}else if (!jobNo.equals("%")){
				System.out.println("application No null");
				//, String , String , String , String , String , String , String , String , String )
				strQuery="select  a"+
				    //   (case  when a.status='N' then 'New Record'
			        //    when a.status='P'  then 'Paid'
			        //    when a.status='E' then 'Estimated'
	                //    when a.status='M' then 'Modified'
	                //    when a.status='A' then 'Allocated'
	                //    else NULL end  ) as Status

							" from  Application a, Applicant b , ApplicationReference c "+
							" where a.id.deptId like :deptId "+
							" AND b.idNo= a.idNo " +
							" AND b.idNo= c.idNo " +
							" and  b.idNo like :idNo "+
							" and a.id.applicationId like :applicationId "+
							" and a.applicationNo like :applicationNo "+
							" and b.firstName like :firstName "+
							" and b.lastName like :lastName "+
							" and b.suburb like :suburb "+
							" and c.projectno like :jobNo "+
							" and b.city like :city ";//: +
							//" BETWEEN :fromDate AND :toDate ";
					query=getEntityManager(webAppName).createQuery(strQuery);
					query.setParameter("deptId", deptId);
					query.setParameter("idNo", idNo);
					query.setParameter("applicationId", applicationId);
					query.setParameter("applicationNo", applicationNo);
					query.setParameter("firstName", firstName);
					query.setParameter("lastName", lastName);
					query.setParameter("suburb", suburb);
					query.setParameter("jobNo", jobNo);
					query.setParameter("city", city);
					//query.setParameter("fromDate", fromDate);
					//query.setParameter("toDate", toDate);
				
			}else{
				System.out.println("not equal");
				//, String , String , String , String , String , String , String , String , String )
				strQuery="select  a"+
				    //   (case  when a.status='N' then 'New Record'
			        //    when a.status='P'  then 'Paid'
			        //    when a.status='E' then 'Estimated'
	                //    when a.status='M' then 'Modified'
	                //    when a.status='A' then 'Allocated'
	                //    else NULL end  ) as Status

							" from  Application a, Applicant b "+
							" where a.id.deptId like :deptId "+
							" AND b.idNo= a.idNo " +
							" and  b.idNo like :idNo "+
							" and a.id.applicationId like :applicationId "+
							" and b.firstName like :firstName "+
							" and b.lastName like :lastName "+
							" and b.suburb like :suburb "+
							" and b.city like :city ";//: +
							//" BETWEEN :fromDate AND :toDate ";
					query=getEntityManager(webAppName).createQuery(strQuery);
					query.setParameter("deptId", deptId);
					query.setParameter("idNo", idNo);
					query.setParameter("applicationId", applicationId);
					query.setParameter("firstName", firstName);
					query.setParameter("lastName", lastName);
					query.setParameter("suburb", suburb);
					query.setParameter("city", city);
					//query.setParameter("fromDate", fromDate);
					//query.setParameter("toDate", toDate);
			}
			
		
		}else {
			
			if (!applicationNo.equals("%")){
				strQuery="select  a"+
				//   (case  when a.status='N' then 'New Record'
				//    when a.status='P'  then 'Paid'
				//    when a.status='E' then 'Estimated'
				//    when a.status='M' then 'Modified'
				//    when a.status='A' then 'Allocated'
				//    else NULL end  ) as Status

					" from  Application a, Applicant b "+
					" where a.id.deptId like :deptId "+
					" AND b.idNo= a.idNo " +
					" and  b.idNo like :idNo "+
					" and a.id.applicationId like :applicationId "+
					" and a.applicationNo like :applicationNo "+
					" and b.firstName like :firstName "+
					" and b.lastName like :lastName "+
					" and b.suburb like :suburb "+
					" and b.city like :city "+
					" AND a.addDate BETWEEN :fromDate AND :toDate ";
					query=getEntityManager(webAppName).createQuery(strQuery);
					query.setParameter("deptId", deptId);
					query.setParameter("idNo", idNo);
					query.setParameter("applicationId", applicationId);
					query.setParameter("applicationNo", applicationNo);
					query.setParameter("firstName", firstName);
					query.setParameter("lastName", lastName);
					query.setParameter("suburb", suburb);
					query.setParameter("city", city);
					query.setParameter("fromDate", fromDate);
					query.setParameter("toDate", toDate);
				
				
				
			}else if (!jobNo.equals("%")){
				strQuery="select  a"+
				//   (case  when a.status='N' then 'New Record'
				//    when a.status='P'  then 'Paid'
				//    when a.status='E' then 'Estimated'
				//    when a.status='M' then 'Modified'
				//    when a.status='A' then 'Allocated'
				//    else NULL end  ) as Status

					" from  Application a, Applicant b , ApplicationReference c "+
					" where a.id.deptId like :deptId "+
					" AND b.idNo= a.idNo " +
					" AND b.idNo= c.idNo " +
					" and  b.idNo like :idNo "+
					" and a.id.applicationId like :applicationId "+
					" and a.applicationNo like :applicationNo "+
					" and b.firstName like :firstName "+
					" and b.lastName like :lastName "+
					" and b.suburb like :suburb "+
					" and b.city like :city "+
					" and c.projectno like :jobNo "+
					" AND a.addDate BETWEEN :fromDate AND :toDate ";
					query=getEntityManager(webAppName).createQuery(strQuery);
					query.setParameter("deptId", deptId);
					query.setParameter("idNo", idNo);
					query.setParameter("applicationId", applicationId);
					query.setParameter("applicationNo", applicationNo);
					query.setParameter("firstName", firstName);
					query.setParameter("lastName", lastName);
					query.setParameter("suburb", suburb);
					query.setParameter("city", city);
					query.setParameter("jobNo", jobNo);
					query.setParameter("fromDate", fromDate);
					query.setParameter("toDate", toDate);
				
			}else{
				strQuery="select  a"+
				//   (case  when a.status='N' then 'New Record'
				//    when a.status='P'  then 'Paid'
				//    when a.status='E' then 'Estimated'
				//    when a.status='M' then 'Modified'
				//    when a.status='A' then 'Allocated'
				//    else NULL end  ) as Status

					" from  Application a, Applicant b "+
					" where a.id.deptId like :deptId "+
					" AND b.idNo= a.idNo " +
					" and  b.idNo like :idNo "+
					" and a.id.applicationId like :applicationId "+
					" and b.firstName like :firstName "+
					" and b.lastName like :lastName "+
					" and b.suburb like :suburb "+
					" and b.city like :city "+
					" AND a.addDate BETWEEN :fromDate AND :toDate ";
					query=getEntityManager(webAppName).createQuery(strQuery);
					query.setParameter("deptId", deptId);
					query.setParameter("idNo", idNo);
					query.setParameter("applicationId", applicationId);
					query.setParameter("firstName", firstName);
					query.setParameter("lastName", lastName);
					query.setParameter("suburb", suburb);
					query.setParameter("city", city);
					query.setParameter("fromDate", fromDate);
					query.setParameter("toDate", toDate);
				
			}
			
		}
		
		
		
		List<Application> list=query.getResultList();
		return list;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public Application findByApplicationNo(String applicationNo, String deptId, String webAppName)
	
			throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr="select i from Application  i where i.applicationNo=:applicationNo AND i.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationNo", applicationNo.trim());
		query.setParameter("deptId", deptId);
		List<Application> list= query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Application findByApplicationNo(String applicationNo, String deptId, String applicationType, String webAppName)
	
			throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr="select i from Application  i where i.applicationNo=:applicationNo AND i.id.deptId=:deptId AND i.applicationType=:applicationType";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationNo", applicationNo.trim());
		query.setParameter("deptId", deptId);
		query.setParameter("applicationType", applicationType);
		List<Application> list= query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}
	@SuppressWarnings("unchecked")
	@Override
	public String getAppStatusByAppNo(String applicationNo, String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr="select status from Application  i where i.applicationNo=:applicationNo AND i.id.deptId=:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationNo", applicationNo);
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
	public List<String> getApplicationNosByStatus(String deptId, String allocatedTo, String applicationType, String status, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr="select i.applicationNo from Application i, Spestedy a  where a.referenceNo=i.applicationNo AND i.applicationType=:applicationType  AND i.id.deptId=:deptId AND (a.allocatedTo=:allocatedTo OR a.allocatedBy=:allocatedTo) AND i.status=:status";
		//a.referenceNo=b.applicationNo AND b.applicationType=:applicationType AND b.applicationSubType=:applicationSubType AND a.id.deptId=:deptId AND (a.allocatedTo=:allocatedTo OR a.allocatedBy=:allocatedTo) AND a.status=:status
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationType", applicationType);
		query.setParameter("deptId", deptId);
		query.setParameter("allocatedTo", allocatedTo);
		query.setParameter("status", status);
		List<String> list= query.getResultList();
		return list;
        
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getApplicationNosByStatus(String deptId, String allocatedTo, String applicationType, String applicationSubType, String status, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr="select i.applicationNo from Application i, Spestedy a  where a.referenceNo=i.applicationNo AND i.applicationType=:applicationType and i.applicationSubType=:applicationSubType AND i.id.deptId=:deptId AND (a.allocatedTo=:allocatedTo OR a.allocatedBy=:allocatedTo) AND i.status=:status";
		//a.referenceNo=b.applicationNo AND b.applicationType=:applicationType AND b.applicationSubType=:applicationSubType AND a.id.deptId=:deptId AND (a.allocatedTo=:allocatedTo OR a.allocatedBy=:allocatedTo) AND a.status=:status
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationType", applicationType);
		query.setParameter("applicationSubType", applicationSubType);
		query.setParameter("deptId", deptId);
		query.setParameter("allocatedTo", allocatedTo);
		query.setParameter("status", status);
		List<String> list= query.getResultList();
		return list;
        
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ApplicationDisplay> getToBeAllocatedApplicationList(String deptId,  String status, String applicationType, String webAppName) {
		//getEntityManager(webAppName);
			
		Query query = getEntityManager(webAppName).createQuery("SELECT new estimate.model.ApplicationDisplay(TRIM(a.applicationNo), a.id.deptId, a.applicationType, a.applicationSubType ,con.ServiceDepoName,ap.fullName,con.schemeName,con.schemeNo) FROM Application a,Applicant ap,WiringLandDetailCon con,ApplicationReference ref WHERE TRIM(a.id.applicationId)=ref.id.applicationId AND TRIM(a.idNo)=ap.idNo AND TRIM(a.id.applicationId)=con.id.applicationId AND a.id.deptId=:deptId AND a.applicationType=:applicationType AND a.status =:status ORDER BY a.applicationNo");//ORDER BY a.id.applicationNo DESC
		query.setParameter("deptId", deptId);
		query.setParameter("status", status);
		query.setParameter("applicationType", applicationType);
		List<ApplicationDisplay> list = query.getResultList();
        return list;
	}
}
