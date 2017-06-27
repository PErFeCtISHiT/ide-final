//服务器UserService的Stub，内容相同
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserService extends Remote{
	public String login(String username, String password) throws RemoteException;

	public  boolean newuser(String user,String password)throws RemoteException;

	public String getName()throws RemoteException;

	public boolean deleteuser(String user, String mode)throws RemoteException;

	public String getUserList()throws RemoteException;
}
