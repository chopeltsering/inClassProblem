package inClassProblem4_part1_byChopel;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class Acceptor {
	
	
	static ArrayList<Socket> socketList;
	Socket sock;
	ServerSocket listener;
	
	public Acceptor(int port){
		socketList = new ArrayList<Socket>();
		try {
			listener = new ServerSocket(port);  // server is listening at port 69
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean accept() throws IOException{
		
		sock = listener.accept();
		if(sock.isConnected()){
			socketList.add(sock);
			new Thread(new Worker(sock)).start();
			return true;
		}else
			return false;
	}
}


 