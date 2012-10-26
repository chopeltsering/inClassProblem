package inClassProblem4_part1_byChopel;

//import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
////import java.io.OutputStream;
////import java.net.ServerSocket;
//import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;


public class VaultServer {
	Reactor reactor;
	Acceptor acceptor;
	Vault vault;
	int port;
	String fileName;	
	String path;
	String [] keysForTestObject;
	ArrayList<String> list;   // to store keys for object.... just so i can iterate over the list
	
	public VaultServer(){
		port = 69;
		vault = new Vault();
		reactor = new Reactor();
		acceptor = new Acceptor(port);
		fileName = "loadClassName.txt";  // file where i have save object name with 
		path = new java.io.File("").getAbsolutePath()+ File.separator + fileName;
		String [] keysForTestObject = {"Register", "Deregister", "Message.1", "Message.2"};
		Collections.addAll(list, keysForTestObject);
	}
	
	public static void main(String[] args) {
		VaultServer server = new VaultServer();
		server.start();
	
	}
	
	public void start(){
		registerEventHandler();
		
		try {
//			acceptor.accept();
//			reactor.dispatch(sock);
//			InputStreamReader IR = new InputStreamReader(sock.getInputStream());
//			BufferedReader BR = new BufferedReader(IR);
		
			while(true){
				//String message = BR.readLine();
				//System.out.println(message);
				boolean newConnection = acceptor.accept();
				System.out.println("new connection has been made: " + newConnection);
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	
	public void registerEventHandler(){
		/*r.registerHandler("GET", new EventHandler(){
			public void HandleEvent(Event e){
				doGet(e.getRequest(), e.getResponse());  // how is doGet or doPost are called from eventHandler class?
			}
		});
		
		r.registerHandler("POST", new EventHandler(){
			public void HandleEvent(Event e){
				doPost(e.getRequest(), e.getResponse());
			}
		});*/
		
		for(String key: list){
			reactor.registerHandler(path, key);
		}
		
	}
	
	public void doGet(Object o, Object p){
		
	}
	
	public void doPost(Object o, Object p){
		
	}

}
