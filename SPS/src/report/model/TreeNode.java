package report.model;

import java.util.List;
 

public class TreeNode {
	 private String id;                 // Node's ID.
	    private String name;             // Node's name property.
	    private List<TreeNode> children;
	    	    
	     public TreeNode(String id,String name)
	     {
	    	 this.id = id;
	    	 this.name = name;
	    	 
	    	 
	     }
		 
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

		public List<TreeNode> getChildren() {
			return children;
		}

		public void setChildren(List<TreeNode> children) {
			this.children = children;
		}

		
		 
 
  
 
 
 
}
