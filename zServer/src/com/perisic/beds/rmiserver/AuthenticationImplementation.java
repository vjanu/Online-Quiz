package com.perisic.beds.rmiserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.perisic.beds.rmiinterface.Authentication;

public class AuthenticationImplementation extends UnicastRemoteObject implements Authentication {

	protected AuthenticationImplementation() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean authenticate(String userName, String password)
			throws RemoteException {

		// SQL string to check whether the entered username and passwords match with the database
		String query = "SELECT * FROM User WHERE username = '"+userName+"'  AND password = '"+password+"' ";
		
		try{
			
            
            ResultSet rs = dbConnect.getConnection().executeQuery(query);
			
			
			// If the above query is executed, there should only be ONE record in the resultset
			// rs.last() means the final record in the ResultSet, but here there is only one record, so that record is the final one
			
			if(rs.last()){    // If the Username and Password matches
				
				return true;
			}
			
			// If the Username or password does not match
			else{
				
				return false;
			}
            
        } catch(SQLException ex){
        	
            ex.printStackTrace();
            
            
        }finally{
        	
            try{
                dbConnect.getConnection().close();
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
		
		return false;
	}
	
	
	public String getUser(String userName, String password ) throws RemoteException{
		
		// SQL string to check whether the entered username and passwords match with the database
		String query = "SELECT * FROM User WHERE username = '"+userName+"'  AND password = '"+password+"' ";
		
		try{
			
            
            ResultSet rs = dbConnect.getConnection().executeQuery(query);
			
			
			// If the above query is executed, there should only be ONE record in the resultset
			// rs.last() means the final record in the ResultSet, but here there is only one record, so that record is the final one
			
			if(rs.last()){    // If the Username and Password matches
				
				String un = rs.getString(2);
				return un;
			}
			
			// If the Username or password does not match
			else{
				
				return "asd";
			}
            
        } catch(SQLException ex){
        	
            ex.printStackTrace();
            
            
        }finally{
        	
            try{
                dbConnect.getConnection().close();
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
		
		return "dsa";
	}
}
