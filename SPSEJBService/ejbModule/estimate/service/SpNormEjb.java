package estimate.service;

import java.math.BigDecimal;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpnormsDaoRemote;
import estimate.model.Spnorms;
import estimate.model.SpnormsPK;


public class SpNormEjb implements SpnormsDaoRemote{

	private Context context;
	private SpnormsDaoRemote dao; 
	public SpNormEjb() {
		super();
		this.dao=lookupDao();
		
	}

	private SpnormsDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpnormsDaoRemote dao = (SpnormsDaoRemote) context.lookup("SpnormsDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void createSpnorms(Spnorms spnorms, String webAppName) {
		dao.createSpnorms(spnorms, webAppName);
		
	}

	@Override
	public List<Spnorms> getAll(String webAppName) {
		// TODO Auto-generated method stub
		return dao.getAll(webAppName);
	}

	@Override
	public void updateSpnorms(Spnorms spnorms, String webAppName) {
		dao.updateSpnorms(spnorms,webAppName);
		
	}

	@Override
	public void removeSpnorms(Spnorms spnorms, String webAppName) {
		dao.removeSpnorms(spnorms,webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Spnorms findById(SpnormsPK id, String webAppName)
			throws PersistenceException {
		return dao.findById(id, webAppName);
	}

	@Override
	public List<Spnorms> getChildrensByParentId(String parentId,
			String webAppName) throws PersistenceException {
		// TODO Auto-generated method stub
		return dao.getChildrensByParentId(parentId, webAppName);
	}

	
	

}
