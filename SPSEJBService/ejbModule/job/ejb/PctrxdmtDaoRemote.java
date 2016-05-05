package job.ejb;

import javax.ejb.Remote;
import job.model.Pctrxdmt;
import job.model.PctrxdmtPK;
import job.model.Pctrxhmt;
import job.model.PctrxhmtPK;


@Remote
public interface PctrxdmtDaoRemote {
	void createPctrxdmt(Pctrxdmt pctrxdmt, String webAppName);
	void updatePctrxdmt(Pctrxdmt pctrxdmt, String webAppName)  ;
	void removePctrxdmt(Pctrxdmt pctrxdmt, String webAppName)  ;
	void removeAll(String webAppName);
	Pctrxdmt findById(PctrxdmtPK id, String webAppName);
	Pctrxdmt findBy3PK(String deptId , String docNo ,String docPf,long seqNo, String webAppName);
    
}
