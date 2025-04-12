package main;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.sql.*;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginScreen {

	private JFrame frmCarRental;
	private JTextField loginUserTextField;
	private JPasswordField loginuserpasswordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen window = new LoginScreen();
					window.frmCarRental.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection = null;
	/**
	 * Create the application.
	 */
	public LoginScreen() {
		initialize();
		connection = sqliteConnection.getDBConnection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCarRental = new JFrame();
		frmCarRental.setTitle("Car Rental - Login");
		frmCarRental.setBounds(100, 100, 450, 300);
		frmCarRental.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCarRental.getContentPane().setLayout(null);
		
		JLabel lblLoginText = new JLabel("LOGIN");
		lblLoginText.setBounds(159, 0, 115, 54);
		lblLoginText.setFont(new Font("Pacifico", Font.BOLD, 30));
		frmCarRental.getContentPane().add(lblLoginText);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBorder(new TitledBorder(null, "Login Account", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(86, 67, 262, 164);
		frmCarRental.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 16, 250, 47);
		panel_2.add(panel);
		panel.setBackground(new Color(255, 255, 255));
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setFont(new Font("Quicksand Light", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 102, 25);
		panel.add(lblNewLabel);
		
		loginUserTextField = new JTextField();
		loginUserTextField.setBounds(122, 13, 116, 20);
		panel.add(loginUserTextField);
		loginUserTextField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 74, 250, 47);
		panel_2.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 255, 255));
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Quicksand Light", Font.BOLD, 20));
		lblPassword.setBounds(10, 11, 102, 25);
		panel_1.add(lblPassword);
		
		loginuserpasswordField = new JPasswordField();
		loginuserpasswordField.setColumns(10);
		loginuserpasswordField.setBounds(122, 13, 116, 20);
		panel_1.add(loginuserpasswordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = loginUserTextField.getText();
				String password = new String(loginuserpasswordField.getPassword());
				
				if (sqliteConnection.userLogin(username, password)) {
					JOptionPane.showMessageDialog(null, "User Login Successful!");
				} else if (sqliteConnection.adminLogin(username, password)){
					
					JOptionPane.showMessageDialog(null, "Admin Login Successful!");
				} else {
					JOptionPane.showMessageDialog(null, "Invalid Credentials!");
				}
			}
		});
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.setBackground(new Color(60, 179, 113));
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogin.setBounds(6, 132, 120, 23);
		panel_2.add(btnLogin);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginuserpasswordField.setText("");
				loginUserTextField.setText("");
			}
		});
		btnClear.setBackground(new Color(165, 42, 42));
		btnClear.setForeground(new Color(0, 0, 0));
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClear.setBounds(136, 132, 120, 23);
		panel_2.add(btnClear);
	
	}
}
