package inClassProblem4_part1_byChopel;

import java.io.FileInputStream;
//import java.io.DataOutputStream;
import java.io.DataInputStream;
//import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Properties;

public class Connector {
Socket sock;
	
	public Connector(){
		
	}
	
	public void connect(int port){
		try {
			sock = new Socket("127.0.0.1", port); // at localhost<ip address of this computer> , at specified port of this computer
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		 
	}
	
	public void sendMessage(String fileName){
		String [] keysForTestObject = {"objectA", "objectB", "objectC", "objectD", "objectE", "objectF", "objectG",
				"objectH", "objectI", "objectJ"};
		
		/*POST <name> HTTP 1.1
		User-Agent: <client name>
		Host: 127.0.0.1
		<blank line>
		<bytes for object>*/
		
		/*HTTP 1.1 200 OK
		<name>:<class name>
		<blank line>*/
		
		DataOutputStream out;
		DataInputStream in;
		byte [] data = new byte[512];
		try {
			in = new DataInputStream(sock.getInputStream());
			out = new DataOutputStream(sock.getOutputStream());
		
			//we can do all these out.write one after another without needing to worry because outputStream gotten from socket is stream based.
			for(String s : keysForTestObject){  
				 
				out.write(Marshaller.serializeObject("POST <"+s+"> HTTP 1.1"));
				out.write(Marshaller.serializeObject("User-Agent: <client name>"));
				//out.write(Marshaller.serializeObject("\n"));
				TestObject object = loadFromFile(fileName, s);	
				System.out.println(object.getValue());
 				out.write(Marshaller.serializeObject(object));
				//out.write
 				//out.close();
 				
 				//return;
 				//in.read(data);
 				
			}
							  
		} catch (IOException e) {
			e.printStackTrace();
		}
					
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
	
	public TestObject loadFromFile(String fileName, String s){
		Properties p = new Properties();
		TestObject object = null;
		try {
			p.load(new FileInputStream("loadClassName.txt")); // need to check if fileName correspond to correct path
			String className = p.getProperty(s);
			System.out.println(className);
			Class<?>cla = Class.forName(className);
			object = (TestObject)cla.newInstance();
			System.out.println(object.getName());

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
