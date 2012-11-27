package inClassProblem7;

import java.rmi.RemoteException;

public class Client implements VaultListener{


	@Override
	public void onUpdate(String name) throws RemoteException {
	}

	@Override
	public void onStore(String name) throws RemoteException {
	}
	
	public static void main(String[] args) {
	}
}
