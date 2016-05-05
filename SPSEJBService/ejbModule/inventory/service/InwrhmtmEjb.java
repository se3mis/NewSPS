package inventory.service;

import java.math.BigDecimal;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import estimate.model.MaterialGrid;

import inventory.ejb.InwrhmtmDaoRemote;
import inventory.model.Inwrhmtm;
import inventory.model.MatInfo;

public class InwrhmtmEjb implements InwrhmtmDaoRemote {
	private Context context;
	private InwrhmtmDaoRemote dao; 
	public InwrhmtmEjb() {
		super();
		this.dao=lookupDao();
		
	}

	private InwrhmtmDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 InwrhmtmDaoRemote dao = (InwrhmtmDaoRemote) context.lookup("InwrhmtmDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	@Override
	public Inwrhmtm findByMatCd(String matCd, String warehouse, String webAppName) {
		return dao.findByMatCd(matCd, warehouse, webAppName);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InwrhmtmEjb ejb=new InwrhmtmEjb();
		System.out.println(ejb.findByMatCd("B2032", "501.11", "SMCTesting"));
		System.out.println(ejb.getAll("514.20", "Adfdf"));

	}

	@Override
	public List<MaterialGrid> getAll(String warehouse, String webAppName) {
		return dao.getAll(warehouse, webAppName);
	}

	@Override
	public BigDecimal findUnitPriceByMatCd(String matCd, String webAppName) {
		// TODO Auto-generated method stub
		return dao.findUnitPriceByMatCd(matCd, webAppName);
	}

	@Override
	public List<String> findNPLMatCds(String deptId, String webAppName) {
		// TODO Auto-generated method stub
		return dao.findNPLMatCds(deptId, webAppName);
	}

	@Override
	public List<String> findMatCds(String deptId, String webAppName) {
		// TODO Auto-generated method stub
		return dao.findMatCds(deptId, webAppName);
	}

	@Override
	public BigDecimal findUPByMatCd(String matCd, String warehouse,
			String webAppName) {
		return dao.findUPByMatCd(matCd,warehouse, webAppName);
	}

	/*@Override
	public List<MatInfo> getMatListByCategory(String deptId, String like) {
		return dao.getMatListByCategory(deptId, like);
	}*/

}
