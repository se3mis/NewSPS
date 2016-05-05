package estimateMT.web;

import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;


@SuppressWarnings("serial")
public class SaveMaterialActionMT extends ActionSupport  implements SessionAware, ParameterAware{

	private String[] chkMatCode;
	private ArrayList<ArrayList> selectMatList;
	private ArrayList<ArrayList> defaultMatList;
	
	private Map<String, Object> sessionMap;
	
	public String execute() throws Exception 
	{
		//System.out.println("session map "+sessionMap.get("OtherMaterialList"));
		try{
			
			
			//default Material list
			defaultMatList = new ArrayList<ArrayList>();
			ArrayList<String> tmpList11 = new ArrayList<String>();
			tmpList11.add("L0305");
			tmpList11.add("CABLE DUPLEX");
			tmpList11.add("MTR.");
			tmpList11.add("7");
			tmpList11.add("100");
			tmpList11.add("700");
		
			ArrayList<String> tmpList22 = new ArrayList<String>();
			tmpList22.add("K1120");
			tmpList22.add("H V TRANSFORMER");
			tmpList22.add("No.");
			tmpList22.add("6690");
			tmpList22.add("2");
			tmpList22.add("13380");
			
			ArrayList<String> tmpList33 = new ArrayList<String>();
			tmpList33.add("L0725");
			tmpList33.add("CABLE INSULATED");
			tmpList33.add("No.");
			tmpList33.add("2541");
			tmpList33.add("1");
			tmpList33.add("2541");
			
			defaultMatList.add(tmpList11);
			defaultMatList.add(tmpList22);
			defaultMatList.add(tmpList33);
		
			/*ArrayList defMatList = (ArrayList)sessionMap.get("RemoveDefMaterialList");
			if(defMatList!=null && defMatList.size()>0 )
			{
				for(int i=0;i<defMatList.size();i++)
				{
					ArrayList tmpList = (ArrayList)defMatList.get(i);
					defaultMatList.remove(tmpList);
				}
			}*/
			sessionMap.put("DefaultMaterialList", defaultMatList);
			if(chkMatCode!=null)
			{
				//ArrayList otherMatList = (ArrayList)sessionMap.get("OtherMaterialList");
				selectMatList = new ArrayList();
				for(int i=0;i<chkMatCode.length;i++)
				{
					StringTokenizer st = new StringTokenizer(chkMatCode[i],"###");
					ArrayList tmpAL = new ArrayList();
					String matCode = st.nextToken();
					String uom  = st.nextToken();
					String up  = st.nextToken();
					String matName  = st.nextToken();
					System.out.println("sel matCode "+matCode);
					
					tmpAL.add(matCode);
					tmpAL.add(matName);
					tmpAL.add(uom);
					tmpAL.add(up);
					
					selectMatList.add(tmpAL);
					//otherMatList.remove(tmpAL);
				}
				sessionMap.put("SelectMaterialList", selectMatList);
				//sessionMap.put("OtherMaterialList", otherMatList);
				//System.out.println("OtherMaterialList size "+otherMatList.size());
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}


	public String removeMaterials()
	{
		System.out.println("removeMaterials");
		return SUCCESS;
	}

	@Override
	public void setParameters(Map<String, String[]> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		// TODO Auto-generated method stub
		this.sessionMap = sessionMap;
		
	}



	public String[] getChkMatCode() {
		return chkMatCode;
	}



	public void setChkMatCode(String[] chkMatCode) {
		this.chkMatCode = chkMatCode;
	}



	public ArrayList<ArrayList> getSelectMatList() {
		return selectMatList;
	}



	public void setSelectMatList(ArrayList<ArrayList> selectMatList) {
		this.selectMatList = selectMatList;
	}



	public ArrayList<ArrayList> getDefaultMatList() {
		return defaultMatList;
	}



	public void setDefaultMatList(ArrayList<ArrayList> defaultMatList) {
		this.defaultMatList = defaultMatList;
	}
	
	

	

	
	
	
	
}
