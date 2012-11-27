package inClassProblem5Part2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import com.google.gson.Gson;

public class Connector {

	Socket sock;
	String host;
	int port;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	Gson gson;
	
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
		gson = new Gson();
	}

	public JSONMessage send(JSONMessage message) throws IOException, ClassNotFoundException{
		oos.writeObject(message); 
		return (JSONMessage) ois.readObject();
		//return gson.fromJson(ois.readObject(), JSONMessage.class);
	
	}
	
	public void closeConnection() throws IOException{
		ois.close();
		oos.close();
		sock.close();
	}

}
 