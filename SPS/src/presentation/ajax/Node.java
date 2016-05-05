package presentation.ajax;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Node {
	public Node(String id, String text) {
		this.id = id;
		this.text = text;
	}
 
	public JSONObject toJson() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("text", text);
		json.put("leaf", leaf);
 
		if (children.isEmpty())
			return json;
 
		JSONArray jsonArray = new JSONArray();
		for (Node child : children)
			jsonArray.put(child.toJson());
 
		json.put("children", jsonArray);
		return json;
	}
	public void addChild(Node node) {
		leaf = false;
		children.add(node);
	}
 
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
 
	private String id;
	private String text;
	private boolean leaf = true;
	private List<Node> children = new ArrayList<Node>();
}
