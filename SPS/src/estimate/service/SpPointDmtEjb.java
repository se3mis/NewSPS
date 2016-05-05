package estimate.service;

import java.math.BigDecimal;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpPegInfoDaoRemote;
import estimate.ejb.SpPointdmtDaoRemote;

import estimate.model.SpPegInfo;
import estimate.model.SpPegInfoPK;
import estimate.model.SpPointdmt;
import estimate.model.SpPointdmtPK;

public class SpPointDmtEjb implements SpPointDmtEjbI {
	private Context context;
	private SpPointdmtDaoRemote dao; 
	public SpPointDmtEjb() {
		super();
		this.dao=lookupDao();
		
	}
	
	private SpPointdmtDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpPointdmtDaoRemote dao = (SpPointdmtDaoRemote) context.lookup("SpPointdmtDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public List<SpPointdmt> getAll(String webAppName)
			throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SpPointdmt> getPegResourceById(String id,String masterId, String webAppName)
			throws PersistenceException {
		return dao.getPegResourceById(id,masterId, webAppName);
	}

	@Override
	public void createSpPointdmt(SpPointdmt spPointdmt, String webAppName)throws Exception {
		dao.createSpPointdmt(spPointdmt, webAppName);
		
	}

	@Override
	public void updateSpPointdmt(SpPointdmt spPointdmt, String webAppName) throws Exception{
		dao.updateSpPointdmt(spPointdmt, webAppName);
		
	}

	@Override
	public void removeSpPointdmt(SpPointdmt spPointdmt, String webAppName) throws Exception{
		dao.removeSpPointdmt(spPointdmt, webAppName);
		
	}
	public SpPointdmt findById(SpPointdmtPK id, String webAppName)
	throws Exception {
		// TODO Auto-generated method stub
		return dao.findById(id,webAppName);
	}

	@Override
	public void updateSpPointdmt(String lineTypeId, String resCode,
			BigDecimal quantity,String masterId, String webAppName)throws Exception {
		dao.updateSpPointdmt(lineTypeId,resCode, quantity,masterId,webAppName);
		
	}

	@Override
	public void removeSpPointdmt(String lineTypeId, String resCode,String masterId,
			String webAppName)throws Exception {
		dao.removeSpPointdmt(lineTypeId,resCode,masterId,webAppName);
		
	}

}
