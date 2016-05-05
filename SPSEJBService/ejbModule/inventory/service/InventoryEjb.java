package inventory.service;

import java.util.List;

import inventory.ejb.InventoryBeanRemote;
import inventory.model.MatInfo;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import estimate.model.MaterialGrid;
import estimate.model.MaterialPriceInfo;

public class InventoryEjb  implements InventoryBeanRemote{
	private Context context;
	private InventoryBeanRemote bean; 
	public InventoryEjb() {
		super();
		this.bean=lookupDao();
		
	}

	private InventoryBeanRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 InventoryBeanRemote bean = (InventoryBeanRemote) context.lookup("InventoryBean/remote");
			 return bean; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	

	@Override
	public List<MaterialPriceInfo> getMaterialPriceInfo(String deptId, String notIn, String webAppName) {
		return bean.getMaterialPriceInfo(deptId, notIn, webAppName);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InventoryEjb ejb=new InventoryEjb();
		//System.out.println(ejb);
		String notIn="'00120', '00122'";
		System.out.println(ejb.getMaterialPriceInfo("501.11", notIn, "SMCTesting"));
		System.out.println(ejb.getMatListByCategory("510.20", "A", "SMCTesting"));

	}

	@Override
	public List<MaterialGrid> getMatListByCategory(String deptId, String like, String webAppName) {
		return bean.getMatListByCategory(deptId, like, webAppName);
	}

	

}
