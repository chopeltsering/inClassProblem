package inClassProblem4NewVersion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connector {

	Socket sock;
	String host;
	int port;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	
	public Connector(){
		
		
	}
	
	public void connect() throws UnknownHostException, IOException{
		sock = new Socket(host, port);
		ois = new ObjectInputStream(sock.getInputStream());
		oos = new ObjectOutputStream(sock.getOutputStream());
		
	}
	
	public void init(String host, int port){
		this.host = host;
		this.port = port;
	}

	public void send(String request, TestObject item) throws ClassNotFoundException {
		try {
			
			if(item == null){  // for termination
				oos.writeObject(request);
				return;
			}
			oos.writeObject(request);
			oos.writeObject(item);
			
			
			String reply = (String)ois.readObject();
			System.out.println(reply);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void closeConnection() throws IOException{
		ois.close();
		oos.close();
		sock.close();
	}

	public Object receive(String request) throws ClassNotFoundException, IOException{
		oos.writeObject(request);
		return ois.readObject();

	}

	
	
}
 