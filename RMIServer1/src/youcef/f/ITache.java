package youcef.f;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ITache extends Remote {

	public int addition(int a, int b) throws RemoteException;

}