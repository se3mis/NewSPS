package estimate.service; 

import java.math.BigDecimal;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import job.model.Pcestdmt;

import estimate.ejb.SpestlabDaoRemote;
import estimate.model.LabourGrid;
import estimate.model.Pcestdtt;
import estimate.model.Spestlab;
import estimate.model.SpestlabPK;

public class SpestlabEjb implements SpestlabDaoRemote{
	private Context context;
	private SpestlabDaoRemote dao;
		
	public SpestlabEjb() {
		super();
		this.dao=lookupDao();
		
	}
	
	private SpestlabDaoRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 SpestlabDaoRemote dao = (SpestlabDaoRemote) context.lookup("SpestlabDao/remote");
			 return dao; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpestlabEjb ejb=new SpestlabEjb();
		String NEW_LINE = System.getProperty("line.separator"); 
		String es="514.20/ENC/2011/1234";
		System.out.println(es.substring(7, 10));
		//ejb.insertTransportOnDTT("514.20/ENC/12/0038", "514.20", "sdfdsf");
		/*final double primitiveDouble = 0.1;  
		       final BigDecimal bdPrimDoubleCtor = new BigDecimal(primitiveDouble);  
		       final BigDecimal bdPrimDoubleValOf = BigDecimal.valueOf(primitiveDouble);  
		       final Double referenceDouble = Double.valueOf(0.1);  
		       final BigDecimal bdRefDoubleCtor = new BigDecimal(referenceDouble);  
		       final BigDecimal bdRefDoubleValOf = BigDecimal.valueOf(referenceDouble);  
		   
		       System.out.println("Primitive Double: " + primitiveDouble);  
		       System.out.println("Reference Double: " + referenceDouble);  
		       System.out.println("Primitive BigDecimal/Double via Double Ctor: " + bdPrimDoubleCtor);  
		       System.out.println("Reference BigDecimal/Double via Double Ctor: " + bdRefDoubleCtor);  
		       System.out.println("Primitive BigDecimal/Double via ValueOf: " + bdPrimDoubleValOf);  
		       System.out.println("Reference BigDecimal/Double via ValueOf: " + bdRefDoubleValOf);  
		   
		       System.out.println(NEW_LINE);  
		   
		       //  
		       // Demonstrate BigDecimal from float  
		       //  
		       final float primitiveFloat = 0.1f;  
		       final BigDecimal bdPrimFloatCtor = new BigDecimal(primitiveFloat);  
		       final BigDecimal bdPrimFloatValOf = BigDecimal.valueOf(primitiveFloat);  
		       final Float referenceFloat = Float.valueOf(0.1f);  
		       final BigDecimal bdRefFloatCtor = new BigDecimal(referenceFloat);  
		       final BigDecimal bdRefFloatValOf = BigDecimal.valueOf(referenceFloat);  
		   
		       System.out.println("Primitive Float: " + primitiveFloat);  
		       System.out.println("Reference Float: " + referenceFloat);  
		       System.out.println("Primitive BigDecimal/Float via Double Ctor: " + bdPrimFloatCtor);  
		       System.out.println("Reference BigDecimal/Float via Double Ctor: " + bdRefFloatCtor);  
		       System.out.println("Primitive BigDecimal/Float via ValueOf: " + bdPrimFloatValOf);  
		       System.out.println("Reference BigDecimal/Float via ValueOf: " + bdRefFloatValOf);  
		   
		       System.out.println(NEW_LINE);  
		   
		       //  
		       // More evidence of issues casting from float to double.  
		       //  
		       final double primitiveDoubleFromFloat = 0.1f;  
		       final Double referenceDoubleFromFloat = new Double(0.1f);  
		       final double primitiveDoubleFromFloatDoubleValue = new Float(0.1f).doubleValue();  
		   
		       System.out.println("Primitive Double from Float: " + primitiveDoubleFromFloat);  
		       System.out.println("Reference Double from Float: " + referenceDoubleFromFloat);  
		       System.out.println("Primitive Double from FloatDoubleValue: " + primitiveDoubleFromFloatDoubleValue);  
		   
		       //  
		       // Using String to maintain precision from float to BigDecimal  
		       //  
		       final String floatString = String.valueOf(new Float(0.1f));  
		       final BigDecimal bdFromFloatViaString = new BigDecimal(floatString);  
		       System.out.println("BigDecimal from Float via String.valueOf(): " + bdFromFloatViaString);  
		*/
		       //System.System.out.println(ejb.getAll("423.50/ENC/2011/0694", "423.50", "R1"));
		//System.System.out.println(ejb.getAll("514.20/ENC/2011/0803", "514.20", "ssss"));
		//System.System.out.println(ejb.getSpestlabList("423.50/SMC/11/0660", "423.50", "R1"));
		//SpestlabPK id1=new SpestlabPK();
		//id1.setEstimateNo("514.20/ENC/2011/0909");
		//id1.setDeptId("514.20");
		//id1.setLabourCode("SM6.1");
		//Spestlab spestlab1=new Spestlab();
		//spestlab1.setId(id1);
		//spestlab1.setActivityDescription("Stringing of Service wire (Single Phase- Last Span )");
		//spestlab1.setItemQty(new BigDecimal(1));
		//spestlab1.setLabourCost(new BigDecimal(910));
		//spestlab1.setLabourHours(null);
		//spestlab1.setUnitLabourHrs(null);
		//spestlab1.setUnitPrice(new BigDecimal(910));
		//ejb.createSpestlab(spestlab1, "dafbdf");
		//System.System.out.println(ejb.findById(id1, "sdfsdf"));
		//[id=SpestlabPK [],, labourCost=910, labourHours=null, unitLabourHrs=null, unitPrice=910],
		//Spestlab [id=SpestlabPK [estimateNo=514.20/ENC/2011/0909, deptId=514.20, labourCode=], activityDescription=, itemQty=, labourCost=, labourHours=null, unitLabourHrs=null, unitPrice=]
		//SpestlabPK id2=new SpestlabPK();
		//id2.setEstimateNo("514.20/ENC/2011/0909");
		//id2.setDeptId("514.20");
		//id2.setLabourCode("SM6.2");
		//Spestlab spestlab2=new Spestlab();
		//spestlab2.setId(id2);
		//spestlab2.setActivityDescription("Stringing of Service wire (Single Phase- Additional Span )");
		//spestlab2.setItemQty(new BigDecimal(9));
		//spestlab2.setLabourCost(new BigDecimal(2394));
		//spestlab2.setLabourHours(null);
		//spestlab2.setUnitLabourHrs(null);
		//spestlab2.setUnitPrice(new BigDecimal(266));
		//ejb.createSpestlab(spestlab2, "dafbdf");
		//System.System.out.println(ejb.findById(id2, "sdfsdf"));   
		//Spestlab spestlab3=ejb.findById(id2, "sdfsdf");
		//Spestlab spestlab4=ejb.findById(id1, "sdfsdf");
		//ejb.removeSpestlab(spestlab3, "sdfsdf");
		//ejb.removeSpestlab(spestlab4, "sdfsdf");
		//System.out.println(ejb.getAll("517.10","R3"));
		//System.out.println(ejb.getAll("517.10","R3").size());
		//ejb.updateContractorLabourCostByEstNo("517.10/ENC/2011/1102", "517.10","R3");
		//ejb.updateDmtHmtForLabour("517.10/ENC/2011/1336", "517.10","R3");
		//ejb.updateContractorLabourCostByEstNo("445.20/ECR/2011/0017", "445.20","R2");
		//ejb.updateDmtHmtForLabour("445.20/ECR/2011/0017", "445.20","R2");
		//ejb.updateDmtHmtForLabour("518.10/ECR/2011/8130", "518.10","R3");
		//ejb.updateContractorLabourCostByEstNo("517.10/ENC/2011/1116", "517.10","R3");
		//ejb.updateDmtHmtForLabour("517.10/ENC/2011/1116", "517.10","R3");
		
		//ejb.updateContractorLabourCostByEstNo("517.10/ENC/2011/1239", "517.10","R3");
		//ejb.updateDmtHmtForLabour("517.10/ENC/2011/1239", "517.10","R3");
		//ejb.updateContractorLabourCostByEstNo("517.10/ENC/2011/1275", "517.10","R3");
		//ejb.updateDmtHmtForLabour("517.10/ENC/2011/1275", "517.10","R3");
		///ejb.updateContractorLabourCostByEstNo("445.30/ENC/2011/3007", "445.30","R2");
		///ejb.updateDmtHmtForLabour("445.30/ENC/2011/3007", "445.30","R2");
		
		//ejb.updateContractorLabourCostByEstNo("518.10/ENC/2011/1150", "518.10","R3");
		ejb.updateContractorLabourCostByEstNo("518.10/ENC/2011/1150", "518.10","R3");
		//ejb.updateContractorLabourCostByEstNo("517.10/ENC/2011/1271", "517.10","R3");
		//ejb.updateDmtHmtForLabour("517.10/ENC/2011/1271", "517.10","R3");
		//ejb.updateLabourCode("SM2.4", "R2");

		
		/* 
		 * should be done
		 * 
		*/
		//517.10,512.20,'512.40','512.50','513.10','513.30','513.40','516.30','516.40','517.20','517.50','518.10','518.20'
		//ejb.updateContractorLabourCost("517.10","R3");
		//ejb.updateDmtHmtForLabour("517.10","R3");
		
		//ejb.insertTransport("517.10/ENC/2011/1102", "517.10", "A0203", "R3");
		//ejb.updateContractorLabourCost("512.20","R3");
		//ejb.updateDmtHmtForLabour("512.20","R3");
		
		
		//ejb.updateDmtHmtForLabour("512.20","R3");
		//ejb.updateDmtHmtForLabour("512.20","R3",0,100);
		//ejb.updateDmtHmtForLabour("512.20","R3",99,200);
		//ejb.updateDmtHmtForLabour("512.20","R3",199,300);
		
		
		//ejb.updateContractorLabourCost("512.40","R3");
		//ejb.updateDmtHmtForLabour("512.40","R3");
		//ejb.updateContractorLabourCost("512.50","R3");
		//ejb.updateDmtHmtForLabour("512.50","R3");
		
		
		
		
		
		//ejb.updateContractorLabourCost("513.10","R3");//
		//ejb.updateDmtHmtForLabour("513.10","R3");//
		
		
		//ejb.updateContractorLabourCost("513.40","R3");//
		//ejb.updateDmtHmtForLabour("513.40","R3");//
		//ejb.updateDmtHmtForLabour("513.40","R3",0,100);
		//ejb.updateDmtHmtForLabour("513.40","R3",99,200);
		//ejb.updateDmtHmtForLabour("513.40","R3",199,310);
		
		//ejb.updateContractorLabourCost("513.30","R3");//
		//ejb.updateDmtHmtForLabour("513.30","R3");//
		
		
		//ejb.updateContractorLabourCost("516.30","R3");
		//ejb.updateDmtHmtForLabour("516.30","R3");
		//ejb.updateDmtHmtForLabour("516.30","R3",0,100);
		//ejb.updateDmtHmtForLabour("516.30","R3",99,200);
		
		//ejb.updateContractorLabourCost("516.40","R3");
		//ejb.updateDmtHmtForLabour("516.40","R3");
		
		//ejb.updateContractorLabourCost("517.10","R3");
		//ejb.updateDmtHmtForLabour("517.10","R3", 0,100);
		//ejb.updateContractorLabourCostByEstNo
		
		//ejb.updateContractorLabourCostByEstNo("517.10/ENC/2011/1105", "517.10","R3");
		//ejb.updateContractorLabourCostByEstNo("517.10/ENC/2011/1120", "517.10","R3");
		//ejb.updateContractorLabourCostByEstNo("517.10/ENC/2011/1159", "517.10","R3");
		///ejb.updateContractorLabourCostByEstNo("517.10/ENC/2011/1267", "517.10","R3");
		//ejb.updateContractorLabourCostByEstNo("517.10/ENC/2011/1271", "517.10","R3");
		//ejb.updateContractorLabourCostByEstNo("517.10/ENC/2011/1313", "517.10","R3");
		//ejb.updateContractorLabourCostByEstNo("517.10/ENC/2011/1337", "517.10","R3");
		//ejb.updateContractorLabourCostByEstNo("517.10/ENC/2011/1435", "517.10","R3");
		//ejb.updateContractorLabourCostByEstNo("517.10/ENC/2011/1451", "517.10","R3");
		//ejb.updateContractorLabourCostByEstNo("517.10/ENC/2011/1471", "517.10","R3");
		//ejb.updateContractorLabourCostByEstNo("517.10/ENC/2011/1495", "517.10","R3");
		//ejb.updateContractorLabourCostByEstNo("517.10/ENC/2011/1502", "517.10","R3");
		//ejb.updateContractorLabourCostByEstNo("517.10/ENC/2011/1565", "517.10","R3");
		
		//String[] list1={ "518.20/ENC/2011/0423","518.20/ENC/2011/0329","518.20/ENC/2011/0305","518.10/ENC/2011/1307","518.10/ENC/2011/1283","518.10/ENC/2011/1229","518.10/ENC/2011/1209","518.10/ENC/2011/1110","518.10/ENC/2011/1100","518.10/ENC/2011/1092","518.10/ENC/2011/1060","518.10/ENC/2011/1045","517.20/ENC/2011/2228","517.20/ENC/2011/2220","517.20/ENC/2011/2209","517.20/ENC/2011/2088","517.20/ENC/2011/2081","517.20/ENC/2011/2053","516.30/ENC/2011/2248","516.30/ENC/2011/2226","516.30/ENC/2011/2222","516.30/ENC/2011/2185","516.30/ENC/2011/2082","516.30/ENC/2011/2055","513.40/ENC/2011/2438","513.40/ENC/2011/2399","513.40/ENC/2011/2390","513.40/ENC/2011/2365","513.40/ENC/2011/2324","513.40/ENC/2011/2302","513.40/ENC/2011/2275","513.40/ENC/2011/2236","513.40/ENC/2011/2177","513.40/ENC/2011/2064","513.40/ENC/2011/2045","513.40/ENC/2011/2017","513.40/ECR/2011/8195","513.40/ECR/2011/8194","513.40/ECR/2011/8157","513.40/ECR/2011/8154","513.40/ECR/2011/8153","513.40/ECR/2011/8150","513.40/ECR/2011/8125","513.30/ENC/2011/2163","513.30/ENC/2011/2156","513.30/ENC/2011/2155","513.30/ENC/2011/2123","513.30/ENC/2011/2122","513.30/ENC/2011/2118","513.30/ENC/2011/2102","513.30/ENC/2011/2100","513.30/ENC/2011/2099","513.30/ENC/2011/2098","513.30/ENC/2011/2040","513.30/ENC/2011/2036","513.30/ENC/2011/2019","513.30/ENC/2011/2005","513.30/ENC/2011/2004","513.10/ENC/2011/2230","513.10/ENC/2011/2222","513.10/ENC/2011/2185","513.10/ENC/2011/2083","513.10/ENC/2011/2056","513.10/ENC/2011/2026","513.10/ENC/2011/2000","512.50/ENC/2011/2269","512.50/ENC/2011/2252","512.50/ENC/2011/2207","512.50/ENC/2011/2203","512.50/ENC/2011/2095","512.50/ENC/2011/2073","512.40/ENC/2011/2189","512.40/ENC/2011/2097","512.20/ENC/2011/2319","512.20/ENC/2011/2272","512.20/ENC/2011/2243","512.20/ENC/2011/2218","512.20/ENC/2011/2173","512.20/ENC/2011/2172","512.20/ENC/2011/2117","512.20/ENC/2011/2007","512.20/ENC/2011/2003"};
		
		//String[] list1={ "512.20/ENC/2011/2117 ","512.20/ENC/2011/2173 ","512.20/ENC/2011/2218 ","512.40/ENC/2011/2189 ","512.50/ENC/2011/2073 ","512.50/ENC/2011/2095 ","512.50/ENC/2011/2203","512.50/ENC/2011/2252","512.50/ENC/2011/2269 ","513.10/ENC/2011/2000 ","513.10/ENC/2011/2056 ","513.10/ENC/2011/2083 ","513.10/ENC/2011/2185 ","513.10/ENC/2011/2230" ,"513.30/ENC/2011/2040 ","513.30/ENC/2011/2098 ","513.30/ENC/2011/2100 ","513.30/ENC/2011/2102 ","513.30/ENC/2011/2163 ","513.30/ENC/2011/2180 ","513.40/ECR/2011/8125 ","513.40/ECR/2011/8150 ","513.40/ECR/2011/8153 ","513.40/ECR/2011/8154 ","513.40/ECR/2011/8157 ","513.40/ECR/2011/8194 ","513.40/ECR/2011/8195 ","513.40/ENC/2011/2017 ","513.40/ENC/2011/2064 ","513.40/ENC/2011/2177 ","513.40/ENC/2011/2275 ","516.30/ENC/2011/2082 ","516.30/ENC/2011/2185 ","516.30/ENC/2011/2226 ","516.30/ENC/2011/2248 ","517.10/ENC/2011/1105 ","517.10/ENC/2011/1120 ","517.10/ENC/2011/1159","517.10/ENC/2011/1313","517.10/ENC/2011/1435","517.10/ENC/2011/1451","517.10/ENC/2011/1565 ","517.20/ENC/2011/2053 ","517.20/ENC/2011/2081","517.20/ENC/2011/2220 ","518.10/ENC/2011/1060 ","518.10/ENC/2011/1100 ","518.10/ENC/2011/1110 ","518.10/ENC/2011/1209 ","518.10/ENC/2011/1307 ","518.20/ENC/2011/0423"};
		
		//for (int t=0; t<list1.length; t++){
		//	ejb.updateContractorLabourCostByEstNo(list1[t].trim(),list1[t].substring(0, 6).trim(), "R3");
		//	System.out.println(list1[t].substring(0, 6)+" "+t);
		//}
		
		/*ejb.updateDmtHmtForLabour("517.10/ENC/2011/1105", "517.10","R3");
		ejb.updateDmtHmtForLabour("517.10/ENC/2011/1120", "517.10","R3");
		ejb.updateDmtHmtForLabour("517.10/ENC/2011/1159", "517.10","R3");
		ejb.updateDmtHmtForLabour("517.10/ENC/2011/1267", "517.10","R3");
		ejb.updateDmtHmtForLabour("517.10/ENC/2011/1271", "517.10","R3");
		ejb.updateDmtHmtForLabour("517.10/ENC/2011/1313", "517.10","R3");
		ejb.updateDmtHmtForLabour("517.10/ENC/2011/1337", "517.10","R3");
		ejb.updateDmtHmtForLabour("517.10/ENC/2011/1435", "517.10","R3");
		ejb.updateDmtHmtForLabour("517.10/ENC/2011/1451", "517.10","R3");
		ejb.updateDmtHmtForLabour("517.10/ENC/2011/1471", "517.10","R3");
		ejb.updateDmtHmtForLabour("517.10/ENC/2011/1495", "517.10","R3");
		ejb.updateDmtHmtForLabour("517.10/ENC/2011/1502", "517.10","R3");
		ejb.updateDmtHmtForLabour("517.10/ENC/2011/1565", "517.10","R3");
		*/
		//ejb.updateContractorLabourCost("517.10","R3");
		//ejb.updateContractorLabourCost("517.20","R3");
		//ejb.updateDmtHmtForLabour("517.20","R3");
		//ejb.updateDmtHmtForLabour("517.20","R3",0,100);
		//ejb.updateDmtHmtForLabour("517.20","R3",100,200);
		
		//ejb.updateContractorLabourCost("517.50","R3");
		//ejb.updateDmtHmtForLabour("517.50","R3");
		
		//ejb.updateContractorLabourCost("518.10","R3");//233
		//ejb.updateDmtHmtForLabour("518.10","R3");
		//ejb.updateDmtHmtForLabour("518.10","R3", 50, 150);
		//ejb.updateDmtHmtForLabour("518.10","R3", 150, 200);
		//ejb.updateDmtHmtForLabour("518.10","R3", 200, 233);
		//ejb.updateDmtHmtForLabour("518.10","R3", 230, 235);
		
		//ejb.updateContractorLabourCost("518.20","R3");
		//ejb.updateDmtHmtForLabour("518.20","R3");
		

		/*
		 * 
		 * R2
		 * 
		 */
		
		//ejb.updateContractorLabourCost("441.10", "R2");
		//ejb.updateDmtHmtForLabour("441.10", "R2");
		//ejb.updateDmtHmtForLabour("441.10", "R2",0,100);
		//ejb.updateDmtHmtForLabour("441.10", "R2",99,133);
		//ejb.updateContractorLabourCost("441.20", "R2");
		//ejb.updateDmtHmtForLabour("441.20", "R2");
		//ejb.updateDmtHmtForLabour("441.20", "R2",0,100);
		//ejb.updateDmtHmtForLabour("441.20", "R2",99,148);
		//ejb.updateContractorLabourCost("441.40", "R2");
		//ejb.updateDmtHmtForLabour("441.40", "R2");
		//ejb.updateDmtHmtForLabour("441.40", "R2",0,100);
		//ejb.updateDmtHmtForLabour("441.40", "R2",99,179);
		//ejb.updateDmtHmtForLabour("441.60", "R2",0,100);///
		//ejb.updateContractorLabourCost("442.10", "R2");
		//ejb.updateDmtHmtForLabour("442.10", "R2");
		//ejb.updateDmtHmtForLabour("442.10", "R2",0,100);
		//ejb.updateDmtHmtForLabour("442.10", "R2",0,100);
		//ejb.updateDmtHmtForLabour("442.10", "R2",99,200);
		//ejb.updateDmtHmtForLabour("442.10", "R2",199,294);
		//ejb.updateContractorLabourCost("442.40", "R2");
		//ejb.updateDmtHmtForLabour("442.40", "R2");
		//ejb.updateDmtHmtForLabour("442.40", "R2",0,58);
		//ejb.updateContractorLabourCost("442.50", "R2");
		//ejb.updateDmtHmtForLabour("442.50", "R2");
		//ejb.updateDmtHmtForLabour("442.50", "R2",0,100); 
		//ejb.updateDmtHmtForLabour("442.50", "R2",99,161); 
		//ejb.updateContractorLabourCost("442.60", "R2");
		//ejb.updateDmtHmtForLabour("442.60", "R2");
		//ejb.updateDmtHmtForLabour("442.60", "R2",0,100);
		////////////////////
		//ejb.updateContractorLabourCost("443.10", "R2");//1463
		//ejb.updateDmtHmtForLabour("443.10", "R2");
		//ejb.updateDmtHmtForLabour("443.10", "R2",0,100);
		//ejb.updateDmtHmtForLabour("443.10", "R2",99,195);
		//ejb.updateContractorLabourCost("443.20","R2");
		//ejb.updateDmtHmtForLabour("443.20", "R2");
		//ejb.updateDmtHmtForLabour("443.20", "R2",0,100);
		//ejb.updateDmtHmtForLabour("443.20", "R2",99,200);
		//ejb.updateDmtHmtForLabour("443.20", "R2",197,239);
		
		//ejb.updateContractorLabourCost("443.50","R2"); //rigth
		//ejb.updateDmtHmtForLabour("443.50", "R2");
		//ejb.updateDmtHmtForLabour("443.50", "R2",0,100);
		//ejb.updateDmtHmtForLabour("443.50", "R2",99,161);
		//ejb.updateDmtHmtForLabour("443.60", "R2");
		//ejb.updateDmtHmtForLabour("443.60", "R2",0,100);
		
		//ejb.updateDmtHmtForLabour("444.10", "R2");  //rigth
		//ejb.updateDmtHmtForLabour("444.10", "R2",0,100);
		
		//ejb.updateDmtHmtForLabour("444.10", "R2",98,167);
		//ejb.updateContractorLabourCost("444.20", "R2");//1463 //rigth
		//ejb.updateDmtHmtForLabour("444.20", "R2");
		//ejb.updateDmtHmtForLabour("444.20", "R2",0,100);//2nd
		
	///////////////////////////////////////////////////////////////////	
		
		//ejb.updateDmtHmtForLabour("444.20", "R2",98,200);//2nd
		//ejb.updateDmtHmtForLabour("444.20", "R2",198,253);//2nd
		
		//ejb.updateDmtHmtForLabour("444.40", "R2");//rigth
		//ejb.updateDmtHmtForLabour("444.40", "R2",0,100);
		//ejb.updateDmtHmtForLabour("444.40", "R2",98,128);
		//ejb.updateDmtHmtForLabour("444.60", "R2");
		
		//ejb.updateDmtHmtForLabour("444.60", "R2",0,100);
		
		//ejb.updateDmtHmtForLabour("445.10", "R2",0,100);
		
		//ejb.updateDmtHmtForLabour("445.10", "R2");
		//ejb.updateDmtHmtForLabour("445.10", "R2",98,145);
		
		//ejb.updateDmtHmtForLabour("445.20", "R2");//rigth
		//ejb.updateDmtHmtForLabour("445.20", "R2",0,100);//done
		//ejb.updateDmtHmtForLabour("445.20", "R2",98,119);//done
		
		//ejb.updateDmtHmtForLabour("445.30", "R2");//rigth
		//ejb.updateDmtHmtForLabour("445.30", "R2",0,100); 
		//ejb.updateDmtHmtForLabour("445.30", "R2",98,123); 
		//ejb.updateDmtHmtForLabour("445.60", "R2");
		//ejb.updateDmtHmtForLabour("445.60", "R2",0,100);
		
		//ejb.updateDmtHmtForLabour("446.10", "R2");//rigth
		//ejb.updateDmtHmtForLabour("446.10", "R2",0,100);//done
		//ejb.updateDmtHmtForLabour("446.10", "R2",98,134);//done
		//ejb.updateContractorLabourCost("446.20", "R2");
		//ejb.updateDmtHmtForLabour("446.20", "R2");
		//ejb.updateDmtHmtForLabour("446.20", "R2",0,100);
		//ejb.updateDmtHmtForLabour("446.20", "R2",98,156);
		//ejb.updateDmtHmtForLabour("446.30", "R2");//rigth
		//ejb.updateDmtHmtForLabour("446.30", "R2",0,100);
		//ejb.updateDmtHmtForLabour("446.60", "R2");
		/*ejb.updateDmtHmtForLabour("446.60", "R2",0,100);*/
		//ejb.getAllByType(deptId, like, webAppName);
		
	}

	@Override
	public void createSpestlab(Spestlab spestlab, String webAppName) {
		dao.createSpestlab(spestlab, webAppName);
		
	}

	@Override
	public List<Spestlab> getAll(String deptId, String webAppName) {
		return dao.getAll(deptId, webAppName);
	}

	@Override
	public List<Spestlab> getAll(String estimateNo, String deptId,
			String webAppName) {
		return dao.getAll(estimateNo, deptId, webAppName);
	}

	@Override
	public void updateSpestlab(Spestlab spestlab, String webAppName) {
		dao.updateSpestlab(spestlab, webAppName);
		
	}

	@Override
	public void removeSpestlab(Spestlab spestlab, String webAppName) {
		dao.removeSpestlab(spestlab, webAppName);
		
	}

	@Override
	public void removeAll(String webAppName) {
		dao.removeAll(webAppName);
		
	}

	@Override
	public Spestlab findById(SpestlabPK id, String webAppName)
			throws PersistenceException {
		return dao.findById(id, webAppName);
	}

	@Override
	public List<LabourGrid> getSpestlabList(String estimateNo, String deptId,
			String webAppName) {
		return dao.getSpestlabList(estimateNo, deptId, webAppName);
	}

	@Override
	public Spestlab findByEstimateNo(String estimateNo, String labourCode,
			String webAppName) {
		return dao.findByEstimateNo(estimateNo, labourCode, webAppName);
	}

	@Override
	public void updateContractorLabourCost(String deptId,String webAppName) {
		dao.updateContractorLabourCost(deptId, webAppName);
	}

	@Override
	public List<String> getAllByType(String deptId, String like,
			String webAppName) {
		return dao.getAllByType(deptId, like, webAppName);
	}

	@Override
	public BigDecimal getSpestlabSUM(String estimateNo, String deptId,
			String webAppName) {
		return dao.getSpestlabSUM(estimateNo, deptId, webAppName);
	}

	@Override
	public void updateDmtHmtForLabour(String deptId, String webAppName) {
		dao.updateDmtHmtForLabour(deptId, webAppName);
		
	}

	@Override
	public void insertTransport(Pcestdmt pcestdmt,String estimateNo, String deptId, String resCd,
			String webAppName) {
		dao.insertTransport(pcestdmt,estimateNo, deptId, resCd, webAppName);
		
	}

	@Override
	public void updateContractorLabourCostByEstNo(String estimateNo,
			String deptId, String webAppName) {
		dao.updateContractorLabourCostByEstNo(estimateNo, deptId, webAppName);
		
	}

	@Override
	public void updateDmtHmtForLabour(String estimateNo, String deptId,
			String webAppName) {
		dao.updateDmtHmtForLabour(estimateNo, deptId, webAppName);
		
	}

	@Override
	public void updateDmtHmtForLabour(String deptId, String webAppName,
			int from, int to) {
		dao.updateDmtHmtForLabour(deptId, webAppName, from, to);
		
	}

	@Override
	public void insertTransport(String estimateNo, String deptId, String resCd,
			String webAppName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertTransportOnDMT(String estimateNo, String deptId,
			String webAppName) {
		dao.insertTransportOnDMT(estimateNo, deptId, webAppName);
	}

	@Override
	public void insertTransportOnDTT(String estimateNo, String deptId,
			String webAppName) {
		dao.insertTransportOnDTT(estimateNo, deptId, webAppName);
		
	}

	@Override
	public void insertTransport(Pcestdtt pcestdtt, String estimateNo,
			String deptId, String resCd, String webAppName) {
		dao.insertTransport(pcestdtt, estimateNo, deptId, resCd, webAppName);
		
	}

	@Override
	public void updateLabourCode(String labourCode, String webAppName) {
		dao.updateLabourCode(labourCode, webAppName);
		
	}

	

}
