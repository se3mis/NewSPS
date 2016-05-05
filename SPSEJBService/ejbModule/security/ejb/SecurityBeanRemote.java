package security.ejb;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface SecurityBeanRemote {
	String getPassword(String userName, String webAppName);
	List<String> getAuthorizedCostCenters(String userName, String webAppName);
	Boolean volidateLogin(String userName, String password, String webAppName) throws Exception;
	String getCostCenter(String userName, String webAppName);
	String getAuthorizedLevel(String userName, String webAppName);
	List<String> getUserList(String deptId, String userLevel, String webAppName);
	public List<String> getUserList(String deptId, List<String> userLevel, String webAppName);
	public String getUserName(String userName, String webAppName);
	public List<String> getAllUserList(String deptId, List<String> userLevel, String webAppName);
}
