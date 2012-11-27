package inClassProblem5Part2;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class JSONMessage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String cmd;
	private final String name;
	private final String object;
	private final String type;
	
	// Use this constructor for a response
	
	public JSONMessage(String name, boolean isResponse) {
		if (isResponse)
			cmd = "OK";
		else
			cmd = "GET";
		this.name = name;
		object = null;
		type = null;
	}
	
	// Use this for a POST request
	
	public JSONMessage(String name, Object obj) {
		this.cmd = "POST";
		this.name = name;
		this.type = obj.getClass().getName();
		Gson gson = new Gson();
		this.object = gson.toJson(obj);
	}
	
	public JSONMessage() {
		this.cmd = "END";
		object = null;
		type = null;
		name = "";
	}
	
	public String getCmd() {
		return cmd;
	}
	public String getName() {
		return name;
	}
	public Object getObject() throws JsonSyntaxException, ClassNotFoundException {
		// The type is used to find the class associated with the object.
		// We store a String for type in order to make a JSONMessage serializable using Gson.
		Gson gson = new Gson();
		return gson.fromJson(object, Class.forName(type));
	}
	
}
