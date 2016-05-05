package com.test;
import java.util.List;

import javax.ejb.Remote;

import com.model.App;

@Remote
public interface DilRemote {
	String sayHello();
	String findDeptName(String DeptNo );
	List<App> getAppList();

}
