package security.service;

import java.util.List;

public interface SecurityEjbI {
	String getPassword(String userName);
	List<String> getAuthorizedCostCenters(String userName);
	Boolean volidateLogin(String userName, String password) throws Exception;
	String getCostCenter(String userName);
	String getAuthorizedLevel(String userName);
	List<String> getUserList(String deptId, String userLevel);
	List<String> getUserList(String deptId, List<String> userLevel);
	public String getUserName(String userName, String webAppName) ;
	List<String> getAllUserList(String deptId, List<String> userLevel);
}
