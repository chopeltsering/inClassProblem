package inClassProblem4_part1_byChopel;

import java.net.Socket;

public class Worker implements Runnable{

	Socket sock;
	
	public Worker(Socket sock){
		this.sock = sock;
	}
	
	public void run(){
		
	}
}
