package estimate.web;

import java.util.List;

public class PostJobList {
	
	private List<JobRecord> list;
	public PostJobList(){ 		
		
	}
	public PostJobList(List<JobRecord> list){ 
		this.list=list;
		
	}

	public void addList(List<JobRecord> list, JobRecord record){
		list.add(record);
	}	
		
	public void removeList(List<JobRecord> list,int index){
		list.remove(index);
	}
	
	public int getSize(){ 
		return this.list.size();
		
	}

}
