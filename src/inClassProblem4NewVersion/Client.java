package inClassProblem4NewVersion;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Client {

	Connector connector;
	String request;
	String header;
	Acceptor acceptor;
	ArrayList <TestObject> list;
	ArrayList <TestObject> newlist;
	String name;
	String host = "localhost";
	int port = 69;
	
	public Client(String name){
		connector = new Connector();
		acceptor = new Acceptor();
		list = new ArrayList<TestObject>();
		newlist = new ArrayList<TestObject>();
		this.name = name;
		
	}
	
	public static void main(String[] args) {
		Client client = new Client("muyango");
		
		//initialize bunch of testObject
		for(int i = 0; i<10; i++){
			client.list.add(new TestObject(""+i));
		}
		
		try {
			client.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	/*POST <name> HTTP 1.1
	User-Agent: <client name>
	Host: 127.0.0.1
	<blank line>
	<bytes for object>*/
	
	/*HTTP 1.1 200 OK
	<name>:<class name>
	<blank line>*/ 
	
	/*GET <name> HTTP 1.1
	User-Agent: …
	Host: …
	<blank line>*/
	
	/*HTTP 1.1 200 OK
	<name>:<class name>
	<blank line>
	<bytes for object>*/
	
	public void start() throws UnknownHostException, IOException, ClassNotFoundException{
		connector.init(host, port);
		
		connector.connect(); // connect first time to sent all the objects
		
		for(TestObject item : list){
			request = "POST " +item.getName()+ " HTTP 1.1 " +
						"User-Agent: "+ this.name +
						" Host: 127.0.0.1" +
						"\n";
			connector.send(request, item);

		} 
		
		//connector.connect(); // connect again for getting all the object
		for(TestObject item : list){
			request = "GET " +item.getName()+" HTTP 1.1 "+
						"User-Agent: ..."+ 
						"Host: "+ this.host + 
						"\n";
			TestObject object = (TestObject)connector.receive(request);
			newlist.add(object);
		}
		
		request = "END transmission";
		connector.send(request, null);
		
	}

}
