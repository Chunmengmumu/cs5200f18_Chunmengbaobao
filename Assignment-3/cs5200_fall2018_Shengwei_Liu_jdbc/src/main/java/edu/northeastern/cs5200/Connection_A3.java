package edu.northeastern.cs5200;

import java.sql.*;

public class Connection_A3 {
	
	private static Connection_A3 instance = null;
	
	private Connection_A3() {}
	
	public static Connection_A3 getInstance() {
		if (instance == null)
			instance = new Connection_A3();
		return instance;
	}
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://cs5200-fall2018-shengweiliu.co1xraymzewt.us-east-2.rds.amazonaws.com:3306/cs5200_fall2018_Shengwei_Liu_jdbc";
	private static final String USER = "ShengweiLiu";
	private static final String PASSWORD = "kingisme";
	
	public static java.sql.Connection getConnection() throws ClassNotFoundException, SQLException {
	Class.forName(DRIVER);
	return  DriverManager.getConnection(URL, USER, PASSWORD);
	}
	
	public static void closeConnection(java.sql.Connection conn) {
	try {
	conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
}

