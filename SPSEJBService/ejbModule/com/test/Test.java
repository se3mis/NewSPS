package com.test;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.model.App;

import estimate.ejb.PcesthttDao;

public class Test {
	//@EJB
	//private BooRemote booBean;
	/**
	 * @param args
	 */
	private Context context;
	private DilRemote dao; 
	private Context context2;
	private DilRemote dao2; 
	
	public Test() {
		super();
		this.dao=lookupDao();
	}
	
	//Context context;
	public String ff(){
		try
		{
			 context = new InitialContext();
			 DilRemote boo = (DilRemote) context.lookup("Dil/remote");
			 //Object obj =context.lookup("Boo/remote");
			 //obj.
			 return boo.sayHello(); 
		} catch (NamingException e)
		{
			e.printStackTrace();
			/* I rethrow it as runtimeexception as there is really no need to continue if an exception happens and I
			 * do not want to catch it everywhere.
			 */ 
			throw new RuntimeException(e);
		}
		
	}
	public String findDeptName(String deptName){
		try
		{
			 context = new InitialContext();
			 DilRemote boo = (DilRemote) context.lookup("Dil/remote");
			 //Object obj =context.lookup("Boo/remote");
			 //obj.
			 return boo.findDeptName(deptName); 
		} catch (NamingException e)
		{
			e.printStackTrace();
			/* I rethrow it as runtimeexception as there is really no need to continue if an exception happens and I
			 * do not want to catch it everywhere.
			 */ 
			throw new RuntimeException(e);
		}
		
	}
	
	
	
	
	
	private DilRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 DilRemote dao = (DilRemote) context.lookup("Dil/remote");
			 System.out.println(dao);
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	private PcesthttDao lookupDao2() {
		try
		{
			 context2 = new InitialContext();
			 PcesthttDao dao = (PcesthttDao) context2.lookup("PcesthttDao/remote");
			 System.out.println(dao.getEstimateList("510.20",new Long(75),"SMCTesting"));
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	public List<App> getAppList() {
		return dao.getAppList();
	}
	
	public static void main(String[] args) {
		Test test=new Test();
		String s="#jsabddkjbasjkbsa";
		System.out.println(s.charAt(0)=='#');
		Calendar calendar = Calendar.getInstance(); 
		calendar.set(2010, 05, 11);// "2010 june 11"
		System.out.println(calendar.getTime());
		System.out.println(s);
		System.out.println("######################");
		//System.out.println(test.findDeptName("430.11"));	
		System.out.println("######################");
		//System.out.println(test.findDeptName("430.11"));
		System.out.println(test.lookupDao2());	
			//Test.slnfskf();
			
			
			
			
			
	}
	


}
