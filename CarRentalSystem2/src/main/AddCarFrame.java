package main;

import javax.swing.*;
import java.awt.event.*;

public class AddCarFrame extends JFrame {
    public AddCarFrame() {
        setTitle("Add New Car");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel brandLabel = new JLabel("Brand:");
        brandLabel.setBounds(50, 30, 100, 25);
        add(brandLabel);

        JTextField brandField = new JTextField();
        brandField.setBounds(150, 30, 180, 25);
        add(brandField);

        JLabel modelLabel = new JLabel("Model:");
        modelLabel.setBounds(50, 70, 100, 25);
        add(modelLabel);

        JTextField modelField = new JTextField();
        modelField.setBounds(150, 70, 180, 25);
        add(modelField);

        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setBounds(50, 110, 100, 25);
        add(yearLabel);

        JTextField yearField = new JTextField();
        yearField.setBounds(150, 110, 180, 25);
        add(yearField);

        JLabel priceLabel = new JLabel("Price per Day:");
        priceLabel.setBounds(50, 150, 100, 25);
        add(priceLabel);

        JTextField priceField = new JTextField();
        priceField.setBounds(150, 150, 180, 25);
        add(priceField);

        JButton addButton = new JButton("Add Car");
        addButton.setBounds(130, 220, 120, 30);
        add(addButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String brand = brandField.getText();
                String model = modelField.getText();
                int year = Integer.parseInt(yearField.getText());
                double pricePerDay = Double.parseDouble(priceField.getText());

                Car newCar = new Car(brand, model, year, pricePerDay, true);

                if (DatabaseManager.addCar(newCar)) {
                    JOptionPane.showMessageDialog(null, "Car added successfully!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add car.");
                }
            }
        });
    }
}
