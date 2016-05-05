package Applicant.ejb;


import java.lang.reflect.Field;


import java.util.List;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.servlet.ServletRequest;


//import org.jboss.ejb3.session.SessionContainer;
//import org.omg.CORBA.Security;

//import com.sun.xml.ws.developer.servlet.HttpSessionScope;

import util.common.Format;
import util.emSelect.EmSelector;
import application.model.Applicant;
import application.model.Application;

/**
 * Session Bean implementation class ApplicantDao
 */
@Stateless
@DeclareRoles("SMCTesting")
public class ApplicantDao extends EmSelector implements  ApplicantDaoRemote, ApplicantDaoLocal{
	@Resource
	SessionContext context;
	//@PersistenceContext
	//private EntityManager em;
	/*@PersistenceContext(unitName="SMCTesting")
	private EntityManager emSMCTesting;
	@PersistenceContext(unitName="SMCR1")
	private EntityManager emSMCR1;
	@PersistenceContext(unitName="SMCR2")
	private EntityManager emSMCR2;
	@PersistenceContext(unitName="SMCR3")
	private EntityManager emSMCR3;
	@PersistenceContext(unitName="SMCR4")
	private EntityManager emSMCR4;*/

	//@PersistenceUnit
	//private EntityManagerFactory emf; 
	
	
    /**
     * Default constructor. 
     */
    public ApplicantDao() {
    	
    }
    
	
    
	
	/*private EntityManager getEntityManager(String webAppName){
		emf = Persistence.createEntityManagerFactory(webAppName);
    	em = emf.createEntityManager(); 
		return em;
		
	}*/
	
	/*private EntityManager getEntityManager(String webAppName){
		if (webAppName.equals("R1"))
				return emSMCR1;	
		else if (webAppName.equals("R2"))
			return emSMCR2;
		else if (webAppName.equals("R3"))
			return emSMCR3;
		else if (webAppName.equals("R4"))
			return emSMCR4;
		else return emSMCTesting;	
			
		
		
	}*/
	
	@Override
	public Applicant findById(String idNo, String webAppName) {
    	return getEntityManager(webAppName).find(Applicant.class, idNo);
    }
	
	
    
    @Override
	public void createApplicant(Applicant applicant, String webAppName) {
    	
    	getEntityManager(webAppName).persist(applicant);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Applicant> getAll(String webAppName) {
		
		Query query = getEntityManager(webAppName).createQuery("select a from Applicant a");
        List<Applicant> list = query.getResultList();
        return list;

	}
	@Override
	public void updateApplicant(Applicant applicant, String webAppName) {
		getEntityManager(webAppName).merge(applicant);
		
	}
	@Override
	public void removeApplicant(Applicant applicant, String webAppName) {
		getEntityManager(webAppName).remove(applicant);
		
	}
	@Override
	public void removeAll(String webAppName) {
		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa If u want to use it");
		
	}
	@Override
	@SuppressWarnings("unchecked")
	public String genDummyId(String deptId, String webAppName){
			String sequence=null;
	    	//Calendar calendar=Calendar.getInstance();
	    	Format format=new Format();
	    	//String[] s=format.formatDate2(calendar.getTime()).trim().toString().split(" ");
			//String prefix=s[0].trim().substring(0, 4)+"/"+s[1].toString()+"/";
			String prefix="d"+deptId.substring(0,3)+format.getYear().substring(format.getYear().length()-2,format.getYear().length());
			String like=prefix+"%";
			System.out.println(like);
	    	String strQuery="select a.id.idNo from Applicant a where  a.id.idNo LIKE :like ORDER BY 1 DESC";//a.deptId =:deptId AND
	    	Query query=getEntityManager(webAppName).createQuery(strQuery);//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
	    	//query.setParameter("deptId", deptId);
	    	query.setParameter("like", like);
	    	List<String> list=query.getResultList();
	    	//System.out.println(list);
	    	if (list.size()!=0){
	    		sequence=query.getResultList().get(0).toString().trim();
	    		System.out.println("^ "+sequence);
	    		sequence=sequence.substring(6);
	    		System.out.println("* "+sequence);
	    		Integer i=Integer.parseInt(sequence)+1;
	    		sequence=i.toString();
	    		System.out.println("# "+sequence);
	    	}else{
	    		sequence="0001";
	    		//System.out.println(sequence);
	    	}
	    	if(sequence.length()==1)
	    		return prefix+"000"+sequence;
	    	else if (sequence.length()==2)
	    		return prefix+"00"+sequence;
	    	else if (sequence.length()==3)
	    		return prefix+"0"+sequence;
	    	else return prefix+sequence;
		}
	
	//DO NOT DELETE
	@SuppressWarnings({ "rawtypes", "unused" })
	private ServletRequest getRequestInfo(){
		try {

            //Dynamic class loading is used to avoid compile-time dependency on JBoss internal libraries

            Class clazz = Class.forName("org.jboss.web.tomcat.security.HttpServletRequestPolicyContextHandler");
            Field requestContextField = clazz.getDeclaredField("requestContext");
            requestContextField.setAccessible(true);
            ThreadLocal ctx = (ThreadLocal) requestContextField.get(null);
            ServletRequest req = ((ServletRequest) ctx.get());
            //System.out.println(req==null?null:req.getRemoteAddr());
            //System.out.println(req==null?null:req.getServerName());
           // System.out.println(req==null?null:req.getRemoteHost());
           // System.out.println(req==null?null:req.getScheme());
           // System.out.println("33333333333 ");
           // System.out.println("$$$$$$$$$$$$$$ "+req==null?null:req.getParameter("webAppName"));
           // System.out.println("############################################# ");
           // pu=req.getParameter("webAppName");
            //System.out.println(req==null?null:req.getParameterMap());
            return req==null?null:req;
        } catch (Exception e) {
            //LOG.log(Level.WARNING, "Failed to determine client IP address",e);
        	return null;
        }
	}
	
	
	/*@SuppressWarnings("unchecked")
	@Override
	public Applicant findByApplicationNo(String applicationNo,String webAppName)
	
			throws PersistenceException {
		//getEntityManager(webAppName);
		String qryStr="select i from Applicant i,ApplicationRefBS refbs where i.idNo = refbs.idNo and  refbs.applicationNo=:applicationNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationNo", applicationNo.trim());
		//query.setParameter("deptId", deptId);
		List<Applicant> list= query.getResultList();
		if (list.isEmpty())
			return null;
        else {
        	return list.get(0);
        }
        
	}*/
		
	
	
    
    

}
