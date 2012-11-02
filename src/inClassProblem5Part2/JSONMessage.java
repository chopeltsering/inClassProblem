package inClassProblem5Part2;

public class JSONMessage{
		
	public final String cmd;
	public final String name;
	public final String object;
	
	public JSONMessage(String cmd, String name, String object){
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
	public String getObject(){
		return object;
	}
	
}
