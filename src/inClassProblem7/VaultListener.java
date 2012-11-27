package inClassProblem7;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface VaultListener extends Remote {
	public void onUpdate(String name) throws RemoteException;
	public void onStore(String name) throws RemoteException;
}