package application.ejb;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import util.emSelect.EmSelector;
import application.model.Spapplon;
import application.model.SpapplonPK;

/**
 * Session Bean implementation class SpapplonDao
 */
@Stateless
public class SpapplonDao extends EmSelector implements SpapplonDaoRemote, SpapplonDaoLocal {
	//@PersistenceContext
	//private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public SpapplonDao() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void createSpapplon(Spapplon spapplon, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).persist(spapplon);
		
	}

	@Override
	public void updateSpapplon(Spapplon spapplon, String webAppName) {
		//getEntityManager(webAppName);
		getEntityManager(webAppName).merge(spapplon);
		
	}

	@Override
	public void removeSpapplon(Spapplon spapplon, String webAppName) {
		//getEntityManager(webAppName);
		spapplon=getEntityManager(webAppName).merge(spapplon);
		getEntityManager(webAppName).remove(spapplon);
		
	}

	@Override
	public void removeAll(String webAppName) {
		//getEntityManager(webAppName);
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spapplon> getAll(String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select s from Spapplon s ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		List<Spapplon> list = query.getResultList();
        return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spapplon> getAll(String deptId, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select s from Spapplon s WHERE s.deptId =:deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<Spapplon> list = query.getResultList();
        return list;
		
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Spapplon findById(SpapplonPK id, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select s from Spapplon s WHERE s.id.deptId =:deptId AND s.id.applicationNo= :applicationNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", id.getDeptId());
		query.setParameter("applicationNo", id.getApplicationNo());
		List<Spapplon> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Spapplon findByApplicationNo(String applicationNo, String webAppName) {
		//getEntityManager(webAppName);
		String qryStr = "select s from Spapplon s WHERE s.id.applicationNo= :applicationNo";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("applicationNo", applicationNo);
		List<Spapplon> list = query.getResultList();
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}
	
	@Override
	public void calculateLoanAmounts(SpapplonPK id,double loanAmount, double noOfInstallments,double interestrate, String webAppName){
		//getEntityManager(webAppName);
		double monthlyIns =loanAmount/noOfInstallments;	
		double tempterest = (loanAmount/24)*(interestrate/100);				
		double monthlyInterest = (tempterest*((noOfInstallments)+1))/(noOfInstallments);				
		double totPayment = (monthlyIns + monthlyInterest)*(noOfInstallments)-loanAmount;				
		double monthlyInstallment = monthlyIns + monthlyInterest;
		Spapplon spapplon=findById(id, webAppName);
		System.err.println(spapplon);
		if (spapplon!=null){
		spapplon.setLoanAmount(new BigDecimal(loanAmount));
		spapplon.setInstallment(new BigDecimal(monthlyInstallment));
		spapplon.setTotalInterest(new BigDecimal(totPayment));
		updateSpapplon(spapplon, webAppName);
		}
							
		
	}

}
