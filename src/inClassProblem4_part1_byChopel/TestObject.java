package inClassProblem4_part1_byChopel;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TestObject implements Serializable{
	
	private int value;
	private String name; 
	
	public TestObject (){
		value = 1;
		name = "hello";
	}
		
	
	public int getValue(){
		return value;
	}
	
	public String getName(){
		return name;
	}
	
}
