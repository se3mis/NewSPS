package application.service;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import application.model.Applicant;

//import application.model.Applicant;
//import application.model.Application;
//import application.model.ApplicationPK;

//import costcenter.ejb.GldeptmDaoRemote;


import Applicant.ejb.ApplicantDaoRemote;
//import application.model1.Applicant;

public class ApplicantEjb implements ApplicantEjbI{
	private Context context;
	private ApplicantDaoRemote dao; 
	private String region=null;
	
	
	public ApplicantEjb(String region) {
		super();
		this.region=region;
		dao=lookupDao();
		
	}
	
	
	
	
	private ApplicantDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 ApplicantDaoRemote dao = (ApplicantDaoRemote) context.lookup("ApplicantDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	public Applicant findById(String idNo) {
		return dao.findById(idNo, region);
	}
		
	@Override
	public void createApplicant(Applicant applicant) {
		
		dao.createApplicant(applicant, region);
		
	}
	
	



	@Override
	public List<Applicant> getAll() {
		return dao.getAll(region);
	}

	@Override
	public void updateApplicant(Applicant applicant) {
		dao.updateApplicant(applicant, region);
		
	}

	@Override
	public void removeApplicant(Applicant applicant) {
		dao.removeApplicant(applicant, region);
		
	}

	@Override
	public void removeAll() {
		// TODO Auto-generated method stub
		
	}
	
	
	




	@Override
	public String genDummyId(String deptId) {
		return dao.genDummyId(deptId, region);
	}
	
	public static void main(String[]args ){
		ApplicantEjb ejb=new ApplicantEjb("SPSTesting");
		//applicantEjb.applicantDao.findById("123");
		System.out.println(ejb.findById("5858"));
		System.out.println(ejb.findById("34567890"));
		System.out.println(ejb.findById("123"));
		//System.out.println(ejb.ffindByApplicationNo("510.20/SNL/2010/0003"));
		//Applicant applicant =new Applicant("987654","NIC", "Dileepa", "Waduge", "92", "Dalugama", "Kelaniya",null, null, null, "wadda", "si", "Y", "N", null, null, null, null, null, null);
		//applicantEjb.createApplicant(applicant);
	}




	




	




	




	




	



	





}
