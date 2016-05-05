package security.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import util.common.Encryption;
import util.emSelect.EmSelector;

/**
 * Session Bean implementation class securityBean
 */
@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless
public class SecurityBean extends EmSelector implements SecurityBeanRemote, SecurityBeanLocal {
	
    /**
     * Default constructor. 
     */
    public SecurityBean() {
        // TODO Auto-generated constructor stub
    }
    
    

	@SuppressWarnings("unchecked")
	@Override
	public String getPassword(String userName, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT s.pswrd FROM Sauserm s WHERE TRIM(s.userId)=:userName";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("userName", userName.toUpperCase());
		List<String> list = query.getResultList();
		//System.out.println(list);
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAuthorizedCostCenters(String userName, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT TRIM(s.id.deptId) FROM Sausrdpm s WHERE TRIM(s.id.userId)=:userName";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("userName", userName.toUpperCase());
		List<String> list = query.getResultList();
		//System.out.println(list);
		return list;
	}

	@Override
	public Boolean volidateLogin(String userName, String password, String webAppName) throws Exception {
		//getEntityManager(webAppName);
		Encryption encryption=new Encryption();
		return encryption.validateLogin(userName, password, webAppName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getCostCenter(String userName, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT TRIM(rptUser) FROM Sauserm s WHERE TRIM(s.userId)=:userName";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("userName", userName.toUpperCase());
		List<String> list = query.getResultList();
		//System.out.println(list);
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0).trim();
        throw new NonUniqueResultException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getAuthorizedLevel(String userName, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT userLevel FROM Sauserm s WHERE TRIM(s.userId)=:userName";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("userName", userName.toUpperCase());
		List<String> list = query.getResultList();
		//System.out.println(list);
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getUserList(String deptId, String userLevel, String webAppName) {
		//getEntityManager(webAppName);
		//20131210
		
		String qryStr = "SELECT TRIM(userId) FROM Sauserm s WHERE TRIM(s.rptUser)=:rptUser AND s.userLevel =:userLevel  order by userId";
		
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("rptUser", deptId);
		query.setParameter("userLevel", userLevel);
		List<String> list = query.getResultList();
		System.out.println("hiii "+ list);
		return list;
    }
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getUserList(String deptId, List<String> userLevel, String webAppName) {
		String costcenterCode = deptId.substring(0, 3);
		String qryStr = "SELECT TRIM(userId) FROM Sauserm s WHERE  s.status = 2 AND TRIM(s.rptUser) like :rptUser AND TRIM(s.userLevel) in (:userLevel)  order by userId";
		System.out.println("hiiiii getuserlist" + qryStr +" quary : " + qryStr);
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("rptUser", deptId);
		query.setParameter("userLevel", userLevel);
		
		List<String> list = query.getResultList();
		System.out.println("hiiiii getuserlist" + list +" quary : " + qryStr);
		return list;
        
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllUserList(String deptId, List<String> userLevel, String webAppName) {
		String costcenterCode = deptId.substring(0, 3);
		String qryStr = "SELECT TRIM(userId) FROM Sauserm s WHERE TRIM(s.rptUser) like :rptUser AND TRIM(s.userLevel) in (:userLevel) order by userId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("rptUser", costcenterCode+"%");
		query.setParameter("userLevel", userLevel);
		List<String> list = query.getResultList();
		//System.out.println(list);
		return list;
        
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String getUserName(String userName, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "SELECT s.userNm FROM Sauserm s WHERE TRIM(s.userId)=:userName";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("userName", userName.toUpperCase());
		List<String> list = query.getResultList();
		//System.out.println(list);
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}

}
