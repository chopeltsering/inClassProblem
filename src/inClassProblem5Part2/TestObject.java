package inClassProblem5Part2;

import java.io.Serializable;

public class TestObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	
	
	public TestObject(String name){
		this.name = name;
	}
	
	public static void main(String[] args) {
		TestObject item = new TestObject("default");
		System.out.println("running TestObject: "+ item.name + " From Static main method");

	}
	
	public String getName(){
		return name;
	}

}
