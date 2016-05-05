package masters.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.emSelect.EmSelector;
import masters.model.Bank;


/**
 * Session Bean implementation class BankBean
 */
@Stateless
public class BankDao extends EmSelector implements BankDaoRemote, BankDaoLocal {
	
    /**
     * Default constructor. 
     */
    public BankDao() {
        // TODO Auto-generated constructor stub
    }
    
    
	@Override
	public void createBank(Bank bank, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(bank);
		
	}

	@Override
	public void updateBank(Bank bank, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(bank);
		
	}

	@Override
	public void removeBank(Bank bank, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).remove(bank);
		
	}

	@Override
	public void removeAll( String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bank> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select b from Bank b"; 
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<Bank> list = query.getResultList();
        return list;
	}

	@Override
	public Bank findById(String bankCode, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		return getEntityManager(webAppName).find(Bank.class, bankCode);
	}

}
