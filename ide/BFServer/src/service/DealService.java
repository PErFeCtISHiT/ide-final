package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DealService extends Remote{
	public String dealing(String filePath) throws RemoteException;
}
