package com.perisic.beds.rmiinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Authentication extends Remote {
	public boolean authenticate(String userName, char[] password) throws RemoteException ;
	public String getUser(String userName, char[] password ) throws RemoteException;
	public boolean registerUser(String userName, char[] password, String type ) throws RemoteException;

}
