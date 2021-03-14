package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

	 
public class Conexion {
	
	public static Connection conectar() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jardineria?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "admin", "4DM1n4DM1n");
		
		return connection;
			
	}

} 
