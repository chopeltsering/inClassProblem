package inClassProblem7;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Vault extends Remote {
	public void store(String name, byte[] object) throws RemoteException;
	public byte[] get(String name) throws RemoteException;
	public byte[] update(String name, byte[] object) throws RemoteException;
	public void register(VaultListener listener) throws RemoteException;
	public void deregister(VaultListener listener) throws RemoteException;
}

