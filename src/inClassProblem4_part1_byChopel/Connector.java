package inClassProblem4_part1_byChopel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Connector {
Socket sock;
	
	public Connector(int port){
		try {
			sock = new Socket(InetAddress.getLocalHost(), port); // at localhost<ip address of this computer> , at specified port of this computer
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Socket connect(){
//		try {
//			InputStream in = sock.getInputStream();
//			OutputStream out = sock.getOutputStream();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		return sock;
	}
}
