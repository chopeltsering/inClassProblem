package inClassProblem7;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client extends UnicastRemoteObject implements VaultListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	
	public Client(String name) throws RemoteException{
		super();
		this.name = name;
	}
	
	@Override
	public void onUpdate(String name) throws RemoteException {
		System.out.println("for "+this.name +" " +name+" has been updated");
	}

	@Override
	public void onStore(String name) throws RemoteException {
		System.out.println("for "+this.name +" " +name+" has been added to the vault");
	}
	
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException, AlreadyBoundException {
		//make clients
		Client client1 = new Client("client1");
		Client client2 = new Client("client2");
		
		//get the stub for vault from registry
		//String url = new String("rmi://localhost:1099/Vault");
		Vault vault = (Vault)Naming.lookup("Vault");
		
		//register the clients to the vault as candidates
		vault.register(client1);
		vault.register(client2);
		
		//do the work on vault
		try {
			//store two objects
			vault.store("firstObject", Marshaller.serializeObject("i am first object"));
			vault.store("secondObject", Marshaller.serializeObject("i am second object"));
			
			//update one of the objects
			vault.update("firstObject", Marshaller.serializeObject("first object has been updated"));
			
			//get the second object
			String obj = (String)Marshaller.deserializeObject(vault.get("secondObject"));
			System.out.println("the second object is: "+obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//deregister the clients
		vault.deregister(client1);
		vault.deregister(client2);
	}
	
	//listener methods are getting called on serverside instead of client side.
	//
	
	

	
	/*//register the clients as remote objects
	Remote remoteObject1 = UnicastRemoteObject.exportObject(client1, 9000);
	Registry clientRegister1 = LocateRegistry.createRegistry(9000);
	clientRegister1.bind("Listener1", remoteObject1); 
	
	Remote remoteObject2 = UnicastRemoteObject.exportObject(client2, 8000);
	Registry clientRegister2 = LocateRegistry.createRegistry(8000);
	clientRegister2.bind("Listener2", remoteObject2); */
}
