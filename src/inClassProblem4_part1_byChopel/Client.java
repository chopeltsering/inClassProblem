package inClassProblem4_part1_byChopel;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Client {
	Reactor reactor;
	Acceptor acceptor;
	Connector connector;
	String fileName;	
	String path;
	String host;
	int port;
	String [] keysForTestObject;
	ArrayList<String> list;   // to store keys for object.... just so i can iterate over the list
	
	public Client(){
		reactor = new Reactor();
		connector = new Connector();
		acceptor = new Acceptor(5000); // will accept connection at port 5000 < to be used in peer to peer connection>
		host = "127.0.0.1";
		port = 69;                   // server is listening at port 69 
		fileName = "loadClassName.txt";  // file where i have save object name with 
		path = new java.io.File("").getAbsolutePath()+ File.separator + fileName;
		String [] keysForTestObject = {"objectA", "objectB", "objectC", "objectD", "objectE", "objectF", "objectG",
				"objectH", "objectI", "objectJ"};
		Collections.addAll(list, keysForTestObject);
	}
	
	public static void main(String[] args) {
		
		Client client = new Client();
		client.start();

	}
	
	public void start(){
		connector.connect(host, port); // connect to server at given host and port number
		try {
			for(String key: list){
				connector.sendMessage(path, key); // sent message to server
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("done messaging");
	}
	
}
