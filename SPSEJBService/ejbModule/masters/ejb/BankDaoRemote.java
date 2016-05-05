package masters.ejb;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import masters.model.Bank;


@Remote
public interface BankDaoRemote {
	void createBank(Bank bank, String webAppName);
	void updateBank(Bank bank, String webAppName);
	void removeBank(Bank bank, String webAppName);
	void removeAll(String webAppName);
	List<Bank> getAll(String webAppName);
	Bank findById(String bankCode, String webAppName) throws PersistenceException;

}
