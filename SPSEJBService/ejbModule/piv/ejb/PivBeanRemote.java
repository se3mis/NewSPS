package piv.ejb;
import java.util.List;

import javax.ejb.Remote;

import application.model.Application;

import piv.model.PivDetail;
import piv.model.PivDetailPK;

@Remote
public interface PivBeanRemote {
	void createPiv(PivDetail pivDetail , String webAppName) ;
	void IssuePiv(PivDetail pivDetail , String webAppName) ;
	String createPivAutoId(PivDetail pivDetail, String pivNoPrefix, String webAppName);
	PivDetail findById(PivDetailPK id, String webAppName);
	void updatePivDetail(PivDetail pivDetail, String webAppName); 
	List<PivDetail> getAll( String webAppName);
	List<PivDetail> getPivDetail(String deptId, String status, String webAppName);
	List<PivDetail> getAll(String deptId, String webAppName);
	void removePivDetail(PivDetail pivDetail, String webAppName);  
	void removeAll( String webAppName);
	void confirmEstPivDetail(PivDetail pivDetail, String webAppName); 
	void confirm(PivDetail pivDetail, Application application, String webAppName);
	boolean canGeneratePiv(String deptId, String estimateNo, String referenceType, String webAppName);
	PivDetail findByReferenceNo(String deptId, String referenceNo, String referenceType, String webAppName);
	void confirmJobPivDetail(PivDetail pivDetail, String webAppName);
	int getMaxPivSeqNo(String deptId, String referenceNo, String referenceType, String webAppName);
	void confirmEstPivDetail(PivDetail pivDetail, Boolean value, String webAppName);
	void confirmReInspectionPiv(PivDetail pivDetail, String webAppName);

}
