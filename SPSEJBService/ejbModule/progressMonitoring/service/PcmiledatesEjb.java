package progressMonitoring.service;

import java.util.List;
import java.util.Date;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import piv.ejb.PivDetailDaoRemote;
import piv.model.PivDetailPK;
import piv.service.PivDetailEjb;
import progressMonitoring.ejb.PcinitialDaoRemote;
import progressMonitoring.ejb.PcmiledatesDaoRemote;
import progressMonitoring.model.Pcinitialdata;
import progressMonitoring.model.PcinitialdataPK;
import progressMonitoring.model.Pcmiledates;
import util.common.Format;

public class PcmiledatesEjb implements PcmiledatesDaoRemote {
	
	
	private Context context;
	private PcmiledatesDaoRemote dao; 
	
	public PcmiledatesEjb() {
		super();
		dao=lookupPcmiledatesDao();
		
	}

	
	private PcmiledatesDaoRemote lookupPcmiledatesDao() {
		try
		{
			 context = new InitialContext();
			 PcmiledatesDaoRemote dao = (PcmiledatesDaoRemote) context.lookup("PcmiledatesDao/remote");
			 return dao; 
			 
		} catch (NamingException e){
			e.printStackTrace();
			
			throw new RuntimeException(e);
		}
		
	}


	
	public static void main(String[] args) {
		PcmiledatesEjb ejb=new PcmiledatesEjb();
		Date objDate;
		Format sss = new Format();
		objDate = sss.StrToDate("2014-08-29");
		Pcmiledates  list = ejb.getMilestoneByDate("501.20", "501.20/CNT/14/0006", "EP08",objDate,"TESTING");
		System.out.println("hiii : " + list);
	
	}


	@Override
	public void createpcmiledates(Pcmiledates pcmiledates, String webAppName) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void removePcmiledates(Pcmiledates pcmiledates, String webAppName) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Pcmiledates> getAll(String webAppName) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void delete(String deptId, String projectNumber, String webAppName) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Pcmiledates> getMilestoneList(String deptId,
			String projectNumber, String webAppName) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Pcmiledates> getDataForEstNumber(String webAppName,
			String value, String Type) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void updatepcmiledates(Pcmiledates pcmiledates, String webAppName) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Pcmiledates getMilestoneById(String deptId, String projectNumber,
			String miletoneId, String webAppName) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Pcmiledates getMilestoneByDate(String deptId, String projectNumber,
			String miletoneId, Date date, String webAppName) {
		// TODO Auto-generated method stub
		return dao.getMilestoneByDate(deptId, projectNumber, miletoneId, date, webAppName);
	}

}
