package offDoc.service;

import java.util.List;

import javax.persistence.PersistenceException;

import offDoc.model.LetterReason;
import offDoc.model.LetterReasonPK;

public interface OffDocsEjbI {
	void createLetterReason(LetterReason  letterReason);
	List<LetterReason> getAll(String deptId) ;
	void updateLetterReason(LetterReason letterReason);
	void removeLetterReason(LetterReason letterReason);
	void removeAll();
	LetterReason findById(LetterReasonPK id) throws PersistenceException ;
	List<LetterReason> getReasons(String deptId, String letterType);

}
