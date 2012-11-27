package inClassProblem6part1;

import java.io.IOException;

public class StoreResponse implements EventHandler {

	@Override
	public Object handleEvent(Event event) throws IOException,InterruptedException {
		System.out.println("inside storeResponse event handler");
		System.out.println(event.getEventType() + event.getText());
		return null;
	}
	public String toString(){
		return "inside StoreResponse event Handler";
	}
}
