package inClassProblem5Part2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.Gson;

public class Acceptor {
	
	Reactor reactor;
	Vault vault;
	ServerSocket listener;
	Socket sock;
	int poolSize = 10;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	Gson gson;
	
	public Acceptor(){
		
	}
	
	public void init(Reactor reactor, Vault vault, int port) throws IOException{
		this.reactor = reactor;
		this.vault = vault;
		listener = new ServerSocket(port);
		gson = new Gson();
	} 
	
	public void accept() throws IOException {
		System.out.println("waiting for connection");
		sock = listener.accept();

		Runnable thread = new Runnable (){ 
			
			public void run() { 
				System.out.println("inside run method");
				try {
					oos = new ObjectOutputStream(sock.getOutputStream());  // if the order of oos and ois is reversed, it hangs?
					ois = new ObjectInputStream(sock.getInputStream());
					System.out.println("just outside while loop");
					
					while(true){
						System.out.println("inside while loop");
	
						JSONMessage message = gson.fromJson((String) ois.readObject(), JSONMessage.class);
						
						System.out.println("request from client is :" +message.getCmd());
						
						if(message.getCmd().equals("POST")){
							System.out.println("its a post request");
							vault.addObject(message.getObject());
							message = new JSONMessage("Ok", message.getName(), null);
							oos.writeObject(gson.toJson(message)); 
						
						}else if(message.getCmd().equals("GET")){
							System.out.println("its a get request");
							message = new JSONMessage("Ok", message.getName(), vault.getObject());
							oos.writeObject(gson.toJson(message)); 
							
						}else if(message.getCmd().equals("END transmission")){
							System.out.println("its a END transmission request");
							message = new JSONMessage("Ok, transmission ended", "", null);
							oos.writeObject(gson.toJson(message)); 
							sock.close();
							oos.close();
							ois.close();
							return;
						}else{
							System.out.println("request is unknown");
						}
					} 		
					
				} catch (IOException | ClassNotFoundException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		};  
		
		System.out.println("after annynymous class");
		ExecutorService pool = Executors.newFixedThreadPool(poolSize);
		pool.execute(thread);
	}
	
	public Object receive(){
		return null; 
	}
	
	
}
