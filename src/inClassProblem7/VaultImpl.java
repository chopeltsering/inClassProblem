package inClassProblem7;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public class VaultImpl implements Vault{

	
	HashMap<String, Object> map = new HashMap<String, Object>();
	ArrayList<VaultListener> list = new ArrayList<>();
	
	@Override
	public void store(String name, byte[] object) {
		try {
			map.put(name, Marshaller.deserializeObject(object));
			for(VaultListener listener: list){
				listener.onStore(name);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public byte[] get(String name) throws RemoteException {
		try {
			return  Marshaller.serializeObject(map.get(name));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public byte[] update(String name, byte[] object) throws RemoteException {
		byte [] temp = null;
		try {
			temp = Marshaller.serializeObject(map.get(name));
			map.put(name, Marshaller.deserializeObject(object));
			for(VaultListener listener: list){
				listener.onUpdate(name);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public void register(VaultListener listener) throws RemoteException {
		list.add(listener);
	}

	@Override
	public void deregister(VaultListener listener) throws RemoteException {
		list.remove(listener);
	}

}
