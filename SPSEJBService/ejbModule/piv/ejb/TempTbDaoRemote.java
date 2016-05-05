package piv.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import piv.model.PivDetail;
import piv.model.PivDetailPK;
import piv.model.TempTb;

@Remote
public interface TempTbDaoRemote {
	public void createTempTb(TempTb tempTb, String webAppName);
	public TempTb findById(String estimationNo,String deptId, String webAppName) throws PersistenceException;
	int removTempTb(TempTb tempTb,
			String webAppName)throws PersistenceException;
	public void updateTempTb(TempTb tempTb, String webAppName)throws PersistenceException;
}
