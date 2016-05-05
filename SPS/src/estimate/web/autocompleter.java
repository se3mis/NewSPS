package estimate.web;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import util.common.Format;
//import warehouse.dao.InwrhmtmDao;


import com.opensymphony.xwork2.ActionSupport;




@SuppressWarnings("serial")
public class autocompleter extends ActionSupport{
	
	// Persistence
	private EntityManagerFactory emf;
	private EntityManager em;
	private Format format;
  
	private List<String> state;
  
public String execute() throws Exception{
    /*state = new ArrayList<String>();
    state.add("Alabama");
    state.add("Alaska");
    state.add("Arizona");
    state.add("Arkansas");
    state.add("California");
    state.add("Colorado");
    state.add("Connecticut");
    state.add("Delaware");
    state.add("District of Columbia");
    state.add("Florida");
    state.add("Georgia");
    state.add("Hawaii");
    state.add("Idaho");
    state.add("Illinois");
    state.add("Indiana");
    state.add("Iowa");  
    state.add("Kansas");    
    state.add("Kentucky");
    state.add("Louisiana");  
    state.add("Maine");  
    state.add("Maryland");
    state.add("Massachusetts");    
    state.add("Michigan");
    state.add("Minnesota");
    state.add("Mississippi");
    state.add("Missouri");
    state.add("Montana");  
    state.add("Nebraska");  
    state.add("Nevada");  
    state.add("New Hampshire");    
    state.add("New Jersey");
    state.add("New Mexico");
    state.add("New York");  
    state.add("North Carolina");
    state.add("North Dakota");  
    state.add("Ohio");
    state.add("Oklahoma");
    state.add("Oregon");  
    state.add("Pennsylvania");
    state.add("Rhode Island");  
    state.add("South Carolina");  
    state.add("South Dakota");
    state.add("Tennessee");  
    state.add("Texas");    
    state.add("Utah");
    state.add("Vermont");  
    state.add("Virginia");    
    state.add("Washington");  
    state.add("West Virginia");
    state.add("Wisconsin");  
    state.add("Wyoming");*/
    setEmf(Persistence.createEntityManagerFactory("SMC"));
	setEm(emf.createEntityManager());
    //InwrhmtmDao inwrhmtmDao= new InwrhmtmDao();
    //System.out.println( inwrhmtmDao);
    //System.out.println( inwrhmtmDao.getAll(getEm()));
    //List<Inwrhmtm> list=inwrhmtmDao.getAll(getEm());
    //System.out.println( list.size());
    //System.out.println( list);
    //System.out.println( inwrhmtmDao.getMaterialtCode(list));
    //List<String> list2=inwrhmtmDao.getWarehouseMaterialCodeList("501.11", em);
    /*for (Inwrhmtm inwrhmtm : list) {
    	//state.add(inwrhmtm.getId().getMatCd().trim());
				

	}*/
    //setState(list2);
    return SUCCESS;
  }
  public List<String> getState(){
    return state;
  }
  public void setState(List<String> list){
	  this.state=list;
	    
	  }
  
  public String getCities(){
	  System.out.println("###########################################");
	  return SUCCESS;
  }
  
  public EntityManagerFactory getEmf() {
		return emf;
	}
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
	public EntityManager getEm() {
		return em;
	}
	public void setEm(EntityManager em) {
		this.em = em;
	}
	public Format getFormat() {
		return format;
	}
	public void setFormat(Format format) {
		this.format = format;
	}
}