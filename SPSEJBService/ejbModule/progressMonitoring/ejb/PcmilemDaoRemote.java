package progressMonitoring.ejb;

import java.util.List;

import javax.ejb.Remote;

import progressMonitoring.model.Pcmilem;


@Remote
public interface PcmilemDaoRemote {

	void createPcmilem(Pcmilem Pcmilem,String webAppName);
	List<Pcmilem> getAll(String webAppName);
	
	
	List<Pcmilem> getMilestoneList(String deptId,
			String webAppName);
	void updatePcmilem(Pcmilem pcmilem,String webAppName);
}
