package job.web;

import java.util.List;



public class TimeCardList {

	private List<TimeCardDetails> list;
	public TimeCardList(){ 		
		
	}
	public TimeCardList(List<TimeCardDetails> list){ 
		this.list=list;
		
	}

	public  void addList(List<TimeCardDetails> list, TimeCardDetails record){
		list.add(record);
	}	
		
	public void removeList(List<TimeCardDetails> list,int index){
		list.remove(index);
	}
	
	public int getSize(){ 
		return this.list.size();
		
	}
}
