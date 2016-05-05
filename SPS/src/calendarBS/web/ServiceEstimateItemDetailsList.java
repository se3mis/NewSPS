package calendarBS.web;

import java.util.List;

public class ServiceEstimateItemDetailsList {

	private List<ServiceEstimateItemDetails> list;
	public ServiceEstimateItemDetailsList(){ 		
		
	}
	public ServiceEstimateItemDetailsList(List<ServiceEstimateItemDetails> list){ 
		this.list=list;
		
	}

	public void addList(List<ServiceEstimateItemDetails> list, ServiceEstimateItemDetails record){
		list.add(record);
	}	
		
	public void removeList(List<ServiceEstimateItemDetails> list,int index){
		list.remove(index);
	}
	
	public int getSize(){ 
		return this.list.size();
		
	}
}
