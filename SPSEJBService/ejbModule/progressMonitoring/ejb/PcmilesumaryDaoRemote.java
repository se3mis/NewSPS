package progressMonitoring.ejb;

import java.util.List;

import javax.ejb.Remote;


import progressMonitoring.model.Pcmiledates;
import progressMonitoring.model.Pcmilesumary;

@Remote
public interface PcmilesumaryDaoRemote {

	void createPcmilesumary(Pcmilesumary pcmilesumary,String webAppName);
	void removePcmilesumary(Pcmilesumary pcmilesumary, String webAppName);
	List<Pcmilesumary> getAll(String webAppName);
	void updatePcmilesumary(Pcmilesumary pcmilesumary,String webAppName);
	Pcmilesumary getMilestoneList(String deptId,String projectNumber,String webAppName);
	Pcmilesumary getMilestoneSummaryId(String deptId,
			String projectNumber,String milestoneId, String webAppName);
	List<Pcmilesumary> getSummaryList(String deptId,String projectNumber, String webAppName);
}
