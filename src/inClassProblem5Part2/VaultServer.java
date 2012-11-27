package inClassProblem5Part2;

import java.io.IOException;

public class VaultServer {

	Acceptor acceptor;
	Vault vault;
	Reactor reactor;
	int port = 5000;
	
	public VaultServer(){
		acceptor = new Acceptor();
		vault = new Vault(50); // some arbitary number for now……È…ÈÈÈÈÈ
	}
	public static void main(String[] args) {
		VaultServer server = new VaultServer();
		try {
			server.start();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start() throws ClassNotFoundException, IOException{
		registerHandler();
		acceptor.init(reactor, vault, port);
		while(true)
			acceptor.accept();
	}

	
	public void registerHandler(){
		
	}
	
}
