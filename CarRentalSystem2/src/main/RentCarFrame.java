package main;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;

public class RentCarFrame extends JFrame {
    public RentCarFrame(int userId) {
        setTitle("Rent a Car");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel carIdLabel = new JLabel("Enter Car ID to Rent:");
        carIdLabel.setBounds(30, 30, 150, 25);
        add(carIdLabel);

        JTextField carIdField = new JTextField();
        carIdField.setBounds(170, 30, 120, 25);
        add(carIdField);

        JButton rentButton = new JButton("Rent");
        rentButton.setBounds(100, 80, 120, 30);
        add(rentButton);

        rentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int carId = Integer.parseInt(carIdField.getText());
                String rentalDate = LocalDate.now().toString();

                boolean success = DatabaseManager.rentCar(userId, carId, rentalDate);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Car rented successfully!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to rent car.");
                }
            }
        });
    }
}
