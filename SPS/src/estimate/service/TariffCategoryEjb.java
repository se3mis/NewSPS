package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.TariffCategoryDaoRemote;
import estimate.model.TariffCategory;
//import estimate.model.TariffCategory;

public class TariffCategoryEjb implements TariffCategoryEjbI {

	private Context context;
	private TariffCategoryDaoRemote dao; 
	private String region=null;
	
	public TariffCategoryEjb(String region) {
		super();
		this.region=region;
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
	public void createTariffCategory(TariffCategory tariffCategory) {
		dao.createTariffCategory(tariffCategory, region);
		
	}

	@Override
	public List<TariffCategory> getAll() {
		return dao.getAll(region);
	}

	@Override
	public void updateTariffCategory(TariffCategory tariffCategory) {
		dao.updateTariffCategory(tariffCategory, region);
		
	}

	@Override
	public void removeTariffCategory(TariffCategory tariffCategory) {
		dao.removeTariffCategory(tariffCategory, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public TariffCategory findById(String tariffCatCode) throws PersistenceException {
		return dao.findById(tariffCatCode, region);
	}
	
	@Override
	public List<String> getTariffCategoryList() {
		return dao.getTariffCategoryList(region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TariffCategoryEjb ejb=new TariffCategoryEjb("region");
		System.out.println(ejb.getAll());


	}

	

}
