package costcenter.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import costcenter.ejb.CostCenterBeanRemote;
import costcenter.model.Area;
import costcenter.model.Gldeptin;
import costcenter.model.Gldeptm;
import costcenter.model.Province;

public class CostCenterEjb implements CostCenterEjbI{
	private Context context;
	private CostCenterBeanRemote bean;
	private String region=null;
	
	
	public CostCenterEjb(String region) {
		super();
		this.region=region;
		this.bean=lookupDao();
		
	}

	private CostCenterBeanRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 CostCenterBeanRemote bean = (CostCenterBeanRemote) context.lookup("CostCenterBean/remote");
			 return bean; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	

	@Override
	public List<Area> getAllAreas() {
		return bean.getAllAreas(region);
	}

	@Override
	public List<Province> getAllProvince() {
		return bean.getAllProvince(region);
	}

	@Override
	public void createGldeptin(Gldeptin gldeptin) {
		bean.createGldeptin(gldeptin, region);
		
	}

	@Override
	public List<Gldeptin> getAllGldeptin() {
		return bean.getAllGldeptin(region);
	}

	@Override
	public void updateGldeptin(Gldeptin gldeptin) {
		bean.updateGldeptin(gldeptin, region);
		
	}

	@Override
	public void removeGldeptin(Gldeptin gldeptin) {
		bean.removeGldeptin(gldeptin, region);
		
	}

	@Override
	public void removeAll() {
		bean.removeAll(region);
		
	}

	@Override
	public Gldeptin findById(String deptId) throws PersistenceException {
		return bean.findById(deptId, region);
	}

	@Override
	public Gldeptin getFindByDeptId(String deptId) {
		return bean.getFindByDeptId(deptId, region);
	}

	@Override
	public String findDeptName(String deptId) {
		return bean.findDeptName(deptId, region);
	}

	@Override
	public Gldeptm findGldeptm(Gldeptm gldeptm) {
		return bean.findGldeptm(gldeptm, region);
	}

	@Override
	public Gldeptm findGldeptm(String deptId) {
		return bean.findGldeptm(deptId, region);
	}

	@Override
	public List<String> findAreaDeptIdList(String deptId) {
		return bean.findAreaDeptIdList(deptId, region);
	}

	@Override
	public List<String> findDgmDeptIdList(String deptId) {
		return bean.findDgmDeptIdList(deptId, region);
	}

	@Override
	public List<Gldeptm> getAllGldeptm() {
		return bean.getAllGldeptm(region);
	}

	@Override
	public List<String> findAgmDeptIdList(String deptId) {
		return bean.findAgmDeptIdList(deptId, region);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//CostCenterEjb ejb=new CostCenterEjb("region");
		//System.out.println(ejb.getAllAreas());
		//System.out.println(ejb.findDeptName("514.20"));
		//System.out.println(ejb.findDeptName("443.10"));
		//System.out.println(ejb.findGldeptm("443.20"));
		//System.out.println(ejb.findGldeptm("443.50"));
		//Gldeptin gldeptin=ejb.findById("443.20");
		//System.out.println(gldeptin);
		
		
		for(int i = 7;i<=77;i+=7){
			System.out.print(i+ " ");
			
			
		}
		

	}

}
