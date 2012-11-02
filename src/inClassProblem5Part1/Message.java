package inClassProblem5Part1;

import java.io.Serializable;

public class Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final String cmd;
	public final String name;
	public final Serializable object;
	
	public Message(String cmd, String name, Serializable object){
		this.cmd = cmd;
		this.name = name;
		this.object = object;
	}
	
	public String getCmd() {
		return cmd;
	}
	public String getName() {
		return name;
	}
	public Serializable getObject() {
		return object;
	}
	 
	
}
