package inClassProblem5Part2;

import java.io.IOException;
import java.net.UnknownHostException;

public class Client {

	Connector connector;
	String request;
	String header;
	Acceptor acceptor;
	String name;
	String host = "localhost";
	final int  port = 5000;
	JSONMessage message;
	
	public Client(String name){
		connector = new Connector();
		acceptor = new Acceptor();
		this.name = name;
	}
	
	public static void main(String[] args){
		Client client = new Client("muyango");
		
		
		try {
			client.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	public void start() throws UnknownHostException, IOException, ClassNotFoundException{
		connector.init(host, port);
		
		connector.connect(); // connect first time to sent all the objects
		
		System.out.println("Sending objects to Server*************************");
		for(int i = 0; i <10 ; i++){
			//for post... call the constructor for post
			message = new JSONMessage(""+i , new TestObject(""+i) ); // object name with object itself is passed
			message = connector.send(message);
			System.out.println(message.getCmd()+ " after saving object in vault");

		} 
		
		System.out.println("\n");
		System.out.println("Receiving objects From Server*************************");
		
		for(int i = 0; i <10 ; i++){
			//for get... call the constructor for get
			message = new JSONMessage(""+i , false ); // false to make constructor choose get as the cmd
			message = connector.send(message);
			System.out.println(message.getCmd()+ " after getting object from the vault");
			TestObject object = (TestObject)message.getObject();
			System.out.println(object.getName());
		} 
		
		System.out.println("\n");
		
		//end transmission
		message = new JSONMessage();
		message = connector.send(message);
		System.out.println(message.getCmd()+ " is nigh");
		connector.closeConnection();
		
	}

}
