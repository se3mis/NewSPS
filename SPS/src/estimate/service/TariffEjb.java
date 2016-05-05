package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.TariffDaoRemote;
//import estimate.model.Tariff;
import estimate.model.Tariff;


public class TariffEjb implements TariffEjbI {

	private Context context;
	private TariffDaoRemote dao;
	private String region=null;
	
	public TariffEjb(String region) {
		super();
		this.region=region;
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
	public void createTariff(Tariff tariff) {
		dao.createTariff(tariff, region);
		
	}

	@Override
	public List<Tariff> getAll() {
		return dao.getAll(region);
	}

	@Override
	public void updateTariff(Tariff tariff) {
		dao.updateTariff(tariff, region);
		
	}

	@Override
	public void removeTariff(Tariff tariff) {
		dao.removeTariff(tariff, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Tariff findById(String tariffCode) throws PersistenceException {
		return dao.findById(tariffCode, region);
	}
	
	@Override
	public List<String> getTariffCodeList() {
		return dao.getTariffCodeList(region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TariffEjb ejb=new TariffEjb("region");
		System.out.println(ejb.getAll());

	}

	

}
