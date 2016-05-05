package application.model;

public class CityMap {
	/*
	 * 
	Colombo 10	Maradhna	541.10
	Colombo  8,9	Borella	541.20
	Colombo  4,6	Wellawatta	542.10
	Colombo  5,7	Seebal Av.	542.20
	Colombo 2,3	Sub 10	547.10
	Colombo  1,11,12	Fort , Sub 8	547.20
	Colombo  13	Grandpass	548.10
	Colombo  14,15	Madampitiya	548.20

	"Colombo_10";
	Colombo_8";
	 Colombo_9";
	 Colombo_4";
	 Colombo_6";
	 Colombo_5";
	 Colombo_7";
	 Colombo_2";
	 Colombo_3";
	 Colombo_1;
	 Colombo_11;
	 Colombo_12;
	 Colombo_13;
	 Colombo_14;
	 Colombo_15;*/
	String costCenterNo=null; 

public String mapCity(String costCenterNo, String serviceCity){
	if (costCenterNo.equals("541.00") || costCenterNo.equals("542.00") || costCenterNo.equals("547.00") || costCenterNo.equals("548.00")) {
		if (serviceCity.equals("Colombo_10")){
			return costCenterNo="541.10";
		}else if(serviceCity.equals("Colombo_8") || serviceCity.equals("Colombo_9")){
			return costCenterNo="541.20";
		}else if(serviceCity.equals("Colombo_4") || serviceCity.equals("Colombo_6")){
			return costCenterNo="542.10";
		}else if(serviceCity.equals("Colombo_5") || serviceCity.equals("Colombo_7")){
			return costCenterNo="542.20";
		}else if(serviceCity.equals("Colombo_2") || serviceCity.equals("Colombo_3")){
			return costCenterNo="547.10";	
		}else if(serviceCity.equals("Colombo_1") || serviceCity.equals("Colombo_11") || serviceCity.equals("Colombo_12")){
			return costCenterNo="547.20";
		}else if(serviceCity.equals("Colombo_13")){
			return costCenterNo="548.10";
		}else if(serviceCity.equals("Colombo_14") || serviceCity.equals("Colombo_15")){
			return costCenterNo="548.20";		
		}else{
			return costCenterNo;
		}
	}else {
		return costCenterNo;
	}
	
		
	}

public String mapArea(String costCenterNo, String serviceCity){
	if (costCenterNo.equals("541.00") || costCenterNo.equals("542.00") || costCenterNo.equals("547.00") || costCenterNo.equals("548.00")) {
		if (serviceCity.equals("Colombo_10")){
			return costCenterNo="541.00";
		}else if(serviceCity.equals("Colombo_8") || serviceCity.equals("Colombo_9")){
			return costCenterNo="541.00";
		}else if(serviceCity.equals("Colombo_4") || serviceCity.equals("Colombo_6")){
			return costCenterNo="542.00";
		}else if(serviceCity.equals("Colombo_5") || serviceCity.equals("Colombo_7")){
			return costCenterNo="542.00";
		}else if(serviceCity.equals("Colombo_2") || serviceCity.equals("Colombo_3")){
			return costCenterNo="547.00";	
		}else if(serviceCity.equals("Colombo_1") || serviceCity.equals("Colombo_11") || serviceCity.equals("Colombo_12")){
			return costCenterNo="547.00";
		}else if(serviceCity.equals("Colombo_13")){
			return costCenterNo="548.00";
		}else if(serviceCity.equals("Colombo_14") || serviceCity.equals("Colombo_15")){
			return costCenterNo="548.00";		
		}else{
			return costCenterNo;
		}
	}else {
		return costCenterNo;
	}
	
		
	}

public String depotDirect(String costCenterNo, String estimateNo){
	if (costCenterNo.equals("541.00") || costCenterNo.equals("542.00") || costCenterNo.equals("547.00") || costCenterNo.equals("548.00")) {
		if(costCenterNo.substring(0, 4).equals(estimateNo.substring(0, 4))){
			return costCenterNo=estimateNo.substring(0, 6);
		}else {
			return costCenterNo;
		}
	} else {
		return costCenterNo;
	}
}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CityMap cityMap=new CityMap();
		System.out.println("541.10 "+cityMap.mapCity("541.00", "Colombo_10"));
		System.out.println("541.20 "+cityMap.mapCity("541.00", "Colombo_8"));
		System.out.println("541.20 "+cityMap.mapCity("541.00", "Colombo_9"));
		
		System.out.println("542.10 "+cityMap.mapCity("541.00", "Colombo_4"));
		System.out.println("542.10 "+cityMap.mapCity("541.00", "Colombo_6"));
		
		System.out.println("542.20 "+cityMap.mapCity("541.00", "Colombo_5"));
		System.out.println("542.20 "+cityMap.mapCity("541.00", "Colombo_7"));
		
		System.out.println("547.10 "+cityMap.mapCity("541.00", "Colombo_2"));
		System.out.println("547.10 "+cityMap.mapCity("541.00", "Colombo_3"));
		
		System.out.println("547.20 "+cityMap.mapCity("541.00", "Colombo_1"));
		System.out.println("547.20 "+cityMap.mapCity("541.00", "Colombo_11"));
		System.out.println("547.20 "+cityMap.mapCity("541.00", "Colombo_12"));
		
		
		System.out.println("548.10 "+cityMap.mapCity("541.00", "Colombo_13"));
		
		System.out.println("548.20 "+cityMap.mapCity("541.00", "Colombo_14"));
		System.out.println("548.20 "+cityMap.mapCity("541.00", "Colombo_15"));
		
		System.out.println("###################################");
		
		System.out.println("541.20 "+cityMap.mapCity("541.00", "Colombo_8"));
		System.out.println("541.20 "+cityMap.mapCity("542.00", "Colombo_8"));
		System.out.println("541.20 "+cityMap.mapCity("547.00", "Colombo_8"));
		System.out.println("541.20 "+cityMap.mapCity("548.00", "Colombo_8"));
		
		System.out.println("###################################");
		
		System.out.println("Colombo_8 "+cityMap.mapArea("541.00", "Colombo_8"));
		System.out.println("Colombo_8 "+cityMap.mapArea("542.00", "Colombo_8"));
		System.out.println("Colombo_8 "+cityMap.mapArea("547.00", "Colombo_8"));
		System.out.println("Colombo_8 "+cityMap.mapArea("548.00", "Colombo_8"));
		
		System.out.println("###################################");
		
		System.out.println("depot "+cityMap.depotDirect("548.00", "541.20/SNC/2011/0001"));
		
		

	}

}
