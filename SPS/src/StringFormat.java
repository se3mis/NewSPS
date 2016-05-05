import java.util.ArrayList;
import java.util.List;

import estimate.web.MsgLineRecord;


public class StringFormat {
	
	public List<MsgLineRecord> setMsgLineList(String s){
		List<MsgLineRecord> list=new ArrayList<MsgLineRecord>();
		//MsgLineRecord lineRecord=new MsgLineRecord();
		
		//String s="A0105,10,40000#A0121,40,240000#A0111,30,180000#";
		String[] x=s.split("#");
		for (int i=0; i<=x.length-1;i++ ){
			MsgLineRecord lineRecord=new MsgLineRecord();
			System.out.println(x[i]);
			String[] y=x[i].split(",");
			//list.
			lineRecord.setCode(y[0]);
			lineRecord.setQuntity(y[1]);
			lineRecord.setCost(y[2]);
			System.out.println("Resource Code "+y[0]);
			System.out.println("Quntity       "+y[1]);
			System.out.println("Cost          "+y[2]);
			
			/*for (int j=0; j<=y.length-1;j++ ){
				System.out.println(y[j]);
				
			}*/
			list.add(lineRecord);
			lineRecord=null;

		}
		return list;
		
	}
	public static void main(String[] args){
		StringFormat sf=new StringFormat();
		String s="A0105,10,40000#A0121,40,240000#A0111,30,180000#";
		String[] x=s.split("#");
		for (int i=0; i<=x.length-1;i++ ){
			System.out.println(x[i]);
			String[] y=x[i].split(",");
			System.out.println("Resource Code "+y[0]);
			System.out.println("Quntity       "+y[1]);
			System.out.println("Cost          "+y[2]);
			
			/*for (int j=0; j<=y.length-1;j++ ){
				System.out.println(y[j]);
				
			}*/
		}
		
		System.out.println(sf.setMsgLineList("A0105,10,40000#A0121,40,240000#A0111,30,180000#"));
		
		
	}

}
