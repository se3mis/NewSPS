package piv.service;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import estimate.model.Spstdesthmt;

import piv.ejb.PivTransactionBeanRemote;
import piv.model.PivDetail;
import application.model.Application;

public class PivTransactionEjb implements PivTransactionEjbI {

	private Context context;
	private PivTransactionBeanRemote bean; 
	private String region=null;
	
	public PivTransactionEjb(String region) {
		super();
		this.region=region;
		this.bean=lookupBean();
	}

	private PivTransactionBeanRemote lookupBean() {
		try
		{
			 context = new InitialContext();
			 PivTransactionBeanRemote bean = (PivTransactionBeanRemote) context.lookup("PivTransactionBean/remote");
			 //System.out.println(dao);
			 return bean; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void confirm(PivDetail pivDetail, Application application) {
		bean.confirm(pivDetail, application, region);
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PivTransactionEjb ejb=new PivTransactionEjb("region");
		System.out.println(ejb);

	}

	@Override
	public void confirmPIV2(PivDetail pivDetail, Spstdesthmt spstdesthmt) {
		bean.confirm(pivDetail, spstdesthmt, region);
		
	}

	
}
