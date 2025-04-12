package main;

import javax.swing.*;
import java.awt.event.*;

public class AdminDashboard extends JFrame {

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome to the Admin Panel!");
        welcomeLabel.setBounds(100, 30, 250, 25);
        add(welcomeLabel);

        JButton addCarButton = new JButton("Add New Car");
        addCarButton.setBounds(100, 80, 200, 30);
        add(addCarButton);

        JButton viewRentalsButton = new JButton("View All Rentals");
        viewRentalsButton.setBounds(100, 130, 200, 30);
        add(viewRentalsButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(100, 180, 200, 30);
        add(logoutButton);

        // Action Listeners
        addCarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddCarFrame().setVisible(true);
            }
        });

        viewRentalsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewRentalsFrame().setVisible(true);
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