package util.emSelect;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import estimate.model.Approval;

import application.model.Application;

public class EmSelector {
	@PersistenceContext(unitName="SMCTesting")
	private EntityManager emSMCTesting;
	@PersistenceContext(unitName="SMCR1")
	private EntityManager emSMCR1;
	@PersistenceContext(unitName="SMCR2")
	private EntityManager emSMCR2;
	@PersistenceContext(unitName="SMCR3")
	private EntityManager emSMCR3;
	@PersistenceContext(unitName="SMCR4")
	private EntityManager emSMCR4;
	@PersistenceContext(unitName="SMCAM")
	private EntityManager emSMCAM;
	public EntityManager getEntityManager(String webAppName){
		if (webAppName.equals("R1"))
				return emSMCR1;	
		else if (webAppName.equals("R2"))
			return emSMCR2;
		else if (webAppName.equals("R3"))
			return emSMCR3;
		else if (webAppName.equals("R4"))
			return emSMCR4;
		else if (webAppName.equals("AM"))
			return emSMCAM;
		
		else return emSMCTesting;	
	}
	
	

}
