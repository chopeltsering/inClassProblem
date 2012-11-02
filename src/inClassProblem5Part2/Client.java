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
	final int  port = 69;
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
			request = "POST";
			message = new JSONMessage(request, ""+i , "object");
			message = connector.send(message);
			System.out.println(message.getCmd()+ " after saving object in vault");

		} 

		System.out.println("Receiving objects to Server*************************");
		for(int i = 0; i <10 ; i++){
			request = "GET";
			message = new JSONMessage(request, ""+i , null);
			message = connector.send(message);
			System.out.println(message.getCmd()+ " after getting object from the vault");
		} 
		
		request = "END transmission";
		message = new JSONMessage(request, "", null);
		message = connector.send(message);
		System.out.println(message.getCmd()+ " connection severed with server");
		connector.closeConnection();
		
	}

}
