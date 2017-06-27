//需要客户端的Stub
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
public interface IOService extends Remote{
	public boolean writeFile(String file, String userId, String fileName)throws RemoteException;

	public String readFile(String userId, String fileName)throws RemoteException;

	public String readFileList(String userId)throws RemoteException;

	public String getCode(int i)throws RemoteException;

	public boolean save(String newcode) throws RemoteException;

	public boolean addFile(String fileName, String user)throws RemoteException;

	public String redo()throws RemoteException;

	public String undo()throws RemoteException;

	public boolean addRe(String s)throws RemoteException;

	public boolean addUn(String s)throws RemoteException;

	public boolean textredo()throws RemoteException;

	public boolean textundo()throws RemoteException;

	public boolean clear()throws RemoteException;

	public boolean delete(String userNow, String cmd, int mode)throws RemoteException;

}
