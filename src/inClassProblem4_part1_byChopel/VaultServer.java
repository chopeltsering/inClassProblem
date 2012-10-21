package inClassProblem4_part1_byChopel;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class VaultServer {
	Reactor r;
	Vault v;
	int port;
	//Marshaller mars;
	
	public VaultServer(){
		port = 69;
		v = new Vault();
		r = new Reactor();
	}
	
	public static void main(String[] args) {
		VaultServer server = new VaultServer();
		server.start();
		
		
	}
	
	public void start(){
		ServerSocket listener;
		try{
			listener = new ServerSocket(port);
			byte [] b = new byte[512];
			Socket s = listener.accept();
			InputStream is = s.getInputStream();
			DataInputStream in = new DataInputStream(is);
			while(true){
				System.out.println("inside while loop");
				
				
				//System.out.println("received new object s");
				
				//OutputStream os = s.getOutputStream();
				
				in.read(b);
				//is.read(b);  // read the object into byte array b;
				
//				v.addObject(Marshaller.deserializeObject(b));  //add to the vector
//				TestObject ob = (TestObject) v.getObject(0);
//				System.out.println(	ob.getName());

				
				
				System.out.println(b);
				System.out.println(Marshaller.deserializeObject(b));
				System.out.println("end of while loop");
			}
			
		}catch(Exception e){
			System.err.println(e);
		}
		registerEventHandler();
	}
	
	public void registerEventHandler(){
		r.registerHandler("GET", new EventHandler(){
			public void HandleEvent(Event e){
				doGet(e.getRequest(), e.getResponse());  // how is doGet or doPost are called from eventHandler class?
			}
		});
		
		r.registerHandler("POST", new EventHandler(){
			public void HandleEvent(Event e){
				doPost(e.getRequest(), e.getResponse());
			}
		});
		
	}
	
	public void doGet(Object o, Object p){
		
	}
	
	public void doPost(Object o, Object p){
		
	}

}
