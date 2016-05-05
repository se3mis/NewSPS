package masters.web;



import java.util.List;
 

public class TreeNode {
	 private String id;                 // Node's ID.
	    private String name;             // Node's name property.
	    private List<TreeNode> children;
	    private String url;
	    
	     public TreeNode(String id,String name,String url)
	     {
	    	 this.id = id;
	    	 this.name = name;
	    	 this.url = url;
	    	 
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

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
		 
 
  
 
 
 
}