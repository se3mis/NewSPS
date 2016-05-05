package masters.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import masters.ejb.BankBeanRemote;
import masters.model.Bank;
import masters.model.BankBranch;
import masters.model.BankBranchPK;

public class BankEjb implements BankBeanRemote{
	private Context context;
	private BankBeanRemote bean; 
	public BankEjb() {
		super();
		this.bean=lookupDao();
		
	}

	private BankBeanRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 BankBeanRemote bean = (BankBeanRemote) context.lookup("BankBean/remote");
			 return bean; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	@Override
	public void createBank(Bank bank, String webAppName) {
		bean.createBank(bank, webAppName);
		
	}

	@Override
	public void updateBank(Bank bank, String webAppName) {
		bean.updateBank(bank, webAppName);
		
	}

	@Override
	public void removeBank(Bank bank, String webAppName) {
		bean.removeBank(bank, webAppName);
		
	}

	@Override
	public void removeAllBanks(String webAppName) {
		bean.removeAllBanks(webAppName);
		
	}

	@Override
	public List<Bank> getAllBanks(String webAppName) {
		return bean.getAllBanks(webAppName);
	}

	@Override
	public Bank findBankCode(String bankCode, String webAppName) throws PersistenceException {
		return bean.findBankCode(bankCode, webAppName);
	}

	@Override
	public void createBankBranch(BankBranch bankBranch, String webAppName) {
		bean.createBankBranch(bankBranch, webAppName);
		
	}

	@Override
	public void updateBankBranch(BankBranch bankBranch, String webAppName) {
		bean.updateBankBranch(bankBranch, webAppName);
		
	}

	@Override
	public void removeBankBranch(BankBranch bankBranch, String webAppName) {
		bean.removeAllBranches( webAppName);
		
	}

	@Override
	public void removeAllBranches( String webAppName) {
		bean.removeAllBranches( webAppName);
		
	}

	@Override
	public List<BankBranch> getAllBranches( String webAppName) {
		return bean.getAllBranches( webAppName);
	}

	@Override
	public List<BankBranch> getBranchList(String bankCode, String webAppName) {
		return bean.getBranchList(bankCode, webAppName);
	}

	@Override
	public BankBranch findBankBranchCode(BankBranchPK id, String webAppName) throws PersistenceException {
		return bean.findBankBranchCode(id, webAppName);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BankEjb ejb=new BankEjb();
		System.out.println(ejb);
		Bank bank=new Bank();
		bank.setBankCode("4444");
		bank.setBankName("3333");
		System.out.println(ejb.findBankCode("7135", "SMCTesting"));
		//System.out.println(ejb.findBankBranchCode(id, webAppName)("045", "SMCTesting"));
		System.out.println(ejb.findBankCode("7135", "SMCTesting"));
		//ejb.updateBank(bank, "SMCTesting");

	}

	@Override
	public List<String> getBranchCodeByBankCode(String bankCode, String webAppName)
			throws PersistenceException {
		return bean.getBranchCodeByBankCode(bankCode, webAppName);
	}

	

}
