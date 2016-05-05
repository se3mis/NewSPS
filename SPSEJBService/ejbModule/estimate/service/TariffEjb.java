package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.TariffDaoRemote;
import estimate.model.Tariff;

public class TariffEjb implements TariffDaoRemote {

	private Context context;
	private TariffDaoRemote dao; 
	public TariffEjb() {
		super();
		this.dao=lookupDao();
		
	}
	
	private TariffDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 TariffDaoRemote dao = (TariffDaoRemote) context.lookup("TariffDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	

	@Override
	public void createTariff(Tariff tariff, String webAppName) {
		dao.createTariff(tariff, webAppName);
		
	}

	@Override
	public List<Tariff> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}

	@Override
	public void updateTariff(Tariff tariff, String webAppName) {
		dao.updateTariff(tariff, webAppName);
		
	}

	@Override
	public void removeTariff(Tariff tariff, String webAppName) {
		dao.removeTariff(tariff, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Tariff findById(String tariffCode, String webAppName) throws PersistenceException {
		return dao.findById(tariffCode, webAppName);
	}
	
	@Override
	public List<String> getTariffCodeList(String webAppName) {
		return dao.getTariffCodeList(webAppName);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TariffEjb ejb=new TariffEjb();
		System.out.println(ejb);
		System.out.println(ejb.findById("11", "SMCTesting"));

	}

	

}
