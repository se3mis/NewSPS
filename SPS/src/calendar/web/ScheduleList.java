package calendar.web;

import java.util.List;


public class ScheduleList {
	
	private List<Schedule> list;
	public ScheduleList(){ 		
		
	}
	public ScheduleList(List<Schedule> list){ 
		this.list=list;
		
	}

	public void addList(List<Schedule> list, Schedule record){
		list.add(record);
	}	
		
	public void removeList(List<Schedule> list,int index){
		list.remove(index);
	}
	
	public int getSize(){ 
		return this.list.size();
		
	}


}
