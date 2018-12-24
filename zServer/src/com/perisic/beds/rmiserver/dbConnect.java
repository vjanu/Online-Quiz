package com.perisic.beds.rmiserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class dbConnect {
	
	// Return the Statement object for executing queries
	public static Statement getConnection() throws SQLException{
		
		Statement st = getCon().createStatement();
		
		return st;
	}

	
	// Return the connection object to connect to the database
	private static Connection getCon() throws SQLException{
				
		//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		
		String url = "jdbc:mysql://localhost:3306/semquiz";
		
		String us = "root";
		String pw = "";
		
		Connection con = DriverManager.getConnection(url,us,pw);
		
		
		return con;
	}
		  
}
