package inventory.service;


import inventory.ejb.InventoryBeanRemote;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import estimate.model.MaterialGrid;
import estimate.model.MaterialPriceInfo;

public class InventoryEjb implements InventoryEjbI{
	private Context context;
	private InventoryBeanRemote bean; 
	private String region=null;
	
	public InventoryEjb(String region) {
		super();
		this.region=region;
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
	public List<MaterialPriceInfo> getMaterialPriceInfo(String deptId, String notIn) {
		return bean.getMaterialPriceInfo(deptId, notIn, region);
	}
	
	

	@Override
	public List<MaterialGrid> getMatListByCategory(String deptId, String like) {
		return bean.getMatListByCategory(deptId, like, region);
	}
	
	public static void main(String[] args){
		InventoryEjb ejb=new InventoryEjb("region");
		System.out.println(ejb);
	}

}
