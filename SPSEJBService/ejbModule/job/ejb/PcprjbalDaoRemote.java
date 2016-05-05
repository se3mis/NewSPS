package job.ejb;
import javax.ejb.Remote;

import job.model.Pcprjbal;

@Remote
public interface PcprjbalDaoRemote {
	void createPcprjbal(Pcprjbal pcprjbal, String webAppName);

}
