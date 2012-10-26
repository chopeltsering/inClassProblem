package inClassProblem4_part1_byChopel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Properties;

public class Connector {
	Socket sock;
	
	public Connector(){
		
	}
	
	public void connect(String host, int port){
		try {
			sock = new Socket(host, port);  
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		 
	}
	
	public void sendMessage(String pathToFile, String key) throws IOException{
		
		
		/*POST <name> HTTP 1.1
		User-Agent: <client name>
		Host: 127.0.0.1
		<blank line>
		<bytes for object>*/
		
		/*HTTP 1.1 200 OK
		<name>:<class name>
		<blank line>*/ 
		
		PrintStream PS;
		PS = new PrintStream(sock.getOutputStream());
		PS.println("POST <"+key+"> HTTP 1.1");
		PS.println("User-Agent: " + key );
		PS.println("Host: 127.0.0.1");
		PS.println("\n");
		TestObject object = loadFromFile(pathToFile, key);	
		PS.println(Marshaller.serializeObject(object).toString());
		System.out.println(object.getValue());				
	}
	
	public Object receiveMessage(){
		byte [] b = new byte [100];
		try {
			InputStream in = sock.getInputStream();
			in.read(b);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return null;

	}
	
	public TestObject loadFromFile(String pathToFile, String key){
		Properties p = new Properties();
		TestObject object = null;
		try {
			p.load(new FileInputStream(pathToFile)); // need to check if fileName correspond to correct path
			String className = p.getProperty(key);
			//System.out.println(className);
			Class<?>cla = Class.forName(className);
			object = (TestObject)cla.newInstance();
			//System.out.println(object.getName());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return object;
	}
}
