package inClassProblem6part1;

import java.io.IOException;


public class Get implements EventHandler{

	Vault vault;
	public Get(){
		vault = new Vault();
	}
	public Object handleEvent(Event event) throws IOException, InterruptedException{
		System.out.println("inside get event handler");
			return vault.getObject(event.getObjectName());
	}
	
	public String toString(){
		return "inside get event Handler";
	}

}
