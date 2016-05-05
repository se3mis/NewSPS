package application.service;

import java.util.List;

import application.model.Spapplon;
import application.model.SpapplonPK;

public interface SpapplonEjbI {
	void createSpapplon(Spapplon spapplon );
	void updateSpapplon(Spapplon spapplon )  ;
	void removeSpapplon(Spapplon spapplon )  ;
	void removeAll();
	List<Spapplon> getAll(); 
	List<Spapplon> getAll(String deptId);
	Spapplon findById(SpapplonPK  id) ;
	Spapplon findByApplicationNo(String applicationNo);



}
