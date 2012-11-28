package inClassProblem7;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Registry {
	
	// See http://www.jguru.com/faq/view.jsp?EID=995L
	
	public static int PORT = java.rmi.registry.Registry.REGISTRY_PORT;
	public static String REGISTRY = "rmiregistry";
	public static java.rmi.registry.Registry registry = null;
	
	// This simply tests the sanity of running an internal rmiregistry
	// It does not keep the registry running.
	public static void main(String[] args) {
		startInternal();
	}
	
	// Start an rmiregistry which is running within the JVM
	public static java.rmi.registry.Registry startInternal() {
		try {
			registry = LocateRegistry.createRegistry(PORT);
			System.out.println("Registry is running on port "+PORT);
			return registry;
		} catch (RemoteException e) {
			System.out.println("Registry could not be started on port "+PORT);
		}
		return null;
	}
	
	public static java.rmi.registry.Registry startExternal() {
		try {
			Runtime.getRuntime().exec(REGISTRY);
			return LocateRegistry.getRegistry();
		} catch (IOException e) {
			System.out.println("Could not start "+REGISTRY);
			return null;
		}
	}
}

