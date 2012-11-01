package inClassProblem4NewVersion;

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
			
			/*event handling is done here, but normally should be done by eventhandler by calling reactor.dispatch from here
			when control flow returns from dispatch method, then return answer.*/
			
			public void run() { 
				System.out.println("inside run method");
				try {
					oos = new ObjectOutputStream(sock.getOutputStream());  // if the order of oos and ois is reversed, it hangs?
					ois = new ObjectInputStream(sock.getInputStream());
					System.out.println("just outside while loop");
					while(true){
						System.out.println("inside while loop");
							
						String request = (String)ois.readObject();
						System.out.println(request);
						if(request.contains("POST")){
							System.out.println("its a post request");
							TestObject item = (TestObject)ois.readObject();
							vault.addObject(item);
							String reply = "HTTP 1.1 200 OK " +
											"name : "+ item.name + 
											"\n";
							oos.writeObject(reply);
						
						}else if(request.contains("GET")){
							System.out.println("its a get request");
							TestObject item = vault.getObject();
							String reply = "HTTP 1.1 200 OK " +
											"name : "+ item.name + 
											"\n";
							oos.writeObject(reply);
							oos.writeObject(item);
						}else if(request.contains("END")){
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
		};  // why is annoymous class become useless when used here, like call a method right here on this line.
		
		System.out.println("after annynymous class");
		ExecutorService pool = Executors.newFixedThreadPool(poolSize);
		pool.execute(thread);
	}
	
	public Object receive(){
		return null; 
	}
	
	
}
