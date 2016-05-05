package job.web;

import java.util.List;

import job.web.JobDetails;;

public class JobCloseList {
	
	private List<JobDetails> list;
	public JobCloseList(){ 		
		
	}
	public JobCloseList(List<JobDetails> list){ 
		this.list=list;
		
	}

	public void addList(List<JobDetails> list, JobDetails record){
		list.add(record);
	}	
		
	public void removeList(List<JobDetails> list,int index){
		list.remove(index);
	}
	
	public int getSize(){ 
		return this.list.size();
		
	}

}
