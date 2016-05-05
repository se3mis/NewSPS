package progressMonitoring.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import job.model.Pcesthmt;

import estimate.model.Pcesthtt;
import estimate.model.Spstdestdmt;

import progressMonitoring.model.Pcinitialdata;
import progressMonitoring.model.PcinitialdataPK;
import util.emSelect.EmSelector;

/**
 * Session Bean implementation class PcinitialDao
 */
@Stateless
public class PcinitialDao extends EmSelector implements PcinitialDaoRemote {

	/**
	 * @see PcinitialdataDao#PcinitialdataDao()
	 */
	public PcinitialDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void createPcinitialdata(Pcinitialdata pcinitialdata,
			String webAppName) {
		getEntityManager(webAppName).persist(pcinitialdata);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pcinitialdata> getAll(String webAppName) {
		// getEntityManager(webAppName);
		String qryStr = "select p.projectNo from Pcinitialdata p";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		List<Pcinitialdata> list = query.getResultList();
		return list;
	}

	@Override
	public void updatePcinitialData(Pcinitialdata pcinitialdata,
			String webAppName) {
		getEntityManager(webAppName).merge(pcinitialdata);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pcinitialdata> getDataForEstNumber(String webAppName,
			String value, String type) {
		try {
			getEntityManager(webAppName);

			String qryStr;
			List<Spstdestdmt> list;
			String keyForType;
			Query query;
	


				/*if (type.equalsIgnoreCase("Commercial")) {
					String qryStrforCom = "SELECT pci FROM Pcinitialdata pci where  TRIM(pci.id.refNo) = :value";
					Query querycom = getEntityManager(webAppName).createQuery(
							qryStrforCom);
					querycom.setParameter("value", value);
					List<Pcinitialdata> listCom = querycom.getResultList();
					if (listCom.isEmpty()) {
						return null;
					}
					return listCom;
				}*/
				if (type.equalsIgnoreCase("constructRef")) {
					String qryStrforConRef = "SELECT pci FROM Pcinitialdata pci where  TRIM(pci.id.fileNo) = :value";
					Query queryConRef = getEntityManager(webAppName)
							.createQuery(qryStrforConRef);
					queryConRef.setParameter("value", value);
					List<Pcinitialdata> listCusRef = queryConRef
							.getResultList();
					if (listCusRef.isEmpty()) {
						return null;
					}
					return listCusRef;

				}

				/*if (type.equalsIgnoreCase("projJobNo")) {
					String qryStrforProJobNo = "SELECT pci FROM Pcinitialdata pci where TRIM(pci.projectNo) = :value";
					Query queryProJob = getEntityManager(webAppName)
							.createQuery(qryStrforProJobNo);
					queryProJob.setParameter("value", value);
					List<Pcinitialdata> listProJobNo = queryProJob
							.getResultList();
					if (listProJobNo.isEmpty()) {
						return null;
					}
					return listProJobNo;

				}*/

				return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			return null;
		}

	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getListOfJobNumbers(String webAppName, String deptId) {
		String qryStr = "select p.projectNo from Pcesthmt p where status=1 and TRIM(p.id.deptId)=:deptId ";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("deptId", deptId);
		List<String> list = query.getResultList();
		return list;

	}

	@Override
	public Pcinitialdata findById(PcinitialdataPK id, String webAppName) throws PersistenceException {
		// TODO Auto-generated method stub
		String qryStr = "SELECT g FROM Pcinitialdata g WHERE TRIM(g.id.refNo) = :refNo AND g.id.fileNo = :fileNo AND g.id.deptId = :deptId";
		Query query = getEntityManager(webAppName).createQuery(qryStr);
		query.setParameter("refNo", id.getRefNo().trim());
		query.setParameter("deptId", id.getDeptId());
		query.setParameter("fileNo", id.getFileNo().trim());
		List<Pcinitialdata> list = query.getResultList();		
		if (list.isEmpty())
			return null;
        else if (list.size() == 1)
        	return list.get(0);
        throw new NonUniqueResultException();
	}

	@Override
	public List<Pcinitialdata> getDataForDescr(String webAppName, String value,
			String Type) {
		// TODO Auto-generated method stub
		try {
			getEntityManager(webAppName);

			String qryStr;
			List<Spstdestdmt> list;
			String keyForType;
			Query query;
	


				/*if (type.equalsIgnoreCase("Commercial")) {
					String qryStrforCom = "SELECT pci FROM Pcinitialdata pci where  TRIM(pci.id.refNo) = :value";
					Query querycom = getEntityManager(webAppName).createQuery(
							qryStrforCom);
					querycom.setParameter("value", value);
					List<Pcinitialdata> listCom = querycom.getResultList();
					if (listCom.isEmpty()) {
						return null;
					}
					return listCom;
				}*/
			String like = value+"%";
				if (Type.equalsIgnoreCase("constructRef")) {
					System.out.println("hiii");
					String qryStrforConRef = "SELECT pci FROM Pcinitialdata pci where  TRIM(pci.decrp) like :like";
					System.out.println("hiii" +qryStrforConRef );
					Query queryConRef = getEntityManager(webAppName)
							.createQuery(qryStrforConRef);
					//queryConRef.setParameter("value", value);
					queryConRef.setParameter("like", like);
					System.out.println("hiii" +queryConRef.toString() );
					List<Pcinitialdata> listCusRef = queryConRef
							.getResultList();
					if (listCusRef.isEmpty()) {
						return null;
					}
					return listCusRef;

				}

				/*if (type.equalsIgnoreCase("projJobNo")) {
					String qryStrforProJobNo = "SELECT pci FROM Pcinitialdata pci where TRIM(pci.projectNo) = :value";
					Query queryProJob = getEntityManager(webAppName)
							.createQuery(qryStrforProJobNo);
					queryProJob.setParameter("value", value);
					List<Pcinitialdata> listProJobNo = queryProJob
							.getResultList();
					if (listProJobNo.isEmpty()) {
						return null;
					}
					return listProJobNo;

				}*/

				return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			return null;
		}
	}

}
