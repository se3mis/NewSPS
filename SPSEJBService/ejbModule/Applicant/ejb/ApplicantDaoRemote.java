package Applicant.ejb;
import java.util.List;

import javax.ejb.Remote;

import application.model.Applicant;

@Remote
public interface ApplicantDaoRemote {
	Applicant findById(String idNo, String webAppName);
	void createApplicant(Applicant applicant, String webAppName);
	List<Applicant> getAll(String webAppName) ;
	void updateApplicant(Applicant applicant, String webAppName)  ;
	void removeApplicant(Applicant applicant, String webAppName)  ;
	void removeAll(String webAppName) ;
	String genDummyId(String deptId, String webAppName);

	//public Applicant findByApplicationNo(String applicationNo,String webAppName);
}
