package youcef.f;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class Tache extends UnicastRemoteObject implements ITache {

	protected Tache() throws RemoteException {
		super();
	}

	@Override
	public int addition(int a, int b) throws RemoteException {
		return a+b;
	}
	
	

}