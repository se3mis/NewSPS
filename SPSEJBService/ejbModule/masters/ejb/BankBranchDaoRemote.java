package masters.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import masters.model.BankBranch;
import masters.model.BankBranchPK;

@Remote
public interface BankBranchDaoRemote {
	void createBankBranch(BankBranch bankBranch, String webAppName);
	void updateBankBranch(BankBranch bankBranch, String webAppName);
	void removeBankBranch(BankBranch bankBranch, String webAppName);
	void removeAll(String webAppName);
	List<BankBranch> getAll(String webAppName);
	List<BankBranch> getBranchList(String bankCode, String webAppName);
	BankBranch findById(BankBranchPK id, String webAppName) throws PersistenceException;
	List<String> getBranchCodeByBankCode(String bankCode, String webAppName) throws PersistenceException;

}
