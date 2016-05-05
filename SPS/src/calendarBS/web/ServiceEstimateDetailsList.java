package calendarBS.web;

import java.util.List;

public class ServiceEstimateDetailsList {

	private List<ServiceEstimateDetails> list;
	public ServiceEstimateDetailsList(){ 		
		
	}
	public ServiceEstimateDetailsList(List<ServiceEstimateDetails> list){ 
		this.list=list;
		
	}

	public void addList(List<ServiceEstimateDetails> list, ServiceEstimateDetails record){
		list.add(record);
	}	
		
	public void removeList(List<ServiceEstimateDetails> list,int index){
		list.remove(index);
	}
	
	public int getSize(){ 
		return this.list.size();
		
	}
	
}
