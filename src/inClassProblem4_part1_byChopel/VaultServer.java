package inClassProblem4_part1_byChopel;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
//import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class VaultServer {
	Reactor r;
	Vault v;
	int port;
	
	public VaultServer(){
		port = 9000;
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
				Socket sock = listener.accept();
				InputStreamReader IR = new InputStreamReader(sock.getInputStream());
				BufferedReader BR = new BufferedReader(IR);
			
			/*byte [] b = new byte[512];
			System.out.println("inside while loop");
			InputStream is = s.getInputStream();
			DataInputStream in = new DataInputStream(is);*/
			
			while(true){
				

				String message = BR.readLine();
				//System.out.println(Marshaller.deserializeObject(b));
				System.out.println(message);
				//s.close();
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
