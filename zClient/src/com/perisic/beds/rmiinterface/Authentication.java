package com.perisic.beds.rmiinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Authentication extends Remote{
	public boolean authenticate(String userName, String password) throws RemoteException ;
	public String getUser(String userName, String password ) throws RemoteException;
}
