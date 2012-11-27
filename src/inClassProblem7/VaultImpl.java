package inClassProblem7;

import java.rmi.RemoteException;

public class VaultImpl implements Vault{

	@Override
	public void store(String name, byte[] object) throws RemoteException {
	}

	@Override
	public byte[] get(String name) throws RemoteException {
		return null;
	}

	@Override
	public byte[] update(String name, byte[] object) throws RemoteException {
		return null;
	}

	@Override
	public void register(VaultListener listener) throws RemoteException {
	}

	@Override
	public void deregister(VaultListener listener) throws RemoteException {
	}

}
