package inClassProblem4_part1_byChopel;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TestObject implements Serializable{
	
	private int value;
	private String name;
	//private transient String name;
	//private Date timStamp;
	//private transient Panel panel;
	
	public TestObject (int value, String name){
		this.value = value;
		this.name = name;
	}
	
	public int getValue(){
		return value;
	}
	
	public String getName(){
		return name;
	}
	
}
