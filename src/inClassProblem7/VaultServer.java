package inClassProblem7;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
//import java.rmi.registry.LocateRegistry;
//import java.rmi.server.UnicastRemoteObject;
//import java.rmi.Remote;

public class VaultServer {

	/**
	 * @param args
	 * @throws RemoteException 
	 * @throws AlreadyBoundException 
	 * @throws MalformedURLException 
	 */
	
	public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException{
		VaultImpl vault = new VaultImpl();
//		Registry register = inClassProblem7.Registry.startInternal();
//		register.bind("Vault", vault);
		Naming.rebind("Vault", vault);
//		Remote remoteObject = UnicastRemoteObject.exportObject(vault, 10000); 
//       Registry register = LocateRegistry.createRegistry(10000);
//		register.bind("Vault", remoteObject);
	}
}
 