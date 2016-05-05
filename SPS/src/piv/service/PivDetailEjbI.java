package piv.service;

import java.util.List;

import piv.model.PivDetail;
import piv.model.PivDetailPK;



public interface PivDetailEjbI {
	void createPiv(PivDetail pivDetail ) ;
	PivDetail findById(PivDetailPK id);
	void updatePivDetail(PivDetail pivDetail);
	PivDetail findByReferenceNo(String deptId, String referenceNo, String referenceType);
	int getMaxPivSeqNo(String deptId, String referenceNo, String referenceType);
	String createPivAutoId(PivDetail pivDetail, String pivNoPrefix);
	List<PivDetail> getAll();
	List<PivDetail> getAll(String deptId);
	List<PivDetail> getPivDetail(String deptId, String status);
	List<PivDetail> getPivDetail(String deptId, String referenceType, String status);
	List<PivDetail> getPivDetailByRefNo(String deptId, String referenceNo, String referenceType);
	PivDetail findByReferenceNo(String referenceNo, String referenceType) ;
	List<PivDetail> getPivDetailByRefNo(String deptId, String referenceNo);
	String getNextPivNo(String pivNoPrefix);
}
