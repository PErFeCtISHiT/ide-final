package runner;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import rmi.RemoteHelper;
import service.IOService;
import service.ExecuteService;
import service.UserService;
import ui.Frames;

public class ClientRunner {
	private RemoteHelper remoteHelper;

	public ClientRunner() {
		linkToServer();
		initGUI();
	}

	private void linkToServer() {
		try {
			remoteHelper = RemoteHelper.getInstance();
			remoteHelper.setRemote(Naming.lookup("rmi://127.0.0.1:8887/DataRemoteObject"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	private void initGUI() {
		Frames mainFrame = new Frames();
	}


	public static void main(String[] args){
		ClientRunner cr = new ClientRunner();
	}
}
