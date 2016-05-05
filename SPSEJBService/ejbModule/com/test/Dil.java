package com.test;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.model.App;

import costcenter.model.Gldeptm;

/**
 * Session Bean implementation class Dil
 */
@Stateless
public class Dil implements DilRemote, DilLocal {
	@PersistenceContext
	EntityManager em;

    /**
     * Default constructor. 
     */
    public Dil() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String sayHello() {
		return "Hello";
	}

	@Override
	@SuppressWarnings("unchecked")
	public String findDeptName(String deptId) {
		List<Gldeptm> gldeptmList = em.createQuery("SELECT g FROM Gldeptm g WHERE g.deptId = :deptId").setParameter("deptId", deptId).getResultList();
		gldeptmList.get(0).getDeptNm();
        return gldeptmList.get(0).getDeptNm().trim();
        //return "ljvnxckjnv";
	}
	@Override
	public List<App> getAppList() {
		App app1=new App("one", "two", "three");
		App app2=new App("1", "2", "3");
		List<App> list=new ArrayList<App>();
		list.add(app1);
		list.add(app2);
		return list;
	}
    

}
