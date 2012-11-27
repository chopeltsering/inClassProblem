package inClassProblem6part1;

import java.io.IOException;

public class Store implements EventHandler{

	Vault vault;
	public Store(){
		vault = new Vault();
	}
	public Object handleEvent(Event event) throws IOException, InterruptedException{
		System.out.println("inside Store event handler");
		vault.addObject(event.getObjectName(), event.getObject());
		return null;
	}
	public String toString(){
		return "inside store event Handler";
	}
}
