package piv.ejb;
import java.util.List;

import javax.ejb.Remote;

import piv.model.PivDetail;
import piv.model.PivDetailPK;

@Remote
public interface PivDetailDaoRemote {
	void createPiv(PivDetail pivDetail , String webAppName) ;
	PivDetail findById(PivDetailPK id, String webAppName);
	void updatePivDetail(PivDetail pivDetail, String webAppName); 
	List<PivDetail> getAll( String webAppName);
	List<PivDetail> getPivDetail(String deptId, String status, String webAppName);
	List<PivDetail> getAll(String deptId, String webAppName);
	void removePivDetail(PivDetail pivDetail, String webAppName);  
	void removeAll( String webAppName);
	boolean canGeneratePiv(String deptId, String estimateNo, String referenceType, String webAppName);
	PivDetail findByReferenceNo(String deptId, String referenceNo, String referenceType, String webAppName);
	int getMaxPivSeqNo(String deptId, String referenceNo, String referenceType, String webAppName);
	String getNextPivNo(String pivNoPrefix, String webAppName);
	String createPivAutoId(PivDetail pivDetail, String pivNoPrefix, String webAppName);
	List<PivDetail> getPivDetail(String deptId, String referenceType,
			String status, String webAppName);
	List<PivDetail> getPivDetailByRefNo(String deptId, String referenceNo,
			String referenceType, String webAppName);
	PivDetail findByReferenceNo(String referenceNo, String referenceType,
			String webAppName);
	PivDetail findByPivNo(String pivNo, String webAppName);
	List<PivDetail> getPivDetailByRefNo(String deptId, String referenceNo, 
			String webAppName);
	List<PivDetail> getPivDetailByRefNoExcludingDestroy(String deptId,
			String referenceNo, String webAppName);
	List<PivDetail> getPivDetailByRefNoExcludingDestroy(String deptId,
			String referenceNo, String referenceType, String webAppName);
	PivDetail findByReferenceNoIncludingSD(String deptId, String referenceNo,
			String referenceType, String webAppName);
	PivDetail findByReferenceNoIncludingSD(String referenceNo,
			String referenceType, String webAppName);
	boolean hasActivePiv(String deptId, String referenceNo,
			String referenceType, String webAppName);
	List<String> getNewPivNos(String deptId, String referenceType,
			String status, String webAppName);
	PivDetail getPivDetailByStatus(String deptId, String referenceNo,
			String status, String webAppName);
	PivDetail getPivDetailByStatusNRefType(String deptId, String referenceNo,
			String referenceType, String status, String webAppName);
	
	
}
