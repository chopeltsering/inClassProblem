package inClassProblem4_part1_byChopel;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Acceptor {
	Socket sock;
	
	public Acceptor(int port){
		try {
			sock = new Socket(InetAddress.getLocalHost(), port); // at localhost<ip address of this computer> , at specified port of this computer
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Socket connect(){
		return null;
	}
}
