package application.ejb;
import java.util.List;

import javax.ejb.Remote;


import application.model.Spapplon;
import application.model.SpapplonPK;

@Remote
public interface SpapplonDaoRemote {
	void createSpapplon(Spapplon spapplon , String webAppName);
	void updateSpapplon(Spapplon spapplon , String webAppName)  ;
	void removeSpapplon(Spapplon spapplon , String webAppName)  ;
	void removeAll(String webAppName);
	List<Spapplon> getAll(String webAppName); 
	List<Spapplon> getAll(String deptId, String webAppName);
	Spapplon findById(SpapplonPK  id, String webAppName) ;
	void calculateLoanAmounts(SpapplonPK id,double loanAmount, double noOfInstallments,
			double interestrate, String webAppName);
	Spapplon findByApplicationNo(String applicationNo, String webAppName);
	

}
