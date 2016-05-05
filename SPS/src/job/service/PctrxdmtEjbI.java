package job.service;

import job.model.Pctrxdmt;
import job.model.PctrxdmtPK;

public interface PctrxdmtEjbI {
	
	void createPctrxdmt(Pctrxdmt pctrxdmt);
	void updatePctrxdmt(Pctrxdmt pctrxdmt)  ;
	void removePctrxdmt(Pctrxdmt pctrxdmt)  ;
	void removeAll(String webAppName);
	Pctrxdmt findById(PctrxdmtPK id);
	Pctrxdmt findBy3PK(String deptId , String docNo ,String docPf,long seqNo);
 
}
