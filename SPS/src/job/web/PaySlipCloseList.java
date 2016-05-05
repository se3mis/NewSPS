package job.web;

import java.util.List;



public class PaySlipCloseList {

	private List<PaySlipDetails> list;

	public List<PaySlipDetails> getList() {
		return list;
	}

	public void setList(List<PaySlipDetails> list) {
		this.list = list;
	}
	
	public PaySlipCloseList(){ 		
		
	}
	public PaySlipCloseList(List<PaySlipDetails> list){ 
		this.list=list;
		
	}

	public void addList(List<PaySlipDetails> list, PaySlipDetails record){
		list.add(record);
	}	
		
	public void removeList(List<PaySlipDetails> list,int index){
		list.remove(index);
	}
	
	public int getSize(){ 
		return this.list.size();
		
	}
}
