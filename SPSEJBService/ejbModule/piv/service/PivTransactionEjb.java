package piv.service;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import estimate.model.Spstdesthmt;

import application.model.Application;
import piv.ejb.PivTransactionBeanRemote;
import piv.model.PivDetail;

public class PivTransactionEjb implements PivTransactionBeanRemote{

	private Context context;
	private PivTransactionBeanRemote bean; 

	public PivTransactionEjb() {
		super();
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
	public void confirm(PivDetail pivDetail, Application application, String webAppName) {
		bean.confirm(pivDetail, application, webAppName);
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PivTransactionEjb ejb=new PivTransactionEjb();
		System.out.print(ejb);

	}

	@Override
	public void confirm(PivDetail pivDetail, Spstdesthmt spstdesthmt,
			String webAppName) {
		bean.confirm(pivDetail, spstdesthmt, webAppName);
		
	}

}
