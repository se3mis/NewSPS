package masters.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.emSelect.EmSelector;
import masters.model.BankBranch;
import masters.model.BankBranchPK;

/**
 * Session Bean implementation class BankBranchDao
 */
@Stateless
public class BankBranchDao extends EmSelector implements BankBranchDaoRemote, BankBranchDaoLocal {
	
    /**
     * Default constructor. 
     */
    public BankBranchDao() {
        // TODO Auto-generated constructor stub
    }

    
	@Override
	public void createBankBranch(BankBranch bankBranch, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(bankBranch);
		
	}

	@Override
	public void updateBankBranch(BankBranch bankBranch, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(bankBranch);
		
	}

	@Override
	public void removeBankBranch(BankBranch bankBranch, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).remove(bankBranch);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		throw new UnsupportedOperationException("Not supported yet. Ask Dileepa");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BankBranch> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select b from BankBranch b"; 
		Query query = getEntityManager(webAppName).createQuery(qryStr);
        List<BankBranch> list = query.getResultList();
        return list;
	}

	@Override
	public BankBranch findById(BankBranchPK id, String webAppName) throws PersistenceException {
		//getEntityManager(webAppName);
		return getEntityManager(webAppName).find(BankBranch.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BankBranch> getBranchList(String bankCode, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select b from BankBranch b WHERE b.id.bankCode=:bankCode"; 
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("bankCode", bankCode);
		List<BankBranch> list = query.getResultList();
        return list;
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getBranchCodeByBankCode(String bankCode, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select b.id.branchCode from BankBranch b WHERE b.id.bankCode=:bankCode"; 
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("bankCode", bankCode);
		List<String> list = query.getResultList();
		if(list !=null && list.size() > 0){
			return list;
		}else{
			return null;
		}
        
		
	}
}
