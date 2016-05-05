package estimate.web;

import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;


public class RemoveMaterialAction extends ActionSupport  implements SessionAware, ParameterAware{

	private String[] chkDefMatCode;
	private String[] chkSelMatCode;
	private ArrayList<ArrayList> selectMatList;
	private ArrayList<ArrayList> defaultMatList;
	
	private Map<String, Object> sessionMap;
	
	public String execute() throws Exception 
	{
		//System.out.println("session map "+sessionMap.get("OtherMaterialList"));
		try{
			
			
			
		
			ArrayList defMatList = (ArrayList)sessionMap.get("DefaultMaterialList");
			defMatList.remove(0);
			/*if(defMatList!=null && defMatList.size()>0 )
			{
				for(int i=0;i<defMatList.size();i++)
				{
					ArrayList tmpList = (ArrayList)defMatList.get(i);
					defaultMatList.remove(tmpList);
				}
			}*/
			sessionMap.put("DefaultMaterialList", defaultMatList);
			/*if(chkDefMatCode!=null)
			{
				//ArrayList otherMatList = (ArrayList)sessionMap.get("OtherMaterialList");
				selectMatList = new ArrayList();
				for(int i=0;i<chkDefMatCode.length;i++)
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
			}*/
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
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



	

	public String[] getChkDefMatCode() {
		return chkDefMatCode;
	}



	public void setChkDefMatCode(String[] chkDefMatCode) {
		this.chkDefMatCode = chkDefMatCode;
	}



	public String[] getChkSelMatCode() {
		return chkSelMatCode;
	}



	public void setChkSelMatCode(String[] chkSelMatCode) {
		this.chkSelMatCode = chkSelMatCode;
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
