package masters.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Tree extends ActionSupport implements SessionAware, ParameterAware{
	private Map<String, Object> session;
	private Map <String, String[]> parameters;
	private TreeNode rootNode;
	private String selectedNode;
	
	public String execute(){
		System.out.println("jkasdhlskajdflskajdflskajdfsklajdfklsajdklsadkjsadasdj");
		this.rootNode = new TreeNode("root", "Root","");
 		List<TreeNode> root_children =   new ArrayList<TreeNode>();
		
 		//**********************
		TreeNode Accounts = new TreeNode("1Accounts","Accounts","");
		//<a href="<s:url action="logout"/>">Logout</a> 
		//String n1="<a href="+"/"";+ "<s:url action="logout"/>">Logout</a>";
		TreeNode trn2 = new TreeNode("23Cost Center wise Utility Expenses","<a href='report' target='right'>Report</a>","R0001.jsp");
		TreeNode trn3 = new TreeNode("34Cost center wise Cash Flow ","<a href='mainHelp' target='i'>Main Help</a>","R0001.jsp");
		TreeNode trn4 = new TreeNode("5Price Variance ","Price Variance ","R0001.jsp");
		TreeNode trn5 = new TreeNode("6Provincial Schedule of Debtors ","Provincial Schedule of Debtors ","R0001.jsp");
	
		List<TreeNode> Accounts_children =   new ArrayList<TreeNode>();
		 
		Accounts_children.add(trn2);
		Accounts_children.add(trn3);
		Accounts_children.add(trn4);
		Accounts_children.add(trn5);
		
		Accounts.setChildren(Accounts_children);
		
		root_children.add(Accounts);
		//**************************
		
		//**********************
		TreeNode Common  = new TreeNode("Common","Common","");
		
		 trn2 = new TreeNode("23Cost Center wise Utility Expenses","Document Inquiry Report ","R0001.jsp");
		 trn3 = new TreeNode("34Cost center wise Cash Flow ","Inquiry - Cash Book ","R0001.jsp");
		 trn4 = new TreeNode("5Price Variance ","Inquiry - Cheque Run ","R0001.jsp");
		 trn5 = new TreeNode("6Provincial Schedule of Debtors ","Inquiry - General Ledger ","R0001.jsp");
		
		List<TreeNode> Common_children =   new ArrayList<TreeNode>();
		 
		Common_children.add(trn2);
		Common_children.add(trn3);
		Common_children.add(trn4);
		Common_children.add(trn5);
		
		Common.setChildren(Common_children);
		
		root_children.add(Common);
		//**************************
		
		//**********************
		
		TreeNode Inventory  = new TreeNode("1Accounts","Accounts","");
		
		 trn2 = new TreeNode("23Cost Center wise Utility Expenses","Area stock holding position statement ","R0001.jsp");
		 trn3 = new TreeNode("34Cost center wise Cash Flow ","C/C wise Receipt & Recipt Cancellation ","R0001.jsp");
		 trn4 = new TreeNode("5Price Variance ","Cost Center wise Issue summary ","R0001.jsp");
		 trn5 = new TreeNode("6Provincial Schedule of Debtors ","Cost Center wise Receipt Summary ","R0001.jsp");
		
		List<TreeNode> Inventory_children =   new ArrayList<TreeNode>();
		 
		Inventory_children.add(trn2);
		Inventory_children.add(trn3);
		Inventory_children.add(trn4);
		Inventory_children.add(trn5);
		
		Inventory.setChildren(Inventory_children);
		
		root_children.add(Inventory);
		//**************************
		
		this.rootNode.setChildren(root_children);
		
	
		//TreeNode Common = new TreeNode("2Common ","Common ");
		//TreeNode IncomeExpenditure = new TreeNode("3Income & Expenditure ","Income & Expenditure ");
		//TreeNode Inventory = new TreeNode("4Inventory ","Inventory ");
		//TreeNode JobCardReports = new TreeNode("5Job Card Reports","Job Card Reports");
		
		return SUCCESS;
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

}
