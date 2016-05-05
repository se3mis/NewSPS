package Applicant.test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


import Applicant.ejb.ApplicantDaoRemote;



public class ApplicantDaoTest {
	private Context context;
	private ApplicantDaoRemote applicantDao; 
	
	
	
	private ApplicantDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 ApplicantDaoRemote applicantDao = (ApplicantDaoRemote) context.lookup("ApplicantDao/remote");
			 System.out.println(applicantDao);
			 return applicantDao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicantDaoTest text=new ApplicantDaoTest();
		System.out.println(text.lookupDao().findById("123", ""));
		
	}

}
