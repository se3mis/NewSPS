package costcenter.model;

import java.io.Serializable;
import java.util.Map;

@SuppressWarnings("serial")
public class TestObject implements Serializable {
	private String a;
	private String b;
	
	public TestObject() {
		super();
	}

	public TestObject(String a, String b) {
		super();
		this.a = a;
		this.b = b;
	}
	
	@SuppressWarnings("rawtypes")
	public TestObject(Map map) {
		super();
		this.a=map.values().toArray()[0].toString();
		this.b=map.values().toArray()[0].toString();
	}



	public String getA() {
		return a;
	}



	public void setA(String a) {
		this.a = a;
	}



	public String getB() {
		return b;
	}



	public void setB(String b) {
		this.b = b;
	}



	@Override
	public String toString() {
		return "TestObject [a=" + a + ", b=" + b + "]";
	}

}
