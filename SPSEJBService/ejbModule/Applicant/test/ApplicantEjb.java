package Applicant.test;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import application.model.Applicant;


import Applicant.ejb.ApplicantDaoRemote;



public class ApplicantEjb implements ApplicantDaoRemote{
	private Context context;
	private ApplicantDaoRemote dao; 
	
	public ApplicantEjb() {
		super();
		this.dao=lookupDao();
	}
	
	private ApplicantDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 //context.bind("name", "");
			 ApplicantDaoRemote dao = (ApplicantDaoRemote) context.lookup("ApplicantDao/remote");
			 //System.out.println(dao);
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicantEjb ejb=new ApplicantEjb();
		System.out.println(ejb.genDummyId("424.20","R1"));
		//System.out.println(ejb.findById("54711002","SMCTesting"));
		//System.out.println(ejb.genDummyId("510.20","SMCTesting"));
		//System.out.println(ejb.findById("D512011002","SMCTesting"));
		//System.out.println(ejb.genDummyId("510.20","SMCTesting"));
		//System.out.println(ejb.findById("D512011002","SMCTesting"));
		//System.out.println(ejb.genDummyId("510.20","SMCTesting"));
		//System.out.println(ejb.findById("D512011002","SMCTesting"));
		//System.out.println(ejb.genDummyId("510.20","SMCTesting"));
		//System.out.println(ejb.findById("D512011002","SMCTesting"));
		//System.out.println(ejb.genDummyId("510.20","SMCTesting"));
		
	}

	@Override
	public Applicant findById(String idNo, String webAppName) {
		return dao.findById(idNo, webAppName);
	}

	@Override
	public void createApplicant(Applicant applicant, String webAppName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Applicant> getAll(String webAppName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateApplicant(Applicant applicant, String webAppName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeApplicant(Applicant applicant, String webAppName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAll(String webAppName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String genDummyId(String deptId, String webAppName) {
		return dao.genDummyId(deptId, webAppName);
	}

}
