package inventory.ejb;
import java.math.BigDecimal;
import java.util.List;

import inventory.model.Intrhmt;

import javax.ejb.Remote;


@Remote
public interface IntrhmtDaoRemote {
	List<Intrhmt> getAll1(String webAppName) ;
	Boolean isMaterialIssued(String jobNo, String deptId,BigDecimal issueTo, BigDecimal status, String webAppName);
	Boolean isMaterialIssued(String jobNo, String deptId, String webAppName);

}
