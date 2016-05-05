package estimate.service;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.PersistenceException;

import estimate.model.SpPegInfo;
import estimate.model.SpPegInfoPK;
import estimate.model.SpPointdmt;
import estimate.model.SpPointdmtPK;
import estimate.model.Splbstrt;
import estimate.model.SplbstrtPK;

public interface SpPointDmtEjbI {
	List<SpPointdmt> getAll(String webAppName)throws PersistenceException;
	//void updateSpPegInfo(SpPegInfo spPegInfo, String webAppName)  ;
	//void removeSpPegInfo(SpPegInfo spPegInfo, String webAppName)  ;
	//void removeAll(String webAppName);
	//SpPegInfo findById(SpPegInfoPK id, String webAppName) throws PersistenceException ;
	
	public List<SpPointdmt> getPegResourceById(String id,String masterId,String webAppName) throws PersistenceException;
	public void createSpPointdmt(SpPointdmt spPointdmt, String webAppName)throws Exception;
	public void updateSpPointdmt(SpPointdmt spPointdmt, String webAppName)throws Exception;
	public void removeSpPointdmt(SpPointdmt spPointdmt, String webAppName)throws Exception;
	public SpPointdmt findById(SpPointdmtPK id, String webAppName)
	throws Exception;
	public void updateSpPointdmt(String lineTypeId, String resCode,
			BigDecimal quantity,String masterId, String webAppName)throws Exception;
	public void removeSpPointdmt(String lineTypeId, String resCode,String masterId,
			String webAppName)throws Exception;
}
