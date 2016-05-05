package estimate.web;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class EstimateList {
	//private EstimateRecord record;
	private List<EstimateRecord> list;
	public EstimateList(){ 
		
		
	}
	public EstimateList(List<EstimateRecord> list){ 
		this.list=list;
		
	}
	
	public EstimateRecord findRecord(List<EstimateRecord> list,String resourceCode){
		EstimateRecord estimateRecord=null;
        for (int i=0; i<=list.size()-1;i++) {
        	//System.out.println(resourceCode);
        	//System.out.println(list.get(i).getResourceCode());
        	if(list.get(i).getResourceCode().equals(resourceCode)){
        		estimateRecord=list.get(i);
        		return estimateRecord;
        	}
        	//System.out.println(i);
        } 
		return estimateRecord;
	}
	
	public Integer findRecordIndex(List<EstimateRecord> list,String resourceCode){
		int i=0;
        for (i=0; i<=list.size()-1;i++) {
        	if(list.get(i).getResourceCode().equals(resourceCode)){
        		return i;
        	}
        	//System.out.println(i);
        } 
		return i;
	}
	
	public void addList(List<EstimateRecord> list, EstimateRecord record){
		list.add(record);
	}
	
		
	public void removeList(List<EstimateRecord> list,int index){
		list.remove(index);
	}
	
	public void updateRecord(EstimateRecord record,BigDecimal estimatedQuantity){
		record.setEstimatedQuantity(estimatedQuantity);
		BigDecimal doubleEstimatedCost=BigDecimal.valueOf(Double.parseDouble(record.getUnitPrice().toString())* Double.parseDouble(record.getEstimatedQuantity().toString()));
		System.out.println(doubleEstimatedCost);
		record.setEstimatedCost(doubleEstimatedCost);
	}
	
	
	
	
	
	@Override
	public String toString() {
		return "EstimateList [list=" + list + "]";
	}

	public static void main(String[] args){
		EstimateList EstimateList= new EstimateList();
		List<EstimateRecord> list = new ArrayList<EstimateRecord>(); 
		
		EstimateRecord record=new EstimateRecord("MAT-COST", "1", "A0105", "10m Pole", new BigDecimal(10), "No", new BigDecimal(20), new BigDecimal(4000.00), new BigDecimal(8000.00));
		//EstimateRecord record=new EstimateRecord("MAT-COST", "1", "1", "10m Pole", Long.valueOf("10"), "No", Long.valueOf("20"), null, Long.valueOf("80000.00"));
		EstimateList.addList(list,record);
		System.out.println("First record add "+list);
		EstimateRecord record2=new EstimateRecord("MAT-COST", "1", "A0110", "11m Pole", new BigDecimal(10), "No", new BigDecimal(20), new BigDecimal(5000.00), new BigDecimal(10000.00));
		EstimateList.addList(list,record2);
		System.out.println("Second record add "+list);
		
		EstimateRecord record3=new EstimateRecord("MAT-COST", "1", "A0120", "13m Pole", new BigDecimal(10), "No", new BigDecimal(20), new BigDecimal(6000.00), new BigDecimal(12000.00));
		EstimateList.addList(list,record3);
		System.out.println("Thired record add "+list);
		
		EstimateList EstimateList2= new EstimateList(list);
		System.out.println("new Estimate List  "+EstimateList2);
		//Collections.sort(list);
		System.out.println("Find the record  "+EstimateList.findRecord(list, "A0110"));
		EstimateRecord record4=EstimateList.findRecord(list, "A0110");
		EstimateList.updateRecord(record4, new BigDecimal(30));
		System.out.println("After merge the record  "+EstimateList.findRecord(list, "A0110"));
		System.out.println("After merge the record list "+list);
		System.out.println("Find the record  "+EstimateList.findRecordIndex(list, "A0110"));
		int index=EstimateList.findRecordIndex(list, "A0110");
		EstimateList.removeList(list, index);
		System.out.println("After record removed "+list);
		
		System.out.println(EstimateList.findRecord(list, "A0105"));
		
		
	}

}
