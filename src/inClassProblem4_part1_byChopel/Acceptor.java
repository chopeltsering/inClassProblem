package inClassProblem4_part1_byChopel;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class Acceptor {
	
	
	ArrayList<Socket> socketList;
	Socket sock;
	ServerSocket listener;
	Reactor reactor;
	
	public Acceptor(int port){
		socketList = new ArrayList<Socket>();
		sock = null;
		try {
			listener = new ServerSocket(port);  // server is listening at port 69
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean accept() throws IOException{
		if(sock == null){
			sock = listener.accept();
		}
		
		if(sock.isConnected()){
			socketList.add(sock);
			new Thread(new Worker(sock, reactor)).start(); // creating new connection thread
			sock = null;  // almost same as closing the socket but connection is maintained since reference to socket has been passed on. 
			return true;
		}else
			return false;
	}
	
	public void setReactor(Reactor reactor){
		this.reactor = reactor;
	}
}


 