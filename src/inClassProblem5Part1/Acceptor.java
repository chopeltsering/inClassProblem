package inClassProblem5Part1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Acceptor {
	
	Reactor reactor;
	Vault vault;
	ServerSocket listener;
	Socket sock;
	int poolSize = 10;
	ObjectInputStream ois;
	ObjectOutputStream oos;

	public Acceptor(){
		
	}
	
	public void init(Reactor reactor, Vault vault, int port) throws IOException{
		this.reactor = reactor;
		this.vault = vault;
		listener = new ServerSocket(port);
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
							
						Message message = (Message)ois.readObject();
						System.out.println("request from client is :" +message.getCmd());
						
						if(message.getCmd().equals("POST")){
							System.out.println("its a post request");
							vault.addObject(message.getObject());
							message = new Message("Ok", message.getName(), null);
							oos.writeObject(message);
						
						}else if(message.getCmd().equals("GET")){
							System.out.println("its a get request");
							message = new Message("Ok", message.getName(), vault.getObject());
							oos.writeObject(message);
							
						}else if(message.getCmd().equals("END transmission")){
							System.out.println("its a END transmission request");
							message = new Message("Ok, transmission ended", "", null);
							oos.writeObject(message);
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
