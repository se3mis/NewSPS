package masters.ejb;

import java.util.List;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import masters.model.Bank;
import masters.model.BankBranch;
import masters.model.BankBranchPK;

/**
 * Session Bean implementation class BankBank
 */
@Stateless
public class BankBean implements BankBeanRemote, BankBeanLocal {
	
	
	@EJB 
	BankDaoRemote bankDaoRemote;
	@EJB
	BankBranchDaoRemote bankBranchDaoRemote;
    /**
     * Default constructor. 
     */
    public BankBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void createBank(Bank bank, String webAppName) {
		bankDaoRemote.createBank(bank, webAppName); 		
	}

	@Override
	public void updateBank(Bank bank, String webAppName) {
		bankDaoRemote.updateBank(bank, webAppName);
		
	}

	@Override
	public void removeBank(Bank bank, String webAppName) {
		bankDaoRemote.removeBank(bank, webAppName);
		
	}

	@Override
	public void removeAllBanks( String webAppName) {
		//bankDaoRemote.removeAllBanks();
		
	}

	@Override
	public List<Bank> getAllBanks(String webAppName) {
		return bankDaoRemote.getAll(webAppName);
	}

	@Override
	public Bank findBankCode(String bankCode, String webAppName) throws PersistenceException {
		return bankDaoRemote.findById(bankCode, webAppName);
	}

	@Override
	public void createBankBranch(BankBranch bankBranch, String webAppName) {
		bankBranchDaoRemote.createBankBranch(bankBranch, webAppName);		
	}

	@Override
	public void updateBankBranch(BankBranch bankBranch, String webAppName) {
		bankBranchDaoRemote.updateBankBranch(bankBranch, webAppName);
		
	}

	@Override
	public void removeBankBranch(BankBranch bankBranch, String webAppName) {
		bankBranchDaoRemote.removeBankBranch(bankBranch, webAppName);
		
	}

	@Override
	public void removeAllBranches(String webAppName) {
		bankBranchDaoRemote.removeAll(webAppName);
		
	}

	@Override
	public List<BankBranch> getAllBranches( String webAppName) {
		return bankBranchDaoRemote.getAll(webAppName);
	}

	@Override
	public BankBranch findBankBranchCode(BankBranchPK id, String webAppName) throws PersistenceException {
		return bankBranchDaoRemote.findById(id, webAppName);
	}

	@Override
	public List<BankBranch> getBranchList(String bankCode, String webAppName) {
		return bankBranchDaoRemote.getBranchList(bankCode, webAppName);
	}
	@Override
	public List<String> getBranchCodeByBankCode(String bankCode, String webAppName) {
		return bankBranchDaoRemote.getBranchCodeByBankCode(bankCode, webAppName);
	}


}
