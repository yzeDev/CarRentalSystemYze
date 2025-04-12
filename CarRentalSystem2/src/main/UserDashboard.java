package main;

import javax.swing.*;
import java.awt.event.*;

public class UserDashboard extends JFrame {
	private int userId;

	public UserDashboard(int userId) {
        setTitle("User Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome to the User Panel!");
        welcomeLabel.setBounds(100, 30, 250, 25);
        add(welcomeLabel);

        JButton viewCarsButton = new JButton("View Available Cars");
        viewCarsButton.setBounds(100, 80, 200, 30);
        add(viewCarsButton);

        JButton rentCarButton = new JButton("Rent a Car");
        rentCarButton.setBounds(100, 130, 200, 30);
        add(rentCarButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(100, 180, 200, 30);
        add(logoutButton);

        // Add listeners
        viewCarsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewCarsFrame().setVisible(true);
            }
        });
        rentCarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RentCarFrame(userId).setVisible(true);
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginFrame().setVisible(true);
                dispose();
            }
        });
    }
}
