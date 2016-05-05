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

public class BankEjb implements BankEjbI {
	private Context context;
	private BankBeanRemote bean;
	private String region=null;
	
	public BankEjb(String region) {
		super();
		this.region=region;
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
	public void createBank(Bank bank) {
		bean.createBank(bank, region);
		
	}

	@Override
	public void updateBank(Bank bank) {
		bean.updateBank(bank, region);
		
	}

	@Override
	public void removeBank(Bank bank) {
		bean.removeBank(bank, region);
		
	}

	@Override
	public void removeAllBanks() {
		bean.removeAllBanks(region);
		
	}

	@Override
	public List<Bank> getAllBanks() {
		return bean.getAllBanks(region);
	}

	@Override
	public Bank findBankCode(String bankCode) throws PersistenceException {
		return bean.findBankCode(bankCode, region);
	}

	@Override
	public void createBankBranch(BankBranch bankBranch) {
		bean.createBankBranch(bankBranch, region);
		
	}

	@Override
	public void updateBankBranch(BankBranch bankBranch) {
		bean.updateBankBranch(bankBranch, region);
		
	}

	@Override
	public void removeBankBranch(BankBranch bankBranch) {
		bean.removeAllBranches(region);
		
	}

	@Override
	public void removeAllBranches() {
		bean.removeAllBranches(region);
		
	}

	@Override
	public List<BankBranch> getAllBranches() {
		return bean.getAllBranches(region);
	}

	@Override
	public List<BankBranch> getBranchList(String bankCode) {
		return bean.getBranchList(bankCode, region);
	}

	@Override
	public BankBranch findBankBranchCode(BankBranchPK id) throws PersistenceException {
		return bean.findBankBranchCode(id, region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BankEjb ejb=new BankEjb("region");
		System.out.println(ejb);

	}

	@Override
	public List<String> getBranchCodeByBankCode(String bankCode)
			throws Exception {
		return bean.getBranchCodeByBankCode(bankCode, region);
	}
	
}
