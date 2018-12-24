package com.perisic.beds.peripherals;

import java.sql.*;

public class dbConnect {
	
	// Return the Statement object for executing queries
	public static Statement getConnection() throws SQLException{
		
		Statement st = getCon().createStatement();
		
		return st;
	}

	
	// Return the connection object to connect to the database
	private static Connection getCon() throws SQLException{
				
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		
		String url = "jdbc:mysql://localhost:3306/SemQuiz";
		
		String us = "root";
		String pw = "";
		
		Connection con = DriverManager.getConnection(url,us,pw);
		
		return con;
	}
		  
}
