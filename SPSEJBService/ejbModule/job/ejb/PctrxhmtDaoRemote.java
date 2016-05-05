package job.ejb;
import javax.ejb.Remote;

import job.model.Pcestdmt;
import job.model.Pctrxdmt;
import job.model.Pctrxhmt;
import job.model.PctrxhmtPK;

@Remote
public interface PctrxhmtDaoRemote {
	
	    void createPctrxhmt(Pctrxhmt pctrxhmt , String webAppName);
		void updatePctrxhmt(Pctrxhmt pctrxhmt , String webAppName)  ;
		void removePctrxhmt(Pctrxhmt pctrxhmt , String webAppName)  ;
		void removeAll(String webAppName);
		Pctrxhmt findById(PctrxhmtPK id, String webAppName);
		Pctrxhmt findBy3PK(String deptId , String docNo ,String docPf,  String webAppName);
}
