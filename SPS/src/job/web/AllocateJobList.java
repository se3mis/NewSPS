package job.web;

import java.util.List;

public class AllocateJobList {
	
	private List<AllocateDetails> list;
	public AllocateJobList(){ 		
		
	}
	public AllocateJobList(List<AllocateDetails> list){ 
		this.list=list;
		
	}

	public void addList(List<AllocateDetails> list, AllocateDetails record){
		list.add(record);
	}	
		
	public void removeList(List<AllocateDetails> list,int index){
		list.remove(index);
	}
	
	public int getSize(){ 
		return this.list.size();
		
	}

}
