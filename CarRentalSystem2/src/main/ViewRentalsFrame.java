package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ViewRentalsFrame extends JFrame {
    public ViewRentalsFrame() {
        setTitle("All Rentals");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {"Rental ID", "User ID", "Car ID", "Rental Date", "Return Date"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        // Fetch data from database
        List<Rental> rentals = DatabaseManager.getAllRentals();
        for (Rental r : rentals) {
            Object[] row = {
                r.getId(),
                r.getUserId(),
                r.getCarId(),
                r.getRentalDate(),
                r.getReturnDate()
            };
            model.addRow(row);
        }
    }
}
