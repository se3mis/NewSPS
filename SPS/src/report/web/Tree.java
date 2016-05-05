package report.web;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import report.model.ReportSps;
import report.model.TreeNode;
import report.service.ReportEjb;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Tree extends ActionSupport implements SessionAware, ParameterAware{
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private TreeNode rootNode;
	private String selectedNode;
	private String[] reportNames=new String[10];
	private int[] reportId=new int[10];
	static  List<TreeNode> pvi_children =   new ArrayList<TreeNode>();//can remove
	private String path;
	private String nId;
	static List<ReportSps> reportList2;
	static List<String> categoryList;
	
	
	public String execute(){
	
	
	
		this.rootNode = new TreeNode("root", "<font size=3.5>SPS Reports</font>");
 		List<TreeNode> root_children =   new ArrayList<TreeNode>();		
 		System.out.println("jkasdhlskajdflskajdflskajdfsklajdfklsajdklsadkjsadasdj");
 		
 		//------------------------create children node(reports) list 4 single category----------------------------------------
 		// 
 	
		
 		
 		
 		//---------create parent nodes for report categories ---------------------------------------------
		
 		categoryList=new ArrayList<String>();
 		
 		ReportEjb reprotEjb=new ReportEjb("region");
 		categoryList=reprotEjb.getCategoryList();
 		System.out.println(categoryList.get(0)+"''''''''''''''''''''''''''''");		
 		
		
 		//**********************Generate tree**********************************
 		
 		for(int j=0; j<categoryList.size();j++){
 			
 			TreeNode pvi = new TreeNode(String.valueOf(j),"<font size=3>"+categoryList.get(j)+"</font>");
 			List<TreeNode> children =   new ArrayList<TreeNode>();
 			
 			List<ReportSps> reportList3=reprotEjb.getReportList(categoryList.get(0));
 				
 			
			//------------------create  nodes for reports as children ----------------------------------------------------------------------
			
						for(int i=0;i<reportList3.size();i++){
							
							this.path="<font size='2.5'><a href='piv?&idd="+reportList3.get(i).getReportId()+"' target='right'>"+reportList3.get(i).getReportName()+"</a></font>";
							this.nId="report"+reportList3.get(i).getReportId();							
							TreeNode trr=new TreeNode(nId,path);
							children.add(trr);
							
						}
			
			//---------------- add these reports to report category node(parent node)------------------
			pvi.setChildren(children);
			root_children.add(pvi);	
			
			
 		}
 		
 		
 		this.rootNode.setChildren(root_children);
 		
		
		return SUCCESS;
	}
	public String getSessionKey(String key) {
		return getSession().get(key).toString();
	}
	
	public void setLoggedData() {
		   System.out.println(getSession());
		   
	   }
	
	
	
	public Map<String, Object> getSession() {
		return session;

	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public Map <String, String[]> getParameters() {
		return parameters;
	}
		

	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters=parameters;
		
	}
	
	public TreeNode getRootNode() {
		return rootNode;
	}

	public void setRootNode(TreeNode rootNode) {
		this.rootNode = rootNode;
	}
	

	public String getSelectedNode() {
		return selectedNode;
	
	}

	public void setSelectedNode(String selectedNode) {
		this.selectedNode = selectedNode;
		
	}
	public String[] getReportNames() {
		return reportNames;
	}
	public void setReportNames(String[] reportNames) {
		this.reportNames = reportNames;
	}
	public int[] getReportId() {
		return reportId;
	}
	public void setReportId(int[] reportId) {
		this.reportId = reportId;
	}
	 public static void main(String[] args)
	{
		Tree tree=new Tree();
		try{
		tree.execute();
		}catch(Exception e)
		{
			System.out.println(e.getMessage().toString());
		}
		
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		path = path;
	}
	public String getnId() {
		return nId;
	}
	public void setnId(String nId) {
		this.nId = nId;
	}
}
