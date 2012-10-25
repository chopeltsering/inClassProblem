package inClassProblem4_part1_byChopel;


import java.io.File;
import java.io.IOException;

public class Client {
	Reactor r;
	Acceptor ac;
	Connector co;
	String fileName = "loadClassName";	
	String path;
	
	public Client(){
		r = new Reactor();
		co = new Connector();
	}
	
	public static void main(String[] args) {
		
		Client cl = new Client();
		cl.path = new java.io.File("").getAbsolutePath()+ File.separator + cl.fileName;
		System.out.println("senting:");
		
		for(;;){		
			cl.co.connect(9000); 			// server is listening at port 69
			try {
				cl.co.sendMessage(cl.path);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("done messaging");
			//return;

		}

	}
}
