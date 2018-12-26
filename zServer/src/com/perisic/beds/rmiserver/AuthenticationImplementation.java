package com.perisic.beds.rmiserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
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

	public boolean authenticate(String userName, char[] password)
			throws RemoteException {

		// SQL string to check whether the entered username and passwords match with the database
		String query = "SELECT * FROM User WHERE username = '"+userName+"'  AND password = '"+String.valueOf(password)+"' ";
		
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
	
	
	public String getUser(String userName, char[] password ) throws RemoteException{
		
		// SQL string to check whether the entered username and passwords match with the database
		String query = "SELECT type FROM User WHERE username = '"+userName+"'  AND password = '"+String.valueOf(password)+"' ";
		String un = "";
		try{
			
            
            ResultSet rs = dbConnect.getConnection().executeQuery(query);
			
			
			// If the above query is executed, there should only be ONE record in the resultset
			// rs.last() means the final record in the ResultSet, but here there is only one record, so that record is the final one
			
			if(rs.last()){    // If the Username and Password matches
				
			    un = rs.getString("type");
				return un;
			}
			
			// If the Username or password does not match
			else{
				  un ="No User";
				return un;
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
		
		return un;
	}

     
	@Override
	public boolean registerUser(String userName, char[] password, String type) throws RemoteException {
				String query = "INSERT INTO User(username, password, type) VALUES(?, ?, ?) ";
				try{
					PreparedStatement preparedStmt = dbConnect.getConn(query);
					preparedStmt.setString(1, userName);
					preparedStmt.setString(2, String.valueOf(password));
					preparedStmt.setString(3, type);
					preparedStmt.executeUpdate();
					
		        } catch(SQLException ex){
		            ex.printStackTrace();
		            
		            
		        }finally{
		            try{
		                dbConnect.getConnection().close();
		                
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		                return false;
		            }
		        }
				
				return true;
			}
}
