package offDoc.web;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import job.model.Pcesthmt;
import job.service.PcesthmtEjb;

public class mainAppJobTransferredReport {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("ddddddddddddddddddd");
		PcesthmtEjb ejb=new PcesthmtEjb("rrrrrr");
		Long bigD = new Long(1);
		List<Pcesthmt> pcesthmtList = ejb.getJobDetailList("510.20",bigD);
		
		 	 
		
		 JobTransferredReport jtr = new JobTransferredReport(pcesthmtList,new Date(),"510.20","Manoj Lakmal");
		 jtr.Print(true);
		
		System.out.println(pcesthmtList.size());
	}

}
