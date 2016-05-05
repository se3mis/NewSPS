package job.ejb;
import java.util.List;

import javax.ejb.Remote;

import job.model.Spestjad;
import job.model.SpestjadPK;

@Remote
public interface SpestjadDaoRemote {
	List<Spestjad> getAll (String deptId, String webAppName);
	Spestjad findById(SpestjadPK id, String webAppName);

}
