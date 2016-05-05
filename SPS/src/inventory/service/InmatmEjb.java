package inventory.service;

import inventory.ejb.InmatmDaoRemote;
import inventory.model.Inmatm;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

public class InmatmEjb implements InmatmEjbI {
	private Context context;
	private InmatmDaoRemote dao; 
	private String region=null;
	
	public InmatmEjb(String region) {
		super();
		this.region=region;
		this.dao=lookupDao();
		
	}

	private InmatmDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 InmatmDaoRemote dao = (InmatmDaoRemote) context.lookup("InmatmDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	
	@Override
	public void createInmatm(Inmatm inmatm) {
		dao.createInmatm(inmatm, region);
		
	}

	@Override
	public List<Inmatm> getAll1() {
		return dao.getAll1(region);
	}

	@Override
	public void updateInmatm(Inmatm inmatm) {
		dao.updateInmatm(inmatm, region);
		
	}

	@Override
	public void removeInmatm(Inmatm inmatm) {
		dao.removeInmatm(inmatm, region);
		
	}

	@Override
	public void removeAll() {
		dao.removeAll(region);
		
	}

	@Override
	public Inmatm findById(String matCd) throws PersistenceException {
		return dao.findById(matCd, region);
	}

	@Override
	public String findName(String matCd) throws PersistenceException {
		return dao.findName(matCd, region);
	}
	
	public static void main(String[] args){
		InmatmEjb ejb=new InmatmEjb("region");
		System.out.println(ejb);
	}

	@Override
	public Inmatm findMatItem(String matCd)
			throws Exception {
		return dao.findMatItem(matCd, region);
	}


}
