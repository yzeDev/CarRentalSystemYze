package main;

import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField UsernameField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Car Rental Login");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setForeground(SystemColor.windowBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblLogin.setBounds(174, 11, 86, 46);
		contentPane.add(lblLogin);
		
		JLabel lblUser = new JLabel("Email:");
		lblUser.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblUser.setBounds(153, 68, 65, 46);
		contentPane.add(lblUser);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblPassword.setBounds(108, 115, 110, 46);
		contentPane.add(lblPassword);
		
		UsernameField = new JTextField();
		UsernameField.setBounds(228, 82, 100, 20);
		contentPane.add(UsernameField);
		UsernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(228, 129, 100, 20);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String input = UsernameField.getText();
	                String password = new String(passwordField.getPassword());

	                int userId = DatabaseManager.userLogin(input, password);
	                if (userId != -1) {
	                    JOptionPane.showMessageDialog(null, "User login successful!");
	                    new UserDashboard(userId).setVisible(true);
	                    dispose();
	                } else if (DatabaseManager.adminLogin(input, password)) {
	                    JOptionPane.showMessageDialog(null, "Admin login successful!");
	                    new AdminDashboard().setVisible(true);
	                    dispose();
	                } else {
	                    JOptionPane.showMessageDialog(null, "Invalid credentials!");
	                }
	            }
		});
		btnLogin.setBounds(129, 172, 89, 23);
		contentPane.add(btnLogin);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(239, 172, 89, 23);
		contentPane.add(btnClear);
	}
}
