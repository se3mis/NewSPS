package masters.web;

import java.util.List;

import estimate.model.Splbsrvc;
import estimate.service.SplbsrvcEjb;

public class testApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SplbsrvcEjb ejb = new SplbsrvcEjb("A");
		 
		List<Splbsrvc> viewList = ejb.getAll("514.20");
		System.out.println(viewList);
	}

}
