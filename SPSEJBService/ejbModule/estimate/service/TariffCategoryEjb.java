package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.TariffCategoryDaoRemote ;
import estimate.model.TariffCategory;

public class TariffCategoryEjb implements TariffCategoryDaoRemote {
	private Context context;
	private TariffCategoryDaoRemote dao; 
	public TariffCategoryEjb() {
		super();
		this.dao=lookupDao();
		
	}
	
	private TariffCategoryDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 TariffCategoryDaoRemote dao = (TariffCategoryDaoRemote) context.lookup("TariffCategoryDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	public void createTariffCategory(TariffCategory tariffCategory, String webAppName) {
		dao.createTariffCategory(tariffCategory, webAppName);
		
	}

	@Override
	public List<TariffCategory> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public void updateTariffCategory(TariffCategory tariffCategory, String webAppName) {
		dao.updateTariffCategory(tariffCategory, webAppName);
		
	}

	@Override
	public void removeTariffCategory(TariffCategory tariffCategory, String webAppName) {
		dao.removeTariffCategory(tariffCategory, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public TariffCategory findById(String tariffCatCode, String webAppName) throws PersistenceException {
		return dao.findById(tariffCatCode, webAppName);
	}
	
	@Override
	public List<String> getTariffCategoryList(String webAppName) {
		return dao.getTariffCategoryList(webAppName);
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TariffCategoryEjb ejb=new TariffCategoryEjb();
		System.out.println(ejb.getAll("SMCTesting"));
		

	}

	



}
