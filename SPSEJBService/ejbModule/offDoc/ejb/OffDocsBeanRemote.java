package offDoc.ejb;


import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import offDoc.model.LetterReason;
import offDoc.model.LetterReasonPK;

@Remote
public interface OffDocsBeanRemote {
	void createLetterReason(LetterReason  letterReason, String webAppName);
	List<LetterReason> getAll(String deptId, String webAppName) ;
	void updateLetterReason(LetterReason letterReason, String webAppName)  ;
	void removeLetterReason(LetterReason letterReason, String webAppName)  ;
	void removeAll(String webAppName) ;
	LetterReason findById(LetterReasonPK id, String webAppName) throws PersistenceException ;
	List<LetterReason> getReasons(String deptId, String letterType, String webAppName);
	

}
