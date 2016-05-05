package masters.service;

import java.util.List;

import javax.persistence.PersistenceException;

import masters.model.Bank;
import masters.model.BankBranch;
import masters.model.BankBranchPK;

public interface BankEjbI {
	void createBank(Bank bank);
	void updateBank(Bank bank);
	void removeBank(Bank bank);
	void removeAllBanks();
	List<Bank> getAllBanks();
	Bank findBankCode(String bankCode) throws PersistenceException;
	void createBankBranch(BankBranch bankBranch);
	void updateBankBranch(BankBranch bankBranch);
	void removeBankBranch(BankBranch bankBranch);
	void removeAllBranches();
	List<BankBranch> getAllBranches();
	List<BankBranch> getBranchList(String bankCode);
	BankBranch findBankBranchCode(BankBranchPK id) throws PersistenceException;
	List<String> getBranchCodeByBankCode(String bankCode)throws Exception;
}
