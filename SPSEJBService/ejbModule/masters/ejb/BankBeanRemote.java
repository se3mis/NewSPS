package masters.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import masters.model.Bank;
import masters.model.BankBranch;
import masters.model.BankBranchPK;

@Remote
public interface BankBeanRemote {
	void createBank(Bank bank, String webAppName);
	void updateBank(Bank bank, String webAppName);
	void removeBank(Bank bank, String webAppName);
	void removeAllBanks(String webAppName);
	List<Bank> getAllBanks(String webAppName);
	Bank findBankCode(String bankCode, String webAppName) throws PersistenceException;
	void createBankBranch(BankBranch bankBranch, String webAppName);
	void updateBankBranch(BankBranch bankBranch, String webAppName);
	void removeBankBranch(BankBranch bankBranch, String webAppName);
	void removeAllBranches(String webAppName);
	List<BankBranch> getAllBranches(String webAppName);
	List<BankBranch> getBranchList(String bankCode, String webAppName);
	BankBranch findBankBranchCode(BankBranchPK id, String webAppName) throws PersistenceException;
	List<String> getBranchCodeByBankCode(String bankCode, String webAppName)throws PersistenceException;
}
