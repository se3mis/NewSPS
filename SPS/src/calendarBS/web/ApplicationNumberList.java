package calendarBS.web;
import java.util.List;



public class ApplicationNumberList {

	private List<ApplicationRecord> list;
	public ApplicationNumberList(){ 		
		
	}
	public ApplicationNumberList(List<ApplicationRecord> list){ 
		this.list=list;
		
	}

	public void addList(List<ApplicationRecord> list, ApplicationRecord record){
		list.add(record);
	}	
		
	public void removeList(List<ApplicationRecord> list,int index){
		list.remove(index);
	}
	
	public int getSize(){ 
		return this.list.size();
		
	}
}
