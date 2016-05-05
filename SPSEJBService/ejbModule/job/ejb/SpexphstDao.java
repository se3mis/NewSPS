package job.ejb;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import util.common.Format;
import util.emSelect.EmSelector;

import job.model.Spexphst;
import job.service.SpexphstEjb;

/**
 * Session Bean implementation class SpexphstDao
 */
@Stateless
public class SpexphstDao extends EmSelector implements SpexphstDaoRemote, SpexphstDaoLocal {
	
    /**
     * Default constructor. 
     */
    public SpexphstDao() {
        // TODO Auto-generated constructor stub
    }
    
    
	@Override
	public void createSpexphst(Spexphst spexphst, String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa");
		
	}
	
	@Override
	public String createSpexphstAutoId(Spexphst spexphst, String webAppName) {
		//getEntityManager(webAppName);
		String nextId=getNextAppointmentId(spexphst.getDeptId(), webAppName);
		spexphst.setReferenceNo(nextId);
		getEntityManager(webAppName).persist(spexphst);
		return nextId;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String getNextAppointmentId(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String sequence=null;
    	Calendar calendar=Calendar.getInstance();
    	Format format=new Format();
    	String[] s=format.formatDate2(calendar.getTime()).trim().toString().split(" ");
		String prefix=deptId+"/"+s[0].trim()+"/";
    	String like=prefix+"%";
    	System.out.println("# "+prefix);
    	String strQuery="select a.referenceNo from Spexphst a where a.deptId =:deptId AND a.referenceNo LIKE :like ORDER BY 1 DESC";
    	Query query=getEntityManager(webAppName).createQuery(strQuery);//SELECT MIS.TEST4_SEQ.NEXTVAL() FROM DUAL
    	query.setParameter("deptId", deptId);
    	query.setParameter("like", like);
    	List<String> list=query.getResultList();
    	//System.out.println(list);
    	if (list.size()!=0){
    		sequence=query.getResultList().get(0).toString().trim();
    		System.out.println("@ "+sequence);
    		sequence=sequence.substring(12);
    		System.out.println("$ "+sequence);
    		Integer i=Integer.parseInt(sequence)+1;
    		sequence=i.toString();
    		System.out.println("% "+sequence);
    	}else{
    		sequence="001";
    		System.out.println(sequence);
    		//System.out.println(sequence.substring(4));
    	}
    	if(sequence.length()==1)
    		return prefix+"00"+sequence;
    	else if (sequence.length()==2)
    		return prefix+"0"+sequence;
    	else return prefix+sequence;
	}

	@Override
	public void updateSpexphst(Spexphst spexphst, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(spexphst);
		
	}

	@Override
	public void removeSpexphst(Spexphst spexphst, String webAppName) {
		//getEntityManager(webAppName);
		spexphst=getEntityManager(webAppName).merge(spexphst);
		getEntityManager(webAppName).remove(spexphst);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spexphst> getAll(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select a from Spexphst a WHERE a.id.deptId =:deptId ORDER BY a.exportedDate DESC, a.exportedTime DESC";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<Spexphst> list = query.getResultList();
        return list;
	}

	@Override
	public Spexphst findById(String referenceNo, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		return getEntityManager(webAppName).find(Spexphst.class, referenceNo);
	}

	public static void main (String[] args){
		SpexphstEjb ejb=new SpexphstEjb();
		System.out.println(ejb);
		
	}
}
