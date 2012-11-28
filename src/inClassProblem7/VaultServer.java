package inClassProblem7;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class VaultServer {

	/**
	 * @param args
	 * @throws RemoteException 
	 * @throws AlreadyBoundException 
	 */
	
	public static void main(String[] args) throws RemoteException, AlreadyBoundException{
		VaultImpl vault = new VaultImpl();
		Remote remoteObject = UnicastRemoteObject.exportObject(vault, 10000);
        Registry register = LocateRegistry.createRegistry(10000);
        register.bind("Vault", remoteObject);
	}
}
 