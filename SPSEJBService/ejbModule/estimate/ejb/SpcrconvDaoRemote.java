package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;

import estimate.model.Spcrconv;
import estimate.model.SpcrconvPK;

@Remote
public interface SpcrconvDaoRemote {
	Spcrconv findById(SpcrconvPK id, String webAppName);
	List<Spcrconv> getAll(String webAppName);

}
