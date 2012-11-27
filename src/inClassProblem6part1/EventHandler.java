package inClassProblem6part1;

import java.io.IOException;

public interface EventHandler {
	public Object handleEvent(Event event) throws IOException, InterruptedException; 
}
   