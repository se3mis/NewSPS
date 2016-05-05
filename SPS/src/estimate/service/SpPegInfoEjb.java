package estimate.service;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpPegInfoDaoRemote;

import estimate.model.SpPegInfo;
import estimate.model.SpPegInfoPK;

public class SpPegInfoEjb implements SpPegInfoEjbI {
	private Context context;
	private SpPegInfoDaoRemote dao; 
	public SpPegInfoEjb() {
		super();
		this.dao=lookupDao();
		
	}
	
	private SpPegInfoDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpPegInfoDaoRemote dao = (SpPegInfoDaoRemote) context.lookup("SpPegInfoDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void createSpPegInfo(SpPegInfo spPegInf, String webAppName) {
		dao.createSpPegInfo(spPegInf, webAppName);
		
	}

	@Override
	public List<SpPegInfo> getAll(String webAppName){
		return dao.getAll(webAppName);
	}

	@Override
	public void updateSpPegInfo(SpPegInfo spPegInfo, String webAppName) {
		dao.updateSpPegInfo(spPegInfo, webAppName);
		
	}

	@Override
	public void removeSpPegInfo(SpPegInfo spPegInfo, String webAppName) {
		dao.removeSpPegInfo(spPegInfo, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll( webAppName);
		
	}

	@Override
	public SpPegInfo findById(SpPegInfoPK id, String webAppName)
			throws PersistenceException {
		return dao.findById( id,webAppName);
	}

	@Override
	public List<SpPegInfo> getPegChildrensByParentId(String parentId,String masterId,
			String webAppName) throws PersistenceException {
		// TODO Auto-generated method stub
		return dao.getPegChildrensByParentId( parentId,masterId,webAppName);
	}
	
	@Override
	public void updateDescription(String id,String name,String masterId, String webAppName)
			throws Exception {
		dao.updateDescription( id,name,masterId,webAppName);
	}
	
	@Override
	public void deletePegItem(String id,String masterId, String webAppName)
			throws Exception {
		dao.deletePegItem( id,masterId,webAppName);
		
	}
	
}
