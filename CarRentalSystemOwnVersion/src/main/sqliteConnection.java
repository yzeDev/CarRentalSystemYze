package main;

import java.sql.*;
import javax.swing.*;

public class sqliteConnection {
	Connection conn = null;
	public static Connection getDBConnection() {
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\SQlite\\carRental.db");
			JOptionPane.showMessageDialog(null, "Connection to SQLite DB has been Successful!");
			return conn;
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			JOptionPane.showMessageDialog(null, "Connection to SQLite DB has been Unsuccessful!");
			return null;
		}
	}
	// User Login
	public static boolean userLogin(String username, String password) {
		String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
		try(Connection conn = getDBConnection();
			Statement pst = conn.createStatement();
			ResultSet rs = pst.executeQuery(query);
			){
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "User Login Successful!");
				return true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Invalid Credentials!");
				return false;
			}
		}catch(Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
			return false;
		}

	}
	// Admin Login
	public static boolean adminLogin(String username, String password) {
		String query = "SELECT * FROM admins WHERE username = '" + username + "' AND password = '" + password + "'";
		try(
			Connection conn = getDBConnection();
			Statement pst = conn.createStatement();
			ResultSet rs = pst.executeQuery(query);
			){
			if (rs.next()) {
				
				return true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Invalid Credentials!");
				return false;
			}
		}catch(Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
			return false;
		}

	}
}