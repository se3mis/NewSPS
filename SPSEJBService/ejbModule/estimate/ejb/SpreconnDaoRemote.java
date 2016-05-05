package estimate.ejb;
import java.util.List;

import javax.ejb.Remote;

import estimate.model.Spreconn;

@Remote
public interface SpreconnDaoRemote {
	Spreconn findById(long disconnectDuration, String webAppName);
	List<Spreconn> getAll(String webAppName);

}
