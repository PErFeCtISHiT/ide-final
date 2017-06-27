package rmi;

import java.rmi.Remote;

import service.DealService;
import service.ExecuteService;
import service.IOService;
import service.UserService;

public class RemoteHelper {
	private Remote remote;
	private static RemoteHelper remoteHelper = new RemoteHelper();
	public static RemoteHelper getInstance(){
		return remoteHelper;
	}

	private RemoteHelper() {
	}

	public void setRemote(Remote remote){
		this.remote = remote;
	}

	public IOService getIOService(){
		return (IOService)remote;
	}

	public UserService getUserService(){
		return (UserService)remote;
	}
	public ExecuteService getExcuteServise(){
		return (ExecuteService)remote;
	}
	public DealService getDealService(){
		return (DealService)remote;
	}
}
