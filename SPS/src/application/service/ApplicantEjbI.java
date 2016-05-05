package application.service;

import java.util.List;

import application.model.Applicant;

//import application.model.Applicant;
//import application.model.Application;
//import application.model.ApplicationPK;

public interface ApplicantEjbI {
	Applicant findById(String idNo);
	void createApplicant(Applicant applicant);
	List<Applicant> getAll() ;
	void updateApplicant(Applicant applicant)  ;
	void removeApplicant(Applicant applicant)  ;
	void removeAll() ;
	String genDummyId(String deptId);

	
 
}
