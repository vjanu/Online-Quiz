package com.perisic.beds.rmiserver;


import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.Remote;



import com.perisic.beds.rmiinterface.Authentication;

public class StartAuthenticationServer {

	public static void main(String[] args) {
		try {
			Authentication authentication = new AuthenticationImplementation();
			Registry registry = LocateRegistry.createRegistry(1088);
//			Registry signUp = LocateRegistry.createRegistry(1089);
			registry.rebind("AuthService",authentication);
//			registry.rebind("RegisterService",authentication);
			
//			Naming.rebind("//localhost/AuthService1128", authentication);
			
			System.out.println("Authentication Service started!");
		}
catch (RemoteException e) {
			
			System.out.println("asdasda"+ e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
