package inClassProblem4_part1_byChopel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

//better name will be connection class
public class Worker implements Runnable{

	Socket sock;
	Reactor reactor;
	
	public Worker(Socket sock, Reactor reactor){
		this.sock = sock;
		this.reactor = reactor;
	}
	
	public void run(){
		InputStreamReader IR;
		try {
			IR = new InputStreamReader(sock.getInputStream());
			BufferedReader BR = new BufferedReader(IR);
			Event event = getEvent(BR);
		

			reactor.dispatch(event);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Event getEvent(BufferedReader buff){
		String event  = buff.readLine();
		Event realEvent = 
		return realEvent; 
	}
}
