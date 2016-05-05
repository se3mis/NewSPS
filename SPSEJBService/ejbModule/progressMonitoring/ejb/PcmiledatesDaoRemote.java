package progressMonitoring.ejb;

import java.util.List;
import java.util.Date;

import javax.ejb.Remote;

import progressMonitoring.model.Pcmiledates;

@Remote
public interface PcmiledatesDaoRemote {

	void createpcmiledates(Pcmiledates pcmiledates,String webAppName);
	void removePcmiledates(Pcmiledates pcmiledates, String webAppName);
	List<Pcmiledates> getAll(String webAppName);
	void delete(String deptId,String projectNumber,String webAppName);
	List<Pcmiledates> getMilestoneList(String deptId,String projectNumber,String webAppName);
	List<Pcmiledates> getDataForEstNumber(String webAppName, String value, String Type);
	void updatepcmiledates(Pcmiledates pcmiledates,String webAppName);
	Pcmiledates getMilestoneById(String deptId,String projectNumber,String miletoneId,String webAppName);
	Pcmiledates getMilestoneByDate(String deptId,String projectNumber,String miletoneId,Date date,String webAppName);
}
