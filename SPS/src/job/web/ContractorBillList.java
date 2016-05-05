package job.web;
import java.util.List;


public class ContractorBillList {
	private List<ContractorBillDetails> list;
	public ContractorBillList(){ 		
		
	}
	public ContractorBillList(List<ContractorBillDetails> list){ 
		this.list=list;
		
	}

	public void addList(List<ContractorBillDetails> list, ContractorBillDetails record){
		list.add(record);
	}	
		
	public void removeList(List<ContractorBillDetails> list,int index){
		list.remove(index);
	}
	
	public int getSize(){ 
		return this.list.size();
		
	}
}
