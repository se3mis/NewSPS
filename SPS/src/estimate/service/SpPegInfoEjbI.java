package estimate.service;

import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.SpPegInfo;
import estimate.model.SpPegInfoPK;
import estimate.model.Splbstrt;
import estimate.model.SplbstrtPK;

public interface SpPegInfoEjbI {
	void createSpPegInfo(SpPegInfo spPegInf, String webAppName);
	List<SpPegInfo> getAll(String webAppName)throws Exception;
	void updateSpPegInfo(SpPegInfo spPegInfo, String webAppName)  ;
	void removeSpPegInfo(SpPegInfo spPegInfo, String webAppName)  ;
	void removeAll(String webAppName);
	SpPegInfo findById(SpPegInfoPK id, String webAppName) throws Exception ;
	
	public List<SpPegInfo> getPegChildrensByParentId(String parentId,String masterId,String webAppName) throws Exception;
	public void updateDescription(String id,String name ,String masterId,String webAppName)
	throws Exception;
	public void deletePegItem(String id,String masterId, String webAppName)
	throws Exception;
}
