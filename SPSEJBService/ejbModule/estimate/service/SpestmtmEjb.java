package estimate.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import estimate.ejb.SpestmtmDaoRemote;
import estimate.model.Spestmtm;
import estimate.model.SpestmtmPK;

public class SpestmtmEjb implements SpestmtmDaoRemote{
	private Context context;
	private SpestmtmDaoRemote dao; 
	public SpestmtmEjb() {
		super();
		this.dao=lookupDao();
		
	}
	
	private SpestmtmDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpestmtmDaoRemote dao = (SpestmtmDaoRemote) context.lookup("SpestmtmDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	public void createSpestmtm(Spestmtm spestmtm, String webAppName) {
		dao.createSpestmtm(spestmtm, webAppName);
		
	}

	@Override
	public List<Spestmtm> getAll(String webAppName) {
		return dao.getAll(webAppName);
	}
	
	@Override
	public List<Spestmtm> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}

	@Override
	public void updateSpestmtm(Spestmtm spestmtm, String webAppName) {
		dao.updateSpestmtm(spestmtm, webAppName);
		
	}

	@Override
	public void removeSpestmtm(Spestmtm spestmtm, String webAppName) {
		dao.removeSpestmtm(spestmtm, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Spestmtm findById(SpestmtmPK id, String webAppName) throws PersistenceException {
		return dao.findById(id, webAppName);
	}
	
	@Override
	public List<Spestmtm> estimateMaterials(Long phase, Long connectionType, String wiringType, String deptId, String webAppName) {
		return dao.estimateMaterials(phase, connectionType, wiringType, deptId, webAppName);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpestmtmEjb ejb=new SpestmtmEjb();
		System.out.println(ejb.getAll("423.10", "R1"));
		System.out.println(ejb.estimateMaterials(new Long("1"), new Long("15"), "UG", "514.20", "SMCTesting"));
		System.out.println(ejb.estimateMaterials(new Long("1"), new Long("15"), "OH", "510.20", "SMCTesting"));
		//System.out.println(ejb.estimateMaterials(new Long("1"), new Long("15"), new Long("0"), new Long("30"),"OH", "510.20"));
		/*SpestmtmPK id= new SpestmtmPK();
		id.setPhase(new Long("1"));
		id.setConnectionType(new Long("15"));
		id.setFromLength(new Long("0"));
		id.setToLength(new Long("30"));
		id.setMatCd("B2040");
		id.setWiringType("UG");
		id.setDeptId("510.20");
		Spestmtm spestmtm=new Spestmtm();
		spestmtm=ejb.findById(id);
		System.out.println(spestmtm);
		//ejb.removeSpestmtm(spestmtm);*/
		
	}

	

	

}
