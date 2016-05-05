package costcenter.ejb;
import javax.ejb.Remote;

@Remote
public interface DeptmapmDaoRemote {
	String findMappingDept(String deptId, String webAppName);

}
