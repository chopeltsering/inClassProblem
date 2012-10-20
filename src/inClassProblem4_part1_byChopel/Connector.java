package inClassProblem4_part1_byChopel;

import java.io.FileInputStream;
//import java.io.DataOutputStream;
import java.io.DataInputStream;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Connector {
Socket sock;
	
	public Connector(){
		
	}
	
	public void connect(int port){
		try {
			sock = new Socket("127.0.0.1", port); // at localhost<ip address of this computer> , at specified port of this computer
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		 
	}
	
	public void sendMessage(byte[] message, String fileName){
		try {
			DataInputStream in = new DataInputStream (new FileInputStream(fileName));
			System.out.println(in.readUTF());
			OutputStream out = sock.getOutputStream();
			out.write(message);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public Object receiveMessage(){
		byte [] b = new byte [100];
		try {
			InputStream in = sock.getInputStream();
			in.read(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
			
		
		return null;

	}
}
