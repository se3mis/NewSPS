package masters.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class NameValueList implements Serializable{
	private String name;
	private String value;
	
	
	
	public NameValueList(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "NameValueList [name=" + name + ", value=" + value + "]";
	}

}
