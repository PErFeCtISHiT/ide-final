package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
public interface ExecuteService extends Remote {
	public String execute(String code, String param) throws RemoteException;
	public String turn(String ook) throws RemoteException;
}
