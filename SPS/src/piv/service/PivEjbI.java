package piv.service;

import java.util.List;

import application.model.Application;
import piv.model.PivDetail;
import piv.model.PivDetailPK;

public interface PivEjbI {
	void createPiv(PivDetail pivDetail ) ;
	String createPivAutoId(PivDetail pivDetail, String pivNoPrefix);
	PivDetail findById(PivDetailPK id);
	void updatePivDetail(PivDetail pivDetail); 
	List<PivDetail> getAll();
	List<PivDetail> getPivDetail(String deptId, String status);
	List<PivDetail> getAll(String deptId);
	void removePivDetail(PivDetail pivDetail);  
	void removeAll();
	void confirmEstPivDetail(PivDetail pivDetail); //For Estimate confirmation
	void confirmEstPivDetail(PivDetail pivDetail, Boolean value); //For Estimate confirmation
	void confirm(PivDetail pivDetail, Application application);//For Application confirmation
	boolean canGeneratePiv(String deptId, String estimateNo, String referenceType);
	PivDetail findByReferenceNo(String deptId, String referenceNo, String referenceType);
	int getMaxPivSeqNo(String deptId, String referenceNo, String referenceType);
	void confirmJobPivDetail(PivDetail pivDetail);
	void confirmReInspectionPiv(PivDetail pivDetail);

}
