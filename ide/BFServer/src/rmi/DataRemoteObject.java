package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import service.IOService;
import service.UserService;
import service.ExecuteService;
import service.DealService;
import serviceImpl.ExecuteServiceImpl;
import serviceImpl.IOServiceImpl;
import serviceImpl.UserServiceImpl;
import serviceImpl.DealServiceImpl;

public class DataRemoteObject extends UnicastRemoteObject implements IOService, UserService,ExecuteService,DealService{
	/**
	 *
	 */
	private static final long serialVersionUID = 4029039744279087114L;
	private IOService iOService;
	private UserService userService;
	private ExecuteService executeService;
	private DealService dealService;
	protected DataRemoteObject() throws RemoteException {
		iOService = new IOServiceImpl();
		userService = new UserServiceImpl();
		executeService = new ExecuteServiceImpl();
		dealService = new DealServiceImpl();
	}

	@Override
	public boolean writeFile(String file, String userId, String fileName) throws RemoteException{
		return iOService.writeFile(file, userId, fileName);
	}

	@Override
	public String readFile(String userId, String fileName) throws RemoteException{
		return iOService.readFile(userId, fileName);
	}

	@Override
	public String readFileList(String userId) throws RemoteException{
		return iOService.readFileList(userId);
	}

	@Override
	public String login(String username, String password) throws RemoteException {
		return userService.login(username, password);
	}
	@Override
	public String execute(String code, String param)throws RemoteException{
		return executeService.execute(code, param);
	}
	@Override
	public String dealing(String filePath) throws RemoteException{
		return dealService.dealing(filePath);
	}
	@Override
	public String turn(String ook) throws RemoteException{
		return executeService.turn(ook);
	}
	@Override
	public boolean newuser(String user,String password) throws RemoteException{
		return userService.newuser(user, password);
	}
	@Override
	public String getName() throws RemoteException{
		return userService.getName();
	}
	@Override
	public boolean save(String newcode) throws RemoteException{
		return iOService.save(newcode);
	}
	@Override
	public String getCode(int i)throws RemoteException{
		return iOService.getCode(i);
	}

	@Override
	public boolean addFile(String fileName, String user) throws RemoteException {
		return iOService.addFile(fileName, user);
	}

	@Override
	public String redo() throws RemoteException {
		return iOService.redo();
	}

	@Override
	public String undo() throws RemoteException {
		return iOService.undo();
	}

	@Override
	public boolean addRe(String s) throws RemoteException {
		return iOService.addRe(s);
	}

	@Override
	public boolean addUn(String s) throws RemoteException {
		return iOService.addUn(s);
	}

	@Override
	public boolean textredo() throws RemoteException {
		return iOService.textredo();
	}

	@Override
	public boolean textundo() throws RemoteException {
		return iOService.textundo();
	}

	@Override
	public boolean clear() throws RemoteException {
		return iOService.clear();
	}

	@Override
	public boolean deleteuser(String user, String mode) throws RemoteException {
		return userService.deleteuser(user, mode);
	}

	@Override
	public String getUserList() throws RemoteException {
		return userService.getUserList();
	}

	@Override
	public boolean delete(String userNow, String cmd, int mode)throws RemoteException{
		return iOService.delete(userNow, cmd, mode);
	}

}
