package offDoc.web;

 
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

 
 
import application.model.Applicant;
import application.model.Application;
import application.service.ApplicantEjb;
import application.service.ApplicationEjb;
import estimate.model.MaterialGrid;
import estimate.model.Pcesthtt;
import estimate.model.Speststd;
import estimate.model.SpeststdPK;
import estimate.service.EstimateEjb;
import estimate.service.PcesthttEjb;
import estimate.service.SpeststdEjb;

public class mainAppStandardEstimate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String applicationNo = "510.20/SNL/2010/0003";
		String costCenterNo = "510.20";
 
		
		 
	 //	EstimateEjb estimateEjb = new EstimateEjb();
	//	List<MaterialGrid> selectMatList =  estimateEjb.getMaterialGrid(applicationNo, costCenterNo);
		  
		List<MaterialGrid> selectMatList = new ArrayList<MaterialGrid>();
		for (int i = 0; i < 12; i++) {
			 
			MaterialGrid mg = new MaterialGrid ("resType :" + i + " x",
					"String resCd", 
					new BigDecimal (34234324),
					"String matNm", "String uom", 
					new BigDecimal (2345325),
					new BigDecimal (32534623), 
					new BigDecimal (234535),
					new BigDecimal (2345345),
					new  BigDecimal (345456),
					new BigDecimal (324535)
					 );
			selectMatList.add(mg);
		}
		
 
		
		StandardEstimate estimate = new StandardEstimate();
		estimate.setLineLength("29.00");
		estimate.setApplicationNumuber("2010/SNL/510.20/00003");
		estimate.setFixedCost("14,540.00");
		estimate.setConsumerName("Manoj Lakmal");
		estimate.setVariableCost("234,234.44");
		estimate.setStreetAddress("89/3, Meemanagoda Para");
		estimate.setSuburb("Kalalgoda");
		estimate.setCity("Pannipitiya");
		estimate.setPostalCode("00024");
		estimate.setOtherCost("435,545.44");
		estimate.setSubTotal("234,234.99");
		estimate.setSecurityDeposit("20,000.00");
		estimate.setVAT("2222222.22");
		estimate.setTotal("3445,45");
		estimate.setEstimatedDate("2010/01/11");
		estimate.setMaterialGrid(selectMatList);
	//	estimate.setMaterialGrid(null);
		estimate.setLabourCost("6355,234.00");
		estimate.setTransportCost(null);
		estimate.setOverheadCost(null);
		estimate.setAdditionalDeposite("23234,4354.00");
		
		PrintStandardEstimate pe = new PrintStandardEstimate (estimate);
		pe.Print(true);
		
		 
	}

}
