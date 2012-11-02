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
			Thread connection = new Thread(new Worker(sock, reactor)); // creating new connection thread
			connection.start(); // ask andrew or roger about whethere giving it to thread pool or running it right here. // thread pool only seems to have a limit on number of connection can be made.
			/*           
			 * Thread pool goes here:
			 * all thread pool seem to be doing is delaying the calling of .start() method by calling run() method
			 * only advantage i see is, thread pool allows <task or snippet of code encapsulated inside an object put into taskQueue 
			 * to be run concurrently. essentially gaining the ability of multithreading  
			 * 
			 * when run() method is called, it seems no new thread is created. so essentially its like calling a method on an object.
			 * */
			sock = null;  // almost same as closing the socket but connection is maintained since reference to socket has been passed on. 
			return true;
		}else
			return false;
	}
	
	public void setReactor(Reactor reactor){
		this.reactor = reactor;
	}
}


 